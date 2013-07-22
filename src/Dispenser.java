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

        // SRG ((OBlockDispenser) OBlock.field_71958_P).func_82526_n(oworld, this.getX(), this.getY(), this.getZ());
        ((OBlockDispenser) OBlock.U).j_(oworld, this.getX(), this.getY(), this.getZ());
    }
}
