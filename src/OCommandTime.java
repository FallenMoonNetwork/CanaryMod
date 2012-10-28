import java.util.Iterator;
import java.util.List;

public class OCommandTime extends OCommandBase {

    public OCommandTime() {}

    public String c() {
        return "time";
    }

    public int a() {
        return 2;
    }

    public String a(OICommandSender oicommandsender) {
        return oicommandsender.a("commands.time.usage", new Object[0]);
    }

    public void b(OICommandSender oicommandsender, String[] astring) {
        if (astring.length > 1) {
            int i;

            if (astring[0].equals("set")) {
                if (astring[1].equals("day")) {
                    i = 0;
                } else if (astring[1].equals("night")) {
                    i = 12500;
                } else {
                    i = a(oicommandsender, astring[1], 0);
                }

                this.a(oicommandsender, i);
                a(oicommandsender, "commands.time.set", new Object[] { Integer.valueOf(i)});
                return;
            }

            if (astring[0].equals("add")) {
                i = a(oicommandsender, astring[1], 0);
                this.b(oicommandsender, i);
                a(oicommandsender, "commands.time.added", new Object[] { Integer.valueOf(i)});
                return;
            }
        }

        throw new OWrongUsageException("commands.time.usage", new Object[0]);
    }

    public List a(OICommandSender oicommandsender, String[] astring) {
        return astring.length == 1 ? a(astring, new String[] { "set", "add"}) : (astring.length == 2 && astring[0].equals("set") ? a(astring, new String[] { "day", "night"}) : null);
    }

    protected void a(OICommandSender oicommandsender, int i) {
        for (int j = 0; j < OMinecraftServer.D().worlds.size(); ++j) {
            //CanaryMod: multiworld time stuffs
            Iterator it = OMinecraftServer.D().worlds.keySet().iterator();
            for(String name = ""; it.hasNext() ; name = (String)it.next() ){
                OMinecraftServer.D().worlds.get(name)[j].b((long) i);
            }
        }
    }

    protected void b(OICommandSender oicommandsender, int i) {
        for (int j = 0; j < OMinecraftServer.D().worlds.size(); ++j) {
            //CanaryMod: multiworld time stuffs.
            Iterator it = OMinecraftServer.D().worlds.keySet().iterator();
            for(String name = ""; it.hasNext() ; name = (String)it.next() ){
                if(OMinecraftServer.D().worlds.containsKey(name)){
                    OWorldServer oworldserver = OMinecraftServer.D().worlds.get(name)[j];
                    oworldserver.b(oworldserver.F() + (long) i);
                }
            }


        }
    }
}