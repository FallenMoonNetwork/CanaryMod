public class HookParametersDamage extends HookParameters {
    private boolean isCanceled;
    private DamageSource ds;
    private BaseEntity attacker;
    private BaseEntity defender;
    private float amount;

    @Deprecated
    public HookParametersDamage(BaseEntity attacker, BaseEntity defender, DamageSource ds, int amount) {
        this(attacker, defender, ds, (float) amount);
    }

    public HookParametersDamage(BaseEntity attacker, BaseEntity defender, DamageSource ds, float amount) {
        this.attacker = attacker;
        this.defender = defender;
        this.amount = amount;
        this.ds = ds;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setCanceled() {
        this.isCanceled = true;
    }

    public DamageSource getDamageSource() {
        return ds;
    }

    public void setDamageSource(DamageSource ds) {
        this.ds = ds;
    }

    public BaseEntity getDefender() {
        return defender;
    }

    public BaseEntity getAttacker() {
        return attacker;
    }

    public int getDamageAmount() {
        return (int) amount;
    }

    public float getDamageAmountFloat() {
        return amount;
    }

    public void setDamageAmount(int amount) {
        this.amount = amount;
    }

    public void setDamageAmount(float amount) {
        this.amount = amount;
    }
}
