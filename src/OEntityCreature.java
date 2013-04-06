public abstract class OEntityCreature extends OEntityLiving {

    private OPathEntity d;
    protected OEntity a_;
    protected boolean b = false;
    protected int c = 0;

    public OEntityCreature(OWorld oworld) {
        super(oworld);
    }

    protected boolean h() {
        return false;
    }

    protected void bq() {
        this.q.C.a("ai");
        if (this.c > 0) {
            --this.c;
        }

        this.b = this.h();
        float f = 16.0F;

        if (this.a_ == null) {
            OEntity target = this.j(); // CanaryMod: invoke once
            if (target == null || !(Boolean) manager.callHook(PluginLoader.Hook.MOB_TARGET, target.getEntity(), this.getEntity())) { // CanaryMod: call hook
                this.a_ = target;
            } //
            if (this.a_ != null) {
                this.d = this.q.a(this, this.a_, f, true, false, false, true);
            }
        } else if (this.a_.R()) {
            float f1 = this.a_.d((OEntity) this);

            if (this.n(this.a_)) {
                this.a(this.a_, f1);
            }
        } else {
            this.a_ = null;
        }

        this.q.C.b();
        if (!this.b && this.a_ != null && (this.d == null || this.ab.nextInt(20) == 0)) {
            this.d = this.q.a(this, this.a_, f, true, false, false, true);
        } else if (!this.b && (this.d == null && this.ab.nextInt(180) == 0 || this.ab.nextInt(120) == 0 || this.c > 0) && this.bC < 100) {
            this.i();
        }

        int i = OMathHelper.c(this.E.b + 0.5D);
        boolean flag = this.G();
        boolean flag1 = this.I();

        this.B = 0.0F;
        if (this.d != null && this.ab.nextInt(100) != 0) {
            this.q.C.a("followpath");
            OVec3 ovec3 = this.d.a((OEntity) this);
            double d0 = (double) (this.O * 2.0F);

            while (ovec3 != null && ovec3.d(this.u, ovec3.d, this.w) < d0 * d0) {
                this.d.a();
                if (this.d.b()) {
                    ovec3 = null;
                    this.d = null;
                } else {
                    ovec3 = this.d.a((OEntity) this);
                }
            }

            this.bG = false;
            if (ovec3 != null) {
                double d1 = ovec3.c - this.u;
                double d2 = ovec3.e - this.w;
                double d3 = ovec3.d - (double) i;
                float f2 = (float) (Math.atan2(d2, d1) * 180.0D / 3.1415927410125732D) - 90.0F;
                float f3 = OMathHelper.g(f2 - this.A);

                this.bE = this.bI;
                if (f3 > 30.0F) {
                    f3 = 30.0F;
                }

                if (f3 < -30.0F) {
                    f3 = -30.0F;
                }

                this.A += f3;
                if (this.b && this.a_ != null) {
                    double d4 = this.a_.u - this.u;
                    double d5 = this.a_.w - this.w;
                    float f4 = this.A;

                    this.A = (float) (Math.atan2(d5, d4) * 180.0D / 3.1415927410125732D) - 90.0F;
                    f3 = (f4 - this.A + 90.0F) * 3.1415927F / 180.0F;
                    this.bD = -OMathHelper.a(f3) * this.bE * 1.0F;
                    this.bE = OMathHelper.b(f3) * this.bE * 1.0F;
                }

                if (d3 > 0.0D) {
                    this.bG = true;
                }
            }

            if (this.a_ != null) {
                this.a(this.a_, 30.0F, 30.0F);
            }

            if (this.G && !this.k()) {
                this.bG = true;
            }

            if (this.ab.nextFloat() < 0.8F && (flag || flag1)) {
                this.bG = true;
            }

            this.q.C.b();
        } else {
            super.bq();
            this.d = null;
        }
    }

    protected void i() {
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
            this.d = this.q.a(this, i, j, k, 10.0F, true, false, false, true);
        }

        this.q.C.b();
    }

    protected void a(OEntity oentity, float f) {}

    public float a(int i, int j, int k) {
        return 0.0F;
    }

    protected OEntity j() {
        return null;
    }

    public boolean bv() {
        int i = OMathHelper.c(this.u);
        int j = OMathHelper.c(this.E.b);
        int k = OMathHelper.c(this.w);

        return super.bv() && this.a(i, j, k) >= 0.0F;
    }

    public boolean k() {
        return this.d != null;
    }

    public void a(OPathEntity opathentity) {
        this.d = opathentity;
    }

    public OEntity l() {
        return this.a_;
    }

    public void b(OEntity oentity) {
        this.a_ = oentity;
    }

    public float bE() {
        float f = super.bE();

        if (this.c > 0 && !this.bh()) {
            f *= 2.0F;
        }

        return f;
    }
}
