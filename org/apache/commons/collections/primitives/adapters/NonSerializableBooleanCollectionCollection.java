/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import org.apache.commons.collections.primitives.BooleanCollection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class NonSerializableBooleanCollectionCollection
/*    */   extends AbstractBooleanCollectionCollection
/*    */ {
/*    */   private BooleanCollection _collection;
/*    */   
/*    */   public NonSerializableBooleanCollectionCollection(BooleanCollection collection) {
/* 42 */     this._collection = null;
/*    */     this._collection = collection;
/*    */   }
/*    */   
/*    */   protected BooleanCollection getBooleanCollection() {
/*    */     return this._collection;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/NonSerializableBooleanCollectionCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */