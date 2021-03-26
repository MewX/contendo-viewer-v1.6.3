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
/*    */ class p
/*    */   implements PrivilegedAction
/*    */ {
/*    */   private final n a;
/*    */   
/*    */   p(n paramn) {
/* 52 */     this.a = paramn;
/*    */   } public Object run() {
/* 54 */     ClassLoader cl = null;
/*    */     try {
/* 56 */       cl = ClassLoader.getSystemClassLoader();
/* 57 */     } catch (SecurityException securityException) {}
/* 58 */     return cl;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/p.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */