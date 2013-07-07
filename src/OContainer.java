import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class OContainer {

    public List b = new ArrayList();
    public List c = new ArrayList();
    public int d;
    private int f = -1;
    private int g;
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
            arraylist.add(((OSlot) this.c.get(i)).d());
        }

        return arraylist;
    }

    public void b() {
        for (int i = 0; i < this.c.size(); ++i) {
            OItemStack oitemstack = ((OSlot) this.c.get(i)).d();
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

        return oslot != null ? oslot.d() : null;
    }

    public OItemStack a(int i, int j, int k, OEntityPlayer oentityplayer) {
        OItemStack oitemstack = null;
        OInventoryPlayer oinventoryplayer = oentityplayer.bn;
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

                if (oslot != null && a(oslot, oinventoryplayer.o(), true) && oslot.a(oinventoryplayer.o()) && oinventoryplayer.o().b > this.h.size() && this.b(oslot)) {
                    this.h.add(oslot);
                }
            } else if (this.g == 2) {
                if (!this.h.isEmpty()) {
                    oitemstack1 = oinventoryplayer.o().m();
                    l = oinventoryplayer.o().b;
                    Iterator iterator = this.h.iterator();

                    while (iterator.hasNext()) {
                        OSlot oslot1 = (OSlot) iterator.next();

                        if (oslot1 != null && a(oslot1, oinventoryplayer.o(), true) && oslot1.a(oinventoryplayer.o()) && oinventoryplayer.o().b >= this.h.size() && this.b(oslot1)) {
                            OItemStack oitemstack2 = oitemstack1.m();
                            int j1 = oslot1.e() ? oslot1.d().b : 0;

                            a(this.h, this.f, oitemstack2, j1);
                            if (oitemstack2.b > oitemstack2.e()) {
                                oitemstack2.b = oitemstack2.e();
                            }

                            if (oitemstack2.b > oslot1.a()) {
                                oitemstack2.b = oslot1.a();
                            }

                            l -= oitemstack2.b - j1;
                            oslot1.c(oitemstack2);
                        }
                    }

                    oitemstack1.b = l;
                    if (oitemstack1.b <= 0) {
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
                            oentityplayer.b(oinventoryplayer.o());
                            oinventoryplayer.b((OItemStack) null);
                        }

                        if (j == 1) {
                            oentityplayer.b(oinventoryplayer.o().a(1));
                            if (oinventoryplayer.o().b == 0) {
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
                            l = oitemstack1.d;
                            oitemstack = oitemstack1.m();
                            if (oslot2 != null && oslot2.d() != null && oslot2.d().d == l) {
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
                        oitemstack1 = oslot2.d();
                        OItemStack oitemstack4 = oinventoryplayer.o();

                        if (oitemstack1 != null) {
                            oitemstack = oitemstack1.m();
                        }

                        if (oitemstack1 == null) {
                            if (oitemstack4 != null && oslot2.a(oitemstack4)) {
                                k1 = j == 0 ? oitemstack4.b : 1;
                                if (k1 > oslot2.a()) {
                                    k1 = oslot2.a();
                                }

                                if (oitemstack4.b >= k1) {
                                    oslot2.c(oitemstack4.a(k1));
                                }

                                if (oitemstack4.b == 0) {
                                    oinventoryplayer.b((OItemStack) null);
                                }
                            }
                        } else if (oslot2.a(oentityplayer)) {
                            if (oitemstack4 == null) {
                                k1 = j == 0 ? oitemstack1.b : (oitemstack1.b + 1) / 2;
                                oitemstack3 = oslot2.a(k1);
                                oinventoryplayer.b(oitemstack3);
                                if (oitemstack1.b == 0) {
                                    oslot2.c((OItemStack) null);
                                }

                                oslot2.a(oentityplayer, oinventoryplayer.o());
                            } else if (oslot2.a(oitemstack4)) {
                                if (oitemstack1.d == oitemstack4.d && oitemstack1.k() == oitemstack4.k() && OItemStack.a(oitemstack1, oitemstack4)) {
                                    k1 = j == 0 ? oitemstack4.b : 1;
                                    if (k1 > oslot2.a() - oitemstack1.b) {
                                        k1 = oslot2.a() - oitemstack1.b;
                                    }

                                    if (k1 > oitemstack4.e() - oitemstack1.b) {
                                        k1 = oitemstack4.e() - oitemstack1.b;
                                    }

                                    oitemstack4.a(k1);
                                    if (oitemstack4.b == 0) {
                                        oinventoryplayer.b((OItemStack) null);
                                    }

                                    oitemstack1.b += k1;
                                } else if (oitemstack4.b <= oslot2.a()) {
                                    oslot2.c(oitemstack4);
                                    oinventoryplayer.b(oitemstack1);
                                }
                            } else if (oitemstack1.d == oitemstack4.d && oitemstack4.e() > 1 && (!oitemstack1.h() || oitemstack1.k() == oitemstack4.k()) && OItemStack.a(oitemstack1, oitemstack4)) {
                                k1 = oitemstack1.b;
                                if (k1 > 0 && k1 + oitemstack4.b <= oitemstack4.e()) {
                                    oitemstack4.b += k1;
                                    oitemstack1 = oslot2.a(k1);
                                    if (oitemstack1.b == 0) {
                                        oslot2.c((OItemStack) null);
                                    }

                                    oslot2.a(oentityplayer, oinventoryplayer.o());
                                }
                            }
                        }

                        oslot2.f();
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

                    if (oslot2.e() && flag) {
                        oitemstack3 = oslot2.d();
                        oinventoryplayer.a(j, oitemstack3.m());
                        if ((oslot2.f != oinventoryplayer || !oslot2.a(oitemstack1)) && oitemstack1 != null) {
                            if (k1 > -1) {
                                oinventoryplayer.a(oitemstack1);
                                oslot2.a(oitemstack3.b);
                                oslot2.c((OItemStack) null);
                                oslot2.a(oentityplayer, oitemstack3);
                            }
                        } else {
                            oslot2.a(oitemstack3.b);
                            oslot2.c(oitemstack1);
                            oslot2.a(oentityplayer, oitemstack3);
                        }
                    } else if (!oslot2.e() && oitemstack1 != null && oslot2.a(oitemstack1)) {
                        oinventoryplayer.a(j, (OItemStack) null);
                        oslot2.c(oitemstack1);
                    }
                }
            } else if (k == 3 && oentityplayer.bG.d && oinventoryplayer.o() == null && i >= 0) {
                oslot2 = (OSlot) this.c.get(i);
                if (oslot2 != null && oslot2.e()) {
                    oitemstack1 = oslot2.d().m();
                    oitemstack1.b = oitemstack1.e();
                    oinventoryplayer.b(oitemstack1);
                }
            } else if (k == 4 && oinventoryplayer.o() == null && i >= 0) {
                oslot2 = (OSlot) this.c.get(i);
                if (oslot2 != null && oslot2.e() && oslot2.a(oentityplayer)) {
                    oitemstack1 = oslot2.a(j == 0 ? 1 : oslot2.d().b);
                    oslot2.a(oentityplayer, oitemstack1);
                    oentityplayer.b(oitemstack1);
                }
            } else if (k == 6 && i >= 0) {
                oslot2 = (OSlot) this.c.get(i);
                oitemstack1 = oinventoryplayer.o();
                if (oitemstack1 != null && (oslot2 == null || !oslot2.e() || !oslot2.a(oentityplayer))) {
                    l = j == 0 ? 0 : this.c.size() - 1;
                    k1 = j == 0 ? 1 : -1;

                    for (int l1 = 0; l1 < 2; ++l1) {
                        for (int i2 = l; i2 >= 0 && i2 < this.c.size() && oitemstack1.b < oitemstack1.e(); i2 += k1) {
                            OSlot oslot3 = (OSlot) this.c.get(i2);

                            if (oslot3.e() && a(oslot3, oitemstack1, true) && oslot3.a(oentityplayer) && this.a(oitemstack1, oslot3) && (l1 != 0 || oslot3.d().b != oslot3.d().e())) {
                                int j2 = Math.min(oitemstack1.e() - oitemstack1.b, oslot3.d().b);
                                OItemStack oitemstack5 = oslot3.a(j2);

                                oitemstack1.b += j2;
                                if (oitemstack5.b <= 0) {
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

        OInventoryPlayer oinventoryplayer = oentityplayer.bn;

        if (oinventoryplayer.o() != null) {
            oentityplayer.b(oinventoryplayer.o());
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
            while (oitemstack.b > 0 && (!flag && k < j || flag && k >= i)) {
                oslot = (OSlot) this.c.get(k);
                oitemstack1 = oslot.d();
                if (oitemstack1 != null && oitemstack1.d == oitemstack.d && (!oitemstack.h() || oitemstack.k() == oitemstack1.k()) && OItemStack.a(oitemstack, oitemstack1)) {
                    int l = oitemstack1.b + oitemstack.b;

                    if (l <= oitemstack.e()) {
                        oitemstack.b = 0;
                        oitemstack1.b = l;
                        oslot.f();
                        flag1 = true;
                    } else if (oitemstack1.b < oitemstack.e()) {
                        oitemstack.b -= oitemstack.e() - oitemstack1.b;
                        oitemstack1.b = oitemstack.e();
                        oslot.f();
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

        if (oitemstack.b > 0) {
            if (flag) {
                k = j - 1;
            } else {
                k = i;
            }

            while (!flag && k < j || flag && k >= i) {
                oslot = (OSlot) this.c.get(k);
                oitemstack1 = oslot.d();
                if (oitemstack1 == null) {
                    oslot.c(oitemstack.m());
                    oslot.f();
                    oitemstack.b = 0;
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
        boolean flag1 = oslot == null || !oslot.e();

        if (oslot != null && oslot.e() && oitemstack != null && oitemstack.a(oslot.d()) && OItemStack.a(oslot.d(), oitemstack)) {
            int i = flag ? 0 : oitemstack.b;

            flag1 |= oslot.d().b + i <= oitemstack.e();
        }

        return flag1;
    }

    public static void a(Set set, int i, OItemStack oitemstack, int j) {
        switch (i) {
            case 0:
                oitemstack.b = OMathHelper.d((float) oitemstack.b / (float) set.size());
                break;

            case 1:
                oitemstack.b = 1;
        }

        oitemstack.b += j;
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
                    f += (float) oitemstack.b / (float) Math.min(oiinventory.d(), oitemstack.e());
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
            OItemStack oitemstack = ((OSlot) this.c.get(i)).d();
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

        OItemStack oitemstack = slot.d();
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
