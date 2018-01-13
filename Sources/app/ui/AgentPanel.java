package app.ui;

import java.awt.*;
import javax.swing.*;
import app.*;

public class AgentPanel
        extends JPanel
{
    private Agent agent;
    
    private JTextField nameField, idField, salaryField, balanceField, salaryLevelField;
    private JPanel mainPanel;

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

    public Agent getAgentFromForm()
    {
        String agentName = nameField.getText();
        int ID = Integer.parseInt(idField.getText());
        double salary = Double.parseDouble(salaryField.getText());
        double salesBalance = Double.parseDouble(balanceField.getText());

        return new Agent(agentName, ID, salary, salesBalance);
    }

    public void updateValues()
    {
        nameField.setText(agent.getName());
        idField.setText(Integer.toString(agent.getID()));
        salaryField.setText(Double.toString(agent.getSalary()));
        balanceField.setText(Double.toString(agent.getSalesBalance()));
        salaryLevelField.setText(Double.toString(agent.getSalaryLevel()));
    }
}
