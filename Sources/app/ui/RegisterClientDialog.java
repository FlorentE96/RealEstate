package app.ui;

import app.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <b><code>RegisterClientDialog</code> is a dialog containing a form for registering a new client.</b>
 * This is a modal dialog, which mean that as long as it is displayed, it blocks the execution of the calling thread.
 * It is set visible directly upon creation and remains visible until either of the "register" or "cancel" button
 * has been pressed.
 * <p>Two fields have to be filled to register a client : </p>
 * <ul>
 *     <li>His name</li>
 *     <li>His income</li>
 * </ul>
 * <p>The registered client can be retrieved using the <code>getRegisteredClient</code> method.</p>
 * <p>The dialog can be constructed with an existing client as a parameter, to edit this client instead of
 * creating a new one.</p>
 *
 * <p>The panel has been designed with IntelliJ's GUI designer.</p>
 *
 * @see Client
 * @see ClientsPanel
 *
 * @author  Florent
 * @version 1.0
 */
public class RegisterClientDialog
    extends JDialog
    implements ActionListener
{
    private JTextField nameField;
    private JTextField incomeField;
    private JButton registerButton;
    private JButton cancelButton;

    /**
     * Main panel designed with IntelliJ's GUI designer.
     */
    private JPanel mainPanel;

    /**
     * The registered/edited client.
     */
    private Client client;

    /**
     * Default constructor for class <code>RegisterClientDialog</code>.
     * The dialog is not resizable.
     *
     * @param owner The frame that called the dialog.
     */
    public RegisterClientDialog(JFrame owner)
    {
        super(owner, "Register Client");

        registerButton.addActionListener(this);
        registerButton.setActionCommand("register");
        cancelButton.addActionListener(this);
        cancelButton.setActionCommand("cancel");

        this.add(mainPanel);
        this.pack();
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);
    }

    /**
     * Overloaded constructor of <code>RegisterClientDialog</code>.
     * This constructor is used to create a dialog that edits an existing client.
     * The dialogs fields are filled with the existing client's data, and the "Register" button's caption
     * is changed to "Edit".
     *
     * @param owner The frame that called the dialog.
     * @param _client The client to edit.
     */
    public RegisterClientDialog(JFrame owner, Client _client)
    {
        super(owner, "Register Client");

        client = _client;

        nameField.setText(client.getName());
        incomeField.setText(Double.toString(client.getIncome()));

        registerButton.setText("Edit");

        registerButton.addActionListener(this);
        registerButton.setActionCommand("register");
        cancelButton.addActionListener(this);
        cancelButton.setActionCommand("cancel");

        this.add(mainPanel);
        this.pack();
        this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);
    }

    public Client getRegisteredClient() { return client; }

    /**
     * Overridden method of <code>ActionListener</code>.
     * Treats the events triggered by the 2 buttons :
     * <ul>
     *     <li>Register/Edit: populates/edit the client with fields' data, generate his unique ID if the client is new,
     *     and hides the dialog</li>
     *     <li>Cancel: hides the window without instantiating/altering the agent.</li>
     * </ul>
     *
     * @param e The <code>ActionEvent</code> which triggered the listener.
     *
     * @see Client#generateID()
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if(command.equals("register"))
        {
            if(client == null) {
                try {
                    client = new Client(nameField.getText(), 0, Double.parseDouble(incomeField.getText()));
                    client.generateID();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number");
                    return;
                }
            }
            else
            {
                try {
                    client.setName(nameField.getText());
                    client.setIncome(Double.parseDouble(incomeField.getText()));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number");
                    return;
                }
            }
            this.setVisible(false);
        }
        else if(command.equals("cancel"))
        {
            this.setVisible(false);
        }
    }
}
