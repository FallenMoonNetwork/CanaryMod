import java.util.Random;

public class OBlockCactus extends OBlock {

    protected OBlockCactus(int i) {
        super(i, OMaterial.y);
        this.b(true);
        this.a(OCreativeTabs.c);
    }

    @Override
    public void a(OWorld oworld, int i, int j, int k, Random random) {
        if (oworld.c(i, j + 1, k)) {
            int l;

            for (l = 1; oworld.a(i, j - l, k) == this.cz; ++l) {
                ;
            }

            if (l < 3) {
                int i1 = oworld.h(i, j, k);

                if (i1 == 15) {
                    oworld.c(i, j + 1, k, this.cz);
                    oworld.b(i, j, k, 0, 4);
                    this.a(oworld, i, j + 1, k, this.cz);
                } else {
                    oworld.b(i, j, k, i1 + 1, 4);
                }
            }
        }
    }

    @Override
    public OAxisAlignedBB b(OWorld oworld, int i, int j, int k) {
        float f = 0.0625F;

        return OAxisAlignedBB.a().a((double) ((float) i + f), (double) j, (double) ((float) k + f), (double) ((float) (i + 1) - f), (double) ((float) (j + 1) - f), (double) ((float) (k + 1) - f));
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    public boolean c() {
        return false;
    }

    @Override
    public int d() {
        return 13;
    }

    @Override
    public boolean c(OWorld oworld, int i, int j, int k) {
        return !super.c(oworld, i, j, k) ? false : this.f(oworld, i, j, k);
    }

    @Override
    public void a(OWorld oworld, int i, int j, int k, int l) {
        if (!this.f(oworld, i, j, k)) {
            oworld.a(i, j, k, true);
        }
    }

    @Override
    public boolean f(OWorld oworld, int i, int j, int k) {
        if (oworld.g(i - 1, j, k).a()) {
            return false;
        } else if (oworld.g(i + 1, j, k).a()) {
            return false;
        } else if (oworld.g(i, j, k - 1).a()) {
            return false;
        } else if (oworld.g(i, j, k + 1).a()) {
            return false;
        } else {
            int l = oworld.a(i, j - 1, k);

            return l == OBlock.aZ.cz || l == OBlock.I.cz;
        }
    }

    @Override
    public void a(OWorld oworld, int i, int j, int k, OEntity oentity) {
        // CanaryMod Damage hook: Cactus
        HookParametersDamage ev = (HookParametersDamage) etc.getLoader().callHook(PluginLoader.Hook.DAMAGE, new HookParametersDamage(null, oentity.getEntity(), DamageType.CACTUS.getDamageSource(), 1));
        if (ev.isCanceled()) {
            return;
        }
        oentity.a(ev.getDamageSource().getDamageSource(), ev.getDamageAmount());
    }
}
