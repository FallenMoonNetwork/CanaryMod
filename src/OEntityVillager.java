import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class OEntityVillager extends OEntityAgeable implements OIMerchant, OINpc {

    private int bq;
    private boolean br;
    private boolean bs;
    OVillage bp;
    private OEntityPlayer bt;
    private OMerchantRecipeList bu;
    private int bv;
    private boolean bw;
    private int bx;
    private String by;
    private boolean bz;
    private float bA;
    private static final Map bB = new HashMap();
    private static final Map bC = new HashMap();

    private Villager villager = new Villager(this);

    public OEntityVillager(OWorld oworld) {
        this(oworld, 0);
    }

    public OEntityVillager(OWorld oworld, int i) {
        super(oworld);
        this.p(i);
        this.a(0.6F, 1.8F);
        this.k().b(true);
        this.k().a(true);
        this.c.a(0, new OEntityAISwimming(this));
        this.c.a(1, new OEntityAIAvoidEntity(this, OEntityZombie.class, 8.0F, 0.6D, 0.6D));
        this.c.a(1, new OEntityAITradePlayer(this));
        this.c.a(1, new OEntityAILookAtTradePlayer(this));
        this.c.a(2, new OEntityAIMoveIndoors(this));
        this.c.a(3, new OEntityAIRestrictOpenDoor(this));
        this.c.a(4, new OEntityAIOpenDoor(this, true));
        this.c.a(5, new OEntityAIMoveTwardsRestriction(this, 0.6D));
        this.c.a(6, new OEntityAIVillagerMate(this));
        this.c.a(7, new OEntityAIFollowGolem(this));
        this.c.a(8, new OEntityAIPlay(this, 0.32D));
        this.c.a(9, new OEntityAIWatchClosest2(this, OEntityPlayer.class, 3.0F, 1.0F));
        this.c.a(9, new OEntityAIWatchClosest2(this, OEntityVillager.class, 5.0F, 0.02F));
        this.c.a(9, new OEntityAIWander(this, 0.6D));
        this.c.a(10, new OEntityAIWatchClosest(this, OEntityLiving.class, 8.0F));
    }

    protected void ax() {
        super.ax();
        this.a(OSharedMonsterAttributes.d).a(0.5D);
    }

    public boolean bb() {
        return true;
    }

    protected void bg() {
        if (--this.bq <= 0) {
            this.q.A.a(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w));
            this.bq = 70 + this.ab.nextInt(50);
            this.bp = this.q.A.a(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w), 32);
            if (this.bp == null) {
                this.bN();
            } else {
                OChunkCoordinates ochunkcoordinates = this.bp.a();

                this.b(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, (int) ((float) this.bp.b() * 0.6F));
                if (this.bz) {
                    this.bz = false;
                    this.bp.b(5);
                }
            }
        }

        if (!this.bS() && this.bv > 0) {
            --this.bv;
            if (this.bv <= 0) {
                if (this.bw) {
                    if (this.bu.size() > 1) {
                        Iterator iterator = this.bu.iterator();

                        while (iterator.hasNext()) {
                            OMerchantRecipe omerchantrecipe = (OMerchantRecipe) iterator.next();

                            if (omerchantrecipe.g()) {
                                omerchantrecipe.a(this.ab.nextInt(6) + this.ab.nextInt(6) + 2);
                            }
                        }
                    }

                    this.q(1);
                    this.bw = false;
                    if (this.bp != null && this.by != null) {
                        this.q.a((OEntity) this, (byte) 14);
                        this.bp.a(this.by, 1);
                    }
                }

                this.d(new OPotionEffect(OPotion.l.H, 200, 0));
            }
        }

        super.bg();
    }

    public boolean a(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bn.h();
        if ((PluginLoader.HookResult) etc.getLoader().callHook(PluginLoader.Hook.ENTITY_RIGHTCLICKED,
                ((OEntityPlayerMP) oentityplayer).getPlayer(), this.getEntity(),
                oitemstack == null ? null : new Item(oitemstack)) == PluginLoader.HookResult.PREVENT_ACTION) {
            return false;
        }
        boolean flag = oitemstack != null && oitemstack.d == OItem.bE.cv;

        if (!flag && this.R() && !this.bS() && !this.g_()) {
            if (!this.q.I) {
                this.a_(oentityplayer);
                oentityplayer.a((OIMerchant) this, this.bw());
            }

            return true;
        } else {
            return super.a(oentityplayer);
        }
    }

    protected void a() {
        super.a();
        this.ah.a(16, Integer.valueOf(0));
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Profession", this.bP());
        onbttagcompound.a("Riches", this.bx);
        if (this.bu != null) {
            onbttagcompound.a("Offers", this.bu.a());
        }
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.p(onbttagcompound.e("Profession"));
        this.bx = onbttagcompound.e("Riches");
        if (onbttagcompound.b("Offers")) {
            ONBTTagCompound onbttagcompound1 = onbttagcompound.l("Offers");

            this.bu = new OMerchantRecipeList(onbttagcompound1);
        }
    }

    protected boolean t() {
        return false;
    }

    protected String r() {
        return this.bS() ? "mob.villager.haggle" : "mob.villager.idle";
    }

    protected String aK() {
        return "mob.villager.hit";
    }

    protected String aL() {
        return "mob.villager.death";
    }

    public void p(int i) {
        this.ah.b(16, Integer.valueOf(i));
    }

    public int bP() {
        return this.ah.c(16);
    }

    public boolean bQ() {
        return this.br;
    }

    public void j(boolean flag) {
        this.br = flag;
    }

    public void k(boolean flag) {
        this.bs = flag;
    }

    public boolean bR() {
        return this.bs;
    }

    public void b(OEntityLivingBase oentitylivingbase) {
        super.b(oentitylivingbase);
        if (this.bp != null && oentitylivingbase != null) {
            this.bp.a(oentitylivingbase);
            if (oentitylivingbase instanceof OEntityPlayer) {
                byte b0 = -1;

                if (this.g_()) {
                    b0 = -3;
                }

                this.bp.a(((OEntityPlayer) oentitylivingbase).c_(), b0);
                if (this.R()) {
                    this.q.a((OEntity) this, (byte) 13);
                }
            }
        }
    }

    public void a(ODamageSource odamagesource) {
        if (this.bp != null) {
            OEntity oentity = odamagesource.i();

            if (oentity != null) {
                if (oentity instanceof OEntityPlayer) {
                    this.bp.a(((OEntityPlayer) oentity).c_(), -2);
                } else if (oentity instanceof OIMob) {
                    this.bp.h();
                }
            } else if (oentity == null) {
                OEntityPlayer oentityplayer = this.q.a(this, 16.0D);

                if (oentityplayer != null) {
                    this.bp.h();
                }
            }
        }

        super.a(odamagesource);
    }

    public void a_(OEntityPlayer oentityplayer) {
        this.bt = oentityplayer;
    }

    public OEntityPlayer m_() {
        return this.bt;
    }

    public boolean bS() {
        return this.bt != null;
    }

    public void a(OMerchantRecipe omerchantrecipe) {
        omerchantrecipe.f();
        this.a_ = -this.o();
        this.a("mob.villager.yes", this.aW(), this.aX());
        if (omerchantrecipe.a((OMerchantRecipe) this.bu.get(this.bu.size() - 1))) {
            this.bv = 40;
            this.bw = true;
            if (this.bt != null) {
                this.by = this.bt.c_();
            } else {
                this.by = null;
            }
        }

        if (omerchantrecipe.a().d == OItem.bJ.cv) {
            this.bx += omerchantrecipe.a().b;
        }
    }

    public void a_(OItemStack oitemstack) {
        if (!this.q.I && this.a_ > -this.o() + 20) {
            this.a_ = -this.o();
            if (oitemstack != null) {
                this.a("mob.villager.yes", this.aW(), this.aX());
            } else {
                this.a("mob.villager.no", this.aW(), this.aX());
            }
        }
    }

    public OMerchantRecipeList b(OEntityPlayer oentityplayer) {
        if (this.bu == null) {
            this.q(1);
        }

        return this.bu;
    }

    private float o(float f) {
        float f1 = f + this.bA;

        return f1 > 0.9F ? 0.9F - (f1 - 0.9F) : f1;
    }

    private void q(int i) {
        if (this.bu != null) {
            this.bA = OMathHelper.c((float) this.bu.size()) * 0.2F;
        } else {
            this.bA = 0.0F;
        }

        OMerchantRecipeList omerchantrecipelist;

        omerchantrecipelist = new OMerchantRecipeList();
        int j;

        label50:
        switch (this.bP()) {
            case 0:
                a(omerchantrecipelist, OItem.V.cv, this.ab, this.o(0.9F));
                a(omerchantrecipelist, OBlock.ag.cF, this.ab, this.o(0.5F));
                a(omerchantrecipelist, OItem.bm.cv, this.ab, this.o(0.5F));
                a(omerchantrecipelist, OItem.aX.cv, this.ab, this.o(0.4F));
                b(omerchantrecipelist, OItem.W.cv, this.ab, this.o(0.9F));
                b(omerchantrecipelist, OItem.bh.cv, this.ab, this.o(0.3F));
                b(omerchantrecipelist, OItem.l.cv, this.ab, this.o(0.3F));
                b(omerchantrecipelist, OItem.be.cv, this.ab, this.o(0.3F));
                b(omerchantrecipelist, OItem.bg.cv, this.ab, this.o(0.3F));
                b(omerchantrecipelist, OItem.k.cv, this.ab, this.o(0.3F));
                b(omerchantrecipelist, OItem.bn.cv, this.ab, this.o(0.3F));
                b(omerchantrecipelist, OItem.n.cv, this.ab, this.o(0.5F));
                if (this.ab.nextFloat() < this.o(0.5F)) {
                    omerchantrecipelist.add(new OMerchantRecipe(new OItemStack(OBlock.K, 10), new OItemStack(OItem.bJ), new OItemStack(OItem.ar.cv, 4 + this.ab.nextInt(2), 0)));
                }
                break;

            case 1:
                a(omerchantrecipelist, OItem.aM.cv, this.ab, this.o(0.8F));
                a(omerchantrecipelist, OItem.aN.cv, this.ab, this.o(0.8F));
                a(omerchantrecipelist, OItem.bI.cv, this.ab, this.o(0.3F));
                b(omerchantrecipelist, OBlock.as.cF, this.ab, this.o(0.8F));
                b(omerchantrecipelist, OBlock.R.cF, this.ab, this.o(0.2F));
                b(omerchantrecipelist, OItem.aS.cv, this.ab, this.o(0.2F));
                b(omerchantrecipelist, OItem.aU.cv, this.ab, this.o(0.2F));
                if (this.ab.nextFloat() < this.o(0.07F)) {
                    OEnchantment oenchantment = OEnchantment.c[this.ab.nextInt(OEnchantment.c.length)];
                    int k = OMathHelper.a(this.ab, oenchantment.d(), oenchantment.b());
                    OItemStack oitemstack = OItem.bY.a(new OEnchantmentData(oenchantment, k));

                    j = 2 + this.ab.nextInt(5 + k * 10) + 3 * k;
                    omerchantrecipelist.add(new OMerchantRecipe(new OItemStack(OItem.aN), new OItemStack(OItem.bJ, j), oitemstack));
                }
                break;

            case 2:
                b(omerchantrecipelist, OItem.bC.cv, this.ab, this.o(0.3F));
                b(omerchantrecipelist, OItem.bF.cv, this.ab, this.o(0.2F));
                b(omerchantrecipelist, OItem.aE.cv, this.ab, this.o(0.4F));
                b(omerchantrecipelist, OBlock.bi.cF, this.ab, this.o(0.3F));
                int[] aint = new int[] { OItem.s.cv, OItem.B.cv, OItem.ag.cv, OItem.ak.cv, OItem.j.cv, OItem.E.cv, OItem.i.cv, OItem.D.cv};
                int[] aint1 = aint;
                int l = aint.length;

                j = 0;

                while (true) {
                    if (j >= l) {
                        break label50;
                    }

                    int i1 = aint1[j];

                    if (this.ab.nextFloat() < this.o(0.05F)) {
                        omerchantrecipelist.add(new OMerchantRecipe(new OItemStack(i1, 1, 0), new OItemStack(OItem.bJ, 2 + this.ab.nextInt(3), 0), OEnchantmentHelper.a(this.ab, new OItemStack(i1, 1, 0), 5 + this.ab.nextInt(15))));
                    }

                    ++j;
                }

            case 3:
                a(omerchantrecipelist, OItem.o.cv, this.ab, this.o(0.7F));
                a(omerchantrecipelist, OItem.q.cv, this.ab, this.o(0.5F));
                a(omerchantrecipelist, OItem.r.cv, this.ab, this.o(0.5F));
                a(omerchantrecipelist, OItem.p.cv, this.ab, this.o(0.5F));
                b(omerchantrecipelist, OItem.s.cv, this.ab, this.o(0.5F));
                b(omerchantrecipelist, OItem.B.cv, this.ab, this.o(0.5F));
                b(omerchantrecipelist, OItem.j.cv, this.ab, this.o(0.3F));
                b(omerchantrecipelist, OItem.E.cv, this.ab, this.o(0.3F));
                b(omerchantrecipelist, OItem.i.cv, this.ab, this.o(0.5F));
                b(omerchantrecipelist, OItem.D.cv, this.ab, this.o(0.5F));
                b(omerchantrecipelist, OItem.h.cv, this.ab, this.o(0.2F));
                b(omerchantrecipelist, OItem.C.cv, this.ab, this.o(0.2F));
                b(omerchantrecipelist, OItem.R.cv, this.ab, this.o(0.2F));
                b(omerchantrecipelist, OItem.S.cv, this.ab, this.o(0.2F));
                b(omerchantrecipelist, OItem.ai.cv, this.ab, this.o(0.2F));
                b(omerchantrecipelist, OItem.am.cv, this.ab, this.o(0.2F));
                b(omerchantrecipelist, OItem.af.cv, this.ab, this.o(0.2F));
                b(omerchantrecipelist, OItem.aj.cv, this.ab, this.o(0.2F));
                b(omerchantrecipelist, OItem.ag.cv, this.ab, this.o(0.2F));
                b(omerchantrecipelist, OItem.ak.cv, this.ab, this.o(0.2F));
                b(omerchantrecipelist, OItem.ah.cv, this.ab, this.o(0.2F));
                b(omerchantrecipelist, OItem.al.cv, this.ab, this.o(0.2F));
                b(omerchantrecipelist, OItem.ae.cv, this.ab, this.o(0.1F));
                b(omerchantrecipelist, OItem.ab.cv, this.ab, this.o(0.1F));
                b(omerchantrecipelist, OItem.ac.cv, this.ab, this.o(0.1F));
                b(omerchantrecipelist, OItem.ad.cv, this.ab, this.o(0.1F));
                break;

            case 4:
                a(omerchantrecipelist, OItem.o.cv, this.ab, this.o(0.7F));
                a(omerchantrecipelist, OItem.as.cv, this.ab, this.o(0.5F));
                a(omerchantrecipelist, OItem.bk.cv, this.ab, this.o(0.5F));
                b(omerchantrecipelist, OItem.aC.cv, this.ab, this.o(0.1F));
                b(omerchantrecipelist, OItem.Y.cv, this.ab, this.o(0.3F));
                b(omerchantrecipelist, OItem.aa.cv, this.ab, this.o(0.3F));
                b(omerchantrecipelist, OItem.X.cv, this.ab, this.o(0.3F));
                b(omerchantrecipelist, OItem.Z.cv, this.ab, this.o(0.3F));
                b(omerchantrecipelist, OItem.at.cv, this.ab, this.o(0.3F));
                b(omerchantrecipelist, OItem.bl.cv, this.ab, this.o(0.3F));
        }

        if (omerchantrecipelist.isEmpty()) {
            a(omerchantrecipelist, OItem.r.cv, this.ab, 1.0F);
        }

        Collections.shuffle(omerchantrecipelist);
        if (this.bu == null) {
            this.bu = new OMerchantRecipeList();
        }

        for (int j1 = 0; j1 < i && j1 < omerchantrecipelist.size(); ++j1) {
            //CanaryMod
            OMerchantRecipe recipe = (OMerchantRecipe) omerchantrecipelist.get(j1);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.VILLAGER_TRADE_UNLOCK, this.getEntity(), recipe.getVillagerTrade())) {
                this.bu.a((OMerchantRecipe) omerchantrecipelist.get(j1));
            }
        }
    }

    private static void a(OMerchantRecipeList omerchantrecipelist, int i, Random random, float f) {
        if (random.nextFloat() < f) {
            omerchantrecipelist.add(new OMerchantRecipe(a(i, random), OItem.bJ));
        }
    }

    private static OItemStack a(int i, Random random) {
        return new OItemStack(i, b(i, random), 0);
    }

    private static int b(int i, Random random) {
        OTuple otuple = (OTuple) bB.get(Integer.valueOf(i));

        return otuple == null ? 1 : (((Integer) otuple.a()).intValue() >= ((Integer) otuple.b()).intValue() ? ((Integer) otuple.a()).intValue() : ((Integer) otuple.a()).intValue() + random.nextInt(((Integer) otuple.b()).intValue() - ((Integer) otuple.a()).intValue()));
    }

    private static void b(OMerchantRecipeList omerchantrecipelist, int i, Random random, float f) {
        if (random.nextFloat() < f) {
            int j = c(i, random);
            OItemStack oitemstack;
            OItemStack oitemstack1;

            if (j < 0) {
                oitemstack = new OItemStack(OItem.bJ.cv, 1, 0);
                oitemstack1 = new OItemStack(i, -j, 0);
            } else {
                oitemstack = new OItemStack(OItem.bJ.cv, j, 0);
                oitemstack1 = new OItemStack(i, 1, 0);
            }

            omerchantrecipelist.add(new OMerchantRecipe(oitemstack, oitemstack1));
        }
    }

    private static int c(int i, Random random) {
        OTuple otuple = (OTuple) bC.get(Integer.valueOf(i));

        return otuple == null ? 1 : (((Integer) otuple.a()).intValue() >= ((Integer) otuple.b()).intValue() ? ((Integer) otuple.a()).intValue() : ((Integer) otuple.a()).intValue() + random.nextInt(((Integer) otuple.b()).intValue() - ((Integer) otuple.a()).intValue()));
    }

    public OEntityLivingData a(OEntityLivingData oentitylivingdata) {
        oentitylivingdata = super.a(oentitylivingdata);
        this.p(this.q.s.nextInt(5));
        return oentitylivingdata;
    }

    public void bT() {
        this.bz = true;
    }

    public OEntityVillager b(OEntityAgeable oentityageable) {
        OEntityVillager oentityvillager = new OEntityVillager(this.q);

        oentityvillager.a((OEntityLivingData) null);
        return oentityvillager;
    }

    public boolean bC() {
        return false;
    }

    public OEntityAgeable a(OEntityAgeable oentityageable) {
        return this.b(oentityageable);
    }

    // CanaryMod start: add getEntity
    @Override
    public Villager getEntity() {
        return villager;
    } // CanaryMod end

    static {
        bB.put(Integer.valueOf(OItem.o.cv), new OTuple(Integer.valueOf(16), Integer.valueOf(24)));
        bB.put(Integer.valueOf(OItem.q.cv), new OTuple(Integer.valueOf(8), Integer.valueOf(10)));
        bB.put(Integer.valueOf(OItem.r.cv), new OTuple(Integer.valueOf(8), Integer.valueOf(10)));
        bB.put(Integer.valueOf(OItem.p.cv), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bB.put(Integer.valueOf(OItem.aM.cv), new OTuple(Integer.valueOf(24), Integer.valueOf(36)));
        bB.put(Integer.valueOf(OItem.aN.cv), new OTuple(Integer.valueOf(11), Integer.valueOf(13)));
        bB.put(Integer.valueOf(OItem.bI.cv), new OTuple(Integer.valueOf(1), Integer.valueOf(1)));
        bB.put(Integer.valueOf(OItem.bp.cv), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bB.put(Integer.valueOf(OItem.bC.cv), new OTuple(Integer.valueOf(2), Integer.valueOf(3)));
        bB.put(Integer.valueOf(OItem.as.cv), new OTuple(Integer.valueOf(14), Integer.valueOf(18)));
        bB.put(Integer.valueOf(OItem.bk.cv), new OTuple(Integer.valueOf(14), Integer.valueOf(18)));
        bB.put(Integer.valueOf(OItem.bm.cv), new OTuple(Integer.valueOf(14), Integer.valueOf(18)));
        bB.put(Integer.valueOf(OItem.aX.cv), new OTuple(Integer.valueOf(9), Integer.valueOf(13)));
        bB.put(Integer.valueOf(OItem.U.cv), new OTuple(Integer.valueOf(34), Integer.valueOf(48)));
        bB.put(Integer.valueOf(OItem.bj.cv), new OTuple(Integer.valueOf(30), Integer.valueOf(38)));
        bB.put(Integer.valueOf(OItem.bi.cv), new OTuple(Integer.valueOf(30), Integer.valueOf(38)));
        bB.put(Integer.valueOf(OItem.V.cv), new OTuple(Integer.valueOf(18), Integer.valueOf(22)));
        bB.put(Integer.valueOf(OBlock.ag.cF), new OTuple(Integer.valueOf(14), Integer.valueOf(22)));
        bB.put(Integer.valueOf(OItem.bo.cv), new OTuple(Integer.valueOf(36), Integer.valueOf(64)));
        bC.put(Integer.valueOf(OItem.k.cv), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bC.put(Integer.valueOf(OItem.bg.cv), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bC.put(Integer.valueOf(OItem.s.cv), new OTuple(Integer.valueOf(7), Integer.valueOf(11)));
        bC.put(Integer.valueOf(OItem.B.cv), new OTuple(Integer.valueOf(12), Integer.valueOf(14)));
        bC.put(Integer.valueOf(OItem.j.cv), new OTuple(Integer.valueOf(6), Integer.valueOf(8)));
        bC.put(Integer.valueOf(OItem.E.cv), new OTuple(Integer.valueOf(9), Integer.valueOf(12)));
        bC.put(Integer.valueOf(OItem.i.cv), new OTuple(Integer.valueOf(7), Integer.valueOf(9)));
        bC.put(Integer.valueOf(OItem.D.cv), new OTuple(Integer.valueOf(10), Integer.valueOf(12)));
        bC.put(Integer.valueOf(OItem.h.cv), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Integer.valueOf(OItem.C.cv), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Integer.valueOf(OItem.R.cv), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Integer.valueOf(OItem.S.cv), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Integer.valueOf(OItem.ai.cv), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Integer.valueOf(OItem.am.cv), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Integer.valueOf(OItem.af.cv), new OTuple(Integer.valueOf(4), Integer.valueOf(6)));
        bC.put(Integer.valueOf(OItem.aj.cv), new OTuple(Integer.valueOf(7), Integer.valueOf(8)));
        bC.put(Integer.valueOf(OItem.ag.cv), new OTuple(Integer.valueOf(10), Integer.valueOf(14)));
        bC.put(Integer.valueOf(OItem.ak.cv), new OTuple(Integer.valueOf(16), Integer.valueOf(19)));
        bC.put(Integer.valueOf(OItem.ah.cv), new OTuple(Integer.valueOf(8), Integer.valueOf(10)));
        bC.put(Integer.valueOf(OItem.al.cv), new OTuple(Integer.valueOf(11), Integer.valueOf(14)));
        bC.put(Integer.valueOf(OItem.ae.cv), new OTuple(Integer.valueOf(5), Integer.valueOf(7)));
        bC.put(Integer.valueOf(OItem.ab.cv), new OTuple(Integer.valueOf(5), Integer.valueOf(7)));
        bC.put(Integer.valueOf(OItem.ac.cv), new OTuple(Integer.valueOf(11), Integer.valueOf(15)));
        bC.put(Integer.valueOf(OItem.ad.cv), new OTuple(Integer.valueOf(9), Integer.valueOf(11)));
        bC.put(Integer.valueOf(OItem.W.cv), new OTuple(Integer.valueOf(-4), Integer.valueOf(-2)));
        bC.put(Integer.valueOf(OItem.bh.cv), new OTuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bC.put(Integer.valueOf(OItem.l.cv), new OTuple(Integer.valueOf(-8), Integer.valueOf(-4)));
        bC.put(Integer.valueOf(OItem.be.cv), new OTuple(Integer.valueOf(-10), Integer.valueOf(-7)));
        bC.put(Integer.valueOf(OBlock.R.cF), new OTuple(Integer.valueOf(-5), Integer.valueOf(-3)));
        bC.put(Integer.valueOf(OBlock.as.cF), new OTuple(Integer.valueOf(3), Integer.valueOf(4)));
        bC.put(Integer.valueOf(OItem.Y.cv), new OTuple(Integer.valueOf(4), Integer.valueOf(5)));
        bC.put(Integer.valueOf(OItem.aa.cv), new OTuple(Integer.valueOf(2), Integer.valueOf(4)));
        bC.put(Integer.valueOf(OItem.X.cv), new OTuple(Integer.valueOf(2), Integer.valueOf(4)));
        bC.put(Integer.valueOf(OItem.Z.cv), new OTuple(Integer.valueOf(2), Integer.valueOf(4)));
        bC.put(Integer.valueOf(OItem.aC.cv), new OTuple(Integer.valueOf(6), Integer.valueOf(8)));
        bC.put(Integer.valueOf(OItem.bF.cv), new OTuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bC.put(Integer.valueOf(OItem.aE.cv), new OTuple(Integer.valueOf(-4), Integer.valueOf(-1)));
        bC.put(Integer.valueOf(OItem.aS.cv), new OTuple(Integer.valueOf(10), Integer.valueOf(12)));
        bC.put(Integer.valueOf(OItem.aU.cv), new OTuple(Integer.valueOf(10), Integer.valueOf(12)));
        bC.put(Integer.valueOf(OBlock.bi.cF), new OTuple(Integer.valueOf(-3), Integer.valueOf(-1)));
        bC.put(Integer.valueOf(OItem.at.cv), new OTuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bC.put(Integer.valueOf(OItem.bl.cv), new OTuple(Integer.valueOf(-7), Integer.valueOf(-5)));
        bC.put(Integer.valueOf(OItem.bn.cv), new OTuple(Integer.valueOf(-8), Integer.valueOf(-6)));
        bC.put(Integer.valueOf(OItem.bC.cv), new OTuple(Integer.valueOf(7), Integer.valueOf(11)));
        bC.put(Integer.valueOf(OItem.n.cv), new OTuple(Integer.valueOf(-12), Integer.valueOf(-8)));
    }
}
