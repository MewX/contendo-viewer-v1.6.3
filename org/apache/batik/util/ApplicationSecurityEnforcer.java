/*     */ package org.apache.batik.util;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.security.Policy;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ApplicationSecurityEnforcer
/*     */ {
/*     */   public static final String EXCEPTION_ALIEN_SECURITY_MANAGER = "ApplicationSecurityEnforcer.message.security.exception.alien.security.manager";
/*     */   public static final String EXCEPTION_NO_POLICY_FILE = "ApplicationSecurityEnforcer.message.null.pointer.exception.no.policy.file";
/*     */   public static final String PROPERTY_JAVA_SECURITY_POLICY = "java.security.policy";
/*     */   public static final String JAR_PROTOCOL = "jar:";
/*     */   public static final String JAR_URL_FILE_SEPARATOR = "!/";
/*     */   public static final String PROPERTY_APP_DEV_BASE = "app.dev.base";
/*     */   public static final String PROPERTY_APP_JAR_BASE = "app.jar.base";
/*     */   public static final String APP_MAIN_CLASS_DIR = "classes/";
/*     */   protected Class appMainClass;
/*     */   protected String securityPolicy;
/*     */   protected String appMainClassRelativeURL;
/*     */   protected BatikSecurityManager lastSecurityManagerInstalled;
/*     */   
/*     */   public ApplicationSecurityEnforcer(Class appMainClass, String securityPolicy, String appJarFile) {
/* 127 */     this(appMainClass, securityPolicy);
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
/*     */   public ApplicationSecurityEnforcer(Class appMainClass, String securityPolicy) {
/* 139 */     this.appMainClass = appMainClass;
/* 140 */     this.securityPolicy = securityPolicy;
/* 141 */     this.appMainClassRelativeURL = appMainClass.getName().replace('.', '/') + ".class";
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
/*     */   public void enforceSecurity(boolean enforce) {
/* 157 */     SecurityManager sm = System.getSecurityManager();
/*     */     
/* 159 */     if (sm != null && sm != this.lastSecurityManagerInstalled)
/*     */     {
/*     */ 
/*     */       
/* 163 */       throw new SecurityException(Messages.getString("ApplicationSecurityEnforcer.message.security.exception.alien.security.manager"));
/*     */     }
/*     */ 
/*     */     
/* 167 */     if (enforce) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 172 */       System.setSecurityManager(null);
/* 173 */       installSecurityManager();
/*     */     }
/* 175 */     else if (sm != null) {
/* 176 */       System.setSecurityManager(null);
/* 177 */       this.lastSecurityManagerInstalled = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getPolicyURL() {
/* 187 */     ClassLoader cl = this.appMainClass.getClassLoader();
/* 188 */     URL policyURL = cl.getResource(this.securityPolicy);
/*     */     
/* 190 */     if (policyURL == null) {
/* 191 */       throw new NullPointerException(Messages.formatMessage("ApplicationSecurityEnforcer.message.null.pointer.exception.no.policy.file", new Object[] { this.securityPolicy }));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 196 */     return policyURL;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void installSecurityManager() {
/* 203 */     Policy policy = Policy.getPolicy();
/* 204 */     BatikSecurityManager securityManager = new BatikSecurityManager();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 211 */     ClassLoader cl = this.appMainClass.getClassLoader();
/* 212 */     String securityPolicyProperty = System.getProperty("java.security.policy");
/*     */ 
/*     */     
/* 215 */     if (securityPolicyProperty == null || securityPolicyProperty.equals("")) {
/*     */ 
/*     */       
/* 218 */       URL policyURL = getPolicyURL();
/*     */       
/* 220 */       System.setProperty("java.security.policy", policyURL.toString());
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 235 */     URL mainClassURL = cl.getResource(this.appMainClassRelativeURL);
/* 236 */     if (mainClassURL == null)
/*     */     {
/*     */       
/* 239 */       throw new RuntimeException(this.appMainClassRelativeURL);
/*     */     }
/*     */     
/* 242 */     String expandedMainClassName = mainClassURL.toString();
/* 243 */     if (expandedMainClassName.startsWith("jar:")) {
/* 244 */       setJarBase(expandedMainClassName);
/*     */     } else {
/* 246 */       setDevBase(expandedMainClassName);
/*     */     } 
/*     */ 
/*     */     
/* 250 */     System.setSecurityManager(securityManager);
/* 251 */     this.lastSecurityManagerInstalled = securityManager;
/*     */ 
/*     */     
/* 254 */     policy.refresh();
/*     */     
/* 256 */     if (securityPolicyProperty == null || securityPolicyProperty.equals("")) {
/* 257 */       System.setProperty("java.security.policy", "");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setJarBase(String expandedMainClassName) {
/* 265 */     String curAppJarBase = System.getProperty("app.jar.base");
/* 266 */     if (curAppJarBase == null) {
/* 267 */       expandedMainClassName = expandedMainClassName.substring("jar:".length());
/*     */       
/* 269 */       int codeBaseEnd = expandedMainClassName.indexOf("!/" + this.appMainClassRelativeURL);
/*     */ 
/*     */ 
/*     */       
/* 273 */       if (codeBaseEnd == -1)
/*     */       {
/*     */ 
/*     */         
/* 277 */         throw new RuntimeException();
/*     */       }
/*     */       
/* 280 */       String appCodeBase = expandedMainClassName.substring(0, codeBaseEnd);
/*     */ 
/*     */ 
/*     */       
/* 284 */       codeBaseEnd = appCodeBase.lastIndexOf('/');
/* 285 */       if (codeBaseEnd == -1) {
/* 286 */         appCodeBase = "";
/*     */       } else {
/* 288 */         appCodeBase = appCodeBase.substring(0, codeBaseEnd);
/*     */       } 
/*     */       
/* 291 */       System.setProperty("app.jar.base", appCodeBase);
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
/*     */   private void setDevBase(String expandedMainClassName) {
/* 305 */     String curAppCodeBase = System.getProperty("app.dev.base");
/* 306 */     if (curAppCodeBase == null) {
/* 307 */       int codeBaseEnd = expandedMainClassName.indexOf("classes/" + this.appMainClassRelativeURL);
/*     */ 
/*     */ 
/*     */       
/* 311 */       if (codeBaseEnd == -1)
/*     */       {
/*     */ 
/*     */         
/* 315 */         throw new RuntimeException();
/*     */       }
/*     */       
/* 318 */       String appCodeBase = expandedMainClassName.substring(0, codeBaseEnd);
/* 319 */       System.setProperty("app.dev.base", appCodeBase);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/ApplicationSecurityEnforcer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */