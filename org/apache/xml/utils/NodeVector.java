/*     */ package org.apache.xml.utils;
/*     */ 
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
/*     */ public class NodeVector
/*     */   implements Serializable, Cloneable
/*     */ {
/*     */   private int m_blocksize;
/*     */   private int[] m_map;
/*  48 */   protected int m_firstFree = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int m_mapSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeVector() {
/*  61 */     this.m_blocksize = 32;
/*  62 */     this.m_mapSize = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeVector(int blocksize) {
/*  72 */     this.m_blocksize = blocksize;
/*  73 */     this.m_mapSize = 0;
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
/*     */   public Object clone() throws CloneNotSupportedException {
/*  86 */     NodeVector clone = (NodeVector)super.clone();
/*     */     
/*  88 */     if (null != this.m_map && this.m_map == clone.m_map) {
/*     */       
/*  90 */       clone.m_map = new int[this.m_map.length];
/*     */       
/*  92 */       System.arraycopy(this.m_map, 0, clone.m_map, 0, this.m_map.length);
/*     */     } 
/*     */     
/*  95 */     return clone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int size() {
/* 105 */     return this.m_firstFree;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addElement(int value) {
/* 116 */     if (this.m_firstFree + 1 >= this.m_mapSize)
/*     */     {
/* 118 */       if (null == this.m_map) {
/*     */         
/* 120 */         this.m_map = new int[this.m_blocksize];
/* 121 */         this.m_mapSize = this.m_blocksize;
/*     */       }
/*     */       else {
/*     */         
/* 125 */         this.m_mapSize += this.m_blocksize;
/*     */         
/* 127 */         int[] newMap = new int[this.m_mapSize];
/*     */         
/* 129 */         System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
/*     */         
/* 131 */         this.m_map = newMap;
/*     */       } 
/*     */     }
/*     */     
/* 135 */     this.m_map[this.m_firstFree] = value;
/*     */     
/* 137 */     this.m_firstFree++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void push(int value) {
/* 148 */     int ff = this.m_firstFree;
/*     */     
/* 150 */     if (ff + 1 >= this.m_mapSize)
/*     */     {
/* 152 */       if (null == this.m_map) {
/*     */         
/* 154 */         this.m_map = new int[this.m_blocksize];
/* 155 */         this.m_mapSize = this.m_blocksize;
/*     */       }
/*     */       else {
/*     */         
/* 159 */         this.m_mapSize += this.m_blocksize;
/*     */         
/* 161 */         int[] newMap = new int[this.m_mapSize];
/*     */         
/* 163 */         System.arraycopy(this.m_map, 0, newMap, 0, ff + 1);
/*     */         
/* 165 */         this.m_map = newMap;
/*     */       } 
/*     */     }
/*     */     
/* 169 */     this.m_map[ff] = value;
/*     */ 
/*     */ 
/*     */     
/* 173 */     this.m_firstFree = ++ff;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final int pop() {
/* 184 */     this.m_firstFree--;
/*     */     
/* 186 */     int n = this.m_map[this.m_firstFree];
/*     */     
/* 188 */     this.m_map[this.m_firstFree] = -1;
/*     */     
/* 190 */     return n;
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
/*     */   public final int popAndTop() {
/* 202 */     this.m_firstFree--;
/*     */     
/* 204 */     this.m_map[this.m_firstFree] = -1;
/*     */     
/* 206 */     return (this.m_firstFree == 0) ? -1 : this.m_map[this.m_firstFree - 1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void popQuick() {
/* 215 */     this.m_firstFree--;
/*     */     
/* 217 */     this.m_map[this.m_firstFree] = -1;
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
/*     */   public final int peepOrNull() {
/* 229 */     return (null != this.m_map && this.m_firstFree > 0) ? this.m_map[this.m_firstFree - 1] : -1;
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
/*     */   public final void pushPair(int v1, int v2) {
/* 244 */     if (null == this.m_map) {
/*     */       
/* 246 */       this.m_map = new int[this.m_blocksize];
/* 247 */       this.m_mapSize = this.m_blocksize;
/*     */ 
/*     */     
/*     */     }
/* 251 */     else if (this.m_firstFree + 2 >= this.m_mapSize) {
/*     */       
/* 253 */       this.m_mapSize += this.m_blocksize;
/*     */       
/* 255 */       int[] newMap = new int[this.m_mapSize];
/*     */       
/* 257 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree);
/*     */       
/* 259 */       this.m_map = newMap;
/*     */     } 
/*     */ 
/*     */     
/* 263 */     this.m_map[this.m_firstFree] = v1;
/* 264 */     this.m_map[this.m_firstFree + 1] = v2;
/* 265 */     this.m_firstFree += 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void popPair() {
/* 276 */     this.m_firstFree -= 2;
/* 277 */     this.m_map[this.m_firstFree] = -1;
/* 278 */     this.m_map[this.m_firstFree + 1] = -1;
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
/*     */   public final void setTail(int n) {
/* 290 */     this.m_map[this.m_firstFree - 1] = n;
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
/*     */   public final void setTailSub1(int n) {
/* 302 */     this.m_map[this.m_firstFree - 2] = n;
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
/*     */   public final int peepTail() {
/* 314 */     return this.m_map[this.m_firstFree - 1];
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
/*     */   public final int peepTailSub1() {
/* 326 */     return this.m_map[this.m_firstFree - 2];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertInOrder(int value) {
/* 337 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 339 */       if (value < this.m_map[i]) {
/*     */         
/* 341 */         insertElementAt(value, i);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/*     */     
/* 347 */     addElement(value);
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
/*     */   public void insertElementAt(int value, int at) {
/* 362 */     if (null == this.m_map) {
/*     */       
/* 364 */       this.m_map = new int[this.m_blocksize];
/* 365 */       this.m_mapSize = this.m_blocksize;
/*     */     }
/* 367 */     else if (this.m_firstFree + 1 >= this.m_mapSize) {
/*     */       
/* 369 */       this.m_mapSize += this.m_blocksize;
/*     */       
/* 371 */       int[] newMap = new int[this.m_mapSize];
/*     */       
/* 373 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + 1);
/*     */       
/* 375 */       this.m_map = newMap;
/*     */     } 
/*     */     
/* 378 */     if (at <= this.m_firstFree - 1)
/*     */     {
/* 380 */       System.arraycopy(this.m_map, at, this.m_map, at + 1, this.m_firstFree - at);
/*     */     }
/*     */     
/* 383 */     this.m_map[at] = value;
/*     */     
/* 385 */     this.m_firstFree++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendNodes(NodeVector nodes) {
/* 396 */     int nNodes = nodes.size();
/*     */     
/* 398 */     if (null == this.m_map) {
/*     */       
/* 400 */       this.m_mapSize = nNodes + this.m_blocksize;
/* 401 */       this.m_map = new int[this.m_mapSize];
/*     */     }
/* 403 */     else if (this.m_firstFree + nNodes >= this.m_mapSize) {
/*     */       
/* 405 */       this.m_mapSize += nNodes + this.m_blocksize;
/*     */       
/* 407 */       int[] newMap = new int[this.m_mapSize];
/*     */       
/* 409 */       System.arraycopy(this.m_map, 0, newMap, 0, this.m_firstFree + nNodes);
/*     */       
/* 411 */       this.m_map = newMap;
/*     */     } 
/*     */     
/* 414 */     System.arraycopy(nodes.m_map, 0, this.m_map, this.m_firstFree, nNodes);
/*     */     
/* 416 */     this.m_firstFree += nNodes;
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
/*     */   public void removeAllElements() {
/* 428 */     if (null == this.m_map) {
/*     */       return;
/*     */     }
/* 431 */     for (int i = 0; i < this.m_firstFree; i++)
/*     */     {
/* 433 */       this.m_map[i] = -1;
/*     */     }
/*     */     
/* 436 */     this.m_firstFree = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void RemoveAllNoClear() {
/* 445 */     if (null == this.m_map) {
/*     */       return;
/*     */     }
/* 448 */     this.m_firstFree = 0;
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
/*     */   public boolean removeElement(int s) {
/* 465 */     if (null == this.m_map) {
/* 466 */       return false;
/*     */     }
/* 468 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 470 */       int node = this.m_map[i];
/*     */       
/* 472 */       if (node == s) {
/*     */         
/* 474 */         if (i > this.m_firstFree) {
/* 475 */           System.arraycopy(this.m_map, i + 1, this.m_map, i - 1, this.m_firstFree - i);
/*     */         } else {
/* 477 */           this.m_map[i] = -1;
/*     */         } 
/* 479 */         this.m_firstFree--;
/*     */         
/* 481 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 485 */     return false;
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
/*     */   public void removeElementAt(int i) {
/* 499 */     if (null == this.m_map) {
/*     */       return;
/*     */     }
/* 502 */     if (i > this.m_firstFree) {
/* 503 */       System.arraycopy(this.m_map, i + 1, this.m_map, i - 1, this.m_firstFree - i);
/*     */     } else {
/* 505 */       this.m_map[i] = -1;
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
/*     */   public void setElementAt(int node, int index) {
/* 521 */     if (null == this.m_map) {
/*     */       
/* 523 */       this.m_map = new int[this.m_blocksize];
/* 524 */       this.m_mapSize = this.m_blocksize;
/*     */     } 
/*     */     
/* 527 */     if (index == -1) {
/* 528 */       addElement(node);
/*     */     }
/* 530 */     this.m_map[index] = node;
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
/*     */   public int elementAt(int i) {
/* 543 */     if (null == this.m_map) {
/* 544 */       return -1;
/*     */     }
/* 546 */     return this.m_map[i];
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
/*     */   public boolean contains(int s) {
/* 559 */     if (null == this.m_map) {
/* 560 */       return false;
/*     */     }
/* 562 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 564 */       int node = this.m_map[i];
/*     */       
/* 566 */       if (node == s) {
/* 567 */         return true;
/*     */       }
/*     */     } 
/* 570 */     return false;
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
/*     */   public int indexOf(int elem, int index) {
/* 587 */     if (null == this.m_map) {
/* 588 */       return -1;
/*     */     }
/* 590 */     for (int i = index; i < this.m_firstFree; i++) {
/*     */       
/* 592 */       int node = this.m_map[i];
/*     */       
/* 594 */       if (node == elem) {
/* 595 */         return i;
/*     */       }
/*     */     } 
/* 598 */     return -1;
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
/*     */   public int indexOf(int elem) {
/* 614 */     if (null == this.m_map) {
/* 615 */       return -1;
/*     */     }
/* 617 */     for (int i = 0; i < this.m_firstFree; i++) {
/*     */       
/* 619 */       int node = this.m_map[i];
/*     */       
/* 621 */       if (node == elem) {
/* 622 */         return i;
/*     */       }
/*     */     } 
/* 625 */     return -1;
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
/*     */   public void sort(int[] a, int lo0, int hi0) throws Exception {
/* 640 */     int lo = lo0;
/* 641 */     int hi = hi0;
/*     */ 
/*     */     
/* 644 */     if (lo >= hi) {
/*     */       return;
/*     */     }
/*     */     
/* 648 */     if (lo == hi - 1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 654 */       if (a[lo] > a[hi]) {
/*     */         
/* 656 */         int T = a[lo];
/*     */         
/* 658 */         a[lo] = a[hi];
/* 659 */         a[hi] = T;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */     
/* 668 */     int pivot = a[(lo + hi) / 2];
/*     */     
/* 670 */     a[(lo + hi) / 2] = a[hi];
/* 671 */     a[hi] = pivot;
/*     */     
/* 673 */     while (lo < hi) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 680 */       while (a[lo] <= pivot && lo < hi)
/*     */       {
/* 682 */         lo++;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 689 */       while (pivot <= a[hi] && lo < hi)
/*     */       {
/* 691 */         hi--;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 697 */       if (lo < hi) {
/*     */         
/* 699 */         int T = a[lo];
/*     */         
/* 701 */         a[lo] = a[hi];
/* 702 */         a[hi] = T;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 715 */     a[hi0] = a[hi];
/* 716 */     a[hi] = pivot;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 723 */     sort(a, lo0, lo - 1);
/* 724 */     sort(a, hi + 1, hi0);
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
/*     */   public void sort() throws Exception {
/* 738 */     sort(this.m_map, 0, this.m_firstFree - 1);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/NodeVector.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */