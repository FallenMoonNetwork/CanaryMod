
public class OEntitySpider extends OEntityMob {

    public OEntitySpider(OWorld oworld) {
        super(oworld);
        this.az = "/mob/spider.png";
        this.a(1.4F, 0.9F);
        this.bw = 0.8F;
    }

    protected void a() {
        super.a();
        this.af.a(16, new Byte((byte) 0));
    }

    public void h_() {
        super.h_();
        if (!this.p.K) {
            this.e(this.F);
        }

    }

    public int aM() {
        return 16;
    }

    public double X() {
        return (double) this.O * 0.75D - 0.5D;
    }

    protected boolean e_() {
        return false;
    }

    protected OEntity k() {
        float f = this.c(1.0F);

        double d0 = 16.0D;
        OEntityPlayer oentityplayer = this.p.b(this, d0);

        if (f < 0.5F && oentityplayer != null && !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentityplayer.entity.getPlayer(), entity)) {
            return oentityplayer;
        } else {
            return null;
        }
    }

    protected String aQ() {
        return "mob.spider";
    }

    protected String aR() {
        return "mob.spider";
    }

    protected String aS() {
        return "mob.spiderdeath";
    }

    protected void a(OEntity oentity, float f) {
        float f1 = this.c(1.0F);

        if (f1 > 0.5F && this.Z.nextInt(100) == 0) {
            this.a = null;
        } else {
            if (f > 2.0F && f < 6.0F && this.Z.nextInt(10) == 0) {
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

    protected int aT() {
        return OItem.K.bT;
    }

    protected void a(boolean flag, int i) {
        super.a(flag, i);
        if (flag && (this.Z.nextInt(3) == 0 || this.Z.nextInt(1 + i) > 0)) {
            this.b(OItem.bu.bT, 1);
        }

    }

    public boolean f_() {
        return this.p();
    }

    public void aj() {}

    public OEnumCreatureAttribute bt() {
        return OEnumCreatureAttribute.c;
    }

    public boolean e(OPotionEffect opotioneffect) {
        return opotioneffect.a() == OPotion.u.H ? false : super.e(opotioneffect);
    }

    public boolean p() {
        return (this.af.a(16) & 1) != 0;
    }

    public void e(boolean flag) {
        byte b0 = this.af.a(16);

        if (flag) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.af.b(16, Byte.valueOf(b0));
    }
}
