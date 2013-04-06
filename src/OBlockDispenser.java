import java.util.Random;

public class OBlockDispenser extends OBlockContainer {

    public static final OIRegistry a = new ORegistryDefaulted(new OBehaviorDefaultDispenseItem());
    protected Random b = new Random();

    protected OBlockDispenser(int i) {
        super(i, OMaterial.e);
        this.a(OCreativeTabs.d);
    }

    public int a(OWorld oworld) {
        return 4;
    }

    public void a(OWorld oworld, int i, int j, int k) {
        super.a(oworld, i, j, k);
        this.k(oworld, i, j, k);
    }

    private void k(OWorld oworld, int i, int j, int k) {
        if (!oworld.I) {
            int l = oworld.a(i, j, k - 1);
            int i1 = oworld.a(i, j, k + 1);
            int j1 = oworld.a(i - 1, j, k);
            int k1 = oworld.a(i + 1, j, k);
            byte b0 = 3;

            if (OBlock.s[l] && !OBlock.s[i1]) {
                b0 = 3;
            }

            if (OBlock.s[i1] && !OBlock.s[l]) {
                b0 = 2;
            }

            if (OBlock.s[j1] && !OBlock.s[k1]) {
                b0 = 5;
            }

            if (OBlock.s[k1] && !OBlock.s[j1]) {
                b0 = 4;
            }

            oworld.b(i, j, k, b0, 2);
        }
    }

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer, int l, float f, float f1, float f2) {
        if (oworld.I) {
            return true;
        } else {
            OTileEntityDispenser otileentitydispenser = (OTileEntityDispenser) oworld.r(i, j, k);

            if (otileentitydispenser != null) {
                oentityplayer.a(otileentitydispenser);
            }

            return true;
        }
    }

    protected void j_(OWorld oworld, int i, int j, int k) {
        OBlockSourceImpl oblocksourceimpl = new OBlockSourceImpl(oworld, i, j, k);
        OTileEntityDispenser otileentitydispenser = (OTileEntityDispenser) oblocksourceimpl.j();

        if (otileentitydispenser != null) {
            int l = otileentitydispenser.j();

            if (l < 0) {
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser(otileentitydispenser), null)) {
                    oworld.e(1001, i, j, k, 0);
                }
            } else {
                OItemStack oitemstack = otileentitydispenser.a(l);
                OIBehaviorDispenseItem oibehaviordispenseitem = this.a(oitemstack);

                if (oibehaviordispenseitem != OIBehaviorDispenseItem.a) {
                    OItemStack oitemstack1 = oibehaviordispenseitem.a(oblocksourceimpl, oitemstack);

                    otileentitydispenser.a(l, oitemstack1.a == 0 ? null : oitemstack1);
                }
            }
        }
    }

    protected OIBehaviorDispenseItem a(OItemStack oitemstack) {
        return (OIBehaviorDispenseItem) a.a(oitemstack.b());
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        boolean flag = oworld.C(i, j, k) || oworld.C(i, j + 1, k);
        int i1 = oworld.h(i, j, k);
        boolean flag1 = (i1 & 8) != 0;

        if (flag && !flag1) {
            oworld.a(i, j, k, this.cz, this.a(oworld));
            oworld.b(i, j, k, i1 | 8, 4);
        } else if (!flag && flag1) {
            oworld.b(i, j, k, i1 & -9, 4);
        }
    }

    public void a(OWorld oworld, int i, int j, int k, Random random) {
        if (!oworld.I) {
            this.j_(oworld, i, j, k);
        }
    }

    public OTileEntity b(OWorld oworld) {
        return new OTileEntityDispenser();
    }

    public void a(OWorld oworld, int i, int j, int k, OEntityLiving oentityliving, OItemStack oitemstack) {
        int l = OBlockPistonBase.a(oworld, i, j, k, oentityliving);

        oworld.b(i, j, k, l, 2);
        if (oitemstack.t()) {
            ((OTileEntityDispenser) oworld.r(i, j, k)).a(oitemstack.s());
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l, int i1) {
        OTileEntityDispenser otileentitydispenser = (OTileEntityDispenser) oworld.r(i, j, k);

        if (otileentitydispenser != null) {
            for (int j1 = 0; j1 < otileentitydispenser.j_(); ++j1) {
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
                        OEntityItem oentityitem = new OEntityItem(oworld, (double) ((float) i + f), (double) ((float) j + f1), (double) ((float) k + f2), new OItemStack(oitemstack.c, k1, oitemstack.k()));

                        if (oitemstack.p()) {
                            oentityitem.d().d((ONBTTagCompound) oitemstack.q().b());
                        }

                        float f3 = 0.05F;

                        oentityitem.x = (double) ((float) this.b.nextGaussian() * f3);
                        oentityitem.y = (double) ((float) this.b.nextGaussian() * f3 + 0.2F);
                        oentityitem.z = (double) ((float) this.b.nextGaussian() * f3);
                        oworld.d((OEntity) oentityitem);
                    }
                }
            }

            oworld.m(i, j, k, l);
        }

        super.a(oworld, i, j, k, l, i1);
    }

    public static OIPosition a(OIBlockSource oiblocksource) {
        OEnumFacing oenumfacing = j_(oiblocksource.h());
        double d0 = oiblocksource.a() + 0.7D * (double) oenumfacing.c();
        double d1 = oiblocksource.b() + 0.7D * (double) oenumfacing.d();
        double d2 = oiblocksource.c() + 0.7D * (double) oenumfacing.e();

        return new OPositionImpl(d0, d1, d2);
    }

    public static OEnumFacing j_(int i) {
        return OEnumFacing.a(i & 7);
    }

    public boolean q_() {
        return true;
    }

    public int b_(OWorld oworld, int i, int j, int k, int l) {
        return OContainer.b((OIInventory) oworld.r(i, j, k));
    }
}
