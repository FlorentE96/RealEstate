package app.ui;

import app.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;

/**
 * <b><code>ClientsPanel</code> is a panel listing all of an agent's clients.</b>
 * <p>From there, the agent can :</p>
 * <ul>
 *     <li>See all his clients and their information</li>
 *     <li>Remove one of his existing clients</li>
 *     <li>Edit a client's information</li>
 *     <li>Add a new client</li>
 *     <li>Open a dialog listing a client's properties (see <code>PropertyListDialog</code> class)</li>
 * </ul>
 * <p>The panel can be updated to reflect the new client list using the <code>updateTable()</code> method.</p>
 *
 * @see Client
 * @see PropertyListDialog
 * @see MainWindow
 *
 * @author  Florent
 * @version 1.0
 */
public class ClientsPanel
        extends JPanel
        implements MouseListener, ActionListener
{
    /**
     * The table component, with the details of the clients
     */
    private JTable clientTable;

    /**
     * The agent (user) of which the list of clients has to be displayed.
     * It is passed via the constructor.
     *
     * @see ClientsPanel(Agent)
     */
    private Agent agent;

    /**
     * Pop up menus for a right-click on a client or outside the list.
     */
    private JPopupMenu rightClickMenu, rightClickMenuLimited;

    /**
     * Constructor for <code>ClientPanel</code>.
     *
     * @param _agent The agent (user) of which the list of clients has to be displayed.
     */
    public ClientsPanel(Agent _agent)
    {
        agent = _agent;

        clientTable = new JTable(getTableModel());
        JScrollPane scrollPane = new JScrollPane(clientTable);
        clientTable.setFillsViewportHeight(true);
        rightClickMenu = new JPopupMenu();
        rightClickMenuLimited = new JPopupMenu();

        JMenuItem deleteClient = new JMenuItem("Delete Client");
        JMenuItem editClient = new JMenuItem("Edit Client");
        JMenuItem addClient = new JMenuItem("Add new client");
        JMenuItem addClient2 = new JMenuItem("Add new client"); // Can't add one item to 2 menus somehow

        deleteClient.addActionListener(this);
        editClient.addActionListener(this);
        addClient.addActionListener(this);
        addClient2.addActionListener(this);

        deleteClient.setActionCommand("delete client");
        editClient.setActionCommand("edit client");
        addClient.setActionCommand("add client");
        addClient2.setActionCommand("add client");

        rightClickMenu.add(deleteClient);
        rightClickMenu.add(editClient);
        rightClickMenu.addSeparator();
        rightClickMenu.add(addClient);

        rightClickMenuLimited.add(addClient2);

        clientTable.addMouseListener(this);

        this.add(scrollPane);
    }

    /**
     * Creates a model for displaying the client table.
     * For each client, these fields are displayed :
     * <ul>
     *    <li>His ID</li>
     *    <li>His name</li>
     *    <li>His income</li>
     *    <li>The number of property he owns</li>
     * </ul>
     * The cells of the table are not editable.
     *
     * @return The table model.
     */
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

    /**
     * Updates the table's information based on the agent.
     */
    public void updateTable()
    {
        clientTable.setModel(getTableModel());
    }

    /**
     * Overridden method of <code>MouseListener</code>.
     * The mouse event treated in this method are :
     * <ul>
     *     <li>A right-click : shows the appropriate pop-up menu depending on where the right-click was made.</li>
     *     <li>A double left-click : opens the <code>PropertyListDialog</code> for the corresponding client.</li>
     *     <li>A left click outside the table : clears the selection</li>
     * </ul>
     *
     * @param e The <code>MouseEvent</code> which triggered the MouseListener.
     *
     * @see MouseListener
     * @see PropertyListDialog
     *
     */
    @Override
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
                this.updateTable();
            }
        }
        else
        {
            clientTable.clearSelection();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    /**
     * Overridden method of <code>ActionListener</code>.
     * Treats the events triggered by the different items of the pop-up menus :
     * <ul>
     *     <li>Delete Client : removes the corresponding client from the agent's list</li>
     *     <li>Edit Client : opens a <code>RegisterClientDialog</code> and passes the corresponding client
     *     to its constructor</li>
     *     <li>Add Client : opens a <code>RegisterClientDialog</code></li>
     * </ul>
     *
     * @param e The <code>ActionEvent</code> which triggered the listener.
     *
     * @see RegisterClientDialog
     */
    @Override
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
        this.updateTable();
    }
}