/*    */ package org.apache.batik.ext.awt.image.codec.png;
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
/*    */ class CRC
/*    */ {
/* 44 */   private static int[] crcTable = new int[256];
/*    */ 
/*    */   
/*    */   static {
/* 48 */     for (int n = 0; n < 256; n++) {
/* 49 */       int c = n;
/* 50 */       for (int k = 0; k < 8; k++) {
/* 51 */         if ((c & 0x1) == 1) {
/* 52 */           c = 0xEDB88320 ^ c >>> 1;
/*    */         } else {
/* 54 */           c >>>= 1;
/*    */         } 
/*    */         
/* 57 */         crcTable[n] = c;
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public static int updateCRC(int crc, byte[] data, int off, int len) {
/* 63 */     int c = crc;
/*    */     
/* 65 */     for (int n = 0; n < len; n++) {
/* 66 */       c = crcTable[(c ^ data[off + n]) & 0xFF] ^ c >>> 8;
/*    */     }
/*    */     
/* 69 */     return c;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/png/CRC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */