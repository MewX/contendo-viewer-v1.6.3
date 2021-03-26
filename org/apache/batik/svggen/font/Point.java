/*    */ package org.apache.batik.svggen.font;
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
/*    */ public class Point
/*    */ {
/* 27 */   public int x = 0;
/* 28 */   public int y = 0;
/*    */   public boolean onCurve = true;
/*    */   public boolean endOfContour = false;
/*    */   public boolean touched = false;
/*    */   
/*    */   public Point(int x, int y, boolean onCurve, boolean endOfContour) {
/* 34 */     this.x = x;
/* 35 */     this.y = y;
/* 36 */     this.onCurve = onCurve;
/* 37 */     this.endOfContour = endOfContour;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/font/Point.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */