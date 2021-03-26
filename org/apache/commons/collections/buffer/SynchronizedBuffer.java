/*    */ package org.apache.commons.collections.buffer;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.collections.Buffer;
/*    */ import org.apache.commons.collections.collection.SynchronizedCollection;
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
/*    */ public class SynchronizedBuffer
/*    */   extends SynchronizedCollection
/*    */   implements Buffer
/*    */ {
/*    */   private static final long serialVersionUID = -6859936183953626253L;
/*    */   
/*    */   public static Buffer decorate(Buffer buffer) {
/* 47 */     return new SynchronizedBuffer(buffer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected SynchronizedBuffer(Buffer buffer) {
/* 58 */     super((Collection)buffer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected SynchronizedBuffer(Buffer buffer, Object lock) {
/* 69 */     super((Collection)buffer, lock);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Buffer getBuffer() {
/* 78 */     return (Buffer)this.collection;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object get() {
/* 83 */     synchronized (this.lock) {
/* 84 */       return getBuffer().get();
/*    */     } 
/*    */   }
/*    */   
/*    */   public Object remove() {
/* 89 */     synchronized (this.lock) {
/* 90 */       return getBuffer().remove();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/buffer/SynchronizedBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */