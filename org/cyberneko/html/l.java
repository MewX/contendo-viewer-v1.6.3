/*     */ package org.cyberneko.html;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.util.Properties;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class l
/*     */ {
/*     */   private static final String b = "xerces.properties";
/*     */   private static final boolean c = false;
/*     */   private static final int d = 80;
/*  63 */   private static Properties e = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   private static long f = -1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Class a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Object a(String factoryId, String fallbackClassName) throws a {
/*  96 */     return a(factoryId, (String)null, fallbackClassName);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Object a(String factoryId, String propertiesFilename, String fallbackClassName) throws a {
/* 128 */     m ss = m.a();
/* 129 */     ClassLoader cl = a();
/*     */ 
/*     */     
/*     */     try {
/* 133 */       String systemProp = ss.a(factoryId);
/* 134 */       if (systemProp != null)
/*     */       {
/* 136 */         return a(systemProp, cl, true);
/*     */       }
/* 138 */     } catch (SecurityException securityException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 143 */     String factoryClassName = null;
/*     */     
/* 145 */     if (propertiesFilename == null) {
/* 146 */       File propertiesFile = null;
/* 147 */       boolean propertiesFileExists = false;
/*     */       try {
/* 149 */         String javah = ss.a("java.home");
/* 150 */         propertiesFilename = javah + File.separator + "lib" + File.separator + "xerces.properties";
/*     */         
/* 152 */         propertiesFile = new File(propertiesFilename);
/* 153 */         propertiesFileExists = ss.b(propertiesFile);
/* 154 */       } catch (SecurityException e) {
/*     */         
/* 156 */         f = -1L;
/* 157 */         l.e = null;
/*     */       } 
/*     */       
/* 160 */       synchronized ((a == null) ? (a = a("org.cyberneko.html.l")) : a) {
/* 161 */         boolean loadProperties = false;
/*     */         
/*     */         try {
/* 164 */           if (f >= 0L) {
/* 165 */             if (propertiesFileExists && f < (f = ss.c(propertiesFile))) {
/*     */               
/* 167 */               loadProperties = true;
/*     */             
/*     */             }
/* 170 */             else if (!propertiesFileExists) {
/* 171 */               f = -1L;
/* 172 */               l.e = null;
/*     */             
/*     */             }
/*     */           
/*     */           }
/* 177 */           else if (propertiesFileExists) {
/* 178 */             loadProperties = true;
/* 179 */             f = ss.c(propertiesFile);
/*     */           } 
/*     */           
/* 182 */           if (loadProperties) {
/*     */             
/* 184 */             l.e = new Properties();
/* 185 */             FileInputStream fis = ss.a(propertiesFile);
/* 186 */             l.e.load(fis);
/* 187 */             fis.close();
/*     */           } 
/* 189 */         } catch (Exception x) {
/* 190 */           l.e = null;
/* 191 */           f = -1L;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 197 */       if (l.e != null) {
/* 198 */         factoryClassName = l.e.getProperty(factoryId);
/*     */       }
/*     */     } else {
/*     */       try {
/* 202 */         FileInputStream fis = ss.a(new File(propertiesFilename));
/* 203 */         Properties props = new Properties();
/* 204 */         props.load(fis);
/* 205 */         fis.close();
/* 206 */         factoryClassName = props.getProperty(factoryId);
/* 207 */       } catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 213 */     if (factoryClassName != null)
/*     */     {
/* 215 */       return a(factoryClassName, cl, true);
/*     */     }
/*     */ 
/*     */     
/* 219 */     Object provider = c(factoryId);
/* 220 */     if (provider != null) {
/* 221 */       return provider;
/*     */     }
/*     */     
/* 224 */     if (fallbackClassName == null) {
/* 225 */       throw new a("Provider for " + factoryId + " cannot be found", null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 230 */     return a(fallbackClassName, cl, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Class a(String x0) {
/*     */     try {
/*     */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/*     */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void b(String msg) {}
/*     */ 
/*     */ 
/*     */   
/*     */   static ClassLoader a() throws a {
/* 251 */     m ss = m.a();
/*     */ 
/*     */ 
/*     */     
/* 255 */     ClassLoader context = ss.b();
/* 256 */     ClassLoader system = ss.c();
/*     */     
/* 258 */     ClassLoader chain = system;
/*     */     while (true) {
/* 260 */       if (context == chain) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 269 */         ClassLoader current = ((a == null) ? (a = a("org.cyberneko.html.l")) : a).getClassLoader();
/*     */         
/* 271 */         chain = system;
/*     */         while (true) {
/* 273 */           if (current == chain)
/*     */           {
/*     */             
/* 276 */             return system;
/*     */           }
/* 278 */           if (chain == null) {
/*     */             break;
/*     */           }
/* 281 */           chain = ss.a(chain);
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 286 */         return current;
/*     */       } 
/*     */       
/* 289 */       if (chain == null) {
/*     */         break;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 296 */       chain = ss.a(chain);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 301 */     return context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Object a(String className, ClassLoader cl, boolean doFallback) throws a {
/*     */     try {
/* 313 */       Class providerClass = b(className, cl, doFallback);
/* 314 */       Object instance = providerClass.newInstance();
/*     */ 
/*     */       
/* 317 */       return instance;
/* 318 */     } catch (ClassNotFoundException x) {
/* 319 */       throw new a("Provider " + className + " not found", x);
/*     */     }
/* 321 */     catch (Exception x) {
/* 322 */       throw new a("Provider " + className + " could not be instantiated: " + x, x);
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
/*     */   static Class b(String className, ClassLoader cl, boolean doFallback) throws ClassNotFoundException, a {
/*     */     Class providerClass;
/* 337 */     SecurityManager security = System.getSecurityManager();
/*     */     try {
/* 339 */       if (security != null) {
/* 340 */         int lastDot = className.lastIndexOf(".");
/* 341 */         String packageName = className;
/* 342 */         if (lastDot != -1) packageName = className.substring(0, lastDot); 
/* 343 */         security.checkPackageAccess(packageName);
/*     */       } 
/* 345 */     } catch (SecurityException e) {
/* 346 */       throw e;
/*     */     } 
/*     */     
/* 349 */     if (cl == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 359 */       providerClass = Class.forName(className);
/*     */     } else {
/*     */       try {
/* 362 */         providerClass = cl.loadClass(className);
/* 363 */       } catch (ClassNotFoundException x) {
/* 364 */         if (doFallback) {
/*     */           
/* 366 */           ClassLoader current = ((a == null) ? (a = a("org.cyberneko.html.l")) : a).getClassLoader();
/* 367 */           if (current == null) {
/* 368 */             providerClass = Class.forName(className);
/* 369 */           } else if (cl != current) {
/* 370 */             cl = current;
/* 371 */             providerClass = cl.loadClass(className);
/*     */           } else {
/* 373 */             throw x;
/*     */           } 
/*     */         } else {
/* 376 */           throw x;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 381 */     return providerClass;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Object c(String factoryId) throws a {
/*     */     BufferedReader bufferedReader;
/* 392 */     m ss = m.a();
/* 393 */     String serviceId = "META-INF/services/" + factoryId;
/* 394 */     InputStream is = null;
/*     */ 
/*     */     
/* 397 */     ClassLoader cl = a();
/*     */     
/* 399 */     is = ss.a(cl, serviceId);
/*     */ 
/*     */     
/* 402 */     if (is == null) {
/* 403 */       ClassLoader current = ((a == null) ? (a = a("org.cyberneko.html.l")) : a).getClassLoader();
/* 404 */       if (cl != current) {
/* 405 */         cl = current;
/* 406 */         is = ss.a(cl, serviceId);
/*     */       } 
/*     */     } 
/*     */     
/* 410 */     if (is == null)
/*     */     {
/* 412 */       return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 436 */       bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 80);
/* 437 */     } catch (UnsupportedEncodingException e) {
/* 438 */       bufferedReader = new BufferedReader(new InputStreamReader(is), 80);
/*     */     } 
/*     */     
/* 441 */     String factoryClassName = null;
/*     */ 
/*     */     
/*     */     try {
/* 445 */       factoryClassName = bufferedReader.readLine();
/* 446 */       bufferedReader.close();
/* 447 */     } catch (IOException x) {
/*     */       
/* 449 */       return null;
/*     */     } 
/*     */     
/* 452 */     if (factoryClassName != null && !"".equals(factoryClassName))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 461 */       return a(factoryClassName, cl, false);
/*     */     }
/*     */ 
/*     */     
/* 465 */     return null;
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
/*     */   static class a
/*     */     extends Error
/*     */   {
/*     */     private Exception a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     a(String msg, Exception x) {
/* 494 */       super(msg);
/* 495 */       this.a = x;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Exception a() {
/* 504 */       return this.a;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/cyberneko/html/l.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */