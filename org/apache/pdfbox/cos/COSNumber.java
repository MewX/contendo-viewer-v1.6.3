/*     */ package org.apache.pdfbox.cos;
/*     */ 
/*     */ import java.io.IOException;
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
/*     */ public abstract class COSNumber
/*     */   extends COSBase
/*     */ {
/*     */   @Deprecated
/*  33 */   public static final COSInteger ZERO = COSInteger.ZERO;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*  39 */   public static final COSInteger ONE = COSInteger.ONE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract float floatValue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract double doubleValue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int intValue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract long longValue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static COSNumber get(String number) throws IOException {
/*  80 */     if (number.length() == 1) {
/*     */       
/*  82 */       char digit = number.charAt(0);
/*  83 */       if ('0' <= digit && digit <= '9')
/*     */       {
/*  85 */         return COSInteger.get((digit - 48));
/*     */       }
/*  87 */       if (digit == '-' || digit == '.')
/*     */       {
/*     */         
/*  90 */         return COSInteger.ZERO;
/*     */       }
/*     */ 
/*     */       
/*  94 */       throw new IOException("Not a number: " + number);
/*     */     } 
/*     */     
/*  97 */     if (number.indexOf('.') == -1 && number.toLowerCase().indexOf('e') == -1) {
/*     */       
/*     */       try {
/*     */         
/* 101 */         if (number.charAt(0) == '+')
/*     */         {
/* 103 */           return COSInteger.get(Long.parseLong(number.substring(1)));
/*     */         }
/* 105 */         return COSInteger.get(Long.parseLong(number));
/*     */       }
/* 107 */       catch (NumberFormatException e) {
/*     */ 
/*     */         
/* 110 */         return new COSFloat(number);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 115 */     return new COSFloat(number);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/COSNumber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */