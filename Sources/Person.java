/**
 * <b>Person is the base class for all persons</b>
 * <p>Each person has at least:
 * <ul>
 *     <li>A name that is set once and can't be changed</li>
 *     <li>An ID that could be changed</li>
 * </ul></p>
 *
 * @see Client
 * @see Agent
 *
 * @author Florent
 * @Author Miron
 * @version 0.1
 */
public class Person {
    /**
     * The name of the person. Can't be modified.
     *
     * @see Person#getName()
     * @see Person#Person(String, int)
     */
    private String name;
    /**
     * The internal ID of the person. It can be modified.
     *
     * @see Person#getID()
     * @see Person#setID(int)
     * @see Person#Person(String, int)
     */
    private int ID;

    /**
     * Constructor Person.
     *
     * <p>The name and ID are specified at construction</p>
     * @param _name
     * @param _ID
     *
     * @see Person#name
     * @see Person#ID
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

    public void setID(int _ID) {
        ID = _ID;
    }
}
