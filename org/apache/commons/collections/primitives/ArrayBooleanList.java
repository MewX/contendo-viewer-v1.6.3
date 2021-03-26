/*     */ package org.apache.commons.collections.primitives;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArrayBooleanList
/*     */   extends RandomAccessBooleanList
/*     */   implements Serializable, BooleanList
/*     */ {
/*     */   public ArrayBooleanList() {
/*  41 */     this(8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayBooleanList(int initialCapacity) {
/*  50 */     if (initialCapacity < 0) {
/*  51 */       throw new IllegalArgumentException("capacity " + initialCapacity);
/*     */     }
/*  53 */     this._data = new boolean[initialCapacity];
/*  54 */     this._size = 0;
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
/*     */   public ArrayBooleanList(BooleanCollection that) {
/*  67 */     this(that.size());
/*  68 */     addAll(that);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean get(int index) {
/*  75 */     checkRange(index);
/*  76 */     return this._data[index];
/*     */   }
/*     */   
/*     */   public int size() {
/*  80 */     return this._size;
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
/*     */   public boolean removeElementAt(int index) {
/*  96 */     checkRange(index);
/*  97 */     incrModCount();
/*  98 */     boolean oldval = this._data[index];
/*  99 */     int numtomove = this._size - index - 1;
/* 100 */     if (numtomove > 0) {
/* 101 */       System.arraycopy(this._data, index + 1, this._data, index, numtomove);
/*     */     }
/* 103 */     this._size--;
/* 104 */     return oldval;
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
/*     */   public boolean set(int index, boolean element) {
/* 120 */     checkRange(index);
/* 121 */     incrModCount();
/* 122 */     boolean oldval = this._data[index];
/* 123 */     this._data[index] = element;
/* 124 */     return oldval;
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
/*     */   
/*     */   public void add(int index, boolean element) {
/* 143 */     checkRangeIncludingEndpoint(index);
/* 144 */     incrModCount();
/* 145 */     ensureCapacity(this._size + 1);
/* 146 */     int numtomove = this._size - index;
/* 147 */     System.arraycopy(this._data, index, this._data, index + 1, numtomove);
/* 148 */     this._data[index] = element;
/* 149 */     this._size++;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 153 */     incrModCount();
/* 154 */     this._size = 0;
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
/*     */   public void ensureCapacity(int mincap) {
/* 166 */     incrModCount();
/* 167 */     if (mincap > this._data.length) {
/* 168 */       int newcap = this._data.length * 3 / 2 + 1;
/* 169 */       boolean[] olddata = this._data;
/* 170 */       this._data = new boolean[(newcap < mincap) ? mincap : newcap];
/* 171 */       System.arraycopy(olddata, 0, this._data, 0, this._size);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void trimToSize() {
/* 180 */     incrModCount();
/* 181 */     if (this._size < this._data.length) {
/* 182 */       boolean[] olddata = this._data;
/* 183 */       this._data = new boolean[this._size];
/* 184 */       System.arraycopy(olddata, 0, this._data, 0, this._size);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 192 */     out.defaultWriteObject();
/* 193 */     out.writeInt(this._data.length);
/* 194 */     for (int i = 0; i < this._size; i++) {
/* 195 */       out.writeBoolean(this._data[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 200 */     in.defaultReadObject();
/* 201 */     this._data = new boolean[in.readInt()];
/* 202 */     for (int i = 0; i < this._size; i++) {
/* 203 */       this._data[i] = in.readBoolean();
/*     */     }
/*     */   }
/*     */   
/*     */   private final void checkRange(int index) {
/* 208 */     if (index < 0 || index >= this._size) {
/* 209 */       throw new IndexOutOfBoundsException("Should be at least 0 and less than " + this._size + ", found " + index);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final void checkRangeIncludingEndpoint(int index) {
/* 216 */     if (index < 0 || index > this._size) {
/* 217 */       throw new IndexOutOfBoundsException("Should be at least 0 and at most " + this._size + ", found " + index);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   private transient boolean[] _data = null;
/* 227 */   private int _size = 0;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/ArrayBooleanList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */