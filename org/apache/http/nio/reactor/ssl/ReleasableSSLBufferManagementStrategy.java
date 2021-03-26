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
/*    */ public class ReleasableSSLBufferManagementStrategy
/*    */   implements SSLBufferManagementStrategy
/*    */ {
/*    */   public SSLBuffer constructBuffer(int size) {
/* 39 */     return new InternalBuffer(size);
/*    */   }
/*    */   
/*    */   private static final class InternalBuffer
/*    */     implements SSLBuffer
/*    */   {
/*    */     private ByteBuffer wrapped;
/*    */     private final int length;
/*    */     
/*    */     public InternalBuffer(int size) {
/* 49 */       Args.positive(size, "size");
/* 50 */       this.length = size;
/*    */     }
/*    */ 
/*    */     
/*    */     public ByteBuffer acquire() {
/* 55 */       if (this.wrapped != null) {
/* 56 */         return this.wrapped;
/*    */       }
/* 58 */       this.wrapped = ByteBuffer.allocate(this.length);
/* 59 */       return this.wrapped;
/*    */     }
/*    */ 
/*    */     
/*    */     public void release() {
/* 64 */       this.wrapped = null;
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean isAcquired() {
/* 69 */       return (this.wrapped != null);
/*    */     }
/*    */ 
/*    */     
/*    */     public boolean hasData() {
/* 74 */       return (this.wrapped != null && this.wrapped.position() > 0);
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/reactor/ssl/ReleasableSSLBufferManagementStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */