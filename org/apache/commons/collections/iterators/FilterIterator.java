/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.Predicate;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilterIterator
/*     */   implements Iterator
/*     */ {
/*     */   private Iterator iterator;
/*     */   private Predicate predicate;
/*     */   private Object nextObject;
/*     */   private boolean nextObjectSet = false;
/*     */   
/*     */   public FilterIterator() {}
/*     */   
/*     */   public FilterIterator(Iterator iterator) {
/*  63 */     this.iterator = iterator;
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
/*     */   public FilterIterator(Iterator iterator, Predicate predicate) {
/*  75 */     this.iterator = iterator;
/*  76 */     this.predicate = predicate;
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
/*  87 */     if (this.nextObjectSet) {
/*  88 */       return true;
/*     */     }
/*  90 */     return setNextObject();
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
/*     */   public Object next() {
/* 102 */     if (!this.nextObjectSet && 
/* 103 */       !setNextObject()) {
/* 104 */       throw new NoSuchElementException();
/*     */     }
/*     */     
/* 107 */     this.nextObjectSet = false;
/* 108 */     return this.nextObject;
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
/*     */   public void remove() {
/* 123 */     if (this.nextObjectSet) {
/* 124 */       throw new IllegalStateException("remove() cannot be called");
/*     */     }
/* 126 */     this.iterator.remove();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator getIterator() {
/* 136 */     return this.iterator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIterator(Iterator iterator) {
/* 146 */     this.iterator = iterator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Predicate getPredicate() {
/* 156 */     return this.predicate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPredicate(Predicate predicate) {
/* 165 */     this.predicate = predicate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean setNextObject() {
/* 174 */     while (this.iterator.hasNext()) {
/* 175 */       Object object = this.iterator.next();
/* 176 */       if (this.predicate.evaluate(object)) {
/* 177 */         this.nextObject = object;
/* 178 */         this.nextObjectSet = true;
/* 179 */         return true;
/*     */       } 
/*     */     } 
/* 182 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/FilterIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */