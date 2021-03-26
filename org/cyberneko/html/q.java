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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class q
/*    */   implements PrivilegedAction
/*    */ {
/*    */   private final ClassLoader a;
/*    */   private final n b;
/*    */   
/*    */   q(n paramn, ClassLoader paramClassLoader) {
/* 65 */     this.b = paramn; this.a = paramClassLoader;
/*    */   } public Object run() {
/* 67 */     ClassLoader parent = null;
/*    */     try {
/* 69 */       parent = this.a.getParent();
/* 70 */     } catch (SecurityException securityException) {}
/*    */ 
/*    */ 
/*    */     
/* 74 */     return (parent == this.a) ? null : parent;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/q.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */