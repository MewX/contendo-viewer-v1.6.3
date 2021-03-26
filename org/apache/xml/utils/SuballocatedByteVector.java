/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SuballocatedByteVector
/*     */ {
/*     */   protected int m_blocksize;
/*  48 */   protected int m_numblocks = 32;
/*     */ 
/*     */   
/*     */   protected byte[][] m_map;
/*     */ 
/*     */   
/*  54 */   protected int m_firstFree = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected byte[] m_map0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SuballocatedByteVector() {
/*  65 */     this(2048);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SuballocatedByteVector(int blocksize) {
/*  75 */     this.m_blocksize = blocksize;
/*  76 */     this.m_map0 = new byte[blocksize];
/*  77 */     this.m_map = new byte[this.m_numblocks][];
/*  78 */     this.m_map[0] = this.m_map0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SuballocatedByteVector(int blocksize, int increaseSize) {
/*  89 */     this(blocksize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 100 */     return this.m_firstFree;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setSize(int sz) {
/* 110 */     if (this.m_firstFree < sz) {
/* 111 */       this.m_firstFree = sz;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addElement(byte value) {
/* 121 */     if (this.m_firstFree < this.m_blocksize) {
/* 122 */       this.m_map0[this.m_firstFree++] = value;
/*     */     } else {
/*     */       
/* 125 */       int index = this.m_firstFree / this.m_blocksize;
/* 126 */       int offset = this.m_firstFree % this.m_blocksize;
/* 127 */       this.m_firstFree++;
/*     */       
/* 129 */       if (index >= this.m_map.length) {
/*     */         
/* 131 */         int newsize = index + this.m_numblocks;
/* 132 */         byte[][] newMap = new byte[newsize][];
/* 133 */         System.arraycopy(this.m_map, 0, newMap, 0, this.m_map.length);
/* 134 */         this.m_map = newMap;
/*     */       } 
/* 136 */       byte[] block = this.m_map[index];
/* 137 */       if (null == block)
/* 138 */         block = this.m_map[index] = new byte[this.m_blocksize]; 
/* 139 */       block[offset] = value;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addElements(byte value, int numberOfElements) {
/* 150 */     if (this.m_firstFree + numberOfElements < this.m_blocksize) {
/* 151 */       for (int i = 0; i < numberOfElements; i++)
/*     */       {
/* 153 */         this.m_map0[this.m_firstFree++] = value;
/*     */       }
/*     */     } else {
/*     */       
/* 157 */       int index = this.m_firstFree / this.m_blocksize;
/* 158 */       int offset = this.m_firstFree % this.m_blocksize;
/* 159 */       this.m_firstFree += numberOfElements;
/* 160 */       while (numberOfElements > 0) {
/*     */         
/* 162 */         if (index >= this.m_map.length) {
/*     */           
/* 164 */           int newsize = index + this.m_numblocks;
/* 165 */           byte[][] newMap = new byte[newsize][];
/* 166 */           System.arraycopy(this.m_map, 0, newMap, 0, this.m_map.length);
/* 167 */           this.m_map = newMap;
/*     */         } 
/* 169 */         byte[] block = this.m_map[index];
/* 170 */         if (null == block)
/* 171 */           block = this.m_map[index] = new byte[this.m_blocksize]; 
/* 172 */         int copied = (this.m_blocksize - offset < numberOfElements) ? (this.m_blocksize - offset) : numberOfElements;
/*     */         
/* 174 */         numberOfElements -= copied;
/* 175 */         while (copied-- > 0) {
/* 176 */           block[offset++] = value;
/*     */         }
/* 178 */         index++; offset = 0;
/*     */       } 
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
/*     */   private void addElements(int numberOfElements) {
/* 191 */     int newlen = this.m_firstFree + numberOfElements;
/* 192 */     if (newlen > this.m_blocksize) {
/*     */       
/* 194 */       int index = this.m_firstFree % this.m_blocksize;
/* 195 */       int newindex = (this.m_firstFree + numberOfElements) % this.m_blocksize;
/* 196 */       for (int i = index + 1; i <= newindex; i++)
/* 197 */         this.m_map[i] = new byte[this.m_blocksize]; 
/*     */     } 
/* 199 */     this.m_firstFree = newlen;
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
/*     */   private void insertElementAt(byte value, int at) {
/* 215 */     if (at == this.m_firstFree) {
/* 216 */       addElement(value);
/* 217 */     } else if (at > this.m_firstFree) {
/*     */       
/* 219 */       int index = at / this.m_blocksize;
/* 220 */       if (index >= this.m_map.length) {
/*     */         
/* 222 */         int newsize = index + this.m_numblocks;
/* 223 */         byte[][] newMap = new byte[newsize][];
/* 224 */         System.arraycopy(this.m_map, 0, newMap, 0, this.m_map.length);
/* 225 */         this.m_map = newMap;
/*     */       } 
/* 227 */       byte[] block = this.m_map[index];
/* 228 */       if (null == block)
/* 229 */         block = this.m_map[index] = new byte[this.m_blocksize]; 
/* 230 */       int offset = at % this.m_blocksize;
/* 231 */       block[offset] = value;
/* 232 */       this.m_firstFree = offset + 1;
/*     */     }
/*     */     else {
/*     */       
/* 236 */       int index = at / this.m_blocksize;
/* 237 */       int maxindex = this.m_firstFree + 1 / this.m_blocksize;
/* 238 */       this.m_firstFree++;
/* 239 */       int offset = at % this.m_blocksize;
/*     */ 
/*     */ 
/*     */       
/* 243 */       while (index <= maxindex) {
/*     */         byte b;
/* 245 */         int copylen = this.m_blocksize - offset - 1;
/* 246 */         byte[] block = this.m_map[index];
/* 247 */         if (null == block) {
/*     */           
/* 249 */           b = 0;
/* 250 */           block = this.m_map[index] = new byte[this.m_blocksize];
/*     */         }
/*     */         else {
/*     */           
/* 254 */           b = block[this.m_blocksize - 1];
/* 255 */           System.arraycopy(block, offset, block, offset + 1, copylen);
/*     */         } 
/* 257 */         block[offset] = value;
/* 258 */         value = b;
/* 259 */         offset = 0;
/* 260 */         index++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAllElements() {
/* 270 */     this.m_firstFree = 0;
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
/*     */   private boolean removeElement(byte s) {
/* 286 */     int at = indexOf(s, 0);
/* 287 */     if (at < 0)
/* 288 */       return false; 
/* 289 */     removeElementAt(at);
/* 290 */     return true;
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
/*     */   private void removeElementAt(int at) {
/* 304 */     if (at < this.m_firstFree) {
/*     */       
/* 306 */       int index = at / this.m_blocksize;
/* 307 */       int maxindex = this.m_firstFree / this.m_blocksize;
/* 308 */       int offset = at % this.m_blocksize;
/*     */       
/* 310 */       while (index <= maxindex) {
/*     */         
/* 312 */         int copylen = this.m_blocksize - offset - 1;
/* 313 */         byte[] block = this.m_map[index];
/* 314 */         if (null == block) {
/* 315 */           block = this.m_map[index] = new byte[this.m_blocksize];
/*     */         } else {
/* 317 */           System.arraycopy(block, offset + 1, block, offset, copylen);
/* 318 */         }  if (index < maxindex) {
/*     */           
/* 320 */           byte[] next = this.m_map[index + 1];
/* 321 */           if (next != null) {
/* 322 */             block[this.m_blocksize - 1] = (next != null) ? next[0] : 0;
/*     */           }
/*     */         } else {
/* 325 */           block[this.m_blocksize - 1] = 0;
/* 326 */         }  offset = 0;
/* 327 */         index++;
/*     */       } 
/*     */     } 
/* 330 */     this.m_firstFree--;
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
/*     */   public void setElementAt(byte value, int at) {
/* 345 */     if (at < this.m_blocksize) {
/*     */       
/* 347 */       this.m_map0[at] = value;
/*     */       
/*     */       return;
/*     */     } 
/* 351 */     int index = at / this.m_blocksize;
/* 352 */     int offset = at % this.m_blocksize;
/*     */     
/* 354 */     if (index >= this.m_map.length) {
/*     */       
/* 356 */       int newsize = index + this.m_numblocks;
/* 357 */       byte[][] newMap = new byte[newsize][];
/* 358 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_map.length);
/* 359 */       this.m_map = newMap;
/*     */     } 
/*     */     
/* 362 */     byte[] block = this.m_map[index];
/* 363 */     if (null == block)
/* 364 */       block = this.m_map[index] = new byte[this.m_blocksize]; 
/* 365 */     block[offset] = value;
/*     */     
/* 367 */     if (at >= this.m_firstFree) {
/* 368 */       this.m_firstFree = at + 1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte elementAt(int i) {
/* 396 */     if (i < this.m_blocksize) {
/* 397 */       return this.m_map0[i];
/*     */     }
/* 399 */     return this.m_map[i / this.m_blocksize][i % this.m_blocksize];
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
/*     */   private boolean contains(byte s) {
/* 411 */     return (indexOf(s, 0) >= 0);
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
/*     */   public int indexOf(byte elem, int index) {
/* 427 */     if (index >= this.m_firstFree) {
/* 428 */       return -1;
/*     */     }
/* 430 */     int bindex = index / this.m_blocksize;
/* 431 */     int boffset = index % this.m_blocksize;
/* 432 */     int maxindex = this.m_firstFree / this.m_blocksize;
/*     */ 
/*     */     
/* 435 */     for (; bindex < maxindex; bindex++) {
/*     */       
/* 437 */       byte[] block = this.m_map[bindex];
/* 438 */       if (block != null)
/* 439 */         for (int i = boffset; i < this.m_blocksize; i++) {
/* 440 */           if (block[i] == elem)
/* 441 */             return i + bindex * this.m_blocksize; 
/* 442 */         }   boffset = 0;
/*     */     } 
/*     */     
/* 445 */     int maxoffset = this.m_firstFree % this.m_blocksize;
/* 446 */     byte[] arrayOfByte = this.m_map[maxindex];
/* 447 */     for (int offset = boffset; offset < maxoffset; offset++) {
/* 448 */       if (arrayOfByte[offset] == elem)
/* 449 */         return offset + maxindex * this.m_blocksize; 
/*     */     } 
/* 451 */     return -1;
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
/*     */   public int indexOf(byte elem) {
/* 466 */     return indexOf(elem, 0);
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
/*     */   private int lastIndexOf(byte elem) {
/* 481 */     int boffset = this.m_firstFree % this.m_blocksize;
/* 482 */     int index = this.m_firstFree / this.m_blocksize;
/* 483 */     for (; index >= 0; 
/* 484 */       index--) {
/*     */       
/* 486 */       byte[] block = this.m_map[index];
/* 487 */       if (block != null)
/* 488 */         for (int offset = boffset; offset >= 0; offset--) {
/* 489 */           if (block[offset] == elem)
/* 490 */             return offset + index * this.m_blocksize; 
/* 491 */         }   boffset = 0;
/*     */     } 
/* 493 */     return -1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/SuballocatedByteVector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */