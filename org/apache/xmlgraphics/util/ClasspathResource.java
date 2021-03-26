/*     */ package org.apache.xmlgraphics.util;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.URL;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import java.util.jar.Attributes;
/*     */ import java.util.jar.Manifest;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ClasspathResource
/*     */ {
/*     */   private final Map contentMappings;
/*     */   private static final String MANIFEST_PATH = "META-INF/MANIFEST.MF";
/*     */   private static final String CONTENT_TYPE_KEY = "Content-Type";
/*     */   private static ClasspathResource classpathResource;
/*     */   
/*     */   private ClasspathResource() {
/*  66 */     this.contentMappings = new HashMap<Object, Object>();
/*  67 */     loadManifests();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized ClasspathResource getInstance() {
/*  76 */     if (classpathResource == null) {
/*  77 */       classpathResource = new ClasspathResource();
/*     */     }
/*  79 */     return classpathResource;
/*     */   }
/*     */ 
/*     */   
/*     */   private Set getClassLoadersForResources() {
/*  84 */     Set<ClassLoader> v = new HashSet();
/*     */     try {
/*  86 */       ClassLoader l = ClassLoader.getSystemClassLoader();
/*  87 */       if (l != null) {
/*  88 */         v.add(l);
/*     */       }
/*  90 */     } catch (SecurityException securityException) {}
/*     */ 
/*     */     
/*     */     try {
/*  94 */       ClassLoader l = Thread.currentThread().getContextClassLoader();
/*  95 */       if (l != null) {
/*  96 */         v.add(l);
/*     */       }
/*  98 */     } catch (SecurityException securityException) {}
/*     */ 
/*     */     
/*     */     try {
/* 102 */       ClassLoader l = ClasspathResource.class.getClassLoader();
/* 103 */       if (l != null) {
/* 104 */         v.add(l);
/*     */       }
/* 106 */     } catch (SecurityException securityException) {}
/*     */ 
/*     */     
/* 109 */     return v;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadManifests() {
/*     */     try {
/* 116 */       Iterator<ClassLoader> it = getClassLoadersForResources().iterator();
/* 117 */       while (it.hasNext()) {
/* 118 */         ClassLoader classLoader = it.next();
/*     */         
/* 120 */         Enumeration<URL> e = classLoader.getResources("META-INF/MANIFEST.MF");
/*     */         
/* 122 */         while (e.hasMoreElements()) {
/* 123 */           URL u = e.nextElement();
/*     */           try {
/* 125 */             Manifest manifest = new Manifest(u.openStream());
/* 126 */             Map<String, Attributes> entries = manifest.getEntries();
/* 127 */             Iterator<Map.Entry> entrysetiterator = entries.entrySet().iterator();
/*     */             
/* 129 */             while (entrysetiterator.hasNext()) {
/* 130 */               Map.Entry entry = entrysetiterator.next();
/*     */               
/* 132 */               String name = (String)entry.getKey();
/* 133 */               Attributes attributes = (Attributes)entry.getValue();
/*     */               
/* 135 */               String contentType = attributes.getValue("Content-Type");
/*     */               
/* 137 */               if (contentType != null) {
/* 138 */                 addToMapping(contentType, name, classLoader);
/*     */               }
/*     */             } 
/* 141 */           } catch (IOException iOException) {}
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 147 */     catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addToMapping(String contentType, String name, ClassLoader classLoader) {
/* 154 */     List<URL> existingFiles = (List)this.contentMappings.get(contentType);
/* 155 */     if (existingFiles == null) {
/* 156 */       existingFiles = new Vector();
/* 157 */       this.contentMappings.put(contentType, existingFiles);
/*     */     } 
/* 159 */     URL url = classLoader.getResource(name);
/* 160 */     if (url != null) {
/* 161 */       existingFiles.add(url);
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
/*     */   public List listResourcesOfMimeType(String mimeType) {
/* 173 */     List content = (List)this.contentMappings.get(mimeType);
/* 174 */     if (content == null) {
/* 175 */       return Collections.EMPTY_LIST;
/*     */     }
/* 177 */     return content;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/ClasspathResource.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */