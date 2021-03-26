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
/*    */ public class PDShadingType6
/*    */   extends PDShadingType4
/*    */ {
/*    */   public PDShadingType6(COSDictionary shadingDictionary) {
/* 36 */     super(shadingDictionary);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getShadingType() {
/* 42 */     return 6;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Paint toPaint(Matrix matrix) {
/* 48 */     return new Type6ShadingPaint(this, matrix);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/PDShadingType6.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */