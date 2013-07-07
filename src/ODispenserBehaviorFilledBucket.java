final class ODispenserBehaviorFilledBucket extends OBehaviorDefaultDispenseItem {

    private final OBehaviorDefaultDispenseItem b = new OBehaviorDefaultDispenseItem();

    ODispenserBehaviorFilledBucket() {}

    public OItemStack b(OIBlockSource oiblocksource, OItemStack oitemstack) {
        OItemBucket oitembucket = (OItemBucket) oitemstack.b();
        int i = oiblocksource.d();
        int j = oiblocksource.e();
        int k = oiblocksource.f();
        OEnumFacing oenumfacing = OBlockDispenser.l_(oiblocksource.h());

        if (oitembucket.a(oiblocksource.k(), i + oenumfacing.c(), j + oenumfacing.d(), k + oenumfacing.e())) {
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) oiblocksource.j()), null)) {
                oitemstack.d = OItem.ay.cv;
                oitemstack.b = 1;
            }
            return oitemstack;
        } else {
            return this.b.a(oiblocksource, oitemstack);
        }
    }
}
