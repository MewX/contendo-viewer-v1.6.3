/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class BinaryHeap
/*     */   extends AbstractCollection
/*     */   implements Buffer, PriorityQueue
/*     */ {
/*     */   private static final int DEFAULT_CAPACITY = 13;
/*     */   int m_size;
/*     */   Object[] m_elements;
/*     */   boolean m_isMinHeap;
/*     */   Comparator m_comparator;
/*     */   
/*     */   public BinaryHeap() {
/*  92 */     this(13, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BinaryHeap(Comparator comparator) {
/* 103 */     this();
/* 104 */     this.m_comparator = comparator;
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
/*     */   public BinaryHeap(int capacity) {
/* 116 */     this(capacity, true);
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
/*     */   public BinaryHeap(int capacity, Comparator comparator) {
/* 129 */     this(capacity);
/* 130 */     this.m_comparator = comparator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BinaryHeap(boolean isMinHeap) {
/* 140 */     this(13, isMinHeap);
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
/*     */   public BinaryHeap(boolean isMinHeap, Comparator comparator) {
/* 152 */     this(isMinHeap);
/* 153 */     this.m_comparator = comparator;
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
/*     */   public BinaryHeap(int capacity, boolean isMinHeap) {
/* 168 */     if (capacity <= 0) {
/* 169 */       throw new IllegalArgumentException("invalid capacity");
/*     */     }
/* 171 */     this.m_isMinHeap = isMinHeap;
/*     */ 
/*     */     
/* 174 */     this.m_elements = new Object[capacity + 1];
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
/*     */   public BinaryHeap(int capacity, boolean isMinHeap, Comparator comparator) {
/* 189 */     this(capacity, isMinHeap);
/* 190 */     this.m_comparator = comparator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 198 */     this.m_elements = new Object[this.m_elements.length];
/* 199 */     this.m_size = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 209 */     return (this.m_size == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFull() {
/* 220 */     return (this.m_elements.length == this.m_size + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insert(Object element) {
/* 229 */     if (isFull()) {
/* 230 */       grow();
/*     */     }
/*     */     
/* 233 */     if (this.m_isMinHeap) {
/* 234 */       percolateUpMinHeap(element);
/*     */     } else {
/* 236 */       percolateUpMaxHeap(element);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object peek() throws NoSuchElementException {
/* 247 */     if (isEmpty()) {
/* 248 */       throw new NoSuchElementException();
/*     */     }
/* 250 */     return this.m_elements[1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object pop() throws NoSuchElementException {
/* 261 */     Object result = peek();
/* 262 */     this.m_elements[1] = this.m_elements[this.m_size--];
/*     */ 
/*     */ 
/*     */     
/* 266 */     this.m_elements[this.m_size + 1] = null;
/*     */     
/* 268 */     if (this.m_size != 0)
/*     */     {
/* 270 */       if (this.m_isMinHeap) {
/* 271 */         percolateDownMinHeap(1);
/*     */       } else {
/* 273 */         percolateDownMaxHeap(1);
/*     */       } 
/*     */     }
/*     */     
/* 277 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void percolateDownMinHeap(int index) {
/* 288 */     Object element = this.m_elements[index];
/* 289 */     int hole = index;
/*     */     
/* 291 */     while (hole * 2 <= this.m_size) {
/* 292 */       int child = hole * 2;
/*     */ 
/*     */ 
/*     */       
/* 296 */       if (child != this.m_size && compare(this.m_elements[child + 1], this.m_elements[child]) < 0) {
/* 297 */         child++;
/*     */       }
/*     */ 
/*     */       
/* 301 */       if (compare(this.m_elements[child], element) >= 0) {
/*     */         break;
/*     */       }
/*     */       
/* 305 */       this.m_elements[hole] = this.m_elements[child];
/* 306 */       hole = child;
/*     */     } 
/*     */     
/* 309 */     this.m_elements[hole] = element;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void percolateDownMaxHeap(int index) {
/* 320 */     Object element = this.m_elements[index];
/* 321 */     int hole = index;
/*     */     
/* 323 */     while (hole * 2 <= this.m_size) {
/* 324 */       int child = hole * 2;
/*     */ 
/*     */ 
/*     */       
/* 328 */       if (child != this.m_size && compare(this.m_elements[child + 1], this.m_elements[child]) > 0) {
/* 329 */         child++;
/*     */       }
/*     */ 
/*     */       
/* 333 */       if (compare(this.m_elements[child], element) <= 0) {
/*     */         break;
/*     */       }
/*     */       
/* 337 */       this.m_elements[hole] = this.m_elements[child];
/* 338 */       hole = child;
/*     */     } 
/*     */     
/* 341 */     this.m_elements[hole] = element;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void percolateUpMinHeap(int index) {
/* 352 */     int hole = index;
/* 353 */     Object element = this.m_elements[hole];
/* 354 */     while (hole > 1 && compare(element, this.m_elements[hole / 2]) < 0) {
/*     */ 
/*     */       
/* 357 */       int next = hole / 2;
/* 358 */       this.m_elements[hole] = this.m_elements[next];
/* 359 */       hole = next;
/*     */     } 
/* 361 */     this.m_elements[hole] = element;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void percolateUpMinHeap(Object element) {
/* 372 */     this.m_elements[++this.m_size] = element;
/* 373 */     percolateUpMinHeap(this.m_size);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void percolateUpMaxHeap(int index) {
/* 384 */     int hole = index;
/* 385 */     Object element = this.m_elements[hole];
/*     */     
/* 387 */     while (hole > 1 && compare(element, this.m_elements[hole / 2]) > 0) {
/*     */ 
/*     */       
/* 390 */       int next = hole / 2;
/* 391 */       this.m_elements[hole] = this.m_elements[next];
/* 392 */       hole = next;
/*     */     } 
/*     */     
/* 395 */     this.m_elements[hole] = element;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void percolateUpMaxHeap(Object element) {
/* 406 */     this.m_elements[++this.m_size] = element;
/* 407 */     percolateUpMaxHeap(this.m_size);
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
/*     */   private int compare(Object a, Object b) {
/* 419 */     if (this.m_comparator != null) {
/* 420 */       return this.m_comparator.compare(a, b);
/*     */     }
/* 422 */     return ((Comparable)a).compareTo(b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void grow() {
/* 430 */     Object[] elements = new Object[this.m_elements.length * 2];
/* 431 */     System.arraycopy(this.m_elements, 0, elements, 0, this.m_elements.length);
/* 432 */     this.m_elements = elements;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 442 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 444 */     sb.append("[ ");
/*     */     
/* 446 */     for (int i = 1; i < this.m_size + 1; i++) {
/* 447 */       if (i != 1) {
/* 448 */         sb.append(", ");
/*     */       }
/* 450 */       sb.append(this.m_elements[i]);
/*     */     } 
/*     */     
/* 453 */     sb.append(" ]");
/*     */     
/* 455 */     return sb.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 465 */     return new Iterator(this) {
/*     */         private int index;
/*     */         private int lastReturnedIndex;
/*     */         private final BinaryHeap this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 471 */           return (this.index <= this.this$0.m_size);
/*     */         }
/*     */         
/*     */         public Object next() {
/* 475 */           if (!hasNext()) throw new NoSuchElementException(); 
/* 476 */           this.lastReturnedIndex = this.index;
/* 477 */           this.index++;
/* 478 */           return this.this$0.m_elements[this.lastReturnedIndex];
/*     */         }
/*     */         
/*     */         public void remove() {
/* 482 */           if (this.lastReturnedIndex == -1) {
/* 483 */             throw new IllegalStateException();
/*     */           }
/* 485 */           this.this$0.m_elements[this.lastReturnedIndex] = this.this$0.m_elements[this.this$0.m_size];
/* 486 */           this.this$0.m_elements[this.this$0.m_size] = null;
/* 487 */           this.this$0.m_size--;
/* 488 */           if (this.this$0.m_size != 0 && this.lastReturnedIndex <= this.this$0.m_size) {
/* 489 */             int compareToParent = 0;
/* 490 */             if (this.lastReturnedIndex > 1) {
/* 491 */               compareToParent = this.this$0.compare(this.this$0.m_elements[this.lastReturnedIndex], this.this$0.m_elements[this.lastReturnedIndex / 2]);
/*     */             }
/*     */             
/* 494 */             if (this.this$0.m_isMinHeap) {
/* 495 */               if (this.lastReturnedIndex > 1 && compareToParent < 0) {
/* 496 */                 this.this$0.percolateUpMinHeap(this.lastReturnedIndex);
/*     */               } else {
/* 498 */                 this.this$0.percolateDownMinHeap(this.lastReturnedIndex);
/*     */               }
/*     */             
/* 501 */             } else if (this.lastReturnedIndex > 1 && compareToParent > 0) {
/* 502 */               this.this$0.percolateUpMaxHeap(this.lastReturnedIndex);
/*     */             } else {
/* 504 */               this.this$0.percolateDownMaxHeap(this.lastReturnedIndex);
/*     */             } 
/*     */           } 
/*     */           
/* 508 */           this.index--;
/* 509 */           this.lastReturnedIndex = -1;
/*     */         }
/*     */       };
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
/*     */   public boolean add(Object object) {
/* 523 */     insert(object);
/* 524 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get() {
/*     */     try {
/* 535 */       return peek();
/* 536 */     } catch (NoSuchElementException e) {
/* 537 */       throw new BufferUnderflowException();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove() {
/*     */     try {
/* 549 */       return pop();
/* 550 */     } catch (NoSuchElementException e) {
/* 551 */       throw new BufferUnderflowException();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 561 */     return this.m_size;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/BinaryHeap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */