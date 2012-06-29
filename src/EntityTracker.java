/**
 * CanaryMod entity tracker wrapper.
 * In order to manage your players per world
 * @author Chris Ksoll
 *
 */
public class EntityTracker {
    private OEntityTracker tracker;
    
    public EntityTracker(OEntityTracker tracker) {
        this.tracker = tracker;
    }
    
    /**
     * Add a player to this entity tracker
     * @param player
     */
    public void trackPlayer(Player player) {
        tracker.a(player.getEntity());
    }
    
    /**
     * Track a new entity
     * @param entity
     */
    public void trackEntity(OEntity entity) {
        tracker.a(entity);
    }
    
    /**
     * Remove a player from this entity tracker
     * @param player
     */
    public void untrackPlayer(Player player) {
        tracker.b((OEntity)player.getEntity());
    }
    
    public void untrackEntity(OEntity entity) {
        tracker.b(entity);
    }
    
    public void untrackPlayerSymmetrics(OEntityPlayerMP player) {
        tracker.a(player);
    }
    
    public void updateTrackedEntities() {
        tracker.a();
    }
    
    public void sendPacketToPlayersAndEntity(OEntity entity, OPacket packet) {
        tracker.b(entity, packet);
    }
    
    public OEntityTracker getTracker() {
        return tracker;
    }
}
