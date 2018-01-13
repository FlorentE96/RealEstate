package app.ui;

import app.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class SalePanel
        extends JPanel
        implements ActionListener {
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

        this.setLayout(new BorderLayout());
        this.add(mainPanel);
    }

    private DefaultListModel<Property> getListModel() {
        DefaultListModel<Property> listModel = new DefaultListModel<Property>();
        if (clientFromCombo.getSelectedItem() != null) {
            ArrayList<Property> propertyList = (ArrayList<Property>) ((Client) clientFromCombo.getSelectedItem()).getPropertyList();
            for (Property property : propertyList) {
                listModel.addElement(property);
            }
        }
        return listModel;
    }

    private DefaultComboBoxModel<Client> getComboBoxModel() {
        DefaultComboBoxModel<Client> comboBoxModel = new DefaultComboBoxModel<Client>();
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

    public void updateValues()
    {
        updatePropertyList();
        updateClientFrom();
        updateClientTo();
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        JComboBox cb = (JComboBox) e.getSource();
        this.updatePropertyList();
    }

}
