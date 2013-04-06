import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Callable;

public class OEntityTracker {

    private final OWorldServer a;
    private Set b = new HashSet();
    private OIntHashMap c = new OIntHashMap();
    private int d;

    private EntityTracker entityTracker; // CanaryMod: reference to our EntityTracker
    private static final DelayQueue<DelayedTask> delayQueue = new DelayQueue<DelayedTask>(); // CanaryMod: New fields to store the runnables in.

    public OEntityTracker(OWorldServer oworldserver) {
        this.a = oworldserver;
        this.d = oworldserver.o().ad().a();
        this.entityTracker = new EntityTracker(this);
    }

    /**
     * CanaryMod Get the EntityTracker
     * @return
     */
    public EntityTracker getCanaryEntityTracker() {
        return this.entityTracker;
    }

    public void a(OEntity oentity) {
        if (oentity instanceof OEntityPlayerMP) {
            this.a(oentity, 512, 2);
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) oentity;
            Iterator iterator = this.b.iterator();

            while (iterator.hasNext()) {
                OEntityTrackerEntry oentitytrackerentry = (OEntityTrackerEntry) iterator.next();

                if (oentitytrackerentry.a != oentityplayermp) {
                    oentitytrackerentry.b(oentityplayermp);
                }
            }
        } else if (oentity instanceof OEntityFishHook) {
            this.a(oentity, 64, 5, true);
        } else if (oentity instanceof OEntityArrow) {
            this.a(oentity, 64, 20, false);
        } else if (oentity instanceof OEntitySmallFireball) {
            this.a(oentity, 64, 10, false);
        } else if (oentity instanceof OEntityFireball) {
            this.a(oentity, 64, 10, false);
        } else if (oentity instanceof OEntitySnowball) {
            this.a(oentity, 64, 10, true);
        } else if (oentity instanceof OEntityEnderPearl) {
            this.a(oentity, 64, 10, true);
        } else if (oentity instanceof OEntityEnderEye) {
            this.a(oentity, 64, 4, true);
        } else if (oentity instanceof OEntityEgg) {
            this.a(oentity, 64, 10, true);
        } else if (oentity instanceof OEntityPotion) {
            this.a(oentity, 64, 10, true);
        } else if (oentity instanceof OEntityExpBottle) {
            this.a(oentity, 64, 10, true);
        } else if (oentity instanceof OEntityFireworkRocket) {
            this.a(oentity, 64, 10, true);
        } else if (oentity instanceof OEntityItem) {
            this.a(oentity, 64, 20, true);
        } else if (oentity instanceof OEntityMinecart) {
            this.a(oentity, 80, 3, true);
        } else if (oentity instanceof OEntityBoat) {
            this.a(oentity, 80, 3, true);
        } else if (oentity instanceof OEntitySquid) {
            this.a(oentity, 64, 3, true);
        } else if (oentity instanceof OEntityWither) {
            this.a(oentity, 80, 3, false);
        } else if (oentity instanceof OEntityBat) {
            this.a(oentity, 80, 3, false);
        } else if (oentity instanceof OIAnimals) {
            this.a(oentity, 80, 3, true);
        } else if (oentity instanceof OEntityDragon) {
            this.a(oentity, 160, 3, true);
        } else if (oentity instanceof OEntityTNTPrimed) {
            this.a(oentity, 160, 10, true);
        } else if (oentity instanceof OEntityFallingSand) {
            this.a(oentity, 160, 20, true);
        } else if (oentity instanceof OEntityPainting) {
            this.a(oentity, 160, Integer.MAX_VALUE, false);
        } else if (oentity instanceof OEntityXPOrb) {
            this.a(oentity, 160, 20, true);
        } else if (oentity instanceof OEntityEnderCrystal) {
            this.a(oentity, 256, Integer.MAX_VALUE, false);
        } else if (oentity instanceof OEntityItemFrame) {
            this.a(oentity, 160, Integer.MAX_VALUE, false);
        }
    }

    public void a(OEntity oentity, int i, int j) {
        this.a(oentity, i, j, false);
    }

    public void a(OEntity oentity, int i, int j, boolean flag) {
        if (i > this.d) {
            i = this.d;
        }

        try {
            if (this.c.b(oentity.k)) {
                throw new IllegalStateException("Entity is already tracked!");
            }

            OEntityTrackerEntry oentitytrackerentry = new OEntityTrackerEntry(oentity, i, j, flag);

            this.b.add(oentitytrackerentry);
            this.c.a(oentity.k, oentitytrackerentry);
            oentitytrackerentry.b(this.a.h);
        } catch (Throwable throwable) {
            OCrashReport ocrashreport = OCrashReport.a(throwable, "Adding entity to track");
            OCrashReportCategory ocrashreportcategory = ocrashreport.a("Entity To Track");

            ocrashreportcategory.a("Tracking range", (i + " blocks"));
            ocrashreportcategory.a("Update interval", (Callable) (new OCallableEntityTracker(this, j)));
            oentity.a(ocrashreportcategory);
            OCrashReportCategory ocrashreportcategory1 = ocrashreport.a("Entity That Is Already Tracked");

            ((OEntityTrackerEntry) this.c.a(oentity.k)).a.a(ocrashreportcategory1);

            try {
                throw new OReportedException(ocrashreport);
            } catch (OReportedException oreportedexception) {
                System.err.println("\"Silently\" catching entity tracking error.");
                oreportedexception.printStackTrace();
            }
        }
    }

    public void b(OEntity oentity) {
        if (oentity instanceof OEntityPlayerMP) {
            OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) oentity;
            Iterator iterator = this.b.iterator();

            while (iterator.hasNext()) {
                OEntityTrackerEntry oentitytrackerentry = (OEntityTrackerEntry) iterator.next();

                oentitytrackerentry.a(oentityplayermp);
            }
        }

        OEntityTrackerEntry oentitytrackerentry1 = (OEntityTrackerEntry) this.c.d(oentity.k);

        if (oentitytrackerentry1 != null) {
            this.b.remove(oentitytrackerentry1);
            oentitytrackerentry1.a();
        }
    }

    public void a() {
        try {
            ArrayList arraylist = new ArrayList();
            Iterator iterator = this.b.iterator();

            while (iterator.hasNext()) {
                OEntityTrackerEntry oentitytrackerentry = (OEntityTrackerEntry) iterator.next();

            oentitytrackerentry.a(this.a.h);
                if (oentitytrackerentry.n && oentitytrackerentry.a instanceof OEntityPlayerMP) {
                    arraylist.add((OEntityPlayerMP) oentitytrackerentry.a);
                }
            }

            for (int i = 0; i < arraylist.size(); ++i) {
                OEntityPlayerMP oentityplayermp = (OEntityPlayerMP) arraylist.get(i);
                Iterator iterator1 = this.b.iterator();

                while (iterator1.hasNext()) {
                    OEntityTrackerEntry oentitytrackerentry1 = (OEntityTrackerEntry) iterator1.next();

                    if (oentitytrackerentry1.a != oentityplayermp) {
                        oentitytrackerentry1.b(oentityplayermp);
                    }
                }
            }
        } catch (ConcurrentModificationException concurrentmodificationexception) {
            // people seem to get this concurrentmodificationexceptionception often, lets just catch so it doesn't crash the server.
            this.a.W().b("CanaryMod WARNING: ConcurrentModificationException in OEntityTracker:", concurrentmodificationexception);
        }
        // CanaryMod: Execute runnables contained in eventQueue.
        for (DelayedTask task = delayQueue.poll(); task != null; task = delayQueue.poll()) {
            // should we catch concurrentmodificationexceptionceptions here?
            task.run();
        }
    }

    // CanaryMod: Allow adding of tasks to the queue

    public static void add(Runnable runnable, long i) {
        // j.u.concurent.* classes are thread safe
        delayQueue.add(new DelayedTask(runnable, i));
    }

    // CanaryMod: deprecated. Use server.addToServerQueue().
    @Deprecated
    public synchronized static void add(Runnable runnable) {
        add(runnable, 0L);
    }

    public void a(OEntity oentity, OPacket opacket) {
        OEntityTrackerEntry oentitytrackerentry = (OEntityTrackerEntry) this.c.a(oentity.k);

        if (oentitytrackerentry != null) {
            oentitytrackerentry.a(opacket);
        }
    }

    public void b(OEntity oentity, OPacket opacket) {
        OEntityTrackerEntry oentitytrackerentry = (OEntityTrackerEntry) this.c.a(oentity.k);

        if (oentitytrackerentry != null) {
            oentitytrackerentry.b(opacket);
        }
    }

    public void a(OEntityPlayerMP oentityplayermp) {
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            OEntityTrackerEntry oentitytrackerentry = (OEntityTrackerEntry) iterator.next();

            oentitytrackerentry.c(oentityplayermp);
        }
    }

    public void a(OEntityPlayerMP oentityplayermp, OChunk ochunk) {
        Iterator iterator = this.b.iterator();

        while (iterator.hasNext()) {
            OEntityTrackerEntry oentitytrackerentry = (OEntityTrackerEntry) iterator.next();

            if (oentitytrackerentry.a != oentityplayermp && oentitytrackerentry.a.aj == ochunk.g && oentitytrackerentry.a.al == ochunk.h) {
                oentitytrackerentry.b(oentityplayermp);
            }
        }
    }
}
