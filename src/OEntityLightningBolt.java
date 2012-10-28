import java.util.Iterator;
import java.util.List;


public class OEntityLightningBolt extends OEntityWeatherEffect {

    private int b;
    public long a = 0L;
    private int c;

    public OEntityLightningBolt(OWorld oworld, double d0, double d1, double d2) {
        super(oworld);
        this.b(d0, d1, d2, 0.0F, 0.0F);
        this.b = 2;
        this.a = this.aa.nextLong();
        this.c = this.aa.nextInt(3) + 1;
        if (oworld.t >= 2 && oworld.a(OMathHelper.c(d0), OMathHelper.c(d1), OMathHelper.c(d2), 10)) {
            int i = OMathHelper.c(d0);
            int j = OMathHelper.c(d1);
            int k = OMathHelper.c(d2);

            if (oworld.a(i, j, k) == 0 && OBlock.au.b(oworld, i, j, k)) {
                // CanaryMod: Ignite hook
                Block block = this.entity.getWorld().getBlockAt(i, j, k);

                block.setStatus(5); // lightning
                if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null)) {
                    oworld.e(i, j, k, OBlock.au.cm);
                }
            }

            for (i = 0; i < 4; ++i) {
                j = OMathHelper.c(d0) + this.aa.nextInt(3) - 1;
                k = OMathHelper.c(d1) + this.aa.nextInt(3) - 1;
                int l = OMathHelper.c(d2) + this.aa.nextInt(3) - 1;

                if (oworld.a(j, k, l) == 0 && OBlock.au.b(oworld, j, k, l)) {
                    // CanaryMod: Ignite hook
                    Block block = this.entity.getWorld().getBlockAt(j, k, l);

                    block.setStatus(5); // lightning
                    if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null)) {
                        oworld.e(j, k, l, OBlock.au.cm);
                    }
                }
            }
        }

    }

    public void j_() {
        super.j_();
        if (this.b == 2) {
            this.p.a(this.t, this.u, this.v, "ambient.weather.thunder", 10000.0F, 0.8F + this.aa.nextFloat() * 0.2F);
            this.p.a(this.t, this.u, this.v, "random.explode", 2.0F, 0.5F + this.aa.nextFloat() * 0.2F);
        }

        --this.b;
        if (this.b < 0) {
            if (this.c == 0) {
                this.x();
            } else if (this.b < -this.aa.nextInt(10)) {
                --this.c;
                this.b = 1;
                this.a = this.aa.nextLong();
                if (this.p.a(OMathHelper.c(this.t), OMathHelper.c(this.u), OMathHelper.c(this.v), 10)) {
                    int i = OMathHelper.c(this.t);
                    int j = OMathHelper.c(this.u);
                    int k = OMathHelper.c(this.v);
                    if (this.p.a(i, j, k) == 0 && OBlock.au.b(this.p, i, j, k)) {
                        // CanaryMod: Ignite hook
                        Block block = this.entity.getWorld().getBlockAt(i, j, k);

                        block.setStatus(5); // lightning
                        if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null)) {
                        this.p.e(i, j, k, OBlock.au.cm);
                        }
                    }
                }
            }
        }

        if (this.b >= 0) {
            double d0 = 3.0D;
            List list = this.p.b((OEntity) this, OAxisAlignedBB.a().a(this.t - d0, this.u - d0, this.v - d0, this.t + d0, this.u + 6.0D + d0, this.v + d0));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                OEntity oentity = (OEntity) iterator.next();

                oentity.a(this);
            }

            this.p.r = 2;
        }

    }

    protected void a() {}

    protected void a(ONBTTagCompound onbttagcompound) {}

    protected void b(ONBTTagCompound onbttagcompound) {}
}
