/*    */ package org.apache.xalan.extensions;
/*    */ 
/*    */ import java.util.Hashtable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ExtensionHandlerJava
/*    */   extends ExtensionHandler
/*    */ {
/* 34 */   protected String m_className = "";
/*    */ 
/*    */   
/* 37 */   private Hashtable m_cachedMethods = new Hashtable();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected ExtensionHandlerJava(String namespaceUri, String scriptLang, String className) {
/* 57 */     super(namespaceUri, scriptLang);
/*    */     
/* 59 */     this.m_className = className;
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
/*    */   public Object getFromCache(Object methodKey, Object objType, Object[] methodArgs) {
/* 77 */     return this.m_cachedMethods.get(methodKey);
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
/*    */   
/*    */   public Object putToCache(Object methodKey, Object objType, Object[] methodArgs, Object methodObj) {
/* 96 */     return this.m_cachedMethods.put(methodKey, methodObj);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/extensions/ExtensionHandlerJava.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */