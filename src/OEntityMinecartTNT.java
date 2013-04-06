public class OEntityMinecartTNT extends OEntityMinecart {

    int a = -1; // CanaryMod: private -> package-private

    private TNTMinecart cart = new TNTMinecart(this); // CanaryMod: reference to wrapper

    public OEntityMinecartTNT(OWorld oworld) {
        super(oworld);
    }

    public OEntityMinecartTNT(OWorld oworld, double d0, double d1, double d2) {
        super(oworld, d0, d1, d2);
    }

    public int l() {
        return 3;
    }

    public OBlock n() {
        return OBlock.aq;
    }

    public void l_() {
        super.l_();
        if (this.a > 0) {
            --this.a;
            this.q.a("smoke", this.u, this.v + 0.5D, this.w, 0.0D, 0.0D, 0.0D);
        } else if (this.a == 0) {
            this.c(this.x * this.x + this.z * this.z);
        }

        if (this.G) {
            double d0 = this.x * this.x + this.z * this.z;

            if (d0 >= 0.009999999776482582D) {
                this.c(d0);
            }
        }
    }

    public void a(ODamageSource odamagesource) {
        super.a(odamagesource);
        double d0 = this.x * this.x + this.z * this.z;

        if (!odamagesource.c()) {
            this.a(new OItemStack(OBlock.aq, 1), 0.0F);
        }

        if (odamagesource.m() || odamagesource.c() || d0 >= 0.009999999776482582D) {
            this.c(d0);
        }
    }

    protected void c(double d0) {
        if (!this.q.I) {
            double d1 = Math.sqrt(d0);

            if (d1 > 5.0D) {
                d1 = 5.0D;
            }

            this.q.a(this, this.u, this.v, this.w, (float) (4.0D + this.ab.nextDouble() * 1.5D * d1), true);
            this.w();
        }
    }

    protected void a(float f) {
        if (f >= 3.0F) {
            float f1 = f / 10.0F;

            this.c((double) (f1 * f1));
        }

        super.a(f);
    }

    public void a(int i, int j, int k, boolean flag) {
        if (flag && this.a < 0) {
            this.d();
        }
    }

    public void d() {
        this.a = 80;
        if (!this.q.I) {
            this.q.a((OEntity) this, (byte) 10);
            this.q.a((OEntity) this, "random.fuse", 1.0F, 1.0F);
        }
    }

    public boolean ay() {
        return this.a > -1;
    }

    public float a(OExplosion oexplosion, OWorld oworld, int i, int j, int k, OBlock oblock) {
        return this.ay() && (OBlockRailBase.d_(oblock.cz) || OBlockRailBase.d_(oworld, i, j + 1, k)) ? 0.0F : super.a(oexplosion, oworld, i, j, k, oblock);
    }

    public boolean a(OExplosion oexplosion, OWorld oworld, int i, int j, int k, int l, float f) {
        return this.ay() && (OBlockRailBase.d_(l) || OBlockRailBase.d_(oworld, i, j + 1, k)) ? false : super.a(oexplosion, oworld, i, j, k, l, f);
    }

    protected void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        if (onbttagcompound.b("TNTFuse")) {
            this.a = onbttagcompound.e("TNTFuse");
        }
    }

    protected void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("TNTFuse", this.a);
    }

    @Override
    public TNTMinecart getEntity() {
        return cart;
    }
}
