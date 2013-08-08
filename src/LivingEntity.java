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
     * Drops this mob's loot. Automatically called if health is set to 0.
     */
    public void dropLoot() {
        // SRG getEntity().func_82160_b(true, 0);
        getEntity().a(true, 0);
    }

    /**
     * Sets the item held by the entity.
     *
     * @param item
     */
    public void setItemInHand(Item item) {
        // SRG getEntity().func_70062_b(0, item.getBaseItem());
        getEntity().c(0, item.getBaseItem());
    }

    /**
     * Gets the item held by the entity.
     *
     * @return
     */
    public Item getItemStackInHand() {
        // SRG OItemStack stack = getEntity().func_70694_bm();
        OItemStack stack = getEntity().aY();
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
            // SRG getEntity().func_70062_b(slot + 1, armor.getBaseItem());
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
        // SRG OItemStack stack = getEntity().func_130225_q(slot);
        OItemStack stack = getEntity().o(slot);
        return stack == null ? null : new Item(stack);
    }

    /**
     * Returns whether or not this entity will despawn naturally.
     *
     * @return
     */
    public boolean isPersistent() {
        // SRG return getEntity().field_82179_bU;
        return getEntity().bt;
    }

    /**
     * Sets whether or not this entity will despawn naturally.
     *
     * @param isPersistent
     */
    public void setPersistent(boolean isPersistent) {
        // SRG getEntity().field_82179_bU = isPersistent;
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
            // SRG return getEntity().field_82174_bp[slot];
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
            // SRG getEntity().field_82174_bp[slot] = chance;
            getEntity().e[slot] = chance;
        }
    }

    /**
     * Whether or not this entity can pick up items.
     *
     * @return
     */
    public boolean canPickUpLoot() {
        // SRG return getEntity().func_98052_bS();
        return getEntity().bD();
    }

    /**
     * Sets whether or not this entity can pick up items.
     *
     * @param flag
     */
    public void setCanPickUpLoot(boolean flag) {
        // SRG getEntity().func_98053_h(flag);
        getEntity().h(flag);
    }

    /**
     * Returns this entity's custom name.
     * @return This entity's custom name if it has one, an empty string otherwise
     */
    public String getCustomName() {
        // SRG return getEntity().func_94057_bL();
        return getEntity().bA();
    }

    /**
     * Sets this entity's custom name.
     * @param name This entity's new custom name
     */
    public void setCustomName(String name) {
        // SRG getEntity().func_94058_c(name);
        getEntity().a(name);
    }

    /**
     * Returns whether this entity has a custom name.
     * @return <tt>true</tt> if this entity has a custom name, <tt>false</tt>
     * otherwise.
     */
    public boolean hasCustomName() {
        // SRG return getEntity().func_94056_bM();
        return getEntity().bB();
    }

    /**
     * Returns whether a possible custom name is shown on this entity.
     * @return <tt>true</tt> if a possible custom name is shown, <tt>false</tt>
     * otherwise.
     */
    public boolean isCustomNameVisible() {
        // SRG return getEntity().func_94062_bN();
        return getEntity().bC();
    }

    /**
     * Sets whether a possible custom name is shown on this entity.
     * @param visible <tt>true</tt> to show a possible custom name,
     * <tt>false</tt> to hide it.
     */
    public void setCustomNameVisible(boolean visible) {
        // SRG getEntity().func_94061_f(visible);
        getEntity().g(visible);
    }
}
