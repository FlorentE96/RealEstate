package app.ui;

import app.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * <b><code>RegisterAgentDialog</code> is a dialog containing a form for registering a new agent.</b>
 * This is a modal dialog, which mean that as long as it is displayed, it blocks the execution of the calling thread.
 * It is set visible directly upon creation and remains visible until either of the "register" or "cancel" button
 * has been pressed.
 * <p>This dialog uses an <code>AgentPanel</code> in registration mode.</p>
 * <p>The registered agent can be retrieved using the <code>getAgentReturned</code> method.</p>
 *
 * @see AgentPanel
 * @see Agent
 *
 * @author  Florent
 * @version 1.0
 */
public class RegisterAgentDialog
        extends JDialog
        implements ActionListener
{
    /**
     * The <code>AgentPanel</code> used for filling the informations.
     */
    private AgentPanel registrationPanel;

    /**
     * The registered agent.
     */
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

    /**
     * Overridden method of <code>ActionListener</code>.
     * Treats the events triggered by the 2 buttons :
     * <ul>
     *     <li>Register : retrieves the populated agent from the <code>AgentPanel</code>, and hides the dialog</li>
     *     <li>Cancel : set the returned agent to <code>null</code> and hides the window</li>
     * </ul>
     *
     * @param e The <code>ActionEvent</code> which triggered the listener.
     *
     * @see AgentPanel#getAgentFromForm()
     */
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
