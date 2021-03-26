/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
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
/*     */ public final class SortingIterator
/*     */   extends DTMAxisIteratorBase
/*     */ {
/*     */   private static final int INIT_DATA_SIZE = 16;
/*     */   private DTMAxisIterator _source;
/*     */   private NodeSortRecordFactory _factory;
/*     */   private NodeSortRecord[] _data;
/*  38 */   private int _free = 0;
/*     */   
/*     */   private int _current;
/*     */   
/*     */   public SortingIterator(DTMAxisIterator source, NodeSortRecordFactory factory) {
/*  43 */     this._source = source;
/*  44 */     this._factory = factory;
/*     */   }
/*     */   
/*     */   public int next() {
/*  48 */     return (this._current < this._free) ? this._data[this._current++].getNode() : -1;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator setStartNode(int node) {
/*     */     
/*  53 */     try { this._source.setStartNode(this._startNode = node);
/*  54 */       this._data = new NodeSortRecord[16];
/*  55 */       this._free = 0;
/*     */ 
/*     */       
/*  58 */       while ((node = this._source.next()) != -1) {
/*  59 */         addRecord(this._factory.makeNodeSortRecord(node, this._free));
/*     */       }
/*     */       
/*  62 */       quicksort(0, this._free - 1);
/*     */       
/*  64 */       this._current = 0;
/*  65 */       return (DTMAxisIterator)this; } catch (Exception e)
/*     */     
/*     */     { 
/*  68 */       return (DTMAxisIterator)this; }
/*     */   
/*     */   }
/*     */   
/*     */   public int getPosition() {
/*  73 */     return (this._current == 0) ? 1 : this._current;
/*     */   }
/*     */   
/*     */   public int getLast() {
/*  77 */     return this._free;
/*     */   }
/*     */   
/*     */   public void setMark() {
/*  81 */     this._source.setMark();
/*  82 */     this._markedNode = this._current;
/*     */   }
/*     */   
/*     */   public void gotoMark() {
/*  86 */     this._source.gotoMark();
/*  87 */     this._current = this._markedNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterator cloneIterator() {
/*     */     
/*  97 */     try { SortingIterator clone = (SortingIterator)clone();
/*  98 */       clone._source = this._source.cloneIterator();
/*  99 */       clone._factory = this._factory;
/* 100 */       clone._data = this._data;
/* 101 */       clone._free = this._free;
/* 102 */       clone._current = this._current;
/* 103 */       clone.setRestartable(false);
/* 104 */       return clone.reset(); } catch (CloneNotSupportedException e)
/*     */     
/*     */     { 
/* 107 */       BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
/*     */       
/* 109 */       return null; }
/*     */   
/*     */   }
/*     */   
/*     */   private void addRecord(NodeSortRecord record) {
/* 114 */     if (this._free == this._data.length) {
/* 115 */       NodeSortRecord[] newArray = new NodeSortRecord[this._data.length * 2];
/* 116 */       System.arraycopy(this._data, 0, newArray, 0, this._free);
/* 117 */       this._data = newArray;
/*     */     } 
/* 119 */     this._data[this._free++] = record;
/*     */   }
/*     */   
/*     */   private void quicksort(int p, int r) {
/* 123 */     while (p < r) {
/* 124 */       int q = partition(p, r);
/* 125 */       quicksort(p, q);
/* 126 */       p = q + 1;
/*     */     } 
/*     */   }
/*     */   
/*     */   private int partition(int p, int r) {
/* 131 */     NodeSortRecord x = this._data[p + r >>> 1];
/* 132 */     int i = p - 1;
/* 133 */     int j = r + 1;
/*     */     while (true) {
/* 135 */       if (x.compareTo(this._data[--j]) >= 0) { do {  }
/* 136 */         while (x.compareTo(this._data[++i]) > 0);
/* 137 */         if (i < j) {
/* 138 */           NodeSortRecord t = this._data[i];
/* 139 */           this._data[i] = this._data[j];
/* 140 */           this._data[j] = t; continue;
/*     */         }  break; }
/*     */     
/* 143 */     }  return j;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/SortingIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */