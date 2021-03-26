/*    */ package org.apache.commons.collections.buffer;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.collections.Buffer;
/*    */ import org.apache.commons.collections.Predicate;
/*    */ import org.apache.commons.collections.collection.PredicatedCollection;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PredicatedBuffer
/*    */   extends PredicatedCollection
/*    */   implements Buffer
/*    */ {
/*    */   private static final long serialVersionUID = 2307609000539943581L;
/*    */   
/*    */   public static Buffer decorate(Buffer buffer, Predicate predicate) {
/* 59 */     return new PredicatedBuffer(buffer, predicate);
/*    */   }
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
/*    */   protected PredicatedBuffer(Buffer buffer, Predicate predicate) {
/* 75 */     super((Collection)buffer, predicate);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Buffer getBuffer() {
/* 84 */     return (Buffer)getCollection();
/*    */   }
/*    */ 
/*    */   
/*    */   public Object get() {
/* 89 */     return getBuffer().get();
/*    */   }
/*    */   
/*    */   public Object remove() {
/* 93 */     return getBuffer().remove();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/buffer/PredicatedBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */