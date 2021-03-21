package net.debreczeni.food.delivery;

import net.debreczeni.food.delivery.presentation.LoginFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

        final LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);
    }
}
