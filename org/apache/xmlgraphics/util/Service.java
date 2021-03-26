/*     */ package org.apache.xmlgraphics.util;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class Service
/*     */ {
/*  54 */   static Map<String, List<String>> classMap = new HashMap<String, List<String>>();
/*  55 */   static Map<String, List<Object>> instanceMap = new HashMap<String, List<Object>>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized Iterator<Object> providers(Class<?> cls) {
/*  69 */     String serviceFile = getServiceFilename(cls);
/*     */     
/*  71 */     List<Object> l = instanceMap.get(serviceFile);
/*  72 */     if (l != null) {
/*  73 */       return l.iterator();
/*     */     }
/*     */     
/*  76 */     l = new ArrayList();
/*  77 */     instanceMap.put(serviceFile, l);
/*     */     
/*  79 */     ClassLoader cl = getClassLoader(cls);
/*  80 */     if (cl != null) {
/*  81 */       List<String> names = getProviderNames(cls, cl);
/*  82 */       for (String name : names) {
/*     */         
/*     */         try {
/*  85 */           Object obj = cl.loadClass(name).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/*     */           
/*  87 */           l.add(obj);
/*  88 */         } catch (Exception exception) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  93 */     return l.iterator();
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
/*     */   public static synchronized Iterator<String> providerNames(Class<?> cls) {
/* 108 */     String serviceFile = getServiceFilename(cls);
/*     */     
/* 110 */     List<String> l = classMap.get(serviceFile);
/* 111 */     if (l != null) {
/* 112 */       return l.iterator();
/*     */     }
/*     */     
/* 115 */     l = new ArrayList<String>();
/* 116 */     classMap.put(serviceFile, l);
/* 117 */     l.addAll(getProviderNames(cls));
/* 118 */     return l.iterator();
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
/*     */   public static Iterator<?> providers(Class<?> cls, boolean returnInstances) {
/* 137 */     return returnInstances ? providers(cls) : providerNames(cls);
/*     */   }
/*     */   
/*     */   private static List<String> getProviderNames(Class<?> cls) {
/* 141 */     return getProviderNames(cls, getClassLoader(cls));
/*     */   }
/*     */   private static List<String> getProviderNames(Class<?> cls, ClassLoader cl) {
/*     */     Enumeration<URL> e;
/* 145 */     List<String> l = new ArrayList<String>();
/*     */ 
/*     */     
/* 148 */     if (cl == null) {
/* 149 */       return l;
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 154 */       e = cl.getResources(getServiceFilename(cls));
/* 155 */     } catch (IOException ioe) {
/* 156 */       return l;
/*     */     } 
/*     */     
/* 159 */     while (e.hasMoreElements()) {
/*     */       try {
/* 161 */         URL u = e.nextElement();
/*     */         
/* 163 */         InputStream is = u.openStream();
/* 164 */         Reader r = new InputStreamReader(is, "UTF-8");
/* 165 */         BufferedReader br = new BufferedReader(r);
/*     */         try {
/* 167 */           for (String line = br.readLine(); line != null; line = br.readLine()) {
/*     */             
/* 169 */             int idx = line.indexOf('#');
/* 170 */             if (idx != -1) {
/* 171 */               line = line.substring(0, idx);
/*     */             }
/*     */ 
/*     */             
/* 175 */             line = line.trim();
/*     */             
/* 177 */             if (line.length() != 0) {
/* 178 */               l.add(line);
/*     */             }
/*     */           } 
/*     */         } finally {
/* 182 */           IOUtils.closeQuietly(br);
/* 183 */           IOUtils.closeQuietly(is);
/*     */         } 
/* 185 */       } catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */     
/* 189 */     return l;
/*     */   }
/*     */   
/*     */   private static ClassLoader getClassLoader(Class<?> cls) {
/* 193 */     ClassLoader cl = null;
/*     */     try {
/* 195 */       cl = cls.getClassLoader();
/* 196 */     } catch (SecurityException securityException) {}
/*     */ 
/*     */ 
/*     */     
/* 200 */     if (cl == null) {
/* 201 */       cl = Service.class.getClassLoader();
/*     */     }
/* 203 */     if (cl == null) {
/* 204 */       cl = ClassLoader.getSystemClassLoader();
/*     */     }
/* 206 */     return cl;
/*     */   }
/*     */   
/*     */   private static String getServiceFilename(Class<?> cls) {
/* 210 */     return "META-INF/services/" + cls.getName();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/Service.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */