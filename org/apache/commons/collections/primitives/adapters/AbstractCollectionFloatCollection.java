/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.primitives.FloatCollection;
/*     */ import org.apache.commons.collections.primitives.FloatIterator;
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
/*     */ abstract class AbstractCollectionFloatCollection
/*     */   implements FloatCollection
/*     */ {
/*     */   public boolean add(float element) {
/*  33 */     return getCollection().add(new Float(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(FloatCollection c) {
/*  37 */     return getCollection().addAll(FloatCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public void clear() {
/*  41 */     getCollection().clear();
/*     */   }
/*     */   
/*     */   public boolean contains(float element) {
/*  45 */     return getCollection().contains(new Float(element));
/*     */   }
/*     */   
/*     */   public boolean containsAll(FloatCollection c) {
/*  49 */     return getCollection().containsAll(FloatCollectionCollection.wrap(c));
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
/*     */   public FloatIterator iterator() {
/*  68 */     return IteratorFloatIterator.wrap(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean removeElement(float element) {
/*  72 */     return getCollection().remove(new Float(element));
/*     */   }
/*     */   
/*     */   public boolean removeAll(FloatCollection c) {
/*  76 */     return getCollection().removeAll(FloatCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public boolean retainAll(FloatCollection c) {
/*  80 */     return getCollection().retainAll(FloatCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public int size() {
/*  84 */     return getCollection().size();
/*     */   }
/*     */   
/*     */   public float[] toArray() {
/*  88 */     Object[] src = getCollection().toArray();
/*  89 */     float[] dest = new float[src.length];
/*  90 */     for (int i = 0; i < src.length; i++) {
/*  91 */       dest[i] = ((Number)src[i]).floatValue();
/*     */     }
/*  93 */     return dest;
/*     */   }
/*     */   
/*     */   public float[] toArray(float[] dest) {
/*  97 */     Object[] src = getCollection().toArray();
/*  98 */     if (dest.length < src.length) {
/*  99 */       dest = new float[src.length];
/*     */     }
/* 101 */     for (int i = 0; i < src.length; i++) {
/* 102 */       dest[i] = ((Number)src[i]).floatValue();
/*     */     }
/* 104 */     return dest;
/*     */   }
/*     */   
/*     */   protected abstract Collection getCollection();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractCollectionFloatCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */