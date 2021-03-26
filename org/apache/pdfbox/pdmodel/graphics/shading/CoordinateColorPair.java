/*    */ package org.apache.pdfbox.pdmodel.graphics.shading;
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
/*    */ class CoordinateColorPair
/*    */ {
/*    */   final Point2D coordinate;
/*    */   final float[] color;
/*    */   
/*    */   CoordinateColorPair(Point2D p, float[] c) {
/* 39 */     this.coordinate = p;
/* 40 */     this.color = (float[])c.clone();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/CoordinateColorPair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */