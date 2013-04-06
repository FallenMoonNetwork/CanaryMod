import java.util.Iterator;
import java.util.List;

public class OEntityPotion extends OEntityThrowable {

    private OItemStack c;

    public OEntityPotion(OWorld oworld) {
        super(oworld);
    }

    public OEntityPotion(OWorld oworld, OEntityLiving oentityliving, int i) {
        this(oworld, oentityliving, new OItemStack(OItem.bt, 1, i));
    }

    public OEntityPotion(OWorld oworld, OEntityLiving oentityliving, OItemStack oitemstack) {
        super(oworld, oentityliving);
        this.c = oitemstack;
    }

    public OEntityPotion(OWorld oworld, double d0, double d1, double d2, OItemStack oitemstack) {
        super(oworld, d0, d1, d2);
        this.c = oitemstack;
    }

    protected float g() {
        return 0.05F;
    }

    protected float c() {
        return 0.5F;
    }

    protected float d() {
        return -20.0F;
    }

    public void a(int i) {
        if (this.c == null) {
            this.c = new OItemStack(OItem.bt, 1, 0);
        }

        this.c.b(i);
    }

    public int i() {
        if (this.c == null) {
            this.c = new OItemStack(OItem.bt, 1, 0);
        }

        return this.c.k();
    }

    protected void a(OMovingObjectPosition omovingobjectposition) {
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PROJECTILE_HIT, new Projectile(this), omovingobjectposition.g == null ? null : omovingobjectposition.g.getEntity()) && !this.q.I) {
            List list = OItem.bt.g(this.c);

            if (list != null && !list.isEmpty()) {
                OAxisAlignedBB oaxisalignedbb = this.E.b(4.0D, 2.0D, 4.0D);
                List list1 = this.q.a(OEntityLiving.class, oaxisalignedbb);

                if (list1 != null && !list1.isEmpty()) {
                    Iterator iterator = list1.iterator();

                    while (iterator.hasNext()) {
                        OEntityLiving oentityliving = (OEntityLiving) iterator.next();
                        double d0 = this.e(oentityliving);

                        if (d0 < 16.0D) {
                            double d1 = 1.0D - Math.sqrt(d0) / 4.0D;

                            if (oentityliving == omovingobjectposition.g) {
                                d1 = 1.0D;
                            }

                            Iterator iterator1 = list.iterator();

                            while (iterator1.hasNext()) {
                                OPotionEffect opotioneffect = (OPotionEffect) iterator1.next();
                                int i = opotioneffect.a();

                                if (OPotion.a[i].b()) {
                                    OPotion.a[i].a(this.h(), oentityliving, opotioneffect.c(), d1);
                                } else {
                                    int j = (int) (d1 * (double) opotioneffect.b() + 0.5D);

                                    if (j > 20) {
                                        oentityliving.d(new OPotionEffect(i, j, opotioneffect.c()));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            this.q.e(2002, (int) Math.round(this.u), (int) Math.round(this.v), (int) Math.round(this.w), this.i());
            this.w();
        }
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        if (onbttagcompound.b("Potion")) {
            this.c = OItemStack.a(onbttagcompound.l("Potion"));
        } else {
            this.a(onbttagcompound.e("potionValue"));
        }

        if (this.c == null) {
            this.w();
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        if (this.c != null) {
            onbttagcompound.a("Potion", this.c.b(new ONBTTagCompound()));
        }
    }
}
