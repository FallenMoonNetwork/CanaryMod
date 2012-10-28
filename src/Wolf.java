/**
 * Wrap around an OEntityWolf to provide extra methods not available to anything but wolves.
 *
 * @author Brian McCarthy
 *
 */
public class Wolf extends TamableEntity{

	/**
	 * Basic wolf constructor.
	 *
	 * @param entity An instance of OEntityWolf to wrap around.
	 */
	public Wolf(OEntityWolf entity){
		super(entity);
	}

	/**
	 * If this wolf is angry.
	 *
	 * @return Boolean of if this wolf is angry.
	 */
	public boolean isAngry(){
		return (getEntity().ag.a(16) & 0x2) != 0;
	}

	/**
	 * Sets if this wolf is angry of not.
	 *
	 * @param angry New angry state of the wolf.
	 */
	public void setAngry(boolean angry){
		int m = getEntity().ag.a(16);
		if (angry){
			getEntity().ag.b(16, Byte.valueOf((byte)(m | 0x2)));
		} else {
			getEntity().ag.b(16, Byte.valueOf((byte)(m & 0xFFFFFFFD)));
		}
	}

	public OEntityWolf getEntity() {
		return (OEntityWolf) entity;
	}

}
