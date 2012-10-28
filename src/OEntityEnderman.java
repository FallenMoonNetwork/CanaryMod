
public class OEntityEnderman extends OEntityMob {

    private static boolean[] d = new boolean[256];
    private int e = 0;
    private int f = 0;

    // CanaryMod Start
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    Enderman entity = new Enderman(this);
    // CanaryMod End

    public OEntityEnderman(OWorld oworld) {
        super(oworld);
        this.aF = "/mob/enderman.png";
        this.bI = 0.2F;
        this.a(0.6F, 2.9F);
        this.X = 1.0F;
    }

    public int aS() {
        return 40;
    }

    protected void a() {
        super.a();
        this.ag.a(16, new Byte((byte) 0));
        this.ag.a(17, new Byte((byte) 0));
        this.ag.a(18, new Byte((byte) 0));
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("carried", (short) this.o());
        onbttagcompound.a("carriedData", (short) this.p());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.a(onbttagcompound.d("carried"));
        this.r(onbttagcompound.d("carriedData"));
    }

    protected OEntity j() {
        OEntityPlayer oentityplayer = this.p.b(this, 64.0D);

        if (oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentityplayer.entity.getPlayer(), entity)) {
            if (this.d(oentityplayer)) {
                if (this.f == 0) {
                    this.p.a(oentityplayer, "mob.endermen.stare", 1.0F, 1.0F);
                }

                if (this.f++ == 5) {
                    this.f = 0;
                    this.f(true);
                    return oentityplayer;
                }
            } else {
                this.f = 0;
            }
        }

        return null;
    }

    private boolean d(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bK.b[3];

        if (oitemstack != null && oitemstack.c == OBlock.bd.cm) {
            return false;
        } else {
            OVec3 ovec3 = oentityplayer.i(1.0F).a();
            OVec3 ovec31 = this.p.R().a(this.t - oentityplayer.t, this.D.b + (double) (this.O / 2.0F) - (oentityplayer.u + (double) oentityplayer.e()), this.v - oentityplayer.v);
            double d0 = ovec31.b();

            ovec31 = ovec31.a();
            double d1 = ovec3.b(ovec31);

            return d1 > 1.0D - 0.025D / d0 ? oentityplayer.m(this) : false;
        }
    }

    public void c() {
        if (this.G()) {
            this.a(ODamageSource.e, 1);
        }

        this.bI = this.a_ != null ? 6.5F : 0.3F;
        int i;

        if (!this.p.J && this.p.K().b("mobGriefing")) {
            int j;
            int k;
            int l;

            if (this.o() == 0) {
                if (this.aa.nextInt(20) == 0) {
                    i = OMathHelper.c(this.t - 2.0D + this.aa.nextDouble() * 4.0D);
                    j = OMathHelper.c(this.u + this.aa.nextDouble() * 3.0D);
                    k = OMathHelper.c(this.v - 2.0D + this.aa.nextDouble() * 4.0D);
                    l = this.p.a(i, j, k);
                    if (d[l] && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_PICKUP, entity, new Block(entity.getWorld(), l, i, j, k, entity.getWorld().getBlockIdAt(i, j, k)))) {
                        this.a(this.p.a(i, j, k));
                        this.r(this.p.g(i, j, k));
                        this.p.e(i, j, k, 0);
                    }
                }
            } else if (this.aa.nextInt(2000) == 0) {
                i = OMathHelper.c(this.t - 1.0D + this.aa.nextDouble() * 2.0D);
                j = OMathHelper.c(this.u + this.aa.nextDouble() * 2.0D);
                k = OMathHelper.c(this.v - 1.0D + this.aa.nextDouble() * 2.0D);
                l = this.p.a(i, j, k);
                int i1 = this.p.a(i, j - 1, k);

                if (l == 0 && i1 > 0 && OBlock.p[i1].b() && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_DROP, entity, new Block(entity.getWorld(), i1, i, j, k, entity.getWorld().getBlockIdAt(i, j, k)))) {
                    this.p.d(i, j, k, this.o(), this.p());
                    this.a(0);
                }
            }
        }

        for (i = 0; i < 2; ++i) {
            this.p.a("portal", this.t + (this.aa.nextDouble() - 0.5D) * (double) this.N, this.u + this.aa.nextDouble() * (double) this.O - 0.25D, this.v + (this.aa.nextDouble() - 0.5D) * (double) this.N, (this.aa.nextDouble() - 0.5D) * 2.0D, -this.aa.nextDouble(), (this.aa.nextDouble() - 0.5D) * 2.0D);
        }

        if (this.p.t() && !this.p.J) {
            float f = this.c(1.0F);

            if (f > 0.5F && this.p.j(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v)) && this.aa.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.a_ = null;
                this.f(false);
                this.m();
            }
        }

        if (this.G()) {
            this.a_ = null;
            this.f(false);
            this.m();
        }

        this.bG = false;
        if (this.a_ != null) {
            this.a(this.a_, 100.0F, 100.0F);
        }

        if (!this.p.J && this.S()) {
            if (this.a_ != null) {
                if (this.a_ instanceof OEntityPlayer && this.d((OEntityPlayer) this.a_)) {
                    this.bD = this.bE = 0.0F;
                    this.bI = 0.0F;
                    if (this.a_.e((OEntity) this) < 16.0D) {
                        this.m();
                    }

                    this.e = 0;
                } else if (this.a_.e((OEntity) this) > 256.0D && this.e++ >= 30 && this.o(this.a_)) {
                    this.e = 0;
                }
            } else {
                this.f(false);
                this.e = 0;
            }
        }

        super.c();
    }

    protected boolean m() {
        double d0 = this.t + (this.aa.nextDouble() - 0.5D) * 64.0D;
        double d1 = this.u + (double) (this.aa.nextInt(64) - 32);
        double d2 = this.v + (this.aa.nextDouble() - 0.5D) * 64.0D;

        return this.j(d0, d1, d2);
    }

    protected boolean o(OEntity oentity) {
        OVec3 ovec3 = this.p.R().a(this.t - oentity.t, this.D.b + (double) (this.O / 2.0F) - oentity.u + (double) oentity.e(), this.v - oentity.v);

        ovec3 = ovec3.a();
        double d0 = 16.0D;
        double d1 = this.t + (this.aa.nextDouble() - 0.5D) * 8.0D - ovec3.c * d0;
        double d2 = this.u + (double) (this.aa.nextInt(16) - 8) - ovec3.d * d0;
        double d3 = this.v + (this.aa.nextDouble() - 0.5D) * 8.0D - ovec3.e * d0;

        return this.j(d1, d2, d3);
    }

    protected boolean j(double d0, double d1, double d2) {
        double d3 = this.t;
        double d4 = this.u;
        double d5 = this.v;

        this.t = d0;
        this.u = d1;
        this.v = d2;
        boolean flag = false;
        int i = OMathHelper.c(this.t);
        int j = OMathHelper.c(this.u);
        int k = OMathHelper.c(this.v);
        int l;

        if (this.p.e(i, j, k)) {
            boolean flag1 = false;

            while (!flag1 && j > 0) {
                l = this.p.a(i, j - 1, k);
                if (l != 0 && OBlock.p[l].cB.c()) {
                    flag1 = true;
                } else {
                    --this.u;
                    --j;
                }
            }

            if (flag1) {
                this.b(this.t, this.u, this.v);
                if (this.p.a((OEntity) this, this.D).isEmpty() && !this.p.d(this.D)) {
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
                float f = (this.aa.nextFloat() - 0.5F) * 0.2F;
                float f1 = (this.aa.nextFloat() - 0.5F) * 0.2F;
                float f2 = (this.aa.nextFloat() - 0.5F) * 0.2F;
                double d7 = d3 + (this.t - d3) * d6 + (this.aa.nextDouble() - 0.5D) * (double) this.N * 2.0D;
                double d8 = d4 + (this.u - d4) * d6 + this.aa.nextDouble() * (double) this.O;
                double d9 = d5 + (this.v - d5) * d6 + (this.aa.nextDouble() - 0.5D) * (double) this.N * 2.0D;

                this.p.a("portal", d7, d8, d9, (double) f, (double) f1, (double) f2);
            }

            this.p.a(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
            this.p.a(this, "mob.endermen.portal", 1.0F, 1.0F);
            return true;
        }
    }

    protected String aW() {
        return this.q() ? "mob.endermen.scream" : "mob.endermen.idle";
    }

    protected String aX() {
        return "mob.endermen.hit";
    }

    protected String aY() {
        return "mob.endermen.death";
    }

    protected int aZ() {
        return OItem.bn.cf;
    }

    protected void a(boolean flag, int i) {
        int j = this.aZ();

        if (j > 0) {
            int k = this.aa.nextInt(2 + i);

            for (int l = 0; l < k; ++l) {
                this.b(j, 1);
            }
        }
    }

    public void a(int i) {
        this.ag.b(16, Byte.valueOf((byte) (i & 255)));
    }

    public int o() {
        return this.ag.a(16);
    }

    public void r(int i) {
        this.ag.b(17, Byte.valueOf((byte) (i & 255)));
    }

    public int p() {
        return this.ag.a(17);
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (odamagesource instanceof OEntityDamageSourceIndirect) {
            for (int j = 0; j < 64; ++j) {
                if (this.m()) {
                    return true;
                }
            }

            return false;
        } else {
            if (odamagesource.g() instanceof OEntityPlayer) {
                this.f(true);
            }

            return super.a(odamagesource, i);
        }
    }

    public boolean q() {
        return this.ag.a(18) > 0;
    }

    public void f(boolean flag) {
        this.ag.b(18, Byte.valueOf((byte) (flag ? 1 : 0)));
    }

    public int c(OEntity oentity) {
        return 7;
    }

    // CanaryMod start
    public static boolean canHoldItem(int i) {
        return d[i];
    }

    public static void setHoldable(int i, boolean flag) {
        d[i] = flag;
    }

    public static boolean getHoldable(int i) {
        return d[i];
    }
    // CanaryMod end

    static {
        d[OBlock.x.cm] = true;
        d[OBlock.y.cm] = true;
        d[OBlock.H.cm] = true;
        d[OBlock.I.cm] = true;
        d[OBlock.ag.cm] = true;
        d[OBlock.ah.cm] = true;
        d[OBlock.ai.cm] = true;
        d[OBlock.aj.cm] = true;
        d[OBlock.ap.cm] = true;
        d[OBlock.aY.cm] = true;
        d[OBlock.aZ.cm] = true;
        d[OBlock.bd.cm] = true;
        d[OBlock.bu.cm] = true;
        d[OBlock.bB.cm] = true;
    }
}
