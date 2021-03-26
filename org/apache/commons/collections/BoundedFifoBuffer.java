/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
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
/*     */ public class BoundedFifoBuffer
/*     */   extends AbstractCollection
/*     */   implements BoundedCollection, Buffer
/*     */ {
/*     */   private final Object[] m_elements;
/*  58 */   private int m_start = 0;
/*  59 */   private int m_end = 0;
/*     */ 
/*     */   
/*     */   private boolean m_full = false;
/*     */   
/*     */   private final int maxElements;
/*     */ 
/*     */   
/*     */   public BoundedFifoBuffer() {
/*  68 */     this(32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundedFifoBuffer(int size) {
/*  79 */     if (size <= 0) {
/*  80 */       throw new IllegalArgumentException("The size must be greater than 0");
/*     */     }
/*  82 */     this.m_elements = new Object[size];
/*  83 */     this.maxElements = this.m_elements.length;
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
/*     */   public BoundedFifoBuffer(Collection coll) {
/*  95 */     this(coll.size());
/*  96 */     addAll(coll);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 105 */     int size = 0;
/*     */     
/* 107 */     if (this.m_end < this.m_start) {
/* 108 */       size = this.maxElements - this.m_start + this.m_end;
/* 109 */     } else if (this.m_end == this.m_start) {
/* 110 */       size = this.m_full ? this.maxElements : 0;
/*     */     } else {
/* 112 */       size = this.m_end - this.m_start;
/*     */     } 
/*     */     
/* 115 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 124 */     return (size() == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFull() {
/* 133 */     return (size() == this.maxElements);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int maxSize() {
/* 142 */     return this.maxElements;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 149 */     this.m_full = false;
/* 150 */     this.m_start = 0;
/* 151 */     this.m_end = 0;
/* 152 */     Arrays.fill(this.m_elements, (Object)null);
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
/* 164 */     if (null == element) {
/* 165 */       throw new NullPointerException("Attempted to add null object to buffer");
/*     */     }
/*     */     
/* 168 */     if (this.m_full) {
/* 169 */       throw new BufferOverflowException("The buffer cannot hold more than " + this.maxElements + " objects.");
/*     */     }
/*     */     
/* 172 */     this.m_elements[this.m_end++] = element;
/*     */     
/* 174 */     if (this.m_end >= this.maxElements) {
/* 175 */       this.m_end = 0;
/*     */     }
/*     */     
/* 178 */     if (this.m_end == this.m_start) {
/* 179 */       this.m_full = true;
/*     */     }
/*     */     
/* 182 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get() {
/* 192 */     if (isEmpty()) {
/* 193 */       throw new BufferUnderflowException("The buffer is already empty");
/*     */     }
/*     */     
/* 196 */     return this.m_elements[this.m_start];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove() {
/* 206 */     if (isEmpty()) {
/* 207 */       throw new BufferUnderflowException("The buffer is already empty");
/*     */     }
/*     */     
/* 210 */     Object element = this.m_elements[this.m_start];
/*     */     
/* 212 */     if (null != element) {
/* 213 */       this.m_elements[this.m_start++] = null;
/*     */       
/* 215 */       if (this.m_start >= this.maxElements) {
/* 216 */         this.m_start = 0;
/*     */       }
/*     */       
/* 219 */       this.m_full = false;
/*     */     } 
/*     */     
/* 222 */     return element;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int increment(int index) {
/* 232 */     index++;
/* 233 */     if (index >= this.maxElements) {
/* 234 */       index = 0;
/*     */     }
/* 236 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int decrement(int index) {
/* 246 */     index--;
/* 247 */     if (index < 0) {
/* 248 */       index = this.maxElements - 1;
/*     */     }
/* 250 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 259 */     return new Iterator(this) {
/*     */         private int index;
/*     */         private int lastReturnedIndex;
/*     */         private boolean isFirst;
/*     */         private final BoundedFifoBuffer this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 266 */           return (this.isFirst || this.index != this.this$0.m_end);
/*     */         }
/*     */ 
/*     */         
/*     */         public Object next() {
/* 271 */           if (!hasNext()) throw new NoSuchElementException(); 
/* 272 */           this.isFirst = false;
/* 273 */           this.lastReturnedIndex = this.index;
/* 274 */           this.index = this.this$0.increment(this.index);
/* 275 */           return this.this$0.m_elements[this.lastReturnedIndex];
/*     */         }
/*     */         
/*     */         public void remove() {
/* 279 */           if (this.lastReturnedIndex == -1) throw new IllegalStateException();
/*     */ 
/*     */           
/* 282 */           if (this.lastReturnedIndex == this.this$0.m_start) {
/* 283 */             this.this$0.remove();
/* 284 */             this.lastReturnedIndex = -1;
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/* 289 */           int i = this.lastReturnedIndex + 1;
/* 290 */           while (i != this.this$0.m_end) {
/* 291 */             if (i >= this.this$0.maxElements) {
/* 292 */               this.this$0.m_elements[i - 1] = this.this$0.m_elements[0];
/* 293 */               i = 0; continue;
/*     */             } 
/* 295 */             this.this$0.m_elements[i - 1] = this.this$0.m_elements[i];
/* 296 */             i++;
/*     */           } 
/*     */ 
/*     */           
/* 300 */           this.lastReturnedIndex = -1;
/* 301 */           this.this$0.m_end = this.this$0.decrement(this.this$0.m_end);
/* 302 */           this.this$0.m_elements[this.this$0.m_end] = null;
/* 303 */           this.this$0.m_full = false;
/* 304 */           this.index = this.this$0.decrement(this.index);
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/BoundedFifoBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */