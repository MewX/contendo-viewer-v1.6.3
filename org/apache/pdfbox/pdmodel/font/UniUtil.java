/*    */ package org.apache.pdfbox.pdmodel.font;
/*    */ 
/*    */ import java.util.Locale;
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
/*    */ final class UniUtil
/*    */ {
/*    */   static String getUniNameOfCodePoint(int codePoint) {
/* 34 */     String hex = Integer.toString(codePoint, 16).toUpperCase(Locale.US);
/* 35 */     switch (hex.length()) {
/*    */       
/*    */       case 1:
/* 38 */         return "uni000" + hex;
/*    */       case 2:
/* 40 */         return "uni00" + hex;
/*    */       case 3:
/* 42 */         return "uni0" + hex;
/*    */     } 
/* 44 */     return "uni" + hex;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/UniUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */