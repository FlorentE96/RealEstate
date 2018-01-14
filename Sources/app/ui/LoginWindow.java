package app.ui;

import app.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;

/**
 * <b>LoginWindow is the dialog for logging an agent in or registering a new user.</b>
 * <p>This is the first dialog shown when the program starts.</p>
 * <p>To log in, you need :
 * <ul>
 *     <li>A login</li>
 *     <li>A password</li>
 * </ul></p>
 *
 * <p>If the login/password combination does not match any registered user, an error dialog is displayed and the fields
 * are cleared.
 * If the combination is correct, the agent's data is retrieved from a CSV file using his login, and the main window is
 * diplayed.<p/>
 * <p>After typing a login and a password, the user has the possibility to register. In that case,
 * a <code>RegisterAgentDialog</code> will be displayed asking for his information. Once all the fields of this dialog
 * have been filled and the user has clicked "register", his login and password will be stored to an encrypted text
 * file, his information to a CSV file and he will then have the possibility to log in in the future.
 * <strong>The encryption is not yet implemented.</strong></p>
 *
 * @see     LoginPanel
 * @see     MainWindow
 * @see     RegisterAgentDialog
 * @see     Agent
 * @see     JPanel
 *
 * @author  Florent
 * @version 1.0
 */
public class LoginWindow
        extends JFrame
        implements ActionListener
{
    /**
     * Login panel, containing all the widgets of the login window.
     *
     * @see LoginPanel
     */
    private LoginPanel myLoginPanel;

    /**
     * Constructor of LoginWindow.
     * Sets the window's title, and configures the panes/menu.
     */
    public LoginWindow()
    {
        super("Login");

        myLoginPanel = new LoginPanel(this);
        this.setResizable(false);
        this.setContentPane(makePanel());
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

    /**
     * Overridden method of <code>ActionListener</code>.
     * Treats the events triggered by the register and login buttons :
     * <ul>
     *     <li><b>Login button </b>: compares the password entered for the true password corresponding to login.
     *     If the password is wrong or the login doesn't exist, a message dialog is displayed and the
     *     GUI data is cleared.
     *     If the password is correct, the login dialog becomes invisible, the agen't data is loaded
     *     and the main window is displayed. After the closing of th main window, the agent's new data
     *     is saved to his CSV file. Then the program terminates.</li>
     *     <li><b>Register button</b> : Opens a <code>RegisterAgentDialog</code> to let the agent fill up his
     *     information. Once this is done, the populated agent object is retrieved, the login/password combination
     *     is saved to the encrypted file and the agent's data to a CSV file.</li>
     * </ul>
     * <p>After both of these actions, the GUI is cleared.</p>
     *
     * @param e The <code>ActionEvent</code> which triggered the listener.
     *
     * @see LoginWindow#getPassword(String)
     * @see LoginPanel#getPassword()
     * @see LoginWindow#loadFromFile(String)
     * @see MainWindow
     * @see LoginWindow#saveToFile(Agent, String)
     * @see LoginPanel#clearGUIValues()
     * @see LoginWindow#registerNewUser(String, char[])
     * @see Agent#generateID()
     * @see RegisterAgentDialog
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if(command.equals("login"))
        {
            String login = myLoginPanel.getLogin();
            char[] correctPassword = this.getPassword(login);
            if(correctPassword != null) {
                char[] typedPassword = myLoginPanel.getPassword();
                if (Arrays.equals(typedPassword, correctPassword)) {
                    this.setVisible(false);
                    Agent user = loadFromFile(myLoginPanel.getLogin());
                    MainWindow myMainWindow = new MainWindow(null, user);
                    saveToFile(user, myLoginPanel.getLogin());
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Login or password incorrect. Please try again.");
                    myLoginPanel.clearGUIValues();
                }
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
                JOptionPane.showMessageDialog(null, "Registration successful");
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Registration cancelled");
            }
            myLoginPanel.clearGUIValues();
        }
    }

    /**
     * <b>Populates an agent object based on a CSV file.</b>
     * <p>The CSV file is named using the agent's login, passed as a parameter.
     * It contains :</p>
     * <ul>
     *     <li>The agent's information (ID, name, salary, balance)</li>
     *     <li>The information for each of his clients (ID, name, income...)</li>
     *     <li>The information for each property of his clients (address, numRooms, size...)</li>
     * </ul>
     * <p>If the CSV file doesn't exist, the method returns <code>null</code>.</p>
     *
     * @param _login The login of the agent of which the information has to be retrieved.
     * @return The agent, populated by the data on the CSV file OR null if the file doesn't exist
     *
     * @see Agent
     */
    private Agent loadFromFile(String _login)
    {
        Agent agent = new Agent();
        File file = new File(_login + ".csv");
        FileReader fr;
        BufferedReader br;
        String line;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            line = br.readLine();

            int numCients;
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
                int numProp;

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
            fr.close();
            br.close();
            return agent;
        }
        catch (NumberFormatException nfEx) {
            JOptionPane.showMessageDialog(null, "There was a problem when retrieving " +
                    "the agent's data.\n Please try again");
            return null;
        }
        catch (IOException ioEx) {
            JOptionPane.showMessageDialog(null, "This user doesn't exist....");
            return null;
        }
    }

    /**
     * Saves an agent's data to a CSV file.
     * <p>It contains :</p>
     * <ul>
     *     <li>The agent's information (ID, name, salary, balance)</li>
     *     <li>The information for each of his clients (ID, name, income...)</li>
     *     <li>The information for each property of his clients (address, numRooms, size...)</li>
     * </ul>
     *
     * @param agent The agent of which the data should be saved.
     * @param _login The login of the agent, used as a name for the CSV file.
     *
     * @see Agent#getCSVData()
     */
    private void saveToFile(Agent agent, String _login)
    {
        File file = new File(_login + ".csv");
        FileWriter fw;
        BufferedWriter bw;
        try {
            fw = new FileWriter(file, false);
            bw = new BufferedWriter (fw);
            String csvData = agent.getCSVData();
            bw.write(csvData);
            bw.close();
            fw.close();
        }
        catch(IOException ioEx) {
            JOptionPane.showMessageDialog(null, ioEx.getMessage());
        }
    }

    /**
     * Register a new user's login/password combination to the encrypted password file.
     *
     * @param _login The login of the user
     * @param _password The corresponding password
     */
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

    /**
     * Retrieves the password associated to a certain login, from the encrypted password file.
     *
     * @param _login the login for which to retrieve the password.
     * @return The password.
     */
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
