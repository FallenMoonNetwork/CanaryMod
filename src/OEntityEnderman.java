import java.util.UUID;

public class OEntityEnderman extends OEntityMob {

    private static final UUID bp = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
    private static final OAttributeModifier bq = (new OAttributeModifier(bp, "Attacking speed boost", 6.199999809265137D, 0)).a(false);
    private static boolean[] br = new boolean[256];
    private int bs;
    private int bt;
    private OEntity bu;
    private boolean bv;

    // CanaryMod Start
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    Enderman entity = new Enderman(this);
    // CanaryMod End

    public OEntityEnderman(OWorld oworld) {
        super(oworld);
        this.a(0.6F, 2.9F);
        this.Y = 1.0F;
    }

    protected void az() {
        super.az();
        this.a(OSharedMonsterAttributes.a).a(40.0D);
        this.a(OSharedMonsterAttributes.d).a(0.30000001192092896D);
        this.a(OSharedMonsterAttributes.e).a(7.0D);
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 0));
        this.ah.a(17, new Byte((byte) 0));
        this.ah.a(18, new Byte((byte) 0));
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("carried", (short) this.bV());
        onbttagcompound.a("carriedData", (short) this.bW());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.a(onbttagcompound.d("carried"));
        this.c(onbttagcompound.d("carriedData"));
    }

    protected OEntity bL() {
        OEntityPlayer oentityplayer = this.q.b(this, 64.0D);

        if (oentityplayer != null) {
            if (this.f(oentityplayer)) {
                this.bv = true;
                if (this.bt == 0) {
                    this.q.a((OEntity) oentityplayer, "mob.endermen.stare", 1.0F, 1.0F);
                }

                if (this.bt++ == 5) {
                    this.bt = 0;
                    this.a(true);
                    return oentityplayer;
                }
            } else {
                this.bt = 0;
            }
        }

        return null;
    }

    private boolean f(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bn.b[3];

        if (oitemstack != null && oitemstack.d == OBlock.bf.cF) {
            return false;
        } else {
            OVec3 ovec3 = oentityplayer.j(1.0F).a();
            OVec3 ovec31 = this.q.V().a(this.u - oentityplayer.u, this.E.b + (double) (this.P / 2.0F) - (oentityplayer.v + (double) oentityplayer.f()), this.w - oentityplayer.w);
            double d0 = ovec31.b();

            ovec31 = ovec31.a();
            double d1 = ovec3.b(ovec31);

            return d1 > 1.0D - 0.025D / d0 ? oentityplayer.o(this) : false;
        }
    }

    public void c() {
        if (this.G()) {
            this.a(ODamageSource.e, 1.0F);
        }

        if (this.bu != this.j) {
            OAttributeInstance oattributeinstance = this.a(OSharedMonsterAttributes.d);

            oattributeinstance.b(bq);
            if (this.j != null) {
                oattributeinstance.a(bq);
            }
        }

        this.bu = this.j;
        int i;

        if (!this.q.I && this.q.O().b("mobGriefing")) {
            int j;
            int k;
            int l;

            if (this.bV() == 0) {
                if (this.ab.nextInt(20) == 0) {
                    i = OMathHelper.c(this.u - 2.0D + this.ab.nextDouble() * 4.0D);
                    j = OMathHelper.c(this.v + this.ab.nextDouble() * 3.0D);
                    k = OMathHelper.c(this.w - 2.0D + this.ab.nextDouble() * 4.0D);
                    l = this.q.a(i, j, k);
                    if (br[l] && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_PICKUP, entity, entity.getWorld().getBlockAt(i, j, k))) {
                        this.a(this.q.a(i, j, k));
                        this.c(this.q.h(i, j, k));
                        this.q.c(i, j, k, 0);
                    }
                }
            } else if (this.ab.nextInt(2000) == 0) {
                i = OMathHelper.c(this.u - 1.0D + this.ab.nextDouble() * 2.0D);
                j = OMathHelper.c(this.v + this.ab.nextDouble() * 2.0D);
                k = OMathHelper.c(this.w - 1.0D + this.ab.nextDouble() * 2.0D);
                l = this.q.a(i, j, k);
                int i1 = this.q.a(i, j - 1, k);

                if (l == 0 && i1 > 0 && OBlock.s[i1].b() && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_DROP, entity, new Block(entity.getWorld(), this.bV(), i, j, k, this.bW()))) {
                    this.q.f(i, j, k, this.bV(), this.bW(), 3);
                    this.a(0);
                }
            }
        }

        for (i = 0; i < 2; ++i) {
            this.q.a("portal", this.u + (this.ab.nextDouble() - 0.5D) * (double) this.O, this.v + this.ab.nextDouble() * (double) this.P - 0.25D, this.w + (this.ab.nextDouble() - 0.5D) * (double) this.O, (this.ab.nextDouble() - 0.5D) * 2.0D, -this.ab.nextDouble(), (this.ab.nextDouble() - 0.5D) * 2.0D);
        }

        if (this.q.v() && !this.q.I) {
            float f = this.d(1.0F);

            if (f > 0.5F && this.q.l(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w)) && this.ab.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.j = null;
                this.a(false);
                this.bv = false;
                this.bT();
            }
        }

        if (this.G() || this.af()) {
            this.j = null;
            this.a(false);
            this.bv = false;
            this.bT();
        }

        if (this.bX() && !this.bv && this.ab.nextInt(100) == 0) {
            this.a(false);
        }

        this.bd = false;
        if (this.j != null) {
            this.a(this.j, 100.0F, 100.0F);
        }

        if (!this.q.I && this.T()) {
            if (this.j != null) {
                if (this.j instanceof OEntityPlayer && this.f((OEntityPlayer) this.j)) {
                    if (this.j.e((OEntity) this) < 16.0D) {
                        this.bT();
                    }

                    this.bs = 0;
                } else if (this.j.e((OEntity) this) > 256.0D && this.bs++ >= 30 && this.c(this.j)) {
                    this.bs = 0;
                }
            } else {
                this.a(false);
                this.bs = 0;
            }
        }

        super.c();
    }

    protected boolean bT() {
        double d0 = this.u + (this.ab.nextDouble() - 0.5D) * 64.0D;
        double d1 = this.v + (double) (this.ab.nextInt(64) - 32);
        double d2 = this.w + (this.ab.nextDouble() - 0.5D) * 64.0D;

        return this.j(d0, d1, d2);
    }

    protected boolean c(OEntity oentity) {
        OVec3 ovec3 = this.q.V().a(this.u - oentity.u, this.E.b + (double) (this.P / 2.0F) - oentity.v + (double) oentity.f(), this.w - oentity.w);

        ovec3 = ovec3.a();
        double d0 = 16.0D;
        double d1 = this.u + (this.ab.nextDouble() - 0.5D) * 8.0D - ovec3.c * d0;
        double d2 = this.v + (double) (this.ab.nextInt(16) - 8) - ovec3.d * d0;
        double d3 = this.w + (this.ab.nextDouble() - 0.5D) * 8.0D - ovec3.e * d0;

        return this.j(d1, d2, d3);
    }

    protected boolean j(double d0, double d1, double d2) {
        double d3 = this.u;
        double d4 = this.v;
        double d5 = this.w;

        this.u = d0;
        this.v = d1;
        this.w = d2;
        boolean flag = false;
        int i = OMathHelper.c(this.u);
        int j = OMathHelper.c(this.v);
        int k = OMathHelper.c(this.w);
        int l;

        if (this.q.f(i, j, k)) {
            boolean flag1 = false;

            while (!flag1 && j > 0) {
                l = this.q.a(i, j - 1, k);
                if (l != 0 && OBlock.s[l].cU.c()) {
                    flag1 = true;
                } else {
                    --this.v;
                    --j;
                }
            }

            if (flag1) {
                this.b(this.u, this.v, this.w);
                if (this.q.a((OEntity) this, this.E).isEmpty() && !this.q.d(this.E)) {
                    flag = true;
                }
            }
        }

        if (!flag) {
            this.b(d3, d4, d5);
            return false;
        } else {
            short short1 = 128;

            for (l = 0; l < short1; ++l) {
                double d6 = (double) l / ((double) short1 - 1.0D);
                float f = (this.ab.nextFloat() - 0.5F) * 0.2F;
                float f1 = (this.ab.nextFloat() - 0.5F) * 0.2F;
                float f2 = (this.ab.nextFloat() - 0.5F) * 0.2F;
                double d7 = d3 + (this.u - d3) * d6 + (this.ab.nextDouble() - 0.5D) * (double) this.O * 2.0D;
                double d8 = d4 + (this.v - d4) * d6 + this.ab.nextDouble() * (double) this.P;
                double d9 = d5 + (this.w - d5) * d6 + (this.ab.nextDouble() - 0.5D) * (double) this.O * 2.0D;

                this.q.a("portal", d7, d8, d9, (double) f, (double) f1, (double) f2);
            }

            this.q.a(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            this.a("mob.endermen.portal", 1.0F, 1.0F);
            return true;
        }
    }

    protected String r() {
        return this.bX() ? "mob.endermen.scream" : "mob.endermen.idle";
    }

    protected String aO() {
        return "mob.endermen.hit";
    }

    protected String aP() {
        return "mob.endermen.death";
    }

    protected int s() {
        return OItem.bp.cv;
    }

    protected void b(boolean flag, int i) {
        int j = this.s();

        if (j > 0) {
            int k = this.ab.nextInt(2 + i);

            for (int l = 0; l < k; ++l) {
                this.b(j, 1);
            }
        }
    }

    public void a(int i) {
        this.ah.b(16, Byte.valueOf((byte) (i & 255)));
    }

    public int bV() {
        return this.ah.a(16);
    }

    public void c(int i) {
        this.ah.b(17, Byte.valueOf((byte) (i & 255)));
    }

    public int bW() {
        return this.ah.a(17);
    }

    public boolean a(ODamageSource odamagesource, float f) {
        if (this.ar()) {
            return false;
        } else {
            this.a(true);
            if (odamagesource instanceof OEntityDamageSource && odamagesource.i() instanceof OEntityPlayer) {
                this.bv = true;
            }

            if (odamagesource instanceof OEntityDamageSourceIndirect) {
                this.bv = false;

                for (int i = 0; i < 64; ++i) {
                    if (this.bT()) {
                        return true;
                    }
                }

                return false;
            } else {
                return super.a(odamagesource, f);
            }
        }
    }

    public boolean bX() {
        return this.ah.a(18) > 0;
    }

    public void a(boolean flag) {
        this.ah.b(18, Byte.valueOf((byte) (flag ? 1 : 0)));
    }

    // CanaryMod start
    public static boolean canHoldItem(int i) {
        return br[i];
    }

    public static void setHoldable(int i, boolean flag) {
        br[i] = flag;
    }

    public static boolean getHoldable(int i) {
        return br[i];
    }

    @Override
    public Enderman getEntity() {
        return entity;
    }
    // CanaryMod end

    static {
        br[OBlock.z.cF] = true;
        br[OBlock.A.cF] = true;
        br[OBlock.J.cF] = true;
        br[OBlock.K.cF] = true;
        br[OBlock.ai.cF] = true;
        br[OBlock.aj.cF] = true;
        br[OBlock.ak.cF] = true;
        br[OBlock.al.cF] = true;
        br[OBlock.ar.cF] = true;
        br[OBlock.ba.cF] = true;
        br[OBlock.bb.cF] = true;
        br[OBlock.bf.cF] = true;
        br[OBlock.bw.cF] = true;
        br[OBlock.bD.cF] = true;
    }
}
