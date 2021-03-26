/*     */ package net.a.a;
/*     */ 
/*     */ import java.io.StringWriter;
/*     */ import javax.annotation.concurrent.ThreadSafe;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @ThreadSafe
/*     */ public final class e
/*     */ {
/*  51 */   private static final Log a = LogFactory.getLog(e.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String a(Node paramNode, boolean paramBoolean1, boolean paramBoolean2) {
/*  73 */     return a(paramNode, paramBoolean1, paramBoolean2, false);
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
/*     */   public static String a(Node paramNode, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
/*  95 */     StringWriter stringWriter = new StringWriter();
/*     */     
/*     */     try {
/*  98 */       Transformer transformer = TransformerFactory.newInstance().newTransformer();
/*  99 */       DOMSource dOMSource = new DOMSource(paramNode);
/* 100 */       StreamResult streamResult = new StreamResult(stringWriter);
/*     */       
/* 102 */       if (paramBoolean1) {
/* 103 */         transformer.setOutputProperty("doctype-public", "-//W3C//DTD MathML 2.0//EN");
/*     */         
/* 105 */         transformer.setOutputProperty("doctype-system", "http://www.w3.org/TR/MathML2/dtd/mathml2.dtd");
/*     */         
/* 107 */         transformer.setOutputProperty("media-type", "application/mathml+xml");
/*     */       } 
/*     */       
/* 110 */       a(paramBoolean2, "indent", transformer);
/*     */       
/* 112 */       a(paramBoolean3, "omit-xml-declaration", transformer);
/*     */       
/* 114 */       transformer.transform(dOMSource, streamResult);
/* 115 */     } catch (TransformerException transformerException) {
/* 116 */       a.warn(transformerException.getMessage(), transformerException);
/*     */     } 
/* 118 */     return stringWriter.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void a(boolean paramBoolean, String paramString, Transformer paramTransformer) {
/* 124 */     if (paramBoolean) {
/* 125 */       paramTransformer.setOutputProperty(paramString, "yes");
/*     */     } else {
/* 127 */       paramTransformer.setOutputProperty(paramString, "no");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */