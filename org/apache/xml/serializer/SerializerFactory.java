/*     */ package org.apache.xml.serializer;
/*     */ 
/*     */ import java.util.Hashtable;
/*     */ import java.util.Properties;
/*     */ import org.apache.xml.res.XMLMessages;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.xml.sax.ContentHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SerializerFactory
/*     */ {
/*  38 */   private static Hashtable m_formats = new Hashtable();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Serializer getSerializer(Properties format) {
/*     */     Serializer serializer;
/*     */     
/*  60 */     try { String method = format.getProperty("method");
/*     */       
/*  62 */       if (method == null) {
/*  63 */         throw new IllegalArgumentException("The output format has a null method name");
/*     */       }
/*     */       
/*  66 */       String className = format.getProperty("{http://xml.apache.org/xalan}content-handler");
/*     */ 
/*     */ 
/*     */       
/*  70 */       if (null == className) {
/*     */ 
/*     */         
/*  73 */         Properties methodDefaults = OutputPropertiesFactory.getDefaultMethodProperties(method);
/*     */         
/*  75 */         className = methodDefaults.getProperty("{http://xml.apache.org/xalan}content-handler");
/*     */         
/*  77 */         if (null == className) {
/*  78 */           throw new IllegalArgumentException("The output format must have a '{http://xml.apache.org/xalan}content-handler' property!");
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  85 */       ClassLoader loader = ObjectFactory.findClassLoader();
/*     */       
/*  87 */       Class cls = ObjectFactory.findProviderClass(className, loader, true);
/*     */ 
/*     */ 
/*     */       
/*  91 */       Object obj = cls.newInstance();
/*     */       
/*  93 */       if (obj instanceof SerializationHandler)
/*     */       
/*     */       { 
/*  96 */         serializer = cls.newInstance();
/*  97 */         serializer.setOutputFormat(format);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */          }
/*     */       
/* 105 */       else if (obj instanceof ContentHandler)
/*     */       
/*     */       { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 114 */         className = "org.apache.xml.serializer.ToXMLSAXHandler";
/* 115 */         cls = ObjectFactory.findProviderClass(className, loader, true);
/* 116 */         SerializationHandler sh = (SerializationHandler)cls.newInstance();
/*     */         
/* 118 */         sh.setContentHandler((ContentHandler)obj);
/* 119 */         sh.setOutputFormat(format);
/*     */         
/* 121 */         serializer = sh;
/*     */          }
/*     */       
/*     */       else
/*     */       
/*     */       { 
/* 127 */         throw new Exception(XMLMessages.createXMLMessage("ER_SERIALIZER_NOT_CONTENTHANDLER", new Object[] { className })); }  } catch (Exception e)
/*     */     
/*     */     { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 137 */       throw new WrappedRuntimeException(e); }
/*     */ 
/*     */ 
/*     */     
/* 141 */     return serializer;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/SerializerFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */