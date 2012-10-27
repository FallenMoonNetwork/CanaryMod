import java.util.Iterator;
import java.util.List;

public abstract class OEntityPlayer extends OEntityLiving implements OICommandSender {

    public OInventoryPlayer bK = new OInventoryPlayer(this);
    private OInventoryEnderChest a = new OInventoryEnderChest();

    public OContainer bL;
    public OContainer bM;
    protected OFoodStats bN = new OFoodStats();
    protected int bO = 0;
    public byte bP = 0;
    public int bQ = 0;
    public float bR;
    public float bS;
    public String bT;
    public int bU = 0;
    public double bV;
    public double bW;
    public double bX;
    public double bY;
    public double bZ;
    public double ca;
    protected boolean cb;
    public OChunkCoordinates cc;
    private int b;
    public float cd;
    public float ce;
    private OChunkCoordinates c;
    private boolean d;
    private OChunkCoordinates e;
    public OPlayerCapabilities cf = new OPlayerCapabilities();
    public int cg;
    public int ch;
    public float ci;
    private OItemStack f;
    private int g;
    protected float cj = 0.1F;
    protected float ck = 0.02F;
    private int h = 0;
    public OEntityFishHook cl = null;
// CanaryMod start
    @SuppressWarnings("FieldNameHidesFieldInSuperclass")
    HumanEntity entity = new HumanEntity(this);
    // CanaryMod end

    public OEntityPlayer(OWorld oworld) {
        super(oworld);
        this.bL = new OContainerPlayer(this.bK, !oworld.J, this);
        this.bM = this.bL;
        this.M = 1.62F;
        OChunkCoordinates ochunkcoordinates = oworld.G();

        this.b((double) ochunkcoordinates.a + 0.5D, (double) (ochunkcoordinates.b + 1), (double) ochunkcoordinates.c + 0.5D, 0.0F, 0.0F);
        this.aI = "humanoid";
        this.aH = 180.0F;
        this.ac = 20;
        this.aF = "/mob/char.png";
    }

    public int aS() {
        return 20;
    }

    protected void a() {
        super.a();
        this.ag.a(16, Byte.valueOf((byte) 0));
        this.ag.a(17, Byte.valueOf((byte) 0));
    }

    public boolean bI() {
        return this.f != null;
    }

    public void bK() {
        if (this.f != null) {
            this.f.b(this.p, this, this.g);
        }

        this.bL();
    }

    public void bL() {
        this.f = null;
        this.g = 0;
        if (!this.p.J) {
            this.d(false);
        }

    }

    public boolean be() {
        return this.bI() && OItem.e[this.f.c].d_(this.f) == OEnumAction.d;
    }

    public void j_() {
        if (this.f != null) {
            OItemStack oitemstack = this.bK.g();

            if (oitemstack == this.f) {
                if (this.g <= 25 && this.g % 4 == 0) {
                    this.c(oitemstack, 5);
                }

                if (--this.g == 0 && !this.p.J) {
                    this.n();
                }
            } else {
                this.bL();
            }
        }

        if (this.bU > 0) {
            --this.bU;
        }

        if (this.bt()) {
            ++this.b;
            if (this.b > 100) {
                this.b = 100;
            }

            if (!this.p.J) {
                if (!this.j()) {
                    this.a(true, true, false);
                } else if (this.p.t()) {
                    this.a(false, true, true);
                }
            }
        } else if (this.b > 0) {
            ++this.b;
            if (this.b >= 110) {
                this.b = 0;
            }
        }

        super.j_();
        if (!this.p.J && this.bM != null && !this.bM.c(this)) {
            this.i();
            this.bM = this.bL;
        }

        if (this.af() && this.cf.a) {
            this.B();
        }

        this.bV = this.bY;
        this.bW = this.bZ;
        this.bX = this.ca;
        double d0 = this.t - this.bY;
        double d1 = this.u - this.bZ;
        double d2 = this.v - this.ca;
        double d3 = 10.0D;

        if (d0 > d3) {
            this.bV = this.bY = this.t;
        }

        if (d2 > d3) {
            this.bX = this.ca = this.v;
        }

        if (d1 > d3) {
            this.bW = this.bZ = this.u;
        }

        if (d0 < -d3) {
            this.bV = this.bY = this.t;
        }

        if (d2 < -d3) {
            this.bX = this.ca = this.v;
        }

        if (d1 < -d3) {
            this.bW = this.bZ = this.u;
        }

        this.bY += d0 * 0.25D;
        this.ca += d2 * 0.25D;
        this.bZ += d1 * 0.25D;
        this.a(OStatList.k, 1);
        if (this.o == null) {
            this.e = null;
        }

        if (!this.p.J) {
            this.bN.a(this);
        }

    }

    public int z() {
        return this.cf.a ? 0 : 80;
    }

    public int ab() {
        return 10;
    }

    protected void c(OItemStack oitemstack, int i) {
        if (oitemstack.n() == OEnumAction.c) {
            this.p.a(this, "random.drink", 0.5F, this.p.u.nextFloat() * 0.1F + 0.9F);
        }

        if (oitemstack.n() == OEnumAction.b) {
            for (int j = 0; j < i; ++j) {
                OVec3 ovec3 = this.p.R().a(((double) this.aa.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);

                ovec3.a(-this.A * 3.1415927F / 180.0F);
                ovec3.b(-this.z * 3.1415927F / 180.0F);
                OVec3 ovec31 = this.p.R().a(((double) this.aa.nextFloat() - 0.5D) * 0.3D, (double) (-this.aa.nextFloat()) * 0.6D - 0.3D, 0.6D);

                ovec31.a(-this.A * 3.1415927F / 180.0F);
                ovec31.b(-this.z * 3.1415927F / 180.0F);
                ovec31 = ovec31.c(this.t, this.u + (double) this.e(), this.v);
                this.p.a("iconcrack_" + oitemstack.b().cf, ovec31.c, ovec31.d, ovec31.e, ovec3.c, ovec3.d + 0.05D, ovec3.e);
            }

            this.p.a(this, "random.eat", 0.5F + 0.5F * (float) this.aa.nextInt(2), (this.aa.nextFloat() - this.aa.nextFloat()) * 0.2F + 1.0F);
        }

    }

    protected void n() {
        if (this.f != null) {
            this.c(this.f, 16);
            int i = this.f.a;
            OItemStack oitemstack = this.f.b(this.p, this);

            if (oitemstack != this.f || oitemstack != null && oitemstack.a != i) {
                this.bK.a[this.bK.c] = oitemstack;
                if (oitemstack.a == 0) {
                    this.bK.a[this.bK.c] = null;
                }
            }

            this.bL();
        }

    }

    protected boolean bd() {
        return this.aT() <= 0 || this.bt();
    }

    protected void i() {
        this.bM = this.bL;
    }

    public void U() {
        double d0 = this.t;
        double d1 = this.u;
        double d2 = this.v;
        float f = this.z;
        float f1 = this.A;

        super.U();
        this.bR = this.bS;
        this.bS = 0.0F;
        this.k(this.t - d0, this.u - d1, this.v - d2);
        if (this.o instanceof OEntityPig) {
            this.A = f1;
            this.z = f;
            this.aw = ((OEntityPig) this.o).aw;
    }
        }

    protected void bk() {
        this.bl();
    }

    public void c() {
        if (this.bO > 0) {
            --this.bO;
        }

        // CanaryMod: adjust 'healing over time' independent of monster-spawn=true/false (nice notchup!)
        PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();

        if (this.p.t == 0 && autoHeal == PluginLoader.HookResult.DEFAULT_ACTION || autoHeal == PluginLoader.HookResult.ALLOW_ACTION) {
            if (this.aT() < this.aS() && this.ab % 20 * 12 == 0) {
                this.i(1);
            }
        }

        this.bK.j();
        this.bR = this.bS;
        super.c();
        this.aM = this.cf.b();
        this.aN = this.ck;
        if (this.ai()) {
            this.aM = (float) ((double) this.aM + (double) this.cf.b() * 0.3D);
            this.aN = (float) ((double) this.aN + (double) this.ck * 0.3D);
        }

        float f = OMathHelper.a(this.w * this.w + this.y * this.y);
        float f1 = (float) Math.atan(-this.x * 0.20000000298023224D) * 15.0F;

        if (f > 0.1F) {
            f = 0.1F;
        }

        if (!this.E || this.aT() <= 0) {
            f = 0.0F;
        }

        if (this.E || this.aT() <= 0) {
            f1 = 0.0F;
        }

        this.bS += (f - this.bS) * 0.4F;
        this.ba += (f1 - this.ba) * 0.8F;
        if (this.aT() > 0) {
            List list = this.p.b((OEntity) this, this.D.b(1.0D, 0.0D, 1.0D));

            if (list != null) {
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    OEntity oentity = (OEntity) iterator.next();

                    if (!oentity.L) {
                        this.q(oentity);
                    }
                }
            }
        }

    }

    private void q(OEntity oentity) {
        oentity.b_(this);
    }

    public void a(ODamageSource odamagesource) {
        super.a(odamagesource);
        this.a(0.2F, 0.2F);
        this.b(this.t, this.u, this.v);
        this.x = 0.10000000149011612D;
        if (this.bT.equals("Notch")) {
            this.a(new OItemStack(OItem.j, 1), true);
        }

        if (!this.p.K().b("keepInventory")) {
            this.bK.l();
        }

        if (odamagesource != null) {
            this.w = (double) (-OMathHelper.b((this.aW + this.z) * 3.1415927F / 180.0F) * 0.1F);
            this.y = (double) (-OMathHelper.a((this.aW + this.z) * 3.1415927F / 180.0F) * 0.1F);
        } else {
            this.w = this.y = 0.0D;
        }

        this.M = 0.1F;
        this.a(OStatList.y, 1);
    }

    public void c(OEntity oentity, int i) {
        this.bQ += i;
        if (oentity instanceof OEntityPlayer) {
            this.a(OStatList.A, 1);
        } else {
            this.a(OStatList.z, 1);
        }

    }

    public OEntityItem bN() {
        return this.a(this.bK.a(this.bK.c, 1), false);
    }

    public OEntityItem c(OItemStack oitemstack) {
        return this.a(oitemstack, false);
    }

    public OEntityItem a(OItemStack oitemstack, boolean flag) {
        if (oitemstack == null) {
            return null;
        } else {
            OEntityItem oentityitem = new OEntityItem(this.p, this.t, this.u - 0.30000001192092896D + (double) this.e(), this.v, oitemstack);

            oentityitem.c = 40;
            float f = 0.1F;
            float f1;

            if (flag) {
                f1 = this.aa.nextFloat() * 0.5F;
                float f2 = this.aa.nextFloat() * 3.1415927F * 2.0F;

                oentityitem.w = (double) (-OMathHelper.a(f2) * f1);
                oentityitem.y = (double) (OMathHelper.b(f2) * f1);
                oentityitem.x = 0.20000000298023224D;
            } else {
                f = 0.3F;
                oentityitem.w = (double) (-OMathHelper.a(this.z / 180.0F * 3.1415927F) * OMathHelper.b(this.A / 180.0F * 3.1415927F) * f);
                oentityitem.y = (double) (OMathHelper.b(this.z / 180.0F * 3.1415927F) * OMathHelper.b(this.A / 180.0F * 3.1415927F) * f);
                oentityitem.x = (double) (-OMathHelper.a(this.A / 180.0F * 3.1415927F) * f + 0.1F);
                f = 0.02F;
                f1 = this.aa.nextFloat() * 3.1415927F * 2.0F;
                f *= this.aa.nextFloat();
                oentityitem.w += Math.cos((double) f1) * (double) f;
                oentityitem.x += (double) ((this.aa.nextFloat() - this.aa.nextFloat()) * 0.1F);
                oentityitem.y += Math.sin((double) f1) * (double) f;
            }

            if (!(Boolean) manager.callHook(PluginLoader.Hook.ITEM_DROP, ((OEntityPlayerMP) this).getPlayer(), oentityitem.item)) {
                Item droppedItem = oentityitem.item.getItem();

                if (droppedItem.getAmount() < 0) {
                    droppedItem.setAmount(1);
                    droppedItem.update();
                }
                this.a(oentityitem);
                this.a(OStatList.v, 1);
                // return the item to the inventory.
            } else {
                return oentityitem;
            }
            return null;
        }
    }

    protected void a(OEntityItem oentityitem) {
        this.p.d((OEntity) oentityitem);
    }

    public float a(OBlock oblock) {
        float f = this.bK.a(oblock);
        int i = OEnchantmentHelper.b(this);

        if (i > 0 && this.bK.b(oblock)) {
            f += (float) (i * i + 1);
        }

        if (this.a(OPotion.e)) {
            f *= 1.0F + (float) (this.b(OPotion.e).c() + 1) * 0.2F;
        }

        if (this.a(OPotion.f)) {
            f *= 1.0F - (float) (this.b(OPotion.f).c() + 1) * 0.2F;
        }

        if (this.a(OMaterial.h) && !OEnchantmentHelper.g(this)) {
            f /= 5.0F;
        }

        if (!this.E) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(OBlock oblock) {
        return this.bK.b(oblock);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.m("Inventory");

        this.bK.b(onbttaglist);
        this.cb = onbttagcompound.n("Sleeping");
        this.b = onbttagcompound.d("SleepTimer");
        this.ci = onbttagcompound.g("XpP");
        this.cg = onbttagcompound.e("XpLevel");
        this.ch = onbttagcompound.e("XpTotal");
        if (this.cb) {
            this.cc = new OChunkCoordinates(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v));
            this.a(true, true, false);
        }

        if (onbttagcompound.b("SpawnX") && onbttagcompound.b("SpawnY") && onbttagcompound.b("SpawnZ")) {
            this.c = new OChunkCoordinates(onbttagcompound.e("SpawnX"), onbttagcompound.e("SpawnY"), onbttagcompound.e("SpawnZ"));
            this.d = onbttagcompound.n("SpawnForced");
        }

        this.bN.a(onbttagcompound);
        this.cf.b(onbttagcompound);
        if (onbttagcompound.b("EnderItems")) {
            ONBTTagList onbttaglist1 = onbttagcompound.m("EnderItems");

            this.a.a(onbttaglist1);
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Inventory", (ONBTBase) this.bK.a(new ONBTTagList()));
        onbttagcompound.a("Sleeping", this.cb);
        onbttagcompound.a("SleepTimer", (short) this.b);
        onbttagcompound.a("XpP", this.ci);
        onbttagcompound.a("XpLevel", this.cg);
        onbttagcompound.a("XpTotal", this.ch);
        if (this.c != null) {
            onbttagcompound.a("SpawnX", this.c.a);
            onbttagcompound.a("SpawnY", this.c.b);
            onbttagcompound.a("SpawnZ", this.c.c);
            onbttagcompound.a("SpawnForced", this.d);
        }

        this.bN.b(onbttagcompound);
        this.cf.a(onbttagcompound);
        onbttagcompound.a("EnderItems", (ONBTBase) this.a.g());
    }

    public void a(OIInventory oiinventory) {}

    public void c(int i, int j, int k) {}

    public void d(int i, int j, int k) {}

    public void b(int i, int j, int k) {}

    public float e() {
        return 0.12F;
    }

    protected void e_() {
        this.M = 1.62F;
    }

    public boolean a(ODamageSource odamagesource, int i) {
        if (this.cf.a && !odamagesource.e()) {
            return false;
        } else {
            this.bC = 0;
            if (this.aT() <= 0) {
                return false;
            } else {
                if (this.bt() && !this.p.J) {
                    this.a(true, true, false);
                }

                if (odamagesource.n()) {
                    if (this.p.t == 0) {
                        i = 0;
                    }

                    if (this.p.t == 1) {
                        i = i / 2 + 1;
                    }

                    if (this.p.t == 3) {
                        i = i * 3 / 2;
                    }
                }

                if (i == 0) {
                    return false;
                } else {
                    OEntity oentity = odamagesource.g();

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

    protected int c(ODamageSource odamagesource, int i) {
        int j = super.c(odamagesource, i);

        if (j <= 0) {
            return 0;
        } else {
            int k = OEnchantmentHelper.a(this.bK.b, odamagesource);

            if (k > 20) {
                k = 20;
            }

            if (k > 0 && k <= 20) {
                int l = 25 - k;
                int i1 = j * l + this.aS;

                j = i1 / 25;
                this.aS = i1 % 25;
            }

            return j;
        }
    }

    protected boolean h() {
        return false;
    }

    protected void a(OEntityLiving oentityliving, boolean flag) {
        if (!(oentityliving instanceof OEntityCreeper) && !(oentityliving instanceof OEntityGhast)) {
            if (oentityliving instanceof OEntityWolf) {
                OEntityWolf oentitywolf = (OEntityWolf) oentityliving;

                if (oentitywolf.m() && this.bT.equals(oentitywolf.o())) {
                    return;
                }
            }

            if (!(oentityliving instanceof OEntityPlayer) || this.h()) {
                List list = this.p.a(OEntityWolf.class, OAxisAlignedBB.a().a(this.t, this.u, this.v, this.t + 1.0D, this.u + 1.0D, this.v + 1.0D).b(16.0D, 4.0D, 16.0D));
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    OEntityWolf oentitywolf1 = (OEntityWolf) iterator.next();

                    if (oentitywolf1.m() && oentitywolf1.l() == null && this.bT.equals(oentitywolf1.o()) && (!flag || !oentitywolf1.n())) {
                        oentitywolf1.h(false);
                        oentitywolf1.b((OEntity) oentityliving);
                    }
                }

            }
        }
    }

    protected void k(int i) {
        this.bK.g(i);
    }

    public int aU() {
        return this.bK.k();
    }

    public float bO() {
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
        if (!this.bt) {
            if (!odamagesource.c() && this.be()) {
            i = 1 + i >> 1;
        }

        i = this.b(odamagesource, i);
        i = this.c(odamagesource, i);
        this.j(odamagesource.d());
            this.aQ -= i;
        }
    }

    public void a(OTileEntityFurnace otileentityfurnace) {}

    public void a(OTileEntityDispenser otileentitydispenser) {}

    public void a(OTileEntity otileentity) {}

    public void a(OTileEntityBrewingStand otileentitybrewingstand) {}

    public void a(OTileEntityBeacon otileentitybeacon) {}

    public void a(OIMerchant oimerchant) {}

    public void d(OItemStack oitemstack) {}

    public boolean o(OEntity oentity) {
        if (oentity.c(this)) {
            return true;
        } else {
            OItemStack oitemstack = this.bP();
            PluginLoader.HookResult res = (PluginLoader.HookResult) manager.callHook(PluginLoader.Hook.ENTITY_RIGHTCLICKED, ((OEntityPlayerMP) this).getPlayer(), oentity.entity, (oitemstack == null) ? null : new Item(oitemstack));

            if (res != PluginLoader.HookResult.PREVENT_ACTION) {
                // Normally when interact action is not defined on the interacted entity, false is returned, and the item stack is not used.
                // For example sheep can interact by shearing and cows by milking, and the item stack changes from this interaction if it returns true.
                // Players on the other hand won't interact normally, but if we want to update the item stack anyways, we will ALLOW the action.
                if (res == PluginLoader.HookResult.ALLOW_ACTION) {

                    if (oitemstack != null && oentity instanceof OEntityLiving) {
                        if (this.cf.d) {
                            oitemstack = oitemstack.l();
                        }

                        if (oitemstack.a((OEntityLiving) oentity)) {
                            if (oitemstack.a <= 0 && !this.cf.d) {
                                this.bQ();
                            }

                            return true;
                        }
                    }
                }

            }
            return false;
        }
    }

    public OItemStack bP() {
        return this.bK.g();
    }

    public void bQ() {
        this.bK.a(this.bK.c, (OItemStack) null);
    }

    public double W() {
        return (double) (this.M - 0.5F);
    }

    public void p(OEntity oentity) {
        if (oentity.aq()) {
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

            if (this.ai()) {
                ++j;
            }

            if (i > 0 || k > 0) {
                boolean flag = this.S > 0.0F && !this.E && !this.g_() && !this.H() && !this.a(OPotion.q) && this.o == null && oentity instanceof OEntityLiving;

                if (flag) {
                    i += this.aa.nextInt(i / 2 + 2);
                }

                i += k;
                boolean flag1 = oentity.a(ODamageSource.a(this), i);

                if (flag1) {
                    if (j > 0) {
                        oentity.g((double) (-OMathHelper.a(this.z * 3.1415927F / 180.0F) * (float) j * 0.5F), 0.1D, (double) (OMathHelper.b(this.z * 3.1415927F / 180.0F) * (float) j * 0.5F));
                        this.w *= 0.6D;
                        this.y *= 0.6D;
                        this.b(false);
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

                    this.k(oentity);
                }

                OItemStack oitemstack = this.bP();

                if (oitemstack != null && oentity instanceof OEntityLiving) {
                    oitemstack.a((OEntityLiving) oentity, this);
                    if (oitemstack.a <= 0) {
                        this.bQ();
                    }
                }

                if (oentity instanceof OEntityLiving) {
                    if (oentity.S()) {
                        this.a((OEntityLiving) oentity, true);
                    }

                    this.a(OStatList.w, i);
                    int l = OEnchantmentHelper.c(this, (OEntityLiving) oentity);

                    if (l > 0 && flag1) {
                        oentity.c(l * 4);
                    }
                }

                this.j(0.3F);
            }

        }
    }

    public void b(OEntity oentity) {}

    public void c(OEntity oentity) {}

    public void x() {
        super.x();
        this.bL.a(this);
        if (this.bM != null) {
            this.bM.a(this);
        }

    }

    public boolean T() {
        return !this.cb && super.T();
    }

    public boolean bS() {
        return false;
    }

    public OEnumStatus a(int i, int j, int k) {
        if (!this.p.J) {
            if (this.bt() || !this.S()) {
                return OEnumStatus.e;
            }

            if (!this.p.v.d()) {
                return OEnumStatus.b;
            }

            if (this.p.t()) {
                return OEnumStatus.c;
            }

            if (Math.abs(this.t - (double) i) > 3.0D || Math.abs(this.u - (double) j) > 2.0D || Math.abs(this.v - (double) k) > 3.0D) {
                return OEnumStatus.d;
            }

            double d0 = 8.0D;
            double d1 = 5.0D;
            List list = this.p.a(OEntityMob.class, OAxisAlignedBB.a().a((double) i - d0, (double) j - d1, (double) k - d0, (double) i + d0, (double) j + d1, (double) k + d0));

            if (!list.isEmpty()) {
                return OEnumStatus.f;
            }
        }

        this.a(0.2F, 0.2F);
        this.M = 0.2F;
        if (this.p.e(i, j, k)) {
            int l = this.p.g(i, j, k);
            int i1 = OBlockBed.e(l);
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

            this.u(i1);
            this.b((double) ((float) i + f), (double) ((float) j + 0.9375F), (double) ((float) k + f1));
        } else {
            this.b((double) ((float) i + 0.5F), (double) ((float) j + 0.9375F), (double) ((float) k + 0.5F));
        }

        this.cb = true;
        this.b = 0;
        this.cc = new OChunkCoordinates(i, j, k);
        this.w = this.y = this.x = 0.0D;
        if (!this.p.J) {
            this.p.c();
        }

        return OEnumStatus.a;
    }

    private void u(int i) {
        this.cd = 0.0F;
        this.ce = 0.0F;
        switch (i) {
            case 0:
            this.ce = -1.8F;
                break;

            case 1:
            this.cd = 1.8F;
                break;

            case 2:
            this.ce = 1.8F;
                break;

            case 3:
            this.cd = -1.8F;
        }

    }

    public void a(boolean flag, boolean flag1, boolean flag2) {
        this.a(0.6F, 1.8F);
        this.e_();
        OChunkCoordinates ochunkcoordinates = this.cc;
        OChunkCoordinates ochunkcoordinates1 = this.cc;

        if (ochunkcoordinates != null && this.p.a(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c) == OBlock.V.cm) {
            OBlockBed.a(this.p, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, false);
            ochunkcoordinates1 = OBlockBed.b(this.p, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, 0);
            if (ochunkcoordinates1 == null) {
                ochunkcoordinates1 = new OChunkCoordinates(ochunkcoordinates.a, ochunkcoordinates.b + 1, ochunkcoordinates.c);
            }

            this.b((double) ((float) ochunkcoordinates1.a + 0.5F), (double) ((float) ochunkcoordinates1.b + this.M + 0.1F), (double) ((float) ochunkcoordinates1.c + 0.5F));
        }

        this.cb = false;
        if (!this.p.J && flag1) {
            this.p.c();
        }

        if (flag) {
            this.b = 0;
        } else {
            this.b = 100;
        }

        if (flag2) {
            this.a(this.cc, false);
        }

    }

    private boolean j() {
        return this.p.a(this.cc.a, this.cc.b, this.cc.c) == OBlock.V.cm;
    }

    public static OChunkCoordinates a(OWorld oworld, OChunkCoordinates ochunkcoordinates, boolean flag) {
        OIChunkProvider oichunkprovider = oworld.H();

        oichunkprovider.c(ochunkcoordinates.a - 3 >> 4, ochunkcoordinates.c - 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a + 3 >> 4, ochunkcoordinates.c - 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a - 3 >> 4, ochunkcoordinates.c + 3 >> 4);
        oichunkprovider.c(ochunkcoordinates.a + 3 >> 4, ochunkcoordinates.c + 3 >> 4);
        if (oworld.a(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c) != OBlock.V.cm) {
            return flag && oworld.c(ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c) && oworld.c(ochunkcoordinates.a, ochunkcoordinates.b + 1, ochunkcoordinates.c) ? ochunkcoordinates : null;
        } else {
            OChunkCoordinates ochunkcoordinates1 = OBlockBed.b(oworld, ochunkcoordinates.a, ochunkcoordinates.b, ochunkcoordinates.c, 0);

            return ochunkcoordinates1;
        }
    }

    public boolean bt() {
        return this.cb;
    }

    public boolean bU() {
        return this.cb && this.b >= 100;
    }

    protected void b(int i, boolean flag) {
        byte b0 = this.ag.a(16);

        if (flag) {
            this.ag.b(16, Byte.valueOf((byte) (b0 | 1 << i)));
        } else {
            this.ag.b(16, Byte.valueOf((byte) (b0 & ~(1 << i))));
        }
    }

    public void b(String s) {}

    public OChunkCoordinates bW() {
        return this.c;
    }

    public boolean bX() {
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

    protected void bf() {
        super.bf();
        this.a(OStatList.u, 1);
        if (this.ai()) {
            this.j(0.8F);
        } else {
            this.j(0.2F);
        }
    }

    public void e(float f, float f1) {
        double d0 = this.t;
        double d1 = this.u;
        double d2 = this.v;

        if (this.cf.b && this.o == null) {
            double d3 = this.x;
            float f2 = this.aN;

            this.aN = this.cf.a();
            super.e(f, f1);
            this.x = d3 * 0.6D;
            this.aN = f2;
        } else {
            super.e(f, f1);
        }

        this.j(this.t - d0, this.u - d1, this.v - d2);
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
            } else if (this.H()) {
                i = Math.round(OMathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i > 0) {
                    this.a(OStatList.m, i);
                    this.j(0.015F * (float) i * 0.01F);
                }
            } else if (this.g_()) {
                if (d1 > 0.0D) {
                    this.a(OStatList.o, (int) Math.round(d1 * 100.0D));
                }
            } else if (this.E) {
                i = Math.round(OMathHelper.a(d0 * d0 + d2 * d2) * 100.0F);
                if (i > 0) {
                    this.a(OStatList.l, i);
                    if (this.ai()) {
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
                        this.e = new OChunkCoordinates(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v));
                    } else if ((double) this.e.e(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v)) >= 1000000.0D) {
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
        if (!this.cf.c) {
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

    public OItemStack q(int i) {
        return this.bK.f(i);
    }

    protected void bB() {}

    protected void bC() {}

    public void t(int i) {
        //CanaryMod: lets use our method.
        addXP(i);
        //CanaryMod: end
    }

    public void a(int i) {
        this.cg += i;
        if (this.cg < 0) {
            this.cg = 0;
        }

        if (i > 0 && this.cg % 5 == 0 && (float) this.h < (float) this.ab - 100.0F) {
            float f = this.cg > 30 ? 1.0F : (float) this.cg / 30.0F;

            this.p.a(this, "random.levelup", f * 0.75F, 1.0F);
            this.h = this.ab;
        }
    }

    public void addXP(int i) {
        int j = Integer.MAX_VALUE - this.ch;

        if (i > j) {
            i = j;
        }

        this.bE += i;
        this.ci += (float) i / (float) this.bY();
        this.ch += i;
        levelUp();
    }

    public void removeXP(int i) {
        this.bE -= i;
        this.ci -= (float) i / (float) this.bY();
        this.ch -= i;
        levelUp();
    }

    public void setXP(int i) {
        this.bE = i;
        this.ci = (float) i / (float) this.bY();
        this.ch = i;
        levelUp();
    }

    public void levelUp() {
        // CanaryMod: Make sure levels are right, even when removing XP.
    	int oldLevel = this.cg;
   		for (; this.ci >= 1.0F; this.ci /= (float) this.bY()) {
   			this.ci = (this.ci - 1.0F) * (float) this.bY();
   			++this.cg;
   		}
    	if (this.cg > oldLevel) {
			manager.callHook(PluginLoader.Hook.LEVEL_UP, ((OEntityPlayerMP) this).getPlayer());
		}
    }

    public int bY() {
        // CanaryMod: Old Experience OPtion
        if(etc.getInstance().isOldExperience()) {
    		return 7 + (this.cg * 7 >> 1);
    	}
        // CanaryMod: End
        return this.cg >= 30 ? 62 + (this.cg - 30) * 7 : (this.cg >= 15 ? 17 + (this.cg - 15) * 3 : 17);
    }

    public void j(float f) {
        if (!this.cf.a) {
            if (!this.p.J) {
                this.bN.a(f);
            }

        }
    }

    public OFoodStats bZ() {
        return this.bN;
    }

    public boolean f(boolean flag) {
        return (flag || this.bN.c()) && !this.cf.a;
    }

    public boolean ca() {
        return this.aT() > 0 && this.aT() < this.aS();
    }

    public void a(OItemStack oitemstack, int i) {
        if (oitemstack != this.f) {
            this.f = oitemstack;
            this.g = i;
            if (!this.p.J) {
                this.d(true);
            }

        }
    }

    public boolean f(int i, int j, int k) {
        if (this.cf.e) {
            return true;
        } else {
            int l = this.p.a(i, j, k);

            if (l > 0 && this.bP() != null) {
                OBlock oblock = OBlock.p[l];
                OItemStack oitemstack = this.bP();

                if (oitemstack.b(oblock) || oitemstack.a(oblock) > 1.0F) {
                    return true;
                }
            }

            return false;
        }
    }

    public boolean a(int i, int j, int k, int l, OItemStack oitemstack) {
        return this.cf.e ? true : (oitemstack != null ? oitemstack.x() : false);
    }

    protected int a(OEntityPlayer oentityplayer) {
        if (this.p.K().b("keepInventory")) {
            return 0;
        } else {
            int i = this.cg * 7;

        return i > 100 ? 100 : i;
    }
    }

    protected boolean aP() {
        return true;
    }

    public String an() {
        return this.bT;
    }

    public void a(OEntityPlayer oentityplayer, boolean flag) {
        if (flag) {
            this.bK.b(oentityplayer.bK);
            this.aQ = oentityplayer.aQ;
            this.bN = oentityplayer.bN;
            this.cg = oentityplayer.cg;
            this.ch = oentityplayer.ch;
            this.ci = oentityplayer.ci;
            this.bQ = oentityplayer.bQ;
            this.aq = oentityplayer.aq;
        } else if (this.p.K().b("keepInventory")) {
            this.bK.b(oentityplayer.bK);
            this.cg = oentityplayer.cg;
            this.ch = oentityplayer.ch;
            this.ci = oentityplayer.ci;
            this.bQ = oentityplayer.bQ;
        }

        this.a = oentityplayer.a;
    }

    protected boolean f_() {
        return !this.cf.b;
    }

    public void o() {}

    public void a(OEnumGameType oenumgametype) {}

    public String c_() {
        return this.bT;
    }

    public OStringTranslate s() {
        return OStringTranslate.a();
    }

    public String a(String s, Object... aobject) {
        return this.s().a(s, aobject);
    }

    public OInventoryEnderChest cb() {
        return this.a;
    }

    public OItemStack p(int i) {
        return i == 0 ? this.bK.g() : this.bK.b[i - 1];
    }

    public OItemStack bA() {
        return this.bK.g();
    }

    public void b(int i, OItemStack oitemstack) {
        this.bK.b[i] = oitemstack;
    }

    public OItemStack[] ae() {
        return this.bK.b;
    }
}
