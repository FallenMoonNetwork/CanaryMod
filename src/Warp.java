/**
 * Warp.java - Contains the stuff for a warp (Name, location, etc.). Also used
 * for homes.
 *
 * @author James
 */
public class Warp {

    /**
     * Warp ID - Used in database transactions.
     */
    public int      ID;

    /**
     * Warp name.
     */
    public String   Name;

    /**
     * Warp group.
     */
    public String   Group;

    /**
     * Warp's location.
     */
    public Location Location;
}
