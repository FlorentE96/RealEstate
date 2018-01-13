package app.ui;

import app.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterClientDialog
    extends JDialog
    implements ActionListener
{
    private JTextField nameField;
    private JTextField incomeField;
    private JButton registerButton;
    private JButton cancelButton;
    private JPanel mainPanel;

    private Client client;

    public RegisterClientDialog(JFrame owner)
    {
        super(owner, "Register app.Client");

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

    public RegisterClientDialog(JFrame owner, Client _client)
    {
        super(owner, "Register app.Client");

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

    public Client getRegisteredClient()
    {
        return client;
    }
}
