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
/*    */ public class FuncRound
/*    */   extends FunctionOneArg
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 42 */     XObject obj = this.m_arg0.execute(xctxt);
/* 43 */     double val = obj.num();
/* 44 */     if (val >= -0.5D && val < 0.0D) return (XObject)new XNumber(-0.0D); 
/* 45 */     if (val == 0.0D) return (XObject)new XNumber(val); 
/* 46 */     return (XObject)new XNumber(Math.floor(val + 0.5D));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncRound.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */