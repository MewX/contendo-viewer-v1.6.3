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
/*    */ public class FuncLocalPart
/*    */   extends FunctionDef1Arg
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 44 */     int context = getArg0AsNode(xctxt);
/* 45 */     if (-1 == context)
/* 46 */       return (XObject)XString.EMPTYSTRING; 
/* 47 */     DTM dtm = xctxt.getDTM(context);
/* 48 */     String s = (context != -1) ? dtm.getLocalName(context) : "";
/* 49 */     if (s.startsWith("#") || s.equals("xmlns")) {
/* 50 */       return (XObject)XString.EMPTYSTRING;
/*    */     }
/* 52 */     return (XObject)new XString(s);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncLocalPart.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */