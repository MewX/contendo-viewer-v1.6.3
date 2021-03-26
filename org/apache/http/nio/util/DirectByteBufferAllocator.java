/*    */ package org.apache.http.nio.util;
/*    */ 
/*    */ import java.nio.ByteBuffer;
/*    */ import org.apache.http.annotation.Contract;
/*    */ import org.apache.http.annotation.ThreadingBehavior;
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
/*    */ public class DirectByteBufferAllocator
/*    */   implements ByteBufferAllocator
/*    */ {
/* 44 */   public static final DirectByteBufferAllocator INSTANCE = new DirectByteBufferAllocator();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ByteBuffer allocate(int size) {
/* 52 */     return ByteBuffer.allocateDirect(size);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/util/DirectByteBufferAllocator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */