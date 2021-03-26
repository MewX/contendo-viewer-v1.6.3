/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.FloatCollection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class NonSerializableFloatCollectionCollection
/*    */   extends AbstractFloatCollectionCollection
/*    */ {
/*    */   private FloatCollection _collection;
/*    */   
/*    */   public NonSerializableFloatCollectionCollection(FloatCollection collection) {
/* 39 */     this._collection = null;
/*    */     this._collection = collection;
/*    */   }
/*    */   
/*    */   protected FloatCollection getFloatCollection() {
/*    */     return this._collection;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/NonSerializableFloatCollectionCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */