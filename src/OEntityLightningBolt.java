import java.util.List;

public class OEntityLightningBolt extends OEntityWeatherEffect {

    private int b;
    public long a = 0L;
    private int c;

    public OEntityLightningBolt(OWorld oworld, double d0, double d1, double d2) {
        super(oworld);
        this.b(d0, d1, d2, 0.0F, 0.0F);
        this.b = 2;
        this.a = this.ab.nextLong();
        this.c = this.ab.nextInt(3) + 1;
        if (!oworld.I && oworld.r >= 2 && oworld.b(OMathHelper.c(d0), OMathHelper.c(d1), OMathHelper.c(d2), 10)) {
            int i = OMathHelper.c(d0);
            int j = OMathHelper.c(d1);
            int k = OMathHelper.c(d2);

            if (oworld.a(i, j, k) == 0 && OBlock.av.c(oworld, i, j, k)) {
                // CanaryMod: Ignite hook
                Block block = this.entity.getWorld().getBlockAt(i, j, k);

                block.setStatus(5); // lightning
                if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null)) {
                    oworld.c(i, j, k, OBlock.av.cz);
                }
            }

            for (i = 0; i < 4; ++i) {
                j = OMathHelper.c(d0) + this.ab.nextInt(3) - 1;
                k = OMathHelper.c(d1) + this.ab.nextInt(3) - 1;
                int l = OMathHelper.c(d2) + this.ab.nextInt(3) - 1;
                if (oworld.a(j, k, l) == 0 && OBlock.av.c(oworld, j, k, l)) {
                    // CanaryMod: Ignite hook
                    Block block = this.entity.getWorld().getBlockAt(j, k, l);

                    block.setStatus(5); // lightning
                    if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null)) {
                        oworld.c(j, k, l, OBlock.av.cz);
                    }
                }
            }
        }
    }

    public void l_() {
        super.l_();
        if (this.b == 2) {
            this.q.a(this.u, this.v, this.w, "ambient.weather.thunder", 10000.0F, 0.8F + this.ab.nextFloat() * 0.2F);
            this.q.a(this.u, this.v, this.w, "random.explode", 2.0F, 0.5F + this.ab.nextFloat() * 0.2F);
        }

        --this.b;
        if (this.b < 0) {
            if (this.c == 0) {
                this.w();
            } else if (this.b < -this.ab.nextInt(10)) {
                --this.c;
                this.b = 1;
                this.a = this.ab.nextLong();
                if (!this.q.I && this.q.b(OMathHelper.c(this.u), OMathHelper.c(this.v), OMathHelper.c(this.w), 10)) {
                    int i = OMathHelper.c(this.u);
                    int j = OMathHelper.c(this.v);
                    int k = OMathHelper.c(this.w);
                    if (this.q.a(i, j, k) == 0 && OBlock.av.c(this.q, i, j, k)) {
                        // CanaryMod: Ignite hook
                        Block block = this.entity.getWorld().getBlockAt(i, j, k);

                        block.setStatus(5); // lightning
                        if (!(Boolean) manager.callHook(PluginLoader.Hook.IGNITE, block, null)) {
                            this.q.c(i, j, k, OBlock.av.cz);
                        }
                    }
                }
            }
        }

        if (this.b >= 0) {
            if (this.q.I) {
                this.q.q = 2;
            } else {
                double d0 = 3.0D;
                List list = this.q.b((OEntity) this, OAxisAlignedBB.a().a(this.u - d0, this.v - d0, this.w - d0, this.u + d0, this.v + 6.0D + d0, this.w + d0));

                for (int l = 0; l < list.size(); ++l) {
                    OEntity oentity = (OEntity) list.get(l);

                    oentity.a(this);
                }
            }
        }
    }

    protected void a() {}

    protected void a(ONBTTagCompound onbttagcompound) {}

    protected void b(ONBTTagCompound onbttagcompound) {}
}
