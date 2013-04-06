import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class OEntityPlayer extends OEntityLiving implements OICommandSender {

    public OInventoryPlayer bK = new OInventoryPlayer(this);
    private OInventoryEnderChest a = new OInventoryEnderChest();
    public OContainer bL;
    public OContainer bM;
    protected OFoodStats bN = new OFoodStats(this); // CanaryMod: pass 'this'
    protected int bO = 0;
    public byte bP = 0;
    public float bQ;
    public float bR;
    public String bS;
    public int bT = 0;
    public double bU;
    public double bV;
    public double bW;
    public double bX;
    public double bY;
    public double bZ;
    protected boolean ca;
    public OChunkCoordinates cb;
    private int b;
    public float cc;
    public float cd;
    private OChunkCoordinates c;
    private boolean d;
    private OChunkCoordinates e;
    public OPlayerCapabilities ce = new OPlayerCapabilities();
    public int cf;
    public int cg;
    public float ch;
    private OItemStack f;
    private int g;
    protected float ci = 0.1F;
    protected float cj = 0.02F;
    private int h = 0;
    public OEntityFishHook ck = null;

    // CanaryMod start
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    HumanEntity entity = new HumanEntity(this);
    private String displayName; // CanaryMod: custom display names
    // CanaryMod end

    public OEntityPlayer(OWorld oworld) {
        super(oworld);
        this.bL = new OContainerPlayer(this.bK, !oworld.I, this);
        this.bM = this.bL;
        this.N = 1.62F;
        OChunkCoordinates ochunkcoordinates = oworld.I();

        this.b((double) ochunkcoordinates.a + 0.5D, (double) (ochunkcoordinates.b + 1), (double) ochunkcoordinates.c + 0.5D, 0.0F, 0.0F);
        this.aK = "humanoid";
        this.aJ = 180.0F;
        this.ad = 20;
        this.aH = "/mob/char.png";
    }

    public int aW() {
        //CanaryMod: set max health here, but check for uninitialized value.
        return this.maxHealth == 0 ? 20 : this.maxHealth;
    }

    protected void a() {
        super.a();
        this.ah.a(16, Byte.valueOf((byte) 0));
        this.ah.a(17, Byte.valueOf((byte) 0));
        this.ah.a(18, Integer.valueOf(0));
    }

    public boolean bV() {
        return this.f != null;
    }

    public void bX() {
        if (this.f != null) {
            this.f.b(this.q, this, this.g);
        }

        this.bY();
    }

    public void bY() {
        this.f = null;
        this.g = 0;
        if (!this.q.I) {
            this.e(false);
        }
    }

    public boolean bk() {
        return this.bV() && OItem.f[this.f.c].b_(this.f) == OEnumAction.d;
    }

    public void l_() {
        if (this.f != null) {
            OItemStack oitemstack = this.bK.h();

            if (oitemstack == this.f) {
                if (this.g <= 25 && this.g % 4 == 0) {
                    this.c(oitemstack, 5);
                }

                if (--this.g == 0 && !this.q.I) {
                    this.m();
                }
            } else {
                this.bY();
            }
        }

        if (this.bT > 0) {
            --this.bT;
        }

        if (this.bz()) {
            ++this.b;
            if (this.b > 100) {
                this.b = 100;
            }

            if (!this.q.I) {
                if (!this.i()) {
                    this.a(true, true, false);
                } else if (this.q.u()) {
                    this.a(false, true, true);
                }
            }
        } else if (this.b > 0) {
            ++this.b;
            if (this.b >= 110) {
                this.b = 0;
            }
        }

        super.l_();
        if (!this.q.I && this.bM != null && !this.bM.a(this)) {
            this.h();
            this.bM = this.bL;
        }

        if (this.ae() && this.ce.a) {
            this.A();
        }

        this.bU = this.bX;
        this.bV = this.bY;
        this.bW = this.bZ;
        double d0 = this.u - this.bX;
        double d1 = this.v - this.bY;
        double d2 = this.w - this.bZ;
        double d3 = 10.0D;

        if (d0 > d3) {
            this.bU = this.bX = this.u;
        }

        if (d2 > d3) {
            this.bW = this.bZ = this.w;
        }

        if (d1 > d3) {
            this.bV = this.bY = this.v;
        }

        if (d0 < -d3) {
            this.bU = this.bX = this.u;
        }

        if (d2 < -d3) {
            this.bW = this.bZ = this.w;
        }

        if (d1 < -d3) {
            this.bV = this.bY = this.v;
        }

        this.bX += d0 * 0.25D;
        this.bZ += d2 * 0.25D;
        this.bY += d1 * 0.25D;
        this.a(OStatList.k, 1);
        if (this.o == null) {
            this.e = null;
        }

        if (!this.q.I) {
            this.bN.a(this);
        }
    }

    public int y() {
        return this.ce.a ? 0 : 80;
    }

    public int aa() {
        return 10;
    }

    public void a(String s, float f, float f1) {
        this.q.a(this, s, f, f1);
    }

    protected void c(OItemStack oitemstack, int i) {
        if (oitemstack.o() == OEnumAction.c) {
            this.a("random.drink", 0.5F, this.q.s.nextFloat() * 0.1F + 0.9F);
        }

        if (oitemstack.o() == OEnumAction.b) {
            for (int j = 0; j < i; ++j) {
                OVec3 ovec3 = this.q.T().a(((double) this.ab.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

                ovec3.a(-this.B * 3.1415927F / 180.0F);
                ovec3.b(-this.A * 3.1415927F / 180.0F);
                OVec3 ovec31 = this.q.T().a(((double) this.ab.nextFloat() - 0.5D) * 0.3D, (double) (-this.ab.nextFloat()) * 0.6D - 0.3D, 0.6D);

                ovec31.a(-this.B * 3.1415927F / 180.0F);
                ovec31.b(-this.A * 3.1415927F / 180.0F);
                ovec31 = ovec31.c(this.u, this.v + (double) this.e(), this.w);
                this.q.a("iconcrack_" + oitemstack.b().cp, ovec31.c, ovec31.d, ovec31.e, ovec3.c, ovec3.d + 0.05D, ovec3.e);
            }

            this.a("random.eat", 0.5F + 0.5F * (float) this.ab.nextInt(2), (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
        }
    }

    protected void m() {
        if (this.f != null) {
            this.c(this.f, 16);
            int i = this.f.a;
            OItemStack oitemstack = this.f.b(this.q, this);

            if (oitemstack != this.f || oitemstack != null && oitemstack.a != i) {
                this.bK.a[this.bK.c] = oitemstack;
                if (oitemstack.a == 0) {
                    this.bK.a[this.bK.c] = null;
                }
            }

            this.bY();
        }
    }

    protected boolean bj() {
        return this.aX() <= 0 || this.bz();
    }

    protected void h() {
        this.bM = this.bL;
    }

    public void a(OEntity oentity) {
        if (this.o == oentity) {
            this.h(oentity);
            if (this.o != null) {
                this.o.n = null;
            }

            this.o = null;
        } else {
            super.a(oentity);
        }
    }

    public void T() {
        double d0 = this.u;
        double d1 = this.v;
        double d2 = this.w;
        float f = this.A;
        float f1 = this.B;

        super.T();
        this.bQ = this.bR;
        this.bR = 0.0F;
        this.k(this.u - d0, this.v - d1, this.w - d2);
        if (this.o instanceof OEntityPig) {
            this.B = f1;
            this.A = f;
            this.ay = ((OEntityPig) this.o).ay;
        }
    }

    protected void bq() {
        this.br();
    }

    public void c() {
        if (this.bO > 0) {
            --this.bO;
        }

        //CanaryMod: adjust 'healing over time' independent of monster-spawn=true/false (nice notchup!)
        PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();

        if (this.q.r == 0 && autoHeal == PluginLoader.HookResult.DEFAULT_ACTION || autoHeal == PluginLoader.HookResult.ALLOW_ACTION){
            if(this.aX() < this.aW() && this.ac % 20 * 12 == 0) {
                this.j(1);
            }
        }

        this.bK.k();
        this.bQ = this.bR;
        super.c();
        this.aO = this.ce.b();
        this.aP = this.cj;
        if (this.ah()) {
            this.aO = (float) ((double) this.aO + (double) this.ce.b() * 0.3D);
            this.aP = (float) ((double) this.aP + (double) this.cj * 0.3D);
        }

        float f = OMathHelper.a(this.x * this.x + this.z * this.z);
        float f1 = (float) Math.atan(-this.y * 0.20000000298023224D) * 15.0F;

        if (f > 0.1F) {
            f = 0.1F;
        }

        if (!this.F || this.aX() <= 0) {
            f = 0.0F;
        }

        if (this.F || this.aX() <= 0) {
            f1 = 0.0F;
        }

        this.bR += (f - this.bR) * 0.4F;
        this.bc += (f1 - this.bc) * 0.8F;
        if (this.aX() > 0) {
            List list = this.q.b((OEntity) this, this.E.b(1.0D, 0.5D, 1.0D));

            if (list != null) {
                for (int i = 0; i < list.size(); ++i) {
                    OEntity oentity = (OEntity) list.get(i);

                    if (!oentity.M) {
                        this.r(oentity);
                    }
                }
            }
        }
    }

    private void r(OEntity oentity) {
        oentity.b_(this);
    }

    public int bZ() {
        return this.ah.c(18);
    }

    public void s(int i) {
        this.ah.b(18, Integer.valueOf(i));
    }

    public void t(int i) {
        int j = this.bZ();

        this.ah.b(18, Integer.valueOf(j + i));
    }

    public void a(ODamageSource odamagesource) {
        super.a(odamagesource);
        this.a(0.2F, 0.2F);
        this.b(this.u, this.v, this.w);
        this.y = 0.10000000149011612D;
        if (this.bS.equals("Notch")) {
            this.a(new OItemStack(OItem.k, 1), true);
        }

        if (!this.q.M().b("keepInventory")) {
            this.bK.m();
        }

        if (odamagesource != null) {
            this.x = (double) (-OMathHelper.b((this.aY + this.A) * 3.1415927F / 180.0F) * 0.1F);
            this.z = (double) (-OMathHelper.a((this.aY + this.A) * 3.1415927F / 180.0F) * 0.1F);
        } else {
            this.x = this.z = 0.0D;
        }

        this.N = 0.1F;
        this.a(OStatList.y, 1);
    }

    public void c(OEntity oentity, int i) {
        this.t(i);
        Collection collection = this.cp().a(OScoreObjectiveCriteria.e);

        if (oentity instanceof OEntityPlayer) {
            this.a(OStatList.A, 1);
            collection.addAll(this.cp().a(OScoreObjectiveCriteria.d));
        } else {
            this.a(OStatList.z, 1);
        }

        Iterator iterator = collection.iterator();

        while (iterator.hasNext()) {
            OScoreObjective oscoreobjective = (OScoreObjective) iterator.next();
            OScore oscore = this.cp().a(this.am(), oscoreobjective);

            oscore.a();
        }
    }

    public OEntityItem a(boolean flag) {
        return this.a(this.bK.a(this.bK.c, flag && this.bK.h() != null ? this.bK.h().a : 1), false);
    }

    public OEntityItem c(OItemStack oitemstack) {
        return this.a(oitemstack, false);
    }

    public OEntityItem a(OItemStack oitemstack, boolean flag) {
        if (oitemstack == null) {
            return null;
        } else {
            OEntityItem oentityitem = new OEntityItem(this.q, this.u, this.v - 0.30000001192092896D + (double) this.e(), this.w, oitemstack);

            oentityitem.b = 40;
            float f = 0.1F;
            float f1;

            if (flag) {
                f1 = this.ab.nextFloat() * 0.5F;
                float f2 = this.ab.nextFloat() * 3.1415927F * 2.0F;

                oentityitem.x = (double) (-OMathHelper.a(f2) * f1);
                oentityitem.z = (double) (OMathHelper.b(f2) * f1);
                oentityitem.y = 0.20000000298023224D;
            } else {
                f = 0.3F;
                oentityitem.x = (double) (-OMathHelper.a(this.A / 180.0F * 3.1415927F) * OMathHelper.b(this.B / 180.0F * 3.1415927F) * f);
                oentityitem.z = (double) (OMathHelper.b(this.A / 180.0F * 3.1415927F) * OMathHelper.b(this.B / 180.0F * 3.1415927F) * f);
                oentityitem.y = (double) (-OMathHelper.a(this.B / 180.0F * 3.1415927F) * f + 0.1F);
                f = 0.02F;
                f1 = this.ab.nextFloat() * 3.1415927F * 2.0F;
                f *= this.ab.nextFloat();
                oentityitem.x += Math.cos((double) f1) * (double) f;
                oentityitem.y += (double) ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.1F);
                oentityitem.z += Math.sin((double) f1) * (double) f;
            }

            if (!(Boolean) manager.callHook(PluginLoader.Hook.ITEM_DROP, ((OEntityPlayerMP) this).getPlayer(), oentityitem.item)) {
                Item droppedItem = oentityitem.item.getItem();

                if (droppedItem.getAmount() < 0) {
                    droppedItem.setAmount(1);
                    droppedItem.update();
                }
                this.a(oentityitem);
                this.a(OStatList.v, 1);

                return oentityitem;
            } else { // return the item to the inventory.
                return null;
            }
        }
    }

    protected void a(OEntityItem oentityitem) {
        this.q.d((OEntity) oentityitem);
    }

    public float a(OBlock oblock, boolean flag) {
        float f = this.bK.a(oblock);

        if (f > 1.0F) {
            int i = OEnchantmentHelper.c(this);
            OItemStack oitemstack = this.bK.h();

            if (i > 0 && oitemstack != null) {
                float f1 = (float) (i * i + 1);

                if (!oitemstack.b(oblock) && f <= 1.0F) {
                    f += f1 * 0.08F;
                } else {
                    f += f1;
                }
            }
        }

        if (this.a(OPotion.e)) {
            f *= 1.0F + (float) (this.b(OPotion.e).c() + 1) * 0.2F;
        }

        if (this.a(OPotion.f)) {
            f *= 1.0F - (float) (this.b(OPotion.f).c() + 1) * 0.2F;
        }

        if (this.a(OMaterial.h) && !OEnchantmentHelper.h(this)) {
            f /= 5.0F;
        }

        if (!this.F) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean a(OBlock oblock) {
        return this.bK.b(oblock);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.m("Inventory");

        this.bK.b(onbttaglist);
        this.bK.c = onbttagcompound.e("SelectedItemSlot");
        this.ca = onbttagcompound.n("Sleeping");
        this.b = onbttagcompound.d("SleepTimer");
        this.ch = onbttagcompound.g("XpP");
        this.cf = onbttagcompound.e("XpLevel");
        this.cg = onbttagcompound.e("XpTotal");
        this.s(onbttagcompound.e("Score"));
        if (this.ca) {
            this.cb = new OChunkCoordinates(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w));
            this.a(true, true, false);
        }

        if (onbttagcompound.b("SpawnX") && onbttagcompound.b("SpawnY") && onbttagcompound.b("SpawnZ")) {
            this.c = new OChunkCoordinates(onbttagcompound.e("SpawnX"), onbttagcompound.e("SpawnY"), onbttagcompound.e("SpawnZ"));
            this.d = onbttagcompound.n("SpawnForced");
        }

        this.bN.a(onbttagcompound);
        this.ce.b(onbttagcompound);
        if (onbttagcompound.b("EnderItems")) {
            ONBTTagList onbttaglist1 = onbttagcompound.m("EnderItems");

            this.a.a(onbttaglist1);
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Inventory", (ONBTBase) this.bK.a(new ONBTTagList()));
        onbttagcompound.a("SelectedItemSlot", this.bK.c);
        onbttagcompound.a("Sleeping", this.ca);
        onbttagcompound.a("SleepTimer", (short) this.b);
        onbttagcompound.a("XpP", this.ch);
        onbttagcompound.a("XpLevel", this.cf);
        onbttagcompound.a("XpTotal", this.cg);
        onbttagcompound.a("Score", this.bZ());
        if (this.c != null) {
            onbttagcompound.a("SpawnX", this.c.a);
            onbttagcompound.a("SpawnY", this.c.b);
            onbttagcompound.a("SpawnZ", this.c.c);
            onbttagcompound.a("SpawnForced", this.d);
        }

        this.bN.b(onbttagcompound);
        this.ce.a(onbttagcompound);
        onbttagcompound.a("EnderItems", (ONBTBase) this.a.h());
    }

    public void a(OIInventory oiinventory) {}

    public void a(OTileEntityHopper otileentityhopper) {}

    public void a(OEntityMinecartHopper oentityminecarthopper) {}

    public void a(int i, int j, int k, String s) {}

    public void c(int i, int j, int k) {}

    public void b(int i, int j, int k) {}

    public float e() {
        return 0.12F;
    }

    protected void e_() {
        this.N = 1.62F;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.aq()) {
            return false;
        } else if (this.ce.a && !odamagesource.g()) {
            return false;
        } else {
            this.bC = 0;
            if (this.aX() <= 0) {
                return false;
            } else {
                if (this.bz() && !this.q.I) {
                    this.a(true, true, false);
                }

                if (odamagesource.p()) {
                    if (this.q.r == 0) {
                        i = 0;
                    }

                    if (this.q.r == 1) {
                        i = i / 2 + 1;
                    }

                    if (this.q.r == 3) {
                        i = i * 3 / 2;
                    }
                }

                if (i == 0) {
                    return false;
                } else {
                    OEntity oentity = odamagesource.i();

                    if (oentity instanceof OEntityArrow && ((OEntityArrow) oentity).c != null) {
                        oentity = ((OEntityArrow) oentity).c;
                    }

                    if (oentity instanceof OEntityLiving) {
                        this.a((OEntityLiving) oentity, false);
                    }

                    this.a(OStatList.x, i);
                    return super.a(odamagesource, i);
                }
            }
        }
    }

    public boolean a(OEntityPlayer oentityplayer) {
        OScorePlayerTeam oscoreplayerteam = this.cq();
        OScorePlayerTeam oscoreplayerteam1 = oentityplayer.cq();

        return oscoreplayerteam != oscoreplayerteam1 ? true : (oscoreplayerteam != null ? oscoreplayerteam.g() : true);
    }

    protected void a(OEntityLiving oentityliving, boolean flag) {
        if (!(oentityliving instanceof OEntityCreeper) && !(oentityliving instanceof OEntityGhast)) {
            if (oentityliving instanceof OEntityWolf) {
                OEntityWolf oentitywolf = (OEntityWolf) oentityliving;

                if (oentitywolf.m() && this.bS.equals(oentitywolf.o())) {
                    return;
                }
            }

            if (!(oentityliving instanceof OEntityPlayer) || this.a((OEntityPlayer) oentityliving)) {
                List list = this.q.a(OEntityWolf.class, OAxisAlignedBB.a().a(this.u, this.v, this.w, this.u + 1.0D, this.v + 1.0D, this.w + 1.0D).b(16.0D, 4.0D, 16.0D));
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    OEntityWolf oentitywolf1 = (OEntityWolf) iterator.next();

                    if (oentitywolf1.m() && oentitywolf1.l() == null && this.bS.equals(oentitywolf1.o()) && (!flag || !oentitywolf1.n())) {
                        oentitywolf1.k(false);
                        oentitywolf1.b((OEntity) oentityliving);
                    }
                }
            }
        }
    }

    protected void k(int i) {
        this.bK.g(i);
    }

    public int aZ() {
        return this.bK.l();
    }

    public float ca() {
        int i = 0;
        OItemStack[] aoitemstack = this.bK.b;
        int j = aoitemstack.length;

        for (int k = 0; k < j; ++k) {
            OItemStack oitemstack = aoitemstack[k];

            if (oitemstack != null) {
                ++i;
            }
        }

        return (float) i / (float) this.bK.b.length;
    }

    protected void d(ODamageSource odamagesource, int i) {
        if (!this.aq()) {
            if (!odamagesource.e() && this.bk()) {
                i = 1 + i >> 1;
            }

            i = this.b(odamagesource, i);
            i = this.c(odamagesource, i);
            this.j(odamagesource.f());
            int j = this.aX();

            this.b(this.aX() - i);
            this.bt.a(odamagesource, j, i);
        }
    }

    public void a(OTileEntityFurnace otileentityfurnace) {}

    public void a(OTileEntityDispenser otileentitydispenser) {}

    public void a(OTileEntity otileentity) {}

    public void a(OTileEntityBrewingStand otileentitybrewingstand) {}

    public void a(OTileEntityBeacon otileentitybeacon) {}

    public void a(OIMerchant oimerchant, String s) {}

    public void d(OItemStack oitemstack) {}

    public boolean p(OEntity oentity) {
        if (oentity.a_(this)) {
            return true;
        } else {
            OItemStack oitemstack = this.cb();
            PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.ENTITY_RIGHTCLICKED, ((OEntityPlayerMP) this).getPlayer(), oentity.entity, (oitemstack == null) ? null : new Item(oitemstack));


            if (res == PluginLoader.HookResult.ALLOW_ACTION) {
                return true;
            } else if (res == PluginLoader.HookResult.DEFAULT_ACTION) {
                // Normally when interact action is not defined on the interacted entity, false is returned, and the item stack is not used.
                // For example sheep can interact by shearing and cows by milking, and the item stack changes from this interaction if it returns true.
                // Players on the other hand won't interact normally, but if we want to update the item stack anyways, we will ALLOW the action.
                if (oitemstack != null && oentity instanceof OEntityLiving) {
                if (this.ce.d) {
                    oitemstack = oitemstack.m();
                    }

                    if (oitemstack.a((OEntityLiving) oentity)) {
                    if (oitemstack.a <= 0 && !this.ce.d) {
                        this.cc();
                        }

                        return true;
                    }
                }
            }

            return false;
        }
    }

    public OItemStack cb() {
        return this.bK.h();
    }

    public void cc() {
        this.bK.a(this.bK.c, (OItemStack) null);
    }

    public double V() {
        return (double) (this.N - 0.5F);
    }

    public void q(OEntity oentity) {
        if (oentity.ap()) {
            if (!oentity.j(this)) {
                int i = this.bK.a(oentity);

                if (this.a(OPotion.g)) {
                    i += 3 << this.b(OPotion.g).c();
                }

                if (this.a(OPotion.t)) {
                    i -= 2 << this.b(OPotion.t).c();
                }

                int j = 0;
                int k = 0;

                if (oentity instanceof OEntityLiving) {
                    k = OEnchantmentHelper.a((OEntityLiving) this, (OEntityLiving) oentity);
                    j += OEnchantmentHelper.b(this, (OEntityLiving) oentity);
                }

                if (this.ah()) {
                    ++j;
                }

                if (i > 0 || k > 0) {
                    boolean flag = this.T > 0.0F && !this.F && !this.g_() && !this.G() && !this.a(OPotion.q) && this.o == null && oentity instanceof OEntityLiving;

                    if (flag && i > 0) {
                        i += this.ab.nextInt(i / 2 + 2);
                    }

                    i += k;
                    boolean flag1 = false;
                    int l = OEnchantmentHelper.a((OEntityLiving) this);

                    if (oentity instanceof OEntityLiving && l > 0 && !oentity.ae()) {
                        flag1 = true;
                        oentity.d(1);
                    }

                    boolean flag2 = oentity.a(ODamageSource.a(this), i);

                    if (flag2) {
                        if (j > 0) {
                            oentity.g((double) (-OMathHelper.a(this.A * 3.1415927F / 180.0F) * (float) j * 0.5F), 0.1D, (double) (OMathHelper.b(this.A * 3.1415927F / 180.0F) * (float) j * 0.5F));
                            this.x *= 0.6D;
                            this.z *= 0.6D;
                            this.c(false);
                        }

                        if (flag) {
                            this.b(oentity);
                        }

                        if (k > 0) {
                            this.c(oentity);
                        }

                        if (i >= 18) {
                            this.a((OStatBase) OAchievementList.E);
                        }

                        this.l(oentity);
                        if (oentity instanceof OEntityLiving) {
                            OEnchantmentThorns.a(this, (OEntityLiving) oentity, this.ab);
                        }
                    }

                    OItemStack oitemstack = this.cb();
                    Object object = oentity;

                    if (oentity instanceof OEntityDragonPart) {
                        OIEntityMultiPart oientitymultipart = ((OEntityDragonPart) oentity).a;

                        if (oientitymultipart != null && oientitymultipart instanceof OEntityLiving) {
                            object = (OEntityLiving) oientitymultipart;
                        }
                    }

                    if (oitemstack != null && object instanceof OEntityLiving) {
                        oitemstack.a((OEntityLiving) object, this);
                        if (oitemstack.a <= 0) {
                            this.cc();
                        }
                    }

                    if (oentity instanceof OEntityLiving) {
                        if (oentity.R()) {
                            this.a((OEntityLiving) oentity, true);
                        }

                        this.a(OStatList.w, i);
                        if (l > 0 && flag2) {
                            oentity.d(l * 4);
                        } else if (flag1) {
                            oentity.A();
                        }
                    }

                    this.j(0.3F);
                }
            }
        }
    }

    public void b(OEntity oentity) {}

    public void c(OEntity oentity) {}

    public void w() {
        super.w();
        this.bL.b(this);
        if (this.bM != null) {
            this.bM.b(this);
        }
    }

    public boolean S() {
        return !this.ca && super.S();
    }

    public boolean ce() {
        return false;
    }

    public OEnumStatus a(int i, int j, int k) {
        if (!this.q.I) {
            if (this.bz() || !this.R()) {
                return OEnumStatus.e;
            }

            if (!this.q.t.d()) {
                return OEnumStatus.b;
            }

            if (this.q.u()) {
                return OEnumStatus.c;
            }

            if (Math.abs(this.u - (double) i) > 3.0D || Math.abs(this.v - (double) j) > 2.0D || Math.abs(this.w - (double) k) > 3.0D) {
                return OEnumStatus.d;
            }

            double d0 = 8.0D;
            double d1 = 5.0D;
            List list = this.q.a(OEntityMob.class, OAxisAlignedBB.a().a((double) i - d0, (double) j - d1, (double) k - d0, (double) i + d0, (double) j + d1, (double) k + d0));

            if (!list.isEmpty()) {
                return OEnumStatus.f;
            }
        }

        this.a(0.2F, 0.2F);
        this.N = 0.2F;
        if (this.q.f(i, j, k)) {
            int l = this.q.h(i, j, k);
            int i1 = OBlockBed.j(l);
            float f = 0.5F;
            float f1 = 0.5F;

            switch (i1) {
                case 0:
                    f1 = 0.9F;
                    break;

                case 1:
                    f = 0.1F;
                    break;

                case 2:
                    f1 = 0.1F;
                    break;

                case 3:
                    f = 0.9F;
            }

            this.x(i1);
            this.b((double) ((float) i + f), (double) ((float) j + 0.9375F), (double) ((float) k + f1));
        } else {
            this.b((double) ((float) i + 0.5F), (double) ((float) j + 0.9375F), (double) ((float) k + 0.5F));
        }

        this.ca = true;
        this.b = 0;
        this.cb = new OChunkCoordinates(i, j, k);
        this.x = this.z = this.y = 0.0D;
        if (!this.q.I) {
            this.q.c();
        }

        return OEnumStatus.a;
    }

    private void x(int i) {
        this.cc = 0.0F;
        this.cd = 0.0F;
        switch (i) {
            case 0:
                this.cd = -1.8F;
                break;

            case 1:
                this.cc = 1.8F;
                break;

            case 2:
                this.cd = 1.8F;
                break;

            case 3:
                this.cc = -1.8F;
        }
    }

    public void a(boolean flag, boolean flag1, boolean flag2) {
        this.a(0.6F, 1.8F);
        this.e_();
        OChunkCoordinates ochunkcoordinates = this.cb;
        OChunkCoordinates ochunkcoordinates1 = this.cb;

        if (ochunkcoordinates != null && this.q.a(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c) == OBlock.W.cz) {
            OBlockBed.a(this.q, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, false);
            ochunkcoordinates1 = OBlockBed.b(this.q, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, 0);
            if (ochunkcoordinates1 == null) {
                ochunkcoordinates1 = new OChunkCoordinates(ochunkcoordinates.a, ochunkcoordinates.b + 1, ochunkcoordinates.c);
            }

            this.b((double) ((float) ochunkcoordinates1.a + 0.5F), (double) ((float) ochunkcoordinates1.b + this.N + 0.1F), (double) ((float) ochunkcoordinates1.c + 0.5F));
        }

        this.ca = false;
        if (!this.q.I && flag1) {
            this.q.c();
        }

        if (flag) {
            this.b = 0;
        } else {
            this.b = 100;
        }

        if (flag2) {
            this.a(this.cb, false);
        }
    }

    private boolean i() {
        return this.q.a(this.cb.a, this.cb.b, this.cb.c) == OBlock.W.cz;
    }

    public static OChunkCoordinates a(OWorld oworld, OChunkCoordinates ochunkcoordinates, boolean flag) {
        OIChunkProvider oichunkprovider = oworld.J();

        oichunkprovider.c(ochunkcoordinates.a - 3 >> 4, ochunkcoordinates.c - 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a + 3 >> 4, ochunkcoordinates.c - 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a - 3 >> 4, ochunkcoordinates.c + 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a + 3 >> 4, ochunkcoordinates.c + 3 >> 4);
        if (oworld.a(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c) == OBlock.W.cz) {
            OChunkCoordinates ochunkcoordinates1 = OBlockBed.b(oworld, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, 0);

            return ochunkcoordinates1;
        } else {
            OMaterial omaterial = oworld.g(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c);
            OMaterial omaterial1 = oworld.g(ochunkcoordinates.a, ochunkcoordinates.b + 1, ochunkcoordinates.c);
            boolean flag1 = !omaterial.a() && !omaterial.d();
            boolean flag2 = !omaterial1.a() && !omaterial1.d();

            return flag && flag1 && flag2 ? ochunkcoordinates : null;
        }
    }

    public boolean bz() {
        return this.ca;
    }

    public boolean cg() {
        return this.ca && this.b >= 100;
    }

    protected void b(int i, boolean flag) {
        byte b0 = this.ah.a(16);

        if (flag) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 1 << i)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & ~(1 << i))));
        }
    }

    public void b(String s) {}

    public OChunkCoordinates ci() {
        return this.c;
    }

    public boolean cj() {
        return this.d;
    }

    public void a(OChunkCoordinates ochunkcoordinates, boolean flag) {
        if (ochunkcoordinates != null) {
            this.c = new OChunkCoordinates(ochunkcoordinates);
            this.d = flag;
        } else {
            this.c = null;
            this.d = false;
        }
    }

    public void a(OStatBase ostatbase) {
        this.a(ostatbase, 1);
    }

    public void a(OStatBase ostatbase, int i) {}

    protected void bl() {
        super.bl();
        this.a(OStatList.u, 1);
        if (this.ah()) {
            this.j(0.8F);
        } else {
            this.j(0.2F);
        }
    }

    public void e(float f, float f1) {
        double d0 = this.u;
        double d1 = this.v;
        double d2 = this.w;

        if (this.ce.b && this.o == null) {
            double d3 = this.y;
            float f2 = this.aP;

            this.aP = this.ce.a();
            super.e(f, f1);
            this.y = d3 * 0.6D;
            this.aP = f2;
        } else {
            super.e(f, f1);
        }

        this.j(this.u - d0, this.v - d1, this.w - d2);
    }

    public void j(double d0, double d1, double d2) {
        if (this.o == null) {
            int i;

            if (this.a(OMaterial.h)) {
                i = Math.round(OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);
                if (i > 0) {
                    this.a(OStatList.q, i);
                    this.j(0.015F * (float) i * 0.01F);
                }
            } else if (this.G()) {
                i = Math.round(OMathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i > 0) {
                    this.a(OStatList.m, i);
                    this.j(0.015F * (float) i * 0.01F);
                }
            } else if (this.g_()) {
                if (d1 > 0.0D) {
                    this.a(OStatList.o, (int) Math.round(d1 * 100.0D));
                }
            } else if (this.F) {
                i = Math.round(OMathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i > 0) {
                    this.a(OStatList.l, i);
                    if (this.ah()) {
                        this.j(0.099999994F * (float) i * 0.01F);
                    } else {
                        this.j(0.01F * (float) i * 0.01F);
                    }
                }
            } else {
                i = Math.round(OMathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i > 25) {
                    this.a(OStatList.p, i);
                }
            }
        }
    }

    private void k(double d0, double d1, double d2) {
        if (this.o != null) {
            int i = Math.round(OMathHelper.a(d0 * d0 + d1 * d1 + d2 * d2) * 100.0F);

            if (i > 0) {
                if (this.o instanceof OEntityMinecart) {
                    this.a(OStatList.r, i);
                    if (this.e == null) {
                        this.e = new OChunkCoordinates(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w));
                    } else if ((double) this.e.e(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w)) >= 1000000.0D) {
                        this.a((OStatBase) OAchievementList.q, 1);
                    }
                } else if (this.o instanceof OEntityBoat) {
                    this.a(OStatList.s, i);
                } else if (this.o instanceof OEntityPig) {
                    this.a(OStatList.t, i);
                }
            }
        }
    }

    protected void a(float f) {
        if (!this.ce.c) {
            if (f >= 2.0F) {
                this.a(OStatList.n, (int) Math.round((double) f * 100.0D));
            }

            super.a(f);
        }
    }

    public void a(OEntityLiving oentityliving) {
        if (oentityliving instanceof OIMob) {
            this.a((OStatBase) OAchievementList.s);
        }
    }

    public void al() {
        if (!this.ce.b) {
            super.al();
        }
    }

    public OItemStack q(int i) {
        return this.bK.f(i);
    }

    protected void bH() {}

    protected void bI() {}

    public void w(int i) {
        this.t(i);
        int j = Integer.MAX_VALUE - this.cg;

        if (i > j) {
            i = j;
        }

        this.ch += (float) i / (float) this.ck();

        for (this.cg += i; this.ch >= 1.0F; this.ch /= (float) this.ck()) {
            this.ch = (this.ch - 1.0F) * (float) this.ck();
            this.a(1);
        }
    }

    // CanaryMod start - custom XP methods
    public void removeXP(int i) {
        if (i > this.cg) { // Don't go below 0
            i = this.cg;
        }
        this.cg -= i;
        this.recalculateXP();
    }

    public void setXP(int i) {
        if (i < this.cf) {
            this.removeXP(this.cf - i);
        } else {
            this.t(i - this.cf);
        }
    }

    public void recalculateXP() {
        this.ch = this.cg / (float) this.ck();
        this.cf = 0;

        while (this.ch >= 1.0F) {
            this.ch = (this.ch - 1.0F) * this.ck();
            this.cf++;
            this.ch /= this.ck();
        }

        if (this instanceof OEntityPlayerMP) {
            ((OEntityPlayerMP) this).getEntity().updateLevels();
        }
    } // CanaryMod end - custom XP methods

    public void a(int i) {
        manager.callHook(PluginLoader.Hook.LEVEL_UP, ((OEntityPlayerMP) this).getPlayer());
        this.cf += i;
        if (this.cf < 0) {
            this.cf = 0;
            this.ch = 0.0F;
            this.cg = 0;
        }

        if (i > 0 && this.cf % 5 == 0 && (float) this.h < (float) this.ac - 100.0F) {
            float f = this.cf > 30 ? 1.0F : (float) this.cf / 30.0F;

            this.q.a((OEntity) this, "random.levelup", f * 0.75F, 1.0F);
            this.h = this.ac;
        }
    }

    public int ck() {
        // CanaryMod: Old experience option
        if(etc.getInstance().isOldExperience()) {
            return 7 + (this.cf * 7 >> 1);
        } // CanaryMod: End
        return this.cf >= 30 ? 62 + (this.cf - 30) * 7 : (this.cf >= 15 ? 17 + (this.cf - 15) * 3 : 17);
    }

    public void j(float f) {
        if (!this.ce.a) {
            if (!this.q.I) {
                this.bN.a(f);
            }
        }
    }

    public OFoodStats cl() {
        return this.bN;
    }

    public boolean i(boolean flag) {
        return (flag || this.bN.c()) && !this.ce.a;
    }

    public boolean cm() {
        return this.aX() > 0 && this.aX() < this.aW();
    }

    public void a(OItemStack oitemstack, int i) {
        if (oitemstack != this.f) {
            this.f = oitemstack;
            this.g = i;
            if (!this.q.I) {
                this.e(true);
            }
        }
    }

    public boolean e(int i, int j, int k) {
        if (this.ce.e) {
            return true;
        } else {
            int l = this.q.a(i, j, k);

            if (l > 0) {
                OBlock oblock = OBlock.r[l];

                if (oblock.cO.q()) {
                    return true;
                }

                if (this.cb() != null) {
                    OItemStack oitemstack = this.cb();

                    if (oitemstack.b(oblock) || oitemstack.a(oblock) > 1.0F) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

    public boolean a(int i, int j, int k, int l, OItemStack oitemstack) {
        return this.ce.e ? true : (oitemstack != null ? oitemstack.y() : false);
    }

    protected int d(OEntityPlayer oentityplayer) {
        if (this.q.M().b("keepInventory")) {
            return 0;
        } else {
            int i = this.cf * 7;

            return i > 100 ? 100 : i;
        }
    }

    protected boolean aT() {
        return true;
    }

    public String am() {
        return this.bS;
    }

    public boolean bQ() {
        return super.bQ();
    }

    public boolean bS() {
        return false;
    }

    public void a(OEntityPlayer oentityplayer, boolean flag) {
        if (flag) {
            this.bK.b(oentityplayer.bK);
            this.aS = oentityplayer.aS;
            this.bN = oentityplayer.bN;
            this.cf = oentityplayer.cf;
            this.cg = oentityplayer.cg;
            this.ch = oentityplayer.ch;
            this.s(oentityplayer.bZ());
            this.as = oentityplayer.as;
        } else if (this.q.M().b("keepInventory")) {
            this.bK.b(oentityplayer.bK);
            this.cf = oentityplayer.cf;
            this.cg = oentityplayer.cg;
            this.ch = oentityplayer.ch;
            this.s(oentityplayer.bZ());
        }

        this.a = oentityplayer.a;
    }

    protected boolean f_() {
        return !this.ce.b;
    }

    public void n() {}

    public void a(OEnumGameType oenumgametype) {}

    public String c_() {
        return this.bS;
    }

    public OStringTranslate r() {
        return OStringTranslate.a();
    }

    public String a(String s, Object... aobject) {
        return this.r().a(s, aobject);
    }

    public OInventoryEnderChest cn() {
        return this.a;
    }

    public OItemStack p(int i) {
        return i == 0 ? this.bK.h() : this.bK.b[i - 1];
    }

    public OItemStack bG() {
        return this.bK.h();
    }

    public void c(int i, OItemStack oitemstack) {
        this.bK.b[i] = oitemstack;
    }

    public OItemStack[] ad() {
        return this.bK.b;
    }

    public boolean aw() {
        return !this.ce.b;
    }

    public OScoreboard cp() {
        return this.q.V();
    }

    public OScorePlayerTeam cq() {
        return this.cp().i(this.bS);
    }

    public String ax() {
        return OScorePlayerTeam.a(this.cq(), this.bS);

    }

    @Override
    public HumanEntity getEntity() {
        return entity;
    } //

    // CanaryMod start
    public String getDisplayName() {
        return displayName == null ? this.bS : displayName;
    }

    public void setDisplayName(String name) {
        this.displayName = name;
    }
    // CanaryMod end
}
