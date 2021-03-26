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
/*     */ public class ObjectVector
/*     */   implements Cloneable
/*     */ {
/*     */   protected int m_blocksize;
/*     */   protected Object[] m_map;
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
/*     */   public ObjectVector() {
/*  53 */     this.m_blocksize = 32;
/*  54 */     this.m_mapSize = this.m_blocksize;
/*  55 */     this.m_map = new Object[this.m_blocksize];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectVector(int blocksize) {
/*  66 */     this.m_blocksize = blocksize;
/*  67 */     this.m_mapSize = blocksize;
/*  68 */     this.m_map = new Object[blocksize];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectVector(int blocksize, int increaseSize) {
/*  79 */     this.m_blocksize = increaseSize;
/*  80 */     this.m_mapSize = blocksize;
/*  81 */     this.m_map = new Object[blocksize];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectVector(ObjectVector v) {
/*  91 */     this.m_map = new Object[v.m_mapSize];
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
/*     */   public final void addElement(Object value) {
/* 127 */     if (this.m_firstFree + 1 >= this.m_mapSize) {
/*     */       
/* 129 */       this.m_mapSize += this.m_blocksize;
/*     */       
/* 131 */       Object[] newMap = new Object[this.m_mapSize];
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
/*     */   public final void addElements(Object value, int numberOfElements) {
/* 151 */     if (this.m_firstFree + numberOfElements >= this.m_mapSize) {
/*     */       
/* 153 */       this.m_mapSize += this.m_blocksize + numberOfElements;
/*     */       
/* 155 */       Object[] newMap = new Object[this.m_mapSize];
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
/* 181 */       Object[] newMap = new Object[this.m_mapSize];
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
/*     */   public final void insertElementAt(Object value, int at) {
/* 204 */     if (this.m_firstFree + 1 >= this.m_mapSize) {
/*     */       
/* 206 */       this.m_mapSize += this.m_blocksize;
/*     */       
/* 208 */       Object[] newMap = new Object[this.m_mapSize];
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
/*     */   public final void removeAllElements() {
/* 231 */     for (int i = 0; i < this.m_firstFree; i++)
/*     */     {
/* 233 */       this.m_map[i] = null;
/*     */     }
/*     */     
/* 236 */     this.m_firstFree = 0;
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
/*     */   public final boolean removeElement(Object s) {
/* 253 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 255 */       if (this.m_map[i] == s) {
/*     */         
/* 257 */         if (i + 1 < this.m_firstFree) {
/* 258 */           System.arraycopy(this.m_map, i + 1, this.m_map, i - 1, this.m_firstFree - i);
/*     */         } else {
/* 260 */           this.m_map[i] = null;
/*     */         } 
/* 262 */         this.m_firstFree--;
/*     */         
/* 264 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 268 */     return false;
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
/* 282 */     if (i > this.m_firstFree) {
/* 283 */       System.arraycopy(this.m_map, i + 1, this.m_map, i, this.m_firstFree);
/*     */     } else {
/* 285 */       this.m_map[i] = null;
/*     */     } 
/* 287 */     this.m_firstFree--;
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
/*     */   public final void setElementAt(Object value, int index) {
/* 302 */     this.m_map[index] = value;
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
/*     */   public final Object elementAt(int i) {
/* 314 */     return this.m_map[i];
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
/*     */   public final boolean contains(Object s) {
/* 327 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 329 */       if (this.m_map[i] == s) {
/* 330 */         return true;
/*     */       }
/*     */     } 
/* 333 */     return false;
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
/*     */   public final int indexOf(Object elem, int index) {
/* 350 */     for (int i = index; i < this.m_firstFree; i++) {
/*     */       
/* 352 */       if (this.m_map[i] == elem) {
/* 353 */         return i;
/*     */       }
/*     */     } 
/* 356 */     return Integer.MIN_VALUE;
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
/*     */   public final int indexOf(Object elem) {
/* 372 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 374 */       if (this.m_map[i] == elem) {
/* 375 */         return i;
/*     */       }
/*     */     } 
/* 378 */     return Integer.MIN_VALUE;
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
/*     */   public final int lastIndexOf(Object elem) {
/* 394 */     for (int i = this.m_firstFree - 1; i >= 0; i--) {
/*     */       
/* 396 */       if (this.m_map[i] == elem) {
/* 397 */         return i;
/*     */       }
/*     */     } 
/* 400 */     return Integer.MIN_VALUE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setToSize(int size) {
/* 410 */     Object[] newMap = new Object[size];
/*     */     
/* 412 */     System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree);
/* 413 */     this.m_mapSize = size;
/*     */     
/* 415 */     this.m_map = newMap;
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
/*     */   public Object clone() throws CloneNotSupportedException {
/* 427 */     return new ObjectVector(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/ObjectVector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */