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
/*     */ 
/*     */ 
/*     */ public class SuballocatedIntVector
/*     */ {
/*     */   protected int m_blocksize;
/*     */   protected int m_SHIFT;
/*     */   protected int m_MASK;
/*     */   protected static final int NUMBLOCKS_DEFAULT = 32;
/*  53 */   protected int m_numblocks = 32;
/*     */ 
/*     */   
/*     */   protected int[][] m_map;
/*     */ 
/*     */   
/*  59 */   protected int m_firstFree = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int[] m_map0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int[] m_buildCache;
/*     */ 
/*     */ 
/*     */   
/*     */   protected int m_buildCacheStartIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SuballocatedIntVector() {
/*  79 */     this(2048);
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
/*     */   public SuballocatedIntVector(int blocksize, int numblocks) {
/*  93 */     for (this.m_SHIFT = 0; 0 != (blocksize >>>= 1); this.m_SHIFT++);
/*     */     
/*  95 */     this.m_blocksize = 1 << this.m_SHIFT;
/*  96 */     this.m_MASK = this.m_blocksize - 1;
/*  97 */     this.m_numblocks = numblocks;
/*     */     
/*  99 */     this.m_map0 = new int[this.m_blocksize];
/* 100 */     this.m_map = new int[numblocks][];
/* 101 */     this.m_map[0] = this.m_map0;
/* 102 */     this.m_buildCache = this.m_map0;
/* 103 */     this.m_buildCacheStartIndex = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SuballocatedIntVector(int blocksize) {
/* 113 */     this(blocksize, 32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 123 */     return this.m_firstFree;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(int sz) {
/* 134 */     if (this.m_firstFree > sz) {
/* 135 */       this.m_firstFree = sz;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addElement(int value) {
/* 145 */     int indexRelativeToCache = this.m_firstFree - this.m_buildCacheStartIndex;
/*     */ 
/*     */     
/* 148 */     if (indexRelativeToCache >= 0 && indexRelativeToCache < this.m_blocksize) {
/* 149 */       this.m_buildCache[indexRelativeToCache] = value;
/* 150 */       this.m_firstFree++;
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/* 159 */       int index = this.m_firstFree >>> this.m_SHIFT;
/* 160 */       int offset = this.m_firstFree & this.m_MASK;
/*     */       
/* 162 */       if (index >= this.m_map.length) {
/*     */         
/* 164 */         int newsize = index + this.m_numblocks;
/* 165 */         int[][] newMap = new int[newsize][];
/* 166 */         System.arraycopy(this.m_map, 0, newMap, 0, this.m_map.length);
/* 167 */         this.m_map = newMap;
/*     */       } 
/* 169 */       int[] block = this.m_map[index];
/* 170 */       if (null == block)
/* 171 */         block = this.m_map[index] = new int[this.m_blocksize]; 
/* 172 */       block[offset] = value;
/*     */ 
/*     */ 
/*     */       
/* 176 */       this.m_buildCache = block;
/* 177 */       this.m_buildCacheStartIndex = this.m_firstFree - offset;
/*     */       
/* 179 */       this.m_firstFree++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addElements(int value, int numberOfElements) {
/* 190 */     if (this.m_firstFree + numberOfElements < this.m_blocksize) {
/* 191 */       for (int i = 0; i < numberOfElements; i++)
/*     */       {
/* 193 */         this.m_map0[this.m_firstFree++] = value;
/*     */       }
/*     */     } else {
/*     */       
/* 197 */       int index = this.m_firstFree >>> this.m_SHIFT;
/* 198 */       int offset = this.m_firstFree & this.m_MASK;
/* 199 */       this.m_firstFree += numberOfElements;
/* 200 */       while (numberOfElements > 0) {
/*     */         
/* 202 */         if (index >= this.m_map.length) {
/*     */           
/* 204 */           int newsize = index + this.m_numblocks;
/* 205 */           int[][] newMap = new int[newsize][];
/* 206 */           System.arraycopy(this.m_map, 0, newMap, 0, this.m_map.length);
/* 207 */           this.m_map = newMap;
/*     */         } 
/* 209 */         int[] block = this.m_map[index];
/* 210 */         if (null == block)
/* 211 */           block = this.m_map[index] = new int[this.m_blocksize]; 
/* 212 */         int copied = (this.m_blocksize - offset < numberOfElements) ? (this.m_blocksize - offset) : numberOfElements;
/*     */         
/* 214 */         numberOfElements -= copied;
/* 215 */         while (copied-- > 0) {
/* 216 */           block[offset++] = value;
/*     */         }
/* 218 */         index++; offset = 0;
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
/* 231 */     int newlen = this.m_firstFree + numberOfElements;
/* 232 */     if (newlen > this.m_blocksize) {
/*     */       
/* 234 */       int index = this.m_firstFree >>> this.m_SHIFT;
/* 235 */       int newindex = this.m_firstFree + numberOfElements >>> this.m_SHIFT;
/* 236 */       for (int i = index + 1; i <= newindex; i++)
/* 237 */         this.m_map[i] = new int[this.m_blocksize]; 
/*     */     } 
/* 239 */     this.m_firstFree = newlen;
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
/*     */   private void insertElementAt(int value, int at) {
/* 255 */     if (at == this.m_firstFree) {
/* 256 */       addElement(value);
/* 257 */     } else if (at > this.m_firstFree) {
/*     */       
/* 259 */       int index = at >>> this.m_SHIFT;
/* 260 */       if (index >= this.m_map.length) {
/*     */         
/* 262 */         int newsize = index + this.m_numblocks;
/* 263 */         int[][] newMap = new int[newsize][];
/* 264 */         System.arraycopy(this.m_map, 0, newMap, 0, this.m_map.length);
/* 265 */         this.m_map = newMap;
/*     */       } 
/* 267 */       int[] block = this.m_map[index];
/* 268 */       if (null == block)
/* 269 */         block = this.m_map[index] = new int[this.m_blocksize]; 
/* 270 */       int offset = at & this.m_MASK;
/* 271 */       block[offset] = value;
/* 272 */       this.m_firstFree = offset + 1;
/*     */     }
/*     */     else {
/*     */       
/* 276 */       int index = at >>> this.m_SHIFT;
/* 277 */       int maxindex = this.m_firstFree >>> this.m_SHIFT;
/* 278 */       this.m_firstFree++;
/* 279 */       int offset = at & this.m_MASK;
/*     */ 
/*     */ 
/*     */       
/* 283 */       while (index <= maxindex) {
/*     */         
/* 285 */         int i, copylen = this.m_blocksize - offset - 1;
/* 286 */         int[] block = this.m_map[index];
/* 287 */         if (null == block) {
/*     */           
/* 289 */           i = 0;
/* 290 */           block = this.m_map[index] = new int[this.m_blocksize];
/*     */         }
/*     */         else {
/*     */           
/* 294 */           i = block[this.m_blocksize - 1];
/* 295 */           System.arraycopy(block, offset, block, offset + 1, copylen);
/*     */         } 
/* 297 */         block[offset] = value;
/* 298 */         value = i;
/* 299 */         offset = 0;
/* 300 */         index++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAllElements() {
/* 310 */     this.m_firstFree = 0;
/* 311 */     this.m_buildCache = this.m_map0;
/* 312 */     this.m_buildCacheStartIndex = 0;
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
/*     */   private boolean removeElement(int s) {
/* 328 */     int at = indexOf(s, 0);
/* 329 */     if (at < 0)
/* 330 */       return false; 
/* 331 */     removeElementAt(at);
/* 332 */     return true;
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
/* 346 */     if (at < this.m_firstFree) {
/*     */       
/* 348 */       int index = at >>> this.m_SHIFT;
/* 349 */       int maxindex = this.m_firstFree >>> this.m_SHIFT;
/* 350 */       int offset = at & this.m_MASK;
/*     */       
/* 352 */       while (index <= maxindex) {
/*     */         
/* 354 */         int copylen = this.m_blocksize - offset - 1;
/* 355 */         int[] block = this.m_map[index];
/* 356 */         if (null == block) {
/* 357 */           block = this.m_map[index] = new int[this.m_blocksize];
/*     */         } else {
/* 359 */           System.arraycopy(block, offset + 1, block, offset, copylen);
/* 360 */         }  if (index < maxindex) {
/*     */           
/* 362 */           int[] next = this.m_map[index + 1];
/* 363 */           if (next != null) {
/* 364 */             block[this.m_blocksize - 1] = (next != null) ? next[0] : 0;
/*     */           }
/*     */         } else {
/* 367 */           block[this.m_blocksize - 1] = 0;
/* 368 */         }  offset = 0;
/* 369 */         index++;
/*     */       } 
/*     */     } 
/* 372 */     this.m_firstFree--;
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
/*     */   public void setElementAt(int value, int at) {
/* 387 */     if (at < this.m_blocksize) {
/* 388 */       this.m_map0[at] = value;
/*     */     } else {
/*     */       
/* 391 */       int index = at >>> this.m_SHIFT;
/* 392 */       int offset = at & this.m_MASK;
/*     */       
/* 394 */       if (index >= this.m_map.length) {
/*     */         
/* 396 */         int newsize = index + this.m_numblocks;
/* 397 */         int[][] newMap = new int[newsize][];
/* 398 */         System.arraycopy(this.m_map, 0, newMap, 0, this.m_map.length);
/* 399 */         this.m_map = newMap;
/*     */       } 
/*     */       
/* 402 */       int[] block = this.m_map[index];
/* 403 */       if (null == block)
/* 404 */         block = this.m_map[index] = new int[this.m_blocksize]; 
/* 405 */       block[offset] = value;
/*     */     } 
/*     */     
/* 408 */     if (at >= this.m_firstFree) {
/* 409 */       this.m_firstFree = at + 1;
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
/*     */   public int elementAt(int i) {
/* 437 */     if (i < this.m_blocksize) {
/* 438 */       return this.m_map0[i];
/*     */     }
/* 440 */     return this.m_map[i >>> this.m_SHIFT][i & this.m_MASK];
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
/*     */   private boolean contains(int s) {
/* 452 */     return (indexOf(s, 0) >= 0);
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
/*     */   public int indexOf(int elem, int index) {
/* 468 */     if (index >= this.m_firstFree) {
/* 469 */       return -1;
/*     */     }
/* 471 */     int bindex = index >>> this.m_SHIFT;
/* 472 */     int boffset = index & this.m_MASK;
/* 473 */     int maxindex = this.m_firstFree >>> this.m_SHIFT;
/*     */ 
/*     */     
/* 476 */     for (; bindex < maxindex; bindex++) {
/*     */       
/* 478 */       int[] block = this.m_map[bindex];
/* 479 */       if (block != null)
/* 480 */         for (int i = boffset; i < this.m_blocksize; i++) {
/* 481 */           if (block[i] == elem)
/* 482 */             return i + bindex * this.m_blocksize; 
/* 483 */         }   boffset = 0;
/*     */     } 
/*     */     
/* 486 */     int maxoffset = this.m_firstFree & this.m_MASK;
/* 487 */     int[] arrayOfInt = this.m_map[maxindex];
/* 488 */     for (int offset = boffset; offset < maxoffset; offset++) {
/* 489 */       if (arrayOfInt[offset] == elem)
/* 490 */         return offset + maxindex * this.m_blocksize; 
/*     */     } 
/* 492 */     return -1;
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
/*     */   public int indexOf(int elem) {
/* 507 */     return indexOf(elem, 0);
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
/*     */   private int lastIndexOf(int elem) {
/* 522 */     int boffset = this.m_firstFree & this.m_MASK;
/* 523 */     int index = this.m_firstFree >>> this.m_SHIFT;
/* 524 */     for (; index >= 0; 
/* 525 */       index--) {
/*     */       
/* 527 */       int[] block = this.m_map[index];
/* 528 */       if (block != null)
/* 529 */         for (int offset = boffset; offset >= 0; offset--) {
/* 530 */           if (block[offset] == elem)
/* 531 */             return offset + index * this.m_blocksize; 
/* 532 */         }   boffset = 0;
/*     */     } 
/* 534 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int[] getMap0() {
/* 543 */     return this.m_map0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int[][] getMap() {
/* 552 */     return this.m_map;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/SuballocatedIntVector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */