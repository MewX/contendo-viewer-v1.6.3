/*    */ package org.apache.xpath.operations;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
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
/*    */ public class Gt
/*    */   extends Operation
/*    */ {
/*    */   public XObject operate(XObject left, XObject right) throws TransformerException {
/* 44 */     return left.greaterThan(right) ? (XObject)XBoolean.S_TRUE : (XObject)XBoolean.S_FALSE;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/operations/Gt.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */