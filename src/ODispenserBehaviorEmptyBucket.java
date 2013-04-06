final class ODispenserBehaviorEmptyBucket extends OBehaviorDefaultDispenseItem {

    private final OBehaviorDefaultDispenseItem b = new OBehaviorDefaultDispenseItem();

    ODispenserBehaviorEmptyBucket() {}

    public OItemStack b(OIBlockSource oiblocksource, OItemStack oitemstack) {
        OEnumFacing oenumfacing = OBlockDispenser.j_(oiblocksource.h());
        OWorld oworld = oiblocksource.k();
        int i = oiblocksource.d() + oenumfacing.c();
        int j = oiblocksource.e() + oenumfacing.d();
        int k = oiblocksource.f() + oenumfacing.e();
        OMaterial omaterial = oworld.g(i, j, k);
        int l = oworld.h(i, j, k);
        OItem oitem;

        if (OMaterial.h.equals(omaterial) && l == 0) {
            oitem = OItem.ay;
        } else {
            if (!OMaterial.i.equals(omaterial) || l != 0) {
                return super.b(oiblocksource, oitemstack);
            }

            oitem = OItem.az;
        }

        oworld.i(i, j, k);
        if (--oitemstack.a == 0) {
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.DISPENSE, new Dispenser((OTileEntityDispenser) oiblocksource.j()), null)) {
                oitemstack.c = oitem.cp;
                oitemstack.a = 1;
            }
        } else if (((OTileEntityDispenser) oiblocksource.j()).a(new OItemStack(oitem)) < 0) {
            this.b.a(oiblocksource, new OItemStack(oitem));
        }

        return oitemstack;
    }
}
