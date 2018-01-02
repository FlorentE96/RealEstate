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
     * A list of preferences of the client. You can add or remove for this list
     *
     * @see Client#addPreference(Preference)
     * @see Client#removePreference(int)
     */
    private Preference preferences[]; // TODO: change this to a collection (version 0.2)

    /**
     * The maximum number of preferences
     *
     * @deprecated 0.2
     */
    public static final int maxPreferences = 10; // TODO: remove this

    /**
     * The current number of preferences for this client.
     *
     * @deprecated 0.2
     */
    private int numPreferences;

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
        preferences = new Preference[maxPreferences]; // TODO: turn to a collection
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
        preferences = new Preference[maxPreferences]; // TODO: collection
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

    /**
     * Add a new preference for the client.
     *
     * @param _preference the preference to be added
     *
     * @deprecated 0.2
     */
    public void addPreference(Preference _preference) {
        if(numPreferences<maxPreferences)
            preferences[numPreferences++] = _preference;
    }

    /**
     * Remove an existing preference of the client.
     *
     * @param index     the index of the preference to be removed.
     *
     * @deprecated 0.2
     */
    public void removePreference(int index) {
        preferences[index] = null;
    }
}
