import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;

public class RegisterAgentDialog extends JDialog implements ActionListener{
    private AgentPanel registrationPanel;
    private JButton valButton, canButton;
    private Agent agentReturned;

    public RegisterAgentDialog(JFrame owner)
    {
        super(owner, "Register");

        registrationPanel = new AgentPanel(new Agent(), true);
        JPanel buttonPanel = new JPanel();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        valButton = new JButton("Register!");
        canButton = new JButton("Cancel");
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

    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if(command.equals("validate"))
        {
            agentReturned = registrationPanel.getAgetFromForm();
            setVisible(false);
        }
        else if(command.equals("cancel"))
        {
            agentReturned = null;
            setVisible(false);
        }
    }
}
