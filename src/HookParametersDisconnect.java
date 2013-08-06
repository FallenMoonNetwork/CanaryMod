/**
 * HookParamtersDisconnect.java - send/receive parameters from hooks
 *
 * @author James
 */

public class HookParametersDisconnect extends HookParametersConnectBase {
    private String reason;

    @Deprecated
    public void setLeaveMessage(String leaveMessage) {
        super.setCustomMessage(leaveMessage);
    }

    @Deprecated
    public String getLeaveMessage() {
        return message == null ? Colors.Yellow + playerName + " left the game" : message;
    }

    /**
     * Returns the reason why this player was disconnected.
     * This is the kick message when kicked, or one of minecraft's translate
     * strings indicating the reason.
     * @return the reason for disconnecting (kick message or other reason)
     */
    public String getReason() {
        return reason;
    }

    @Deprecated
    public HookParametersDisconnect(String leaveMessage, String reason) {
        super(null);
        this.reason = reason;
    }

    public HookParametersDisconnect(Object differCtors, String playerName, String reason) {
        super(playerName);
        this.reason = reason;
    }
}
