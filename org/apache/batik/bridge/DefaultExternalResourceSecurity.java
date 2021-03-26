/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ import org.apache.batik.util.ParsedURL;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DefaultExternalResourceSecurity
/*    */   implements ExternalResourceSecurity
/*    */ {
/*    */   public static final String DATA_PROTOCOL = "data";
/*    */   public static final String ERROR_CANNOT_ACCESS_DOCUMENT_URL = "DefaultExternalResourceSecurity.error.cannot.access.document.url";
/*    */   public static final String ERROR_EXTERNAL_RESOURCE_FROM_DIFFERENT_URL = "DefaultExternalResourceSecurity.error.external.resource.from.different.url";
/*    */   protected SecurityException se;
/*    */   
/*    */   public void checkLoadExternalResource() {
/* 59 */     if (this.se != null) {
/* 60 */       this.se.fillInStackTrace();
/* 61 */       throw this.se;
/*    */     } 
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
/*    */   public DefaultExternalResourceSecurity(ParsedURL externalResourceURL, ParsedURL docURL) {
/* 77 */     if (docURL == null) {
/* 78 */       this.se = new SecurityException(Messages.formatMessage("DefaultExternalResourceSecurity.error.cannot.access.document.url", new Object[] { externalResourceURL }));
/*    */     }
/*    */     else {
/*    */       
/* 82 */       String docHost = docURL.getHost();
/* 83 */       String externalResourceHost = externalResourceURL.getHost();
/*    */       
/* 85 */       if (docHost != externalResourceHost && (docHost == null || !docHost.equals(externalResourceHost)))
/*    */       {
/*    */         
/* 88 */         if (externalResourceURL == null || !"data".equals(externalResourceURL.getProtocol()))
/*    */         {
/*    */           
/* 91 */           this.se = new SecurityException(Messages.formatMessage("DefaultExternalResourceSecurity.error.external.resource.from.different.url", new Object[] { externalResourceURL }));
/*    */         }
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/DefaultExternalResourceSecurity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */