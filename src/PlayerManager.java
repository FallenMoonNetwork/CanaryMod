
/**
 * CanaryMod Player manager wrapper
 * @author Chris Ksoll
 *
 */
public class PlayerManager {
    private OPlayerManager pm;
    
    public PlayerManager(OPlayerManager pm) {
        this.pm = pm;
    }
    
    /**
     * Update the given player
     * @param player
     */
    public void updateMountedMovingPlayer(OEntityPlayerMP player) {
        pm.c(player);
    }
    
    /**
     * Update given player
     * @param player
     */
    public void updateMountedMovingPlayer(Player player) {
        pm.c(player.getEntity());
    }
    
    /**
     * Add player to this player manager
     * @param player
     */
    public void addPlayer(OEntityPlayerMP player) {
        pm.a(player);
    }
    
    /**
     * Remove the given player from this player manager
     * @param player
     */
    public void removePlayer(OEntityPlayerMP player) {
        pm.b(player);
    }
    
    public void markBlockNeedsUpdate(int x, int y, int z) {
        pm.a(x, y, z);
    }
}
