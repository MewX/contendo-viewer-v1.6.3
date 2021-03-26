/*    */ package jp.cssj.sakae.d;
/*    */ 
/*    */ import java.awt.GraphicsConfigTemplate;
/*    */ import java.awt.GraphicsConfiguration;
/*    */ import java.awt.GraphicsDevice;
/*    */ 
/*    */ class f
/*    */   extends GraphicsDevice {
/*    */   private final GraphicsConfiguration a;
/*    */   
/*    */   f(e config) {
/* 12 */     this.a = config;
/*    */   }
/*    */   
/*    */   public GraphicsConfiguration getBestConfiguration(GraphicsConfigTemplate gct) {
/* 16 */     return this.a;
/*    */   }
/*    */   
/*    */   public GraphicsConfiguration[] getConfigurations() {
/* 20 */     return new GraphicsConfiguration[] { this.a };
/*    */   }
/*    */   
/*    */   public GraphicsConfiguration getDefaultConfiguration() {
/* 24 */     return this.a;
/*    */   }
/*    */   
/*    */   public String getIDstring() {
/* 28 */     return toString();
/*    */   }
/*    */   
/*    */   public int getType() {
/* 32 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/d/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */