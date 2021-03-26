/*    */ package org.apache.xmlgraphics.java2d;
/*    */ 
/*    */ import java.awt.GraphicsConfigTemplate;
/*    */ import java.awt.GraphicsConfiguration;
/*    */ import java.awt.GraphicsDevice;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GenericGraphicsDevice
/*    */   extends GraphicsDevice
/*    */ {
/*    */   private final GraphicsConfiguration gc;
/*    */   
/*    */   public GenericGraphicsDevice(GraphicsConfiguration gc) {
/* 43 */     this.gc = gc;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GraphicsConfiguration getBestConfiguration(GraphicsConfigTemplate gct) {
/* 53 */     return this.gc;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GraphicsConfiguration[] getConfigurations() {
/* 62 */     return new GraphicsConfiguration[] { this.gc };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GraphicsConfiguration getDefaultConfiguration() {
/* 71 */     return this.gc;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getIDstring() {
/* 80 */     return toString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getType() {
/* 89 */     return 1;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/GenericGraphicsDevice.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */