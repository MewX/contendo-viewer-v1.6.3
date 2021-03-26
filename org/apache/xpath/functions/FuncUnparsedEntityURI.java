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
/*    */ public class FuncUnparsedEntityURI
/*    */   extends FunctionOneArg
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 43 */     String name = this.m_arg0.execute(xctxt).str();
/* 44 */     int context = xctxt.getCurrentNode();
/* 45 */     DTM dtm = xctxt.getDTM(context);
/* 46 */     int doc = dtm.getDocument();
/*    */     
/* 48 */     String uri = dtm.getUnparsedEntityURI(name);
/*    */     
/* 50 */     return (XObject)new XString(uri);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncUnparsedEntityURI.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */