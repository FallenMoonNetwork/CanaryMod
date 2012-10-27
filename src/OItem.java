import java.util.Random;


public class OItem {

    private OCreativeTabs a = null;
    protected static Random d = new Random();
    public static OItem[] e = new OItem[32000];
    public static OItem f = (new OItemSpade(0, OEnumToolMaterial.c)).b(2, 5).b("shovelIron");
    public static OItem g = (new OItemPickaxe(1, OEnumToolMaterial.c)).b(2, 6).b("pickaxeIron");
    public static OItem h = (new OItemAxe(2, OEnumToolMaterial.c)).b(2, 7).b("hatchetIron");
    public static OItem i = (new OItemFlintAndSteel(3)).b(5, 0).b("flintAndSteel");
    public static OItem j = (new OItemFood(4, 4, 0.3F, false)).b(10, 0).b("apple");
    public static OItem k = (new OItemBow(5)).b(5, 1).b("bow");
    public static OItem l = (new OItem(6)).b(5, 2).b("arrow").a(OCreativeTabs.j);
    public static OItem m = (new OItemCoal(7)).b(7, 0).b("coal");
    public static OItem n = (new OItem(8)).b(7, 3).b("diamond").a(OCreativeTabs.l);
    public static OItem o = (new OItem(9)).b(7, 1).b("ingotIron").a(OCreativeTabs.l);
    public static OItem p = (new OItem(10)).b(7, 2).b("ingotGold").a(OCreativeTabs.l);
    public static OItem q = (new OItemSword(11, OEnumToolMaterial.c)).b(2, 4).b("swordIron");
    public static OItem r = (new OItemSword(12, OEnumToolMaterial.a)).b(0, 4).b("swordWood");
    public static OItem s = (new OItemSpade(13, OEnumToolMaterial.a)).b(0, 5).b("shovelWood");
    public static OItem t = (new OItemPickaxe(14, OEnumToolMaterial.a)).b(0, 6).b("pickaxeWood");
    public static OItem u = (new OItemAxe(15, OEnumToolMaterial.a)).b(0, 7).b("hatchetWood");
    public static OItem v = (new OItemSword(16, OEnumToolMaterial.b)).b(1, 4).b("swordStone");
    public static OItem w = (new OItemSpade(17, OEnumToolMaterial.b)).b(1, 5).b("shovelStone");
    public static OItem x = (new OItemPickaxe(18, OEnumToolMaterial.b)).b(1, 6).b("pickaxeStone");
    public static OItem y = (new OItemAxe(19, OEnumToolMaterial.b)).b(1, 7).b("hatchetStone");
    public static OItem z = (new OItemSword(20, OEnumToolMaterial.d)).b(3, 4).b("swordDiamond");
    public static OItem A = (new OItemSpade(21, OEnumToolMaterial.d)).b(3, 5).b("shovelDiamond");
    public static OItem B = (new OItemPickaxe(22, OEnumToolMaterial.d)).b(3, 6).b("pickaxeDiamond");
    public static OItem C = (new OItemAxe(23, OEnumToolMaterial.d)).b(3, 7).b("hatchetDiamond");
    public static OItem D = (new OItem(24)).b(5, 3).o().b("stick").a(OCreativeTabs.l);
    public static OItem E = (new OItem(25)).b(7, 4).b("bowl").a(OCreativeTabs.l);
    public static OItem F = (new OItemSoup(26, 6)).b(8, 4).b("mushroomStew");
    public static OItem G = (new OItemSword(27, OEnumToolMaterial.e)).b(4, 4).b("swordGold");
    public static OItem H = (new OItemSpade(28, OEnumToolMaterial.e)).b(4, 5).b("shovelGold");
    public static OItem I = (new OItemPickaxe(29, OEnumToolMaterial.e)).b(4, 6).b("pickaxeGold");
    public static OItem J = (new OItemAxe(30, OEnumToolMaterial.e)).b(4, 7).b("hatchetGold");
    public static OItem K = (new OItemReed(31, OBlock.bX)).b(8, 0).b("string").a(OCreativeTabs.l);
    public static OItem L = (new OItem(32)).b(8, 1).b("feather").a(OCreativeTabs.l);
    public static OItem M = (new OItem(33)).b(8, 2).b("sulphur").c(OPotionHelper.k).a(OCreativeTabs.l);
    public static OItem N = (new OItemHoe(34, OEnumToolMaterial.a)).b(0, 8).b("hoeWood");
    public static OItem O = (new OItemHoe(35, OEnumToolMaterial.b)).b(1, 8).b("hoeStone");
    public static OItem P = (new OItemHoe(36, OEnumToolMaterial.c)).b(2, 8).b("hoeIron");
    public static OItem Q = (new OItemHoe(37, OEnumToolMaterial.d)).b(3, 8).b("hoeDiamond");
    public static OItem R = (new OItemHoe(38, OEnumToolMaterial.e)).b(4, 8).b("hoeGold");
    public static OItem S = (new OItemSeeds(39, OBlock.aC.cm, OBlock.aD.cm)).b(9, 0).b("seeds");
    public static OItem T = (new OItem(40)).b(9, 1).b("wheat").a(OCreativeTabs.l);
    public static OItem U = (new OItemFood(41, 5, 0.6F, false)).b(9, 2).b("bread");
    public static OItem V = (new OItemArmor(42, OEnumArmorMaterial.a, 0, 0)).b(0, 0).b("helmetCloth");
    public static OItem W = (new OItemArmor(43, OEnumArmorMaterial.a, 0, 1)).b(0, 1).b("chestplateCloth");
    public static OItem X = (new OItemArmor(44, OEnumArmorMaterial.a, 0, 2)).b(0, 2).b("leggingsCloth");
    public static OItem Y = (new OItemArmor(45, OEnumArmorMaterial.a, 0, 3)).b(0, 3).b("bootsCloth");
    public static OItem Z = (new OItemArmor(46, OEnumArmorMaterial.b, 1, 0)).b(1, 0).b("helmetChain");
    public static OItem aa = (new OItemArmor(47, OEnumArmorMaterial.b, 1, 1)).b(1, 1).b("chestplateChain");
    public static OItem ab = (new OItemArmor(48, OEnumArmorMaterial.b, 1, 2)).b(1, 2).b("leggingsChain");
    public static OItem ac = (new OItemArmor(49, OEnumArmorMaterial.b, 1, 3)).b(1, 3).b("bootsChain");
    public static OItem ad = (new OItemArmor(50, OEnumArmorMaterial.c, 2, 0)).b(2, 0).b("helmetIron");
    public static OItem ae = (new OItemArmor(51, OEnumArmorMaterial.c, 2, 1)).b(2, 1).b("chestplateIron");
    public static OItem af = (new OItemArmor(52, OEnumArmorMaterial.c, 2, 2)).b(2, 2).b("leggingsIron");
    public static OItem ag = (new OItemArmor(53, OEnumArmorMaterial.c, 2, 3)).b(2, 3).b("bootsIron");
    public static OItem ah = (new OItemArmor(54, OEnumArmorMaterial.e, 3, 0)).b(3, 0).b("helmetDiamond");
    public static OItem ai = (new OItemArmor(55, OEnumArmorMaterial.e, 3, 1)).b(3, 1).b("chestplateDiamond");
    public static OItem aj = (new OItemArmor(56, OEnumArmorMaterial.e, 3, 2)).b(3, 2).b("leggingsDiamond");
    public static OItem ak = (new OItemArmor(57, OEnumArmorMaterial.e, 3, 3)).b(3, 3).b("bootsDiamond");
    public static OItem al = (new OItemArmor(58, OEnumArmorMaterial.d, 4, 0)).b(4, 0).b("helmetGold");
    public static OItem am = (new OItemArmor(59, OEnumArmorMaterial.d, 4, 1)).b(4, 1).b("chestplateGold");
    public static OItem an = (new OItemArmor(60, OEnumArmorMaterial.d, 4, 2)).b(4, 2).b("leggingsGold");
    public static OItem ao = (new OItemArmor(61, OEnumArmorMaterial.d, 4, 3)).b(4, 3).b("bootsGold");
    public static OItem ap = (new OItem(62)).b(6, 0).b("flint").a(OCreativeTabs.l);
    public static OItem aq = (new OItemFood(63, 3, 0.3F, true)).b(7, 5).b("porkchopRaw");
    public static OItem ar = (new OItemFood(64, 8, 0.8F, true)).b(8, 5).b("porkchopCooked");
    public static OItem as = (new OItemHangingEntity(65, OEntityPainting.class)).b(10, 1).b("painting");
    public static OItem at = (new OItemAppleGold(66, 4, 1.2F, false)).j().a(OPotion.l.H, 5, 0, 1.0F).b(11, 0).b("appleGold");
    public static OItem au = (new OItemSign(67)).b(10, 2).b("sign");
    public static OItem av = (new OItemDoor(68, OMaterial.d)).b(11, 2).b("doorWood");
    public static OItem aw = (new OItemBucket(69, 0)).b(10, 4).b("bucket").d(16);
    public static OItem ax = (new OItemBucket(70, OBlock.D.cm)).b(11, 4).b("bucketWater").a(aw);
    public static OItem ay = (new OItemBucket(71, OBlock.F.cm)).b(12, 4).b("bucketLava").a(aw);
    public static OItem az = (new OItemMinecart(72, 0)).b(7, 8).b("minecart");
    public static OItem aA = (new OItemSaddle(73)).b(8, 6).b("saddle");
    public static OItem aB = (new OItemDoor(74, OMaterial.f)).b(12, 2).b("doorIron");
    public static OItem aC = (new OItemRedstone(75)).b(8, 3).b("redstone").c(OPotionHelper.i);
    public static OItem aD = (new OItemSnowball(76)).b(14, 0).b("snowball");
    public static OItem aE = (new OItemBoat(77)).b(8, 8).b("boat");
    public static OItem aF = (new OItem(78)).b(7, 6).b("leather").a(OCreativeTabs.l);
    public static OItem aG = (new OItemBucketMilk(79)).b(13, 4).b("milk").a(aw);
    public static OItem aH = (new OItem(80)).b(6, 1).b("brick").a(OCreativeTabs.l);
    public static OItem aI = (new OItem(81)).b(9, 3).b("clay").a(OCreativeTabs.l);
    public static OItem aJ = (new OItemReed(82, OBlock.ba)).b(11, 1).b("reeds").a(OCreativeTabs.l);
    public static OItem aK = (new OItem(83)).b(10, 3).b("paper").a(OCreativeTabs.f);
    public static OItem aL = (new OItem(84)).b(11, 3).b("book").a(OCreativeTabs.f);
    public static OItem aM = (new OItem(85)).b(14, 1).b("slimeball").a(OCreativeTabs.f);
    public static OItem aN = (new OItemMinecart(86, 1)).b(7, 9).b("minecartChest");
    public static OItem aO = (new OItemMinecart(87, 2)).b(7, 10).b("minecartFurnace");
    public static OItem aP = (new OItemEgg(88)).b(12, 0).b("egg");
    public static OItem aQ = (new OItem(89)).b(6, 3).b("compass").a(OCreativeTabs.i);
    public static OItem aR = (new OItemFishingRod(90)).b(5, 4).b("fishingRod");
    public static OItem aS = (new OItem(91)).b(6, 4).b("clock").a(OCreativeTabs.i);
    public static OItem aT = (new OItem(92)).b(9, 4).b("yellowDust").c(OPotionHelper.j).a(OCreativeTabs.l);
    public static OItem aU = (new OItemFood(93, 2, 0.3F, false)).b(9, 5).b("fishRaw");
    public static OItem aV = (new OItemFood(94, 5, 0.6F, false)).b(10, 5).b("fishCooked");
    public static OItem aW = (new OItemDye(95)).b(14, 4).b("dyePowder");
    public static OItem aX = (new OItem(96)).b(12, 1).b("bone").o().a(OCreativeTabs.f);
    public static OItem aY = (new OItem(97)).b(13, 0).b("sugar").c(OPotionHelper.b).a(OCreativeTabs.l);
    public static OItem aZ = (new OItemReed(98, OBlock.bj)).d(1).b(13, 1).b("cake").a(OCreativeTabs.h);
    public static OItem ba = (new OItemBed(99)).d(1).b(13, 2).b("bed");
    public static OItem bb = (new OItemReed(100, OBlock.bk)).b(6, 5).b("diode").a(OCreativeTabs.d);
    public static OItem bc = (new OItemFood(101, 2, 0.1F, false)).b(12, 5).b("cookie");
    public static OItemMap bd = (OItemMap) (new OItemMap(102)).b(12, 3).b("map");
    public static OItemShears be = (OItemShears) (new OItemShears(103)).b(13, 5).b("shears");
    public static OItem bf = (new OItemFood(104, 2, 0.3F, false)).b(13, 6).b("melon");
    public static OItem bg = (new OItemSeeds(105, OBlock.bv.cm, OBlock.aD.cm)).b(13, 3).b("seeds_pumpkin");
    public static OItem bh = (new OItemSeeds(106, OBlock.bw.cm, OBlock.aD.cm)).b(14, 3).b("seeds_melon");
    public static OItem bi = (new OItemFood(107, 3, 0.3F, true)).b(9, 6).b("beefRaw");
    public static OItem bj = (new OItemFood(108, 8, 0.8F, true)).b(10, 6).b("beefCooked");
    public static OItem bk = (new OItemFood(109, 2, 0.3F, true)).a(OPotion.s.H, 30, 0, 0.3F).b(9, 7).b("chickenRaw");
    public static OItem bl = (new OItemFood(110, 6, 0.6F, true)).b(10, 7).b("chickenCooked");
    public static OItem bm = (new OItemFood(111, 4, 0.1F, true)).a(OPotion.s.H, 30, 0, 0.8F).b(11, 5).b("rottenFlesh");
    public static OItem bn = (new OItemEnderPearl(112)).b(11, 6).b("enderPearl");
    public static OItem bo = (new OItem(113)).b(12, 6).b("blazeRod").a(OCreativeTabs.l);
    public static OItem bp = (new OItem(114)).b(11, 7).b("ghastTear").c(OPotionHelper.c).a(OCreativeTabs.k);
    public static OItem bq = (new OItem(115)).b(12, 7).b("goldNugget").a(OCreativeTabs.l);
    public static OItem br = (new OItemSeeds(116, OBlock.bG.cm, OBlock.bf.cm)).b(13, 7).b("netherStalkSeeds").c("+4");
    public static OItemPotion bs = (OItemPotion) (new OItemPotion(117)).b(13, 8).b("potion");
    public static OItem bt = (new OItemGlassBottle(118)).b(12, 8).b("glassBottle");
    public static OItem bu = (new OItemFood(119, 2, 0.8F, false)).a(OPotion.u.H, 5, 0, 1.0F).b(11, 8).b("spiderEye").c(OPotionHelper.d);
    public static OItem bv = (new OItem(120)).b(10, 8).b("fermentedSpiderEye").c(OPotionHelper.e).a(OCreativeTabs.k);
    public static OItem bw = (new OItem(121)).b(13, 9).b("blazePowder").c(OPotionHelper.g).a(OCreativeTabs.k);
    public static OItem bx = (new OItem(122)).b(13, 10).b("magmaCream").c(OPotionHelper.h).a(OCreativeTabs.k);
    public static OItem by = (new OItemReed(123, OBlock.bI)).b(12, 10).b("brewingStand").a(OCreativeTabs.k);
    public static OItem bz = (new OItemReed(124, OBlock.bJ)).b(12, 9).b("cauldron").a(OCreativeTabs.k);
    public static OItem bA = (new OItemEnderEye(125)).b(11, 9).b("eyeOfEnder");
    public static OItem bB = (new OItem(126)).b(9, 8).b("speckledMelon").c(OPotionHelper.f).a(OCreativeTabs.k);
    public static OItem bC = (new OItemMonsterPlacer(127)).b(9, 9).b("monsterPlacer");
    public static OItem bD = (new OItemExpBottle(128)).b(11, 10).b("expBottle");
    public static OItem bE = (new OItemFireball(129)).b(14, 2).b("fireball");
    public static OItem bF = (new OItemWritableBook(130)).b(11, 11).b("writingBook").a(OCreativeTabs.f);
    public static OItem bG = (new OItemEditableBook(131)).b(12, 11).b("writtenBook");
    public static OItem bH = (new OItem(132)).b(10, 11).b("emerald").a(OCreativeTabs.l);
    public static OItem bI = (new OItemHangingEntity(133, OEntityItemFrame.class)).b(14, 12).b("frame");
    public static OItem bJ = (new OItemReed(134, OBlock.cf)).b(13, 11).b("flowerPot").a(OCreativeTabs.c);
    public static OItem bK = (new OItemSeedFood(135, 4, 0.6F, OBlock.cg.cm, OBlock.aD.cm)).b(8, 7).b("carrots");
    public static OItem bL = (new OItemSeedFood(136, 1, 0.3F, OBlock.ch.cm, OBlock.aD.cm)).b(7, 7).b("potato");
    public static OItem bM = (new OItemFood(137, 6, 0.6F, false)).b(6, 7).b("potatoBaked");
    public static OItem bN = (new OItemFood(138, 2, 0.3F, false)).a(OPotion.u.H, 5, 0, 0.6F).b(6, 8).b("potatoPoisonous");
    public static OItemEmptyMap bO = (OItemEmptyMap) (new OItemEmptyMap(139)).b(13, 12).b("emptyMap");
    public static OItem bP = (new OItemFood(140, 6, 1.2F, false)).b(6, 9).b("carrotGolden").c(OPotionHelper.l);
    public static OItem bQ = (new OItemSkull(141)).b("skull");
    public static OItem bR = (new OItemCarrotOnAStick(142)).b(6, 6).b("carrotOnAStick");
    public static OItem bS = (new OItemSimpleFoiled(143)).b(9, 11).b("netherStar").a(OCreativeTabs.l);
    public static OItem bT = (new OItemFood(144, 8, 0.3F, false)).b(8, 9).b("pumpkinPie").a(OCreativeTabs.h);
    public static OItem bU = (new OItemRecord(2000, "13")).b(0, 15).b("record");
    public static OItem bV = (new OItemRecord(2001, "cat")).b(1, 15).b("record");
    public static OItem bW = (new OItemRecord(2002, "blocks")).b(2, 15).b("record");
    public static OItem bX = (new OItemRecord(2003, "chirp")).b(3, 15).b("record");
    public static OItem bY = (new OItemRecord(2004, "far")).b(4, 15).b("record");
    public static OItem bZ = (new OItemRecord(2005, "mall")).b(5, 15).b("record");
    public static OItem ca = (new OItemRecord(2006, "mellohi")).b(6, 15).b("record");
    public static OItem cb = (new OItemRecord(2007, "stal")).b(7, 15).b("record");
    public static OItem cc = (new OItemRecord(2008, "strad")).b(8, 15).b("record");
    public static OItem cd = (new OItemRecord(2009, "ward")).b(9, 15).b("record");
    public static OItem ce = (new OItemRecord(2010, "11")).b(10, 15).b("record");
    public final int cf;
    protected int cg = 64;
    private int b = 0;
    protected int ch;
    protected boolean ci = false;
    protected boolean cj = false;
    private OItem c = null;
    private String ck = null;
    private String cl;

    protected OItem(int i) {
        this.cf = 256 + i;
        if (e[256 + i] != null) {
            System.out.println("CONFLICT @ " + i);
        }

        e[256 + i] = this;
    }

    public OItem c(int i) {
        this.ch = i;
        return this;
    }

    // CanaryMod set max stack size
    public OItem d(int i) {
        this.cg = i;
        return this;
    }

    public OItem b(int i, int j) {
        this.ch = i + j * 16;
        return this;
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        if (oitemstack.c != 325 && oitemstack.c != 326 && oitemstack.c != 327) { // fix for onItemUse being called twice for buckets
            return (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), null, this.getBlockInfo(oworld, i, j, k, l), new Item(oitemstack));
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

    public int k() { // CanaryMod - get max stacksize
        return this.cg;
    }

    public int a(int i) {
        return 0;
    }

    public boolean l() {
        return this.cj;
    }

    protected OItem a(boolean flag) {
        this.cj = flag;
        return this;
    }

    public int m() {
        return this.b;
    }

    protected OItem e(int i) {
        this.b = i;
        return this;
    }

    public boolean n() {
        return this.b > 0 && !this.cj;
    }

    public boolean a(OItemStack oitemstack, OEntityLiving oentityliving, OEntityLiving oentityliving1) {
        return false;
    }

    public boolean a(OItemStack oitemstack, OWorld oworld, int i, int j, int k, int l, OEntityLiving oentityliving) {
        return false;
    }

    public int a(OEntity oentity) {
        return 1;
    }

    public boolean a(OBlock oblock) {
        return false;
    }

    public boolean a(OItemStack oitemstack, OEntityLiving oentityliving) {
        return false;
    }

    public OItem o() {
        this.ci = true;
        return this;
    }

    public OItem b(String s) {
        this.cl = "item." + s;
        return this;
    }

    public String g(OItemStack oitemstack) {
        String s = this.c_(oitemstack);

        return s == null ? "" : OStatCollector.a(s);
    }

    public String a() {
        return this.cl;
    }

    public String c_(OItemStack oitemstack) {
        return this.cl;
    }

    public OItem a(OItem oitem) {
        this.c = oitem;
        return this;
    }

    public boolean h(OItemStack oitemstack) {
        return true;
    }

    public boolean q() {
        return true;
    }

    public OItem r() {
        return this.c;
    }

    public boolean s() {
        return this.c != null;
    }

    public String t() {
        return OStatCollector.a(this.a() + ".name");
    }

    public String i(OItemStack oitemstack) {
        return OStatCollector.a(this.c_(oitemstack) + ".name");
    }

    public void a(OItemStack oitemstack, OWorld oworld, OEntity oentity, int i, boolean flag) {}

    public void d(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {}

    public boolean f() {
        return false;
    }

    public OEnumAction d_(OItemStack oitemstack) {
        return OEnumAction.a;
    }

    public int a(OItemStack oitemstack) {
        return 0;
    }

    public void a(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer, int i) {}

    protected OItem c(String s) {
        this.ck = s;
        return this;
    }

    public String u() {
        return this.ck;
    }

    public boolean v() {
        return this.ck != null;
    }

    public String j(OItemStack oitemstack) {
        return ("" + OStringTranslate.a().c(this.g(oitemstack))).trim();
    }

    public boolean k(OItemStack oitemstack) {
        return this.k() == 1 && this.n();
    }

    protected OMovingObjectPosition a(OWorld oworld, OEntityPlayer oentityplayer, boolean flag) {
        float f = 1.0F;
        float f1 = oentityplayer.C + (oentityplayer.A - oentityplayer.C) * f;
        float f2 = oentityplayer.B + (oentityplayer.z - oentityplayer.B) * f;
        double d0 = oentityplayer.q + (oentityplayer.t - oentityplayer.q) * (double) f;
        double d1 = oentityplayer.r + (oentityplayer.u - oentityplayer.r) * (double) f + 1.62D - (double) oentityplayer.M;
        double d2 = oentityplayer.s + (oentityplayer.v - oentityplayer.s) * (double) f;
        OVec3 ovec3 = oworld.R().a(d0, d1, d2);
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

    public boolean x() {
        return true;
    }

    public boolean a(OItemStack oitemstack, OItemStack oitemstack1) {
        return false;
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