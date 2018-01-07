/**
 * <b>Apartment is a subclass of Property</b>
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
// TODO: Write JavaDoc
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

    public Apartment(String _neighbourhood, String _nRooms, double _price, HouseStatus _status, Owner _owner, Renter _renter, bollean _hadGarage, int _floor, int _number){
        super(String _neighbourhood, String _nRooms, double _price, HouseStatus _status, Owner _owner, Renter _renter, bollean _hadGarage)
        floor = _floor;
        number = _number;
    }
}
