package app.ui;

import app.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

/**
 * <b>MainWindow is the main dialog of the program.</b>
 * <p>It is a modal dialog, which means that as long as it is visible, the calling method thread is blocked.</p>
 * <p>This window hosts the 3 panels of the program, using a tab system : </p>
 * <ul>
 *     <li>The Agent Panel, containing the user's personal information</li>
 *     <li>The Client Panel, containing the details for all the user's clients and giving access to a client's
 *     list of properties</li>
 *     <li>The Sale Panel, allowing the user to make a sale.</li>
 * </ul>
 *
 * <p>The window is immediately shown upon creation. It is resizable, with a minimum limit corresponding to the
 * minimum size of all its components. </p>
 *
 * @see AgentPanel
 * @see ClientsPanel
 * @see SalePanel
 *
 */
public class MainWindow
        extends JDialog
        implements ActionListener, ChangeListener
{
    private AgentPanel myAgentPanel;
    private ClientsPanel myClientsPanel;
    private SalePanel mySalePanel;
    private Agent user;

    public MainWindow(JFrame owner, Agent _user)
    {
        super(owner, "Real Estate Manager");
        user = _user;

        this.setContentPane(this.makePanel());
        this.setJMenuBar(makeMenu());

        this.pack();
        this.setMinimumSize(this.getSize());
        this.setModal(true);
        this.setVisible(true);
    }

    private JPanel makePanel()
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        myAgentPanel = new AgentPanel(user,false);
        myClientsPanel = new ClientsPanel(user);
        mySalePanel = new SalePanel(user);

        JTabbedPane myTabbedPane = new JTabbedPane();
        myTabbedPane.addChangeListener(this);
        myTabbedPane.addTab("Performance", myAgentPanel);
        myTabbedPane.addTab("Clients", myClientsPanel);
        myTabbedPane.addTab("Sale", mySalePanel);

        mainPanel.add(myTabbedPane);

        return mainPanel;
    }

    private JMenuBar makeMenu()
    {
        //Barras de menus são compostas por menus
        JMenuBar mbr_barra = new JMenuBar();
        JMenu fileMenu;
        JMenuItem quit;


        fileMenu = new JMenu("File");
        mbr_barra.add(fileMenu);

        quit = new JMenuItem("Quit");

        // fileMenu.addSeparator();
        fileMenu.add(quit);

        quit.setActionCommand("quit");
        quit.addActionListener(this);

        return mbr_barra;
    }

    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if(command.equals("quit"))
        {
            this.setVisible(false);
        }
    }

    public void stateChanged(ChangeEvent e) {
        myAgentPanel.updateValues();
        myClientsPanel.updateTable();
        mySalePanel.updateValues();
    }

//    public void saveDataToFile
}
