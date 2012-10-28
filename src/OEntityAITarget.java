
public abstract class OEntityAITarget extends OEntityAIBase {

    protected OEntityLiving d;
    protected float e;
    protected boolean f;
    private boolean a;
    private int b;
    private int c;
    private int g;

    public OEntityAITarget(OEntityLiving oentityliving, float f, boolean flag) {
        this(oentityliving, f, flag, false);
    }

    public OEntityAITarget(OEntityLiving oentityliving, float f, boolean flag, boolean flag1) {
        super();
        this.b = 0;
        this.c = 0;
        this.g = 0;
        this.d = oentityliving;
        this.e = f;
        this.f = flag;
        this.a = flag1;
    }

    public boolean b() {
        OEntityLiving oentityliving = this.d.aF();

        if (oentityliving == null) {
            return false;
        } else if (!oentityliving.S()) {
            return false;
        } else if (this.d.e(oentityliving) > (double) (this.e * this.e)) {
            return false;
        } else {
            if (this.f) {
                if (this.d.az().a(oentityliving)) {
                    this.g = 0;
                } else if (++this.g > 60) {
                    return false;
                }
            }

            return true;
        }
    }

    public void c() {
        this.b = 0;
        this.c = 0;
        this.g = 0;
    }

    public void d() {
        this.d.b((OEntityLiving) null);
    }

    protected boolean a(OEntityLiving oentityliving, boolean flag) {
        if (oentityliving == null) {
            return false;
        } else if (oentityliving == this.d) {
            return false;
        } else if (!oentityliving.S()) {
            return false;
        } else if (!this.d.a(oentityliving.getClass())) {
                return false;
            } else {
            if (this.d instanceof OEntityTameable && ((OEntityTameable) this.d).m()) {
                if (oentityliving instanceof OEntityTameable && ((OEntityTameable) oentityliving).m()) {
                        return false;
                    }

                if (oentityliving == ((OEntityTameable) this.d).p()) {
                        return false;
                    }
            } else if (oentityliving instanceof OEntityPlayer && !flag && ((OEntityPlayer) oentityliving).cf.a) {
                    return false;
                }

            if (!this.d.e(OMathHelper.c(oentityliving.t), OMathHelper.c(oentityliving.u), OMathHelper.c(oentityliving.v))) {
                    return false;
            } else if (this.f && !this.d.az().a(oentityliving)) {
                    return false;
                } else {
                    if (this.a) {
                        if (--this.c <= 0) {
                            this.b = 0;
                        }

                        if (this.b == 0) {
                            this.b = this.a(oentityliving) ? 1 : 2;
                        }

                        if (this.b == 2) {
                            return false;
                        }
                    }

                    // CanaryMod - MOB_TARGET
                    if (oentityliving.entity.isPlayer()) {
                        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.MOB_TARGET, oentityliving.entity.getPlayer(), this.d.entity)) {
                            return false;
                        }
                    }
                    // CanaryMod end

                    return true;
                }
            }
    }

    private boolean a(OEntityLiving oentityliving) {
        this.c = 10 + this.d.aA().nextInt(5);
        OPathEntity opathentity = this.d.ay().a(oentityliving);

        if (opathentity == null) {
            return false;
        } else {
            OPathPoint opathpoint = opathentity.c();

            if (opathpoint == null) {
                return false;
            } else {
                int i = opathpoint.a - OMathHelper.c(oentityliving.t);
                int j = opathpoint.c - OMathHelper.c(oentityliving.v);

                return (double) (i * i + j * j) <= 2.25D;
            }
        }
    }
}
