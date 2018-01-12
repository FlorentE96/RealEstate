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

    public enum PropertyStatus {rented, owned};
    /**
     * The neighbourhood of the property.
     *
     *
     */
    private String neighbourhood;

    /**
     * The number of the rooms.
     *
     * @see
     */
    private int numRoom;

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
    private PropertyStatus status;

    /**
     * If the property had garage is TRUE, else is FALSE.
     *
     * @see
     */
    private boolean hasGarage;

    /**
     * Constructor for the class.
     */
    public Property(String _neighbourhood, int _numRoom, double _price, PropertyStatus _status, boolean _hasGarage){

        neighbourhood = _neighbourhood;
        numRoom = _numRoom;
        price = _price;
        status = _status;
        hasGarage = _hasGarage;

    }

    public Property(String _neighbourhood){

        neighbourhood = _neighbourhood;
        numRoom = 0;
        price = 0.0;
        status = PropertyStatus.owned;
        hasGarage = false;

    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public int getNumRoom() {
        return numRoom;
    }

    public void setNumRoom(int numRoom) {
        this.numRoom = numRoom;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public PropertyStatus getStatus() {
        return status;
    }

    public void setStatus(PropertyStatus status) {
        this.status = status;
    }

    public boolean isHasGarage() {
        return hasGarage;
    }

    public void setHasGarage(boolean hasGarage) {
        this.hasGarage = hasGarage;
    }

    public String toString()
    {
        return neighbourhood;
    }
}
