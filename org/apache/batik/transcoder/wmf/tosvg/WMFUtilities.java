/*     */ package org.apache.batik.transcoder.wmf.tosvg;
/*     */ 
/*     */ import java.io.UnsupportedEncodingException;
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
/*     */ 
/*     */ public class WMFUtilities
/*     */ {
/*     */   public static String decodeString(WMFFont wmfFont, byte[] bstr) {
/*     */     try {
/*  42 */       switch (wmfFont.charset) {
/*     */         case 0:
/*  44 */           return new String(bstr, "ISO-8859-1");
/*     */         case 1:
/*  46 */           return new String(bstr, "US-ASCII");
/*     */         case 128:
/*  48 */           return new String(bstr, "Shift_JIS");
/*     */         case 129:
/*  50 */           return new String(bstr, "cp949");
/*     */         case 130:
/*  52 */           return new String(bstr, "x-Johab");
/*     */         case 134:
/*  54 */           return new String(bstr, "GB2312");
/*     */         case 136:
/*  56 */           return new String(bstr, "Big5");
/*     */         case 161:
/*  58 */           return new String(bstr, "windows-1253");
/*     */         case 162:
/*  60 */           return new String(bstr, "cp1254");
/*     */         case 163:
/*  62 */           return new String(bstr, "cp1258");
/*     */         case 177:
/*  64 */           return new String(bstr, "windows-1255");
/*     */         case 178:
/*  66 */           return new String(bstr, "windows-1256");
/*     */         case 204:
/*  68 */           return new String(bstr, "windows-1251");
/*     */         case 222:
/*  70 */           return new String(bstr, "cp874");
/*     */         case 238:
/*  72 */           return new String(bstr, "cp1250");
/*     */         case 255:
/*  74 */           return new String(bstr, "cp437");
/*     */       } 
/*     */ 
/*     */     
/*  78 */     } catch (UnsupportedEncodingException unsupportedEncodingException) {}
/*     */ 
/*     */ 
/*     */     
/*  82 */     return new String(bstr);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getHorizontalAlignment(int align) {
/*  88 */     int v = align;
/*  89 */     v %= 24;
/*  90 */     v %= 8;
/*  91 */     if (v >= 6) return 6; 
/*  92 */     if (v >= 2) return 2; 
/*  93 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getVerticalAlignment(int align) {
/*  99 */     int v = align;
/* 100 */     if (v / 24 != 0) return 24; 
/* 101 */     v %= 24;
/* 102 */     if (v / 8 != 0) return 8; 
/* 103 */     return 0;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/wmf/tosvg/WMFUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */