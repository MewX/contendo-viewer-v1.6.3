/*    */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*    */ 
/*    */ import java.awt.Point;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
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
/*    */ class IntPoint
/*    */   extends Point
/*    */ {
/* 36 */   private static final Log LOG = LogFactory.getLog(IntPoint.class);
/*    */ 
/*    */   
/*    */   IntPoint(int x, int y) {
/* 40 */     super(x, y);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 46 */     return 89 * (623 + this.x) + this.y;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 52 */     if (this == obj)
/*    */     {
/* 54 */       return true;
/*    */     }
/* 56 */     if (obj == null)
/*    */     {
/* 58 */       return false;
/*    */     }
/* 60 */     if (getClass() != obj.getClass()) {
/*    */       
/* 62 */       if (obj instanceof java.awt.geom.Point2D)
/*    */       {
/*    */         
/* 65 */         LOG.error("IntPoint should not be used together with its base class");
/*    */       }
/* 67 */       return false;
/*    */     } 
/* 69 */     IntPoint other = (IntPoint)obj;
/* 70 */     return (this.x == other.x && this.y == other.y);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/IntPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */