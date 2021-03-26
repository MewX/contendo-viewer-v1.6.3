/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.primitives.ShortCollection;
/*     */ import org.apache.commons.collections.primitives.ShortIterator;
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
/*     */ abstract class AbstractCollectionShortCollection
/*     */   implements ShortCollection
/*     */ {
/*     */   public boolean add(short element) {
/*  33 */     return getCollection().add(new Short(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(ShortCollection c) {
/*  37 */     return getCollection().addAll(ShortCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public void clear() {
/*  41 */     getCollection().clear();
/*     */   }
/*     */   
/*     */   public boolean contains(short element) {
/*  45 */     return getCollection().contains(new Short(element));
/*     */   }
/*     */   
/*     */   public boolean containsAll(ShortCollection c) {
/*  49 */     return getCollection().containsAll(ShortCollectionCollection.wrap(c));
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
/*     */   public ShortIterator iterator() {
/*  68 */     return IteratorShortIterator.wrap(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean removeElement(short element) {
/*  72 */     return getCollection().remove(new Short(element));
/*     */   }
/*     */   
/*     */   public boolean removeAll(ShortCollection c) {
/*  76 */     return getCollection().removeAll(ShortCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public boolean retainAll(ShortCollection c) {
/*  80 */     return getCollection().retainAll(ShortCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public int size() {
/*  84 */     return getCollection().size();
/*     */   }
/*     */   
/*     */   public short[] toArray() {
/*  88 */     Object[] src = getCollection().toArray();
/*  89 */     short[] dest = new short[src.length];
/*  90 */     for (int i = 0; i < src.length; i++) {
/*  91 */       dest[i] = ((Number)src[i]).shortValue();
/*     */     }
/*  93 */     return dest;
/*     */   }
/*     */   
/*     */   public short[] toArray(short[] dest) {
/*  97 */     Object[] src = getCollection().toArray();
/*  98 */     if (dest.length < src.length) {
/*  99 */       dest = new short[src.length];
/*     */     }
/* 101 */     for (int i = 0; i < src.length; i++) {
/* 102 */       dest[i] = ((Number)src[i]).shortValue();
/*     */     }
/* 104 */     return dest;
/*     */   }
/*     */   
/*     */   protected abstract Collection getCollection();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractCollectionShortCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */