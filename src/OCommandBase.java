import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class OCommandBase implements OICommand {

    private static OIAdminCommand a = null;

    public OCommandBase() {}

    public int a() {
        return 4;
    }

    public String a(OICommandSender oicommandsender) {
        return "/" + this.c();
    }

    public List b() {
        return null;
    }

    public boolean b(OICommandSender oicommandsender) {
        return oicommandsender.a(this.a(), this.c());
    }

    public List a(OICommandSender oicommandsender, String[] astring) {
        return null;
    }

    public static int a(OICommandSender oicommandsender, String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException numberformatexception) {
            throw new ONumberInvalidException("commands.generic.num.invalid", new Object[] { s});
        }
    }

    public static int a(OICommandSender oicommandsender, String s, int i) {
        return a(oicommandsender, s, i, Integer.MAX_VALUE);
    }

    public static int a(OICommandSender oicommandsender, String s, int i, int j) {
        int k = a(oicommandsender, s);

        if (k < i) {
            throw new ONumberInvalidException("commands.generic.num.tooSmall", new Object[] { Integer.valueOf(k), Integer.valueOf(i)});
        } else if (k > j) {
            throw new ONumberInvalidException("commands.generic.num.tooBig", new Object[] { Integer.valueOf(k), Integer.valueOf(j)});
        } else {
            return k;
        }
    }

    public static double b(OICommandSender oicommandsender, String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException numberformatexception) {
            throw new ONumberInvalidException("commands.generic.double.invalid", new Object[] { s});
        }
    }

    public static OEntityPlayerMP c(OICommandSender oicommandsender) {
        if (oicommandsender instanceof OEntityPlayerMP) {
            return (OEntityPlayerMP) oicommandsender;
        } else {
            throw new OPlayerNotFoundException("You must specify which player you wish to perform this action on.", new Object[0]);
        }
    }

    public static OEntityPlayerMP c(OICommandSender oicommandsender, String s) {
        OEntityPlayerMP oentityplayermp = OPlayerSelector.a(oicommandsender, s);

        if (oentityplayermp != null) {
            return oentityplayermp;
        } else {
            oentityplayermp = OMinecraftServer.D().ad().f(s);
            if (oentityplayermp == null) {
                throw new OPlayerNotFoundException();
            } else {
                return oentityplayermp;
            }
        }
    }

    public static String d(OICommandSender oicommandsender, String s) {
        OEntityPlayerMP oentityplayermp = OPlayerSelector.a(oicommandsender, s);

        if (oentityplayermp != null) {
            return oentityplayermp.am();
        } else if (OPlayerSelector.b(s)) {
            throw new OPlayerNotFoundException();
        } else {
            return s;
        }
    }

    public static String a(OICommandSender oicommandsender, String[] astring, int i) {
        return a(oicommandsender, astring, i, false);
    }

    public static String a(OICommandSender oicommandsender, String[] astring, int i, boolean flag) {
        StringBuilder stringbuilder = new StringBuilder();

        for (int j = i; j < astring.length; ++j) {
            if (j > i) {
                stringbuilder.append(" ");
            }

            String s = astring[j];

            if (flag) {
                String s1 = OPlayerSelector.b(oicommandsender, s);

                if (s1 != null) {
                    s = s1;
                } else if (OPlayerSelector.b(s)) {
                    throw new OPlayerNotFoundException();
                }
            }

            stringbuilder.append(s);
        }

        return stringbuilder.toString();
    }

    public static String a(Object[] aobject) {
        StringBuilder stringbuilder = new StringBuilder();

        for (int i = 0; i < aobject.length; ++i) {
            String s = aobject[i].toString();

            if (i > 0) {
                if (i == aobject.length - 1) {
                    stringbuilder.append(" and ");
                } else {
                    stringbuilder.append(", ");
                }
            }

            stringbuilder.append(s);
        }

        return stringbuilder.toString();
    }

    public static String a(Collection collection) {
        return a(collection.toArray(new String[0]));
    }

    public static boolean a(String s, String s1) {
        return s1.regionMatches(true, 0, s, 0, s.length());
    }

    public static List a(String[] astring, String... astring1) {
        String s = astring[astring.length - 1];
        ArrayList arraylist = new ArrayList();
        String[] astring2 = astring;
        int i = astring1.length;

        for (int j = 0; j < i; ++j) {
            String s1 = astring2[j];

            if (a(s, s1)) {
                arraylist.add(s1);
            }
        }

        return arraylist;
    }

    public static List a(String[] astring, Iterable iterable) {
        String s = astring[astring.length - 1];
        ArrayList arraylist = new ArrayList();
        Iterator iterator = iterable.iterator();

        while (iterator.hasNext()) {
            String s1 = (String) iterator.next();

            if (a(s, s1)) {
                arraylist.add(s1);
            }
        }

        return arraylist;
    }

    public boolean a(String[] astring, int i) {
        return false;
    }

    public static void a(OICommandSender oicommandsender, String s, Object... aobject) {
        a(oicommandsender, 0, s, aobject);
    }

    public static void a(OICommandSender oicommandsender, int i, String s, Object... aobject) {
        if (a != null) {
            a.a(oicommandsender, i, s, aobject);
        }
    }

    public static void a(OIAdminCommand oiadmincommand) {
        a = oiadmincommand;
    }

    public int a(OICommand oicommand) {
        return this.c().compareTo(oicommand.c());
    }

    public int compareTo(Object object) {
        return this.a((OICommand) object);
    }
}
