/*    */ package org.apache.xpath.functions;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xml.utils.XMLString;
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
/*    */ public class FuncSubstringAfter
/*    */   extends Function2Args
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 44 */     XMLString s1 = this.m_arg0.execute(xctxt).xstr();
/* 45 */     XMLString s2 = this.m_arg1.execute(xctxt).xstr();
/* 46 */     int index = s1.indexOf(s2);
/*    */     
/* 48 */     return (-1 == index) ? (XObject)XString.EMPTYSTRING : (XObject)s1.substring(index + s2.length());
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncSubstringAfter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */