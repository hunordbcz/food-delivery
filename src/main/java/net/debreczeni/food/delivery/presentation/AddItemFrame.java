package net.debreczeni.food.delivery.presentation;

import net.debreczeni.food.delivery.bll.ItemBLL;

import javax.swing.*;
import java.awt.*;

public class AddItemFrame extends JFrame {
    private final ItemBLL itemBLL = new ItemBLL();
    private JTextField priceField;
    private JTextField nameField;
    private JButton submitButton;
    private JPanel mainPanel;

    public AddItemFrame(Component relativeTo) throws HeadlessException {
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(relativeTo);

        submitButton.addActionListener(e -> {
            try {
                itemBLL.addNewItem(nameField.getText(), priceField.getText());
                this.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error occurred", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
}
