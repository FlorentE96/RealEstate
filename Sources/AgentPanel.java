
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.event.*;

public class AgentPanel
        extends JPanel
{
    boolean editable;
    
    JTextField nameField, idField, salaryField, balanceField, salaryLevelField;

    public AgentPanel(Agent user, boolean isRegistering)
    {
        editable = isRegistering;
        
        JPanel infoPanel;
        infoPanel = new JPanel();

        nameField = new JTextField(10);
        idField = new JTextField(10);
        salaryField = new JTextField(10);
        balanceField = new JTextField(10);
        salaryLevelField = new JTextField(10);

        nameField.setEditable(editable);
        idField.setEditable(editable);
        salaryField.setEditable(editable);
        balanceField.setEditable(editable);
        salaryLevelField.setEditable(editable);

        nameField.setText(user.getName());
        idField.setText(Integer.toString(user.getID()));
        salaryField.setText(Double.toString(user.getSalary()));
        balanceField.setText(Double.toString(user.getSalesBalance()));
        salaryLevelField.setText(Double.toString(user.getSalaryLevel()));

        JLabel nameLabel = new JLabel("Name:", JLabel.TRAILING);
        JLabel idLabel = new JLabel("ID:", JLabel.TRAILING);
        JLabel salaryLabel = new JLabel("Current Salary:", JLabel.TRAILING);
        JLabel balanceLabel = new JLabel("Sales Balance:", JLabel.TRAILING);
        JLabel salaryLevelLabel = new JLabel("Salary Level:", JLabel.TRAILING);

        nameLabel.setLabelFor(nameField);
        idLabel.setLabelFor(idField);
        salaryLabel.setLabelFor(salaryField);
        balanceLabel.setLabelFor(balanceField);
        salaryLevelLabel.setLabelFor(salaryLevelField);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        infoPanel.setLayout(new GridBagLayout());
        GridBagConstraints layoutConstraints = new GridBagConstraints();

        infoPanel.setOpaque(true);

        layoutConstraints.fill = GridBagConstraints.NONE;
        layoutConstraints.anchor = GridBagConstraints.NORTHWEST;
        layoutConstraints.weightx = 0.5;
        layoutConstraints.weighty = 0.5;
        layoutConstraints.insets = new Insets(0,10,10,10);

        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        infoPanel.add(idLabel, layoutConstraints);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 1;
        infoPanel.add(nameLabel, layoutConstraints);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 2;
        infoPanel.add(salaryLabel, layoutConstraints);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 3;
        infoPanel.add(balanceLabel, layoutConstraints);
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 4;
        infoPanel.add(salaryLevelLabel, layoutConstraints);
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 4;

        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        infoPanel.add(salaryLevelField, layoutConstraints);
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 0;
        infoPanel.add(idField, layoutConstraints);
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 1;
        infoPanel.add(nameField, layoutConstraints);
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 2;
        infoPanel.add(salaryField, layoutConstraints);
        layoutConstraints.gridx = 1;
        layoutConstraints.gridy = 3;
        infoPanel.add(balanceField, layoutConstraints);

        this.add(infoPanel);
    }
}
