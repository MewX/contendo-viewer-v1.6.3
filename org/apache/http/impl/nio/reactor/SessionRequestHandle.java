/*    */ package org.apache.http.impl.nio.reactor;
/*    */ 
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
/*    */ public class SessionRequestHandle
/*    */ {
/*    */   private final SessionRequestImpl sessionRequest;
/*    */   private final long requestTime;
/*    */   
/*    */   public SessionRequestHandle(SessionRequestImpl sessionRequest) {
/* 46 */     Args.notNull(sessionRequest, "Session request");
/* 47 */     this.sessionRequest = sessionRequest;
/* 48 */     this.requestTime = System.currentTimeMillis();
/*    */   }
/*    */   
/*    */   public SessionRequestImpl getSessionRequest() {
/* 52 */     return this.sessionRequest;
/*    */   }
/*    */   
/*    */   public long getRequestTime() {
/* 56 */     return this.requestTime;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/SessionRequestHandle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */