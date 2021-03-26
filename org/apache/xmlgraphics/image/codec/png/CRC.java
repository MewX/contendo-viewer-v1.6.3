/*    */ package org.apache.xmlgraphics.image.codec.png;
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
/*    */ final class CRC
/*    */ {
/* 56 */   private static int[] crcTable = new int[256];
/*    */ 
/*    */   
/*    */   static {
/* 60 */     for (int n = 0; n < 256; n++) {
/* 61 */       int c = n;
/* 62 */       for (int k = 0; k < 8; k++) {
/* 63 */         if ((c & 0x1) == 1) {
/* 64 */           c = 0xEDB88320 ^ c >>> 1;
/*    */         } else {
/* 66 */           c >>>= 1;
/*    */         } 
/*    */         
/* 69 */         crcTable[n] = c;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static int updateCRC(int crc, byte[] data, int off, int len) {
/* 75 */     int c = crc;
/*    */     
/* 77 */     for (int n = 0; n < len; n++) {
/* 78 */       c = crcTable[(c ^ data[off + n]) & 0xFF] ^ c >>> 8;
/*    */     }
/*    */     
/* 81 */     return c;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/png/CRC.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */