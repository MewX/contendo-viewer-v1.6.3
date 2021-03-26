/*    */ package org.apache.batik.util;
/*    */ 
/*    */ import java.net.MalformedURLException;
/*    */ import java.net.URL;
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
/*    */ public class ParsedURLJarProtocolHandler
/*    */   extends ParsedURLDefaultProtocolHandler
/*    */ {
/*    */   public static final String JAR = "jar";
/*    */   
/*    */   public ParsedURLJarProtocolHandler() {
/* 38 */     super("jar");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ParsedURLData parseURL(ParsedURL baseURL, String urlStr) {
/* 46 */     String start = urlStr.substring(0, "jar".length() + 1).toLowerCase();
/*    */ 
/*    */     
/* 49 */     if (start.equals("jar:")) {
/* 50 */       return parseURL(urlStr);
/*    */     }
/*    */     
/*    */     try {
/* 54 */       URL context = new URL(baseURL.toString());
/* 55 */       URL url = new URL(context, urlStr);
/* 56 */       return constructParsedURLData(url);
/* 57 */     } catch (MalformedURLException mue) {
/* 58 */       return super.parseURL(baseURL, urlStr);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/ParsedURLJarProtocolHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */