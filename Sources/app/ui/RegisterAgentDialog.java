package app.ui;

import app.*;
import javax.swing.*;
import java.awt.event.*;

public class RegisterAgentDialog
        extends JDialog
        implements ActionListener
{
    private AgentPanel registrationPanel;
    private Agent agentReturned;

    public RegisterAgentDialog(JFrame owner)
    {
        super(owner, "Register");

        registrationPanel = new AgentPanel(new Agent(), true);
        JPanel buttonPanel = new JPanel();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JButton valButton = new JButton("Register!");
        JButton canButton = new JButton("Cancel");
        valButton.addActionListener(this);
        canButton.addActionListener(this);
        valButton.setActionCommand("validate");
        canButton.setActionCommand("cancel");
        buttonPanel.add(valButton);
        buttonPanel.add(canButton);

        mainPanel.add(registrationPanel);
        mainPanel.add(buttonPanel);
        this.add(mainPanel);
        this.pack();
        //this.setResizable(false);
        this.setModal(true);
        this.setVisible(true);
    }

    public Agent getAgentReturned()
    {
        return agentReturned;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if(command.equals("validate"))
        {
            agentReturned = registrationPanel.getAgentFromForm();
            setVisible(false);
        }
        else if(command.equals("cancel"))
        {
            agentReturned = null;
            setVisible(false);
        }
    }
}
