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
        // SRG return getEntity().func_70909_n();
        return getEntity().bT();
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
        // SRG getEntity().func_70910_a(name);
        getEntity().b(name);
    }

    /**
     * Return the name of this animals owner.
     *
     * @return The name of the player who owns this animal.
     */
    public String getOwnerName(){
        // SRG return getEntity().func_70905_p();
        return getEntity().h_();
    }

    /**
     * Returns a Player instance of this animals owner.
     *
     * @return A Player instance of this animals owner, else null.
     */
    public Player getOwner(){
        // SRG OEntityLivingBase oentity = getEntity().func_130012_q();
        OEntityLivingBase oentity = getEntity().bV();
        if (oentity != null && oentity.getEntity().isPlayer()){
            return oentity.getEntity().getPlayer();
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
        // SRG getEntity().func_70903_f(tame);
        getEntity().j(tame);
    }

    /**
     * Make this animal sit.
     *
     * @param sitting If this animal should be sitting.
     */
    public void setSitting(boolean sitting){
        // SRG getEntity().func_70904_g(sitting);
        getEntity().k(sitting);
    }

    /**
     * Returns if this animal is currently sitting.
     *
     * @return Sitting or not.
     */
    public boolean isSitting(){
        // SRG return getEntity().func_70906_o();
        return getEntity().bU();
    }

    @Override
    public OEntityTameable getEntity() {
        return (OEntityTameable) entity;
    }
}
