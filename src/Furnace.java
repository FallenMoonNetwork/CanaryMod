/**
 * Furnace.java - Interface for furnaces
 * 
 * @author 14mRh4X0r
 */
public class Furnace extends BaseContainerBlock<OTileEntityFurnace> implements ComplexBlock {

    public Furnace(OTileEntityFurnace furnace) {
        super(furnace, "Furnace");
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
     * Returns the number of ticks until the item to smelt is smolten.
     * @return cook time ticks
     */
    public short getCookTime() {
        return (short) container.c;
    }

    /**
     * Sets the number of ticks until the item to smelt is smolten.
     * @param time ticks of cooking left
     */
    public void setCookTime(short time) {
        container.c = time;
    }
}
