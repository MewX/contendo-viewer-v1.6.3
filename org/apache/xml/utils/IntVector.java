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
/*     */ public class IntVector
/*     */   implements Cloneable
/*     */ {
/*     */   protected int m_blocksize;
/*     */   protected int[] m_map;
/*  41 */   protected int m_firstFree = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int m_mapSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntVector() {
/*  53 */     this.m_blocksize = 32;
/*  54 */     this.m_mapSize = this.m_blocksize;
/*  55 */     this.m_map = new int[this.m_blocksize];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntVector(int blocksize) {
/*  66 */     this.m_blocksize = blocksize;
/*  67 */     this.m_mapSize = blocksize;
/*  68 */     this.m_map = new int[blocksize];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntVector(int blocksize, int increaseSize) {
/*  79 */     this.m_blocksize = increaseSize;
/*  80 */     this.m_mapSize = blocksize;
/*  81 */     this.m_map = new int[blocksize];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IntVector(IntVector v) {
/*  91 */     this.m_map = new int[v.m_mapSize];
/*  92 */     this.m_mapSize = v.m_mapSize;
/*  93 */     this.m_firstFree = v.m_firstFree;
/*  94 */     this.m_blocksize = v.m_blocksize;
/*  95 */     System.arraycopy(v.m_map, 0, this.m_map, 0, this.m_firstFree);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int size() {
/* 105 */     return this.m_firstFree;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setSize(int sz) {
/* 115 */     this.m_firstFree = sz;
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
/*     */   public final void addElement(int value) {
/* 127 */     if (this.m_firstFree + 1 >= this.m_mapSize) {
/*     */       
/* 129 */       this.m_mapSize += this.m_blocksize;
/*     */       
/* 131 */       int[] newMap = new int[this.m_mapSize];
/*     */       
/* 133 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
/*     */       
/* 135 */       this.m_map = newMap;
/*     */     } 
/*     */     
/* 138 */     this.m_map[this.m_firstFree] = value;
/*     */     
/* 140 */     this.m_firstFree++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void addElements(int value, int numberOfElements) {
/* 151 */     if (this.m_firstFree + numberOfElements >= this.m_mapSize) {
/*     */       
/* 153 */       this.m_mapSize += this.m_blocksize + numberOfElements;
/*     */       
/* 155 */       int[] newMap = new int[this.m_mapSize];
/*     */       
/* 157 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
/*     */       
/* 159 */       this.m_map = newMap;
/*     */     } 
/*     */     
/* 162 */     for (int i = 0; i < numberOfElements; i++) {
/*     */       
/* 164 */       this.m_map[this.m_firstFree] = value;
/* 165 */       this.m_firstFree++;
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
/*     */   public final void addElements(int numberOfElements) {
/* 177 */     if (this.m_firstFree + numberOfElements >= this.m_mapSize) {
/*     */       
/* 179 */       this.m_mapSize += this.m_blocksize + numberOfElements;
/*     */       
/* 181 */       int[] newMap = new int[this.m_mapSize];
/*     */       
/* 183 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
/*     */       
/* 185 */       this.m_map = newMap;
/*     */     } 
/*     */     
/* 188 */     this.m_firstFree += numberOfElements;
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
/*     */   public final void insertElementAt(int value, int at) {
/* 204 */     if (this.m_firstFree + 1 >= this.m_mapSize) {
/*     */       
/* 206 */       this.m_mapSize += this.m_blocksize;
/*     */       
/* 208 */       int[] newMap = new int[this.m_mapSize];
/*     */       
/* 210 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
/*     */       
/* 212 */       this.m_map = newMap;
/*     */     } 
/*     */     
/* 215 */     if (at <= this.m_firstFree - 1)
/*     */     {
/* 217 */       System.arraycopy(this.m_map, at, this.m_map, at + 1, this.m_firstFree - at);
/*     */     }
/*     */     
/* 220 */     this.m_map[at] = value;
/*     */     
/* 222 */     this.m_firstFree++;
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
/*     */   public final void removeAllElements() {
/* 234 */     for (int i = 0; i < this.m_firstFree; i++)
/*     */     {
/* 236 */       this.m_map[i] = Integer.MIN_VALUE;
/*     */     }
/*     */     
/* 239 */     this.m_firstFree = 0;
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
/*     */   public final boolean removeElement(int s) {
/* 256 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 258 */       if (this.m_map[i] == s) {
/*     */         
/* 260 */         if (i + 1 < this.m_firstFree) {
/* 261 */           System.arraycopy(this.m_map, i + 1, this.m_map, i - 1, this.m_firstFree - i);
/*     */         } else {
/* 263 */           this.m_map[i] = Integer.MIN_VALUE;
/*     */         } 
/* 265 */         this.m_firstFree--;
/*     */         
/* 267 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 271 */     return false;
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
/*     */   public final void removeElementAt(int i) {
/* 285 */     if (i > this.m_firstFree) {
/* 286 */       System.arraycopy(this.m_map, i + 1, this.m_map, i, this.m_firstFree);
/*     */     } else {
/* 288 */       this.m_map[i] = Integer.MIN_VALUE;
/*     */     } 
/* 290 */     this.m_firstFree--;
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
/*     */   public final void setElementAt(int value, int index) {
/* 305 */     this.m_map[index] = value;
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
/*     */   public final int elementAt(int i) {
/* 317 */     return this.m_map[i];
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
/*     */   public final boolean contains(int s) {
/* 330 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 332 */       if (this.m_map[i] == s) {
/* 333 */         return true;
/*     */       }
/*     */     } 
/* 336 */     return false;
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
/*     */   public final int indexOf(int elem, int index) {
/* 353 */     for (int i = index; i < this.m_firstFree; i++) {
/*     */       
/* 355 */       if (this.m_map[i] == elem) {
/* 356 */         return i;
/*     */       }
/*     */     } 
/* 359 */     return Integer.MIN_VALUE;
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
/*     */   public final int indexOf(int elem) {
/* 375 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 377 */       if (this.m_map[i] == elem) {
/* 378 */         return i;
/*     */       }
/*     */     } 
/* 381 */     return Integer.MIN_VALUE;
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
/*     */   public final int lastIndexOf(int elem) {
/* 397 */     for (int i = this.m_firstFree - 1; i >= 0; i--) {
/*     */       
/* 399 */       if (this.m_map[i] == elem) {
/* 400 */         return i;
/*     */       }
/*     */     } 
/* 403 */     return Integer.MIN_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() throws CloneNotSupportedException {
/* 414 */     return new IntVector(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/IntVector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */