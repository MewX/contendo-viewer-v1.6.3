/*     */ package org.apache.batik.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLResourceDescriptor
/*     */ {
/*     */   public static final String XML_PARSER_CLASS_NAME_KEY = "org.xml.sax.driver";
/*     */   public static final String CSS_PARSER_CLASS_NAME_KEY = "org.w3c.css.sac.driver";
/*     */   public static final String RESOURCES = "resources/XMLResourceDescriptor.properties";
/*  57 */   protected static Properties parserProps = null;
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String xmlParserClassName;
/*     */ 
/*     */ 
/*     */   
/*     */   protected static String cssParserClassName;
/*     */ 
/*     */ 
/*     */   
/*     */   protected static synchronized Properties getParserProps() {
/*  70 */     if (parserProps != null) return parserProps;
/*     */     
/*  72 */     parserProps = new Properties();
/*     */     try {
/*  74 */       Class<XMLResourceDescriptor> cls = XMLResourceDescriptor.class;
/*  75 */       InputStream is = cls.getResourceAsStream("resources/XMLResourceDescriptor.properties");
/*  76 */       parserProps.load(is);
/*  77 */     } catch (IOException ioe) {
/*  78 */       throw new MissingResourceException(ioe.getMessage(), "resources/XMLResourceDescriptor.properties", null);
/*     */     } 
/*     */     
/*  81 */     return parserProps;
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
/*     */   public static String getXMLParserClassName() {
/*  93 */     if (xmlParserClassName == null) {
/*  94 */       xmlParserClassName = getParserProps().getProperty("org.xml.sax.driver");
/*     */     }
/*     */     
/*  97 */     return xmlParserClassName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setXMLParserClassName(String xmlParserClassName) {
/* 106 */     XMLResourceDescriptor.xmlParserClassName = xmlParserClassName;
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
/*     */   public static String getCSSParserClassName() {
/* 120 */     if (cssParserClassName == null) {
/* 121 */       cssParserClassName = getParserProps().getProperty("org.w3c.css.sac.driver");
/*     */     }
/*     */     
/* 124 */     return cssParserClassName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setCSSParserClassName(String cssParserClassName) {
/* 133 */     XMLResourceDescriptor.cssParserClassName = cssParserClassName;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/XMLResourceDescriptor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */