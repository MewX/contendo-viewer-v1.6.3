/*     */ package org.apache.batik.xml;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PushbackInputStream;
/*     */ import java.io.Reader;
/*     */ import org.apache.batik.util.EncodingUtilities;
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
/*     */ public class XMLUtilities
/*     */   extends XMLCharacters
/*     */ {
/*     */   public static final int IS_XML_10_NAME = 1;
/*     */   public static final int IS_XML_10_QNAME = 2;
/*     */   
/*     */   public static boolean isXMLSpace(char c) {
/*  53 */     return (c <= ' ' && (4294977024L >> c & 0x1L) != 0L);
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
/*     */   public static boolean isXMLNameFirstCharacter(char c) {
/*  65 */     return ((NAME_FIRST_CHARACTER[c / 32] & 1 << c % 32) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isXML11NameFirstCharacter(char c) {
/*  73 */     return ((NAME11_FIRST_CHARACTER[c / 32] & 1 << c % 32) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isXMLNameCharacter(char c) {
/*  80 */     return ((NAME_CHARACTER[c / 32] & 1 << c % 32) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isXML11NameCharacter(char c) {
/*  87 */     return ((NAME11_CHARACTER[c / 32] & 1 << c % 32) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isXMLCharacter(int c) {
/*  98 */     return ((XML_CHARACTER[c >>> 5] & 1 << (c & 0x1F)) != 0 || (c >= 65536 && c <= 1114111));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isXML11Character(int c) {
/* 106 */     return ((c >= 1 && c <= 55295) || (c >= 57344 && c <= 65533) || (c >= 65536 && c <= 1114111));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isXMLPublicIdCharacter(char c) {
/* 115 */     return (c < '' && (PUBLIC_ID_CHARACTER[c / 32] & 1 << c % 32) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isXMLVersionCharacter(char c) {
/* 123 */     return (c < '' && (VERSION_CHARACTER[c / 32] & 1 << c % 32) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isXMLAlphabeticCharacter(char c) {
/* 131 */     return (c < '' && (ALPHABETIC_CHARACTER[c / 32] & 1 << c % 32) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int testXMLQName(String s) {
/* 141 */     int isQName = 2;
/* 142 */     boolean foundColon = false;
/* 143 */     int len = s.length();
/* 144 */     if (len == 0) {
/* 145 */       return 0;
/*     */     }
/* 147 */     char c = s.charAt(0);
/* 148 */     if (!isXMLNameFirstCharacter(c)) {
/* 149 */       return 0;
/*     */     }
/* 151 */     if (c == ':') {
/* 152 */       isQName = 0;
/*     */     }
/* 154 */     for (int i = 1; i < len; i++) {
/* 155 */       c = s.charAt(i);
/* 156 */       if (!isXMLNameCharacter(c)) {
/* 157 */         return 0;
/*     */       }
/* 159 */       if (isQName != 0 && c == ':') {
/* 160 */         if (foundColon || i == len - 1) {
/* 161 */           isQName = 0;
/*     */         } else {
/* 163 */           foundColon = true;
/*     */         } 
/*     */       }
/*     */     } 
/* 167 */     return 0x1 | isQName;
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
/*     */   public static Reader createXMLDocumentReader(InputStream is) throws IOException {
/* 183 */     PushbackInputStream pbis = new PushbackInputStream(is, 128);
/* 184 */     byte[] buf = new byte[4];
/*     */     
/* 186 */     int len = pbis.read(buf);
/* 187 */     if (len > 0) {
/* 188 */       pbis.unread(buf, 0, len);
/*     */     }
/*     */     
/* 191 */     if (len == 4) {
/* 192 */       switch (buf[0] & 0xFF) {
/*     */         case 0:
/* 194 */           if (buf[1] == 60 && buf[2] == 0 && buf[3] == 63) {
/* 195 */             return new InputStreamReader(pbis, "UnicodeBig");
/*     */           }
/*     */           break;
/*     */         
/*     */         case 60:
/* 200 */           switch (buf[1] & 0xFF) {
/*     */             case 0:
/* 202 */               if (buf[2] == 63 && buf[3] == 0) {
/* 203 */                 return new InputStreamReader(pbis, "UnicodeLittle");
/*     */               }
/*     */               break;
/*     */             
/*     */             case 63:
/* 208 */               if (buf[2] == 120 && buf[3] == 109) {
/* 209 */                 Reader r = createXMLDeclarationReader(pbis, "UTF8");
/* 210 */                 String enc = getXMLDeclarationEncoding(r, "UTF8");
/* 211 */                 return new InputStreamReader(pbis, enc);
/*     */               } 
/*     */               break;
/*     */           } 
/*     */           break;
/*     */         case 76:
/* 217 */           if (buf[1] == 111 && (buf[2] & 0xFF) == 167 && (buf[3] & 0xFF) == 148) {
/*     */ 
/*     */             
/* 220 */             Reader r = createXMLDeclarationReader(pbis, "CP037");
/* 221 */             String enc = getXMLDeclarationEncoding(r, "CP037");
/* 222 */             return new InputStreamReader(pbis, enc);
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 254:
/* 227 */           if ((buf[1] & 0xFF) == 255) {
/* 228 */             return new InputStreamReader(pbis, "Unicode");
/*     */           }
/*     */           break;
/*     */         
/*     */         case 255:
/* 233 */           if ((buf[1] & 0xFF) == 254) {
/* 234 */             return new InputStreamReader(pbis, "Unicode");
/*     */           }
/*     */           break;
/*     */       } 
/*     */     }
/* 239 */     return new InputStreamReader(pbis, "UTF8");
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
/*     */   protected static Reader createXMLDeclarationReader(PushbackInputStream pbis, String enc) throws IOException {
/* 251 */     byte[] buf = new byte[128];
/* 252 */     int len = pbis.read(buf);
/*     */     
/* 254 */     if (len > 0) {
/* 255 */       pbis.unread(buf, 0, len);
/*     */     }
/*     */     
/* 258 */     return new InputStreamReader(new ByteArrayInputStream(buf, 4, len), enc);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String getXMLDeclarationEncoding(Reader r, String e) throws IOException {
/*     */     int c;
/* 270 */     if ((c = r.read()) != 108) {
/* 271 */       return e;
/*     */     }
/*     */     
/* 274 */     if (!isXMLSpace((char)(c = r.read()))) {
/* 275 */       return e;
/*     */     }
/*     */     
/* 278 */     while (isXMLSpace((char)(c = r.read())));
/*     */ 
/*     */ 
/*     */     
/* 282 */     if (c != 118) {
/* 283 */       return e;
/*     */     }
/* 285 */     if ((c = r.read()) != 101) {
/* 286 */       return e;
/*     */     }
/* 288 */     if ((c = r.read()) != 114) {
/* 289 */       return e;
/*     */     }
/* 291 */     if ((c = r.read()) != 115) {
/* 292 */       return e;
/*     */     }
/* 294 */     if ((c = r.read()) != 105) {
/* 295 */       return e;
/*     */     }
/* 297 */     if ((c = r.read()) != 111) {
/* 298 */       return e;
/*     */     }
/* 300 */     if ((c = r.read()) != 110) {
/* 301 */       return e;
/*     */     }
/*     */     
/* 304 */     c = r.read();
/* 305 */     while (isXMLSpace((char)c)) {
/* 306 */       c = r.read();
/*     */     }
/*     */     
/* 309 */     if (c != 61) {
/* 310 */       return e;
/*     */     }
/*     */     
/* 313 */     while (isXMLSpace((char)(c = r.read())));
/*     */ 
/*     */ 
/*     */     
/* 317 */     if (c != 34 && c != 39) {
/* 318 */       return e;
/*     */     }
/* 320 */     char sc = (char)c;
/*     */     
/*     */     while (true) {
/* 323 */       c = r.read();
/* 324 */       if (c == sc) {
/*     */         break;
/*     */       }
/* 327 */       if (!isXMLVersionCharacter((char)c)) {
/* 328 */         return e;
/*     */       }
/*     */     } 
/*     */     
/* 332 */     if (!isXMLSpace((char)(c = r.read()))) {
/* 333 */       return e;
/*     */     }
/* 335 */     while (isXMLSpace((char)(c = r.read())));
/*     */ 
/*     */ 
/*     */     
/* 339 */     if (c != 101) {
/* 340 */       return e;
/*     */     }
/* 342 */     if ((c = r.read()) != 110) {
/* 343 */       return e;
/*     */     }
/* 345 */     if ((c = r.read()) != 99) {
/* 346 */       return e;
/*     */     }
/* 348 */     if ((c = r.read()) != 111) {
/* 349 */       return e;
/*     */     }
/* 351 */     if ((c = r.read()) != 100) {
/* 352 */       return e;
/*     */     }
/* 354 */     if ((c = r.read()) != 105) {
/* 355 */       return e;
/*     */     }
/* 357 */     if ((c = r.read()) != 110) {
/* 358 */       return e;
/*     */     }
/* 360 */     if ((c = r.read()) != 103) {
/* 361 */       return e;
/*     */     }
/*     */     
/* 364 */     c = r.read();
/* 365 */     while (isXMLSpace((char)c)) {
/* 366 */       c = r.read();
/*     */     }
/*     */     
/* 369 */     if (c != 61) {
/* 370 */       return e;
/*     */     }
/*     */     
/* 373 */     while (isXMLSpace((char)(c = r.read())));
/*     */ 
/*     */ 
/*     */     
/* 377 */     if (c != 34 && c != 39) {
/* 378 */       return e;
/*     */     }
/* 380 */     sc = (char)c;
/*     */     
/* 382 */     StringBuffer enc = new StringBuffer();
/*     */     while (true) {
/* 384 */       c = r.read();
/* 385 */       if (c == -1) {
/* 386 */         return e;
/*     */       }
/* 388 */       if (c == sc) {
/* 389 */         return encodingToJavaEncoding(enc.toString(), e);
/*     */       }
/* 391 */       enc.append((char)c);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String encodingToJavaEncoding(String e, String de) {
/* 402 */     String result = EncodingUtilities.javaEncoding(e);
/* 403 */     return (result == null) ? de : result;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/xml/XMLUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */