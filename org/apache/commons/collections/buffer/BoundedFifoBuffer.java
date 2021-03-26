/*     */ package org.apache.commons.collections.buffer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections.BoundedCollection;
/*     */ import org.apache.commons.collections.Buffer;
/*     */ import org.apache.commons.collections.BufferOverflowException;
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
/*     */ public class BoundedFifoBuffer
/*     */   extends AbstractCollection
/*     */   implements Serializable, BoundedCollection, Buffer
/*     */ {
/*     */   private static final long serialVersionUID = 5603722811189451017L;
/*     */   private transient Object[] elements;
/*  71 */   private transient int start = 0;
/*  72 */   private transient int end = 0;
/*     */ 
/*     */   
/*     */   private transient boolean full = false;
/*     */   
/*     */   private final int maxElements;
/*     */ 
/*     */   
/*     */   public BoundedFifoBuffer() {
/*  81 */     this(32);
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
/*  92 */     if (size <= 0) {
/*  93 */       throw new IllegalArgumentException("The size must be greater than 0");
/*     */     }
/*  95 */     this.elements = new Object[size];
/*  96 */     this.maxElements = this.elements.length;
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
/* 108 */     this(coll.size());
/* 109 */     addAll(coll);
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
/* 120 */     out.defaultWriteObject();
/* 121 */     out.writeInt(size());
/* 122 */     for (Iterator it = iterator(); it.hasNext();) {
/* 123 */       out.writeObject(it.next());
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
/* 135 */     in.defaultReadObject();
/* 136 */     this.elements = new Object[this.maxElements];
/* 137 */     int size = in.readInt();
/* 138 */     for (int i = 0; i < size; i++) {
/* 139 */       this.elements[i] = in.readObject();
/*     */     }
/* 141 */     this.start = 0;
/* 142 */     this.end = size;
/* 143 */     this.full = (size == this.maxElements);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 153 */     int size = 0;
/*     */     
/* 155 */     if (this.end < this.start) {
/* 156 */       size = this.maxElements - this.start + this.end;
/* 157 */     } else if (this.end == this.start) {
/* 158 */       size = this.full ? this.maxElements : 0;
/*     */     } else {
/* 160 */       size = this.end - this.start;
/*     */     } 
/*     */     
/* 163 */     return size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 172 */     return (size() == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFull() {
/* 181 */     return (size() == this.maxElements);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int maxSize() {
/* 190 */     return this.maxElements;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 197 */     this.full = false;
/* 198 */     this.start = 0;
/* 199 */     this.end = 0;
/* 200 */     Arrays.fill(this.elements, (Object)null);
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
/* 212 */     if (null == element) {
/* 213 */       throw new NullPointerException("Attempted to add null object to buffer");
/*     */     }
/*     */     
/* 216 */     if (this.full) {
/* 217 */       throw new BufferOverflowException("The buffer cannot hold more than " + this.maxElements + " objects.");
/*     */     }
/*     */     
/* 220 */     this.elements[this.end++] = element;
/*     */     
/* 222 */     if (this.end >= this.maxElements) {
/* 223 */       this.end = 0;
/*     */     }
/*     */     
/* 226 */     if (this.end == this.start) {
/* 227 */       this.full = true;
/*     */     }
/*     */     
/* 230 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object get() {
/* 240 */     if (isEmpty()) {
/* 241 */       throw new BufferUnderflowException("The buffer is already empty");
/*     */     }
/*     */     
/* 244 */     return this.elements[this.start];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object remove() {
/* 254 */     if (isEmpty()) {
/* 255 */       throw new BufferUnderflowException("The buffer is already empty");
/*     */     }
/*     */     
/* 258 */     Object element = this.elements[this.start];
/*     */     
/* 260 */     if (null != element) {
/* 261 */       this.elements[this.start++] = null;
/*     */       
/* 263 */       if (this.start >= this.maxElements) {
/* 264 */         this.start = 0;
/*     */       }
/*     */       
/* 267 */       this.full = false;
/*     */     } 
/*     */     
/* 270 */     return element;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int increment(int index) {
/* 280 */     index++;
/* 281 */     if (index >= this.maxElements) {
/* 282 */       index = 0;
/*     */     }
/* 284 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int decrement(int index) {
/* 294 */     index--;
/* 295 */     if (index < 0) {
/* 296 */       index = this.maxElements - 1;
/*     */     }
/* 298 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator iterator() {
/* 307 */     return new Iterator(this) {
/*     */         private int index;
/*     */         private int lastReturnedIndex;
/*     */         private boolean isFirst;
/*     */         private final BoundedFifoBuffer this$0;
/*     */         
/*     */         public boolean hasNext() {
/* 314 */           return (this.isFirst || this.index != this.this$0.end);
/*     */         }
/*     */ 
/*     */         
/*     */         public Object next() {
/* 319 */           if (!hasNext()) {
/* 320 */             throw new NoSuchElementException();
/*     */           }
/* 322 */           this.isFirst = false;
/* 323 */           this.lastReturnedIndex = this.index;
/* 324 */           this.index = this.this$0.increment(this.index);
/* 325 */           return this.this$0.elements[this.lastReturnedIndex];
/*     */         }
/*     */         
/*     */         public void remove() {
/* 329 */           if (this.lastReturnedIndex == -1) {
/* 330 */             throw new IllegalStateException();
/*     */           }
/*     */ 
/*     */           
/* 334 */           if (this.lastReturnedIndex == this.this$0.start) {
/* 335 */             this.this$0.remove();
/* 336 */             this.lastReturnedIndex = -1;
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/* 341 */           int i = this.lastReturnedIndex + 1;
/* 342 */           while (i != this.this$0.end) {
/* 343 */             if (i >= this.this$0.maxElements) {
/* 344 */               this.this$0.elements[i - 1] = this.this$0.elements[0];
/* 345 */               i = 0; continue;
/*     */             } 
/* 347 */             this.this$0.elements[i - 1] = this.this$0.elements[i];
/* 348 */             i++;
/*     */           } 
/*     */ 
/*     */           
/* 352 */           this.lastReturnedIndex = -1;
/* 353 */           this.this$0.end = this.this$0.decrement(this.this$0.end);
/* 354 */           this.this$0.elements[this.this$0.end] = null;
/* 355 */           this.this$0.full = false;
/* 356 */           this.index = this.this$0.decrement(this.index);
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/buffer/BoundedFifoBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */