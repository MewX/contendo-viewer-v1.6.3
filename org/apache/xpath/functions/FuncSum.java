/*    */ package org.apache.xpath.functions;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xml.dtm.DTM;
/*    */ import org.apache.xml.dtm.DTMIterator;
/*    */ import org.apache.xml.utils.XMLString;
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
/*    */ public class FuncSum
/*    */   extends FunctionOneArg
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 46 */     DTMIterator nodes = this.m_arg0.asIterator(xctxt, xctxt.getCurrentNode());
/* 47 */     double sum = 0.0D;
/*    */     
/*    */     int pos;
/* 50 */     while (-1 != (pos = nodes.nextNode())) {
/*    */       
/* 52 */       DTM dtm = nodes.getDTM(pos);
/* 53 */       XMLString s = dtm.getStringValue(pos);
/*    */       
/* 55 */       if (null != s)
/* 56 */         sum += s.toDouble(); 
/*    */     } 
/* 58 */     nodes.detach();
/*    */     
/* 60 */     return (XObject)new XNumber(sum);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncSum.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */