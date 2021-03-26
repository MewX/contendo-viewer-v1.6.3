/*     */ package org.apache.xalan.templates;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xalan.transformer.TransformerImpl;
/*     */ import org.apache.xpath.ExpressionOwner;
/*     */ import org.apache.xpath.XPath;
/*     */ import org.apache.xpath.XPathContext;
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
/*     */ public class ElemIf
/*     */   extends ElemTemplateElement
/*     */ {
/*  47 */   private XPath m_test = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTest(XPath v) {
/*  57 */     this.m_test = v;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XPath getTest() {
/*  68 */     return this.m_test;
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
/*     */   public void compose(StylesheetRoot sroot) throws TransformerException {
/*  84 */     super.compose(sroot);
/*     */     
/*  86 */     Vector vnames = sroot.getComposeState().getVariableNames();
/*     */     
/*  88 */     if (null != this.m_test) {
/*  89 */       this.m_test.fixupVariables(vnames, sroot.getComposeState().getGlobalsSize());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getXSLToken() {
/* 100 */     return 36;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNodeName() {
/* 110 */     return "if";
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
/* 129 */     XPathContext xctxt = transformer.getXPathContext();
/* 130 */     int sourceNode = xctxt.getCurrentNode();
/*     */     
/* 132 */     if (TransformerImpl.S_DEBUG) {
/*     */       
/* 134 */       XObject test = this.m_test.execute(xctxt, sourceNode, this);
/*     */       
/* 136 */       if (TransformerImpl.S_DEBUG) {
/* 137 */         transformer.getTraceManager().fireSelectedEvent(sourceNode, this, "test", this.m_test, test);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 143 */       if (TransformerImpl.S_DEBUG) {
/* 144 */         transformer.getTraceManager().fireTraceEvent(this);
/*     */       }
/* 146 */       if (test.bool())
/*     */       {
/* 148 */         transformer.executeChildTemplates(this, true);
/*     */       }
/*     */       
/* 151 */       if (TransformerImpl.S_DEBUG) {
/* 152 */         transformer.getTraceManager().fireTraceEndEvent(this);
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 159 */     else if (this.m_test.bool(xctxt, sourceNode, this)) {
/*     */       
/* 161 */       transformer.executeChildTemplates(this, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void callChildVisitors(XSLTVisitor visitor, boolean callAttrs) {
/* 172 */     if (callAttrs)
/* 173 */       this.m_test.getExpression().callVisitors((ExpressionOwner)this.m_test, visitor); 
/* 174 */     super.callChildVisitors(visitor, callAttrs);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemIf.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */