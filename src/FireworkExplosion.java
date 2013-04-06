import java.awt.Color;
import java.util.Arrays;

/**
 * Interface for FireworkExplosions.
 *
 * @author gregthegeek
 *
 */
public class FireworkExplosion {
    public enum Shape {
        SMALL_BALL,
        LARGE_BALL,
        STAR,
        CREEPER,
        BURST;
    }

    public Shape shape;
    public Color[] colors;
    public boolean twinkle = false;
    public boolean trail = false;

    /**
     * Creates a new FireworkExplosion.
     *
     * @param shape The shape of the explosion.
     * @param colors The colors of the explosion.
     */
    public FireworkExplosion(Shape shape, Color... colors) {
        this.shape = shape;
        this.colors = colors;
    }

    /**
     * Creates a new FireworkExplosion.
     *
     * @param shape The shape of the explosion.
     * @param twinkle Whether or not this explosion twinkles.
     * @param trail Whether or not this explosion leaves trails.
     * @param colors The colors of the explosion.
     */
    public FireworkExplosion(Shape shape, boolean twinkle, boolean trail, Color... colors) {
        this(shape, colors);
        this.twinkle = twinkle;
        this.trail = trail;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof FireworkExplosion) {
            FireworkExplosion ex = (FireworkExplosion) obj;
            return shape == ex.shape && twinkle == ex.twinkle && trail == ex.trail;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("FireworkExplosion[shape=%s, colors=%s, extra=%s]", shape.toString(), Arrays.toString(colors), twinkle && trail ? "twinkle and trail" : (twinkle ? "twinkle" : (trail ? "trail" : "none")));
    }

    /**
     * Returns this explosion in NBTTagCompound form.
     *
     * @return NBTTagCompound.
     */
    public NBTTagCompound toNBT() {
        NBTTagCompound tag = new NBTTagCompound("");
        tag.add("Type", (byte) shape.ordinal());
        int[] c = new int[colors.length];
        for(int i=0; i<c.length; i++) {
            c[i] = colors[i].getRGB();
        }
        tag.add("Colors", c);
        if(twinkle) {tag.add("Flicker", (byte) 1);}
        if(trail) {tag.add("Trail", (byte) 1);}
        return tag;
    }
}
