/*    */ package org.apache.batik.dom.svg;
/*    */ 
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
/*    */ public class SVGPointItem
/*    */   extends AbstractSVGItem
/*    */   implements SVGPoint
/*    */ {
/*    */   protected float x;
/*    */   protected float y;
/*    */   
/*    */   public SVGPointItem(float x, float y) {
/* 43 */     this.x = x;
/* 44 */     this.y = y;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected String getStringValue() {
/* 51 */     return Float.toString(this.x) + ',' + Float.toString(this.y);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getX() {
/* 60 */     return this.x;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getY() {
/* 67 */     return this.y;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setX(float x) {
/* 74 */     this.x = x;
/* 75 */     resetAttribute();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setY(float y) {
/* 82 */     this.y = y;
/* 83 */     resetAttribute();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SVGPoint matrixTransform(SVGMatrix matrix) {
/* 90 */     return SVGOMPoint.matrixTransform(this, matrix);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGPointItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */