/**
 * Furnace.java - Interface for furnaces
 *
 * @author 14mRh4X0r
 */
public class Furnace extends BaseContainerBlock<OTileEntityFurnace> implements ComplexBlock {

    public Furnace(OTileEntityFurnace furnace) {
        this(null, furnace);
    }
    public Furnace(OContainer oContainer, OTileEntityFurnace furnace) {
        super(oContainer, furnace, "Furnace");
    }

    /**
     * Returns the number of ticks the current fuel item has to go.
     * @return burn time ticks
     */
    public short getBurnTime() {
        return (short) container.a;
    }

    /**
     * Sets the number of ticks the current fuel item has to go.
     * @param time ticks of burning left
     */
    public void setBurnTime(short time) {
        container.a = time;
    }

    /**
     * Returns the number of ticks the item to smelt has smolten.
     * An item is ready on 200 ticks.
     * @return cook time ticks
     */
    public short getCookTime() {
        return (short) container.c;
    }

    /**
     * Sets the number of ticks the item to smelt has smolten.
     * An item is ready on 200 ticks.
     * @param time ticks of cooking
     */
    public void setCookTime(short time) {
        container.c = time;
    }
}
