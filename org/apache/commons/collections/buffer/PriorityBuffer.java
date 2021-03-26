/*     */ package org.apache.commons.collections.buffer;
/*     */ 
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.Buffer;
/*     */ import org.apache.commons.collections.BufferUnderflowException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PriorityBuffer
/*     */   extends AbstractCollection
/*     */   implements Buffer
/*     */ {
/*     */   private static final int DEFAULT_CAPACITY = 13;
/*     */   protected Object[] elements;
/*     */   protected int size;
/*     */   protected boolean ascendingOrder;
/*     */   protected Comparator comparator;
/*     */   
/*     */   public PriorityBuffer() {
/*  93 */     this(13, true, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PriorityBuffer(Comparator comparator) {
/* 104 */     this(13, true, comparator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PriorityBuffer(boolean ascendingOrder) {
/* 115 */     this(13, ascendingOrder, null);
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
/*     */   public PriorityBuffer(boolean ascendingOrder, Comparator comparator) {
/* 127 */     this(13, ascendingOrder, comparator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PriorityBuffer(int capacity) {
/* 138 */     this(capacity, true, null);
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
/*     */   public PriorityBuffer(int capacity, Comparator comparator) {
/* 151 */     this(capacity, true, comparator);
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
/*     */   public PriorityBuffer(int capacity, boolean ascendingOrder) {
/* 164 */     this(capacity, ascendingOrder, null);
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
/*     */   public PriorityBuffer(int capacity, boolean ascendingOrder, Comparator comparator) {
/* 180 */     if (capacity <= 0) {
/* 181 */       throw new IllegalArgumentException("invalid capacity");
/*     */     }
/* 183 */     this.ascendingOrder = ascendingOrder;
/*     */ 
/*     */     
/* 186 */     this.elements = new Object[capacity + 1];
/* 187 */     this.comparator = comparator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAscendingOrder() {
/* 197 */     return this.ascendingOrder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Comparator comparator() {
/* 206 */     return this.comparator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 216 */     return this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 223 */     this.elements = new Object[this.elements.length];
/* 224 */     this.size = 0;
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
/*     */   public boolean add(Object element) {
/* 236 */     if (isAtCapacity()) {
/* 237 */       grow();
/*     */     }
/*     */     
/* 240 */     if (this.ascendingOrder) {
/* 241 */       percolateUpMinHeap(element);
/*     */     } else {
/* 243 */       percolateUpMaxHeap(element);
/*     */     } 
/* 245 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get() {
/* 255 */     if (isEmpty()) {
/* 256 */       throw new BufferUnderflowException();
/*     */     }
/* 258 */     return this.elements[1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove() {
/* 269 */     Object result = get();
/* 270 */     this.elements[1] = this.elements[this.size--];
/*     */ 
/*     */ 
/*     */     
/* 274 */     this.elements[this.size + 1] = null;
/*     */     
/* 276 */     if (this.size != 0)
/*     */     {
/* 278 */       if (this.ascendingOrder) {
/* 279 */         percolateDownMinHeap(1);
/*     */       } else {
/* 281 */         percolateDownMaxHeap(1);
/*     */       } 
/*     */     }
/*     */     
/* 285 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isAtCapacity() {
/* 296 */     return (this.elements.length == this.size + 1);
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
/*     */   protected void percolateDownMinHeap(int index) {
/* 308 */     Object element = this.elements[index];
/* 309 */     int hole = index;
/*     */     
/* 311 */     while (hole * 2 <= this.size) {
/* 312 */       int child = hole * 2;
/*     */ 
/*     */ 
/*     */       
/* 316 */       if (child != this.size && compare(this.elements[child + 1], this.elements[child]) < 0) {
/* 317 */         child++;
/*     */       }
/*     */ 
/*     */       
/* 321 */       if (compare(this.elements[child], element) >= 0) {
/*     */         break;
/*     */       }
/*     */       
/* 325 */       this.elements[hole] = this.elements[child];
/* 326 */       hole = child;
/*     */     } 
/*     */     
/* 329 */     this.elements[hole] = element;
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
/* 340 */     Object element = this.elements[index];
/* 341 */     int hole = index;
/*     */     
/* 343 */     while (hole * 2 <= this.size) {
/* 344 */       int child = hole * 2;
/*     */ 
/*     */ 
/*     */       
/* 348 */       if (child != this.size && compare(this.elements[child + 1], this.elements[child]) > 0) {
/* 349 */         child++;
/*     */       }
/*     */ 
/*     */       
/* 353 */       if (compare(this.elements[child], element) <= 0) {
/*     */         break;
/*     */       }
/*     */       
/* 357 */       this.elements[hole] = this.elements[child];
/* 358 */       hole = child;
/*     */     } 
/*     */     
/* 361 */     this.elements[hole] = element;
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
/* 372 */     int hole = index;
/* 373 */     Object element = this.elements[hole];
/* 374 */     while (hole > 1 && compare(element, this.elements[hole / 2]) < 0) {
/*     */ 
/*     */       
/* 377 */       int next = hole / 2;
/* 378 */       this.elements[hole] = this.elements[next];
/* 379 */       hole = next;
/*     */     } 
/* 381 */     this.elements[hole] = element;
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
/* 392 */     this.elements[++this.size] = element;
/* 393 */     percolateUpMinHeap(this.size);
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
/* 404 */     int hole = index;
/* 405 */     Object element = this.elements[hole];
/*     */     
/* 407 */     while (hole > 1 && compare(element, this.elements[hole / 2]) > 0) {
/*     */ 
/*     */       
/* 410 */       int next = hole / 2;
/* 411 */       this.elements[hole] = this.elements[next];
/* 412 */       hole = next;
/*     */     } 
/*     */     
/* 415 */     this.elements[hole] = element;
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
/* 426 */     this.elements[++this.size] = element;
/* 427 */     percolateUpMaxHeap(this.size);
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
/*     */   protected int compare(Object a, Object b) {
/* 439 */     if (this.comparator != null) {
/* 440 */       return this.comparator.compare(a, b);
/*     */     }
/* 442 */     return ((Comparable)a).compareTo(b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void grow() {
/* 450 */     Object[] array = new Object[this.elements.length * 2];
/* 451 */     System.arraycopy(this.elements, 0, array, 0, this.elements.length);
/* 452 */     this.elements = array;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 462 */     return new Iterator(this) {
/*     */         private int index;
/*     */         private int lastReturnedIndex;
/*     */         private final PriorityBuffer this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 468 */           return (this.index <= this.this$0.size);
/*     */         }
/*     */         
/*     */         public Object next() {
/* 472 */           if (!hasNext()) {
/* 473 */             throw new NoSuchElementException();
/*     */           }
/* 475 */           this.lastReturnedIndex = this.index;
/* 476 */           this.index++;
/* 477 */           return this.this$0.elements[this.lastReturnedIndex];
/*     */         }
/*     */         
/*     */         public void remove() {
/* 481 */           if (this.lastReturnedIndex == -1) {
/* 482 */             throw new IllegalStateException();
/*     */           }
/* 484 */           this.this$0.elements[this.lastReturnedIndex] = this.this$0.elements[this.this$0.size];
/* 485 */           this.this$0.elements[this.this$0.size] = null;
/* 486 */           this.this$0.size--;
/* 487 */           if (this.this$0.size != 0 && this.lastReturnedIndex <= this.this$0.size) {
/* 488 */             int compareToParent = 0;
/* 489 */             if (this.lastReturnedIndex > 1) {
/* 490 */               compareToParent = this.this$0.compare(this.this$0.elements[this.lastReturnedIndex], this.this$0.elements[this.lastReturnedIndex / 2]);
/*     */             }
/*     */             
/* 493 */             if (this.this$0.ascendingOrder) {
/* 494 */               if (this.lastReturnedIndex > 1 && compareToParent < 0) {
/* 495 */                 this.this$0.percolateUpMinHeap(this.lastReturnedIndex);
/*     */               } else {
/* 497 */                 this.this$0.percolateDownMinHeap(this.lastReturnedIndex);
/*     */               }
/*     */             
/* 500 */             } else if (this.lastReturnedIndex > 1 && compareToParent > 0) {
/* 501 */               this.this$0.percolateUpMaxHeap(this.lastReturnedIndex);
/*     */             } else {
/* 503 */               this.this$0.percolateDownMaxHeap(this.lastReturnedIndex);
/*     */             } 
/*     */           } 
/*     */           
/* 507 */           this.index--;
/* 508 */           this.lastReturnedIndex = -1;
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
/*     */   public String toString() {
/* 521 */     StringBuffer sb = new StringBuffer();
/*     */     
/* 523 */     sb.append("[ ");
/*     */     
/* 525 */     for (int i = 1; i < this.size + 1; i++) {
/* 526 */       if (i != 1) {
/* 527 */         sb.append(", ");
/*     */       }
/* 529 */       sb.append(this.elements[i]);
/*     */     } 
/*     */     
/* 532 */     sb.append(" ]");
/*     */     
/* 534 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/buffer/PriorityBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */