import java.util.Random;

final class ODispenserBehaviorFireball extends OBehaviorDefaultDispenseItem {

    ODispenserBehaviorFireball() {}

    public OItemStack b(OIBlockSource oiblocksource, OItemStack oitemstack) {
        OEnumFacing oenumfacing = OBlockDispenser.j_(oiblocksource.h());
        OIPosition oiposition = OBlockDispenser.a(oiblocksource);
        double d0 = oiposition.a() + (double) ((float) oenumfacing.c() * 0.3F);
        double d1 = oiposition.b() + (double) ((float) oenumfacing.c() * 0.3F);
        double d2 = oiposition.c() + (double) ((float) oenumfacing.e() * 0.3F);
        OWorld oworld = oiblocksource.k();
        Random random = oworld.s;
        double d3 = random.nextGaussian() * 0.05D + (double) oenumfacing.c();
        double d4 = random.nextGaussian() * 0.05D + (double) oenumfacing.d();
        double d5 = random.nextGaussian() * 0.05D + (double) oenumfacing.e();

        OEntitySmallFireball oentitysmallfireball = new OEntitySmallFireball(oworld, d0, d1, d2, d3, d4, d5);
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) oiblocksource.j()), new BaseEntity(oentitysmallfireball))) {
            oworld.d((OEntity) oentitysmallfireball);
            oitemstack.a(1);
        }
        return oitemstack;
    }

    protected void a(OIBlockSource oiblocksource) {
        oiblocksource.k().e(1009, oiblocksource.d(), oiblocksource.e(), oiblocksource.f(), 0);
    }
}
