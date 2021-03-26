/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.util.Properties;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.templates.OutputProperties;
/*     */ import org.apache.xml.serializer.Serializer;
/*     */ import org.apache.xml.serializer.SerializerFactory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SerializerSwitcher
/*     */ {
/*     */   public static void switchSerializerIfHTML(TransformerImpl transformer, String ns, String localName) throws TransformerException {
/*  57 */     if (null == transformer) {
/*     */       return;
/*     */     }
/*  60 */     if ((null == ns || ns.length() == 0) && localName.equalsIgnoreCase("html")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  66 */       if (null != transformer.getOutputPropertyNoDefault("method")) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  71 */       Properties prevProperties = transformer.getOutputFormat().getProperties();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  76 */       OutputProperties htmlOutputProperties = new OutputProperties("html");
/*     */       
/*  78 */       htmlOutputProperties.copyFrom(prevProperties, true);
/*  79 */       Properties htmlProperties = htmlOutputProperties.getProperties();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  84 */       try { Serializer oldSerializer = null;
/*     */         
/*  86 */         if (null != oldSerializer)
/*     */         
/*  88 */         { Serializer serializer = SerializerFactory.getSerializer(htmlProperties);
/*     */ 
/*     */           
/*  91 */           Writer writer = oldSerializer.getWriter();
/*     */           
/*  93 */           if (null != writer) {
/*  94 */             serializer.setWriter(writer);
/*     */           } else {
/*     */             
/*  97 */             OutputStream os = oldSerializer.getOutputStream();
/*     */             
/*  99 */             if (null != os) {
/* 100 */               serializer.setOutputStream(os);
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 105 */           ContentHandler ch = serializer.asContentHandler();
/*     */           
/* 107 */           transformer.setContentHandler(ch); }  } catch (IOException e)
/*     */       
/*     */       { 
/*     */ 
/*     */         
/* 112 */         throw new TransformerException(e); }
/*     */     
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getOutputPropertyNoDefault(String qnameString, Properties props) throws IllegalArgumentException {
/* 132 */     String value = (String)props.get(qnameString);
/*     */     
/* 134 */     return value;
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
/*     */   public static Serializer switchSerializerIfHTML(String ns, String localName, Properties props, Serializer oldSerializer) throws TransformerException {
/* 151 */     Serializer newSerializer = oldSerializer;
/*     */     
/* 153 */     if ((null == ns || ns.length() == 0) && localName.equalsIgnoreCase("html")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 159 */       if (null != getOutputPropertyNoDefault("method", props)) {
/* 160 */         return newSerializer;
/*     */       }
/*     */ 
/*     */       
/* 164 */       Properties prevProperties = props;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 169 */       OutputProperties htmlOutputProperties = new OutputProperties("html");
/*     */       
/* 171 */       htmlOutputProperties.copyFrom(prevProperties, true);
/* 172 */       Properties htmlProperties = htmlOutputProperties.getProperties();
/*     */ 
/*     */ 
/*     */       
/* 176 */       if (null != oldSerializer) {
/*     */         
/* 178 */         Serializer serializer = SerializerFactory.getSerializer(htmlProperties);
/*     */ 
/*     */         
/* 181 */         Writer writer = oldSerializer.getWriter();
/*     */         
/* 183 */         if (null != writer) {
/* 184 */           serializer.setWriter(writer);
/*     */         } else {
/*     */           
/* 187 */           OutputStream os = serializer.getOutputStream();
/*     */           
/* 189 */           if (null != os)
/* 190 */             serializer.setOutputStream(os); 
/*     */         } 
/* 192 */         newSerializer = serializer;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 200 */     return newSerializer;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/SerializerSwitcher.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */