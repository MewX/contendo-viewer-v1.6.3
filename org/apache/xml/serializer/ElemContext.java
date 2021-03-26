/*     */ package org.apache.xml.serializer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ElemContext
/*     */ {
/*     */   final int m_currentElemDepth;
/*  60 */   ElemDesc m_elementDesc = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   String m_elementLocalName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   String m_elementName = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   String m_elementURI = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean m_isCdataSection;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean m_isRaw = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ElemContext m_next;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final ElemContext m_prev;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean m_startTagOpen = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ElemContext() {
/* 118 */     this.m_prev = this;
/*     */     
/* 120 */     this.m_currentElemDepth = 0;
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
/*     */   private ElemContext(ElemContext previous) {
/* 135 */     this.m_prev = previous;
/* 136 */     previous.m_currentElemDepth++;
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
/*     */   final ElemContext pop() {
/* 149 */     return this.m_prev;
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
/*     */   final ElemContext push() {
/* 161 */     ElemContext frame = this.m_next;
/* 162 */     if (frame == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 167 */       frame = new ElemContext(this);
/* 168 */       this.m_next = frame;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 176 */     frame.m_startTagOpen = true;
/* 177 */     return frame;
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
/*     */   final ElemContext push(String uri, String localName, String qName) {
/* 195 */     ElemContext frame = this.m_next;
/* 196 */     if (frame == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 201 */       frame = new ElemContext(this);
/* 202 */       this.m_next = frame;
/*     */     } 
/*     */ 
/*     */     
/* 206 */     frame.m_elementName = qName;
/* 207 */     frame.m_elementLocalName = localName;
/* 208 */     frame.m_elementURI = uri;
/* 209 */     frame.m_isCdataSection = false;
/* 210 */     frame.m_startTagOpen = true;
/*     */ 
/*     */ 
/*     */     
/* 214 */     return frame;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/serializer/ElemContext.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */