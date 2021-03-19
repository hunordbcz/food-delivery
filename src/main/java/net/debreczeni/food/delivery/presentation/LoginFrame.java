package net.debreczeni.food.delivery.presentation;

import net.debreczeni.food.delivery.bll.UserBLL;
import net.debreczeni.food.delivery.exceptions.InvalidCredentialsException;
import net.debreczeni.food.delivery.model.Administrator;
import net.debreczeni.food.delivery.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JPanel loginPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton enterButton;
    private JButton registerButton;

    private final UserBLL userBLL = new UserBLL();

    public LoginFrame(Component relativeTo){
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(this.loginPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(relativeTo);

        enterButton.addActionListener(e -> {
            try {
                final User user = userBLL.login(usernameField.getText(),passwordField.getText());

                if(user instanceof Administrator){
                    final CustomerFrame customerFrame = new CustomerFrame(this);
                    customerFrame.setVisible(true);
                }

                //Successful login
                final JFrame homepage = userBLL.getHomepage(user).apply(this);
                homepage.setVisible(true);
                this.dispose();
            } catch (InvalidCredentialsException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Login message", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> {
            RegisterFrame registerFrame = new RegisterFrame(this);
            registerFrame.setVisible(true);
            this.dispose();
        });
    }

    public LoginFrame() throws HeadlessException {
        this(null);
    }
}
