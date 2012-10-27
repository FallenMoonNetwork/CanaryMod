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
		int i = getEntity().af.a(16);
		if (sheared){
			getEntity().af.b(16, Byte.valueOf((byte)(i | 0x10)));
		} else {
			getEntity().af.b(16, Byte.valueOf((byte)(i & 0xFFFFFFEF)));
		}
	}

	/**
	 * Returns if the sheep has wool.
	 * 
	 * @return If this sheep has wool.
	 */
	public boolean isSheared(){
		return (getEntity().af.a(16) & 0x10) != 0;
	}

	/**
	 * Set the color of the wool.
	 * 
	 * @param color int value of the new color.
	 */
	public void setWoolColor(int color){
		int i = getEntity().af.a(16);
		getEntity().af.b(16, Byte.valueOf((byte)(i & 0xF0 | color & 0xF)));
	}

	/**
	 * Returns an int of the color of the wool.
	 * 
	 * @return int value of wool color.
	 */
	public int getColor(){
		return getEntity().af.a(16) & 0xF;
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
