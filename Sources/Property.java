/**
 * <b>Property is the base class for all properties</b>
 * <p>Each property has at least:
 * <ul>
 *     <li>A ID </li>
 *     <li>Numbers of rooms</li>
 *     <li>Numbers of bathrooms</li>
 *     <li>Area</li>
 *     <li>Property type</li>
 * </ul></p>
 *
 * @see     Client
 * @see     Agent
 *
 * @author  Florent
 * @author  Miron
 * @version 0.1
 */
// TODO: Write JavaDoc
public abstract class Property {

    public enum HouseStatus {rented, owned};
    /**
     * The neighbourhood of the property.
     *
     * @see ???????
     */
    private String neighbouhood;
    /**
     * The number of the rooms.
     *
     * @see
     */
    private String nRooms;
    /**
     * The price of the property.
     *
     * @see
     */
    private double price;
    /**
     * The status of the Property.
     *
     * @see
     */
    private HouseStatus status;
    /**
     * The owner of the property.
     *
     * @see
     */
    private Owner owner;
    /**
     * The agent of the property.
     *
     * @see
     */
    private Agent agent;
    /**
     * The renter of the property.
     *
     * @see
     */
    private Renter renter;    /**
     * If the property had garage is TRUE, else is FALSE.
     *
     * @see
     */
    private boolean hadGarage;
    /**
     * Constructor for the class.
     */
    public Property(String _neighbourhood, String _nRooms, double _price, HouseStatus _status, Owner _owner, Renter _renter, boolean _hadGarage){

        neighbouhood = _neighbourhood;
        nRooms = _nRooms;
        price = _price;
        status = _status;
        owner = _owner;
        agent = _agent;
        renter = _renter;
        hadGarage = _hadGarage;

    }

    /**
     * Update the price of the property.
     */
    public void updatePrice(double update){
        price = update;
    }
}
