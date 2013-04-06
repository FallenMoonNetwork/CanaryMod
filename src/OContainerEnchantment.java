import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OContainerEnchantment extends OContainer {

    public OIInventory a = new OSlotEnchantmentTable(this, "Enchant", true, 1);
    private OWorld h;
    private int i;
    private int j;
    private int k;
    private Random l = new Random();
    public long f;
    public int[] g = new int[3];

    public OContainerEnchantment(OInventoryPlayer oinventoryplayer, OWorld oworld, int i, int j, int k) {
        this.h = oworld;
        this.i = i;
        this.j = j;
        this.k = k;
        this.a((OSlot) (new OSlotEnchantment(this, this.a, 0, 25, 47)));

        int l;

        for (l = 0; l < 3; ++l) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.a(new OSlot(oinventoryplayer, i1 + l * 9 + 9, 8 + i1 * 18, 84 + l * 18));
            }
        }

        for (l = 0; l < 9; ++l) {
            this.a(new OSlot(oinventoryplayer, l, 8 + l * 18, 142));
        }
    }

    @Override
    public void a(OICrafting oicrafting) {
        super.a(oicrafting);
        oicrafting.a(this, 0, this.g[0]);
        oicrafting.a(this, 1, this.g[1]);
        oicrafting.a(this, 2, this.g[2]);
    }

    @Override
    public void b() {
        super.b();

        for (int i = 0; i < this.e.size(); ++i) {
            OICrafting oicrafting = (OICrafting) this.e.get(i);

            oicrafting.a(this, 0, this.g[0]);
            oicrafting.a(this, 1, this.g[1]);
            oicrafting.a(this, 2, this.g[2]);
        }
    }

    @Override
    public void a(OIInventory oiinventory) {
        if (oiinventory == this.a) {
            OItemStack oitemstack = oiinventory.a(0);
            int i;

            if (oitemstack != null && oitemstack.w()) {
                this.f = this.l.nextLong();
                if (!this.h.I) {
                    i = 0;

                    int j;

                    for (j = -1; j <= 1; ++j) {
                        for (int k = -1; k <= 1; ++k) {
                            if ((j != 0 || k != 0) && this.h.c(this.i + k, this.j, this.k + j) && this.h.c(this.i + k, this.j + 1, this.k + j)) {
                                if (this.h.a(this.i + k * 2, this.j, this.k + j * 2) == OBlock.ar.cz) {
                                    ++i;
                                }

                                if (this.h.a(this.i + k * 2, this.j + 1, this.k + j * 2) == OBlock.ar.cz) {
                                    ++i;
                                }

                                if (k != 0 && j != 0) {
                                    if (this.h.a(this.i + k * 2, this.j, this.k + j) == OBlock.ar.cz) {
                                        ++i;
                                    }

                                    if (this.h.a(this.i + k * 2, this.j + 1, this.k + j) == OBlock.ar.cz) {
                                        ++i;
                                    }

                                    if (this.h.a(this.i + k, this.j, this.k + j * 2) == OBlock.ar.cz) {
                                        ++i;
                                    }

                                    if (this.h.a(this.i + k, this.j + 1, this.k + j * 2) == OBlock.ar.cz) {
                                        ++i;
                                    }
                                }
                            }
                        }
                    }

                    for (j = 0; j < 3; ++j) {
                        this.g[j] = OEnchantmentHelper.a(this.l, j, i, oitemstack);
                    }

                    this.b();
                }
            } else {
                for (i = 0; i < 3; ++i) {
                    this.g[i] = 0;
                }
            }
        }
    }

    @Override
    public boolean a(OEntityPlayer oentityplayer, int i) {
        OItemStack oitemstack = this.a.a(0);

        if (this.g[i] > 0 && oitemstack != null && (oentityplayer.cf >= this.g[i] || oentityplayer.ce.d)) {
            if (!this.h.I) {
                List list = OEnchantmentHelper.b(this.l, oitemstack, this.g[i]);
                boolean flag = oitemstack.c == OItem.aM.cp;

                if (list != null) {
                    // CanaryMod hook: onEnchant
                    HookParametersEnchant enchantParameters = (HookParametersEnchant) etc.getLoader().callHook(PluginLoader.Hook.ENCHANT, new HookParametersEnchant(((OEntityPlayerMP) oentityplayer).getPlayer(), oitemstack.c, list));

                    if (!enchantParameters.isCanceled() && enchantParameters.isValid(false)) {
                        List<Enchantment> enchantments = enchantParameters.getEnchantments();

                        list = new ArrayList();
                        for (Enchantment enchantment : enchantments) {
                            list.add(new OEnchantmentData(enchantment.getEnchantment(), enchantment.getLevel()));
                        }

                        oentityplayer.a(-this.g[i]);
                        if (flag) {
                            oitemstack.c = OItem.bX.cp;
                        }

                        int j = flag ? this.l.nextInt(list.size()) : -1;

                        for (int k = 0; k < list.size(); ++k) {
                            OEnchantmentData oenchantmentdata = (OEnchantmentData) list.get(k);

                            if (!flag || k == j) {
                                if (flag) {
                                OItem.bX.a(oitemstack, oenchantmentdata);
                                } else {
                                    oitemstack.a(oenchantmentdata.b, oenchantmentdata.c);
                                }
                            }
                        }

                        this.a(this.a);
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void b(OEntityPlayer oentityplayer) {
        super.b(oentityplayer);
        if (!this.h.I) {
            OItemStack oitemstack = this.a.b(0);

            if (oitemstack != null) {
                oentityplayer.c(oitemstack);
            }
        }
    }

    @Override
    public boolean a(OEntityPlayer oentityplayer) {
        return this.h.a(this.i, this.j, this.k) != OBlock.bI.cz ? false : oentityplayer.e((double) this.i + 0.5D, (double) this.j + 0.5D, (double) this.k + 0.5D) <= 64.0D;
    }

    @Override
    public OItemStack b(OEntityPlayer oentityplayer, int i) {
        OItemStack oitemstack = null;
        OSlot oslot = (OSlot) this.c.get(i);

        if (oslot != null && oslot.d()) {
            OItemStack oitemstack1 = oslot.c();

            oitemstack = oitemstack1.m();
            if (i == 0) {
                if (!this.a(oitemstack1, 1, 37, true)) {
                    return null;
                }
            } else {
                if (((OSlot) this.c.get(0)).d() || !((OSlot) this.c.get(0)).a(oitemstack1)) {
                    return null;
                }

                if (oitemstack1.p() && oitemstack1.a == 1) {
                    ((OSlot) this.c.get(0)).c(oitemstack1.m());
                    oitemstack1.a = 0;
                } else if (oitemstack1.a >= 1) {
                    ((OSlot) this.c.get(0)).c(new OItemStack(oitemstack1.c, 1, oitemstack1.k()));
                    --oitemstack1.a;
                }
            }

            if (oitemstack1.a == 0) {
                oslot.c((OItemStack) null);
            } else {
                oslot.e();
            }

            if (oitemstack1.a == oitemstack.a) {
                return null;
            }

            oslot.a(oentityplayer, oitemstack1);
        }

        return oitemstack;
    }

    // CanaryMod
    @Override
    public EnchantmentTable getInventory() {
        if(super.getInventory() instanceof EnchantmentTable)
            return (EnchantmentTable)super.getInventory();

        EnchantmentTable inv = new EnchantmentTable(this);
        setInventory(inv);
        return inv;
    }
}
