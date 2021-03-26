/*    */ package org.apache.xalan.xsltc.dom;
/*    */ 
/*    */ import org.apache.xalan.xsltc.runtime.BasisLibrary;
/*    */ import org.apache.xml.dtm.DTMAxisIterator;
/*    */ import org.apache.xml.dtm.ref.DTMAxisIteratorBase;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class AbsoluteIterator
/*    */   extends DTMAxisIteratorBase
/*    */ {
/*    */   private DTMAxisIterator _source;
/*    */   
/*    */   public AbsoluteIterator(DTMAxisIterator source) {
/* 48 */     this._source = source;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRestartable(boolean isRestartable) {
/* 53 */     this._isRestartable = isRestartable;
/* 54 */     this._source.setRestartable(isRestartable);
/*    */   }
/*    */   
/*    */   public DTMAxisIterator setStartNode(int node) {
/* 58 */     this._startNode = 0;
/* 59 */     if (this._isRestartable) {
/* 60 */       this._source.setStartNode(this._startNode);
/* 61 */       resetPosition();
/*    */     } 
/* 63 */     return (DTMAxisIterator)this;
/*    */   }
/*    */   
/*    */   public int next() {
/* 67 */     return returnNode(this._source.next());
/*    */   }
/*    */   
/*    */   public DTMAxisIterator cloneIterator() {
/*    */     
/* 72 */     try { AbsoluteIterator clone = (AbsoluteIterator)clone();
/* 73 */       clone._source = this._source.cloneIterator();
/* 74 */       clone.resetPosition();
/* 75 */       clone._isRestartable = false;
/* 76 */       return (DTMAxisIterator)clone; } catch (CloneNotSupportedException e)
/*    */     
/*    */     { 
/* 79 */       BasisLibrary.runTimeError("ITERATOR_CLONE_ERR", e.toString());
/*    */       
/* 81 */       return null; }
/*    */   
/*    */   }
/*    */   
/*    */   public DTMAxisIterator reset() {
/* 86 */     this._source.reset();
/* 87 */     return resetPosition();
/*    */   }
/*    */   
/*    */   public void setMark() {
/* 91 */     this._source.setMark();
/*    */   }
/*    */   
/*    */   public void gotoMark() {
/* 95 */     this._source.gotoMark();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/AbsoluteIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */