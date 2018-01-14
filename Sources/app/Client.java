package app;

import java.util.*;

/**
 * <b>Client class.</b>
 * <p>Client is a subclass of Person.</p>
 * <p>Each client has :</p>
 * <ul>
 *     <li>The common data of all persons (ID, name)</li>
 *     <li>An income (double)</li>
 *     <li>A list of properties</li>
 * </ul>
 *
 * @see Person
 * @see Property
 *
 * @author  Florent
 * @version 1.0
 */
public class Client extends Person {
    /**
     * The income of the client.
     */
    private double income;

    /**
     * A list of properties of the client. You can add or remove from this list
     */
    private List<Property> propertyList;

    /**
     * Default constructor for class Client.
     * The fields are all initialized to 0/empty.
     *
     * @see Person#Person(String, int)
     */
    public Client() {
        super("", 0);
        income = 0.0;
        propertyList = new ArrayList<>();
    }

    /**
     * Overloaded constructor for class Client
     * The income and the property list are initialized to 0/empty.
     *
     * @param _name The name of the Client
     * @param _ID   The ID of the app.Client
     *
     * @see         Client#Client()
     * @see         Person#Person(String, int)
     */
    public Client(String _name, int _ID) {
        super(_name, _ID);
        income = 0.0;
        propertyList = new ArrayList<>();
    }


    /**
     * Overloaded constructor for class Client.
     * The property list is initialized to empty.
     *
     * @param _name     The name of the app.Client
     * @param _ID       The ID of the app.Client
     * @param _income   The monthly income of the app.Client
     *
     * @see             Client#Client()
     * @see             Person#Person(String, int)
     */
    public Client(String _name, int _ID, double _income) {
        super(_name, _ID);
        income = _income;
        propertyList = new ArrayList<>();
    }

    public Client(String _name)
    {
        super(_name, 0);
        income = 0.0;
        this.generateID();
        propertyList = new ArrayList<>();
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double _income) {
        income = _income;
    }

    /**
     * Method to determine whether a client is trustable to buy a given property.
     *
     * @param property  the property for which to check if the client is reliable or not.
     *
     * @return          <code>true</code> if the person can be trusted for the property.
     *                  <code>false</code> if the person isn't trustable for the property.
     */
    public boolean isTrustable(Property property) {
        // TODO: implementation
        return true;
    }

    /**
     * Adds a property to the client's list.
     *
     * @param property The property to be added.
     */
    public void addProperty(Property property)
    {
        propertyList.add(property);
    }

    /**
     * Removes a property from the client's list.
     *
     * @param property The property to be removed.
     */
    public void removeProperty(Property property)
    {
        propertyList.remove(property);
    }

    /**
     * Overloaded method.
     * Removes the property at a certain index of the agent's list.
     *
     * @param index
     */
    public void removeProperty(int index)
    {
        if(index>=0 && index<getNumPropertiesOwned())
            propertyList.remove(index);
    }

    public List<Property> getPropertyList()
    {
        return propertyList;
    }

    /**
     * Returns the property at a given index of the client's list.
     *
     * @param index The index of the property to be retrieved.
     * @return The property at given index.
     */
    public Property getProperty(int index)
    {
        return propertyList.get(index);
    }

    /**
     * Returns the number of properties owned by the client.
     *
     * @return The number of properties owned by the client.
     */
    public int getNumPropertiesOwned()
    {
        return this.propertyList.size();
    }

    /**
     * Overridden method.
     * Returns a String containing the client's data, in CSV format.
     * Data is the following, separated by commas :
     * <ul>
     *     <li>The common data of all persons (ID, name)</li>
     *     <li>The income</li>
     *     <li>The number of properties owned</li>
     * </ul>
     * <p>Then the data of each property of the client's list, on separate lines.</p>
     *
     * @return a String containing the CSV data
     *
     * @see Person#getCSVData()
     * @see Property#getCSVData()
     */
    @Override
    public String getCSVData() {
        String csvData = super.getCSVData() + "," +
                Double.toString(this.getIncome()) + "," +
                Integer.toString(this.getNumPropertiesOwned());
        csvData += "\n";

        for (Property property : propertyList)
        {
            csvData += property.getCSVData() + "\n";
        }

        return csvData;
    }
}
