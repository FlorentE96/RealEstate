package app.ui;

import app.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class PropertyListDialog
        extends JDialog
        implements MouseListener, ActionListener
{
    private JPopupMenu rightClickMenu, rightClickMenuLimited;
    private JTable propertyTable;

    private Client client;

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

    private DefaultTableModel getTableModel(){
        String[] columnNames = {"Type", "Address","Num. Rooms","Size","Garden", "Garage", "Pool", "Terrace", "Elevator", "Floor", "Number", "price"};
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

    private void updateTable()
    {
        propertyTable.setModel(getTableModel());
    }

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
