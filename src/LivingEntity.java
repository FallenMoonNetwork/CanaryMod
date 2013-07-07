import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Interface for living entities.
 */
public class LivingEntity extends LivingEntityBase {

    /**
     * Interface for living entities
     */
    public LivingEntity() {}

    /**
     * Interface for living entities
     *
     * @param livingEntity
     */
    public LivingEntity(OEntityLiving livingEntity) {
        super(livingEntity);
    }

    /**
     * Returns the entity we're wrapping.
     *
     * @return
     */
    @Override
    public OEntityLiving getEntity() {
        return (OEntityLiving) entity;
    }

    /**
     * Returns the entity's health.
     *
     * @return health
     * @deprecated Health is measured in floats now, use
     * {@link #getHealthFloat()} instead.
     */
    @Deprecated
    public int getHealth() {
        return (int) getHealthFloat();
    }

    /**
     * Increase entity health.
     *
     * @param health
     *            amount of health to increase the players health with.
     * @deprecated Health is measured in floats now, use
     * {@link #increaseHealth(float)} instead.
     */
    @Deprecated
    public void increaseHealth(int health) {
        getEntity().f(health);
    }

    /**
     * Sets the entity's health. 20 = max health 1 = 1/2 heart 2 = 1 heart
     *
     * @param health The entity's new health.
     * @deprecated Health is measured in floats now, use
     * {@link #setHealth(float)} instead.
     */
    @Deprecated
    public void setHealth(int health) {
        setHealth((float) health);
    }

    /**
     * Get the amount of ticks this entity is dead. 20 ticks per second.
     *
     * @return
     */
    // TODO: pull up
    public int getDeathTicks() {
        return getEntity().aB;
    }

    /**
     * Set the amount of ticks this entity is dead. 20 ticks per second.
     *
     * @param ticks
     */
    // TODO: pull up
    public void setDeathTicks(int ticks) {
        getEntity().aB = ticks;
    }

    /**
     * Get an entities max health value
     * @return max health
     * @deprecated Max health is measured in doubles now, use
     * {@link #getMaxHealthDouble()} instead.
     */
    @Deprecated
    public int getMaxHealth(){
        return (int) getMaxHealthDouble();
    }

    /**
     * Set the entities max health
     * @param toSet max health
     * @deprecated Max health is measured in doubles now, use
     * {@link #setMaxHealth(double)} instead.
     */
    @Deprecated
    public void setMaxHealth(int toSet){
        setMaxHealth(toSet);
    }

    /**
     * Get the amount of ticks this entity will not take damage. (unless it
     * heals) 20 ticks per second.
     *
     * @return
     */
    // TODO: pull up
    public int getBaseNoDamageTicks() {
        return getEntity().aI;
    }

    /**
     * Get the current maximum damage taken during this NoDamageTime
     *
     * @return
     * @deprecated Damage is measured in floats now, use
     * {@link #getLastDamageFloat()} instead.
     */
    public int getLastDamage() {
        return (int) getLastDamageFloat();
    }

    /**
     * Set the current maximum damage taken during this NoDamageTime (if any
     * damage is higher than this number the difference will be added)
     *
     * @param amount
     * @deprecated Damage is measured in floats now, use
     * {@link #setLastDamage(float)} instead.
     */
    public void setLastDamage(int amount) {
        setLastDamage((float) amount);
    }

    /**
     * Drops this mob's loot. Automatically called if health is set to 0.
     */
    public void dropLoot() {
        getEntity().a(true, 0);
    }

    /**
<<<<<<< HEAD
     * Gets the entity's mob spawner.
     * @return MobSpawner of the entity, or null if it wasn't spawned with a mob spawner.
     */
    public MobSpawner getSpawner() {
        MobSpawnerLogic spawner = this.getEntity().spawner;
        return spawner instanceof MobSpawner ? (MobSpawner) spawner : null;
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
        OPotionEffect var3 = (OPotionEffect) getEntity().bn.get(effect.getType().getId());

        if (var3 == null) { // Return if we don't have the potion effect.
            return;
        }

        getEntity().bn.remove(Integer.valueOf(effect.getType().getId()));
        getEntity().c(var3);
    }

    /**
     * Returns whether this entity has a potion effect of the specified type.
     * @param effectType The potion effect type to check for
     * @return whether this entity has a potion effect of the specified type
     */
    public boolean hasPotionEffect(PotionEffect.Type effectType) {
        return getEntity().bn.containsKey(effectType.getId());
    }

    /**
     * Returns a Collection of potion effects active on the entity.
     *
     * @return List of potion effects
     */
    @SuppressWarnings("unchecked")
    public List<PotionEffect> getPotionEffects() {
        Collection<OPotionEffect> potionEffects = getEntity().bC();
        ArrayList<PotionEffect> list = new ArrayList<PotionEffect>();

        for (OPotionEffect potionEffect : potionEffects) {
            list.add(potionEffect.potionEffect);
        }
        return list;
    }

    /**
=======
>>>>>>> 73d9f76... Update to 1.6.1, less MD5/Versioning.
     * Sets the item held by the entity.
     *
     * @param item
     */
    public void setItemInHand(Item item) {
        getEntity().c(0, item.getBaseItem());
    }

    /**
     * Gets the item held by the entity.
     *
     * @return
     */
    public Item getItemStackInHand() {
        OItemStack stack = getEntity().aV();
        return stack == null ? null : new Item(stack);
    }

    /**
     * Sets an armor slot of the entity.
     *
     * @param slot
     *             The slot of the armor, 0 being boots and 3 being helmet
     * @param armor
     *             The item of armor to add
     */
    public void setArmorSlot(int slot, Item armor) {
        if(slot >= 0 && slot <= 3) {
            getEntity().c(slot + 1, armor.getBaseItem());
        }
    }

    /**
     * Gets the item in one of the entity's armor slots.
     *
     * @param slot
     *             The slot of the armor, 0 being boots and 3 being helmet
     * @return
     */
    public Item getArmorSlot(int slot) {
        if(slot < 0 || slot > 3) {
            return null;
        }
        OItemStack stack = getEntity().o(slot);
        return stack == null ? null : new Item(stack);
    }

    /**
     * Returns whether or not this entity will despawn naturally.
     *
     * @return
     */
    public boolean isPersistent() {
        return getEntity().bt;
    }

    /**
     * Sets whether or not this entity will despawn naturally.
     *
     * @param isPersistent
     */
    public void setPersistent(boolean isPersistent) {
        getEntity().bt = isPersistent;
    }

    /**
     * Returns the drop chance of an item owned by this entity.
     *
     * @param slot The slot of the item, 0-4: 0 is hand, 1-4 are armor slots (1=boots and 4=helmet)
     * @return The drop chance, 0-1 (anything greater than 1 is guaranteed to drop)
     */
    public float getDropChance(int slot) {
        if(slot >= 0 && slot <= 4) {
            return getEntity().e[slot];
        }
        return 0;
    }

    /**
     * Sets the drop chance of an item owned by this entity.
     *
     * @param slot The slot of the item, 0-4: 0 is hand, 1-4 are armor slots (1=boots and 4=helmet)
     * @param chance The drop chance, 0-1 (anything greater than 1 is guaranteed to drop)
     */
    public void setDropChance(int slot, float chance) {
        if(slot >= 0 && slot <= 4) {
            getEntity().e[slot] = chance;
        }
    }

    /**
     * Whether or not this entity can pick up items.
     *
     * @return
     */
    public boolean canPickUpLoot() {
        return getEntity().bz();
    }

    /**
     * Sets whether or not this entity can pick up items.
     *
     * @param flag
     */
    public void setCanPickUpLoot(boolean flag) {
        getEntity().h(flag);
    }

    /**
     * Damages this entity, taking into account armor/enchantments/potions
     *
     * @param type The type of damage to deal (certain types byass armor or affect potions differently)
     * @param amount The amount of damage to deal (2 = 1 heart)
     * @deprecated use applyDamage(DamageType, int)
     */
    @Deprecated
    public void applyDamage(PluginLoader.DamageType type, int amount) {
        getEntity().d(type.getDamageSource(), (float) amount);
    }

    /**
     * Damages this entity, taking into account armor/enchantments/potions
     * @param type
     * @param amount
     * @deprecated Damage is measured in floats now. Use
     * {@link #applyDamage(DamageType, float)} instead.
     */
    @Deprecated
    public void applyDamage(DamageType type, int amount) {
        getEntity().d(type.getDamageSource().getDamageSource(), (float) amount);
    }

    /**
     * Damages this entity, taking into account armor/enchantments/potions
     * @param source
     * @param amount
     * @deprecated Damage is measured in floats now, use
     * {@link #applyDamage(DamageSource, float)} instead.
     */
    @Deprecated
    public void applayDamage(DamageSource source, int amount) {
        getEntity().d(source.getDamageSource(), amount);
    }

    /**
     * Damages this entity, taking into account armor/enchantments/potions
     * @param source
     * @param amount
     */
    // TODO: pull up
    public void applyDamage(DamageSource source, float amount) {
        getEntity().d(source.getDamageSource(), amount);
    }

    /**
     * Returns this entity's custom name.
     * @return This entity's custom name if it has one, an empty string otherwise
     */
    public String getCustomName() {
        return getEntity().bw();
    }

    /**
     * Sets this entity's custom name.
     * @param name This entity's new custom name
     */
    public void setCustomName(String name) {
        getEntity().a(name);
    }

    /**
     * Returns whether this entity has a custom name.
     * @return <tt>true</tt> if this entity has a custom name, <tt>false</tt>
     * otherwise.
     */
    public boolean hasCustomName() {
        return getEntity().bx();
    }

    /**
     * Returns whether a possible custom name is shown on this entity.
     * @return <tt>true</tt> if a possible custom name is shown, <tt>false</tt>
     * otherwise.
     */
    public boolean isCustomNameVisible() {
        return getEntity().by();
    }

    /**
     * Sets whether a possible custom name is shown on this entity.
     * @param visible <tt>true</tt> to show a possible custom name,
     * <tt>false</tt> to hide it.
     */
    public void setCustomNameVisible(boolean visible) {
        getEntity().g(visible);
    }
}
