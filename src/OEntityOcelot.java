public class OEntityOcelot extends OEntityTameable {

    private OEntityAITempt e;
    private Ocelot ocelot = new Ocelot(this); // CanaryMod: one ocelot per ocelot

    public OEntityOcelot(OWorld oworld) {
        super(oworld);
        this.aH = "/mob/ozelot.png";
        this.a(0.6F, 0.8F);
        this.aC().a(true);
        this.bo.a(1, new OEntityAISwimming(this));
        this.bo.a(2, this.d);
        this.bo.a(3, this.e = new OEntityAITempt(this, 0.18F, OItem.aV.cp, true));
        this.bo.a(4, new OEntityAIAvoidEntity(this, OEntityPlayer.class, 16.0F, 0.23F, 0.4F));
        this.bo.a(5, new OEntityAIFollowOwner(this, 0.3F, 10.0F, 5.0F));
        this.bo.a(6, new OEntityAIOcelotSit(this, 0.4F));
        this.bo.a(7, new OEntityAILeapAtTarget(this, 0.3F));
        this.bo.a(8, new OEntityAIOcelotAttack(this));
        this.bo.a(9, new OEntityAIMate(this, 0.23F));
        this.bo.a(10, new OEntityAIWander(this, 0.23F));
        this.bo.a(11, new OEntityAIWatchClosest(this, OEntityPlayer.class, 10.0F));
        this.bp.a(1, new OEntityAITargetNonTamed(this, OEntityChicken.class, 14.0F, 750, false));
    }

    protected void a() {
        super.a();
        this.ah.a(18, Byte.valueOf((byte) 0));
    }

    public void bp() {
        if (this.aA().a()) {
            float f = this.aA().b();

            if (f == 0.18F) {
                this.b(true);
                this.c(false);
            } else if (f == 0.4F) {
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

    protected boolean bm() {
        return !this.m();
    }

    public boolean bh() {
        return true;
    }

    public int aW() {
        //CanaryMod: set max health here, but check for uninitialized value.
        return this.maxHealth == 0 ? 10 : this.maxHealth;
    }

    protected void a(float f) {}

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("CatType", this.t());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.s(onbttagcompound.e("CatType"));
    }

    protected String bb() {
        return this.m() ? (this.r() ? "mob.cat.purr" : (this.ab.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow")) : "";
    }

    protected String bc() {
        return "mob.cat.hitt";
    }

    protected String bd() {
        return "mob.cat.hitt";
    }

    protected float ba() {
        return 0.4F;
    }

    protected int be() {
        return OItem.aG.cp;
    }

    public boolean m(OEntity oentity) {
        return oentity.a(ODamageSource.a((OEntityLiving) this), 3);
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.aq()) {
            return false;
        } else {
            this.d.a(false);
            return super.a(odamagesource, i);
        }
    }

    protected void a(boolean flag, int i) {}

    public boolean a_(OEntityPlayer oentityplayer) {
        OItemStack oitemstack = oentityplayer.bK.h();

        if (this.m()) {
            if (oentityplayer.bS.equalsIgnoreCase(this.o()) && !this.q.I && !this.c(oitemstack)) {
                this.d.a(!this.n());
            }
        } else if (this.e.f() && oitemstack != null && oitemstack.c == OItem.aV.cp && oentityplayer.e(this) < 9.0D) {
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
                    this.s(1 + this.q.s.nextInt(3));
                    this.a(oentityplayer.bS);
                    this.i(true);
                    this.d.a(true);
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

    public OEntityOcelot b(OEntityAgeable oentityageable) {
        OEntityOcelot oentityocelot = new OEntityOcelot(this.q);

        if (this.m()) {
            oentityocelot.a(this.o());
            oentityocelot.j(true);
            oentityocelot.s(this.t());
        }

        return oentityocelot;
    }

    public boolean c(OItemStack oitemstack) {
        return oitemstack != null && oitemstack.c == OItem.aV.cp;
    }

    public boolean a(OEntityAnimal oentityanimal) {
        if (oentityanimal == this) {
            return false;
        } else if (!this.m()) {
            return false;
        } else if (!(oentityanimal instanceof OEntityOcelot)) {
            return false;
        } else {
            OEntityOcelot oentityocelot = (OEntityOcelot) oentityanimal;

            return !oentityocelot.m() ? false : this.r() && oentityocelot.r();
        }
    }

    public int t() {
        return this.ah.a(18);
    }

    public void s(int i) {
        this.ah.b(18, Byte.valueOf((byte) i));
    }

    public boolean bv() {
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

                if (l == OBlock.y.cz || l == OBlock.O.cz) {
                    return true;
                }
            }

            return false;
        }
    }

    public String am() {
        return this.bP() ? this.bO() : (this.m() ? "entity.Cat.name" : super.am());
    }

    public void bJ() {
        if (this.q.s.nextInt(7) == 0) {
            for (int i = 0; i < 2; ++i) {
                OEntityOcelot oentityocelot = new OEntityOcelot(this.q);

                oentityocelot.b(this.u, this.v, this.w, this.A, 0.0F);
                oentityocelot.a(-24000);
                this.q.d((OEntity) oentityocelot);
            }
        }
    }

    public OEntityAgeable a(OEntityAgeable oentityageable) {
        return this.b(oentityageable);
    }

    @Override
    public Ocelot getEntity() {
        return ocelot;
    }
}
