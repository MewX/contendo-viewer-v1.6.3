/*    */ package org.apache.commons.collections.buffer;
/*    */ 
/*    */ import org.apache.commons.collections.Buffer;
/*    */ import org.apache.commons.collections.functors.InstanceofPredicate;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TypedBuffer
/*    */ {
/*    */   public static Buffer decorate(Buffer buffer, Class type) {
/* 50 */     return new PredicatedBuffer(buffer, InstanceofPredicate.getInstance(type));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/buffer/TypedBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */