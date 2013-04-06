/**
 *
 * @author Meaglin
 */
public class Dispenser extends BaseContainerBlock<OTileEntityDispenser> implements ComplexBlock {

    public Dispenser(OTileEntityDispenser disp) {
        this(null, disp);
    }
    public Dispenser(OContainer oContainer, OTileEntityDispenser disp) {
        super(oContainer, disp, "Trap");
    }

    public void fire() {
        OWorld oworld = this.getWorld().getWorld();

        // OBlock.P = OBlockDispenser, n = dispense
        ((OBlockDispenser) OBlock.T).j_(oworld, this.getX(), this.getY(), this.getZ());
    }
}
