 public class OEntitySnowball extends OEntityThrowable
 {
   public OEntitySnowball(OWorld paramOWorld)
   {
     super(paramOWorld);
   }
 
   public OEntitySnowball(OWorld paramOWorld, OEntityLiving paramOEntityLiving) {
     super(paramOWorld, paramOEntityLiving);
   }
 
   public OEntitySnowball(OWorld paramOWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
     super(paramOWorld, paramDouble1, paramDouble2, paramDouble3);
   }
 
   protected void a(OMovingObjectPosition paramOMovingObjectPosition)
   {
       BaseEntity var1 = new BaseEntity(this);
       int var2 = 2;
       etc.getLoader().callHook(PluginLoader.Hook.IMPACT, var1, var2);
     if (paramOMovingObjectPosition.g != null) {
       int i = 0;
       if ((paramOMovingObjectPosition.g instanceof OEntityBlaze)) {
         i = 3;
       }
       if (!paramOMovingObjectPosition.g.a(ODamageSource.a(this, this.c), i));
     }
 
     for (int i = 0; i < 8; i++)
       this.bi.a("snowballpoof", this.bm, this.bn, this.bo, 0.0D, 0.0D, 0.0D);
     if (!this.bi.F)
       X();

   }
 }