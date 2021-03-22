package net.debreczeni.food.delivery.repository;

import net.debreczeni.food.delivery.model.HasID;
import net.debreczeni.food.delivery.util.ConnectionFactory;
import net.debreczeni.food.delivery.util.Pair;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static java.lang.reflect.Modifier.isStatic;
import static net.debreczeni.food.delivery.repository.SQL.ORDER_TYPE.ASC;
import static net.debreczeni.food.delivery.repository.SQL.ORDER_TYPE.DESC;


/**
 * Used to make the connection between the DB and Application
 *
 * @param <T> Defines the current type
 */
abstract class AbstractRepository<T extends HasID> implements Repository<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractRepository.class.getName());

    private final List<String> fieldNames;
    private final Class<T> type;
    private final SQL<T> queries;

    @SuppressWarnings("unchecked")
    protected AbstractRepository(String tableName) {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.fieldNames = getDeclaredFields();
        this.queries = new SQL<>(tableName, fieldNames);
    }

    /**
     * Get the fields names for the current type
     *
     * @return List of Strings that contains the names
     */
    private List<String> getDeclaredFields() {
        return Arrays.stream(type.getDeclaredFields())
                .filter(field -> !isStatic(field.getModifiers()))
                .map(Field::getName)
                .collect(Collectors.toList());
    }

    /**
     * Get the field values for a given object
     *
     * @param t       The object
     * @param sqlType The type of query that will be ran afterwards
     * @return List of Objects that contain the values of the fields
     */
    private List<Object> getFieldValues(T t, SQL.STATEMENT_TYPE sqlType) throws IllegalAccessException {
        List<Object> fieldValues = new LinkedList<>();
        for (Field field : type.getDeclaredFields()) {
            if (sqlType == SQL.STATEMENT_TYPE.INSERT && field.getName().equals("id") || isStatic(field.getModifiers())) {
                continue;
            }

            PropertyDescriptor propertyDescriptor;
            try {
                propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                Method method = propertyDescriptor.getReadMethod();
                Object value = method.invoke(t);
                fieldValues.add(value);
            } catch (IntrospectionException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return fieldValues;
    }

    /**
     * Sends an update query to the DB
     *
     * @param query  The query that is executed
     * @param values The values that are added to the query
     * @return The number of rows changed / added
     */
    private Integer sendUpdate(String query, List<Object> values) {
        synchronized(AbstractRepository.class){
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);

                if (values != null) {
                    for (int i = 0; i < values.size(); i++) {
                        final Object value = values.get(i);
                        if (value == null) {
                            statement.setNull(i + 1, Types.OTHER);
                        } else {
                            statement.setObject(i + 1, value);
                        }
                    }
                }

                return statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, type.getName() + "::sendUpdate " + e.getMessage());
            } finally {
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
        }
        return 0;
    }

    /**
     * Send simple query to the DB
     *
     * @param query  The query that is executed
     * @param values The values that are added to the query
     * @return List of parsed objects from the result of the query
     */
    private List<T> sendQuery(String query, List<Object> values) {
        synchronized (AbstractRepository.class){
            Connection connection = null;
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {
                connection = ConnectionFactory.getConnection();
                statement = connection.prepareStatement(query);

                if (values != null) {
                    for (int i = 0; i < values.size(); i++) {
                        statement.setObject(i + 1, values.get(i));
                    }
                }

                resultSet = statement.executeQuery();

                return createObjects(resultSet);
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, type.getName() + "::sendQuery " + e.getMessage());
            } finally {
                ConnectionFactory.close(resultSet);
                ConnectionFactory.close(statement);
                ConnectionFactory.close(connection);
            }
        }
        return null;
    }

    /**
     * Makes a select query and executes it
     *
     * @param rules List of Pairs of String and Object, that is used to filter the data
     * @param order Ascending or Descending
     * @return List of parsed objects from the result of the query
     */
    public List<T> select(List<Pair<String, Object>> rules, SQL.ORDER_TYPE order) {
        List<String> fields = new LinkedList<>();
        List<Object> values = new LinkedList<>();

        if (rules != null) {
            for (Pair<String, Object> rule : rules) {
                fields.add(rule.first);
                values.add(rule.second);
            }
        }

        String query;
        if (order == DESC) {
            query = queries.createDescSelectQuery(fields);
        } else {
            query = queries.createSelectQuery(fields);
        }

        return sendQuery(query, values);
    }

    public List<T> selectBigger(List<Pair<String, Object>> rules, SQL.ORDER_TYPE order) {
        List<String> fields = new LinkedList<>();
        List<Object> values = new LinkedList<>();

        if (rules != null) {
            for (Pair<String, Object> rule : rules) {
                fields.add(rule.first);
                values.add(rule.second);
            }
        }

        String query = queries.createSelectQueryBigger(fields);
        return sendQuery(query, values);
    }

    /**
     * Makes an insert query and executes it
     *
     * @param t The object whose values will be inserted in the DB
     * @return True on success | False on failure
     */
    public Boolean insert(T t) {
        List<Object> values = null;
        try {
            values = getFieldValues(t, SQL.STATEMENT_TYPE.INSERT);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        String query = queries.createInsertQuery(t);
        return sendUpdate(query, values) != 0;
    }

    /**
     * Makes an update query and executes it
     *
     * @param t The object whose values will be updated in the DB
     * @return True on success | False on failure
     */
    public Boolean update(T t) {
        List<Object> referenceValues = null;
        try {
            referenceValues = getFieldValues(t, SQL.STATEMENT_TYPE.UPDATE);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        assert referenceValues != null;
        referenceValues.add(referenceValues.get(0));
        referenceValues.remove(referenceValues.get(0));


        String query = queries.createUpdateQuery(t, Collections.singletonList("id"));
        return sendUpdate(query, referenceValues) != 0;
    }

    public Boolean delete(T t) {
        String query = queries.createDeleteQuery(Collections.singletonList("id"));
        return sendUpdate(query, Collections.singletonList(t.getId())) != 0;
    }

    /**
     * Makes a delete query and executes it
     *
     * @param rules List of Pairs of String and Object, that is used to filter the data
     * @return True on success | False on failure
     */
    public Boolean delete(List<Pair<String, Object>> rules) {
        List<String> fields = new LinkedList<>();
        List<Object> values = new LinkedList<>();

        if (rules == null) {
            return false;
        }

        for (Pair<String, Object> rule : rules) {
            fields.add(rule.first);
            values.add(rule.second);
        }

        String query = queries.createDeleteQuery(fields);

        return sendUpdate(query, values) != 0;
    }

    /**
     * @return The last element of current type in the DB
     */
    public T findLast() {
        return this.select(null, DESC).get(0);
    }

    /**
     * @return All the elements of current type in the DB
     */
    public List<T> findAll() {
        return this.select(null, ASC);
    }

    /**
     * Find an element of current type by ID in the DB
     *
     * @param id The given id to search for
     * @return The object if found | NULL if no element was found
     */
    public T findByID(int id) {
        List<Pair<String, Object>> rules = new LinkedList<>();
        rules.add(new Pair<>("id", id));

        List<T> response = select(rules, ASC);
        if (response != null && !response.isEmpty()) {
            return response.get(0);
        }
        return null;
    }


    /**
     * Creates objects from the result of a query that was made
     *
     * @param resultSet The result from a made query
     * @return List of Objects of current type
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        Method method;
        try {
            while (resultSet.next()) {
                T instance = type.newInstance();
                for (String fieldName : fieldNames) {
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);

                    if (value != null) {
                        method = type.getMethod(propertyDescriptor.getWriteMethod().getName(), value.getClass());
                        method.invoke(instance, value);
                    }
                }
                list.add(instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}