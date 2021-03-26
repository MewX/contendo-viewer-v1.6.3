/*    */ package org.apache.xalan.res;
/*    */ 
/*    */ import java.util.ListResourceBundle;
/*    */ import org.apache.xml.res.XMLMessages;
/*    */ import org.apache.xpath.res.XPATHMessages;
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
/*    */ public class XSLMessages
/*    */   extends XPATHMessages
/*    */ {
/* 34 */   private static ListResourceBundle XSLTBundle = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static final String XSLT_ERROR_RESOURCES = "org.apache.xalan.res.XSLTErrorResources";
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static final String createMessage(String msgKey, Object[] args) {
/* 52 */     if (XSLTBundle == null) {
/* 53 */       XSLTBundle = XMLMessages.loadResourceBundle("org.apache.xalan.res.XSLTErrorResources");
/*    */     }
/* 55 */     if (XSLTBundle != null)
/*    */     {
/* 57 */       return XMLMessages.createMsg(XSLTBundle, msgKey, args);
/*    */     }
/*    */     
/* 60 */     return "Could not load any resource bundles.";
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
/*    */   public static final String createWarning(String msgKey, Object[] args) {
/* 75 */     if (XSLTBundle == null) {
/* 76 */       XSLTBundle = XMLMessages.loadResourceBundle("org.apache.xalan.res.XSLTErrorResources");
/*    */     }
/* 78 */     if (XSLTBundle != null)
/*    */     {
/* 80 */       return XMLMessages.createMsg(XSLTBundle, msgKey, args);
/*    */     }
/*    */     
/* 83 */     return "Could not load any resource bundles.";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/res/XSLMessages.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */