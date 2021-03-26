/*    */ package org.apache.commons.collections.buffer;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.collections.Buffer;
/*    */ import org.apache.commons.collections.Transformer;
/*    */ import org.apache.commons.collections.collection.AbstractCollectionDecorator;
/*    */ import org.apache.commons.collections.collection.TransformedCollection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TransformedBuffer
/*    */   extends TransformedCollection
/*    */   implements Buffer
/*    */ {
/*    */   private static final long serialVersionUID = -7901091318986132033L;
/*    */   
/*    */   public static Buffer decorate(Buffer buffer, Transformer transformer) {
/* 54 */     return new TransformedBuffer(buffer, transformer);
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
/*    */   protected TransformedBuffer(Buffer buffer, Transformer transformer) {
/* 69 */     super((Collection)buffer, transformer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected Buffer getBuffer() {
/* 78 */     return (Buffer)((AbstractCollectionDecorator)this).collection;
/*    */   }
/*    */ 
/*    */   
/*    */   public Object get() {
/* 83 */     return getBuffer().get();
/*    */   }
/*    */   
/*    */   public Object remove() {
/* 87 */     return getBuffer().remove();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/buffer/TransformedBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */