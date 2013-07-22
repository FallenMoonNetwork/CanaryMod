

/**
 * Sign.java - Interface to tileEntitys
 *
 * @author James
 */
public class Sign extends ComplexBlockBase<OTileEntitySign> {

    /**
     * Creates a tileEntity interface
     *
     * @param localSign
     */
    public Sign(OTileEntitySign localSign) {
        super(localSign);
    }

    /**
     * Sets the line of text at specified index
     *
     * @param index
     *            line
     * @param text
     *            text
     */
    public void setText(int index, String text) {
        // SRG if (index >= 0 && tileEntity.field_70412_a.length > index) {
        if (index >= 0 && tileEntity.a.length > index) {
            // SRG tileEntity.field_70412_a[index] = text;
            tileEntity.a[index] = text;
        }
    }

    /**
     * Returns the line of text
     *
     * @param index
     *            line of text
     * @return text
     */
    public String getText(int index) {
        // SRG if (index >= 0 && tileEntity.field_70412_a.length > index) {
        if (index >= 0 && tileEntity.a.length > index) {
            // SRG return tileEntity.field_70412_a[index];
            return tileEntity.a[index];
        }
        return "";
    }

    @Override
    public void update() {
        // SRG getWorld().getWorld().func_72845_h(getX(), getY(), getZ());
        getWorld().getWorld().j(getX(), getY(), getZ());
    }

    /**
     * Returns a String value representing this Block
     *
     * @return String representation of this block
     */
    @Override
    public String toString() {
        return String.format("Sign [x=%d, y=%d, z=%d]", getX(), getY(), getZ());
    }

    /**
     * Tests the given object to see if it equals this object
     *
     * @param obj
     *            the object to test
     * @return true if the two objects match
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sign other = (Sign) obj;

        if (getX() != other.getX()) {
            return false;
        }
        if (getY() != other.getY()) {
            return false;
        }
        if (getZ() != other.getZ()) {
            return false;
        }
        if (!getWorld().equals(other.getWorld())) {
            return false;
        }
        return true;
    }

    /**
     * Returns a semi-unique hashcode for this block
     *
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;

        hash = 97 * hash + getX();
        hash = 97 * hash + getY();
        hash = 97 * hash + getZ();
        hash = 97 * hash + getWorld().hashCode();
        return hash;
    }
}
