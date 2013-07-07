
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LivingEntityBase extends BaseEntity {

    public LivingEntityBase() {}
    
    protected LivingEntityBase(OEntityLivingBase nms) {
        super(nms);
    }

    /**
     * Returns the entity we're wrapping.
     *
     * @return
     */
    @Override
    public OEntityLivingBase getEntity() {
        return (OEntityLivingBase) entity;
    }

    /**
     * Returns this entity's health.
     * @return This entity's health.
     */
    public float getHealthFloat() {
        return getEntity().aJ();
    }

    /**
     * Sets the entity's health. 20 = max health 1 = 1/2 heart 2 = 1 heart
     *
     * @param health The entity's new health.
     */
    public void setHealth(float health) {
        getEntity().g(health);
    }

    /**
     * Increase entity health.
     *
     * @param health
     *            amount of health to increase the players health with.
     */
    public void increaseHealth(float health) {
        getEntity().f(health);
    }

    /**
     * Get an entities max health value
     * @return max health
     */
    public double getMaxHealthDouble() {
        return getEntity().aP();
    }

    /**
     * Set the entities max health
     * @param toSet max health
     */
    public void setMaxHealth(double maxHealth) {
        if (maxHealth > 0) {
            getEntity().a(OSharedMonsterAttributes.a).a(maxHealth);
        }
    }

    /**
     * Set the amount of ticks this entity will not take damage. (until it
     * heals) 20 ticks per second.
     *
     * @param ticks
     */
    public void setBaseNoDamageTicks(int ticks) {
        getEntity().aI = ticks;
    }

    /**
     * Get the current maximum damage taken during this NoDamageTime
     *
     * @return
     */
    public float getLastDamageFloat() {
        return getEntity().bc;
    }

    /**
     * Set the current maximum damage taken during this NoDamageTime (if any
     * damage is higher than this number the difference will be added)
     *
     * @param amount
     */
    public void setLastDamage(float amount) {
        getEntity().bc = amount;
    }

    /**
     * Adds a potion effect to the entity.
     *
     * @param effect the effect to add.
     */
    public void addPotionEffect(PotionEffect effect) {
        getEntity().d(effect.potionEffect);
    }

    /**
     * Removes a potion effect from entity.
     *
     * @param effect The potion effect to remove
     */
    public void removePotionEffect(PotionEffect effect) {
        getEntity().k(effect.getType().getId());
    }

    /**
     * Returns whether this entity has a potion effect of the specified type.
     * @param effectType The potion effect type to check for
     * @return whether this entity has a potion effect of the specified type
     */
    public boolean hasPotionEffect(PotionEffect.Type effectType) {
        return getEntity().i(effectType.getId());
    }

    /**
     * Returns a Collection of potion effects active on the entity.
     *
     * @return List of potion effects
     */
    @SuppressWarnings("unchecked")
    public List<PotionEffect> getPotionEffects() {
        Collection<OPotionEffect> potionEffects = getEntity().aH();
        ArrayList<PotionEffect> list = new ArrayList<PotionEffect>();

        for (OPotionEffect potionEffect : potionEffects) {
            list.add(potionEffect.potionEffect);
        }
        return list;
    }

    /**
     * Damages this entity, taking into account armor/enchantments/potions
     * @param type
     * @param amount
     */
    public void applyDamage(DamageType type, float amount) {
        getEntity().d(type.getDamageSource().getDamageSource(), amount);
    }
}
