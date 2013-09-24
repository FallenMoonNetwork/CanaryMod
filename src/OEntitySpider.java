public class OEntitySpider extends OEntityMob {

    public OEntitySpider(OWorld oworld) {
        super(oworld);
        this.a(1.4F, 0.9F);
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

    protected void az() {
        super.az();
        this.a(OSharedMonsterAttributes.a).a(16.0D);
        this.a(OSharedMonsterAttributes.d).a(0.800000011920929D);
    }

    protected OEntity bL() {
        float f = this.d(1.0F);

        if (f < 0.5F) {
            double d0 = 16.0D;

            return this.q.b(this, d0);
        } else {
            return null;
        }
    }

    protected String r() {
        return "mob.spider.say";
    }

    protected String aO() {
        return "mob.spider.say";
    }

    protected String aP() {
        return "mob.spider.death";
    }

    protected void a(int i, int j, int k, int l) {
        this.a("mob.spider.step", 0.15F, 1.0F);
    }

    protected void a(OEntity oentity, float f) {
        float f1 = this.d(1.0F);

        if (f1 > 0.5F && this.ab.nextInt(100) == 0) {
            this.j = null;
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

    protected int s() {
        return OItem.M.cv;
    }

    protected void b(boolean flag, int i) {
        super.b(flag, i);
        if (flag && (this.ab.nextInt(3) == 0 || this.ab.nextInt(1 + i) > 0)) {
            this.b(OItem.bw.cv, 1);
        }
    }

    public boolean e() {
        return this.bT();
    }

    public void am() {}

    public OEnumCreatureAttribute aY() {
        return OEnumCreatureAttribute.c;
    }

    public boolean d(OPotionEffect opotioneffect) {
        return opotioneffect.a() == OPotion.u.H ? false : super.d(opotioneffect);
    }

    public boolean bT() {
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

    public OEntityLivingData a(OEntityLivingData oentitylivingdata) {
        Object object = super.a(oentitylivingdata);

        if (this.q.s.nextInt(100) == 0) {
            OEntitySkeleton oentityskeleton = new OEntitySkeleton(this.q);

            oentityskeleton.b(this.u, this.v, this.w, this.A, 0.0F);
            oentityskeleton.a((OEntityLivingData) null);
            this.q.d((OEntity) oentityskeleton);
            oentityskeleton.a((OEntity) this);
        }

        if (object == null) {
            object = new OSpiderEffectsGroupData();
            if (this.q.r > 2 && this.q.s.nextFloat() < 0.1F * this.q.b(this.u, this.v, this.w)) {
                ((OSpiderEffectsGroupData) object).a(this.q.s);
            }
        }

        if (object instanceof OSpiderEffectsGroupData) {
            int i = ((OSpiderEffectsGroupData) object).a;

            if (i > 0 && OPotion.a[i] != null) {
                this.c(new OPotionEffect(i, Integer.MAX_VALUE));
            }
        }

        return (OEntityLivingData) object;
    }
}
