public class OEntitySpider extends OEntityMob {

    public OEntitySpider(OWorld oworld) {
        super(oworld);
        this.aH = "/mob/spider.png";
        this.a(1.4F, 0.9F);
        this.bI = 0.8F;
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 0));
    }

    public void l_() {
        super.l_();
        if (!this.q.I) {
            this.a(this.G);
        }
    }

    public int aW() {
        //CanaryMod: set max health here, but check for uninitialized value.
        return this.maxHealth == 0 ? 16 : this.maxHealth;
    }

    public double W() {
        return (double) this.P * 0.75D - 0.5D;
    }

    protected OEntity j() {
        float f = this.c(1.0F);

        if (f < 0.5F) {
            double d0 = 16.0D;

            return this.q.b(this, d0);
        } else {
            return null;
        }
    }

    protected String bb() {
        return "mob.spider.say";
    }

    protected String bc() {
        return "mob.spider.say";
    }

    protected String bd() {
        return "mob.spider.death";
    }

    protected void a(int i, int j, int k, int l) {
        this.a("mob.spider.step", 0.15F, 1.0F);
    }

    protected void a(OEntity oentity, float f) {
        float f1 = this.c(1.0F);

        if (f1 > 0.5F && this.ab.nextInt(100) == 0) {
            this.a_ = null;
        } else {
            if (f > 2.0F && f < 6.0F && this.ab.nextInt(10) == 0) {
                if (this.F) {
                    double d0 = oentity.u - this.u;
                    double d1 = oentity.w - this.w;
                    float f2 = OMathHelper.a(d0 * d0 + d1 * d1);

                    this.x = d0 / (double) f2 * 0.5D * 0.800000011920929D + this.x * 0.20000000298023224D;
                    this.z = d1 / (double) f2 * 0.5D * 0.800000011920929D + this.z * 0.20000000298023224D;
                    this.y = 0.4000000059604645D;
                }
            } else {
                super.a(oentity, f);
            }
        }
    }

    protected int be() {
        return OItem.L.cp;
    }

    protected void a(boolean flag, int i) {
        super.a(flag, i);
        if (flag && (this.ab.nextInt(3) == 0 || this.ab.nextInt(1 + i) > 0)) {
            this.b(OItem.bv.cp, 1);
        }
    }

    public boolean g_() {
        return this.o();
    }

    public void al() {}

    public OEnumCreatureAttribute bF() {
        return OEnumCreatureAttribute.c;
    }

    public boolean e(OPotionEffect opotioneffect) {
        return opotioneffect.a() == OPotion.u.H ? false : super.e(opotioneffect);
    }

    public boolean o() {
        return (this.ah.a(16) & 1) != 0;
    }

    public void a(boolean flag) {
        byte b0 = this.ah.a(16);

        if (flag) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.ah.b(16, Byte.valueOf(b0));
    }

    public void bJ() {
        if (this.q.s.nextInt(100) == 0) {
            OEntitySkeleton oentityskeleton = new OEntitySkeleton(this.q);

            oentityskeleton.b(this.u, this.v, this.w, this.A, 0.0F);
            oentityskeleton.bJ();
            this.q.d((OEntity) oentityskeleton);
            oentityskeleton.a((OEntity) this);
        }
    }
}
