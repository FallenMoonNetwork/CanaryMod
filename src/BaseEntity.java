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
        return this.getEntity().ak();
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
        this.getEntity().d = ticks;
    }

    /**
     * Gets the World object from this entity.
     * @return the World this entity is in
     */
    public World getWorld() {
        return this.getEntity().q.world;
    }

    /**
     * Returns the x-motion of this entity
     *
     * @return x-motion
     */
    public double getMotionX() {
        return this.entity.x;
    }

    /**
     * Returns the y-motion of this entity
     *
     * @return y-motion
     */
    public double getMotionY() {
        return this.entity.y;
    }

    /**
     * Returns the z-motion of this wntity
     *
     * @return z-motion
     */
    public double getMotionZ() {
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
        this.entity.x = motion;
        this.entity.J = true;
    }

    /**
     * Sets the y-motion of this entity
     *
     * @param motion
     * motion to set
     */
    public void setMotionY(double motion) {
        this.entity.y = motion;
        this.entity.J = true;
    }

    /**
     * Sets the z-motion of this entity
     *
     * @param motion
     * motion to set
     */
    public void setMotionZ(double motion) {
        this.entity.z = motion;
        this.entity.J = true;
    }

    /**
     * Destroys this entity
     */
    public void destroy() {
        this.entity.w();
    }

    /**
     * Returns this mob's name
     *
     * @return name
     */
    public String getName() {
        return OEntityList.b(this.entity);
    }

    /**
     * Returns whether this entity is sprinting.
     * @return the sprinting state
     */
    public boolean getSprinting() {
        return this.entity.ah();
    }

    /**
     * Set whether this entity is sprinting.
     * Note: for players, this may not make them go faster.
     * @param sprinting
     */
    public void setSprinting(boolean sprinting) {
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
            return getEntity().c(tag.getBaseTag());
        }
        getEntity().e(tag.getBaseTag());
        return true;
    }

    /**
     * Reads this entity's data from an NBTTagCompound
     *
     * @param tag the tag to read the data from
     */
    public void readFromTag(NBTTagCompound tag) {
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
        loc.dimension = getWorld().getType().getId();
        loc.world = getWorld().getName();
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
        OWorld world = entity.q;

        entity.b(getX(), getY(), getZ(), getRotation(), getPitch());
        world.d(entity);

        if (rider != null) {
            OEntityLiving mob2 = rider.getEntity();

            mob2.b(getX(), getY(), getZ(), getRotation(), 0f);
            world.d(mob2);
            mob2.a(entity);
        }
    }

    /**
     * Returns whether or not this entity is invulnerable.
     *
     * @return
     */
    public boolean isInvulnerable() {
        return getEntity().aq();
    }

    /**
     * Sets whether or not this entity is invulnerable.
     *
     * @param isInvulnerable
     */
    public void setInvulnerable(boolean isInvulnerable) {
        entity.h = isInvulnerable;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BaseEntity) {
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
        if(this.entity.n == null)
            return null;
        return this.entity.n.getEntity();
    }

    /**
     * Sets the entity's rider.
     * @param entity
     */
    public void setRiddenByEntity(BaseEntity entity) {
        entity.getEntity().a(this.getEntity());
    }

    /**
     * Get the "vehicle" for this entity. Vehicle is any entity that is underneath this entity.
     * Example player in a minecart, the minecart is the vehicle.
     * @return
     */
    public BaseEntity getRidingEntity() {
        if(this.entity.o == null)
            return null;
        return this.entity.o.getEntity();
    }

    /**
     * Sets the entity's "vehicle".
     * Can also be used to dismount vehicle.
     * @param entity
     */
    public void setRidingEntity(BaseEntity entity) {
        if(entity == null)
            this.entity.a((OEntity)null);
        else
            this.entity.a(entity.getEntity());
    }

    /**
     * Dismounts entity from vehicle
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
        return this.entity.M;
    }

    /**
     * If this entity is touching the ground.
     *
     * @return boolean True if on ground, false if in midair.
     */
    public boolean isOnGround() {
        return this.entity.F;
    }

    /**
     * Gets the height of this entity's eyes above its feet.
     * Will return 0 if this entity has no eyes.
     * @return
     */
    public float getEyeHeight() {
        return getEntity().e();
    }

    /**
     * Returns whether or not this entity is currently sneaking (crouching).
     *
     * @return true if sneaking
     */
    public boolean getSneaking() {
        return getEntity().ag();
    }

    /**
     * Force this entity to be sneaking or not
     *
     * @param sneaking
     *            true if sneaking
     */
    public void setSneaking(boolean sneaking) {
        getEntity().b(sneaking);
    }
}
