/*    */ package org.apache.commons.collections.collection;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import org.apache.commons.collections.Unmodifiable;
/*    */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class UnmodifiableCollection
/*    */   extends AbstractSerializableCollectionDecorator
/*    */   implements Unmodifiable
/*    */ {
/*    */   private static final long serialVersionUID = -239892006883819945L;
/*    */   
/*    */   public static Collection decorate(Collection coll) {
/* 51 */     if (coll instanceof Unmodifiable) {
/* 52 */       return coll;
/*    */     }
/* 54 */     return new UnmodifiableCollection(coll);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private UnmodifiableCollection(Collection coll) {
/* 65 */     super(coll);
/*    */   }
/*    */ 
/*    */   
/*    */   public Iterator iterator() {
/* 70 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*    */   }
/*    */   
/*    */   public boolean add(Object object) {
/* 74 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean addAll(Collection coll) {
/* 78 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public void clear() {
/* 82 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean remove(Object object) {
/* 86 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean removeAll(Collection coll) {
/* 90 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean retainAll(Collection coll) {
/* 94 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/collection/UnmodifiableCollection.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */