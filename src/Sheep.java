/**
 * Wrap around an OEntitySheep to provide extra methods not available to anything but sheep.
 * Mostly wool options.
 *
 * @author Brian McCarthy
 *
 */
public class Sheep extends Mob {

    /**
     * Basic sheep constructor.
     *
     * @param entity An instance of OEntitySheep to wrap around.
     */
    public Sheep(OEntitySheep entity) {
        super(entity);
    }

    /**
     * Set if the sheep has wool.
     *
     * @param sheared Sheared or not
     */
    public void setSheared(boolean sheared){
        getEntity().f(sheared);
    }

    /**
     * Returns if the sheep has wool.
     *
     * @return If this sheep has wool.
     */
    public boolean isSheared(){
        return getEntity().n();
    }

    /**
     * Set the color of the wool.
     *
     * @param color int value of the new color.
     */
    public void setWoolColor(int color){
        getEntity().s(color);
    }

    /**
     * Returns an int of the color of the wool.
     *
     * @return int value of wool color.
     */
    public int getColor(){
        return getEntity().m();
    }

    /**
     * Returns a Cloth.Color value of the wool.
     *
     * @return Cloth.Color of the wool. This is useful for getting the name of the wool.
     */
    public Cloth.Color getClothColor(){
        return Cloth.Color.getColor(getColor());
    }

    public OEntitySheep getEntity() {
        return (OEntitySheep) entity;
    }
}
