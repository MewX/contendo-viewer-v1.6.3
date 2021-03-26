/*    */ package org.apache.xpath.functions;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xalan.transformer.TransformerImpl;
/*    */ import org.apache.xml.utils.QName;
/*    */ import org.apache.xpath.ExtensionsProvider;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FuncExtElementAvailable
/*    */   extends FunctionOneArg
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 51 */     String str1, str2, fullName = this.m_arg0.execute(xctxt).str();
/* 52 */     int indexOfNSSep = fullName.indexOf(':');
/*    */     
/* 54 */     if (indexOfNSSep < 0) {
/*    */       
/* 56 */       String prefix = "";
/* 57 */       str1 = "http://www.w3.org/1999/XSL/Transform";
/* 58 */       str2 = fullName;
/*    */     }
/*    */     else {
/*    */       
/* 62 */       String str = fullName.substring(0, indexOfNSSep);
/* 63 */       str1 = xctxt.getNamespaceContext().getNamespaceForPrefix(str);
/* 64 */       if (null == str1)
/* 65 */         return (XObject)XBoolean.S_FALSE; 
/* 66 */       str2 = fullName.substring(indexOfNSSep + 1);
/*    */     } 
/*    */     
/* 69 */     if (str1.equals("http://www.w3.org/1999/XSL/Transform") || str1.equals("http://xml.apache.org/xalan")) {
/*    */ 
/*    */       
/*    */       try { 
/*    */         
/* 74 */         TransformerImpl transformer = (TransformerImpl)xctxt.getOwnerObject(); return 
/* 75 */           transformer.getStylesheet().getAvailableElements().containsKey(new QName(str1, str2)) ? (XObject)XBoolean.S_TRUE : (XObject)XBoolean.S_FALSE; } catch (Exception e)
/*    */       
/*    */       { 
/*    */ 
/*    */ 
/*    */         
/* 81 */         return (XObject)XBoolean.S_FALSE; }
/*    */     
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 87 */     ExtensionsProvider extProvider = (ExtensionsProvider)xctxt.getOwnerObject();
/* 88 */     return extProvider.elementAvailable(str1, str2) ? (XObject)XBoolean.S_TRUE : (XObject)XBoolean.S_FALSE;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncExtElementAvailable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */