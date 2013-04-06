/**
 * Interface for a projectile
 *
 * @author gregthegeek
 *
 */
public class Projectile extends BaseEntity {

    /**
     * Wraps a projectile
     *
     * @param base the projectile to wrap
     */
    public Projectile(OEntity base) {
        super(base);
    }

    /**
     * Launch/shoot this projectile
     */
    public void launch() {
        getWorld().launchProjectile(this);
    }

    /**
     * Returns the shooter of this projectile. null if none.
     * Note: not all projectiles keep track of shooter.
     *
     * @return
     */
    public BaseEntity getShooter() {
        OEntity me = getEntity();
        if(me instanceof OEntityThrowable) {
            return ((OEntityThrowable) me).h() == null ? null : ((OEntityThrowable) me).h().getEntity();
        } else if(me instanceof OEntityArrow) {
            return ((OEntityArrow) me).c == null ? null : ((OEntityArrow) me).c.getEntity();
        } else if(me instanceof OEntityFireball) {
            return ((OEntityFireball) me).a == null ? null : ((OEntityFireball) me).a.getEntity();
        }
        return null;
    }

    /**
     * Sets the shooter of this projectile.
     * Note: not all projectiles keep track of shooter.
     *
     * @return whether or not the operation was successful
     */
    public boolean setShooter(LivingEntity shooter) {
        OEntity me = getEntity();
        if(me instanceof OEntityThrowable) {
            ((OEntityThrowable) me).setShooter(shooter.getEntity());
            return true;
        } else if(me instanceof OEntityArrow) {
            ((OEntityArrow) me).c = shooter.getEntity();
            return true;
        } else if(me instanceof OEntityFireball) {
            ((OEntityFireball) me).a = shooter.getEntity();
            return true;
        }
        return false;
    }

    /**
     * Aims this projectile at a location.
     * May not work so well on anything that's not an instance of OIProjectile.
     *
     * @param x The x coordinate at which to aim it.
     * @param y The y coordinate at which to aim it.
     * @param z The z coordinate at which to aim it.
     * @param power The power that it will be fired at.
     * @param inaccuracy The inaccuracy with which it will be fired.
     */
    public void aimAt(double x, double y, double z, float power, float inaccuracy) { //pretty much copied directly from OEntityArrow.c()
        double var6 = x - entity.u;
        double var8 = y + (double)0 - 0.699999988079071D - entity.v;
        double var10 = z - entity.w;
        double var12 = (double)OMathHelper.a(var6 * var6 + var10 * var10);
        if(var12 >= 1.0E-7D) {
           float var14 = (float)(Math.atan2(var10, var6) * 180.0D / 3.1415927410125732D) - 90.0F;
           float var15 = (float)(-(Math.atan2(var8, var12) * 180.0D / 3.1415927410125732D));
           double var16 = var6 / var12;
           double var18 = var10 / var12;
           entity.b(entity.u + var16, entity.v, entity.w + var18, var14, var15);
           entity.N = 0.0F;
           if(getEntity() instanceof OIProjectile) {
               float var20 = (float)var12 * 0.2F;
                  ((OIProjectile) getEntity()).c(var6, var8 + (double)var20, var10, power, inaccuracy);
           }
        }
    }

    /**
     * Aims this projectile at a location.
     * May not work so well on anything that's not an instance of OIProjectile.
     *
     * @param location The location at which to aim it.
     * @param power The power that it will be fired at.
     * @param inaccuracy The inaccuracy with which it will be fired.
     */
    public void aimAt(Location location, float power, float inaccuracy) {
        aimAt(location.x, location.y, location.z, power, inaccuracy);
    }
}
