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
        this.a(new OCommandSpreadPlayers());
        this.a(new OCommandPlaySound());
        this.a(new OServerCommandScoreboard());
        if (OMinecraftServer.F().V()) {
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

        if (oicommandsender instanceof OTileEntityCommandBlock && !OMinecraftServer.F().worlds.get(etc.getServer().getDefaultWorld().getName())[0].O().b("commandBlockOutput")) { // CanaryMod - multiworld fix
            flag = false;
        }

        OChatMessageComponent ochatmessagecomponent = OChatMessageComponent.b("chat.type.admin", new Object[] { oicommandsender.c_(), OChatMessageComponent.b(s, aobject)});

        ochatmessagecomponent.a(OEnumChatFormatting.h);
        ochatmessagecomponent.b(Boolean.valueOf(true));
        if (flag) {
            Iterator iterator = OMinecraftServer.F().af().a.iterator();

            while (iterator.hasNext()) {
                OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) iterator.next();

                if (oentityplayermp != oicommandsender && OMinecraftServer.F().af().e(oentityplayermp.c_())) {
                    oentityplayermp.a(ochatmessagecomponent);
                }
            }
        }

        if (oicommandsender != OMinecraftServer.F()) {
            OMinecraftServer.F().a(ochatmessagecomponent);
        }

        if ((i & 1) != 1) {
            oicommandsender.a(OChatMessageComponent.b(s, aobject));
        }
    }
}
