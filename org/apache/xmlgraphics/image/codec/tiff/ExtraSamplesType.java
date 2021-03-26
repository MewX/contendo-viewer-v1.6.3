/*    */ package org.apache.xmlgraphics.image.codec.tiff;
/*    */ 
/*    */ import java.awt.image.ColorModel;
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
/*    */ enum ExtraSamplesType
/*    */ {
/* 23 */   UNSPECIFIED(0),
/* 24 */   ASSOCIATED_ALPHA(1),
/* 25 */   UNASSOCIATED_ALPHA(2);
/*    */   
/*    */   private final int typeValue;
/*    */   
/*    */   ExtraSamplesType(int value) {
/* 30 */     this.typeValue = value;
/*    */   }
/*    */   
/*    */   static ExtraSamplesType getValue(ColorModel colorModel, int numExtraSamples) {
/* 34 */     if (numExtraSamples == 1 && colorModel.hasAlpha()) {
/* 35 */       return colorModel.isAlphaPremultiplied() ? ASSOCIATED_ALPHA : UNASSOCIATED_ALPHA;
/*    */     }
/* 37 */     return UNSPECIFIED;
/*    */   }
/*    */   
/*    */   int getValue() {
/* 41 */     return this.typeValue;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/tiff/ExtraSamplesType.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */