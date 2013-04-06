import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class OContainer {

    public List b = new ArrayList();
    public List c = new ArrayList();
    public int d = 0;
    private short a = 0;
    private int f = -1;
    private int g = 0;
    private final Set h = new HashSet();
    protected List e = new ArrayList();
    private Set i = new HashSet();

    private Inventory inventory; // CanaryMod: Used to know which inventory was passed to this container GUI.

    public OContainer() {}

    protected OSlot a(OSlot oslot) {
        oslot.g = this.c.size();
        this.c.add(oslot);
        this.b.add(null);
        return oslot;
    }

    public void a(OICrafting oicrafting) {
        if (this.e.contains(oicrafting)) {
            throw new IllegalArgumentException("Listener already listening");
        } else {
            this.e.add(oicrafting);
            oicrafting.a(this, this.a());
            this.b();
        }
    }

    public List a() {
        ArrayList arraylist = new ArrayList();

        for (int i = 0; i < this.c.size(); ++i) {
            arraylist.add(((OSlot) this.c.get(i)).c());
        }

        return arraylist;
    }

    public void b() {
        for (int i = 0; i < this.c.size(); ++i) {
            OItemStack oitemstack = ((OSlot) this.c.get(i)).c();
            OItemStack oitemstack1 = (OItemStack) this.b.get(i);

            if (!OItemStack.b(oitemstack1, oitemstack)) {
                oitemstack1 = oitemstack == null ? null : oitemstack.m();
                this.b.set(i, oitemstack1);

                for (int j = 0; j < this.e.size(); ++j) {
                    ((OICrafting) this.e.get(j)).a(this, i, oitemstack1);
                }
            }
        }
    }

    public boolean a(OEntityPlayer oentityplayer, int i) {
        return false;
    }

    public OSlot a(OIInventory oiinventory, int i) {
        for (int j = 0; j < this.c.size(); ++j) {
            OSlot oslot = (OSlot) this.c.get(j);

            if (oslot.a(oiinventory, i)) {
                return oslot;
            }
        }

        return null;
    }

    public OSlot a(int i) {
        return (OSlot) this.c.get(i);
    }

    public OItemStack b(OEntityPlayer oentityplayer, int i) {
        OSlot oslot = (OSlot) this.c.get(i);

        return oslot != null ? oslot.c() : null;
    }

    public OItemStack a(int i, int j, int k, OEntityPlayer oentityplayer) {
        OItemStack oitemstack = null;
        OInventoryPlayer oinventoryplayer = oentityplayer.bK;
        int l;
        OItemStack oitemstack1;

        if (k == 5) {
            int i1 = this.g;

            this.g = c(j);
            if ((i1 != 1 || this.g != 2) && i1 != this.g) {
                this.d();
            } else if (oinventoryplayer.o() == null) {
                this.d();
            } else if (this.g == 0) {
                this.f = b(j);
                if (d(this.f)) {
                    this.g = 1;
                    this.h.clear();
                } else {
                    this.d();
                }
            } else if (this.g == 1) {
                OSlot oslot = (OSlot) this.c.get(i);

                if (oslot != null && a(oslot, oinventoryplayer.o(), true) && oslot.a(oinventoryplayer.o()) && oinventoryplayer.o().a > this.h.size() && this.b(oslot)) {
                    this.h.add(oslot);
                }
            } else if (this.g == 2) {
                if (!this.h.isEmpty()) {
                    oitemstack1 = oinventoryplayer.o().m();
                    l = oinventoryplayer.o().a;
                    Iterator iterator = this.h.iterator();

                    while (iterator.hasNext()) {
                        OSlot oslot1 = (OSlot) iterator.next();

                        if (oslot1 != null && a(oslot1, oinventoryplayer.o(), true) && oslot1.a(oinventoryplayer.o()) && oinventoryplayer.o().a >= this.h.size() && this.b(oslot1)) {
                            OItemStack oitemstack2 = oitemstack1.m();
                            int j1 = oslot1.d() ? oslot1.c().a : 0;

                            a(this.h, this.f, oitemstack2, j1);
                            if (oitemstack2.a > oitemstack2.e()) {
                                oitemstack2.a = oitemstack2.e();
                            }

                            if (oitemstack2.a > oslot1.a()) {
                                oitemstack2.a = oslot1.a();
                            }

                            l -= oitemstack2.a - j1;
                            oslot1.c(oitemstack2);
                        }
                    }

                    oitemstack1.a = l;
                    if (oitemstack1.a <= 0) {
                        oitemstack1 = null;
                    }

                    oinventoryplayer.b(oitemstack1);
                }

                this.d();
            } else {
                this.d();
            }
        } else if (this.g != 0) {
            this.d();
        } else {
            OSlot oslot2;
            int k1;
            OItemStack oitemstack3;

            if ((k == 0 || k == 1) && (j == 0 || j == 1)) {
                if (i == -999) {
                    if (oinventoryplayer.o() != null && i == -999) {
                        if (j == 0) {
                            oentityplayer.c(oinventoryplayer.o());
                            oinventoryplayer.b((OItemStack) null);
                        }

                        if (j == 1) {
                            oentityplayer.c(oinventoryplayer.o().a(1));
                            if (oinventoryplayer.o().a == 0) {
                                oinventoryplayer.b((OItemStack) null);
                            }
                        }
                    }
                } else if (k == 1) {
                    if (i < 0) {
                        return null;
                    }

                    oslot2 = (OSlot) this.c.get(i);
                    if (oslot2 != null && oslot2.a(oentityplayer)) {
                        oitemstack1 = this.b(oentityplayer, i);
                        if (oitemstack1 != null) {
                            l = oitemstack1.c;
                            oitemstack = oitemstack1.m();
                            if (oslot2 != null && oslot2.c() != null && oslot2.c().c == l) {
                                this.a(i, j, true, oentityplayer);
                            }
                        }
                    }
                } else {
                    if (i < 0) {
                        return null;
                    }

                    oslot2 = (OSlot) this.c.get(i);
                    if (oslot2 != null) {
                        oitemstack1 = oslot2.c();
                        OItemStack oitemstack4 = oinventoryplayer.o();

                        if (oitemstack1 != null) {
                            oitemstack = oitemstack1.m();
                        }

                        if (oitemstack1 == null) {
                            if (oitemstack4 != null && oslot2.a(oitemstack4)) {
                                k1 = j == 0 ? oitemstack4.a : 1;
                                if (k1 > oslot2.a()) {
                                    k1 = oslot2.a();
                                }

                                oslot2.c(oitemstack4.a(k1));
                                if (oitemstack4.a == 0) {
                                    oinventoryplayer.b((OItemStack) null);
                                }
                            }
                        } else if (oslot2.a(oentityplayer)) {
                            if (oitemstack4 == null) {
                                k1 = j == 0 ? oitemstack1.a : (oitemstack1.a + 1) / 2;
                                oitemstack3 = oslot2.a(k1);
                                oinventoryplayer.b(oitemstack3);
                                if (oitemstack1.a == 0) {
                                    oslot2.c((OItemStack) null);
                                }

                                oslot2.a(oentityplayer, oinventoryplayer.o());
                            } else if (oslot2.a(oitemstack4)) {
                                if (oitemstack1.c == oitemstack4.c && oitemstack1.k() == oitemstack4.k() && OItemStack.a(oitemstack1, oitemstack4)) {
                                    k1 = j == 0 ? oitemstack4.a : 1;
                                    if (k1 > oslot2.a() - oitemstack1.a) {
                                        k1 = oslot2.a() - oitemstack1.a;
                                    }

                                    if (k1 > oitemstack4.e() - oitemstack1.a) {
                                        k1 = oitemstack4.e() - oitemstack1.a;
                                    }

                                    oitemstack4.a(k1);
                                    if (oitemstack4.a == 0) {
                                        oinventoryplayer.b((OItemStack) null);
                                    }

                                    oitemstack1.a += k1;
                                } else if (oitemstack4.a <= oslot2.a()) {
                                    oslot2.c(oitemstack4);
                                    oinventoryplayer.b(oitemstack1);
                                }
                            } else if (oitemstack1.c == oitemstack4.c && oitemstack4.e() > 1 && (!oitemstack1.h() || oitemstack1.k() == oitemstack4.k()) && OItemStack.a(oitemstack1, oitemstack4)) {
                                k1 = oitemstack1.a;
                                if (k1 > 0 && k1 + oitemstack4.a <= oitemstack4.e()) {
                                    oitemstack4.a += k1;
                                    oitemstack1 = oslot2.a(k1);
                                    if (oitemstack1.a == 0) {
                                        oslot2.c((OItemStack) null);
                                    }

                                    oslot2.a(oentityplayer, oinventoryplayer.o());
                                }
                            }
                        }

                        oslot2.e();
                    }
                }
            } else if (k == 2 && j >= 0 && j < 9) {
                oslot2 = (OSlot) this.c.get(i);
                if (oslot2.a(oentityplayer)) {
                    oitemstack1 = oinventoryplayer.a(j);
                    boolean flag = oitemstack1 == null || oslot2.f == oinventoryplayer && oslot2.a(oitemstack1);

                    k1 = -1;
                    if (!flag) {
                        k1 = oinventoryplayer.j();
                        flag |= k1 > -1;
                    }

                    if (oslot2.d() && flag) {
                        oitemstack3 = oslot2.c();
                        oinventoryplayer.a(j, oitemstack3);
                        if ((oslot2.f != oinventoryplayer || !oslot2.a(oitemstack1)) && oitemstack1 != null) {
                            if (k1 > -1) {
                                oinventoryplayer.a(oitemstack1);
                                oslot2.a(oitemstack3.a);
                                oslot2.c((OItemStack) null);
                                oslot2.a(oentityplayer, oitemstack3);
                            }
                        } else {
                            oslot2.a(oitemstack3.a);
                            oslot2.c(oitemstack1);
                            oslot2.a(oentityplayer, oitemstack3);
                        }
                    } else if (!oslot2.d() && oitemstack1 != null && oslot2.a(oitemstack1)) {
                        oinventoryplayer.a(j, (OItemStack) null);
                        oslot2.c(oitemstack1);
                    }
                }
            } else if (k == 3 && oentityplayer.ce.d && oinventoryplayer.o() == null && i >= 0) {
                oslot2 = (OSlot) this.c.get(i);
                if (oslot2 != null && oslot2.d()) {
                    oitemstack1 = oslot2.c().m();
                    oitemstack1.a = oitemstack1.e();
                    oinventoryplayer.b(oitemstack1);
                }
            } else if (k == 4 && oinventoryplayer.o() == null && i >= 0) {
                oslot2 = (OSlot) this.c.get(i);
                if (oslot2 != null && oslot2.d()) {
                    oitemstack1 = oslot2.a(j == 0 ? 1 : oslot2.c().a);
                    oslot2.a(oentityplayer, oitemstack1);
                    oentityplayer.c(oitemstack1);
                }
            } else if (k == 6 && i >= 0) {
                oslot2 = (OSlot) this.c.get(i);
                oitemstack1 = oinventoryplayer.o();
                if (oitemstack1 != null && (oslot2 == null || !oslot2.d() || !oslot2.a(oentityplayer))) {
                    l = j == 0 ? 0 : this.c.size() - 1;
                    k1 = j == 0 ? 1 : -1;

                    for (int l1 = 0; l1 < 2; ++l1) {
                        for (int i2 = l; i2 >= 0 && i2 < this.c.size() && oitemstack1.a < oitemstack1.e(); i2 += k1) {
                            OSlot oslot3 = (OSlot) this.c.get(i2);

                            if (oslot3.d() && a(oslot3, oitemstack1, true) && oslot3.a(oentityplayer) && this.a(oitemstack1, oslot3) && (l1 != 0 || oslot3.c().a != oslot3.c().e())) {
                                int j2 = Math.min(oitemstack1.e() - oitemstack1.a, oslot3.c().a);
                                OItemStack oitemstack5 = oslot3.a(j2);

                                oitemstack1.a += j2;
                                if (oitemstack5.a <= 0) {
                                    oslot3.c((OItemStack) null);
                                }

                                oslot3.a(oentityplayer, oitemstack5);
                            }
                        }
                    }
                }

                this.b();
            }
        }

        return oitemstack;
    }

    public boolean a(OItemStack oitemstack, OSlot oslot) {
        return true;
    }

    protected void a(int i, int j, boolean flag, OEntityPlayer oentityplayer) {
        this.a(i, j, 1, oentityplayer);
    }

    public void b(OEntityPlayer oentityplayer) {
        // CanaryMod: onCloseInventory
        if (oentityplayer instanceof OEntityPlayerMP) {
            HookParametersCloseInventory closeInventoryParameters = new HookParametersCloseInventory(((OEntityPlayerMP) oentityplayer).getPlayer(), this.inventory, false);

            etc.getLoader().callHook(PluginLoader.Hook.CLOSE_INVENTORY, closeInventoryParameters);
        }

        OInventoryPlayer oinventoryplayer = oentityplayer.bK;

        if (oinventoryplayer.o() != null) {
            oentityplayer.c(oinventoryplayer.o());
            oinventoryplayer.b((OItemStack) null);
        }
    }

    public void a(OIInventory oiinventory) {
        this.b();
    }

    public void a(int i, OItemStack oitemstack) {
        this.a(i).c(oitemstack);
    }

    public boolean c(OEntityPlayer oentityplayer) {
        return !this.i.contains(oentityplayer);
    }

    public void a(OEntityPlayer oentityplayer, boolean flag) {
        if (flag) {
            this.i.remove(oentityplayer);
        } else {
            this.i.add(oentityplayer);
        }
    }

    public abstract boolean a(OEntityPlayer oentityplayer);

    protected boolean a(OItemStack oitemstack, int i, int j, boolean flag) {
        boolean flag1 = false;
        int k = i;

        if (flag) {
            k = j - 1;
        }

        OSlot oslot;
        OItemStack oitemstack1;

        if (oitemstack.f()) {
            while (oitemstack.a > 0 && (!flag && k < j || flag && k >= i)) {
                oslot = (OSlot) this.c.get(k);
                oitemstack1 = oslot.c();
                if (oitemstack1 != null && oitemstack1.c == oitemstack.c && (!oitemstack.h() || oitemstack.k() == oitemstack1.k()) && OItemStack.a(oitemstack, oitemstack1)) {
                    int l = oitemstack1.a + oitemstack.a;

                    if (l <= oitemstack.e()) {
                        oitemstack.a = 0;
                        oitemstack1.a = l;
                        oslot.e();
                        flag1 = true;
                    } else if (oitemstack1.a < oitemstack.e()) {
                        oitemstack.a -= oitemstack.e() - oitemstack1.a;
                        oitemstack1.a = oitemstack.e();
                        oslot.e();
                        flag1 = true;
                    }
                }

                if (flag) {
                    --k;
                } else {
                    ++k;
                }
            }
        }

        if (oitemstack.a > 0) {
            if (flag) {
                k = j - 1;
            } else {
                k = i;
            }

            while (!flag && k < j || flag && k >= i) {
                oslot = (OSlot) this.c.get(k);
                oitemstack1 = oslot.c();
                if (oitemstack1 == null) {
                    oslot.c(oitemstack.m());
                    oslot.e();
                    oitemstack.a = 0;
                    flag1 = true;
                    break;
                }

                if (flag) {
                    --k;
                } else {
                    ++k;
                }
            }
        }

        return flag1;
    }

    public static int b(int i) {
        return i >> 2 & 3;
    }

    public static int c(int i) {
        return i & 3;
    }

    public static boolean d(int i) {
        return i == 0 || i == 1;
    }

    protected void d() {
        this.g = 0;
        this.h.clear();
    }

    public static boolean a(OSlot oslot, OItemStack oitemstack, boolean flag) {
        boolean flag1 = oslot == null || !oslot.d();

        if (oslot != null && oslot.d() && oitemstack != null && oitemstack.a(oslot.c()) && OItemStack.a(oslot.c(), oitemstack)) {
            int i = flag ? 0 : oitemstack.a;

            flag1 |= oslot.c().a + i <= oitemstack.e();
        }

        return flag1;
    }

    public static void a(Set set, int i, OItemStack oitemstack, int j) {
        switch (i) {
            case 0:
                oitemstack.a = OMathHelper.d((float) oitemstack.a / (float) set.size());
                break;

            case 1:
                oitemstack.a = 1;
        }

        oitemstack.a += j;
    }

    public boolean b(OSlot oslot) {
        return true;
    }

    public static int b(OIInventory oiinventory) {
        if (oiinventory == null) {
            return 0;
        } else {
            int i = 0;
            float f = 0.0F;

            for (int j = 0; j < oiinventory.j_(); ++j) {
                OItemStack oitemstack = oiinventory.a(j);

                if (oitemstack != null) {
                    f += (float) oitemstack.a / (float) Math.min(oiinventory.d(), oitemstack.e());
                    ++i;
                }
            }

            f /= (float) oiinventory.j_();
            return OMathHelper.d(f * 14.0F) + (i > 0 ? 1 : 0);
        }
    }

    // CanaryMod: get and set inventory passed to the GUI.
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Mostly a copy of updateCraftingResults()
     * The only change is to bypass a check that prevents crafting-result slot updates.
     */
    public void updateChangedSlots() {
        for (int i = 0; i < this.c.size(); ++i) {
            OItemStack oitemstack = ((OSlot) this.c.get(i)).c();
            OItemStack oitemstack1 = (OItemStack) this.b.get(i);

            if (!OItemStack.b(oitemstack1, oitemstack)) {
                oitemstack1 = oitemstack == null ? null : oitemstack.m();
                this.b.set(i, oitemstack1);

                /* Change from updateCraftingResults() here.
                 * Originally (or similar format depending on Notchian updates):
                 * for (int j = 0; j < this.e.size(); ++j) {
                 *     ((OICrafting) this.e.get(j)).a(this, i, oitemstack1);
                 * }
                 *
                 * Now:
                 */
                sendUpdateToCrafters(i, oitemstack);
                // End change.
            }
        }
    }

    private void sendUpdateToCrafters(int slotIndex, OItemStack oitemstack) {
        for (int j = 0; j < this.e.size(); ++j) {
            if(this.e.get(j) instanceof OEntityPlayerMP) {
                ((OEntityPlayerMP) this.e.get(j)).updateSlot(this.d, slotIndex, oitemstack);
            }
        }
    }

    public void updateSlot(int index) {
        OSlot slot = getSlot(index);
        if(slot == null)
            return;

        OItemStack oitemstack = slot.c();
        if(oitemstack != null)
            oitemstack = oitemstack.m();

        sendUpdateToCrafters(index, oitemstack);
    }

    public OSlot getSlot(int index) {
        if(index < 0 || index >= this.c.size())
            return null;
        return this.a(index);
    }
}
