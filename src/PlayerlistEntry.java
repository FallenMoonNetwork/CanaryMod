/**
 * PlayerlistEntry.java - Object for passing to the onPlayerlistEntryGet Hook
 *
 * @author Talmor
 */
public class PlayerlistEntry {

    private String name;
    private int ping;
    private boolean show;

    public PlayerlistEntry(String name, int ping, boolean show) {
        super();
        this.setName(name);
        this.ping = ping;
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (etc.getInstance().isPlayerList_colors()) {
            this.name = name.substring(0, Math.min(name.length(), 14)) + Colors.Reset;
        } else {
            name = Colors.strip(name);
            this.name = name.substring(0, Math.min(name.length(), 16));
        }
    }

    public int getPing() {
        return ping;
    }

    public void setPing(int ping) {
        this.ping = ping;
    }

}
