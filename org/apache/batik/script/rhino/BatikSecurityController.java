/*     */ package org.apache.batik.script.rhino;
/*     */ 
/*     */ import java.security.AccessControlContext;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import org.mozilla.javascript.Callable;
/*     */ import org.mozilla.javascript.Context;
/*     */ import org.mozilla.javascript.GeneratedClassLoader;
/*     */ import org.mozilla.javascript.Scriptable;
/*     */ import org.mozilla.javascript.SecurityController;
/*     */ import org.mozilla.javascript.WrappedException;
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
/*     */ public class BatikSecurityController
/*     */   extends SecurityController
/*     */ {
/*     */   public GeneratedClassLoader createClassLoader(ClassLoader parentLoader, Object securityDomain) {
/*  49 */     if (securityDomain instanceof RhinoClassLoader) {
/*  50 */       return (RhinoClassLoader)securityDomain;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  55 */     throw new SecurityException("Script() objects are not supported");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getDynamicSecurityDomain(Object securityDomain) {
/*  66 */     ClassLoader loader = (RhinoClassLoader)securityDomain;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     if (loader != null) {
/*  73 */       return loader;
/*     */     }
/*  75 */     return AccessController.getContext();
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
/*     */   public Object callWithDomain(Object securityDomain, final Context cx, final Callable callable, final Scriptable scope, final Scriptable thisObj, final Object[] args) {
/*     */     AccessControlContext acc;
/*  95 */     if (securityDomain instanceof AccessControlContext) {
/*  96 */       acc = (AccessControlContext)securityDomain;
/*     */     } else {
/*  98 */       RhinoClassLoader loader = (RhinoClassLoader)securityDomain;
/*  99 */       acc = loader.rhinoAccessControlContext;
/*     */     } 
/*     */     
/* 102 */     PrivilegedExceptionAction<?> execAction = new PrivilegedExceptionAction() {
/*     */         public Object run() {
/* 104 */           return callable.call(cx, scope, thisObj, args);
/*     */         }
/*     */       };
/*     */     try {
/* 108 */       return AccessController.doPrivileged(execAction, acc);
/* 109 */     } catch (Exception e) {
/* 110 */       throw new WrappedException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/script/rhino/BatikSecurityController.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */