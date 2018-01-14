package app.ui;

import app.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
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
 * @see JDialog
 *
 * @version 1.0
 */
public class MainWindow
        extends JDialog
        implements ActionListener, ChangeListener
{
    /**
     * Th agent's personal data panel.
     *
     * @see AgentPanel
     */
    private AgentPanel myAgentPanel;

    /**
     * The agen't client list panel.
     *
     * @see ClientsPanel
     */
    private ClientsPanel myClientsPanel;

    /**
     * The sale panel.
     *
     * @see SalePanel
     */
    private SalePanel mySalePanel;

    /**
     * The agent using the program.
     */
    private Agent user;

    /**
     * Default constructor for class MainWindow.
     *
     * @param owner The frame that called the dialog.
     * @param _user The agent logged in to the program.
     *
     * @see JDialog#JDialog(Frame, String)
     */
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

    /**
     * Creates the main login panel.
     * This panel contains 3 tabs :
     * <ul>
     *     <li>The Agent tab, with all the user's personal details</li>
     *     <li>The Clients tab, with a list of his clients</li>
     *     <li>The Sales tab, allowing him to perform a sale.</li>
     * </ul>
     *
     * @return The main panel.
     *
     * @see AgentPanel
     * @see ClientsPanel
     * @see SalePanel
     */
    private JPanel makePanel()
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        myAgentPanel = new AgentPanel(user,false);
        myClientsPanel = new ClientsPanel(user);
        mySalePanel = new SalePanel(user);

        JTabbedPane myTabbedPane = new JTabbedPane();
        myTabbedPane.addChangeListener(this);
        myTabbedPane.addTab("Agent", myAgentPanel);
        myTabbedPane.addTab("Clients", myClientsPanel);
        myTabbedPane.addTab("Sale", mySalePanel);

        mainPanel.add(myTabbedPane);

        return mainPanel;
    }


    /**
     * Creates the main menu.
     * This menu only contains an option to quit.
     *
     * @return The menu bar.
     */
    private JMenuBar makeMenu()
    {
        JMenuBar mbr_barra = new JMenuBar();
        JMenu fileMenu;
        JMenuItem quit;


        fileMenu = new JMenu("File");
        mbr_barra.add(fileMenu);

        quit = new JMenuItem("Quit");

        fileMenu.add(quit);

        quit.setActionCommand("quit");
        quit.addActionListener(this);

        return mbr_barra;
    }
    /**
     * Overridden method of <code>ActionListener</code>.
     * Treats the events triggered by the menu's items :
     * If the "quit" item is selected, then the dialog is hidden. Being a model dialog, after it is hidden the focus
     * is given back to the owner.
     *
     * @param e The <code>ActionEvent</code> which triggered the listener.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if(command.equals("quit"))
        {
            this.setVisible(false);
        }
    }

    /**
     * Overriden method of <code>ChangeListener</code>.
     * Is triggered whenever the dialogs's state has changed : for example the tab has been switched.
     * When such an event occurs, the components of all the tabs are updated.
     *
     * @param e The <code>ChangeEvent</code> to be treated.
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        myAgentPanel.updateValues();
        myClientsPanel.updateTable();
        mySalePanel.updateValues();
        // NOTE : For optimization, we could treat the event to update only a tab that has been selected.
    }
}
