/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ import org.apache.xml.dtm.DTMDOMException;
/*     */ import org.apache.xml.dtm.DTMIterator;
/*     */ import org.apache.xml.utils.WrappedRuntimeException;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.traversal.NodeFilter;
/*     */ import org.w3c.dom.traversal.NodeIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DTMNodeIterator
/*     */   implements NodeIterator
/*     */ {
/*     */   private DTMIterator dtm_iter;
/*     */   private boolean valid = true;
/*     */   
/*     */   public DTMNodeIterator(DTMIterator dtmIterator) {
/*     */     
/*  72 */     try { this.dtm_iter = (DTMIterator)dtmIterator.clone(); } catch (CloneNotSupportedException cnse)
/*     */     
/*     */     { 
/*     */       
/*  76 */       throw new WrappedRuntimeException(cnse); }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DTMIterator getDTMIterator() {
/*  85 */     return this.dtm_iter;
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
/*     */   public void detach() {
/* 101 */     this.valid = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getExpandEntityReferences() {
/* 111 */     return false;
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
/*     */   public NodeFilter getFilter() {
/* 128 */     throw new DTMDOMException((short)9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node getRoot() {
/* 137 */     int handle = this.dtm_iter.getRoot();
/* 138 */     return this.dtm_iter.getDTM(handle).getNode(handle);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWhatToShow() {
/* 147 */     return this.dtm_iter.getWhatToShow();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Node nextNode() throws DOMException {
/* 158 */     if (!this.valid) {
/* 159 */       throw new DTMDOMException((short)11);
/*     */     }
/* 161 */     int handle = this.dtm_iter.nextNode();
/* 162 */     if (handle == -1)
/* 163 */       return null; 
/* 164 */     return this.dtm_iter.getDTM(handle).getNode(handle);
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
/*     */   public Node previousNode() {
/* 176 */     if (!this.valid) {
/* 177 */       throw new DTMDOMException((short)11);
/*     */     }
/* 179 */     int handle = this.dtm_iter.previousNode();
/* 180 */     if (handle == -1)
/* 181 */       return null; 
/* 182 */     return this.dtm_iter.getDTM(handle).getNode(handle);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/DTMNodeIterator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */