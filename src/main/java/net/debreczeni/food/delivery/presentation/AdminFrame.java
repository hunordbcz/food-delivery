package net.debreczeni.food.delivery.presentation;

import net.debreczeni.food.delivery.presentation.tables.CustomerTableModel;
import net.debreczeni.food.delivery.service.UserService;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {
    final UserService userService = new UserService();

    private JTabbedPane tabbedPane;
    private JPanel dashboardPanel;
    private JTable itemTable;
    private JButton button1;
    private JButton backToLoginButton;
    private JTable customerTable;
    private JScrollPane customersTable;

    public AdminFrame(Component relativeTo) {
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(this.dashboardPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(relativeTo);

        final CustomerTableModel customerTableModel = new CustomerTableModel();
        customerTable.setModel(customerTableModel);
    }
}
