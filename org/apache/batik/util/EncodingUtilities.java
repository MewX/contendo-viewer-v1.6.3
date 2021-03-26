/*    */ package org.apache.batik.util;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ public class EncodingUtilities
/*    */ {
/* 35 */   protected static final Map ENCODINGS = new HashMap<Object, Object>();
/*    */   static {
/* 37 */     ENCODINGS.put("UTF-8", "UTF8");
/* 38 */     ENCODINGS.put("UTF-16", "Unicode");
/* 39 */     ENCODINGS.put("US-ASCII", "ASCII");
/*    */     
/* 41 */     ENCODINGS.put("ISO-8859-1", "8859_1");
/* 42 */     ENCODINGS.put("ISO-8859-2", "8859_2");
/* 43 */     ENCODINGS.put("ISO-8859-3", "8859_3");
/* 44 */     ENCODINGS.put("ISO-8859-4", "8859_4");
/* 45 */     ENCODINGS.put("ISO-8859-5", "8859_5");
/* 46 */     ENCODINGS.put("ISO-8859-6", "8859_6");
/* 47 */     ENCODINGS.put("ISO-8859-7", "8859_7");
/* 48 */     ENCODINGS.put("ISO-8859-8", "8859_8");
/* 49 */     ENCODINGS.put("ISO-8859-9", "8859_9");
/* 50 */     ENCODINGS.put("ISO-2022-JP", "JIS");
/*    */     
/* 52 */     ENCODINGS.put("WINDOWS-31J", "MS932");
/* 53 */     ENCODINGS.put("EUC-JP", "EUCJIS");
/* 54 */     ENCODINGS.put("GB2312", "GB2312");
/* 55 */     ENCODINGS.put("BIG5", "Big5");
/* 56 */     ENCODINGS.put("EUC-KR", "KSC5601");
/* 57 */     ENCODINGS.put("ISO-2022-KR", "ISO2022KR");
/* 58 */     ENCODINGS.put("KOI8-R", "KOI8_R");
/*    */     
/* 60 */     ENCODINGS.put("EBCDIC-CP-US", "Cp037");
/* 61 */     ENCODINGS.put("EBCDIC-CP-CA", "Cp037");
/* 62 */     ENCODINGS.put("EBCDIC-CP-NL", "Cp037");
/* 63 */     ENCODINGS.put("EBCDIC-CP-WT", "Cp037");
/* 64 */     ENCODINGS.put("EBCDIC-CP-DK", "Cp277");
/* 65 */     ENCODINGS.put("EBCDIC-CP-NO", "Cp277");
/* 66 */     ENCODINGS.put("EBCDIC-CP-FI", "Cp278");
/* 67 */     ENCODINGS.put("EBCDIC-CP-SE", "Cp278");
/* 68 */     ENCODINGS.put("EBCDIC-CP-IT", "Cp280");
/* 69 */     ENCODINGS.put("EBCDIC-CP-ES", "Cp284");
/* 70 */     ENCODINGS.put("EBCDIC-CP-GB", "Cp285");
/* 71 */     ENCODINGS.put("EBCDIC-CP-FR", "Cp297");
/* 72 */     ENCODINGS.put("EBCDIC-CP-AR1", "Cp420");
/* 73 */     ENCODINGS.put("EBCDIC-CP-HE", "Cp424");
/* 74 */     ENCODINGS.put("EBCDIC-CP-BE", "Cp500");
/* 75 */     ENCODINGS.put("EBCDIC-CP-CH", "Cp500");
/* 76 */     ENCODINGS.put("EBCDIC-CP-ROECE", "Cp870");
/* 77 */     ENCODINGS.put("EBCDIC-CP-YU", "Cp870");
/* 78 */     ENCODINGS.put("EBCDIC-CP-IS", "Cp871");
/* 79 */     ENCODINGS.put("EBCDIC-CP-AR2", "Cp918");
/*    */     
/* 81 */     ENCODINGS.put("CP1252", "Cp1252");
/*    */   }
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
/*    */   public static String javaEncoding(String encoding) {
/* 96 */     return (String)ENCODINGS.get(encoding.toUpperCase());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/EncodingUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */