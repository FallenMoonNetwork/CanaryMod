public class OEntityOcelot extends OEntityTameable {

    private OEntityAITempt bq;

    private Ocelot ocelot = new Ocelot(this); // CanaryMod: one ocelot per ocelot

    public OEntityOcelot(OWorld oworld) {
        super(oworld);
        this.a(0.6F, 0.8F);
        this.k().a(true);
        this.c.a(1, new OEntityAISwimming(this));
        this.c.a(2, this.bp);
        this.c.a(3, this.bq = new OEntityAITempt(this, 0.6D, OItem.aW.cv, true));
        this.c.a(4, new OEntityAIAvoidEntity(this, OEntityPlayer.class, 16.0F, 0.8D, 1.33D));
        this.c.a(5, new OEntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
        this.c.a(6, new OEntityAIOcelotSit(this, 1.33D));
        this.c.a(7, new OEntityAILeapAtTarget(this, 0.3F));
        this.c.a(8, new OEntityAIOcelotAttack(this));
        this.c.a(9, new OEntityAIMate(this, 0.8D));
        this.c.a(10, new OEntityAIWander(this, 0.8D));
        this.c.a(11, new OEntityAIWatchClosest(this, OEntityPlayer.class, 10.0F));
        this.d.a(1, new OEntityAITargetNonTamed(this, OEntityChicken.class, 750, false));
    }

    protected void a() {
        super.a();
        this.ah.a(18, Byte.valueOf((byte) 0));
    }

    public void bg() {
        if (this.i().a()) {
            double d0 = this.i().b();

            if (d0 == 0.6D) {
                this.b(true);
                this.c(false);
            } else if (d0 == 1.33D) {
                this.b(false);
                this.c(true);
            } else {
                this.b(false);
                this.c(false);
            }
        } else {
            this.b(false);
            this.c(false);
        }
    }

    protected boolean t() {
        return !this.bP() && this.ac > 2400;
    }

    public boolean bb() {
        return true;
    }

    protected void ax() {
        super.ax();
        this.a(OSharedMonsterAttributes.a).a(10.0D);
        this.a(OSharedMonsterAttributes.d).a(0.30000001192092896D);
    }

    protected void b(float f) {}

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("CatType", this.bW());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.p(onbttagcompound.e("CatType"));
    }

    protected String r() {
        return this.bP() ? (this.bU() ? "mob.cat.purr" : (this.ab.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow")) : "";
    }

    protected String aK() {
        return "mob.cat.hitt";
    }

    protected String aL() {
        return "mob.cat.hitt";
    }

    protected float aW() {
        return 0.4F;
    }

    protected int s() {
        return OItem.aH.cv;
    }

    public boolean m(OEntity oentity) {
        return oentity.a(ODamageSource.a((OEntityLivingBase) this), 3.0F);
    }

    public boolean a(ODamageSource odamagesource, float f) {
        if (this.ap()) {
            return false;
        } else {
            this.bp.a(false);
            return super.a(odamagesource, f);
        }
    }

    protected void b(boolean flag, int i) {}

    public boolean a(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bn.h();

        if (this.bP()) {
            if (oentityplayer.c_().equalsIgnoreCase(this.h_()) && !this.q.I && !this.c(oitemstack)) {
                this.bp.a(!this.bQ());
            }
        } else if (this.bq.f() && oitemstack != null && oitemstack.d == OItem.aW.cv && oentityplayer.e(this) < 9.0D) {
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
                    this.p(1 + this.q.s.nextInt(3));
                    this.b(oentityplayer.c_());
                    this.j(true);
                    this.bp.a(true);
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

    public OEntityOcelot b(OEntityAgeable oentityageable) {
        OEntityOcelot oentityocelot = new OEntityOcelot(this.q);

        if (this.bP()) {
            oentityocelot.b(this.h_());
            oentityocelot.k(true);
            oentityocelot.p(this.bW());
        }

        return oentityocelot;
    }

    public boolean c(OItemStack oitemstack) {
        return oitemstack != null && oitemstack.d == OItem.aW.cv;
    }

    public boolean a(OEntityAnimal oentityanimal) {
        if (oentityanimal == this) {
            return false;
        } else if (!this.bP()) {
            return false;
        } else if (!(oentityanimal instanceof OEntityOcelot)) {
            return false;
        } else {
            OEntityOcelot oentityocelot = (OEntityOcelot) oentityanimal;

            return !oentityocelot.bP() ? false : this.bU() && oentityocelot.bU();
        }
    }

    public int bW() {
        return this.ah.a(18);
    }

    public void p(int i) {
        this.ah.b(18, Byte.valueOf((byte) i));
    }

    public boolean bo() {
        if (this.q.s.nextInt(3) == 0) {
            return false;
        } else {
            if (this.q.b(this.E) && this.q.a((OEntity) this, this.E).isEmpty() && !this.q.d(this.E)) {
                int i = OMathHelper.c(this.u);
                int j = OMathHelper.c(this.E.b);
                int k = OMathHelper.c(this.w);

                if (j < 63) {
                    return false;
                }

                int l = this.q.a(i, j - 1, k);

                if (l == OBlock.z.cF || l == OBlock.P.cF) {
                    return true;
                }
            }

            return false;
        }
    }

    public String al() {
        return this.bx() ? this.bw() : (this.bP() ? "entity.Cat.name" : super.al());
    }

    public OEntityLivingData a(OEntityLivingData oentitylivingdata) {
        oentitylivingdata = super.a(oentitylivingdata);
        if (this.q.s.nextInt(7) == 0) {
            for (int i = 0; i < 2; ++i) {
                OEntityOcelot oentityocelot = new OEntityOcelot(this.q);

                oentityocelot.b(this.u, this.v, this.w, this.A, 0.0F);
                oentityocelot.c(-24000);
                this.q.d((OEntity) oentityocelot);
            }
        }

        return oentitylivingdata;
    }

    public OEntityAgeable a(OEntityAgeable oentityageable) {
        return this.b(oentityageable);
    }

    @Override
    public Ocelot getEntity() {
        return ocelot;
    }
}
