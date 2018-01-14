package app.ui;

import app.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterPropertyDialog
        extends JDialog
        implements ActionListener, ItemListener
{
    private JPanel mainPanel;
    private JComboBox<String> typeComboBox;
    private JPanel cardPanel;
    private JPanel housePanel;
    private JPanel apartmentPanel;
    private JLabel typeLabel;
    private JSpinner aptNumSpinner;
    private JSpinner floorSpinner;
    private JCheckBox garageCheckBox;
    private JCheckBox terraceCheckBox;
    private JCheckBox elevatorCheckBox;
    private JTextField priceField;
    private JTextField addressField;
    private JTextField sizeField;
    private JCheckBox garageCheckBox1;
    private JCheckBox gardenCheckBox;
    private JCheckBox poolCheckBox;
    private JButton registerButton;
    private JButton cancelButton;
    private JPanel buttonPanel;
    private JSpinner numRoomsSpinner;

    private Property property;

    public RegisterPropertyDialog(JFrame owner) {
        super(owner, "Register Property");

        typeComboBox.addItem("House");
        typeComboBox.addItem("Appartment");
        typeComboBox.setEditable(false);
        typeComboBox.addItemListener(this);

        priceField.setHorizontalAlignment(SwingConstants.RIGHT);
        sizeField.setHorizontalAlignment(SwingConstants.RIGHT);


        aptNumSpinner.setModel(new SpinnerNumberModel(1, 0, 300, 1));
        floorSpinner.setModel(new SpinnerNumberModel(0, -5, 20, 1));
        numRoomsSpinner.setModel(new SpinnerNumberModel(3, 1, 30, 1));

        registerButton.addActionListener(this);
        cancelButton.addActionListener(this);
        registerButton.setActionCommand("register");
        cancelButton.setActionCommand("cancel");

        this.add(mainPanel);
        this.pack();
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);
    }

    public RegisterPropertyDialog(JFrame owner, Property _property) {
        super(owner, "Register Property");

        property = _property;

        typeComboBox.addItem("House");
        typeComboBox.addItem("Appartment");
        typeComboBox.setEditable(false);
        typeComboBox.addItemListener(this);

        priceField.setHorizontalAlignment(SwingConstants.RIGHT);
        sizeField.setHorizontalAlignment(SwingConstants.RIGHT);

        registerButton.setText("Edit");

        typeComboBox.setEnabled(false);

        aptNumSpinner.setModel(new SpinnerNumberModel(1, 0, 300, 1));
        floorSpinner.setModel(new SpinnerNumberModel(0, -5, 20, 1));
        numRoomsSpinner.setModel(new SpinnerNumberModel(3, 1, 30, 1));

        registerButton.addActionListener(this);
        cancelButton.addActionListener(this);
        registerButton.setActionCommand("register");
        cancelButton.setActionCommand("cancel");

        addressField.setText(property.getAddress());
        sizeField.setText(Double.toString(property.getSize()));
        priceField.setText(Double.toString(property.getPrice()));
        numRoomsSpinner.setValue(property.getNumRoom());
        garageCheckBox.setSelected(property.isHasGarage());
        garageCheckBox1.setSelected(property.isHasGarage());

        if(property instanceof House)
        {
            gardenCheckBox.setSelected(((House) property).isHasGarden());
            poolCheckBox.setSelected(((House) property).isHasPool());
        }
        else if(property instanceof Apartment)
        {
            terraceCheckBox.setSelected(((Apartment) property).isHasTerrace());
            elevatorCheckBox.setSelected(((Apartment) property).isHasElevator());
            aptNumSpinner.setValue(((Apartment) property).getNumber());
            floorSpinner.setValue(((Apartment) property).getFloor());
        }

        this.add(mainPanel);
        this.pack();
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);
    }

    public Property getReturnedProperty()
    {
        return property;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        String command = ev.getActionCommand();
        if (command.equals("register")) {
            if (addressField.getText().equals("") || priceField.getText().equals("") || sizeField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please fill up all the fields");
                return;
            }
            if(property == null) {
                try {
                    String address = addressField.getText();
                    double price = Double.parseDouble(priceField.getText());
                    double size = Double.parseDouble(sizeField.getText());
                    int numRooms = (int) numRoomsSpinner.getValue();
                    boolean hasGarage = garageCheckBox.isSelected();
                    if (typeComboBox.getSelectedItem() == null) // useless but removes a warning
                        return;
                    if (typeComboBox.getSelectedItem().equals("House")) {
                        boolean hasGarden = gardenCheckBox.isSelected();
                        boolean hasPool = poolCheckBox.isSelected();

                        property = new House(address, numRooms, size, price, hasGarage, hasGarden, hasPool);
                    } else {
                        boolean hasTerrace = terraceCheckBox.isSelected();
                        boolean hasElevator = elevatorCheckBox.isSelected();

                        int floor = (int) floorSpinner.getValue();
                        int aptNum = (int) aptNumSpinner.getValue();

                        property = new Apartment(address, numRooms, size, price, hasGarage, hasTerrace, hasElevator, floor, aptNum);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid price");
                    return;
                }
            }
            else
            {
                try {
                    property.setAddress(addressField.getText());
                    property.setPrice(Double.parseDouble(priceField.getText()));
                    property.setSize(Double.parseDouble(sizeField.getText()));
                    property.setNumRoom((int) numRoomsSpinner.getValue());
                    property.setHasGarage(garageCheckBox.isSelected());
                    if (typeComboBox.getSelectedItem() == null) // useless but removes a warning
                        return;
                    if (property instanceof House) {
                        ((House) property).setHasGarden(gardenCheckBox.isSelected());
                        ((House) property).setHasPool(poolCheckBox.isSelected());
                    } else if (property instanceof Apartment){
                        ((Apartment) property).setHasTerrace(terraceCheckBox.isSelected());
                        ((Apartment) property).setHasElevator(elevatorCheckBox.isSelected());

                        ((Apartment) property).setFloor((int) floorSpinner.getValue());
                        ((Apartment) property).setNumber((int) aptNumSpinner.getValue());
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid price");
                    return;
                }
            }
            this.setVisible(false);
        } else if (command.equals("cancel")) {
            this.setVisible(false);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        cl.show(cardPanel, (String) evt.getItem());
    }
}
