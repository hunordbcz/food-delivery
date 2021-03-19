package net.debreczeni.food.delivery.presentation;

import javax.swing.*;
import java.awt.*;

public class CustomerFrame extends JFrame{
    private JTabbedPane tabbedPane;
    private JPanel dashboardPanel;
    private JButton addButton;
    private JTable table1;
    private JButton removeButton;
    private JTable table2;

    public CustomerFrame(Component relativeTo) {
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(this.dashboardPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(relativeTo);
    }
}
