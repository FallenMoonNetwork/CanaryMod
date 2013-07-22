/**
 * Wrapper around a stat.
 * @author Brian McCarthy
 *
 */
public class Stat {

    /**
     * OStatBase we are wrapping.
     */
    private OStatBase statBase;

    /**
     * Constructor.
     * @param statBase OStatBase to wrap.
     */
    public Stat(OStatBase statBase) {
        this.statBase = statBase;
    }

    /**
     * Get the id of this stat.
     * May not be very useful apart from comparing equality of stats with different names.
     * E.G. if Minecraft changes the name
     * @return
     */
    public int getID() {
        // SRG return statBase.field_75975_e;
        return statBase.e;
    }

    /**
     * Gets the name of the stat.
     * NOTE: This has gone through Minecraft's string translate to get a readable form/
     * @return
     */
    public String getName() {
        return statBase.toString();
    }

    /**
     * Return is this stat is independent.
     * Unsure what this does at the moment.
     * @return
     */
    public boolean isIndependent() {
        // SRG return statBase.field_75972_f;
        return statBase.f;
    }

    /**
     * Get the base wrapped stat.
     * @return
     */
    public OStatBase getBase() {
        return statBase;
    }

}
