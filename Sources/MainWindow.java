import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class MainWindow extends JFrame implements ActionListener{

    public MainWindow()
    {
        super("Real Estate Manager");

        this.setContentPane(makePanel());
        this.setJMenuBar(makeMenu());
    }

    private JPanel makePanel()
    {
        JPanel mainPanel = new JPanel();

        mainPanel.add(new LoginPanel(this));

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
        else if(command.equals("quit"))
            System.exit(0);
    }

}
