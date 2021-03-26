/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.DoubleCollection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class NonSerializableDoubleCollectionCollection
/*    */   extends AbstractDoubleCollectionCollection
/*    */ {
/*    */   private DoubleCollection _collection;
/*    */   
/*    */   public NonSerializableDoubleCollectionCollection(DoubleCollection collection) {
/* 39 */     this._collection = null;
/*    */     this._collection = collection;
/*    */   }
/*    */   
/*    */   protected DoubleCollection getDoubleCollection() {
/*    */     return this._collection;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/NonSerializableDoubleCollectionCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */