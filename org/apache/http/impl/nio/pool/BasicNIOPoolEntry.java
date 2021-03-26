/*    */ package org.apache.http.impl.nio.pool;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.http.HttpHost;
/*    */ import org.apache.http.annotation.Contract;
/*    */ import org.apache.http.annotation.ThreadingBehavior;
/*    */ import org.apache.http.nio.NHttpClientConnection;
/*    */ import org.apache.http.pool.PoolEntry;
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
/*    */ @Contract(threading = ThreadingBehavior.SAFE)
/*    */ public class BasicNIOPoolEntry
/*    */   extends PoolEntry<HttpHost, NHttpClientConnection>
/*    */ {
/*    */   private volatile int socketTimeout;
/*    */   
/*    */   public BasicNIOPoolEntry(String id, HttpHost route, NHttpClientConnection conn) {
/* 51 */     super(id, route, conn);
/*    */   }
/*    */ 
/*    */   
/*    */   public void close() {
/*    */     try {
/* 57 */       ((NHttpClientConnection)getConnection()).close();
/* 58 */     } catch (IOException iOException) {}
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isClosed() {
/* 64 */     return !((NHttpClientConnection)getConnection()).isOpen();
/*    */   }
/*    */   
/*    */   int getSocketTimeout() {
/* 68 */     return this.socketTimeout;
/*    */   }
/*    */   
/*    */   void setSocketTimeout(int socketTimeout) {
/* 72 */     this.socketTimeout = socketTimeout;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/impl/nio/pool/BasicNIOPoolEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */