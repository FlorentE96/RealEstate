package app.ui;

import app.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * <b><code>RegisterPropertyDialog</code> is a dialog containing a form for registering a new property.</b>
 * This is a modal dialog, which mean that as long as it is displayed, it blocks the execution of the calling thread.
 * It is set visible directly upon creation and remains visible until either of the "register" or "cancel" button
 * has been pressed.
 *
 * <p>Two types of properties exist : houses and apartment. Thus, we use a <code>CardLayout</code>
 * to alter the form depending on the chosen type. The type is chosen via a <code>ComboBox</code>.</p>
 *
 * <p>The common fields to both types are :</p>
 * <ul>
 *     <li>The address</li>
 *     <li>The numbers of rooms</li>
 *     <li>The price</li>
 *     <li>The size</li>
 *     <li>The presence of a garage</li>
 * </ul>
 *
 * <p>The additional fields for apartments are : </p>
 * <ul>
 *     <li>The presence of a terrace</li>
 *     <li>The presence of an elevator</li>
 *     <li>The floor</li>
 *     <li>The number</li>
 * </ul>
 *
 * <p>The additional fields for houses are : </p>
 * <ul>
 *     <li>The presence of a garden</li>
 *     <li>The presence of a pool</li>
 * </ul>
 *
 * <p>The registered property can be retrieved using the <code>getRegisteredClient</code> method.</p>
 * <p>The dialog can be constructed with an existing property as a parameter, to edit this property instead of
 * creating a new one. <strong>In that case, the type of property becomes read only.</strong></p>
 *
 * <p>The panel has been designed with IntelliJ's GUI designer.</p>
 *
 * @see Property
 *
 * @author  Florent
 * @version 1.0
 */
public class RegisterPropertyDialog
        extends JDialog
        implements ActionListener, ItemListener
{
    /**
     * Main panel designed with IntelliJ's GUI designer.
     */
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

    /**
     * The registered/edited property
     */
    private Property property;

    /**
     * Default constructor for class <code>RegisterPropertyDialog</code>.
     * The dialog is not resizable.
     *
     * @param owner The frame that called the dialog.
     */
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

    /**
     * Overloaded constructor of <code>RegisterPropertyDialog</code>.
     * This constructor is used to create a dialog that edits an existing property.
     * Depending on the property type, the dialogs fields are filled with the existing property's data,.
     * The property type <strong>cannot</strong> be changed.
     * The "Register" button's caption is changed to "Edit".
     *
     * @param owner The frame that called the dialog
     * @param _property The property to be edited
     */
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

    /**
     * Overridden method of <code>ActionListener</code>.
     * Treats the events triggered by the 2 buttons :
     * <ul>
     *     <li>Register/Edit : populates/edit the property with fields' data, and hides the dialog</li>
     *     <li>Cancel : hides the window without instantiating/altering the property.</li>
     * </ul>
     *
     * @param ev The <code>ActionEvent</code> which triggered the listener.
     *
     * @see Client#generateID()
     */
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

    /**
     * Overridden method of <code>ItemListener</code>
     * Is triggered every time the property type is changed, and shows the appropriate card of the card panel.
     *
     * @param evt the <code>ItemEvent</code> that triggered the method.
     *
     * @see ItemEvent
     * @see CardLayout
     */
    @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout) (cardPanel.getLayout());
        cl.show(cardPanel, (String) evt.getItem());
    }
}
