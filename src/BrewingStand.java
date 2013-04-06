/**
 * Interface to brewing stands.
 * @author willem
 */
public class BrewingStand extends BaseContainerBlock<OTileEntityBrewingStand> implements ComplexBlock {

    public BrewingStand(OTileEntityBrewingStand brewingStand) {
        this(null, brewingStand);
    }
    public BrewingStand(OContainer oContainer, OTileEntityBrewingStand brewingStand) {
        super(oContainer, brewingStand, "Brewing Stand");
    }

    /**
     * Returns the time left to brew for.
     * Range is from 0 to 400.
     * @return this stand's brew time
     */
    public int getBrewTime() {
        return container.x_();
    }

}
