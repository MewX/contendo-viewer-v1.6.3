/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import java.io.Externalizable;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInput;
/*     */ import java.io.ObjectOutput;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BitArray
/*     */   implements Externalizable
/*     */ {
/*     */   private int[] _bits;
/*     */   private int _bitSize;
/*     */   private int _intSize;
/*     */   private int _mask;
/*  42 */   private static final int[] _masks = new int[] { Integer.MIN_VALUE, 1073741824, 536870912, 268435456, 134217728, 67108864, 33554432, 16777216, 8388608, 4194304, 2097152, 1048576, 524288, 262144, 131072, 65536, 32768, 16384, 8192, 4096, 2048, 1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final boolean DEBUG_ASSERTIONS = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BitArray() {
/*  58 */     this(32);
/*     */   }
/*     */   
/*     */   public BitArray(int size) {
/*  62 */     if (size < 32) size = 32; 
/*  63 */     this._bitSize = size;
/*  64 */     this._intSize = (this._bitSize >>> 5) + 1;
/*  65 */     this._bits = new int[this._intSize + 1];
/*     */   }
/*     */   
/*     */   public BitArray(int size, int[] bits) {
/*  69 */     if (size < 32) size = 32; 
/*  70 */     this._bitSize = size;
/*  71 */     this._intSize = (this._bitSize >>> 5) + 1;
/*  72 */     this._bits = bits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMask(int mask) {
/*  80 */     this._mask = mask;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMask() {
/*  87 */     return this._mask;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int size() {
/*  94 */     return this._bitSize;
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
/*     */   public final boolean getBit(int bit) {
/* 108 */     return ((this._bits[bit >>> 5] & _masks[bit % 32]) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getNextBit(int startBit) {
/* 115 */     for (int i = startBit >>> 5; i <= this._intSize; i++) {
/* 116 */       int bits = this._bits[i];
/* 117 */       if (bits != 0) {
/* 118 */         for (int b = startBit % 32; b < 32; b++) {
/* 119 */           if ((bits & _masks[b]) != 0) {
/* 120 */             return (i << 5) + b;
/*     */           }
/*     */         } 
/*     */       }
/* 124 */       startBit = 0;
/*     */     } 
/* 126 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 135 */   private int _pos = Integer.MAX_VALUE;
/* 136 */   private int _node = 0;
/* 137 */   private int _int = 0;
/* 138 */   private int _bit = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getBitNumber(int pos) {
/* 143 */     if (pos == this._pos) return this._node;
/*     */ 
/*     */ 
/*     */     
/* 147 */     if (pos < this._pos) {
/* 148 */       this._int = this._bit = this._pos = 0;
/*     */     }
/*     */ 
/*     */     
/* 152 */     for (; this._int <= this._intSize; this._int++) {
/* 153 */       int bits = this._bits[this._int];
/* 154 */       if (bits != 0) {
/* 155 */         for (; this._bit < 32; this._bit++) {
/* 156 */           if ((bits & _masks[this._bit]) != 0 && 
/* 157 */             ++this._pos == pos) {
/* 158 */             this._node = (this._int << 5) + this._bit - 1;
/* 159 */             return this._node;
/*     */           } 
/*     */         } 
/*     */         
/* 163 */         this._bit = 0;
/*     */       } 
/*     */     } 
/* 166 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int[] data() {
/* 173 */     return this._bits;
/*     */   }
/*     */   
/* 176 */   int _first = Integer.MAX_VALUE;
/* 177 */   int _last = Integer.MIN_VALUE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setBit(int bit) {
/* 190 */     if (bit >= this._bitSize)
/* 191 */       return;  int i = bit >>> 5;
/* 192 */     if (i < this._first) this._first = i; 
/* 193 */     if (i > this._last) this._last = i; 
/* 194 */     this._bits[i] = this._bits[i] | _masks[bit % 32];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final BitArray merge(BitArray other) {
/* 203 */     if (this._last == -1) {
/* 204 */       this._bits = other._bits;
/*     */     
/*     */     }
/* 207 */     else if (other._last != -1) {
/* 208 */       int start = (this._first < other._first) ? this._first : other._first;
/* 209 */       int stop = (this._last > other._last) ? this._last : other._last;
/*     */ 
/*     */       
/* 212 */       if (other._intSize > this._intSize) {
/* 213 */         if (stop > this._intSize) stop = this._intSize; 
/* 214 */         for (int i = start; i <= stop; i++)
/* 215 */           other._bits[i] = other._bits[i] | this._bits[i]; 
/* 216 */         this._bits = other._bits;
/*     */       }
/*     */       else {
/*     */         
/* 220 */         if (stop > other._intSize) stop = other._intSize; 
/* 221 */         for (int i = start; i <= stop; i++)
/* 222 */           this._bits[i] = this._bits[i] | other._bits[i]; 
/*     */       } 
/*     */     } 
/* 225 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void resize(int newSize) {
/* 232 */     if (newSize > this._bitSize) {
/* 233 */       this._intSize = (newSize >>> 5) + 1;
/* 234 */       int[] newBits = new int[this._intSize + 1];
/* 235 */       System.arraycopy(this._bits, 0, newBits, 0, (this._bitSize >>> 5) + 1);
/* 236 */       this._bits = newBits;
/* 237 */       this._bitSize = newSize;
/*     */     } 
/*     */   }
/*     */   
/*     */   public BitArray cloneArray() {
/* 242 */     return new BitArray(this._intSize, this._bits);
/*     */   }
/*     */   
/*     */   public void writeExternal(ObjectOutput out) throws IOException {
/* 246 */     out.writeInt(this._bitSize);
/* 247 */     out.writeInt(this._mask);
/* 248 */     out.writeObject(this._bits);
/* 249 */     out.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
/* 257 */     this._bitSize = in.readInt();
/* 258 */     this._intSize = (this._bitSize >>> 5) + 1;
/* 259 */     this._mask = in.readInt();
/* 260 */     this._bits = (int[])in.readObject();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/BitArray.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */