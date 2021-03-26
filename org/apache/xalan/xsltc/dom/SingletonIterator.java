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
/*    */ public class SingletonIterator
/*    */   extends DTMAxisIteratorBase
/*    */ {
/*    */   private int _node;
/*    */   private final boolean _isConstant;
/*    */   
/*    */   public SingletonIterator() {
/* 34 */     this(-2147483648, false);
/*    */   }
/*    */   
/*    */   public SingletonIterator(int node) {
/* 38 */     this(node, false);
/*    */   }
/*    */   
/*    */   public SingletonIterator(int node, boolean constant) {
/* 42 */     this._node = this._startNode = node;
/* 43 */     this._isConstant = constant;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DTMAxisIterator setStartNode(int node) {
/* 51 */     if (this._isConstant) {
/* 52 */       this._node = this._startNode;
/* 53 */       return resetPosition();
/*    */     } 
/* 55 */     if (this._isRestartable) {
/* 56 */       if (this._node <= 0)
/* 57 */         this._node = this._startNode = node; 
/* 58 */       return resetPosition();
/*    */     } 
/* 60 */     return (DTMAxisIterator)this;
/*    */   }
/*    */   
/*    */   public DTMAxisIterator reset() {
/* 64 */     if (this._isConstant) {
/* 65 */       this._node = this._startNode;
/* 66 */       return resetPosition();
/*    */     } 
/*    */     
/* 69 */     boolean temp = this._isRestartable;
/* 70 */     this._isRestartable = true;
/* 71 */     setStartNode(this._startNode);
/* 72 */     this._isRestartable = temp;
/*    */     
/* 74 */     return (DTMAxisIterator)this;
/*    */   }
/*    */   
/*    */   public int next() {
/* 78 */     int result = this._node;
/* 79 */     this._node = -1;
/* 80 */     return returnNode(result);
/*    */   }
/*    */   
/*    */   public void setMark() {
/* 84 */     this._markedNode = this._node;
/*    */   }
/*    */   
/*    */   public void gotoMark() {
/* 88 */     this._node = this._markedNode;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/SingletonIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */