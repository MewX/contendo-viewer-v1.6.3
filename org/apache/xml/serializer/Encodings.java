/*     */ package org.apache.xml.serializer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.io.Writer;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Properties;
/*     */ import java.util.StringTokenizer;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
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
/*     */ public class Encodings
/*     */ {
/*     */   static final int m_defaultLastPrintable = 127;
/*     */   static final String ENCODINGS_FILE = "org/apache/xml/serializer/Encodings.properties";
/*     */   static final String ENCODINGS_PROP = "org.apache.xalan.serialize.encodings";
/*  66 */   private static final Method SUN_CHAR2BYTE_CONVERTER_METHOD = findCharToByteConverterMethod();
/*     */   public static final String DEFAULT_MIME_ENCODING = "UTF-8";
/*     */   
/*     */   private static Method findCharToByteConverterMethod() {
/*     */     try {
/*  71 */       AccessController.doPrivileged(new PrivilegedAction() {
/*     */             public Object run() {
/*     */               
/*  74 */               try { Class charToByteConverterClass = Class.forName("sun.io.CharToByteConverter");
/*     */                 
/*  76 */                 Class[] argTypes = { String.class };
/*  77 */                 return charToByteConverterClass.getMethod("getConverter", argTypes); } catch (Exception e)
/*     */               
/*     */               { 
/*  80 */                 throw new RuntimeException(e.toString()); }
/*     */             
/*     */             }
/*     */           });
/*     */     } catch (Exception e) {
/*     */       
/*  86 */       System.err.println("Warning: Could not get charToByteConverterClass!");
/*     */     } 
/*     */ 
/*     */     
/*  90 */     return null;
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
/*     */   public static Writer getWriter(OutputStream output, String encoding) throws UnsupportedEncodingException {
/* 107 */     for (int i = 0; i < _encodings.length; i++) {
/*     */       
/* 109 */       if ((_encodings[i]).name.equalsIgnoreCase(encoding)) {
/*     */ 
/*     */         
/*     */         try { 
/* 113 */           return new OutputStreamWriter(output, (_encodings[i]).javaName); } catch (IllegalArgumentException iae)
/*     */         
/*     */         { 
/*     */            }
/*     */         
/* 118 */         catch (UnsupportedEncodingException unsupportedEncodingException) {}
/*     */       }
/*     */     } 
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
/* 131 */     try { return new OutputStreamWriter(output, encoding); } catch (IllegalArgumentException iae)
/*     */     
/*     */     { 
/*     */       
/* 135 */       throw new UnsupportedEncodingException(encoding); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Object getCharToByteConverter(String encoding) {
/* 147 */     if (SUN_CHAR2BYTE_CONVERTER_METHOD == null) {
/* 148 */       return null;
/*     */     }
/*     */     
/* 151 */     Object[] args = new Object[1];
/* 152 */     for (int i = 0; i < _encodings.length; i++) {
/*     */       
/* 154 */       if ((_encodings[i]).name.equalsIgnoreCase(encoding)) {
/*     */ 
/*     */         
/*     */         try { 
/* 158 */           args[0] = (_encodings[i]).javaName;
/* 159 */           Object converter = SUN_CHAR2BYTE_CONVERTER_METHOD.invoke(null, args);
/*     */           
/* 161 */           if (null != converter)
/* 162 */             return converter;  } catch (Exception exception) {}
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     return null;
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
/*     */   public static int getLastPrintable(String encoding) {
/* 185 */     String normalizedEncoding = encoding.toUpperCase();
/* 186 */     EncodingInfo ei = (EncodingInfo)_encodingTableKeyJava.get(normalizedEncoding);
/* 187 */     if (ei == null)
/* 188 */       ei = (EncodingInfo)_encodingTableKeyMime.get(normalizedEncoding); 
/* 189 */     if (ei != null)
/* 190 */       return ei.lastPrintable; 
/* 191 */     return 127;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getLastPrintable() {
/* 202 */     return 127;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getMimeEncoding(String encoding) {
/* 227 */     if (null == encoding) {
/*     */ 
/*     */ 
/*     */       
/*     */       try { 
/*     */ 
/*     */ 
/*     */         
/* 235 */         encoding = System.getProperty("file.encoding", "UTF8");
/*     */         
/* 237 */         if (null != encoding)
/*     */         
/*     */         { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 247 */           String jencoding = (encoding.equalsIgnoreCase("Cp1252") || encoding.equalsIgnoreCase("ISO8859_1") || encoding.equalsIgnoreCase("8859_1") || encoding.equalsIgnoreCase("UTF8")) ? "UTF-8" : convertJava2MimeEncoding(encoding);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 255 */           encoding = (null != jencoding) ? jencoding : "UTF-8";
/*     */            }
/*     */         
/*     */         else
/*     */         
/* 260 */         { encoding = "UTF-8"; }  } catch (SecurityException se)
/*     */       
/*     */       { 
/*     */ 
/*     */         
/* 265 */         encoding = "UTF-8"; }
/*     */ 
/*     */     
/*     */     } else {
/*     */       
/* 270 */       encoding = convertJava2MimeEncoding(encoding);
/*     */     } 
/*     */     
/* 273 */     return encoding;
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
/*     */   public static String convertJava2MimeEncoding(String encoding) {
/* 285 */     EncodingInfo enc = (EncodingInfo)_encodingTableKeyJava.get(encoding.toUpperCase());
/*     */     
/* 287 */     if (null != enc)
/* 288 */       return enc.name; 
/* 289 */     return encoding;
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
/*     */   public static String convertMime2JavaEncoding(String encoding) {
/* 302 */     for (int i = 0; i < _encodings.length; i++) {
/*     */       
/* 304 */       if ((_encodings[i]).name.equalsIgnoreCase(encoding))
/*     */       {
/* 306 */         return (_encodings[i]).javaName;
/*     */       }
/*     */     } 
/*     */     
/* 310 */     return encoding;
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
/*     */   private static EncodingInfo[] loadEncodingInfo() {
/* 322 */     URL url = null;
/*     */ 
/*     */     
/* 325 */     try { String urlString = null;
/* 326 */       InputStream is = null;
/*     */ 
/*     */ 
/*     */       
/* 330 */       try { urlString = System.getProperty("org.apache.xalan.serialize.encodings", ""); } catch (SecurityException securityException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 336 */       if (urlString != null && urlString.length() > 0) {
/* 337 */         url = new URL(urlString);
/* 338 */         is = url.openStream();
/*     */       } 
/*     */       
/* 341 */       if (is == null) {
/* 342 */         SecuritySupport ss = SecuritySupport.getInstance();
/* 343 */         is = ss.getResourceAsStream(ObjectFactory.findClassLoader(), "org/apache/xml/serializer/Encodings.properties");
/*     */       } 
/*     */ 
/*     */       
/* 347 */       Properties props = new Properties();
/* 348 */       if (is != null) {
/* 349 */         props.load(is);
/* 350 */         is.close();
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 360 */       int totalEntries = props.size();
/* 361 */       int totalMimeNames = 0;
/* 362 */       Enumeration keys = props.keys();
/* 363 */       for (int i = 0; i < totalEntries; i++) {
/*     */         
/* 365 */         String javaName = keys.nextElement();
/* 366 */         String val = props.getProperty(javaName);
/* 367 */         totalMimeNames++;
/* 368 */         int pos = val.indexOf(' ');
/* 369 */         for (int m = 0; m < pos; m++) {
/* 370 */           if (val.charAt(m) == ',')
/* 371 */             totalMimeNames++; 
/*     */         } 
/* 373 */       }  EncodingInfo[] ret = new EncodingInfo[totalMimeNames];
/* 374 */       int j = 0;
/* 375 */       keys = props.keys();
/* 376 */       for (int k = 0; k < totalEntries; k++) {
/*     */         
/* 378 */         String javaName = keys.nextElement();
/* 379 */         String val = props.getProperty(javaName);
/* 380 */         int pos = val.indexOf(' ');
/*     */ 
/*     */         
/* 383 */         if (pos < 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 388 */           String mimeName = val;
/* 389 */           int lastPrintable = 255;
/*     */         }
/*     */         else {
/*     */           
/* 393 */           int m = Integer.decode(val.substring(pos).trim()).intValue();
/*     */           
/* 395 */           StringTokenizer st = new StringTokenizer(val.substring(0, pos), ",");
/*     */           
/* 397 */           boolean first = true;
/* 398 */           for (; st.hasMoreTokens(); 
/* 399 */             first = false) {
/*     */             
/* 401 */             String str = st.nextToken();
/* 402 */             ret[j] = new EncodingInfo(str, javaName, m);
/*     */             
/* 404 */             _encodingTableKeyMime.put(str.toUpperCase(), ret[j]);
/*     */ 
/*     */             
/* 407 */             if (first) {
/* 408 */               _encodingTableKeyJava.put(javaName.toUpperCase(), ret[j]);
/*     */             }
/*     */             
/* 411 */             j++;
/*     */           } 
/*     */         } 
/*     */       } 
/* 415 */       return ret; } catch (MalformedURLException mue)
/*     */     
/*     */     { 
/*     */       
/* 419 */       throw new WrappedRuntimeException(mue); } catch (IOException ioe)
/*     */     
/*     */     { 
/*     */       
/* 423 */       throw new WrappedRuntimeException(ioe); }
/*     */   
/*     */   }
/*     */   
/* 427 */   private static final Hashtable _encodingTableKeyJava = new Hashtable();
/* 428 */   private static final Hashtable _encodingTableKeyMime = new Hashtable();
/* 429 */   private static final EncodingInfo[] _encodings = loadEncodingInfo();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/Encodings.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */