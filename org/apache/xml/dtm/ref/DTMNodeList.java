/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DTMNodeList
/*     */   extends DTMNodeListBase
/*     */ {
/*     */   private DTMIterator m_iter;
/*     */   
/*     */   private DTMNodeList() {}
/*     */   
/*     */   public DTMNodeList(DTMIterator dtmIterator) {
/*  71 */     if (dtmIterator != null) {
/*  72 */       int pos = dtmIterator.getCurrentPos();
/*     */       
/*  74 */       try { this.m_iter = dtmIterator.cloneWithReset(); } catch (CloneNotSupportedException cnse)
/*     */       
/*  76 */       { this.m_iter = dtmIterator; }
/*     */       
/*  78 */       this.m_iter.setShouldCacheNodes(true);
/*  79 */       this.m_iter.runTo(-1);
/*  80 */       this.m_iter.setCurrentPos(pos);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMIterator getDTMIterator() {
/*  90 */     return this.m_iter;
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
/*     */   public Node item(int index) {
/* 107 */     if (this.m_iter != null) {
/* 108 */       int handle = this.m_iter.item(index);
/* 109 */       if (handle == -1) {
/* 110 */         return null;
/*     */       }
/* 112 */       return this.m_iter.getDTM(handle).getNode(handle);
/*     */     } 
/* 114 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 123 */     return (this.m_iter != null) ? this.m_iter.getLength() : 0;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/DTMNodeList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */