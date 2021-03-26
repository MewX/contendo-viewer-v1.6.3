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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArrayUnsignedByteList
/*     */   extends RandomAccessShortList
/*     */   implements Serializable, ShortList
/*     */ {
/*     */   public ArrayUnsignedByteList() {
/*  51 */     this(8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayUnsignedByteList(int initialCapacity) {
/*  60 */     if (initialCapacity < 0) {
/*  61 */       throw new IllegalArgumentException("capacity " + initialCapacity);
/*     */     }
/*  63 */     this._data = new byte[initialCapacity];
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
/*     */   public ArrayUnsignedByteList(ShortCollection that) {
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
/*     */   public short get(int index) {
/*  95 */     checkRange(index);
/*  96 */     return toShort(this._data[index]);
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
/*     */   public short removeElementAt(int index) {
/* 119 */     checkRange(index);
/* 120 */     incrModCount();
/* 121 */     short oldval = toShort(this._data[index]);
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
/*     */   public short set(int index, short element) {
/* 146 */     assertValidUnsignedByte(element);
/* 147 */     checkRange(index);
/* 148 */     incrModCount();
/* 149 */     short oldval = toShort(this._data[index]);
/* 150 */     this._data[index] = fromShort(element);
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
/*     */   public void add(int index, short element) {
/* 172 */     assertValidUnsignedByte(element);
/* 173 */     checkRangeIncludingEndpoint(index);
/* 174 */     incrModCount();
/* 175 */     ensureCapacity(this._size + 1);
/* 176 */     int numtomove = this._size - index;
/* 177 */     System.arraycopy(this._data, index, this._data, index + 1, numtomove);
/* 178 */     this._data[index] = fromShort(element);
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
/* 199 */       byte[] olddata = this._data;
/* 200 */       this._data = new byte[(newcap < mincap) ? mincap : newcap];
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
/* 212 */       byte[] olddata = this._data;
/* 213 */       this._data = new byte[this._size];
/* 214 */       System.arraycopy(olddata, 0, this._data, 0, this._size);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final short toShort(byte value) {
/* 222 */     return (short)((short)value & 0xFF);
/*     */   }
/*     */   
/*     */   private final byte fromShort(short value) {
/* 226 */     return (byte)(value & 0xFF);
/*     */   }
/*     */   
/*     */   private final void assertValidUnsignedByte(short value) throws IllegalArgumentException {
/* 230 */     if (value > 255) {
/* 231 */       throw new IllegalArgumentException(value + " > " + 'ÿ');
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
/* 242 */       out.writeByte(this._data[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 247 */     in.defaultReadObject();
/* 248 */     this._data = new byte[in.readInt()];
/* 249 */     for (int i = 0; i < this._size; i++) {
/* 250 */       this._data[i] = in.readByte();
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
/* 266 */   private transient byte[] _data = null;
/* 267 */   private int _size = 0;
/*     */   public static final short MAX_VALUE = 255;
/*     */   public static final short MIN_VALUE = 0;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/ArrayUnsignedByteList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */