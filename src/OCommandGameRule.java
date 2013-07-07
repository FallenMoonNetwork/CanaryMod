import java.util.List;

public class OCommandGameRule extends OCommandBase {

    public OCommandGameRule() {}

    public String c() {
        return "gamerule";
    }

    public int a() {
        return 2;
    }

    public String c(OICommandSender oicommandsender) {
        return "commands.gamerule.usage";
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        String s;

        if (astring.length == 2) {
            s = astring[0];
            String s1 = astring[1];
            OGameRules ogamerules = this.d();

            if (ogamerules.e(s)) {
                ogamerules.b(s, s1);
                a(oicommandsender, "commands.gamerule.success", new Object[0]);
            } else {
                a(oicommandsender, "commands.gamerule.norule", new Object[] { s});
            }
        } else if (astring.length == 1) {
            s = astring[0];
            OGameRules ogamerules1 = this.d();

            if (ogamerules1.e(s)) {
                String s2 = ogamerules1.a(s);

                oicommandsender.a(OChatMessageComponent.d(s).a(" = ").a(s2));
            } else {
                a(oicommandsender, "commands.gamerule.norule", new Object[] { s});
            }
        } else if (astring.length == 0) {
            OGameRules ogamerules2 = this.d();

            oicommandsender.a(OChatMessageComponent.d(a(ogamerules2.b())));
        } else {
            throw new OWrongUsageException("commands.gamerule.usage", new Object[0]);
        }
    }

    public List a(OICommandSender oicommandsender, String[] astring) {
        return astring.length == 1 ? a(astring, this.d().b()) : (astring.length == 2 ? a(astring, new String[] { "true", "false"}) : null);
    }

    private OGameRules d() {
        return OMinecraftServer.F().getWorld(OMinecraftServer.F().L(), 0).O();
    }
}
