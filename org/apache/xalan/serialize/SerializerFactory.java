/*     */ package org.apache.xalan.serialize;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.Writer;
/*     */ import java.util.Properties;
/*     */ import org.apache.xml.serializer.DOMSerializer;
/*     */ import org.apache.xml.serializer.Serializer;
/*     */ import org.w3c.dom.Node;
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
/*     */ 
/*     */ public abstract class SerializerFactory
/*     */ {
/*     */   public static Serializer getSerializer(Properties format) {
/*  57 */     Serializer ser = org.apache.xml.serializer.SerializerFactory.getSerializer(format);
/*  58 */     SerializerWrapper si = new SerializerWrapper(ser);
/*  59 */     return si;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class SerializerWrapper
/*     */     implements Serializer
/*     */   {
/*     */     private final Serializer m_serializer;
/*     */ 
/*     */     
/*     */     private DOMSerializer m_old_DOMSerializer;
/*     */ 
/*     */ 
/*     */     
/*     */     SerializerWrapper(Serializer ser) {
/*  76 */       this.m_serializer = ser;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setOutputStream(OutputStream output) {
/*  82 */       this.m_serializer.setOutputStream(output);
/*     */     }
/*     */ 
/*     */     
/*     */     public OutputStream getOutputStream() {
/*  87 */       return this.m_serializer.getOutputStream();
/*     */     }
/*     */ 
/*     */     
/*     */     public void setWriter(Writer writer) {
/*  92 */       this.m_serializer.setWriter(writer);
/*     */     }
/*     */ 
/*     */     
/*     */     public Writer getWriter() {
/*  97 */       return this.m_serializer.getWriter();
/*     */     }
/*     */ 
/*     */     
/*     */     public void setOutputFormat(Properties format) {
/* 102 */       this.m_serializer.setOutputFormat(format);
/*     */     }
/*     */ 
/*     */     
/*     */     public Properties getOutputFormat() {
/* 107 */       return this.m_serializer.getOutputFormat();
/*     */     }
/*     */ 
/*     */     
/*     */     public ContentHandler asContentHandler() throws IOException {
/* 112 */       return this.m_serializer.asContentHandler();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DOMSerializer asDOMSerializer() throws IOException {
/* 121 */       if (this.m_old_DOMSerializer == null)
/*     */       {
/* 123 */         this.m_old_DOMSerializer = new SerializerFactory.DOMSerializerWrapper(this.m_serializer.asDOMSerializer());
/*     */       }
/*     */       
/* 126 */       return this.m_old_DOMSerializer;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean reset() {
/* 133 */       return this.m_serializer.reset();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class DOMSerializerWrapper
/*     */     implements DOMSerializer
/*     */   {
/*     */     private final DOMSerializer m_dom;
/*     */ 
/*     */ 
/*     */     
/*     */     DOMSerializerWrapper(DOMSerializer domser) {
/* 148 */       this.m_dom = domser;
/*     */     }
/*     */ 
/*     */     
/*     */     public void serialize(Node node) throws IOException {
/* 153 */       this.m_dom.serialize(node);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/serialize/SerializerFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */