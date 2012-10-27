
final class OEnchantmentModifierDamage implements OIEnchantmentModifier {

    public int a;
    public ODamageSource b;

    private OEnchantmentModifierDamage() {
        super();
    }

    public void a(OEnchantment oenchantment, int i) {
        this.a += oenchantment.a(i, this.b);
    }

    OEnchantmentModifierDamage(OEmpty3 oempty3) {
        this();
    }
}
