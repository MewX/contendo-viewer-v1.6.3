/*     */ package org.apache.xalan.xsltc.compiler;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.InputStream;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedAction;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
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
/*     */ class SecuritySupport12
/*     */   extends SecuritySupport
/*     */ {
/*     */   ClassLoader getContextClassLoader() {
/*  44 */     return AccessController.<ClassLoader>doPrivileged(new PrivilegedAction(this) { private final SecuritySupport12 this$0;
/*     */           
/*     */           public Object run() {
/*  47 */             ClassLoader cl = null;
/*     */             
/*  49 */             try { cl = Thread.currentThread().getContextClassLoader(); } catch (SecurityException securityException) {}
/*     */             
/*  51 */             return cl;
/*     */           } }
/*     */       );
/*     */   }
/*     */   
/*     */   ClassLoader getSystemClassLoader() {
/*  57 */     return AccessController.<ClassLoader>doPrivileged(new PrivilegedAction(this) { private final SecuritySupport12 this$0;
/*     */           
/*     */           public Object run() {
/*  60 */             ClassLoader cl = null;
/*     */             
/*  62 */             try { cl = ClassLoader.getSystemClassLoader(); } catch (SecurityException securityException) {}
/*     */             
/*  64 */             return cl;
/*     */           } }
/*     */       );
/*     */   }
/*     */   
/*     */   ClassLoader getParentClassLoader(ClassLoader cl) {
/*  70 */     return AccessController.<ClassLoader>doPrivileged(new PrivilegedAction(this, cl) { private final ClassLoader val$cl; private final SecuritySupport12 this$0;
/*     */           
/*     */           public Object run() {
/*  73 */             ClassLoader parent = null;
/*     */             
/*  75 */             try { parent = this.val$cl.getParent(); } catch (SecurityException securityException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  80 */             return (parent == this.val$cl) ? null : parent;
/*     */           } }
/*     */       );
/*     */   }
/*     */   
/*     */   String getSystemProperty(String propName) {
/*  86 */     return AccessController.<String>doPrivileged(new PrivilegedAction(this, propName) { private final String val$propName; private final SecuritySupport12 this$0;
/*     */           
/*     */           public Object run() {
/*  89 */             return System.getProperty(this.val$propName);
/*     */           } }
/*     */       );
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   FileInputStream getFileInputStream(File file) throws FileNotFoundException {
/*     */     try {
/*  98 */       return AccessController.<FileInputStream>doPrivileged(new PrivilegedExceptionAction(this, file) { private final File val$file; private final SecuritySupport12 this$0;
/*     */             
/*     */             public Object run() throws FileNotFoundException {
/* 101 */               return new FileInputStream(this.val$file);
/*     */             } }
/*     */         );
/*     */     } catch (PrivilegedActionException e) {
/* 105 */       throw (FileNotFoundException)e.getException();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   InputStream getResourceAsStream(ClassLoader cl, String name) {
/* 112 */     return AccessController.<InputStream>doPrivileged(new PrivilegedAction(this, cl, name) { private final ClassLoader val$cl;
/*     */           
/*     */           public Object run() {
/*     */             InputStream inputStream;
/* 116 */             if (this.val$cl == null) {
/* 117 */               inputStream = ClassLoader.getSystemResourceAsStream(this.val$name);
/*     */             } else {
/* 119 */               inputStream = this.val$cl.getResourceAsStream(this.val$name);
/*     */             } 
/* 121 */             return inputStream;
/*     */           }
/*     */           private final String val$name; private final SecuritySupport12 this$0; }
/*     */       );
/*     */   }
/*     */   boolean getFileExists(File f) {
/* 127 */     return ((Boolean)AccessController.<Boolean>doPrivileged(new PrivilegedAction(this, f) { private final File val$f; private final SecuritySupport12 this$0;
/*     */           
/*     */           public Object run() {
/* 130 */             return new Boolean(this.val$f.exists());
/*     */           } }
/*     */       )).booleanValue();
/*     */   }
/*     */   
/*     */   long getLastModified(File f) {
/* 136 */     return ((Long)AccessController.<Long>doPrivileged(new PrivilegedAction(this, f) { private final File val$f; private final SecuritySupport12 this$0;
/*     */           
/*     */           public Object run() {
/* 139 */             return new Long(this.val$f.lastModified());
/*     */           } }
/*     */       )).longValue();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/SecuritySupport12.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */