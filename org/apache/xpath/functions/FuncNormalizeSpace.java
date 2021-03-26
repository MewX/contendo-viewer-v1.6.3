/*    */ package org.apache.xpath.functions;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xml.dtm.DTM;
/*    */ import org.apache.xml.utils.XMLString;
/*    */ import org.apache.xpath.XPathContext;
/*    */ import org.apache.xpath.objects.XObject;
/*    */ import org.xml.sax.ContentHandler;
/*    */ import org.xml.sax.SAXException;
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
/*    */ public class FuncNormalizeSpace
/*    */   extends FunctionDef1Arg
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 45 */     XMLString s1 = getArg0AsString(xctxt);
/*    */     
/* 47 */     return (XObject)s1.fixWhiteSpace(true, true, false);
/*    */   }
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
/*    */   public void executeCharsToContentHandler(XPathContext xctxt, ContentHandler handler) throws TransformerException, SAXException {
/* 67 */     if (Arg0IsNodesetExpr()) {
/*    */       
/* 69 */       int node = getArg0AsNode(xctxt);
/* 70 */       if (-1 != node)
/*    */       {
/* 72 */         DTM dtm = xctxt.getDTM(node);
/* 73 */         dtm.dispatchCharactersEvents(node, handler, true);
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 78 */       XObject obj = execute(xctxt);
/* 79 */       obj.dispatchCharactersEvents(handler);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncNormalizeSpace.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */