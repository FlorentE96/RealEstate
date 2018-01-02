import java.awt.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class LoginPanel extends JPanel
                        implements ActionListener
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private JTextField textID;
    private JPasswordField textPassword;
    private JButton buttonLogin;

    /**
     * Constructeur d'objets de classe PainelCadastro
     */
    public LoginPanel()
    {
        JPanel titlePanel, loginPanel, buttonPanel;
        titlePanel = new JPanel();
        loginPanel = new JPanel();
        buttonPanel = new JPanel();

        JLabel titleLabel, idLabel, passwordLabel;

        textID = new JTextField(15);
        textPassword= new JPasswordField(15);
        buttonLogin = new JButton("Login");

        buttonLogin.setActionCommand("login");
        buttonLogin.addActionListener(this);

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

        this.add(titlePanel);
        this.add(loginPanel);
        this.add(buttonPanel);

    }

    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if(command.equals("add"))
        {
        }
    }

    public void clearGUIValues()
    {
        textID.setText("");
        textPassword.setText("");
    }
}
