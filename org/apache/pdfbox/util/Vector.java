/*    */ package org.apache.pdfbox.util;
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
/*    */ public final class Vector
/*    */ {
/*    */   private final float x;
/*    */   private final float y;
/*    */   
/*    */   public Vector(float x, float y) {
/* 30 */     this.x = x;
/* 31 */     this.y = y;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getX() {
/* 39 */     return this.x;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getY() {
/* 47 */     return this.y;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Vector scale(float sxy) {
/* 57 */     return new Vector(this.x * sxy, this.y * sxy);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     return "(" + this.x + ", " + this.y + ")";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/util/Vector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */