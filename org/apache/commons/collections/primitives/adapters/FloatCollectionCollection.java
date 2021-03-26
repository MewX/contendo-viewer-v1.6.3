/*    */ package org.apache.commons.collections.primitives.adapters;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.util.Collection;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class FloatCollectionCollection
/*    */   extends AbstractFloatCollectionCollection
/*    */   implements Serializable
/*    */ {
/*    */   private FloatCollection _collection;
/*    */   
/*    */   public static Collection wrap(FloatCollection collection) {
/* 51 */     if (null == collection)
/* 52 */       return null; 
/* 53 */     if (collection instanceof Serializable) {
/* 54 */       return new FloatCollectionCollection(collection);
/*    */     }
/* 56 */     return new NonSerializableFloatCollectionCollection(collection);
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
/*    */   public FloatCollectionCollection(FloatCollection collection) {
/* 74 */     this._collection = null;
/*    */     this._collection = collection;
/*    */   }
/*    */   
/*    */   protected FloatCollection getFloatCollection() {
/*    */     return this._collection;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/FloatCollectionCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */