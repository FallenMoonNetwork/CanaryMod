/**
 * BaseEntity.java - Class for accessing things that all entities share - W, X,
 * Y, health.
 *
 * @author James
 */
public class BaseEntity implements Metadatable {
    OEntity entity;

    /**
     * Creates an interface for an entity
     *
     * @param entity
     */
    public BaseEntity(OEntity entity) {
        this.entity = entity;
    }

    /**
     * Interface for entities.
     */
    public BaseEntity() {}

    /**
     * Returns the ID for this mob
     *
     * @return id
     */
    public int getId() {
        // SRG return this.entity.field_70157_k;
        return this.entity.k;
    }

    /**
     * Teleports to the provided location.
     *
     * @param x
     * @param rotation
     * @param y
     * @param z
     * @param pitch
     */
    public void teleportTo(double x, double y, double z, float rotation, float pitch) {
        // SRG this.entity.func_70012_b(x, y, z, rotation, pitch);
        this.entity.b(x, y, z, rotation, pitch);
    }

    /**
     * Teleports to the other entity.
     *
     * @param ent
     *            entity to teleport to
     */
    public void teleportTo(BaseEntity ent) {
        this.teleportTo(ent.getX(), ent.getY(), ent.getZ(), ent.getRotation(), ent.getPitch());
    }

    /**
     * Teleports to the provided location.
     *
     * @param location
     *            location to teleport to
     */
    public void teleportTo(Location location) {
        this.teleportTo(location.x, location.y, location.z, location.rotX, location.rotY);
    }

    /**
     * Returns the entity's W
     *
     * @return x
     */
    public double getX() {
        // SRG return this.entity.field_70165_t;
        return this.entity.u;
    }

    /**
     * Sets the entity's W
     *
     * @param x
     *            x to set
     */
    public void setX(double x) {
        this.teleportTo(x, this.getY(), this.getZ(), this.getRotation(), this.getPitch());
    }

    /**
     * Returns the entity's X
     *
     * @return y
     */
    public double getY() {
        // SRG return this.entity.field_70163_u;
        return this.entity.v;
    }

    /**
     * Sets the entity's X
     *
     * @param y
     *            y to set
     */
    public void setY(double y) {
        this.teleportTo(this.getX(), y, this.getZ(), this.getRotation(), this.getPitch());
    }

    /**
     * Returns the entity's Y
     *
     * @return z
     */
    public double getZ() {
        // SRG return this.entity.field_70161_v;
        return this.entity.w;
    }

    /**
     * Sets the entity's Y
     *
     * @param z
     *            z to set
     */
    public void setZ(double z) {
        this.teleportTo(this.getX(), this.getY(), z, this.getRotation(), this.getPitch());
    }

    /**
     * Returns the entity's pitch
     *
     * @return pitch
     */
    public float getPitch() {
        // SRG return this.entity.field_70125_A;
        return this.entity.B;
    }

    /**
     * Sets the entity's pitch
     *
     * @param pitch
     *            pitch to set
     */
    public void setPitch(float pitch) {
        this.teleportTo(this.getX(), this.getY(), this.getZ(), this.getRotation(), pitch);
    }

    /**
     * Returns the entity's rotation
     *
     * @return rotation
     */
    public float getRotation() {
        // SRG return this.entity.field_70177_z;
        return this.entity.A;
    }

    /**
     * Sets the entity's rotation
     *
     * @param rotation
     *            rotation to set
     */
    public void setRotation(float rotation) {
        this.teleportTo(this.getX(), this.getY(), this.getZ(), rotation, this.getPitch());
    }

    /**
     * Returns the entity we're wrapping.
     *
     * @return
     */
    public OEntity getEntity() {
        return this.entity;
    }

    /**
     * Returns whether or not this entity is a mob
     *
     * @return true if mob
     */
    public boolean isMob() {
        return this.entity instanceof OIMob;
    }

    public static boolean isMob(OEntity entity) {
        return entity instanceof OIMob;
    }

    /**
     * Returns whether or not this entity is an animal
     *
     * @return true if animal
     */
    public boolean isAnimal() {
        return this.entity instanceof OIAnimals;
    }

    public static boolean isAnimal(OEntity entity) {
        return entity instanceof OIAnimals;
    }

    /**
     * Returns true if this entity is a player
     *
     * @return true if player
     */
    public boolean isPlayer() {
        return this.entity instanceof OEntityPlayerMP;
    }

    public static boolean isPlayer(OEntity entity) {
        return entity instanceof OEntityPlayerMP;
    }

    /**
     * Returns whether or not this entity is alive
     *
     * @return true if living entity
     */
    public boolean isLiving() {
        return this.entity instanceof OEntityLiving;
    }

    public static boolean isLiving(OEntity entity) {
        return entity instanceof OEntityLiving;
    }

    /**
     * Returns whether or not this entity is an item entity
     *
     * @return true if item entity
     */
    public boolean isItem() {
        return this.entity instanceof OEntityItem;
    }

    public static boolean isItem(OEntity entity) {
        return entity instanceof OEntityItem;
    }

    /**
     * Returns the player for this entity
     *
     * @return player
     */
    public Player getPlayer() {
        if (!this.isPlayer()) {
            return null;
        }

        OEntityPlayerMP p = (OEntityPlayerMP) this.entity;

        return p.getPlayer();
    }

    /**
     * Get the default amount of AirTicks for this entity 20 ticks per second.
     *
     * @return 300
     * @deprecated It doesn't exist anymore P:
     */
    @Deprecated
    public int getBaseAirTicks() {
        return 300;
    }

    /**
     * Set the default amount of AirTicks for this entity 20 ticks per second.
     *
     * @param ticks
     * @deprecated It doesn't exist anymore
     * @throws UnsupportedOperationException because it doesn't exist anymore
     */
    @Deprecated
    public void setBaseAirTicks(int ticks) {
        throw new UnsupportedOperationException("BaseAirTicks has been abolished by Notch.");
    }

    /**
     * Get the current NoDamageTicks for this entity.
     *
     * This gets lowered every game tick, until its smaller than half the
     * BaseNoDamageTicks it only registers any damage more than
     * {@link LivingEntity#getLastDamage()}. 20 ticks per second.
     *
     * @return
     */
    public int getNoDamageTicks() {
        // SRG return this.getEntity().field_70172_ad;
        return this.getEntity().af;
    }

    /**
     * Set the current NoDamageTicks for this entity.
     *
     * This gets lowered every game tick, until its smaller than half the
     * BaseNoDamageTicks it only registers any damage more than
     * {@link LivingEntity#getLastDamage()}. 20 ticks per second.
     *
     * @param ticks
     */
    public void setNoDamageTicks(int ticks) {
        // SRG this.getEntity().field_70172_ad = ticks;
        this.getEntity().af = ticks;
    }

    /**
     * Get the amount of AirTicks left.
     *
     * This gets lowered every game tick when you are under water. 20 ticks per
     * second.
     *
     * @return
     */
    public int getAirTicks() {
        // SRG return this.getEntity().func_70086_ai();
        return this.getEntity().al();
    }

    /**
     * Set the amount of AirTicks left.
     *
     * This gets lowered every game tick when you are under water. 20 ticks per
     * second.
     *
     * @param ticks the number of ticks you have air
     */
    public void setAirTicks(int ticks) {
        // SRG this.getEntity().func_70050_g(ticks);
        this.getEntity().g(ticks);
    }

    /**
     * Get the amount of FireTicks left.
     *
     * This gets lowered every game tick when you are on fire. 20 ticks per
     * second.
     *
     * @return
     */
    public int getFireTicks() {
        // SRG return this.getEntity().field_70151_c;
        return this.getEntity().d;
    }

    /**
     * Set the amount of FireTicks left.
     *
     * This gets lowered every game tick when you are on fire. 20 ticks per
     * second.
     *
     * @param ticks the amount of fire ticks
     */
    public void setFireTicks(int ticks) {
        // SRG this.getEntity().field_70151_c = ticks;
        this.getEntity().d = ticks;
    }

    /**
     * Gets the World object from this entity.
     * @return the World this entity is in
     */
    public World getWorld() {
        // SRG return this.getEntity().field_70170_p.world;
        return this.getEntity().q.world;
    }

    /**
     * Returns the x-motion of this entity
     *
     * @return x-motion
     */
    public double getMotionX() {
        // SRG return this.entity.field_70159_w;
        return this.entity.x;
    }

    /**
     * Returns the y-motion of this entity
     *
     * @return y-motion
     */
    public double getMotionY() {
        // SRG return this.entity.field_70181_x;
        return this.entity.y;
    }

    /**
     * Returns the z-motion of this wntity
     *
     * @return z-motion
     */
    public double getMotionZ() {
        // SRG return this.entity.field_70179_y;
        return this.entity.z;
    }

    /**
     * Set entity motion
     *
     * @param motionX
     * @param motionY
     * @param motionZ
     */
    public void setMotion(double motionX, double motionY, double motionZ) {
        this.setMotionX(motionX);
        this.setMotionY(motionY);
        this.setMotionZ(motionZ);
    }

    /**
     * Sets the x-motion of this entity
     *
     * @param motion
     * motion to set
     */
    public void setMotionX(double motion) {
        // SRG this.entity.field_70159_w = motion;
        this.entity.x = motion;
        // SRG this.entity.field_70133_I = true;
        this.entity.J = true;
    }

    /**
     * Sets the y-motion of this entity
     *
     * @param motion
     * motion to set
     */
    public void setMotionY(double motion) {
        // SRG this.entity.field_70181_x = motion;
        this.entity.y = motion;
        // SRG this.entity.field_70133_I = true;
        this.entity.J = true;
    }

    /**
     * Sets the z-motion of this entity
     *
     * @param motion
     * motion to set
     */
    public void setMotionZ(double motion) {
        // SRG this.entity.field_70179_y = motion;
        this.entity.z = motion;
        // SRG this.entity.field_70133_I = true;
        this.entity.J = true;
    }

    /**
     * Destroys this entity
     */
    public void destroy() {
        // SRG this.entity.func_70106_y();
        this.entity.x();
    }

    /**
     * Returns this mob's name
     *
     * @return name
     */
    public String getName() {
        // SRG return OEntityList.func_75621_b(this.entity);
        return OEntityList.b(this.entity);
    }

    /**
     * Returns whether this entity is sprinting.
     * @return the sprinting state
     */
    public boolean getSprinting() {
        // SRG return this.entity.func_70051_ag();
        return this.entity.ai();
    }

    /**
     * Set whether this entity is sprinting.
     * Note: for players, this may not make them go faster.
     * @param sprinting
     */
    public void setSprinting(boolean sprinting) {
        // SRG this.entity.func_70031_b(sprinting);
        this.entity.c(sprinting);
    }

    /**
     * Writes this entity's data to an NBTTagCompound
     *
     * @param tag the tag to write the data to
     * @param includeId whether or not to include the entity id in the write
     * @return whether or not the write was successful
     */
    public boolean writeToTag(NBTTagCompound tag, boolean includeId) {
        if (includeId) {
            // SRG return getEntity().func_98035_c(tag.getBaseTag());
            return getEntity().c(tag.getBaseTag());
        }
        // SRG getEntity().func_70109_d(tag.getBaseTag());
        getEntity().e(tag.getBaseTag());
        return true;
    }

    /**
     * Reads this entity's data from an NBTTagCompound
     *
     * @param tag the tag to read the data from
     */
    public void readFromTag(NBTTagCompound tag) {
        // SRG getEntity().func_70020_e(tag.getBaseTag());
        getEntity().f(tag.getBaseTag());
    }

    @Override
    public NBTTagCompound getMetaTag() {
        return getEntity().metadata;
    }

    /**
     * Returns the location of this entity.
     *
     * @return location
     */
    public Location getLocation() {
        Location loc = new Location();

        loc.x = getX();
        loc.y = getY();
        loc.z = getZ();
        loc.rotX = getRotation();
        loc.rotY = getPitch();
        loc.setWorld(getWorld());
        return loc;
    }

    /**
     * Spawns this entity
     */
    public void spawn() {
        spawn((LivingEntity) null);
    }

    /**
     * Spawns this entity with a rider
     *
     * @param rider
     */
    public void spawn(LivingEntity rider) {
        OWorld world = this.getWorld().getWorld();

        this.teleportTo(getX(), getY(), getZ(), getRotation(), getPitch());
        // SRG world.func_72838_d(entity);
        world.d(entity);

        if (rider != null) {
            OEntityLiving mob2 = rider.getEntity();

            rider.teleportTo(getX(), getY(), getZ(), getRotation(), 0f);
            // SRG world.func_72838_d(mob2);
            world.d(mob2);
            rider.setRidingEntity(this);
        }
    }

    /**
     * Returns whether or not this entity is invulnerable.
     *
     * @return
     */
    public boolean isInvulnerable() {
        // SRG return getEntity().func_85032_ar();
        return getEntity().ar();
    }

    /**
     * Sets whether or not this entity is invulnerable.
     *
     * @param isInvulnerable
     */
    public void setInvulnerable(boolean isInvulnerable) {
        // SRG entity.field_83001_bt = isInvulnerable;
        entity.h = isInvulnerable;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BaseEntity) {
            return ((BaseEntity) obj).getId() == getId();
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Entity[id=%d, name=%s, location=%s]", getId(), getName(), getLocation());
    }

    /**
     * Gets the "passenger" for this entity. Passenger is any entity that is on top of this entity.
     * Example player in a minecart, the player is the passenger.
     * @return
     */
    public BaseEntity getRiddenByEntity() {
        // SRG return this.entity.field_70153_n == null ? null : this.entity.field_70153_n.getEntity();
        return this.entity.n == null ? null : this.entity.n.getEntity();
    }

    /**
     * Sets the entity's rider.
     * @param entity
     */
    public void setRiddenByEntity(BaseEntity entity) {
        if (entity == null) {
            if (this.getRiddenByEntity() != null) {
                this.getRiddenByEntity().dismount();
            }
        } else {
            entity.setRidingEntity(this);
        }
    }

    /**
     * Get the "vehicle" for this entity. Vehicle is any entity that is underneath this entity.
     * Example player in a minecart, the minecart is the vehicle.
     * @return
     */
    public BaseEntity getRidingEntity() {
        // SRG return this.entity.field_70154_o == null ? null : this.entity.field_70154_o.getEntity();
        return this.entity.o == null ? null : this.entity.o.getEntity();
    }

    /**
     * Sets the entity's "vehicle".
     * Can also be used to dismount vehicle.
     * @param entity
     */
    public void setRidingEntity(BaseEntity entity) {
        // SRG this.entity.func_70078_a((OEntity) (entity == null ? null : entity.getEntity()));
        this.entity.a((OEntity) (entity == null ? null : entity.getEntity()));
    }

    /**
     * Dismounts entity from vehicle.
     */
    public void dismount() {
        setRidingEntity(null);
    }

    /**
     * If this entity is marked as "to be removed". This commonly happens next tick,
     * so something can be destroyed and the next tick can still be detected as being in the world.
     * @return
     */
    public boolean isDead() {
        // SRG return this.entity.field_70128_L;
        return this.entity.M;
    }

    /**
     * If this entity is touching the ground.
     *
     * @return boolean True if on ground, false if in midair.
     */
    public boolean isOnGround() {
        // SRG return this.entity.field_70122_E;
        return this.entity.F;
    }

    /**
     * Gets the height of this entity's eyes above its feet.
     * Will return 0 if this entity has no eyes.
     * @return
     */
    public float getEyeHeight() {
        // SRG return getEntity().func_70047_e();
        return getEntity().f();
    }

    /**
     * Returns whether or not this entity is currently sneaking (crouching).
     *
     * @return true if sneaking
     */
    public boolean getSneaking() {
        // SRG return getEntity().func_70093_af();
        return getEntity().ah();
    }

    /**
     * Force this entity to be sneaking or not
     *
     * @param sneaking
     *            true if sneaking
     */
    public void setSneaking(boolean sneaking) {
        // SRG getEntity().func_70095_a(sneaking);
        getEntity().b(sneaking);
    }

    /**
     * Gets the entity's mob spawner.
     * @return MobSpawner of the entity, or null if it wasn't spawned with a mob spawner.
     */
    public MobSpawner getSpawner() {
        MobSpawnerLogic spawner = this.getEntity().spawner;
        return spawner instanceof MobSpawner ? (MobSpawner) spawner : null;
    }
}
