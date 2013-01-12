/**
 * An interface class to Beacons.
 * @author m4411k4
 */
public class Beacon extends BaseContainerBlock<OTileEntityBeacon> implements ComplexBlock {
    private final OTileEntityBeacon beacon;
    
    public Beacon(OTileEntityBeacon block) {
        this(null, block);
    }
    
    public Beacon(OContainer oContainer, OTileEntityBeacon block) {
        super(oContainer, block, "Beacon");
        this.beacon = block;
    }
    
    /**
     * Checks through the list of supported potions to see if the input effect
     * is one of them.
     * 
     * @param effect
     * @return
     */
    public boolean isValidEffect(PotionEffect.Type effect) {
        return isValidEffectAtLevels(effect, OTileEntityBeacon.a.length);
    }
    
    /**
     * Checks through the list of supported potions up to the specified level
     * 
     * @param effect
     * @param levels
     * @return
     */
    public boolean isValidEffectAtLevels(PotionEffect.Type effect, int levels) {
        OPotion[][] potions = OTileEntityBeacon.a;
        for(int i = 0; i < levels && i < potions.length; i++) {
            for(int j = 0; j < potions[i].length; j++) {
                if(potions[i][j].H == effect.getId())
                    return true;
            }
        }
        return false;
    }
    
    public PotionEffect.Type getPrimaryEffect() {
        return PotionEffect.Type.fromId(this.beacon.getPrimaryEffect());
    }
    
    /**
     * Sets the potion effect *IF* the effect is on the supported potion list
     * 
     * @param effect
     */
    public void setPrimaryEffect(PotionEffect.Type effect) {
        this.beacon.setPrimaryEffect(effect.getId());
    }
    
    /**
     * Allows mods to set other potion effects not allowed by default
     * 
     * @param effect
     */
    public void setPrimaryEffectDirectly(PotionEffect.Type effect) {
        this.beacon.setPrimaryEffectDirectly(effect.getId());
    }
    
    public PotionEffect.Type getSecondaryEffect() {
        return PotionEffect.Type.fromId(this.beacon.getSecondaryEffect());
    }
    
    /**
     * Sets the potion effect *IF* the effect is on the supported potion list
     * 
     * @param effect
     */
    public void setSecondaryEffect(PotionEffect.Type effect) {
        this.beacon.setSecondaryEffect(effect.getId());
    }
    
    /**
     * Allows mods to set other potion effects not allowed by default
     * 
     * @param effect
     */
    public void setSecondaryEffectDirectly(PotionEffect.Type effect) {
        this.beacon.setSecondaryEffectDirectly(effect.getId());
    }
}
