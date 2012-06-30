 import java.util.Random;
 
 public class OEntityEgg extends OEntityThrowable
 {
   public OEntityEgg(OWorld paramOWorld)
   {
     super(paramOWorld);
   }
 
   public OEntityEgg(OWorld paramOWorld, OEntityLiving paramOEntityLiving) {
     super(paramOWorld, paramOEntityLiving);
   }
 
   public OEntityEgg(OWorld paramOWorld, double paramDouble1, double paramDouble2, double paramDouble3) {
     super(paramOWorld, paramDouble1, paramDouble2, paramDouble3);
   }
 
   protected void a(OMovingObjectPosition paramOMovingObjectPosition)
   {
       BaseEntity var1 = new BaseEntity(this);
       int var2 = 1;
etc.getLoader().callHook(PluginLoader.Hook.IMPACT, var1, var2);
     if (((paramOMovingObjectPosition.g == null) || 
       (!paramOMovingObjectPosition.g.a(ODamageSource.a(this, this.c), 0))) || (
       (!this.bi.F) && (this.bS.nextInt(8) == 0))) {
       int i = 1;
       if (this.bS.nextInt(32) == 0) i = 4;
       for (int j = 0; j < i; j++) {
         OEntityChicken localOEntityChicken = new OEntityChicken(this.bi);
         localOEntityChicken.c(-24000);
 
         localOEntityChicken.c(this.bm, this.bn, this.bo, this.bs, 0.0F);
         this.bi.b(localOEntityChicken);
         
      }
     }
 
     for (int i = 0; i < 8; i++) {
       this.bi.a("snowballpoof", this.bm, this.bn, this.bo, 0.0D, 0.0D, 0.0D);
     }
     if (!this.bi.F)
       X();
}
}