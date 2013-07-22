import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public abstract class OEntityLiving extends OEntityLivingBase {

    public int a_;
    protected int b;
    private OEntityLookHelper h;
    private OEntityMoveHelper i;
    private OEntityJumpHelper j;
    private OEntityBodyHelper bn;
    private OPathNavigate bo;
    protected final OEntityAITasks c;
    protected final OEntityAITasks d;
    private OEntityLivingBase bp;
    private OEntitySenses bq;
    private OItemStack[] br = new OItemStack[5];
    protected float[] e = new float[5];
    private boolean bs;
    protected boolean bt; // CanaryMod: private -> protected
    protected float f;
    protected OEntity bu; // CanaryMod: private -> protected
    protected int g;
    private boolean bv;
    private OEntity bw;
    private ONBTTagCompound bx;

    private LivingEntity livingEntity = new LivingEntity(this);

    public OEntityLiving(OWorld oworld) {
        super(oworld);
        this.c = new OEntityAITasks(oworld != null && oworld.C != null ? oworld.C : null);
        this.d = new OEntityAITasks(oworld != null && oworld.C != null ? oworld.C : null);
        this.h = new OEntityLookHelper(this);
        this.i = new OEntityMoveHelper(this);
        this.j = new OEntityJumpHelper(this);
        this.bn = new OEntityBodyHelper(this);
        this.bo = new OPathNavigate(this, oworld);
        this.bq = new OEntitySenses(this);

        for (int i = 0; i < this.e.length; ++i) {
            this.e[i] = 0.085F;
        }
    }

    protected void ay() {
        super.ay();
        this.aW().b(OSharedMonsterAttributes.b).a(16.0D);
    }

    public OEntityLookHelper h() {
        return this.h;
    }

    public OEntityMoveHelper i() {
        return this.i;
    }

    public OEntityJumpHelper j() {
        return this.j;
    }

    public OPathNavigate k() {
        return this.bo;
    }

    public OEntitySenses l() {
        return this.bq;
    }

    public OEntityLivingBase m() {
        return this.bp;
    }

    public void d(OEntityLivingBase oentitylivingbase) {
        // CanaryMod start: MOB_TARGET hook
        if (oentitylivingbase != null && (Boolean) manager.callHook(PluginLoader.Hook.MOB_TARGET, oentitylivingbase.getEntity(), this.getEntity())) {
            return;
        } // CanaryMod end
        this.bp = oentitylivingbase;
    }

    public boolean a(Class oclass) {
        return OEntityCreeper.class != oclass && OEntityGhast.class != oclass;
    }

    public void n() {}

    protected void a() {
        super.a();
        this.ah.a(11, Byte.valueOf((byte) 0));
        this.ah.a(10, "");
    }

    public int o() {
        return 80;
    }

    public void p() {
        String s = this.r();

        if (s != null) {
            this.a(s, this.aZ(), this.ba());
        }
    }

    public void x() {
        super.x();
        this.q.C.a("mobBaseTick");
        if (this.S() && this.ab.nextInt(1000) < this.a_++) {
            this.a_ = -this.o();
            this.p();
        }

        this.q.C.b();
    }

    protected int e(OEntityPlayer oentityplayer) {
        if (this.b > 0) {
            int i = this.b;
            OItemStack[] aoitemstack = this.ad();

            for (int j = 0; j < aoitemstack.length; ++j) {
                if (aoitemstack[j] != null && this.e[j] <= 1.0F) {
                    i += 1 + this.ab.nextInt(3);
                }
            }

            return i;
        } else {
            return this.b;
        }
    }

    public void q() {
        for (int i = 0; i < 20; ++i) {
            double d0 = this.ab.nextGaussian() * 0.02D;
            double d1 = this.ab.nextGaussian() * 0.02D;
            double d2 = this.ab.nextGaussian() * 0.02D;
            double d3 = 10.0D;

            this.q.a("explode", this.u + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O - d0 * d3, this.v + (double) (this.ab.nextFloat() * this.P) - d1 * d3, this.w + (double) (this.ab.nextFloat() * this.O * 2.0F) - (double) this.O - d2 * d3, d0, d1, d2);
        }
    }

    public void l_() {
        super.l_();
        if (!this.q.I) {
            this.bF();
        }
    }

    protected float f(float f, float f1) {
        if (this.be()) {
            this.bn.a();
            return f1;
        } else {
            return super.f(f, f1);
        }
    }

    protected String r() {
        return null;
    }

    protected int s() {
        return 0;
    }

    protected void b(boolean flag, int i) {
        int j = this.s();

        if (j > 0) {
            int k = this.ab.nextInt(3);

            if (i > 0) {
                k += this.ab.nextInt(i + 1);
            }

            for (int l = 0; l < k; ++l) {
                this.b(j, 1);
            }
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("CanPickUpLoot", this.bD());
        onbttagcompound.a("PersistenceRequired", this.bt);
        ONBTTagList onbttaglist = new ONBTTagList();

        ONBTTagCompound onbttagcompound1;

        for (int i = 0; i < this.br.length; ++i) {
            onbttagcompound1 = new ONBTTagCompound();
            if (this.br[i] != null) {
                this.br[i].b(onbttagcompound1);
            }

            onbttaglist.a((ONBTBase) onbttagcompound1);
        }

        onbttagcompound.a("Equipment", (ONBTBase) onbttaglist);
        ONBTTagList onbttaglist1 = new ONBTTagList();

        for (int j = 0; j < this.e.length; ++j) {
            onbttaglist1.a((ONBTBase) (new ONBTTagFloat(j + "", this.e[j])));
        }

        onbttagcompound.a("DropChances", (ONBTBase) onbttaglist1);
        onbttagcompound.a("CustomName", this.bA());
        onbttagcompound.a("CustomNameVisible", this.bC());
        onbttagcompound.a("Leashed", this.bv);
        if (this.bw != null) {
            onbttagcompound1 = new ONBTTagCompound("Leash");
            if (this.bw instanceof OEntityLivingBase) {
                onbttagcompound1.a("UUIDMost", this.bw.av().getMostSignificantBits());
                onbttagcompound1.a("UUIDLeast", this.bw.av().getLeastSignificantBits());
            } else if (this.bw instanceof OEntityHanging) {
                OEntityHanging oentityhanging = (OEntityHanging) this.bw;

                onbttagcompound1.a("X", oentityhanging.b);
                onbttagcompound1.a("Y", oentityhanging.c);
                onbttagcompound1.a("Z", oentityhanging.d);
            }

            onbttagcompound.a("Leash", (ONBTBase) onbttagcompound1);
        }
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.h(onbttagcompound.n("CanPickUpLoot"));
        this.bt = onbttagcompound.n("PersistenceRequired");
        if (onbttagcompound.b("CustomName") && onbttagcompound.i("CustomName").length() > 0) {
            this.a(onbttagcompound.i("CustomName"));
        }

        this.g(onbttagcompound.n("CustomNameVisible"));
        ONBTTagList onbttaglist;
        int i;

        if (onbttagcompound.b("Equipment")) {
            onbttaglist = onbttagcompound.m("Equipment");

            for (i = 0; i < this.br.length; ++i) {
                this.br[i] = OItemStack.a((ONBTTagCompound) onbttaglist.b(i));
            }
        }

        if (onbttagcompound.b("DropChances")) {
            onbttaglist = onbttagcompound.m("DropChances");

            for (i = 0; i < onbttaglist.c(); ++i) {
                this.e[i] = ((ONBTTagFloat) onbttaglist.b(i)).a;
            }
        }

        this.bv = onbttagcompound.n("Leashed");
        if (this.bv && onbttagcompound.b("Leash")) {
            this.bx = onbttagcompound.l("Leash");
        }
    }

    public void n(float f) {
        this.bf = f;
    }

    public void i(float f) {
        super.i(f);
        this.n(f);
    }

    public void c() {
        super.c();
        this.q.C.a("looting");
        if (!this.q.I && this.bD() && !this.aU && this.q.O().b("mobGriefing")) {
            List list = this.q.a(OEntityItem.class, this.E.b(1.0D, 0.0D, 1.0D));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                OEntityItem oentityitem = (OEntityItem) iterator.next();

                if (!oentityitem.M && oentityitem.d() != null) {
                    OItemStack oitemstack = oentityitem.d();
                    int i = b(oitemstack);

                    if (i > -1) {
                        boolean flag = true;
                        OItemStack oitemstack1 = this.n(i);

                        if (oitemstack1 != null) {
                            if (i == 0) {
                                if (oitemstack.b() instanceof OItemSword && !(oitemstack1.b() instanceof OItemSword)) {
                                    flag = true;
                                } else if (oitemstack.b() instanceof OItemSword && oitemstack1.b() instanceof OItemSword) {
                                    OItemSword oitemsword = (OItemSword) oitemstack.b();
                                    OItemSword oitemsword1 = (OItemSword) oitemstack1.b();

                                    if (oitemsword.g() == oitemsword1.g()) {
                                        flag = oitemstack.k() > oitemstack1.k() || oitemstack.p() && !oitemstack1.p();
                                    } else {
                                        flag = oitemsword.g() > oitemsword1.g();
                                    }
                                } else {
                                    flag = false;
                                }
                            } else if (oitemstack.b() instanceof OItemArmor && !(oitemstack1.b() instanceof OItemArmor)) {
                                flag = true;
                            } else if (oitemstack.b() instanceof OItemArmor && oitemstack1.b() instanceof OItemArmor) {
                                OItemArmor oitemarmor = (OItemArmor) oitemstack.b();
                                OItemArmor oitemarmor1 = (OItemArmor) oitemstack1.b();

                                if (oitemarmor.c == oitemarmor1.c) {
                                    flag = oitemstack.k() > oitemstack1.k() || oitemstack.p() && !oitemstack1.p();
                                } else {
                                    flag = oitemarmor.c > oitemarmor1.c;
                                }
                            } else {
                                flag = false;
                            }
                        }

                        if (flag) {
                            if (oitemstack1 != null && this.ab.nextFloat() - 0.1F < this.e[i]) {
                                this.a(oitemstack1, 0.0F);
                            }

                            this.c(i, oitemstack);
                            this.e[i] = 2.0F;
                            this.bt = true;
                            this.a(oentityitem, 1);
                            oentityitem.w();
                        }
                    }
                }
            }
        }

        this.q.C.b();
    }

    protected boolean be() {
        return false;
    }

    protected boolean t() {
        return true;
    }

    protected void bo() {
        if (this.bt) {
            this.aV = 0;
        } else {
            OEntityPlayer oentityplayer = this.q.a(this, -1.0D);

            if (oentityplayer != null) {
                double d0 = oentityplayer.u - this.u;
                double d1 = oentityplayer.v - this.v;
                double d2 = oentityplayer.w - this.w;
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;

                if (this.t() && d3 > 16384.0D && !(Boolean) manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.getEntity())) {
                    this.w();
                }

                if (this.aV > 600 && this.ab.nextInt(800) == 0 && d3 > 1024.0D && this.t()) {
                    if (!(Boolean) manager.callHook(PluginLoader.Hook.ENTITY_DESPAWN, this.getEntity())) {
                        this.w();
                    } else {
                        this.aV = 0;
                    }
                } else if (d3 < 1024.0D) {
                    this.aV = 0;
                }
            }
        }
    }

    protected void bh() {
        ++this.aV;
        this.q.C.a("checkDespawn");
        this.bo();
        this.q.C.b();
        this.q.C.a("sensing");
        this.bq.a();
        this.q.C.b();
        this.q.C.a("targetSelector");
        this.d.a();
        this.q.C.b();
        this.q.C.a("goalSelector");
        this.c.a();
        this.q.C.b();
        this.q.C.a("navigation");
        this.bo.f();
        this.q.C.b();
        this.q.C.a("mob tick");
        this.bj();
        this.q.C.b();
        this.q.C.a("controls");
        this.q.C.a("move");
        this.i.c();
        this.q.C.c("look");
        this.h.a();
        this.q.C.c("jump");
        this.j.b();
        this.q.C.b();
        this.q.C.b();
    }

    protected void bk() {
        super.bk();
        this.be = 0.0F;
        this.bf = 0.0F;
        this.bo();
        float f = 8.0F;

        if (this.ab.nextFloat() < 0.02F) {
            OEntityPlayer oentityplayer = this.q.a(this, (double) f);

            if (oentityplayer != null) {
                this.bu = oentityplayer;
                this.g = 10 + this.ab.nextInt(20);
            } else {
                this.bg = (this.ab.nextFloat() - 0.5F) * 20.0F;
            }
        }

        if (this.bu != null) {
            this.a(this.bu, 10.0F, (float) this.bp());
            if (this.g-- <= 0 || this.bu.M || this.bu.e((OEntity) this) > (double) (f * f)) {
                this.bu = null;
            }
        } else {
            if (this.ab.nextFloat() < 0.05F) {
                this.bg = (this.ab.nextFloat() - 0.5F) * 20.0F;
            }

            this.A += this.bg;
            this.B = this.f;
        }

        boolean flag = this.G();
        boolean flag1 = this.I();

        if (flag || flag1) {
            this.bd = this.ab.nextFloat() < 0.8F;
        }
    }

    public int bp() {
        return 40;
    }

    public void a(OEntity oentity, float f, float f1) {
        double d0 = oentity.u - this.u;
        double d1 = oentity.w - this.w;
        double d2;

        if (oentity instanceof OEntityLivingBase) {
            OEntityLivingBase oentitylivingbase = (OEntityLivingBase) oentity;

            d2 = oentitylivingbase.v + (double) oentitylivingbase.f() - (this.v + (double) this.f());
        } else {
            d2 = (oentity.E.b + oentity.E.e) / 2.0D - (this.v + (double) this.f());
        }

        double d3 = (double) OMathHelper.a(d0 * d0 + d1 * d1);
        float f2 = (float) (Math.atan2(d1, d0) * 180.0D / 3.1415927410125732D) - 90.0F;
        float f3 = (float) (-(Math.atan2(d2, d3) * 180.0D / 3.1415927410125732D));

        this.B = this.b(this.B, f3, f1);
        this.A = this.b(this.A, f2, f);
    }

    private float b(float f, float f1, float f2) {
        float f3 = OMathHelper.g(f1 - f);

        if (f3 > f2) {
            f3 = f2;
        }

        if (f3 < -f2) {
            f3 = -f2;
        }

        return f + f3;
    }

    public boolean bs() {
        return this.q.b(this.E) && this.q.a((OEntity) this, this.E).isEmpty() && !this.q.d(this.E);
    }

    public int bv() {
        return 4;
    }

    public int ar() {
        if (this.m() == null) {
            return 3;
        } else {
            int i = (int) (this.aM() - this.aS() * 0.33F);

            i -= (3 - this.q.r) * 4;
            if (i < 0) {
                i = 0;
            }

            return i + 3;
        }
    }

    public OItemStack aY() {
        return this.br[0];
    }

    public OItemStack n(int i) {
        return this.br[i];
    }

    public OItemStack o(int i) {
        return this.br[i + 1];
    }

    public void c(int i, OItemStack oitemstack) {
        this.br[i] = oitemstack;
    }

    public OItemStack[] ad() {
        return this.br;
    }

    protected void a(boolean flag, int i) {
        for (int j = 0; j < this.ad().length; ++j) {
            OItemStack oitemstack = this.n(j);
            boolean flag1 = this.e[j] > 1.0F;

            if (oitemstack != null && (flag || flag1) && this.ab.nextFloat() - (float) i * 0.01F < this.e[j]) {
                if (!flag1 && oitemstack.g()) {
                    int k = Math.max(oitemstack.l() - 25, 1);
                    int l = oitemstack.l() - this.ab.nextInt(this.ab.nextInt(k) + 1);

                    if (l > k) {
                        l = k;
                    }

                    if (l < 1) {
                        l = 1;
                    }

                    oitemstack.b(l);
                }

                this.a(oitemstack, 0.0F);
            }
        }
    }

    protected void bw() {
        if (this.ab.nextFloat() < 0.15F * this.q.b(this.u, this.v, this.w)) {
            int i = this.ab.nextInt(2);
            float f = this.q.r == 3 ? 0.1F : 0.25F;

            if (this.ab.nextFloat() < 0.095F) {
                ++i;
            }

            if (this.ab.nextFloat() < 0.095F) {
                ++i;
            }

            if (this.ab.nextFloat() < 0.095F) {
                ++i;
            }

            for (int j = 3; j >= 0; --j) {
                OItemStack oitemstack = this.o(j);

                if (j < 3 && this.ab.nextFloat() < f) {
                    break;
                }

                if (oitemstack == null) {
                    OItem oitem = a(j + 1, i);

                    if (oitem != null) {
                        this.c(j + 1, new OItemStack(oitem));
                    }
                }
            }
        }
    }

    public static int b(OItemStack oitemstack) {
        if (oitemstack.d != OBlock.bf.cF && oitemstack.d != OItem.bS.cv) {
            if (oitemstack.b() instanceof OItemArmor) {
                switch (((OItemArmor) oitemstack.b()).b) {
                    case 0:
                        return 4;

                    case 1:
                        return 3;

                    case 2:
                        return 2;

                    case 3:
                        return 1;
                }
            }

            return 0;
        } else {
            return 4;
        }
    }

    public static OItem a(int i, int j) {
        switch (i) {
            case 4:
                if (j == 0) {
                    return OItem.X;
                } else if (j == 1) {
                    return OItem.an;
                } else if (j == 2) {
                    return OItem.ab;
                } else if (j == 3) {
                    return OItem.af;
                } else if (j == 4) {
                    return OItem.aj;
                }

            case 3:
                if (j == 0) {
                    return OItem.Y;
                } else if (j == 1) {
                    return OItem.ao;
                } else if (j == 2) {
                    return OItem.ac;
                } else if (j == 3) {
                    return OItem.ag;
                } else if (j == 4) {
                    return OItem.ak;
                }

            case 2:
                if (j == 0) {
                    return OItem.Z;
                } else if (j == 1) {
                    return OItem.ap;
                } else if (j == 2) {
                    return OItem.ad;
                } else if (j == 3) {
                    return OItem.ah;
                } else if (j == 4) {
                    return OItem.al;
                }

            case 1:
                if (j == 0) {
                    return OItem.aa;
                } else if (j == 1) {
                    return OItem.aq;
                } else if (j == 2) {
                    return OItem.ae;
                } else if (j == 3) {
                    return OItem.ai;
                } else if (j == 4) {
                    return OItem.am;
                }

            default:
                return null;
        }
    }

    protected void bx() {
        float f = this.q.b(this.u, this.v, this.w);

        if (this.aY() != null && this.ab.nextFloat() < 0.25F * f) {
            OEnchantmentHelper.a(this.ab, this.aY(), (int) (5.0F + f * (float) this.ab.nextInt(18)));
        }

        for (int i = 0; i < 4; ++i) {
            OItemStack oitemstack = this.o(i);

            if (oitemstack != null && this.ab.nextFloat() < 0.5F * f) {
                OEnchantmentHelper.a(this.ab, oitemstack, (int) (5.0F + f * (float) this.ab.nextInt(18)));
            }
        }
    }

    public OEntityLivingData a(OEntityLivingData oentitylivingdata) {
        this.a(OSharedMonsterAttributes.b).a(new OAttributeModifier("Random spawn bonus", this.ab.nextGaussian() * 0.05D, 1));
        return oentitylivingdata;
    }

    public boolean by() {
        return false;
    }

    public String am() {
        return this.bB() ? this.bA() : super.am();
    }

    public void bz() {
        this.bt = true;
    }

    public void a(String s) {
        this.ah.b(10, s);
    }

    public String bA() {
        return this.ah.e(10);
    }

    public boolean bB() {
        return this.ah.e(10).length() > 0;
    }

    public void g(boolean flag) {
        this.ah.b(11, Byte.valueOf((byte) (flag ? 1 : 0)));
    }

    public boolean bC() {
        return this.ah.a(11) == 1;
    }

    public void a(int i, float f) {
        this.e[i] = f;
    }

    public boolean bD() {
        return this.bs;
    }

    public void h(boolean flag) {
        this.bs = flag;
    }

    public boolean bE() {
        return this.bt;
    }

    public final boolean c(OEntityPlayer oentityplayer) {
        if (this.bH() && this.bI() == oentityplayer) {
            this.a(true, !oentityplayer.bG.d);
            return true;
        } else {
            OItemStack oitemstack = oentityplayer.bn.h();

            if (oitemstack != null && oitemstack.d == OItem.ch.cv && this.bG()) {
                if (!(this instanceof OEntityTameable) || !((OEntityTameable) this).bT()) {
                    this.b(oentityplayer, true);
                    --oitemstack.b;
                    return true;
                }

                if (oentityplayer.c_().equalsIgnoreCase(((OEntityTameable) this).h_())) {
                    this.b(oentityplayer, true);
                    --oitemstack.b;
                    return true;
                }
            }

            return this.a(oentityplayer) ? true : super.c(oentityplayer);
        }
    }

    protected boolean a(OEntityPlayer oentityplayer) {
        return false;
    }

    protected void bF() {
        if (this.bx != null) {
            this.bJ();
        }

        if (this.bv) {
            if (this.bw == null || this.bw.M) {
                this.a(true, true);
            }
        }
    }

    public void a(boolean flag, boolean flag1) {
        if (this.bv) {
            this.bv = false;
            this.bw = null;
            if (!this.q.I && flag1) {
                this.b(OItem.ch.cv, 1);
            }

            if (!this.q.I && flag && this.q instanceof OWorldServer) {
                ((OWorldServer) this.q).q().a((OEntity) this, (OPacket) (new OPacket39AttachEntity(1, this, (OEntity) null)));
            }
        }
    }

    public boolean bG() {
        return !this.bH() && !(this instanceof OIMob);
    }

    public boolean bH() {
        return this.bv;
    }

    public OEntity bI() {
        return this.bw;
    }

    public void b(OEntity oentity, boolean flag) {
        this.bv = true;
        this.bw = oentity;
        if (!this.q.I && flag && this.q instanceof OWorldServer) {
            ((OWorldServer) this.q).q().a((OEntity) this, (OPacket) (new OPacket39AttachEntity(1, this, this.bw)));
        }
    }

    private void bJ() {
        if (this.bv && this.bx != null) {
            if (this.bx.b("UUIDMost") && this.bx.b("UUIDLeast")) {
                UUID uuid = new UUID(this.bx.f("UUIDMost"), this.bx.f("UUIDLeast"));
                List list = this.q.a(OEntityLivingBase.class, this.E.b(10.0D, 10.0D, 10.0D));
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    OEntityLivingBase oentitylivingbase = (OEntityLivingBase) iterator.next();

                    if (oentitylivingbase.av().equals(uuid)) {
                        this.bw = oentitylivingbase;
                        break;
                    }
                }
            } else if (this.bx.b("X") && this.bx.b("Y") && this.bx.b("Z")) {
                int i = this.bx.e("X");
                int j = this.bx.e("Y");
                int k = this.bx.e("Z");
                OEntityLeashKnot oentityleashknot = OEntityLeashKnot.b(this.q, i, j, k);

                if (oentityleashknot == null) {
                    oentityleashknot = OEntityLeashKnot.a(this.q, i, j, k);
                }

                this.bw = oentityleashknot;
            } else {
                this.a(false, true);
            }
        }

        this.bx = null;
    }

    // CanaryMod start: add getEntity
    @Override
    public LivingEntity getEntity() {
        return livingEntity;
    } // CanaryMod end
}
