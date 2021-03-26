/*    */ package org.apache.http.nio.reactor.ssl;
/*    */ 
/*    */ import java.nio.ByteBuffer;
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
/*    */ public class PermanentSSLBufferManagementStrategy
/*    */   implements SSLBufferManagementStrategy
/*    */ {
/*    */   public SSLBuffer constructBuffer(int size) {
/* 39 */     return new InternalBuffer(size);
/*    */   }
/*    */   
/*    */   private static final class InternalBuffer
/*    */     implements SSLBuffer
/*    */   {
/*    */     private final ByteBuffer buffer;
/*    */     
/*    */     public InternalBuffer(int size) {
/* 48 */       Args.positive(size, "size");
/* 49 */       this.buffer = ByteBuffer.allocate(size);
/*    */     }
/*    */ 
/*    */     
/*    */     public ByteBuffer acquire() {
/* 54 */       return this.buffer;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void release() {}
/*    */ 
/*    */ 
/*    */     
/*    */     public boolean isAcquired() {
/* 64 */       return true;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean hasData() {
/* 69 */       return (this.buffer.position() > 0);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/reactor/ssl/PermanentSSLBufferManagementStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */