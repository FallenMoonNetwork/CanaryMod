public enum DamageType {

    /**
     * Creeper explosion
     */
    // SRG CREEPER_EXPLOSION(new OEntityDamageSource("explosion.player", new OEntityCreeper(null)).func_76351_m().func_94540_d().damageSource), //
    CREEPER_EXPLOSION(new OEntityDamageSource("explosion.player", new OEntityCreeper(null)).o().d().damageSource), //
    /**
     * Damage dealt by another entity
     */
    // SRG ENTITY(ODamageSource.func_76358_a((OEntityLivingBase) null).damageSource), //
    ENTITY(ODamageSource.a((OEntityLivingBase) null).damageSource), //
    /**
     * Damage caused by explosion
     */
    // SRG EXPLOSION((new ODamageSource("explosion")).func_76351_m().func_94540_d().damageSource), //
    EXPLOSION((new ODamageSource("explosion")).o().d().damageSource), //
    /**
     * Damage caused from falling (fall distance - 3.0)
     */
    // SRG FALL(ODamageSource.field_76379_h.damageSource), //
    FALL(ODamageSource.h.damageSource), //
    /**
     * Damage caused by fire (1)
     */
    // SRG FIRE(ODamageSource.field_76372_a.damageSource), //
    FIRE(ODamageSource.a.damageSource), //
    /**
     * Low periodic damage caused by burning (1)
     */
    // SRG FIRE_TICK(ODamageSource.field_76370_b.damageSource), //
    FIRE_TICK(ODamageSource.b.damageSource), //
    /**
     * Damage caused from lava (4)
     */
    // SRG LAVA(ODamageSource.field_76371_c.damageSource), //
    LAVA(ODamageSource.c.damageSource), //
    /**
     * Damage caused from drowning (2)
     */
    // SRG WATER(ODamageSource.field_76369_e.damageSource), //
    WATER(ODamageSource.e.damageSource), //
    /**
     * Damage caused by cactus (1)
     */
    // SRG CACTUS(ODamageSource.field_76367_g.damageSource), //
    CACTUS(ODamageSource.g.damageSource), //
    /**
     * Damage caused by suffocating(1)
     */
    // SRG SUFFOCATION(ODamageSource.field_76368_d.damageSource), //
    SUFFOCATION(ODamageSource.d.damageSource), //
    /**
     * Damage caused by lightning (5)
     */
    // SRG LIGHTNING(ODamageSource.field_76372_a.damageSource), //
    LIGHTNING(ODamageSource.a.damageSource), //
    /**
     * Damage caused by starvation (1)
     */
    // SRG STARVATION(ODamageSource.field_76366_f.damageSource), //
    STARVATION(ODamageSource.f.damageSource), //
    /**
     * Damage caused by poison (1) (Potions, Poison)
     */
    // SRG POTION(ODamageSource.field_76376_m.damageSource), //
    POTION(ODamageSource.k.damageSource), //
    /**
     * Damage caused by the "Wither" effect (1)
     */
    // SRG WITHER(ODamageSource.field_82727_n.damageSource), //
    WITHER(ODamageSource.l.damageSource), //
    /**
     * Damage caused by throwing an enderpearl (5)
     */
    // SRG ENDERPEARL(ODamageSource.field_76379_h.damageSource), //
    ENDERPEARL(ODamageSource.h.damageSource), //
    /**
     * Damage caused by falling anvil
     */
    // SRG ANVIL(ODamageSource.field_82728_o.damageSource), //
    ANVIL(ODamageSource.m.damageSource), //
    /**
     * Damage caused by falling block
     */
    // SRG FALLING_BLOCK(ODamageSource.field_82729_p.damageSource), //
    FALLING_BLOCK(ODamageSource.n.damageSource), //
    /**
     * Generic damage cause
     */
    // SRG GENERIC(ODamageSource.field_76377_j.damageSource);
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
