import java.util.List;


public class OBlockPistonBase extends OBlock {

    private boolean a;
    private boolean attemptRetractBlock; // CanaryMod: Used to signal wether to retract the block attached to the stick piston.

    public OBlockPistonBase(int i, int j, boolean flag) {
        super(i, j, OMaterial.F);
        this.a = flag;
        this.a(h);
        this.c(0.5F);
        this.a(OCreativeTabs.d);
    }

    public int a(int i, int j) {
        int k = e(j);

        return k > 5 ? this.cl : (i == k ? (!f(j) && this.ct <= 0.0D && this.cu <= 0.0D && this.cv <= 0.0D && this.cw >= 1.0D && this.cx >= 1.0D && this.cy >= 1.0D ? this.cl : 110) : (i == OFacing.a[k] ? 109 : 108));
    }

    public int d() {
        return 16;
    }

    public boolean c() {
        return false;
    }

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer, int l, float f, float f1, float f2) {
        return false;
    }

    public void a(OWorld oworld, int i, int j, int k, OEntityLiving oentityliving) {
        int l = b(oworld, i, j, k, (OEntityPlayer) oentityliving);

        oworld.c(i, j, k, l);
        if (!oworld.J) {
            this.l(oworld, i, j, k);
        }

    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (!oworld.J) {
            this.l(oworld, i, j, k);
        }

    }

    public void g(OWorld oworld, int i, int j, int k) {
        if (!oworld.J && oworld.p(i, j, k) == null) {
            this.l(oworld, i, j, k);
        }

    }

    private void l(OWorld oworld, int i, int j, int k) {
        int l = oworld.g(i, j, k);
        int i1 = e(l);

        if (i1 != 7) {
            boolean flag = this.d(oworld, i, j, k, i1);

            if (flag && !f(l)) {

                if (h(oworld, i, j, k, i1)) {
                    // CanaryMod hook onPistonExtend
                    boolean allowExtension = !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PISTON_EXTEND, new Block(oworld.world, (this.a) ? Block.Type.StickyPiston.getType() : Block.Type.Piston.getType(), i, j, k, l));

                    if (allowExtension) {
                        oworld.c(i, j, k, this.cm, 0, i1);
                    }
                }
            } else if (!flag && f(l)) {
                // CanaryMod hook onPistonRetract
                // hook result is saved in attemptRetractBlock because later in the code the block is actually moved, and only there we should deny retraction.
                this.attemptRetractBlock = !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PISTON_RETRACT, new Block(oworld.world, (this.a) ? Block.Type.StickyPiston.getType() : Block.Type.Piston.getType(), i, j, k, l));

                oworld.c(i, j, k, this.cm, 1, i1);
            }

        }
    }

    private boolean d(OWorld oworld, int i, int j, int k, int l) {
        return l != 0 && oworld.l(i, j - 1, k, 0) ? true : (l != 1 && oworld.l(i, j + 1, k, 1) ? true : (l != 2 && oworld.l(i, j, k - 1, 2) ? true : (l != 3 && oworld.l(i, j, k + 1, 3) ? true : (l != 5 && oworld.l(i + 1, j, k, 5) ? true : (l != 4 && oworld.l(i - 1, j, k, 4) ? true : (oworld.l(i, j, k, 0) ? true : (oworld.l(i, j + 2, k, 1) ? true : (oworld.l(i, j + 1, k - 1, 2) ? true : (oworld.l(i, j + 1, k + 1, 3) ? true : (oworld.l(i - 1, j + 1, k, 4) ? true : oworld.l(i + 1, j + 1, k, 5)))))))))));
    }

    public void b(OWorld oworld, int i, int j, int k, int l, int i1) {
        if (l == 0) {
            oworld.d(i, j, k, i1 | 8);
        } else {
            oworld.d(i, j, k, i1);
        }

        if (l == 0) {
            if (this.i(oworld, i, j, k, i1)) {
                oworld.c(i, j, k, i1 | 8);
                oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "tile.piston.out", 0.5F, oworld.u.nextFloat() * 0.25F + 0.6F);
            } else {
                oworld.d(i, j, k, i1);
            }
        } else if (l == 1) {
            OTileEntity otileentity = oworld.p(i + OFacing.b[i1], j + OFacing.c[i1], k + OFacing.d[i1]);

            if (otileentity instanceof OTileEntityPiston) {
                ((OTileEntityPiston) otileentity).f();
            }

            oworld.c(i, j, k, OBlock.af.cm, i1);
            oworld.a(i, j, k, OBlockPistonMoving.a(this.cm, i1, i1, false, true));
            if (this.a) {
                int j1 = i + OFacing.b[i1] * 2;
                int k1 = j + OFacing.c[i1] * 2;
                int l1 = k + OFacing.d[i1] * 2;
                int i2 = oworld.a(j1, k1, l1);
                int j2 = oworld.g(j1, k1, l1);
                boolean flag = false;

                if (i2 == OBlock.af.cm) {
                    OTileEntity otileentity1 = oworld.p(j1, k1, l1);

                    if (otileentity1 instanceof OTileEntityPiston) {
                        OTileEntityPiston otileentitypiston = (OTileEntityPiston) otileentity1;

                        if (otileentitypiston.c() == i1 && otileentitypiston.b()) {
                            otileentitypiston.f();
                            i2 = otileentitypiston.a();
                            j2 = otileentitypiston.p();
                            flag = true;
                        }
                    }
                }

                if (this.attemptRetractBlock && !flag && i2 > 0 && a(i2, oworld, j1, k1, l1, false) && (OBlock.p[i2].q_() == 0 || i2 == OBlock.ac.cm || i2 == OBlock.Y.cm)) {
                    i += OFacing.b[i1];
                    j += OFacing.c[i1];
                    k += OFacing.d[i1];
                    oworld.c(i, j, k, OBlock.af.cm, j2);
                    oworld.a(i, j, k, OBlockPistonMoving.a(i2, j2, i1, false, false));
                    oworld.e(j1, k1, l1, 0);
                } else if (!flag || !this.attemptRetractBlock) { // if retraction fails normally (i2 == 0) OR the onPistonRetract returned false earlier.
                    oworld.e(i + OFacing.b[i1], j + OFacing.c[i1], k + OFacing.d[i1], 0);
                }
            } else {
                oworld.e(i + OFacing.b[i1], j + OFacing.c[i1], k + OFacing.d[i1], 0);
            }

            oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "tile.piston.in", 0.5F, oworld.u.nextFloat() * 0.15F + 0.6F);
        }
    }

    public void a(OIBlockAccess oiblockaccess, int i, int j, int k) {
        int l = oiblockaccess.g(i, j, k);

        if (f(l)) {
            switch (e(l)) {
                case 0:
                    this.a(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
                    break;

                case 1:
                    this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
                    break;

                case 2:
                    this.a(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
                    break;

                case 3:
                    this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
                    break;

                case 4:
                    this.a(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                    break;

                case 5:
                    this.a(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
            }
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

    }

    public void f() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void a(OWorld oworld, int i, int j, int k, OAxisAlignedBB oaxisalignedbb, List list, OEntity oentity) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.a(oworld, i, j, k, oaxisalignedbb, list, oentity);
    }

    public OAxisAlignedBB e(OWorld oworld, int i, int j, int k) {
        this.a(oworld, i, j, k);
        return super.e(oworld, i, j, k);
    }

    public boolean b() {
        return false;
    }

    public static int e(int i) {
        return i & 7;
    }

    public static boolean f(int i) {
        return (i & 8) != 0;
    }

    public static int b(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer) {
        if (OMathHelper.e((float) oentityplayer.t - (float) i) < 2.0F && OMathHelper.e((float) oentityplayer.v - (float) k) < 2.0F) {
            double d0 = oentityplayer.u + 1.82D - (double) oentityplayer.M;

            if (d0 - (double) j > 2.0D) {
                return 1;
            }

            if ((double) j - d0 > 0.0D) {
                return 0;
            }
        }

        int l = OMathHelper.c((double) (oentityplayer.z * 4.0F / 360.0F) + 0.5D) & 3;

        return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
    }

    private static boolean a(int i, OWorld oworld, int j, int k, int l, boolean flag) {
        if (i == OBlock.as.cm) {
            return false;
        } else {
            if (i != OBlock.ac.cm && i != OBlock.Y.cm) {
                if (OBlock.p[i].m(oworld, j, k, l) == -1.0F) {
                    return false;
                }

                if (OBlock.p[i].q_() == 2) {
                    return false;
                }

                if (!flag && OBlock.p[i].q_() == 1) {
                    return false;
                }
            } else if (f(oworld.g(j, k, l))) {
                return false;
            }

            return !(OBlock.p[i] instanceof OBlockContainer);
        }
    }

    private static boolean h(OWorld oworld, int i, int j, int k, int l) {
        int i1 = i + OFacing.b[l];
        int j1 = j + OFacing.c[l];
        int k1 = k + OFacing.d[l];
        int l1 = 0;

        while (true) {
            if (l1 < 13) {
                if (j1 <= 0 || j1 >= 255) {
                    return false;
                }

                int i2 = oworld.a(i1, j1, k1);

                if (i2 != 0) {
                    if (!a(i2, oworld, i1, j1, k1, true)) {
                        return false;
                    }

                    if (OBlock.p[i2].q_() != 1) {
                        if (l1 == 12) {
                            return false;
                        }

                        i1 += OFacing.b[l];
                        j1 += OFacing.c[l];
                        k1 += OFacing.d[l];
                        ++l1;
                        continue;
                    }
                }
            }

            return true;
        }
    }

    private boolean i(OWorld oworld, int i, int j, int k, int l) {
        int i1 = i + OFacing.b[l];
        int j1 = j + OFacing.c[l];
        int k1 = k + OFacing.d[l];
        int l1 = 0;

        while (true) {
            int i2;

            if (l1 < 13) {
                if (j1 <= 0 || j1 >= 255) {
                    return false;
                }

                i2 = oworld.a(i1, j1, k1);
                if (i2 != 0) {
                    if (!a(i2, oworld, i1, j1, k1, true)) {
                        return false;
                    }

                    if (OBlock.p[i2].q_() != 1) {
                        if (l1 == 12) {
                            return false;
                        }

                        i1 += OFacing.b[l];
                        j1 += OFacing.c[l];
                        k1 += OFacing.d[l];
                        ++l1;
                        continue;
                    }

                    OBlock.p[i2].c(oworld, i1, j1, k1, oworld.g(i1, j1, k1), 0);
                    oworld.e(i1, j1, k1, 0);
                }
            }

            while (i1 != i || j1 != j || k1 != k) {
                l1 = i1 - OFacing.b[l];
                i2 = j1 - OFacing.c[l];
                int j2 = k1 - OFacing.d[l];
                int k2 = oworld.a(l1, i2, j2);
                int l2 = oworld.g(l1, i2, j2);

                if (k2 == this.cm && l1 == i && i2 == j && j2 == k) {
                    oworld.a(i1, j1, k1, OBlock.af.cm, l | (this.a ? 8 : 0), false);
                    oworld.a(i1, j1, k1, OBlockPistonMoving.a(OBlock.ad.cm, l | (this.a ? 8 : 0), l, true, false));
                } else {
                    oworld.a(i1, j1, k1, OBlock.af.cm, l2, false);
                    oworld.a(i1, j1, k1, OBlockPistonMoving.a(k2, l2, l, true, false));
                }

                i1 = l1;
                j1 = i2;
                k1 = j2;
            }

            return true;
        }
    }
}
