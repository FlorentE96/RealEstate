import javax.swing.*;
import java.awt.event.*;
import java.io.*;


/**
 * <b>LoginWindow is the dialog for loging a user</b>
 * <p>To log in, you need :
 * <ul>
 *     <li>A login</li>
 *     <li>A password</li>
 * </ul></p>
 *
 * <p>Using the "File" menu, you can register a new user, or quit the program</p>
 * <p>To launch the login dialog, call the person#showLoginDialog() method.
 * This method returns an @Agent object if the log was successful.</p>
 * <p>The @Agent object returned contains information loaded from an encrypted CSV file (@loadFromeFile(int _login).</p>
 *
 * @see     LoginPanel
 * @see     Agent
 *
 * @author  Florent
 * @version 0.1
 */
public class LoginWindow extends JFrame implements ActionListener
{
    /**
     * Login panel, containing all the widgets of the login window.
     *
     * @see LoginPanel
     * @see LoginWindow#makePanel()
     */
    private LoginPanel myLoginPanel;
    /**
     * A flag to know if an user has been correctly logged.
     *
     * @see LoginWindow#actionPerformed(ActionEvent)
     * @see LoginWindow#showLoginDialog()
     */
    private boolean isLogged;

    /**
     * Constructor of LoginWindow.
     * Sets the window's title, and configures the panes/menu.
     * The isLogged variable is set to <i>false</i>.
     */
    public LoginWindow()
    {
        super("Login");

        isLogged = false;

        myLoginPanel = new LoginPanel(this);

        this.setContentPane(makePanel());
        this.setJMenuBar(makeMenu());
    }

    /**
     * Creates the main login panel.
     *
     * @see LoginPanel
     */
    private JPanel makePanel()
    {
        JPanel mainPanel = new JPanel();
        mainPanel.add(myLoginPanel);

        return mainPanel;
    }

    private JMenuBar makeMenu()
    {
        //Barras de menus são compostas por menus
        JMenuBar mbr_barra = new JMenuBar();
        JMenu fileMenu;
        JMenuItem newUser, quit;


        fileMenu = new JMenu("File");
        mbr_barra.add(fileMenu);

        newUser = new JMenuItem("New User");
        quit = new JMenuItem("Quit");

        fileMenu.add(newUser);
        fileMenu.addSeparator();
        fileMenu.add(quit);

        newUser.setActionCommand("new");
        newUser.addActionListener(this);
        quit.setActionCommand("quit");
        quit.addActionListener(this);

        return mbr_barra;
    }

    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if(command.equals("new"))
        {
        }
        else if(command.equals("login"))
        {
            // TODO : check the password and login
            // TODO : if wrong password, diplay error dialog
            // TODO : if right password, set login
            String login = myLoginPanel.getLogin();
            char[] password = myLoginPanel.getPassword();
            isLogged = true;
        }
        else if(command.equals("register"))
        {
            registerNewUser(myLoginPanel.getLogin(), myLoginPanel.getPassword());
        }
        else if(command.equals("quit"))
            System.exit(0);
    }

    public Agent showLoginDialog()
    {

        this.pack();
        this.setVisible(true);

        while(!isLogged)
        {
            try {
                Thread.sleep(100); // Give time to other threads to process events
            } catch (InterruptedException ie) {
            }
        }

        return loadFromFile(myLoginPanel.getLogin());
    }

    private Agent loadFromFile(String _login)
    {
        // TODO : read name
        String name = "";
        // TODO : read ID
        int ID = 123;
        // TODO : read salary
        double salary = 0.0;
        // TODO : read sales balance
        double salesBalance = 0.0;

        return new Agent(name, ID, salary, salesBalance);
    }

    private void registerNewUser(String _login, char[] _password) {
        String myKey = "Mary has one cat"; // NOTE: has to be 16 bytes
        String loginEncrypted;
        String passwordEncrypted;
        try {
            File passwordFile = new File("passwords.txt");
            FileWriter fw = new FileWriter(passwordFile, true);
            BufferedWriter bw = new BufferedWriter(fw);

            loginEncrypted = EncryptionUtils.encrypt(myKey, _login);
            passwordEncrypted = EncryptionUtils.encrypt(myKey, _password);

            bw.write(loginEncrypted + ";" + passwordEncrypted + "\n");
            bw.close();
            fw.close();
        } catch (EncryptionException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
