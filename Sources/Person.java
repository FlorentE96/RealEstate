/**
 * <b>Person is the base class for all persons</b>
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
public class Person {
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
     * Constructor Person.
     *
     * <p>The name and ID are specified at construction</p>
     *
     * @param _name     the name of the Person
     * @param _ID       the ID of the Person
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
     * @return  the name of the Person
     *
     * @see     Person#name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for ID.
     *
     * @return  The ID of the Person
     *
     * @see     Person#ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Setter for ID.
     *
     * @param _name     the new name of the person.
     *
     * @see             Person#name
     */
    public void setName(String _name) {
        name = _name;
    }
}