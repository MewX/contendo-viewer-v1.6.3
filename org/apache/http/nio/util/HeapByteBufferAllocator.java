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
/*    */ public class HeapByteBufferAllocator
/*    */   implements ByteBufferAllocator
/*    */ {
/* 44 */   public static final HeapByteBufferAllocator INSTANCE = new HeapByteBufferAllocator();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ByteBuffer allocate(int size) {
/* 52 */     return ByteBuffer.allocate(size);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/http/nio/util/HeapByteBufferAllocator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */