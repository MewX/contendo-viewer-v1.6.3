/*    */ package org.apache.batik.extension.svg;
/*    */ 
/*    */ import java.awt.geom.Rectangle2D;
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
/*    */ public class RegionInfo
/*    */   extends Rectangle2D.Float
/*    */ {
/* 34 */   private float verticalAlignment = 0.0F;
/*    */ 
/*    */   
/*    */   public RegionInfo(float x, float y, float w, float h, float verticalAlignment) {
/* 38 */     super(x, y, w, h);
/* 39 */     this.verticalAlignment = verticalAlignment;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getVerticalAlignment() {
/* 48 */     return this.verticalAlignment;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setVerticalAlignment(float verticalAlignment) {
/* 57 */     this.verticalAlignment = verticalAlignment;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/RegionInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */