/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.xml.serializer.Serializer;
/*     */ import org.apache.xml.utils.MutableAttrListImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class QueuedEvents
/*     */ {
/*  35 */   protected int m_eventCount = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_docPending = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean m_docEnded = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_elemIsPending = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean m_elemIsEnded = false;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected MutableAttrListImpl m_attributes = new MutableAttrListImpl();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean m_nsDeclsHaveBeenAdded = false;
/*     */ 
/*     */ 
/*     */   
/*     */   protected String m_name;
/*     */ 
/*     */ 
/*     */   
/*     */   protected String m_url;
/*     */ 
/*     */ 
/*     */   
/*     */   protected String m_localName;
/*     */ 
/*     */ 
/*     */   
/*  81 */   protected Vector m_namespaces = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Serializer m_serializer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reInitEvents() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/* 107 */     pushDocumentEvent();
/* 108 */     reInitEvents();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void pushDocumentEvent() {
/* 119 */     this.m_docPending = true;
/*     */     
/* 121 */     this.m_eventCount++;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void popEvent() {
/* 130 */     this.m_elemIsPending = false;
/* 131 */     this.m_attributes.clear();
/*     */     
/* 133 */     this.m_nsDeclsHaveBeenAdded = false;
/* 134 */     this.m_name = null;
/* 135 */     this.m_url = null;
/* 136 */     this.m_localName = null;
/* 137 */     this.m_namespaces = null;
/*     */     
/* 139 */     this.m_eventCount--;
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
/*     */   void setSerializer(Serializer s) {
/* 153 */     this.m_serializer = s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Serializer getSerializer() {
/* 164 */     return this.m_serializer;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/QueuedEvents.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */