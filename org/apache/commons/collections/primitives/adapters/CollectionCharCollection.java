/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
/*    */ import org.apache.commons.collections.primitives.CharCollection;
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
/*    */ public final class CollectionCharCollection
/*    */   extends AbstractCollectionCharCollection
/*    */   implements Serializable
/*    */ {
/*    */   private Collection _collection;
/*    */   
/*    */   public static CharCollection wrap(Collection collection) {
/* 49 */     if (null == collection)
/* 50 */       return null; 
/* 51 */     if (collection instanceof Serializable) {
/* 52 */       return new CollectionCharCollection(collection);
/*    */     }
/* 54 */     return new NonSerializableCollectionCharCollection(collection);
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
/*    */   public CollectionCharCollection(Collection collection) {
/* 71 */     this._collection = null;
/*    */     this._collection = collection;
/*    */   }
/*    */   
/*    */   protected Collection getCollection() {
/*    */     return this._collection;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/CollectionCharCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */