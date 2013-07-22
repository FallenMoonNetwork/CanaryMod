import java.util.ArrayList;
import java.util.List;

public class MobSpawnerLogic {

    private OMobSpawnerBaseLogic logic;

    @SuppressWarnings("LeakingThisInConstructor")
    public MobSpawnerLogic(OMobSpawnerBaseLogic logic) {
        this.logic = logic;
        this.logic.logic = this;
    }

    public int getX() {
        // SRG return logic.func_98275_b();
        return logic.b();
    }

    public int getY() {
        // SRG return logic.func_98274_c();
        return logic.c();
    }

    public int getZ() {
        // SRG return logic.func_98266_d();
        return logic.d();
    }

    public World getWorld() {
        // SRG return logic.func_98271_a().world;
        return logic.a().world;
    }

    public void update() {
        // SRG logic.func_98278_g();
        logic.g();
    }

    /**
     * Allows what to spawn to change on-the-fly.
     * <strong>NB:</strong> This only works if a complex entity has not been set.
     * @param spawn The name of the entity to spawn.
     * @see #setSpawnedEntity(BaseEntity)
     * @see #setSpawnedEntity(Item)
     */
    public void setSpawn(String spawn) {
        // SRG logic.func_98272_a(spawn);
        logic.a(spawn);
        update();
    }

    /**
     * Returns the spawn used.
     *
     * @return
     */
    public String getSpawn() {
        // SRG return logic.func_98276_e();
        return logic.e();
    }

    /**
     * Returns the current delay of the spawner. When this reaches 0, the
     * spawner tries to spawn a mob.
     * @return The current delay in ticks.
     */
    public int getDelay() {
        // SRG return logic.field_98286_b;
        return logic.b;
    }

    /**
     * Allows delay of what to spawn to change on-the-fly.
     * Modification of this is near-useless as delays get randomized after
     * spawn. Setting this to 0 causes the spawner to try to spawn a mob on the
     * next tick.
     *
     * @param delay The new delay
     */
    public void setDelay(int delay) {
        // SRG logic.field_98286_b = delay;
        logic.b = delay;
    }

    /**
     * Returns the minimum delay of the spawner.
     * The delay between spawns is picked randomly between this and the max delay.
     *
     * @return
     */
    public int getMinDelay() {
        // SRG return logic.field_98283_g;
        return logic.g;
    }

    /**
     * Sets the minimum delay of the spawner.
     * The delay between spawns is picked randomly between this and the max delay.
     * Default is 200.
     *
     * @param delay
     */
    public void setMinDelay(int delay) {
        // SRG logic.field_98283_g = delay;
        logic.g = delay;
    }

    /**
     * Returns the maximum delay of the spawner.
     * The delay between spawns is picked randomly between this and the min delay.
     *
     * @return
     */
    public int getMaxDelay() {
        // SRG return logic.field_98293_h;
        return logic.h;
    }

    /**
     * Sets the maximum delay of the spawner.
     * The delay between spawns is picked randomly between this and the min delay.
     * Default is 800.
     *
     * @param delay
     */
    public void setMaxDelay(int delay) {
        // SRG logic.field_98293_h = delay;
        logic.h = delay;
    }

    /**
     * Returns the amount of mobs this spawner attempts to spawn.
     *
     * @return
     */
    public int getSpawnCount() {
        // SRG return logic.field_98294_i;
        return logic.i;
    }

    /**
     * Sets the amount of mobs this spawner attempts to spawn.
     * Default is 4.
     *
     * @param count
     */
    public void setSpawnCount(int count) {
        // SRG logic.field_98294_i = count;
        logic.i = count;
    }

    /**
     * Returns the maximum number of entities this spawner allows nearby in order to continue spawning.
     * Any more entities and this spawner won't spawn mobs.
     *
     * @return
     */
    public int getMaxNearbyEntities() {
        // SRG return logic.field_98292_k;
        return logic.k;
    }

    /**
     * Sets the maximum number of entities this spawner allows nearby in order to continue spawning.
     * Any more entities and this spawner won't spawn mobs.
     * Default is 6.
     *
     * @param entities
     */
    public void setMaxNearbyEntities(int entities) {
        // SRG logic.field_98292_k = entities;
        logic.k = entities;
    }

    /**
     * If there are no players within this distance of the spawner, it won't spawn.
     *
     * @return
     */
    public int getRequiredPlayerRange() {
        // SRG return logic.field_98289_l;
        return logic.l;
    }

    /**
     * If there are no players within this distance of the spawner, it won't spawn.
     * Default is 16.
     *
     * @param range
     */
    public void setRequiredPlayerRange(int range) {
        // SRG logic.field_98289_l = range;
        logic.l = range;
    }

    /**
     * Returns the maximum distance that this spawner will spawn mobs at.
     *
     * @return
     */
    public int getSpawnRange() {
        // SRG return logic.field_98290_m;
        return logic.m;
    }

    /**
     * Sets the maximum distance that this spawner will spawn mobs at.
     * Default is 4.
     *
     * @param range
     */
    public void setSpawnRange(int range) {
        // SRG logic.field_98290_m = range;
        logic.m = range;
    }

    /**
     * Sets the entity spawned by this spawner.
     *
     * @param entity The entity this spawner should spawn
     */
    public void setSpawnedEntity(BaseEntity entity) {
        setSpawnedEntity(entity.getEntity());
    }

    /**
     * Sets the entity spawned by this spawner to an item.
     *
     * @param itemEntity The item this spawner should spawn
     */
    public void setSpawnedEntity(Item itemEntity) {
        setSpawnedEntity(new OEntityItem(null, 0, 0, 0, itemEntity.getBaseItem()));
    }

    /**
     * Sets the entity spawned by this spawner.
     *
     * @param entity The entity this spawner should spawn
     */
    public void setSpawnedEntity(OEntity entity) {
        // gets the tag with the id for this entity
        NBTTagCompound properties = new NBTTagCompound();
        entity.getEntity().writeToTag(properties, true);

        //sets the entity and weight for this spawn
        NBTTagCompound entry = new NBTTagCompound();
        entry.add("Type", properties.getString("id"));
        entry.add("Weight", 1);

        entry.add("Properties", properties);
        // SRG logic.func_98277_a(new OWeightedRandomMinecart(logic, entry.getBaseTag(), properties.getString("id")));
        logic.a(new OWeightedRandomMinecart(logic, entry.getBaseTag(), properties.getString("id")));
    }

    /**
     * Sets the entities spawned by this spawner.
     *
     * @param entity The entity this spawner should spawn
     */
    public void setSpawnedEntity(BaseEntity... entity) {
        List<OEntity> entities = new ArrayList<OEntity>();
        for (BaseEntity be : entity){
            entities.add((OEntity)be.getEntity());
        }
        setSpawnedEntity(entities.toArray());
    }

    /**
     * Sets the entities spawned by this spawner to items.
     *
     * @param itemEntity The item this spawner should spawn
     */
    public void setSpawnedEntity(Item... itemEntity) {
        List<OEntity> entities = new ArrayList<OEntity>();
        for (Item i : itemEntity){
            entities.add((OEntity)new OEntityItem(null, 0, 0, 0, i.getBaseItem()));
        }
        setSpawnedEntity(entities.toArray());
    }

    /**
     * Sets the entities spawned by this spawner.
     *
     * @param entities The list of entities this spawner should spawn
     */
    public void setSpawnedEntity(Object[] entities) {
        NBTTagCompound toSet = new NBTTagCompound();
        writeToTag(toSet);
        NBTTagList list = new NBTTagList();
        for (Object object : entities) {
            if (!(object instanceof OEntity)) {
                continue;
            }
            OEntity entity = (OEntity)object;
            // gets the tag with the id for this entity
            NBTTagCompound properties = new NBTTagCompound();
            entity.getEntity().writeToTag(properties, true);

            // sets the entity and weight for this spawn
            NBTTagCompound entry = new NBTTagCompound();
            entry.add("Type", properties.getString("id"));
            entry.add("Weight", 1);

            // sets the properties of this spawn.
            entry.add("Properties", properties);
            list.add(entry);
        }
        toSet.add("SpawnPotentials", list);
        readFromTag(toSet);
    }

    /**
     * Returns the wrapped (native) logic object.
     * @return the wrapped <tt>OMobSpawnerBaseLogic</tt>
     */
    public OMobSpawnerBaseLogic getLogic() {
        return logic;
    }

    /**
     * Writes this logic's data to an NBTTagCompound.
     *
     * @param tag the tag to write the data to
     */
    public void writeToTag(NBTTagCompound tag) {
        // SRG logic.func_98280_b(tag.getBaseTag());
        logic.b(tag.getBaseTag());
    }

    /**
     * Reads this logic's data from an NBTTagCompound.
     *
     * @param tag the tag to read the data from
     */
    public void readFromTag(NBTTagCompound tag) {
        // SRG logic.func_98270_a(tag.getBaseTag());
        logic.a(tag.getBaseTag());
    }
}
