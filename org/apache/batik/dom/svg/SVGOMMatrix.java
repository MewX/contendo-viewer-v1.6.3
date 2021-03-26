/*    */ package org.apache.batik.dom.svg;
/*    */ 
/*    */ import java.awt.geom.AffineTransform;
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
/*    */ public class SVGOMMatrix
/*    */   extends AbstractSVGMatrix
/*    */ {
/*    */   protected AffineTransform affineTransform;
/*    */   
/*    */   public SVGOMMatrix(AffineTransform at) {
/* 41 */     this.affineTransform = at;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected AffineTransform getAffineTransform() {
/* 48 */     return this.affineTransform;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGOMMatrix.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */