/**
 * <b>House is a subclass of Property</b>
 * <p>Each property has at least:
 * <ul>
 *     <li>A bollean hasGarden </li>
 * </ul></p>
 *
 * @see
 *
 * @author  Florent
 * @author  Miron
 * @version 0.1
 */
// TODO: Write JavaDoc
public class House extends Property {
    /**
     * If the property had garage is TRUE, else is FALSE.
     *
     * @see
     */
    private bollean hasGarden;

    public House(String _neighbourhood, String _nRooms, double _price, HouseStatus _status, Owner _owner, Renter _renter, bollean _hadGarage, bollean _hasGarden){
            super(String _neighbourhood, String _nRooms, double _price, HouseStatus _status, Owner _owner, Renter _renter, bollean _hadGarage)
            hasGarden = _hasGarden;
        }

}
