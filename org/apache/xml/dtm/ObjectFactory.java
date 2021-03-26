/*     */ package org.apache.xml.dtm;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ObjectFactory
/*     */ {
/*     */   private static final String DEFAULT_PROPERTIES_FILENAME = "xalan.properties";
/*     */   private static final String SERVICES_PATH = "META-INF/services/";
/*     */   private static final boolean DEBUG = false;
/*  68 */   private static Properties fXalanProperties = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  75 */   private static long fLastModified = -1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Object createObject(String factoryId, String fallbackClassName) throws ConfigurationError {
/* 101 */     return createObject(factoryId, null, fallbackClassName);
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
/*     */   static Object createObject(String factoryId, String propertiesFilename, String fallbackClassName) throws ConfigurationError {
/* 131 */     Class factoryClass = lookUpFactoryClass(factoryId, propertiesFilename, fallbackClassName);
/*     */ 
/*     */ 
/*     */     
/* 135 */     if (factoryClass == null) {
/* 136 */       throw new ConfigurationError("Provider for " + factoryId + " cannot be found", null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 141 */     try { Object instance = factoryClass.newInstance();
/* 142 */       debugPrintln("created new instance of factory " + factoryId);
/* 143 */       return instance; } catch (Exception x)
/*     */     
/* 145 */     { throw new ConfigurationError("Provider for factory " + factoryId + " could not be instantiated: " + x, x); }
/*     */   
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
/*     */   static Class lookUpFactoryClass(String factoryId) throws ConfigurationError {
/* 176 */     return lookUpFactoryClass(factoryId, null, null);
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
/*     */   static Class lookUpFactoryClass(String factoryId, String propertiesFilename, String fallbackClassName) throws ConfigurationError {
/* 206 */     String factoryClassName = lookUpFactoryClassName(factoryId, propertiesFilename, fallbackClassName);
/*     */ 
/*     */     
/* 209 */     ClassLoader cl = findClassLoader();
/*     */     
/* 211 */     if (factoryClassName == null) {
/* 212 */       factoryClassName = fallbackClassName;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 217 */     try { Class providerClass = findProviderClass(factoryClassName, cl, true);
/*     */ 
/*     */       
/* 220 */       debugPrintln("created new instance of " + providerClass + " using ClassLoader: " + cl);
/*     */       
/* 222 */       return providerClass; } catch (ClassNotFoundException x)
/*     */     
/* 224 */     { throw new ConfigurationError("Provider " + factoryClassName + " not found", x); } catch (Exception x)
/*     */     
/*     */     { 
/* 227 */       throw new ConfigurationError("Provider " + factoryClassName + " could not be instantiated: " + x, x); }
/*     */   
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
/*     */   static String lookUpFactoryClassName(String factoryId, String propertiesFilename, String fallbackClassName) {
/* 259 */     SecuritySupport ss = SecuritySupport.getInstance();
/*     */ 
/*     */ 
/*     */     
/* 263 */     try { String systemProp = ss.getSystemProperty(factoryId);
/* 264 */       if (systemProp != null)
/* 265 */       { debugPrintln("found system property, value=" + systemProp);
/* 266 */         return systemProp; }  } catch (SecurityException securityException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 274 */     String factoryClassName = null;
/*     */ 
/*     */     
/* 277 */     if (propertiesFilename == null) {
/* 278 */       File propertiesFile = null;
/* 279 */       boolean propertiesFileExists = false;
/*     */       
/* 281 */       try { String javah = ss.getSystemProperty("java.home");
/* 282 */         propertiesFilename = javah + File.separator + "lib" + File.separator + "xalan.properties";
/*     */         
/* 284 */         propertiesFile = new File(propertiesFilename);
/* 285 */         propertiesFileExists = ss.getFileExists(propertiesFile); } catch (SecurityException e)
/*     */       
/*     */       { 
/* 288 */         fLastModified = -1L;
/* 289 */         fXalanProperties = null; }
/*     */ 
/*     */       
/* 292 */       synchronized (ObjectFactory.class) {
/* 293 */         boolean loadProperties = false;
/*     */ 
/*     */         
/* 296 */         try { if (fLastModified >= 0L) {
/* 297 */             if (propertiesFileExists && fLastModified < (fLastModified = ss.getLastModified(propertiesFile))) {
/*     */               
/* 299 */               loadProperties = true;
/*     */             
/*     */             }
/* 302 */             else if (!propertiesFileExists) {
/* 303 */               fLastModified = -1L;
/* 304 */               fXalanProperties = null;
/*     */             
/*     */             }
/*     */           
/*     */           }
/* 309 */           else if (propertiesFileExists) {
/* 310 */             loadProperties = true;
/* 311 */             fLastModified = ss.getLastModified(propertiesFile);
/*     */           } 
/*     */           
/* 314 */           if (loadProperties)
/*     */           
/*     */           { 
/* 317 */             fXalanProperties = new Properties();
/* 318 */             FileInputStream fis = ss.getFileInputStream(propertiesFile);
/*     */             
/* 320 */             fXalanProperties.load(fis);
/* 321 */             fis.close(); }  } catch (Exception x)
/*     */         
/*     */         { 
/* 324 */           fXalanProperties = null;
/* 325 */           fLastModified = -1L; }
/*     */       
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 331 */       if (fXalanProperties != null) {
/* 332 */         factoryClassName = fXalanProperties.getProperty(factoryId);
/*     */       }
/*     */     } else {
/*     */       
/* 336 */       try { FileInputStream fis = ss.getFileInputStream(new File(propertiesFilename));
/*     */         
/* 338 */         Properties props = new Properties();
/* 339 */         props.load(fis);
/* 340 */         fis.close();
/* 341 */         factoryClassName = props.getProperty(factoryId); } catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 348 */     if (factoryClassName != null) {
/* 349 */       debugPrintln("found in " + propertiesFilename + ", value=" + factoryClassName);
/*     */       
/* 351 */       return factoryClassName;
/*     */     } 
/*     */ 
/*     */     
/* 355 */     return findJarServiceProviderName(factoryId);
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
/*     */   private static void debugPrintln(String msg) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static ClassLoader findClassLoader() throws ConfigurationError {
/* 376 */     SecuritySupport ss = SecuritySupport.getInstance();
/*     */ 
/*     */ 
/*     */     
/* 380 */     ClassLoader context = ss.getContextClassLoader();
/* 381 */     ClassLoader system = ss.getSystemClassLoader();
/*     */     
/* 383 */     ClassLoader chain = system;
/*     */     while (true) {
/* 385 */       if (context == chain) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 394 */         ClassLoader current = ObjectFactory.class.getClassLoader();
/*     */         
/* 396 */         chain = system;
/*     */         while (true) {
/* 398 */           if (current == chain)
/*     */           {
/*     */             
/* 401 */             return system;
/*     */           }
/* 403 */           if (chain == null) {
/*     */             break;
/*     */           }
/* 406 */           chain = ss.getParentClassLoader(chain);
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 411 */         return current;
/*     */       } 
/*     */       
/* 414 */       if (chain == null) {
/*     */         break;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 421 */       chain = ss.getParentClassLoader(chain);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 426 */     return context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Object newInstance(String className, ClassLoader cl, boolean doFallback) throws ConfigurationError {
/*     */     
/* 438 */     try { Class providerClass = findProviderClass(className, cl, doFallback);
/* 439 */       Object instance = providerClass.newInstance();
/* 440 */       debugPrintln("created new instance of " + providerClass + " using ClassLoader: " + cl);
/*     */       
/* 442 */       return instance; } catch (ClassNotFoundException x)
/*     */     
/* 444 */     { throw new ConfigurationError("Provider " + className + " not found", x); } catch (Exception x)
/*     */     
/*     */     { 
/* 447 */       throw new ConfigurationError("Provider " + className + " could not be instantiated: " + x, x); }
/*     */   
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
/*     */   static Class findProviderClass(String className, ClassLoader cl, boolean doFallback) throws ClassNotFoundException, ConfigurationError {
/*     */     Class clazz;
/* 462 */     SecurityManager security = System.getSecurityManager();
/*     */     
/* 464 */     try { if (security != null)
/* 465 */         security.checkPackageAccess(className);  } catch (SecurityException e)
/*     */     
/*     */     { 
/* 468 */       throw e; }
/*     */ 
/*     */ 
/*     */     
/* 472 */     if (cl == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 482 */       clazz = Class.forName(className);
/*     */     } else {
/*     */       
/* 485 */       try { clazz = cl.loadClass(className); } catch (ClassNotFoundException x)
/*     */       
/* 487 */       { if (doFallback) {
/*     */           
/* 489 */           ClassLoader current = ObjectFactory.class.getClassLoader();
/* 490 */           if (current == null) {
/* 491 */             clazz = Class.forName(className);
/* 492 */           } else if (cl != current) {
/* 493 */             cl = current;
/* 494 */             clazz = cl.loadClass(className);
/*     */           } else {
/* 496 */             throw x;
/*     */           } 
/*     */         } else {
/* 499 */           throw x;
/*     */         }  }
/*     */     
/*     */     } 
/*     */     
/* 504 */     return clazz;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String findJarServiceProviderName(String factoryId) {
/*     */     BufferedReader bufferedReader;
/* 514 */     SecuritySupport ss = SecuritySupport.getInstance();
/* 515 */     String serviceId = "META-INF/services/" + factoryId;
/* 516 */     InputStream is = null;
/*     */ 
/*     */     
/* 519 */     ClassLoader cl = findClassLoader();
/*     */     
/* 521 */     is = ss.getResourceAsStream(cl, serviceId);
/*     */ 
/*     */     
/* 524 */     if (is == null) {
/* 525 */       ClassLoader current = ObjectFactory.class.getClassLoader();
/* 526 */       if (cl != current) {
/* 527 */         cl = current;
/* 528 */         is = ss.getResourceAsStream(cl, serviceId);
/*     */       } 
/*     */     } 
/*     */     
/* 532 */     if (is == null)
/*     */     {
/* 534 */       return null;
/*     */     }
/*     */     
/* 537 */     debugPrintln("found jar resource=" + serviceId + " using ClassLoader: " + cl);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 558 */     try { bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8")); } catch (UnsupportedEncodingException e)
/*     */     
/* 560 */     { bufferedReader = new BufferedReader(new InputStreamReader(is)); }
/*     */ 
/*     */     
/* 563 */     String factoryClassName = null;
/*     */ 
/*     */ 
/*     */     
/* 567 */     try { factoryClassName = bufferedReader.readLine();
/* 568 */       bufferedReader.close(); } catch (IOException x)
/*     */     
/*     */     { 
/* 571 */       return null; }
/*     */ 
/*     */     
/* 574 */     if (factoryClassName != null && !"".equals(factoryClassName)) {
/*     */       
/* 576 */       debugPrintln("found in resource, value=" + factoryClassName);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 583 */       return factoryClassName;
/*     */     } 
/*     */ 
/*     */     
/* 587 */     return null;
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
/*     */   static class ConfigurationError
/*     */     extends Error
/*     */   {
/*     */     private Exception exception;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     ConfigurationError(String msg, Exception x) {
/* 616 */       super(msg);
/* 617 */       this.exception = x;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Exception getException() {
/* 626 */       return this.exception;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ObjectFactory.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */