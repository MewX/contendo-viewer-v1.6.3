/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.primitives.IntCollection;
/*     */ import org.apache.commons.collections.primitives.IntIterator;
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
/*     */ abstract class AbstractCollectionIntCollection
/*     */   implements IntCollection
/*     */ {
/*     */   public boolean add(int element) {
/*  33 */     return getCollection().add(new Integer(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(IntCollection c) {
/*  37 */     return getCollection().addAll(IntCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public void clear() {
/*  41 */     getCollection().clear();
/*     */   }
/*     */   
/*     */   public boolean contains(int element) {
/*  45 */     return getCollection().contains(new Integer(element));
/*     */   }
/*     */   
/*     */   public boolean containsAll(IntCollection c) {
/*  49 */     return getCollection().containsAll(IntCollectionCollection.wrap(c));
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
/*     */   public IntIterator iterator() {
/*  68 */     return IteratorIntIterator.wrap(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean removeElement(int element) {
/*  72 */     return getCollection().remove(new Integer(element));
/*     */   }
/*     */   
/*     */   public boolean removeAll(IntCollection c) {
/*  76 */     return getCollection().removeAll(IntCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public boolean retainAll(IntCollection c) {
/*  80 */     return getCollection().retainAll(IntCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public int size() {
/*  84 */     return getCollection().size();
/*     */   }
/*     */   
/*     */   public int[] toArray() {
/*  88 */     Object[] src = getCollection().toArray();
/*  89 */     int[] dest = new int[src.length];
/*  90 */     for (int i = 0; i < src.length; i++) {
/*  91 */       dest[i] = ((Number)src[i]).intValue();
/*     */     }
/*  93 */     return dest;
/*     */   }
/*     */   
/*     */   public int[] toArray(int[] dest) {
/*  97 */     Object[] src = getCollection().toArray();
/*  98 */     if (dest.length < src.length) {
/*  99 */       dest = new int[src.length];
/*     */     }
/* 101 */     for (int i = 0; i < src.length; i++) {
/* 102 */       dest[i] = ((Number)src[i]).intValue();
/*     */     }
/* 104 */     return dest;
/*     */   }
/*     */   
/*     */   protected abstract Collection getCollection();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractCollectionIntCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */