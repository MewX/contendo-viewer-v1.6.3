/*    */ package org.apache.xpath.functions;
/*    */ 
/*    */ import javax.xml.transform.TransformerException;
/*    */ import org.apache.xml.dtm.DTM;
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
/*    */ public class FuncLang
/*    */   extends FunctionOneArg
/*    */ {
/*    */   public XObject execute(XPathContext xctxt) throws TransformerException {
/* 44 */     String lang = this.m_arg0.execute(xctxt).str();
/* 45 */     int parent = xctxt.getCurrentNode();
/* 46 */     boolean isLang = false;
/* 47 */     DTM dtm = xctxt.getDTM(parent);
/*    */     
/* 49 */     while (-1 != parent) {
/*    */       
/* 51 */       if (1 == dtm.getNodeType(parent)) {
/*    */         
/* 53 */         int langAttr = dtm.getAttributeNode(parent, "http://www.w3.org/XML/1998/namespace", "lang");
/*    */         
/* 55 */         if (-1 != langAttr) {
/*    */           
/* 57 */           String langVal = dtm.getNodeValue(langAttr);
/*    */           
/* 59 */           if (langVal.toLowerCase().startsWith(lang.toLowerCase())) {
/*    */             
/* 61 */             int valLen = lang.length();
/*    */             
/* 63 */             if (langVal.length() == valLen || langVal.charAt(valLen) == '-')
/*    */             {
/*    */               
/* 66 */               isLang = true;
/*    */             }
/*    */           } 
/*    */           
/*    */           break;
/*    */         } 
/*    */       } 
/*    */       
/* 74 */       parent = dtm.getParent(parent);
/*    */     } 
/*    */     
/* 77 */     return isLang ? (XObject)XBoolean.S_TRUE : (XObject)XBoolean.S_FALSE;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/functions/FuncLang.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */