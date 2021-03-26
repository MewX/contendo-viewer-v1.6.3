/*    */ package org.apache.xpath.functions;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xpath.ExtensionsProvider;
/*    */ import org.apache.xpath.XPathContext;
/*    */ import org.apache.xpath.compiler.Keywords;
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
/*    */ public class FuncExtFunctionAvailable
/*    */   extends FunctionOneArg
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 50 */     String str1, str2, fullName = this.m_arg0.execute(xctxt).str();
/* 51 */     int indexOfNSSep = fullName.indexOf(':');
/*    */     
/* 53 */     if (indexOfNSSep < 0) {
/*    */       
/* 55 */       String prefix = "";
/* 56 */       str1 = "http://www.w3.org/1999/XSL/Transform";
/* 57 */       str2 = fullName;
/*    */     }
/*    */     else {
/*    */       
/* 61 */       String str = fullName.substring(0, indexOfNSSep);
/* 62 */       str1 = xctxt.getNamespaceContext().getNamespaceForPrefix(str);
/* 63 */       if (null == str1)
/* 64 */         return (XObject)XBoolean.S_FALSE; 
/* 65 */       str2 = fullName.substring(indexOfNSSep + 1);
/*    */     } 
/*    */     
/* 68 */     if (str1.equals("http://www.w3.org/1999/XSL/Transform")) {
/*    */ 
/*    */       
/*    */       try { 
/* 72 */         return Keywords.functionAvailable(str2) ? (XObject)XBoolean.S_TRUE : (XObject)XBoolean.S_FALSE; } catch (Exception e)
/*    */       
/*    */       { 
/*    */         
/* 76 */         return (XObject)XBoolean.S_FALSE; }
/*    */     
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 82 */     ExtensionsProvider extProvider = (ExtensionsProvider)xctxt.getOwnerObject();
/* 83 */     return extProvider.functionAvailable(str1, str2) ? (XObject)XBoolean.S_TRUE : (XObject)XBoolean.S_FALSE;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncExtFunctionAvailable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */