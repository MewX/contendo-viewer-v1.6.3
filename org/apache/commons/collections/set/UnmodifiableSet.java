/*    */ package org.apache.commons.collections.set;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import java.util.Iterator;
/*    */ import java.util.Set;
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
/*    */ public final class UnmodifiableSet
/*    */   extends AbstractSerializableSetDecorator
/*    */   implements Unmodifiable
/*    */ {
/*    */   private static final long serialVersionUID = 6499119872185240161L;
/*    */   
/*    */   public static Set decorate(Set set) {
/* 49 */     if (set instanceof Unmodifiable) {
/* 50 */       return set;
/*    */     }
/* 52 */     return new UnmodifiableSet(set);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private UnmodifiableSet(Set set) {
/* 63 */     super(set);
/*    */   }
/*    */ 
/*    */   
/*    */   public Iterator iterator() {
/* 68 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*    */   }
/*    */   
/*    */   public boolean add(Object object) {
/* 72 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean addAll(Collection coll) {
/* 76 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public void clear() {
/* 80 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean remove(Object object) {
/* 84 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean removeAll(Collection coll) {
/* 88 */     throw new UnsupportedOperationException();
/*    */   }
/*    */   
/*    */   public boolean retainAll(Collection coll) {
/* 92 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/set/UnmodifiableSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */