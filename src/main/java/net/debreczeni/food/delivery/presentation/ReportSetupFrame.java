package net.debreczeni.food.delivery.presentation;

import net.debreczeni.food.delivery.bll.OrderBLL;
import net.debreczeni.food.delivery.exceptions.InvalidInput;
import net.debreczeni.food.delivery.model.Order;
import net.debreczeni.food.delivery.presentation.tables.OrderTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReportSetupFrame extends JFrame {
    private final OrderBLL orderBLL = new OrderBLL();
    private Timestamp currentStartInterval;
    private JPanel mainPanel;
    private JButton showOrdersButton;
    private JSlider dateSlider;
    private JLabel firstDateField;
    private JLabel fromField;

    public ReportSetupFrame(Component relativeTo) throws InvalidInput {
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(relativeTo);

        List<Order> orders = orderBLL.findAll(true).stream().sorted(Comparator.comparing(Order::getCreatedAt)).collect(Collectors.toList());
        if (orders.isEmpty()) {
            this.dispose();
            throw new InvalidInput("There are no orders");
        }

        final Timestamp firstDate = orders.get(0).getCreatedAt();
        final Timestamp now = new Timestamp(new Date().getTime());

        long difference = now.getTime() - firstDate.getTime();
        firstDateField.setText(firstDate.toString());
        fromField.setText(firstDate.toString());
        currentStartInterval = firstDate;
        dateSlider.addChangeListener(l -> {
            currentStartInterval = new Timestamp(firstDate.getTime() + difference * dateSlider.getValue() / 100);
            fromField.setText(currentStartInterval.toString());
        });

        showOrdersButton.addActionListener(e -> {
            final OrdersListFrame ordersListFrame = new OrdersListFrame(this, "Report", orderBLL.findFrom(currentStartInterval));
            ordersListFrame.setVisible(true);
        });
    }
}
