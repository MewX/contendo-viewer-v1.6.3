/*     */ package org.apache.pdfbox.pdmodel.encryption;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SecurityHandlerFactory
/*     */ {
/*  38 */   public static final SecurityHandlerFactory INSTANCE = new SecurityHandlerFactory();
/*     */   
/*  40 */   private final Map<String, Class<? extends SecurityHandler>> nameToHandler = new HashMap<String, Class<? extends SecurityHandler>>();
/*     */ 
/*     */   
/*  43 */   private final Map<Class<? extends ProtectionPolicy>, Class<? extends SecurityHandler>> policyToHandler = new HashMap<Class<? extends ProtectionPolicy>, Class<? extends SecurityHandler>>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private SecurityHandlerFactory() {
/*  50 */     registerHandler("Standard", (Class)StandardSecurityHandler.class, (Class)StandardProtectionPolicy.class);
/*     */ 
/*     */ 
/*     */     
/*  54 */     registerHandler("Adobe.PubSec", (Class)PublicKeySecurityHandler.class, (Class)PublicKeyProtectionPolicy.class);
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
/*     */   public void registerHandler(String name, Class<? extends SecurityHandler> securityHandler, Class<? extends ProtectionPolicy> protectionPolicy) {
/*  74 */     if (this.nameToHandler.containsKey(name))
/*     */     {
/*  76 */       throw new IllegalStateException("The security handler name is already registered");
/*     */     }
/*     */     
/*  79 */     this.nameToHandler.put(name, securityHandler);
/*  80 */     this.policyToHandler.put(protectionPolicy, securityHandler);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SecurityHandler newSecurityHandlerForPolicy(ProtectionPolicy policy) {
/*  90 */     Class<? extends SecurityHandler> handlerClass = this.policyToHandler.get(policy.getClass());
/*  91 */     if (handlerClass == null)
/*     */     {
/*  93 */       return null;
/*     */     }
/*     */     
/*  96 */     Class<?>[] argsClasses = new Class[] { policy.getClass() };
/*  97 */     Object[] args = { policy };
/*  98 */     return newSecurityHandler(handlerClass, argsClasses, args);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SecurityHandler newSecurityHandlerForFilter(String name) {
/* 108 */     Class<? extends SecurityHandler> handlerClass = this.nameToHandler.get(name);
/* 109 */     if (handlerClass == null)
/*     */     {
/* 111 */       return null;
/*     */     }
/*     */     
/* 114 */     Class<?>[] argsClasses = new Class[0];
/* 115 */     Object[] args = new Object[0];
/* 116 */     return newSecurityHandler(handlerClass, argsClasses, args);
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
/*     */   private SecurityHandler newSecurityHandler(Class<? extends SecurityHandler> handlerClass, Class<?>[] argsClasses, Object[] args) {
/*     */     try {
/* 132 */       Constructor<? extends SecurityHandler> ctor = handlerClass.getDeclaredConstructor(argsClasses);
/* 133 */       return ctor.newInstance(args);
/*     */     }
/* 135 */     catch (NoSuchMethodException e) {
/*     */ 
/*     */       
/* 138 */       throw new RuntimeException(e);
/*     */     }
/* 140 */     catch (IllegalAccessException e) {
/*     */ 
/*     */       
/* 143 */       throw new RuntimeException(e);
/*     */     }
/* 145 */     catch (InstantiationException e) {
/*     */ 
/*     */       
/* 148 */       throw new RuntimeException(e);
/*     */     }
/* 150 */     catch (InvocationTargetException e) {
/*     */ 
/*     */       
/* 153 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/encryption/SecurityHandlerFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */