public abstract class OBehaviorProjectileDispense extends OBehaviorDefaultDispenseItem {

    public OBehaviorProjectileDispense() {}

    public OItemStack b(OIBlockSource oiblocksource, OItemStack oitemstack) {
        OWorld oworld = oiblocksource.k();
        OIPosition oiposition = OBlockDispenser.a(oiblocksource);
        OEnumFacing oenumfacing = OBlockDispenser.j_(oiblocksource.h());
        OIProjectile oiprojectile = this.a(oworld, oiposition);

        oiprojectile.c((double) oenumfacing.c(), (double) ((float) oenumfacing.d() + 0.1F), (double) oenumfacing.e(), this.b(), this.a());
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) oiblocksource.j()), new Projectile((OEntity) oiprojectile))) {
            oworld.d((OEntity) oiprojectile);
            oitemstack.a(1);
        }
        return oitemstack;
    }

    protected void a(OIBlockSource oiblocksource) {
        oiblocksource.k().e(1002, oiblocksource.d(), oiblocksource.e(), oiblocksource.f(), 0);
    }

    protected abstract OIProjectile a(OWorld oworld, OIPosition oiposition);

    protected float a() {
        return 6.0F;
    }

    protected float b() {
        return 1.1F;
    }
}
