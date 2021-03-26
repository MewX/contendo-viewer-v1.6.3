/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.runtime.BasisLibrary;
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
/*     */ import org.apache.xml.dtm.ref.DTMAxisIteratorBase;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class UnionIterator
/*     */   extends DTMAxisIteratorBase
/*     */ {
/*     */   private final DOM _dom;
/*     */   private static final int InitSize = 8;
/*     */   
/*     */   private static final class LookAheadIterator
/*     */   {
/*     */     public int node;
/*     */     public int markedNode;
/*     */     public DTMAxisIterator iterator;
/*     */     public boolean isStartSet = false;
/*     */     
/*     */     public LookAheadIterator(DTMAxisIterator iterator) {
/*  47 */       this.iterator = iterator;
/*     */     }
/*     */     
/*     */     public int step() {
/*  51 */       this.node = this.iterator.next();
/*  52 */       return this.node;
/*     */     }
/*     */     
/*     */     public LookAheadIterator cloneIterator() {
/*  56 */       LookAheadIterator clone = new LookAheadIterator(this.iterator.cloneIterator());
/*     */       
/*  58 */       clone.node = this.node;
/*  59 */       clone.markedNode = this.node;
/*  60 */       return clone;
/*     */     }
/*     */     
/*     */     public void setMark() {
/*  64 */       this.markedNode = this.node;
/*  65 */       this.iterator.setMark();
/*     */     }
/*     */     
/*     */     public void gotoMark() {
/*  69 */       this.node = this.markedNode;
/*  70 */       this.iterator.gotoMark();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   private int _heapSize = 0;
/*  78 */   private int _size = 8;
/*  79 */   private LookAheadIterator[] _heap = new LookAheadIterator[8];
/*  80 */   private int _free = 0;
/*     */ 
/*     */ 
/*     */   
/*     */   private int _returnedLast;
/*     */ 
/*     */   
/*  87 */   private int _cachedReturnedLast = -1;
/*     */   
/*     */   private int _cachedHeapSize;
/*     */   
/*     */   public UnionIterator(DOM dom) {
/*  92 */     this._dom = dom;
/*     */   }
/*     */ 
/*     */   
/*     */   public DTMAxisIterator cloneIterator() {
/*  97 */     this._isRestartable = false;
/*  98 */     LookAheadIterator[] heapCopy = new LookAheadIterator[this._heap.length];
/*     */ 
/*     */     
/* 101 */     try { UnionIterator clone = (UnionIterator)clone();
/* 102 */       for (int i = 0; i < this._free; i++) {
/* 103 */         heapCopy[i] = this._heap[i].cloneIterator();
/*     */       }
/* 105 */       clone.setRestartable(false);
/* 106 */       clone._heap = heapCopy;
/* 107 */       return clone.reset(); } catch (CloneNotSupportedException e)
/*     */     
/*     */     { 
/* 110 */       BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
/*     */       
/* 112 */       return null; }
/*     */   
/*     */   }
/*     */   
/*     */   public UnionIterator addIterator(DTMAxisIterator iterator) {
/* 117 */     if (this._free == this._size) {
/* 118 */       LookAheadIterator[] newArray = new LookAheadIterator[this._size *= 2];
/* 119 */       System.arraycopy(this._heap, 0, newArray, 0, this._free);
/* 120 */       this._heap = newArray;
/*     */     } 
/* 122 */     this._heapSize++;
/* 123 */     this._heap[this._free++] = new LookAheadIterator(iterator);
/* 124 */     return this;
/*     */   }
/*     */   
/*     */   public int next() {
/* 128 */     while (this._heapSize > 0) {
/* 129 */       int smallest = (this._heap[0]).node;
/* 130 */       if (smallest == -1) {
/* 131 */         if (this._heapSize > 1) {
/*     */           
/* 133 */           LookAheadIterator temp = this._heap[0];
/* 134 */           this._heap[0] = this._heap[--this._heapSize];
/* 135 */           this._heap[this._heapSize] = temp;
/*     */         } else {
/*     */           
/* 138 */           return -1;
/*     */         }
/*     */       
/* 141 */       } else if (smallest == this._returnedLast) {
/* 142 */         this._heap[0].step();
/*     */       } else {
/*     */         
/* 145 */         this._heap[0].step();
/* 146 */         heapify(0);
/* 147 */         return returnNode(this._returnedLast = smallest);
/*     */       } 
/*     */       
/* 150 */       heapify(0);
/*     */     } 
/* 152 */     return -1;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator setStartNode(int node) {
/* 156 */     if (this._isRestartable) {
/* 157 */       this._startNode = node;
/* 158 */       for (int i = 0; i < this._free; i++) {
/* 159 */         if (!(this._heap[i]).isStartSet) {
/* 160 */           (this._heap[i]).iterator.setStartNode(node);
/* 161 */           this._heap[i].step();
/* 162 */           (this._heap[i]).isStartSet = true;
/*     */         } 
/*     */       } 
/*     */       
/* 166 */       for (int j = (this._heapSize = this._free) / 2; j >= 0; j--) {
/* 167 */         heapify(j);
/*     */       }
/* 169 */       this._returnedLast = -1;
/* 170 */       return resetPosition();
/*     */     } 
/* 172 */     return (DTMAxisIterator)this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void heapify(int i) {
/*     */     while (true) {
/* 180 */       int r = i + 1 << 1, l = r - 1;
/* 181 */       int smallest = (l < this._heapSize && this._dom.lessThan((this._heap[l]).node, (this._heap[i]).node)) ? l : i;
/*     */       
/* 183 */       if (r < this._heapSize && this._dom.lessThan((this._heap[r]).node, (this._heap[smallest]).node))
/*     */       {
/* 185 */         smallest = r;
/*     */       }
/* 187 */       if (smallest != i) {
/* 188 */         LookAheadIterator temp = this._heap[smallest];
/* 189 */         this._heap[smallest] = this._heap[i];
/* 190 */         this._heap[i] = temp;
/* 191 */         i = smallest;
/*     */         continue;
/*     */       } 
/*     */       break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setMark() {
/* 199 */     for (int i = 0; i < this._free; i++) {
/* 200 */       this._heap[i].setMark();
/*     */     }
/* 202 */     this._cachedReturnedLast = this._returnedLast;
/* 203 */     this._cachedHeapSize = this._heapSize;
/*     */   }
/*     */   
/*     */   public void gotoMark() {
/* 207 */     for (int i = 0; i < this._free; i++) {
/* 208 */       this._heap[i].gotoMark();
/*     */     }
/*     */     
/* 211 */     for (int j = (this._heapSize = this._cachedHeapSize) / 2; j >= 0; j--) {
/* 212 */       heapify(j);
/*     */     }
/* 214 */     this._returnedLast = this._cachedReturnedLast;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator reset() {
/* 218 */     for (int i = 0; i < this._free; i++) {
/* 219 */       (this._heap[i]).iterator.reset();
/* 220 */       this._heap[i].step();
/*     */     } 
/*     */     
/* 223 */     for (int j = (this._heapSize = this._free) / 2; j >= 0; j--) {
/* 224 */       heapify(j);
/*     */     }
/* 226 */     this._returnedLast = -1;
/* 227 */     return resetPosition();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/UnionIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */