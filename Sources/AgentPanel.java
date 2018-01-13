
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.event.*;

public class AgentPanel
        extends JPanel
{
    private boolean editable;
    private Agent agent;
    
    private JTextField nameField, idField, salaryField, balanceField, salaryLevelField;
    private JPanel mainPanel;

    public AgentPanel(Agent _agent, boolean isRegistering)
    {
        agent = _agent;
        editable = isRegistering;
        
        JPanel infoPanel;
        infoPanel = new JPanel();

        nameField.setEditable(editable);
        idField.setEditable(false);
        salaryField.setEditable(editable);
        balanceField.setEditable(editable);
        salaryLevelField.setEditable(false);

        this.updateValues();

        this.setLayout(new BorderLayout());
        this.add(mainPanel);
    }

    public Agent getAgetFromForm()
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
