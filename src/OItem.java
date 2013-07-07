import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.Random;
import java.util.UUID;

public class OItem {

    protected static final UUID e = UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CF");
    private OCreativeTabs a;
    protected static Random f = new Random();
    public static OItem[] g = new OItem[32000];
    public static OItem h = (new OItemSpade(0, OEnumToolMaterial.c)).b("shovelIron").d("iron_shovel");
    public static OItem i = (new OItemPickaxe(1, OEnumToolMaterial.c)).b("pickaxeIron").d("iron_pickaxe");
    public static OItem j = (new OItemAxe(2, OEnumToolMaterial.c)).b("hatchetIron").d("iron_axe");
    public static OItem k = (new OItemFlintAndSteel(3)).b("flintAndSteel").d("flint_and_steel");
    public static OItem l = (new OItemFood(4, 4, 0.3F, false)).b("apple").d("apple");
    public static OItemBow m = (OItemBow) (new OItemBow(5)).b("bow").d("bow");
    public static OItem n = (new OItem(6)).b("arrow").a(OCreativeTabs.j).d("arrow");
    public static OItem o = (new OItemCoal(7)).b("coal").d("coal");
    public static OItem p = (new OItem(8)).b("diamond").a(OCreativeTabs.l).d("diamond");
    public static OItem q = (new OItem(9)).b("ingotIron").a(OCreativeTabs.l).d("iron_ingot");
    public static OItem r = (new OItem(10)).b("ingotGold").a(OCreativeTabs.l).d("gold_ingot");
    public static OItem s = (new OItemSword(11, OEnumToolMaterial.c)).b("swordIron").d("iron_sword");
    public static OItem t = (new OItemSword(12, OEnumToolMaterial.a)).b("swordWood").d("wood_sword");
    public static OItem u = (new OItemSpade(13, OEnumToolMaterial.a)).b("shovelWood").d("wood_shovel");
    public static OItem v = (new OItemPickaxe(14, OEnumToolMaterial.a)).b("pickaxeWood").d("wood_pickaxe");
    public static OItem w = (new OItemAxe(15, OEnumToolMaterial.a)).b("hatchetWood").d("wood_axe");
    public static OItem x = (new OItemSword(16, OEnumToolMaterial.b)).b("swordStone").d("stone_sword");
    public static OItem y = (new OItemSpade(17, OEnumToolMaterial.b)).b("shovelStone").d("stone_shovel");
    public static OItem z = (new OItemPickaxe(18, OEnumToolMaterial.b)).b("pickaxeStone").d("stone_pickaxe");
    public static OItem A = (new OItemAxe(19, OEnumToolMaterial.b)).b("hatchetStone").d("stone_axe");
    public static OItem B = (new OItemSword(20, OEnumToolMaterial.d)).b("swordDiamond").d("diamond_sword");
    public static OItem C = (new OItemSpade(21, OEnumToolMaterial.d)).b("shovelDiamond").d("diamond_shovel");
    public static OItem D = (new OItemPickaxe(22, OEnumToolMaterial.d)).b("pickaxeDiamond").d("diamond_pickaxe");
    public static OItem E = (new OItemAxe(23, OEnumToolMaterial.d)).b("hatchetDiamond").d("diamond_axe");
    public static OItem F = (new OItem(24)).q().b("stick").a(OCreativeTabs.l).d("stick");
    public static OItem G = (new OItem(25)).b("bowl").a(OCreativeTabs.l).d("bowl");
    public static OItem H = (new OItemSoup(26, 6)).b("mushroomStew").d("mushroom_stew");
    public static OItem I = (new OItemSword(27, OEnumToolMaterial.e)).b("swordGold").d("gold_sword");
    public static OItem J = (new OItemSpade(28, OEnumToolMaterial.e)).b("shovelGold").d("gold_shovel");
    public static OItem K = (new OItemPickaxe(29, OEnumToolMaterial.e)).b("pickaxeGold").d("gold_pickaxe");
    public static OItem L = (new OItemAxe(30, OEnumToolMaterial.e)).b("hatchetGold").d("gold_axe");
    public static OItem M = (new OItemReed(31, OBlock.bZ)).b("string").a(OCreativeTabs.l).d("string");
    public static OItem N = (new OItem(32)).b("feather").a(OCreativeTabs.l).d("feather");
    public static OItem O = (new OItem(33)).b("sulphur").c(OPotionHelper.k).a(OCreativeTabs.l).d("gunpowder");
    public static OItem P = (new OItemHoe(34, OEnumToolMaterial.a)).b("hoeWood").d("wood_hoe");
    public static OItem Q = (new OItemHoe(35, OEnumToolMaterial.b)).b("hoeStone").d("stone_hoe");
    public static OItem R = (new OItemHoe(36, OEnumToolMaterial.c)).b("hoeIron").d("iron_hoe");
    public static OItem S = (new OItemHoe(37, OEnumToolMaterial.d)).b("hoeDiamond").d("diamond_hoe");
    public static OItem T = (new OItemHoe(38, OEnumToolMaterial.e)).b("hoeGold").d("gold_hoe");
    public static OItem U = (new OItemSeeds(39, OBlock.aE.cF, OBlock.aF.cF)).b("seeds").d("seeds_wheat");
    public static OItem V = (new OItem(40)).b("wheat").a(OCreativeTabs.l).d("wheat");
    public static OItem W = (new OItemFood(41, 5, 0.6F, false)).b("bread").d("bread");
    public static OItemArmor X = (OItemArmor) (new OItemArmor(42, OEnumArmorMaterial.a, 0, 0)).b("helmetCloth").d("leather_helmet");
    public static OItemArmor Y = (OItemArmor) (new OItemArmor(43, OEnumArmorMaterial.a, 0, 1)).b("chestplateCloth").d("leather_chestplate");
    public static OItemArmor Z = (OItemArmor) (new OItemArmor(44, OEnumArmorMaterial.a, 0, 2)).b("leggingsCloth").d("leather_leggings");
    public static OItemArmor aa = (OItemArmor) (new OItemArmor(45, OEnumArmorMaterial.a, 0, 3)).b("bootsCloth").d("leather_boots");
    public static OItemArmor ab = (OItemArmor) (new OItemArmor(46, OEnumArmorMaterial.b, 1, 0)).b("helmetChain").d("chainmail_helmet");
    public static OItemArmor ac = (OItemArmor) (new OItemArmor(47, OEnumArmorMaterial.b, 1, 1)).b("chestplateChain").d("chainmail_chestplate");
    public static OItemArmor ad = (OItemArmor) (new OItemArmor(48, OEnumArmorMaterial.b, 1, 2)).b("leggingsChain").d("chainmail_leggings");
    public static OItemArmor ae = (OItemArmor) (new OItemArmor(49, OEnumArmorMaterial.b, 1, 3)).b("bootsChain").d("chainmail_boots");
    public static OItemArmor af = (OItemArmor) (new OItemArmor(50, OEnumArmorMaterial.c, 2, 0)).b("helmetIron").d("iron_helmet");
    public static OItemArmor ag = (OItemArmor) (new OItemArmor(51, OEnumArmorMaterial.c, 2, 1)).b("chestplateIron").d("iron_chestplate");
    public static OItemArmor ah = (OItemArmor) (new OItemArmor(52, OEnumArmorMaterial.c, 2, 2)).b("leggingsIron").d("iron_leggings");
    public static OItemArmor ai = (OItemArmor) (new OItemArmor(53, OEnumArmorMaterial.c, 2, 3)).b("bootsIron").d("iron_boots");
    public static OItemArmor aj = (OItemArmor) (new OItemArmor(54, OEnumArmorMaterial.e, 3, 0)).b("helmetDiamond").d("diamond_helmet");
    public static OItemArmor ak = (OItemArmor) (new OItemArmor(55, OEnumArmorMaterial.e, 3, 1)).b("chestplateDiamond").d("diamond_chestplate");
    public static OItemArmor al = (OItemArmor) (new OItemArmor(56, OEnumArmorMaterial.e, 3, 2)).b("leggingsDiamond").d("diamond_leggings");
    public static OItemArmor am = (OItemArmor) (new OItemArmor(57, OEnumArmorMaterial.e, 3, 3)).b("bootsDiamond").d("diamond_boots");
    public static OItemArmor an = (OItemArmor) (new OItemArmor(58, OEnumArmorMaterial.d, 4, 0)).b("helmetGold").d("gold_helmet");
    public static OItemArmor ao = (OItemArmor) (new OItemArmor(59, OEnumArmorMaterial.d, 4, 1)).b("chestplateGold").d("gold_chestplate");
    public static OItemArmor ap = (OItemArmor) (new OItemArmor(60, OEnumArmorMaterial.d, 4, 2)).b("leggingsGold").d("gold_leggings");
    public static OItemArmor aq = (OItemArmor) (new OItemArmor(61, OEnumArmorMaterial.d, 4, 3)).b("bootsGold").d("gold_boots");
    public static OItem ar = (new OItem(62)).b("flint").a(OCreativeTabs.l).d("flint");
    public static OItem as = (new OItemFood(63, 3, 0.3F, true)).b("porkchopRaw").d("porkchop_raw");
    public static OItem at = (new OItemFood(64, 8, 0.8F, true)).b("porkchopCooked").d("porkchop_cooked");
    public static OItem au = (new OItemHangingEntity(65, OEntityPainting.class)).b("painting").d("painting");
    public static OItem av = (new OItemAppleGold(66, 4, 1.2F, false)).k().a(OPotion.l.H, 5, 1, 1.0F).b("appleGold").d("apple_golden");
    public static OItem aw = (new OItemSign(67)).b("sign").d("sign");
    public static OItem ax = (new OItemDoor(68, OMaterial.d)).b("doorWood").d("door_wood");
    public static OItem ay = (new OItemBucket(69, 0)).b("bucket").d(16).d("bucket_empty");
    public static OItem az = (new OItemBucket(70, OBlock.F.cF)).b("bucketWater").a(ay).d("bucket_water");
    public static OItem aA = (new OItemBucket(71, OBlock.H.cF)).b("bucketLava").a(ay).d("bucket_lava");
    public static OItem aB = (new OItemMinecart(72, 0)).b("minecart").d("minecart_normal");
    public static OItem aC = (new OItemSaddle(73)).b("saddle").d("saddle");
    public static OItem aD = (new OItemDoor(74, OMaterial.f)).b("doorIron").d("door_iron");
    public static OItem aE = (new OItemRedstone(75)).b("redstone").c(OPotionHelper.i).d("redstone_dust");
    public static OItem aF = (new OItemSnowball(76)).b("snowball").d("snowball");
    public static OItem aG = (new OItemBoat(77)).b("boat").d("boat");
    public static OItem aH = (new OItem(78)).b("leather").a(OCreativeTabs.l).d("leather");
    public static OItem aI = (new OItemBucketMilk(79)).b("milk").a(ay).d("bucket_milk");
    public static OItem aJ = (new OItem(80)).b("brick").a(OCreativeTabs.l).d("brick");
    public static OItem aK = (new OItem(81)).b("clay").a(OCreativeTabs.l).d("clay_ball");
    public static OItem aL = (new OItemReed(82, OBlock.bc)).b("reeds").a(OCreativeTabs.l).d("reeds");
    public static OItem aM = (new OItem(83)).b("paper").a(OCreativeTabs.f).d("paper");
    public static OItem aN = (new OItemBook(84)).b("book").a(OCreativeTabs.f).d("book_normal");
    public static OItem aO = (new OItem(85)).b("slimeball").a(OCreativeTabs.f).d("slimeball");
    public static OItem aP = (new OItemMinecart(86, 1)).b("minecartChest").d("minecart_chest");
    public static OItem aQ = (new OItemMinecart(87, 2)).b("minecartFurnace").d("minecart_furnace");
    public static OItem aR = (new OItemEgg(88)).b("egg").d("egg");
    public static OItem aS = (new OItem(89)).b("compass").a(OCreativeTabs.i).d("compass");
    public static OItemFishingRod aT = (OItemFishingRod) (new OItemFishingRod(90)).b("fishingRod").d("fishing_rod");
    public static OItem aU = (new OItem(91)).b("clock").a(OCreativeTabs.i).d("clock");
    public static OItem aV = (new OItem(92)).b("yellowDust").c(OPotionHelper.j).a(OCreativeTabs.l).d("glowstone_dust");
    public static OItem aW = (new OItemFood(93, 2, 0.3F, false)).b("fishRaw").d("fish_raw");
    public static OItem aX = (new OItemFood(94, 5, 0.6F, false)).b("fishCooked").d("fish_cooked");
    public static OItem aY = (new OItemDye(95)).b("dyePowder").d("dye_powder");
    public static OItem aZ = (new OItem(96)).b("bone").q().a(OCreativeTabs.f).d("bone");
    public static OItem ba = (new OItem(97)).b("sugar").c(OPotionHelper.b).a(OCreativeTabs.l).d("sugar");
    public static OItem bb = (new OItemReed(98, OBlock.bl)).d(1).b("cake").a(OCreativeTabs.h).d("cake");
    public static OItem bc = (new OItemBed(99)).d(1).b("bed").d("bed");
    public static OItem bd = (new OItemReed(100, OBlock.bm)).b("diode").a(OCreativeTabs.d).d("repeater");
    public static OItem be = (new OItemFood(101, 2, 0.1F, false)).b("cookie").d("cookie");
    public static OItemMap bf = (OItemMap) (new OItemMap(102)).b("map").d("map_filled");
    public static OItemShears bg = (OItemShears) (new OItemShears(103)).b("shears").d("shears");
    public static OItem bh = (new OItemFood(104, 2, 0.3F, false)).b("melon").d("melon");
    public static OItem bi = (new OItemSeeds(105, OBlock.bx.cF, OBlock.aF.cF)).b("seeds_pumpkin").d("seeds_pumpkin");
    public static OItem bj = (new OItemSeeds(106, OBlock.by.cF, OBlock.aF.cF)).b("seeds_melon").d("seeds_melon");
    public static OItem bk = (new OItemFood(107, 3, 0.3F, true)).b("beefRaw").d("beef_raw");
    public static OItem bl = (new OItemFood(108, 8, 0.8F, true)).b("beefCooked").d("beef_cooked");
    public static OItem bm = (new OItemFood(109, 2, 0.3F, true)).a(OPotion.s.H, 30, 0, 0.3F).b("chickenRaw").d("chicken_raw");
    public static OItem bn = (new OItemFood(110, 6, 0.6F, true)).b("chickenCooked").d("chicken_cooked");
    public static OItem bo = (new OItemFood(111, 4, 0.1F, true)).a(OPotion.s.H, 30, 0, 0.8F).b("rottenFlesh").d("rotten_flesh");
    public static OItem bp = (new OItemEnderPearl(112)).b("enderPearl").d("ender_pearl");
    public static OItem bq = (new OItem(113)).b("blazeRod").a(OCreativeTabs.l).d("blaze_rod");
    public static OItem br = (new OItem(114)).b("ghastTear").c(OPotionHelper.c).a(OCreativeTabs.k).d("ghast_tear");
    public static OItem bs = (new OItem(115)).b("goldNugget").a(OCreativeTabs.l).d("gold_nugget");
    public static OItem bt = (new OItemSeeds(116, OBlock.bI.cF, OBlock.bh.cF)).b("netherStalkSeeds").c("+4").d("nether_wart");
    public static OItemPotion bu = (OItemPotion) (new OItemPotion(117)).b("potion").d("potion");
    public static OItem bv = (new OItemGlassBottle(118)).b("glassBottle").d("potion_bottle_empty");
    public static OItem bw = (new OItemFood(119, 2, 0.8F, false)).a(OPotion.u.H, 5, 0, 1.0F).b("spiderEye").c(OPotionHelper.d).d("spider_eye");
    public static OItem bx = (new OItem(120)).b("fermentedSpiderEye").c(OPotionHelper.e).a(OCreativeTabs.k).d("spider_eye_fermented");
    public static OItem by = (new OItem(121)).b("blazePowder").c(OPotionHelper.g).a(OCreativeTabs.k).d("blaze_powder");
    public static OItem bz = (new OItem(122)).b("magmaCream").c(OPotionHelper.h).a(OCreativeTabs.k).d("magma_cream");
    public static OItem bA = (new OItemReed(123, OBlock.bK)).b("brewingStand").a(OCreativeTabs.k).d("brewing_stand");
    public static OItem bB = (new OItemReed(124, OBlock.bL)).b("cauldron").a(OCreativeTabs.k).d("cauldron");
    public static OItem bC = (new OItemEnderEye(125)).b("eyeOfEnder").d("ender_eye");
    public static OItem bD = (new OItem(126)).b("speckledMelon").c(OPotionHelper.f).a(OCreativeTabs.k).d("melon_speckled");
    public static OItem bE = (new OItemMonsterPlacer(127)).b("monsterPlacer").d("spawn_egg");
    public static OItem bF = (new OItemExpBottle(128)).b("expBottle").d("experience_bottle");
    public static OItem bG = (new OItemFireball(129)).b("fireball").d("fireball");
    public static OItem bH = (new OItemWritableBook(130)).b("writingBook").a(OCreativeTabs.f).d("book_writable");
    public static OItem bI = (new OItemEditableBook(131)).b("writtenBook").d("book_written");
    public static OItem bJ = (new OItem(132)).b("emerald").a(OCreativeTabs.l).d("emerald");
    public static OItem bK = (new OItemHangingEntity(133, OEntityItemFrame.class)).b("frame").d("item_frame");
    public static OItem bL = (new OItemReed(134, OBlock.ch)).b("flowerPot").a(OCreativeTabs.c).d("flower_pot");
    public static OItem bM = (new OItemSeedFood(135, 4, 0.6F, OBlock.ci.cF, OBlock.aF.cF)).b("carrots").d("carrot");
    public static OItem bN = (new OItemSeedFood(136, 1, 0.3F, OBlock.cj.cF, OBlock.aF.cF)).b("potato").d("potato");
    public static OItem bO = (new OItemFood(137, 6, 0.6F, false)).b("potatoBaked").d("potato_baked");
    public static OItem bP = (new OItemFood(138, 2, 0.3F, false)).a(OPotion.u.H, 5, 0, 0.6F).b("potatoPoisonous").d("potato_poisonous");
    public static OItemEmptyMap bQ = (OItemEmptyMap) (new OItemEmptyMap(139)).b("emptyMap").d("map_empty");
    public static OItem bR = (new OItemFood(140, 6, 1.2F, false)).b("carrotGolden").c(OPotionHelper.l).d("carrot_golden");
    public static OItem bS = (new OItemSkull(141)).b("skull").d("skull");
    public static OItem bT = (new OItemCarrotOnAStick(142)).b("carrotOnAStick").d("carrot_on_a_stick");
    public static OItem bU = (new OItemSimpleFoiled(143)).b("netherStar").a(OCreativeTabs.l).d("nether_star");
    public static OItem bV = (new OItemFood(144, 8, 0.3F, false)).b("pumpkinPie").a(OCreativeTabs.h).d("pumpkin_pie");
    public static OItem bW = (new OItemFirework(145)).b("fireworks").d("fireworks");
    public static OItem bX = (new OItemFireworkCharge(146)).b("fireworksCharge").a(OCreativeTabs.f).d("fireworks_charge");
    public static OItemEnchantedBook bY = (OItemEnchantedBook) (new OItemEnchantedBook(147)).d(1).b("enchantedBook").d("book_enchanted");
    public static OItem bZ = (new OItemReed(148, OBlock.cq)).b("comparator").a(OCreativeTabs.d).d("comparator");
    public static OItem ca = (new OItem(149)).b("netherbrick").a(OCreativeTabs.l).d("netherbrick");
    public static OItem cb = (new OItem(150)).b("netherquartz").a(OCreativeTabs.l).d("quartz");
    public static OItem cc = (new OItemMinecart(151, 3)).b("minecartTnt").d("minecart_tnt");
    public static OItem cd = (new OItemMinecart(152, 5)).b("minecartHopper").d("minecart_hopper");
    public static OItem ce = (new OItem(161)).b("horsearmormetal").d(1).a(OCreativeTabs.f).d("iron_horse_armor");
    public static OItem cf = (new OItem(162)).b("horsearmorgold").d(1).a(OCreativeTabs.f).d("gold_horse_armor");
    public static OItem cg = (new OItem(163)).b("horsearmordiamond").d(1).a(OCreativeTabs.f).d("diamond_horse_armor");
    public static OItem ch = (new OItemLeash(164)).b("leash").d("lead");
    public static OItem ci = (new OItemNameTag(165)).b("nameTag").d("name_tag");
    public static OItem cj = (new OItemRecord(2000, "13")).b("record").d("record_13");
    public static OItem ck = (new OItemRecord(2001, "cat")).b("record").d("record_cat");
    public static OItem cl = (new OItemRecord(2002, "blocks")).b("record").d("record_blocks");
    public static OItem cm = (new OItemRecord(2003, "chirp")).b("record").d("record_chirp");
    public static OItem cn = (new OItemRecord(2004, "far")).b("record").d("record_far");
    public static OItem co = (new OItemRecord(2005, "mall")).b("record").d("record_mall");
    public static OItem cp = (new OItemRecord(2006, "mellohi")).b("record").d("record_mellohi");
    public static OItem cq = (new OItemRecord(2007, "stal")).b("record").d("record_stal");
    public static OItem cr = (new OItemRecord(2008, "strad")).b("record").d("record_strad");
    public static OItem cs = (new OItemRecord(2009, "ward")).b("record").d("record_ward");
    public static OItem ct = (new OItemRecord(2010, "11")).b("record").d("record_11");
    public static OItem cu = (new OItemRecord(2011, "wait")).b("record").d("record_wait");
    public final int cv;
    protected int cw = 64;
    private int b;
    protected boolean cx;
    protected boolean cy;
    private OItem c;
    private String d;
    private String cB;
    protected String cA;

    protected OItem(int i) {
        this.cv = 256 + i;
        if (g[256 + i] != null) {
            System.out.println("CONFLICT @ " + i);
        }

        g[256 + i] = this;
    }

    public OItem d(int i) {
        this.cw = i;
        return this;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        if ((oitemstack.c != 325) && (oitemstack.c != 326) && (oitemstack.c != 327)) {
            return (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), null, getBlockInfo(oworld, i, j, k, l), new Item(oitemstack));
        }
        return false;
    }

    public float a(OItemStack oitemstack, OBlock oblock) {
        return 1.0F;
    }

    public OItemStack a(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {
        return oitemstack;
    }

    public OItemStack b(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {
        return oitemstack;
    }

    public int m() {
        return this.cw;
    }

    public int a(int i) {
        return 0;
    }

    public boolean n() {
        return this.cy;
    }

    protected OItem a(boolean flag) {
        this.cy = flag;
        return this;
    }

    public int o() {
        return this.b;
    }

    protected OItem e(int i) {
        this.b = i;
        return this;
    }

    public boolean p() {
        return this.b > 0 && !this.cy;
    }

    public boolean a(OItemStack oitemstack, OEntityLivingBase oentitylivingbase, OEntityLivingBase oentitylivingbase1) {
        return false;
    }

    public boolean a(OItemStack oitemstack, OWorld oworld, int i, int j, int k, int l, OEntityLivingBase oentitylivingbase) {
        return false;
    }

    public boolean a(OBlock oblock) {
        return false;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OEntityLivingBase oentitylivingbase) {
        return false;
    }

    public OItem q() {
        this.cx = true;
        return this;
    }

    public OItem b(String s) {
        this.cB = s;
        return this;
    }

    public String i(OItemStack oitemstack) {
        String s = this.d(oitemstack);

        return s == null ? "" : OStatCollector.a(s);
    }

    public String a() {
        return "item." + this.cB;
    }

    public String d(OItemStack oitemstack) {
        return "item." + this.cB;
    }

    public OItem a(OItem oitem) {
        this.c = oitem;
        return this;
    }

    public boolean j(OItemStack oitemstack) {
        return true;
    }

    public boolean s() {
        return true;
    }

    public OItem t() {
        return this.c;
    }

    public boolean u() {
        return this.c != null;
    }

    public String v() {
        return OStatCollector.a(this.a() + ".name");
    }

    public String k(OItemStack oitemstack) {
        return OStatCollector.a(this.d(oitemstack) + ".name");
    }

    public void a(OItemStack oitemstack, OWorld oworld, OEntity oentity, int i, boolean flag) {}

    public void d(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {}

    public boolean f() {
        return false;
    }

    public OEnumAction c_(OItemStack oitemstack) {
        return OEnumAction.a;
    }

    public int d_(OItemStack oitemstack) {
        return 0;
    }

    public void a(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer, int i) {}

    protected OItem c(String s) {
        this.d = s;
        return this;
    }

    public String w() {
        return this.d;
    }

    public boolean x() {
        return this.d != null;
    }

    public String l(OItemStack oitemstack) {
        return ("" + OStatCollector.a(this.i(oitemstack) + ".name")).trim();
    }

    public boolean e_(OItemStack oitemstack) {
        return this.m() == 1 && this.p();
    }

    protected OMovingObjectPosition a(OWorld oworld, OEntityPlayer oentityplayer, boolean flag) {
        float f = 1.0F;
        float f1 = oentityplayer.D + (oentityplayer.B - oentityplayer.D) * f;
        float f2 = oentityplayer.C + (oentityplayer.A - oentityplayer.C) * f;
        double d0 = oentityplayer.r + (oentityplayer.u - oentityplayer.r) * (double) f;
        double d1 = oentityplayer.s + (oentityplayer.v - oentityplayer.s) * (double) f + 1.62D - (double) oentityplayer.N;
        double d2 = oentityplayer.t + (oentityplayer.w - oentityplayer.t) * (double) f;
        OVec3 ovec3 = oworld.V().a(d0, d1, d2);
        float f3 = OMathHelper.b(-f2 * 0.017453292F - 3.1415927F);
        float f4 = OMathHelper.a(-f2 * 0.017453292F - 3.1415927F);
        float f5 = -OMathHelper.b(-f1 * 0.017453292F);
        float f6 = OMathHelper.a(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        OVec3 ovec31 = ovec3.c((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);

        return oworld.a(ovec3, ovec31, flag, !flag);
    }

    public int c() {
        return 0;
    }

    public OItem a(OCreativeTabs ocreativetabs) {
        this.a = ocreativetabs;
        return this;
    }

    public boolean z() {
        return true;
    }

    public boolean a(OItemStack oitemstack, OItemStack oitemstack1) {
        return false;
    }

    public Multimap h() {
        return HashMultimap.create();
    }

    protected OItem d(String s) {
        this.cA = s;
        return this;
    }

    static {
        OStatList.c();
    }

    // CanaryMod start - Add convenience method to get block clicked.
    protected Block getBlockInfo(OWorld oworld, int i, int j, int k, int l) {
        Block b = oworld.world.getBlockAt(i, j, k);
        b.setFaceClicked(Block.Face.fromId(l));
        return b;
    } // CanaryMod end
}
