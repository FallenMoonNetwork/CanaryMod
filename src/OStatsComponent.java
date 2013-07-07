import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.Timer;

public class OStatsComponent extends JComponent {

    private static final DecimalFormat a = new DecimalFormat("########0.000");
    private int[] b = new int[256];
    private int c;
    private String[] d = new String[11];
    private final OMinecraftServer e;

    public OStatsComponent(OMinecraftServer ominecraftserver) {
        this.e = ominecraftserver;
        this.setPreferredSize(new Dimension(456, 246));
        this.setMinimumSize(new Dimension(456, 246));
        this.setMaximumSize(new Dimension(456, 246));
        (new Timer(500, new OStatsComponentINNER1(this))).start();
        this.setBackground(Color.BLACK);
    }

    private void a() {
        long l = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.gc();
        this.d[0] = "Memory use: " + l / 1024L / 1024L + " mb (" + Runtime.getRuntime().freeMemory() * 100L / Runtime.getRuntime().maxMemory() + "% free)";
        this.d[1] = "Threads: " + OTcpConnection.a.get() + " + " + OTcpConnection.b.get();
        this.d[2] = "Avg tick: " + a.format(this.a(this.e.j) * 1.0E-6D) + " ms";
        this.d[3] = "Avg sent: " + (int) this.a(this.e.f) + ", Avg size: " + (int) this.a(this.e.g);
        this.d[4] = "Avg rec: " + (int) this.a(this.e.h) + ", Avg size: " + (int) this.a(this.e.i);
        if (this.e.worlds != null) {
            // CanaryMod start: Multiworld
            int i = 5;
            for (Map.Entry<String, OWorldServer[]> entry : this.e.worlds.entrySet()) {
                String worldName = entry.getKey();
                OWorldServer[] level = entry.getValue();
                this.d[i++] = "World " + worldName + ":";

                for (int j = 0; j < level.length; ++j) {
                    this.d[i + j] = "  Lvl " + j + " tick: " + a.format(this.a(this.e.worldTickNanos.get(worldName)[j]) * 1.0E-6D) + " ms";
                    if (level[j] != null && level[j].b != null) {
                        this.d[i + j] = this.d[i + j] + ", " + level[j].b.e();
                        this.d[i + j] = this.d[i + j] + ", Vec3: " + level[j].V().d() + " / " + level[j].V().c();
                    }
                }
                i += level.length;
            } // CanaryMod end
        }

        double d0 = 12500.0D;

        this.b[this.c++ & 255] = (int) (this.a(this.e.g) * 100.0D / 12500.0D);
        this.repaint();
    }

    private double a(long[] along) {
        long i = 0L;

        for (int j = 0; j < along.length; ++j) {
            i += along[j];
        }

        return (double) i / (double) along.length;
    }

    public void paint(Graphics graphics) {
        graphics.setColor(new Color(16777215));
        graphics.fillRect(0, 0, 456, 246);

        int i;

        for (i = 0; i < 256; ++i) {
            int j = this.b[i + this.c & 255];

            graphics.setColor(new Color(j + 28 << 16));
            graphics.fillRect(i, 100 - j, 1, j);
        }

        graphics.setColor(Color.BLACK);

        for (i = 0; i < this.d.length; ++i) {
            String s = this.d[i];

            if (s != null) {
                graphics.drawString(s, 32, 116 + i * 16);
            }
        }
    }

    static void a(OStatsComponent ostatscomponent) {
        ostatscomponent.a();
    }
}
