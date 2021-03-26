/*     */ package org.apache.commons.lang3.text.translate;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.Writer;
/*     */ import java.util.Arrays;
/*     */ import java.util.EnumSet;
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
/*     */ @Deprecated
/*     */ public class NumericEntityUnescaper
/*     */   extends CharSequenceTranslator
/*     */ {
/*     */   private final EnumSet<OPTION> options;
/*     */   
/*     */   public enum OPTION
/*     */   {
/*  38 */     semiColonRequired, semiColonOptional, errorIfNoSemiColon;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NumericEntityUnescaper(OPTION... options) {
/*  60 */     if (options.length > 0) {
/*  61 */       this.options = EnumSet.copyOf(Arrays.asList(options));
/*     */     } else {
/*  63 */       this.options = EnumSet.copyOf(Arrays.asList(new OPTION[] { OPTION.semiColonRequired }));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSet(OPTION option) {
/*  74 */     return (this.options != null && this.options.contains(option));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int translate(CharSequence input, int index, Writer out) throws IOException {
/*  82 */     int seqEnd = input.length();
/*     */     
/*  84 */     if (input.charAt(index) == '&' && index < seqEnd - 2 && input.charAt(index + 1) == '#') {
/*  85 */       int entityValue, start = index + 2;
/*  86 */       boolean isHex = false;
/*     */       
/*  88 */       char firstChar = input.charAt(start);
/*  89 */       if (firstChar == 'x' || firstChar == 'X') {
/*  90 */         start++;
/*  91 */         isHex = true;
/*     */ 
/*     */         
/*  94 */         if (start == seqEnd) {
/*  95 */           return 0;
/*     */         }
/*     */       } 
/*     */       
/*  99 */       int end = start;
/*     */       
/* 101 */       while (end < seqEnd && ((input.charAt(end) >= '0' && input.charAt(end) <= '9') || (input
/* 102 */         .charAt(end) >= 'a' && input.charAt(end) <= 'f') || (input
/* 103 */         .charAt(end) >= 'A' && input.charAt(end) <= 'F'))) {
/* 104 */         end++;
/*     */       }
/*     */       
/* 107 */       boolean semiNext = (end != seqEnd && input.charAt(end) == ';');
/*     */       
/* 109 */       if (!semiNext) {
/* 110 */         if (isSet(OPTION.semiColonRequired)) {
/* 111 */           return 0;
/*     */         }
/* 113 */         if (isSet(OPTION.errorIfNoSemiColon)) {
/* 114 */           throw new IllegalArgumentException("Semi-colon required at end of numeric entity");
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 120 */         if (isHex) {
/* 121 */           entityValue = Integer.parseInt(input.subSequence(start, end).toString(), 16);
/*     */         } else {
/* 123 */           entityValue = Integer.parseInt(input.subSequence(start, end).toString(), 10);
/*     */         } 
/* 125 */       } catch (NumberFormatException nfe) {
/* 126 */         return 0;
/*     */       } 
/*     */       
/* 129 */       if (entityValue > 65535) {
/* 130 */         char[] chars = Character.toChars(entityValue);
/* 131 */         out.write(chars[0]);
/* 132 */         out.write(chars[1]);
/*     */       } else {
/* 134 */         out.write(entityValue);
/*     */       } 
/*     */       
/* 137 */       return 2 + end - start + (isHex ? 1 : 0) + (semiNext ? 1 : 0);
/*     */     } 
/* 139 */     return 0;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/text/translate/NumericEntityUnescaper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */