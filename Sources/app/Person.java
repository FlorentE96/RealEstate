package app;

/**
 * <b>app.Person is the base class for all persons</b>
 * <p>Each person has at least:
 * <ul>
 *     <li>A name</li>
 *     <li>An ID that can not be changed</li>
 * </ul></p>
 *
 * @see     Client
 * @see     Agent
 *
 * @author  Florent
 * @author  Miron
 * @version 0.1
 */
public abstract class Person {
    /**
     * The name of the person.
     *
     * @see Person#getName()
     * @see Person#setName(String)
     * @see Person#Person(String, int)
     */
    private String name;
    /**
     * The internal ID of the person. It can't be modified.
     *
     * @see Person#getID()
     * @see Person#Person(String, int)
     */
    private int ID;

    /**
     * Constructor app.Person.
     *
     * <p>The name and ID are specified at construction</p>
     *
     * @param _name     the name of the app.Person
     * @param _ID       the ID of the app.Person
     *
     * @see             Person#name
     * @see             Person#ID
     */
    public Person (String _name, int _ID) {
        name = _name;
        ID = _ID;
    }

    /**
     * Getter for name.
     *
     * @return  the name of the app.Person
     *
     * @see     Person#name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for ID.
     *
     * @return  The ID of the app.Person
     *
     * @see     Person#ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Setter for name.
     *
     * @param _name     the new name of the person.
     *
     * @see             Person#name
     */
    public void setName(String _name) {
        name = _name;
    }

    /**
     * Setter for ID.
     *
     * @param _ID     the new name of the person.
     *
     * @see             Person#name
     */
    public void setID(int _ID) {
        ID = _ID;
    }

    public void generateID()
    {
        ID = this.hashCode();
    }

    public String toString()
    {
        return Integer.toString(this.ID).concat(" - " + this.name);
    }

//    public abstract String getAllInfo();
}
