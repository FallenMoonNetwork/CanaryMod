
import java.util.Random;

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
        if (me instanceof OEntityThrowable) {
            // SRG return ((OEntityThrowable) me).func_85052_h() == null ? null : ((OEntityThrowable) me).func_85052_h().getEntity();
            return ((OEntityThrowable) me).h() == null ? null : ((OEntityThrowable) me).h().getEntity();
        } else if(me instanceof OEntityArrow) {
            // SRG return ((OEntityArrow) me).field_70250_c == null ? null : ((OEntityArrow) me).field_70250_c.getEntity();
            return ((OEntityArrow) me).c == null ? null : ((OEntityArrow) me).c.getEntity();
        } else if(me instanceof OEntityFireball) {
            // SRG return ((OEntityFireball) me).field_70235_a == null ? null : ((OEntityFireball) me).field_70235_a.getEntity();
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
            // SRG ((OEntityArrow) me).field_70250_c = shooter.getEntity();
            ((OEntityArrow) me).c = shooter.getEntity();
            return true;
        } else if(me instanceof OEntityFireball) {
            // SRG ((OEntityFireball) me).field_70235_a = shooter.getEntity();
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
        if (entity instanceof OIProjectile) { // We have a method to do exactly this!
            ((OIProjectile) entity).c(x - getX(), y - getY(), z - getZ(), power, inaccuracy);
        } else {
            // SRG Random random = entity.ab; // Use this entity's random generator
            Random random = entity.ab; // Use this entity's random generator
            OEntityFireball fireball = (OEntityFireball) entity;

            // Turn into vector
            x -= getX();
            y -= getY();
            z -= getZ();

            // Convert to unit vector
            double vecLen = Math.sqrt(x * x + y * y + z * z);
            x /= vecLen;
            y /= vecLen;
            z /= vecLen;

            // Add inaccuracy
            x += random.nextGaussian() * (random.nextBoolean() ? -1 : 1) * 0.0075 * inaccuracy;
            y += random.nextGaussian() * (random.nextBoolean() ? -1 : 1) * 0.0075 * inaccuracy;
            z += random.nextGaussian() * (random.nextBoolean() ? -1 : 1) * 0.0075 * inaccuracy;
            // Add power
            x *= power;
            y *= power;
            z *= power;

            // Set acceleration!
            // SRG fireball.field_70232_b = x;
            fireball.b = x;
            // SRG fireball.field_70233_c = y;
            fireball.c = y;
            // SRG fireball.field_70230_d = z;
            fireball.d = z;
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
