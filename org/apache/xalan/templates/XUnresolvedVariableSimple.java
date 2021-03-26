/*    */ package org.apache.xalan.templates;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xpath.Expression;
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
/*    */ public class XUnresolvedVariableSimple
/*    */   extends XObject
/*    */ {
/*    */   public XUnresolvedVariableSimple(ElemVariable obj) {
/* 37 */     super(obj);
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
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 52 */     Expression expr = ((ElemVariable)this.m_obj).getSelect().getExpression();
/* 53 */     XObject xobj = expr.execute(xctxt);
/* 54 */     xobj.allowDetachToRelease(false);
/* 55 */     return xobj;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getType() {
/* 65 */     return 600;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getTypeString() {
/* 76 */     return "XUnresolvedVariableSimple (" + object().getClass().getName() + ")";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/templates/XUnresolvedVariableSimple.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */