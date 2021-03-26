/*    */ package org.apache.xpath.operations;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xpath.XPathContext;
/*    */ import org.apache.xpath.objects.XNumber;
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
/*    */ 
/*    */ public class Minus
/*    */   extends Operation
/*    */ {
/*    */   public XObject operate(XObject left, XObject right) throws TransformerException {
/* 46 */     return (XObject)new XNumber(left.num() - right.num());
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
/*    */   public double num(XPathContext xctxt) throws TransformerException {
/* 62 */     return this.m_left.num(xctxt) - this.m_right.num(xctxt);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/operations/Minus.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */