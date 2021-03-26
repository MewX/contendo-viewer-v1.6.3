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
/*    */ 
/*    */ 
/*    */ public class FuncDoclocation
/*    */   extends FunctionDef1Arg
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 45 */     int whereNode = getArg0AsNode(xctxt);
/* 46 */     String fileLocation = null;
/*    */     
/* 48 */     if (-1 != whereNode) {
/*    */       
/* 50 */       DTM dtm = xctxt.getDTM(whereNode);
/*    */ 
/*    */       
/* 53 */       if (11 == dtm.getNodeType(whereNode))
/*    */       {
/* 55 */         whereNode = dtm.getFirstChild(whereNode);
/*    */       }
/*    */       
/* 58 */       if (-1 != whereNode)
/*    */       {
/* 60 */         fileLocation = dtm.getDocumentBaseURI();
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 66 */     return (XObject)new XString((null != fileLocation) ? fileLocation : "");
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncDoclocation.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */