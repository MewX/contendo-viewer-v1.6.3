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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MatchingIterator
/*     */   extends DTMAxisIteratorBase
/*     */ {
/*     */   private DTMAxisIterator _source;
/*     */   private final int _match;
/*     */   
/*     */   public MatchingIterator(int match, DTMAxisIterator source) {
/*  59 */     this._source = source;
/*  60 */     this._match = match;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRestartable(boolean isRestartable) {
/*  65 */     this._isRestartable = isRestartable;
/*  66 */     this._source.setRestartable(isRestartable);
/*     */   }
/*     */ 
/*     */   
/*     */   public DTMAxisIterator cloneIterator() {
/*     */     
/*  72 */     try { MatchingIterator clone = (MatchingIterator)clone();
/*  73 */       clone._source = this._source.cloneIterator();
/*  74 */       clone._isRestartable = false;
/*  75 */       return clone.reset(); } catch (CloneNotSupportedException e)
/*     */     
/*     */     { 
/*  78 */       BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
/*     */       
/*  80 */       return null; }
/*     */   
/*     */   }
/*     */   
/*     */   public DTMAxisIterator setStartNode(int node) {
/*  85 */     if (this._isRestartable) {
/*     */       
/*  87 */       this._source.setStartNode(node);
/*     */ 
/*     */       
/*  90 */       this._position = 1;
/*  91 */       while ((node = this._source.next()) != -1 && node != this._match) {
/*  92 */         this._position++;
/*     */       }
/*     */     } 
/*  95 */     return (DTMAxisIterator)this;
/*     */   }
/*     */   
/*     */   public DTMAxisIterator reset() {
/*  99 */     this._source.reset();
/* 100 */     return resetPosition();
/*     */   }
/*     */   
/*     */   public int next() {
/* 104 */     return this._source.next();
/*     */   }
/*     */   
/*     */   public int getLast() {
/* 108 */     if (this._last == -1) {
/* 109 */       this._last = this._source.getLast();
/*     */     }
/* 111 */     return this._last;
/*     */   }
/*     */   
/*     */   public int getPosition() {
/* 115 */     return this._position;
/*     */   }
/*     */   
/*     */   public void setMark() {
/* 119 */     this._source.setMark();
/*     */   }
/*     */   
/*     */   public void gotoMark() {
/* 123 */     this._source.gotoMark();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/MatchingIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */