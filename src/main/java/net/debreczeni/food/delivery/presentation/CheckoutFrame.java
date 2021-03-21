package net.debreczeni.food.delivery.presentation;

import net.debreczeni.food.delivery.bll.OrderBLL;
import net.debreczeni.food.delivery.model.Customer;
import net.debreczeni.food.delivery.model.Item;
import net.debreczeni.food.delivery.model.PaymentType;
import net.debreczeni.food.delivery.presentation.tables.CustomItemTableModel;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class CheckoutFrame extends JFrame {
    private final OrderBLL orderBLL = new OrderBLL();
    private JPanel mainPanel;
    private JTable itemsTable;
    private JTextField deliveryAddressField;
    private JRadioButton cashRadioButton;
    private JRadioButton cardRadioButton;
    private JButton submitOrderButton;
    private JLabel itemsValueField;
    private JLabel discountValueField;
    private JLabel totalValueField;
    private final Double itemsValue;
    private Double discountValue = 0D;
    private final Double totalValue;



    public CheckoutFrame(Component relativeTo, Customer customer, List<Item> items) throws HeadlessException {
        this.setTitle(this.getClass().getSimpleName());
        this.setContentPane(this.mainPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(relativeTo);

        itemsValue = items.stream().map(Item::getPrice).reduce(0D, Double::sum);
        if (customer.getIsLoyal()) {
            discountValue = items.stream().map(Item::getPrice).reduce(0D, Double::sum) * 0.05;
        }
        totalValue = itemsValue - discountValue;

        itemsValueField.setText(String.format("%.2f", itemsValue));
        discountValueField.setText(String.format("%.2f", discountValue));
        totalValueField.setText(String.format("%.2f", totalValue));

        deliveryAddressField.setText(customer.getAddress());
        ButtonGroup bg = new ButtonGroup();
        bg.add(cashRadioButton);
        bg.add(cardRadioButton);

        CustomItemTableModel itemsTableModel = new CustomItemTableModel(items);
        itemsTable.setModel(itemsTableModel);

        submitOrderButton.addActionListener(e -> {
            try {
                orderBLL.makeOrder(
                        customer,
                        items,
                        deliveryAddressField.getText(),
                        cashRadioButton.isSelected() ? PaymentType.CASH : PaymentType.CARD,
                        discountValue,
                        totalValue
                );

                this.dispose();
            } catch (Exception ex) {

            }
        });
    }
}
