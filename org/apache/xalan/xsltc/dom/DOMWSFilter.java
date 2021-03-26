/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.DOMEnhancedForDTM;
/*     */ import org.apache.xalan.xsltc.StripFilter;
/*     */ import org.apache.xalan.xsltc.runtime.AbstractTranslet;
/*     */ import org.apache.xalan.xsltc.runtime.Hashtable;
/*     */ import org.apache.xml.dtm.DTM;
/*     */ import org.apache.xml.dtm.DTMWSFilter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DOMWSFilter
/*     */   implements DTMWSFilter
/*     */ {
/*     */   private AbstractTranslet m_translet;
/*     */   private StripFilter m_filter;
/*     */   private Hashtable m_mappings;
/*     */   private DTM m_currentDTM;
/*     */   private short[] m_currentMapping;
/*     */   
/*     */   public DOMWSFilter(AbstractTranslet translet) {
/*  57 */     this.m_translet = translet;
/*  58 */     this.m_mappings = new Hashtable();
/*     */     
/*  60 */     if (translet instanceof StripFilter) {
/*  61 */       this.m_filter = (StripFilter)translet;
/*     */     }
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
/*     */   public short getShouldStripSpace(int node, DTM dtm) {
/*  78 */     if (this.m_filter != null && dtm instanceof DOM) {
/*  79 */       DOM dom = (DOM)dtm;
/*  80 */       int type = 0;
/*     */       
/*  82 */       if (dtm instanceof DOMEnhancedForDTM) {
/*  83 */         short[] arrayOfShort; DOMEnhancedForDTM mappableDOM = (DOMEnhancedForDTM)dtm;
/*     */ 
/*     */         
/*  86 */         if (dtm == this.m_currentDTM) {
/*  87 */           arrayOfShort = this.m_currentMapping;
/*     */         } else {
/*     */           
/*  90 */           arrayOfShort = (short[])this.m_mappings.get(dtm);
/*  91 */           if (arrayOfShort == null) {
/*  92 */             arrayOfShort = mappableDOM.getMapping(this.m_translet.getNamesArray(), this.m_translet.getUrisArray(), this.m_translet.getTypesArray());
/*     */ 
/*     */ 
/*     */             
/*  96 */             this.m_mappings.put(dtm, arrayOfShort);
/*  97 */             this.m_currentDTM = dtm;
/*  98 */             this.m_currentMapping = arrayOfShort;
/*     */           } 
/*     */         } 
/*     */         
/* 102 */         int expType = mappableDOM.getExpandedTypeID(node);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 109 */         if (expType >= 0 && expType < arrayOfShort.length) {
/* 110 */           type = arrayOfShort[expType];
/*     */         } else {
/* 112 */           type = -1;
/*     */         } 
/*     */       } else {
/*     */         
/* 116 */         return 3;
/*     */       } 
/*     */       
/* 119 */       if (this.m_filter.stripSpace(dom, node, type)) {
/* 120 */         return 2;
/*     */       }
/* 122 */       return 1;
/*     */     } 
/*     */     
/* 125 */     return 1;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/DOMWSFilter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */