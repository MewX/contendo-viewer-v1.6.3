/*    */ package org.apache.batik.swing.gvt;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.util.EventObject;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GVTTreeRendererEvent
/*    */   extends EventObject
/*    */ {
/*    */   protected BufferedImage image;
/*    */   
/*    */   public GVTTreeRendererEvent(Object source, BufferedImage bi) {
/* 45 */     super(source);
/* 46 */     this.image = bi;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BufferedImage getImage() {
/* 53 */     return this.image;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/swing/gvt/GVTTreeRendererEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */