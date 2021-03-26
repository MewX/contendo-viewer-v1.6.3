/*     */ package jp.cssj.sakae.sac.util;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public class EncodingUtilities
/*     */ {
/*  67 */   protected static final Map<String, String> ENCODINGS = new HashMap<>();
/*     */   static {
/*  69 */     ENCODINGS.put("UTF-8", "UTF8");
/*  70 */     ENCODINGS.put("UTF-16", "Unicode");
/*  71 */     ENCODINGS.put("US-ASCII", "ASCII");
/*     */     
/*  73 */     ENCODINGS.put("ISO-8859-1", "8859_1");
/*  74 */     ENCODINGS.put("ISO-8859-2", "8859_2");
/*  75 */     ENCODINGS.put("ISO-8859-3", "8859_3");
/*  76 */     ENCODINGS.put("ISO-8859-4", "8859_4");
/*  77 */     ENCODINGS.put("ISO-8859-5", "8859_5");
/*  78 */     ENCODINGS.put("ISO-8859-6", "8859_6");
/*  79 */     ENCODINGS.put("ISO-8859-7", "8859_7");
/*  80 */     ENCODINGS.put("ISO-8859-8", "8859_8");
/*  81 */     ENCODINGS.put("ISO-8859-9", "8859_9");
/*  82 */     ENCODINGS.put("ISO-2022-JP", "JIS");
/*     */     
/*  84 */     ENCODINGS.put("WINDOWS-31J", "MS932");
/*  85 */     ENCODINGS.put("EUC-JP", "EUCJIS");
/*  86 */     ENCODINGS.put("GB2312", "GB2312");
/*  87 */     ENCODINGS.put("BIG5", "Big5");
/*  88 */     ENCODINGS.put("EUC-KR", "KSC5601");
/*  89 */     ENCODINGS.put("ISO-2022-KR", "ISO2022KR");
/*  90 */     ENCODINGS.put("KOI8-R", "KOI8_R");
/*     */     
/*  92 */     ENCODINGS.put("EBCDIC-CP-US", "Cp037");
/*  93 */     ENCODINGS.put("EBCDIC-CP-CA", "Cp037");
/*  94 */     ENCODINGS.put("EBCDIC-CP-NL", "Cp037");
/*  95 */     ENCODINGS.put("EBCDIC-CP-WT", "Cp037");
/*  96 */     ENCODINGS.put("EBCDIC-CP-DK", "Cp277");
/*  97 */     ENCODINGS.put("EBCDIC-CP-NO", "Cp277");
/*  98 */     ENCODINGS.put("EBCDIC-CP-FI", "Cp278");
/*  99 */     ENCODINGS.put("EBCDIC-CP-SE", "Cp278");
/* 100 */     ENCODINGS.put("EBCDIC-CP-IT", "Cp280");
/* 101 */     ENCODINGS.put("EBCDIC-CP-ES", "Cp284");
/* 102 */     ENCODINGS.put("EBCDIC-CP-GB", "Cp285");
/* 103 */     ENCODINGS.put("EBCDIC-CP-FR", "Cp297");
/* 104 */     ENCODINGS.put("EBCDIC-CP-AR1", "Cp420");
/* 105 */     ENCODINGS.put("EBCDIC-CP-HE", "Cp424");
/* 106 */     ENCODINGS.put("EBCDIC-CP-BE", "Cp500");
/* 107 */     ENCODINGS.put("EBCDIC-CP-CH", "Cp500");
/* 108 */     ENCODINGS.put("EBCDIC-CP-ROECE", "Cp870");
/* 109 */     ENCODINGS.put("EBCDIC-CP-YU", "Cp870");
/* 110 */     ENCODINGS.put("EBCDIC-CP-IS", "Cp871");
/* 111 */     ENCODINGS.put("EBCDIC-CP-AR2", "Cp918");
/*     */     
/* 113 */     ENCODINGS.put("CP1252", "Cp1252");
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
/*     */   public static String javaEncoding(String encoding) {
/* 129 */     if (encoding == null) {
/* 130 */       return null;
/*     */     }
/* 132 */     String javaEncoding = ENCODINGS.get(encoding.toUpperCase());
/* 133 */     if (javaEncoding == null) {
/* 134 */       return encoding;
/*     */     }
/* 136 */     return javaEncoding;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/util/EncodingUtilities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */