package app;

/**
 * <b>Property is the base class for all properties</b>
 * <p>Each property has:</p>
 * <ul>
 *     <li>An address</li>
 *     <li>A numbers of rooms</li>
 *     <li>A price</li>
 *     <li>A size</li>
 *     <li>The option of a garage</li>
 * </ul>
 *
 * @see     Apartment
 * @see     House
 *
 * @author  Florent
 * @version 1.0
 */
public abstract class Property {

    /**
     * The address of the property.
     */
    private String address;

    /**
     * The number of the rooms.
     */
    private int numRoom;

    /**
     * The price of the property, in $
     */
    private double price;

    /**
     * The size of the property, in m².
     */
    private double size;

    /**
     * If the property has a garage : TRUE, else  FALSE.
     */
    private boolean hasGarage;

    /**
     * Default constructor for class Property.
     * Values are all initialized to 0/false/empty except for the number of rooms, which is set to 1.
     */
    public Property()
    {
        address = "";
        size = 0.0;
        numRoom = 1;
        price = 0.0;
        hasGarage = false;
    }

    /**
     * Overloaded constructor for class Property.
     * Values are all initialized to 0/false/empty except for the number of rooms, which is set to 1.
     *
     * @param _address The address of the property.
     */
    public Property(String _address)
    {
        address = _address;
        size = 0.0;
        numRoom = 1;
        price = 0.0;
        hasGarage = false;
    }

    /**
     * Overloaded constructor for class Property
     *
     * @param _address The address of the property;
     * @param _numRoom The number of rooms.
     * @param _size The size of the property.
     * @param _price The price of the property.
     * @param _hasGarage The presence of a garage.
     */
    public Property(String _address, int _numRoom, double _size, double _price, boolean _hasGarage)
    {
        address = _address;
        size = _size;
        numRoom = _numRoom;
        price = _price;
        hasGarage = _hasGarage;
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

    /**
     * Overridden method to convert the object to a string.
     * The address is the only attribute used.
     *
     * @return The string corresponding to the object.
     */
    @Override
    public String toString()
    {
        return address;
    }

    /**
     * Returns a String containing the property's data, in CSV format.
     * Data is the following, separated by commas :
     * <ul>
     *     <li>The address of the property</li>
     *     <li>The number of rooms</li>
     *     <li>The price</li>
     *     <li>The size</li>
     *     <li>The presence of a garage</li>
     * </ul>
     *
     * @return A string containing the CSV data.
     */
    public String getCSVData()
    {
        return (this.getAddress()          + "," +
                Integer.toString(this.getNumRoom()) + "," +
                Double.toString(this.getPrice())    + "," +
                Double.toString(this.getSize())     + "," +
                Boolean.toString(hasGarage));
    }
}
