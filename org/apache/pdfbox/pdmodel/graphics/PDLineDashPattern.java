/*    */ package org.apache.pdfbox.pdmodel.graphics;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import org.apache.pdfbox.cos.COSArray;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSInteger;
/*    */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
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
/*    */ public final class PDLineDashPattern
/*    */   implements COSObjectable
/*    */ {
/*    */   private final int phase;
/*    */   private final float[] array;
/*    */   
/*    */   public PDLineDashPattern() {
/* 43 */     this.array = new float[0];
/* 44 */     this.phase = 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDLineDashPattern(COSArray array, int phase) {
/* 54 */     this.array = array.toFloatArray();
/* 55 */     this.phase = phase;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public COSBase getCOSObject() {
/* 61 */     COSArray cos = new COSArray();
/* 62 */     COSArray patternArray = new COSArray();
/* 63 */     patternArray.setFloatArray(this.array);
/* 64 */     cos.add((COSBase)patternArray);
/* 65 */     cos.add((COSBase)COSInteger.get(this.phase));
/* 66 */     return (COSBase)cos;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPhase() {
/* 76 */     return this.phase;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float[] getDashArray() {
/* 85 */     return (float[])this.array.clone();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 91 */     return "PDLineDashPattern{array=" + Arrays.toString(this.array) + ", phase=" + this.phase + "}";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/PDLineDashPattern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */