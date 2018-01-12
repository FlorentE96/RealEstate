import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MainWindow
        extends JFrame
        implements ActionListener
{
    private AgentPanel myAgentPanel;
    private ClientsPanel myClientsPanel;
    private SalePanel mySalePanel;
    private Agent user;

    public MainWindow(Agent _user)
    {
        super("Real Estate Manager");
        user = _user;

        this.setContentPane(this.makePanel());
        this.setJMenuBar(makeMenu());

        this.pack();
//        this.setMaximumSize(this.getSize());
        this.setMinimumSize(this.getSize());

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
            System.exit(0);
    }

}
