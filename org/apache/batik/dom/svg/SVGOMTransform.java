/*    */ package org.apache.batik.dom.svg;
/*    */ 
/*    */ import java.awt.geom.AffineTransform;
/*    */ import org.w3c.dom.DOMException;
/*    */ import org.w3c.dom.svg.SVGMatrix;
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
/*    */ public class SVGOMTransform
/*    */   extends AbstractSVGTransform
/*    */ {
/*    */   protected SVGMatrix createMatrix() {
/* 45 */     return new AbstractSVGMatrix() {
/*    */         protected AffineTransform getAffineTransform() {
/* 47 */           return SVGOMTransform.this.affineTransform;
/*    */         }
/*    */         public void setA(float a) throws DOMException {
/* 50 */           SVGOMTransform.this.setType((short)1);
/* 51 */           super.setA(a);
/*    */         }
/*    */         public void setB(float b) throws DOMException {
/* 54 */           SVGOMTransform.this.setType((short)1);
/* 55 */           super.setB(b);
/*    */         }
/*    */         public void setC(float c) throws DOMException {
/* 58 */           SVGOMTransform.this.setType((short)1);
/* 59 */           super.setC(c);
/*    */         }
/*    */         public void setD(float d) throws DOMException {
/* 62 */           SVGOMTransform.this.setType((short)1);
/* 63 */           super.setD(d);
/*    */         }
/*    */         public void setE(float e) throws DOMException {
/* 66 */           SVGOMTransform.this.setType((short)1);
/* 67 */           super.setE(e);
/*    */         }
/*    */         public void setF(float f) throws DOMException {
/* 70 */           SVGOMTransform.this.setType((short)1);
/* 71 */           super.setF(f);
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGOMTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */