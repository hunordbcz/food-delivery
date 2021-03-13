package net.debreczeni.food.delivery.presentation;

import net.debreczeni.food.delivery.bll.UserBLL;
import net.debreczeni.food.delivery.exceptions.InvalidCredentialsException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton enterButton;

    private final UserBLL userBLL = new UserBLL();

    public LoginFrame() throws HeadlessException {
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(this.loginPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        enterButton.addActionListener(e -> {
            try {
                userBLL.login(usernameField.getText(),passwordField.getText());
            } catch (InvalidCredentialsException invalidCredentialsException) {
                invalidCredentialsException.printStackTrace();
            }
        });
    }
}
