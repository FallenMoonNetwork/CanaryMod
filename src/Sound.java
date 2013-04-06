/**
 *
 * @author Somners
 */
public enum Sound {

    CLICK("random.click"),
    FIZZ("random.fizz"),
    NOTE("note"),
    PISTON_OUT("tile.piston.out"),
    PISTON_IN("tile.piston.in"),
    BOW_HIT("random.bowhit"),
    ENDERMAN_PORTAL("mob.endermen.portal"),
    FIRE("fire.fire"),
    THUNDER("ambient.weather.thunder"),
    EXPLODE("random.explode"),
    FIRE_IGNITE("fire.ignite"),
    CHEST_OPEN("random.chestopen"),
    CHEST_CLOSE("random.chestclosed"),
    AMBIENT_CAVE("ambient.cave.cave"),
    FUSE("random.fuse"),
    ENDERMAN_STARE("mob.enderman.stare"),
    BOW("random.bow"),
    ENDERDRAGON_WINGS("mob.enderdragon.wings"),
    COW_SAY("mob.cow.say"),
    COW_HURT("mob.cow.hurt"),
    COW_STEP("mob.cow.step"),
    BAT_IDLE("mob.bat.idle"),
    BAT_HURT("mob.bat.hurt"),
    BAT_DEATH("mob.bat.death"),
    BLAZE_BREATHE("mob.blaze.breathe"),
    BLAZE_HIT("mob.blaze.hit"),
    BLAZE_DEATH("mob.blaze.death"),
    CHICKEN_SAY("mob.chicken.say"),
    CHICKEN_HURT("mob.chicken.hurt"),
    CHICKEN_STEP("mob.chicken.step"),
    ENDERDRAGON_GROWL("mob.enderdragon.growl"),
    ENDERDRAGON_HIT("mob.enderdragon.hit"),
    ENDERMAN_HIT("mob.endermen.hit"),
    ENDERMAN_DEATH("mob.endermen.death"),
    ENDERMAN_SCREAM("mob.endermen.scream"),
    ENDERMAN_IDLE("mob.endermen.idle"),
    GHAST_MOAN("mob.ghast.moan"),
    GHAST_SCREAM("mob.ghast.scream"),
    GHAST_DEATH("mob.ghast.death"),
    IRONGOLEM_HIT("mob.irongolem.hit"),
    IRONGOLEM_DEATH("mob.irongolem.death"),
    CAT_HIT("mob.cat.hitt"),
    CAT_PURR("mob.cat.purr"),
    CAT_PURREOW("mob.cat.purreow"),
    CAT_MEOW("mob.cat.meow"),
    PIG_SAY("mob.pig.say"),
    PIG_DEATH("mob.pig.death"),
    ZOMBIEPIG_SAY("mob.zombiepig.zpig"),
    ZOMBIEPIG_HURT("mob.zombiepig.zpighurt"),
    ZOMBIEPIG_DEATH("mob.zombiepig.zpigdeath"),
    SHEEP_SAY("mob.sheep.say"),
    SHEEP_STEP("mob.sheep.step"),
    VILLAGER_SAY("mob.villager.default"),
    VILLAGER_HURT("mob.villager.defaulthurt"),
    VILLAGER_DEATH("mob.villager.defaultdeath"),
    SILVERFISH_SAY("mob.silverfish.say"),
    SILVERFISH_HIT("mob.silverfish.hit"),
    SILVERFISH_DEATH("mob.silverfish.kill"),
    SKELETON_SAY("mob.skeleton.say"),
    SKELETON_HURT("mob.skeleton.hurt"),
    SKELETON_DEATH("mob.skeleton.death"),
    SKELETON_STEP("mob.skeleton.step"),
    SPIDER_SAY("mob.spider.say"),
    SPIDER_DEATH("mob.spider.death"),
    SPIDER_STEP("mob.spider.step"),
    WITCH_IDLE("mob.witch.idle"),
    WITCH_HURT("mob.witch.hurt"),
    WITCH_DEATH("mob.witch.death"),
    WITHER_IDLE("mob.wither.idle"),
    WITHER_HURT("mob.wither.hurt"),
    WITHER_DEATH("mob.wither.death"),
    ZOMBIE_SAY("mob.zombie.say"),
    ZOMBIE_HURT("mob.zombie.hurt"),
    ZOMBIE_DEATH("mob.zombie.death"),
    WOLF_GROWL("mob.wolf.growl"),
    WOLF_WHINE("mob.wolf.whine"),
    WOLF_PANTING("mob.wolf.panting"),
    WOLF_BARK("mob.wolf.bark"),
    WOLF_DEATH("mob.wolf.death"),
    WOLF_HURT("mob.wolf.hurt");

    String sound;

    Sound(String sound){
        this.sound = sound;
    }

    public String getSoundString(){
        return sound;
    }

    public enum Instrument {
        HARP,
        BASS_DRUM,
        SNARE,
        CLICK,
        BASS_GUITAR;
    }
}
