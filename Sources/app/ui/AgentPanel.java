package app.ui;

import java.awt.*;
import javax.swing.*;
import app.*;

/**
 * <b>AgentPanel is a panel containing an agent's information.</b>
 * <p>The different fields displayed are :</p>
 * <ul>
 *     <li>The name of the agent</li>
 *     <li>The ID of the agent </li>
 *     <li>The salary of the agent</li>
 *     <li>The sales' balance of the agent</li>
 * </ul>
 * <p>The panel can be updated to reflect the new values using the <code>updateValues()</code> method.</p>
 * <p>By default, all the fields are disabled (read-only) but the panel can be created in registration mode, for
 * the registration of a new agent or the modification of an existing one.</p>
 *
 * <p>The panel has been designed with IntelliJ's GUI designer.</p>
 *
 * @see Agent
 * @see MainWindow
 *
 * @author  Florent
 * @version 1.0
 */
public class AgentPanel
        extends JPanel
{
    /**
     * The agent of which information has to be displayed.
     * This is passed as a parameter to the constructor of the class.
     *
     * @see Agent
     * @see AgentPanel
     */
    private Agent agent;

    /**
     * Text fields containing the different values
     */
    private JTextField nameField, idField, salaryField, balanceField, salaryLevelField;

    /**
     * Main panel designed with IntelliJ's GUI designer
     */
    private JPanel mainPanel;

    /**
     * Constructor of the class.
     *
     * @param _agent This is the agent of which information is going to be displayed,
     *               i.e. the program's user.
     * @param isRegistering This boolean allows to select if the pane is editable or not.
     *                      Editable agent panels (<code>isRegistering=true</code>) are
     *                      used to edit/fill up the agent's information ;
     *                      non-editable (<code>isRegistering=false</code>) are used solely to display.
     */
    public AgentPanel(Agent _agent, boolean isRegistering)
    {
        agent = _agent;

        nameField.setEditable(isRegistering);
        idField.setEditable(false);
        salaryField.setEditable(isRegistering);
        balanceField.setEditable(isRegistering);
        salaryLevelField.setEditable(false);

        this.updateValues();

        this.setLayout(new BorderLayout());
        this.add(mainPanel);
    }

    /**
     * Retrieves an agent object populated with the form's informations.
     * This is useful when the panel is used for edition of an Agent.
     * @return The populated agent.
     *
     * @see Agent
     */
    public Agent getAgentFromForm()
    {
        String agentName = nameField.getText();
        int ID = Integer.parseInt(idField.getText());
        double salary = Double.parseDouble(salaryField.getText());
        double salesBalance = Double.parseDouble(balanceField.getText());

        return new Agent(agentName, ID, salary, salesBalance);
    }


    /**
     * Updates the panel's values based on the agent.
     */
    public void updateValues()
    {
        nameField.setText(agent.getName());
        idField.setText(Integer.toString(agent.getID()));
        salaryField.setText(Double.toString(agent.getSalary()));
        balanceField.setText(Double.toString(agent.getSalesBalance()));
        salaryLevelField.setText(Double.toString(agent.getSalaryLevel()));
    }
}
