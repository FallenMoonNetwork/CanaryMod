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
        return entity == null ? null : (OEntityPlayer) entity;
    }

    /**
     * Returns the name
     *
     * @return
     */
    @Override
    public String getName() {
        OEntityPlayer oep = getEntity();
        return oep == null ? null : oep.bS;
    }

    /**
     * Returns the name displayed above this player's head.
     *
     * @return String
     */
    public String getDisplayName() {
        OEntityPlayer oep = getEntity();
        return oep == null ? null : oep.getDisplayName();
    }

    /**
     * Sets the name displayed above this player's head.
     *
     * @param name The name displayed. Any non-color modification will affect skin.
     */
    public void setDisplayName(String name) {
        if (getEntity() != null) {
            getEntity().setDisplayName(name);
        }
    }

    /**
     * Returns whether this player can receive damage.
     * @return the disableDamage state
     */
    public boolean isDamageDisabled() {
        OEntityPlayer oep = getEntity();
        return oep == null ? null : oep.ce.a;
    }

    /**
     * Sets whether this player can receive damage.
     * @param disabled the new value.
     * @see #updateCapabilities()
     */
    public void setDamageDisabled(boolean disabled) {
        if (getEntity() != null) {
            getEntity().ce.a = disabled;
        }
    }

    /**
     * Returns whether the player is flying.
     * @return the flying state
     */
    public boolean isFlying() {
        OEntityPlayer oep = getEntity();
        return oep == null ? null : oep.ce.b;
    }

    /**
     * Sets whether the player is flying.
     * @param flying the flying state.
     * @see #updateCapabilities()
     */
    public void setFlying(boolean flying) {
        if (getEntity() != null) {
            getEntity().ce.b = flying;
        }
    }

    /**
     * Returns whether this player is allowed to fly.
     * @return the canFly state
     */
    public boolean canFly() {
        OEntityPlayer oep = getEntity();
        return oep == null ? null : oep.ce.c;
    }

    /**
     * Sets whether this player is allowed to fly.
     * @param allow <tt>true</tt> to allow flying, <tt>false</tt> to deny it.
     * @see #updateCapabilities()
     */
    public void setCanFly(boolean allow) {
        if (getEntity() != null) {
            getEntity().ce.c = allow;
        }
    }

    /**
     * Returns whether the player has a creative perks.
     * When set, enables stuff like items not depleting on use, buckets not
     * emptying, anvils and enchantments always working, etc.
     * @return whether player has a creative inventory.
     */
    public boolean hasCreativePerks() {
        OEntityPlayer oep = getEntity();
        return oep == null ? null : oep.ce.d;
    }

    /**
     * Sets whether the player has a creative inventory.
     * When set, enables stuff like items not depleting on use, buckets not
     * emptying, anvils and enchantments always working, etc.
     * @param creativePerks the new state
     * @see #updateCapabilities()
     */
    public void setCreativePerks(boolean creativePerks) {
        if (getEntity() != null) {
            getEntity().ce.d = creativePerks;
        }
    }

    /**
     * Returns whether the player has build restrictions like in Adventure.
     * @return whether the player has build restrictions.
     */
    public boolean hasAdventureRestrictions() {
        OEntityPlayer oep = getEntity();
        return oep == null ? null : !oep.ce.e;
    }

    /**
     * Sets whether the player has build restrictions like in Adventure.
     * @param restrict The new value for the flag.
     * @see #updateCapabilities()
     */
    public void setAdventureRestrictions(boolean restrict) {
        if (getEntity() != null) {
            getEntity().ce.e = !restrict;
        }
    }

    /**
     * Returns the current flying speed of the player.
     * Default seems to be <tt>0.05</tt>.
     * @return The current flying speed
     */
    public float getFlyingSpeed() {
        OEntityPlayer oep = getEntity();
        return oep == null ? null : oep.ce.a();
    }

    /**
     * Returns the current walking speed of the player.
     * Default seems to be <tt>0.1</tt>.
     * @return The current walking speed
     */
    public float getWalkingSpeed() {
        OEntityPlayer oep = getEntity();
        return oep == null ? null : oep.ce.b();
    }

    /**
     * Updates the human's capabilities to the client.
     * The client won't be affected unless you call this.
     */
    public void updateCapabilities() {
        if (getEntity() != null) {
            getEntity().n();
        }
    }

    /**
     * Whether or not the player is in a bed.
     * @return Sleeping or not.
     */
    public boolean isSleeping() {
        OEntityPlayer oep = getEntity();
        return oep == null ? null : oep.ca;
    }
}
