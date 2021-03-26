/*     */ package org.cyberneko.html;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.InputStream;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedActionException;
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
/*     */ class n
/*     */   extends m
/*     */ {
/*     */   ClassLoader b() {
/*  38 */     return AccessController.<ClassLoader>doPrivileged(new o(this));
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
/*     */   ClassLoader c() {
/*  51 */     return AccessController.<ClassLoader>doPrivileged(new p(this));
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
/*     */   ClassLoader a(ClassLoader cl) {
/*  64 */     return AccessController.<ClassLoader>doPrivileged(new q(this, cl));
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
/*     */   String a(String propName) {
/*  80 */     return AccessController.<String>doPrivileged(new r(this, propName));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   FileInputStream a(File file) throws FileNotFoundException {
/*     */     try {
/*  92 */       return AccessController.<FileInputStream>doPrivileged(new s(this, file));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  98 */     catch (PrivilegedActionException e) {
/*  99 */       throw (FileNotFoundException)e.getException();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   InputStream a(ClassLoader cl, String name) {
/* 106 */     return AccessController.<InputStream>doPrivileged(new t(this, cl, name));
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
/*     */   boolean b(File f) {
/* 121 */     return ((Boolean)AccessController.<Boolean>doPrivileged(new u(this, f))).booleanValue();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   long c(File f) {
/* 130 */     return ((Long)AccessController.<Long>doPrivileged(new v(this, f))).longValue();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/n.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */