import java.util.Random;

public class OItem {

    private OCreativeTabs a = null;
    protected static Random e = new Random();
    public static OItem[] f = new OItem[32000];
    public static OItem g = (new OItemSpade(0, OEnumToolMaterial.c)).b("shovelIron");
    public static OItem h = (new OItemPickaxe(1, OEnumToolMaterial.c)).b("pickaxeIron");
    public static OItem i = (new OItemAxe(2, OEnumToolMaterial.c)).b("hatchetIron");
    public static OItem j = (new OItemFlintAndSteel(3)).b("flintAndSteel");
    public static OItem k = (new OItemFood(4, 4, 0.3F, false)).b("apple");
    public static OItemBow l = (OItemBow) (new OItemBow(5)).b("bow");
    public static OItem m = (new OItem(6)).b("arrow").a(OCreativeTabs.j);
    public static OItem n = (new OItemCoal(7)).b("coal");
    public static OItem o = (new OItem(8)).b("diamond").a(OCreativeTabs.l);
    public static OItem p = (new OItem(9)).b("ingotIron").a(OCreativeTabs.l);
    public static OItem q = (new OItem(10)).b("ingotGold").a(OCreativeTabs.l);
    public static OItem r = (new OItemSword(11, OEnumToolMaterial.c)).b("swordIron");
    public static OItem s = (new OItemSword(12, OEnumToolMaterial.a)).b("swordWood");
    public static OItem t = (new OItemSpade(13, OEnumToolMaterial.a)).b("shovelWood");
    public static OItem u = (new OItemPickaxe(14, OEnumToolMaterial.a)).b("pickaxeWood");
    public static OItem v = (new OItemAxe(15, OEnumToolMaterial.a)).b("hatchetWood");
    public static OItem w = (new OItemSword(16, OEnumToolMaterial.b)).b("swordStone");
    public static OItem x = (new OItemSpade(17, OEnumToolMaterial.b)).b("shovelStone");
    public static OItem y = (new OItemPickaxe(18, OEnumToolMaterial.b)).b("pickaxeStone");
    public static OItem z = (new OItemAxe(19, OEnumToolMaterial.b)).b("hatchetStone");
    public static OItem A = (new OItemSword(20, OEnumToolMaterial.d)).b("swordDiamond");
    public static OItem B = (new OItemSpade(21, OEnumToolMaterial.d)).b("shovelDiamond");
    public static OItem C = (new OItemPickaxe(22, OEnumToolMaterial.d)).b("pickaxeDiamond");
    public static OItem D = (new OItemAxe(23, OEnumToolMaterial.d)).b("hatchetDiamond");
    public static OItem E = (new OItem(24)).p().b("stick").a(OCreativeTabs.l);
    public static OItem F = (new OItem(25)).b("bowl").a(OCreativeTabs.l);
    public static OItem G = (new OItemSoup(26, 6)).b("mushroomStew");
    public static OItem H = (new OItemSword(27, OEnumToolMaterial.e)).b("swordGold");
    public static OItem I = (new OItemSpade(28, OEnumToolMaterial.e)).b("shovelGold");
    public static OItem J = (new OItemPickaxe(29, OEnumToolMaterial.e)).b("pickaxeGold");
    public static OItem K = (new OItemAxe(30, OEnumToolMaterial.e)).b("hatchetGold");
    public static OItem L = (new OItemReed(31, OBlock.bY)).b("string").a(OCreativeTabs.l);
    public static OItem M = (new OItem(32)).b("feather").a(OCreativeTabs.l);
    public static OItem N = (new OItem(33)).b("sulphur").c(OPotionHelper.k).a(OCreativeTabs.l);
    public static OItem O = (new OItemHoe(34, OEnumToolMaterial.a)).b("hoeWood");
    public static OItem P = (new OItemHoe(35, OEnumToolMaterial.b)).b("hoeStone");
    public static OItem Q = (new OItemHoe(36, OEnumToolMaterial.c)).b("hoeIron");
    public static OItem R = (new OItemHoe(37, OEnumToolMaterial.d)).b("hoeDiamond");
    public static OItem S = (new OItemHoe(38, OEnumToolMaterial.e)).b("hoeGold");
    public static OItem T = (new OItemSeeds(39, OBlock.aD.cz, OBlock.aE.cz)).b("seeds");
    public static OItem U = (new OItem(40)).b("wheat").a(OCreativeTabs.l);
    public static OItem V = (new OItemFood(41, 5, 0.6F, false)).b("bread");
    public static OItemArmor W = (OItemArmor) (new OItemArmor(42, OEnumArmorMaterial.a, 0, 0)).b("helmetCloth");
    public static OItemArmor X = (OItemArmor) (new OItemArmor(43, OEnumArmorMaterial.a, 0, 1)).b("chestplateCloth");
    public static OItemArmor Y = (OItemArmor) (new OItemArmor(44, OEnumArmorMaterial.a, 0, 2)).b("leggingsCloth");
    public static OItemArmor Z = (OItemArmor) (new OItemArmor(45, OEnumArmorMaterial.a, 0, 3)).b("bootsCloth");
    public static OItemArmor aa = (OItemArmor) (new OItemArmor(46, OEnumArmorMaterial.b, 1, 0)).b("helmetChain");
    public static OItemArmor ab = (OItemArmor) (new OItemArmor(47, OEnumArmorMaterial.b, 1, 1)).b("chestplateChain");
    public static OItemArmor ac = (OItemArmor) (new OItemArmor(48, OEnumArmorMaterial.b, 1, 2)).b("leggingsChain");
    public static OItemArmor ad = (OItemArmor) (new OItemArmor(49, OEnumArmorMaterial.b, 1, 3)).b("bootsChain");
    public static OItemArmor ae = (OItemArmor) (new OItemArmor(50, OEnumArmorMaterial.c, 2, 0)).b("helmetIron");
    public static OItemArmor af = (OItemArmor) (new OItemArmor(51, OEnumArmorMaterial.c, 2, 1)).b("chestplateIron");
    public static OItemArmor ag = (OItemArmor) (new OItemArmor(52, OEnumArmorMaterial.c, 2, 2)).b("leggingsIron");
    public static OItemArmor ah = (OItemArmor) (new OItemArmor(53, OEnumArmorMaterial.c, 2, 3)).b("bootsIron");
    public static OItemArmor ai = (OItemArmor) (new OItemArmor(54, OEnumArmorMaterial.e, 3, 0)).b("helmetDiamond");
    public static OItemArmor aj = (OItemArmor) (new OItemArmor(55, OEnumArmorMaterial.e, 3, 1)).b("chestplateDiamond");
    public static OItemArmor ak = (OItemArmor) (new OItemArmor(56, OEnumArmorMaterial.e, 3, 2)).b("leggingsDiamond");
    public static OItemArmor al = (OItemArmor) (new OItemArmor(57, OEnumArmorMaterial.e, 3, 3)).b("bootsDiamond");
    public static OItemArmor am = (OItemArmor) (new OItemArmor(58, OEnumArmorMaterial.d, 4, 0)).b("helmetGold");
    public static OItemArmor an = (OItemArmor) (new OItemArmor(59, OEnumArmorMaterial.d, 4, 1)).b("chestplateGold");
    public static OItemArmor ao = (OItemArmor) (new OItemArmor(60, OEnumArmorMaterial.d, 4, 2)).b("leggingsGold");
    public static OItemArmor ap = (OItemArmor) (new OItemArmor(61, OEnumArmorMaterial.d, 4, 3)).b("bootsGold");
    public static OItem aq = (new OItem(62)).b("flint").a(OCreativeTabs.l);
    public static OItem ar = (new OItemFood(63, 3, 0.3F, true)).b("porkchopRaw");
    public static OItem as = (new OItemFood(64, 8, 0.8F, true)).b("porkchopCooked");
    public static OItem at = (new OItemHangingEntity(65, OEntityPainting.class)).b("painting");
    public static OItem au = (new OItemAppleGold(66, 4, 1.2F, false)).j().a(OPotion.l.H, 5, 0, 1.0F).b("appleGold");
    public static OItem av = (new OItemSign(67)).b("sign");
    public static OItem aw = (new OItemDoor(68, OMaterial.d)).b("doorWood");
    public static OItem ax = (new OItemBucket(69, 0)).b("bucket").d(16);
    public static OItem ay = (new OItemBucket(70, OBlock.E.cz)).b("bucketWater").a(ax);
    public static OItem az = (new OItemBucket(71, OBlock.G.cz)).b("bucketLava").a(ax);
    public static OItem aA = (new OItemMinecart(72, 0)).b("minecart");
    public static OItem aB = (new OItemSaddle(73)).b("saddle");
    public static OItem aC = (new OItemDoor(74, OMaterial.f)).b("doorIron");
    public static OItem aD = (new OItemRedstone(75)).b("redstone").c(OPotionHelper.i);
    public static OItem aE = (new OItemSnowball(76)).b("snowball");
    public static OItem aF = (new OItemBoat(77)).b("boat");
    public static OItem aG = (new OItem(78)).b("leather").a(OCreativeTabs.l);
    public static OItem aH = (new OItemBucketMilk(79)).b("milk").a(ax);
    public static OItem aI = (new OItem(80)).b("brick").a(OCreativeTabs.l);
    public static OItem aJ = (new OItem(81)).b("clay").a(OCreativeTabs.l);
    public static OItem aK = (new OItemReed(82, OBlock.bb)).b("reeds").a(OCreativeTabs.l);
    public static OItem aL = (new OItem(83)).b("paper").a(OCreativeTabs.f);
    public static OItem aM = (new OItemBook(84)).b("book").a(OCreativeTabs.f);
    public static OItem aN = (new OItem(85)).b("slimeball").a(OCreativeTabs.f);
    public static OItem aO = (new OItemMinecart(86, 1)).b("minecartChest");
    public static OItem aP = (new OItemMinecart(87, 2)).b("minecartFurnace");
    public static OItem aQ = (new OItemEgg(88)).b("egg");
    public static OItem aR = (new OItem(89)).b("compass").a(OCreativeTabs.i);
    public static OItemFishingRod aS = (OItemFishingRod) (new OItemFishingRod(90)).b("fishingRod");
    public static OItem aT = (new OItem(91)).b("clock").a(OCreativeTabs.i);
    public static OItem aU = (new OItem(92)).b("yellowDust").c(OPotionHelper.j).a(OCreativeTabs.l);
    public static OItem aV = (new OItemFood(93, 2, 0.3F, false)).b("fishRaw");
    public static OItem aW = (new OItemFood(94, 5, 0.6F, false)).b("fishCooked");
    public static OItem aX = (new OItemDye(95)).b("dyePowder");
    public static OItem aY = (new OItem(96)).b("bone").p().a(OCreativeTabs.f);
    public static OItem aZ = (new OItem(97)).b("sugar").c(OPotionHelper.b).a(OCreativeTabs.l);
    public static OItem ba = (new OItemReed(98, OBlock.bk)).d(1).b("cake").a(OCreativeTabs.h);
    public static OItem bb = (new OItemBed(99)).d(1).b("bed");
    public static OItem bc = (new OItemReed(100, OBlock.bl)).b("diode").a(OCreativeTabs.d);
    public static OItem bd = (new OItemFood(101, 2, 0.1F, false)).b("cookie");
    public static OItemMap be = (OItemMap) (new OItemMap(102)).b("map");
    public static OItemShears bf = (OItemShears) (new OItemShears(103)).b("shears");
    public static OItem bg = (new OItemFood(104, 2, 0.3F, false)).b("melon");
    public static OItem bh = (new OItemSeeds(105, OBlock.bw.cz, OBlock.aE.cz)).b("seeds_pumpkin");
    public static OItem bi = (new OItemSeeds(106, OBlock.bx.cz, OBlock.aE.cz)).b("seeds_melon");
    public static OItem bj = (new OItemFood(107, 3, 0.3F, true)).b("beefRaw");
    public static OItem bk = (new OItemFood(108, 8, 0.8F, true)).b("beefCooked");
    public static OItem bl = (new OItemFood(109, 2, 0.3F, true)).a(OPotion.s.H, 30, 0, 0.3F).b("chickenRaw");
    public static OItem bm = (new OItemFood(110, 6, 0.6F, true)).b("chickenCooked");
    public static OItem bn = (new OItemFood(111, 4, 0.1F, true)).a(OPotion.s.H, 30, 0, 0.8F).b("rottenFlesh");
    public static OItem bo = (new OItemEnderPearl(112)).b("enderPearl");
    public static OItem bp = (new OItem(113)).b("blazeRod").a(OCreativeTabs.l);
    public static OItem bq = (new OItem(114)).b("ghastTear").c(OPotionHelper.c).a(OCreativeTabs.k);
    public static OItem br = (new OItem(115)).b("goldNugget").a(OCreativeTabs.l);
    public static OItem bs = (new OItemSeeds(116, OBlock.bH.cz, OBlock.bg.cz)).b("netherStalkSeeds").c("+4");
    public static OItemPotion bt = (OItemPotion) (new OItemPotion(117)).b("potion");
    public static OItem bu = (new OItemGlassBottle(118)).b("glassBottle");
    public static OItem bv = (new OItemFood(119, 2, 0.8F, false)).a(OPotion.u.H, 5, 0, 1.0F).b("spiderEye").c(OPotionHelper.d);
    public static OItem bw = (new OItem(120)).b("fermentedSpiderEye").c(OPotionHelper.e).a(OCreativeTabs.k);
    public static OItem bx = (new OItem(121)).b("blazePowder").c(OPotionHelper.g).a(OCreativeTabs.k);
    public static OItem by = (new OItem(122)).b("magmaCream").c(OPotionHelper.h).a(OCreativeTabs.k);
    public static OItem bz = (new OItemReed(123, OBlock.bJ)).b("brewingStand").a(OCreativeTabs.k);
    public static OItem bA = (new OItemReed(124, OBlock.bK)).b("cauldron").a(OCreativeTabs.k);
    public static OItem bB = (new OItemEnderEye(125)).b("eyeOfEnder");
    public static OItem bC = (new OItem(126)).b("speckledMelon").c(OPotionHelper.f).a(OCreativeTabs.k);
    public static OItem bD = (new OItemMonsterPlacer(127)).b("monsterPlacer");
    public static OItem bE = (new OItemExpBottle(128)).b("expBottle");
    public static OItem bF = (new OItemFireball(129)).b("fireball");
    public static OItem bG = (new OItemWritableBook(130)).b("writingBook").a(OCreativeTabs.f);
    public static OItem bH = (new OItemEditableBook(131)).b("writtenBook");
    public static OItem bI = (new OItem(132)).b("emerald").a(OCreativeTabs.l);
    public static OItem bJ = (new OItemHangingEntity(133, OEntityItemFrame.class)).b("frame");
    public static OItem bK = (new OItemReed(134, OBlock.cg)).b("flowerPot").a(OCreativeTabs.c);
    public static OItem bL = (new OItemSeedFood(135, 4, 0.6F, OBlock.ch.cz, OBlock.aE.cz)).b("carrots");
    public static OItem bM = (new OItemSeedFood(136, 1, 0.3F, OBlock.ci.cz, OBlock.aE.cz)).b("potato");
    public static OItem bN = (new OItemFood(137, 6, 0.6F, false)).b("potatoBaked");
    public static OItem bO = (new OItemFood(138, 2, 0.3F, false)).a(OPotion.u.H, 5, 0, 0.6F).b("potatoPoisonous");
    public static OItemEmptyMap bP = (OItemEmptyMap) (new OItemEmptyMap(139)).b("emptyMap");
    public static OItem bQ = (new OItemFood(140, 6, 1.2F, false)).b("carrotGolden").c(OPotionHelper.l);
    public static OItem bR = (new OItemSkull(141)).b("skull");
    public static OItem bS = (new OItemCarrotOnAStick(142)).b("carrotOnAStick");
    public static OItem bT = (new OItemSimpleFoiled(143)).b("netherStar").a(OCreativeTabs.l);
    public static OItem bU = (new OItemFood(144, 8, 0.3F, false)).b("pumpkinPie").a(OCreativeTabs.h);
    public static OItem bV = (new OItemFirework(145)).b("fireworks");
    public static OItem bW = (new OItemFireworkCharge(146)).b("fireworksCharge").a(OCreativeTabs.f);
    public static OItemEnchantedBook bX = (OItemEnchantedBook) (new OItemEnchantedBook(147)).d(1).b("enchantedBook");
    public static OItem bY = (new OItemReed(148, OBlock.cp)).b("comparator").a(OCreativeTabs.d);
    public static OItem bZ = (new OItem(149)).b("netherbrick").a(OCreativeTabs.l);
    public static OItem ca = (new OItem(150)).b("netherquartz").a(OCreativeTabs.l);
    public static OItem cb = (new OItemMinecart(151, 3)).b("minecartTnt");
    public static OItem cc = (new OItemMinecart(152, 5)).b("minecartHopper");
    public static OItem cd = (new OItemRecord(2000, "13")).b("record");
    public static OItem ce = (new OItemRecord(2001, "cat")).b("record");
    public static OItem cf = (new OItemRecord(2002, "blocks")).b("record");
    public static OItem cg = (new OItemRecord(2003, "chirp")).b("record");
    public static OItem ch = (new OItemRecord(2004, "far")).b("record");
    public static OItem ci = (new OItemRecord(2005, "mall")).b("record");
    public static OItem cj = (new OItemRecord(2006, "mellohi")).b("record");
    public static OItem ck = (new OItemRecord(2007, "stal")).b("record");
    public static OItem cl = (new OItemRecord(2008, "strad")).b("record");
    public static OItem cm = (new OItemRecord(2009, "ward")).b("record");
    public static OItem cn = (new OItemRecord(2010, "11")).b("record");
    public static OItem co = (new OItemRecord(2011, "wait")).b("record");
    public final int cp;
    protected int cq = 64;
    private int b = 0;
    protected boolean cr = false;
    protected boolean cs = false;
    private OItem c = null;
    private String d = null;
    private String cu;

    protected OItem(int i) {
        this.cp = 256 + i;
        if (f[256 + i] != null) {
            System.out.println("CONFLICT @ " + i);
        }

        f[256 + i] = this;
    }

    public OItem d(int i) {
        this.cq = i;
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

    public int l() {
        return this.cq;
    }

    public int a(int i) {
        return 0;
    }

    public boolean m() {
        return this.cs;
    }

    protected OItem a(boolean flag) {
        this.cs = flag;
        return this;
    }

    public int n() {
        return this.b;
    }

    protected OItem e(int i) {
        this.b = i;
        return this;
    }

    public boolean o() {
        return this.b > 0 && !this.cs;
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

    public OItem p() {
        this.cr = true;
        return this;
    }

    public OItem b(String s) {
        this.cu = s;
        return this;
    }

    public String i(OItemStack oitemstack) {
        String s = this.d(oitemstack);

        return s == null ? "" : OStatCollector.a(s);
    }

    public String a() {
        return "item." + this.cu;
    }

    public String d(OItemStack oitemstack) {
        return "item." + this.cu;
    }

    public OItem a(OItem oitem) {
        this.c = oitem;
        return this;
    }

    public boolean j(OItemStack oitemstack) {
        return true;
    }

    public boolean r() {
        return true;
    }

    public OItem s() {
        return this.c;
    }

    public boolean t() {
        return this.c != null;
    }

    public String u() {
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

    public OEnumAction b_(OItemStack oitemstack) {
        return OEnumAction.a;
    }

    public int c_(OItemStack oitemstack) {
        return 0;
    }

    public void a(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer, int i) {}

    protected OItem c(String s) {
        this.d = s;
        return this;
    }

    public String v() {
        return this.d;
    }

    public boolean w() {
        return this.d != null;
    }

    public String l(OItemStack oitemstack) {
        return ("" + OStringTranslate.a().c(this.i(oitemstack))).trim();
    }

    public boolean d_(OItemStack oitemstack) {
        return this.l() == 1 && this.o();
    }

    protected OMovingObjectPosition a(OWorld oworld, OEntityPlayer oentityplayer, boolean flag) {
        float f = 1.0F;
        float f1 = oentityplayer.D + (oentityplayer.B - oentityplayer.D) * f;
        float f2 = oentityplayer.C + (oentityplayer.A - oentityplayer.C) * f;
        double d0 = oentityplayer.r + (oentityplayer.u - oentityplayer.r) * (double) f;
        double d1 = oentityplayer.s + (oentityplayer.v - oentityplayer.s) * (double) f + 1.62D - (double) oentityplayer.N;
        double d2 = oentityplayer.t + (oentityplayer.w - oentityplayer.t) * (double) f;
        OVec3 ovec3 = oworld.T().a(d0, d1, d2);
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

    public boolean y() {
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
