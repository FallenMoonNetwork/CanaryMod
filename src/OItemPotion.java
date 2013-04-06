import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OItemPotion extends OItem {

    private HashMap a = new HashMap();
    private static final Map b = new LinkedHashMap();

    public OItemPotion(int i) {
        super(i);
        this.d(1);
        this.a(true);
        this.e(0);
        this.a(OCreativeTabs.k);
    }

    public List g(OItemStack oitemstack) {
        if (oitemstack.p() && oitemstack.q().b("CustomPotionEffects")) {
            ArrayList arraylist = new ArrayList();
            ONBTTagList onbttaglist = oitemstack.q().m("CustomPotionEffects");

            for (int i = 0; i < onbttaglist.c(); ++i) {
                ONBTTagCompound onbttagcompound = (ONBTTagCompound) onbttaglist.b(i);

                arraylist.add(OPotionEffect.b(onbttagcompound));
            }

            return arraylist;
        } else {
            List list = (List) this.a.get(Integer.valueOf(oitemstack.k()));

            if (list == null) {
                list = OPotionHelper.b(oitemstack.k(), false);
                this.a.put(Integer.valueOf(oitemstack.k()), list);
            }

            return list;
        }
    }

    public List c(int i) {
        List list = (List) this.a.get(Integer.valueOf(i));

        if (list == null) {
            list = OPotionHelper.b(i, false);
            this.a.put(Integer.valueOf(i), list);
        }

        return list;
    }

    public OItemStack b(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {
        if (!oentityplayer.ce.d) {
            --oitemstack.a;
        }

        if (!oworld.I) {
            List list = this.g(oitemstack);

            if (list != null) {
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    OPotionEffect opotioneffect = (OPotionEffect) iterator.next();

                    oentityplayer.d(new OPotionEffect(opotioneffect));
                }
            }
        }

        if (!oentityplayer.ce.d) {
            if (oitemstack.a <= 0) {
                return new OItemStack(OItem.bu);
            }

            oentityplayer.bK.a(new OItemStack(OItem.bu));
        }

        return oitemstack;
    }

    public int c_(OItemStack oitemstack) {
        return 32;
    }

    public OEnumAction b_(OItemStack oitemstack) {
        return OEnumAction.c;
    }

    public OItemStack a(OItemStack oitemstack, OWorld oworld, OEntityPlayer oentityplayer) {
        if (f(oitemstack.k())) {
            if (!oentityplayer.ce.d) {
                --oitemstack.a;
            }

            oworld.a((OEntity) oentityplayer, "random.bow", 0.5F, 0.4F / (e.nextFloat() * 0.4F + 0.8F));
            if (!oworld.I) {
                oworld.d((OEntity) (new OEntityPotion(oworld, oentityplayer, oitemstack)));
            }

            return oitemstack;
        } else {
            oentityplayer.a(oitemstack, this.c_(oitemstack));
            return oitemstack;
        }
    }

    public boolean a(OItemStack oitemstack, OEntityPlayer oentityplayer, OWorld oworld, int i, int j, int k, int l, float f, float f1, float f2) {
        return (Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_USE, ((OEntityPlayerMP) oentityplayer).getPlayer(), null, this.getBlockInfo(oworld, i, j, k, l), ((OEntityPlayerMP) oentityplayer).getPlayer().getItemStackInHand());
    }

    public static boolean f(int i) {
        return (i & 16384) != 0;
    }

    public String l(OItemStack oitemstack) {
        if (oitemstack.k() == 0) {
            return OStatCollector.a("item.emptyPotion.name").trim();
        } else {
            String s = "";

            if (f(oitemstack.k())) {
                s = OStatCollector.a("potion.prefix.grenade").trim() + " ";
            }

            List list = OItem.bt.g(oitemstack);
            String s1;

            if (list != null && !list.isEmpty()) {
                s1 = ((OPotionEffect) list.get(0)).f();
                s1 = s1 + ".postfix";
                return s + OStatCollector.a(s1).trim();
            } else {
                s1 = OPotionHelper.c(oitemstack.k());
                return OStatCollector.a(s1).trim() + " " + super.l(oitemstack);
            }
        }
    }
}
