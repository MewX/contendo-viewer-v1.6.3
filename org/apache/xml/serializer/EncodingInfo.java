/*    */ package org.apache.xml.serializer;
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
/*    */ public class EncodingInfo
/*    */ {
/*    */   final String name;
/*    */   final String javaName;
/*    */   final int lastPrintable;
/*    */   
/*    */   public EncodingInfo(String name, String javaName, int lastPrintable) {
/* 54 */     this.name = name;
/* 55 */     this.javaName = javaName;
/* 56 */     this.lastPrintable = lastPrintable;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/EncodingInfo.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */