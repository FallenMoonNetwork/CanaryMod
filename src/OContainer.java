import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class OContainer {

    public List b = new ArrayList();
    public List c = new ArrayList();
    public int d = 0;
    private short a = 0;
    protected List e = new ArrayList();
    private Set f = new HashSet();
    // CanaryMod: inventory - Used to know which inventory was passed to this container GUI.
    private Inventory inventory;

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
                oitemstack1 = oitemstack == null ? null : oitemstack.l();
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
        OInventoryPlayer oinventoryplayer = oentityplayer.bJ;
        OSlot oslot;
        OItemStack oitemstack1;
        int l;
        OItemStack oitemstack2;

        if ((k == 0 || k == 1) && (j == 0 || j == 1)) {
            if (i == -999) {
                if (oinventoryplayer.n() != null && i == -999) {
                    if (j == 0) {
                        oentityplayer.c(oinventoryplayer.n());
                        oinventoryplayer.b((OItemStack) null);
                    }

                    if (j == 1) {
                        oentityplayer.c(oinventoryplayer.n().a(1));
                        if (oinventoryplayer.n().a == 0) {
                            oinventoryplayer.b((OItemStack) null);
                        }
                    }
                }
            } else if (k == 1) {
                oslot = (OSlot) this.c.get(i);
                if (oslot != null && oslot.a(oentityplayer)) {
                    oitemstack1 = this.b(oentityplayer, i);
                    if (oitemstack1 != null) {
                        int i1 = oitemstack1.c;

                        oitemstack = oitemstack1.l();
                        if (oslot != null && oslot.c() != null && oslot.c().c == i1) {
                            this.a(i, j, true, oentityplayer);
                        }
                    }
                }
            } else {
                if (i < 0) {
                    return null;
                }

                oslot = (OSlot) this.c.get(i);
                if (oslot != null) {
                    oitemstack1 = oslot.c();
                    OItemStack oitemstack3 = oinventoryplayer.n();

                    if (oitemstack1 != null) {
                        oitemstack = oitemstack1.l();
                    }

                    if (oitemstack1 == null) {
                        if (oitemstack3 != null && oslot.a(oitemstack3)) {
                            l = j == 0 ? oitemstack3.a : 1;
                            if (l > oslot.a()) {
                                l = oslot.a();
                            }

                            oslot.c(oitemstack3.a(l));
                            if (oitemstack3.a == 0) {
                                oinventoryplayer.b((OItemStack) null);
                            }
                        }
                    } else if (oslot.a(oentityplayer)) {
                        if (oitemstack3 == null) {
                            l = j == 0 ? oitemstack1.a : (oitemstack1.a + 1) / 2;
                            oitemstack2 = oslot.a(l);
                            oinventoryplayer.b(oitemstack2);
                            if (oitemstack1.a == 0) {
                                oslot.c((OItemStack) null);
                            }

                            oslot.a(oentityplayer, oinventoryplayer.n());
                        } else if (oslot.a(oitemstack3)) {
                            if (oitemstack1.c == oitemstack3.c && oitemstack1.j() == oitemstack3.j() && OItemStack.a(oitemstack1, oitemstack3)) {
                                l = j == 0 ? oitemstack3.a : 1;
                                if (l > oslot.a() - oitemstack1.a) {
                                    l = oslot.a() - oitemstack1.a;
                                }

                                if (l > oitemstack3.d() - oitemstack1.a) {
                                    l = oitemstack3.d() - oitemstack1.a;
                                }

                                oitemstack3.a(l);
                                if (oitemstack3.a == 0) {
                                    oinventoryplayer.b((OItemStack) null);
                                }

                                oitemstack1.a += l;
                            } else if (oitemstack3.a <= oslot.a()) {
                                oslot.c(oitemstack3);
                                oinventoryplayer.b(oitemstack1);
                            }
                        } else if (oitemstack1.c == oitemstack3.c && oitemstack3.d() > 1 && (!oitemstack1.g() || oitemstack1.j() == oitemstack3.j()) && OItemStack.a(oitemstack1, oitemstack3)) {
                            l = oitemstack1.a;
                            if (l > 0 && l + oitemstack3.a <= oitemstack3.d()) {
                                oitemstack3.a += l;
                                oitemstack1 = oslot.a(l);
                                if (oitemstack1.a == 0) {
                                    oslot.c((OItemStack) null);
                                }

                                oslot.a(oentityplayer, oinventoryplayer.n());
                            }
                        }
                    }

                    oslot.e();
                }
            }
        } else if (k == 2 && j >= 0 && j < 9) {
            oslot = (OSlot) this.c.get(i);
            if (oslot.a(oentityplayer)) {
                oitemstack1 = oinventoryplayer.a(j);
                boolean flag = oitemstack1 == null || oslot.f == oinventoryplayer && oslot.a(oitemstack1);

                l = -1;
                if (!flag) {
                    l = oinventoryplayer.i();
                    flag |= l > -1;
                }

                if (oslot.d() && flag) {
                    oitemstack2 = oslot.c();
                    oinventoryplayer.a(j, oitemstack2);
                    if ((oslot.f != oinventoryplayer || !oslot.a(oitemstack1)) && oitemstack1 != null) {
                        if (l > -1) {
                            oinventoryplayer.a(oitemstack1);
                            oslot.a(oitemstack2.a);
                            oslot.c((OItemStack) null);
                            oslot.a(oentityplayer, oitemstack2);
                        }
                    } else {
                        oslot.a(oitemstack2.a);
                        oslot.c(oitemstack1);
                        oslot.a(oentityplayer, oitemstack2);
                    }
                } else if (!oslot.d() && oitemstack1 != null && oslot.a(oitemstack1)) {
                    oinventoryplayer.a(j, (OItemStack) null);
                    oslot.c(oitemstack1);
                }
            }
        } else if (k == 3 && oentityplayer.cd.d && oinventoryplayer.n() == null && i >= 0) {
            oslot = (OSlot) this.c.get(i);
            if (oslot != null && oslot.d()) {
                oitemstack1 = oslot.c().l();
                oitemstack1.a = oitemstack1.d();
                oinventoryplayer.b(oitemstack1);
            }
        }

        return oitemstack;
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

        OInventoryPlayer oinventoryplayer = oentityplayer.bJ;

        if (oinventoryplayer.n() != null) {
            oentityplayer.c(oinventoryplayer.n());
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
        return !this.f.contains(oentityplayer);
    }

    public void a(OEntityPlayer oentityplayer, boolean flag) {
        if (flag) {
            this.f.remove(oentityplayer);
        } else {
            this.f.add(oentityplayer);
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

        if (oitemstack.e()) {
            while (oitemstack.a > 0 && (!flag && k < j || flag && k >= i)) {
                oslot = (OSlot) this.c.get(k);
                oitemstack1 = oslot.c();
                if (oitemstack1 != null && oitemstack1.c == oitemstack.c && (!oitemstack.g() || oitemstack.j() == oitemstack1.j()) && OItemStack.a(oitemstack, oitemstack1)) {
                    int l = oitemstack1.a + oitemstack.a;

                    if (l <= oitemstack.d()) {
                        oitemstack.a = 0;
                        oitemstack1.a = l;
                        oslot.e();
                        flag1 = true;
                    } else if (oitemstack1.a < oitemstack.d()) {
                        oitemstack.a -= oitemstack.d() - oitemstack1.a;
                        oitemstack1.a = oitemstack.d();
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
                    oslot.c(oitemstack.l());
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
                oitemstack1 = oitemstack == null ? null : oitemstack.l();
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
            oitemstack = oitemstack.l();
        
        sendUpdateToCrafters(index, oitemstack);
    }
    
    public OSlot getSlot(int index) {
        if(index < 0 || index >= this.c.size())
            return null;
        return this.a(index);
    }
}
