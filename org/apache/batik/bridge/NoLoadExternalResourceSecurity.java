/*    */ package org.apache.batik.bridge;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NoLoadExternalResourceSecurity
/*    */   implements ExternalResourceSecurity
/*    */ {
/*    */   public static final String ERROR_NO_EXTERNAL_RESOURCE_ALLOWED = "NoLoadExternalResourceSecurity.error.no.external.resource.allowed";
/*    */   protected SecurityException se;
/*    */   
/*    */   public void checkLoadExternalResource() {
/* 48 */     if (this.se != null) {
/* 49 */       this.se.fillInStackTrace();
/* 50 */       throw this.se;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public NoLoadExternalResourceSecurity() {
/* 57 */     this.se = new SecurityException(Messages.formatMessage("NoLoadExternalResourceSecurity.error.no.external.resource.allowed", null));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/NoLoadExternalResourceSecurity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */