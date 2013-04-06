public abstract class OEntityTameable extends OEntityAnimal {

    protected OEntityAISit d = new OEntityAISit(this);

    private TamableEntity tamableEntity = new TamableEntity(this);

    public OEntityTameable(OWorld oworld) {
        super(oworld);
    }

    protected void a() {
        super.a();
        this.ah.a(16, Byte.valueOf((byte) 0));
        this.ah.a(17, "");
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        if (this.o() == null) {
            onbttagcompound.a("Owner", "");
        } else {
            onbttagcompound.a("Owner", this.o());
        }

        onbttagcompound.a("Sitting", this.n());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        String s = onbttagcompound.i("Owner");

        if (s.length() > 0) {
            this.a(s);
            this.j(true);
        }

        this.d.a(onbttagcompound.n("Sitting"));
        this.k(onbttagcompound.n("Sitting"));
    }

    protected void i(boolean flag) {
        String s = "heart";

        if (!flag) {
            s = "smoke";
        }

        for (int i = 0; i < 7; ++i) {
            double d0 = this.ab.nextGaussian() * 0.02D;
            double d1 = this.ab.nextGaussian() * 0.02D;
            double d2 = this.ab.nextGaussian() * 0.02D;

            this.q.a(s, this.u + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, this.v + 0.5D + (double) (this.ab.nextFloat() * this.P), this.w + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, d0, d1, d2);
        }
    }

    public boolean m() {
        return (this.ah.a(16) & 4) != 0;
    }

    public void j(boolean flag) {
        byte b0 = this.ah.a(16);

        if (flag) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 4)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & -5)));
        }
    }

    public boolean n() {
        return (this.ah.a(16) & 1) != 0;
    }

    public void k(boolean flag) {
        byte b0 = this.ah.a(16);

        if (flag) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & -2)));
        }
    }

    public String o() {
        return this.ah.e(17);
    }

    public void a(String s) {
        this.ah.b(17, s);
    }

    public OEntityLiving p() {
        return this.q.a(this.o());
    }

    public OEntityAISit q() {
        return this.d;
    }

    @Override
    public TamableEntity getEntity() {
        return tamableEntity;
    }
}
