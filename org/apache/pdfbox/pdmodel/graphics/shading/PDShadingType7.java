/*    */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*    */ 
/*    */ import java.awt.Paint;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.util.Matrix;
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
/*    */ public class PDShadingType7
/*    */   extends PDShadingType6
/*    */ {
/*    */   public PDShadingType7(COSDictionary shadingDictionary) {
/* 36 */     super(shadingDictionary);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getShadingType() {
/* 42 */     return 7;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Paint toPaint(Matrix matrix) {
/* 48 */     return new Type7ShadingPaint(this, matrix);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/PDShadingType7.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */