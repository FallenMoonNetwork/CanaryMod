public class OItemBucket extends OItem {

    private int a;

    public OItemBucket(int i, int j) {
        super(i);
        this.cw = 1;
        this.a = j;
        this.a(OCreativeTabs.f);
    }

    public OItemStack a(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {
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

                    if (oworld.g(i, j, k) == OMaterial.h && oworld.h(i, j, k) == 0) {
                        // CanaryMod: Filling a bucket with water!
                        if (oentityplayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, ((OEntityPlayerMP) oentityplayer).getPlayer().getItemStackInHand())) {
                            return oitemstack;
                        }

                        oworld.i(i, j, k);
                        if (oentityplayer.bG.d) {
                            return oitemstack;
                        }

                        if (--oitemstack.b <= 0) {
                            return new OItemStack(OItem.az);
                        }

                        if (!oentityplayer.bn.a(new OItemStack(OItem.az))) {
                            oentityplayer.b(new OItemStack(OItem.az.cv, 1, 0));
                        }

                        return oitemstack;
                    }

                    if (oworld.g(i, j, k) == OMaterial.i && oworld.h(i, j, k) == 0) {
                        // CanaryMod: Filling a bucket with lava!
                        if (oentityplayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, ((OEntityPlayerMP) oentityplayer).getPlayer().getItemStackInHand())) {
                            return oitemstack;
                        }

                        oworld.i(i, j, k);
                        if (oentityplayer.bG.d) {
                            return oitemstack;
                        }

                        if (--oitemstack.b <= 0) {
                            return new OItemStack(OItem.aA);
                        }

                        if (!oentityplayer.bn.a(new OItemStack(OItem.aA))) {
                            oentityplayer.b(new OItemStack(OItem.aA.cv, 1, 0));
                        }

                        return oitemstack;
                    }
                } else {
                    if (this.a < 0) {
                        return new OItemStack(OItem.ay);
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

                    if (this.a(oworld, i, j, k, oentityplayer) && !oentityplayer.bG.d) { // CanaryMod: pass oentityplayer
                        return new OItemStack(OItem.ay);
                    }
                }
            }

            return oitemstack;
        }
    }

    public boolean a(OWorld oworld, int i, int j, int k) {
        return this.a(oworld, i, j, k, null);
    }

    public boolean a(OWorld oworld, int i, int j, int k, OEntityPlayer oentityplayer) { // CanaryMod: pass oentityplayer
        if (this.a <= 0) {
            return false;
        } else {
            OMaterial omaterial = oworld.g(i, j, k);
            boolean flag = !omaterial.a();

            if (!oworld.c(i, j, k) && !flag) {
                return false;
            } else {
                // CanaryMod: bucket empty
                if (oentityplayer != null) {
                    OMovingObjectPosition omovingobjectposition = this.a(oworld, oentityplayer, false);
                    Block blockClicked = this.getBlockInfo(oworld, i, j, k, omovingobjectposition.e);
                    Block blockPlaced = new Block(oworld.world, this.a, i, j, k);
                    if (oentityplayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, ((OEntityPlayerMP) oentityplayer).getPlayer().getItemStackInHand())) {
                        return false;
                    }
                }

                if (oworld.t.f && this.a == OBlock.F.cF) {
                    oworld.a((double) ((float) i + 0.5F), (double) ((float) j + 0.5F), (double) ((float) k + 0.5F), "random.fizz", 0.5F, 2.6F + (oworld.s.nextFloat() - oworld.s.nextFloat()) * 0.8F);

                    for (int l = 0; l < 8; ++l) {
                        oworld.a("largesmoke", (double) i + Math.random(), (double) j + Math.random(), (double) k + Math.random(), 0.0D, 0.0D, 0.0D);
                    }
                } else {
                    if (!oworld.I && flag && !omaterial.d()) {
                        oworld.a(i, j, k, true);
                    }

                    oworld.f(i, j, k, this.a, 0, 3);
                }

                return true;
            }
        }
    }
}
