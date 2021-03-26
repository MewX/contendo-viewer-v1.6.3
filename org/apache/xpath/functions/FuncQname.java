/*    */ package org.apache.xpath.functions;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xml.dtm.DTM;
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
/*    */ public class FuncQname
/*    */   extends FunctionDef1Arg
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/*    */     XString xString;
/* 44 */     int context = getArg0AsNode(xctxt);
/*    */ 
/*    */     
/* 47 */     if (-1 != context) {
/*    */       
/* 49 */       DTM dtm = xctxt.getDTM(context);
/* 50 */       String qname = dtm.getNodeNameX(context);
/* 51 */       xString = (null == qname) ? XString.EMPTYSTRING : new XString(qname);
/*    */     }
/*    */     else {
/*    */       
/* 55 */       xString = XString.EMPTYSTRING;
/*    */     } 
/*    */     
/* 58 */     return (XObject)xString;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncQname.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */