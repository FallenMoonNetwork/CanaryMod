import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class OEntityLiving extends OEntity {

    private static final float[] b = new float[] { 0.0F, 0.0F, 0.1F, 0.2F};
    private static final float[] c = new float[] { 0.0F, 0.0F, 0.25F, 0.5F};
    private static final float[] d = new float[] { 0.0F, 0.0F, 0.05F, 0.02F};
    public static final float[] au = new float[] { 0.0F, 0.1F, 0.15F, 0.45F};
    public int av = 20;
    public float aw;
    public float ax;
    public float ay = 0.0F;
    public float az = 0.0F;
    public float aA = 0.0F;
    public float aB = 0.0F;
    protected float aC;
    protected float aD;
    protected float aE;
    protected float aF;
    protected boolean aG = true;
    protected String aH = "/mob/char.png";
    protected boolean aI = true;
    protected float aJ = 0.0F;
    protected String aK = null;
    protected float aL = 1.0F;
    protected int aM = 0;
    protected float aN = 0.0F;
    public float aO = 0.1F;
    public float aP = 0.02F;
    public float aQ;
    public float aR;
    protected int aS = this.aW(); //CanaryMod: health variable
    public int aT;
    protected int aU;
    public int aV;
    public int aW;
    public int aX;
    public float aY = 0.0F;
    public int aZ = 0;
    public int ba = 0;
    public float bb;
    public float bc;
    protected boolean bd = false;
    protected int be;
    public int bf = -1;
    public float bg = (float) (Math.random() * 0.8999999761581421D + 0.10000000149011612D);
    public float bh;
    public float bi;
    public float bj;
    protected OEntityPlayer bk = null;
    protected int bl = 0;
    private OEntityLiving e = null;
    private int f = 0;
    private OEntityLiving g = null;
    public int bm = 0;
    protected HashMap bn = new HashMap();
    private boolean h = true;
    private int i;
    private OEntityLookHelper j;
    private OEntityMoveHelper bK;
    private OEntityJumpHelper bL;
    private OEntityBodyHelper bM;
    private OPathNavigate bN;
    protected final OEntityAITasks bo;
    protected final OEntityAITasks bp;
    private OEntityLiving bO;
    private OEntitySenses bP;
    private float bQ;
    private OChunkCoordinates bR = new OChunkCoordinates(0, 0, 0);
    private float bS = -1.0F;
    private OItemStack[] bT = new OItemStack[5];
    protected float[] bq = new float[5];
    private OItemStack[] bU = new OItemStack[5];
    public boolean br = false;
    public int bs = 0;
    protected boolean bV = false; //CanaryMod: private -> protected
    protected boolean bW = false; //CanaryMod: private -> protected
    protected final OCombatTracker bt = new OCombatTracker(this);
    protected int bu;
    protected double bv;
    protected double bw;
    protected double bx;
    protected double by;
    protected double bz;
    float bA = 0.0F;
    protected int bB = 0;
    protected int bC = 0;
    protected float bD;
    protected float bE;
    protected float bF;
    protected boolean bG = false;
    protected float bH = 0.0F;
    protected float bI = 0.7F;
    private int bX = 0;
    private OEntity bY;
    protected int bJ = 0;

    // CanaryMod Start
    LivingEntity entity = new LivingEntity(this);
    protected MobSpawnerLogic spawner = null;
    public int maxHealth = 0;
    // CanaryMod end

    public OEntityLiving(OWorld oworld) {
        super(oworld);
        this.m = true;
        this.bo = new OEntityAITasks(oworld != null && oworld.C != null ? oworld.C : null);
        this.bp = new OEntityAITasks(oworld != null && oworld.C != null ? oworld.C : null);
        this.j = new OEntityLookHelper(this);
        this.bK = new OEntityMoveHelper(this);
        this.bL = new OEntityJumpHelper(this);
        this.bM = new OEntityBodyHelper(this);
        this.bN = new OPathNavigate(this, oworld, (float) this.ay());
        this.bP = new OEntitySenses(this);
        this.ax = (float) (Math.random() + 1.0D) * 0.01F;
        this.b(this.u, this.v, this.w);
        this.aw = (float) Math.random() * 12398.0F;
        this.A = (float) (Math.random() * 3.1415927410125732D * 2.0D);
        this.aA = this.A;

        for (int i = 0; i < this.bq.length; ++i) {
            this.bq[i] = 0.085F;
        }

        this.Y = 0.5F;
    }

    protected int ay() {
        return 16;
    }

    public OEntityLookHelper az() {
        return this.j;
    }

    public OEntityMoveHelper aA() {
        return this.bK;
    }

    public OEntityJumpHelper aB() {
        return this.bL;
    }

    public OPathNavigate aC() {
        return this.bN;
    }

    public OEntitySenses aD() {
        return this.bP;
    }

    public Random aE() {
        return this.ab;
    }

    public OEntityLiving aF() {
        return this.e;
    }

    public OEntityLiving aG() {
        return this.g;
    }

    public void l(OEntity oentity) {
        if (oentity instanceof OEntityLiving) {
            this.g = (OEntityLiving) oentity;
        }
    }

    public int aH() {
        return this.bC;
    }

    public float ao() {
        return this.aA;
    }

    public float aI() {
        return this.bQ;
    }

    public void e(float f) {
        this.bQ = f;
        this.f(f);
    }

    public boolean m(OEntity oentity) {
        this.l(oentity);
        return false;
    }

    public OEntityLiving aJ() {
        return this.bO;
    }

    public void b(OEntityLiving oentityliving) {
        // CanaryMod start: MOB_TARGET hook
        if (oentityliving != null && (Boolean) manager.callHook(PluginLoader.Hook.MOB_TARGET, oentityliving.getEntity(), this.getEntity())) {
            return;
        } // CanaryMod end
        this.bO = oentityliving;
    }

    public boolean a(Class oclass) {
        return OEntityCreeper.class != oclass && OEntityGhast.class != oclass;
    }

    public void aK() {}

    protected void a(double d0, boolean flag) {
        if (!this.G()) {
            this.H();
        }

        if (flag && this.T > 0.0F) {
            int i = OMathHelper.c(this.u);
            int j = OMathHelper.c(this.v - 0.20000000298023224D - (double) this.N);
            int k = OMathHelper.c(this.w);
            int l = this.q.a(i, j, k);

            if (l == 0) {
                int i1 = this.q.e(i, j - 1, k);

                if (i1 == 11 || i1 == 32 || i1 == 21) {
                    l = this.q.a(i, j - 1, k);
                }
            }

            if (l > 0) {
                OBlock.r[l].a(this.q, i, j, k, this, this.T);
            }
        }

        super.a(d0, flag);
    }

    public boolean aL() {
        return this.d(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w));
    }

    public boolean d(int i, int j, int k) {
        return this.bS == -1.0F ? true : this.bR.e(i, j, k) < this.bS * this.bS;
    }

    public void b(int i, int j, int k, int l) {
        this.bR.b(i, j, k);
        this.bS = (float) l;
    }

    public OChunkCoordinates aM() {
        return this.bR;
    }

    public float aN() {
        return this.bS;
    }

    public void aO() {
        this.bS = -1.0F;
    }

    public boolean aP() {
        return this.bS != -1.0F;
    }

    public void c(OEntityLiving oentityliving) {
        this.e = oentityliving;
        this.f = this.e != null ? 100 : 0;
    }

    protected void a() {
        this.ah.a(8, Integer.valueOf(this.i));
        this.ah.a(9, Byte.valueOf((byte) 0));
        this.ah.a(10, Byte.valueOf((byte) 0));
        this.ah.a(6, Byte.valueOf((byte) 0));
        this.ah.a(5, "");
    }

    public boolean n(OEntity oentity) {
        return this.q.a(this.q.T().a(this.u, this.v + (double) this.e(), this.w), this.q.T().a(oentity.u, oentity.v + (double) oentity.e(), oentity.w)) == null;
    }

    public boolean K() {
        return !this.M;
    }

    public boolean L() {
        return !this.M;
    }

    public float e() {
        return this.P * 0.85F;
    }

    public int aQ() {
        return 80;
    }

    public void aR() {
        String s = this.bb();

        if (s != null) {
            this.a(s, this.ba(), this.aY());
        }
    }

    public void x() {
        this.aQ = this.aR;
        super.x();
        this.q.C.a("mobBaseTick");
        if (this.R() && this.ab.nextInt(1000) < this.aV++) {
            this.aV = -this.aQ();
            this.aR();
        }

        if (this.R() && this.S()) {
            // CanaryMod Damage hook: Suffocation
            HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(null, this.entity, DamageType.SUFFOCATION.getDamageSource(), 1));
            if (!ev.isCanceled()) {
                this.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmount());
            }
        }

        if (this.E() || this.q.I) {
            this.A();
        }

        boolean flag = this instanceof OEntityPlayer && ((OEntityPlayer) this).ce.a;

        if (this.R() && this.a(OMaterial.h) && !this.bf() && !this.bn.containsKey(Integer.valueOf(OPotion.o.H)) && !flag) {
            this.g(this.h(this.ak()));
            if (this.ak() == -20) {
                this.g(0);

                // CanaryMod Damage hook: Drowning
                HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(null, this.entity, DamageType.WATER.getDamageSource(), 2));
                if (!ev.isCanceled()) {
                    for (int i = 0; i < 8; ++i) {
                    float f = this.ab.nextFloat() - this.ab.nextFloat();
                    float f1 = this.ab.nextFloat() - this.ab.nextFloat();
                    float f2 = this.ab.nextFloat() - this.ab.nextFloat();

                    this.q.a("bubble", this.u + (double) f, this.v + (double) f1, this.w + (double) f2, this.x, this.y, this.z);
                    }

                    this.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmount());
                }
            }

            this.A();
        } else {
            this.g(300);
        }

        this.bb = this.bc;
        if (this.ba > 0) {
            --this.ba;
        }

        if (this.aW > 0) {
            --this.aW;
        }

        if (this.af > 0) {
            --this.af;
        }

        if (this.aS <= 0) {
            this.aS();
        }

        if (this.bl > 0) {
            --this.bl;
        } else {
            this.bk = null;
        }

        if (this.g != null && !this.g.R()) {
            this.g = null;
        }

        if (this.e != null) {
            if (!this.e.R()) {
                this.c((OEntityLiving) null);
            } else if (this.f > 0) {
                --this.f;
            } else {
                this.c((OEntityLiving) null);
            }
        }

        this.bA();
        this.aF = this.aE;
        this.az = this.ay;
        this.aB = this.aA;
        this.C = this.A;
        this.D = this.B;
        this.q.C.b();
    }

    protected void aS() {
        ++this.aZ;
        if (this.aZ == 20) {
            int i;

            if (!this.q.I && (this.bl > 0 || this.aT()) && !this.h_() && this.q.M().b("doMobLoot")) {
                i = this.d(this.bk);

                while (i > 0) {
                    int j = OEntityXPOrb.a(i);

                    i -= j;
                    this.q.d((OEntity) (new OEntityXPOrb(this.q, this.u, this.v, this.w, j)));
                }
            }

            this.w();

            for (i = 0; i < 20; ++i) {
                double d0 = this.ab.nextGaussian() * 0.02D;
                double d1 = this.ab.nextGaussian() * 0.02D;
                double d2 = this.ab.nextGaussian() * 0.02D;

                this.q.a("explode", this.u + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, this.v + (double) (this.ab.nextFloat() * this.P), this.w + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O, d0, d1, d2);
            }
        }
    }

    protected int h(int i) {
        int j = OEnchantmentHelper.b(this);

        return j > 0 && this.ab.nextInt(j + 1) > 0 ? i : i - 1;
    }

    protected int d(OEntityPlayer oentityplayer) {
        if (this.be > 0) {
            int i = this.be;
            OItemStack[] aoitemstack = this.ad();

            for (int j = 0; j < aoitemstack.length; ++j) {
                if (aoitemstack[j] != null && this.bq[j] <= 1.0F) {
                    i += 1 + this.ab.nextInt(3);
                }
            }

            return i;
        } else {
            return this.be;
        }
    }

    protected boolean aT() {
        return false;
    }

    public void aU() {
        for (int i = 0; i < 20; ++i) {
            double d0 = this.ab.nextGaussian() * 0.02D;
            double d1 = this.ab.nextGaussian() * 0.02D;
            double d2 = this.ab.nextGaussian() * 0.02D;
            double d3 = 10.0D;

            this.q.a("explode", this.u + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O - d0 * d3, this.v + (double) (this.ab.nextFloat() * this.P) - d1 * d3, this.w + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O - d2 * d3, d0, d1, d2);
        }
    }

    public void T() {
        super.T();
        this.aC = this.aD;
        this.aD = 0.0F;
        this.T = 0.0F;
    }

    public void l_() {
        super.l_();
        if (!this.q.I) {
            int i;

            for (i = 0; i < 5; ++i) {
                OItemStack oitemstack = this.p(i);

                if (!OItemStack.b(oitemstack, this.bU[i])) {
                    ((OWorldServer) this.q).p().a((OEntity) this, (OPacket) (new OPacket5PlayerInventory(this.k, i, oitemstack)));
                    this.bU[i] = oitemstack == null ? null : oitemstack.m();
                }
            }

            i = this.bM();
            if (i > 0) {
                if (this.bm <= 0) {
                    this.bm = 20 * (30 - i);
                }

                --this.bm;
                if (this.bm <= 0) {
                    this.r(i - 1);
                }
            }
        }

        this.c();
        double d0 = this.u - this.r;
        double d1 = this.w - this.t;
        float f = (float) (d0 * d0 + d1 * d1);
        float f1 = this.ay;
        float f2 = 0.0F;

        this.aC = this.aD;
        float f3 = 0.0F;

        if (f > 0.0025000002F) {
            f3 = 1.0F;
            f2 = (float) Math.sqrt((double) f) * 3.0F;
            f1 = (float) Math.atan2(d1, d0) * 180.0F / 3.1415927F - 90.0F;
        }

        if (this.aR > 0.0F) {
            f1 = this.A;
        }

        if (!this.F) {
            f3 = 0.0F;
        }

        this.aD += (f3 - this.aD) * 0.3F;
        this.q.C.a("headTurn");
        if (this.bh()) {
            this.bM.a();
        } else {
            float f4 = OMathHelper.g(f1 - this.ay);

            this.ay += f4 * 0.3F;
            float f5 = OMathHelper.g(this.A - this.ay);
            boolean flag = f5 < -90.0F || f5 >= 90.0F;

            if (f5 < -75.0F) {
                f5 = -75.0F;
            }

            if (f5 >= 75.0F) {
                f5 = 75.0F;
            }

            this.ay = this.A - f5;
            if (f5 * f5 > 2500.0F) {
                this.ay += f5 * 0.2F;
            }

            if (flag) {
                f2 *= -1.0F;
            }
        }

        this.q.C.b();
        this.q.C.a("rangeChecks");

        while (this.A - this.C < -180.0F) {
            this.C -= 360.0F;
        }

        while (this.A - this.C >= 180.0F) {
            this.C += 360.0F;
        }

        while (this.ay - this.az < -180.0F) {
            this.az -= 360.0F;
        }

        while (this.ay - this.az >= 180.0F) {
            this.az += 360.0F;
        }

        while (this.B - this.D < -180.0F) {
            this.D -= 360.0F;
        }

        while (this.B - this.D >= 180.0F) {
            this.D += 360.0F;
        }

        while (this.aA - this.aB < -180.0F) {
            this.aB -= 360.0F;
        }

        while (this.aA - this.aB >= 180.0F) {
            this.aB += 360.0F;
        }

        this.q.C.b();
        this.aE += f2;
    }

    public void j(int i) {
        if (this.aS > 0) {
            this.b(this.aX() + i);
            if (this.aS > this.aW()) {
                this.b(this.aW());
            }

            this.af = this.av / 2;
        }
    }

    public abstract int aW();

    public int aX() {
        return this.aS;
    }

    public void b(int i) {
        this.aS = i;
        if (i > this.aW()) {
            i = this.aW();
        }
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.aq()) {
            return false;
        } else if (this.q.I) {
            return false;
        } else {
            this.bC = 0;
            if (this.aS <= 0) {
                return false;
            } else if (odamagesource.m() && this.a(OPotion.n)) {
                return false;
            } else {
                if ((odamagesource == ODamageSource.m || odamagesource == ODamageSource.n) && this.p(4) != null) {
                    this.p(4).a(i * 4 + this.ab.nextInt(i * 2), this);
                    i = (int) ((float) i * 0.75F);
                }

                this.bi = 1.5F;

                LivingEntity attacker = null;

                if ((odamagesource != null) && odamagesource instanceof OEntityDamageSource && ((OEntityDamageSource)odamagesource).h() instanceof OEntityLiving) {
                    OEntityLiving ent = (OEntityLiving) ((OEntityDamageSource)odamagesource).h();
                    attacker = ent.getEntity();
                }
                if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.ATTACK, attacker, this.entity, i)) {
                    if (this instanceof OEntityCreature) {
                        ((OEntityCreature) this).c = 0;
                    }
                    return false;
                }

                boolean flag = true;

                if ((float) this.af > (float) this.av / 2.0F) {
                    if (i <= this.bB) {
                        return false;
                    }

                    HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(attacker, this.entity, odamagesource.getDamageSource(), i - this.bB));
                    if (attacker != null && ev.isCanceled()) {
                        return false;
                    }
                    this.d(ev.getDamageSource().getDamageSource(), ev.getDamageAmount());
                    this.bB = i;

                    flag = false;
                } else {
                    HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(attacker, this.entity, odamagesource.getDamageSource(), i));
                    if (attacker != null && ev.isCanceled()) {
                        return false;
                    }
                    this.bB = i;
                    this.aT = this.aS;
                    this.af = this.av;
                    this.d(ev.getDamageSource().getDamageSource(), ev.getDamageAmount());
                    this.aW = this.aX = 10;
                }

                this.aY = 0.0F;
                OEntity oentity = odamagesource.i();

                if (oentity != null) {
                    if (oentity instanceof OEntityLiving) {
                        this.c((OEntityLiving) oentity);
                    }

                    if (oentity instanceof OEntityPlayer) {
                        this.bl = 100;
                        this.bk = (OEntityPlayer) oentity;
                    } else if (oentity instanceof OEntityWolf) {
                        OEntityWolf oentitywolf = (OEntityWolf) oentity;

                        if (oentitywolf.m()) {
                            this.bl = 100;
                            this.bk = null;
                        }
                    }
                }

                if (flag) {
                    this.q.a(this, (byte) 2);
                    if (odamagesource != ODamageSource.e) {
                        this.J();
                    }

                    if (oentity != null) {
                        double d0 = oentity.u - this.u;

                        double d1;

                        for (d1 = oentity.w - this.w; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
                            d0 = (Math.random() - Math.random()) * 0.01D;
                        }

                        this.aY = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - this.A;
                        this.a(oentity, i, d0, d1);
                    } else {
                        this.aY = (float) ((int) (Math.random() * 2.0D) * 180);
                    }
                }

                if (this.aS <= 0) {
                    if (flag) {
                        this.a(this.bd(), this.ba(), this.aY());
                    }

                    this.a(odamagesource);
                } else if (flag) {
                    this.a(this.bc(), this.ba(), this.aY());
                }

                return true;
            }
        }
    }

    protected float aY() {
        return this.h_() ? (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.5F : (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F;
    }

    public int aZ() {
        int i = 0;
        OItemStack[] aoitemstack = this.ad();
        int j = aoitemstack.length;

        for (int k = 0; k < j; ++k) {
            OItemStack oitemstack = aoitemstack[k];

            if (oitemstack != null && oitemstack.b() instanceof OItemArmor) {
                int l = ((OItemArmor) oitemstack.b()).c;

                i += l;
            }
        }

        return i;
    }

    protected void k(int i) {}

    protected int b(ODamageSource odamagesource, int i) {
        if (!odamagesource.e()) {
            int j = 25 - this.aZ();
            int k = i * j + this.aU;

            this.k(i);
            i = k / 25;
            this.aU = k % 25;
        }

        return i;
    }

    protected int c(ODamageSource odamagesource, int i) {
        int j;
        int k;
        int l;

        if (this.a(OPotion.m)) {
            j = (this.b(OPotion.m).c() + 1) * 5;
            k = 25 - j;
            l = i * k + this.aU;
            i = l / 25;
            this.aU = l % 25;
        }

        if (i <= 0) {
            return 0;
        } else {
            j = OEnchantmentHelper.a(this.ad(), odamagesource);
            if (j > 20) {
                j = 20;
            }

            if (j > 0 && j <= 20) {
                k = 25 - j;
                l = i * k + this.aU;
                i = l / 25;
                this.aU = l % 25;
            }

            return i;
        }
    }

    protected void d(ODamageSource odamagesource, int i) {
        if (!this.aq()) {
            i = this.b(odamagesource, i);
            i = this.c(odamagesource, i);
            int j = this.aX();

            this.aS -= i;
            this.bt.a(odamagesource, j, i);
        }
    }

    protected float ba() {
        return 1.0F;
    }

    protected String bb() {
        return null;
    }

    protected String bc() {
        return "damage.hit";
    }

    protected String bd() {
        return "damage.hit";
    }

    public void a(OEntity oentity, int i, double d0, double d1) {
        this.an = true;
        float f = OMathHelper.a(d0 * d0 + d1 * d1);
        float f1 = 0.4F;

        this.x /= 2.0D;
        this.y /= 2.0D;
        this.z /= 2.0D;
        this.x -= d0 / (double) f * (double) f1;
        this.y += (double) f1;
        this.z -= d1 / (double) f * (double) f1;
        if (this.y > 0.4000000059604645D) {
            this.y = 0.4000000059604645D;
        }
    }

    public void a(ODamageSource odamagesource) {
        // CanaryMod: call DEATH hook
        manager.callHook(PluginLoader.Hook.DEATH, this.getEntity());//

        OEntity oentity = odamagesource.i();
        OEntityLiving oentityliving = this.bN();

        if (this.aM >= 0 && oentityliving != null) {
            oentityliving.c(this, this.aM);
        }

        if (oentity != null) {
            oentity.a(this);
        }

        this.bd = true;
        if (!this.q.I) {
            int i = 0;

            if (oentity instanceof OEntityPlayer) {
                i = OEnchantmentHelper.g((OEntityLiving) oentity);
            }

            if (!this.h_() && this.q.M().b("doMobLoot")) {
                this.a(this.bl > 0, i);
                this.b(this.bl > 0, i);
                if (this.bl > 0) {
                    int j = this.ab.nextInt(200) - i;

                    if (j < 5) {
                        this.l(j <= 0 ? 1 : 0);
                    }
                }
            }
        }

        this.q.a(this, (byte) 3);
    }

    protected void l(int i) {}

    protected void a(boolean flag, int i) {
        int j = this.be();

        if (j > 0) {
            int k = this.ab.nextInt(3);

            if (i > 0) {
                k += this.ab.nextInt(i + 1);
            }

            for (int l = 0; l < k; ++l) {
                this.b(j, 1);
            }
        }
    }

    protected int be() {
        return 0;
    }

    protected void a(float f) {
        super.a(f);
        int i = OMathHelper.f(f - 3.0F);

        if (i > 0) {
            // CanaryMod Damage hook: Falling
            HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(null, this.entity, DamageType.FALL.getDamageSource(), i));
            if (!ev.isCanceled()) {
                if (i > 4) {
                    this.a("damage.fallbig", 1.0F, 1.0F);
                } else {
                    this.a("damage.fallsmall", 1.0F, 1.0F);
                }

                this.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmount());
            }
            int j = this.q.a(OMathHelper.c(this.u), OMathHelper.c(this.v - 0.20000000298023224D - (double) this.N), OMathHelper.c(this.w));

            if (j > 0) {
                OStepSound ostepsound = OBlock.r[j].cM;

                this.a(ostepsound.e(), ostepsound.c() * 0.5F, ostepsound.d() * 0.75F);
            }
        }
    }

    public void e(float f, float f1) {
        double d0;

        if (this.G() && (!(this instanceof OEntityPlayer) || !((OEntityPlayer) this).ce.b)) {
            d0 = this.v;
            this.a(f, f1, this.bh() ? 0.04F : 0.02F);
            this.d(this.x, this.y, this.z);
            this.x *= 0.800000011920929D;
            this.y *= 0.800000011920929D;
            this.z *= 0.800000011920929D;
            this.y -= 0.02D;
            if (this.G && this.c(this.x, this.y + 0.6000000238418579D - this.v + d0, this.z)) {
                this.y = 0.30000001192092896D;
            }
        } else if (this.I() && (!(this instanceof OEntityPlayer) || !((OEntityPlayer) this).ce.b)) {
            d0 = this.v;
            this.a(f, f1, 0.02F);
            this.d(this.x, this.y, this.z);
            this.x *= 0.5D;
            this.y *= 0.5D;
            this.z *= 0.5D;
            this.y -= 0.02D;
            if (this.G && this.c(this.x, this.y + 0.6000000238418579D - this.v + d0, this.z)) {
                this.y = 0.30000001192092896D;
            }
        } else {
            float f2 = 0.91F;

            if (this.F) {
                f2 = 0.54600006F;
                int i = this.q.a(OMathHelper.c(this.u), OMathHelper.c(this.E.b) - 1, OMathHelper.c(this.w));

                if (i > 0) {
                    f2 = OBlock.r[i].cP * 0.91F;
                }
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);
            float f4;

            if (this.F) {
                if (this.bh()) {
                    f4 = this.aI();
                } else {
                    f4 = this.aO;
                }

                f4 *= f3;
            } else {
                f4 = this.aP;
            }

            this.a(f, f1, f4);
            f2 = 0.91F;
            if (this.F) {
                f2 = 0.54600006F;
                int j = this.q.a(OMathHelper.c(this.u), OMathHelper.c(this.E.b) - 1, OMathHelper.c(this.w));

                if (j > 0) {
                    f2 = OBlock.r[j].cP * 0.91F;
                }
            }

            if (this.g_()) {
                float f5 = 0.15F;

                if (this.x < (double) (-f5)) {
                    this.x = (double) (-f5);
                }

                if (this.x > (double) f5) {
                    this.x = (double) f5;
                }

                if (this.z < (double) (-f5)) {
                    this.z = (double) (-f5);
                }

                if (this.z > (double) f5) {
                    this.z = (double) f5;
                }

                this.T = 0.0F;
                if (this.y < -0.15D) {
                    this.y = -0.15D;
                }

                boolean flag = this.ag() && this instanceof OEntityPlayer;

                if (flag && this.y < 0.0D) {
                    this.y = 0.0D;
                }
            }

            this.d(this.x, this.y, this.z);
            if (this.G && this.g_()) {
                this.y = 0.2D;
            }

            if (this.q.I && (!this.q.f((int) this.u, 0, (int) this.w) || !this.q.d((int) this.u, (int) this.w).d)) {
                if (this.v > 0.0D) {
                    this.y = -0.1D;
                } else {
                    this.y = 0.0D;
                }
            } else {
                this.y -= 0.08D;
            }

            this.y *= 0.9800000190734863D;
            this.x *= (double) f2;
            this.z *= (double) f2;
        }

        this.bh = this.bi;
        d0 = this.u - this.r;
        double d1 = this.w - this.t;
        float f6 = OMathHelper.a(d0 * d0 + d1 * d1) * 4.0F;

        if (f6 > 1.0F) {
            f6 = 1.0F;
        }

        this.bi += (f6 - this.bi) * 0.4F;
        this.bj += this.bi;
    }

    public boolean g_() {
        int i = OMathHelper.c(this.u);
        int j = OMathHelper.c(this.E.b);
        int k = OMathHelper.c(this.w);
        int l = this.q.a(i, j, k);

        return l == OBlock.aJ.cz || l == OBlock.by.cz;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        if (this.aS < -32768) {
            this.aS = -32768;
        }

        onbttagcompound.a("Health", (short) this.aS);
        onbttagcompound.a("HurtTime", (short) this.aW);
        onbttagcompound.a("DeathTime", (short) this.aZ);
        onbttagcompound.a("AttackTime", (short) this.ba);
        onbttagcompound.a("CanPickUpLoot", this.bS());
        onbttagcompound.a("PersistenceRequired", this.bW);
        ONBTTagList onbttaglist = new ONBTTagList();

        for (int i = 0; i < this.bT.length; ++i) {
            ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

            if (this.bT[i] != null) {
                this.bT[i].b(onbttagcompound1);
            }

                onbttaglist.a((ONBTBase) onbttagcompound1);
            }

        onbttagcompound.a("Equipment", (ONBTBase) onbttaglist);
        ONBTTagList onbttaglist1;

        if (!this.bn.isEmpty()) {
            onbttaglist1 = new ONBTTagList();
            Iterator iterator = this.bn.values().iterator();

            while (iterator.hasNext()) {
                OPotionEffect opotioneffect = (OPotionEffect) iterator.next();

                onbttaglist1.a((ONBTBase) opotioneffect.a(new ONBTTagCompound()));
            }

            onbttagcompound.a("ActiveEffects", (ONBTBase) onbttaglist1);
        }

        onbttaglist1 = new ONBTTagList();

        for (int j = 0; j < this.bq.length; ++j) {
            onbttaglist1.a((ONBTBase) (new ONBTTagFloat(j + "", this.bq[j])));
        }

        onbttagcompound.a("DropChances", (ONBTBase) onbttaglist1);
        onbttagcompound.a("CustomName", this.bO());
        onbttagcompound.a("CustomNameVisible", this.bQ());
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.aS = onbttagcompound.d("Health");
        if (!onbttagcompound.b("Health")) {
            this.aS = this.aW();
        }

        this.aW = onbttagcompound.d("HurtTime");
        this.aZ = onbttagcompound.d("DeathTime");
        this.ba = onbttagcompound.d("AttackTime");
        this.h(onbttagcompound.n("CanPickUpLoot"));
        this.bW = onbttagcompound.n("PersistenceRequired");
        if (onbttagcompound.b("CustomName") && onbttagcompound.i("CustomName").length() > 0) {
            this.c(onbttagcompound.i("CustomName"));
        }

        this.g(onbttagcompound.n("CustomNameVisible"));
        ONBTTagList onbttaglist;
        int i;

        if (onbttagcompound.b("Equipment")) {
            onbttaglist = onbttagcompound.m("Equipment");

            for (i = 0; i < this.bT.length; ++i) {
                this.bT[i] = OItemStack.a((ONBTTagCompound) onbttaglist.b(i));
            }
        }

        if (onbttagcompound.b("ActiveEffects")) {
            onbttaglist = onbttagcompound.m("ActiveEffects");

            for (i = 0; i < onbttaglist.c(); ++i) {
                ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.b(i);
                OPotionEffect opotioneffect = OPotionEffect.b(onbttagcompound1);

                this.bn.put(Integer.valueOf(opotioneffect.a()), opotioneffect);
            }
        }

        if (onbttagcompound.b("DropChances")) {
            onbttaglist = onbttagcompound.m("DropChances");

            for (i = 0; i < onbttaglist.c(); ++i) {
                this.bq[i] = ((ONBTTagFloat) onbttaglist.b(i)).a;
            }
        }
    }

    public boolean R() {
        return !this.M && this.aS > 0;
    }

    public boolean bf() {
        return false;
    }

    public void f(float f) {
        this.bE = f;
    }

    public void f(boolean flag) {
        this.bG = flag;
    }

    public void c() {
        if (this.bX > 0) {
            --this.bX;
        }

        if (this.bu > 0) {
            double d0 = this.u + (this.bv - this.u) / (double) this.bu;
            double d1 = this.v + (this.bw - this.v) / (double) this.bu;
            double d2 = this.w + (this.bx - this.w) / (double) this.bu;
            double d3 = OMathHelper.g(this.by - (double) this.A);

            this.A = (float) ((double) this.A + d3 / (double) this.bu);
            this.B = (float) ((double) this.B + (this.bz - (double) this.B) / (double) this.bu);
            --this.bu;
            this.b(d0, d1, d2);
            this.b(this.A, this.B);
        } else if (!this.bi()) {
            this.x *= 0.98D;
            this.y *= 0.98D;
            this.z *= 0.98D;
        }

        if (Math.abs(this.x) < 0.005D) {
            this.x = 0.0D;
        }

        if (Math.abs(this.y) < 0.005D) {
            this.y = 0.0D;
        }

        if (Math.abs(this.z) < 0.005D) {
            this.z = 0.0D;
        }

        this.q.C.a("ai");
        if (this.bj()) {
            this.bG = false;
            this.bD = 0.0F;
            this.bE = 0.0F;
            this.bF = 0.0F;
        } else if (this.bi()) {
            if (this.bh()) {
                this.q.C.a("newAi");
                this.bo();
                this.q.C.b();
            } else {
                this.q.C.a("oldAi");
                this.bq();
                this.q.C.b();
                this.aA = this.A;
            }
        }

        this.q.C.b();
        this.q.C.a("jump");
        if (this.bG) {
            if (!this.G() && !this.I()) {
                if (this.F && this.bX == 0) {
                    this.bl();
                    this.bX = 10;
                }
            } else {
                this.y += 0.03999999910593033D;
            }
        } else {
            this.bX = 0;
        }

        this.q.C.b();
        this.q.C.a("travel");
        this.bD *= 0.98F;
        this.bE *= 0.98F;
        this.bF *= 0.9F;
        float f = this.aO;

        this.aO *= this.bE();
        this.e(this.bD, this.bE);
        this.aO = f;
        this.q.C.b();
        this.q.C.a("push");
        if (!this.q.I) {
            this.bg();
        }

        this.q.C.b();
        this.q.C.a("looting");
        if (!this.q.I && this.bS() && !this.bd && this.q.M().b("mobGriefing")) {
            List list = this.q.a(OEntityItem.class, this.E.b(1.0D, 0.0D, 1.0D));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                OEntityItem oentityitem = (OEntityItem) iterator.next();

                if (!oentityitem.M && oentityitem.d() != null) {
                    OItemStack oitemstack = oentityitem.d();
                    int i = b(oitemstack);

                    if (i > -1) {
                        boolean flag = true;
                        OItemStack oitemstack1 = this.p(i);

                        if (oitemstack1 != null) {
                            if (i == 0) {
                                if (oitemstack.b() instanceof OItemSword && !(oitemstack1.b() instanceof OItemSword)) {
                                    flag = true;
                                } else if (oitemstack.b() instanceof OItemSword && oitemstack1.b() instanceof OItemSword) {
                                    OItemSword oitemsword = (OItemSword) oitemstack.b();
                                    OItemSword oitemsword1 = (OItemSword) oitemstack1.b();

                                    if (oitemsword.g() == oitemsword1.g()) {
                                        flag = oitemstack.k() > oitemstack1.k() || oitemstack.p() && !oitemstack1.p();
                                    } else {
                                        flag = oitemsword.g() > oitemsword1.g();
                                    }
                                } else {
                                    flag = false;
                                }
                            } else if (oitemstack.b() instanceof OItemArmor && !(oitemstack1.b() instanceof OItemArmor)) {
                                flag = true;
                            } else if (oitemstack.b() instanceof OItemArmor && oitemstack1.b() instanceof OItemArmor) {
                                OItemArmor oitemarmor = (OItemArmor) oitemstack.b();
                                OItemArmor oitemarmor1 = (OItemArmor) oitemstack1.b();

                                if (oitemarmor.c == oitemarmor1.c) {
                                    flag = oitemstack.k() > oitemstack1.k() || oitemstack.p() && !oitemstack1.p();
                                } else {
                                    flag = oitemarmor.c > oitemarmor1.c;
                                }
                            } else {
                                flag = false;
                            }
                        }

                        if (flag) {
                            if (oitemstack1 != null && this.ab.nextFloat() - 0.1F < this.bq[i]) {
                                this.a(oitemstack1, 0.0F);
                            }

                            this.c(i, oitemstack);
                            this.bq[i] = 2.0F;
                            this.bW = true;
                            this.a((OEntity) oentityitem, 1);
                            oentityitem.w();
                        }
                    }
                }
            }
        }

        this.q.C.b();
    }

    protected void bg() {
        List list = this.q.b((OEntity) this, this.E.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); ++i) {
                OEntity oentity = (OEntity) list.get(i);

                if (oentity.L()) {
                    this.o(oentity);
                }
            }
        }
    }

    protected void o(OEntity oentity) {
        oentity.f((OEntity) this);
    }

    protected boolean bh() {
        return false;
    }

    protected boolean bi() {
        return !this.q.I;
    }

    protected boolean bj() {
        return this.aS <= 0;
    }

    public boolean bk() {
        return false;
    }

    protected void bl() {
        this.y = 0.41999998688697815D;
        if (this.a(OPotion.j)) {
            this.y += (double) ((float) (this.b(OPotion.j).c() + 1) * 0.1F);
        }

        if (this.ah()) {
            float f = this.A * 0.017453292F;

            this.x -= (double) (OMathHelper.a(f) * 0.2F);
            this.z += (double) (OMathHelper.b(f) * 0.2F);
        }

        this.an = true;
    }

    protected boolean bm() {
        return true;
    }

    protected void bn() {
        if (!this.bW) {
            OEntityPlayer oentityplayer = this.q.a(this, -1.0D);

            if (oentityplayer != null) {
                double d0 = oentityplayer.u - this.u;
                double d1 = oentityplayer.v - this.v;
                double d2 = oentityplayer.w - this.w;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (this.bm() && d3 > 16384.0D) {
                    if (!(Boolean) OEntityLiving.manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.entity)) {
                        this.w();
                    }

                }

                if (this.bC > 600 && this.ab.nextInt(800) == 0 && d3 > 1024.0D && this.bm()) {
                    // CanaryMod hook onEntityDespawn
                    if (!(Boolean) OEntityLiving.manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.entity)) {
                        this.w();
                    } else {
                        this.bB = 0;
                    }
                } else if (d3 < 1024.0D) {
                    this.bC = 0;
                }
            }
        }
    }

    protected void bo() {
        ++this.bC;
        this.q.C.a("checkDespawn");
        this.bn();
        this.q.C.b();
        this.q.C.a("sensing");
        this.bP.a();
        this.q.C.b();
        this.q.C.a("targetSelector");
        this.bp.a();
        this.q.C.b();
        this.q.C.a("goalSelector");
        this.bo.a();
        this.q.C.b();
        this.q.C.a("navigation");
        this.bN.e();
        this.q.C.b();
        this.q.C.a("mob tick");
        this.bp();
        this.q.C.b();
        this.q.C.a("controls");
        this.q.C.a("move");
        this.bK.c();
        this.q.C.c("look");
        this.j.a();
        this.q.C.c("jump");
        this.bL.b();
        this.q.C.b();
        this.q.C.b();
    }

    protected void bp() {}

    protected void bq() {
        ++this.bC;
        this.bn();
        this.bD = 0.0F;
        this.bE = 0.0F;
        float f = 8.0F;

        if (this.ab.nextFloat() < 0.02F) {
            OEntityPlayer oentityplayer = this.q.a(this, (double) f);

            if (oentityplayer != null) {
                this.bY = oentityplayer;
                this.bJ = 10 + this.ab.nextInt(20);
            } else {
                this.bF = (this.ab.nextFloat() - 0.5F) * 20.0F;
            }
        }

        if (this.bY != null) {
            this.a(this.bY, 10.0F, (float) this.bs());
            if (this.bJ-- <= 0 || this.bY.M || this.bY.e((OEntity) this) > (double) (f * f)) {
                this.bY = null;
            }
        } else {
            if (this.ab.nextFloat() < 0.05F) {
                this.bF = (this.ab.nextFloat() - 0.5F) * 20.0F;
            }

            this.A += this.bF;
            this.B = this.bH;
        }

        boolean flag = this.G();
        boolean flag1 = this.I();

        if (flag || flag1) {
            this.bG = this.ab.nextFloat() < 0.8F;
        }
    }

    protected void br() {
        int i = this.h();

        if (this.br) {
            ++this.bs;
            if (this.bs >= i) {
                this.bs = 0;
                this.br = false;
            }
        } else {
            this.bs = 0;
        }

        this.aR = (float) this.bs / (float) i;
    }

    public int bs() {
        return 40;
    }

    public void a(OEntity oentity, float f, float f1) {
        double d0 = oentity.u - this.u;
        double d1 = oentity.w - this.w;
        double d2;

        if (oentity instanceof OEntityLiving) {
            OEntityLiving oentityliving = (OEntityLiving) oentity;

            d2 = oentityliving.v + (double) oentityliving.e() - (this.v + (double) this.e());
        } else {
            d2 = (oentity.E.b + oentity.E.e) / 2.0D - (this.v + (double) this.e());
        }

        double d3 = (double) OMathHelper.a(d0 * d0 + d1 * d1);
        float f2 = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
        float f3 = (float) (-(Math.atan2(d2, d3) * 180.0D / 3.1415927410125732D));

        this.B = this.b(this.B, f3, f1);
        this.A = this.b(this.A, f2, f);
    }

    private float b(float f, float f1, float f2) {
        float f3 = OMathHelper.g(f1 - f);

        if (f3 > f2) {
            f3 = f2;
        }

        if (f3 < -f2) {
            f3 = -f2;
        }

        return f + f3;
    }

    public boolean bv() {
        return this.q.b(this.E) && this.q.a((OEntity) this, this.E).isEmpty() && !this.q.d(this.E);
    }

    protected void B() {
        this.a(ODamageSource.i, 4);
    }

    public OVec3 Y() {
        return this.i(1.0F);
    }

    public OVec3 i(float f) {
        float f1;
        float f2;
        float f3;
        float f4;

        if (f == 1.0F) {
            f1 = OMathHelper.b(-this.A * 0.017453292F - 3.1415927F);
            f2 = OMathHelper.a(-this.A * 0.017453292F - 3.1415927F);
            f3 = -OMathHelper.b(-this.B * 0.017453292F);
            f4 = OMathHelper.a(-this.B * 0.017453292F);
            return this.q.T().a((double) (f2 * f3), (double) f4, (double) (f1 * f3));
        } else {
            f1 = this.D + (this.B - this.D) * f;
            f2 = this.C + (this.A - this.C) * f;
            f3 = OMathHelper.b(-f2 * 0.017453292F - 3.1415927F);
            f4 = OMathHelper.a(-f2 * 0.017453292F - 3.1415927F);
            float f5 = -OMathHelper.b(-f1 * 0.017453292F);
            float f6 = OMathHelper.a(-f1 * 0.017453292F);

            return this.q.T().a((double) (f4 * f5), (double) f6, (double) (f3 * f5));
        }
    }

    public int by() {
        return 4;
    }

    public boolean bz() {
        return false;
    }

    protected void bA() {
        Iterator iterator = ((HashMap) this.bn.clone()).keySet().iterator(); //CanaryMod: hashmap is cloned to prevent concurrent modification exceptions

        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            OPotionEffect opotioneffect = (OPotionEffect) this.bn.get(integer);

            if (!opotioneffect.a(this)) {
                if (!this.q.I) {
                    this.bn.remove(integer); //CanaryMod: changed from iterator.remove() to coincide with cloning
                    this.c(opotioneffect);
                }
            } else if (opotioneffect.b() % 600 == 0) {
                this.b(opotioneffect);
            }
        }

        int i;

        if (this.h) {
            if (!this.q.I) {
                if (this.bn.isEmpty()) {
                    this.ah.b(9, Byte.valueOf((byte) 0));
                    this.ah.b(8, Integer.valueOf(0));
                    this.d(false);
                } else {
                    i = OPotionHelper.a(this.bn.values());
                    this.ah.b(9, Byte.valueOf((byte) (OPotionHelper.b(this.bn.values()) ? 1 : 0)));
                    this.ah.b(8, Integer.valueOf(i));
                    this.d(this.m(OPotion.p.H));
                }
            }

            this.h = false;
        }

        i = this.ah.c(8);
        boolean flag = this.ah.a(9) > 0;

        if (i > 0) {
            boolean flag1 = false;

            if (!this.ai()) {
                flag1 = this.ab.nextBoolean();
            } else {
                flag1 = this.ab.nextInt(15) == 0;
            }

            if (flag) {
                flag1 &= this.ab.nextInt(5) == 0;
            }

            if (flag1 && i > 0) {
                double d0 = (double) (i >> 16 & 255) / 255.0D;
                double d1 = (double) (i >> 8 & 255) / 255.0D;
                double d2 = (double) (i >> 0 & 255) / 255.0D;

                this.q.a(flag ? "mobSpellAmbient" : "mobSpell", this.u + (this.ab.nextDouble() - 0.5D) * (double) this.O, this.v + this.ab.nextDouble() * (double) this.P - (double) this.N, this.w + (this.ab.nextDouble() - 0.5D) * (double) this.O, d0, d1, d2);
            }
        }
    }

    public void bB() {
        Iterator iterator = this.bn.keySet().iterator();

        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            OPotionEffect opotioneffect = (OPotionEffect) this.bn.get(integer);

            if (!this.q.I) {
                iterator.remove();
                this.c(opotioneffect);
            }
        }
    }

    public Collection bC() {
        return this.bn.values();
    }

    public boolean m(int i) {
        return this.bn.containsKey(Integer.valueOf(i));
    }

    public boolean a(OPotion opotion) {
        return this.bn.containsKey(Integer.valueOf(opotion.H));
    }

    public OPotionEffect b(OPotion opotion) {
        return (OPotionEffect) this.bn.get(Integer.valueOf(opotion.H));
    }

    public void d(OPotionEffect opotioneffect) {
        // CanaryMod - POTION_EFFECT HOOK
        PotionEffect pe = (PotionEffect) etc.getLoader().callHook(PluginLoader.Hook.POTION_EFFECT, this.entity, opotioneffect.potionEffect);

        if (pe == null) {
            return;
        }
        opotioneffect = pe.potionEffect;
        if (this.e(opotioneffect)) {
            if (this.bn.containsKey(Integer.valueOf(opotioneffect.a()))) {
                ((OPotionEffect) this.bn.get(Integer.valueOf(opotioneffect.a()))).a(opotioneffect);
                this.b((OPotionEffect) this.bn.get(Integer.valueOf(opotioneffect.a())));
            } else {
                this.bn.put(Integer.valueOf(opotioneffect.a()), opotioneffect);
                this.a(opotioneffect);
            }
        }
    }

    public boolean e(OPotionEffect opotioneffect) {
        if (this.bF() == OEnumCreatureAttribute.b) {
            int i = opotioneffect.a();

            if (i == OPotion.l.H || i == OPotion.u.H) {
                return false;
            }
        }

        return true;
    }

    public boolean bD() {
        return this.bF() == OEnumCreatureAttribute.b;
    }

    public void o(int i) {
        OPotionEffect opotioneffect = (OPotionEffect) this.bn.remove(Integer.valueOf(i));

        if (opotioneffect != null) {
            this.c(opotioneffect);
        }
    }

    protected void a(OPotionEffect opotioneffect) {
        this.h = true;
    }

    protected void b(OPotionEffect opotioneffect) {
        this.h = true;
    }

    protected void c(OPotionEffect opotioneffect) {
        etc.getLoader().callHook(PluginLoader.Hook.POTION_EFFECTFINISHED, this.entity, opotioneffect.potionEffect);
        this.h = true;
    }

    public float bE() {
        float f = 1.0F;

        if (this.a(OPotion.c)) {
            f *= 1.0F + 0.2F * (float) (this.b(OPotion.c).c() + 1);
        }

        if (this.a(OPotion.d)) {
            f *= 1.0F - 0.15F * (float) (this.b(OPotion.d).c() + 1);
        }

        if (f < 0.0F) {
            f = 0.0F;
        }

        return f;
    }

    public void a(double d0, double d1, double d2) {
        this.b(d0, d1, d2, this.A, this.B);
    }

    public boolean h_() {
        return false;
    }

    public OEnumCreatureAttribute bF() {
        return OEnumCreatureAttribute.a;
    }

    public void a(OItemStack oitemstack) {
        this.a("random.break", 0.8F, 0.8F + this.q.s.nextFloat() * 0.4F);

        for (int i = 0; i < 5; ++i) {
            OVec3 ovec3 = this.q.T().a(((double) this.ab.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

            ovec3.a(-this.B * 3.1415927F / 180.0F);
            ovec3.b(-this.A * 3.1415927F / 180.0F);
            OVec3 ovec31 = this.q.T().a(((double) this.ab.nextFloat() - 0.5D) * 0.3D, (double) (-this.ab.nextFloat()) * 0.6D - 0.3D, 0.6D);

            ovec31.a(-this.B * 3.1415927F / 180.0F);
            ovec31.b(-this.A * 3.1415927F / 180.0F);
            ovec31 = ovec31.c(this.u, this.v + (double) this.e(), this.w);
            this.q.a("iconcrack_" + oitemstack.b().cp, ovec31.c, ovec31.d, ovec31.e, ovec3.c, ovec3.d + 0.05D, ovec3.e);
        }
    }

    public int ar() {
        if (this.aJ() == null) {
            return 3;
        } else {
            int i = (int) ((float) this.aS - (float) this.aW() * 0.33F);

            i -= (3 - this.q.r) * 4;
            if (i < 0) {
                i = 0;
            }

            return i + 3;
        }
    }

    public OItemStack bG() {
        return this.bT[0];
    }

    public OItemStack p(int i) {
        return this.bT[i];
    }

    public OItemStack q(int i) {
        return this.bT[i + 1];
    }

    public void c(int i, OItemStack oitemstack) {
        this.bT[i] = oitemstack;
    }

    public OItemStack[] ad() {
        return this.bT;
    }

    protected void b(boolean flag, int i) {
        for (int j = 0; j < this.ad().length; ++j) {
            OItemStack oitemstack = this.p(j);
            boolean flag1 = this.bq[j] > 1.0F;

            if (oitemstack != null && (flag || flag1) && this.ab.nextFloat() - (float) i * 0.01F < this.bq[j]) {
                if (!flag1 && oitemstack.g()) {
                    int k = Math.max(oitemstack.l() - 25, 1);
                    int l = oitemstack.l() - this.ab.nextInt(this.ab.nextInt(k) + 1);

                    if (l > k) {
                        l = k;
                    }

                    if (l < 1) {
                        l = 1;
                    }

                    oitemstack.b(l);
                }

                this.a(oitemstack, 0.0F);
            }
        }
    }

    protected void bH() {
        if (this.ab.nextFloat() < d[this.q.r]) {
            int i = this.ab.nextInt(2);
            float f = this.q.r == 3 ? 0.1F : 0.25F;

            if (this.ab.nextFloat() < 0.095F) {
                ++i;
            }

            if (this.ab.nextFloat() < 0.095F) {
                ++i;
            }

            if (this.ab.nextFloat() < 0.095F) {
                ++i;
            }

            for (int j = 3; j >= 0; --j) {
                OItemStack oitemstack = this.q(j);

                if (j < 3 && this.ab.nextFloat() < f) {
                    break;
                }

                if (oitemstack == null) {
                    OItem oitem = a(j + 1, i);

                    if (oitem != null) {
                        this.c(j + 1, new OItemStack(oitem));
                    }
                }
            }
        }
    }

    public void a(OEntity oentity, int i) {
        if (!oentity.M && !this.q.I) {
            OEntityTracker oentitytracker = ((OWorldServer) this.q).p();

            if (oentity instanceof OEntityItem) {
                oentitytracker.a(oentity, (OPacket) (new OPacket22Collect(oentity.k, this.k)));
            }

            if (oentity instanceof OEntityArrow) {
                oentitytracker.a(oentity, (OPacket) (new OPacket22Collect(oentity.k, this.k)));
            }

            if (oentity instanceof OEntityXPOrb) {
                oentitytracker.a(oentity, (OPacket) (new OPacket22Collect(oentity.k, this.k)));
            }
        }
    }

    public static int b(OItemStack oitemstack) {
        if (oitemstack.c != OBlock.be.cz && oitemstack.c != OItem.bR.cp) {
            if (oitemstack.b() instanceof OItemArmor) {
                switch (((OItemArmor) oitemstack.b()).b) {
                    case 0:
                        return 4;

                    case 1:
                        return 3;

                    case 2:
                        return 2;

                    case 3:
                        return 1;
                }
            }

            return 0;
        } else {
            return 4;
        }
    }

    public static OItem a(int i, int j) {
        switch (i) {
            case 4:
                if (j == 0) {
                    return OItem.W;
                } else if (j == 1) {
                    return OItem.am;
                } else if (j == 2) {
                    return OItem.aa;
                } else if (j == 3) {
                    return OItem.ae;
                } else if (j == 4) {
                    return OItem.ai;
                }

            case 3:
                if (j == 0) {
                    return OItem.X;
                } else if (j == 1) {
                    return OItem.an;
                } else if (j == 2) {
                    return OItem.ab;
                } else if (j == 3) {
                    return OItem.af;
                } else if (j == 4) {
                    return OItem.aj;
                }

            case 2:
                if (j == 0) {
                    return OItem.Y;
                } else if (j == 1) {
                    return OItem.ao;
                } else if (j == 2) {
                    return OItem.ac;
                } else if (j == 3) {
                    return OItem.ag;
                } else if (j == 4) {
                    return OItem.ak;
                }

            case 1:
                if (j == 0) {
                    return OItem.Z;
                } else if (j == 1) {
                    return OItem.ap;
                } else if (j == 2) {
                    return OItem.ad;
                } else if (j == 3) {
                    return OItem.ah;
                } else if (j == 4) {
                    return OItem.al;
                }

            default:
                return null;
        }
    }

    protected void bI() {
        if (this.bG() != null && this.ab.nextFloat() < b[this.q.r]) {
            OEnchantmentHelper.a(this.ab, this.bG(), 5 + this.q.r * this.ab.nextInt(6));
        }

        for (int i = 0; i < 4; ++i) {
            OItemStack oitemstack = this.q(i);

            if (oitemstack != null && this.ab.nextFloat() < c[this.q.r]) {
                OEnchantmentHelper.a(this.ab, oitemstack, 5 + this.q.r * this.ab.nextInt(6));
            }
        }
    }

    public void bJ() {}

    private int h() {
        return this.a(OPotion.e) ? 6 - (1 + this.b(OPotion.e).c()) * 1 : (this.a(OPotion.f) ? 6 + (1 + this.b(OPotion.f).c()) * 2 : 6);
    }

    public void bK() {
        if (!this.br || this.bs >= this.h() / 2 || this.bs < 0) {
            this.bs = -1;
            this.br = true;
            if (this.q instanceof OWorldServer) {
                ((OWorldServer) this.q).p().a((OEntity) this, (OPacket) (new OPacket18Animation(this, 1)));
            }
        }
    }

    public boolean bL() {
        return false;
    }

    public final int bM() {
        return this.ah.a(10);
    }

    public final void r(int i) {
        this.ah.b(10, Byte.valueOf((byte) i));
    }

    public OEntityLiving bN() {
        return (OEntityLiving) (this.bt.c() != null ? this.bt.c() : (this.bk != null ? this.bk : (this.e != null ? this.e : null)));
    }

    public String am() {
        return this.bP() ? this.bO() : super.am();
    }

    public void c(String s) {
        this.ah.b(5, s);
    }

    public String bO() {
        return this.ah.e(5);
    }

    public boolean bP() {
        return this.ah.e(5).length() > 0;
    }

    public void g(boolean flag) {
        this.ah.b(6, Byte.valueOf((byte) (flag ? 1 : 0)));
    }

    public boolean bQ() {
        return this.ah.a(6) == 1;
    }

    public void a(int i, float f) {
        this.bq[i] = f;
    }

    public boolean bS() {
        return this.bV;
    }

    public void h(boolean flag) {
        this.bV = flag;
    }

    // CanaryMod start: add getEntity
    @Override
    public LivingEntity getEntity() {
        return entity;
    } // CanaryMod end
}
