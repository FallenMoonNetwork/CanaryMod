import java.util.UUID;

public abstract class OEntityCreature extends OEntityLiving {

    public static final UUID h = UUID.fromString("E199AD21-BA8A-4C53-8D13-6182D5C69D3A");
    public static final OAttributeModifier i = (new OAttributeModifier(h, "Fleeing speed bonus", 2.0D, 2)).a(false);
    private OPathEntity bp;
    protected OEntity j;
    protected boolean bn;
    protected int bo;
    private OChunkCoordinates bq = new OChunkCoordinates(0, 0, 0);
    private float br = -1.0F;
    private OEntityAIBase bs = new OEntityAIMoveTwardsRestriction(this, 1.0D);
    private boolean bt;

    public OEntityCreature(OWorld oworld) {
        super(oworld);
    }

    protected boolean bF() {
        return false;
    }

    protected void bh() {
        this.q.C.a("ai");
        if (this.bo > 0 && --this.bo == 0) {
            OAttributeInstance oattributeinstance = this.a(OSharedMonsterAttributes.d);

            oattributeinstance.b(i);
        }

        this.bn = this.bF();
        float f11 = 16.0F;

        if (this.j == null) {
            OEntity target = this.bH(); // CanaryMod: invoke once
            if (target == null || !(Boolean) manager.callHook(PluginLoader.Hook.MOB_TARGET, target.getEntity(), this.getEntity())) { // CanaryMod: call hook
                this.j = target;
            } //
            if (this.j != null) {
                this.bp = this.q.a(this, this.j, f11, true, false, false, true);
            }
        } else if (this.j.R()) {
            float f1 = this.j.d((OEntity) this);

            if (this.o(this.j)) {
                this.a(this.j, f1);
            }
        } else {
            this.j = null;
        }

        this.q.C.b();
        if (!this.bn && this.j != null && (this.bp == null || this.ab.nextInt(20) == 0)) {
            this.bp = this.q.a(this, this.j, f11, true, false, false, true);
        } else if (!this.bn && (this.bp == null && this.ab.nextInt(180) == 0 || this.ab.nextInt(120) == 0 || this.bo > 0) && this.aV < 100) {
            this.bG();
        }

        int i = OMathHelper.c(this.E.b + 0.5D);
        boolean flag = this.G();
        boolean flag1 = this.I();

        this.B = 0.0F;
        if (this.bp != null && this.ab.nextInt(100) != 0) {
            this.q.C.a("followpath");
            OVec3 ovec3 = this.bp.a((OEntity) this);
            double d0 = (double) (this.O * 2.0F);

            while (ovec3 != null && ovec3.d(this.u, ovec3.d, this.w) < d0 * d0) {
                this.bp.a();
                if (this.bp.b()) {
                    ovec3 = null;
                    this.bp = null;
                } else {
                    ovec3 = this.bp.a((OEntity) this);
                }
            }

            this.bd = false;
            if (ovec3 != null) {
                double d1 = ovec3.c - this.u;
                double d2 = ovec3.e - this.w;
                double d3 = ovec3.d - (double) i;
                float f2 = (float) (Math.atan2(d2, d1) * 180.0D / 3.1415927410125732D) - 90.0F;
                float f3 = OMathHelper.g(f2 - this.A);

                this.bf = (float) this.a(OSharedMonsterAttributes.d).e();
                if (f3 > 30.0F) {
                    f3 = 30.0F;
                }

                if (f3 < -30.0F) {
                    f3 = -30.0F;
                }

                this.A += f3;
                if (this.bn && this.j != null) {
                    double d4 = this.j.u - this.u;
                    double d5 = this.j.w - this.w;
                    float f4 = this.A;

                    this.A = (float) (Math.atan2(d5, d4) * 180.0D / 3.1415927410125732D) - 90.0F;
                    f3 = (f4 - this.A + 90.0F) * 3.1415927F / 180.0F;
                    this.be = -OMathHelper.a(f3) * this.bf * 1.0F;
                    this.bf = OMathHelper.b(f3) * this.bf * 1.0F;
                }

                if (d3 > 0.0D) {
                    this.bd = true;
                }
            }

            if (this.j != null) {
                this.a(this.j, 30.0F, 30.0F);
            }

            if (this.G && !this.bI()) {
                this.bd = true;
            }

            if (this.ab.nextFloat() < 0.8F && (flag || flag1)) {
                this.bd = true;
            }

            this.q.C.b();
        } else {
            super.bh();
            this.bp = null;
        }
    }

    protected void bG() {
        this.q.C.a("stroll");
        boolean flag = false;
        int i = -1;
        int j = -1;
        int k = -1;
        float f = -99999.0F;

        for (int l = 0; l < 10; ++l) {
            int i1 = OMathHelper.c(this.u + (double) this.ab.nextInt(13) - 6.0D);
            int j1 = OMathHelper.c(this.v + (double) this.ab.nextInt(7) - 3.0D);
            int k1 = OMathHelper.c(this.w + (double) this.ab.nextInt(13) - 6.0D);
            float f1 = this.a(i1, j1, k1);

            if (f1 > f) {
                f = f1;
                i = i1;
                j = j1;
                k = k1;
                flag = true;
            }
        }

        if (flag) {
            this.bp = this.q.a(this, i, j, k, 10.0F, true, false, false, true);
        }

        this.q.C.b();
    }

    protected void a(OEntity oentity, float f) {}

    public float a(int i, int j, int k) {
        return 0.0F;
    }

    protected OEntity bH() {
        return null;
    }

    public boolean bo() {
        int i = OMathHelper.c(this.u);
        int j = OMathHelper.c(this.E.b);
        int k = OMathHelper.c(this.w);

        return super.bo() && this.a(i, j, k) >= 0.0F;
    }

    public boolean bI() {
        return this.bp != null;
    }

    public void a(OPathEntity opathentity) {
        this.bp = opathentity;
    }

    public OEntity bJ() {
        return this.j;
    }

    public void b(OEntity oentity) {
        this.j = oentity;
    }

    public boolean bK() {
        return this.b(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w));
    }

    public boolean b(int i, int j, int k) {
        return this.br == -1.0F ? true : this.bq.e(i, j, k) < this.br * this.br;
    }

    public void b(int i, int j, int k, int l) {
        this.bq.b(i, j, k);
        this.br = (float) l;
    }

    public OChunkCoordinates bL() {
        return this.bq;
    }

    public float bM() {
        return this.br;
    }

    public void bN() {
        this.br = -1.0F;
    }

    public boolean bO() {
        return this.br != -1.0F;
    }

    protected void bB() {
        super.bB();
        if (this.bD() && this.bE() != null && this.bE().q == this.q) {
            OEntity oentity = this.bE();

            this.b((int) oentity.u, (int) oentity.v, (int) oentity.w, 5);
            float f = this.d(oentity);

            if (this instanceof OEntityTameable && ((OEntityTameable) this).bQ()) {
                if (f > 10.0F) {
                    this.i(true);
                }

                return;
            }

            if (!this.bt) {
                this.c.a(2, this.bs);
                this.k().a(false);
                this.bt = true;
            }

            if (f > 4.0F) {
                this.k().a(oentity, 1.0D);
            }

            if (f > 6.0F) {
                double d0 = (oentity.u - this.u) / (double) f;
                double d1 = (oentity.v - this.v) / (double) f;
                double d2 = (oentity.w - this.w) / (double) f;

                this.x += d0 * Math.abs(d0) * 0.4D;
                this.y += d1 * Math.abs(d1) * 0.4D;
                this.z += d2 * Math.abs(d2) * 0.4D;
            }

            if (f > 10.0F) {
                this.i(true);
            }
        } else if (!this.bD() && this.bt) {
            this.bt = false;
            this.c.a(this.bs);
            this.k().a(true);
            this.bN();
        }
    }
}
