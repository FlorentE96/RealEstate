/**
 * <b>Client is a subclass of Person.</b>
 * <p>Each client has :
 * <ul>
 *     <li>An income, used to calculate if he reliable or not</li>
 *     <li>A set of preferences used to advise specific properties</li>
 * </ul></p>
 *
 * @see Person
 * @see Owner
 * @see Renter
 *
 * @author Florent
 * @author Miron
 * @version 0.1
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
    private Preference preferences[];

    //TODO: Write JavaDoc
    public static final int maxPreferences = 10; // TODO: find optimal value

    //TODO: Write JavaDoc
    private int numPreferences;

    /**
     * Constructor for class Client
     *
     * @param _name
     * @param _ID
     */
    public Client(String _name, int _ID) {
        super(_name, _ID);
        income = 0.0; // TODO: fix default salary
        preferences = new Preference[maxPreferences]; // TODO: check this
    }

    public Client(String _name, int _ID, double _income) {
        super(_name, _ID);
        income = _income; // TODO: fix default salary
        preferences = new Preference[0]; // TODO: check this
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double _income) {
        income = _income;
    }
    /**
     *
     * @param property : the property for which to check if the client is reliable or not
     * @return whether the person is trustable or not
     *
     * @see Client#income
     */
    public boolean isTrustable(Property property) {
        // TODO: implementation
        return true;
    }

    //TODO: Write JavaDoc
    //TODO: Implement
    //TODO: Throw exception if already full
    public void addPreference(Preference _preference) {
        if(numPreferences<maxPreferences)
            preferences[numPreferences++] = _preference;
    }

    public void removePreference(int index) {
        preferences[index] = null; //TODO: check this
    }
}
