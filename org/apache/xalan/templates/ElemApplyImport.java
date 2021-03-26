/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import javax.xml.transform.SourceLocator;
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
/*     */ public class ElemApplyImport
/*     */   extends ElemTemplateElement
/*     */ {
/*     */   public int getXSLToken() {
/*  46 */     return 72;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*  56 */     return "apply-imports";
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/*  73 */     if (transformer.currentTemplateRuleIsNull())
/*     */     {
/*  75 */       transformer.getMsgMgr().error((SourceLocator)this, "ER_NO_APPLY_IMPORT_IN_FOR_EACH");
/*     */     }
/*     */ 
/*     */     
/*  79 */     if (TransformerImpl.S_DEBUG) {
/*  80 */       transformer.getTraceManager().fireTraceEvent(this);
/*     */     }
/*  82 */     int sourceNode = transformer.getXPathContext().getCurrentNode();
/*  83 */     if (-1 != sourceNode) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  88 */       transformer.applyTemplateToNode(this, null, sourceNode);
/*     */     }
/*     */     else {
/*     */       
/*  92 */       transformer.getMsgMgr().error((SourceLocator)this, "ER_NULL_SOURCENODE_APPLYIMPORTS");
/*     */     } 
/*     */     
/*  95 */     if (TransformerImpl.S_DEBUG) {
/*  96 */       transformer.getTraceManager().fireTraceEndEvent(this);
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
/*     */   public ElemTemplateElement appendChild(ElemTemplateElement newChild) {
/* 110 */     error("ER_CANNOT_ADD", new Object[] { newChild.getNodeName(), getNodeName() });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemApplyImport.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */