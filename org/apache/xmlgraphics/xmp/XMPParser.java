/*    */ package org.apache.xmlgraphics.xmp;
/*    */ 
/*    */ import java.net.URL;
/*    */ import javax.xml.transform.Source;
/*    */ import javax.xml.transform.Transformer;
/*    */ import javax.xml.transform.TransformerException;
/*    */ import javax.xml.transform.TransformerFactory;
/*    */ import javax.xml.transform.sax.SAXResult;
/*    */ import javax.xml.transform.stream.StreamSource;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class XMPParser
/*    */ {
/*    */   public static Metadata parseXMP(URL url) throws TransformerException {
/* 46 */     return parseXMP(new StreamSource(url.toExternalForm()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Metadata parseXMP(Source src) throws TransformerException {
/* 56 */     TransformerFactory tFactory = TransformerFactory.newInstance();
/* 57 */     Transformer transformer = tFactory.newTransformer();
/* 58 */     XMPHandler handler = createXMPHandler();
/* 59 */     SAXResult res = new SAXResult(handler);
/* 60 */     transformer.transform(src, res);
/* 61 */     return handler.getMetadata();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static XMPHandler createXMPHandler() {
/* 69 */     return new XMPHandler();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/XMPParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */