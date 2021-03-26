/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ElemUnknown
/*     */   extends ElemLiteralResult
/*     */ {
/*     */   public int getXSLToken() {
/*  43 */     return -1;
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
/*     */   private void executeFallbacks(TransformerImpl transformer) throws TransformerException {
/*  59 */     for (ElemTemplateElement child = this.m_firstChild; child != null; 
/*  60 */       child = child.m_nextSibling) {
/*     */       
/*  62 */       if (child.getXSLToken() == 57) {
/*     */         
/*     */         try {
/*     */           
/*  66 */           transformer.pushElemTemplateElement(child);
/*  67 */           ((ElemFallback)child).executeFallback(transformer);
/*     */         }
/*     */         finally {
/*     */           
/*  71 */           transformer.popElemTemplateElement();
/*     */         } 
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
/*     */   
/*     */   private boolean hasFallbackChildren() {
/*  85 */     for (ElemTemplateElement child = this.m_firstChild; child != null; 
/*  86 */       child = child.m_nextSibling) {
/*     */       
/*  88 */       if (child.getXSLToken() == 57) {
/*  89 */         return true;
/*     */       }
/*     */     } 
/*  92 */     return false;
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 111 */     if (TransformerImpl.S_DEBUG) {
/* 112 */       transformer.getTraceManager().fireTraceEvent(this);
/*     */     }
/*     */     
/*     */     try {
/* 116 */       if (hasFallbackChildren())
/* 117 */         executeFallbacks(transformer); 
/* 118 */     } catch (TransformerException e) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 123 */       transformer.getErrorListener().fatalError(e);
/*     */     } 
/* 125 */     if (TransformerImpl.S_DEBUG)
/* 126 */       transformer.getTraceManager().fireTraceEndEvent(this); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemUnknown.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */