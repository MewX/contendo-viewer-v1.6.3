/*    */ package org.apache.http.impl.nio.reactor;
/*    */ 
/*    */ import org.apache.http.nio.reactor.IOSession;
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
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class SessionHandle
/*    */ {
/*    */   private final IOSession session;
/*    */   private final long startedTime;
/*    */   private long lastReadTime;
/*    */   private long lastWriteTime;
/*    */   private long lastAccessTime;
/*    */   
/*    */   public SessionHandle(IOSession session) {
/* 54 */     Args.notNull(session, "Session");
/* 55 */     this.session = session;
/* 56 */     long now = System.currentTimeMillis();
/* 57 */     this.startedTime = now;
/* 58 */     this.lastReadTime = now;
/* 59 */     this.lastWriteTime = now;
/* 60 */     this.lastAccessTime = now;
/*    */   }
/*    */   
/*    */   public IOSession getSession() {
/* 64 */     return this.session;
/*    */   }
/*    */   
/*    */   public long getStartedTime() {
/* 68 */     return this.startedTime;
/*    */   }
/*    */   
/*    */   public long getLastReadTime() {
/* 72 */     return this.lastReadTime;
/*    */   }
/*    */   
/*    */   public long getLastWriteTime() {
/* 76 */     return this.lastWriteTime;
/*    */   }
/*    */   
/*    */   public long getLastAccessTime() {
/* 80 */     return this.lastAccessTime;
/*    */   }
/*    */   
/*    */   public void resetLastRead() {
/* 84 */     long now = System.currentTimeMillis();
/* 85 */     this.lastReadTime = now;
/* 86 */     this.lastAccessTime = now;
/*    */   }
/*    */   
/*    */   public void resetLastWrite() {
/* 90 */     long now = System.currentTimeMillis();
/* 91 */     this.lastWriteTime = now;
/* 92 */     this.lastAccessTime = now;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/reactor/SessionHandle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */