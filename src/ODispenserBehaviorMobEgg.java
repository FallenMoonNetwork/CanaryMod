final class ODispenserBehaviorMobEgg extends OBehaviorDefaultDispenseItem {

    ODispenserBehaviorMobEgg() {}

    public OItemStack b(OIBlockSource oiblocksource, OItemStack oitemstack) {
        OEnumFacing oenumfacing = OBlockDispenser.l_(oiblocksource.h());
        double d0 = oiblocksource.a() + (double) oenumfacing.c();
        double d1 = (double) ((float) oiblocksource.e() + 0.2F);
        double d2 = oiblocksource.c() + (double) oenumfacing.e();
        OEntity oentity = OItemMonsterPlacer.a(oiblocksource.k(), oitemstack.k(), d0, d1, d2, false); // CanaryMod: don't spawn entity yet

        if (oentity instanceof OEntityLivingBase && oitemstack.u()) {
            ((OEntityLiving) oentity).a(oitemstack.s());
        }

        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) oiblocksource.j()), oentity.getEntity())) {
            oiblocksource.k().d(oentity); // CanaryMod: now we spawn the entity
            oitemstack.a(1);
        }
        return oitemstack;
    }
}
