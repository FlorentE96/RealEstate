package app;

/**
 * <b>app.Apartment is a subclass of app.Property</b>
 * <p>Each aparment has at least:
 * <ul>
 *     <li>The floor of the appartment </li>
 *     <li>The number of the appartment </li>
 * </ul></p>
 *
 * @see
 *
 * @author  Florent
 * @author  Miron
 * @version 0.1
 */
public class Apartment extends Property {

    /**
     * The floor of the apartment.
     *
     * @see
     */
    private int floor;

    /**
     * The number of the apartment;
     *
     * @see
     */
    private int number;

    /**
     * If the apartment has a terrace : TRUE, else FALSE.
     *
     * @see
     */
    private boolean hasTerrace;

    /**
     * If the apartment has an elevator : TRUE, else FALSE.
     *
     * @see
     */
    private boolean hasElevator;

    public Apartment(String _address, int _numRooms, double _price, double _size, boolean _hasGarage, boolean _hasTerrace, boolean _hasElevator, int _floor, int _number){
        super(_address, _numRooms, _size, _price, _hasGarage);
        hasTerrace = _hasTerrace;
        hasElevator = _hasElevator;
        floor = _floor;
        number = _number;
    }

    @Override
    public String getCSVData() {
        String csvData = "apt" + "," + super.getCSVData() + "," +
                Boolean.toString(this.hasTerrace) + "," +
                Boolean.toString(this.hasElevator) + "," +
                Integer.toString(this.floor) + "," +
                Integer.toString(this.number);

        return csvData;
    }
}
