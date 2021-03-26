/*     */ package org.apache.xml.serializer;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Properties;
/*     */ import org.apache.xml.res.XMLMessages;
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
/*     */ public class OutputPropertiesFactory
/*     */ {
/*     */   public static final String S_BUILTIN_EXTENSIONS_UNIVERSAL = "{http://xml.apache.org/xalan}";
/*     */   public static final String S_KEY_INDENT_AMOUNT = "{http://xml.apache.org/xalan}indent-amount";
/*     */   public static final String S_KEY_CONTENT_HANDLER = "{http://xml.apache.org/xalan}content-handler";
/*     */   public static final String S_KEY_ENTITIES = "{http://xml.apache.org/xalan}entities";
/*     */   public static final String S_USE_URL_ESCAPING = "{http://xml.apache.org/xalan}use-url-escaping";
/*     */   public static final String S_OMIT_META_TAG = "{http://xml.apache.org/xalan}omit-meta-tag";
/*     */   public static final String S_BUILTIN_OLD_EXTENSIONS_UNIVERSAL = "{http://xml.apache.org/xslt}";
/*  89 */   public static final int S_BUILTIN_OLD_EXTENSIONS_UNIVERSAL_LEN = "{http://xml.apache.org/xslt}".length();
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String S_XSLT_PREFIX = "xslt.output.";
/*     */ 
/*     */ 
/*     */   
/*  97 */   private static final int S_XSLT_PREFIX_LEN = "xslt.output.".length();
/*     */   private static final String S_XALAN_PREFIX = "org.apache.xslt.";
/*  99 */   private static final int S_XALAN_PREFIX_LEN = "org.apache.xslt.".length();
/*     */ 
/*     */   
/* 102 */   private static Integer m_synch_object = new Integer(1);
/*     */ 
/*     */   
/*     */   private static final String PROP_DIR = "org/apache/xml/serializer/";
/*     */ 
/*     */   
/*     */   private static final String PROP_FILE_XML = "output_xml.properties";
/*     */ 
/*     */   
/*     */   private static final String PROP_FILE_TEXT = "output_text.properties";
/*     */ 
/*     */   
/*     */   private static final String PROP_FILE_HTML = "output_html.properties";
/*     */ 
/*     */   
/*     */   private static final String PROP_FILE_UNKNOWN = "output_unknown.properties";
/*     */ 
/*     */   
/* 120 */   private static Properties m_xml_properties = null;
/*     */ 
/*     */   
/* 123 */   private static Properties m_html_properties = null;
/*     */ 
/*     */   
/* 126 */   private static Properties m_text_properties = null;
/*     */ 
/*     */   
/* 129 */   private static Properties m_unknown_properties = null;
/*     */ 
/*     */   
/* 132 */   private static final Class ACCESS_CONTROLLER_CLASS = findAccessControllerClass();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Class findAccessControllerClass() {
/*     */     
/* 144 */     try { return Class.forName("java.security.AccessController"); } catch (Exception exception)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 152 */       return null; }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Properties getDefaultMethodProperties(String method) {
/* 169 */     String fileName = null;
/* 170 */     Properties defaultProperties = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 175 */     try { synchronized (m_synch_object) {
/*     */         
/* 177 */         if (null == m_xml_properties) {
/*     */           
/* 179 */           fileName = "output_xml.properties";
/* 180 */           m_xml_properties = loadPropertiesFile(fileName, null);
/*     */         } 
/*     */       } 
/*     */       
/* 184 */       if (method.equals("xml"))
/*     */       
/* 186 */       { defaultProperties = m_xml_properties; }
/*     */       
/* 188 */       else if (method.equals("html"))
/*     */       
/* 190 */       { if (null == m_html_properties) {
/*     */           
/* 192 */           fileName = "output_html.properties";
/* 193 */           m_html_properties = loadPropertiesFile(fileName, m_xml_properties);
/*     */         } 
/*     */ 
/*     */         
/* 197 */         defaultProperties = m_html_properties; }
/*     */       
/* 199 */       else if (method.equals("text"))
/*     */       
/* 201 */       { if (null == m_text_properties) {
/*     */           
/* 203 */           fileName = "output_text.properties";
/* 204 */           m_text_properties = loadPropertiesFile(fileName, m_xml_properties);
/*     */           
/* 206 */           if (null == m_text_properties.getProperty("encoding")) {
/*     */ 
/*     */             
/* 209 */             String mimeEncoding = Encodings.getMimeEncoding(null);
/* 210 */             m_text_properties.put("encoding", mimeEncoding);
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 216 */         defaultProperties = m_text_properties; }
/*     */       
/* 218 */       else if (method.equals(""))
/*     */       
/* 220 */       { if (null == m_unknown_properties) {
/*     */           
/* 222 */           fileName = "output_unknown.properties";
/* 223 */           m_unknown_properties = loadPropertiesFile(fileName, m_xml_properties);
/*     */         } 
/*     */ 
/*     */         
/* 227 */         defaultProperties = m_unknown_properties;
/*     */          }
/*     */       
/*     */       else
/*     */       
/* 232 */       { defaultProperties = m_xml_properties; }  } catch (IOException ioe)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 237 */       throw new WrappedRuntimeException(XMLMessages.createXMLMessage("ER_COULD_NOT_LOAD_METHOD_PROPERTY", new Object[] { fileName, method }), ioe); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 244 */     return new Properties(defaultProperties);
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
/*     */   private static Properties loadPropertiesFile(String resourceName, Properties defaults) throws IOException {
/* 269 */     Properties props = new Properties(defaults);
/*     */     
/* 271 */     InputStream is = null;
/* 272 */     BufferedInputStream bis = null;
/*     */ 
/*     */ 
/*     */     
/* 276 */     try { if (ACCESS_CONTROLLER_CLASS != null) {
/*     */         
/* 278 */         is = AccessController.<InputStream>doPrivileged(new PrivilegedAction(resourceName) {
/*     */               private final String val$resourceName;
/*     */               
/*     */               public Object run() {
/* 282 */                 return OutputPropertiesFactory.class.getResourceAsStream(this.val$resourceName);
/*     */               }
/*     */             });
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 290 */         is = OutputPropertiesFactory.class.getResourceAsStream(resourceName);
/*     */       } 
/*     */ 
/*     */       
/* 294 */       bis = new BufferedInputStream(is);
/* 295 */       props.load(bis); } catch (IOException ioe)
/*     */     
/*     */     { 
/*     */       
/* 299 */       if (defaults == null)
/*     */       {
/* 301 */         throw ioe;
/*     */       }
/*     */ 
/*     */       
/* 305 */       throw new WrappedRuntimeException(XMLMessages.createXMLMessage("ER_COULD_NOT_LOAD_RESOURCE", new Object[] { resourceName }), ioe); } catch (SecurityException se)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 316 */       if (defaults == null)
/*     */       {
/* 318 */         throw se;
/*     */       }
/*     */ 
/*     */       
/* 322 */       throw new WrappedRuntimeException(XMLMessages.createXMLMessage("ER_COULD_NOT_LOAD_RESOURCE", new Object[] { resourceName }), se);
/*     */ 
/*     */       
/*     */        }
/*     */     
/*     */     finally
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 332 */       if (bis != null)
/*     */       {
/* 334 */         bis.close();
/*     */       }
/* 336 */       if (is != null)
/*     */       {
/* 338 */         is.close();
/*     */       } }
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
/* 356 */     Enumeration keys = ((Properties)props.clone()).keys();
/* 357 */     while (keys.hasMoreElements()) {
/*     */       
/* 359 */       String key = keys.nextElement();
/*     */ 
/*     */ 
/*     */       
/* 363 */       String value = null;
/*     */ 
/*     */       
/* 366 */       try { value = System.getProperty(key); } catch (SecurityException securityException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 372 */       if (value == null) {
/* 373 */         value = (String)props.get(key);
/*     */       }
/* 375 */       String newKey = fixupPropertyString(key, true);
/* 376 */       String newValue = null;
/*     */ 
/*     */       
/* 379 */       try { newValue = System.getProperty(newKey); } catch (SecurityException securityException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 385 */       if (newValue == null) {
/* 386 */         newValue = fixupPropertyString(value, false);
/*     */       } else {
/* 388 */         newValue = fixupPropertyString(newValue, false);
/*     */       } 
/* 390 */       if (key != newKey || value != newValue) {
/*     */         
/* 392 */         props.remove(key);
/* 393 */         props.put(newKey, newValue);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 398 */     return props;
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
/*     */   private static String fixupPropertyString(String s, boolean doClipping) {
/* 411 */     if (doClipping && s.startsWith("xslt.output."))
/*     */     {
/* 413 */       s = s.substring(S_XSLT_PREFIX_LEN);
/*     */     }
/* 415 */     if (s.startsWith("org.apache.xslt."))
/*     */     {
/* 417 */       s = "{http://xml.apache.org/xalan}" + s.substring(S_XALAN_PREFIX_LEN);
/*     */     }
/*     */     
/*     */     int index;
/* 421 */     if ((index = s.indexOf("\\u003a")) > 0) {
/*     */       
/* 423 */       String temp = s.substring(index + 6);
/* 424 */       s = s.substring(0, index) + ":" + temp;
/*     */     } 
/*     */     
/* 427 */     return s;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/OutputPropertiesFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */