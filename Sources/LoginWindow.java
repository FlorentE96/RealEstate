import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;

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


//    private String myKey;
    /**
     * Constructor of LoginWindow.
     * Sets the window's title, and configures the panes/menu.
     * The isLogged variable is set to <i>false</i>.
     */
    public LoginWindow()
    {
        super("Login");
//        myKey = "Mary has one cat"; // NOTE: has to be 16 bytes
        isLogged = false;

        myLoginPanel = new LoginPanel(this);
        this.setResizable(false);
        this.setContentPane(makePanel());
//        this.setJMenuBar(makeMenu());
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
            String login = myLoginPanel.getLogin();
            char[] correctPassword = this.getPassword(login);
            if(correctPassword != null) {
                char[] typedPassword = myLoginPanel.getPassword();
                if (typedPassword.length != correctPassword.length) {
                    isLogged = false;
                } else {
                    isLogged = Arrays.equals(typedPassword, correctPassword);
                }
            }
            else
            {
                isLogged = false;
            }

            if(!isLogged)
            {
                JOptionPane.showMessageDialog(null, "Login or password incorrect.");
            }
        }
        else if(command.equals("register"))
        {
            // TODO : check if already existing
            // TODO : register informations (ID name etc.)
            RegisterAgentDialog myDialog = new RegisterAgentDialog(this);
            Agent agentReturned = myDialog.getAgentReturned();
            if(agentReturned != null)
            {
                registerNewUser(myLoginPanel.getLogin(), myLoginPanel.getPassword());
                agentReturned.saveAgentInfo();
                JOptionPane.showMessageDialog(null, "Registration successful");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Registration cancelled");
            }
            myLoginPanel.clearGUIValues();
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

        this.setVisible(false);
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
        try {
            File passwordFile = new File("passwords.txt");
            FileWriter fw = new FileWriter(passwordFile, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(_login);
            bw.write(",");
            bw.write(_password);
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private char[] getPassword(String _login)
    {
        try {
            File passwordFile = new File("passwords.txt");
            FileReader fr = new FileReader(passwordFile);
            BufferedReader br = new BufferedReader(fr);

            while (br.ready()) {
                String line = br.readLine();
                String[] csvData = line.split(",");
                if (csvData[0].equals(_login)) {
                    return csvData[1].toCharArray();
                }
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No user has been registered...\n Please register a user first");
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }
}
