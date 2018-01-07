/**
 * <b>Owner is a subclass of Client.</b>
 * <p>Each owner has at least:
 * <ul>
 *     <li>A array with ID of all properties owned</li>
 * </ul></p>
 *
 * @see     Client
 *
 * @author  Florent
 * @author  Miron
 * @version 0.1
 */public class Owner extends Client
{
    /**
     * The properties of the owner.
     *
     * @see
     */
    //TODO:adicionar via collection
    //private Property propertyOwned[];

    public Owner(String _name, int _ID)
    {
        super(_name, _ID);
    }

    /**
    *  Add a property to the owner
    */
    public void addProperty(Property _property)
    {
        //TODO:
        //adicionar via collection
        //propertyOwned[] = _property;
    }
}