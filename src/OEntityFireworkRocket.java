public class OEntityFireworkRocket extends OEntity {

    protected int a; // CanaryMod: private -> protected
    protected int b; // CanaryMod: private -> protected

    public OEntityFireworkRocket(OWorld oworld) {
        super(oworld);
        this.a(0.25F, 0.25F);
    }

    protected void a() {
        this.ah.a(8, 5);
    }

    public OEntityFireworkRocket(OWorld oworld, double d0, double d1, double d2, OItemStack oitemstack) {
        super(oworld);
        this.a = 0;
        this.a(0.25F, 0.25F);
        this.b(d0, d1, d2);
        this.N = 0.0F;
        int i = 1;

        if (oitemstack != null && oitemstack.p()) {
            this.ah.b(8, oitemstack);
            ONBTTagCompound onbttagcompound = oitemstack.q();
            ONBTTagCompound onbttagcompound1 = onbttagcompound.l("Fireworks");

            if (onbttagcompound1 != null) {
                i += onbttagcompound1.c("Flight");
            }
        }

        this.x = this.ab.nextGaussian() * 0.001D;
        this.z = this.ab.nextGaussian() * 0.001D;
        this.y = 0.05D;
        this.b = 10 * i + this.ab.nextInt(6) + this.ab.nextInt(7);
    }

    public void l_() {
        this.U = this.u;
        this.V = this.v;
        this.W = this.w;
        super.l_();
        this.x *= 1.15D;
        this.z *= 1.15D;
        this.y += 0.04D;
        this.d(this.x, this.y, this.z);
        float f = OMathHelper.a(this.x * this.x + this.z * this.z);

        this.A = (float) (Math.atan2(this.x, this.z) * 180.0D / 3.1415927410125732D);

        for (this.B = (float) (Math.atan2(this.y, (double) f) * 180.0D / 3.1415927410125732D); this.B - this.D < -180.0F; this.D -= 360.0F) {
            ;
        }

        while (this.B - this.D >= 180.0F) {
            this.D += 360.0F;
        }

        while (this.A - this.C < -180.0F) {
            this.C -= 360.0F;
        }

        while (this.A - this.C >= 180.0F) {
            this.C += 360.0F;
        }

        this.B = this.D + (this.B - this.D) * 0.2F;
        this.A = this.C + (this.A - this.C) * 0.2F;
        if (this.a == 0) {
            this.q.a((OEntity) this, "fireworks.launch", 3.0F, 1.0F);
        }

        ++this.a;
        if (this.q.I && this.a % 2 < 2) {
            this.q.a("fireworksSpark", this.u, this.v - 0.3D, this.w, this.ab.nextGaussian() * 0.05D, -this.y * 0.5D, this.ab.nextGaussian() * 0.05D);
        }

        if (!this.q.I && this.a > this.b && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.FIREWORK_EXPLODE, new Firework(this))) { // CanaryMod: call onFireworkExplode when the rocket explodes
            this.q.a((OEntity) this, (byte) 17);
            this.w();
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Life", this.a);
        onbttagcompound.a("LifeTime", this.b);
        OItemStack oitemstack = this.ah.f(8);

        if (oitemstack != null) {
            ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

            oitemstack.b(onbttagcompound1);
            onbttagcompound.a("FireworksItem", onbttagcompound1);
        }
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.a = onbttagcompound.e("Life");
        this.b = onbttagcompound.e("LifeTime");
        ONBTTagCompound onbttagcompound1 = onbttagcompound.l("FireworksItem");

        if (onbttagcompound1 != null) {
            OItemStack oitemstack = OItemStack.a(onbttagcompound1);

            if (oitemstack != null) {
                this.ah.b(8, oitemstack);
            }
        }
    }

    public float c(float f) {
        return super.c(f);
    }

    public boolean ap() {
        return false;
    }
}
