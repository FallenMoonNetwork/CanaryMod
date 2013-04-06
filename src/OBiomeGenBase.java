import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class OBiomeGenBase {

    public static final OBiomeGenBase[] a = new OBiomeGenBase[256];
    public static final OBiomeGenBase b = (new OBiomeGenOcean(0)).b(112).a("Ocean").b(-1.0F, 0.4F);
    public static final OBiomeGenBase c = (new OBiomeGenPlains(1)).b(9286496).a("Plains").a(0.8F, 0.4F);
    public static final OBiomeGenBase d = (new OBiomeGenDesert(2)).b(16421912).a("Desert").m().a(2.0F, 0.0F).b(0.1F, 0.2F);
    public static final OBiomeGenBase e = (new OBiomeGenHills(3)).b(6316128).a("Extreme Hills").b(0.3F, 1.5F).a(0.2F, 0.3F);
    public static final OBiomeGenBase f = (new OBiomeGenForest(4)).b(353825).a("Forest").a(5159473).a(0.7F, 0.8F);
    public static final OBiomeGenBase g = (new OBiomeGenTaiga(5)).b(747097).a("Taiga").a(5159473).b().a(0.05F, 0.8F).b(0.1F, 0.4F);
    public static final OBiomeGenBase h = (new OBiomeGenSwamp(6)).b(522674).a("Swampland").a(9154376).b(-0.2F, 0.1F).a(0.8F, 0.9F);
    public static final OBiomeGenBase i = (new OBiomeGenRiver(7)).b(255).a("River").b(-0.5F, 0.0F);
    public static final OBiomeGenBase j = (new OBiomeGenHell(8)).b(16711680).a("Hell").m().a(2.0F, 0.0F);
    public static final OBiomeGenBase k = (new OBiomeGenEnd(9)).b(8421631).a("Sky").m();
    public static final OBiomeGenBase l = (new OBiomeGenOcean(10)).b(9474208).a("FrozenOcean").b().b(-1.0F, 0.5F).a(0.0F, 0.5F);
    public static final OBiomeGenBase m = (new OBiomeGenRiver(11)).b(10526975).a("FrozenRiver").b().b(-0.5F, 0.0F).a(0.0F, 0.5F);
    public static final OBiomeGenBase n = (new OBiomeGenSnow(12)).b(16777215).a("Ice Plains").b().a(0.0F, 0.5F);
    public static final OBiomeGenBase o = (new OBiomeGenSnow(13)).b(10526880).a("Ice Mountains").b().b(0.3F, 1.3F).a(0.0F, 0.5F);
    public static final OBiomeGenBase p = (new OBiomeGenMushroomIsland(14)).b(16711935).a("MushroomIsland").a(0.9F, 1.0F).b(0.2F, 1.0F);
    public static final OBiomeGenBase q = (new OBiomeGenMushroomIsland(15)).b(10486015).a("MushroomIslandShore").a(0.9F, 1.0F).b(-1.0F, 0.1F);
    public static final OBiomeGenBase r = (new OBiomeGenBeach(16)).b(16440917).a("Beach").a(0.8F, 0.4F).b(0.0F, 0.1F);
    public static final OBiomeGenBase s = (new OBiomeGenDesert(17)).b(13786898).a("DesertHills").m().a(2.0F, 0.0F).b(0.3F, 0.8F);
    public static final OBiomeGenBase t = (new OBiomeGenForest(18)).b(2250012).a("ForestHills").a(5159473).a(0.7F, 0.8F).b(0.3F, 0.7F);
    public static final OBiomeGenBase u = (new OBiomeGenTaiga(19)).b(1456435).a("TaigaHills").b().a(5159473).a(0.05F, 0.8F).b(0.3F, 0.8F);
    public static final OBiomeGenBase v = (new OBiomeGenHills(20)).b(7501978).a("Extreme Hills Edge").b(0.2F, 0.8F).a(0.2F, 0.3F);
    public static final OBiomeGenBase w = (new OBiomeGenJungle(21)).b(5470985).a("Jungle").a(5470985).a(1.2F, 0.9F).b(0.2F, 0.4F);
    public static final OBiomeGenBase x = (new OBiomeGenJungle(22)).b(2900485).a("JungleHills").a(5470985).a(1.2F, 0.9F).b(1.8F, 0.5F);
    public String y;
    public int z;
    public byte A;
    public byte B;
    public int C;
    public float D;
    public float E;
    public float F;
    public float G;
    public int H;
    public OBiomeDecorator I;
    protected List J;
    protected List K;
    protected List L;
    protected List M;
    private boolean S;
    private boolean T;
    public final int N;
    protected OWorldGenTrees O;
    protected OWorldGenBigTree P;
    protected OWorldGenForest Q;
    protected OWorldGenSwamp R;

    protected OBiomeGenBase(int i) {
        this.A = (byte) OBlock.y.cz;
        this.B = (byte) OBlock.z.cz;
        this.C = 5169201;
        this.D = 0.1F;
        this.E = 0.3F;
        this.F = 0.5F;
        this.G = 0.5F;
        this.H = 16777215;
        this.J = new ArrayList();
        this.K = new ArrayList();
        this.L = new ArrayList();
        this.M = new ArrayList();
        this.T = true;
        this.O = new OWorldGenTrees(false);
        this.P = new OWorldGenBigTree(false);
        this.Q = new OWorldGenForest(false);
        this.R = new OWorldGenSwamp();
        this.N = i;
        a[i] = this;
        this.I = this.a();
        this.K.add(new OSpawnListEntry(OEntitySheep.class, 12, 4, 4));
        this.K.add(new OSpawnListEntry(OEntityPig.class, 10, 4, 4));
        this.K.add(new OSpawnListEntry(OEntityChicken.class, 10, 4, 4));
        this.K.add(new OSpawnListEntry(OEntityCow.class, 8, 4, 4));
        this.J.add(new OSpawnListEntry(OEntitySpider.class, 10, 4, 4));
        this.J.add(new OSpawnListEntry(OEntityZombie.class, 10, 4, 4));
        this.J.add(new OSpawnListEntry(OEntitySkeleton.class, 10, 4, 4));
        this.J.add(new OSpawnListEntry(OEntityCreeper.class, 10, 4, 4));
        this.J.add(new OSpawnListEntry(OEntitySlime.class, 10, 4, 4));
        this.J.add(new OSpawnListEntry(OEntityEnderman.class, 1, 1, 4));
        this.L.add(new OSpawnListEntry(OEntitySquid.class, 10, 4, 4));
        this.M.add(new OSpawnListEntry(OEntityBat.class, 10, 8, 8));
    }

    protected OBiomeDecorator a() {
        return new OBiomeDecorator(this);
    }

    private OBiomeGenBase a(float f, float f1) {
        if (f > 0.1F && f < 0.2F) {
            throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
        } else {
            this.F = f;
            this.G = f1;
            return this;
        }
    }

    private OBiomeGenBase b(float f, float f1) {
        this.D = f;
        this.E = f1;
        return this;
    }

    private OBiomeGenBase m() {
        this.T = false;
        return this;
    }

    public OWorldGenerator a(Random random) {
        return (OWorldGenerator) (random.nextInt(10) == 0 ? this.P : this.O);
    }

    public OWorldGenerator b(Random random) {
        return new OWorldGenTallGrass(OBlock.ab.cz, 1);
    }

    protected OBiomeGenBase b() {
        this.S = true;
        return this;
    }

    protected OBiomeGenBase a(String s) {
        this.y = s;
        return this;
    }

    protected OBiomeGenBase a(int i) {
        this.C = i;
        return this;
    }

    protected OBiomeGenBase b(int i) {
        this.z = i;
        return this;
    }

    public List a(OEnumCreatureType oenumcreaturetype) {
        // CanaryMod start - responsible for adding spawned monsters to the monster list.
        etc config = etc.getInstance();

        if (oenumcreaturetype == OEnumCreatureType.a) {
            return config.getMonstersClass(this);
        }
        if (oenumcreaturetype == OEnumCreatureType.b) {
            return config.getAnimalsClass(this);
        }
        if (oenumcreaturetype == OEnumCreatureType.d) {
            return config.getWaterAnimalsClass(this);
        }
        if (oenumcreaturetype == OEnumCreatureType.c) {
            return config.getAmbientAnimalsClass(this);
        }
        return null; // CanaryMod end
    }

    public boolean c() {
        return this.S;
    }

    public boolean d() {
        return this.S ? false : this.T;
    }

    public boolean e() {
        return this.G > 0.85F;
    }

    public float f() {
        return 0.1F;
    }

    public final int g() {
        return (int) (this.G * 65536.0F);
    }

    public final int h() {
        return (int) (this.F * 65536.0F);
    }

    public final float j() {
        return this.F;
    }

    public void a(OWorld oworld, Random random, int i, int j) {
        this.I.a(oworld, random, i, j);
    }
}
