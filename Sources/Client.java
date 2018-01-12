import java.util.*;

/**
 * <b>Client is a subclass of Person.</b>
 * <p>Each client has :
 * <ul>
 *     <li>An income, used to calculate if he reliable or not</li>
 *     <li>A set of preferences used to advise specific properties</li>
 * </ul></p>
 *
 * @see     Person
 * @see     Owner
 * @see     Renter
 *
 * @author  Florent
 * @author  Miron
 * @version 0.2
 */
public class Client extends Person {
    /**
     * The income of the client.
     *
     * @see Client#setIncome(double)
     * @see Client#getIncome()
     * @see Client#Client(String, int, double)
     * @see Client#isTrustable(Property)
     */
    private double income;

    /**
     * A list of properties of the client. You can add or remove from this list
     *
     * @see Client#addProperty(Property)
     * @see Client#removeProperty(Property)
     */
    private List<Property> propertyList;

    /**
     * Constructor for class Client
     *
     * @param _name The name of the Client
     * @param _ID   The ID of the Client
     *
     * @see         Person#Person(String, int)
     */
    public Client(String _name, int _ID) {
        super(_name, _ID);
        income = 0.0; // TODO: fix default salary
        propertyList = new ArrayList<Property>();
    }

    /**
     * Overloaded constructor for class Client
     *
     * @param _name     The name of the Client
     * @param _ID       The ID of the Client
     * @param _income   The monthly income of the Client
     *
     * @see             Person#Person(String, int)
     */
    public Client(String _name, int _ID, double _income) {
        super(_name, _ID);
        income = _income;
        propertyList = new ArrayList<Property>();
    }

    public Client(String _name)
    {
        super(_name, 0);
        income = 0.0;
        this.generateID();
        propertyList = new ArrayList<Property>();
    }

    /**
     * Getter for the Clien'ts income
     *
     * @return  The client's income (in $R)
     *
     * @see     Client#income
     */
    public double getIncome() {
        return income;
    }

    /**
     * Setter for the Clien'ts income
     *
     * @param _income  The client's income (in $R)
     *
     * @see            Client#income
     */
    public void setIncome(double _income) {
        income = _income;
    }

    /**
     *
     * @param property  the property for which to check if the client is reliable or not
     *
     * @return          <code>true</code> if the person can be trusted for the property
     *                  <code>false</code> if the person isn't trustable for the property
     *
     * @see             Client#income
     */
    public boolean isTrustable(Property property) {
        // TODO: implementation
        return true;
    }

    public void addProperty(Property property)
    {
        propertyList.add(property);
    }

    public void removeProperty(Property property)
    {
        propertyList.remove(property);
    }

    public void removeProperty(int index)
    {
        propertyList.remove(index);
    }

    public List<Property> getPropertyList()
    {
        return propertyList;
    }

    public int getNumPropertiesOwned()
    {
        return this.propertyList.size();
    }
}
