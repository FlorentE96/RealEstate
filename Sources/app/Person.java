package app;

/**
 * <b>=Person is the base class for all persons</b>
 * <p>Each person has:
 * <ul>
 *     <li>An ID</li>
 *     <li>A name</li>
 * </ul></p>
 *
 * @see     Client
 * @see     Agent
 *
 * @author  Florent
 * @version 1.0
 */
public abstract class Person {
    /**
     * The name of the person.
     *
     */
    private String name;

    /**
     * The ID of the person.
     */
    private int ID;

    /**
     * Constructor for class Person.
     *
     * @param _name     the name of the Person
     * @param _ID       the ID of the Person
     */
    public Person (String _name, int _ID) {
        name = _name;
        ID = _ID;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public void setName(String _name) {
        name = _name;
    }

    public void setID(int _ID) {
        ID = _ID;
    }

    /**
     * Generates a unique identifier for the person based on the object's hashcode.
     */
    public void generateID()
    {
        ID = this.hashCode();
    }

    /**
     * Overridden method to convert the object to a string.
     * The address is the only attribute used.
     *
     * @return The string corresponding to the object.
     */
    @Override
    public String toString()
    {
        return Integer.toString(this.ID).concat(" - " + this.name);
    }

    /**
     * Returns a String containing the property's data, in CSV format.
     * Data is the following, separated by commas :
     * <ul>
     *     <li>The ID of the person</li>
     *     <li>The name of the person</li>
     * </ul>
     *
     * @return A string containing the CSV data.
     */
    public String getCSVData()
    {
        return (Integer.toString(this.getID()) + "," +
                this.getName());
    }
}
