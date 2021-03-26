/*    */ package org.apache.xalan.templates;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xalan.transformer.TransformerImpl;
/*    */ import org.apache.xpath.XPath;
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
/*    */ 
/*    */ public class ElemVariablePsuedo
/*    */   extends ElemVariable
/*    */ {
/*    */   XUnresolvedVariableSimple m_lazyVar;
/*    */   
/*    */   public void setSelect(XPath v) {
/* 42 */     super.setSelect(v);
/* 43 */     this.m_lazyVar = new XUnresolvedVariableSimple(this);
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void execute(TransformerImpl transformer) throws TransformerException {
/* 63 */     transformer.getXPathContext().getVarStack().setLocalVariable(this.m_index, this.m_lazyVar);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/ElemVariablePsuedo.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */