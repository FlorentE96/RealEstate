package app.ui;

import app.*;
import javax.swing.*;
import javax.swing.text.StyledEditorKit;
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
 * This method returns an @app.Agent object if the log was successful.</p>
 * <p>The @app.Agent object returned contains information loaded from an encrypted CSV file (@loadFromeFile(int _login).</p>
 *
 * @see     LoginPanel
 * @see     Agent
 *
 * @author  Florent
 * @version 0.1
 */
public class LoginWindow
        extends JFrame
        implements ActionListener
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
     *
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
        this.pack();
        this.setVisible(true);
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
                    isLogged = true;
                    this.setVisible(false);
                    Agent user = loadFromFile(myLoginPanel.getLogin());
                    MainWindow myMainWindow = new MainWindow(null, user);
                    saveToFile(user, myLoginPanel.getLogin());
                    System.exit(0);
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
            RegisterAgentDialog myDialog = new RegisterAgentDialog(this);
            Agent agentReturned = myDialog.getAgentReturned();

            if(agentReturned != null)
            {
                agentReturned.generateID();
                saveToFile(agentReturned, myLoginPanel.getLogin());
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

    private Agent loadFromFile(String _login)
    {
        Agent agent = new Agent();
        File file = new File(_login + ".csv");
        FileReader fr = null;
        BufferedReader br = null;
        String line;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            line = br.readLine();

            int numCients = 0;
            { // to restrain the scope of csvData
                String[] csvData = line.split(",");
                agent.setID(Integer.parseInt(csvData[0]));
                agent.setName(csvData[1]);
                agent.setSalary(Double.parseDouble(csvData[2]));
                agent.setSalesBalance(Double.parseDouble(csvData[3]));

                numCients = Integer.parseInt(csvData[4]);
            }

            for(int i = 0; i<numCients; i++) {
                line = br.readLine();

                Client client = new Client();
                int numProp = 0;

                {
                    String[] csvData = line.split(",");
                    client.setID(Integer.parseInt(csvData[0]));
                    client.setName(csvData[1]);
                    client.setIncome(Double.parseDouble(csvData[2]));

                    numProp = Integer.parseInt(csvData[3]);
                }

                for(int j=0; j<numProp; j++)
                {
                    line = br.readLine();

                    String[] csvData = line.split(",");

                    if(csvData[0].equals("house")) {
                        House property = new House();

                        property.setAddress(csvData[1]);
                        property.setNumRoom(Integer.parseInt(csvData[2]));
                        property.setPrice(Double.parseDouble(csvData[3]));
                        property.setSize(Double.parseDouble(csvData[4]));
                        property.setHasGarage(Boolean.parseBoolean(csvData[5]));

                        property.setHasGarden(Boolean.parseBoolean(csvData[6]));
                        property.setHasPool(Boolean.parseBoolean(csvData[7]));

                        client.addProperty(property);
                    }
                    else if(csvData[0].equals("apt"))
                    {
                        Apartment property = new Apartment();

                        property.setAddress(csvData[1]);
                        property.setNumRoom(Integer.parseInt(csvData[2]));
                        property.setPrice(Double.parseDouble(csvData[3]));
                        property.setSize(Double.parseDouble(csvData[4]));
                        property.setHasGarage(Boolean.parseBoolean(csvData[5]));

                        property.setHasTerrace(Boolean.parseBoolean(csvData[6]));
                        property.setHasElevator(Boolean.parseBoolean(csvData[7]));
                        property.setFloor(Integer.parseInt(csvData[8]));
                        property.setNumber(Integer.parseInt(csvData[9]));

                        client.addProperty(property);
                    }
                }

                agent.addClient(client);
            }
            return agent;
        }
        catch (NumberFormatException | IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
        finally {
            try {
                fr.close();
                br.close();
            }
            catch(Exception ex) {}
        }
    }

    private void saveToFile(Agent agent, String _login)
    {
        File file = new File(_login + ".csv");
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file, false);
            bw = new BufferedWriter (fw);
            String csvData = agent.getCSVData();
            bw.write(csvData);
        }
        catch(IOException ioEx) {
            JOptionPane.showMessageDialog(null, ioEx.getMessage());
            return;
        }
        finally {
            try {
                bw.close();
                fw.close();
            }
            catch(Exception ex) {}
        }
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
