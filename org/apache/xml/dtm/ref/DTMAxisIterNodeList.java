/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
/*     */ import org.apache.xml.utils.IntVector;
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
/*     */ public class DTMAxisIterNodeList
/*     */   extends DTMNodeListBase
/*     */ {
/*     */   private DTM m_dtm;
/*     */   private DTMAxisIterator m_iter;
/*     */   private IntVector m_cachedNodes;
/*  60 */   private int m_last = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DTMAxisIterNodeList() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterNodeList(DTM dtm, DTMAxisIterator dtmAxisIterator) {
/*  71 */     if (dtmAxisIterator == null) {
/*  72 */       this.m_last = 0;
/*     */     } else {
/*  74 */       this.m_cachedNodes = new IntVector();
/*  75 */       this.m_dtm = dtm;
/*     */     } 
/*  77 */     this.m_iter = dtmAxisIterator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMAxisIterator getDTMAxisIterator() {
/*  86 */     return this.m_iter;
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
/* 103 */     if (this.m_iter != null) {
/*     */       
/* 105 */       int count = this.m_cachedNodes.size();
/*     */       
/* 107 */       if (count > index) {
/* 108 */         int node = this.m_cachedNodes.elementAt(index);
/* 109 */         return this.m_dtm.getNode(node);
/* 110 */       }  if (this.m_last == -1) {
/*     */         int i;
/* 112 */         while ((i = this.m_iter.next()) != -1 && count <= index) {
/* 113 */           this.m_cachedNodes.addElement(i);
/* 114 */           count++;
/*     */         } 
/* 116 */         if (i == -1) {
/* 117 */           this.m_last = count;
/*     */         } else {
/* 119 */           return this.m_dtm.getNode(i);
/*     */         } 
/*     */       } 
/*     */     } 
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 131 */     if (this.m_last == -1) {
/*     */       int node;
/* 133 */       while ((node = this.m_iter.next()) != -1) {
/* 134 */         this.m_cachedNodes.addElement(node);
/*     */       }
/* 136 */       this.m_last = this.m_cachedNodes.size();
/*     */     } 
/* 138 */     return this.m_last;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/DTMAxisIterNodeList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */