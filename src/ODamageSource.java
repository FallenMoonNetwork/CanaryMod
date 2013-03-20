public class ODamageSource {

    public static ODamageSource a = (new ODamageSource("inFire")).l();
    public static ODamageSource b = (new ODamageSource("onFire")).j().l();
    public static ODamageSource c = (new ODamageSource("lava")).l();
    public static ODamageSource d = (new ODamageSource("inWall")).j();
    public static ODamageSource e = (new ODamageSource("drown")).j();
    public static ODamageSource f = (new ODamageSource("starve")).j();
    public static ODamageSource g = new ODamageSource("cactus");
    public static ODamageSource h = (new ODamageSource("fall")).j();
    public static ODamageSource i = (new ODamageSource("outOfWorld")).j().k();
    public static ODamageSource j = (new ODamageSource("generic")).j();
    public static ODamageSource k = (new ODamageSource("magic")).j().r();
    public static ODamageSource l = (new ODamageSource("wither")).j();
    public static ODamageSource m = new ODamageSource("anvil");
    public static ODamageSource n = new ODamageSource("fallingBlock");
    private boolean p = false;
    private boolean q = false;
    private float r = 0.3F;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v = false;
    private boolean w = false;
    public String o;
    //CanaryMod start
    protected DamageSource canaryDamageSource;
    
    /**
     * Return the damage source handler for this ODamageSource
     * @return
     */
    public DamageSource getDamageSource() {
        return canaryDamageSource;
    }
    //CanaryMod end

    public static ODamageSource a(OEntityLiving var0) {
        return new OEntityDamageSource("mob", var0);
    }

    public static ODamageSource a(OEntityPlayer var0) {
        return new OEntityDamageSource("player", var0);
    }

    public static ODamageSource a(OEntityArrow var0, OEntity var1) {
        return (new OEntityDamageSourceIndirect("arrow", var0, var1)).b();
    }

    public static ODamageSource a(OEntityFireball var0, OEntity var1) {
        return var1 == null ? (new OEntityDamageSourceIndirect("onFire", var0, var0)).l().b() : (new OEntityDamageSourceIndirect("fireball", var0, var1)).l().b();
    }

    public static ODamageSource a(OEntity var0, OEntity var1) {
        return (new OEntityDamageSourceIndirect("thrown", var0, var1)).b();
    }

    public static ODamageSource b(OEntity var0, OEntity var1) {
        return (new OEntityDamageSourceIndirect("indirectMagic", var0, var1)).j().r();
    }

    public static ODamageSource a(OEntity var0) {
        return (new OEntityDamageSource("thorns", var0)).r();
    }

    public static ODamageSource a(OExplosion var0) {
        return var0 != null && var0.c() != null ? (new OEntityDamageSource("explosion.player", var0.c())).o().d() : (new ODamageSource("explosion")).o().d();
    }

    public boolean a() {
        return this.t;
    }

    public ODamageSource b() {
        this.t = true;
        return this;
    }

    public boolean c() {
        return this.w;
    }

    public ODamageSource d() {
        this.w = true;
        return this;
    }

    public boolean e() {
        return this.p;
    }

    public float f() {
        return this.r;
    }

    public boolean g() {
        return this.q;
    }

    protected ODamageSource(String var1) {
        this.o = var1;
        this.canaryDamageSource = new DamageSource(this);
    }

    public OEntity h() {
        return this.i();
    }

    public OEntity i() {
        return null;
    }

    protected ODamageSource j() {
        this.p = true;
        this.r = 0.0F;
        return this;
    }

    protected ODamageSource k() {
        this.q = true;
        return this;
    }

    protected ODamageSource l() {
        this.s = true;
        return this;
    }

    public String b(OEntityLiving var1) {
        OEntityLiving var2 = var1.bN();
        String var3 = "death.attack." + this.o;
        String var4 = var3 + ".player";

        return var2 != null && OStatCollector.b(var4) ? OStatCollector.a(var4, new Object[] { var1.ax(), var2.ax()}) : OStatCollector.a(var3, new Object[] { var1.ax()});
    }

    public boolean m() {
        return this.s;
    }

    public String n() {
        return this.o;
    }

    public ODamageSource o() {
        this.u = true;
        return this;
    }

    public boolean p() {
        return this.u;
    }

    public boolean q() {
        return this.v;
    }

    public ODamageSource r() {
        this.v = true;
        return this;
    }
}
