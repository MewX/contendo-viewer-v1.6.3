/*    */ package org.cyberneko.html;
/*    */ 
/*    */ import java.security.PrivilegedAction;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class o
/*    */   implements PrivilegedAction
/*    */ {
/*    */   private final n a;
/*    */   
/*    */   o(n paramn) {
/* 39 */     this.a = paramn;
/*    */   } public Object run() {
/* 41 */     ClassLoader cl = null;
/*    */     try {
/* 43 */       cl = Thread.currentThread().getContextClassLoader();
/* 44 */     } catch (SecurityException securityException) {}
/* 45 */     return cl;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/o.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */