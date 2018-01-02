import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class LoginWindow extends JFrame implements ActionListener
{
    private LoginPanel myLoginPanel;

    public LoginWindow()
    {
        super("Login");

        this.setContentPane(makePanel());
        this.setJMenuBar(makeMenu());

        this.pack();

        this.setVisible(true);
    }

    private JPanel makePanel()
    {
        JPanel mainPanel = new JPanel();

        myLoginPanel = new LoginPanel();

        mainPanel.add(myLoginPanel);

        return mainPanel;
    }

    private JMenuBar makeMenu()
    {
        //Barras de menus são compostas por menus
        JMenuBar mbr_barra = new JMenuBar();
        JMenu fileMenu;
        JMenuItem newUser, removeUser, quit;


        fileMenu = new JMenu("File");
        mbr_barra.add(fileMenu);

        newUser = new JMenuItem("New User");
        removeUser = new JMenuItem("Remove User");
        quit = new JMenuItem("Quit");

        fileMenu.add(newUser);
        fileMenu.add(removeUser);
        fileMenu.addSeparator();
        fileMenu.add(quit);

        newUser.setActionCommand("new");
        newUser.addActionListener(this);
        removeUser.setActionCommand("remove");
        removeUser.addActionListener(this);
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
        else if(command.equals("remove"))
        {
        }
        else if(command.equals("quit"))
            System.exit(0);
    }
}
