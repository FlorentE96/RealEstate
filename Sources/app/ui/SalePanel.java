package app.ui;

import app.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

public class SalePanel
        extends JPanel
        implements ActionListener, ListSelectionListener
{
    private JList<Property> propertyList;
    private Agent agent;
    private JButton makeSaleButton;
    private JComboBox<Client> clientFromCombo;
    private JComboBox<Client> clientToCombo;
    private JTextField priceField;
    private JLabel ownerLabel;
    private JPanel mainPanel;

    public SalePanel(Agent _agent) {
        agent = _agent;

        clientFromCombo.setModel(getComboBoxModel());
        clientToCombo.setModel(getComboBoxModel());
        clientFromCombo.addActionListener(this);

        propertyList.setModel(this.getListModel());
        propertyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        propertyList.setVisibleRowCount(5);
        propertyList.addListSelectionListener(this);

        makeSaleButton.addActionListener(this);
        makeSaleButton.setActionCommand("sell");

        this.setLayout(new BorderLayout());
        this.add(mainPanel);
    }

    private DefaultListModel<Property> getListModel() {
        DefaultListModel<Property> listModel = new DefaultListModel<>();
        if (clientFromCombo.getSelectedItem() != null) {
            ArrayList<Property> propertyList = (ArrayList<Property>) ((Client) clientFromCombo.getSelectedItem()).getPropertyList();
            for (Property property : propertyList) {
                listModel.addElement(property);
            }
        }
        return listModel;
    }

    private DefaultComboBoxModel<Client> getComboBoxModel() {
        DefaultComboBoxModel<Client> comboBoxModel = new DefaultComboBoxModel<>();
        for (Client client : agent.getClientList()) {
            comboBoxModel.addElement(client);
        }
        return comboBoxModel;
    }

    private void updatePropertyList() {
        propertyList.setModel(this.getListModel());
    }

    private void updateClientTo() {
        clientToCombo.setModel(this.getComboBoxModel());
    }

    private void updateClientFrom() {
        clientFromCombo.setModel(this.getComboBoxModel());
    }

    private void updateAdvisedPrice() {
        int index = propertyList.getSelectedIndex();
        if(index >= 0) {
            priceField.setText(Double.toString(propertyList.getModel().getElementAt(index).getPrice()));
        }
        else
        {
            priceField.setText("");
        }
    }

    public void updateValues()
    {
        updatePropertyList();
        updateClientFrom();
        updateClientTo();
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if(command.equals("sell"))
        {
            if(clientFromCombo.getSelectedItem() == clientToCombo.getSelectedItem()) {
                JOptionPane.showMessageDialog(null, "Please select a seller different from the buyer...");
                return;
            }
            if(propertyList.isSelectionEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please select a property...");
                return;
            }
            try {
                agent.makeSale((Client) clientFromCombo.getSelectedItem(),
                        (Client) clientToCombo.getSelectedItem(),
                        propertyList.getModel().getElementAt(propertyList.getSelectedIndex()),
                        Double.parseDouble(priceField.getText()));

                JOptionPane.showMessageDialog(null, "Sold! Your balance has been updated.");
            }
            catch (NumberFormatException ex)
            {
                JOptionPane.showMessageDialog(null, "Please enter a correct amount");
                return;
            }
        }
        this.updatePropertyList();
    }

    public void valueChanged(ListSelectionEvent e)
    {
        this.updateAdvisedPrice();
    }
}
