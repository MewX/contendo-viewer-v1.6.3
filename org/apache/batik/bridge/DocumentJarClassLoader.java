/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.security.CodeSource;
/*     */ import java.security.Permission;
/*     */ import java.security.PermissionCollection;
/*     */ import java.security.Policy;
/*     */ import java.security.cert.Certificate;
/*     */ import java.util.Enumeration;
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
/*     */ public class DocumentJarClassLoader
/*     */   extends URLClassLoader
/*     */ {
/*  47 */   protected CodeSource documentCodeSource = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DocumentJarClassLoader(URL jarURL, URL documentURL) {
/*  54 */     super(new URL[] { jarURL });
/*     */     
/*  56 */     if (documentURL != null) {
/*  57 */       this.documentCodeSource = new CodeSource(documentURL, (Certificate[])null);
/*     */     }
/*     */   }
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
/*     */   protected PermissionCollection getPermissions(CodeSource codesource) {
/*  81 */     Policy p = Policy.getPolicy();
/*     */     
/*  83 */     PermissionCollection pc = null;
/*  84 */     if (p != null) {
/*  85 */       pc = p.getPermissions(codesource);
/*     */     }
/*     */ 
/*     */     
/*  89 */     if (this.documentCodeSource != null) {
/*  90 */       PermissionCollection urlPC = super.getPermissions(this.documentCodeSource);
/*     */ 
/*     */       
/*  93 */       if (pc != null) {
/*  94 */         Enumeration<Permission> items = urlPC.elements();
/*  95 */         while (items.hasMoreElements()) {
/*  96 */           pc.add(items.nextElement());
/*     */         }
/*     */       } else {
/*  99 */         pc = urlPC;
/*     */       } 
/*     */     } 
/*     */     
/* 103 */     return pc;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/DocumentJarClassLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */