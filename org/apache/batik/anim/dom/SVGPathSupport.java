/*    */ package org.apache.batik.anim.dom;
/*    */ 
/*    */ import java.awt.geom.Point2D;
/*    */ import org.apache.batik.dom.svg.SVGPathContext;
/*    */ import org.w3c.dom.DOMException;
/*    */ import org.w3c.dom.svg.SVGMatrix;
/*    */ import org.w3c.dom.svg.SVGPoint;
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
/*    */ public class SVGPathSupport
/*    */ {
/*    */   public static float getTotalLength(SVGOMPathElement path) {
/* 42 */     SVGPathContext pathCtx = (SVGPathContext)path.getSVGContext();
/* 43 */     return pathCtx.getTotalLength();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int getPathSegAtLength(SVGOMPathElement path, float x) {
/* 51 */     SVGPathContext pathCtx = (SVGPathContext)path.getSVGContext();
/* 52 */     return pathCtx.getPathSegAtLength(x);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static SVGPoint getPointAtLength(final SVGOMPathElement path, final float distance) {
/* 60 */     final SVGPathContext pathCtx = (SVGPathContext)path.getSVGContext();
/* 61 */     if (pathCtx == null) return null;
/*    */     
/* 63 */     return new SVGPoint() {
/*    */         public float getX() {
/* 65 */           Point2D pt = pathCtx.getPointAtLength(distance);
/* 66 */           return (float)pt.getX();
/*    */         }
/*    */         public float getY() {
/* 69 */           Point2D pt = pathCtx.getPointAtLength(distance);
/* 70 */           return (float)pt.getY();
/*    */         }
/*    */         public void setX(float x) throws DOMException {
/* 73 */           throw path.createDOMException((short)7, "readonly.point", null);
/*    */         }
/*    */ 
/*    */         
/*    */         public void setY(float y) throws DOMException {
/* 78 */           throw path.createDOMException((short)7, "readonly.point", null);
/*    */         }
/*    */ 
/*    */         
/*    */         public SVGPoint matrixTransform(SVGMatrix matrix) {
/* 83 */           throw path.createDOMException((short)7, "readonly.point", null);
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGPathSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */