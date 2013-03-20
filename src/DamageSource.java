/**
 * A basic DamageSource
 * @author chris
 *
 */
public class DamageSource {
    
    protected ODamageSource osource;
    
    /**
     * Create a new CanaryMod DamageSource from an ODamageSource
     * @param osource
     */
    public DamageSource(ODamageSource osource) {
        this.osource = osource;
    }
    
    /**
     * Get the death message for the player that will be displayed in chat,
     * if the player dies from this {@link DamageSource}
     * @param player
     * @return
     */
    public String getDeathMessage(Player player) {
        return osource.b(player.getEntity());
    }
    
    /**
     * Get the name of the damage type as defined
     * when the underlying ODamageSource was created.
     * @return
     */
    public String getName() {
        return osource.o;
    }
    
    /**
     * Get the Entity that is causing the damage.
     * This may return null if the the damage was not caused by an entity.
     * @return
     */
    public BaseEntity getDamageSourceEntity() {
        if(osource.i() != null) {
            return osource.i().getEntity();
        }
        return null;
    }
    
    /**
     * Checks if this DamageSource is a projectile.
     * @return true if projectile, false otherwise
     */
    public boolean isProjectileDamage() {
        return osource.a();
    }
    
    /**
     * Mark this DamageSource as projectile and return an instance.
     * @return this
     */
    public DamageSource setProjectileDamage() {
        osource.b();
        return this;
    }
    
    /**
     * Checks if this DamageSOurce is an explosion.
     * @return true if is explosion, false otherwise
     */
    public boolean isExplosionDamage() {
        return osource.c();
    }
    
    /**
     * Mark this DamageSource as explosion and return an instance.
     * @return this
     */
    public DamageSource setExplosionDamage() {
        osource.d();
        return this;
    }
    
    /**
     * Check if this is fire damage
     * @return true if fire damage, false otherwise
     */
    public boolean isFireDamage() {
        return osource.m();
    }
    
    /**
     * Check if this is magic damage.
     * @return true if magic damage, false otherwise
     */
    public boolean isMagicDamage() {
        return osource.q();
    }
    
    /**
     * Mark this DamageSource as magic damage and return an instance
     * @return this
     */
    public DamageSource setMagicDamage() {
        osource.r();
        return this;
    }
    /**
     * Returns true when this damage source cannot be blocked.
     * @return
     */
    public boolean isUnblockable() {
        return osource.e();
    }
    
    /**
     * Returns true when this damage also applies in creative-mode
     * @return
     */
    public boolean canDamageInCreativeMode() {
        return osource.g();
    }
    
    /**
     * Check if this DamageSource is caused by an entity
     * @return
     */
    public boolean isEntityDamageSource() {
        return osource instanceof OEntityDamageSource;
    }
    
    /**
     * Check if this DamageSource has an indirect cause (magic, explosions etc)
     * @return
     */
    public boolean isIndirectDamageSource() {
        return osource instanceof OEntityDamageSourceIndirect;
    }
    
    /**
     * Returns the amount of hunger that will be removed upon damage infliction.
     * @return
     */
    public float getHungerImpact() {
        return osource.f();
    }
    
    /**
     * Check if the damage done will be scaled down/up by difficulty level
     * @return
     */
    public boolean isDifficultyScaled() {
        return osource.p();
    }
    
    /**
     * Make this DamageSource dependent on difficulty level
     * @return
     */
    public DamageSource setDifficultyScaled() {
        osource.q();
        return this;
    }
    
    /**
     * Returns the native ODamageSource referenec
     * @return
     */
    public ODamageSource getDamageSource() {
        return osource;
    }
    
    public DamageType getDamageType() {
        return DamageType.fromDamageSource(this);
    }
    
    // Static util methods
    
    /**
     * Create a DamageSource for the given LivingEntity.
     * @param entity LivingEntity that causes the damage
     * @return {@link EntityDamageSource}
     */
    public static DamageSource createMobDamage(LivingEntity entity) {
        return ODamageSource.a(entity.getEntity()).getDamageSource();
    }
    
    /**
     * Create a DamageSource for the given Player.
     * @param player Player that causes the damage
     * @return {@link EntityDamageSource}
     */
    public static DamageSource createPlayerDamage(Player player) {
        return ODamageSource.a(player.getEntity()).getDamageSource();
    }
    
    /**
     * Creates a thorn-enchantment DamageSource that is caused by the given entity
     * @param entity
     * @return
     */
    public static DamageSource createThornsDamage(BaseEntity entity) {
        return ODamageSource.a(entity.getEntity()).getDamageSource();
    }
}
