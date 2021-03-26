/*     */ package org.apache.commons.collections.buffer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractCollection;
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
/*     */ public class UnboundedFifoBuffer
/*     */   extends AbstractCollection
/*     */   implements Serializable, Buffer
/*     */ {
/*     */   private static final long serialVersionUID = -3482960336579541419L;
/*     */   protected transient Object[] buffer;
/*     */   protected transient int head;
/*     */   protected transient int tail;
/*     */   
/*     */   public UnboundedFifoBuffer() {
/*  82 */     this(32);
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
/*  93 */     if (initialSize <= 0) {
/*  94 */       throw new IllegalArgumentException("The size must be greater than 0");
/*     */     }
/*  96 */     this.buffer = new Object[initialSize + 1];
/*  97 */     this.head = 0;
/*  98 */     this.tail = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 109 */     out.defaultWriteObject();
/* 110 */     out.writeInt(size());
/* 111 */     for (Iterator it = iterator(); it.hasNext();) {
/* 112 */       out.writeObject(it.next());
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
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 124 */     in.defaultReadObject();
/* 125 */     int size = in.readInt();
/* 126 */     this.buffer = new Object[size];
/* 127 */     for (int i = 0; i < size; i++) {
/* 128 */       this.buffer[i] = in.readObject();
/*     */     }
/* 130 */     this.head = 0;
/* 131 */     this.tail = size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 141 */     int size = 0;
/*     */     
/* 143 */     if (this.tail < this.head) {
/* 144 */       size = this.buffer.length - this.head + this.tail;
/*     */     } else {
/* 146 */       size = this.tail - this.head;
/*     */     } 
/*     */     
/* 149 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 158 */     return (size() == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean add(Object obj) {
/* 169 */     if (obj == null) {
/* 170 */       throw new NullPointerException("Attempted to add null object to buffer");
/*     */     }
/*     */     
/* 173 */     if (size() + 1 >= this.buffer.length) {
/* 174 */       Object[] tmp = new Object[(this.buffer.length - 1) * 2 + 1];
/*     */       
/* 176 */       int j = 0; int i;
/* 177 */       for (i = this.head; i != this.tail; ) {
/* 178 */         tmp[j] = this.buffer[i];
/* 179 */         this.buffer[i] = null;
/*     */         
/* 181 */         j++;
/* 182 */         i++;
/* 183 */         if (i == this.buffer.length) {
/* 184 */           i = 0;
/*     */         }
/*     */       } 
/*     */       
/* 188 */       this.buffer = tmp;
/* 189 */       this.head = 0;
/* 190 */       this.tail = j;
/*     */     } 
/*     */     
/* 193 */     this.buffer[this.tail] = obj;
/* 194 */     this.tail++;
/* 195 */     if (this.tail >= this.buffer.length) {
/* 196 */       this.tail = 0;
/*     */     }
/* 198 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get() {
/* 208 */     if (isEmpty()) {
/* 209 */       throw new BufferUnderflowException("The buffer is already empty");
/*     */     }
/*     */     
/* 212 */     return this.buffer[this.head];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove() {
/* 222 */     if (isEmpty()) {
/* 223 */       throw new BufferUnderflowException("The buffer is already empty");
/*     */     }
/*     */     
/* 226 */     Object element = this.buffer[this.head];
/*     */     
/* 228 */     if (null != element) {
/* 229 */       this.buffer[this.head] = null;
/*     */       
/* 231 */       this.head++;
/* 232 */       if (this.head >= this.buffer.length) {
/* 233 */         this.head = 0;
/*     */       }
/*     */     } 
/*     */     
/* 237 */     return element;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int increment(int index) {
/* 247 */     index++;
/* 248 */     if (index >= this.buffer.length) {
/* 249 */       index = 0;
/*     */     }
/* 251 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int decrement(int index) {
/* 261 */     index--;
/* 262 */     if (index < 0) {
/* 263 */       index = this.buffer.length - 1;
/*     */     }
/* 265 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 274 */     return new Iterator(this) {
/*     */         private int index;
/*     */         private int lastReturnedIndex;
/*     */         private final UnboundedFifoBuffer this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 280 */           return (this.index != this.this$0.tail);
/*     */         }
/*     */ 
/*     */         
/*     */         public Object next() {
/* 285 */           if (!hasNext()) {
/* 286 */             throw new NoSuchElementException();
/*     */           }
/* 288 */           this.lastReturnedIndex = this.index;
/* 289 */           this.index = this.this$0.increment(this.index);
/* 290 */           return this.this$0.buffer[this.lastReturnedIndex];
/*     */         }
/*     */         
/*     */         public void remove() {
/* 294 */           if (this.lastReturnedIndex == -1) {
/* 295 */             throw new IllegalStateException();
/*     */           }
/*     */ 
/*     */           
/* 299 */           if (this.lastReturnedIndex == this.this$0.head) {
/* 300 */             this.this$0.remove();
/* 301 */             this.lastReturnedIndex = -1;
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/* 306 */           int i = this.lastReturnedIndex + 1;
/* 307 */           while (i != this.this$0.tail) {
/* 308 */             if (i >= this.this$0.buffer.length) {
/* 309 */               this.this$0.buffer[i - 1] = this.this$0.buffer[0];
/* 310 */               i = 0; continue;
/*     */             } 
/* 312 */             this.this$0.buffer[i - 1] = this.this$0.buffer[i];
/* 313 */             i++;
/*     */           } 
/*     */ 
/*     */           
/* 317 */           this.lastReturnedIndex = -1;
/* 318 */           this.this$0.tail = this.this$0.decrement(this.this$0.tail);
/* 319 */           this.this$0.buffer[this.this$0.tail] = null;
/* 320 */           this.index = this.this$0.decrement(this.index);
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/buffer/UnboundedFifoBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */