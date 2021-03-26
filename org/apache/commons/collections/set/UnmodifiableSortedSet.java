/*     */ package org.apache.commons.collections.set;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.SortedSet;
/*     */ import org.apache.commons.collections.Unmodifiable;
/*     */ import org.apache.commons.collections.iterators.UnmodifiableIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class UnmodifiableSortedSet
/*     */   extends AbstractSortedSetDecorator
/*     */   implements Serializable, Unmodifiable
/*     */ {
/*     */   private static final long serialVersionUID = -725356885467962424L;
/*     */   
/*     */   public static SortedSet decorate(SortedSet set) {
/*  53 */     if (set instanceof Unmodifiable) {
/*  54 */       return set;
/*     */     }
/*  56 */     return new UnmodifiableSortedSet(set);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/*  67 */     out.defaultWriteObject();
/*  68 */     out.writeObject(this.collection);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/*  79 */     in.defaultReadObject();
/*  80 */     this.collection = (Collection)in.readObject();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private UnmodifiableSortedSet(SortedSet set) {
/*  91 */     super(set);
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/*  96 */     return UnmodifiableIterator.decorate(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean add(Object object) {
/* 100 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean addAll(Collection coll) {
/* 104 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void clear() {
/* 108 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean remove(Object object) {
/* 112 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean removeAll(Collection coll) {
/* 116 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public boolean retainAll(Collection coll) {
/* 120 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public SortedSet subSet(Object fromElement, Object toElement) {
/* 125 */     SortedSet sub = getSortedSet().subSet(fromElement, toElement);
/* 126 */     return new UnmodifiableSortedSet(sub);
/*     */   }
/*     */   
/*     */   public SortedSet headSet(Object toElement) {
/* 130 */     SortedSet sub = getSortedSet().headSet(toElement);
/* 131 */     return new UnmodifiableSortedSet(sub);
/*     */   }
/*     */   
/*     */   public SortedSet tailSet(Object fromElement) {
/* 135 */     SortedSet sub = getSortedSet().tailSet(fromElement);
/* 136 */     return new UnmodifiableSortedSet(sub);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/set/UnmodifiableSortedSet.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */