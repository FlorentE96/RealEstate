package app;

/**
 * <b>House class.</b>
 * <p>House is a subclass of Property</p>
 * <p>Each house has :</p>
 * <ul>
 *     <li>The common data of all properties (address, number of rooms, size, price and garage option)</li>
 *     <li>A boolean representing the presence of a garden</li>
 *     <li>A boolean representing the presence of an pool</li>
 * </ul>
 *
 * @see Property
 *
 * @author  Florent
 * @version 1.0
 */
public class House extends Property {

    /**
     * If the property has a garden : TRUE, else FALSE.
     *
     */
    private boolean hasGarden;

    /**
     * If the property has a pool : TRUE, else FALSE.
     *
     */
    private boolean hasPool;

    /**
     * Default constructor for class House.
     * All parameters are initialized to false.
     *
     * @see Property#Property()
     */
    public House()
    {
        super();
        hasGarden = false;
        hasPool = false;
    }

    /**
     * Overloaded Constructor for class House.
     * All settings are initialized to false.
     *
     * @param _address The address of the house.
     *
     * @see House#House()
     * @see Property#Property(String)
     */
    public House(String _address)
    {
        super(_address);
        hasGarden = false;
        hasPool = false;
    }

    /**
     * Overloaded Constructor for class House.
     *
     * @param _address The address of the house.
     * @param _numRooms The number of rooms.
     * @param _size The size of the house.
     * @param _price The price of the house.
     * @param _hasGarage The presence of a garage.
     * @param _hasGarden The presence of a garden.
     * @param _hasPool The presence of a pool.
     *
     * @see Property#Property(String, int, double, double, boolean)
     * @see House#House()
     */
    public House(String _address, int _numRooms, double _size, double _price,
                 boolean _hasGarage, boolean _hasGarden, boolean _hasPool)
    {
        super(_address, _numRooms, _size, _price, _hasGarage);
        hasGarden = _hasGarden;
        hasPool = _hasPool;
    }

    public boolean isHasGarden() {
        return hasGarden;
    }

    public void setHasGarden(boolean hasGarden) {
        this.hasGarden = hasGarden;
    }

    public boolean isHasPool() {
        return hasPool;
    }

    public void setHasPool(boolean hasPool) {
        this.hasPool = hasPool;
    }

    /**
     * Overriden method to retrieve CSV data in a String, from the house's attributes
     * Data is the following, separated by commas :
     * <ul>
     *     <li>The identifier "house" to specify the type of property</li>
     *     <li>The common data of all properties (address, number of rooms, size, price and garage option)</li>
     *     <li>The presence of a garden</li>
     *     <li>The presence of a pool</li>
     * </ul>
     *
     * @return A string containing the CSV data.
     *
     * @see Property#getCSVData()
     */
    @Override
    public String getCSVData() {
        return ("house" + "," + super.getCSVData() + "," +
                Boolean.toString(this.hasGarden) + "," +
                Boolean.toString(this.hasPool));
    }
}
