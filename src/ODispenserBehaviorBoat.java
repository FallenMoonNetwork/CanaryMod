final class ODispenserBehaviorBoat extends OBehaviorDefaultDispenseItem {

    private final OBehaviorDefaultDispenseItem b = new OBehaviorDefaultDispenseItem();

    ODispenserBehaviorBoat() {}

    public OItemStack b(OIBlockSource oiblocksource, OItemStack oitemstack) {
        OEnumFacing oenumfacing = OBlockDispenser.j_(oiblocksource.h());
        OWorld oworld = oiblocksource.k();
        double d0 = oiblocksource.a() + (double) ((float) oenumfacing.c() * 1.125F);
        double d1 = oiblocksource.b() + (double) ((float) oenumfacing.d() * 1.125F);
        double d2 = oiblocksource.c() + (double) ((float) oenumfacing.e() * 1.125F);
        int i = oiblocksource.d() + oenumfacing.c();
        int j = oiblocksource.e() + oenumfacing.d();
        int k = oiblocksource.f() + oenumfacing.e();
        OMaterial omaterial = oworld.g(i, j, k);
        double d3;

        if (OMaterial.h.equals(omaterial)) {
            d3 = 1.0D;
        } else {
            if (!OMaterial.a.equals(omaterial) || !OMaterial.h.equals(oworld.g(i, j - 1, k))) {
                return this.b.a(oiblocksource, oitemstack);
            }

            d3 = 0.0D;
        }

        OEntityBoat oentityboat = new OEntityBoat(oworld, d0, d1 + d3, d2);

        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) oiblocksource.j()), new Boat(oentityboat))) {
            oworld.d((OEntity) oentityboat);
            oitemstack.a(1);
        }
        return oitemstack;
    }

    protected void a(OIBlockSource oiblocksource) {
        oiblocksource.k().e(1000, oiblocksource.d(), oiblocksource.e(), oiblocksource.f(), 0);
    }
}
