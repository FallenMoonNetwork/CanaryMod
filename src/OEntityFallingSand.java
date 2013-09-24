import java.util.ArrayList;
import java.util.Iterator;

public class OEntityFallingSand extends OEntity {

    public int a;
    public int b;
    public int c;
    public boolean d;
    private boolean f;
    private boolean g;
    private int h;
    private float i;
    public ONBTTagCompound e;

    public OEntityFallingSand(OWorld oworld) {
        super(oworld);
        this.d = true;
        this.h = 40;
        this.i = 2.0F;
    }

    public OEntityFallingSand(OWorld oworld, double d0, double d1, double d2, int i) {
        this(oworld, d0, d1, d2, i, 0);
    }

    public OEntityFallingSand(OWorld oworld, double d0, double d1, double d2, int i, int j) {
        super(oworld);
        this.d = true;
        this.h = 40;
        this.i = 2.0F;
        this.a = i;
        this.b = j;
        this.m = true;
        this.a(0.98F, 0.98F);
        this.N = this.P / 2.0F;
        this.b(d0, d1, d2);
        this.x = 0.0D;
        this.y = 0.0D;
        this.z = 0.0D;
        this.r = d0;
        this.s = d1;
        this.t = d2;
    }

    protected boolean e_() {
        return false;
    }

    protected void a() {}

    public boolean L() {
        return !this.M;
    }

    public void l_() {
        if (this.a == 0) {
            this.x();
        } else {
            this.r = this.u;
            this.s = this.v;
            this.t = this.w;
            ++this.c;
            this.y -= 0.03999999910593033D;
            this.d(this.x, this.y, this.z);
            this.x *= 0.9800000190734863D;
            this.y *= 0.9800000190734863D;
            this.z *= 0.9800000190734863D;
            if (!this.q.I) {
                int i = OMathHelper.c(this.u);
                int j = OMathHelper.c(this.v);
                int k = OMathHelper.c(this.w);

                if (this.c == 1) {
                    if (this.q.a(i, j, k) != this.a) {
                        this.x();
                        return;
                    }

                    this.q.i(i, j, k);
                }

                if (this.F) {
                    this.x *= 0.699999988079071D;
                    this.z *= 0.699999988079071D;
                    this.y *= -0.5D;
                    if (this.q.a(i, j, k) != OBlock.ah.cF) {
                        this.x();
                        if (!this.f && this.q.a(this.a, i, j, k, true, 1, (OEntity) null, (OItemStack) null) && !OBlockSand.a_(this.q, i, j - 1, k) && this.q.f(i, j, k, this.a, this.b, 3)) {
                            if (OBlock.s[this.a] instanceof OBlockSand) {
                                ((OBlockSand) OBlock.s[this.a]).a_(this.q, i, j, k, this.b);
                            }

                            if (this.e != null && OBlock.s[this.a] instanceof OITileEntityProvider) {
                                OTileEntity otileentity = this.q.r(i, j, k);

                                if (otileentity != null) {
                                    ONBTTagCompound onbttagcompound = new ONBTTagCompound();

                                    otileentity.b(onbttagcompound);
                                    Iterator iterator = this.e.c().iterator();

                                    while (iterator.hasNext()) {
                                        ONBTBase onbtbase = (ONBTBase) iterator.next();

                                        if (!onbtbase.e().equals("x") && !onbtbase.e().equals("y") && !onbtbase.e().equals("z")) {
                                            onbttagcompound.a(onbtbase.e(), onbtbase.b());
                                        }
                                    }

                                    otileentity.a(onbttagcompound);
                                    otileentity.e();
                                }
                            }
                        } else if (this.d && !this.f) {
                            this.a(new OItemStack(this.a, 1, OBlock.s[this.a].a(this.b)), 0.0F);
                        }
                    }
                } else if (this.c > 100 && !this.q.I && (j < 1 || j > 256) || this.c > 600) {
                    if (this.d) {
                        this.a(new OItemStack(this.a, 1, OBlock.s[this.a].a(this.b)), 0.0F);
                    }

                    this.x();
                }
            }
        }
    }

    protected void b(float f) {
        if (this.g) {
            int i = OMathHelper.f(f - 1.0F);

            if (i > 0) {
                ArrayList arraylist = new ArrayList(this.q.b((OEntity) this, this.E));
                ODamageSource odamagesource = this.a == OBlock.cm.cF ? ODamageSource.m : ODamageSource.n;
                Iterator iterator = arraylist.iterator();

                while (iterator.hasNext()) {
                    OEntity oentity = (OEntity) iterator.next();
                    HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(this.getEntity(), oentity.getEntity(), odamagesource.damageSource, (float) Math.min(OMathHelper.d((float) i * this.i), this.h)));
                    if (!ev.isCanceled()) {
                         oentity.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmountFloat());
                    }
                }

                if (this.a == OBlock.cm.cF && (double) this.ab.nextFloat() < 0.05000000074505806D + (double) i * 0.05D) {
                    int j = this.b >> 2;
                    int k = this.b & 3;

                    ++j;
                    if (j > 2) {
                        this.f = true;
                    } else {
                        this.b = k | j << 2;
                    }
                }
            }
        }
    }

    protected void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("Tile", (byte) this.a);
        onbttagcompound.a("TileID", this.a);
        onbttagcompound.a("Data", (byte) this.b);
        onbttagcompound.a("Time", (byte) this.c);
        onbttagcompound.a("DropItem", this.d);
        onbttagcompound.a("HurtEntities", this.g);
        onbttagcompound.a("FallHurtAmount", this.i);
        onbttagcompound.a("FallHurtMax", this.h);
        if (this.e != null) {
            onbttagcompound.a("TileEntityData", this.e);
        }
    }

    protected void a(ONBTTagCompound onbttagcompound) {
        if (onbttagcompound.b("TileID")) {
            this.a = onbttagcompound.e("TileID");
        } else {
            this.a = onbttagcompound.c("Tile") & 255;
        }

        this.b = onbttagcompound.c("Data") & 255;
        this.c = onbttagcompound.c("Time") & 255;
        if (onbttagcompound.b("HurtEntities")) {
            this.g = onbttagcompound.n("HurtEntities");
            this.i = onbttagcompound.g("FallHurtAmount");
            this.h = onbttagcompound.e("FallHurtMax");
        } else if (this.a == OBlock.cm.cF) {
            this.g = true;
        }

        if (onbttagcompound.b("DropItem")) {
            this.d = onbttagcompound.n("DropItem");
        }

        if (onbttagcompound.b("TileEntityData")) {
            this.e = onbttagcompound.l("TileEntityData");
        }

        if (this.a == 0) {
            this.a = OBlock.J.cF;
        }
    }

    public void a(boolean flag) {
        this.g = flag;
    }

    public void a(OCrashReportCategory ocrashreportcategory) {
        super.a(ocrashreportcategory);
        ocrashreportcategory.a("Immitating block ID", Integer.valueOf(this.a));
        ocrashreportcategory.a("Immitating block data", Integer.valueOf(this.b));
    }
}
