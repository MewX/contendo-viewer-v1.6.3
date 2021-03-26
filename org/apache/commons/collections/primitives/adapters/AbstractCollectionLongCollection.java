/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.primitives.LongCollection;
/*     */ import org.apache.commons.collections.primitives.LongIterator;
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
/*     */ abstract class AbstractCollectionLongCollection
/*     */   implements LongCollection
/*     */ {
/*     */   public boolean add(long element) {
/*  33 */     return getCollection().add(new Long(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(LongCollection c) {
/*  37 */     return getCollection().addAll(LongCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public void clear() {
/*  41 */     getCollection().clear();
/*     */   }
/*     */   
/*     */   public boolean contains(long element) {
/*  45 */     return getCollection().contains(new Long(element));
/*     */   }
/*     */   
/*     */   public boolean containsAll(LongCollection c) {
/*  49 */     return getCollection().containsAll(LongCollectionCollection.wrap(c));
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
/*     */   public LongIterator iterator() {
/*  68 */     return IteratorLongIterator.wrap(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean removeElement(long element) {
/*  72 */     return getCollection().remove(new Long(element));
/*     */   }
/*     */   
/*     */   public boolean removeAll(LongCollection c) {
/*  76 */     return getCollection().removeAll(LongCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public boolean retainAll(LongCollection c) {
/*  80 */     return getCollection().retainAll(LongCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public int size() {
/*  84 */     return getCollection().size();
/*     */   }
/*     */   
/*     */   public long[] toArray() {
/*  88 */     Object[] src = getCollection().toArray();
/*  89 */     long[] dest = new long[src.length];
/*  90 */     for (int i = 0; i < src.length; i++) {
/*  91 */       dest[i] = ((Number)src[i]).longValue();
/*     */     }
/*  93 */     return dest;
/*     */   }
/*     */   
/*     */   public long[] toArray(long[] dest) {
/*  97 */     Object[] src = getCollection().toArray();
/*  98 */     if (dest.length < src.length) {
/*  99 */       dest = new long[src.length];
/*     */     }
/* 101 */     for (int i = 0; i < src.length; i++) {
/* 102 */       dest[i] = ((Number)src[i]).longValue();
/*     */     }
/* 104 */     return dest;
/*     */   }
/*     */   
/*     */   protected abstract Collection getCollection();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractCollectionLongCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */