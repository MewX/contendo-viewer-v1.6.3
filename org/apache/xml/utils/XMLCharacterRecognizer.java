/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLCharacterRecognizer
/*     */ {
/*     */   public static boolean isWhiteSpace(char ch) {
/*  38 */     return (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isWhiteSpace(char[] ch, int start, int length) {
/*  53 */     int end = start + length;
/*     */     
/*  55 */     for (int s = start; s < end; s++) {
/*     */       
/*  57 */       if (!isWhiteSpace(ch[s])) {
/*  58 */         return false;
/*     */       }
/*     */     } 
/*  61 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isWhiteSpace(StringBuffer buf) {
/*  73 */     int n = buf.length();
/*     */     
/*  75 */     for (int i = 0; i < n; i++) {
/*     */       
/*  77 */       if (!isWhiteSpace(buf.charAt(i))) {
/*  78 */         return false;
/*     */       }
/*     */     } 
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isWhiteSpace(String s) {
/*  93 */     if (null != s) {
/*     */       
/*  95 */       int n = s.length();
/*     */       
/*  97 */       for (int i = 0; i < n; i++) {
/*     */         
/*  99 */         if (!isWhiteSpace(s.charAt(i))) {
/* 100 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/* 104 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/XMLCharacterRecognizer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */