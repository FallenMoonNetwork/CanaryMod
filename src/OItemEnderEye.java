
public class OItemEnderEye extends OItem {

    public OItemEnderEye(int i) {
        super(i);
        this.a(OCreativeTabs.f);
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        int i1 = oworld.a(i, j, k);
        int j1 = oworld.g(i, j, k);
        
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), this.getBlockInfo(oworld, i, j, k, l), null, new Item(oitemstack))) {
            return true;
        }

        if (oentityplayer.a(i, j, k, l, oitemstack) && i1 == OBlock.bL.cm && !OBlockEndPortalFrame.e(j1)) {
            if (oworld.J) {
                return true;
            } else {
                oworld.c(i, j, k, j1 + 4);
                --oitemstack.a;

                int k1;

                for (k1 = 0; k1 < 16; ++k1) {
                    double d0 = (double) ((float) i + (5.0F + d.nextFloat() * 6.0F) / 16.0F);
                    double d1 = (double) ((float) j + 0.8125F);
                    double d2 = (double) ((float) k + (5.0F + d.nextFloat() * 6.0F) / 16.0F);
                    double d3 = 0.0D;
                    double d4 = 0.0D;
                    double d5 = 0.0D;

                    oworld.a("smoke", d0, d1, d2, d3, d4, d5);
                }

                k1 = j1 & 3;
                int l1 = 0;
                int i2 = 0;
                boolean flag = false;
                boolean flag1 = true;
                int j2 = ODirection.g[k1];

                int k2;
                int l2;
                int i3;
                int j3;
                int k3;

                for (k2 = -2; k2 <= 2; ++k2) {
                    j3 = i + ODirection.a[j2] * k2;
                    k3 = k + ODirection.b[j2] * k2;
                    l2 = oworld.a(j3, j, k3);
                    if (l2 == OBlock.bL.cm) {
                        i3 = oworld.g(j3, j, k3);
                        if (!OBlockEndPortalFrame.e(i3)) {
                            flag1 = false;
                            break;
                        }

                        i2 = k2;
                        if (!flag) {
                            l1 = k2;
                            flag = true;
                        }
                    }
                }

                if (flag1 && i2 == l1 + 2) {
                    for (k2 = l1; k2 <= i2; ++k2) {
                        j3 = i + ODirection.a[j2] * k2;
                        k3 = k + ODirection.b[j2] * k2;
                        j3 += ODirection.a[k1] * 4;
                        k3 += ODirection.b[k1] * 4;
                        l2 = oworld.a(j3, j, k3);
                        i3 = oworld.g(j3, j, k3);
                        if (l2 != OBlock.bL.cm || !OBlockEndPortalFrame.e(i3)) {
                            flag1 = false;
                            break;
                        }
                    }

                    for (k2 = l1 - 1; k2 <= i2 + 1; k2 += 4) {
                        for (j3 = 1; j3 <= 3; ++j3) {
                            k3 = i + ODirection.a[j2] * k2;
                            l2 = k + ODirection.b[j2] * k2;
                            k3 += ODirection.a[k1] * j3;
                            l2 += ODirection.b[k1] * j3;
                            i3 = oworld.a(k3, j, l2);
                            int l3 = oworld.g(k3, j, l2);

                            if (i3 != OBlock.bL.cm || !OBlockEndPortalFrame.e(l3)) {
                                flag1 = false;
                                break;
                            }
                        }
                    }

                    if (flag1) {
                        for (k2 = l1; k2 <= i2; ++k2) {
                            for (j3 = 1; j3 <= 3; ++j3) {
                                k3 = i + ODirection.a[j2] * k2;
                                l2 = k + ODirection.b[j2] * k2;
                                k3 += ODirection.a[k1] * j3;
                                l2 += ODirection.b[k1] * j3;
                                oworld.e(k3, j, l2, OBlock.bK.cm);
                            }
                        }
                    }
                }

                return true;
            }
        } else {
            return false;
        }
    }

    public OItemStack a(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {
        OMovingObjectPosition omovingobjectposition = this.a(oworld, oentityplayer, false);

        if (omovingobjectposition != null && omovingobjectposition.a == OEnumMovingObjectType.a) {
            int i = oworld.a(omovingobjectposition.b, omovingobjectposition.c, omovingobjectposition.d);

            if (i == OBlock.bL.cm) {
                return oitemstack;
            }
        }

        if (!oworld.J) {
            OChunkPosition ochunkposition = oworld.b("Stronghold", (int) oentityplayer.t, (int) oentityplayer.u, (int) oentityplayer.v);

            if (ochunkposition != null) {
                OEntityEnderEye oentityendereye = new OEntityEnderEye(oworld, oentityplayer.t, oentityplayer.u + 1.62D - (double) oentityplayer.M, oentityplayer.v);

                oentityendereye.a((double) ochunkposition.a, ochunkposition.b, (double) ochunkposition.c);
                oworld.d((OEntity) oentityendereye);
                oworld.a(oentityplayer, "random.bow", 0.5F, 0.4F / (d.nextFloat() * 0.4F + 0.8F));
                oworld.a((OEntityPlayer) null, 1002, (int) oentityplayer.t, (int) oentityplayer.u, (int) oentityplayer.v, 0);
                if (!oentityplayer.cf.d) {
                    --oitemstack.a;
                }
            }
        }

        return oitemstack;
    }
}
