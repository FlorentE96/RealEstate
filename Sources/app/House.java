package app;

/**
 * <b>app.House is a subclass of app.Property</b>
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
     * If the property has a garden : TRUE, else FALSE.
     *
     * @see
     */
    private boolean hasGarden;

    /**
     * If the property has a pool : TRUE, else FALSE.
     *
     * @see
     */
    private boolean hasPool;

    public House(String _address, int _numRooms, double _size, double _price, boolean _hasGarage, boolean _hasGarden, boolean _hasPool){
            super(_address, _numRooms, _size, _price, _hasGarage);
            hasGarden = _hasGarden;
            hasPool = _hasPool;
        }

    public House()
    {
        super();
        hasGarden = false;
        hasPool = false;
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

    public House(String _neighbourhood)
    {
        super(_neighbourhood);
        hasGarden = false;
        hasPool = false;
    }

    @Override
    public String getCSVData() {
        String csvData = "house" + "," + super.getCSVData() + "," +
                Boolean.toString(this.hasGarden) + "," +
                Boolean.toString(this.hasPool);

        return csvData;
    }
}
