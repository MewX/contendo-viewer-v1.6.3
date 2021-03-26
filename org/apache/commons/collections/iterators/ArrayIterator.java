/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.lang.reflect.Array;
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
/*     */ 
/*     */ public class ArrayIterator
/*     */   implements ResettableIterator
/*     */ {
/*     */   protected Object array;
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
/*     */ 
/*     */   
/*     */   public ArrayIterator() {}
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
/*     */   public ArrayIterator(Object array) {
/*  76 */     setArray(array);
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
/*     */   public ArrayIterator(Object array, int startIndex) {
/*  91 */     setArray(array);
/*  92 */     checkBound(startIndex, "start");
/*  93 */     this.startIndex = startIndex;
/*  94 */     this.index = startIndex;
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
/*     */   public ArrayIterator(Object array, int startIndex, int endIndex) {
/* 110 */     setArray(array);
/* 111 */     checkBound(startIndex, "start");
/* 112 */     checkBound(endIndex, "end");
/* 113 */     if (endIndex < startIndex) {
/* 114 */       throw new IllegalArgumentException("End index must not be less than start index.");
/*     */     }
/* 116 */     this.startIndex = startIndex;
/* 117 */     this.endIndex = endIndex;
/* 118 */     this.index = startIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkBound(int bound, String type) {
/* 129 */     if (bound > this.endIndex) {
/* 130 */       throw new ArrayIndexOutOfBoundsException("Attempt to make an ArrayIterator that " + type + "s beyond the end of the array. ");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 135 */     if (bound < 0) {
/* 136 */       throw new ArrayIndexOutOfBoundsException("Attempt to make an ArrayIterator that " + type + "s before the start of the array. ");
/*     */     }
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
/*     */   public boolean hasNext() {
/* 151 */     return (this.index < this.endIndex);
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
/* 162 */     if (!hasNext()) {
/* 163 */       throw new NoSuchElementException();
/*     */     }
/* 165 */     return Array.get(this.array, this.index++);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove() {
/* 174 */     throw new UnsupportedOperationException("remove() method is not supported");
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
/*     */   public Object getArray() {
/* 187 */     return this.array;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setArray(Object array) {
/* 209 */     this.endIndex = Array.getLength(array);
/* 210 */     this.startIndex = 0;
/* 211 */     this.array = array;
/* 212 */     this.index = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 219 */     this.index = this.startIndex;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/ArrayIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */