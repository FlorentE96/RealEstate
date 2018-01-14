package app.ui;

import app.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;

/**
 * <b><code>SalePanel</code> is a panel allowing the user to register a sale.</b>
 * <p>To register a sale, an agent has to select :</p>
 * <ul>
 *     <li>A selling client</li>
 *     <li>A buying client (different from the seller)</li>
 *     <li>The property to be sold</li>
 *     <li>A price</li>
 * </ul>
 *
 * <p>The panel has been designed with IntelliJ's GUI designer.</p>
 *
 * @see Client
 * @see Property
 * @see MainWindow
 *
 * @author  Florent
 * @version 1.0
 */
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

    /**
     * Main panel designed with IntelliJ's GUI designer.
     */
    private JPanel mainPanel;

    /**
     * Default constructor for <code>ClientPanel</code>.
     *
     * @param _agent The user logged in the program.
     */
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

    /**
     * Creates the model to display the property list.
     *
     * @return the list model
     *
     * @see Property
     */
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

    /**
     * Creates the model to display the client combo boxes.
     *
     * @return the list model
     *
     * @see Client
     */
    private DefaultComboBoxModel<Client> getComboBoxModel() {
        DefaultComboBoxModel<Client> comboBoxModel = new DefaultComboBoxModel<>();
        for (Client client : agent.getClientList()) {
            comboBoxModel.addElement(client);
        }
        return comboBoxModel;
    }

    /**
     * Updates the property list depending on the selected selling client.
     */
    private void updatePropertyList() {
        propertyList.setModel(this.getListModel());
    }

    /**
     * Update the list of buying clients based on the agent's list.
     */
    private void updateClientTo() {
        clientToCombo.setModel(this.getComboBoxModel());
    }

    /**
     * Update the list of selling clients based on the agent's list.
     */
    private void updateClientFrom() {
        clientFromCombo.setModel(this.getComboBoxModel());
    }

    /**
     * Updates the displayed reference price of the property, based on its price attribute.
     */
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

    /**
     * Updates every component of the dialog.
     */
    public void updateValues()
    {
        updatePropertyList();
        updateClientFrom();
        updateClientTo();
    }

    /**
     * Overridden method of <code>ActionListener</code>.
     * Treats the events triggered by "sell" button :
     * if all the fields are correctly filled, the sale is made and the panel is updated.
     *
     * @param e The <code>ActionEvent</code> which triggered the listener.
     *
     * @see Agent#makeSale(Client, Client, Property, double)
     */
    @Override
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

    /**
     * Overridden method of <code>ListSelectionListener</code>.
     * Is triggered every time the selected property is changed, and updates the advised price accordingly.
     *
     * @param e
     *
     * @see ListSelectionListener
     */
    @Override
    public void valueChanged(ListSelectionEvent e)
    {
        this.updateAdvisedPrice();
    }
}
