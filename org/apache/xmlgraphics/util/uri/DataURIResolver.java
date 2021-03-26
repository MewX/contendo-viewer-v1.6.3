/*    */ package org.apache.xmlgraphics.util.uri;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.InputStream;
/*    */ import java.io.StringReader;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.net.URLDecoder;
/*    */ import javax.xml.transform.Source;
/*    */ import javax.xml.transform.TransformerException;
/*    */ import javax.xml.transform.URIResolver;
/*    */ import javax.xml.transform.stream.StreamSource;
/*    */ import org.apache.commons.logging.Log;
/*    */ import org.apache.commons.logging.LogFactory;
/*    */ import org.apache.xmlgraphics.util.io.Base64DecodeStream;
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
/*    */ public class DataURIResolver
/*    */   implements URIResolver
/*    */ {
/* 45 */   private static final Log LOG = LogFactory.getLog(URIResolver.class);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Source resolve(String href, String base) throws TransformerException {
/* 52 */     if (href.startsWith("data:")) {
/* 53 */       return parseDataURI(href);
/*    */     }
/* 55 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private Source parseDataURI(String href) {
/* 66 */     int commaPos = href.indexOf(',');
/*    */     
/* 68 */     String header = href.substring(0, commaPos);
/* 69 */     String data = href.substring(commaPos + 1);
/* 70 */     if (header.endsWith(";base64")) {
/* 71 */       byte[] bytes = new byte[0];
/*    */       try {
/* 73 */         bytes = data.getBytes("UTF-8");
/* 74 */       } catch (UnsupportedEncodingException e) {
/* 75 */         e.printStackTrace();
/*    */       } 
/* 77 */       ByteArrayInputStream encodedStream = new ByteArrayInputStream(bytes);
/* 78 */       Base64DecodeStream decodedStream = new Base64DecodeStream(encodedStream);
/*    */       
/* 80 */       return new StreamSource((InputStream)decodedStream, href);
/*    */     } 
/* 82 */     String encoding = "UTF-8";
/* 83 */     int charsetpos = header.indexOf(";charset=");
/* 84 */     if (charsetpos > 0) {
/* 85 */       encoding = header.substring(charsetpos + 9);
/*    */     }
/*    */     try {
/* 88 */       String unescapedString = URLDecoder.decode(data, encoding);
/*    */       
/* 90 */       return new StreamSource(new StringReader(unescapedString), href);
/*    */     }
/* 92 */     catch (IllegalArgumentException e) {
/* 93 */       LOG.warn(e.getMessage());
/* 94 */     } catch (UnsupportedEncodingException e) {
/* 95 */       LOG.warn(e.getMessage());
/*    */     } 
/*    */     
/* 98 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/uri/DataURIResolver.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */