import java.util.Random;

public class OBlockDispenser extends OBlockContainer {

    public static final OIRegistry a = new ORegistryDefaulted(new OBehaviorDefaultDispenseItem());
    private Random b = new Random();

    protected OBlockDispenser(int i) {
        super(i, OMaterial.e);
        this.cl = 45;
        this.a(OCreativeTabs.d);
    }

    public int r_() {
        return 4;
    }

    public int a(int i, Random random, int j) {
        return OBlock.S.cm;
    }

    public void g(OWorld oworld, int i, int j, int k) {
        super.g(oworld, i, j, k);
        this.l(oworld, i, j, k);
    }

    private void l(OWorld oworld, int i, int j, int k) {
        if (!oworld.J) {
            int l = oworld.a(i, j, k - 1);
            int i1 = oworld.a(i, j, k + 1);
            int j1 = oworld.a(i - 1, j, k);
            int k1 = oworld.a(i + 1, j, k);
            byte b0 = 3;

            if (OBlock.q[l] && !OBlock.q[i1]) {
                b0 = 3;
            }

            if (OBlock.q[i1] && !OBlock.q[l]) {
                b0 = 2;
            }

            if (OBlock.q[j1] && !OBlock.q[k1]) {
                b0 = 5;
            }

            if (OBlock.q[k1] && !OBlock.q[j1]) {
                b0 = 4;
            }

            oworld.c(i, j, k, b0);
        }
    }

    public int a(int i) {
        return i == 1 ? this.cl + 17 : (i == 0 ? this.cl + 17 : (i == 3 ? this.cl + 1 : this.cl));
    }

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer, int l, float f, float f1, float f2) {
        if (oworld.J) {
            return true;
        } else {
            OTileEntityDispenser otileentitydispenser = (OTileEntityDispenser) oworld.p(i, j, k);

            if (otileentitydispenser != null) {
                oentityplayer.a(otileentitydispenser);
            }

            return true;
        }
    }

    void n(OWorld oworld, int i, int j, int k) { // CanaryMod: private -> package-private
        OBlockSourceImpl oblocksourceimpl = new OBlockSourceImpl(oworld, i, j, k);
        OTileEntityDispenser otileentitydispenser = (OTileEntityDispenser) oblocksourceimpl.j();

        if (otileentitydispenser != null) {
            int l = otileentitydispenser.i();

            if (l < 0) {
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), null)) {
                    oworld.f(1001, i, j, k, 0);
                }
            } else {
                OItemStack oitemstack = otileentitydispenser.a(l);
                OIBehaviorDispenseItem oibehaviordispenseitem = (OIBehaviorDispenseItem) a.a(oitemstack.b());

                if (oibehaviordispenseitem != OIBehaviorDispenseItem.a) {
                    // TODO Add dispense hook in oibehaviordispenseitem implementations
                    OItemStack oitemstack1 = oibehaviordispenseitem.a(oblocksourceimpl, oitemstack);

                    otileentitydispenser.a(l, oitemstack1.a == 0 ? null : oitemstack1);
                }
            }
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (l > 0 && OBlock.p[l].i()) {
            boolean flag = oworld.z(i, j, k) || oworld.z(i, j + 1, k);

            if (flag) {
                oworld.a(i, j, k, this.cm, this.r_());
            }
        }
    }

    public void b(OWorld oworld, int i, int j, int k, Random random) {
        if (!oworld.J && (oworld.z(i, j, k) || oworld.z(i, j + 1, k))) {
            this.n(oworld, i, j, k);
        }
    }

    public OTileEntity a(OWorld oworld) {
        return new OTileEntityDispenser();
    }

    public void a(OWorld oworld, int i, int j, int k, OEntityLiving oentityliving) {
        int l = OMathHelper.c((double) (oentityliving.z * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            oworld.c(i, j, k, 2);
        }

        if (l == 1) {
            oworld.c(i, j, k, 5);
        }

        if (l == 2) {
            oworld.c(i, j, k, 3);
        }

        if (l == 3) {
            oworld.c(i, j, k, 4);
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        OTileEntityDispenser otileentitydispenser = (OTileEntityDispenser) oworld.p(i, j, k);

        if (otileentitydispenser != null) {
            for (int j1 = 0; j1 < otileentitydispenser.k_(); ++j1) {
                OItemStack oitemstack = otileentitydispenser.a(j1);

                if (oitemstack != null) {
                    float f = this.b.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.b.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.b.nextFloat() * 0.8F + 0.1F;

                    while (oitemstack.a > 0) {
                        int k1 = this.b.nextInt(21) + 10;

                        if (k1 > oitemstack.a) {
                            k1 = oitemstack.a;
                        }

                        oitemstack.a -= k1;
                        OEntityItem oentityitem = new OEntityItem(oworld, (double) ((float) i + f), (double) ((float) j + f1), (double) ((float) k + f2), new OItemStack(oitemstack.c, k1, oitemstack.j()));

                        if (oitemstack.o()) {
                            oentityitem.a.d((ONBTTagCompound) oitemstack.p().b());
                        }

                        float f3 = 0.05F;

                        oentityitem.w = (double) ((float) this.b.nextGaussian() * f3);
                        oentityitem.x = (double) ((float) this.b.nextGaussian() * f3 + 0.2F);
                        oentityitem.y = (double) ((float) this.b.nextGaussian() * f3);
                        oworld.d((OEntity) oentityitem);
                    }
                }
            }
        }

        super.a(oworld, i, j, k, l, i1);
    }

    public static OIPosition a(OIBlockSource oiblocksource) {
        OEnumFacing oenumfacing = OEnumFacing.a(oiblocksource.h());
        double d0 = oiblocksource.a() + 0.6D * (double) oenumfacing.c();
        double d1 = oiblocksource.b();
        double d2 = oiblocksource.c() + 0.6D * (double) oenumfacing.e();

        return new OPositionImpl(d0, d1, d2);
    }
}
