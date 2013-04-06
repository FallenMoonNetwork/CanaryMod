public class OEntityMinecartFurnace extends OEntityMinecart {

    int c = 0; // CanaryMod: private -> package-private
    public double a;
    public double b;

    public OEntityMinecartFurnace(OWorld oworld) {
        super(oworld);
    }

    public OEntityMinecartFurnace(OWorld oworld, double d0, double d1, double d2) {
        super(oworld, d0, d1, d2);
    }

    public int l() {
        return 2;
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 0));
    }

    public void l_() {
        super.l_();
        if (this.c > 0) {
            --this.c;
        }

        if (this.c <= 0) {
            this.a = this.b = 0.0D;
        }

        this.f(this.c > 0);
        if (this.d() && this.ab.nextInt(4) == 0) {
            this.q.a("largesmoke", this.u, this.v + 0.8D, this.w, 0.0D, 0.0D, 0.0D);
        }
    }

    public void a(ODamageSource odamagesource) {
        super.a(odamagesource);
        if (!odamagesource.c()) {
            this.a(new OItemStack(OBlock.aF, 1), 0.0F);
        }
    }

    protected void a(int i, int j, int k, double d0, double d1, int l, int i1) {
        super.a(i, j, k, d0, d1, l, i1);
        double d2 = this.a * this.a + this.b * this.b;

        if (d2 > 1.0E-4D && this.x * this.x + this.z * this.z > 0.001D) {
            d2 = (double) OMathHelper.a(d2);
            this.a /= d2;
            this.b /= d2;
            if (this.a * this.x + this.b * this.z < 0.0D) {
                this.a = 0.0D;
                this.b = 0.0D;
            } else {
                this.a = this.x;
                this.b = this.z;
            }
        }
    }

    protected void h() {
        double d0 = this.a * this.a + this.b * this.b;

        if (d0 > 1.0E-4D) {
            d0 = (double) OMathHelper.a(d0);
            this.a /= d0;
            this.b /= d0;
            double d1 = 0.05D;

            this.x *= 0.800000011920929D;
            this.y *= 0.0D;
            this.z *= 0.800000011920929D;
            this.x += this.a * d1;
            this.z += this.b * d1;
        } else {
            this.x *= 0.9800000190734863D;
            this.y *= 0.0D;
            this.z *= 0.9800000190734863D;
        }

        super.h();
    }

    public boolean a_(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bK.h();

        if (oitemstack != null && oitemstack.c == OItem.n.cp) {
            if (--oitemstack.a == 0) {
                oentityplayer.bK.a(oentityplayer.bK.c, (OItemStack) null);
            }

            this.c += 3600;
        }

        this.a = this.u - oentityplayer.u;
        this.b = this.w - oentityplayer.w;
        return true;
    }

    protected void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("PushX", this.a);
        onbttagcompound.a("PushZ", this.b);
        onbttagcompound.a("Fuel", (short) this.c);
    }

    protected void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.a = onbttagcompound.h("PushX");
        this.b = onbttagcompound.h("PushZ");
        this.c = onbttagcompound.d("Fuel");
    }

    protected boolean d() {
        return (this.ah.a(16) & 1) != 0;
    }

    protected void f(boolean flag) {
        if (flag) {
            this.ah.b(16, Byte.valueOf((byte) (this.ah.a(16) | 1)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (this.ah.a(16) & -2)));
        }
    }

    public OBlock n() {
        return OBlock.aG;
    }

    public int p() {
        return 2;
    }
}
