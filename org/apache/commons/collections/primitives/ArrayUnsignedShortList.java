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
/*     */ public class ArrayUnsignedShortList
/*     */   extends RandomAccessIntList
/*     */   implements Serializable, IntList
/*     */ {
/*     */   public static final int MAX_VALUE = 65535;
/*     */   public static final int MIN_VALUE = 0;
/*     */   
/*     */   public ArrayUnsignedShortList() {
/*  51 */     this(8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayUnsignedShortList(int initialCapacity) {
/*  60 */     if (initialCapacity < 0) {
/*  61 */       throw new IllegalArgumentException("capacity " + initialCapacity);
/*     */     }
/*  63 */     this._data = new short[initialCapacity];
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
/*     */   public ArrayUnsignedShortList(IntCollection that) {
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
/*     */   public int get(int index) {
/*  95 */     checkRange(index);
/*  96 */     return toInt(this._data[index]);
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
/*     */   public int removeElementAt(int index) {
/* 119 */     checkRange(index);
/* 120 */     incrModCount();
/* 121 */     int oldval = toInt(this._data[index]);
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
/*     */   public int set(int index, int element) {
/* 146 */     assertValidUnsignedShort(element);
/* 147 */     checkRange(index);
/* 148 */     incrModCount();
/* 149 */     int oldval = toInt(this._data[index]);
/* 150 */     this._data[index] = fromInt(element);
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
/*     */   public void add(int index, int element) {
/* 172 */     assertValidUnsignedShort(element);
/* 173 */     checkRangeIncludingEndpoint(index);
/* 174 */     incrModCount();
/* 175 */     ensureCapacity(this._size + 1);
/* 176 */     int numtomove = this._size - index;
/* 177 */     System.arraycopy(this._data, index, this._data, index + 1, numtomove);
/* 178 */     this._data[index] = fromInt(element);
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
/* 199 */       short[] olddata = this._data;
/* 200 */       this._data = new short[(newcap < mincap) ? mincap : newcap];
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
/* 212 */       short[] olddata = this._data;
/* 213 */       this._data = new short[this._size];
/* 214 */       System.arraycopy(olddata, 0, this._data, 0, this._size);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int toInt(short value) {
/* 222 */     return value & 0xFFFF;
/*     */   }
/*     */   
/*     */   private final short fromInt(int value) {
/* 226 */     return (short)(value & 0xFFFF);
/*     */   }
/*     */   
/*     */   private final void assertValidUnsignedShort(int value) throws IllegalArgumentException {
/* 230 */     if (value > 65535) {
/* 231 */       throw new IllegalArgumentException(value + " > " + Character.MAX_VALUE);
/*     */     }
/* 233 */     if (value < 0) {
/* 234 */       throw new IllegalArgumentException(value + " < " + Character.MIN_VALUE);
/*     */     }
/*     */   }
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 239 */     out.defaultWriteObject();
/* 240 */     out.writeInt(this._data.length);
/* 241 */     for (int i = 0; i < this._size; i++) {
/* 242 */       out.writeShort(this._data[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 247 */     in.defaultReadObject();
/* 248 */     this._data = new short[in.readInt()];
/* 249 */     for (int i = 0; i < this._size; i++) {
/* 250 */       this._data[i] = in.readShort();
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
/* 276 */   private transient short[] _data = null;
/* 277 */   private int _size = 0;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/ArrayUnsignedShortList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */