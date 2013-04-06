/**
 * Implemented by objects that store customizable persistent metadata about themselves
 *
 * @author gregthegeek
 *
 */
public interface Metadatable {

    /**
     * Returns an NBTTagCompound that is saved with this object.
     * This tag is for plugin use only.
     * Changing values in this tag will not affect the behavior of the object.
     *
     * @return
     */
    public NBTTagCompound getMetaTag();

}
