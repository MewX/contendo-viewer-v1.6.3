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
/*     */ public abstract class AbstractBooleanCollection
/*     */   implements BooleanCollection
/*     */ {
/*     */   public abstract BooleanIterator iterator();
/*     */   
/*     */   public abstract int size();
/*     */   
/*     */   public boolean add(boolean element) {
/*  38 */     throw new UnsupportedOperationException("add(boolean) is not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addAll(BooleanCollection c) {
/*  43 */     boolean modified = false;
/*  44 */     for (BooleanIterator iter = c.iterator(); iter.hasNext();) {
/*  45 */       modified |= add(iter.next());
/*     */     }
/*  47 */     return modified;
/*     */   }
/*     */   
/*     */   public void clear() {
/*  51 */     for (BooleanIterator iter = iterator(); iter.hasNext(); ) {
/*  52 */       iter.next();
/*  53 */       iter.remove();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean contains(boolean element) {
/*  58 */     for (BooleanIterator iter = iterator(); iter.hasNext();) {
/*  59 */       if (iter.next() == element) {
/*  60 */         return true;
/*     */       }
/*     */     } 
/*  63 */     return false;
/*     */   }
/*     */   
/*     */   public boolean containsAll(BooleanCollection c) {
/*  67 */     for (BooleanIterator iter = c.iterator(); iter.hasNext();) {
/*  68 */       if (!contains(iter.next())) {
/*  69 */         return false;
/*     */       }
/*     */     } 
/*  72 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*  76 */     return (0 == size());
/*     */   }
/*     */   
/*     */   public boolean removeElement(boolean element) {
/*  80 */     for (BooleanIterator iter = iterator(); iter.hasNext();) {
/*  81 */       if (iter.next() == element) {
/*  82 */         iter.remove();
/*  83 */         return true;
/*     */       } 
/*     */     } 
/*  86 */     return false;
/*     */   }
/*     */   
/*     */   public boolean removeAll(BooleanCollection c) {
/*  90 */     boolean modified = false;
/*  91 */     for (BooleanIterator iter = c.iterator(); iter.hasNext();) {
/*  92 */       modified |= removeElement(iter.next());
/*     */     }
/*  94 */     return modified;
/*     */   }
/*     */   
/*     */   public boolean retainAll(BooleanCollection c) {
/*  98 */     boolean modified = false;
/*  99 */     for (BooleanIterator iter = iterator(); iter.hasNext();) {
/* 100 */       if (!c.contains(iter.next())) {
/* 101 */         iter.remove();
/* 102 */         modified = true;
/*     */       } 
/*     */     } 
/* 105 */     return modified;
/*     */   }
/*     */   
/*     */   public boolean[] toArray() {
/* 109 */     boolean[] array = new boolean[size()];
/* 110 */     int i = 0;
/* 111 */     for (BooleanIterator iter = iterator(); iter.hasNext(); ) {
/* 112 */       array[i] = iter.next();
/* 113 */       i++;
/*     */     } 
/* 115 */     return array;
/*     */   }
/*     */   
/*     */   public boolean[] toArray(boolean[] a) {
/* 119 */     if (a.length < size()) {
/* 120 */       return toArray();
/*     */     }
/* 122 */     int i = 0;
/* 123 */     for (BooleanIterator iter = iterator(); iter.hasNext(); ) {
/* 124 */       a[i] = iter.next();
/* 125 */       i++;
/*     */     } 
/* 127 */     return a;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/AbstractBooleanCollection.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */