/*    */ package org.apache.xalan.templates;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xalan.transformer.TransformerImpl;
/*    */ import org.apache.xpath.XPathContext;
/*    */ import org.apache.xpath.objects.XObject;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ElemExsltFuncResult
/*    */   extends ElemVariable
/*    */ {
/*    */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 40 */     XPathContext context = transformer.getXPathContext();
/*    */     
/* 42 */     if (TransformerImpl.S_DEBUG) {
/* 43 */       transformer.getTraceManager().fireTraceEvent(this);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 48 */     if (transformer.currentFuncResultSeen()) {
/* 49 */       throw new TransformerException("An EXSLT function cannot set more than one result!");
/*    */     }
/*    */     
/* 52 */     int sourceNode = context.getCurrentNode();
/*    */ 
/*    */     
/* 55 */     XObject var = getValue(transformer, sourceNode);
/* 56 */     transformer.popCurrentFuncResult();
/* 57 */     transformer.pushCurrentFuncResult(var);
/*    */     
/* 59 */     if (TransformerImpl.S_DEBUG) {
/* 60 */       transformer.getTraceManager().fireTraceEndEvent(this);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getXSLToken() {
/* 72 */     return 89;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNodeName() {
/* 84 */     return "result";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemExsltFuncResult.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */