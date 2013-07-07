public class TamableEntity extends Mob {
    public TamableEntity(OEntityTameable entity){
        super(entity);
    }

    /**
     * If this animal is tame.
     *
     * @return True if tamed.
     */
    public boolean isTame(){
        return getEntity().bP();
    }

    /**
     * Sets the owner of this animal.
     *
     * @param player The new player who is the owner of this animal.
     */
    public void setOwner(Player player){
        setOwnerName(player.getName());
    }

    /**
     * Sets the owner of this animal.
     *
     * @param name The name of the player who owns this animal.
     */
    public void setOwnerName(String name){
        getEntity().a(name);
    }

    /**
     * Return the name of this animals owner.
     *
     * @return The name of the player who owns this animal.
     */
    public String getOwnerName(){
        return getEntity().h_();
    }

    /**
     * Returns a Player instance of this animals owner.
     *
     * @return A Player instance of this animals owner, else null.
     */
    public Player getOwner(){
        BaseEntity be = getEntity().d().getEntity();
        if (be.isPlayer()){
            return be.getPlayer();
        } else {
            return null;
        }
    }

    /**
     * Sets if this animal is tame.
     *
     * @param tame True if the animal should be tame.
     */
    public void setTame(boolean tame){
        getEntity().j(tame);
    }

    /**
     * Make this animal sit.
     *
     * @param sitting If this animal should be sitting.
     */
    public void setSitting(boolean sitting){
        getEntity().k(sitting);
    }

    /**
     * Returns if this animal is currently sitting.
     *
     * @return Sitting or not.
     */
    public boolean isSitting(){
        return getEntity().bQ();
    }

    @Override
    public OEntityTameable getEntity() {
        return (OEntityTameable) entity;
    }
}