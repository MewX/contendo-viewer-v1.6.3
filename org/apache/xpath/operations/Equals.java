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
/*    */ public class Equals
/*    */   extends Operation
/*    */ {
/*    */   public XObject operate(XObject left, XObject right) throws TransformerException {
/* 45 */     return left.equals(right) ? (XObject)XBoolean.S_TRUE : (XObject)XBoolean.S_FALSE;
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
/*    */   public boolean bool(XPathContext xctxt) throws TransformerException {
/* 62 */     XObject left = this.m_left.execute(xctxt, true);
/* 63 */     XObject right = this.m_right.execute(xctxt, true);
/*    */     
/* 65 */     boolean result = left.equals(right);
/* 66 */     left.detach();
/* 67 */     right.detach();
/* 68 */     return result;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/operations/Equals.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */