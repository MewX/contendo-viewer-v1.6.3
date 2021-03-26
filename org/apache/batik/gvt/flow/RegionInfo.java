/*    */ package org.apache.batik.gvt.flow;
/*    */ 
/*    */ import java.awt.Shape;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegionInfo
/*    */ {
/*    */   private Shape shape;
/*    */   private float verticalAlignment;
/*    */   
/*    */   public RegionInfo(Shape s, float verticalAlignment) {
/* 49 */     this.shape = s;
/* 50 */     this.verticalAlignment = verticalAlignment;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Shape getShape() {
/* 57 */     return this.shape;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setShape(Shape s) {
/* 64 */     this.shape = s;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getVerticalAlignment() {
/* 73 */     return this.verticalAlignment;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setVerticalAlignment(float verticalAlignment) {
/* 82 */     this.verticalAlignment = verticalAlignment;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/flow/RegionInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */