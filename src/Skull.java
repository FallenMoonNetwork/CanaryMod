/**
 * Interface for the OTileEntitySkull class
 *
 * @author gregthegeek
 *
 */
public class Skull implements ComplexBlock {
    private final OTileEntitySkull skullBase;

    public Skull(OTileEntitySkull skullBase) {
        this.skullBase = skullBase;
    }

    @Override
    public int getX() {
        return skullBase.l;
    }

    @Override
    public int getY() {
        return skullBase.m;
    }

    @Override
    public int getZ() {
        return skullBase.n;
    }

    @Override
    public void update() {
        skullBase.k_();
    }

    @Override
    public Block getBlock() {
        return getWorld().getBlockAt(getX(), getY(), getZ());
    }

    @Override
    public World getWorld() {
        return skullBase.k.world;
    }

    /**
     * Returns the name of the skin this skull shows, if any
     *
     * @return
     */
    public String getPlayerName() {
        return skullBase.c();
    }

    /**
     * Sets the type of skull this is
     *
     * @param type the type of mob this belongs to (0-4)
     * @param playerName determines the skin of this head if it's a player head
     */
    public void setType(int type, String playerName) {
        skullBase.a(type, playerName);
    }

    /**
     * Sets the orientation of this skull (0-15?)
     *
     * @param rot
     */
    public void setOrientation(int rot) {
        skullBase.a(rot);
    }

    public OTileEntitySkull getBaseSkull() {
        return skullBase;
    }

    @Override
    public NBTTagCompound getMetaTag() {
        return skullBase.metadata;
    }

    @Override
    public void writeToTag(NBTTagCompound tag) {
        skullBase.b(tag.getBaseTag());
    }

    @Override
    public void readFromTag(NBTTagCompound tag) {
        skullBase.a(tag.getBaseTag());
    }
}
