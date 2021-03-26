/*     */ package org.apache.batik.script.rhino;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FilePermission;
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.security.AccessControlContext;
/*     */ import java.security.CodeSource;
/*     */ import java.security.Permission;
/*     */ import java.security.PermissionCollection;
/*     */ import java.security.ProtectionDomain;
/*     */ import java.security.cert.Certificate;
/*     */ import org.mozilla.javascript.GeneratedClassLoader;
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
/*     */ public class RhinoClassLoader
/*     */   extends URLClassLoader
/*     */   implements GeneratedClassLoader
/*     */ {
/*     */   protected URL documentURL;
/*     */   protected CodeSource codeSource;
/*     */   protected AccessControlContext rhinoAccessControlContext;
/*     */   
/*     */   public RhinoClassLoader(URL documentURL, ClassLoader parent) {
/*  67 */     super((documentURL != null) ? new URL[1] : new URL[0], parent);
/*     */     
/*  69 */     this.documentURL = documentURL;
/*  70 */     if (documentURL != null) {
/*  71 */       this.codeSource = new CodeSource(documentURL, (Certificate[])null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     ProtectionDomain rhinoProtectionDomain = new ProtectionDomain(this.codeSource, getPermissions(this.codeSource));
/*     */ 
/*     */ 
/*     */     
/*  82 */     this.rhinoAccessControlContext = new AccessControlContext(new ProtectionDomain[] { rhinoProtectionDomain });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static URL[] getURL(ClassLoader parent) {
/*  91 */     if (parent instanceof RhinoClassLoader) {
/*  92 */       URL documentURL = ((RhinoClassLoader)parent).documentURL;
/*  93 */       if (documentURL != null) {
/*  94 */         return new URL[] { documentURL };
/*     */       }
/*  96 */       return new URL[0];
/*     */     } 
/*     */     
/*  99 */     return new URL[0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Class defineClass(String name, byte[] data) {
/* 108 */     return defineClass(name, data, 0, data.length, this.codeSource);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void linkClass(Class<?> clazz) {
/* 115 */     resolveClass(clazz);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AccessControlContext getAccessControlContext() {
/* 123 */     return this.rhinoAccessControlContext;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PermissionCollection getPermissions(CodeSource codesource) {
/* 133 */     PermissionCollection perms = null;
/*     */     
/* 135 */     if (codesource != null) {
/* 136 */       perms = super.getPermissions(codesource);
/*     */     }
/*     */     
/* 139 */     if (this.documentURL != null && perms != null) {
/* 140 */       Permission p = null;
/* 141 */       Permission dirPerm = null;
/*     */       try {
/* 143 */         p = this.documentURL.openConnection().getPermission();
/* 144 */       } catch (IOException e) {
/* 145 */         p = null;
/*     */       } 
/*     */       
/* 148 */       if (p instanceof FilePermission) {
/* 149 */         String path = p.getName();
/* 150 */         if (!path.endsWith(File.separator)) {
/*     */ 
/*     */           
/* 153 */           int dirEnd = path.lastIndexOf(File.separator);
/* 154 */           if (dirEnd != -1) {
/*     */             
/* 156 */             path = path.substring(0, dirEnd + 1);
/* 157 */             path = path + "-";
/* 158 */             dirPerm = new FilePermission(path, "read");
/* 159 */             perms.add(dirPerm);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 165 */     return perms;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/script/rhino/RhinoClassLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */