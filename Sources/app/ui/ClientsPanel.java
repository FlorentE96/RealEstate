package app.ui;

import app.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;

public class ClientsPanel
        extends JPanel
        implements MouseListener, ActionListener
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private JTable clientTable;
    private Agent agent;
    private JPopupMenu rightClickMenu, rightClickMenuLimited;

    /**
     * Constructeur d'objets de classe PainelConsulta
     */
    public ClientsPanel(Agent _agent)
    {
        agent = _agent;

        clientTable = new JTable(getTableModel());
        JScrollPane scrollPane = new JScrollPane(clientTable);
        clientTable.setFillsViewportHeight(true);
        rightClickMenu = new JPopupMenu();
        rightClickMenuLimited = new JPopupMenu();

        JMenuItem deleteClient = new JMenuItem("Delete app.Client");
        JMenuItem editClient = new JMenuItem("Edit app.Client");
        JMenuItem addClient = new JMenuItem("Add new client");
        JMenuItem addClient2 = new JMenuItem("Add new client"); // Can't add one item to 2 menus somehow
        JMenuItem addProperty = new JMenuItem("Add property to client");

        deleteClient.addActionListener(this);
        editClient.addActionListener(this);
        addClient.addActionListener(this);
        addClient2.addActionListener(this);
        addProperty.addActionListener(this);

        deleteClient.setActionCommand("delete client");
        editClient.setActionCommand("edit client");
        addClient.setActionCommand("add client");
        addClient2.setActionCommand("add client");
        addProperty.setActionCommand("add property");

        rightClickMenu.add(deleteClient);
        rightClickMenu.add(editClient);
        rightClickMenu.add(addProperty);
        rightClickMenu.addSeparator();
        rightClickMenu.add(addClient);

        rightClickMenuLimited.add(addClient2);

        clientTable.addMouseListener(this);

        this.add(scrollPane);
    }

    //getTableModel
    private DefaultTableModel getTableModel(){
        String[] columnNames = {"ID","Name","Income","Num. Prop."};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0){
            public boolean isCellEditable(int row, int column){
                return false; // This causes all cells to be not editable
            }};
        for (Client client : agent.getClientList()) {
            tableModel.addRow(new Object[] {
                    client.getID(),
                    client.getName(),
                    client.getIncome(),
                    client.getNumPropertiesOwned()
                    });
        }
        return tableModel;
    }

    public void updateTable()
    {
        clientTable.setModel(getTableModel());
    }

    public void mousePressed(MouseEvent e)
    {

    }
    public void mouseReleased(MouseEvent e) {
        Point point = e.getPoint();
        int rowAtPoint = clientTable.rowAtPoint(point);
        if (e.isPopupTrigger())
        {
            if (rowAtPoint >= 0 && rowAtPoint < clientTable.getRowCount()) {
                rightClickMenu.show(e.getComponent(), e.getX(), e.getY());
            }
            else {
                rightClickMenuLimited.show(e.getComponent(), e.getX(), e.getY());
            }
        }
        if (rowAtPoint >= 0 && rowAtPoint < clientTable.getRowCount())
        {
            clientTable.setRowSelectionInterval(rowAtPoint, rowAtPoint);

            if(e.getClickCount() == 2)
            {
                JDialog dialog = new PropertyListDialog(null, agent.getClient(clientTable.getSelectedRow()));
            }
        }
        else
        {
            clientTable.clearSelection();
        }
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if(command.equals("delete client"))
        {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this client?","Confirm", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                agent.removeClient(clientTable.getSelectedRow());
            }
        }
        else if(command.equals("edit client"))
        {
            Client clientToEdit = agent.getClient(clientTable.getSelectedRow());
            RegisterClientDialog myRegisterClientDialog = new RegisterClientDialog(null, clientToEdit);
        }
        else if(command.equals("add client"))
        {
            RegisterClientDialog myRegisterClientDialog = new RegisterClientDialog(null);
            Client client = myRegisterClientDialog.getRegisteredClient();
            if(client != null)
                agent.addClient(client);
        }
        else if(command.equals("add property"))
        {
            RegisterPropertyDialog myRegisterPropertyDialog = new RegisterPropertyDialog(null);
            Property property = myRegisterPropertyDialog.getReturnedProperty();
            agent.getClient(clientTable.getSelectedRow()).addProperty(property);
        }
        this.updateTable();
    }
}