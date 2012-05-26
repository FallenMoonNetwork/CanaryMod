/**
 * Wrap around an OEntityWolf to provide extra methods not available to anything but wolves.
 * 
 * @author Brian McCarthy
 *
 */
public class Wolf extends Mob{

	/**
	 * Basic wolf constructor.
	 * 
	 * @param entity An instance of OEntityWolf to wrap around.
	 */
	public Wolf(OEntityWolf entity) {
		super(entity);
	}
	
	/**
	 * If this wolf is angry.
	 * 
	 * @return Boolean of if this wolf is angry.
	 */
	public boolean isAngry(){
		return (getEntity().bY.a(16) & 0x2) != 0;
	}

	/**
	 * Sets if this wolf is angry of not.
	 * 
	 * @param angry New angry state of the wolf.
	 */
	public void setAngry(boolean angry){
		int m = getEntity().bY.a(16);
		if (angry){
			getEntity().bY.b(16, Byte.valueOf((byte)(m | 0x2)));
		} else {
			getEntity().bY.b(16, Byte.valueOf((byte)(m & 0xFFFFFFFD)));
		}
	}

	/**
	 * Sets the owner of this wolf.
	 * 
	 * @param player The new player who is the owner of this wolf.
	 */
	public void setOwner(Player player){
		setOwnerName(player.getName());
	}

	/**
	 * Sets the owner of this wolf.
	 * 
	 * @param name The name of the player who owns this wolf.
	 */
	public void setOwnerName(String name){
		getEntity().a(name);
	}

	/**
	 * Return the name of this wolfs owner.
	 * 
	 * @return The name of the player who owns this wolf.
	 */
	public String getOwnerName(){
		return getEntity().A();
	}

	/**
	 * Returns a Player instance of this wolfs owner.
	 * 
	 * @return A Player instance of this wolfs owner, else null.
	 */
	public Player getOwner(){
		LivingEntity le = new LivingEntity(getEntity().bi.a(getOwnerName()));
		if (le.isPlayer()){
			return le.getPlayer();
		} else {
			return null;
		}
	}

	/**
	 * If this wolf is in love.
	 * 
	 * @return True if wolf is in love.
	 */
	public boolean isInLove(){
		return getEntity().r_();
	}

	/**
	 * Sets if this wolf is tame.
	 * 
	 * @param tame True if the wolf should be tame.
	 */
	public void setTame(boolean tame){
		getEntity().b(true);
	}

	/**
	 * If this wolf is tame.
	 * 
	 * @return True if tamed.
	 */
	public boolean isTame(){
		return getEntity().u_();
	}

	/**
	 * Make this wolf sit.
	 * 
	 * @param sitting If this wolf should be sitting.
	 */
	public void setSitting(boolean sitting){
		int i = getEntity().bY.a(16);
		if (sitting)
			getEntity().bY.b(16, Byte.valueOf((byte)(i | 0x1)));
		else
			getEntity().bY.b(16, Byte.valueOf((byte)(i & 0xFFFFFFFE)));
	}

	/**
	 * Returns if this wolf is currently sitting.
	 * 
	 * @return Sitting or not.
	 */
	public boolean isSitting(){
		return getEntity().v_();
	}

	public OEntityWolf getEntity() {
		return (OEntityWolf) entity;
	}

}
