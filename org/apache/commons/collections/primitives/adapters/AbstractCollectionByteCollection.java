/*     */ package org.apache.commons.collections.primitives.adapters;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.collections.primitives.ByteCollection;
/*     */ import org.apache.commons.collections.primitives.ByteIterator;
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
/*     */ abstract class AbstractCollectionByteCollection
/*     */   implements ByteCollection
/*     */ {
/*     */   public boolean add(byte element) {
/*  33 */     return getCollection().add(new Byte(element));
/*     */   }
/*     */   
/*     */   public boolean addAll(ByteCollection c) {
/*  37 */     return getCollection().addAll(ByteCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public void clear() {
/*  41 */     getCollection().clear();
/*     */   }
/*     */   
/*     */   public boolean contains(byte element) {
/*  45 */     return getCollection().contains(new Byte(element));
/*     */   }
/*     */   
/*     */   public boolean containsAll(ByteCollection c) {
/*  49 */     return getCollection().containsAll(ByteCollectionCollection.wrap(c));
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
/*     */   public ByteIterator iterator() {
/*  68 */     return IteratorByteIterator.wrap(getCollection().iterator());
/*     */   }
/*     */   
/*     */   public boolean removeElement(byte element) {
/*  72 */     return getCollection().remove(new Byte(element));
/*     */   }
/*     */   
/*     */   public boolean removeAll(ByteCollection c) {
/*  76 */     return getCollection().removeAll(ByteCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public boolean retainAll(ByteCollection c) {
/*  80 */     return getCollection().retainAll(ByteCollectionCollection.wrap(c));
/*     */   }
/*     */   
/*     */   public int size() {
/*  84 */     return getCollection().size();
/*     */   }
/*     */   
/*     */   public byte[] toArray() {
/*  88 */     Object[] src = getCollection().toArray();
/*  89 */     byte[] dest = new byte[src.length];
/*  90 */     for (int i = 0; i < src.length; i++) {
/*  91 */       dest[i] = ((Number)src[i]).byteValue();
/*     */     }
/*  93 */     return dest;
/*     */   }
/*     */   
/*     */   public byte[] toArray(byte[] dest) {
/*  97 */     Object[] src = getCollection().toArray();
/*  98 */     if (dest.length < src.length) {
/*  99 */       dest = new byte[src.length];
/*     */     }
/* 101 */     for (int i = 0; i < src.length; i++) {
/* 102 */       dest[i] = ((Number)src[i]).byteValue();
/*     */     }
/* 104 */     return dest;
/*     */   }
/*     */   
/*     */   protected abstract Collection getCollection();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/adapters/AbstractCollectionByteCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */