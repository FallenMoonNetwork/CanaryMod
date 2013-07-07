public class OCommandServerSaveAll extends OCommandBase {

    public OCommandServerSaveAll() {}

    public String c() {
        return "save-all";
    }

    public int a() {
        return 4;
    }

    public String c(OICommandSender oicommandsender) {
        return "commands.save.usage";
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        OMinecraftServer ominecraftserver = OMinecraftServer.F();

        oicommandsender.a(OChatMessageComponent.e("commands.save.start"));
        if (ominecraftserver.af() != null) {
            ominecraftserver.af().g();
        }

        try {
            int i;
            OWorldServer oworldserver;
            boolean flag;

            for (OWorldServer[] level: ominecraftserver.worlds.values()) { // CanaryMod: multiworld
                for (i = 0; i < level.length; ++i) {
                    if (level[i] != null) {
                        oworldserver = level[i];
                        flag = oworldserver.c;
                        oworldserver.c = false;
                        oworldserver.a(true, (OIProgressUpdate) null);
                        oworldserver.c = flag;
                    }
                }
            }

            if (astring.length > 0 && "flush".equals(astring[0])) {
                oicommandsender.a(OChatMessageComponent.e("commands.save.flushStart"));

                for (OWorldServer[] level: ominecraftserver.worlds.values()) { // CanaryMod: multiworld
                    for (i = 0; i < level.length; ++i) {
                        if (level[i] != null) {
                            oworldserver = level[i];
                            flag = oworldserver.c;
                            oworldserver.c = false;
                            oworldserver.m();
                            oworldserver.c = flag;
                        }
                    }
                }

                oicommandsender.a(OChatMessageComponent.e("commands.save.flushEnd"));
            }
        } catch (OMinecraftException ominecraftexception) {
            a(oicommandsender, "commands.save.failed", new Object[] { ominecraftexception.getMessage()});
            return;
        }

        a(oicommandsender, "commands.save.success", new Object[0]);
    }
}
