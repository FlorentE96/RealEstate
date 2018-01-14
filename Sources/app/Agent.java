package app;

import java.util.*;

/**
 * <b>Agent class.</b>
 * <p>Agent is a subclass of Person.</p>
 * <p>Each agent has :</p>
 * <ul>
 *     <li>The common data of all persons (ID, name)</li>
 *     <li>A salary (double)</li>
 *     <li>A sales balance (double)</li>
 *     <li>A list of clients</li>
 * </ul>
 *
 * @see Person
 * @see Client
 *
 * @author  Florent
 * @version 1.0
 */
public class Agent extends Person {
    /**
     * Constant of the initial salary for all agents.
     */
    final private double INITIAL_SALARY = 1500.0;

    /**
     * The current salary of the agent.
     */
    private double salary;

    /**
     * The balance of the agent sales (total amount sold).
     */
    private double salesBalance;

    /**
     * The list of the agent's clients.
     *
     * @see Client
     */
    private List<Client> clientList;

    /**
     * Default constructor for class Agent.
     * Sets the ID to 0, the name to empty, the salary to its initial value, the balance to 0 and the client list
     * to an empty list.
     */
    public Agent()
    {
        super("",0);
        this.salary = this.INITIAL_SALARY;
        this.salesBalance = 0.0;
        clientList = new ArrayList<>();
    }

    /**
     * Overloaded constructor for class Agent.
     *
     * @param _name The name of the Agent
     * @param _ID The agent's ID
     * @param _salary The agen't salary
     */
    public Agent(String _name, int _ID, double _salary)
    {
        super(_name,_ID);
        this.salary = _salary;
        this.salesBalance = 0.0;
        clientList = new ArrayList<>();
    }

    /**
     * Overloaded constructor for class Agent.
     *
     * @param _name The name of the agent
     * @param _ID The agent's ID
     * @param _salary The agent's salary
     * @param _salesBalance The sales balance of the agent
     */
    public Agent(String _name, int _ID, double _salary, double _salesBalance)
    {
        super(_name,_ID);
        this.salary = _salary;
        this.salesBalance = _salesBalance;
        clientList = new ArrayList<>();
    }

    /**
     * returns the agent's client list
     * @return The list of the agent's clients
     */
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

    /**
     * Transforms the agent's salary to an integer "level"
     *
     * @return The salary level
     */
    public int getSalaryLevel()
    {
        // TODO : salary to level conversion
        return 0;
    }

    /**
     * Makes a propert sale from on client to another, and add the price of the transaction to the agent's balance.
     *
     * @param clientFrom The client selling the property
     * @param clientTo The client buying the property
     * @param property The property that is sold
     * @param price The price of the transaction
     */
    public void makeSale(Client clientFrom, Client clientTo, Property property, double price)
    {
        clientFrom.removeProperty(property);
        clientTo.addProperty(property);
        this.salesBalance += price;
    }

    /**
     * Adds a new client to the agent's list.
     *
     * @param client the client to be added
     */
    public void addClient(Client client)
    {
        clientList.add(client);
    }

    /**
     * Removes a specific client from the agent's list.
     *
     * @param client The client to be removed.
     */
    public void removeClient(Client client)
    {
        clientList.remove(client);
    }

    /**
     * Overloaded method.
     * Removes the client at a certain index of the agent's list.
     *
     * @param index The index (in the list) of the client to be removed
     *
     * @see Agent#removeClient(Client)
     */
    public void removeClient(int index)
    {
        if(index>=0 && index<getNumClients())
            clientList.remove(index);
    }

    /**
     * Retrieves a client at a specific index in the agent's list.
     *
     * @param index The index of the client to be retrieved.
     * @return The client at the index.
     */
    public Client getClient(int index)
    {
        return clientList.get(index);
    }

    /**
     * Retrieves the number of the agent's clients.
     *
     * @return The number of the agent's clients.
     */
    public int getNumClients() { return clientList.size(); }

    /**
     * Overridden method.
     * Returns a String containing the agent's data, in CSV format.
     * Data is the following, separated by commas :
     * <ul>
     *     <li>The common data of all persons (ID, name)</li>
     *     <li>The salary</li>
     *     <li>The sales balance</li>
     *     <li>The number of clients</li>
     * </ul>
     * <p>Then the data of each client of the agent's list, on separate lines.</p>
     *
     * @return A string containing the CSV data.
     *
     * @see Person#getCSVData()
     * @see Client#getCSVData()
     */
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
