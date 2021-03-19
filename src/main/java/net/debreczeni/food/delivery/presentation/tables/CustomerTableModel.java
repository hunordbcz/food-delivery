package net.debreczeni.food.delivery.presentation.tables;

import net.debreczeni.food.delivery.model.Administrator;
import net.debreczeni.food.delivery.model.Customer;
import net.debreczeni.food.delivery.model.User;
import net.debreczeni.food.delivery.service.UserService;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class CustomerTableModel extends AbstractTableModel {
    private final static int ID = 0;
    private final static int NAME = 1;
    private final static int USERNAME = 2;
    private final static int NR_IDENTITY = 3;
    private final static int CNP = 4;
    private final static int ADDRESS = 5;
    private final static int IS_LOYAL = 6;
    private final static int IS_ADMIN = 7;
    private List<User> users;
    private final UserService userService = new UserService();
    public CustomerTableModel() {
        users = userService.getAll();
    }

    public void refreshTable() {
        users = userService.getAll();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        final User user = users.get(rowIndex);
        if (user instanceof Administrator) {
            switch (columnIndex) {
                case ID:
                    return user.getId();
                case NAME:
                    return user.getName();
                case USERNAME:
                    return user.getUsername();
                case IS_ADMIN:
                    return true;
                case NR_IDENTITY:
                case CNP:
                case ADDRESS:
                case IS_LOYAL:
                default:
                    return null;
            }
        }
        if (user instanceof Customer) {
            switch (columnIndex) {
                case ID:
                    return user.getId();
                case NAME:
                    return user.getName();
                case USERNAME:
                    return user.getUsername();
                case NR_IDENTITY:
                    return ((Customer) user).getNrIdentity();
                case CNP:
                    return ((Customer) user).getCnp();
                case ADDRESS:
                    return ((Customer) user).getAddress();
                case IS_LOYAL:
                    return ((Customer) user).getIsLoyal();
                case IS_ADMIN:
                    return false;
                default:
                    return null;
            }
        }

        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        final User user = users.get(rowIndex);
        if (user instanceof Administrator) {
            switch (columnIndex) {
                case NAME:
                    user.setName((String) aValue);
                    break;
                case USERNAME:
                    user.setUsername((String) aValue);
                    break;
                default:
                    break;
            }
        }
        if (user instanceof Customer) {
            switch (columnIndex) {
                case NAME:
                    user.setName((String) aValue);
                    break;
                case USERNAME:
                    user.setUsername((String) aValue);
                    break;
                case NR_IDENTITY:
                    ((Customer) user).setNrIdentity((String) aValue);
                    break;
                case CNP:
                    ((Customer) user).setCnp((Integer) aValue);
                    break;
                case ADDRESS:
                    ((Customer) user).setAddress((String) aValue);
                    break;
                case IS_LOYAL:
                    ((Customer) user).setIsLoyal((Boolean) aValue);
                    break;
                default:
                    break;
            }
        }

        userService.update(user);
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case ID:
                return "ID";
            case NAME:
                return "Name";
            case USERNAME:
                return "Username";
            case NR_IDENTITY:
                return "Nr Identity";
            case CNP:
                return "CNP";
            case ADDRESS:
                return "Address";
            case IS_LOYAL:
                return "Is Loyal";
            case IS_ADMIN:
                return "Is Admin";
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        boolean isAdmin = users.get(rowIndex) instanceof Administrator;
        switch (columnIndex) {
            case NAME:
            case USERNAME:
                return true;
            case NR_IDENTITY:
            case CNP:
            case ADDRESS:
            case IS_LOYAL:
                return !isAdmin;
            case IS_ADMIN:
            case ID:
            default:
                return false;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
            case CNP:
                return Integer.class;
            case NAME:
            case NR_IDENTITY:
            case USERNAME:
            case ADDRESS:
                return String.class;
            case IS_LOYAL:
            case IS_ADMIN:
                return Boolean.class;
            default:
                return String.class;
        }
    }
}
