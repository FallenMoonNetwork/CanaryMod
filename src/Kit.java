import java.util.Map;


/**
 * Kit.java - Contains the stuff for a kit.
 *
 * @author James
 */
public class Kit {

    /**
     * Kit ID - Used in database transactions
     */
    public int                  ID;

    /**
     * Kit Name
     */
    public String               Name;

    /**
     * List of Item IDs and amounts to give
     */
    public Map<String, Integer> IDs;

    /**
     * Delay between uses
     */
    public int                  Delay;

    /**
     * Group that can use this kit.
     */
    public String               Group;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kit other = (Kit) obj;
        if ((this.Name == null) ? (other.Name != null) : !this.Name.equals(other.Name)) {
            return false;
        }
        if (this.IDs != other.IDs && (this.IDs == null || !this.IDs.equals(other.IDs))) {
            return false;
        }
        if (this.Delay != other.Delay) {
            return false;
        }
        if ((this.Group == null) ? (other.Group != null) : !this.Group.equals(other.Group)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.Name != null ? this.Name.hashCode() : 0);
        hash = 97 * hash + (this.IDs != null ? this.IDs.hashCode() : 0);
        hash = 97 * hash + this.Delay;
        hash = 97 * hash + (this.Group != null ? this.Group.hashCode() : 0);
        return hash;
    }
}
