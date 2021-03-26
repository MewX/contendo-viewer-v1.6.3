/*     */ package org.apache.commons.collections;
/*     */ 
/*     */ import java.util.AbstractCollection;
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
/*     */ public class UnboundedFifoBuffer
/*     */   extends AbstractCollection
/*     */   implements Buffer
/*     */ {
/*     */   protected Object[] m_buffer;
/*     */   protected int m_head;
/*     */   protected int m_tail;
/*     */   
/*     */   public UnboundedFifoBuffer() {
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
/*     */   public UnboundedFifoBuffer(int initialSize) {
/*  79 */     if (initialSize <= 0) {
/*  80 */       throw new IllegalArgumentException("The size must be greater than 0");
/*     */     }
/*  82 */     this.m_buffer = new Object[initialSize + 1];
/*  83 */     this.m_head = 0;
/*  84 */     this.m_tail = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/*  93 */     int size = 0;
/*     */     
/*  95 */     if (this.m_tail < this.m_head) {
/*  96 */       size = this.m_buffer.length - this.m_head + this.m_tail;
/*     */     } else {
/*  98 */       size = this.m_tail - this.m_head;
/*     */     } 
/*     */     
/* 101 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 110 */     return (size() == 0);
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
/*     */   public boolean add(Object obj) {
/* 122 */     if (obj == null) {
/* 123 */       throw new NullPointerException("Attempted to add null object to buffer");
/*     */     }
/*     */     
/* 126 */     if (size() + 1 >= this.m_buffer.length) {
/* 127 */       Object[] tmp = new Object[(this.m_buffer.length - 1) * 2 + 1];
/*     */       
/* 129 */       int j = 0; int i;
/* 130 */       for (i = this.m_head; i != this.m_tail; ) {
/* 131 */         tmp[j] = this.m_buffer[i];
/* 132 */         this.m_buffer[i] = null;
/*     */         
/* 134 */         j++;
/* 135 */         i++;
/* 136 */         if (i == this.m_buffer.length) {
/* 137 */           i = 0;
/*     */         }
/*     */       } 
/*     */       
/* 141 */       this.m_buffer = tmp;
/* 142 */       this.m_head = 0;
/* 143 */       this.m_tail = j;
/*     */     } 
/*     */     
/* 146 */     this.m_buffer[this.m_tail] = obj;
/* 147 */     this.m_tail++;
/* 148 */     if (this.m_tail >= this.m_buffer.length) {
/* 149 */       this.m_tail = 0;
/*     */     }
/* 151 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get() {
/* 161 */     if (isEmpty()) {
/* 162 */       throw new BufferUnderflowException("The buffer is already empty");
/*     */     }
/*     */     
/* 165 */     return this.m_buffer[this.m_head];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove() {
/* 175 */     if (isEmpty()) {
/* 176 */       throw new BufferUnderflowException("The buffer is already empty");
/*     */     }
/*     */     
/* 179 */     Object element = this.m_buffer[this.m_head];
/*     */     
/* 181 */     if (null != element) {
/* 182 */       this.m_buffer[this.m_head] = null;
/*     */       
/* 184 */       this.m_head++;
/* 185 */       if (this.m_head >= this.m_buffer.length) {
/* 186 */         this.m_head = 0;
/*     */       }
/*     */     } 
/*     */     
/* 190 */     return element;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int increment(int index) {
/* 200 */     index++;
/* 201 */     if (index >= this.m_buffer.length) {
/* 202 */       index = 0;
/*     */     }
/* 204 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int decrement(int index) {
/* 214 */     index--;
/* 215 */     if (index < 0) {
/* 216 */       index = this.m_buffer.length - 1;
/*     */     }
/* 218 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 227 */     return new Iterator(this) {
/*     */         private int index;
/*     */         private int lastReturnedIndex;
/*     */         private final UnboundedFifoBuffer this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 233 */           return (this.index != this.this$0.m_tail);
/*     */         }
/*     */ 
/*     */         
/*     */         public Object next() {
/* 238 */           if (!hasNext())
/* 239 */             throw new NoSuchElementException(); 
/* 240 */           this.lastReturnedIndex = this.index;
/* 241 */           this.index = this.this$0.increment(this.index);
/* 242 */           return this.this$0.m_buffer[this.lastReturnedIndex];
/*     */         }
/*     */         
/*     */         public void remove() {
/* 246 */           if (this.lastReturnedIndex == -1) {
/* 247 */             throw new IllegalStateException();
/*     */           }
/*     */           
/* 250 */           if (this.lastReturnedIndex == this.this$0.m_head) {
/* 251 */             this.this$0.remove();
/* 252 */             this.lastReturnedIndex = -1;
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/* 257 */           int i = this.lastReturnedIndex + 1;
/* 258 */           while (i != this.this$0.m_tail) {
/* 259 */             if (i >= this.this$0.m_buffer.length) {
/* 260 */               this.this$0.m_buffer[i - 1] = this.this$0.m_buffer[0];
/* 261 */               i = 0; continue;
/*     */             } 
/* 263 */             this.this$0.m_buffer[i - 1] = this.this$0.m_buffer[i];
/* 264 */             i++;
/*     */           } 
/*     */ 
/*     */           
/* 268 */           this.lastReturnedIndex = -1;
/* 269 */           this.this$0.m_tail = this.this$0.decrement(this.this$0.m_tail);
/* 270 */           this.this$0.m_buffer[this.this$0.m_tail] = null;
/* 271 */           this.index = this.this$0.decrement(this.index);
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/UnboundedFifoBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */