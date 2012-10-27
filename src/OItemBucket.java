public class OItemBucket extends OItem {

    private int a;

    public OItemBucket(int i, int j) {
        super(i);
        this.cg = 1;
        this.a = j;
        this.a(OCreativeTabs.f);
    }

    public OItemStack a(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {
        float f = 1.0F;
        double d0 = oentityplayer.q + (oentityplayer.t - oentityplayer.q) * (double) f;
        double d1 = oentityplayer.r + (oentityplayer.u - oentityplayer.r) * (double) f + 1.62D - (double) oentityplayer.M;
        double d2 = oentityplayer.s + (oentityplayer.v - oentityplayer.s) * (double) f;
        boolean flag = this.a == 0;
        OMovingObjectPosition omovingobjectposition = this.a(oworld, oentityplayer, flag);

        if (omovingobjectposition == null) {
            return oitemstack;
        } else {
            if (omovingobjectposition.a == OEnumMovingObjectType.a) {
                int i = omovingobjectposition.b;
                int j = omovingobjectposition.c;
                int k = omovingobjectposition.d;

                if (!oworld.a(oentityplayer, i, j, k)) {
                    return oitemstack;
                }

                // CanaryMod: Click == placed when handling an empty bucket!
                Block blockClicked = this.getBlockInfo(oworld, i, j, k, omovingobjectposition.e);
                Block blockPlaced = new Block(oworld.world, 0, i, j, k);

                if (this.a == 0) {
                    if (!oentityplayer.a(i, j, k, omovingobjectposition.e, oitemstack)) {
                        return oitemstack;
                    }

                    if (oworld.f(i, j, k) == OMaterial.h && oworld.g(i, j, k) == 0) {
                        // CanaryMod: Filling a bucket with water!
                        if (oentityplayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, new Item(oitemstack))) {
                            return oitemstack;
                        }

                        oworld.e(i, j, k, 0);
                        if (oentityplayer.cf.d) {
                            return oitemstack;
                        }

                        if (--oitemstack.a <= 0) {
                            return new OItemStack(OItem.ax);
                        }

                        if (!oentityplayer.bK.a(new OItemStack(OItem.ax))) {
                            oentityplayer.c(new OItemStack(OItem.ax.cf, 1, 0));
                        }

                        return oitemstack;
                    }

                    if (oworld.f(i, j, k) == OMaterial.i && oworld.g(i, j, k) == 0) {
                        // CanaryMod: Filling a bucket with lava!
                        if (oentityplayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, new Item(oitemstack))) {
                            return oitemstack;
                        }

                        oworld.e(i, j, k, 0);
                        if (oentityplayer.cf.d) {
                            return oitemstack;
                        }

                        if (--oitemstack.a <= 0) {
                            return new OItemStack(OItem.ay);
                        }

                        if (!oentityplayer.bK.a(new OItemStack(OItem.ay))) {
                            oentityplayer.c(new OItemStack(OItem.ay.cf, 1, 0));
                        }

                        return oitemstack;
                    }
                } else {
                    if (this.a < 0) {
                        return new OItemStack(OItem.aw);
                    }

                    if (omovingobjectposition.e == 0) {
                        --j;
                    }

                    if (omovingobjectposition.e == 1) {
                        ++j;
                    }

                    if (omovingobjectposition.e == 2) {
                        --k;
                    }

                    if (omovingobjectposition.e == 3) {
                        ++k;
                    }

                    if (omovingobjectposition.e == 4) {
                        --i;
                    }

                    if (omovingobjectposition.e == 5) {
                        ++i;
                    }

                    if (!oentityplayer.a(i, j, k, omovingobjectposition.e, oitemstack)) {
                        return oitemstack;
                    }

                    if (this.a(oworld, d0, d1, d2, i, j, k, oentityplayer) && !oentityplayer.cf.d) {
                        return new OItemStack(OItem.aw);
                    }
                }
            } else if (this.a == 0 && omovingobjectposition.g instanceof OEntityCow) {
                // CanaryMod hook: onCowMilk
                if (oentityplayer instanceof OEntityPlayerMP &&!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COW_MILK, ((OEntityPlayerMP) oentityplayer).getPlayer(), new Mob((OEntityCow) omovingobjectposition.g))) {
                    return new OItemStack(OItem.aG);
                }
            }

            return oitemstack;
        }
    }

    public boolean a(OWorld oworld, double d0, double d1, double d2, int i, int j, int k) {
        return this.a(oworld, d0, d1, d2, i, j, k, null);
    }

    public boolean a(OWorld oworld, double d0, double d1, double d2, int i, int j, int k, OEntityPlayer oentityplayer) { // CanaryMod: pass oentityplayer
        if (this.a <= 0) {
            return false;
        } else if (!oworld.c(i, j, k) && oworld.f(i, j, k).a()) {
            return false;
        } else {
            if (oworld.v.e && this.a == OBlock.D.cm) {
                oworld.a(d0 + 0.5D, d1 + 0.5D, d2 + 0.5D, "random.fizz", 0.5F, 2.6F + (oworld.u.nextFloat() - oworld.u.nextFloat()) * 0.8F);

                for (int l = 0; l < 8; ++l) {
                    oworld.a("largesmoke", (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
                }
            } else {
                // CanaryMod: bucket empty
                if (oentityplayer != null) {
                    OMovingObjectPosition omovingobjectposition = this.a(oworld, oentityplayer, false);
                    Block blockClicked = this.getBlockInfo(oworld, i, j, k, omovingobjectposition.e);
                    Block blockPlaced = new Block(oworld.world, this.a, i, j, k);
                    if (oentityplayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, new Item(this.a == Block.Type.Water.getType() ? Item.Type.WaterBucket : Item.Type.LavaBucket))) {
                        return false;
                    }
                }
                oworld.d(i, j, k, this.a, 0);
            }

            return true;
        }
    }
}
