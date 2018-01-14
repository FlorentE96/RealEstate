package app.ui;

import app.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 * <b>PropertyListDialog is a dialog that shows a table with all the details of a specific client.</b>
 * This is a modal dialog, which mean that as long as it is displayed, it blocks the execution of the calling thread.
 * It is set visible directly upon creation.
 * <p>From this dialog, the user can : </p>
 * <ul>
 *     <li>Review all the information of a client's properties</li>
 *     <li>Remove a client's property from the list</li>
 *     <li>Add a property to a client</li>
 *     <li>Edit a client's property</li>
 * </ul>
 * <p>The list can be updated to reflect the new changes, using the <code>updateTable</code> method.</p>
 *
 * @see Property
 * @see ClientsPanel
 *
 * @author  Florent
 * @version 1.0
 */
public class PropertyListDialog
        extends JDialog
        implements MouseListener, ActionListener
{
    /**
     * Pop up menus for a right-click on a property or outside the list.
     */
    private JPopupMenu rightClickMenu, rightClickMenuLimited;

    /**
     * Table containing all the details of the properties.
     *
     * @see PropertyListDialog#getTableModel()
     */
    private JTable propertyTable;

    /**
     * Client of which the properties will be detailed.
     */
    private Client client;

    /**
     * Constructor for class <code>PropertyListDialog</code>.
     * Initializes the dialog as modal, and adds all the components.
     * The dialog is set visible directly upon creation.
     *
     * @param owner
     * @param _client
     */
    public PropertyListDialog(JFrame owner, Client _client)
    {
        super(owner, "Property list of "+_client.getName());

        rightClickMenu = new JPopupMenu();
        rightClickMenuLimited = new JPopupMenu();

        client = _client;
        propertyTable = new JTable(this.getTableModel());
        JScrollPane scrollPane = new JScrollPane(propertyTable);
        propertyTable.setFillsViewportHeight(true);
        JMenuItem deleteProperty = new JMenuItem("Delete Property");
        JMenuItem addProperty = new JMenuItem("Add New Property");
        JMenuItem addProperty2 = new JMenuItem("Add New Property"); // Can't add one item to 2 menus somehow

        deleteProperty.addActionListener(this);
        addProperty.addActionListener(this);
        addProperty2.addActionListener(this);

        deleteProperty.setActionCommand("delete Property");
        addProperty.setActionCommand("add Property");
        addProperty2.setActionCommand("add Property");

        rightClickMenu.add(deleteProperty);
        rightClickMenu.addSeparator();
        rightClickMenu.add(addProperty);

        rightClickMenuLimited.add(addProperty2);

        propertyTable.addMouseListener(this);
        this.add(scrollPane);
        this.pack();
        this.setResizable(true);
        this.setModal(true);
        this.setVisible(true);
    }

    /**
     * Creates a model for displaying the property table.
     * For each property, these fields are displayed :
     * <ul>
     *    <li>The type of property ('H' for House or 'A' for Apartment)</li>
     *    <li>The number of rooms</li>
     *    <li>The size</li>
     *    <li>The presence of a garden (when applicable)</li>
     *    <li>The presence of a garage</li>
     *    <li>The presence of a pool (when applicable)</li>
     *    <li>The presence of a terrace (when applicable)</li>
     *    <li>The floor of the property (when applicable)</li>
     *    <li>The apartment number (when applicable)</li>
     *    <li>The selling price</li>
     * </ul>
     * When a field is not applicable (for example the elevator field for a house), "NA" is displayed in the cell.
     * The cells of the table are not editable.
     *
     * @return The table model.
     *
     * @see Property
     * @see House
     * @see Apartment
     */
    private DefaultTableModel getTableModel(){
        String[] columnNames = {"Type", "Address","Num. Rooms","Size","Garden", "Garage", "Pool", "Terrace", "Elevator", "Floor", "Number", "Price"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0){
            public boolean isCellEditable(int row, int column){
                return false; // This causes all cells to be not editable
            }};
        for (Property property : client.getPropertyList()) {
            if(property instanceof House) {
                tableModel.addRow(new Object[]{
                        "H",
                        property.getAddress(),
                        property.getNumRoom(),
                        property.getSize(),
                        ((House) property).isHasGarden(),
                        property.isHasGarage(),
                        ((House) property).isHasPool(),
                        "NA",
                        "NA",
                        "NA",
                        "NA",
                        property.getPrice()
                });
            }
            else if(property instanceof Apartment)
            {
                tableModel.addRow(new Object[]{
                        "A",
                        property.getAddress(),
                        property.getNumRoom(),
                        property.getSize(),
                        "NA",
                        property.isHasGarage(),
                        "NA",
                        ((Apartment) property).isHasTerrace(),
                        ((Apartment) property).isHasElevator(),
                        ((Apartment) property).getFloor(),
                        ((Apartment) property).getNumber(),
                        property.getPrice()
                });
            }
        }
        return tableModel;
    }

    /**
     * Updates the table's information based on the client.
     */
    private void updateTable()
    {
        propertyTable.setModel(getTableModel());
    }

    /**
     * Overridden method of <code>MouseListener</code>.
     * The mouse event treated in this method are :
     * <ul>
     *     <li>A right-click : shows the appropriate pop-up menu depending on where the right-click was made.</li>
     *     <li>A double left-click : opens the <code>RegisterPropertyDialog</code> in edition mode
     *     for the corresponding property.</li>
     *     <li>A left click outside the table : clears the selection</li>
     * </ul>
     *
     * @param e The <code>MouseEvent</code> which triggered the MouseListener.
     *
     * @see MouseListener
     * @see RegisterClientDialog
     *
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        Point point = e.getPoint();
        int rowAtPoint = propertyTable.rowAtPoint(point);
        if (e.isPopupTrigger())
        {
            if (rowAtPoint >= 0 && rowAtPoint < propertyTable.getRowCount()) {
                rightClickMenu.show(e.getComponent(), e.getX(), e.getY());
            }
            else {
                rightClickMenuLimited.show(e.getComponent(), e.getX(), e.getY());
            }
        }
        if (rowAtPoint >= 0 && rowAtPoint < propertyTable.getRowCount())
        {
            propertyTable.setRowSelectionInterval(rowAtPoint, rowAtPoint);

            if(e.getClickCount() == 2)
            {
                JDialog dialog = new RegisterPropertyDialog(null, client.getProperty(propertyTable.getSelectedRow()));
                this.updateTable();
            }
        }
        else
        {
            propertyTable.clearSelection();
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
     *     <li>Delete Property : removes the corresponding property from the client's list</li>
     *     <li>Add Property : opens a <code>RegisterPropertyDialog</code> and add the resulting property to the
     *     client's list.</li>
     * </ul>
     *
     * @param e The <code>ActionEvent</code> which triggered the listener.
     *
     * @see RegisterPropertyDialog
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if(command.equals("delete Property"))
        {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this property?","Confirm", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.YES_OPTION){
                client.removeProperty(propertyTable.getSelectedRow());
            }
        }
        else if(command.equals("add Property"))
        {
            RegisterPropertyDialog myRegisterPropertyDialog = new RegisterPropertyDialog(null);
            Property property = myRegisterPropertyDialog.getReturnedProperty();
            if(property != null)
                client.addProperty(property);
        }
        this.updateTable();
    }
}
