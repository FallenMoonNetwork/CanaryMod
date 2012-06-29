/**
 * HookParamtersConnect.java - send/receive parameters from hooks
 * 
 * @author James
 */

public class HookParametersLogincheck extends HookParameters {
    private String world, playerName, ip, kickReason;

    public HookParametersLogincheck(String world, String playerName, String ip) {
        this.world = world;
        this.playerName = playerName;
        this.ip = ip;
        this.kickReason = null;
    }
    /**
     * Get name of joining player
     * @return
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * get IP of joining player
     * @return
     */
    public String getIp() {
        return ip;
    }

    /**
     * Get the world of joining player (mostly default, you may set it to another world)
     * @return
     */
    public String getWorldName() {
        return world;
    }

    /**
     * Set the world name for the joining player
     * @param world
     */
    public void setWorldName(String world) {
        this.world = world;
    }
    
    public void setKickReason(String reason) {
        this.kickReason = reason;
    }
    
    public String getKickReason() {
        return this.kickReason;
    }
}
