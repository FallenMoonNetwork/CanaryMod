import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

public abstract class OEntityLivingBase extends OEntity {

    private static final UUID b = UUID.fromString("662A6B8D-DA3E-4C1C-8813-96EA6097278D");
    private static final OAttributeModifier c = (new OAttributeModifier(b, "Sprinting speed boost", 0.30000001192092896D, 2)).a(false);
    private OBaseAttributeMap d;
    private final OCombatTracker e = new OCombatTracker(this);
    private final Map f = Collections.synchronizedMap(new HashMap()); // CanaryMod: synchronize to prevent ConcurrentModificationException
    private final OItemStack[] g = new OItemStack[5];
    public boolean au;
    public int av;
    public int aw;
    public float ax;
    public int ay;
    public int az;
    public float aA;
    public int aB;
    public int aC;
    public float aD;
    public float aE;
    public float aF;
    public float aG;
    public float aH;
    public int aI = 20;
    public float aJ;
    public float aK;
    public float aL;
    public float aM;
    public float aN;
    public float aO;
    public float aP;
    public float aQ;
    public float aR = 0.02F;
    protected OEntityPlayer aS;
    protected int aT;
    protected boolean aU;
    protected int aV;
    protected float aW;
    protected float aX;
    protected float aY;
    protected float aZ;
    protected float ba;
    protected int bb;
    protected float bc;
    protected boolean bd;
    public float be;
    public float bf;
    protected float bg;
    protected int bh;
    protected double bi;
    protected double bj;
    protected double bk;
    protected double bl;
    protected double bm;
    private boolean h = true;
    private OEntityLivingBase i;
    private int j;
    private OEntityLivingBase bn;
    private int bo;
    private float bp;
    private int bq;
    private float br;
    
    private LivingEntityBase livingEntityBase = new LivingEntityBase(this);

    public OEntityLivingBase(OWorld oworld) {
        super(oworld);
        this.ay();
        this.g(this.aS());
        this.m = true;
        this.aM = (float) (Math.random() + 1.0D) * 0.01F;
        this.b(this.u, this.v, this.w);
        this.aL = (float) Math.random() * 12398.0F;
        this.A = (float) (Math.random() * 3.1415927410125732D * 2.0D);
        this.aP = this.A;
        this.Y = 0.5F;
    }

    protected void a() {
        this.ah.a(7, Integer.valueOf(0));
        this.ah.a(8, Byte.valueOf((byte) 0));
        this.ah.a(9, Byte.valueOf((byte) 0));
        this.ah.a(6, Float.valueOf(1.0F));
    }

    protected void ay() {
        this.aW().b(OSharedMonsterAttributes.a);
        this.aW().b(OSharedMonsterAttributes.c);
        this.aW().b(OSharedMonsterAttributes.d);
        if (!this.be()) {
            this.a(OSharedMonsterAttributes.d).a(0.10000000149011612D);
        }
    }

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
                OBlock.s[l].a(this.q, i, j, k, this, this.T);
            }
        }

        super.a(d0, flag);
    }

    public boolean az() {
        return false;
    }

    public void x() {
        this.aD = this.aE;
        super.x();
        this.q.C.a("livingEntityBaseTick");
        if (this.S() && this.T()) {
            // CanaryMod Damage hook: Suffocation
            HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(null, this.getEntity(), DamageType.SUFFOCATION.getDamageSource(), 1.0F));
            if (!ev.isCanceled()) {
                this.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmountFloat());
            }
        }

        if (this.E() || this.q.I) {
            this.A();
        }

        boolean flag = this instanceof OEntityPlayer && ((OEntityPlayer) this).bG.a;

        if (this.S() && this.a(OMaterial.h)) {
            if (!this.az() && !this.i(OPotion.o.H) && !flag) {
                this.g(this.h(this.ak()));
                if (this.ak() == -20) {
                    this.g(0);

                    // CanaryMod Damage hook: Drowning
                    HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(null, this.getEntity(), DamageType.WATER.getDamageSource(), 2.0F));
                    if (!ev.isCanceled()) {
                        for (int i = 0; i < 8; ++i) {
                            float f = this.ab.nextFloat() - this.ab.nextFloat();
                            float f1 = this.ab.nextFloat() - this.ab.nextFloat();
                            float f2 = this.ab.nextFloat() - this.ab.nextFloat();

                            this.q.a("bubble", this.u + (double) f, this.v + (double) f1, this.w + (double) f2, this.x, this.y, this.z);
                        }

                        this.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmountFloat());
                    }
                }
            }

            this.A();
            if (!this.q.I && this.af() && this.o instanceof OEntityLivingBase) {
                this.a((OEntity) null);
            }
        } else {
            this.g(300);
        }

        this.aJ = this.aK;
        if (this.aC > 0) {
            --this.aC;
        }

        if (this.ay > 0) {
            --this.ay;
        }

        if (this.af > 0) {
            --this.af;
        }

        if (this.aM() <= 0.0F) {
            this.aA();
        }

        if (this.aT > 0) {
            --this.aT;
        } else {
            this.aS = null;
        }

        if (this.bn != null && !this.bn.S()) {
            this.bn = null;
        }

        if (this.i != null && !this.i.S()) {
            this.b((OEntityLivingBase) null);
        }

        this.aI();
        this.aZ = this.aY;
        this.aO = this.aN;
        this.aQ = this.aP;
        this.C = this.A;
        this.D = this.B;
        this.q.C.b();
    }

    public boolean g_() {
        return false;
    }

    protected void aA() {
        ++this.aB;
        if (this.aB == 20) {
            int i;

            if (!this.q.I && (this.aT > 0 || this.aB()) && !this.g_() && this.q.O().b("doMobLoot")) {
                i = this.e(this.aS);

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

    protected int e(OEntityPlayer oentityplayer) {
        return 0;
    }

    protected boolean aB() {
        return false;
    }

    public Random aC() {
        return this.ab;
    }

    public OEntityLivingBase aD() {
        return this.i;
    }

    public int aE() {
        return this.j;
    }

    public void b(OEntityLivingBase oentitylivingbase) {
        this.i = oentitylivingbase;
        this.j = this.ac;
    }

    public OEntityLivingBase aF() {
        return this.bn;
    }

    public int aG() {
        return this.bo;
    }

    public void k(OEntity oentity) {
        if (oentity instanceof OEntityLivingBase) {
            this.bn = (OEntityLivingBase) oentity;
        } else {
            this.bn = null;
        }

        this.bo = this.ac;
    }

    public int aH() {
        return this.aV;
    }

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("HealF", this.aM());
        onbttagcompound.a("Health", (short) ((int) Math.ceil((double) this.aM())));
        onbttagcompound.a("HurtTime", (short) this.ay);
        onbttagcompound.a("DeathTime", (short) this.aB);
        onbttagcompound.a("AttackTime", (short) this.aC);
        onbttagcompound.a("AbsorptionAmount", this.bm());
        OItemStack[] aoitemstack = this.ad();
        int i = aoitemstack.length;

        int j;
        OItemStack oitemstack;

        for (j = 0; j < i; ++j) {
            oitemstack = aoitemstack[j];
            if (oitemstack != null) {
                this.d.a(oitemstack.D());
            }
        }

        onbttagcompound.a("Attributes", (ONBTBase) OSharedMonsterAttributes.a(this.aW()));
        aoitemstack = this.ad();
        i = aoitemstack.length;

        for (j = 0; j < i; ++j) {
            oitemstack = aoitemstack[j];
            if (oitemstack != null) {
                this.d.b(oitemstack.D());
            }
        }

        if (!this.f.isEmpty()) {
            ONBTTagList onbttaglist = new ONBTTagList();
            Iterator iterator = this.f.values().iterator();

            while (iterator.hasNext()) {
                OPotionEffect opotioneffect = (OPotionEffect) iterator.next();

                onbttaglist.a((ONBTBase) opotioneffect.a(new ONBTTagCompound()));
            }

            onbttagcompound.a("ActiveEffects", (ONBTBase) onbttaglist);
        }
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.m(onbttagcompound.g("AbsorptionAmount"));
        if (onbttagcompound.b("Attributes") && this.q != null && !this.q.I) {
            OSharedMonsterAttributes.a(this.aW(), onbttagcompound.m("Attributes"), this.q == null ? null : this.q.Y());
        }

        if (onbttagcompound.b("ActiveEffects")) {
            ONBTTagList onbttaglist = onbttagcompound.m("ActiveEffects");

            for (int i = 0; i < onbttaglist.c(); ++i) {
                ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.b(i);
                OPotionEffect opotioneffect = OPotionEffect.b(onbttagcompound1);

                this.f.put(Integer.valueOf(opotioneffect.a()), opotioneffect);
            }
        }

        if (onbttagcompound.b("HealF")) {
            this.g(onbttagcompound.g("HealF"));
        } else {
            ONBTBase onbtbase = onbttagcompound.a("Health");

            if (onbtbase == null) {
                this.g(this.aS());
            } else if (onbtbase.a() == 5) {
                this.g(((ONBTTagFloat) onbtbase).a);
            } else if (onbtbase.a() == 2) {
                this.g((float) ((ONBTTagShort) onbtbase).a);
            }
        }

        this.ay = onbttagcompound.d("HurtTime");
        this.aB = onbttagcompound.d("DeathTime");
        this.aC = onbttagcompound.d("AttackTime");
    }

    protected void aI() {
        Iterator iterator = this.f.keySet().iterator();

        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            OPotionEffect opotioneffect = (OPotionEffect) this.f.get(integer);

            if (!opotioneffect.a(this)) {
                if (!this.q.I) {
                    iterator.remove();
                    this.b(opotioneffect);
                }
            } else if (opotioneffect.b() % 600 == 0) {
                this.a(opotioneffect, false);
            }
        }

        int i;

        if (this.h) {
            if (!this.q.I) {
                if (this.f.isEmpty()) {
                    this.ah.b(8, Byte.valueOf((byte) 0));
                    this.ah.b(7, Integer.valueOf(0));
                    this.d(false);
                } else {
                    i = OPotionHelper.a(this.f.values());
                    this.ah.b(8, Byte.valueOf((byte) (OPotionHelper.b(this.f.values()) ? 1 : 0)));
                    this.ah.b(7, Integer.valueOf(i));
                    this.d(this.i(OPotion.p.H));
                }
            }

            this.h = false;
        }

        i = this.ah.c(7);
        boolean flag = this.ah.a(8) > 0;

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

    public void aJ() {
        Iterator iterator = this.f.keySet().iterator();

        while (iterator.hasNext()) {
            Integer integer = (Integer) iterator.next();
            OPotionEffect opotioneffect = (OPotionEffect) this.f.get(integer);

            if (!this.q.I) {
                iterator.remove();
                this.b(opotioneffect);
            }
        }
    }

    public Collection aK() {
        return this.f.values();
    }

    public boolean i(int i) {
        return this.f.containsKey(Integer.valueOf(i));
    }

    public boolean a(OPotion opotion) {
        return this.f.containsKey(Integer.valueOf(opotion.H));
    }

    public OPotionEffect b(OPotion opotion) {
        return (OPotionEffect) this.f.get(Integer.valueOf(opotion.H));
    }

    public void c(OPotionEffect opotioneffect) {
        // CanaryMod - POTION_EFFECT HOOK
        PotionEffect pe = (PotionEffect) etc.getLoader().callHook(PluginLoader.Hook.POTION_EFFECT, this.getEntity(), opotioneffect.potionEffect);

        if (pe == null) {
            return;
        }
        opotioneffect = pe.potionEffect;

        if (this.d(opotioneffect)) {
            if (this.f.containsKey(Integer.valueOf(opotioneffect.a()))) {
                ((OPotionEffect) this.f.get(Integer.valueOf(opotioneffect.a()))).a(opotioneffect);
                this.a((OPotionEffect) this.f.get(Integer.valueOf(opotioneffect.a())), true);
            } else {
                this.f.put(Integer.valueOf(opotioneffect.a()), opotioneffect);
                this.a(opotioneffect);
            }
        }
    }

    public boolean d(OPotionEffect opotioneffect) {
        if (this.aX() == OEnumCreatureAttribute.b) {
            int i = opotioneffect.a();

            if (i == OPotion.l.H || i == OPotion.u.H) {
                return false;
            }
        }

        return true;
    }

    public boolean aL() {
        return this.aX() == OEnumCreatureAttribute.b;
    }

    public void k(int i) {
        OPotionEffect opotioneffect = (OPotionEffect) this.f.remove(Integer.valueOf(i));

        if (opotioneffect != null) {
            this.b(opotioneffect);
        }
    }

    protected void a(OPotionEffect opotioneffect) {
        this.h = true;
        if (!this.q.I) {
            OPotion.a[opotioneffect.a()].b(this, this.aW(), opotioneffect.c());
        }
    }

    protected void a(OPotionEffect opotioneffect, boolean flag) {
        this.h = true;
        if (flag && !this.q.I) {
            OPotion.a[opotioneffect.a()].a(this, this.aW(), opotioneffect.c());
            OPotion.a[opotioneffect.a()].b(this, this.aW(), opotioneffect.c());
        }
    }

    protected void b(OPotionEffect opotioneffect) {
        etc.getLoader().callHook(PluginLoader.Hook.POTION_EFFECTFINISHED, this.getEntity(), opotioneffect.potionEffect);
        this.h = true;
        if (!this.q.I) {
            OPotion.a[opotioneffect.a()].a(this, this.aW(), opotioneffect.c());
        }
    }

    public void f(float f) {
        float f1 = this.aM();

        if (f1 > 0.0F) {
            this.g(f1 + f);
        }
    }

    public final float aM() {
        return this.ah.d(6);
    }

    public void g(float f) {
        this.ah.b(6, Float.valueOf(OMathHelper.a(f, 0.0F, this.aS())));
    }

    public boolean a(ODamageSource odamagesource, float f) {
        if (this.aq()) {
            return false;
        } else if (this.q.I) {
            return false;
        } else {
            this.aV = 0;
            if (this.aM() <= 0.0F) {
                return false;
            } else if (odamagesource.m() && this.a(OPotion.n)) {
                return false;
            } else {
                if ((odamagesource == ODamageSource.m || odamagesource == ODamageSource.n) && this.n(4) != null) {
                    this.n(4).a((int) (f * 4.0F + this.ab.nextFloat() * f * 2.0F), this);
                    f *= 0.75F;
                }

                this.aG = 1.5F;
                boolean flag = true;

                BaseEntity attacker = null;

                if (odamagesource instanceof OEntityDamageSource && ((OEntityDamageSource) odamagesource).h() instanceof OEntityLiving) {
                    OEntityLiving ent = (OEntityLiving) ((OEntityDamageSource) odamagesource).h();
                    attacker = ent.getEntity();
                }
                if (attacker != null && (Boolean) manager.callHook(PluginLoader.Hook.ATTACK, attacker, this.getEntity(), f)) {
                    if (this instanceof OEntityCreature) {
                        ((OEntityCreature) this).bo = 0;
                    }
                    return false;
                }

                if ((float) this.af > (float) this.aI / 2.0F) {
                    if (f <= this.bc) {
                        return false;
                    }
                    
                    HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(attacker, this.getEntity(), odamagesource.damageSource, f - this.bc));
                    if (attacker != null && ev.isCanceled()) {
                        return false;
                    }

                    this.d(ev.getDamageSource().getDamageSource(), ev.getDamageAmountFloat());
                    this.bc = f;
                    flag = false;
                } else {
                    HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(attacker, this.getEntity(), odamagesource.damageSource, f));
                    if (attacker != null && ev.isCanceled()) {
                        return false;
                    }

                    this.bc = f;
                    this.ax = this.aM();
                    this.af = this.aI;
                    this.d(ev.getDamageSource().getDamageSource(), ev.getDamageAmountFloat());
                    this.ay = this.az = 10;
                }

                this.aA = 0.0F;
                OEntity oentity = odamagesource.i();

                if (oentity != null) {
                    if (oentity instanceof OEntityLivingBase) {
                        this.b((OEntityLivingBase) oentity);
                    }

                    if (oentity instanceof OEntityPlayer) {
                        this.aT = 100;
                        this.aS = (OEntityPlayer) oentity;
                    } else if (oentity instanceof OEntityWolf) {
                        OEntityWolf oentitywolf = (OEntityWolf) oentity;

                        if (oentitywolf.bT()) {
                            this.aT = 100;
                            this.aS = null;
                        }
                    }
                }

                if (flag) {
                    this.q.a((OEntity) this, (byte) 2);
                    if (odamagesource != ODamageSource.e) {
                        this.J();
                    }

                    if (oentity != null) {
                        double d0 = oentity.u - this.u;

                        double d1;

                        for (d1 = oentity.w - this.w; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D) {
                            d0 = (Math.random() - Math.random()) * 0.01D;
                        }

                        this.aA = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - this.A;
                        this.a(oentity, f, d0, d1);
                    } else {
                        this.aA = (float) ((int) (Math.random() * 2.0D) * 180);
                    }
                }

                if (this.aM() <= 0.0F) {
                    if (flag) {
                        this.a(this.aO(), this.aZ(), this.ba());
                    }

                    this.a(odamagesource);
                } else if (flag) {
                    this.a(this.aN(), this.aZ(), this.ba());
                }

                return true;
            }
        }
    }

    public void a(OItemStack oitemstack) {
        this.a("random.break", 0.8F, 0.8F + this.q.s.nextFloat() * 0.4F);

        for (int i = 0; i < 5; ++i) {
            OVec3 ovec3 = this.q.V().a(((double) this.ab.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

            ovec3.a(-this.B * 3.1415927F / 180.0F);
            ovec3.b(-this.A * 3.1415927F / 180.0F);
            OVec3 ovec31 = this.q.V().a(((double) this.ab.nextFloat() - 0.5D) * 0.3D, (double) (-this.ab.nextFloat()) * 0.6D - 0.3D, 0.6D);

            ovec31.a(-this.B * 3.1415927F / 180.0F);
            ovec31.b(-this.A * 3.1415927F / 180.0F);
            ovec31 = ovec31.c(this.u, this.v + (double) this.f(), this.w);
            this.q.a("iconcrack_" + oitemstack.b().cv, ovec31.c, ovec31.d, ovec31.e, ovec3.c, ovec3.d + 0.05D, ovec3.e);
        }
    }

    public void a(ODamageSource odamagesource) {
        manager.callHook(PluginLoader.Hook.DEATH, this.getEntity()); // CanaryMod: call DEATH hook

        OEntity oentity = odamagesource.i();
        OEntityLivingBase oentitylivingbase = this.aR();

        if (this.bb >= 0 && oentitylivingbase != null) {
            oentitylivingbase.b(this, this.bb);
        }

        if (oentity != null) {
            oentity.a(this);
        }

        this.aU = true;
        if (!this.q.I) {
            int i = 0;

            if (oentity instanceof OEntityPlayer) {
                i = OEnchantmentHelper.g((OEntityLivingBase) oentity);
            }

            if (!this.g_() && this.q.O().b("doMobLoot")) {
                this.b(this.aT > 0, i);
                this.a(this.aT > 0, i);
                if (this.aT > 0) {
                    int j = this.ab.nextInt(200) - i;

                    if (j < 5) {
                        this.l(j <= 0 ? 1 : 0);
                    }
                }
            }
        }

        this.q.a((OEntity) this, (byte) 3);
    }

    protected void a(boolean flag, int i) {}

    public void a(OEntity oentity, float f, double d0, double d1) {
        if (this.ab.nextDouble() >= this.a(OSharedMonsterAttributes.c).e()) {
            this.an = true;
            float f1 = OMathHelper.a(d0 * d0 + d1 * d1);
            float f2 = 0.4F;

            this.x /= 2.0D;
            this.y /= 2.0D;
            this.z /= 2.0D;
            this.x -= d0 / (double) f1 * (double) f2;
            this.y += (double) f2;
            this.z -= d1 / (double) f1 * (double) f2;
            if (this.y > 0.4000000059604645D) {
                this.y = 0.4000000059604645D;
            }
        }
    }

    protected String aN() {
        return "damage.hit";
    }

    protected String aO() {
        return "damage.hit";
    }

    protected void l(int i) {}

    protected void b(boolean flag, int i) {}

    public boolean e() {
        int i = OMathHelper.c(this.u);
        int j = OMathHelper.c(this.E.b);
        int k = OMathHelper.c(this.w);
        int l = this.q.a(i, j, k);

        return l == OBlock.aK.cF || l == OBlock.bz.cF;
    }

    public boolean S() {
        return !this.M && this.aM() > 0.0F;
    }

    protected void b(float f) {
        super.b(f);
        OPotionEffect opotioneffect = this.b(OPotion.j);
        float f1 = opotioneffect != null ? (float) (opotioneffect.c() + 1) : 0.0F;
        int i = OMathHelper.f(f - 3.0F - f1);

        if (i > 0) {
            // CanaryMod Damage hook: Falling
            HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(null, this.getEntity(), DamageType.FALL.getDamageSource(), (float) i));
            if (!ev.isCanceled()) {
                if (i > 4) {
                    this.a("damage.fallbig", 1.0F, 1.0F);
                } else {
                    this.a("damage.fallsmall", 1.0F, 1.0F);
                }

                this.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmountFloat());
            }
            int j = this.q.a(OMathHelper.c(this.u), OMathHelper.c(this.v - 0.20000000298023224D - (double) this.N), OMathHelper.c(this.w));

            if (j > 0) {
                OStepSound ostepsound = OBlock.s[j].cS;

                this.a(ostepsound.e(), ostepsound.c() * 0.5F, ostepsound.d() * 0.75F);
            }
        }
    }

    public int aP() {
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

    protected void h(float f) {}

    protected float b(ODamageSource odamagesource, float f) {
        if (!odamagesource.e()) {
            int i = 25 - this.aP();
            float f1 = f * (float) i;

            this.h(f);
            f = f1 / 25.0F;
        }

        return f;
    }

    protected float c(ODamageSource odamagesource, float f) {
        if (this instanceof OEntityZombie) {
            f = f;
        }

        int i;
        int j;
        float f1;

        if (this.a(OPotion.m) && odamagesource != ODamageSource.i) {
            i = (this.b(OPotion.m).c() + 1) * 5;
            j = 25 - i;
            f1 = f * (float) j;
            f = f1 / 25.0F;
        }

        if (f <= 0.0F) {
            return 0.0F;
        } else {
            i = OEnchantmentHelper.a(this.ad(), odamagesource);
            if (i > 20) {
                i = 20;
            }

            if (i > 0 && i <= 20) {
                j = 25 - i;
                f1 = f * (float) j;
                f = f1 / 25.0F;
            }

            return f;
        }
    }

    protected void d(ODamageSource odamagesource, float f) {
        if (!this.aq()) {
            f = this.b(odamagesource, f);
            f = this.c(odamagesource, f);
            float f1 = f;

            f = Math.max(f - this.bm(), 0.0F);
            this.m(this.bm() - (f1 - f));
            if (f != 0.0F) {
                float f2 = this.aM();

                this.g(f2 - f);
                this.aQ().a(odamagesource, f2, f);
                this.m(this.bm() - f);
            }
        }
    }

    public OCombatTracker aQ() {
        return this.e;
    }

    public OEntityLivingBase aR() {
        return (OEntityLivingBase) (this.e.c() != null ? this.e.c() : (this.aS != null ? this.aS : (this.i != null ? this.i : null)));
    }

    public final float aS() {
        return (float) this.a(OSharedMonsterAttributes.a).e();
    }

    public final int aT() {
        return this.ah.a(9);
    }

    public final void m(int i) {
        this.ah.b(9, Byte.valueOf((byte) i));
    }

    private int h() {
        return this.a(OPotion.e) ? 6 - (1 + this.b(OPotion.e).c()) * 1 : (this.a(OPotion.f) ? 6 + (1 + this.b(OPotion.f).c()) * 2 : 6);
    }

    public void aU() {
        if (!this.au || this.av >= this.h() / 2 || this.av < 0) {
            this.av = -1;
            this.au = true;
            if (this.q instanceof OWorldServer) {
                ((OWorldServer) this.q).q().a((OEntity) this, (OPacket) (new OPacket18Animation(this, 1)));
            }
        }
    }

    protected void B() {
        this.a(ODamageSource.i, 4.0F);
    }

    protected void aV() {
        int i = this.h();

        if (this.au) {
            ++this.av;
            if (this.av >= i) {
                this.av = 0;
                this.au = false;
            }
        } else {
            this.av = 0;
        }

        this.aE = (float) this.av / (float) i;
    }

    public OAttributeInstance a(OAttribute oattribute) {
        return this.aW().a(oattribute);
    }

    public OBaseAttributeMap aW() {
        if (this.d == null) {
            this.d = new OServersideAttributeMap();
        }

        return this.d;
    }

    public OEnumCreatureAttribute aX() {
        return OEnumCreatureAttribute.a;
    }

    public abstract OItemStack aY();

    public abstract OItemStack n(int i);

    public abstract void c(int i, OItemStack oitemstack);

    public void c(boolean flag) {
        super.c(flag);
        OAttributeInstance oattributeinstance = this.a(OSharedMonsterAttributes.d);

        if (oattributeinstance.a(b) != null) {
            oattributeinstance.b(c);
        }

        if (flag) {
            oattributeinstance.a(c);
        }
    }

    public abstract OItemStack[] ad();

    protected float aZ() {
        return 1.0F;
    }

    protected float ba() {
        return this.g_() ? (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.5F : (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F;
    }

    protected boolean bb() {
        return this.aM() <= 0.0F;
    }

    public void a(double d0, double d1, double d2) {
        this.b(d0, d1, d2, this.A, this.B);
    }

    public void l(OEntity oentity) {
        double d0 = oentity.u;
        double d1 = oentity.E.b + (double) oentity.P;
        double d2 = oentity.w;

        for (double d3 = -1.5D; d3 < 2.0D; ++d3) {
            for (double d4 = -1.5D; d4 < 2.0D; ++d4) {
                if (d3 != 0.0D || d4 != 0.0D) {
                    int i = (int) (this.u + d3);
                    int j = (int) (this.w + d4);
                    OAxisAlignedBB oaxisalignedbb = this.E.c(d3, 1.0D, d4);

                    if (this.q.a(oaxisalignedbb).isEmpty()) {
                        if (this.q.w(i, (int) this.v, j)) {
                            this.a(this.u + d3, this.v + 1.0D, this.w + d4);
                            return;
                        }

                        if (this.q.w(i, (int) this.v - 1, j) || this.q.g(i, (int) this.v - 1, j) == OMaterial.h) {
                            d0 = this.u + d3;
                            d1 = this.v + 1.0D;
                            d2 = this.w + d4;
                        }
                    }
                }
            }
        }

        this.a(d0, d1, d2);
    }

    protected void bd() {
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

    public void e(float f, float f1) {
        double d0;

        if (this.G() && (!(this instanceof OEntityPlayer) || !((OEntityPlayer) this).bG.b)) {
            d0 = this.v;
            this.a(f, f1, this.be() ? 0.04F : 0.02F);
            this.d(this.x, this.y, this.z);
            this.x *= 0.800000011920929D;
            this.y *= 0.800000011920929D;
            this.z *= 0.800000011920929D;
            this.y -= 0.02D;
            if (this.G && this.c(this.x, this.y + 0.6000000238418579D - this.v + d0, this.z)) {
                this.y = 0.30000001192092896D;
            }
        } else if (this.I() && (!(this instanceof OEntityPlayer) || !((OEntityPlayer) this).bG.b)) {
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
                    f2 = OBlock.s[i].cV * 0.91F;
                }
            }

            float f3 = 0.16277136F / (f2 * f2 * f2);
            float f4;

            if (this.F) {
                f4 = this.bf() * f3;
            } else {
                f4 = this.aR;
            }

            this.a(f, f1, f4);
            f2 = 0.91F;
            if (this.F) {
                f2 = 0.54600006F;
                int j = this.q.a(OMathHelper.c(this.u), OMathHelper.c(this.E.b) - 1, OMathHelper.c(this.w));

                if (j > 0) {
                    f2 = OBlock.s[j].cV * 0.91F;
                }
            }

            if (this.e()) {
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
            if (this.G && this.e()) {
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

        this.aF = this.aG;
        d0 = this.u - this.r;
        double d1 = this.w - this.t;
        float f6 = OMathHelper.a(d0 * d0 + d1 * d1) * 4.0F;

        if (f6 > 1.0F) {
            f6 = 1.0F;
        }

        this.aG += (f6 - this.aG) * 0.4F;
        this.aH += this.aG;
    }

    protected boolean be() {
        return false;
    }

    public float bf() {
        return this.be() ? this.bp : 0.1F;
    }

    public void i(float f) {
        this.bp = f;
    }

    public boolean m(OEntity oentity) {
        this.k(oentity);
        return false;
    }

    public boolean bg() {
        return false;
    }

    public void l_() {
        super.l_();
        if (!this.q.I) {
            int i = this.aT();

            if (i > 0) {
                if (this.aw <= 0) {
                    this.aw = 20 * (30 - i);
                }

                --this.aw;
                if (this.aw <= 0) {
                    this.m(i - 1);
                }
            }

            for (int j = 0; j < 5; ++j) {
                OItemStack oitemstack = this.g[j];
                OItemStack oitemstack1 = this.n(j);

                if (!OItemStack.b(oitemstack1, oitemstack)) {
                    ((OWorldServer) this.q).q().a((OEntity) this, (OPacket) (new OPacket5PlayerInventory(this.k, j, oitemstack1)));
                    if (oitemstack != null) {
                        this.d.a(oitemstack.D());
                    }

                    if (oitemstack1 != null) {
                        this.d.b(oitemstack1.D());
                    }

                    this.g[j] = oitemstack1 == null ? null : oitemstack1.m();
                }
            }
        }

        this.c();
        double d0 = this.u - this.r;
        double d1 = this.w - this.t;
        float f = (float) (d0 * d0 + d1 * d1);
        float f1 = this.aN;
        float f2 = 0.0F;

        this.aW = this.aX;
        float f3 = 0.0F;

        if (f > 0.0025000002F) {
            f3 = 1.0F;
            f2 = (float) Math.sqrt((double) f) * 3.0F;
            f1 = (float) Math.atan2(d1, d0) * 180.0F / 3.1415927F - 90.0F;
        }

        if (this.aE > 0.0F) {
            f1 = this.A;
        }

        if (!this.F) {
            f3 = 0.0F;
        }

        this.aX += (f3 - this.aX) * 0.3F;
        this.q.C.a("headTurn");
        f2 = this.f(f1, f2);
        this.q.C.b();
        this.q.C.a("rangeChecks");

        while (this.A - this.C < -180.0F) {
            this.C -= 360.0F;
        }

        while (this.A - this.C >= 180.0F) {
            this.C += 360.0F;
        }

        while (this.aN - this.aO < -180.0F) {
            this.aO -= 360.0F;
        }

        while (this.aN - this.aO >= 180.0F) {
            this.aO += 360.0F;
        }

        while (this.B - this.D < -180.0F) {
            this.D -= 360.0F;
        }

        while (this.B - this.D >= 180.0F) {
            this.D += 360.0F;
        }

        while (this.aP - this.aQ < -180.0F) {
            this.aQ -= 360.0F;
        }

        while (this.aP - this.aQ >= 180.0F) {
            this.aQ += 360.0F;
        }

        this.q.C.b();
        this.aY += f2;
    }

    protected float f(float f, float f1) {
        float f2 = OMathHelper.g(f - this.aN);

        this.aN += f2 * 0.3F;
        float f3 = OMathHelper.g(this.A - this.aN);
        boolean flag = f3 < -90.0F || f3 >= 90.0F;

        if (f3 < -75.0F) {
            f3 = -75.0F;
        }

        if (f3 >= 75.0F) {
            f3 = 75.0F;
        }

        this.aN = this.A - f3;
        if (f3 * f3 > 2500.0F) {
            this.aN += f3 * 0.2F;
        }

        if (flag) {
            f1 *= -1.0F;
        }

        return f1;
    }

    public void c() {
        if (this.bq > 0) {
            --this.bq;
        }

        if (this.bh > 0) {
            double d0 = this.u + (this.bi - this.u) / (double) this.bh;
            double d1 = this.v + (this.bj - this.v) / (double) this.bh;
            double d2 = this.w + (this.bk - this.w) / (double) this.bh;
            double d3 = OMathHelper.g(this.bl - (double) this.A);

            this.A = (float) ((double) this.A + d3 / (double) this.bh);
            this.B = (float) ((double) this.B + (this.bm - (double) this.B) / (double) this.bh);
            --this.bh;
            this.b(d0, d1, d2);
            this.b(this.A, this.B);
        } else if (!this.bl()) {
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
        if (this.bb()) {
            this.bd = false;
            this.be = 0.0F;
            this.bf = 0.0F;
            this.bg = 0.0F;
        } else if (this.bl()) {
            if (this.be()) {
                this.q.C.a("newAi");
                this.bh();
                this.q.C.b();
            } else {
                this.q.C.a("oldAi");
                this.bk();
                this.q.C.b();
                this.aP = this.A;
            }
        }

        this.q.C.b();
        this.q.C.a("jump");
        if (this.bd) {
            if (!this.G() && !this.I()) {
                if (this.F && this.bq == 0) {
                    this.bd();
                    this.bq = 10;
                }
            } else {
                this.y += 0.03999999910593033D;
            }
        } else {
            this.bq = 0;
        }

        this.q.C.b();
        this.q.C.a("travel");
        this.be *= 0.98F;
        this.bf *= 0.98F;
        this.bg *= 0.9F;
        this.e(this.be, this.bf);
        this.q.C.b();
        this.q.C.a("push");
        if (!this.q.I) {
            this.bi();
        }

        this.q.C.b();
    }

    protected void bh() {}

    protected void bi() {
        List list = this.q.b((OEntity) this, this.E.b(0.20000000298023224D, 0.0D, 0.20000000298023224D));

        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); ++i) {
                OEntity oentity = (OEntity) list.get(i);

                if (oentity.L()) {
                    this.n(oentity);
                }
            }
        }
    }

    protected void n(OEntity oentity) {
        oentity.f((OEntity) this);
    }

    public void U() {
        super.U();
        this.aW = this.aX;
        this.aX = 0.0F;
        this.T = 0.0F;
    }

    protected void bj() {}

    protected void bk() {
        ++this.aV;
    }

    public void f(boolean flag) {
        this.bd = flag;
    }

    public void a(OEntity oentity, int i) {
        if (!oentity.M && !this.q.I) {
            OEntityTracker oentitytracker = ((OWorldServer) this.q).q();

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

    public boolean o(OEntity oentity) {
        return this.q.a(this.q.V().a(this.u, this.v + (double) this.f(), this.w), this.q.V().a(oentity.u, oentity.v + (double) oentity.f(), oentity.w)) == null;
    }

    public OVec3 Z() {
        return this.j(1.0F);
    }

    public OVec3 j(float f) {
        float f1;
        float f2;
        float f3;
        float f4;

        if (f == 1.0F) {
            f1 = OMathHelper.b(-this.A * 0.017453292F - 3.1415927F);
            f2 = OMathHelper.a(-this.A * 0.017453292F - 3.1415927F);
            f3 = -OMathHelper.b(-this.B * 0.017453292F);
            f4 = OMathHelper.a(-this.B * 0.017453292F);
            return this.q.V().a((double) (f2 * f3), (double) f4, (double) (f1 * f3));
        } else {
            f1 = this.D + (this.B - this.D) * f;
            f2 = this.C + (this.A - this.C) * f;
            f3 = OMathHelper.b(-f2 * 0.017453292F - 3.1415927F);
            f4 = OMathHelper.a(-f2 * 0.017453292F - 3.1415927F);
            float f5 = -OMathHelper.b(-f1 * 0.017453292F);
            float f6 = OMathHelper.a(-f1 * 0.017453292F);

            return this.q.V().a((double) (f4 * f5), (double) f6, (double) (f3 * f5));
        }
    }

    public boolean bl() {
        return !this.q.I;
    }

    public boolean K() {
        return !this.M;
    }

    public boolean L() {
        return !this.M;
    }

    public float f() {
        return this.P * 0.85F;
    }

    protected void J() {
        this.J = this.ab.nextDouble() >= this.a(OSharedMonsterAttributes.c).e();
    }

    public float ao() {
        return this.aP;
    }

    public float bm() {
        return this.br;
    }

    public void m(float f) {
        if (f < 0.0F) {
            f = 0.0F;
        }

        this.br = f;
    }

    public OTeam bn() {
        return null;
    }

    public boolean c(OEntityLivingBase oentitylivingbase) {
        return this.a(oentitylivingbase.bn());
    }

    public boolean a(OTeam oteam) {
        return this.bn() != null ? this.bn().a(oteam) : false;
    }
    
    // CanaryMod start: add getEntity
    @Override
    public LivingEntityBase getEntity() {
        return livingEntityBase;
    } // CanaryMod end
}
