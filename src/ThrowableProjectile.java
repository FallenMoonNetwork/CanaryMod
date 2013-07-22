public class ThrowableProjectile extends Projectile {

    public ThrowableProjectile(OEntityThrowable base) {
        super(base);
    }

    @Override
    public LivingEntityBase getShooter() {
        // SRG return getEntity().func_85052_h() == null ? null : getEntity().func_85052_h().getEntity();
        return getEntity().h() == null ? null : getEntity().h().getEntity();
    }

    @Override
    public boolean setShooter(LivingEntity shooter) {
        getEntity().setShooter(shooter.getEntity());
        return true;
    }

    @Override
    public OEntityThrowable getEntity() {
        return (OEntityThrowable) entity;
    }

}
