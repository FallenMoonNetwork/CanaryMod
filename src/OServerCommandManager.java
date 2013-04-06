import java.util.Iterator;

public class OServerCommandManager extends OCommandHandler implements OIAdminCommand {

    public OServerCommandManager() {
        this.a(new OCommandTime());
        this.a(new OCommandGameMode());
        this.a(new OCommandDifficulty());
        this.a(new OCommandDefaultGameMode());
        this.a(new OCommandKill());
        this.a(new OCommandToggleDownfall());
        this.a(new OCommandWeather());
        this.a(new OCommandXP());
        this.a(new OCommandServerTp());
        this.a(new OCommandGive());
        this.a(new OCommandEffect());
        this.a(new OCommandEnchant());
        this.a(new OCommandServerEmote());
        this.a(new OCommandShowSeed());
        this.a(new OCommandHelp());
        this.a(new OCommandDebug());
        this.a(new OCommandServerMessage());
        this.a(new OCommandServerSay());
        this.a(new OCommandSetSpawnpoint());
        this.a(new OCommandGameRule());
        this.a(new OCommandClearInventory());
        this.a(new OServerCommandTestFor());
        this.a(new OServerCommandScoreboard());
        if (OMinecraftServer.D().T()) {
            this.a(new OCommandServerOp());
            this.a(new OCommandServerDeop());
            this.a(new OCommandServerStop());
            this.a(new OCommandServerSaveAll());
            this.a(new OCommandServerSaveOff());
            this.a(new OCommandServerSaveOn());
            this.a(new OCommandServerBanIp());
            this.a(new OCommandServerPardonIp());
            this.a(new OCommandServerBan());
            this.a(new OCommandServerBanlist());
            this.a(new OCommandServerPardon());
            this.a(new OCommandServerKick());
            this.a(new OCommandServerList());
            this.a(new OCommandServerWhitelist());
        } else {
            this.a(new OCommandServerPublishLocal());
        }

        OCommandBase.a((OIAdminCommand) this);
    }

    public void a(OICommandSender oicommandsender, int i, String s, Object... aobject) {
        boolean flag = true;

        if (oicommandsender instanceof OTileEntityCommandBlock && !OMinecraftServer.D().worlds.get(etc.getServer().getDefaultWorld().getName())[0].M().b("commandBlockOutput")) { // CanaryMod - multiworld fix
            flag = false;
        }

        if (flag) {
            Iterator iterator = OMinecraftServer.D().ad().a.iterator();

            while (iterator.hasNext()) {
                OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) iterator.next();

                if (oentityplayermp != oicommandsender && OMinecraftServer.D().ad().e(oentityplayermp.bS)) {
                    oentityplayermp.a("" + OEnumChatFormatting.h + "" + OEnumChatFormatting.u + "[" + oicommandsender.c_() + ": " + oentityplayermp.a(s, aobject) + "]");
                }
            }
        }

        if (oicommandsender != OMinecraftServer.D()) {
            OMinecraftServer.D().al().a("[" + oicommandsender.c_() + ": " + OMinecraftServer.D().a(s, aobject) + "]");
        }

        if ((i & 1) != 1) {
            oicommandsender.a(oicommandsender.a(s, aobject));
        }
    }
}
