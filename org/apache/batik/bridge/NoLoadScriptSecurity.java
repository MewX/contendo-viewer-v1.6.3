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
/*    */ 
/*    */ public class NoLoadScriptSecurity
/*    */   implements ScriptSecurity
/*    */ {
/*    */   public static final String ERROR_NO_SCRIPT_OF_TYPE_ALLOWED = "NoLoadScriptSecurity.error.no.script.of.type.allowed";
/*    */   protected SecurityException se;
/*    */   
/*    */   public void checkLoadScript() {
/* 49 */     throw this.se;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public NoLoadScriptSecurity(String scriptType) {
/* 59 */     this.se = new SecurityException(Messages.formatMessage("NoLoadScriptSecurity.error.no.script.of.type.allowed", new Object[] { scriptType }));
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/NoLoadScriptSecurity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */