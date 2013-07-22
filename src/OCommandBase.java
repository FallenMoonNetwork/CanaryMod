import com.google.common.primitives.Doubles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class OCommandBase implements OICommand {

    private static OIAdminCommand a;

    public OCommandBase() {}

    public int a() {
        return 4;
    }

    public List b() {
        return null;
    }

    public boolean a(OICommandSender oicommandsender) {
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
            double d0 = Double.parseDouble(s);

            if (!Doubles.isFinite(d0)) {
                throw new ONumberInvalidException("commands.generic.double.invalid", new Object[] { s});
            } else {
                return d0;
            }
        } catch (NumberFormatException numberformatexception) {
            throw new ONumberInvalidException("commands.generic.double.invalid", new Object[] { s});
        }
    }

    public static double a(OICommandSender oicommandsender, String s, double d0) {
        return a(oicommandsender, s, d0, Double.MAX_VALUE);
    }

    public static double a(OICommandSender oicommandsender, String s, double d0, double d1) {
        double d2 = b(oicommandsender, s);

        if (d2 < d0) {
            throw new ONumberInvalidException("commands.generic.double.tooSmall", new Object[] { Double.valueOf(d2), Double.valueOf(d0)});
        } else if (d2 > d1) {
            throw new ONumberInvalidException("commands.generic.double.tooBig", new Object[] { Double.valueOf(d2), Double.valueOf(d1)});
        } else {
            return d2;
        }
    }

    public static boolean c(OICommandSender oicommandsender, String s) {
        if (!s.equals("true") && !s.equals("1")) {
            if (!s.equals("false") && !s.equals("0")) {
                throw new OCommandException("commands.generic.boolean.invalid", new Object[] { s});
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    public static OEntityPlayerMP b(OICommandSender oicommandsender) {
        if (oicommandsender instanceof OEntityPlayerMP) {
            return (OEntityPlayerMP) oicommandsender;
        } else {
            throw new OPlayerNotFoundException("You must specify which player you wish to perform this action on.", new Object[0]);
        }
    }

    public static OEntityPlayerMP d(OICommandSender oicommandsender, String s) {
        OEntityPlayerMP oentityplayermp = OPlayerSelector.a(oicommandsender, s);

        if (oentityplayermp != null) {
            return oentityplayermp;
        } else {
            oentityplayermp = OMinecraftServer.F().af().f(s);
            if (oentityplayermp == null) {
                throw new OPlayerNotFoundException();
            } else {
                return oentityplayermp;
            }
        }
    }

    public static String e(OICommandSender oicommandsender, String s) {
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

    public static double a(OICommandSender oicommandsender, double d0, String s) {
        return a(oicommandsender, d0, s, -30000000, 30000000);
    }

    public static double a(OICommandSender oicommandsender, double d0, String s, int i, int j) {
        boolean flag = s.startsWith("~");

        if (flag && Double.isNaN(d0)) {
            throw new ONumberInvalidException("commands.generic.num.invalid", new Object[] { Double.valueOf(d0)});
        } else {
            double d1 = flag ? d0 : 0.0D;

            if (!flag || s.length() > 1) {
                boolean flag1 = s.contains(".");

                if (flag) {
                    s = s.substring(1);
                }

                d1 += b(oicommandsender, s);
                if (!flag1 && !flag) {
                    d1 += 0.5D;
                }
            }

            if (i != 0 || j != 0) {
                if (d1 < (double) i) {
                    throw new ONumberInvalidException("commands.generic.double.tooSmall", new Object[] { Double.valueOf(d1), Integer.valueOf(i)});
                }

                if (d1 > (double) j) {
                    throw new ONumberInvalidException("commands.generic.double.tooBig", new Object[] { Double.valueOf(d1), Integer.valueOf(j)});
                }
            }

            return d1;
        }
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
        return a(collection.toArray(new String[collection.size()]));
    }

    public static String b(Collection collection) {
        String[] astring = new String[collection.size()];
        int i = 0;

        OEntityLivingBase oentitylivingbase;

        for (Iterator iterator = collection.iterator(); iterator.hasNext(); astring[i++] = oentitylivingbase.ax()) {
            oentitylivingbase = (OEntityLivingBase) iterator.next();
        }

        return a((Object[]) astring);
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
