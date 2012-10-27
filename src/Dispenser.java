/**
 * 
 * @author Meaglin
 */
public class Dispenser extends BaseContainerBlock implements ComplexBlock {

    public Dispenser(OTileEntityDispenser disp) {
        super(disp, "Trap");
    }
    
    public void fire() {
        OWorld oworld = this.getWorld().getWorld();
        
        // OBlock.P = OBlockDispenser, c = dispenseItem, oworld.v = Random
        ((OBlockDispenser) OBlock.P).c(oworld, this.getX(), this.getY(), this.getZ(), oworld.v);
    }
}
