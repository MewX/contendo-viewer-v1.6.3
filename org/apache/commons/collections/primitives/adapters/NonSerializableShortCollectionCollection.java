/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.ShortCollection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class NonSerializableShortCollectionCollection
/*    */   extends AbstractShortCollectionCollection
/*    */ {
/*    */   private ShortCollection _collection;
/*    */   
/*    */   public NonSerializableShortCollectionCollection(ShortCollection collection) {
/* 39 */     this._collection = null;
/*    */     this._collection = collection;
/*    */   }
/*    */   
/*    */   protected ShortCollection getShortCollection() {
/*    */     return this._collection;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/NonSerializableShortCollectionCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */