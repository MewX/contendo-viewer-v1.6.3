/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.ListIterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.ResettableListIterator;
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
/*     */ public class SingletonListIterator
/*     */   implements ListIterator, ResettableListIterator
/*     */ {
/*     */   private boolean beforeFirst = true;
/*     */   private boolean nextCalled = false;
/*     */   private boolean removed = false;
/*     */   private Object object;
/*     */   
/*     */   public SingletonListIterator(Object object) {
/*  47 */     this.object = object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNext() {
/*  58 */     return (this.beforeFirst && !this.removed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPrevious() {
/*  69 */     return (!this.beforeFirst && !this.removed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextIndex() {
/*  79 */     return this.beforeFirst ? 0 : 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int previousIndex() {
/*  90 */     return this.beforeFirst ? -1 : 0;
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
/*     */   public Object next() {
/* 103 */     if (!this.beforeFirst || this.removed) {
/* 104 */       throw new NoSuchElementException();
/*     */     }
/* 106 */     this.beforeFirst = false;
/* 107 */     this.nextCalled = true;
/* 108 */     return this.object;
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
/*     */   public Object previous() {
/* 121 */     if (this.beforeFirst || this.removed) {
/* 122 */       throw new NoSuchElementException();
/*     */     }
/* 124 */     this.beforeFirst = true;
/* 125 */     return this.object;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove() {
/* 136 */     if (!this.nextCalled || this.removed) {
/* 137 */       throw new IllegalStateException();
/*     */     }
/* 139 */     this.object = null;
/* 140 */     this.removed = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(Object obj) {
/* 150 */     throw new UnsupportedOperationException("add() is not supported by this iterator");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(Object obj) {
/* 161 */     if (!this.nextCalled || this.removed) {
/* 162 */       throw new IllegalStateException();
/*     */     }
/* 164 */     this.object = obj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 171 */     this.beforeFirst = true;
/* 172 */     this.nextCalled = false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/SingletonListIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */