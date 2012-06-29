
public abstract class OEntityAITarget extends OEntityAIBase {

    protected OEntityLiving c;
    protected float d;
    protected boolean e;
    private boolean a;
    private int b;
    private int f;
    private int g;


    public OEntityAITarget(OEntityLiving var1, float var2, boolean var3) {
        this(var1, var2, var3, false);
    }

    public OEntityAITarget(OEntityLiving var1, float var2, boolean var3, boolean var4) {
        super();
        this.b = 0;
        this.f = 0;
        this.g = 0;
        this.c = var1;
        this.d = var2;
        this.e = var3;
        this.a = var4;
    }

    public boolean b() {
        OEntityLiving var1 = this.c.at();
        if (var1 == null) {
            return false;
        } else if (!var1.aE()) {
            return false;
        } else if (this.c.j(var1) > (double) (this.d * this.d)) {
            return false;
        } else {
            if (this.e) {
                if (!this.c.am().a(var1)) {
                    if (++this.g > 60) {
                        return false;
                    }
                } else {
                    this.g = 0;
                }
            }

            return true;
        }
    }

    public void c() {
        this.b = 0;
        this.f = 0;
        this.g = 0;
    }

    public void d() {
        this.c.b((OEntityLiving) null);
    }

    protected boolean a(OEntityLiving var1, boolean var2) {
        if (var1 == null) {
            return false;
        } else if (var1 == this.c) {
            return false;
        } else if (!var1.aE()) {
            return false;
        } else if (var1.bw.e > this.c.bw.b && var1.bw.b < this.c.bw.e) {
            if (!this.c.a(var1.getClass())) {
                return false;
            } else {
                if (this.c instanceof OEntityTamable && ((OEntityTamable) this.c).u_()) {
                    if (var1 instanceof OEntityTamable && ((OEntityTamable) var1).u_()) {
                        return false;
                    }

                    if (var1 == ((OEntityTamable) this.c).w_()) {
                        return false;
                    }
                } else if (var1 instanceof OEntityPlayer && !var2 && ((OEntityPlayer) var1).L.a) {
                    return false;
                }

                if (!this.c.e(OMathHelper.b(var1.bm), OMathHelper.b(var1.bn), OMathHelper.b(var1.bo))) {
                    return false;
                } else if (this.e && !this.c.am().a(var1)) {
                    return false;
                } else {
                    if (this.a) {
                        if (--this.f <= 0) {
                            this.b = 0;
                        }

                        if (this.b == 0) {
                            this.b = this.a(var1) ? 1 : 2;
                        }

                        if (this.b == 2) {
                            return false;
                        }
                    }

                    //CanaryMod - MOB_TARGET
                    if (var1.entity.isPlayer()) {
                        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, var1.entity.getPlayer(), this.c.entity)) {
                            return false;
                        }
                    }
                    //CanaryMod end

                    return true;
                }
            }
        } else {
            return false;
        }
    }

    private boolean a(OEntityLiving var1) {
        this.f = 10 + this.c.an().nextInt(5);
        OPathEntity var2 = this.c.al().a(var1);
        if (var2 == null) {
            return false;
        } else {
            OPathPoint var3 = var2.c();
            if (var3 == null) {
                return false;
            } else {
                int var4 = var3.a - OMathHelper.b(var1.bm);
                int var5 = var3.c - OMathHelper.b(var1.bo);
                return (double) (var4 * var4 + var5 * var5) <= 2.25D;
            }
        }
    }
}
