/*    */ package org.apache.xpath.functions;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xpath.XPathContext;
/*    */ import org.apache.xpath.objects.XObject;
/*    */ import org.apache.xpath.objects.XString;
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
/*    */ public class FuncSubstringBefore
/*    */   extends Function2Args
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 43 */     String s1 = this.m_arg0.execute(xctxt).str();
/* 44 */     String s2 = this.m_arg1.execute(xctxt).str();
/* 45 */     int index = s1.indexOf(s2);
/*    */     
/* 47 */     return (-1 == index) ? (XObject)XString.EMPTYSTRING : (XObject)new XString(s1.substring(0, index));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncSubstringBefore.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */