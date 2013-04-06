public abstract class OEntityMinecartContainer extends OEntityMinecart implements OIInventory, Container<OItemStack> {

    private OItemStack[] a = new OItemStack[36];
    private boolean b = true;

    private ContainerMinecart container = this instanceof OEntityMinecartChest
            ? new ChestMinecart((OEntityMinecartChest) this)
            : new HopperMinecart((OEntityMinecartHopper) this);

    public OEntityMinecartContainer(OWorld oworld) {
        super(oworld);
    }

    public OEntityMinecartContainer(OWorld oworld, double d0, double d1, double d2) {
        super(oworld, d0, d1, d2);
    }

    public void a(ODamageSource odamagesource) {
        super.a(odamagesource);

        for (int i = 0; i < this.j_(); ++i) {
            OItemStack oitemstack = this.a(i);

            if (oitemstack != null) {
                float f = this.ab.nextFloat() * 0.8F + 0.1F;
                float f1 = this.ab.nextFloat() * 0.8F + 0.1F;
                float f2 = this.ab.nextFloat() * 0.8F + 0.1F;

                while (oitemstack.a > 0) {
                    int j = this.ab.nextInt(21) + 10;

                    if (j > oitemstack.a) {
                        j = oitemstack.a;
                    }

                    oitemstack.a -= j;
                    OEntityItem oentityitem = new OEntityItem(this.q, this.u + (double) f, this.v + (double) f1, this.w + (double) f2, new OItemStack(oitemstack.c, j, oitemstack.k()));
                    float f3 = 0.05F;

                    oentityitem.x = (double) ((float) this.ab.nextGaussian() * f3);
                    oentityitem.y = (double) ((float) this.ab.nextGaussian() * f3 + 0.2F);
                    oentityitem.z = (double) ((float) this.ab.nextGaussian() * f3);
                    this.q.d((OEntity) oentityitem);
                }
            }
        }
    }

    public OItemStack a(int i) {
        return this.a[i];
    }

    public OItemStack a(int i, int j) {
        if (this.a[i] != null) {
            OItemStack oitemstack;

            if (this.a[i].a <= j) {
                oitemstack = this.a[i];
                this.a[i] = null;
                return oitemstack;
            } else {
                oitemstack = this.a[i].a(j);
                if (this.a[i].a == 0) {
                    this.a[i] = null;
                }

                return oitemstack;
            }
        } else {
            return null;
        }
    }

    public OItemStack b(int i) {
        if (this.a[i] != null) {
            OItemStack oitemstack = this.a[i];

            this.a[i] = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        this.a[i] = oitemstack;
        if (oitemstack != null && oitemstack.a > this.d()) {
            oitemstack.a = this.d();
        }
    }

    public void k_() {}

    public boolean a(OEntityPlayer oentityplayer) {
        return this.M ? false : oentityplayer.e(this) <= 64.0D;
    }

    public void f() {}

    public void g() {}

    public boolean b(int i, OItemStack oitemstack) {
        return true;
    }

    public String b() {
        return this.c() ? this.t() : "container.minecart";
    }

    public int d() {
        return 64;
    }

    public void c(int i) {
        this.b = false;
        super.c(i);
    }

    public void w() {
        if (this.b) {
            for (int i = 0; i < this.j_(); ++i) {
                OItemStack oitemstack = this.a(i);

                if (oitemstack != null) {
                    float f = this.ab.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.ab.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.ab.nextFloat() * 0.8F + 0.1F;

                    while (oitemstack.a > 0) {
                        int j = this.ab.nextInt(21) + 10;

                        if (j > oitemstack.a) {
                            j = oitemstack.a;
                        }

                        oitemstack.a -= j;
                        OEntityItem oentityitem = new OEntityItem(this.q, this.u + (double) f, this.v + (double) f1, this.w + (double) f2, new OItemStack(oitemstack.c, j, oitemstack.k()));

                        if (oitemstack.p()) {
                            oentityitem.d().d((ONBTTagCompound) oitemstack.q().b());
                        }

                        float f3 = 0.05F;

                        oentityitem.x = (double) ((float) this.ab.nextGaussian() * f3);
                        oentityitem.y = (double) ((float) this.ab.nextGaussian() * f3 + 0.2F);
                        oentityitem.z = (double) ((float) this.ab.nextGaussian() * f3);
                        this.q.d((OEntity) oentityitem);
                    }
                }
            }
        }

        super.w();
    }

    protected void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        ONBTTagList onbttaglist = new ONBTTagList();

        for (int i = 0; i < this.a.length; ++i) {
            if (this.a[i] != null) {
                ONBTTagCompound onbttagcompound1 = new ONBTTagCompound();

                onbttagcompound1.a("Slot", (byte) i);
                this.a[i].b(onbttagcompound1);
                onbttaglist.a((ONBTBase) onbttagcompound1);
            }
        }

        onbttagcompound.a("Items", (ONBTBase) onbttaglist);
    }

    protected void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        ONBTTagList onbttaglist = onbttagcompound.m("Items");

        this.a = new OItemStack[this.j_()];

        for (int i = 0; i < onbttaglist.c(); ++i) {
            ONBTTagCompound onbttagcompound1 = (ONBTTagCompound) onbttaglist.b(i);
            int j = onbttagcompound1.c("Slot") & 255;

            if (j >= 0 && j < this.a.length) {
                this.a[j] = OItemStack.a(onbttagcompound1);
            }
        }
    }

    public boolean a_(OEntityPlayer oentityplayer) {
        if (!this.q.I) {
            oentityplayer.a((OIInventory) this);
        }

        return true;
    }

    protected void h() {
        int i = 15 - OContainer.b((OIInventory) this);
        float f = 0.98F + (float) i * 0.001F;

        this.x *= (double) f;
        this.y *= 0.0D;
        this.z *= (double) f;
    }

    @Override
    public OItemStack[] getContents() {
        return this.a.clone();
    }

    @Override
    public void setContents(OItemStack[] values) {
        System.arraycopy(values, 0, this.a, 0, this.getContentsSize());
    }

    @Override
    public OItemStack getContentsAt(int index) {
        return this.a(index);
    }

    @Override
    public void setContentsAt(int index, OItemStack value) {
        this.a(index, value);
    }

    @Override
    public int getContentsSize() {
        return this.j_();
    }

    @Override
    public String getName() {
        return this.b();
    }

    @Override
    public void setName(String value) {
        this.a(value);
    }

    @Override
    public ContainerMinecart getEntity() {
        return this.container;
    }
}
