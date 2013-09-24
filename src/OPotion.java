import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

public class OPotion {

    public static final OPotion[] a = new OPotion[32];
    public static final OPotion b = null;
    public static final OPotion c = (new OPotion(1, false, 8171462)).b("potion.moveSpeed").b(0, 0).a(OSharedMonsterAttributes.d, "91AEAA56-376B-4498-935B-2F7F68070635", 0.20000000298023224D, 2);
    public static final OPotion d = (new OPotion(2, true, 5926017)).b("potion.moveSlowdown").b(1, 0).a(OSharedMonsterAttributes.d, "7107DE5E-7CE8-4030-940E-514C1F160890", -0.15000000596046448D, 2);
    public static final OPotion e = (new OPotion(3, false, 14270531)).b("potion.digSpeed").b(2, 0).a(1.5D);
    public static final OPotion f = (new OPotion(4, true, 4866583)).b("potion.digSlowDown").b(3, 0);
    public static final OPotion g = (new OPotionAttackDamage(5, false, 9643043)).b("potion.damageBoost").b(4, 0).a(OSharedMonsterAttributes.e, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 3.0D, 2);
    public static final OPotion h = (new OPotionHealth(6, false, 16262179)).b("potion.heal");
    public static final OPotion i = (new OPotionHealth(7, true, 4393481)).b("potion.harm");
    public static final OPotion j = (new OPotion(8, false, 7889559)).b("potion.jump").b(2, 1);
    public static final OPotion k = (new OPotion(9, true, 5578058)).b("potion.confusion").b(3, 1).a(0.25D);
    public static final OPotion l = (new OPotion(10, false, 13458603)).b("potion.regeneration").b(7, 0).a(0.25D);
    public static final OPotion m = (new OPotion(11, false, 10044730)).b("potion.resistance").b(6, 1);
    public static final OPotion n = (new OPotion(12, false, 14981690)).b("potion.fireResistance").b(7, 1);
    public static final OPotion o = (new OPotion(13, false, 3035801)).b("potion.waterBreathing").b(0, 2);
    public static final OPotion p = (new OPotion(14, false, 8356754)).b("potion.invisibility").b(0, 1);
    public static final OPotion q = (new OPotion(15, true, 2039587)).b("potion.blindness").b(5, 1).a(0.25D);
    public static final OPotion r = (new OPotion(16, false, 2039713)).b("potion.nightVision").b(4, 1);
    public static final OPotion s = (new OPotion(17, true, 5797459)).b("potion.hunger").b(1, 1);
    public static final OPotion t = (new OPotionAttackDamage(18, true, 4738376)).b("potion.weakness").b(5, 0).a(OSharedMonsterAttributes.e, "22653B89-116E-49DC-9B6B-9971489B5BE5", 2.0D, 0);
    public static final OPotion u = (new OPotion(19, true, 5149489)).b("potion.poison").b(6, 0).a(0.25D);
    public static final OPotion v = (new OPotion(20, true, 3484199)).b("potion.wither").b(1, 2).a(0.25D);
    public static final OPotion w = (new OPotionHealthBoost(21, false, 16284963)).b("potion.healthBoost").b(2, 2).a(OSharedMonsterAttributes.a, "5D6F0BA2-1186-46AC-B896-C61C5CEE99CC", 4.0D, 0);
    public static final OPotion x = (new OPotionAbsoption(22, false, 2445989)).b("potion.absorption").b(2, 2);
    public static final OPotion y = (new OPotionHealth(23, false, 16262179)).b("potion.saturation");
    public static final OPotion z = null;
    public static final OPotion A = null;
    public static final OPotion B = null;
    public static final OPotion C = null;
    public static final OPotion D = null;
    public static final OPotion E = null;
    public static final OPotion F = null;
    public static final OPotion G = null;
    public final int H;
    private final Map I = Maps.newHashMap();
    private final boolean J;
    private final int K;
    private String L = "";
    private int M = -1;
    private double N;
    private boolean O;

    protected OPotion(int i, boolean flag, int j) {
        this.H = i;
        a[i] = this;
        this.J = flag;
        if (flag) {
            this.N = 0.5D;
        } else {
            this.N = 1.0D;
        }

        this.K = j;
    }

    protected OPotion b(int i, int j) {
        this.M = i + j * 8;
        return this;
    }

    public int c() {
        return this.H;
    }

    public void a(OEntityLivingBase oentitylivingbase, int i) {
        if (this.H == l.H) {
            if (oentitylivingbase.aN() < oentitylivingbase.aT()) {
                oentitylivingbase.f(1.0F);
            }
        } else if (this.H == u.H) {
            if (oentitylivingbase.aN() > 1.0F) {
                // Canarymod: DAMAGE From Poison
                HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(null, oentitylivingbase.getEntity(), DamageType.POTION.getDamageSource(), 1.0F));
                if (!ev.isCanceled()) {
                    oentitylivingbase.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmountFloat());
                } //
            }
        } else if (this.H == v.H) {
            // CanaryMod: Wither effect damage
            HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(null, oentitylivingbase.getEntity(), DamageType.WITHER.getDamageSource(), 1.0F));
            if (!ev.isCanceled()) {
                oentitylivingbase.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmountFloat());
            } //
        } else if (this.H == s.H && oentitylivingbase instanceof OEntityPlayer) {
            ((OEntityPlayer) oentitylivingbase).j(0.025F * (float) (i + 1));
        } else if (this.H == y.H && oentitylivingbase instanceof OEntityPlayer) {
            if (!oentitylivingbase.q.I) {
                ((OEntityPlayer) oentitylivingbase).bI().a(i + 1, 1.0F);
            }
        } else if ((this.H != h.H || oentitylivingbase.aM()) && (this.H != OPotion.i.H || !oentitylivingbase.aM())) {
            if (this.H == OPotion.i.H && !oentitylivingbase.aM() || this.H == h.H && oentitylivingbase.aM()) {
                // Canarymod: harm/heal potion
                HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(null, oentitylivingbase.getEntity(), DamageType.POTION.getDamageSource(), (float) (6 << i)));
                if (!ev.isCanceled()) {
                    oentitylivingbase.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmountFloat());
                } //
            }
        } else {
            oentitylivingbase.f((float) Math.max(4 << i, 0));
        }
    }

    public void a(OEntityLivingBase oentitylivingbase, OEntityLivingBase oentitylivingbase1, int i, double d0) {
        int j;

        if ((this.H != h.H || oentitylivingbase1.aM()) && (this.H != OPotion.i.H || !oentitylivingbase1.aM())) {
            if (this.H == OPotion.i.H && !oentitylivingbase1.aM() || this.H == h.H && oentitylivingbase1.aM()) {
                j = (int) (d0 * (double) (6 << i) + 0.5D);
                if (oentitylivingbase == null) {
                    oentitylivingbase1.a(ODamageSource.k, (float) j);
                } else {
                    oentitylivingbase1.a(ODamageSource.b(oentitylivingbase1, oentitylivingbase), (float) j);
                }
            }
        } else {
            j = (int) (d0 * (double) (4 << i) + 0.5D);
            oentitylivingbase1.f((float) j);
        }
    }

    public boolean b() {
        return false;
    }

    public boolean a(int i, int j) {
        int k;

        if (this.H == l.H) {
            k = 50 >> j;
            return k > 0 ? i % k == 0 : true;
        } else if (this.H == u.H) {
            k = 25 >> j;
            return k > 0 ? i % k == 0 : true;
        } else if (this.H == v.H) {
            k = 40 >> j;
            return k > 0 ? i % k == 0 : true;
        } else {
            return this.H == s.H;
        }
    }

    public OPotion b(String s) {
        this.L = s;
        return this;
    }

    public String a() {
        return this.L;
    }

    protected OPotion a(double d0) {
        this.N = d0;
        return this;
    }

    public double g() {
        return this.N;
    }

    public boolean i() {
        return this.O;
    }

    public int j() {
        return this.K;
    }

    public OPotion a(OAttribute oattribute, String s, double d0, int i) {
        OAttributeModifier oattributemodifier = new OAttributeModifier(UUID.fromString(s), this.a(), d0, i);

        this.I.put(oattribute, oattributemodifier);
        return this;
    }

    public void a(OEntityLivingBase oentitylivingbase, OBaseAttributeMap obaseattributemap, int i) {
        Iterator iterator = this.I.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();
            OAttributeInstance oattributeinstance = obaseattributemap.a((OAttribute) entry.getKey());

            if (oattributeinstance != null) {
                oattributeinstance.b((OAttributeModifier) entry.getValue());
            }
        }
    }

    public void b(OEntityLivingBase oentitylivingbase, OBaseAttributeMap obaseattributemap, int i) {
        Iterator iterator = this.I.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry entry = (Entry) iterator.next();
            OAttributeInstance oattributeinstance = obaseattributemap.a((OAttribute) entry.getKey());

            if (oattributeinstance != null) {
                OAttributeModifier oattributemodifier = (OAttributeModifier) entry.getValue();

                oattributeinstance.b(oattributemodifier);
                oattributeinstance.a(new OAttributeModifier(oattributemodifier.a(), this.a() + " " + i, this.a(i, oattributemodifier), oattributemodifier.c()));
            }
        }
    }

    public double a(int i, OAttributeModifier oattributemodifier) {
        return oattributemodifier.d() * (double) (i + 1);
    }
}
