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
/*     */ public class ArrayByteList
/*     */   extends RandomAccessByteList
/*     */   implements Serializable, ByteList
/*     */ {
/*     */   public ArrayByteList() {
/*  42 */     this(8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayByteList(int initialCapacity) {
/*  51 */     if (initialCapacity < 0) {
/*  52 */       throw new IllegalArgumentException("capacity " + initialCapacity);
/*     */     }
/*  54 */     this._data = new byte[initialCapacity];
/*  55 */     this._size = 0;
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
/*     */   public ArrayByteList(ByteCollection that) {
/*  68 */     this(that.size());
/*  69 */     addAll(that);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte get(int index) {
/*  76 */     checkRange(index);
/*  77 */     return this._data[index];
/*     */   }
/*     */   
/*     */   public int size() {
/*  81 */     return this._size;
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
/*     */   public byte removeElementAt(int index) {
/*  98 */     checkRange(index);
/*  99 */     incrModCount();
/* 100 */     byte oldval = this._data[index];
/* 101 */     int numtomove = this._size - index - 1;
/* 102 */     if (numtomove > 0) {
/* 103 */       System.arraycopy(this._data, index + 1, this._data, index, numtomove);
/*     */     }
/* 105 */     this._size--;
/* 106 */     return oldval;
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
/*     */   public byte set(int index, byte element) {
/* 123 */     checkRange(index);
/* 124 */     incrModCount();
/* 125 */     byte oldval = this._data[index];
/* 126 */     this._data[index] = element;
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
/*     */   public void add(int index, byte element) {
/* 146 */     checkRangeIncludingEndpoint(index);
/* 147 */     incrModCount();
/* 148 */     ensureCapacity(this._size + 1);
/* 149 */     int numtomove = this._size - index;
/* 150 */     System.arraycopy(this._data, index, this._data, index + 1, numtomove);
/* 151 */     this._data[index] = element;
/* 152 */     this._size++;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 156 */     incrModCount();
/* 157 */     this._size = 0;
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
/* 169 */     incrModCount();
/* 170 */     if (mincap > this._data.length) {
/* 171 */       int newcap = this._data.length * 3 / 2 + 1;
/* 172 */       byte[] olddata = this._data;
/* 173 */       this._data = new byte[(newcap < mincap) ? mincap : newcap];
/* 174 */       System.arraycopy(olddata, 0, this._data, 0, this._size);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void trimToSize() {
/* 183 */     incrModCount();
/* 184 */     if (this._size < this._data.length) {
/* 185 */       byte[] olddata = this._data;
/* 186 */       this._data = new byte[this._size];
/* 187 */       System.arraycopy(olddata, 0, this._data, 0, this._size);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeObject(ObjectOutputStream out) throws IOException {
/* 195 */     out.defaultWriteObject();
/* 196 */     out.writeInt(this._data.length);
/* 197 */     for (int i = 0; i < this._size; i++) {
/* 198 */       out.writeByte(this._data[i]);
/*     */     }
/*     */   }
/*     */   
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 203 */     in.defaultReadObject();
/* 204 */     this._data = new byte[in.readInt()];
/* 205 */     for (int i = 0; i < this._size; i++) {
/* 206 */       this._data[i] = in.readByte();
/*     */     }
/*     */   }
/*     */   
/*     */   private final void checkRange(int index) {
/* 211 */     if (index < 0 || index >= this._size) {
/* 212 */       throw new IndexOutOfBoundsException("Should be at least 0 and less than " + this._size + ", found " + index);
/*     */     }
/*     */   }
/*     */   
/*     */   private final void checkRangeIncludingEndpoint(int index) {
/* 217 */     if (index < 0 || index > this._size) {
/* 218 */       throw new IndexOutOfBoundsException("Should be at least 0 and at most " + this._size + ", found " + index);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 225 */   private transient byte[] _data = null;
/* 226 */   private int _size = 0;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/primitives/ArrayByteList.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */