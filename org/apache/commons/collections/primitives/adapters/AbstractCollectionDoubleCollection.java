/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.primitives.DoubleCollection;
/*     */ import org.apache.commons.collections.primitives.DoubleIterator;
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
/*     */ abstract class AbstractCollectionDoubleCollection
/*     */   implements DoubleCollection
/*     */ {
/*     */   public boolean add(double element) {
/*  33 */     return getCollection().add(new Double(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(DoubleCollection c) {
/*  37 */     return getCollection().addAll(DoubleCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public void clear() {
/*  41 */     getCollection().clear();
/*     */   }
/*     */   
/*     */   public boolean contains(double element) {
/*  45 */     return getCollection().contains(new Double(element));
/*     */   }
/*     */   
/*     */   public boolean containsAll(DoubleCollection c) {
/*  49 */     return getCollection().containsAll(DoubleCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public String toString() {
/*  53 */     return getCollection().toString();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  57 */     return getCollection().isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DoubleIterator iterator() {
/*  68 */     return IteratorDoubleIterator.wrap(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean removeElement(double element) {
/*  72 */     return getCollection().remove(new Double(element));
/*     */   }
/*     */   
/*     */   public boolean removeAll(DoubleCollection c) {
/*  76 */     return getCollection().removeAll(DoubleCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public boolean retainAll(DoubleCollection c) {
/*  80 */     return getCollection().retainAll(DoubleCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public int size() {
/*  84 */     return getCollection().size();
/*     */   }
/*     */   
/*     */   public double[] toArray() {
/*  88 */     Object[] src = getCollection().toArray();
/*  89 */     double[] dest = new double[src.length];
/*  90 */     for (int i = 0; i < src.length; i++) {
/*  91 */       dest[i] = ((Number)src[i]).doubleValue();
/*     */     }
/*  93 */     return dest;
/*     */   }
/*     */   
/*     */   public double[] toArray(double[] dest) {
/*  97 */     Object[] src = getCollection().toArray();
/*  98 */     if (dest.length < src.length) {
/*  99 */       dest = new double[src.length];
/*     */     }
/* 101 */     for (int i = 0; i < src.length; i++) {
/* 102 */       dest[i] = ((Number)src[i]).doubleValue();
/*     */     }
/* 104 */     return dest;
/*     */   }
/*     */   
/*     */   protected abstract Collection getCollection();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractCollectionDoubleCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */