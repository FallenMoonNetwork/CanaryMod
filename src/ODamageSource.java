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

    DamageSource damageSource = new DamageSource(this); // CanaryMod: reference to wrapper

    public static ODamageSource a(OEntityLiving oentityliving) {
        return new OEntityDamageSource("mob", oentityliving);
    }

    public static ODamageSource a(OEntityPlayer oentityplayer) {
        return new OEntityDamageSource("player", oentityplayer);
    }

    public static ODamageSource a(OEntityArrow oentityarrow, OEntity oentity) {
        return (new OEntityDamageSourceIndirect("arrow", oentityarrow, oentity)).b();
    }

    public static ODamageSource a(OEntityFireball oentityfireball, OEntity oentity) {
        return oentity == null ? (new OEntityDamageSourceIndirect("onFire", oentityfireball, oentityfireball)).l().b() : (new OEntityDamageSourceIndirect("fireball", oentityfireball, oentity)).l().b();
    }

    public static ODamageSource a(OEntity oentity, OEntity oentity1) {
        return (new OEntityDamageSourceIndirect("thrown", oentity, oentity1)).b();
    }

    public static ODamageSource b(OEntity oentity, OEntity oentity1) {
        return (new OEntityDamageSourceIndirect("indirectMagic", oentity, oentity1)).j().r();
    }

    public static ODamageSource a(OEntity oentity) {
        return (new OEntityDamageSource("thorns", oentity)).r();
    }

    public static ODamageSource a(OExplosion oexplosion) {
        return oexplosion != null && oexplosion.c() != null ? (new OEntityDamageSource("explosion.player", oexplosion.c())).o().d() : (new ODamageSource("explosion")).o().d();
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

    protected ODamageSource(String s) {
        this.o = s;
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

    public String b(OEntityLiving oentityliving) {
        OEntityLiving oentityliving1 = oentityliving.bN();
        String s = "death.attack." + this.o;
        String s1 = s + ".player";

        return oentityliving1 != null && OStatCollector.b(s1) ? OStatCollector.a(s1, new Object[] { oentityliving.ax(), oentityliving1.ax()}) : OStatCollector.a(s, new Object[] { oentityliving.ax()});
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
