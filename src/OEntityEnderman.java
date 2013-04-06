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
        this.aH = "/mob/enderman.png";
        this.bI = 0.2F;
        this.a(0.6F, 2.9F);
        this.Y = 1.0F;
    }

    public int aW() {
        //CanaryMod: set max health here, but check for uninitialized value.
        return this.maxHealth == 0 ? 40 : this.maxHealth;
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 0));
        this.ah.a(17, new Byte((byte) 0));
        this.ah.a(18, new Byte((byte) 0));
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("carried", (short) this.o());
        onbttagcompound.a("carriedData", (short) this.p());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.a(onbttagcompound.d("carried"));
        this.s(onbttagcompound.d("carriedData"));
    }

    protected OEntity j() {
        OEntityPlayer oentityplayer = this.q.b(this, 64.0D);

        if (oentityplayer != null) {
            if (this.e(oentityplayer)) {
                if (this.f == 0) {
                    this.q.a((OEntity) oentityplayer, "mob.endermen.stare", 1.0F, 1.0F);
                }

                if (this.f++ == 5) {
                    this.f = 0;
                    this.a(true);
                    return oentityplayer;
                }
            } else {
                this.f = 0;
            }
        }

        return null;
    }

    private boolean e(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bK.b[3];

        if (oitemstack != null && oitemstack.c == OBlock.be.cz) {
            return false;
        } else {
            OVec3 ovec3 = oentityplayer.i(1.0F).a();
            OVec3 ovec31 = this.q.T().a(this.u - oentityplayer.u, this.E.b + (double) (this.P / 2.0F) - (oentityplayer.v + (double) oentityplayer.e()), this.w - oentityplayer.w);
            double d0 = ovec31.b();

            ovec31 = ovec31.a();
            double d1 = ovec3.b(ovec31);

            return d1 > 1.0D - 0.025D / d0 ? oentityplayer.n(this) : false;
        }
    }

    public void c() {
        if (this.F()) {
            this.a(ODamageSource.e, 1);
        }

        this.bI = this.a_ != null ? 6.5F : 0.3F;
        int i;

        if (!this.q.I && this.q.M().b("mobGriefing")) {
            int j;
            int k;
            int l;

            if (this.o() == 0) {
                if (this.ab.nextInt(20) == 0) {
                    i = OMathHelper.c(this.u - 2.0D + this.ab.nextDouble() * 4.0D);
                    j = OMathHelper.c(this.v + this.ab.nextDouble() * 3.0D);
                    k = OMathHelper.c(this.w - 2.0D + this.ab.nextDouble() * 4.0D);
                    l = this.q.a(i, j, k);
                    if (d[l] && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_PICKUP, entity, new Block(entity.getWorld(), l, i, j, k, entity.getWorld().getBlockIdAt(i, j, k)))) {
                        this.a(this.q.a(i, j, k));
                        this.s(this.q.h(i, j, k));
                        this.q.c(i, j, k, 0);
                    }
                }
            } else if (this.ab.nextInt(2000) == 0) {
                i = OMathHelper.c(this.u - 1.0D + this.ab.nextDouble() * 2.0D);
                j = OMathHelper.c(this.v + this.ab.nextDouble() * 2.0D);
                k = OMathHelper.c(this.w - 1.0D + this.ab.nextDouble() * 2.0D);
                l = this.q.a(i, j, k);
                int i1 = this.q.a(i, j - 1, k);

                if (l == 0 && i1 > 0 && OBlock.r[i1].b() && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ENDERMAN_DROP, entity, new Block(entity.getWorld(), i1, i, j, k, entity.getWorld().getBlockIdAt(i, j, k)))) {
                    this.q.f(i, j, k, this.o(), this.p(), 3);
                    this.a(0);
                }
            }
        }

        for (i = 0; i < 2; ++i) {
            this.q.a("portal", this.u + (this.ab.nextDouble() - 0.5D) * (double) this.O, this.v + this.ab.nextDouble() * (double) this.P - 0.25D, this.w + (this.ab.nextDouble() - 0.5D) * (double) this.O, (this.ab.nextDouble() - 0.5D) * 2.0D, -this.ab.nextDouble(), (this.ab.nextDouble() - 0.5D) * 2.0D);
        }

        if (this.q.u() && !this.q.I) {
            float f = this.c(1.0F);

            if (f > 0.5F && this.q.l(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w)) && this.ab.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.a_ = null;
                this.a(false);
                this.m();
            }
        }

        if (this.F() || this.ae()) {
            this.a_ = null;
            this.a(false);
            this.m();
        }

        this.bG = false;
        if (this.a_ != null) {
            this.a(this.a_, 100.0F, 100.0F);
        }

        if (!this.q.I && this.R()) {
            if (this.a_ != null) {
                if (this.a_ instanceof OEntityPlayer && this.e((OEntityPlayer) this.a_)) {
                    this.bD = this.bE = 0.0F;
                    this.bI = 0.0F;
                    if (this.a_.e((OEntity) this) < 16.0D) {
                        this.m();
                    }

                    this.e = 0;
                } else if (this.a_.e((OEntity) this) > 256.0D && this.e++ >= 30 && this.p(this.a_)) {
                    this.e = 0;
                }
            } else {
                this.a(false);
                this.e = 0;
            }
        }

        super.c();
    }

    protected boolean m() {
        double d0 = this.u + (this.ab.nextDouble() - 0.5D) * 64.0D;
        double d1 = this.v + (double) (this.ab.nextInt(64) - 32);
        double d2 = this.w + (this.ab.nextDouble() - 0.5D) * 64.0D;

        return this.j(d0, d1, d2);
    }

    protected boolean p(OEntity oentity) {
        OVec3 ovec3 = this.q.T().a(this.u - oentity.u, this.E.b + (double) (this.P / 2.0F) - oentity.v + (double) oentity.e(), this.w - oentity.w);

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
                if (l != 0 && OBlock.r[l].cO.c()) {
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

    protected String bb() {
        return this.q() ? "mob.endermen.scream" : "mob.endermen.idle";
    }

    protected String bc() {
        return "mob.endermen.hit";
    }

    protected String bd() {
        return "mob.endermen.death";
    }

    protected int be() {
        return OItem.bo.cp;
    }

    protected void a(boolean flag, int i) {
        int j = this.be();

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

    public int o() {
        return this.ah.a(16);
    }

    public void s(int i) {
        this.ah.b(17, Byte.valueOf((byte) (i & 255)));
    }

    public int p() {
        return this.ah.a(17);
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.aq()) {
            return false;
        } else {
            this.a(true);
            if (odamagesource instanceof OEntityDamageSourceIndirect) {
                for (int j = 0; j < 64; ++j) {
                    if (this.m()) {
                        return true;
                    }
                }

                return false;
            } else {
                return super.a(odamagesource, i);
            }
        }
    }

    public boolean q() {
        return this.ah.a(18) > 0;
    }

    public void a(boolean flag) {
        this.ah.b(18, Byte.valueOf((byte) (flag ? 1 : 0)));
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

    @Override
    public Enderman getEntity() {
        return entity;
    }
    // CanaryMod end

    static {
        d[OBlock.y.cz] = true;
        d[OBlock.z.cz] = true;
        d[OBlock.I.cz] = true;
        d[OBlock.J.cz] = true;
        d[OBlock.ah.cz] = true;
        d[OBlock.ai.cz] = true;
        d[OBlock.aj.cz] = true;
        d[OBlock.ak.cz] = true;
        d[OBlock.aq.cz] = true;
        d[OBlock.aZ.cz] = true;
        d[OBlock.ba.cz] = true;
        d[OBlock.be.cz] = true;
        d[OBlock.bv.cz] = true;
        d[OBlock.bC.cz] = true;
    }
}
