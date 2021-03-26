/*     */ package org.apache.pdfbox.cos;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
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
/*     */ final class PDFDocEncoding
/*     */ {
/*     */   private static final char REPLACEMENT_CHARACTER = '�';
/*  37 */   private static final int[] CODE_TO_UNI = new int[256];
/*  38 */   private static final Map<Character, Integer> UNI_TO_CODE = new HashMap<Character, Integer>(256);
/*     */   
/*     */   static {
/*  41 */     for (int i = 0; i < 256; i++) {
/*     */ 
/*     */       
/*  44 */       if (i <= 23 || i >= 32)
/*     */       {
/*     */ 
/*     */         
/*  48 */         if (i <= 126 || i >= 161)
/*     */         {
/*     */ 
/*     */           
/*  52 */           if (i != 173)
/*     */           {
/*     */ 
/*     */ 
/*     */             
/*  57 */             set(i, (char)i);
/*     */           }
/*     */         }
/*     */       }
/*     */     } 
/*  62 */     set(24, '˘');
/*  63 */     set(25, 'ˇ');
/*  64 */     set(26, 'ˆ');
/*  65 */     set(27, '˙');
/*  66 */     set(28, '˝');
/*  67 */     set(29, '˛');
/*  68 */     set(30, '˚');
/*  69 */     set(31, '˜');
/*     */     
/*  71 */     set(127, '�');
/*  72 */     set(128, '•');
/*  73 */     set(129, '†');
/*  74 */     set(130, '‡');
/*  75 */     set(131, '…');
/*  76 */     set(132, '—');
/*  77 */     set(133, '–');
/*  78 */     set(134, 'ƒ');
/*  79 */     set(135, '⁄');
/*  80 */     set(136, '‹');
/*  81 */     set(137, '›');
/*  82 */     set(138, '−');
/*  83 */     set(139, '‰');
/*  84 */     set(140, '„');
/*  85 */     set(141, '“');
/*  86 */     set(142, '”');
/*  87 */     set(143, '‘');
/*  88 */     set(144, '’');
/*  89 */     set(145, '‚');
/*  90 */     set(146, '™');
/*  91 */     set(147, 'ﬁ');
/*  92 */     set(148, 'ﬂ');
/*  93 */     set(149, 'Ł');
/*  94 */     set(150, 'Œ');
/*  95 */     set(151, 'Š');
/*  96 */     set(152, 'Ÿ');
/*  97 */     set(153, 'Ž');
/*  98 */     set(154, 'ı');
/*  99 */     set(155, 'ł');
/* 100 */     set(156, 'œ');
/* 101 */     set(157, 'š');
/* 102 */     set(158, 'ž');
/* 103 */     set(159, '�');
/* 104 */     set(160, '€');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void set(int code, char unicode) {
/* 114 */     CODE_TO_UNI[code] = unicode;
/* 115 */     UNI_TO_CODE.put(Character.valueOf(unicode), Integer.valueOf(code));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toString(byte[] bytes) {
/* 123 */     StringBuilder sb = new StringBuilder();
/* 124 */     for (byte b : bytes) {
/*     */       
/* 126 */       if ((b & 0xFF) >= CODE_TO_UNI.length) {
/*     */         
/* 128 */         sb.append('?');
/*     */       }
/*     */       else {
/*     */         
/* 132 */         sb.append((char)CODE_TO_UNI[b & 0xFF]);
/*     */       } 
/*     */     } 
/* 135 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] getBytes(String text) {
/* 143 */     ByteArrayOutputStream out = new ByteArrayOutputStream();
/* 144 */     for (char c : text.toCharArray()) {
/*     */       
/* 146 */       Integer code = UNI_TO_CODE.get(Character.valueOf(c));
/* 147 */       if (code == null) {
/*     */         
/* 149 */         out.write(0);
/*     */       }
/*     */       else {
/*     */         
/* 153 */         out.write(code.intValue());
/*     */       } 
/*     */     } 
/* 156 */     return out.toByteArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean containsChar(char character) {
/* 166 */     return UNI_TO_CODE.containsKey(Character.valueOf(character));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/PDFDocEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */