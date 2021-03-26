/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.ListIterator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FilterListIterator
/*     */   implements ListIterator
/*     */ {
/*     */   private ListIterator iterator;
/*     */   private Predicate predicate;
/*     */   private Object nextObject;
/*     */   private boolean nextObjectSet = false;
/*     */   private Object previousObject;
/*     */   private boolean previousObjectSet = false;
/*  71 */   private int nextIndex = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FilterListIterator() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FilterListIterator(ListIterator iterator) {
/*  92 */     this.iterator = iterator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FilterListIterator(ListIterator iterator, Predicate predicate) {
/* 103 */     this.iterator = iterator;
/* 104 */     this.predicate = predicate;
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
/*     */   public FilterListIterator(Predicate predicate) {
/* 117 */     this.predicate = predicate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(Object o) {
/* 123 */     throw new UnsupportedOperationException("FilterListIterator.add(Object) is not supported.");
/*     */   }
/*     */   
/*     */   public boolean hasNext() {
/* 127 */     if (this.nextObjectSet) {
/* 128 */       return true;
/*     */     }
/* 130 */     return setNextObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPrevious() {
/* 135 */     if (this.previousObjectSet) {
/* 136 */       return true;
/*     */     }
/* 138 */     return setPreviousObject();
/*     */   }
/*     */ 
/*     */   
/*     */   public Object next() {
/* 143 */     if (!this.nextObjectSet && 
/* 144 */       !setNextObject()) {
/* 145 */       throw new NoSuchElementException();
/*     */     }
/*     */     
/* 148 */     this.nextIndex++;
/* 149 */     Object temp = this.nextObject;
/* 150 */     clearNextObject();
/* 151 */     return temp;
/*     */   }
/*     */   
/*     */   public int nextIndex() {
/* 155 */     return this.nextIndex;
/*     */   }
/*     */   
/*     */   public Object previous() {
/* 159 */     if (!this.previousObjectSet && 
/* 160 */       !setPreviousObject()) {
/* 161 */       throw new NoSuchElementException();
/*     */     }
/*     */     
/* 164 */     this.nextIndex--;
/* 165 */     Object temp = this.previousObject;
/* 166 */     clearPreviousObject();
/* 167 */     return temp;
/*     */   }
/*     */   
/*     */   public int previousIndex() {
/* 171 */     return this.nextIndex - 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void remove() {
/* 176 */     throw new UnsupportedOperationException("FilterListIterator.remove() is not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void set(Object o) {
/* 181 */     throw new UnsupportedOperationException("FilterListIterator.set(Object) is not supported.");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListIterator getListIterator() {
/* 191 */     return this.iterator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setListIterator(ListIterator iterator) {
/* 201 */     this.iterator = iterator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Predicate getPredicate() {
/* 211 */     return this.predicate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPredicate(Predicate predicate) {
/* 220 */     this.predicate = predicate;
/*     */   }
/*     */ 
/*     */   
/*     */   private void clearNextObject() {
/* 225 */     this.nextObject = null;
/* 226 */     this.nextObjectSet = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean setNextObject() {
/* 234 */     if (this.previousObjectSet) {
/* 235 */       clearPreviousObject();
/* 236 */       if (!setNextObject()) {
/* 237 */         return false;
/*     */       }
/* 239 */       clearNextObject();
/*     */     } 
/*     */ 
/*     */     
/* 243 */     while (this.iterator.hasNext()) {
/* 244 */       Object object = this.iterator.next();
/* 245 */       if (this.predicate.evaluate(object)) {
/* 246 */         this.nextObject = object;
/* 247 */         this.nextObjectSet = true;
/* 248 */         return true;
/*     */       } 
/*     */     } 
/* 251 */     return false;
/*     */   }
/*     */   
/*     */   private void clearPreviousObject() {
/* 255 */     this.previousObject = null;
/* 256 */     this.previousObjectSet = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean setPreviousObject() {
/* 264 */     if (this.nextObjectSet) {
/* 265 */       clearNextObject();
/* 266 */       if (!setPreviousObject()) {
/* 267 */         return false;
/*     */       }
/* 269 */       clearPreviousObject();
/*     */     } 
/*     */ 
/*     */     
/* 273 */     while (this.iterator.hasPrevious()) {
/* 274 */       Object object = this.iterator.previous();
/* 275 */       if (this.predicate.evaluate(object)) {
/* 276 */         this.previousObject = object;
/* 277 */         this.previousObjectSet = true;
/* 278 */         return true;
/*     */       } 
/*     */     } 
/* 281 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/FilterListIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */