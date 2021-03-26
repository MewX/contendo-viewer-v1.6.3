/*    */ package org.apache.batik.gvt;
/*    */ 
/*    */ import java.awt.geom.Point2D;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Marker
/*    */ {
/*    */   protected double orient;
/*    */   protected GraphicsNode markerNode;
/*    */   protected Point2D ref;
/*    */   
/*    */   public Marker(GraphicsNode markerNode, Point2D ref, double orient) {
/* 59 */     if (markerNode == null) {
/* 60 */       throw new IllegalArgumentException();
/*    */     }
/*    */     
/* 63 */     if (ref == null) {
/* 64 */       throw new IllegalArgumentException();
/*    */     }
/*    */     
/* 67 */     this.markerNode = markerNode;
/* 68 */     this.ref = ref;
/* 69 */     this.orient = orient;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Point2D getRef() {
/* 76 */     return (Point2D)this.ref.clone();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double getOrient() {
/* 83 */     return this.orient;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public GraphicsNode getMarkerNode() {
/* 90 */     return this.markerNode;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/Marker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */