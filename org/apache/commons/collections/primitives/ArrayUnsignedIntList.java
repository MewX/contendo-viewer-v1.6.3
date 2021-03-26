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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArrayUnsignedIntList
/*     */   extends RandomAccessLongList
/*     */   implements Serializable, LongList
/*     */ {
/*     */   public static final long MAX_VALUE = 4294967295L;
/*     */   public static final long MIN_VALUE = 0L;
/*     */   
/*     */   public ArrayUnsignedIntList() {
/*  51 */     this(8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayUnsignedIntList(int initialCapacity) {
/*  60 */     if (initialCapacity < 0) {
/*  61 */       throw new IllegalArgumentException("capacity " + initialCapacity);
/*     */     }
/*  63 */     this._data = new int[initialCapacity];
/*  64 */     this._size = 0;
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
/*     */   public ArrayUnsignedIntList(LongCollection that) {
/*  77 */     this(that.size());
/*  78 */     addAll(that);
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
/*     */   public long get(int index) {
/*  95 */     checkRange(index);
/*  96 */     return toLong(this._data[index]);
/*     */   }
/*     */   
/*     */   public int size() {
/* 100 */     return this._size;
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
/*     */   public long removeElementAt(int index) {
/* 119 */     checkRange(index);
/* 120 */     incrModCount();
/* 121 */     long oldval = toLong(this._data[index]);
/* 122 */     int numtomove = this._size - index - 1;
/* 123 */     if (numtomove > 0) {
/* 124 */       System.arraycopy(this._data, index + 1, this._data, index, numtomove);
/*     */     }
/* 126 */     this._size--;
/* 127 */     return oldval;
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
/*     */   public long set(int index, long element) {
/* 146 */     assertValidUnsignedInt(element);
/* 147 */     checkRange(index);
/* 148 */     incrModCount();
/* 149 */     long oldval = toLong(this._data[index]);
/* 150 */     this._data[index] = fromLong(element);
/* 151 */     return oldval;
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
/*     */ 
/*     */   
/*     */   public void add(int index, long element) {
/* 172 */     assertValidUnsignedInt(element);
/* 173 */     checkRangeIncludingEndpoint(index);
/* 174 */     incrModCount();
/* 175 */     ensureCapacity(this._size + 1);
/* 176 */     int numtomove = this._size - index;
/* 177 */     System.arraycopy(this._data, index, this._data, index + 1, numtomove);
/* 178 */     this._data[index] = fromLong(element);
/* 179 */     this._size++;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 183 */     incrModCount();
/* 184 */     this._size = 0;
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
/* 196 */     incrModCount();
/* 197 */     if (mincap > this._data.length) {
/* 198 */       int newcap = this._data.length * 3 / 2 + 1;
/* 199 */       int[] olddata = this._data;
/* 200 */       this._data = new int[(newcap < mincap) ? mincap : newcap];
/* 201 */       System.arraycopy(olddata, 0, this._data, 0, this._size);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void trimToSize() {
/* 210 */     incrModCount();
/* 211 */     if (this._size < this._data.length) {
/* 212 */       int[] olddata = this._data;
/* 213 */       this._data = new int[this._size];
/* 214 */       System.arraycopy(olddata, 0, this._data, 0, this._size);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final long toLong(int value) {
/* 222 */     return value & 0xFFFFFFFFL;
/*     */   }
/*     */   
/*     */   private final int fromLong(long value) {
/* 226 */     return (int)(value & 0xFFFFFFFFL);
/*     */   }
/*     */   
/*     */   private final void assertValidUnsignedInt(long value) throws IllegalArgumentException {
/* 230 */     if (value > 4294967295L) {
/* 231 */       throw new IllegalArgumentException(value + " > " + 4294967295L);
/*     */     }
/* 233 */     if (value < 0L) {
/* 234 */       throw new IllegalArgumentException(value + " < " + 0L);
/*     */     }
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 239 */     out.defaultWriteObject();
/* 240 */     out.writeInt(this._data.length);
/* 241 */     for (int i = 0; i < this._size; i++) {
/* 242 */       out.writeInt(this._data[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 247 */     in.defaultReadObject();
/* 248 */     this._data = new int[in.readInt()];
/* 249 */     for (int i = 0; i < this._size; i++) {
/* 250 */       this._data[i] = in.readInt();
/*     */     }
/*     */   }
/*     */   
/*     */   private final void checkRange(int index) {
/* 255 */     if (index < 0 || index >= this._size) {
/* 256 */       throw new IndexOutOfBoundsException("Should be at least 0 and less than " + this._size + ", found " + index);
/*     */     }
/*     */   }
/*     */   
/*     */   private final void checkRangeIncludingEndpoint(int index) {
/* 261 */     if (index < 0 || index > this._size) {
/* 262 */       throw new IndexOutOfBoundsException("Should be at least 0 and at most " + this._size + ", found " + index);
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
/*     */ 
/*     */   
/* 279 */   private transient int[] _data = null;
/* 280 */   private int _size = 0;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/ArrayUnsignedIntList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */