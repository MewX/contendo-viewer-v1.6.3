/*    */ package org.apache.xmlgraphics.image.codec.tiff;
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
/*    */ public enum CompressionValue
/*    */ {
/* 24 */   NONE(1),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 29 */   GROUP3_1D(2),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 34 */   GROUP3_2D(3),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 39 */   GROUP4(4),
/*    */   
/* 41 */   LZW(5),
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 47 */   JPEG_BROKEN(6),
/*    */   
/* 49 */   JPEG_TTN2(7),
/*    */   
/* 51 */   PACKBITS(32773),
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 56 */   DEFLATE(32946);
/*    */   
/*    */   private final int compressionValue;
/*    */   
/*    */   CompressionValue(int compressionValue) {
/* 61 */     this.compressionValue = compressionValue;
/*    */   }
/*    */   
/*    */   int getValue() {
/* 65 */     return this.compressionValue;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static CompressionValue getValue(String name) {
/* 74 */     if (name == null) {
/* 75 */       return PACKBITS;
/*    */     }
/* 77 */     for (CompressionValue cv : values()) {
/* 78 */       if (cv.toString().equalsIgnoreCase(name)) {
/* 79 */         return cv;
/*    */       }
/*    */     } 
/* 82 */     throw new IllegalArgumentException("Unknown compression value: " + name);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/tiff/CompressionValue.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */