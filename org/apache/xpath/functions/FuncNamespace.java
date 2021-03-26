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
/*    */ public class FuncNamespace
/*    */   extends FunctionDef1Arg
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/*    */     String str;
/* 44 */     int context = getArg0AsNode(xctxt);
/*    */ 
/*    */     
/* 47 */     if (context != -1) {
/*    */       
/* 49 */       DTM dtm = xctxt.getDTM(context);
/* 50 */       int t = dtm.getNodeType(context);
/* 51 */       if (t == 1) {
/*    */         
/* 53 */         str = dtm.getNamespaceURI(context);
/*    */       }
/* 55 */       else if (t == 2) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 61 */         str = dtm.getNodeName(context);
/* 62 */         if (str.startsWith("xmlns:") || str.equals("xmlns")) {
/* 63 */           return (XObject)XString.EMPTYSTRING;
/*    */         }
/* 65 */         str = dtm.getNamespaceURI(context);
/*    */       } else {
/*    */         
/* 68 */         return (XObject)XString.EMPTYSTRING;
/*    */       } 
/*    */     } else {
/* 71 */       return (XObject)XString.EMPTYSTRING;
/*    */     } 
/* 73 */     return (null == str) ? (XObject)XString.EMPTYSTRING : (XObject)new XString(str);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncNamespace.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */