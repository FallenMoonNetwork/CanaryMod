import java.util.Iterator;
import java.util.List;

public class OEntityWolf extends OEntityTameable {

    private float bq;
    private float br;
    private boolean bs;
    private boolean bt;
    private float bu;
    private float bv;

    private Wolf wolf = new Wolf(this);

    public OEntityWolf(OWorld oworld) {
        super(oworld);
        this.a(0.6F, 0.8F);
        this.k().a(true);
        this.c.a(1, new OEntityAISwimming(this));
        this.c.a(2, this.bp);
        this.c.a(3, new OEntityAILeapAtTarget(this, 0.4F));
        this.c.a(4, new OEntityAIAttackOnCollide(this, 1.0D, true));
        this.c.a(5, new OEntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.c.a(6, new OEntityAIMate(this, 1.0D));
        this.c.a(7, new OEntityAIWander(this, 1.0D));
        this.c.a(8, new OEntityAIBeg(this, 8.0F));
        this.c.a(9, new OEntityAIWatchClosest(this, OEntityPlayer.class, 8.0F));
        this.c.a(9, new OEntityAILookIdle(this));
        this.d.a(1, new OEntityAIOwnerHurtByTarget(this));
        this.d.a(2, new OEntityAIOwnerHurtTarget(this));
        this.d.a(3, new OEntityAIHurtByTarget(this, true));
        this.d.a(4, new OEntityAITargetNonTamed(this, OEntitySheep.class, 200, false));
        this.k(false);
    }

    protected void ax() {
        super.ax();
        this.a(OSharedMonsterAttributes.d).a(0.30000001192092896D);
        if (this.bP()) {
            this.a(OSharedMonsterAttributes.a).a(20.0D);
        } else {
            this.a(OSharedMonsterAttributes.a).a(8.0D);
        }
    }

    public boolean bb() {
        return true;
    }

    public void c(OEntityLivingBase oentitylivingbase) {
        super.c(oentitylivingbase);
        if (oentitylivingbase == null) {
            if (!this.bY()) {
                return;
            }

            this.m(false);
            List list = this.q.a(this.getClass(), OAxisAlignedBB.a().a(this.u, this.v, this.w, this.u + 1.0D, this.v + 1.0D, this.w + 1.0D).b(16.0D, 10.0D, 16.0D));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                OEntityWolf oentitywolf = (OEntityWolf) iterator.next();

                if (this != oentitywolf) {
                    oentitywolf.m(false);
                }
            }
        } else {
            this.m(true);
        }
    }

    protected void bg() {
        this.ah.b(18, Float.valueOf(this.aJ()));
    }

    protected void a() {
        super.a();
        this.ah.a(18, new Float(this.aJ()));
        this.ah.a(19, new Byte((byte) 0));
        this.ah.a(20, new Byte((byte) OBlockColored.j_(1)));
    }

    protected void a(int i, int j, int k, int l) {
        this.a("mob.wolf.step", 0.15F, 1.0F);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Angry", this.bY());
        onbttagcompound.a("CollarColor", (byte) this.bZ());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.m(onbttagcompound.n("Angry"));
        if (onbttagcompound.b("CollarColor")) {
            this.p(onbttagcompound.c("CollarColor"));
        }
    }

    protected String r() {
        return this.bY() ? "mob.wolf.growl" : (this.ab.nextInt(3) == 0 ? (this.bP() && this.ah.d(18) < 10.0F ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
    }

    protected String aK() {
        return "mob.wolf.hurt";
    }

    protected String aL() {
        return "mob.wolf.death";
    }

    protected float aW() {
        return 0.4F;
    }

    protected int s() {
        return -1;
    }

    public void c() {
        super.c();
        if (!this.q.I && this.bs && !this.bt && !this.bI() && this.F) {
            this.bt = true;
            this.bu = 0.0F;
            this.bv = 0.0F;
            this.q.a((OEntity) this, (byte) 8);
        }
    }

    public void l_() {
        super.l_();
        this.br = this.bq;
        if (this.ca()) {
            this.bq += (1.0F - this.bq) * 0.4F;
        } else {
            this.bq += (0.0F - this.bq) * 0.4F;
        }

        if (this.ca()) {
            this.g = 10;
        }

        if (this.F()) {
            this.bs = true;
            this.bt = false;
            this.bu = 0.0F;
            this.bv = 0.0F;
        } else if ((this.bs || this.bt) && this.bt) {
            if (this.bu == 0.0F) {
                this.a("mob.wolf.shake", this.aW(), (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
            }

            this.bv = this.bu;
            this.bu += 0.05F;
            if (this.bv >= 2.0F) {
                this.bs = false;
                this.bt = false;
                this.bv = 0.0F;
                this.bu = 0.0F;
            }

            if (this.bu > 0.4F) {
                float f = (float) this.E.b;
                int i = (int) (OMathHelper.a((this.bu - 0.4F) * 3.1415927F) * 7.0F);

                for (int j = 0; j < i; ++j) {
                    float f1 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O * 0.5F;
                    float f2 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O * 0.5F;

                    this.q.a("splash", this.u + (double) f1, (double) (f + 0.8F), this.w + (double) f2, this.x, this.y, this.z);
                }
            }
        }
    }

    public float f() {
        return this.P * 0.8F;
    }

    public int bl() {
        return this.bQ() ? 20 : super.bl();
    }

    public boolean a(ODamageSource odamagesource, float f) {
        if (this.ap()) {
            return false;
        } else {
            OEntity oentity = odamagesource.i();

            this.bp.a(false);
            if (oentity != null && !(oentity instanceof OEntityPlayer) && !(oentity instanceof OEntityArrow)) {
                f = (f + 1.0F) / 2.0F;
            }

            return super.a(odamagesource, f);
        }
    }

    public boolean m(OEntity oentity) {
        int i = this.bP() ? 4 : 2;

        return oentity.a(ODamageSource.a((OEntityLivingBase) this), (float) i);
    }

    public void k(boolean flag) {
        super.k(flag);
        if (flag) {
            this.a(OSharedMonsterAttributes.a).a(20.0D);
        } else {
            this.a(OSharedMonsterAttributes.a).a(8.0D);
        }
    }

    public boolean a(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bn.h();

        if (this.bP()) {
            if (oitemstack != null) {
                if (OItem.g[oitemstack.d] instanceof OItemFood) {
                    OItemFood oitemfood = (OItemFood) OItem.g[oitemstack.d];

                    if (oitemfood.j() && this.ah.d(18) < 20.0F) {
                        if (!oentityplayer.bG.d) {
                            --oitemstack.b;
                        }

                        this.f((float) oitemfood.g());
                        if (oitemstack.b <= 0) {
                            oentityplayer.bn.a(oentityplayer.bn.c, (OItemStack) null);
                        }

                        return true;
                    }
                } else if (oitemstack.d == OItem.aY.cv) {
                    int i = OBlockColored.j_(oitemstack.k());

                    if (i != this.bZ()) {
                        this.p(i);
                        if (!oentityplayer.bG.d && --oitemstack.b <= 0) {
                            oentityplayer.bn.a(oentityplayer.bn.c, (OItemStack) null);
                        }

                        return true;
                    }
                }
            }

            if (oentityplayer.c_().equalsIgnoreCase(this.h_()) && !this.q.I && !this.c(oitemstack)) {
                this.bp.a(!this.bQ());
                this.bd = false;
                this.a((OPathEntity) null);
            }
        } else if (oitemstack != null && oitemstack.d == OItem.aZ.cv && !this.bY()) {
            if (!oentityplayer.bG.d) {
                --oitemstack.b;
            }

            if (oitemstack.b <= 0) {
                oentityplayer.bn.a(oentityplayer.bn.c, (OItemStack) null);
            }

            if (!this.q.I) {
                // CanaryMod hook: onTame
                // randomize the tame result. if its 0 - tame success.
                int tameResult = this.ab.nextInt(3);
                // Call hook
                PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.TAME, oentityplayer.entity.getPlayer(), new Mob(this), tameResult == 0);

                // if taming succeeded normally (tameResult == 0) or plugin hook result is allow (force taming)
                if (tameResult == 0 && res == PluginLoader.HookResult.DEFAULT_ACTION || res == PluginLoader.HookResult.ALLOW_ACTION) {

                    this.k(true);
                    this.a((OPathEntity) null);
                    this.c((OEntityLivingBase) null);
                    this.bp.a(true);
                    this.g(20.0F);
                    this.b(oentityplayer.c_());
                    this.j(true);
                    this.q.a((OEntity) this, (byte) 7);
                } else {
                    this.j(false);
                    this.q.a((OEntity) this, (byte) 6);
                }
            }

            return true;
        }

        return super.a(oentityplayer);
    }

    public boolean c(OItemStack oitemstack) {
        return oitemstack == null ? false : (!(OItem.g[oitemstack.d] instanceof OItemFood) ? false : ((OItemFood) OItem.g[oitemstack.d]).j());
    }

    public int br() {
        return 8;
    }

    public boolean bY() {
        return (this.ah.a(16) & 2) != 0;
    }

    public void m(boolean flag) {
        byte b0 = this.ah.a(16);

        if (flag) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 2)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & -3)));
        }
    }

    public int bZ() {
        return this.ah.a(20) & 15;
    }

    public void p(int i) {
        this.ah.b(20, Byte.valueOf((byte) (i & 15)));
    }

    public OEntityWolf b(OEntityAgeable oentityageable) {
        OEntityWolf oentitywolf = new OEntityWolf(this.q);
        String s = this.h_();

        if (s != null && s.trim().length() > 0) {
            oentitywolf.b(s);
            oentitywolf.k(true);
        }

        return oentitywolf;
    }

    public void n(boolean flag) {
        if (flag) {
            this.ah.b(19, Byte.valueOf((byte) 1));
        } else {
            this.ah.b(19, Byte.valueOf((byte) 0));
        }
    }

    public boolean a(OEntityAnimal oentityanimal) {
        if (oentityanimal == this) {
            return false;
        } else if (!this.bP()) {
            return false;
        } else if (!(oentityanimal instanceof OEntityWolf)) {
            return false;
        } else {
            OEntityWolf oentitywolf = (OEntityWolf) oentityanimal;

            return !oentitywolf.bP() ? false : (oentitywolf.bQ() ? false : this.bU() && oentitywolf.bU());
        }
    }

    public boolean ca() {
        return this.ah.a(19) == 1;
    }

    protected boolean t() {
        return !this.bP() && this.ac > 2400;
    }

    public OEntityAgeable a(OEntityAgeable oentityageable) {
        return this.b(oentityageable);
    }

    @Override
    public Wolf getEntity() {
        return wolf;
    } //
}
