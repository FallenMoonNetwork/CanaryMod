import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Enchantment - a class used to access all enchantment related stuff
 *
 * @author Yariv
 *
 */
public class Enchantment {

    /**
     * Type - used to identify enchantments
     *
     * @author Yariv
     *
     */
    public enum Type {
        Protection(0), //
        FireProtection(1), //
        FeatherFalling(2), //
        BlastProtection(3), //
        ProjectileProtection(4), //
        Respiration(5), //
        AquaAffinity(6), //
        Thorns(7), //
        Sharpness(16), //
        Smite(17), //
        BaneOfArthropods(18), //
        Knockback(19), //
        FireAspect(20), //
        Looting(21), //
        Efficiency(32), //
        SilkTouch(33), //
        Unbreaking(34), //
        Fortune(35), //
        ArrowDamage(48), //
        ArrowKnockback(49), //
        ArrowFire(50), //
        ArrowInfinite(51);

        private int                       id;
        private static Map<Integer, Type> map;

        private Type(int id) {
            this.id = id;
            add(id, this);
        }

        private static void add(int type, Type name) {
            if (map == null) {
                map = new HashMap<Integer, Type>();
            }

            map.put(type, name);
        }

        public int getType() {
            return id;
        }

        public static Type fromId(final int type) {
            return map.get(type);
        }
    }

    private Type type;
    private int  level;

    /**
     * Creates a new enchantment
     *
     * @param type
     * @param level
     */
    public Enchantment(Type type, int level) {
        this.type = type;
        this.level = level;
    }

    /**
     * Returns the OEnchantment object for this enchantment.
     * Notice this isnt the enchantment data, but the enchantment description.
     * @return Returns the OEnchantment object for this enchantment
     */
    public OEnchantment getEnchantment()
    {
        if (this.type.getType() >= 0 && this.type.getType() < OEnchantment.b.length) {
            OEnchantment enchantmentType = OEnchantment.b[this.type.getType()];
            if (enchantmentType != null)
            {
                return enchantmentType;
            }
        }
        return null;
    }

    /**
     * Returns an OEnchantment object for a given enchantment type.
     * Notice this isnt the enchantment data, but the enchantment description.
     * @return Returns the OEnchantment object for a given enchantment type
     */
    public static OEnchantment getEnchantment(Type type)
    {
        if (type != null)
        {
            if (type.getType() >= 0 && type.getType() < OEnchantment.b.length) {
                OEnchantment enchantmentType = OEnchantment.b[type.getType()];
                if (enchantmentType != null)
                {
                    return enchantmentType;
                }
            }
        }
        return null;
    }

    /**
     * Type of enchantment.
     *
     * @return type
     */
    public Type getType() {
        return type;
    }

    /**
     * Set type of enchantment.
     * Note that it does NOT check for validity of the enchantment!!!
     *
     * @param type
     */
    public void setType(int type) {
        this.type = Type.fromId(type);
    }

    /**
     * Set type of enchantment.
     * Note that it does NOT check for validity of the enchantment!!!
     *
     * @param type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Gets level of enchantment.
     *
     * @return
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Sets level of enchantment.
     * Note that it does NOT check for validity of the enchantment!!!
     *
     * @param level
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Gets the weight of the enchantment or -1 if the enchantment is invalid.
     * @return
     */
    public int getWeight()
    {
        OEnchantment enchantment = getEnchantment();
        if (enchantment != null)
        {
            return enchantment.c();
        }
        return -1;
    }

    /**
     * Gets the weight of an enchantment given its type or -1 if the
     * enchantment is invalid.
     * @return
     */
    public static int getWeight(Type type)
    {
        OEnchantment enchantment = getEnchantment(type);
        if (enchantment != null)
        {
            return enchantment.c();
        }
        return -1;
    }

    /**
     * Gets the minimum level of the enchantment or -1 if the enchantment is
     * invalid.
     * @return
     */
    public int getMinLevel()
    {
        OEnchantment enchantment = getEnchantment();
        if (enchantment != null)
        {
            return enchantment.d();
        }
        return -1;
    }

    /**
     * Gets the minimum level of an enchantment given its type or -1 if the
     * enchantment is invalid.
     * @return
     */
    public static int getMinLevel(Type type)
    {
        OEnchantment enchantment = getEnchantment(type);
        if (enchantment != null)
        {
            return enchantment.d();
        }
        return -1;
    }

    /**
     * Gets the maximum level of the enchantment or -1 if the enchantment is
     * invalid.
     * @return
     */
    public int getMaxLevel()
    {
        OEnchantment enchantment = getEnchantment();
        if (enchantment != null)
        {
            return enchantment.b();
        }
        return -1;
    }

    /**
     * Gets the maximum level of an enchantment given its type or -1 if the
     * enchantment is invalid.
     * @return
     */
    public static int getMaxLevel(Type type)
    {
        OEnchantment enchantment = getEnchantment(type);
        if (enchantment != null)
        {
            return enchantment.b();
        }
        return -1;
    }

    /**
     * Gets the minimum enchantability strength needed for the enchantment or
     * -1 if the enchantment is invalid.
     * @return
     */
    public int getMinEnchantability()
    {
        OEnchantment enchantment = getEnchantment();
        if (enchantment != null)
        {
            return enchantment.a(this.level);
        }
        return -1;
    }

    /**
     * Gets the minimum enchantability strength needed for the enchantment
     * given its type or -1 if the enchantment is invalid.
     * @return
     */
    public static int getMinEnchantability(Type type, int level)
    {
        OEnchantment enchantment = getEnchantment(type);
        if (enchantment != null)
        {
            return enchantment.a(level);
        }
        return -1;
    }

    /**
     * Gets the maximum enchantability strength needed for the enchantment or
     * -1 if the enchantment is invalid.
     * @return
     */
    public int getMaxEnchantability()
    {
        OEnchantment enchantment = getEnchantment();
        if (enchantment != null)
        {
            return enchantment.b(this.level);
        }
        return -1;
    }

    /**
     * Gets the maximum enchantability strength needed for the enchantment
     * given its type or -1 if the enchantment is invalid.
     * @return
     */
    public static int getMaxEnchantability(Type type, int level)
    {
        OEnchantment enchantment = getEnchantment(type);
        if (enchantment != null)
        {
            return enchantment.b(level);
        }
        return -1;
    }

    /**
     * Gets the modified damage for the enchantment for a given damage source
     * or -1 if the enchantment or damage source is invalid.
     * @return
     */
    public int getDamage(ODamageSource damageSource)
    {
        if (damageSource != null)
        {
            OEnchantment enchantment = getEnchantment();
            if (enchantment != null)
            {
                return enchantment.a(this.level, damageSource);
            }
        }
        return -1;
    }

    /**
     * Gets the modified damage for an enchantment for a given damage source or
     * -1 if the enchantment or damage source is invalid.
     * @return
     */
    public static int getDamage(Type type, int level, ODamageSource damageSource)
    {
        if (damageSource != null)
        {
            OEnchantment enchantment = getEnchantment(type);
            if (enchantment != null)
            {
                return enchantment.a(level, damageSource);
            }
        }
        return -1;
    }

    /**
     * Gets the modified living for the enchantment for a given living entity
     * or -1 if the enchantment or entity is invalid.
     * @return
     */
    public int getLiving(OEntityLiving entity)
    {
        if (entity != null)
        {
            OEnchantment enchantment = getEnchantment();
            if (enchantment != null)
            {
                return enchantment.a(this.level, entity);
            }
        }
        return -1;
    }

    /**
     * Gets the modified living for an enchantment for a given living entity or
     * -1 if the enchantment or entity is invalid.
     * @return
     */
    public static int getLiving(Type type, int level, OEntityLiving entity)
    {
        if (entity != null)
        {
            OEnchantment enchantment = getEnchantment(type);
            if (enchantment != null)
            {
                return enchantment.a(level, entity);
            }
        }
        return -1;
    }

    /**
     * Returns true if this enchantment can stack with another enchantment.
     * @param enchantment The other enchantment
     * @return true if enchantments can stack
     */
    public boolean canStack(Enchantment enchantment)
    {
        return canStack(enchantment.getType());
    }

    /**
     * Returns true if this enchantment can stack with another enchantment.
     * @param type The other enchantment type
     * @return true if enchantments can stack
     */
    public boolean canStack(Type type)
    {
        OEnchantment enchantment = getEnchantment(this.type);
        if (enchantment != null)
        {
            OEnchantment otherEnchantment = getEnchantment(type);
            if (otherEnchantment != null)
            {
                return enchantment.a(otherEnchantment);
            }
        }
        return false;
    }

    /**
     * Returns true if two enchantments can stack with each other.
     * @param type1
     * @param type2
     * @return true if enchantments can stack
     */
    public static boolean canStack(Type type1, Type type2)
    {
        OEnchantment enchantment = getEnchantment(type1);
        if (enchantment != null)
        {
            OEnchantment otherEnchantment = getEnchantment(type2);
            if (otherEnchantment != null)
            {
                return enchantment.a(otherEnchantment);
            }
        }
        return false;
    }

    /**
     * Checks if the enchantment is valid.
     *
     * @return true if valid
     */
    public boolean isValid() {
        return isValid(this.type, this.level);
    }

    /**
     * Checks if an enchantment is valid.
     *
     * @param type
     *            The type of the enchantment
     * @param level
     *            The level of the enchantment
     * @return true if valid
     */
    public static boolean isValid(Type type, int level) {
        if (type != null)
        {
            OEnchantment enchantmentType = getEnchantment(type);
            if (enchantmentType != null)
            {
                return (level >= getMinLevel(type) && level <= getMaxLevel(type));
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Enchantment) {
            Enchantment other = (Enchantment) obj;
            return other.getType() == getType() && other.getLevel() == getLevel();
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Enchantment[type=%s, level=%d]", getType(), getLevel());
    }
}
