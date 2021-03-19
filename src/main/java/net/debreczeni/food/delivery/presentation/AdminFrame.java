package net.debreczeni.food.delivery.presentation;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame{
    private JTabbedPane tabbedPane;
    private JPanel dashboardPanel;
    private JTable customerTable;
    private JTable itemTable;
    private JButton button1;
    private JButton backToLoginButton;

    public AdminFrame(Component relativeTo) {
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(this.dashboardPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(relativeTo);
    }
}
