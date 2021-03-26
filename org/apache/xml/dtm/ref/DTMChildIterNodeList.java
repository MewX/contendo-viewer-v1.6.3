/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ import org.apache.xml.dtm.DTM;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DTMChildIterNodeList
/*     */   extends DTMNodeListBase
/*     */ {
/*     */   private int m_firstChild;
/*     */   private DTM m_parentDTM;
/*     */   
/*     */   private DTMChildIterNodeList() {}
/*     */   
/*     */   public DTMChildIterNodeList(DTM parentDTM, int parentHandle) {
/*  76 */     this.m_parentDTM = parentDTM;
/*  77 */     this.m_firstChild = parentDTM.getFirstChild(parentHandle);
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
/*  94 */     int handle = this.m_firstChild;
/*  95 */     while (--index >= 0 && handle != -1) {
/*  96 */       handle = this.m_parentDTM.getNextSibling(handle);
/*     */     }
/*  98 */     if (handle == -1) {
/*  99 */       return null;
/*     */     }
/* 101 */     return this.m_parentDTM.getNode(handle);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 109 */     int count = 0;
/* 110 */     int handle = this.m_firstChild;
/* 111 */     for (; handle != -1; 
/* 112 */       handle = this.m_parentDTM.getNextSibling(handle)) {
/* 113 */       count++;
/*     */     }
/* 115 */     return count;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/DTMChildIterNodeList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */