/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.ArrayStack;
/*     */ import org.apache.commons.collections.Transformer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ObjectGraphIterator
/*     */   implements Iterator
/*     */ {
/*  80 */   protected final ArrayStack stack = new ArrayStack(8);
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object root;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Transformer transformer;
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean hasNext = false;
/*     */ 
/*     */ 
/*     */   
/*     */   protected Iterator currentIterator;
/*     */ 
/*     */   
/*     */   protected Object currentValue;
/*     */ 
/*     */   
/*     */   protected Iterator lastUsedIterator;
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectGraphIterator(Object root, Transformer transformer) {
/* 107 */     if (root instanceof Iterator) {
/* 108 */       this.currentIterator = (Iterator)root;
/*     */     } else {
/* 110 */       this.root = root;
/*     */     } 
/* 112 */     this.transformer = transformer;
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
/*     */   public ObjectGraphIterator(Iterator rootIterator) {
/* 127 */     this.currentIterator = rootIterator;
/* 128 */     this.transformer = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateCurrentIterator() {
/* 136 */     if (this.hasNext) {
/*     */       return;
/*     */     }
/* 139 */     if (this.currentIterator == null) {
/* 140 */       if (this.root != null) {
/*     */ 
/*     */         
/* 143 */         if (this.transformer == null) {
/* 144 */           findNext(this.root);
/*     */         } else {
/* 146 */           findNext(this.transformer.transform(this.root));
/*     */         } 
/* 148 */         this.root = null;
/*     */       } 
/*     */     } else {
/* 151 */       findNextByIterator(this.currentIterator);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void findNext(Object value) {
/* 161 */     if (value instanceof Iterator) {
/*     */       
/* 163 */       findNextByIterator((Iterator)value);
/*     */     } else {
/*     */       
/* 166 */       this.currentValue = value;
/* 167 */       this.hasNext = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void findNextByIterator(Iterator iterator) {
/* 177 */     if (iterator != this.currentIterator) {
/*     */       
/* 179 */       if (this.currentIterator != null) {
/* 180 */         this.stack.push(this.currentIterator);
/*     */       }
/* 182 */       this.currentIterator = iterator;
/*     */     } 
/*     */     
/* 185 */     while (this.currentIterator.hasNext() && !this.hasNext) {
/* 186 */       Object next = this.currentIterator.next();
/* 187 */       if (this.transformer != null) {
/* 188 */         next = this.transformer.transform(next);
/*     */       }
/* 190 */       findNext(next);
/*     */     } 
/* 192 */     if (!this.hasNext)
/*     */     {
/* 194 */       if (!this.stack.isEmpty()) {
/*     */ 
/*     */ 
/*     */         
/* 198 */         this.currentIterator = (Iterator)this.stack.pop();
/* 199 */         findNextByIterator(this.currentIterator);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNext() {
/* 210 */     updateCurrentIterator();
/* 211 */     return this.hasNext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object next() {
/* 221 */     updateCurrentIterator();
/* 222 */     if (!this.hasNext) {
/* 223 */       throw new NoSuchElementException("No more elements in the iteration");
/*     */     }
/* 225 */     this.lastUsedIterator = this.currentIterator;
/* 226 */     Object result = this.currentValue;
/* 227 */     this.currentValue = null;
/* 228 */     this.hasNext = false;
/* 229 */     return result;
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
/*     */   public void remove() {
/* 246 */     if (this.lastUsedIterator == null) {
/* 247 */       throw new IllegalStateException("Iterator remove() cannot be called at this time");
/*     */     }
/* 249 */     this.lastUsedIterator.remove();
/* 250 */     this.lastUsedIterator = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/ObjectGraphIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */