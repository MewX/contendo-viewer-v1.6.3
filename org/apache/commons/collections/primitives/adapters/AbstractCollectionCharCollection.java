/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.primitives.CharCollection;
/*     */ import org.apache.commons.collections.primitives.CharIterator;
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
/*     */ abstract class AbstractCollectionCharCollection
/*     */   implements CharCollection
/*     */ {
/*     */   public boolean add(char element) {
/*  33 */     return getCollection().add(new Character(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(CharCollection c) {
/*  37 */     return getCollection().addAll(CharCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public void clear() {
/*  41 */     getCollection().clear();
/*     */   }
/*     */   
/*     */   public boolean contains(char element) {
/*  45 */     return getCollection().contains(new Character(element));
/*     */   }
/*     */   
/*     */   public boolean containsAll(CharCollection c) {
/*  49 */     return getCollection().containsAll(CharCollectionCollection.wrap(c));
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
/*     */   public CharIterator iterator() {
/*  68 */     return IteratorCharIterator.wrap(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean removeElement(char element) {
/*  72 */     return getCollection().remove(new Character(element));
/*     */   }
/*     */   
/*     */   public boolean removeAll(CharCollection c) {
/*  76 */     return getCollection().removeAll(CharCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public boolean retainAll(CharCollection c) {
/*  80 */     return getCollection().retainAll(CharCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public int size() {
/*  84 */     return getCollection().size();
/*     */   }
/*     */   
/*     */   public char[] toArray() {
/*  88 */     Object[] src = getCollection().toArray();
/*  89 */     char[] dest = new char[src.length];
/*  90 */     for (int i = 0; i < src.length; i++) {
/*  91 */       dest[i] = ((Character)src[i]).charValue();
/*     */     }
/*  93 */     return dest;
/*     */   }
/*     */   
/*     */   public char[] toArray(char[] dest) {
/*  97 */     Object[] src = getCollection().toArray();
/*  98 */     if (dest.length < src.length) {
/*  99 */       dest = new char[src.length];
/*     */     }
/* 101 */     for (int i = 0; i < src.length; i++) {
/* 102 */       dest[i] = ((Character)src[i]).charValue();
/*     */     }
/* 104 */     return dest;
/*     */   }
/*     */   
/*     */   protected abstract Collection getCollection();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractCollectionCharCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */