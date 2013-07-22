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
        this.trackEntity(player.getEntity());
    }

    /**
     * Track a new entity
     * @param entity
     */
    public void trackEntity(OEntity entity) {
        // SRG tracker.func_72786_a(entity);
        tracker.a(entity);
    }

    /**
     * Remove a player from this entity tracker
     * @param player
     */
    public void untrackPlayer(Player player) {
        this.untrackEntity(player.getEntity());
    }

    public void untrackEntity(OEntity entity) {
        // SRG tracker.func_72790_b(entity);
        tracker.b(entity);
    }

    public void untrackPlayerSymmetrics(OEntityPlayerMP player) {
        // SRG tracker.func_72787_a(player);
        tracker.a(player);
    }

    public void updateTrackedEntities() {
        // SRG tracker.func_72788_a();
        tracker.a();
    }

    public void sendPacketToPlayersAndEntity(OEntity entity, OPacket packet) {
        // SRG tracker.func_72789_b(entity, packet);
        tracker.b(entity, packet);
    }

    public OEntityTracker getTracker() {
        return tracker;
    }
}
