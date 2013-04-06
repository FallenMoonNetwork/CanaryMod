public class OEntityWolf extends OEntityTameable {

    private float e;
    private float f;
    private boolean g;
    private boolean h;
    private float i;
    private float j;

    private Wolf wolf = new Wolf(this);

    public OEntityWolf(OWorld oworld) {
        super(oworld);
        this.aH = "/mob/wolf.png";
        this.a(0.6F, 0.8F);
        this.bI = 0.3F;
        this.aC().a(true);
        this.bo.a(1, new OEntityAISwimming(this));
        this.bo.a(2, this.d);
        this.bo.a(3, new OEntityAILeapAtTarget(this, 0.4F));
        this.bo.a(4, new OEntityAIAttackOnCollide(this, this.bI, true));
        this.bo.a(5, new OEntityAIFollowOwner(this, this.bI, 10.0F, 2.0F));
        this.bo.a(6, new OEntityAIMate(this, this.bI));
        this.bo.a(7, new OEntityAIWander(this, this.bI));
        this.bo.a(8, new OEntityAIBeg(this, 8.0F));
        this.bo.a(9, new OEntityAIWatchClosest(this, OEntityPlayer.class, 8.0F));
        this.bo.a(9, new OEntityAILookIdle(this));
        this.bp.a(1, new OEntityAIOwnerHurtByTarget(this));
        this.bp.a(2, new OEntityAIOwnerHurtTarget(this));
        this.bp.a(3, new OEntityAIHurtByTarget(this, true));
        this.bp.a(4, new OEntityAITargetNonTamed(this, OEntitySheep.class, 16.0F, 200, false));
    }

    public boolean bh() {
        return true;
    }

    public void b(OEntityLiving oentityliving) {
        super.b(oentityliving);
        if (oentityliving instanceof OEntityPlayer) {
            this.l(true);
        }
    }

    protected void bp() {
        this.ah.b(18, Integer.valueOf(this.aX()));
    }

    public int aW() {
        //CanaryMod: set max health here, but check for uninitialized value.
        return this.maxHealth == 0 ? (this.m() ? 20 : 8) : this.maxHealth;
    }

    protected void a() {
        super.a();
        this.ah.a(18, new Integer(this.aX()));
        this.ah.a(19, new Byte((byte) 0));
        this.ah.a(20, new Byte((byte) OBlockCloth.g_(1)));
    }

    protected void a(int i, int j, int k, int l) {
        this.a("mob.wolf.step", 0.15F, 1.0F);
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Angry", this.bU());
        onbttagcompound.a("CollarColor", (byte) this.bV());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.l(onbttagcompound.n("Angry"));
        if (onbttagcompound.b("CollarColor")) {
            this.s(onbttagcompound.c("CollarColor"));
        }
    }

    protected boolean bm() {
        return this.bU();
    }

    protected String bb() {
        return this.bU() ? "mob.wolf.growl" : (this.ab.nextInt(3) == 0 ? (this.m() && this.ah.c(18) < 10 ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
    }

    protected String bc() {
        return "mob.wolf.hurt";
    }

    protected String bd() {
        return "mob.wolf.death";
    }

    protected float ba() {
        return 0.4F;
    }

    protected int be() {
        return -1;
    }

    public void c() {
        super.c();
        if (!this.q.I && this.g && !this.h && !this.k() && this.F) {
            this.h = true;
            this.i = 0.0F;
            this.j = 0.0F;
            this.q.a((OEntity) this, (byte) 8);
        }
    }

    public void l_() {
        super.l_();
        this.f = this.e;
        if (this.bW()) {
            this.e += (1.0F - this.e) * 0.4F;
        } else {
            this.e += (0.0F - this.e) * 0.4F;
        }

        if (this.bW()) {
            this.bJ = 10;
        }

        if (this.F()) {
            this.g = true;
            this.h = false;
            this.i = 0.0F;
            this.j = 0.0F;
        } else if ((this.g || this.h) && this.h) {
            if (this.i == 0.0F) {
                this.a("mob.wolf.shake", this.ba(), (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
            }

            this.j = this.i;
            this.i += 0.05F;
            if (this.j >= 2.0F) {
                this.g = false;
                this.h = false;
                this.j = 0.0F;
                this.i = 0.0F;
            }

            if (this.i > 0.4F) {
                float f = (float) this.E.b;
                int i = (int) (OMathHelper.a((this.i - 0.4F) * 3.1415927F) * 7.0F);

                for (int j = 0; j < i; ++j) {
                    float f1 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O * 0.5F;
                    float f2 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O * 0.5F;

                    this.q.a("splash", this.u + (double) f1, (double) (f + 0.8F), this.w + (double) f2, this.x, this.y, this.z);
                }
            }
        }
    }

    public float e() {
        return this.P * 0.8F;
    }

    public int bs() {
        return this.n() ? 20 : super.bs();
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.aq()) {
            return false;
        } else {
            OEntity oentity = odamagesource.i();

            this.d.a(false);
            if (oentity != null && !(oentity instanceof OEntityPlayer) && !(oentity instanceof OEntityArrow)) {
                i = (i + 1) / 2;
            }

            return super.a(odamagesource, i);
        }
    }

    public boolean m(OEntity oentity) {
        int i = this.m() ? 4 : 2;

        return oentity.a(ODamageSource.a((OEntityLiving) this), i);
    }

    public boolean a_(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bK.h();

        if (this.m()) {
            if (oitemstack != null) {
                if (OItem.f[oitemstack.c] instanceof OItemFood) {
                    OItemFood oitemfood = (OItemFood) OItem.f[oitemstack.c];

                    if (oitemfood.i() && this.ah.c(18) < 20) {
                        if (!oentityplayer.ce.d) {
                            --oitemstack.a;
                        }

                        this.j(oitemfood.g());
                        if (oitemstack.a <= 0) {
                            oentityplayer.bK.a(oentityplayer.bK.c, (OItemStack) null);
                        }

                        return true;
                    }
                } else if (oitemstack.c == OItem.aX.cp) {
                    int i = OBlockCloth.g_(oitemstack.k());

                    if (i != this.bV()) {
                        this.s(i);
                        if (!oentityplayer.ce.d && --oitemstack.a <= 0) {
                            oentityplayer.bK.a(oentityplayer.bK.c, (OItemStack) null);
                        }

                        return true;
                    }
                }
            }

            if (oentityplayer.bS.equalsIgnoreCase(this.o()) && !this.q.I && !this.c(oitemstack)) {
                this.d.a(!this.n());
                this.bG = false;
                this.a((OPathEntity) null);
            }
        } else if (oitemstack != null && oitemstack.c == OItem.aY.cp && !this.bU()) {
            if (!oentityplayer.ce.d) {
                --oitemstack.a;
            }

            if (oitemstack.a <= 0) {
                oentityplayer.bK.a(oentityplayer.bK.c, (OItemStack) null);
            }

            if (!this.q.I) {
                // CanaryMod hook: onTame
                // randomize the tame result. if its 0 - tame success.
                int tameResult = this.ab.nextInt(3);
                // Call hook
                PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.TAME, oentityplayer.entity.getPlayer(), new Mob(this), tameResult == 0);

                // if taming succeeded normally (tameResult == 0) or plugin hook result is allow (force taming)
                if (tameResult == 0 && res == PluginLoader.HookResult.DEFAULT_ACTION || res == PluginLoader.HookResult.ALLOW_ACTION) {

                    this.j(true);
                    this.a((OPathEntity) null);
                    this.b((OEntityLiving) null);
                    this.d.a(true);
                    this.b(20);
                    this.a(oentityplayer.bS);
                    this.i(true);
                    this.q.a((OEntity) this, (byte) 7);
                } else {
                    this.i(false);
                    this.q.a((OEntity) this, (byte) 6);
                }
            }

            return true;
        }

        return super.a_(oentityplayer);
    }

    public boolean c(OItemStack oitemstack) {
        return oitemstack == null ? false : (!(OItem.f[oitemstack.c] instanceof OItemFood) ? false : ((OItemFood) OItem.f[oitemstack.c]).i());
    }

    public int by() {
        return 8;
    }

    public boolean bU() {
        return (this.ah.a(16) & 2) != 0;
    }

    public void l(boolean flag) {
        byte b0 = this.ah.a(16);

        if (flag) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 2)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & -3)));
        }
    }

    public int bV() {
        return this.ah.a(20) & 15;
    }

    public void s(int i) {
        this.ah.b(20, Byte.valueOf((byte) (i & 15)));
    }

    public OEntityWolf b(OEntityAgeable oentityageable) {
        OEntityWolf oentitywolf = new OEntityWolf(this.q);
        String s = this.o();

        if (s != null && s.trim().length() > 0) {
            oentitywolf.a(s);
            oentitywolf.j(true);
        }

        return oentitywolf;
    }

    public void m(boolean flag) {
        byte b0 = this.ah.a(19);

        if (flag) {
            this.ah.b(19, Byte.valueOf((byte) 1));
        } else {
            this.ah.b(19, Byte.valueOf((byte) 0));
        }
    }

    public boolean a(OEntityAnimal oentityanimal) {
        if (oentityanimal == this) {
            return false;
        } else if (!this.m()) {
            return false;
        } else if (!(oentityanimal instanceof OEntityWolf)) {
            return false;
        } else {
            OEntityWolf oentitywolf = (OEntityWolf) oentityanimal;

            return !oentitywolf.m() ? false : (oentitywolf.n() ? false : this.r() && oentitywolf.r());
        }
    }

    public boolean bW() {
        return this.ah.a(19) == 1;
    }

    public OEntityAgeable a(OEntityAgeable oentityageable) {
        return this.b(oentityageable);
    }

    @Override
    public Wolf getEntity() {
        return wolf;
    } //
}
