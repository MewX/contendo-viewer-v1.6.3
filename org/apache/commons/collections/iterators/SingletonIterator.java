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
/*     */ 
/*     */ public class SingletonIterator
/*     */   implements Iterator, ResettableIterator
/*     */ {
/*     */   private final boolean removeAllowed;
/*     */   private boolean beforeFirst = true;
/*     */   private boolean removed = false;
/*     */   private Object object;
/*     */   
/*     */   public SingletonIterator(Object object) {
/*  53 */     this(object, true);
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
/*     */   public SingletonIterator(Object object, boolean removeAllowed) {
/*  66 */     this.object = object;
/*  67 */     this.removeAllowed = removeAllowed;
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
/*  79 */     return (this.beforeFirst && !this.removed);
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
/*  92 */     if (!this.beforeFirst || this.removed) {
/*  93 */       throw new NoSuchElementException();
/*     */     }
/*  95 */     this.beforeFirst = false;
/*  96 */     return this.object;
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
/*     */   public void remove() {
/* 109 */     if (this.removeAllowed) {
/* 110 */       if (this.removed || this.beforeFirst) {
/* 111 */         throw new IllegalStateException();
/*     */       }
/* 113 */       this.object = null;
/* 114 */       this.removed = true;
/*     */     } else {
/*     */       
/* 117 */       throw new UnsupportedOperationException();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 125 */     this.beforeFirst = true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/SingletonIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */