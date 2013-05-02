import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class OEntityPlayerMP extends OEntityPlayer implements OICrafting {

    private OStringTranslate cl = new OStringTranslate("en_US");
    public ONetServerHandler a;
    public OMinecraftServer b;
    public OItemInWorldManager c;
    public double d;
    public double e;
    public final List f = new LinkedList();
    public final List g = new LinkedList();
    private int cm = -99999999; // CanaryMod: last health client was set to
    private int cn = -99999999;
    private boolean co = true;
    private int cp = -99999999;
    private int cq = 60;
    private int cr = 0;
    private int cs = 0;
    private boolean ct = true;
    private int cu = 0;
    public boolean h;
    public int i;
    public boolean j = false;

    private Player player; // CanaryMod: Player storage

    public OEntityPlayerMP(OMinecraftServer ominecraftserver, OWorld oworld, String s, OItemInWorldManager oiteminworldmanager) {
        super(oworld);
        oiteminworldmanager.b = this;
        this.c = oiteminworldmanager;
        this.cr = ominecraftserver.ad().o();
        OChunkCoordinates ochunkcoordinates = oworld.J();
        int i = ochunkcoordinates.a;
        int j = ochunkcoordinates.c;
        int k = ochunkcoordinates.b;

        if (!oworld.t.f && oworld.M().r() != OEnumGameType.d) {
            int l = Math.max(5, ominecraftserver.ak() - 6);

            i += this.ab.nextInt(l * 2) - l;
            j += this.ab.nextInt(l * 2) - l;
            k = oworld.i(i, j);
        }

        this.b = ominecraftserver;

        this.Y = 0.0F;
        this.bS = s;
        this.N = 0.0F;
        this.b((double) i + 0.5D, (double) k, (double) j + 0.5D, 0.0F, 0.0F);

        while (!oworld.a((OEntity) this, this.E).isEmpty()) {
            this.b(this.u, this.v + 1.0D, this.w);
        }


        // CanaryMod: Store player
        player = etc.getDataSource().getPlayer(s);
        player.setUser(this);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        if (onbttagcompound.b("playerGameType")) {
            if (OMinecraftServer.D().am()) {
                this.c.a(OMinecraftServer.D().g());
            } else {
                this.c.a(OEnumGameType.a(onbttagcompound.e("playerGameType")));
            }
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("playerGameType", this.c.b().a());
    }

    public void a(int i) {
        super.a(i);
        this.cp = -1;
    }

    public void d_() {
        this.bM.a((OICrafting) this);
    }

    protected void e_() {
        this.N = 0.0F;
    }

    public float e() {
        return 1.62F;
    }

    public void l_() {
        this.c.a();
        --this.cq;
        this.bM.b();

        while (!this.g.isEmpty()) {
            int i = Math.min(this.g.size(), 127);
            int[] aint = new int[i];
            Iterator iterator = this.g.iterator();
            int j = 0;

            while (iterator.hasNext() && j < i) {
                aint[j++] = ((Integer) iterator.next()).intValue();
                iterator.remove();
            }

            this.a.b(new OPacket29DestroyEntity(aint));
        }

        if (!this.f.isEmpty()) {
            ArrayList arraylist = new ArrayList();
            Iterator iterator1 = this.f.iterator();
            ArrayList arraylist1 = new ArrayList();

            while (iterator1.hasNext() && arraylist.size() < 5) {
                OChunkCoordIntPair ochunkcoordintpair = (OChunkCoordIntPair) iterator1.next();

                iterator1.remove();
                if (ochunkcoordintpair != null && this.q.f(ochunkcoordintpair.a << 4, 0, ochunkcoordintpair.b << 4)) {
                    arraylist.add(this.q.e(ochunkcoordintpair.a, ochunkcoordintpair.b));
                    arraylist1.addAll(((OWorldServer) this.q).c(ochunkcoordintpair.a * 16, 0, ochunkcoordintpair.b * 16, ochunkcoordintpair.a * 16 + 16, 256, ochunkcoordintpair.b * 16 + 16));
                }
            }

            if (!arraylist.isEmpty()) {
                this.a.b(new OPacket56MapChunks(arraylist));
                Iterator iterator2 = arraylist1.iterator();

                while (iterator2.hasNext()) {
                    OTileEntity otileentity = (OTileEntity) iterator2.next();

                    this.b(otileentity);
                }

                iterator2 = arraylist.iterator();

                while (iterator2.hasNext()) {
                    OChunk ochunk = (OChunk) iterator2.next();

                    this.o().q().a(this, ochunk);
                }
            }
        }
    }

    public void b(int i) {
        super.b(i);
        Collection collection = this.cr().a(OScoreObjectiveCriteria.f);
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            OScoreObjective oscoreobjective = (OScoreObjective) iterator.next();

            this.cr().a(this.am(), oscoreobjective).a(Arrays.asList(new OEntityPlayer[] { this}));
        }
    }

    public void g() {
        try {
            super.l_();

            for (int i = 0; i < this.bK.j_(); ++i) {
                OItemStack oitemstack = this.bK.a(i);

                if (oitemstack != null && OItem.f[oitemstack.c].f() && this.a.e() <= 5) {
                    OPacket opacket = ((OItemMapBase) OItem.f[oitemstack.c]).c(oitemstack, this.q, this);

                    if (opacket != null) {
                        this.a.b(opacket);
                    }
                }
            }

            // CanaryMod: Update the health
            if (this.aX() != this.cm && cm != -99999999) {
                // updates your health when it is changed.
                if (!etc.getInstance().isHealthEnabled()) {
                    super.b(this.aW());
                    this.M = false;
                } else if ((Boolean) manager.callHook(PluginLoader.Hook.HEALTH_CHANGE, getPlayer(), cm, this.aX())) {
                    super.b(this.cm);
                }
            }

            if (this.aX() != this.cm || this.cn != this.bN.a() || this.bN.e() == 0.0F != this.co) {
                //CanaryMod: convert health for values above 20
                int health = (int)(this.aX() / (this.aW() / 20));
                health = (this.aX() > 0 && health == 0) ? 1 : health;
                this.a.b(new OPacket8UpdateHealth(health, this.bN.a(), this.bN.e()));
                this.cm = this.aX();
                this.cn = this.bN.a();
                this.co = this.bN.e() == 0.0F;
            }

            // CanaryMod: Update experience
            if (this.cg != this.cp) {
                // updates your experience when it is changed.
                if (!etc.getInstance().isExpEnabled()) {
                    this.cg = 0;
                    this.cf = 0;
                } else if ((Boolean) manager.callHook(PluginLoader.Hook.EXPERIENCE_CHANGE, getPlayer(), this.cp, cg)) {
                    this.cg = this.cp;
                }
            }

            if (this.cg != this.cp) {
                this.cp = this.cg;
                this.a.b(new OPacket43Experience(this.ch, this.cg, this.cf));
            }
        } catch (Throwable throwable) {
            OCrashReport ocrashreport = OCrashReport.a(throwable, "Ticking player");
            OCrashReportCategory ocrashreportcategory = ocrashreport.a("Player being ticked");

            this.a(ocrashreportcategory);
            throw new OReportedException(ocrashreport);
        }
    }

    public void a(ODamageSource odamagesource) {
        manager.callHook(PluginLoader.Hook.DEATH, this.getEntity());
        if (etc.getInstance().deathMessages) { //CanaryMod: check if death messages are enabled
            this.b.ad().k(this.bt.b());
        }

        if (!this.q.N().b("keepInventory")) {
            this.bK.m();
        }

        Collection collection = this.q.W().a(OScoreObjectiveCriteria.c);
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            OScoreObjective oscoreobjective = (OScoreObjective) iterator.next();
            OScore oscore = this.cr().a(this.am(), oscoreobjective);

            oscore.a();
        }

        OEntityLiving oentityliving = this.bN();

        if (oentityliving != null) {
            oentityliving.c(this, this.aM);
        }
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.aq()) {
            return false;
        } else {
            boolean flag = this.b.T() && this.b.X() && "fall".equals(odamagesource.o);

            if (!flag && this.cq > 0 && odamagesource != ODamageSource.i) {
                return false;
            } else {
                if (odamagesource instanceof OEntityDamageSource) {
                    OEntity oentity = odamagesource.i();

                    if (oentity instanceof OEntityPlayer && !this.a((OEntityPlayer) oentity)) {
                        return false;
                    }

                    if (oentity instanceof OEntityArrow) {
                        OEntityArrow oentityarrow = (OEntityArrow) oentity;

                        if (oentityarrow.c instanceof OEntityPlayer && !this.a((OEntityPlayer) oentityarrow.c)) {
                            return false;
                        }
                    }
                }

                return super.a(odamagesource, i);
            }
        }
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return !this.b.X() ? false : super.a(oentityplayer);
    }

    public void c(int i) {
        if (this.ar == 1 && i == 1) {
            this.a((OStatBase) OAchievementList.C);
            this.q.e((OEntity) this);
            this.j = true;
            this.a.b(new OPacket70GameEvent(4, 0));
        } else {
            if (this.ar == 1 && i == 0) {
                this.a((OStatBase) OAchievementList.B);
                OChunkCoordinates ochunkcoordinates = this.b.getWorld(this.q.name, i).l();

                if (ochunkcoordinates != null) {
                    this.a.a((double) ochunkcoordinates.a, (double) ochunkcoordinates.b, (double) ochunkcoordinates.c, 0.0F, 0.0F);
                }

                i = 1;
            } else {
                this.a((OStatBase) OAchievementList.x);
            }

            // CanaryMod onPortalUse
            Location goingTo = simulatePortalUse(i, OMinecraftServer.D().getWorld(q.name, i));
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.PORTAL_USE, player, goingTo)) {
                this.b.ad().a(this, i);
            this.cp = -1;
                this.cm = -1;
            } //
            this.cn = -1;
        }
    }

    //Simulates the use of a Portal by the Player to determine the location going to
    private final Location simulatePortalUse(int dimensionTo, OWorldServer oworldserverTo) {
        double y = this.u;
        float rotX = this.A;
        float rotY = this.B;
        double x = this.t;
        double z = this.v;
        double adjust = 8.0D;
        if (dimensionTo == -1) {
            x /= adjust;
            z /= adjust;
        } else if (dimensionTo == 0) {
            x *= adjust;
            z *= adjust;
        } else {
            OChunkCoordinates ochunkcoordinates;
            if (dimensionTo == 1) {
                ochunkcoordinates = oworldserverTo.J();
            } else {
                ochunkcoordinates = oworldserverTo.l();
            }
            x = (double) ochunkcoordinates.a;
            y = (double) ochunkcoordinates.b;
            z = (double) ochunkcoordinates.c;
            rotX = 90.0F;
            rotY = 0.0F;
        }
        if (dimensionTo != 1) {
            x = (double) OMathHelper.a((int) x, -29999872, 29999872);
            z = (double) OMathHelper.a((int) z, -29999872, 29999872);
        }
        return new Location(oworldserverTo.world, x, y, z, rotX, rotY);
    }

    private void b(OTileEntity otileentity) {
        if (otileentity != null) {
            // CanaryMod: Let plugins know we're showing a sign
            if (otileentity instanceof OTileEntitySign) {
                Sign sign = new Sign((OTileEntitySign) otileentity);

                manager.callHook(PluginLoader.Hook.SIGN_SHOW, getPlayer(), sign);
            }
            OPacket opacket = otileentity.m();

            if (opacket != null) {
                this.a.b(opacket);
            }
        }
    }

    public void a(OEntity oentity, int i) {
        super.a(oentity, i);
        this.bM.b();
    }

    public OEnumStatus a(int i, int j, int k) {
        OEnumStatus oenumstatus = super.a(i, j, k);

        if (oenumstatus == OEnumStatus.a) {
            OPacket17Sleep opacket17sleep = new OPacket17Sleep(this, 0, i, j, k);

            this.o().q().a((OEntity) this, (OPacket) opacket17sleep);
            this.a.a(this.u, this.v, this.w, this.A, this.B);
            this.a.b(opacket17sleep);
        }

        return oenumstatus;
    }

    public void a(boolean flag, boolean flag1, boolean flag2) {
        if (this.bz()) {
            this.o().q().b(this, new OPacket18Animation(this, 3));
        }

        super.a(flag, flag1, flag2);
        if (this.a != null) {
            this.a.a(this.u, this.v, this.w, this.A, this.B);
        }
    }

    public void a(OEntity oentity) {
        super.a(oentity);
        this.a.b(new OPacket39AttachEntity(this, this.o));
        this.a.a(this.u, this.v, this.w, this.A, this.B);
    }

    protected void a(double d0, boolean flag) {}

    public void b(double d0, boolean flag) {
        super.a(d0, flag);
    }

    private void ct() {
        this.cu = this.cu % 100 + 1;
    }

    public void b(int i, int j, int k) {
        // CanaryMod: Check if we can open this
        OContainerWorkbench container = new OContainerWorkbench(this.bK, this.q, i, j, k);
        Inventory inv = new Workbench(container);

        container.setInventory(inv);
        String name = "Crafting";

        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }

        if (inv != null) {
            name = inv.getName();
        }
        //CanaryMod: end

        this.ct();
        this.a.b(new OPacket100OpenWindow(this.cu, 1, name, 9, true));
        this.bM = new OContainerWorkbench(this.bK, this.q, i, j, k);
        this.bM.d = this.cu;
        this.bM.a((OICrafting) this);
    }

    public void a(int i, int j, int k, String s) {
        // CanaryMod: Check if we can open this
        OContainerEnchantment container = new OContainerEnchantment(this.bK, this.q, i, j, k);
        Inventory inv = new EnchantmentTable(container);

        container.setInventory(inv);
        String name = s == null ? "" : s;

        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }

        if (inv != null) {
            name = inv.getName();
        }

        this.ct();
        this.a.b(new OPacket100OpenWindow(this.cu, 4, name, 9, s != null));
        this.bM = container;
        this.bM.d = this.cu;
        this.bM.a((OICrafting) this);
    }

    public void c(int i, int j, int k) {
        // CanaryMod: Check if we can open this
        OContainerRepair container = new OContainerRepair(this.bK, this.q, i, j, k, this);
        Inventory inv = container.getInventory();

        container.setInventory(inv);
        String name = "Repairing";

        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        }

        if (inv != null) {
            name = inv.getName();
        }

        this.ct();
        this.a.b(new OPacket100OpenWindow(this.cu, 8, name, 9, true));
        this.bM = container;
        this.bM.d = this.cu;
        this.bM.a((OICrafting) this);
    }

    public void a(OIInventory oiinventory) {
        // CanaryMod: Check if we can open this
        Inventory inv = null;
        HookParametersOpenInventory openInventoryParameters = null;

        if (oiinventory instanceof OTileEntityChest) {
            inv = new Chest((OTileEntityChest) oiinventory);
            openInventoryParameters = new HookParametersOpenInventory(getPlayer(), inv, false);
            if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, openInventoryParameters)) {
                return;
            }
        } else if (oiinventory instanceof OInventoryLargeChest) {
            inv = new DoubleChest((OInventoryLargeChest) oiinventory);
            openInventoryParameters = new HookParametersOpenInventory(getPlayer(), inv, false);
            if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, openInventoryParameters)) {
                return;
            }
        } else if (oiinventory instanceof OInventoryEnderChest) {
            inv = new EnderChestInventory((OInventoryEnderChest) oiinventory, getPlayer());
            openInventoryParameters = new HookParametersOpenInventory(getPlayer(), inv, false);
            if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, openInventoryParameters)) {
                return;
            }
        } else if (oiinventory instanceof OEntityMinecartContainer) {
            inv = ((OEntityMinecartContainer) oiinventory).getEntity();
            openInventoryParameters = new HookParametersOpenInventory(getPlayer(), inv, false);
            if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, openInventoryParameters)) {
                return;
            }
        }

        if (this.bM != this.bL) {
            this.h();
        }

        this.ct();
        this.a.b(new OPacket100OpenWindow(this.cu, 0, oiinventory.b(), oiinventory.j_(), oiinventory.c()));
        // CanaryMod: Check if openend the chest in silence mode.
        this.bM = new OContainerChest(this.bK, oiinventory, (openInventoryParameters == null) ? false : openInventoryParameters.isSilenced());
        this.bM.setInventory(inv);
        if(inv != null)
            inv.setOContainer(this.bM);
        // CanaryMod end
        this.bM.d = this.cu;
        this.bM.a((OICrafting) this);
    }

    public void a(OTileEntityHopper otileentityhopper) {
        // CanaryMod: Check if we can open this
        OContainerHopper container = new OContainerHopper(this.bK, otileentityhopper);
        Inventory inv = otileentityhopper.getComplexBlock();

        inv.setOContainer(container);
        container.setInventory(inv);

        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        } //

        this.ct();
        this.a.b(new OPacket100OpenWindow(this.cu, 9, otileentityhopper.b(), otileentityhopper.j_(), otileentityhopper.c()));
        this.bM = container;
        this.bM.d = this.cu;
        this.bM.a((OICrafting) this);
    }

    public void a(OEntityMinecartHopper oentityminecarthopper) {
        // CanaryMod: Check if we can open this
        OContainerHopper container = new OContainerHopper(this.bK, oentityminecarthopper);
        Inventory inv = oentityminecarthopper.getEntity();

        container.setInventory(inv);
        inv.setOContainer(container);

        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        } //

        this.ct();
        this.a.b(new OPacket100OpenWindow(this.cu, 9, oentityminecarthopper.b(), oentityminecarthopper.j_(), oentityminecarthopper.c()));
        this.bM = container;
        this.bM.d = this.cu;
        this.bM.a((OICrafting) this);
    }

    public void a(OTileEntityFurnace otileentityfurnace) {
        // CanaryMod: Check if we can open this
        Inventory inv = new Furnace(otileentityfurnace);

        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        } //

        this.ct();
        this.a.b(new OPacket100OpenWindow(this.cu, 2, otileentityfurnace.b(), otileentityfurnace.j_(), otileentityfurnace.c()));
        this.bM = new OContainerFurnace(this.bK, otileentityfurnace);
        this.bL.setInventory(inv); // CanaryMod: Set the inventory for the GUI
        if (inv != null) {
            inv.setOContainer(this.bL); // CanaryMod: Set the OContainer for the Furnace Inventory
        }
        this.bM.d = this.cu;
        this.bM.a((OICrafting) this);
    }

    public void a(OTileEntityDispenser otileentitydispenser) {
        // CanaryMod: Check if we can open this
        Inventory inv = new Dispenser(otileentitydispenser);

        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        } //

        this.ct();
        this.a.b(new OPacket100OpenWindow(this.cu, otileentitydispenser instanceof OTileEntityDropper ? 10 : 3, otileentitydispenser.b(), otileentitydispenser.j_(), otileentitydispenser.c()));
        this.bM = new OContainerDispenser(this.bK, otileentitydispenser);
        this.bM.setInventory(inv); // CanaryMod: set inventory for the GUI
        if (inv != null) {
            inv.setOContainer(this.bM); // CanaryMod: set OContainer for the inventory
        }
        this.bM.d = this.cu;
        this.bM.a((OICrafting) this);
    }

    public void a(OTileEntityBrewingStand otileentitybrewingstand) {
        // CanaryMod start: Check if we can open this
        Inventory inv = new BrewingStand(otileentitybrewingstand);

        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        } // CanaryMod end

        this.ct();
        this.a.b(new OPacket100OpenWindow(this.cu, 5, otileentitybrewingstand.b(), otileentitybrewingstand.j_(), otileentitybrewingstand.c()));
        this.bM = new OContainerBrewingStand(this.bK, otileentitybrewingstand);
        this.bL.setInventory(inv); // CanaryMod: set inventory for the GUI
        if(inv != null) {
            inv.setOContainer(this.bL); // CanaryMod: set OContainer for the inventory
        }
        this.bM.d = this.cu;
        this.bM.a((OICrafting) this);
    }

    public void a(OTileEntityBeacon otileentitybeacon) {
        // CanaryMod: Check if we can open this
        Inventory inv = new Beacon(otileentitybeacon);

        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, new HookParametersOpenInventory(getPlayer(), inv, false))) {
            return;
        } //

        this.ct();
        this.a.b(new OPacket100OpenWindow(this.cu, 7, otileentitybeacon.b(), otileentitybeacon.j_(), otileentitybeacon.c()));
        this.bM = new OContainerBeacon(this.bK, otileentitybeacon);
        this.bM.setInventory(inv); // CanaryMod: set inventory for the GUI
        if(inv != null)
            inv.setOContainer(this.bM); // CanaryMod: set OContainer for the inventory
        this.bM.d = this.cu;
        this.bM.a((OICrafting) this);
    }

    public void a(OIMerchant oimerchant, String s) {
        this.ct();
        this.bM = new OContainerMerchant(this.bK, oimerchant, this.q);
        this.bM.d = this.cu;
        this.bM.a((OICrafting) this);
        OInventoryMerchant oinventorymerchant = ((OContainerMerchant) this.bM).e();

        this.a.b(new OPacket100OpenWindow(this.cu, 6, s == null ? "" : s, oinventorymerchant.j_(), s != null));
        OMerchantRecipeList omerchantrecipelist = oimerchant.b(this);

        if (omerchantrecipelist != null) {
            try {
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                DataOutputStream dataoutputstream = new DataOutputStream(bytearrayoutputstream);

                dataoutputstream.writeInt(this.cu);
                omerchantrecipelist.a(dataoutputstream);
                this.a.b(new OPacket250CustomPayload("MC|TrList", bytearrayoutputstream.toByteArray()));
            } catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
    }

    public void a(OContainer ocontainer, int i, OItemStack oitemstack) {
        if (!(ocontainer.a(i) instanceof OSlotCrafting)) {
            if (!this.h) {
                this.a.b(new OPacket103SetSlot(ocontainer.d, i, oitemstack));
            }
        }
    }

    public void a(OContainer ocontainer) {
        this.a(ocontainer, ocontainer.a());
    }

    public void a(OContainer ocontainer, List list) {
        this.a.b(new OPacket104WindowItems(ocontainer.d, list));
        this.a.b(new OPacket103SetSlot(-1, -1, this.bK.o()));
    }

    public void a(OContainer ocontainer, int i, int j) {
        this.a.b(new OPacket105UpdateProgressbar(ocontainer.d, i, j));
    }

    public void h() {
        this.a.b(new OPacket101CloseWindow(this.bM.d));
        this.j();
    }

    public void i() {
        if (!this.h) {
            this.a.b(new OPacket103SetSlot(-1, -1, this.bK.o()));
        }
    }

    public void j() {
        this.bM.b((OEntityPlayer) this);
        this.bM = this.bL;
    }

    public void a(OStatBase ostatbase, int i) {
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.STAT_GAINED, getPlayer(), new Stat(ostatbase))) {
            return;
        }
        if (ostatbase != null) {
            if (!ostatbase.f) {
                while (i > 100) {
                    this.a.b(new OPacket200Statistic(ostatbase.e, 100));
                    i -= 100;
                }

                this.a.b(new OPacket200Statistic(ostatbase.e, i));
            }
        }
    }

    public void k() {
        if (this.n != null) {
            this.n.a((OEntity) this);
        }

        if (this.ca) {
            this.a(true, false, false);
        }
    }

    public void l() {
        this.cm = -99999999;
    }

    public void b(String s) {
        OStringTranslate ostringtranslate = OStringTranslate.a();
        String s1 = ostringtranslate.a(s);

        this.a.b(new OPacket3Chat(s1));
    }

    protected void m() {
        this.a.b(new OPacket38EntityStatus(this.k, (byte) 9));
        super.m();
    }

    public void a(OItemStack oitemstack, int i) {
        super.a(oitemstack, i);
        if (oitemstack != null && oitemstack.b() != null && oitemstack.b().b_(oitemstack) == OEnumAction.b) {

            // CanaryMod: Call EAT Hook
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.EAT, this.getPlayer(), new Item(oitemstack))) {
                super.a(oitemstack, i);
                this.o().q().b(this, new OPacket18Animation(this, 5));
            } else {
                this.a.b((OPacket) (new OPacket38EntityStatus(this.k, (byte) 9)));
                this.getPlayer().updateLevels();
                this.getPlayer().updateInventory();
            }
        } else { // CanaryMod: Bow, or block action
            super.a(oitemstack, i);
        }
    }

    public void a(OEntityPlayer oentityplayer, boolean flag) {
        super.a(oentityplayer, flag);
        this.cp = -1;
        this.cm = -1;
        this.cn = -1;
        this.g.addAll(((OEntityPlayerMP) oentityplayer).g);
    }

    protected void a(OPotionEffect opotioneffect) {
        super.a(opotioneffect);
        this.a.b(new OPacket41EntityEffect(this.k, opotioneffect));
    }

    protected void b(OPotionEffect opotioneffect) {
        super.b(opotioneffect);
        this.a.b(new OPacket41EntityEffect(this.k, opotioneffect));
    }

    protected void c(OPotionEffect opotioneffect) {
        super.c(opotioneffect);
        this.a.b(new OPacket42RemoveEntityEffect(this.k, opotioneffect));
    }

    public void a(double d0, double d1, double d2) {
        this.a.a(d0, d1, d2, this.A, this.B);
    }

    public void b(OEntity oentity) {
        this.o().q().b(this, new OPacket18Animation(oentity, 6));
    }

    public void c(OEntity oentity) {
        this.o().q().b(this, new OPacket18Animation(oentity, 7));
    }

    public void n() {
        if (this.a != null) {
            this.a.b(new OPacket202PlayerAbilities(this.ce));
        }
    }

    public OWorldServer o() {
        return (OWorldServer) this.q;
    }

    public void a(OEnumGameType oenumgametype) {
        this.c.a(oenumgametype);
        this.a.b(new OPacket70GameEvent(3, oenumgametype.a()));
    }

    public void a(String s) {
        this.a.b(new OPacket3Chat(s));
    }

    public boolean a(int i, String s) {
        if (s.isEmpty()) { // Purely checking for permission level
            return i < 2 || i <3 && player.canIgnoreRestrictions() || player.isAdmin();
        }

        // CanaryMod: use our own permission system
        if (s.isEmpty()) {
            return false;
        } else if (s.charAt(0) != '/') {
            s = "/" + s;
        }
        return player.canUseCommand(s);
    }

    public String p() {
        String s = this.a.a.c().toString();

        s = s.substring(s.indexOf("/") + 1);
        s = s.substring(0, s.indexOf(":"));
        return s;
    }

    public void a(OPacket204ClientInfo opacket204clientinfo) {
        if (this.cl.b().containsKey(opacket204clientinfo.d())) {
            this.cl.a(opacket204clientinfo.d(), false);
        }

        int i = 256 >> opacket204clientinfo.f();

        if (i > 3 && i < 15) {
            this.cr = i;
        }

        this.cs = opacket204clientinfo.g();
        this.ct = opacket204clientinfo.h();
        if (this.b.I() && this.b.H().equals(this.bS)) {
            this.b.c(opacket204clientinfo.i());
        }

        this.b(1, !opacket204clientinfo.j());
    }

    public OStringTranslate r() {
        return this.cl;
    }

    public int t() {
        return this.cs;
    }

    public void a(String s, int i) {
        String s1 = s + "\u0000" + i;

        this.a.b(new OPacket250CustomPayload("MC|TPack", s1.getBytes()));
    }

    public OChunkCoordinates b() {
        return new OChunkCoordinates(OMathHelper.c(this.u), OMathHelper.c(this.v + 0.5D), OMathHelper.c(this.w));
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public Player getEntity() {
        return player;
    }

    /**
     * Reloads the player
     */
    public void reloadPlayer() {
        player = etc.getDataSource().getPlayer(this.bS);
        player.setUser(this);
    }

    // CanaryMod start
    @Override
    public void setDisplayName(String name) {
        super.setDisplayName(name);
        OPacket20NamedEntitySpawn pkt = new OPacket20NamedEntitySpawn(this);
        for(Player p : etc.getServer().getPlayerList()) { // could be improved to only send to nearby players
            if(!p.equals(this.player)) {
                p.getEntity().a.b(pkt);
            }
        }
    }

    public void updateSlot(int windowId, int slotIndex, OItemStack item) {
        this.a.b(new OPacket103SetSlot(windowId, slotIndex, item));
    }

    public boolean getColorEnabled() {
        return this.ct;
    }

    public int getViewDistance() {
        return this.cr;
    }
    // CanaryMod end
}
