import java.util.Iterator;
import java.util.List;

public class OTileEntityBeacon extends OTileEntity implements OIInventory, Container<OItemStack> {

    public static final OPotion[][] a = new OPotion[][] { { OPotion.c, OPotion.e}, { OPotion.m, OPotion.j}, { OPotion.g}, { OPotion.l}};
    private boolean d;
    private int e = -1;
    private int f;
    private int g;
    private OItemStack h;
    private String i;

    private final Beacon beacon = new Beacon(this); // CanaryMod: reference to wrapper

    public OTileEntityBeacon() {}

    public void h() {
        if (this.k.G() % 80L == 0L) {
            this.v();
            this.u();
        }
    }

    private void u() {
        if (this.d && this.e > 0 && !this.k.I && this.f > 0) {
            double d0 = (double) (this.e * 10 + 10);
            byte b0 = 0;

            if (this.e >= 4 && this.f == this.g) {
                b0 = 1;
            }

            OAxisAlignedBB oaxisalignedbb = OAxisAlignedBB.a().a((double) this.l, (double) this.m, (double) this.n, (double) (this.l + 1), (double) (this.m + 1), (double) (this.n + 1)).b(d0, d0, d0);

            oaxisalignedbb.e = (double) this.k.P();
            List list = this.k.a(OEntityPlayer.class, oaxisalignedbb);
            Iterator iterator = list.iterator();

            OEntityPlayer oentityplayer;

            while (iterator.hasNext()) {
                oentityplayer = (OEntityPlayer) iterator.next();
                oentityplayer.d(new OPotionEffect(this.f, 180, b0, true));
            }

            if (this.e >= 4 && this.f != this.g && this.g > 0) {
                iterator = list.iterator();

                while (iterator.hasNext()) {
                    oentityplayer = (OEntityPlayer) iterator.next();
                    oentityplayer.d(new OPotionEffect(this.g, 180, 0, true));
                }
            }
        }
    }

    private void v() {
        if (!this.k.l(this.l, this.m + 1, this.n)) {
            this.d = false;
            this.e = 0;
        } else {
            this.d = true;
            this.e = 0;

            for (int i = 1; i <= 4; this.e = i++) {
                int j = this.m - i;

                if (j < 0) {
                    break;
                }

                boolean flag = true;

                for (int k = this.l - i; k <= this.l + i && flag; ++k) {
                    for (int l = this.n - i; l <= this.n + i; ++l) {
                        int i1 = this.k.a(k, j, l);

                        if (i1 != OBlock.bZ.cz && i1 != OBlock.al.cz && i1 != OBlock.aB.cz && i1 != OBlock.am.cz) {
                            flag = false;
                            break;
                        }
                    }
                }

                if (!flag) {
                    break;
                }
            }

            if (this.e == 0) {
                this.d = false;
            }
        }
    }

    public int j() {
        return this.f;
    }

    public int k() {
        return this.g;
    }

    public int l() {
        return this.e;
    }

    public void d(int i) {
        this.f = 0;

        for (int j = 0; j < this.e && j < 3; ++j) {
            OPotion[] aopotion = a[j];
            int k = aopotion.length;

            for (int l = 0; l < k; ++l) {
                OPotion opotion = aopotion[l];

                if (opotion.H == i) {
                    this.f = i;
                    return;
                }
            }
        }
    }

    public void e(int i) {
        this.g = 0;
        if (this.e >= 4) {
            for (int j = 0; j < 4; ++j) {
                OPotion[] aopotion = a[j];
                int k = aopotion.length;

                for (int l = 0; l < k; ++l) {
                    OPotion opotion = aopotion[l];

                    if (opotion.H == i) {
                        this.g = i;
                        return;
                    }
                }
            }
        }
    }

    public OPacket m() {
        ONBTTagCompound onbttagcompound = new ONBTTagCompound();

        this.b(onbttagcompound);
        return new OPacket132TileEntityData(this.l, this.m, this.n, 3, onbttagcompound);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        super.a(onbttagcompound);
        this.f = onbttagcompound.e("Primary");
        this.g = onbttagcompound.e("Secondary");
        this.e = onbttagcompound.e("Levels");
    }

    public void b(ONBTTagCompound onbttagcompound) {
        super.b(onbttagcompound);
        onbttagcompound.a("Primary", this.f);
        onbttagcompound.a("Secondary", this.g);
        onbttagcompound.a("Levels", this.e);
    }

    public int j_() {
        return 1;
    }

    public OItemStack a(int i) {
        return i == 0 ? this.h : null;
    }

    public OItemStack a(int i, int j) {
        if (i == 0 && this.h != null) {
            if (j >= this.h.a) {
                OItemStack oitemstack = this.h;

                this.h = null;
                return oitemstack;
            } else {
                this.h.a -= j;
                return new OItemStack(this.h.c, j, this.h.k());
            }
        } else {
            return null;
        }
    }

    public OItemStack b(int i) {
        if (i == 0 && this.h != null) {
            OItemStack oitemstack = this.h;

            this.h = null;
            return oitemstack;
        } else {
            return null;
        }
    }

    public void a(int i, OItemStack oitemstack) {
        if (i == 0) {
            this.h = oitemstack;
        }
    }

    public String b() {
        return this.c() ? this.i : "container.beacon";
    }

    public boolean c() {
        return this.i != null && this.i.length() > 0;
    }

    public void a(String s) {
        this.i = s;
    }

    public int d() {
        return 1;
    }

    public boolean a(OEntityPlayer oentityplayer) {
        return this.k.r(this.l, this.m, this.n) != this ? false : oentityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void f() {}

    public void g() {}

    public boolean b(int i, OItemStack oitemstack) {
        return oitemstack.c == OItem.bI.cp || oitemstack.c == OItem.o.cp || oitemstack.c == OItem.q.cp || oitemstack.c == OItem.p.cp;
    }

    @Override
    public OItemStack[] getContents() {
        return new OItemStack[]{this.h};
    }

    @Override
    public void setContents(OItemStack[] values) {
        this.h = values == null || values.length == 0 ? null : values[0];
    }

    @Override
    public OItemStack getContentsAt(int index) {
        return index == 0 ? this.h : null;
    }

    @Override
    public void setContentsAt(int index, OItemStack value) {
        if (index == 0) {
            this.h = value;
        }
    }

    @Override
    public int getContentsSize() {
        return 1;
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
    public Beacon getComplexBlock() {
        return beacon;
    }

    public int getLevels() {
        return e;
    }

    public void setLevels(int levels) {
        this.e = levels;
    }

    public int getPrimaryEffect() {
        return f;
    }

    public void setPrimaryEffect(int effect) {
        this.d(effect);
    }

    public void setPrimaryEffectDirectly(int effect) {
        this.f = effect;
    }

    public int getSecondaryEffect() {
        return g;
    }

    public void setSecondaryEffect(int effect) {
        this.e(effect);
    }

    public void setSecondaryEffectDirectly(int effect) {
        this.g = effect;
    }
}
