package net.debreczeni.food.delivery.presentation;

import net.debreczeni.food.delivery.bll.UserBLL;
import net.debreczeni.food.delivery.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {
    private JPanel registerPanel;
    private JTextField username;
    private JPasswordField password;
    private JPasswordField passwordConfirmation;
    private JTextField nrIdentity;
    private JTextField cnp;
    private JTextField address;
    private JButton submitButton;
    private JButton backToLoginButton;
    private JTextField nameField;

    private final UserBLL userBLL = new UserBLL();

    public RegisterFrame(Component relativeTo){
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(this.registerPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(relativeTo);

        backToLoginButton.addActionListener(e -> {
            final LoginFrame loginFrame = new LoginFrame(this);
            loginFrame.setVisible(true);
            this.dispose();
        });
        submitButton.addActionListener(e -> {
            userBLL.register(
                    nameField.getText(),
                    username.getText(),
                    password.getText(),
                    passwordConfirmation.getText(),
                    nrIdentity.getText(),
                    cnp.getText(),
                    address.getText()
            );
        });
    }
}
