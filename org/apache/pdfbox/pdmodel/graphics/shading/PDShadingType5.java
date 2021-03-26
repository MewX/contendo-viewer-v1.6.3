/*    */ package org.apache.pdfbox.pdmodel.graphics.shading;
/*    */ 
/*    */ import java.awt.Paint;
/*    */ import org.apache.pdfbox.cos.COSArray;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.pdmodel.common.PDRange;
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
/*    */ public class PDShadingType5
/*    */   extends PDTriangleBasedShadingType
/*    */ {
/*    */   public PDShadingType5(COSDictionary shadingDictionary) {
/* 37 */     super(shadingDictionary);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getShadingType() {
/* 43 */     return 5;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getVerticesPerRow() {
/* 54 */     return getCOSObject().getInt(COSName.VERTICES_PER_ROW, -1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setVerticesPerRow(int verticesPerRow) {
/* 64 */     getCOSObject().setInt(COSName.VERTICES_PER_ROW, verticesPerRow);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Paint toPaint(Matrix matrix) {
/* 70 */     return new Type5ShadingPaint(this, matrix);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/PDShadingType5.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */