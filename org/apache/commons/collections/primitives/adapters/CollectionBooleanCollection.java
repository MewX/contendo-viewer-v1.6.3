/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CollectionBooleanCollection
/*    */   extends AbstractCollectionBooleanCollection
/*    */   implements Serializable
/*    */ {
/*    */   private Collection _collection;
/*    */   
/*    */   public static BooleanCollection wrap(Collection collection) {
/* 52 */     if (null == collection)
/* 53 */       return null; 
/* 54 */     if (collection instanceof Serializable) {
/* 55 */       return new CollectionBooleanCollection(collection);
/*    */     }
/* 57 */     return new NonSerializableCollectionBooleanCollection(collection);
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
/*    */ 
/*    */   
/*    */   public CollectionBooleanCollection(Collection collection) {
/* 76 */     this._collection = null;
/*    */     this._collection = collection;
/*    */   }
/*    */   
/*    */   protected Collection getCollection() {
/*    */     return this._collection;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/CollectionBooleanCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */