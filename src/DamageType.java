

public enum DamageType {

    /**
     * Creeper explosion
     * TODO: Fix this, creeper aint magic
     */
    CREEPER_EXPLOSION(new OEntityDamageSource("explosion.player", new OEntityCreeper(null)).o().d().getDamageSource()), //
    /**
     * Damage dealt by another entity
     */
    ENTITY(ODamageSource.a((OEntityLiving) null).getDamageSource()), //
    /**
     * Damage caused by explosion
     */
    EXPLOSION((new ODamageSource("explosion")).o().d().getDamageSource()), //
    /**
     * Damage caused from falling (fall distance - 3.0)
     */
    FALL(ODamageSource.h.getDamageSource()), //
    /**
     * Damage caused by fire (1)
     */
     FIRE(ODamageSource.a.getDamageSource()), //
    /**
     * Low periodic damage caused by burning (1)
     */
     FIRE_TICK(ODamageSource.b.getDamageSource()), //
    /**
     * Damage caused from lava (4)
     */
     LAVA(ODamageSource.c.getDamageSource()), //
    /**
     * Damage caused from drowning (2)
     */
     WATER(ODamageSource.e.getDamageSource()), //
    /**
     * Damage caused by cactus (1)
     */
     CACTUS(ODamageSource.g.getDamageSource()), //
    /**
     * Damage caused by suffocating(1)
     */
     SUFFOCATION(ODamageSource.d.getDamageSource()), //
    /**
     * Damage caused by lightning (5)
     */
     LIGHTNING(ODamageSource.a.getDamageSource()), //
    /**
     * Damage caused by starvation (1)
     */
     STARVATION(ODamageSource.f.getDamageSource()), //
    /**
     * Damage caused by poison (1) (Potions, Poison)
     */
     POTION(ODamageSource.k.getDamageSource()), //
     /**
      * Damage caused by the "Wither" effect (1)
      */
     WITHER(ODamageSource.l.getDamageSource()), //
     /**
      * Damage caused by throwing an enderpearl (5)
      * TODO: Is that fall damage per definition? Magic damage would suit good here
      */
     ENDERPEARL(ODamageSource.h.getDamageSource()), //
     /**
      * Damage caused by falling anvil
      */
     ANVIL(ODamageSource.m.getDamageSource()), //
     /**
      * Damage caused by falling block
      */
     FALLING_BLOCK(ODamageSource.n.getDamageSource()),
     /**
      * Generic damage cause
      */
     GENERIC(ODamageSource.j.getDamageSource());

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
     * Get DamageType from a given DamageSource
     * @param source
     * @return
     */
     public static DamageType fromDamageSource(DamageSource source) {
         for(DamageType t : DamageType.values()) {
             if(t.getDamageSource().getName().equals(source.getName())) {
                 return t;
             }
         }
         if(source.isEntityDamageSource()) {
             return ENTITY;
         }
         return GENERIC;
     }
}