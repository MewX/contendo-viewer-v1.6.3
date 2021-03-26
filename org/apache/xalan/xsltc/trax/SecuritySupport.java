/*     */ package org.apache.xalan.xsltc.trax;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.InputStream;
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
/*     */ class SecuritySupport
/*     */ {
/*     */   private static final Object securitySupport;
/*     */   
/*     */   static {
/*  46 */     SecuritySupport ss = null;
/*     */     
/*  48 */     try { Class c = Class.forName("java.security.AccessController");
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
/*  66 */       ss = new SecuritySupport12(); } catch (Exception ex)
/*     */     
/*     */     {  }
/*     */     finally
/*  70 */     { if (ss == null)
/*  71 */         ss = new SecuritySupport(); 
/*  72 */       securitySupport = ss; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static SecuritySupport getInstance() {
/*  81 */     return (SecuritySupport)securitySupport;
/*     */   }
/*     */   
/*     */   ClassLoader getContextClassLoader() {
/*  85 */     return null;
/*     */   }
/*     */   
/*     */   ClassLoader getSystemClassLoader() {
/*  89 */     return null;
/*     */   }
/*     */   
/*     */   ClassLoader getParentClassLoader(ClassLoader cl) {
/*  93 */     return null;
/*     */   }
/*     */   
/*     */   String getSystemProperty(String propName) {
/*  97 */     return System.getProperty(propName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   FileInputStream getFileInputStream(File file) throws FileNotFoundException {
/* 103 */     return new FileInputStream(file);
/*     */   }
/*     */   
/*     */   InputStream getResourceAsStream(ClassLoader cl, String name) {
/*     */     InputStream inputStream;
/* 108 */     if (cl == null) {
/* 109 */       inputStream = ClassLoader.getSystemResourceAsStream(name);
/*     */     } else {
/* 111 */       inputStream = cl.getResourceAsStream(name);
/*     */     } 
/* 113 */     return inputStream;
/*     */   }
/*     */   
/*     */   boolean getFileExists(File f) {
/* 117 */     return f.exists();
/*     */   }
/*     */   
/*     */   long getLastModified(File f) {
/* 121 */     return f.lastModified();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/trax/SecuritySupport.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */