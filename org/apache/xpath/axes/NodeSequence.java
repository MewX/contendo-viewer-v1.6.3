/*     */ package org.apache.xpath.axes;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.dtm.DTMManager;
/*     */ import org.apache.xml.utils.NodeVector;
/*     */ import org.apache.xpath.XPathContext;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NodeSequence
/*     */   extends XObject
/*     */   implements Cloneable, DTMIterator, PathComponent
/*     */ {
/*  40 */   protected int m_last = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   protected int m_next = 0;
/*     */   
/*     */   protected DTMIterator m_iter;
/*     */   
/*     */   protected DTMManager m_dtmMgr;
/*     */ 
/*     */   
/*     */   protected NodeVector getVector() {
/*  55 */     return (NodeVector)this.m_obj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void SetVector(NodeVector v) {
/*  63 */     this.m_obj = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasCache() {
/*  73 */     return (this.m_obj != null);
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
/*     */   public final void setIter(DTMIterator iter) {
/*  88 */     this.m_iter = iter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final DTMIterator getContainedIter() {
/*  97 */     return this.m_iter;
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
/*     */   public NodeSequence(DTMIterator iter, int context, XPathContext xctxt, boolean shouldCacheNodes) {
/* 118 */     setIter(iter);
/* 119 */     setRoot(context, xctxt);
/* 120 */     setShouldCacheNodes(shouldCacheNodes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeSequence(Object nodeVector) {
/* 130 */     super(nodeVector);
/* 131 */     if (null != nodeVector) {
/*     */       
/* 133 */       assertion(nodeVector instanceof NodeVector, "Must have a NodeVector as the object for NodeSequence!");
/*     */       
/* 135 */       if (nodeVector instanceof DTMIterator) {
/*     */         
/* 137 */         setIter((DTMIterator)nodeVector);
/* 138 */         this.m_last = ((DTMIterator)nodeVector).getLength();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeSequence(DTMManager dtmMgr) {
/* 150 */     super(new NodeVector());
/* 151 */     this.m_last = 0;
/* 152 */     this.m_dtmMgr = dtmMgr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeSequence() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTM getDTM(int nodeHandle) {
/* 169 */     DTMManager mgr = getDTMManager();
/* 170 */     if (null != mgr) {
/* 171 */       return getDTMManager().getDTM(nodeHandle);
/*     */     }
/*     */     
/* 174 */     assertion(false, "Can not get a DTM Unless a DTMManager has been set!");
/* 175 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMManager getDTMManager() {
/* 184 */     return this.m_dtmMgr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRoot() {
/* 192 */     if (null != this.m_iter) {
/* 193 */       return this.m_iter.getRoot();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRoot(int nodeHandle, Object environment) {
/* 208 */     if (null != this.m_iter) {
/*     */       
/* 210 */       XPathContext xctxt = (XPathContext)environment;
/* 211 */       this.m_dtmMgr = xctxt.getDTMManager();
/* 212 */       this.m_iter.setRoot(nodeHandle, environment);
/* 213 */       if (!this.m_iter.isDocOrdered()) {
/*     */         
/* 215 */         if (!hasCache())
/* 216 */           setShouldCacheNodes(true); 
/* 217 */         runTo(-1);
/* 218 */         this.m_next = 0;
/*     */       } 
/*     */     } else {
/*     */       
/* 222 */       assertion(false, "Can not setRoot on a non-iterated NodeSequence!");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 230 */     this.m_next = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWhatToShow() {
/* 239 */     return hasCache() ? -17 : this.m_iter.getWhatToShow();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getExpandEntityReferences() {
/* 248 */     if (null != this.m_iter) {
/* 249 */       return this.m_iter.getExpandEntityReferences();
/*     */     }
/* 251 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int nextNode() {
/* 261 */     NodeVector vec = getVector();
/* 262 */     if (null != vec) {
/*     */       
/* 264 */       if (this.m_next < vec.size()) {
/*     */         
/* 266 */         int i = vec.elementAt(this.m_next);
/* 267 */         this.m_next++;
/* 268 */         return i;
/*     */       } 
/* 270 */       if (-1 != this.m_last || null == this.m_iter) {
/*     */         
/* 272 */         this.m_next++;
/* 273 */         return -1;
/*     */       } 
/*     */     } 
/*     */     
/* 277 */     if (null == this.m_iter) {
/* 278 */       return -1;
/*     */     }
/* 280 */     int next = this.m_iter.nextNode();
/* 281 */     if (-1 != next) {
/*     */       
/* 283 */       if (hasCache()) {
/*     */         
/* 285 */         if (this.m_iter.isDocOrdered()) {
/*     */           
/* 287 */           getVector().addElement(next);
/* 288 */           this.m_next++;
/*     */         }
/*     */         else {
/*     */           
/* 292 */           int insertIndex = addNodeInDocOrder(next);
/* 293 */           if (insertIndex >= 0) {
/* 294 */             this.m_next++;
/*     */           }
/*     */         } 
/*     */       } else {
/* 298 */         this.m_next++;
/*     */       } 
/*     */     } else {
/*     */       
/* 302 */       this.m_last = this.m_next;
/* 303 */       this.m_next++;
/*     */     } 
/*     */     
/* 306 */     return next;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int previousNode() {
/* 314 */     if (hasCache()) {
/*     */       
/* 316 */       if (this.m_next <= 0) {
/* 317 */         return -1;
/*     */       }
/*     */       
/* 320 */       this.m_next--;
/* 321 */       return item(this.m_next);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 326 */     int n = this.m_iter.previousNode();
/* 327 */     this.m_next = this.m_iter.getCurrentPos();
/* 328 */     return this.m_next;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void detach() {
/* 337 */     if (null != this.m_iter)
/* 338 */       this.m_iter.detach(); 
/* 339 */     super.detach();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void allowDetachToRelease(boolean allowRelease) {
/* 349 */     if (false == allowRelease && !hasCache())
/*     */     {
/* 351 */       setShouldCacheNodes(true);
/*     */     }
/*     */     
/* 354 */     if (null != this.m_iter)
/* 355 */       this.m_iter.allowDetachToRelease(allowRelease); 
/* 356 */     super.allowDetachToRelease(allowRelease);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentNode() {
/* 364 */     if (hasCache()) {
/*     */       
/* 366 */       int currentIndex = this.m_next - 1;
/* 367 */       NodeVector vec = getVector();
/* 368 */       if (currentIndex >= 0 && currentIndex < vec.size()) {
/* 369 */         return vec.elementAt(currentIndex);
/*     */       }
/* 371 */       return -1;
/*     */     } 
/*     */     
/* 374 */     if (null != this.m_iter)
/*     */     {
/* 376 */       return this.m_iter.getCurrentNode();
/*     */     }
/*     */     
/* 379 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFresh() {
/* 387 */     return (0 == this.m_next);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setShouldCacheNodes(boolean b) {
/* 395 */     if (b) {
/*     */       
/* 397 */       if (!hasCache())
/*     */       {
/* 399 */         SetVector(new NodeVector());
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 405 */       SetVector(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMutable() {
/* 413 */     return hasCache();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCurrentPos() {
/* 421 */     return this.m_next;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void runTo(int index) {
/* 431 */     if (-1 == index) {
/*     */       
/* 433 */       int pos = this.m_next; int n; do {  }
/* 434 */       while (-1 != (n = nextNode()));
/* 435 */       this.m_next = pos;
/*     */     } else {
/* 437 */       if (this.m_next == index) {
/*     */         return;
/*     */       }
/*     */       
/* 441 */       if (hasCache() && this.m_next < getVector().size())
/*     */       
/* 443 */       { this.m_next = index; }
/*     */       
/* 445 */       else if (null == getVector() && index < this.m_next) { int i; do {
/*     */         
/* 447 */         } while (this.m_next >= index && -1 != (i = previousNode())); }
/*     */       else
/*     */       { int i; do {
/*     */         
/* 451 */         } while (this.m_next < index && -1 != (i = nextNode())); }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentPos(int i) {
/* 461 */     runTo(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int item(int index) {
/* 469 */     setCurrentPos(index);
/* 470 */     int n = nextNode();
/* 471 */     this.m_next = index;
/* 472 */     return n;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItem(int node, int index) {
/* 480 */     NodeVector vec = getVector();
/* 481 */     if (null != vec) {
/*     */       
/* 483 */       vec.setElementAt(node, index);
/* 484 */       this.m_last = vec.size();
/*     */     } else {
/*     */       
/* 487 */       this.m_iter.setItem(node, index);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 495 */     if (hasCache()) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 500 */       if (this.m_iter instanceof org.apache.xpath.NodeSetDTM)
/*     */       {
/* 502 */         return this.m_iter.getLength();
/*     */       }
/*     */       
/* 505 */       if (-1 == this.m_last) {
/*     */         
/* 507 */         int pos = this.m_next;
/* 508 */         runTo(-1);
/* 509 */         this.m_next = pos;
/*     */       } 
/* 511 */       return this.m_last;
/*     */     } 
/*     */ 
/*     */     
/* 515 */     return (-1 == this.m_last) ? (this.m_last = this.m_iter.getLength()) : this.m_last;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMIterator cloneWithReset() throws CloneNotSupportedException {
/* 525 */     NodeSequence seq = (NodeSequence)super.clone();
/* 526 */     seq.m_next = 0;
/* 527 */     return seq;
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
/*     */   public Object clone() throws CloneNotSupportedException {
/* 541 */     return super.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDocOrdered() {
/* 550 */     if (null != this.m_iter) {
/* 551 */       return this.m_iter.isDocOrdered();
/*     */     }
/* 553 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAxis() {
/* 561 */     if (null != this.m_iter) {
/* 562 */       return this.m_iter.getAxis();
/*     */     }
/*     */     
/* 565 */     assertion(false, "Can not getAxis from a non-iterated node sequence!");
/* 566 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnalysisBits() {
/* 575 */     if (null != this.m_iter && this.m_iter instanceof PathComponent) {
/* 576 */       return ((PathComponent)this.m_iter).getAnalysisBits();
/*     */     }
/* 578 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fixupVariables(Vector vars, int globalsSize) {
/* 586 */     super.fixupVariables(vars, globalsSize);
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
/*     */   protected int addNodeInDocOrder(int node) {
/* 604 */     assertion(hasCache(), "addNodeInDocOrder must be done on a mutable sequence!");
/*     */     
/* 606 */     int insertIndex = -1;
/*     */     
/* 608 */     NodeVector vec = getVector();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 613 */     int size = vec.size();
/*     */     int i;
/* 615 */     for (i = size - 1; i >= 0; i--) {
/*     */       
/* 617 */       int child = vec.elementAt(i);
/*     */       
/* 619 */       if (child == node) {
/*     */         
/* 621 */         i = -2;
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/* 626 */       DTM dtm = this.m_dtmMgr.getDTM(node);
/* 627 */       if (!dtm.isNodeAfter(node, child)) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 633 */     if (i != -2) {
/*     */       
/* 635 */       insertIndex = i + 1;
/*     */       
/* 637 */       vec.insertElementAt(node, insertIndex);
/*     */     } 
/*     */ 
/*     */     
/* 641 */     return insertIndex;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/axes/NodeSequence.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */