public class HumanEntity extends LivingEntity {

    /**
     * Constructor
     */
    public HumanEntity() {}

    /**
     * Constructor
     *
     * @param human
     */
    public HumanEntity(OEntityPlayer human) {
        super(human);
    }

    /**
     * Returns the entity we're wrapping.
     *
     * @return
     */
    @Override
    public OEntityPlayer getEntity() {
        return (OEntityPlayer) entity;
    }

    /**
     * Returns the name
     *
     * @return
     */
    @Override
    public String getName() {
        return getEntity().bS;
    }

    /**
     * Returns the name displayed above this player's head.
     *
     * @return String
     */
    public String getDisplayName() {
        return getEntity().getDisplayName();
    }

    /**
     * Sets the name displayed above this player's head.
     *
     * @param name The name displayed. Any non-color modification will affect skin.
     */
    public void setDisplayName(String name) {
        getEntity().setDisplayName(name);
    }

    /**
     * Returns whether this player can receive damage.
     * @return the disableDamage state
     */
    public boolean isDamageDisabled() {
        return getEntity().ce.a;
    }

    /**
     * Sets whether this player can receive damage.
     * @param disabled the new value.
     * @see #updateCapabilities()
     */
    public void setDamageDisabled(boolean disabled) {
        getEntity().ce.a = disabled;
    }

    /**
     * Returns whether the player is flying.
     * @return the flying state
     */
    public boolean isFlying() {
        return getEntity().ce.b;
    }

    /**
     * Sets whether the player is flying.
     * @param flying the flying state.
     * @see #updateCapabilities()
     */
    public void setFlying(boolean flying) {
        getEntity().ce.b = flying;
    }

    /**
     * Returns whether this player is allowed to fly.
     * @return the canFly state
     */
    public boolean canFly() {
        return getEntity().ce.c;
    }

    /**
     * Sets whether this player is allowed to fly.
     * @param allow <tt>true</tt> to allow flying, <tt>false</tt> to deny it.
     * @see #updateCapabilities()
     */
    public void setCanFly(boolean allow) {
        getEntity().ce.c = allow;
    }

    /**
     * Returns whether the player has a creative perks.
     * When set, enables stuff like items not depleting on use, buckets not
     * emptying, anvils and enchantments always working, etc.
     * @return whether player has a creative inventory.
     */
    public boolean hasCreativePerks() {
        return getEntity().ce.d;
    }

    /**
     * Sets whether the player has a creative inventory.
     * When set, enables stuff like items not depleting on use, buckets not
     * emptying, anvils and enchantments always working, etc.
     * @param creativePerks the new state
     * @see #updateCapabilities()
     */
    public void setCreativePerks(boolean creativePerks) {
        getEntity().ce.d = creativePerks;
    }

    /**
     * Returns whether the player has build restrictions like in Adventure.
     * @return whether the player has build restrictions.
     */
    public boolean hasAdventureRestrictions() {
        return !getEntity().ce.e;
    }

    /**
     * Sets whether the player has build restrictions like in Adventure.
     * @param restrict The new value for the flag.
     * @see #updateCapabilities()
     */
    public void setAdventureRestrictions(boolean restrict) {
        getEntity().ce.e = !restrict;
    }

    /**
     * Returns the current flying speed of the player.
     * Default seems to be <tt>0.05</tt>.
     * @return The current flying speed
     */
    public float getFlyingSpeed() {
        return getEntity().ce.a();
    }

    /**
     * Returns the current walking speed of the player.
     * Default seems to be <tt>0.1</tt>.
     * @return The current walking speed
     */
    public float getWalkingSpeed() {
        return getEntity().ce.b();
    }

    /**
     * Updates the human's capabilities to the client.
     * The client won't be affected unless you call this.
     */
    public void updateCapabilities() {
        getEntity().n();
    }

    /**
     * Whether or not the player is in a bed.
     * @return Sleeping or not.
     */
    public boolean isSleeping() {
        return getEntity().ca;
    }
}
