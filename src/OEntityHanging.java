import java.util.Iterator;
import java.util.List;

public abstract class OEntityHanging extends OEntity {

    private int e;
    public int a;
    public int b;
    public int c;
    public int d;
    
    // CanaryMod: Our variables :3
    public HangingEntity hangingEntity = new HangingEntity(this);
    // CanaryMod: End

    public OEntityHanging(OWorld oworld) {
        super(oworld);
        this.e = 0;
        this.a = 0;
        this.N = 0.0F;
        this.a(0.5F, 0.5F);
    }

    public OEntityHanging(OWorld oworld, int i, int j, int k, int l) {
        this(oworld);
        this.b = i;
        this.c = j;
        this.d = k;
    }

    protected void a() {}

    public void a(int i) {
        this.a = i;
        this.C = this.A = (float) (i * 90);
        float f = (float) this.d();
        float f1 = (float) this.g();
        float f2 = (float) this.d();

        if (i != 2 && i != 0) {
            f = 0.5F;
        } else {
            f2 = 0.5F;
            this.A = this.C = (float) (ODirection.f[i] * 90);
        }

        f /= 32.0F;
        f1 /= 32.0F;
        f2 /= 32.0F;
        float f3 = (float) this.b + 0.5F;
        float f4 = (float) this.c + 0.5F;
        float f5 = (float) this.d + 0.5F;
        float f6 = 0.5625F;

        if (i == 2) {
            f5 -= f6;
        }

        if (i == 1) {
            f3 -= f6;
        }

        if (i == 0) {
            f5 += f6;
        }

        if (i == 3) {
            f3 += f6;
        }

        if (i == 2) {
            f3 -= this.b(this.d());
        }

        if (i == 1) {
            f5 += this.b(this.d());
        }

        if (i == 0) {
            f3 += this.b(this.d());
        }

        if (i == 3) {
            f5 -= this.b(this.d());
        }

        f4 += this.b(this.g());
        this.b((double) f3, (double) f4, (double) f5);
        float f7 = -0.03125F;

        this.E.b((double) (f3 - f - f7), (double) (f4 - f1 - f7), (double) (f5 - f2 - f7), (double) (f3 + f + f7), (double) (f4 + f1 + f7), (double) (f5 + f2 + f7));
    }

    private float b(int i) {
        return i == 32 ? 0.5F : (i == 64 ? 0.5F : 0.0F);
    }

    public void l_() {
        if (this.e++ == 100 && !this.q.I) {
            this.e = 0;
            if (!this.M && !this.c()) {
                // CanaryMod: onHangingEntityDestroyed
                if((Boolean)etc.getLoader().callHook(PluginLoader.Hook.HANGING_ENTITY_DESTROYED, this.getEntity(), null)){
                    return;
                }//
                this.w();
                this.h();
            }
        }
    }

    public boolean c() {
        if (!this.q.a((OEntity) this, this.E).isEmpty()) {
            return false;
        } else {
            int i = Math.max(1, this.d() / 16);
            int j = Math.max(1, this.g() / 16);
            int k = this.b;
            int l = this.c;
            int i1 = this.d;

            if (this.a == 2) {
                k = OMathHelper.c(this.u - (double) ((float) this.d() / 32.0F));
            }

            if (this.a == 1) {
                i1 = OMathHelper.c(this.w - (double) ((float) this.d() / 32.0F));
            }

            if (this.a == 0) {
                k = OMathHelper.c(this.u - (double) ((float) this.d() / 32.0F));
            }

            if (this.a == 3) {
                i1 = OMathHelper.c(this.w - (double) ((float) this.d() / 32.0F));
            }

            l = OMathHelper.c(this.v - (double) ((float) this.g() / 32.0F));

            for (int j1 = 0; j1 < i; ++j1) {
                for (int k1 = 0; k1 < j; ++k1) {
                    OMaterial omaterial;

                    if (this.a != 2 && this.a != 0) {
                        omaterial = this.q.g(this.b, l + k1, i1 + j1);
                    } else {
                        omaterial = this.q.g(k + j1, l + k1, this.d);
                    }

                    if (!omaterial.a()) {
                        return false;
                    }
                }
            }

            List list = this.q.b((OEntity) this, this.E);
            Iterator iterator = list.iterator();

            OEntity oentity;

            do {
                if (!iterator.hasNext()) {
                    return true;
                }

                oentity = (OEntity) iterator.next();
            } while (!(oentity instanceof OEntityHanging));

            return false;
        }
    }

    public boolean K() {
        return true;
    }

    public boolean j(OEntity oentity) {
        return oentity instanceof OEntityPlayer ? this.a(ODamageSource.a((OEntityPlayer) oentity), 0) : false;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.aq()) {
            return false;
        } else {
            if (!this.M && !this.q.I) {
                // CanaryMod: onHangingEntityDestroyed
                if((Boolean)etc.getLoader().callHook(PluginLoader.Hook.HANGING_ENTITY_DESTROYED, this.getEntity(), odamagesource.damageSource)){
                    return false;
                }//
                this.w();
                this.J();
                OEntityPlayer oentityplayer = null;

                if (odamagesource.i() instanceof OEntityPlayer) {
                    oentityplayer = (OEntityPlayer) odamagesource.i();
                }

                if (oentityplayer != null && oentityplayer.ce.d) {
                    return true;
                }

                this.h();
            }

            return true;
        }
    }

    public void d(double d0, double d1, double d2) {
        if (!this.q.I && !this.M && d0 * d0 + d1 * d1 + d2 * d2 > 0.0D) {
            // CanaryMod: onHangingEntityDestroyed
            if((Boolean)etc.getLoader().callHook(PluginLoader.Hook.HANGING_ENTITY_DESTROYED, this.getEntity())){
                return;
            }//
            this.w();
            this.h();
        }
    }

    public void g(double d0, double d1, double d2) {
        if (!this.q.I && !this.M && d0 * d0 + d1 * d1 + d2 * d2 > 0.0D) {
            // CanaryMod: onHangingEntityDestroyed
            if((Boolean)etc.getLoader().callHook(PluginLoader.Hook.HANGING_ENTITY_DESTROYED, this.getEntity())){
                return;
            }//
            this.w();
            this.h();
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Direction", (byte) this.a);
        onbttagcompound.a("TileX", this.b);
        onbttagcompound.a("TileY", this.c);
        onbttagcompound.a("TileZ", this.d);
        switch (this.a) {
            case 0:
                onbttagcompound.a("Dir", (byte) 2);
                break;

            case 1:
                onbttagcompound.a("Dir", (byte) 1);
                break;

            case 2:
                onbttagcompound.a("Dir", (byte) 0);
                break;

            case 3:
                onbttagcompound.a("Dir", (byte) 3);
        }
    }

    public void a(ONBTTagCompound onbttagcompound) {
        if (onbttagcompound.b("Direction")) {
            this.a = onbttagcompound.c("Direction");
        } else {
            switch (onbttagcompound.c("Dir")) {
                case 0:
                    this.a = 2;
                    break;

                case 1:
                    this.a = 1;
                    break;

                case 2:
                    this.a = 0;
                    break;

                case 3:
                    this.a = 3;
            }
        }

        this.b = onbttagcompound.e("TileX");
        this.c = onbttagcompound.e("TileY");
        this.d = onbttagcompound.e("TileZ");
        this.a(this.a);
    }

    public abstract int d();

    public abstract int g();

    public abstract void h();
    
    //CanaryMod: our methods go here :3
    @Override
    public HangingEntity getEntity(){
        return this.hangingEntity;
    }
    
}
