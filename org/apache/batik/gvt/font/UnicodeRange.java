/*    */ package org.apache.batik.gvt.font;
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
/*    */ public class UnicodeRange
/*    */ {
/*    */   private int firstUnicodeValue;
/*    */   private int lastUnicodeValue;
/*    */   
/*    */   public UnicodeRange(String unicodeRange) {
/* 40 */     if (unicodeRange.startsWith("U+") && unicodeRange.length() > 2) {
/*    */       String firstValue, lastValue;
/* 42 */       unicodeRange = unicodeRange.substring(2);
/* 43 */       int dashIndex = unicodeRange.indexOf('-');
/*    */ 
/*    */ 
/*    */       
/* 47 */       if (dashIndex != -1) {
/* 48 */         firstValue = unicodeRange.substring(0, dashIndex);
/* 49 */         lastValue = unicodeRange.substring(dashIndex + 1);
/*    */       } else {
/*    */         
/* 52 */         firstValue = unicodeRange;
/* 53 */         lastValue = unicodeRange;
/* 54 */         if (unicodeRange.indexOf('?') != -1) {
/* 55 */           firstValue = firstValue.replace('?', '0');
/* 56 */           lastValue = lastValue.replace('?', 'F');
/*    */         } 
/*    */       } 
/*    */       try {
/* 60 */         this.firstUnicodeValue = Integer.parseInt(firstValue, 16);
/* 61 */         this.lastUnicodeValue = Integer.parseInt(lastValue, 16);
/* 62 */       } catch (NumberFormatException e) {
/* 63 */         this.firstUnicodeValue = -1;
/* 64 */         this.lastUnicodeValue = -1;
/*    */       } 
/*    */     } else {
/*    */       
/* 68 */       this.firstUnicodeValue = -1;
/* 69 */       this.lastUnicodeValue = -1;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean contains(String unicode) {
/* 77 */     if (unicode.length() == 1) {
/* 78 */       int unicodeVal = unicode.charAt(0);
/* 79 */       return contains(unicodeVal);
/*    */     } 
/* 81 */     return false;
/*    */   }
/*    */   
/*    */   public boolean contains(int unicodeVal) {
/* 85 */     return (unicodeVal >= this.firstUnicodeValue && unicodeVal <= this.lastUnicodeValue);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/UnicodeRange.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */