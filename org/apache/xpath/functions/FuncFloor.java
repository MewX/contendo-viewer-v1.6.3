/*    */ package org.apache.xpath.functions;
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
/*    */ public class FuncFloor
/*    */   extends FunctionOneArg
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 42 */     return (XObject)new XNumber(Math.floor(this.m_arg0.execute(xctxt).num()));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncFloor.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */