package app.ui;

import app.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LoginPanel extends JPanel
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private JTextField textID;
    private JPasswordField textPassword;
    private JButton buttonLogin, buttonRegister;

    public LoginPanel(ActionListener _listener)
    {
        JPanel titlePanel, loginPanel, buttonPanel;
        titlePanel = new JPanel();
        loginPanel = new JPanel();
        buttonPanel = new JPanel();

        JLabel titleLabel, idLabel, passwordLabel;

        textID = new JTextField(15);
        textPassword= new JPasswordField(15);
        buttonLogin = new JButton("Login");
        buttonRegister = new JButton("Register");

        buttonLogin.setActionCommand("login");
        buttonLogin.addActionListener(_listener);

        buttonRegister.setActionCommand("register");
        buttonRegister.addActionListener(_listener);

        idLabel = new JLabel("ID:");
        passwordLabel = new JLabel("Password:");
        titleLabel = new JLabel("Login");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.GRAY);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        loginPanel.setLayout(new GridLayout(3,2));
        titlePanel.setPreferredSize(new Dimension(0, 60));
        titlePanel.add(titleLabel);
        loginPanel.add(idLabel);
        loginPanel.add(textID);
        loginPanel.add(passwordLabel);
        loginPanel.add(textPassword);
        buttonPanel.add(buttonLogin);
        buttonPanel.add(buttonRegister);

        this.add(titlePanel);
        this.add(loginPanel);
        this.add(buttonPanel);

    }

    public String getLogin() { return this.textID.getText(); }

    public char[] getPassword() { return this.textPassword.getPassword(); }

    public void clearGUIValues()
    {
        textID.setText("");
        textPassword.setText("");
    }
}
