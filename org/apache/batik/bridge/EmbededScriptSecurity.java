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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EmbededScriptSecurity
/*    */   implements ScriptSecurity
/*    */ {
/*    */   public static final String DATA_PROTOCOL = "data";
/*    */   public static final String ERROR_CANNOT_ACCESS_DOCUMENT_URL = "DefaultScriptSecurity.error.cannot.access.document.url";
/*    */   public static final String ERROR_SCRIPT_NOT_EMBEDED = "EmbededScriptSecurity.error.script.not.embeded";
/*    */   protected SecurityException se;
/*    */   
/*    */   public void checkLoadScript() {
/* 62 */     if (this.se != null) {
/* 63 */       throw this.se;
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
/*    */   public EmbededScriptSecurity(String scriptType, ParsedURL scriptURL, ParsedURL docURL) {
/* 82 */     if (docURL == null) {
/* 83 */       this.se = new SecurityException(Messages.formatMessage("DefaultScriptSecurity.error.cannot.access.document.url", new Object[] { scriptURL }));
/*    */ 
/*    */     
/*    */     }
/* 87 */     else if (!docURL.equals(scriptURL) && (scriptURL == null || !"data".equals(scriptURL.getProtocol()))) {
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 92 */       this.se = new SecurityException(Messages.formatMessage("EmbededScriptSecurity.error.script.not.embeded", new Object[] { scriptURL }));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/EmbededScriptSecurity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */