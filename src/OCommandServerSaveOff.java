public class OCommandServerSaveOff extends OCommandBase {

    public OCommandServerSaveOff() {}

    public String c() {
        return "save-off";
    }

    public int a() {
        return 4;
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        OMinecraftServer ominecraftserver = OMinecraftServer.D();

        for (OWorldServer[] level : ominecraftserver.worlds.values()) { // CanaryMod: multiworld 
            for (int i = 0; i < level.length; ++i) {
                if (level[i] != null) {
                    OWorldServer oworldserver = level[i];
                    oworldserver.c = true;
                }
            }
        }

        a(oicommandsender, "commands.save.disabled", new Object[0]);
    }
}
