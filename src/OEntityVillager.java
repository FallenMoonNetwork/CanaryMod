import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class OEntityVillager extends OEntityAgeable implements OINpc, OIMerchant {

    private int e;
    private boolean f;
    private boolean g;
    OVillage d;
    private OEntityPlayer h;
    private OMerchantRecipeList i;
    private int j;
    private boolean bK;
    private int bL;
    private String bM;
    private boolean bN;
    private float bO;
    private static final Map bP = new HashMap();
    private static final Map bQ = new HashMap();

    public OEntityVillager(OWorld oworld) {
        this(oworld, 0);
    }

    public OEntityVillager(OWorld oworld, int i) {
        super(oworld);
        this.e = 0;
        this.f = false;
        this.g = false;
        this.d = null;
        this.s(i);
        this.aH = "/mob/villager/villager.png";
        this.bI = 0.5F;
        this.a(0.6F, 1.8F);
        this.aC().b(true);
        this.aC().a(true);
        this.bo.a(0, new OEntityAISwimming(this));
        this.bo.a(1, new OEntityAIAvoidEntity(this, OEntityZombie.class, 8.0F, 0.3F, 0.35F));
        this.bo.a(1, new OEntityAITradePlayer(this));
        this.bo.a(1, new OEntityAILookAtTradePlayer(this));
        this.bo.a(2, new OEntityAIMoveIndoors(this));
        this.bo.a(3, new OEntityAIRestrictOpenDoor(this));
        this.bo.a(4, new OEntityAIOpenDoor(this, true));
        this.bo.a(5, new OEntityAIMoveTwardsRestriction(this, 0.3F));
        this.bo.a(6, new OEntityAIVillagerMate(this));
        this.bo.a(7, new OEntityAIFollowGolem(this));
        this.bo.a(8, new OEntityAIPlay(this, 0.32F));
        this.bo.a(9, new OEntityAIWatchClosest2(this, OEntityPlayer.class, 3.0F, 1.0F));
        this.bo.a(9, new OEntityAIWatchClosest2(this, OEntityVillager.class, 5.0F, 0.02F));
        this.bo.a(9, new OEntityAIWander(this, 0.3F));
        this.bo.a(10, new OEntityAIWatchClosest(this, OEntityLiving.class, 8.0F));
    }

    public boolean bh() {
        return true;
    }

    protected void bp() {
        if (--this.e <= 0) {
            this.q.A.a(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w));
            this.e = 70 + this.ab.nextInt(50);
            this.d = this.q.A.a(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w), 32);
            if (this.d == null) {
                this.aO();
            } else {
                OChunkCoordinates ochunkcoordinates = this.d.a();

                this.b(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, (int) ((float) this.d.b() * 0.6F));
                if (this.bN) {
                    this.bN = false;
                    this.d.b(5);
                }
            }
        }

        if (!this.p() && this.j > 0) {
            --this.j;
            if (this.j <= 0) {
                if (this.bK) {
                    if (this.i.size() > 1) {
                        Iterator iterator = this.i.iterator();

                        while (iterator.hasNext()) {
                            OMerchantRecipe omerchantrecipe = (OMerchantRecipe) iterator.next();

                            if (omerchantrecipe.g()) {
                                omerchantrecipe.a(this.ab.nextInt(6) + this.ab.nextInt(6) + 2);
                            }
                        }
                    }

                    this.t(1);
                    this.bK = false;
                    if (this.d != null && this.bM != null) {
                        this.q.a((OEntity) this, (byte) 14);
                        this.d.a(this.bM, 1);
                    }
                }

                this.d(new OPotionEffect(OPotion.l.H, 200, 0));
            }
        }

        super.bp();
    }

    public boolean a_(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bK.h();
        if ((PluginLoader.HookResult) etc.getLoader().callHook(PluginLoader.Hook.ENTITY_RIGHTCLICKED,
                ((OEntityPlayerMP) oentityplayer).getPlayer(), new Villager(this),
                oitemstack == null ? null : new Item(oitemstack)) == PluginLoader.HookResult.PREVENT_ACTION) {
            return false;
        }
        boolean flag = oitemstack != null && oitemstack.c == OItem.bD.cp;

        if (!flag && this.R() && !this.p() && !this.h_()) {
            if (!this.q.I) {
                this.a(oentityplayer);
                oentityplayer.a((OIMerchant) this, this.bO());
            }

            return true;
        } else {
            return super.a_(oentityplayer);
        }
    }

    protected void a() {
        super.a();
        this.ah.a(16, Integer.valueOf(0));
    }

    public int aW() {
        return 20;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Profession", this.m());
        onbttagcompound.a("Riches", this.bL);
        if (this.i != null) {
            onbttagcompound.a("Offers", this.i.a());
        }
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.s(onbttagcompound.e("Profession"));
        this.bL = onbttagcompound.e("Riches");
        if (onbttagcompound.b("Offers")) {
            ONBTTagCompound onbttagcompound1 = onbttagcompound.l("Offers");

            this.i = new OMerchantRecipeList(onbttagcompound1);
        }
    }

    protected boolean bm() {
        return false;
    }

    protected String bb() {
        return "mob.villager.default";
    }

    protected String bc() {
        return "mob.villager.defaulthurt";
    }

    protected String bd() {
        return "mob.villager.defaultdeath";
    }

    public void s(int i) {
        this.ah.b(16, Integer.valueOf(i));
    }

    public int m() {
        return this.ah.c(16);
    }

    public boolean n() {
        return this.f;
    }

    public void i(boolean flag) {
        this.f = flag;
    }

    public void j(boolean flag) {
        this.g = flag;
    }

    public boolean o() {
        return this.g;
    }

    public void c(OEntityLiving oentityliving) {
        super.c(oentityliving);
        if (this.d != null && oentityliving != null) {
            this.d.a(oentityliving);
            if (oentityliving instanceof OEntityPlayer) {
                byte b0 = -1;

                if (this.h_()) {
                    b0 = -3;
                }

                this.d.a(((OEntityPlayer) oentityliving).c_(), b0);
                if (this.R()) {
                    this.q.a((OEntity) this, (byte) 13);
                }
            }
        }
    }

    public void a(ODamageSource odamagesource) {
        if (this.d != null) {
            OEntity oentity = odamagesource.i();

            if (oentity != null) {
                if (oentity instanceof OEntityPlayer) {
                    this.d.a(((OEntityPlayer) oentity).c_(), -2);
                } else if (oentity instanceof OIMob) {
                    this.d.h();
                }
            } else if (oentity == null) {
                OEntityPlayer oentityplayer = this.q.a(this, 16.0D);

                if (oentityplayer != null) {
                    this.d.h();
                }
            }
        }

        super.a(odamagesource);
    }

    public void a(OEntityPlayer oentityplayer) {
        this.h = oentityplayer;
    }

    public OEntityPlayer m_() {
        return this.h;
    }

    public boolean p() {
        return this.h != null;
    }

    public void a(OMerchantRecipe omerchantrecipe) {
        omerchantrecipe.f();
        if (omerchantrecipe.a((OMerchantRecipe) this.i.get(this.i.size() - 1))) {
            this.j = 40;
            this.bK = true;
            if (this.h != null) {
                this.bM = this.h.c_();
            } else {
                this.bM = null;
            }
        }

        if (omerchantrecipe.a().c == OItem.bI.cp) {
            this.bL += omerchantrecipe.a().a;
        }
    }

    public OMerchantRecipeList b(OEntityPlayer oentityplayer) {
        if (this.i == null) {
            this.t(1);
        }

        return this.i;
    }

    private float j(float f) {
        float f1 = f + this.bO;

        return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
    }

    private void t(int i) {
        if (this.i != null) {
            this.bO = OMathHelper.c((float) this.i.size()) * 0.2F;
        } else {
            this.bO = 0.0F;
        }

        OMerchantRecipeList omerchantrecipelist;

        omerchantrecipelist = new OMerchantRecipeList();
        int j;

        label50:
        switch (this.m()) {
            case 0:
                a(omerchantrecipelist, OItem.U.cp, this.ab, this.j(0.9F));
                a(omerchantrecipelist, OBlock.af.cz, this.ab, this.j(0.5F));
                a(omerchantrecipelist, OItem.bl.cp, this.ab, this.j(0.5F));
                a(omerchantrecipelist, OItem.aW.cp, this.ab, this.j(0.4F));
                b(omerchantrecipelist, OItem.V.cp, this.ab, this.j(0.9F));
                b(omerchantrecipelist, OItem.bg.cp, this.ab, this.j(0.3F));
                b(omerchantrecipelist, OItem.k.cp, this.ab, this.j(0.3F));
                b(omerchantrecipelist, OItem.bd.cp, this.ab, this.j(0.3F));
                b(omerchantrecipelist, OItem.bf.cp, this.ab, this.j(0.3F));
                b(omerchantrecipelist, OItem.j.cp, this.ab, this.j(0.3F));
                b(omerchantrecipelist, OItem.bm.cp, this.ab, this.j(0.3F));
                b(omerchantrecipelist, OItem.m.cp, this.ab, this.j(0.5F));
                if (this.ab.nextFloat() < this.j(0.5F)) {
                    omerchantrecipelist.add(new OMerchantRecipe(new OItemStack(OBlock.J, 10), new OItemStack(OItem.bI), new OItemStack(OItem.aq.cp, 4 + this.ab.nextInt(2), 0)));
                }
                break;

            case 1:
                a(omerchantrecipelist, OItem.aL.cp, this.ab, this.j(0.8F));
                a(omerchantrecipelist, OItem.aM.cp, this.ab, this.j(0.8F));
                a(omerchantrecipelist, OItem.bH.cp, this.ab, this.j(0.3F));
                b(omerchantrecipelist, OBlock.ar.cz, this.ab, this.j(0.8F));
                b(omerchantrecipelist, OBlock.Q.cz, this.ab, this.j(0.2F));
                b(omerchantrecipelist, OItem.aR.cp, this.ab, this.j(0.2F));
                b(omerchantrecipelist, OItem.aT.cp, this.ab, this.j(0.2F));
                if (this.ab.nextFloat() < this.j(0.07F)) {
                    OEnchantment oenchantment = OEnchantment.c[this.ab.nextInt(OEnchantment.c.length)];
                    int k = OMathHelper.a(this.ab, oenchantment.d(), oenchantment.b());
                    OItemStack oitemstack = OItem.bX.a(new OEnchantmentData(oenchantment, k));

                    j = 2 + this.ab.nextInt(5 + k * 10) + 3 * k;
                    omerchantrecipelist.add(new OMerchantRecipe(new OItemStack(OItem.aM), new OItemStack(OItem.bI, j), oitemstack));
                }
                break;

            case 2:
                b(omerchantrecipelist, OItem.bB.cp, this.ab, this.j(0.3F));
                b(omerchantrecipelist, OItem.bE.cp, this.ab, this.j(0.2F));
                b(omerchantrecipelist, OItem.aD.cp, this.ab, this.j(0.4F));
                b(omerchantrecipelist, OBlock.bh.cz, this.ab, this.j(0.3F));
                int[] aint = new int[] { OItem.r.cp, OItem.A.cp, OItem.af.cp, OItem.aj.cp, OItem.i.cp, OItem.D.cp, OItem.h.cp, OItem.C.cp};
                int[] aint1 = aint;
                int l = aint.length;

                j = 0;

                while (true) {
                    if (j >= l) {
                        break label50;
                    }

                    int i1 = aint1[j];

                    if (this.ab.nextFloat() < this.j(0.05F)) {
                        omerchantrecipelist.add(new OMerchantRecipe(new OItemStack(i1, 1, 0), new OItemStack(OItem.bI, 2 + this.ab.nextInt(3), 0), OEnchantmentHelper.a(this.ab, new OItemStack(i1, 1, 0), 5 + this.ab.nextInt(15))));
                    }

                    ++j;
                }

            case 3:
                a(omerchantrecipelist, OItem.n.cp, this.ab, this.j(0.7F));
                a(omerchantrecipelist, OItem.p.cp, this.ab, this.j(0.5F));
                a(omerchantrecipelist, OItem.q.cp, this.ab, this.j(0.5F));
                a(omerchantrecipelist, OItem.o.cp, this.ab, this.j(0.5F));
                b(omerchantrecipelist, OItem.r.cp, this.ab, this.j(0.5F));
                b(omerchantrecipelist, OItem.A.cp, this.ab, this.j(0.5F));
                b(omerchantrecipelist, OItem.i.cp, this.ab, this.j(0.3F));
                b(omerchantrecipelist, OItem.D.cp, this.ab, this.j(0.3F));
                b(omerchantrecipelist, OItem.h.cp, this.ab, this.j(0.5F));
                b(omerchantrecipelist, OItem.C.cp, this.ab, this.j(0.5F));
                b(omerchantrecipelist, OItem.g.cp, this.ab, this.j(0.2F));
                b(omerchantrecipelist, OItem.B.cp, this.ab, this.j(0.2F));
                b(omerchantrecipelist, OItem.Q.cp, this.ab, this.j(0.2F));
                b(omerchantrecipelist, OItem.R.cp, this.ab, this.j(0.2F));
                b(omerchantrecipelist, OItem.ah.cp, this.ab, this.j(0.2F));
                b(omerchantrecipelist, OItem.al.cp, this.ab, this.j(0.2F));
                b(omerchantrecipelist, OItem.ae.cp, this.ab, this.j(0.2F));
                b(omerchantrecipelist, OItem.ai.cp, this.ab, this.j(0.2F));
                b(omerchantrecipelist, OItem.af.cp, this.ab, this.j(0.2F));
                b(omerchantrecipelist, OItem.aj.cp, this.ab, this.j(0.2F));
                b(omerchantrecipelist, OItem.ag.cp, this.ab, this.j(0.2F));
                b(omerchantrecipelist, OItem.ak.cp, this.ab, this.j(0.2F));
                b(omerchantrecipelist, OItem.ad.cp, this.ab, this.j(0.1F));
                b(omerchantrecipelist, OItem.aa.cp, this.ab, this.j(0.1F));
                b(omerchantrecipelist, OItem.ab.cp, this.ab, this.j(0.1F));
                b(omerchantrecipelist, OItem.ac.cp, this.ab, this.j(0.1F));
                break;

            case 4:
                a(omerchantrecipelist, OItem.n.cp, this.ab, this.j(0.7F));
                a(omerchantrecipelist, OItem.ar.cp, this.ab, this.j(0.5F));
                a(omerchantrecipelist, OItem.bj.cp, this.ab, this.j(0.5F));
                b(omerchantrecipelist, OItem.aB.cp, this.ab, this.j(0.1F));
                b(omerchantrecipelist, OItem.X.cp, this.ab, this.j(0.3F));
                b(omerchantrecipelist, OItem.Z.cp, this.ab, this.j(0.3F));
                b(omerchantrecipelist, OItem.W.cp, this.ab, this.j(0.3F));
                b(omerchantrecipelist, OItem.Y.cp, this.ab, this.j(0.3F));
                b(omerchantrecipelist, OItem.as.cp, this.ab, this.j(0.3F));
                b(omerchantrecipelist, OItem.bk.cp, this.ab, this.j(0.3F));
        }

        if (omerchantrecipelist.isEmpty()) {
            a(omerchantrecipelist, OItem.q.cp, this.ab, 1.0F);
        }

        Collections.shuffle(omerchantrecipelist);
        if (this.i == null) {
            this.i = new OMerchantRecipeList();
        }

        for (int j1 = 0; j1 < i && j1 < omerchantrecipelist.size(); ++j1) {
            //CanaryMod
            OMerchantRecipe recipe = (OMerchantRecipe) omerchantrecipelist.get(j1);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.VILLAGER_TRADE_UNLOCK, new Villager(this), new VillagerTrade(recipe))) {
                this.i.a((OMerchantRecipe) omerchantrecipelist.get(j1));
            }
        }
    }

    private static void a(OMerchantRecipeList omerchantrecipelist, int i, Random random, float f) {
        if (random.nextFloat() < f) {
            omerchantrecipelist.add(new OMerchantRecipe(a(i, random), OItem.bI));
        }
    }

    private static OItemStack a(int i, Random random) {
        return new OItemStack(i, b(i, random), 0);
    }

    private static int b(int i, Random random) {
        OTuple otuple = (OTuple) bP.get(Integer.valueOf(i));

        return otuple == null ? 1 : (((Integer) otuple.a()).intValue() >= ((Integer) otuple.b()).intValue() ? ((Integer) otuple.a()).intValue() : ((Integer) otuple.a()).intValue() + random.nextInt(((Integer) otuple.b()).intValue() - ((Integer) otuple.a()).intValue()));
    }

    private static void b(OMerchantRecipeList omerchantrecipelist, int i, Random random, float f) {
        if (random.nextFloat() < f) {
            int j = c(i, random);
            OItemStack oitemstack;
            OItemStack oitemstack1;

            if (j < 0) {
                oitemstack = new OItemStack(OItem.bI.cp, 1, 0);
                oitemstack1 = new OItemStack(i, -j, 0);
            } else {
                oitemstack = new OItemStack(OItem.bI.cp, j, 0);
                oitemstack1 = new OItemStack(i, 1, 0);
            }

            omerchantrecipelist.add(new OMerchantRecipe(oitemstack, oitemstack1));
        }
    }

    private static int c(int i, Random random) {
        OTuple otuple = (OTuple) bQ.get(Integer.valueOf(i));

        return otuple == null ? 1 : (((Integer) otuple.a()).intValue() >= ((Integer) otuple.b()).intValue() ? ((Integer) otuple.a()).intValue() : ((Integer) otuple.a()).intValue() + random.nextInt(((Integer) otuple.b()).intValue() - ((Integer) otuple.a()).intValue()));
    }

    public void bJ() {
        this.s(this.q.s.nextInt(5));
    }

    public void q() {
        this.bN = true;
    }

    public OEntityVillager b(OEntityAgeable oentityageable) {
        OEntityVillager oentityvillager = new OEntityVillager(this.q);

        oentityvillager.bJ();
        return oentityvillager;
    }

    public OEntityAgeable a(OEntityAgeable oentityageable) {
        return this.b(oentityageable);
    }

    static {
        bP.put(Integer.valueOf(OItem.n.cp), new OTuple(Integer.valueOf(16), Integer.valueOf(24)));
        bP.put(Integer.valueOf(OItem.p.cp), new OTuple(Integer.valueOf(8), Integer.valueOf(10)));
        bP.put(Integer.valueOf(OItem.q.cp), new OTuple(Integer.valueOf(8), Integer.valueOf(10)));
        bP.put(Integer.valueOf(OItem.o.cp), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bP.put(Integer.valueOf(OItem.aL.cp), new OTuple(Integer.valueOf(24), Integer.valueOf(36)));
        bP.put(Integer.valueOf(OItem.aM.cp), new OTuple(Integer.valueOf(11), Integer.valueOf(13)));
        bP.put(Integer.valueOf(OItem.bH.cp), new OTuple(Integer.valueOf(1), Integer.valueOf(1)));
        bP.put(Integer.valueOf(OItem.bo.cp), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bP.put(Integer.valueOf(OItem.bB.cp), new OTuple(Integer.valueOf(2), Integer.valueOf(3)));
        bP.put(Integer.valueOf(OItem.ar.cp), new OTuple(Integer.valueOf(14), Integer.valueOf(18)));
        bP.put(Integer.valueOf(OItem.bj.cp), new OTuple(Integer.valueOf(14), Integer.valueOf(18)));
        bP.put(Integer.valueOf(OItem.bl.cp), new OTuple(Integer.valueOf(14), Integer.valueOf(18)));
        bP.put(Integer.valueOf(OItem.aW.cp), new OTuple(Integer.valueOf(9), Integer.valueOf(13)));
        bP.put(Integer.valueOf(OItem.T.cp), new OTuple(Integer.valueOf(34), Integer.valueOf(48)));
        bP.put(Integer.valueOf(OItem.bi.cp), new OTuple(Integer.valueOf(30), Integer.valueOf(38)));
        bP.put(Integer.valueOf(OItem.bh.cp), new OTuple(Integer.valueOf(30), Integer.valueOf(38)));
        bP.put(Integer.valueOf(OItem.U.cp), new OTuple(Integer.valueOf(18), Integer.valueOf(22)));
        bP.put(Integer.valueOf(OBlock.af.cz), new OTuple(Integer.valueOf(14), Integer.valueOf(22)));
        bP.put(Integer.valueOf(OItem.bn.cp), new OTuple(Integer.valueOf(36), Integer.valueOf(64)));
        bQ.put(Integer.valueOf(OItem.j.cp), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(OItem.bf.cp), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(OItem.r.cp), new OTuple(Integer.valueOf(7), Integer.valueOf(11)));
        bQ.put(Integer.valueOf(OItem.A.cp), new OTuple(Integer.valueOf(12), Integer.valueOf(14)));
        bQ.put(Integer.valueOf(OItem.i.cp), new OTuple(Integer.valueOf(6), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(OItem.D.cp), new OTuple(Integer.valueOf(9), Integer.valueOf(12)));
        bQ.put(Integer.valueOf(OItem.h.cp), new OTuple(Integer.valueOf(7), Integer.valueOf(9)));
        bQ.put(Integer.valueOf(OItem.C.cp), new OTuple(Integer.valueOf(10), Integer.valueOf(12)));
        bQ.put(Integer.valueOf(OItem.g.cp), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bQ.put(Integer.valueOf(OItem.B.cp), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(OItem.Q.cp), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bQ.put(Integer.valueOf(OItem.R.cp), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(OItem.ah.cp), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bQ.put(Integer.valueOf(OItem.al.cp), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(OItem.ae.cp), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bQ.put(Integer.valueOf(OItem.ai.cp), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(OItem.af.cp), new OTuple(Integer.valueOf(10), Integer.valueOf(14)));
        bQ.put(Integer.valueOf(OItem.aj.cp), new OTuple(Integer.valueOf(16), Integer.valueOf(19)));
        bQ.put(Integer.valueOf(OItem.ag.cp), new OTuple(Integer.valueOf(8), Integer.valueOf(10)));
        bQ.put(Integer.valueOf(OItem.ak.cp), new OTuple(Integer.valueOf(11), Integer.valueOf(14)));
        bQ.put(Integer.valueOf(OItem.ad.cp), new OTuple(Integer.valueOf(5), Integer.valueOf(7)));
        bQ.put(Integer.valueOf(OItem.aa.cp), new OTuple(Integer.valueOf(5), Integer.valueOf(7)));
        bQ.put(Integer.valueOf(OItem.ab.cp), new OTuple(Integer.valueOf(11), Integer.valueOf(15)));
        bQ.put(Integer.valueOf(OItem.ac.cp), new OTuple(Integer.valueOf(9), Integer.valueOf(11)));
        bQ.put(Integer.valueOf(OItem.V.cp), new OTuple(Integer.valueOf(-4), Integer.valueOf(-2)));
        bQ.put(Integer.valueOf(OItem.bg.cp), new OTuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bQ.put(Integer.valueOf(OItem.k.cp), new OTuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bQ.put(Integer.valueOf(OItem.bd.cp), new OTuple(Integer.valueOf(-10), Integer.valueOf(-7)));
        bQ.put(Integer.valueOf(OBlock.Q.cz), new OTuple(Integer.valueOf(-5), Integer.valueOf(-3)));
        bQ.put(Integer.valueOf(OBlock.ar.cz), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(OItem.X.cp), new OTuple(Integer.valueOf(4), Integer.valueOf(5)));
        bQ.put(Integer.valueOf(OItem.Z.cp), new OTuple(Integer.valueOf(2), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(OItem.W.cp), new OTuple(Integer.valueOf(2), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(OItem.Y.cp), new OTuple(Integer.valueOf(2), Integer.valueOf(4)));
        bQ.put(Integer.valueOf(OItem.aB.cp), new OTuple(Integer.valueOf(6), Integer.valueOf(8)));
        bQ.put(Integer.valueOf(OItem.bE.cp), new OTuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bQ.put(Integer.valueOf(OItem.aD.cp), new OTuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bQ.put(Integer.valueOf(OItem.aR.cp), new OTuple(Integer.valueOf(10), Integer.valueOf(12)));
        bQ.put(Integer.valueOf(OItem.aT.cp), new OTuple(Integer.valueOf(10), Integer.valueOf(12)));
        bQ.put(Integer.valueOf(OBlock.bh.cz), new OTuple(Integer.valueOf(-3), Integer.valueOf(-1)));
        bQ.put(Integer.valueOf(OItem.as.cp), new OTuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bQ.put(Integer.valueOf(OItem.bk.cp), new OTuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bQ.put(Integer.valueOf(OItem.bm.cp), new OTuple(Integer.valueOf(-8), Integer.valueOf(-6)));
        bQ.put(Integer.valueOf(OItem.bB.cp), new OTuple(Integer.valueOf(7), Integer.valueOf(11)));
        bQ.put(Integer.valueOf(OItem.m.cp), new OTuple(Integer.valueOf(-12), Integer.valueOf(-8)));
    }
}
