package net.debreczeni.food.delivery.presentation;

import net.debreczeni.food.delivery.bll.OrderBLL;
import net.debreczeni.food.delivery.model.Order;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ReportSetupFrame extends JFrame {
    private final OrderBLL orderBLL = new OrderBLL();
    private JPanel mainPanel;
    private JButton showOrdersButton;
    private JSlider dateSlider;
    private JLabel firstDateField;

    public ReportSetupFrame(Component relativeTo) {
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(relativeTo);

        List<Order> orders = orderBLL.findAll().stream().sorted((a,b) -> a.getCreatedAt().compareTo(b.getCreatedAt())).collect(Collectors.toList());
        if(orders.isEmpty()){
            JOptionPane.showMessageDialog(this, "There are no orders", "Missing orders", JOptionPane.WARNING_MESSAGE);
        }
        firstDateField.setText(orders.get(0).getCreatedAt().toString());
    }
}
