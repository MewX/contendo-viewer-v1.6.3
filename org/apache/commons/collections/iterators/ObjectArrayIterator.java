/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.ResettableIterator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectArrayIterator
/*     */   implements Iterator, ResettableIterator
/*     */ {
/*  46 */   protected Object[] array = null;
/*     */   
/*  48 */   protected int startIndex = 0;
/*     */   
/*  50 */   protected int endIndex = 0;
/*     */   
/*  52 */   protected int index = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectArrayIterator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectArrayIterator(Object[] array) {
/*  72 */     this(array, 0, array.length);
/*     */   }
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
/*     */   public ObjectArrayIterator(Object[] array, int start) {
/*  85 */     this(array, start, array.length);
/*     */   }
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
/*     */   public ObjectArrayIterator(Object[] array, int start, int end) {
/* 101 */     if (start < 0) {
/* 102 */       throw new ArrayIndexOutOfBoundsException("Start index must not be less than zero");
/*     */     }
/* 104 */     if (end > array.length) {
/* 105 */       throw new ArrayIndexOutOfBoundsException("End index must not be greater than the array length");
/*     */     }
/* 107 */     if (start > array.length) {
/* 108 */       throw new ArrayIndexOutOfBoundsException("Start index must not be greater than the array length");
/*     */     }
/* 110 */     if (end < start) {
/* 111 */       throw new IllegalArgumentException("End index must not be less than start index");
/*     */     }
/* 113 */     this.array = array;
/* 114 */     this.startIndex = start;
/* 115 */     this.endIndex = end;
/* 116 */     this.index = start;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNext() {
/* 128 */     return (this.index < this.endIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object next() {
/* 139 */     if (!hasNext()) {
/* 140 */       throw new NoSuchElementException();
/*     */     }
/* 142 */     return this.array[this.index++];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove() {
/* 151 */     throw new UnsupportedOperationException("remove() method is not supported for an ObjectArrayIterator");
/*     */   }
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
/*     */   public Object[] getArray() {
/* 165 */     return this.array;
/*     */   }
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
/*     */   public void setArray(Object[] array) {
/* 181 */     if (this.array != null) {
/* 182 */       throw new IllegalStateException("The array to iterate over has already been set");
/*     */     }
/* 184 */     this.array = array;
/* 185 */     this.startIndex = 0;
/* 186 */     this.endIndex = array.length;
/* 187 */     this.index = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStartIndex() {
/* 196 */     return this.startIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEndIndex() {
/* 205 */     return this.endIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 212 */     this.index = this.startIndex;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/ObjectArrayIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */