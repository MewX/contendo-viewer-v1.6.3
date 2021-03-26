/*     */ package org.apache.pdfbox.util;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
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
/*     */ public final class Hex
/*     */ {
/*  35 */   private static final Log LOG = LogFactory.getLog(Hex.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   private static final byte[] HEX_BYTES = new byte[] { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
/*  44 */   private static final char[] HEX_CHARS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getString(byte b) {
/*  53 */     char[] chars = { HEX_CHARS[getHighNibble(b)], HEX_CHARS[getLowNibble(b)] };
/*  54 */     return new String(chars);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getString(byte[] bytes) {
/*  62 */     StringBuilder string = new StringBuilder(bytes.length * 2);
/*  63 */     for (byte b : bytes)
/*     */     {
/*  65 */       string.append(HEX_CHARS[getHighNibble(b)]).append(HEX_CHARS[getLowNibble(b)]);
/*     */     }
/*  67 */     return string.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] getBytes(byte b) {
/*  75 */     return new byte[] { HEX_BYTES[getHighNibble(b)], HEX_BYTES[getLowNibble(b)] };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] getBytes(byte[] bytes) {
/*  83 */     byte[] asciiBytes = new byte[bytes.length * 2];
/*  84 */     for (int i = 0; i < bytes.length; i++) {
/*     */       
/*  86 */       asciiBytes[i * 2] = HEX_BYTES[getHighNibble(bytes[i])];
/*  87 */       asciiBytes[i * 2 + 1] = HEX_BYTES[getLowNibble(bytes[i])];
/*     */     } 
/*  89 */     return asciiBytes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static char[] getChars(short num) {
/*  97 */     char[] hex = new char[4];
/*  98 */     hex[0] = HEX_CHARS[num >> 12 & 0xF];
/*  99 */     hex[1] = HEX_CHARS[num >> 8 & 0xF];
/* 100 */     hex[2] = HEX_CHARS[num >> 4 & 0xF];
/* 101 */     hex[3] = HEX_CHARS[num & 0xF];
/* 102 */     return hex;
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
/*     */   public static char[] getCharsUTF16BE(String text) {
/* 122 */     char[] hex = new char[text.length() * 4];
/*     */     
/* 124 */     for (int stringIdx = 0, charIdx = 0; stringIdx < text.length(); stringIdx++) {
/*     */       
/* 126 */       char c = text.charAt(stringIdx);
/* 127 */       hex[charIdx++] = HEX_CHARS[c >> 12 & 0xF];
/* 128 */       hex[charIdx++] = HEX_CHARS[c >> 8 & 0xF];
/* 129 */       hex[charIdx++] = HEX_CHARS[c >> 4 & 0xF];
/* 130 */       hex[charIdx++] = HEX_CHARS[c & 0xF];
/*     */     } 
/*     */     
/* 133 */     return hex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeHexByte(byte b, OutputStream output) throws IOException {
/* 144 */     output.write(HEX_BYTES[getHighNibble(b)]);
/* 145 */     output.write(HEX_BYTES[getLowNibble(b)]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void writeHexBytes(byte[] bytes, OutputStream output) throws IOException {
/* 156 */     for (byte b : bytes)
/*     */     {
/* 158 */       writeHexByte(b, output);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getHighNibble(byte b) {
/* 170 */     return (b & 0xF0) >> 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int getLowNibble(byte b) {
/* 181 */     return b & 0xF;
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
/*     */   public static byte[] decodeBase64(String base64Value) {
/*     */     try {
/* 199 */       Class<?> b64Class = Class.forName("java.util.Base64");
/* 200 */       Method getDecoderMethod = b64Class.getMethod("getDecoder", new Class[0]);
/* 201 */       Object base64Decoder = getDecoderMethod.invoke(b64Class, new Object[0]);
/* 202 */       Method decodeMethod = base64Decoder.getClass().getMethod("decode", new Class[] { String.class });
/* 203 */       return (byte[])decodeMethod.invoke(base64Decoder, new Object[] { base64Value.replaceAll("\\s", "") });
/*     */     }
/* 205 */     catch (ClassNotFoundException ex) {
/*     */       
/* 207 */       LOG.debug(ex);
/*     */     }
/* 209 */     catch (IllegalAccessException ex) {
/*     */       
/* 211 */       LOG.debug(ex);
/*     */     }
/* 213 */     catch (IllegalArgumentException ex) {
/*     */       
/* 215 */       LOG.debug(ex);
/*     */     }
/* 217 */     catch (NoSuchMethodException ex) {
/*     */       
/* 219 */       LOG.debug(ex);
/*     */     }
/* 221 */     catch (SecurityException ex) {
/*     */       
/* 223 */       LOG.debug(ex);
/*     */     }
/* 225 */     catch (InvocationTargetException ex) {
/*     */       
/* 227 */       LOG.debug(ex);
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 232 */       Class<?> datatypeConverterClass = Class.forName("javax.xml.bind.DatatypeConverter");
/* 233 */       Method parseBase64BinaryMethod = datatypeConverterClass.getMethod("parseBase64Binary", new Class[] { String.class });
/* 234 */       return (byte[])parseBase64BinaryMethod.invoke(null, new Object[] { base64Value });
/*     */     }
/* 236 */     catch (ClassNotFoundException ex) {
/*     */       
/* 238 */       LOG.debug(ex);
/*     */     }
/* 240 */     catch (IllegalAccessException ex) {
/*     */       
/* 242 */       LOG.debug(ex);
/*     */     }
/* 244 */     catch (IllegalArgumentException ex) {
/*     */       
/* 246 */       LOG.debug(ex);
/*     */     }
/* 248 */     catch (NoSuchMethodException ex) {
/*     */       
/* 250 */       LOG.debug(ex);
/*     */     }
/* 252 */     catch (SecurityException ex) {
/*     */       
/* 254 */       LOG.debug(ex);
/*     */     }
/* 256 */     catch (InvocationTargetException ex) {
/*     */       
/* 258 */       LOG.debug(ex);
/*     */     } 
/* 260 */     LOG.error("Can't decode base64 value, try adding javax.xml.bind:jaxb-api to your build");
/* 261 */     return new byte[0];
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
/*     */   public static byte[] decodeHex(String s) throws IOException {
/* 273 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 274 */     int i = 0;
/* 275 */     while (i < s.length() - 1) {
/*     */       
/* 277 */       if (s.charAt(i) == '\n' || s.charAt(i) == '\r') {
/*     */         
/* 279 */         i++;
/*     */         
/*     */         continue;
/*     */       } 
/* 283 */       String hexByte = s.substring(i, i + 2);
/*     */       
/*     */       try {
/* 286 */         baos.write(Integer.parseInt(hexByte, 16));
/*     */       }
/* 288 */       catch (NumberFormatException ex) {
/*     */         
/* 290 */         LOG.error("Can't parse " + hexByte + ", aborting decode", ex);
/*     */         break;
/*     */       } 
/* 293 */       i += 2;
/*     */     } 
/*     */     
/* 296 */     return baos.toByteArray();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/util/Hex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */