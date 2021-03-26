/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.awt.GraphicsEnvironment;
/*    */ import java.awt.Toolkit;
/*    */ import org.apache.xmlgraphics.image.loader.ImageContext;
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
/*    */ public class DefaultImageContext
/*    */   implements ImageContext
/*    */ {
/*    */   private final float sourceResolution;
/*    */   
/*    */   public DefaultImageContext() {
/* 39 */     if (GraphicsEnvironment.isHeadless()) {
/* 40 */       this.sourceResolution = 72.0F;
/*    */     } else {
/* 42 */       this.sourceResolution = Toolkit.getDefaultToolkit().getScreenResolution();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public float getSourceResolution() {
/* 49 */     return this.sourceResolution;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/DefaultImageContext.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */