/*     */ package org.cyberneko.html;
/*     */ 
/*     */ import java.io.InputStream;
/*     */ import java.security.PrivilegedAction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class t
/*     */   implements PrivilegedAction
/*     */ {
/*     */   private final ClassLoader a;
/*     */   private final String b;
/*     */   private final n c;
/*     */   
/*     */   t(n paramn, ClassLoader paramClassLoader, String paramString) {
/* 107 */     this.c = paramn; this.a = paramClassLoader; this.b = paramString;
/*     */   } public Object run() {
/*     */     InputStream ris;
/* 110 */     if (this.a == null) {
/* 111 */       ris = ClassLoader.getSystemResourceAsStream(this.b);
/*     */     } else {
/* 113 */       ris = this.a.getResourceAsStream(this.b);
/*     */     } 
/* 115 */     return ris;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/t.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */