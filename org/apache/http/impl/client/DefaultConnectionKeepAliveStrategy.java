/*    */ package org.apache.http.impl.client;
/*    */ 
/*    */ import org.apache.http.HeaderElement;
/*    */ import org.apache.http.HttpResponse;
/*    */ import org.apache.http.annotation.Contract;
/*    */ import org.apache.http.annotation.ThreadingBehavior;
/*    */ import org.apache.http.conn.ConnectionKeepAliveStrategy;
/*    */ import org.apache.http.message.BasicHeaderElementIterator;
/*    */ import org.apache.http.protocol.HttpContext;
/*    */ import org.apache.http.util.Args;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Contract(threading = ThreadingBehavior.IMMUTABLE)
/*    */ public class DefaultConnectionKeepAliveStrategy
/*    */   implements ConnectionKeepAliveStrategy
/*    */ {
/* 52 */   public static final DefaultConnectionKeepAliveStrategy INSTANCE = new DefaultConnectionKeepAliveStrategy();
/*    */ 
/*    */   
/*    */   public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
/* 56 */     Args.notNull(response, "HTTP response");
/* 57 */     BasicHeaderElementIterator basicHeaderElementIterator = new BasicHeaderElementIterator(response.headerIterator("Keep-Alive"));
/*    */     
/* 59 */     while (basicHeaderElementIterator.hasNext()) {
/* 60 */       HeaderElement he = basicHeaderElementIterator.nextElement();
/* 61 */       String param = he.getName();
/* 62 */       String value = he.getValue();
/* 63 */       if (value != null && param.equalsIgnoreCase("timeout")) {
/*    */         try {
/* 65 */           return Long.parseLong(value) * 1000L;
/* 66 */         } catch (NumberFormatException numberFormatException) {}
/*    */       }
/*    */     } 
/*    */     
/* 70 */     return -1L;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/client/DefaultConnectionKeepAliveStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */