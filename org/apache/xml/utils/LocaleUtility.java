/*    */ package org.apache.xml.utils;
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
/*    */ 
/*    */ public class LocaleUtility
/*    */ {
/*    */   public static final char IETF_SEPARATOR = '-';
/*    */   public static final String EMPTY_STRING = "";
/*    */   
/*    */   public static Locale langToLocale(String lang) {
/* 38 */     if (lang == null || lang.equals("")) {
/* 39 */       return Locale.getDefault();
/*    */     }
/* 41 */     String language = "";
/* 42 */     String country = "";
/* 43 */     String variant = "";
/*    */     
/* 45 */     int i1 = lang.indexOf('-');
/* 46 */     if (i1 < 0) {
/* 47 */       language = lang;
/*    */     } else {
/* 49 */       language = lang.substring(0, i1);
/* 50 */       i1++;
/* 51 */       int i2 = lang.indexOf('-', i1);
/* 52 */       if (i2 < 0) {
/* 53 */         country = lang.substring(i1);
/*    */       } else {
/* 55 */         country = lang.substring(i1, i2);
/* 56 */         variant = lang.substring(i2 + 1);
/*    */       } 
/*    */     } 
/*    */     
/* 60 */     if (language.length() == 2) {
/* 61 */       language = language.toLowerCase();
/*    */     } else {
/* 63 */       language = "";
/*    */     } 
/*    */     
/* 66 */     if (country.length() == 2) {
/* 67 */       country = country.toUpperCase();
/*    */     } else {
/* 69 */       country = "";
/*    */     } 
/*    */     
/* 72 */     if (variant.length() > 0 && (language.length() == 2 || country.length() == 2)) {
/*    */       
/* 74 */       variant = variant.toUpperCase();
/*    */     } else {
/* 76 */       variant = "";
/*    */     } 
/*    */     
/* 79 */     return new Locale(language, country, variant);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/LocaleUtility.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */