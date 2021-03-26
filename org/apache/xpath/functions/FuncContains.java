/*    */ package org.apache.xpath.functions;
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
/*    */ public class FuncContains
/*    */   extends Function2Args
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 43 */     String s1 = this.m_arg0.execute(xctxt).str();
/* 44 */     String s2 = this.m_arg1.execute(xctxt).str();
/*    */ 
/*    */     
/* 47 */     if (s1.length() == 0 && s2.length() == 0) {
/* 48 */       return (XObject)XBoolean.S_TRUE;
/*    */     }
/* 50 */     int index = s1.indexOf(s2);
/*    */     
/* 52 */     return (index > -1) ? (XObject)XBoolean.S_TRUE : (XObject)XBoolean.S_FALSE;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncContains.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */