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
/*     */ 
/*     */ 
/*     */ public class ElemFallback
/*     */   extends ElemTemplateElement
/*     */ {
/*     */   public int getXSLToken() {
/*  45 */     return 57;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*  55 */     return "fallback";
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void executeFallback(TransformerImpl transformer) throws TransformerException {
/*  95 */     int parentElemType = this.m_parentNode.getXSLToken();
/*  96 */     if (79 == parentElemType || -1 == parentElemType) {
/*     */ 
/*     */ 
/*     */       
/* 100 */       if (TransformerImpl.S_DEBUG) {
/* 101 */         transformer.getTraceManager().fireTraceEvent(this);
/*     */       }
/* 103 */       transformer.executeChildTemplates(this, true);
/*     */       
/* 105 */       if (TransformerImpl.S_DEBUG) {
/* 106 */         transformer.getTraceManager().fireTraceEndEvent(this);
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 112 */       System.out.println("Error!  parent of xsl:fallback must be an extension or unknown element!");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemFallback.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */