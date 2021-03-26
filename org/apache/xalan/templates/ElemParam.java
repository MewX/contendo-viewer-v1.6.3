/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xpath.VariableStack;
/*     */ import org.apache.xpath.objects.XObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ElemParam
/*     */   extends ElemVariable
/*     */ {
/*     */   int m_qnameID;
/*     */   
/*     */   public ElemParam() {}
/*     */   
/*     */   public int getXSLToken() {
/*  57 */     return 41;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/*  67 */     return "param";
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
/*     */   public ElemParam(ElemParam param) throws TransformerException {
/*  79 */     super(param);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void compose(StylesheetRoot sroot) throws TransformerException {
/*  90 */     super.compose(sroot);
/*  91 */     this.m_qnameID = sroot.getComposeState().getQNameID(this.m_qname);
/*  92 */     int parentToken = this.m_parentNode.getXSLToken();
/*  93 */     if (parentToken == 19 || parentToken == 88)
/*     */     {
/*  95 */       ((ElemTemplate)this.m_parentNode).m_inArgsSize++;
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
/*     */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 110 */     if (TransformerImpl.S_DEBUG) {
/* 111 */       transformer.getTraceManager().fireTraceEvent(this);
/*     */     }
/* 113 */     VariableStack vars = transformer.getXPathContext().getVarStack();
/*     */     
/* 115 */     if (!vars.isLocalSet(this.m_index)) {
/*     */ 
/*     */       
/* 118 */       int sourceNode = transformer.getXPathContext().getCurrentNode();
/* 119 */       XObject var = getValue(transformer, sourceNode);
/*     */ 
/*     */       
/* 122 */       transformer.getXPathContext().getVarStack().setLocalVariable(this.m_index, var);
/*     */     } 
/*     */     
/* 125 */     if (TransformerImpl.S_DEBUG)
/* 126 */       transformer.getTraceManager().fireTraceEndEvent(this); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemParam.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */