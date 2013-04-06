final class ODispenserBehaviorFireworks extends OBehaviorDefaultDispenseItem {

    ODispenserBehaviorFireworks() {}

    public OItemStack b(OIBlockSource oiblocksource, OItemStack oitemstack) {
        OEnumFacing oenumfacing = OBlockDispenser.j_(oiblocksource.h());
        double d0 = oiblocksource.a() + (double) oenumfacing.c();
        double d1 = (double) ((float) oiblocksource.e() + 0.2F);
        double d2 = oiblocksource.c() + (double) oenumfacing.e();
        OEntityFireworkRocket oentityfireworkrocket = new OEntityFireworkRocket(oiblocksource.k(), d0, d1, d2, oitemstack);

        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) oiblocksource.j()), new Firework(oentityfireworkrocket))) { //CanaryMod: call dispense hook
            oiblocksource.k().d((OEntity) oentityfireworkrocket);
            oitemstack.a(1);
        }
        return oitemstack;
    }

    protected void a(OIBlockSource oiblocksource) {
        oiblocksource.k().e(1002, oiblocksource.d(), oiblocksource.e(), oiblocksource.f(), 0);
    }
}
