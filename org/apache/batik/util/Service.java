/*     */ package org.apache.batik.util;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Service
/*     */ {
/*  48 */   static HashMap providerMap = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized Iterator providers(Class cls) {
/*     */     Enumeration<URL> e;
/*  62 */     String serviceFile = "META-INF/services/" + cls.getName();
/*     */ 
/*     */ 
/*     */     
/*  66 */     List<Object> l = (List)providerMap.get(serviceFile);
/*  67 */     if (l != null) {
/*  68 */       return l.iterator();
/*     */     }
/*  70 */     l = new ArrayList();
/*  71 */     providerMap.put(serviceFile, l);
/*     */     
/*  73 */     ClassLoader cl = null;
/*     */     try {
/*  75 */       cl = cls.getClassLoader();
/*  76 */     } catch (SecurityException securityException) {}
/*     */ 
/*     */ 
/*     */     
/*  80 */     if (cl == null) cl = Service.class.getClassLoader();
/*     */ 
/*     */     
/*  83 */     if (cl == null) return l.iterator();
/*     */ 
/*     */     
/*     */     try {
/*  87 */       e = cl.getResources(serviceFile);
/*  88 */     } catch (IOException ioe) {
/*  89 */       return l.iterator();
/*     */     } 
/*     */     
/*  92 */     while (e.hasMoreElements()) {
/*  93 */       InputStream is = null;
/*  94 */       Reader r = null;
/*  95 */       BufferedReader br = null;
/*     */       try {
/*  97 */         URL u = e.nextElement();
/*     */ 
/*     */         
/* 100 */         is = u.openStream();
/* 101 */         r = new InputStreamReader(is, "UTF-8");
/* 102 */         br = new BufferedReader(r);
/*     */         
/* 104 */         String line = br.readLine();
/* 105 */         while (line != null) {
/*     */           
/*     */           try {
/* 108 */             int idx = line.indexOf('#');
/* 109 */             if (idx != -1) {
/* 110 */               line = line.substring(0, idx);
/*     */             }
/*     */             
/* 113 */             line = line.trim();
/*     */ 
/*     */             
/* 116 */             if (line.length() == 0) {
/* 117 */               line = br.readLine();
/*     */ 
/*     */               
/*     */               continue;
/*     */             } 
/*     */             
/* 123 */             Object obj = cl.loadClass(line).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/*     */             
/* 125 */             l.add(obj);
/* 126 */           } catch (Exception exception) {}
/*     */ 
/*     */           
/* 129 */           line = br.readLine();
/*     */         } 
/* 131 */       } catch (Exception exception) {
/*     */       
/* 133 */       } catch (LinkageError linkageError) {
/*     */ 
/*     */       
/*     */       } finally {
/* 137 */         if (is != null) {
/*     */           try {
/* 139 */             is.close();
/* 140 */           } catch (IOException iOException) {}
/*     */           
/* 142 */           is = null;
/*     */         } 
/* 144 */         if (r != null) {
/*     */           try {
/* 146 */             r.close();
/* 147 */           } catch (IOException iOException) {}
/*     */           
/* 149 */           r = null;
/*     */         } 
/* 151 */         if (br != null) {
/*     */           try {
/* 153 */             br.close();
/* 154 */           } catch (IOException iOException) {}
/*     */           
/* 156 */           br = null;
/*     */         } 
/*     */       } 
/*     */     } 
/* 160 */     return l.iterator();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/util/Service.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */