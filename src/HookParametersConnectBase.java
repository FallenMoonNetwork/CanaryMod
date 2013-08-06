/**
 * Base superclass for HookParameters(Dis)Connect.
 */
public abstract class HookParametersConnectBase extends HookParameters {
    protected boolean hidden = false;
    protected String message = null;
    protected String playerName;

    public HookParametersConnectBase(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Gets the custom message for this hook.
     * @return the custom message or <tt>null</tt> if unset.
     */
    public String getCustomMessage() {
        return this.message;
    }

    /**
     * Sets the custom message for this hook.
     * Setting to <tt>null</tt> causes the default message to display.
     * @param message the custom message, or <tt>null</tt> for default message
     */
    public void setCustomMessage(String message) {
        this.message = message;
    }

    /**
     * Gets the player name for this message to show.
     * Used for the default message to substitute the player name.
     * @return the playername being substituted
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Sets the player name for this message to show.
     * Used for the default message to substitute the player name.
     * @param playerName the playername to use
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Returns whether this join/leave message is hidden
     * @return <tt>true</tt> when the message is hidden, <tt>false</tt> otherwise
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * Sets whether this join/leave is hidden.
     * @param hidden <tt>true</tt> to not show a message, <tt>false</tt> to show
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

}
