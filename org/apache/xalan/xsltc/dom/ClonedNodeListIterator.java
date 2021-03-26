/*    */ package org.apache.xalan.xsltc.dom;
/*    */ 
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
/*    */ public final class ClonedNodeListIterator
/*    */   extends DTMAxisIteratorBase
/*    */ {
/*    */   private CachedNodeListIterator _source;
/* 36 */   private int _index = 0;
/*    */   
/*    */   public ClonedNodeListIterator(CachedNodeListIterator source) {
/* 39 */     this._source = source;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRestartable(boolean isRestartable) {}
/*    */ 
/*    */   
/*    */   public DTMAxisIterator setStartNode(int node) {
/* 48 */     return (DTMAxisIterator)this;
/*    */   }
/*    */   
/*    */   public int next() {
/* 52 */     return this._source.getNode(this._index++);
/*    */   }
/*    */   
/*    */   public int getPosition() {
/* 56 */     return (this._index == 0) ? 1 : this._index;
/*    */   }
/*    */   
/*    */   public int getNodeByPosition(int pos) {
/* 60 */     return this._source.getNode(pos);
/*    */   }
/*    */   
/*    */   public DTMAxisIterator cloneIterator() {
/* 64 */     return this._source.cloneIterator();
/*    */   }
/*    */   
/*    */   public DTMAxisIterator reset() {
/* 68 */     this._index = 0;
/* 69 */     return (DTMAxisIterator)this;
/*    */   }
/*    */   
/*    */   public void setMark() {
/* 73 */     this._source.setMark();
/*    */   }
/*    */   
/*    */   public void gotoMark() {
/* 77 */     this._source.gotoMark();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/ClonedNodeListIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */