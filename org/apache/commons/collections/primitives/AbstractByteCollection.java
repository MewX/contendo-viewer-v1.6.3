/*     */ package org.apache.commons.collections.primitives;
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
/*     */ public abstract class AbstractByteCollection
/*     */   implements ByteCollection
/*     */ {
/*     */   public abstract ByteIterator iterator();
/*     */   
/*     */   public abstract int size();
/*     */   
/*     */   public boolean add(byte element) {
/*  42 */     throw new UnsupportedOperationException("add(byte) is not supported.");
/*     */   }
/*     */   
/*     */   public boolean addAll(ByteCollection c) {
/*  46 */     boolean modified = false;
/*  47 */     for (ByteIterator iter = c.iterator(); iter.hasNext();) {
/*  48 */       modified |= add(iter.next());
/*     */     }
/*  50 */     return modified;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  54 */     for (ByteIterator iter = iterator(); iter.hasNext(); ) {
/*  55 */       iter.next();
/*  56 */       iter.remove();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(byte element) {
/*  61 */     for (ByteIterator iter = iterator(); iter.hasNext();) {
/*  62 */       if (iter.next() == element) {
/*  63 */         return true;
/*     */       }
/*     */     } 
/*  66 */     return false;
/*     */   }
/*     */   
/*     */   public boolean containsAll(ByteCollection c) {
/*  70 */     for (ByteIterator iter = c.iterator(); iter.hasNext();) {
/*  71 */       if (!contains(iter.next())) {
/*  72 */         return false;
/*     */       }
/*     */     } 
/*  75 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  79 */     return (0 == size());
/*     */   }
/*     */   
/*     */   public boolean removeElement(byte element) {
/*  83 */     for (ByteIterator iter = iterator(); iter.hasNext();) {
/*  84 */       if (iter.next() == element) {
/*  85 */         iter.remove();
/*  86 */         return true;
/*     */       } 
/*     */     } 
/*  89 */     return false;
/*     */   }
/*     */   
/*     */   public boolean removeAll(ByteCollection c) {
/*  93 */     boolean modified = false;
/*  94 */     for (ByteIterator iter = c.iterator(); iter.hasNext();) {
/*  95 */       modified |= removeElement(iter.next());
/*     */     }
/*  97 */     return modified;
/*     */   }
/*     */   
/*     */   public boolean retainAll(ByteCollection c) {
/* 101 */     boolean modified = false;
/* 102 */     for (ByteIterator iter = iterator(); iter.hasNext();) {
/* 103 */       if (!c.contains(iter.next())) {
/* 104 */         iter.remove();
/* 105 */         modified = true;
/*     */       } 
/*     */     } 
/* 108 */     return modified;
/*     */   }
/*     */   
/*     */   public byte[] toArray() {
/* 112 */     byte[] array = new byte[size()];
/* 113 */     int i = 0;
/* 114 */     for (ByteIterator iter = iterator(); iter.hasNext(); ) {
/* 115 */       array[i] = iter.next();
/* 116 */       i++;
/*     */     } 
/* 118 */     return array;
/*     */   }
/*     */   
/*     */   public byte[] toArray(byte[] a) {
/* 122 */     if (a.length < size()) {
/* 123 */       return toArray();
/*     */     }
/* 125 */     int i = 0;
/* 126 */     for (ByteIterator iter = iterator(); iter.hasNext(); ) {
/* 127 */       a[i] = iter.next();
/* 128 */       i++;
/*     */     } 
/* 130 */     return a;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/AbstractByteCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */