import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class SalePanel
        extends JPanel
        implements ActionListener
{
    private JList<Property> propertyList;
    private Agent agent;
    private JButton makeSaleButton;
    private JComboBox<Client> clientFromCombo;
    private JComboBox<Client> clientToCombo;
    private JTextField priceField;
    private JLabel ownerLabel;
    private JPanel mainPanel;

    public SalePanel(Agent _agent)
    {
        agent = _agent;

        clientFromCombo.setModel(getComboBoxModel());
        clientToCombo.setModel(getComboBoxModel());
        clientFromCombo.addActionListener(this);

        propertyList = new JList<>(getListModel());
        propertyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        propertyList.setVisibleRowCount(5);

        JScrollPane scrollPane = new JScrollPane(propertyList);
        this.setLayout(new BorderLayout());
        this.add(mainPanel);
//        this.add(clientFromCombo);
//        this.add(clientToCombo);
//        this.add(scrollPane);
    }

    private DefaultListModel<Property> getListModel(){
        DefaultListModel<Property> listModel = new DefaultListModel<Property>();
        if(clientFromCombo.getSelectedItem() != null) {
            ArrayList<Property> propertyList = (ArrayList<Property>) ((Client) clientFromCombo.getSelectedItem()).getPropertyList();
            for (Property property : propertyList) {
                listModel.addElement(property);
            }
        }
        return listModel;
    }

    private DefaultComboBoxModel<Client> getComboBoxModel(){
        DefaultComboBoxModel<Client> comboBoxModel = new DefaultComboBoxModel<Client>();
        for (Client client : agent.getClientList()) {
            comboBoxModel.addElement(client);
        }
        return comboBoxModel;
    }

    public void updatePropertyList()
    {
        propertyList.setModel(this.getListModel());
    }

    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        JComboBox cb = (JComboBox)e.getSource();
        this.updatePropertyList();
    }
}
