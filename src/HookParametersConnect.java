/**
 * HookParamtersConnect.java - send/receive parameters from hooks
 *
 * @author James
 */

public class HookParametersConnect extends HookParametersConnectBase {
    private boolean applyPotionsEffects;

    /**
     * Returns whether potion effects should be applied on join.
     * Defaults to <tt>true</tt>.
     * @return whether potion effects should be applied
     */
    public boolean applyPotionsEffects() {
        return applyPotionsEffects;
    }

    /**
     * Sets whether potion effects should be applied on join.
     * Defaults to <tt>true</tt>.
     * @param applyPotionsEffects <tt>true</tt> to apply potion effects,
     * <tt>false</tt> to not apply them
     */
    public void setApplyPotionsEffects(boolean applyPotionsEffects) {
        this.applyPotionsEffects = applyPotionsEffects;
    }

    @Deprecated
    public void setJoinMessage(String joinMessage) {
        this.message = joinMessage;
    }

    @Deprecated
    public String getJoinMessage() {
        return message == null ? Colors.Yellow + playerName + " joined the game" : message;
    }

    @Deprecated
    public HookParametersConnect(String joinMessage, boolean potionEffects) {
        super(null);
        this.message = joinMessage;
        this.applyPotionsEffects = potionEffects;
    }

    public HookParametersConnect(boolean potionEffects, String playerName) {
        super(playerName);
        this.applyPotionsEffects = potionEffects;
    }
}
