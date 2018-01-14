package app.ui;

import app.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PropertyListDialog
        extends JDialog
{

    private Client client;

    public PropertyListDialog(JFrame owner, Client _client)
    {
        super(owner, "Property list of "+_client.getName());



        client = _client;
        JTable propertyTable = new JTable(this.getTableModel());
        JScrollPane scrollPane = new JScrollPane(propertyTable);

        this.add(scrollPane);
        this.pack();
        this.setResizable(true);
        this.setModal(true);
        this.setVisible(true);
    }


    //getTableModel
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
}
