/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class DoubleCollectionCollection
/*    */   extends AbstractDoubleCollectionCollection
/*    */   implements Serializable
/*    */ {
/*    */   private DoubleCollection _collection;
/*    */   
/*    */   public static Collection wrap(DoubleCollection collection) {
/* 51 */     if (null == collection)
/* 52 */       return null; 
/* 53 */     if (collection instanceof Serializable) {
/* 54 */       return new DoubleCollectionCollection(collection);
/*    */     }
/* 56 */     return new NonSerializableDoubleCollectionCollection(collection);
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
/*    */   public DoubleCollectionCollection(DoubleCollection collection) {
/* 74 */     this._collection = null;
/*    */     this._collection = collection;
/*    */   }
/*    */   
/*    */   protected DoubleCollection getDoubleCollection() {
/*    */     return this._collection;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/DoubleCollectionCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */