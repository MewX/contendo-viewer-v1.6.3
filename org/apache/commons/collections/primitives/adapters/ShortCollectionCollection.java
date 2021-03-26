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
/*    */ 
/*    */ 
/*    */ public final class ShortCollectionCollection
/*    */   extends AbstractShortCollectionCollection
/*    */   implements Serializable
/*    */ {
/*    */   private ShortCollection _collection;
/*    */   
/*    */   public static Collection wrap(ShortCollection collection) {
/* 51 */     if (null == collection)
/* 52 */       return null; 
/* 53 */     if (collection instanceof Serializable) {
/* 54 */       return new ShortCollectionCollection(collection);
/*    */     }
/* 56 */     return new NonSerializableShortCollectionCollection(collection);
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
/*    */   public ShortCollectionCollection(ShortCollection collection) {
/* 74 */     this._collection = null;
/*    */     this._collection = collection;
/*    */   }
/*    */   
/*    */   protected ShortCollection getShortCollection() {
/*    */     return this._collection;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/ShortCollectionCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */