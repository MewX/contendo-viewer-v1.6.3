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
/*    */ public class EmbededExternalResourceSecurity
/*    */   implements ExternalResourceSecurity
/*    */ {
/*    */   public static final String DATA_PROTOCOL = "data";
/*    */   public static final String ERROR_EXTERNAL_RESOURCE_NOT_EMBEDED = "EmbededExternalResourceSecurity.error.external.resource.not.embeded";
/*    */   protected SecurityException se;
/*    */   
/*    */   public void checkLoadExternalResource() {
/* 53 */     if (this.se != null) {
/* 54 */       throw this.se;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EmbededExternalResourceSecurity(ParsedURL externalResourceURL) {
/* 65 */     if (externalResourceURL == null || !"data".equals(externalResourceURL.getProtocol()))
/*    */     {
/*    */       
/* 68 */       this.se = new SecurityException(Messages.formatMessage("EmbededExternalResourceSecurity.error.external.resource.not.embeded", new Object[] { externalResourceURL }));
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/EmbededExternalResourceSecurity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */