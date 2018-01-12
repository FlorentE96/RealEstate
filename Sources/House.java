/**
 * <b>House is a subclass of Property</b>
 * <p>Each property has at least:
 * <ul>
 *     <li>A boolean hasGarden </li>
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
    private boolean hasGarden;

    public House(String _neighbourhood, int _numRooms, double _price, PropertyStatus _status, boolean _hadGarage, boolean _hasGarden){
            super(_neighbourhood, _numRooms, _price, _status, _hadGarage);
            hasGarden = _hasGarden;
        }

    public House(String _neighbourhood)
    {
        super(_neighbourhood);
        hasGarden = false;
    }
}
