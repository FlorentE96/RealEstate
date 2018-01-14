package app;

/**
 * <b>Apartment class.</b>
 * <p>Apartment is a subclass of Property</p>
 * <p>Each apartment has :</p>
 * <ul>
 *     <li>The common data of all properties (address, number of rooms, size, price and garage option)</li>
 *     <li>A boolean representing the presence of a terrace</li>
 *     <li>A boolean representing the presence of an elevator</li>
 *     <li>A floor number (int)</li>
 *     <li>An apartment number (int)</li>
 * </ul>
 *
 * @see Property
 *
 * @author  Florent
 * @version 1.0
 */
public class Apartment extends Property {

    /**
     * The floor of the apartment.
     *
     */
    private int floor;

    /**
     * The number of the apartment;
     *
     */
    private int number;

    /**
     * If the apartment has a terrace : TRUE, else FALSE.
     *
     */
    private boolean hasTerrace;

    /**
     * If the apartment has an elevator : TRUE, else FALSE.
     *
     */
    private boolean hasElevator;

    /**
     * Constructor for Apartment.
     *
     * @param _address The address of the apartment.
     * @param _numRooms The number of rooms in the apartment.
     * @param _price The price of the apartment.
     * @param _size The size of the apartment.
     * @param _hasGarage True if the apartment has a garage.
     * @param _hasTerrace True if the apartment has a terrace.
     * @param _hasElevator True if the apartment has an elevator.
     * @param _floor The floor number of the apartment.
     * @param _number The number of the apartment.
     *
     * @see Property#Property(String, int, double, double, boolean)
     */
    public Apartment(String _address, int _numRooms, double _price, double _size, boolean _hasGarage, boolean _hasTerrace, boolean _hasElevator, int _floor, int _number){
        super(_address, _numRooms, _price, _size, _hasGarage);
        hasTerrace = _hasTerrace;
        hasElevator = _hasElevator;
        floor = _floor;
        number = _number;
    }

    /**
     * Overloaded constructor for Apartment.
     * Default values are false for garage, apartment, garden, 0 floors, apt. 1.
     *
     * @see Property#Property()
     */
    public Apartment()
    {
        super();
        hasTerrace = false;
        hasElevator = false;
        floor = 0;
        number = 1;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isHasTerrace() {
        return hasTerrace;
    }

    public void setHasTerrace(boolean hasTerrace) {
        this.hasTerrace = hasTerrace;
    }

    public boolean isHasElevator() {
        return hasElevator;
    }

    public void setHasElevator(boolean hasElevator) {
        this.hasElevator = hasElevator;
    }

    /**
     * Overriden method to retrieve CSV data in a String, from the apartment's attributes
     * Data is the following, separated by commas :
     * <ul>
     *     <li>The identifier "apt" to specify the type of property</li>
     *     <li>The common data of all properties (address, number of rooms, size, price and garage option)</li>
     *     <li>The presence of a terrace</li>
     *     <li>The presence of an elevator</li>
     *     <li>The floor of the apartment</li>
     *     <li>The number of the apartment</li>
     * </ul>
     *
     * @return A string containing the CSV data.
     *
     * @see Property#getCSVData()
     */
    @Override
    public String getCSVData() {
        return ("apt" + "," + super.getCSVData() + "," +
                Boolean.toString(this.isHasTerrace()) + "," +
                Boolean.toString(this.isHasElevator()) + "," +
                Integer.toString(this.getFloor()) + "," +
                Integer.toString(this.getNumber()));
    }
}
