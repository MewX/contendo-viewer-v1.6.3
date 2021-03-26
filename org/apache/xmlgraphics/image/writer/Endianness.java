/*    */ package org.apache.xmlgraphics.image.writer;
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
/*    */ public enum Endianness
/*    */ {
/* 34 */   DEFAULT,
/*    */ 
/*    */   
/* 37 */   LITTLE_ENDIAN,
/*    */ 
/*    */   
/* 40 */   BIG_ENDIAN;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Endianness getEndianType(String value) {
/* 50 */     if (value != null) {
/* 51 */       for (Endianness endianValue : values()) {
/* 52 */         if (endianValue.toString().equalsIgnoreCase(value)) {
/* 53 */           return endianValue;
/*    */         }
/*    */       } 
/*    */     }
/* 57 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/writer/Endianness.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */