import java.util.*;

// TODO: write JavaDoc
public class Agent extends Person {
    final public double INITIAL_SALARY = 1500.0;
    private double salary;
    private double salesBalance;
    private List<Client> clientList;

    public Agent(String _name, int _ID, double _salary)
    {
        super(_name,_ID);
        this.salary = _salary;
        this.salesBalance = 0.0;
        clientList = new ArrayList<Client>();
    }

    public Agent()
    {
        super("",0);
        this.salary = this.INITIAL_SALARY;
        this.salesBalance = 0.0;
        clientList = new ArrayList<Client>();
    }

    public Agent(String _name, int _ID, double _salary, double _salesBalance)
    {
        super(_name,_ID);
        this.salary = _salary;
        this.salesBalance = _salesBalance;
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

    public void updateSalary()
    {
        if(salesBalance/5 > salary)
        {
            salary *= 1.10;
        }
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
        clientList.remove(index);
    }

    public Client getClient(int index)
    {
        return clientList.get(index);
    }
}
