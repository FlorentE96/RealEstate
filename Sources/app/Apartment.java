package app;

/**
 * <b>app.Apartment is a subclass of app.Property</b>
 * <p>Each aparment has at least:
 * <ul>
 *     <li>The floor of the appartment </li>
 *     <li>The number of the appartment </li>
 * </ul></p>
 *
 *
 * @author  Florent
 * @author  Miron
 * @version 0.1
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

    public Apartment(String _address, int _numRooms, double _price, double _size, boolean _hasGarage, boolean _hasTerrace, boolean _hasElevator, int _floor, int _number){
        super(_address, _numRooms, _price, _size, _hasGarage);
        hasTerrace = _hasTerrace;
        hasElevator = _hasElevator;
        floor = _floor;
        number = _number;
    }

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

    @Override
    public String getCSVData() {
        return ("apt" + "," + super.getCSVData() + "," +
                Boolean.toString(this.isHasTerrace()) + "," +
                Boolean.toString(this.isHasElevator()) + "," +
                Integer.toString(this.getFloor()) + "," +
                Integer.toString(this.getNumber()));
    }
}
