/*     */ package org.apache.commons.collections.iterators;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.BitSet;
/*     */ import java.util.Collection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
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
/*     */ public class CollatingIterator
/*     */   implements Iterator
/*     */ {
/*  45 */   private Comparator comparator = null;
/*     */ 
/*     */   
/*  48 */   private ArrayList iterators = null;
/*     */ 
/*     */   
/*  51 */   private ArrayList values = null;
/*     */ 
/*     */   
/*  54 */   private BitSet valueSet = null;
/*     */ 
/*     */   
/*  57 */   private int lastReturned = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CollatingIterator() {
/*  67 */     this((Comparator)null, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CollatingIterator(Comparator comp) {
/*  78 */     this(comp, 2);
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
/*     */   public CollatingIterator(Comparator comp, int initIterCapacity) {
/*  92 */     this.iterators = new ArrayList(initIterCapacity);
/*  93 */     setComparator(comp);
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
/*     */   public CollatingIterator(Comparator comp, Iterator a, Iterator b) {
/* 107 */     this(comp, 2);
/* 108 */     addIterator(a);
/* 109 */     addIterator(b);
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
/*     */   public CollatingIterator(Comparator comp, Iterator[] iterators) {
/* 122 */     this(comp, iterators.length);
/* 123 */     for (int i = 0; i < iterators.length; i++) {
/* 124 */       addIterator(iterators[i]);
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
/*     */   
/*     */   public CollatingIterator(Comparator comp, Collection iterators) {
/* 140 */     this(comp, iterators.size());
/* 141 */     for (Iterator it = iterators.iterator(); it.hasNext(); ) {
/* 142 */       Iterator item = it.next();
/* 143 */       addIterator(item);
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
/*     */   public void addIterator(Iterator iterator) {
/* 157 */     checkNotStarted();
/* 158 */     if (iterator == null) {
/* 159 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 161 */     this.iterators.add(iterator);
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
/*     */   public void setIterator(int index, Iterator iterator) {
/* 174 */     checkNotStarted();
/* 175 */     if (iterator == null) {
/* 176 */       throw new NullPointerException("Iterator must not be null");
/*     */     }
/* 178 */     this.iterators.set(index, iterator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getIterators() {
/* 187 */     return UnmodifiableList.decorate(this.iterators);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Comparator getComparator() {
/* 194 */     return this.comparator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComparator(Comparator comp) {
/* 203 */     checkNotStarted();
/* 204 */     this.comparator = comp;
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
/* 215 */     start();
/* 216 */     return (anyValueSet(this.valueSet) || anyHasNext(this.iterators));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object next() throws NoSuchElementException {
/* 226 */     if (!hasNext()) {
/* 227 */       throw new NoSuchElementException();
/*     */     }
/* 229 */     int leastIndex = least();
/* 230 */     if (leastIndex == -1) {
/* 231 */       throw new NoSuchElementException();
/*     */     }
/* 233 */     Object val = this.values.get(leastIndex);
/* 234 */     clear(leastIndex);
/* 235 */     this.lastReturned = leastIndex;
/* 236 */     return val;
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
/*     */   public void remove() {
/* 248 */     if (this.lastReturned == -1) {
/* 249 */       throw new IllegalStateException("No value can be removed at present");
/*     */     }
/* 251 */     Iterator it = this.iterators.get(this.lastReturned);
/* 252 */     it.remove();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void start() {
/* 261 */     if (this.values == null) {
/* 262 */       this.values = new ArrayList(this.iterators.size());
/* 263 */       this.valueSet = new BitSet(this.iterators.size());
/* 264 */       for (int i = 0; i < this.iterators.size(); i++) {
/* 265 */         this.values.add(null);
/* 266 */         this.valueSet.clear(i);
/*     */       } 
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
/*     */   private boolean set(int i) {
/* 281 */     Iterator it = this.iterators.get(i);
/* 282 */     if (it.hasNext()) {
/* 283 */       this.values.set(i, it.next());
/* 284 */       this.valueSet.set(i);
/* 285 */       return true;
/*     */     } 
/* 287 */     this.values.set(i, null);
/* 288 */     this.valueSet.clear(i);
/* 289 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void clear(int i) {
/* 298 */     this.values.set(i, null);
/* 299 */     this.valueSet.clear(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkNotStarted() throws IllegalStateException {
/* 309 */     if (this.values != null) {
/* 310 */       throw new IllegalStateException("Can't do that after next or hasNext has been called.");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int least() {
/* 321 */     int leastIndex = -1;
/* 322 */     Object leastObject = null;
/* 323 */     for (int i = 0; i < this.values.size(); i++) {
/* 324 */       if (!this.valueSet.get(i)) {
/* 325 */         set(i);
/*     */       }
/* 327 */       if (this.valueSet.get(i)) {
/* 328 */         if (leastIndex == -1) {
/* 329 */           leastIndex = i;
/* 330 */           leastObject = this.values.get(i);
/*     */         } else {
/* 332 */           Object curObject = this.values.get(i);
/* 333 */           if (this.comparator.compare(curObject, leastObject) < 0) {
/* 334 */             leastObject = curObject;
/* 335 */             leastIndex = i;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 340 */     return leastIndex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean anyValueSet(BitSet set) {
/* 348 */     for (int i = 0; i < set.size(); i++) {
/* 349 */       if (set.get(i)) {
/* 350 */         return true;
/*     */       }
/*     */     } 
/* 353 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean anyHasNext(ArrayList iters) {
/* 361 */     for (int i = 0; i < iters.size(); i++) {
/* 362 */       Iterator it = iters.get(i);
/* 363 */       if (it.hasNext()) {
/* 364 */         return true;
/*     */       }
/*     */     } 
/* 367 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/iterators/CollatingIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */