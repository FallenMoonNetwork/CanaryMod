import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class OEntityList {

    private static Map b = new HashMap();
    private static Map c = new HashMap();
    private static Map d = new HashMap();
    private static Map e = new HashMap();
    private static Map f = new HashMap();
    public static HashMap a = new LinkedHashMap();

    private static void a(Class oclass, String s, int i) {
        b.put(s, oclass);
        c.put(oclass, s);
        d.put(Integer.valueOf(i), oclass);
        e.put(oclass, Integer.valueOf(i));
        f.put(s, Integer.valueOf(i));
    }

    private static void a(Class oclass, String s, int i, int j, int k) {
        a(oclass, s, i);
        a.put(Integer.valueOf(i), new OEntityEggInfo(i, j, k));
    }

    public static OEntity a(String s, OWorld oworld) {
        OEntity oentity = null;

        try {
            Class oclass = (Class) b.get(s);

            if (oclass != null) {
                oentity = (OEntity) oclass.getConstructor(new Class[] { OWorld.class}).newInstance(new Object[] { oworld});
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return oentity;
    }

    public static OEntity a(ONBTTagCompound onbttagcompound, OWorld oworld) {
        OEntity oentity = null;

        if ("Minecart".equals(onbttagcompound.i("id"))) {
            switch (onbttagcompound.e("Type")) {
                case 0:
                    onbttagcompound.a("id", "MinecartRideable");
                    break;

                case 1:
                    onbttagcompound.a("id", "MinecartChest");
                    break;

                case 2:
                    onbttagcompound.a("id", "MinecartFurnace");
            }

            onbttagcompound.o("Type");
        }

        try {
            Class oclass = (Class) b.get(onbttagcompound.i("id"));

            if (oclass != null) {
                oentity = (OEntity) oclass.getConstructor(new Class[] { OWorld.class}).newInstance(new Object[] { oworld});
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (oentity != null) {
            oentity.f(onbttagcompound);
        } else {
            oworld.W().b("Skipping Entity with id " + onbttagcompound.i("id"));
        }

        return oentity;
    }

    public static OEntity a(int i, OWorld oworld) {
        OEntity oentity = null;

        try {
            Class oclass = a(i);

            if (oclass != null) {
                oentity = (OEntity) oclass.getConstructor(new Class[] { OWorld.class}).newInstance(new Object[] { oworld});
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        if (oentity == null) {
            oworld.W().b("Skipping Entity with id " + i);
        }

        return oentity;
    }

    public static int a(OEntity oentity) {
        Class oclass = oentity.getClass();

        return e.containsKey(oclass) ? ((Integer) e.get(oclass)).intValue() : 0;
    }

    public static Class a(int i) {
        return (Class) d.get(Integer.valueOf(i));
    }

    public static String b(OEntity oentity) {
        return (String) c.get(oentity.getClass());
    }

    public static String b(int i) {
        Class oclass = a(i);

        return oclass != null ? (String) c.get(oclass) : null;
    }

    // CanaryMod: Let us do a name->class lookup for mob spawning
    public static Class<?> getEntity(String s) {
        return (Class<?>) b.get(s);
    }

    // CanaryMod: Let us do a class->name lookup for mob spawning
    public static String getName(Class<? extends OEntity> clazz) {
        return (String) c.get(clazz);
    }

    static {
        a(OEntityItem.class, "Item", 1);
        a(OEntityXPOrb.class, "XPOrb", 2);
        a(OEntityPainting.class, "Painting", 9);
        a(OEntityArrow.class, "Arrow", 10);
        a(OEntitySnowball.class, "Snowball", 11);
        a(OEntityLargeFireball.class, "Fireball", 12);
        a(OEntitySmallFireball.class, "SmallFireball", 13);
        a(OEntityEnderPearl.class, "ThrownEnderpearl", 14);
        a(OEntityEnderEye.class, "EyeOfEnderSignal", 15);
        a(OEntityPotion.class, "ThrownPotion", 16);
        a(OEntityExpBottle.class, "ThrownExpBottle", 17);
        a(OEntityItemFrame.class, "ItemFrame", 18);
        a(OEntityWitherSkull.class, "WitherSkull", 19);
        a(OEntityTNTPrimed.class, "PrimedTnt", 20);
        a(OEntityFallingSand.class, "FallingSand", 21);
        a(OEntityFireworkRocket.class, "FireworksRocketEntity", 22);
        a(OEntityBoat.class, "Boat", 41);
        a(OEntityMinecartEmpty.class, "MinecartRideable", 42);
        a(OEntityMinecartChest.class, "MinecartChest", 43);
        a(OEntityMinecartFurnace.class, "MinecartFurnace", 44);
        a(OEntityMinecartTNT.class, "MinecartTNT", 45);
        a(OEntityMinecartHopper.class, "MinecartHopper", 46);
        a(OEntityMinecartMobSpawner.class, "MinecartSpawner", 47);
        a(OEntityLiving.class, "Mob", 48);
        a(OEntityMob.class, "Monster", 49);
        a(OEntityCreeper.class, "Creeper", 50, 894731, 0);
        a(OEntitySkeleton.class, "Skeleton", 51, 12698049, 4802889);
        a(OEntitySpider.class, "Spider", 52, 3419431, 11013646);
        a(OEntityGiantZombie.class, "Giant", 53);
        a(OEntityZombie.class, "Zombie", 54, '\uafaf', 7969893);
        a(OEntitySlime.class, "Slime", 55, 5349438, 8306542);
        a(OEntityGhast.class, "Ghast", 56, 16382457, 12369084);
        a(OEntityPigZombie.class, "PigZombie", 57, 15373203, 5009705);
        a(OEntityEnderman.class, "Enderman", 58, 1447446, 0);
        a(OEntityCaveSpider.class, "CaveSpider", 59, 803406, 11013646);
        a(OEntitySilverfish.class, "Silverfish", 60, 7237230, 3158064);
        a(OEntityBlaze.class, "Blaze", 61, 16167425, 16775294);
        a(OEntityMagmaCube.class, "LavaSlime", 62, 3407872, 16579584);
        a(OEntityDragon.class, "EnderDragon", 63);
        a(OEntityWither.class, "WitherBoss", 64);
        a(OEntityBat.class, "Bat", 65, 4996656, 986895);
        a(OEntityWitch.class, "Witch", 66, 3407872, 5349438);
        a(OEntityPig.class, "Pig", 90, 15771042, 14377823);
        a(OEntitySheep.class, "Sheep", 91, 15198183, 16758197);
        a(OEntityCow.class, "Cow", 92, 4470310, 10592673);
        a(OEntityChicken.class, "Chicken", 93, 10592673, 16711680);
        a(OEntitySquid.class, "Squid", 94, 2243405, 7375001);
        a(OEntityWolf.class, "Wolf", 95, 14144467, 13545366);
        a(OEntityMooshroom.class, "MushroomCow", 96, 10489616, 12040119);
        a(OEntitySnowman.class, "SnowMan", 97);
        a(OEntityOcelot.class, "Ozelot", 98, 15720061, 5653556);
        a(OEntityIronGolem.class, "VillagerGolem", 99);
        a(OEntityVillager.class, "Villager", 120, 5651507, 12422002);
        a(OEntityEnderCrystal.class, "EnderCrystal", 200);
    }
}
