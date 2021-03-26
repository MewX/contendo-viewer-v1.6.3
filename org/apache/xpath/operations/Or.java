/*    */ package org.apache.xpath.operations;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xpath.XPathContext;
/*    */ import org.apache.xpath.objects.XBoolean;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Or
/*    */   extends Operation
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 45 */     XObject expr1 = this.m_left.execute(xctxt);
/*    */     
/* 47 */     if (!expr1.bool()) {
/*    */       
/* 49 */       XObject expr2 = this.m_right.execute(xctxt);
/*    */       
/* 51 */       return expr2.bool() ? (XObject)XBoolean.S_TRUE : (XObject)XBoolean.S_FALSE;
/*    */     } 
/*    */     
/* 54 */     return (XObject)XBoolean.S_TRUE;
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
/*    */   public boolean bool(XPathContext xctxt) throws TransformerException {
/* 69 */     return (this.m_left.bool(xctxt) || this.m_right.bool(xctxt));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/operations/Or.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */