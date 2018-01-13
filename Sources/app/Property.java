package app;

/**
 * <b>app.Property is the base class for all properties</b>
 * <p>Each property has at least:
 * <ul>
 *     <li>A ID </li>
 *     <li>Numbers of rooms</li>
 *     <li>Numbers of bathrooms</li>
 *     <li>Area</li>
 *     <li>app.Property type</li>
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

    /**
     * The neighbourhood of the property.
     *
     *
     */
    private String address;

    /**
     * The number of the rooms.
     *
     * @see
     */
    private int numRoom;

    /**
     * The price of the property, in $
     *
     * @see
     */
    private double price;

    /**
     * The size of the property, in m².
     *
     * @see
     */
    private double size;

    /**
     * If the property has a garage : TRUE, else  FALSE.
     *
     * @see
     */
    private boolean hasGarage;

    /**
     * Constructor for the class.
     */
    public Property(String _address, int _numRoom, double _size, double _price, boolean _hasGarage)
    {
        address = _address;
        size = _size;
        numRoom = _numRoom;
        price = _price;
        hasGarage = _hasGarage;
    }

    public Property(String _address)
    {
        address = _address;
        size = 0.0;
        numRoom = 1;
        price = 0.0;
        hasGarage = false;
    }

    public Property()
    {
        address = "";
        size = 0.0;
        numRoom = 1;
        price = 0.0;
        hasGarage = false;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean isHasGarage() {
        return hasGarage;
    }

    public void setHasGarage(boolean hasGarage) {
        this.hasGarage = hasGarage;
    }

    public String toString()
    {
        return address;
    }

    public String getCSVData()
    {
        String csvData = this.getAddress()          + "," +
                Integer.toString(this.getNumRoom()) + "," +
                Double.toString(this.getPrice())    + "," +
                Double.toString(this.getSize())     + "," +
                Boolean.toString(hasGarage);
        return csvData;
    }
}
