/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class NonSerializableCollectionFloatCollection
/*    */   extends AbstractCollectionFloatCollection
/*    */ {
/*    */   private Collection _collection;
/*    */   
/*    */   public NonSerializableCollectionFloatCollection(Collection collection) {
/* 34 */     this._collection = null;
/*    */     this._collection = collection;
/*    */   }
/*    */   
/*    */   protected Collection getCollection() {
/*    */     return this._collection;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/NonSerializableCollectionFloatCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */