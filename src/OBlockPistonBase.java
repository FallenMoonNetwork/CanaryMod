import java.util.List;

public class OBlockPistonBase extends OBlock {

    private final boolean a;

    private boolean attemptRetractBlock; // CanaryMod: Used to signal wether to retract the block attached to the stick piston.

    public OBlockPistonBase(int i, boolean flag) {
        super(i, OMaterial.F);
        this.a = flag;
        this.a(j);
        this.c(0.5F);
        this.a(OCreativeTabs.d);
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

    public void a(OWorld oworld, int i, int j, int k, OEntityLiving oentityliving, OItemStack oitemstack) {
        int l = a(oworld, i, j, k, oentityliving);

        oworld.b(i, j, k, l, 2);
        if (!oworld.I) {
            this.k(oworld, i, j, k);
        }
    }

    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (!oworld.I) {
            this.k(oworld, i, j, k);
        }
    }

    public void a(OWorld oworld, int i, int j, int k) {
        if (!oworld.I && oworld.r(i, j, k) == null) {
            this.k(oworld, i, j, k);
        }
    }

    private void k(OWorld oworld, int i, int j, int k) {
        int l = oworld.h(i, j, k);
        int i1 = d(l);

        if (i1 != 7) {
            boolean flag = this.d(oworld, i, j, k, i1);

            if (flag && !e(l)) {
                if (e(oworld, i, j, k, i1)) {
                    // CanaryMod hook onPistonExtend
                    boolean allowExtension = !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PISTON_EXTEND, new Block(oworld.world, (this.a) ? Block.Type.StickyPiston.getType() : Block.Type.Piston.getType(), i, j, k, l));

                    if (allowExtension) {
                        oworld.d(i, j, k, this.cz, 0, i1);
                    }
                }
            } else if (!flag && e(l)) {
                // CanaryMod hook onPistonRetract
                // hook result is saved in attemptRetractBlock because later in the code the block is actually moved, and only there we should deny retraction.
                this.attemptRetractBlock = !(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PISTON_RETRACT, new Block(oworld.world, (this.a) ? Block.Type.StickyPiston.getType() : Block.Type.Piston.getType(), i, j, k, l));

                oworld.b(i, j, k, i1, 2);
                oworld.d(i, j, k, this.cz, 1, i1);
            }
        }
    }

    private boolean d(OWorld oworld, int i, int j, int k, int l) {
        return l != 0 && oworld.k(i, j - 1, k, 0) ? true : (l != 1 && oworld.k(i, j + 1, k, 1) ? true : (l != 2 && oworld.k(i, j, k - 1, 2) ? true : (l != 3 && oworld.k(i, j, k + 1, 3) ? true : (l != 5 && oworld.k(i + 1, j, k, 5) ? true : (l != 4 && oworld.k(i - 1, j, k, 4) ? true : (oworld.k(i, j, k, 0) ? true : (oworld.k(i, j + 2, k, 1) ? true : (oworld.k(i, j + 1, k - 1, 2) ? true : (oworld.k(i, j + 1, k + 1, 3) ? true : (oworld.k(i - 1, j + 1, k, 4) ? true : oworld.k(i + 1, j + 1, k, 5)))))))))));
    }

    public boolean b(OWorld oworld, int i, int j, int k, int l, int i1) {
        if (!oworld.I) {
            boolean flag = this.d(oworld, i, j, k, i1);

            if (flag && l == 1) {
                oworld.b(i, j, k, i1 | 8, 2);
                return false;
            }

            if (!flag && l == 0) {
                return false;
            }
        }

        if (l == 0) {
            if (!this.f(oworld, i, j, k, i1)) {
                return false;
            }

            oworld.b(i, j, k, i1 | 8, 2);
            oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "tile.piston.out", 0.5F, oworld.s.nextFloat() * 0.25F + 0.6F);
        } else if (l == 1) {
            OTileEntity otileentity = oworld.r(i + OFacing.b[i1], j + OFacing.c[i1], k + OFacing.d[i1]);

            if (otileentity instanceof OTileEntityPiston) {
                ((OTileEntityPiston) otileentity).f();
            }

            oworld.f(i, j, k, OBlock.ag.cz, i1, 3);
            oworld.a(i, j, k, OBlockPistonMoving.a(this.cz, i1, i1, false, true));
            if (this.a) {
                int j1 = i + OFacing.b[i1] * 2;
                int k1 = j + OFacing.c[i1] * 2;
                int l1 = k + OFacing.d[i1] * 2;
                int i2 = oworld.a(j1, k1, l1);
                int j2 = oworld.h(j1, k1, l1);
                boolean flag1 = false;

                if (i2 == OBlock.ag.cz) {
                    OTileEntity otileentity1 = oworld.r(j1, k1, l1);

                    if (otileentity1 instanceof OTileEntityPiston) {
                        OTileEntityPiston otileentitypiston = (OTileEntityPiston) otileentity1;

                        if (otileentitypiston.c() == i1 && otileentitypiston.b()) {
                            otileentitypiston.f();
                            i2 = otileentitypiston.a();
                            j2 = otileentitypiston.p();
                            flag1 = true;
                        }
                    }
                }

                if (this.attemptRetractBlock && !flag1 && i2 > 0 && a(i2, oworld, j1, k1, l1, false) && (OBlock.r[i2].h() == 0 || i2 == OBlock.ad.cz || i2 == OBlock.Z.cz)) {
                    i += OFacing.b[i1];
                    j += OFacing.c[i1];
                    k += OFacing.d[i1];
                    oworld.f(i, j, k, OBlock.ag.cz, j2, 3);
                    oworld.a(i, j, k, OBlockPistonMoving.a(i2, j2, i1, false, false));
                    oworld.i(j1, k1, l1);
                } else if (!flag1 || !this.attemptRetractBlock) { // if retraction fails normally (!flag) OR the onPistonRetract returned false earlier.
                    oworld.i(i + OFacing.b[i1], j + OFacing.c[i1], k + OFacing.d[i1]);
                }
            } else {
                oworld.i(i + OFacing.b[i1], j + OFacing.c[i1], k + OFacing.d[i1]);
            }

            oworld.a((double) i + 0.5D, (double) j + 0.5D, (double) k + 0.5D, "tile.piston.in", 0.5F, oworld.s.nextFloat() * 0.15F + 0.6F);
        }

        return true;
    }

    public void a(OIBlockAccess oiblockaccess, int i, int j, int k) {
        int l = oiblockaccess.h(i, j, k);

        if (e(l)) {
            switch (d(l)) {
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

    public void g() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void a(OWorld oworld, int i, int j, int k, OAxisAlignedBB oaxisalignedbb, List list, OEntity oentity) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.a(oworld, i, j, k, oaxisalignedbb, list, oentity);
    }

    public OAxisAlignedBB b(OWorld oworld, int i, int j, int k) {
        this.a((OIBlockAccess) oworld, i, j, k);
        return super.b(oworld, i, j, k);
    }

    public boolean b() {
        return false;
    }

    public static int d(int i) {
        return i & 7;
    }

    public static boolean e(int i) {
        return (i & 8) != 0;
    }

    public static int a(OWorld oworld, int i, int j, int k, OEntityLiving oentityliving) {
        if (OMathHelper.e((float) oentityliving.u - (float) i) < 2.0F && OMathHelper.e((float) oentityliving.w - (float) k) < 2.0F) {
            double d0 = oentityliving.v + 1.82D - (double) oentityliving.N;

            if (d0 - (double) j > 2.0D) {
                return 1;
            }

            if ((double) j - d0 > 0.0D) {
                return 0;
            }
        }

        int l = OMathHelper.c((double) (oentityliving.A * 4.0F / 360.0F) + 0.5D) & 3;

        return l == 0 ? 2 : (l == 1 ? 5 : (l == 2 ? 3 : (l == 3 ? 4 : 0)));
    }

    private static boolean a(int i, OWorld oworld, int j, int k, int l, boolean flag) {
        if (i == OBlock.at.cz) {
            return false;
        } else {
            if (i != OBlock.ad.cz && i != OBlock.Z.cz) {
                if (OBlock.r[i].l(oworld, j, k, l) == -1.0F) {
                    return false;
                }

                if (OBlock.r[i].h() == 2) {
                    return false;
                }

                if (OBlock.r[i].h() == 1) {
                    if (!flag) {
                        return false;
                    }

                    return true;
                }
            } else if (e(oworld.h(j, k, l))) {
                return false;
            }

            return !(OBlock.r[i] instanceof OITileEntityProvider);
        }
    }

    private static boolean e(OWorld oworld, int i, int j, int k, int l) {
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

                    if (OBlock.r[i2].h() != 1) {
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

    private boolean f(OWorld oworld, int i, int j, int k, int l) {
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

                    if (OBlock.r[i2].h() != 1) {
                        if (l1 == 12) {
                            return false;
                        }

                        i1 += OFacing.b[l];
                        j1 += OFacing.c[l];
                        k1 += OFacing.d[l];
                        ++l1;
                        continue;
                    }

                    OBlock.r[i2].c(oworld, i1, j1, k1, oworld.h(i1, j1, k1), 0);
                    oworld.i(i1, j1, k1);
                }
            }

            l1 = i1;
            i2 = j1;
            int j2 = k1;
            int k2 = 0;

            int[] aint;
            int l2;
            int i3;
            int j3;

            for (aint = new int[13]; i1 != i || j1 != j || k1 != k; k1 = j3) {
                l2 = i1 - OFacing.b[l];
                i3 = j1 - OFacing.c[l];
                j3 = k1 - OFacing.d[l];
                int k3 = oworld.a(l2, i3, j3);
                int l3 = oworld.h(l2, i3, j3);

                if (k3 == this.cz && l2 == i && i3 == j && j3 == k) {
                    oworld.f(i1, j1, k1, OBlock.ag.cz, l | (this.a ? 8 : 0), 4);
                    oworld.a(i1, j1, k1, OBlockPistonMoving.a(OBlock.ae.cz, l | (this.a ? 8 : 0), l, true, false));
                } else {
                    oworld.f(i1, j1, k1, OBlock.ag.cz, l3, 4);
                    oworld.a(i1, j1, k1, OBlockPistonMoving.a(k3, l3, l, true, false));
                }

                aint[k2++] = k3;
                i1 = l2;
                j1 = i3;
            }

            i1 = l1;
            j1 = i2;
            k1 = j2;

            for (k2 = 0; i1 != i || j1 != j || k1 != k; k1 = j3) {
                l2 = i1 - OFacing.b[l];
                i3 = j1 - OFacing.c[l];
                j3 = k1 - OFacing.d[l];
                oworld.f(l2, i3, j3, aint[k2++]);
                i1 = l2;
                j1 = i3;
            }

            return true;
        }
    }
}
