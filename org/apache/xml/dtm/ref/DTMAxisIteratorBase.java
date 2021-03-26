/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DTMAxisIteratorBase
/*     */   implements DTMAxisIterator
/*     */ {
/*  34 */   protected int _last = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   protected int _position = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int _markedNode;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   protected int _startNode = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean _includeSelf = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean _isRestartable = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStartNode() {
/*  71 */     return this._startNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterator reset() {
/*  81 */     boolean temp = this._isRestartable;
/*     */     
/*  83 */     this._isRestartable = true;
/*     */     
/*  85 */     setStartNode(this._startNode);
/*     */     
/*  87 */     this._isRestartable = temp;
/*     */     
/*  89 */     return this;
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
/*     */   public DTMAxisIterator includeSelf() {
/* 104 */     this._includeSelf = true;
/*     */     
/* 106 */     return this;
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
/*     */   public int getLast() {
/* 123 */     if (this._last == -1) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 132 */       int temp = this._position;
/* 133 */       setMark();
/*     */       
/* 135 */       reset();
/*     */       
/*     */       do {
/* 138 */         this._last++;
/*     */       }
/* 140 */       while (next() != -1);
/*     */       
/* 142 */       gotoMark();
/* 143 */       this._position = temp;
/*     */     } 
/*     */     
/* 146 */     return this._last;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPosition() {
/* 155 */     return (this._position == 0) ? 1 : this._position;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReverse() {
/* 163 */     return false;
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
/*     */   public DTMAxisIterator cloneIterator() {
/*     */     
/* 178 */     try { DTMAxisIteratorBase clone = (DTMAxisIteratorBase)clone();
/*     */       
/* 180 */       clone._isRestartable = false;
/*     */ 
/*     */       
/* 183 */       return clone; } catch (CloneNotSupportedException e)
/*     */     
/*     */     { 
/*     */       
/* 187 */       throw new WrappedRuntimeException(e); }
/*     */   
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
/*     */   protected final int returnNode(int node) {
/* 211 */     this._position++;
/*     */     
/* 213 */     return node;
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
/*     */   protected final DTMAxisIterator resetPosition() {
/* 227 */     this._position = 0;
/*     */     
/* 229 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDocOrdered() {
/* 240 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAxis() {
/* 251 */     return -1;
/*     */   }
/*     */   
/*     */   public void setRestartable(boolean isRestartable) {
/* 255 */     this._isRestartable = isRestartable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNodeByPosition(int position) {
/* 266 */     if (position > 0) {
/* 267 */       int pos = isReverse() ? (getLast() - position + 1) : position;
/*     */       
/*     */       int node;
/* 270 */       while ((node = next()) != -1) {
/* 271 */         if (pos == getPosition()) {
/* 272 */           return node;
/*     */         }
/*     */       } 
/*     */     } 
/* 276 */     return -1;
/*     */   }
/*     */   
/*     */   public abstract DTMAxisIterator setStartNode(int paramInt);
/*     */   
/*     */   public abstract void gotoMark();
/*     */   
/*     */   public abstract void setMark();
/*     */   
/*     */   public abstract int next();
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/DTMAxisIteratorBase.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */