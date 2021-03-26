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
/*    */ public class DefaultScriptSecurity
/*    */   implements ScriptSecurity
/*    */ {
/*    */   public static final String DATA_PROTOCOL = "data";
/*    */   public static final String ERROR_CANNOT_ACCESS_DOCUMENT_URL = "DefaultScriptSecurity.error.cannot.access.document.url";
/*    */   public static final String ERROR_SCRIPT_FROM_DIFFERENT_URL = "DefaultScriptSecurity.error.script.from.different.url";
/*    */   protected SecurityException se;
/*    */   
/*    */   public void checkLoadScript() {
/* 59 */     if (this.se != null) {
/* 60 */       throw this.se;
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
/*    */ 
/*    */ 
/*    */   
/*    */   public DefaultScriptSecurity(String scriptType, ParsedURL scriptURL, ParsedURL docURL) {
/* 79 */     if (docURL == null) {
/* 80 */       this.se = new SecurityException(Messages.formatMessage("DefaultScriptSecurity.error.cannot.access.document.url", new Object[] { scriptURL }));
/*    */     }
/*    */     else {
/*    */       
/* 84 */       String docHost = docURL.getHost();
/* 85 */       String scriptHost = scriptURL.getHost();
/*    */       
/* 87 */       if (docHost != scriptHost && (docHost == null || !docHost.equals(scriptHost)))
/*    */       {
/* 89 */         if (!docURL.equals(scriptURL) && (scriptURL == null || !"data".equals(scriptURL.getProtocol())))
/*    */         {
/*    */ 
/*    */ 
/*    */           
/* 94 */           this.se = new SecurityException(Messages.formatMessage("DefaultScriptSecurity.error.script.from.different.url", new Object[] { scriptURL }));
/*    */         }
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/DefaultScriptSecurity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */