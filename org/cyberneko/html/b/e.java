/*    */ package org.cyberneko.html.b;
/*    */ 
/*    */ import org.apache.xerces.xni.NamespaceContext;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class e
/*    */   extends d
/*    */ {
/*    */   static Class a;
/*    */   static Class b;
/*    */   
/*    */   public e() throws InstantiationException {
/*    */     try {
/* 32 */       Class[] args = { (a == null) ? (a = a("java.lang.String")) : a, (a == null) ? (a = a("java.lang.String")) : a };
/* 33 */       ((b == null) ? (b = a("org.apache.xerces.xni.NamespaceContext")) : b).getMethod("declarePrefix", args);
/*    */     }
/* 35 */     catch (NoSuchMethodException noSuchMethodException) {
/*    */       
/* 37 */       throw new InstantiationException(noSuchMethodException.getMessage());
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(NamespaceContext namespaceContext, String ns, String avalue) {
/* 43 */     namespaceContext.declarePrefix(ns, avalue);
/*    */   }
/*    */   
/*    */   static Class a(String x0) {
/*    */     try {
/*    */       return Class.forName(x0);
/*    */     } catch (ClassNotFoundException x1) {
/*    */       throw new NoClassDefFoundError(x1.getMessage());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/b/e.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */