/*    */ package org.apache.http.impl.nio;
/*    */ 
/*    */ import org.apache.http.nio.reactor.IOSession;
/*    */ import org.apache.http.protocol.HttpContext;
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
/*    */ class SessionHttpContext
/*    */   implements HttpContext
/*    */ {
/*    */   private final IOSession ioSession;
/*    */   
/*    */   public SessionHttpContext(IOSession ioSession) {
/* 39 */     this.ioSession = ioSession;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object getAttribute(String id) {
/* 44 */     return this.ioSession.getAttribute(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public Object removeAttribute(String id) {
/* 49 */     return this.ioSession.removeAttribute(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAttribute(String id, Object obj) {
/* 54 */     this.ioSession.setAttribute(id, obj);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     StringBuilder sb = new StringBuilder();
/* 63 */     sb.append("[ioSession=");
/* 64 */     sb.append(this.ioSession);
/* 65 */     sb.append("]");
/* 66 */     return sb.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/SessionHttpContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */