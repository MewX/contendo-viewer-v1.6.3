/*    */ package org.apache.batik.ext.awt.geom;
/*    */ 
/*    */ import java.awt.geom.Point2D;
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
/*    */ public interface Segment
/*    */   extends Cloneable
/*    */ {
/*    */   double minX();
/*    */   
/*    */   double maxX();
/*    */   
/*    */   double minY();
/*    */   
/*    */   double maxY();
/*    */   
/*    */   Rectangle2D getBounds2D();
/*    */   
/*    */   Point2D.Double evalDt(double paramDouble);
/*    */   
/*    */   Point2D.Double eval(double paramDouble);
/*    */   
/*    */   Segment getSegment(double paramDouble1, double paramDouble2);
/*    */   
/*    */   Segment splitBefore(double paramDouble);
/*    */   
/*    */   Segment splitAfter(double paramDouble);
/*    */   
/*    */   void subdivide(Segment paramSegment1, Segment paramSegment2);
/*    */   
/*    */   void subdivide(double paramDouble, Segment paramSegment1, Segment paramSegment2);
/*    */   
/*    */   double getLength();
/*    */   
/*    */   double getLength(double paramDouble);
/*    */   
/*    */   SplitResults split(double paramDouble);
/*    */   
/*    */   public static class SplitResults
/*    */   {
/*    */     Segment[] above;
/*    */     Segment[] below;
/*    */     
/*    */     SplitResults(Segment[] below, Segment[] above) {
/* 53 */       this.below = below;
/* 54 */       this.above = above;
/*    */     }
/*    */     
/*    */     Segment[] getBelow() {
/* 58 */       return this.below;
/*    */     }
/*    */     Segment[] getAbove() {
/* 61 */       return this.above;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/geom/Segment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */