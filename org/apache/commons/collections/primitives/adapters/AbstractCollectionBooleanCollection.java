/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.primitives.BooleanCollection;
/*     */ import org.apache.commons.collections.primitives.BooleanIterator;
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
/*     */ abstract class AbstractCollectionBooleanCollection
/*     */   implements BooleanCollection
/*     */ {
/*     */   public boolean add(boolean element) {
/*  35 */     return getCollection().add(new Boolean(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(BooleanCollection c) {
/*  39 */     return getCollection().addAll(BooleanCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public void clear() {
/*  43 */     getCollection().clear();
/*     */   }
/*     */   
/*     */   public boolean contains(boolean element) {
/*  47 */     return getCollection().contains(new Boolean(element));
/*     */   }
/*     */   
/*     */   public boolean containsAll(BooleanCollection c) {
/*  51 */     return getCollection().containsAll(BooleanCollectionCollection.wrap(c));
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  56 */     return getCollection().toString();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  60 */     return getCollection().isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BooleanIterator iterator() {
/*  69 */     return IteratorBooleanIterator.wrap(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean removeElement(boolean element) {
/*  73 */     return getCollection().remove(new Boolean(element));
/*     */   }
/*     */   
/*     */   public boolean removeAll(BooleanCollection c) {
/*  77 */     return getCollection().removeAll(BooleanCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public boolean retainAll(BooleanCollection c) {
/*  81 */     return getCollection().retainAll(BooleanCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public int size() {
/*  85 */     return getCollection().size();
/*     */   }
/*     */   
/*     */   public boolean[] toArray() {
/*  89 */     Object[] src = getCollection().toArray();
/*  90 */     boolean[] dest = new boolean[src.length];
/*  91 */     for (int i = 0; i < src.length; i++) {
/*  92 */       dest[i] = ((Boolean)src[i]).booleanValue();
/*     */     }
/*  94 */     return dest;
/*     */   }
/*     */   
/*     */   public boolean[] toArray(boolean[] dest) {
/*  98 */     Object[] src = getCollection().toArray();
/*  99 */     if (dest.length < src.length) {
/* 100 */       dest = new boolean[src.length];
/*     */     }
/* 102 */     for (int i = 0; i < src.length; i++) {
/* 103 */       dest[i] = ((Boolean)src[i]).booleanValue();
/*     */     }
/* 105 */     return dest;
/*     */   }
/*     */   
/*     */   protected abstract Collection getCollection();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractCollectionBooleanCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */