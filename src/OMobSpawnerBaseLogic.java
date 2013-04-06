import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class OMobSpawnerBaseLogic {

    public int b = 20;
    private String a = "Pig";
    private List e = null;
    private OWeightedRandomMinecart f = null;
    public double c;
    public double d = 0.0D;
    int g = 200; // CanaryMod: private -> package-private
    int h = 800; // CanaryMod: private -> package-private
    int i = 4; // CanaryMod: private -> package-private
    private OEntity j;
    int k = 6; // CanaryMod: private -> package-private
    int l = 16; // CanaryMod: private -> package-private
    int m = 4; // CanaryMod: private -> package-private

    MobSpawnerLogic logic;

    public OMobSpawnerBaseLogic() {}

    public String e() {
        if (this.i() == null) {
            if (this.a.equals("Minecart")) {
                this.a = "MinecartRideable";
            }

            return this.a;
        } else {
            return this.i().c;
        }
    }

    public void a(String s) {
        this.a = s;
    }

    public boolean f() {
        return this.a().a((double) this.b() + 0.5D, (double) this.c() + 0.5D, (double) this.d() + 0.5D, (double) this.l) != null;
    }

    public void g() {
        if (this.f()) {
            double d0;

            if (this.a().I) {
                double d1 = (double) ((float) this.b() + this.a().s.nextFloat());
                double d2 = (double) ((float) this.c() + this.a().s.nextFloat());

                d0 = (double) ((float) this.d() + this.a().s.nextFloat());
                this.a().a("smoke", d1, d2, d0, 0.0D, 0.0D, 0.0D);
                this.a().a("flame", d1, d2, d0, 0.0D, 0.0D, 0.0D);
                if (this.b > 0) {
                    --this.b;
                }

                this.d = this.c;
                this.c = (this.c + (double) (1000.0F / ((float) this.b + 200.0F))) % 360.0D;
            } else {
                if (this.b == -1) {
                    this.j();
                }

                if (this.b > 0) {
                    --this.b;
                    return;
                }

                boolean flag = false;

                for (int i = 0; i < this.i; ++i) {
                    OEntity oentity = OEntityList.a(this.e(), this.a());

                    if (oentity == null) {
                        return;
                    }

                    int j = this.a().a(oentity.getClass(), OAxisAlignedBB.a().a((double) this.b(), (double) this.c(), (double) this.d(), (double) (this.b() + 1), (double) (this.c() + 1), (double) (this.d() + 1)).b((double) (this.m * 2), 4.0D, (double) (this.m * 2))).size();

                    if (j >= this.k) {
                        this.j();
                        return;
                    }

                    d0 = (double) this.b() + (this.a().s.nextDouble() - this.a().s.nextDouble()) * (double) this.m;
                    double d3 = (double) (this.c() + this.a().s.nextInt(3) - 1);
                    double d4 = (double) this.d() + (this.a().s.nextDouble() - this.a().s.nextDouble()) * (double) this.m;
                    OEntityLiving oentityliving = oentity instanceof OEntityLiving ? (OEntityLiving) oentity : null;

                    oentity.b(d0, d3, d4, this.a().s.nextFloat() * 360.0F, 0.0F);
                    if (oentityliving == null || oentityliving.bv()) {
                        this.a(oentity);
                        this.a().e(2004, this.b(), this.c(), this.d(), 0);
                        if (oentityliving != null) {
                            // CanaryMod - set spawner block for spawned entity
                            oentityliving.spawner = this.logic;
                            oentityliving.aU();
                        }

                        flag = true;
                    }
                }

                if (flag) {
                    this.j();
                }
            }
        }
    }

    public OEntity a(OEntity oentity) {
        if (this.i() != null) {
            ONBTTagCompound onbttagcompound = new ONBTTagCompound();

            oentity.d(onbttagcompound);
            Iterator iterator = this.i().b.c().iterator();

            while (iterator.hasNext()) {
                ONBTBase onbtbase = (ONBTBase) iterator.next();

                onbttagcompound.a(onbtbase.e(), onbtbase.b());
            }

            oentity.f(onbttagcompound);
            if (oentity.q != null) {
                oentity.q.d(oentity);
            }

            ONBTTagCompound onbttagcompound1;

            for (OEntity oentity1 = oentity; onbttagcompound.b("Riding"); onbttagcompound = onbttagcompound1) {
                onbttagcompound1 = onbttagcompound.l("Riding");
                OEntity oentity2 = OEntityList.a(onbttagcompound1.i("id"), this.a());

                if (oentity2 != null) {
                    ONBTTagCompound onbttagcompound2 = new ONBTTagCompound();

                    oentity2.d(onbttagcompound2);
                    Iterator iterator1 = onbttagcompound1.c().iterator();

                    while (iterator1.hasNext()) {
                        ONBTBase onbtbase1 = (ONBTBase) iterator1.next();

                        onbttagcompound2.a(onbtbase1.e(), onbtbase1.b());
                    }

                    oentity2.f(onbttagcompound2);
                    oentity2.b(oentity1.u, oentity1.v, oentity1.w, oentity1.A, oentity1.B);
                    this.a().d(oentity2);
                    oentity1.a(oentity2);
                }

                oentity1 = oentity2;
            }
        } else if (oentity instanceof OEntityLiving && oentity.q != null) {
            ((OEntityLiving) oentity).bJ();
            this.a().d(oentity);
        }

        return oentity;
    }

    private void j() {
        if (this.h <= this.g) {
            this.b = this.g;
        } else {
            int i = this.h - this.g;

            this.b = this.g + this.a().s.nextInt(i);
        }

        if (this.e != null && this.e.size() > 0) {
            this.a((OWeightedRandomMinecart) OWeightedRandom.a(this.a().s, (Collection) this.e));
        }

        this.a(1);
    }

    public void a(ONBTTagCompound onbttagcompound) {
        this.a = onbttagcompound.i("EntityId");
        this.b = onbttagcompound.d("Delay");
        if (onbttagcompound.b("SpawnPotentials")) {
            this.e = new ArrayList();
            ONBTTagList onbttaglist = onbttagcompound.m("SpawnPotentials");

            for (int i = 0; i < onbttaglist.c(); ++i) {
                this.e.add(new OWeightedRandomMinecart(this, (ONBTTagCompound) onbttaglist.b(i)));
            }
        } else {
            this.e = null;
        }

        if (onbttagcompound.b("SpawnData")) {
            this.a(new OWeightedRandomMinecart(this, onbttagcompound.l("SpawnData"), this.a));
        } else {
            this.a((OWeightedRandomMinecart) null);
        }

        if (onbttagcompound.b("MinSpawnDelay")) {
            this.g = onbttagcompound.d("MinSpawnDelay");
            this.h = onbttagcompound.d("MaxSpawnDelay");
            this.i = onbttagcompound.d("SpawnCount");
        }

        if (onbttagcompound.b("MaxNearbyEntities")) {
            this.k = onbttagcompound.d("MaxNearbyEntities");
            this.l = onbttagcompound.d("RequiredPlayerRange");
        }

        if (onbttagcompound.b("SpawnRange")) {
            this.m = onbttagcompound.d("SpawnRange");
        }

        if (this.a() != null && this.a().I) {
            this.j = null;
        }
    }

    public void b(ONBTTagCompound onbttagcompound) {
        onbttagcompound.a("EntityId", this.e());
        onbttagcompound.a("Delay", (short) this.b);
        onbttagcompound.a("MinSpawnDelay", (short) this.g);
        onbttagcompound.a("MaxSpawnDelay", (short) this.h);
        onbttagcompound.a("SpawnCount", (short) this.i);
        onbttagcompound.a("MaxNearbyEntities", (short) this.k);
        onbttagcompound.a("RequiredPlayerRange", (short) this.l);
        onbttagcompound.a("SpawnRange", (short) this.m);
        if (this.i() != null) {
            onbttagcompound.a("SpawnData", (ONBTTagCompound) this.i().b.b());
        }

        if (this.i() != null || this.e != null && this.e.size() > 0) {
            ONBTTagList onbttaglist = new ONBTTagList();

            if (this.e != null && this.e.size() > 0) {
                Iterator iterator = this.e.iterator();

                while (iterator.hasNext()) {
                    OWeightedRandomMinecart oweightedrandomminecart = (OWeightedRandomMinecart) iterator.next();

                    onbttaglist.a((ONBTBase) oweightedrandomminecart.a());
                }
            } else {
                onbttaglist.a((ONBTBase) this.i().a());
            }

            onbttagcompound.a("SpawnPotentials", (ONBTBase) onbttaglist);
        }
    }

    public boolean b(int i) {
        if (i == 1 && this.a().I) {
            this.b = this.g;
            return true;
        } else {
            return false;
        }
    }

    public OWeightedRandomMinecart i() {
        return this.f;
    }

    public void a(OWeightedRandomMinecart oweightedrandomminecart) {
        this.f = oweightedrandomminecart;
    }

    public abstract void a(int i);

    public abstract OWorld a();

    public abstract int b();

    public abstract int c();

    public abstract int d();
}
