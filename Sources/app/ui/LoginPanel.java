package app.ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * <b>LoginPanel is the panel for the login window, containing all the components.</b>
 * <p>It contains the 'login' and 'password' fields, as well as 2 buttons : 'login' and 'register'.</p>
 * <p>The fields' values can be retrieved via getters, and the UI can be cleared using
 * the <code>clearGUIValues</code> method.</p>
 *
 * @see LoginWindow
 * @see JPanel
 *
 * @author  Florent
 * @version 1.0
 */
public class LoginPanel extends JPanel
{
    /**
     * Text field for the user's login.
     */
    private JTextField textID;

    /**
     * Hidden text field for the password.
     */
    private JPasswordField textPassword;

    /**
     * Constructor for the login panel.
     *
     * @param _listener the <code>ActionListener</code> that will treat the buttons' events.
     *
     * @see ActionListener
     * @see LoginWindow
     */
    public LoginPanel(ActionListener _listener)
    {
        JPanel titlePanel, loginPanel, buttonPanel;
        titlePanel = new JPanel();
        loginPanel = new JPanel();
        buttonPanel = new JPanel();

        JLabel titleLabel, idLabel, passwordLabel;

        textID = new JTextField(15);
        textPassword= new JPasswordField(15);
        JButton buttonLogin = new JButton("Login");
        JButton buttonRegister = new JButton("Register");

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

    /**
     * Getter for the login entered.
     *
     * @return the login typed by the user (String).
     */
    public String getLogin() { return this.textID.getText(); }

    /**
     * Getter for the password typed.
     *
     * @return the password as character's array (for safety reasons).
     */
    public char[] getPassword() { return this.textPassword.getPassword(); }

    /**
     * Method to clear the field's content.
     */
    public void clearGUIValues()
    {
        textID.setText("");
        textPassword.setText("");
    }
}
