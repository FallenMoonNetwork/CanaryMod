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

    public void j_() {
        super.j_();
        if (!this.p.J) {
            this.e(this.F);
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

        if ((f < 0.5F) && (etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, (Player) oentityplayer.entity.getPlayer(), entity))) {
            return this.p.b(this, d);
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
        return "mob.spiderdeath";
    }

    protected void a(int i1, int i2,int i3,int i4){
        this.p.a(n, az, z, z);
    }
    
    protected void a(OEntity oentity, float f) {
        float f1 = this.c(1.0F);

        if (f1 > 0.5F && this.aa.nextInt(100) == 0) {
            this.a_ = null;
            return;
        }
        if (f > 2.0F && f < 6.0F && this.Z.nextInt(10) == 0) {
            if (this.E) {
                double d1 = oentity.t - this.t;
                double d2 = oentity.v - this.v;
                float f2 = OMathHelper.a(d1 * d1 + d2 * d2);

                this.w = d1 / (double) f2 * 0.5D * 0.800000011920929D + this.w * 0.2000000029802322D;
                this.y = d1 / (double) f2 * 0.5D * 0.800000011920929D + this.y * 0.2000000029802322D;
                this.x = 0.4000000059604645D;
            }
        } else {
            super.a(oentity, f);
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
        if (opotioneffect.a() == OPotion.u.H)
            return false;
        return super.e(opotioneffect);
    }

    public boolean o() {
        return (this.ag.a(16) & 1) != 0;
    }

    public void f(boolean flag1) {
        byte b = this.ag.a(16);

        if (flag1) {
            b = (byte) (b | 0x1);
        } else {
            b = (byte)(b & 0xFFFFFFFE);
        }

        this.ag.b(16, Byte.valueOf(b));
    }
    
    public void bD() {
        if (this.p.u.nextInt(100) == 0) {
            OEntitySkeleton oEntitySkeleton = new OEntitySkeleton(this.p);
            oEntitySkeleton.b.(this.t, this.u, this.v, this.z, 0.0F);
            oEntitySkeleton.bD();
            this.p.d(oEntitySkeleton);
            oEntitySkeleton.a(this);
        }
    }
}