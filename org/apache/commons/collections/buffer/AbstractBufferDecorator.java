/*    */ package org.apache.commons.collections.buffer;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.collections.Buffer;
/*    */ import org.apache.commons.collections.collection.AbstractCollectionDecorator;
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
/*    */ public abstract class AbstractBufferDecorator
/*    */   extends AbstractCollectionDecorator
/*    */   implements Buffer
/*    */ {
/*    */   protected AbstractBufferDecorator() {}
/*    */   
/*    */   protected AbstractBufferDecorator(Buffer buffer) {
/* 48 */     super((Collection)buffer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Buffer getBuffer() {
/* 57 */     return (Buffer)getCollection();
/*    */   }
/*    */ 
/*    */   
/*    */   public Object get() {
/* 62 */     return getBuffer().get();
/*    */   }
/*    */   
/*    */   public Object remove() {
/* 66 */     return getBuffer().remove();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/buffer/AbstractBufferDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */