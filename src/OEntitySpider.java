
public class OEntitySpider extends OEntityMob {

    public OEntitySpider(OWorld oworld) {
        super(oworld);
        this.aF = "/mob/spider.png";
        this.a(1.4F, 0.9F);
        this.bI = 0.8F;
    }

    protected void a() {
        super.a();
        this.ag.a(16, new Byte((byte) 0));
    }

    public void j_() {
        super.j_();
        if (!this.p.J) {
            this.f(this.F);
        }

    }

    public int aS() {
        return 16;
    }

    public double X() {
        return (double) this.O * 0.75D - 0.5D;
    }

    protected OEntity j() {
        float f = this.c(1.0F);

        double d0 = 16.0D;
        OEntityPlayer oentityplayer = this.p.b(this, d0);

        if (f < 0.5F && oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentityplayer.entity.getPlayer(), entity)) {
            return oentityplayer;
        } else {
            return null;
        }
    }

    protected String aW() {
        return "mob.spider.say";
    }

    protected String aX() {
        return "mob.spider.say";
    }

    protected String aY() {
        return "mob.spider.death";
    }

    protected void a(int i, int j, int k, int l) {
        this.p.a(this, "mob.spider.step", 0.15F, 1.0F);
    }

    protected void a(OEntity oentity, float f) {
        float f1 = this.c(1.0F);

        if (f1 > 0.5F && this.aa.nextInt(100) == 0) {
            this.a_ = null;
        } else {
            if (f > 2.0F && f < 6.0F && this.aa.nextInt(10) == 0) {
                if (this.E) {
                    double d0 = oentity.t - this.t;
                    double d1 = oentity.v - this.v;
                    float f2 = OMathHelper.a(d0 * d0 + d1 * d1);

                    this.w = d0 / (double) f2 * 0.5D * 0.800000011920929D + this.w * 0.20000000298023224D;
                    this.y = d1 / (double) f2 * 0.5D * 0.800000011920929D + this.y * 0.20000000298023224D;
                    this.x = 0.4000000059604645D;
                }
            } else {
                super.a(oentity, f);
            }

        }
    }

    protected int aZ() {
        return OItem.K.cf;
    }

    protected void a(boolean flag, int i) {
        super.a(flag, i);
        if (flag && (this.aa.nextInt(3) == 0 || this.aa.nextInt(1 + i) > 0)) {
            this.b(OItem.bu.cf, 1);
        }

    }

    public boolean g_() {
        return this.o();
    }

    public void am() {}

    public OEnumCreatureAttribute bz() {
        return OEnumCreatureAttribute.c;
    }

    public boolean e(OPotionEffect opotioneffect) {
        return opotioneffect.a() == OPotion.u.H ? false : super.e(opotioneffect);
    }

    public boolean o() {
        return (this.ag.a(16) & 1) != 0;
    }

    public void f(boolean flag) {
        byte b0 = this.ag.a(16);

        if (flag) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.ag.b(16, Byte.valueOf(b0));
    }

    public void bD() {
        if (this.p.u.nextInt(100) == 0) {
            OEntitySkeleton oentityskeleton = new OEntitySkeleton(this.p);

            oentityskeleton.b(this.t, this.u, this.v, this.z, 0.0F);
            oentityskeleton.bD();
            this.p.d((OEntity) oentityskeleton);
            oentityskeleton.a((OEntity) this);
        }
    }
}
