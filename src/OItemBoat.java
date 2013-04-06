import java.util.List;

public class OItemBoat extends OItem {

    public OItemBoat(int i) {
        super(i);
        this.cq = 1;
        this.a(OCreativeTabs.e);
    }

    public OItemStack a(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {
        float f = 1.0F;
        float f1 = oentityplayer.D + (oentityplayer.B - oentityplayer.D) * f;
        float f2 = oentityplayer.C + (oentityplayer.A - oentityplayer.C) * f;
        double d0 = oentityplayer.r + (oentityplayer.u - oentityplayer.r) * (double) f;
        double d1 = oentityplayer.s + (oentityplayer.v - oentityplayer.s) * (double) f + 1.62D - (double) oentityplayer.N;
        double d2 = oentityplayer.t + (oentityplayer.w - oentityplayer.t) * (double) f;
        OVec3 ovec3 = oworld.T().a(d0, d1, d2);
        float f3 = OMathHelper.b(-f2 * 0.017453292F - 3.1415927F);
        float f4 = OMathHelper.a(-f2 * 0.017453292F - 3.1415927F);
        float f5 = -OMathHelper.b(-f1 * 0.017453292F);
        float f6 = OMathHelper.a(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        OVec3 ovec31 = ovec3.c((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
        OMovingObjectPosition omovingobjectposition = oworld.a(ovec3, ovec31, true);

        if (omovingobjectposition == null) {
            return oitemstack;
        } else {
            OVec3 ovec32 = oentityplayer.i(f);
            boolean flag = false;
            float f9 = 1.0F;
            List list = oworld.b((OEntity) oentityplayer, oentityplayer.E.a(ovec32.c * d3, ovec32.d * d3, ovec32.e * d3).b((double) f9, (double) f9, (double) f9));

            int i;

            for (i = 0; i < list.size(); ++i) {
                OEntity oentity = (OEntity) list.get(i);

                if (oentity.K()) {
                    float f10 = oentity.X();
                    OAxisAlignedBB oaxisalignedbb = oentity.E.b((double) f10, (double) f10, (double) f10);

                    if (oaxisalignedbb.a(ovec3)) {
                        flag = true;
                    }
                }
            }

            if (flag) {
                return oitemstack;
            } else {
                if (omovingobjectposition.a == OEnumMovingObjectType.a) {
                    i = omovingobjectposition.b;
                    int j = omovingobjectposition.c;
                    int k = omovingobjectposition.d;

                    if (oworld.a(i, j, k) == OBlock.aW.cz) {
                        --j;
                    }

                    // CanaryMod: placing of a boat
                    Block blockClicked = this.getBlockInfo(oworld, i, j, k, omovingobjectposition.e);
                    Block blockPlaced = new Block(oworld.world, 0, i, j, k);

                    // CanaryMod: Call hook
                    if (oentityplayer instanceof OEntityPlayerMP && (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), blockPlaced, blockClicked, ((OEntityPlayerMP) oentityplayer).getPlayer().getItemStackInHand())) {
                        return oitemstack;
                    }
                    OEntityBoat oentityboat = new OEntityBoat(oworld, (double) ((float) i + 0.5F), (double) ((float) j + 1.0F), (double) ((float) k + 0.5F));

                    oentityboat.A = (float) (((OMathHelper.c((double) (oentityplayer.A * 4.0F / 360.0F) + 0.5D) & 3) - 1) * 90);
                    if (!oworld.a((OEntity) oentityboat, oentityboat.E.b(-0.1D, -0.1D, -0.1D)).isEmpty()) {
                        return oitemstack;
                    }

                    if (!oworld.I) {
                        oworld.d((OEntity) oentityboat);
                    }

                    if (!oentityplayer.ce.d) {
                        --oitemstack.a;
                    }
                }

                return oitemstack;
            }
        }
    }
}
