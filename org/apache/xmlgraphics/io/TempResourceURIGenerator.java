/*    */ package org.apache.xmlgraphics.io;
/*    */ 
/*    */ import java.net.URI;
/*    */ import java.util.concurrent.atomic.AtomicLong;
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
/*    */ public final class TempResourceURIGenerator
/*    */ {
/*    */   public static final String TMP_SCHEME = "tmp";
/*    */   private final String tempURIPrefix;
/*    */   private final AtomicLong counter;
/*    */   
/*    */   public TempResourceURIGenerator(String uriPrefix) {
/* 38 */     this.counter = new AtomicLong();
/* 39 */     this.tempURIPrefix = URI.create("tmp:///" + uriPrefix).toASCIIString();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public URI generate() {
/* 47 */     return URI.create(this.tempURIPrefix + getUniqueId());
/*    */   }
/*    */   
/*    */   private String getUniqueId() {
/* 51 */     return Long.toHexString(this.counter.getAndIncrement());
/*    */   }
/*    */   
/*    */   public static boolean isTempURI(URI uri) {
/* 55 */     return "tmp".equals(uri.getScheme());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/io/TempResourceURIGenerator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */