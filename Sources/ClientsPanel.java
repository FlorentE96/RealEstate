

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.event.*;


public class ClientsPanel
        extends JPanel
        implements MouseListener
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private JTable clientTable;
    private JScrollPane scrollPane;
    private ArrayList<Client> clientList;
    private JDialog dialog;

    /**
     * Constructeur d'objets de classe PainelConsulta
     */
    public ClientsPanel()
    {
        String[] columnNames = {"ID","Name","Income","Preferences","Status"};

        //Dados para a tabela
        clientList = new ArrayList<Client>();

        clientTable = new JTable(getTableModel());
        scrollPane = new JScrollPane(clientTable);
        clientTable.setFillsViewportHeight(true);

        clientTable.addMouseListener(this);

        this.add(scrollPane);
    }

    //getTableModel
    private DefaultTableModel getTableModel(){
        String[] columnNames = {"ID","Name","Income","Preferences","Status"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0){
            public boolean isCellEditable(int row, int column){
                return false; // This causes all cells to be not editable
            }};
        for (Client client : clientList) {
            tableModel.addRow(new Object[] {
                    client.getID(),
                    client.getName(),
                    client.getIncome(),
                    "", // TODO : client.getPreferences(),
                    "", //TODO : client.getStatus()
                    });
        }
        return tableModel;
    }

    public void registerClient(Client _client)
    {
        if(_client.getID() <= 0)
        {
            _client.setID(clientList.size()+1);
            clientList.add(_client);
        }
        else
        {
            clientList.set(_client.getID()-1, _client);
            dialog.dispose();
        }
        clientTable.setModel(getTableModel());
    }

    public void setArrayData(ArrayList<Client> newClientList)
    {
        if(!newClientList.isEmpty()) {
            clientList.clear();
            clientList.addAll(newClientList);
            clientTable.setModel(getTableModel());
        }
        else
        {
            JOptionPane.showMessageDialog(null, "The list is empty...");
        }
    }

    public void clearArrayData()
    {
        clientList.clear();
        clientTable.setModel(getTableModel());
    }

    public String getCSVData() {
        String csvData = "";
        for (Client peca : clientList)
        {
            csvData += peca.getID() + "," +
                    peca.getName() + "," +
                    peca.getIncome() + "," +
                    "" + "," + // preferences
                    "" + "," + // status
                    "\n";
        }
        return csvData;
    }

    public void mousePressed(MouseEvent e)
    {
        JTable table = (JTable)e.getSource();
        Point point = e.getPoint();
        int linha = table.rowAtPoint(point);

        if(e.getClickCount() == 2 && linha >= 0)
        {
            // TODO : client registration
//            dialog = new JDialog();
//            RegisterClientPanel registerClientPanel =
//                    new RegisterClientPanel(clientList.get(linha));
//            painelEdicao.addCadastroListener(this);
//            dialog.add(registerClientPanel);
//            dialog.pack();
//            dialog.setVisible(true);
        }
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}

}
