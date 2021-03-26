/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.collections.list.UnmodifiableList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IteratorChain
/*     */   implements Iterator
/*     */ {
/*  53 */   protected final List iteratorChain = new ArrayList();
/*     */   
/*  55 */   protected int currentIteratorIndex = 0;
/*     */   
/*  57 */   protected Iterator currentIterator = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   protected Iterator lastUsedIterator = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isLocked = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IteratorChain() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IteratorChain(Iterator iterator) {
/*  89 */     addIterator(iterator);
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
/*     */   public IteratorChain(Iterator a, Iterator b) {
/* 102 */     addIterator(a);
/* 103 */     addIterator(b);
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
/*     */   public IteratorChain(Iterator[] iterators) {
/* 115 */     for (int i = 0; i < iterators.length; i++) {
/* 116 */       addIterator(iterators[i]);
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
/*     */   public IteratorChain(Collection iterators) {
/* 130 */     for (Iterator it = iterators.iterator(); it.hasNext(); ) {
/* 131 */       Iterator item = it.next();
/* 132 */       addIterator(item);
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
/*     */   public void addIterator(Iterator iterator) {
/* 145 */     checkLocked();
/* 146 */     if (iterator == null) {
/* 147 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 149 */     this.iteratorChain.add(iterator);
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
/*     */   public void setIterator(int index, Iterator iterator) throws IndexOutOfBoundsException {
/* 162 */     checkLocked();
/* 163 */     if (iterator == null) {
/* 164 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 166 */     this.iteratorChain.set(index, iterator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getIterators() {
/* 175 */     return UnmodifiableList.decorate(this.iteratorChain);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 184 */     return this.iteratorChain.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLocked() {
/* 195 */     return this.isLocked;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkLocked() {
/* 202 */     if (this.isLocked == true) {
/* 203 */       throw new UnsupportedOperationException("IteratorChain cannot be changed after the first use of a method from the Iterator interface");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void lockChain() {
/* 212 */     if (!this.isLocked) {
/* 213 */       this.isLocked = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateCurrentIterator() {
/* 222 */     if (this.currentIterator == null) {
/* 223 */       if (this.iteratorChain.isEmpty()) {
/* 224 */         this.currentIterator = EmptyIterator.INSTANCE;
/*     */       } else {
/* 226 */         this.currentIterator = this.iteratorChain.get(0);
/*     */       } 
/*     */ 
/*     */       
/* 230 */       this.lastUsedIterator = this.currentIterator;
/*     */     } 
/*     */     
/* 233 */     while (!this.currentIterator.hasNext() && this.currentIteratorIndex < this.iteratorChain.size() - 1) {
/* 234 */       this.currentIteratorIndex++;
/* 235 */       this.currentIterator = this.iteratorChain.get(this.currentIteratorIndex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasNext() {
/* 246 */     lockChain();
/* 247 */     updateCurrentIterator();
/* 248 */     this.lastUsedIterator = this.currentIterator;
/*     */     
/* 250 */     return this.currentIterator.hasNext();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object next() {
/* 260 */     lockChain();
/* 261 */     updateCurrentIterator();
/* 262 */     this.lastUsedIterator = this.currentIterator;
/*     */     
/* 264 */     return this.currentIterator.next();
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
/*     */   public void remove() {
/* 282 */     lockChain();
/* 283 */     updateCurrentIterator();
/*     */     
/* 285 */     this.lastUsedIterator.remove();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/IteratorChain.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */