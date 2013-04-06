public enum DamageType {

    /**
     * Creeper explosion
     */
    CREEPER_EXPLOSION(new OEntityDamageSource("explosion.player", new OEntityCreeper(null)).o().d().damageSource), //
    /**
     * Damage dealt by another entity
     */
    ENTITY(ODamageSource.a((OEntityLiving) null).damageSource), //
    /**
     * Damage caused by explosion
     */
    EXPLOSION((new ODamageSource("explosion")).o().d().damageSource), //
    /**
     * Damage caused from falling (fall distance - 3.0)
     */
    FALL(ODamageSource.h.damageSource), //
    /**
     * Damage caused by fire (1)
     */
    FIRE(ODamageSource.a.damageSource), //
    /**
     * Low periodic damage caused by burning (1)
     */
    FIRE_TICK(ODamageSource.b.damageSource), //
    /**
     * Damage caused from lava (4)
     */
    LAVA(ODamageSource.c.damageSource), //
    /**
     * Damage caused from drowning (2)
     */
    WATER(ODamageSource.e.damageSource), //
    /**
     * Damage caused by cactus (1)
     */
    CACTUS(ODamageSource.g.damageSource), //
    /**
     * Damage caused by suffocating(1)
     */
    SUFFOCATION(ODamageSource.d.damageSource), //
    /**
     * Damage caused by lightning (5)
     */
    LIGHTNING(ODamageSource.a.damageSource), //
    /**
     * Damage caused by starvation (1)
     */
    STARVATION(ODamageSource.f.damageSource), //
    /**
     * Damage caused by poison (1) (Potions, Poison)
     */
    POTION(ODamageSource.k.damageSource), //
    /**
     * Damage caused by the "Wither" effect (1)
     */
    WITHER(ODamageSource.l.damageSource), //
    /**
     * Damage caused by throwing an enderpearl (5)
     */
    ENDERPEARL(ODamageSource.h.damageSource), //
    /**
     * Damage caused by falling anvil
     */
    ANVIL(ODamageSource.m.damageSource), //
    /**
     * Damage caused by falling block
     */
    FALLING_BLOCK(ODamageSource.n.damageSource),
    /**
     * Generic damage cause
     */
    GENERIC(ODamageSource.j.damageSource);

    private final DamageSource source;

    private DamageType(DamageSource source) {
        this.source = source;
    }

    public DamageSource getDamageSource() {
        return this.source;
    }

    /**
    * Returns the message that would be displayed in chat if a player died from this.
    *
    * @param died The player who 'died'.
    * @return The death message.
    */
    public String getDeathMessage(Player died) {
        return source.getDeathMessage(died);
    }

    /**
     * Get <tt>DamageType</tt> from a given {@link DamageSource}.
     * @param source The {@link DamageSource} to get the <tt>DamageType</tt> for.
     * @return The <tt>DamageType</tt> corresponding to <tt>source</tt>
     */
     public static DamageType fromDamageSource(DamageSource source) {
         for (DamageType t : DamageType.values()) {
             if (t.getDamageSource().getName().equals(source.getName())) {
                 return t;
             }
         }

         if (source.isEntityDamageSource()) {
             return ENTITY;
         }

         return GENERIC;
     }
}
