public class OCommandServerSaveOn extends OCommandBase {

    public OCommandServerSaveOn() {}

    public String c() {
        return "save-on";
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
                    oworldserver.c = false;
                }
            }
        }

        a(oicommandsender, "commands.save.enabled", new Object[0]);
    }
}
