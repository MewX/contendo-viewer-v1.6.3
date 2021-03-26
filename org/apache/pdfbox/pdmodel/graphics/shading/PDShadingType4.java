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
/*    */ public class PDShadingType4
/*    */   extends PDTriangleBasedShadingType
/*    */ {
/*    */   public PDShadingType4(COSDictionary shadingDictionary) {
/* 37 */     super(shadingDictionary);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getShadingType() {
/* 43 */     return 4;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getBitsPerFlag() {
/* 54 */     return getCOSObject().getInt(COSName.BITS_PER_FLAG, -1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setBitsPerFlag(int bitsPerFlag) {
/* 64 */     getCOSObject().setInt(COSName.BITS_PER_FLAG, bitsPerFlag);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Paint toPaint(Matrix matrix) {
/* 70 */     return new Type4ShadingPaint(this, matrix);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/shading/PDShadingType4.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */