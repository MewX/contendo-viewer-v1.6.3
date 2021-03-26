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
/*    */ class Vertex
/*    */ {
/*    */   public Point2D point;
/*    */   public float[] color;
/*    */   
/*    */   Vertex(Point2D p, float[] c) {
/* 33 */     this.point = p;
/* 34 */     this.color = (float[])c.clone();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 40 */     StringBuilder sb = new StringBuilder();
/* 41 */     for (float f : this.color) {
/*    */       
/* 43 */       if (sb.length() > 0)
/*    */       {
/* 45 */         sb.append(' ');
/*    */       }
/* 47 */       sb.append(String.format("%3.2f", new Object[] { Float.valueOf(f) }));
/*    */     } 
/* 49 */     return "Vertex{ " + this.point + ", colors=[" + sb + "] }";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/Vertex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */