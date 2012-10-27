public class OCommandServerSaveAll extends OCommandBase {

    public OCommandServerSaveAll() {}

    public String c() {
        return "save-all";
    }

    public int a() {
        return 4;
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        OMinecraftServer ominecraftserver = OMinecraftServer.D();

        oicommandsender.a(oicommandsender.a("commands.save.start", new Object[0]));
        if (ominecraftserver.ad() != null) {
            ominecraftserver.ad().g();
        }

        try {
            for (int i = 0; i < ominecraftserver.c.length; ++i) {
                if (ominecraftserver.c[i] != null) {
                    OWorldServer oworldserver = ominecraftserver.c[i];
                    boolean flag = oworldserver.c;

                    oworldserver.c = false;
                    oworldserver.a(true, (OIProgressUpdate) null);
                    oworldserver.c = flag;
                }
            }
        } 
        catch (OMinecraftException oMinecraftException){
            a(oicommandsender, "commands.save.failed", new Object[] { oMinecraftException.getMessage() });
            return;
        }

        a(oicommandsender, "commands.save.success", new Object[0]);
    }
}
