/*    */ package org.apache.xmlgraphics.io;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import java.net.URI;
/*    */ import java.net.URL;
/*    */ import javax.xml.transform.Source;
/*    */ import javax.xml.transform.TransformerException;
/*    */ import javax.xml.transform.URIResolver;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class URIResolverAdapter
/*    */   implements ResourceResolver
/*    */ {
/*    */   private final URIResolver resolver;
/*    */   
/*    */   public URIResolverAdapter(URIResolver resolver) {
/* 42 */     this.resolver = resolver;
/*    */   }
/*    */ 
/*    */   
/*    */   public Resource getResource(URI uri) throws IOException {
/*    */     try {
/* 48 */       Source src = this.resolver.resolve(uri.toASCIIString(), null);
/* 49 */       InputStream resourceStream = XmlSourceUtil.getInputStream(src);
/*    */       
/* 51 */       if (resourceStream == null) {
/* 52 */         URL url = new URL(src.getSystemId());
/* 53 */         resourceStream = url.openStream();
/*    */       } 
/* 55 */       return new Resource(resourceStream);
/* 56 */     } catch (TransformerException e) {
/* 57 */       throw new IOException(e.getMessage());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public OutputStream getOutputStream(URI uri) throws IOException {
/*    */     try {
/* 64 */       Source src = this.resolver.resolve(uri.toASCIIString(), null);
/* 65 */       return (new URL(src.getSystemId())).openConnection().getOutputStream();
/* 66 */     } catch (TransformerException te) {
/* 67 */       throw new IOException(te.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/io/URIResolverAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */