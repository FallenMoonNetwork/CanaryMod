public class OFoodStats {

    // CanaryMod: made fields public
    public int a = 20;
    public float b = 5.0F;
    public float c;
    public int d = 0;
    private int e = 20;
    // CanaryMod: enitity
    private OEntityPlayer entity;

    public OFoodStats() {}

    public OFoodStats(OEntityPlayer oentityplayer) {
        this.entity = oentityplayer;
    }

    public void a(int i, float f) {
        // CanaryMod: Calls onFoodLevelChange
        int newLevel = Math.min(i + this.a, 20);
        int oldLevel = this.a;

        this.a = ((Integer) etc.getLoader().callHook(PluginLoader.Hook.FOODLEVEL_CHANGE, new Object[] { ((OEntityPlayerMP) this.entity).getPlayer(), Integer.valueOf(oldLevel), Integer.valueOf(newLevel) })).intValue();

        // CanaryMod: Calls onFoodSaturationChange
        float newSLevel = Math.min(this.b + (float) i * f * 2.0F, (float) this.a);
        float oldSLevel = this.b;

        this.b = ((Float) etc.getLoader().callHook(PluginLoader.Hook.FOODSATURATION_CHANGE, new Object[] { ((OEntityPlayerMP) this.entity).getPlayer(), Float.valueOf(oldSLevel), Float.valueOf(newSLevel) })).floatValue();

        ((OEntityPlayerMP) entity).getPlayer().updateLevels();
    }

    public void a(OItemFood oitemfood) {
        this.a(oitemfood.g(), oitemfood.h());
    }

    public void a(OEntityPlayer oentityplayer) {
        int i = oentityplayer.q.r;

        this.e = this.a;
        if (this.c > 4.0F) {
            // CanaryMod: Calls onFoodExhaustionChange
            float newExLevel = this.c - 4.0F;
            float oldExLevel = this.c;

            this.c = (Float) etc.getLoader().callHook(PluginLoader.Hook.FOODEXHAUSTION_CHANGE, ((OEntityPlayerMP) oentityplayer).getPlayer(), oldExLevel, newExLevel);
            if (this.b > 0.0F) {
                // CanaryMod: Calls onFoodSaturationChange
                float newLevel = Math.max(this.b - 1.0F, 0.0F);
                float oldLevel = this.b;

                this.b = (Float) etc.getLoader().callHook(PluginLoader.Hook.FOODSATURATION_CHANGE, ((OEntityPlayerMP) oentityplayer).getPlayer(), oldLevel, newLevel);
            } else if (i > 0) {
                // CanaryMod: Calls onFoodLevelChange
                int newLevel = Math.max(this.a - 1, 0);
                int oldLevel = this.a;

                this.a = (Integer) etc.getLoader().callHook(PluginLoader.Hook.FOODLEVEL_CHANGE, ((OEntityPlayerMP) oentityplayer).getPlayer(), oldLevel, newLevel);
            }
        }

        if (this.a >= 18 && oentityplayer.cm()) {
            ++this.d;
            if (this.d >= 80) {
                oentityplayer.j(1);
                this.d = 0;
            }
        } else if (this.a <= 0) {
            ++this.d;
            if (this.d >= 80) {
                if (oentityplayer.aX() > 10 || i >= 3 || oentityplayer.aX() > 1 && i >= 2) {
                    // CanaryMod: DAMAGE From starvation
                    HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(null, oentityplayer.getEntity(), DamageType.STARVATION.getDamageSource(), 1));
                    if (!ev.isCanceled()) {
                        oentityplayer.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmount());
                    }
                }

                this.d = 0;
            }
        } else {
            this.d = 0;
        }
    }

    public void a(ONBTTagCompound onbttagcompound) {
        if (onbttagcompound.b("foodLevel")) {
            this.a = onbttagcompound.e("foodLevel");
            this.d = onbttagcompound.e("foodTickTimer");
            this.b = onbttagcompound.g("foodSaturationLevel");
            this.c = onbttagcompound.g("foodExhaustionLevel");
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("foodLevel", this.a);
        onbttagcompound.a("foodTickTimer", this.d);
        onbttagcompound.a("foodSaturationLevel", this.b);
        onbttagcompound.a("foodExhaustionLevel", this.c);
    }

    public int a() {
        return this.a;
    }

    public boolean c() {
        return this.a < 20;
    }

    public void a(float f) {
        this.c = Math.min(this.c + f, 40.0F);
    }

    public float e() {
        return this.b;
    }
}
