package app;

import java.util.*;

// TODO: write JavaDoc
public class Agent extends Person {
    final private double INITIAL_SALARY = 1500.0;
    private double salary;
    private double salesBalance;
    private List<Client> clientList;

    public Agent(String _name, int _ID, double _salary)
    {
        super(_name,_ID);
        this.salary = _salary;
        this.salesBalance = 0.0;
        clientList = new ArrayList<>();
    }

    public Agent()
    {
        super("",0);
        this.salary = this.INITIAL_SALARY;
        this.salesBalance = 0.0;
        clientList = new ArrayList<>();
    }

    public Agent(String _name, int _ID, double _salary, double _salesBalance)
    {
        super(_name,_ID);
        this.salary = _salary;
        this.salesBalance = _salesBalance;
        clientList = new ArrayList<>();
    }

    public List<Client> getClientList()
    {
        return clientList;
    }

    public double getSalary()
    {
        return salary;
    }

    public double getSalesBalance()
    {
        return salesBalance;
    }

    public void setSalary(double _salary)
    {
        this.salary = _salary;
    }

    public void setSalesBalance(double _salesBalance)
    {
        this.salesBalance = _salesBalance;
    }

    public int getSalaryLevel()
    {
        // TODO : salary to level conversion
        return 0;
    }

    public void saveAgentInfo()
    {
        // TODO : save agent's info to csv file
    }

    public void makeSale(Client clientFrom, Client clientTo, Property property, double price)
    {
        clientFrom.removeProperty(property);
        clientTo.addProperty(property);
        this.salesBalance += price;
    }

    public void addClient(Client client)
    {
        clientList.add(client);
    }

    public void removeClient(Client client)
    {
        clientList.remove(client);
    }

    public void removeClient(int index)
    {
        if(index>=0 && index<getNumClients())
            clientList.remove(index);
    }

    public Client getClient(int index)
    {
        return clientList.get(index);
    }

    public int getNumClients() { return clientList.size(); }

    @Override
    public String getCSVData() {
        String csvData = super.getCSVData() + "," +
                Double.toString(this.getSalary()) + "," +
                Double.toString(this.getSalesBalance()) + "," +
                Integer.toString(this.getNumClients());
        csvData += "\n";

        for (Client client : clientList)
        {
            csvData += client.getCSVData();
        }

        return csvData;
    }
}
