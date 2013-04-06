/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Somners
 */
public enum Particle {

    LARGE_SMOKE("largesmoke"),
    PORTAL("portal"),
    RED_DUST("reddust"),
    LARGE_EXPLODE("largeexplode"),
    EXPLODE("explode"),
    LARGE_EXPLOSION("largeexplosion"),
    NOTE("note"),
    BUBBLE("bubble"),
    FLAME("flame"),
    CRIT("crit"),
    SMOKE("smoke"),
    HEART("heart"),
    SPLASH("splash"),
    SNOWBALLPOOF("snowballpoof"),
    SUSPENDED("suspended"),
    DEPTH_SUSPEND("depthsuspend"),
    TOWN_AURA("townaura"),
    MAGIC_CRIT("magicCrit"),
    MOB_SPELL("mobSpell"),
    SPELL("spell"),
    INSTANT_SPELL("instantSpell"),
    ENCHANTMENT_TABLE("enchantmenttable"),
    LAVA("lava"),
    FOOTSTEP("footstep"),
    CLOUD("cloud"),
    DRIP_LAVA("dripLava"),
    DRIP_WATER("dripWater"),
    SNOW_SHOVEL("snowshovel"),
    SLIME("slime"),
    ITEM_BREAK("iconcrack_256"),
    BLOCK_BREAK("tilecrack_1");

    private String particle;

    Particle(String particle){
        this.particle = particle;
    }

    public String getParticleString(){
        return particle;
    }

}
