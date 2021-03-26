/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CollectionShortCollection
/*    */   extends AbstractCollectionShortCollection
/*    */   implements Serializable
/*    */ {
/*    */   private Collection _collection;
/*    */   
/*    */   public static ShortCollection wrap(Collection collection) {
/* 49 */     if (null == collection)
/* 50 */       return null; 
/* 51 */     if (collection instanceof Serializable) {
/* 52 */       return new CollectionShortCollection(collection);
/*    */     }
/* 54 */     return new NonSerializableCollectionShortCollection(collection);
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
/*    */   
/*    */   public CollectionShortCollection(Collection collection) {
/* 71 */     this._collection = null;
/*    */     this._collection = collection;
/*    */   }
/*    */   
/*    */   protected Collection getCollection() {
/*    */     return this._collection;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/CollectionShortCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */