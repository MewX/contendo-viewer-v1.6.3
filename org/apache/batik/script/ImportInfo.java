/*     */ package org.apache.batik.script;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.net.URL;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImportInfo
/*     */ {
/*     */   static final String defaultFile = "META-INF/imports/script.txt";
/*  63 */   static String importFile = "META-INF/imports/script.txt"; static {
/*     */     
/*  65 */     try { importFile = System.getProperty("org.apache.batik.script.imports", "META-INF/imports/script.txt"); }
/*     */     
/*  67 */     catch (SecurityException securityException) {  }
/*  68 */     catch (NumberFormatException numberFormatException) {}
/*     */   }
/*     */ 
/*     */   
/*  72 */   static ImportInfo defaultImports = null;
/*     */   
/*     */   protected Set classes;
/*     */   
/*     */   protected Set packages;
/*     */   static final String classStr = "class";
/*     */   static final String packageStr = "package";
/*     */   
/*     */   public static ImportInfo getImports() {
/*  81 */     if (defaultImports == null)
/*  82 */       defaultImports = readImports(); 
/*  83 */     return defaultImports;
/*     */   }
/*     */   static ImportInfo readImports() {
/*     */     Enumeration<URL> e;
/*  87 */     ImportInfo ret = new ImportInfo();
/*     */ 
/*     */     
/*  90 */     ClassLoader cl = ImportInfo.class.getClassLoader();
/*     */ 
/*     */     
/*  93 */     if (cl == null) return ret;
/*     */ 
/*     */     
/*     */     try {
/*  97 */       e = cl.getResources(importFile);
/*  98 */     } catch (IOException ioe) {
/*  99 */       return ret;
/*     */     } 
/*     */     
/* 102 */     while (e.hasMoreElements()) {
/*     */       try {
/* 104 */         URL url = e.nextElement();
/* 105 */         ret.addImports(url);
/* 106 */       } catch (Exception exception) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 112 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImportInfo() {
/* 123 */     this.classes = new HashSet();
/* 124 */     this.packages = new HashSet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator getClasses() {
/* 131 */     return Collections.unmodifiableSet(this.classes).iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator getPackages() {
/* 137 */     return Collections.unmodifiableSet(this.packages).iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addClass(String cls) {
/* 144 */     this.classes.add(cls);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPackage(String pkg) {
/* 149 */     this.packages.add(pkg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeClass(String cls) {
/* 156 */     return this.classes.remove(cls);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removePackage(String pkg) {
/* 162 */     return this.packages.remove(pkg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addImports(URL src) throws IOException {
/* 173 */     InputStream is = null;
/* 174 */     Reader r = null;
/* 175 */     BufferedReader br = null;
/*     */     try {
/* 177 */       is = src.openStream();
/* 178 */       r = new InputStreamReader(is, "UTF-8");
/* 179 */       br = new BufferedReader(r);
/*     */       
/*     */       String line;
/* 182 */       while ((line = br.readLine()) != null) {
/*     */         
/* 184 */         int idx = line.indexOf('#');
/* 185 */         if (idx != -1) {
/* 186 */           line = line.substring(0, idx);
/*     */         }
/*     */         
/* 189 */         line = line.trim();
/*     */         
/* 191 */         if (line.length() == 0) {
/*     */           continue;
/*     */         }
/* 194 */         idx = line.indexOf(' ');
/* 195 */         if (idx == -1)
/*     */           continue; 
/* 197 */         String prefix = line.substring(0, idx);
/* 198 */         line = line.substring(idx + 1);
/* 199 */         boolean isPackage = "package".equals(prefix);
/* 200 */         boolean isClass = "class".equals(prefix);
/*     */         
/* 202 */         if (!isPackage && !isClass)
/*     */           continue; 
/* 204 */         while (line.length() != 0) {
/* 205 */           String id; idx = line.indexOf(' ');
/*     */           
/* 207 */           if (idx == -1) {
/* 208 */             id = line;
/* 209 */             line = "";
/*     */           } else {
/* 211 */             id = line.substring(0, idx);
/* 212 */             line = line.substring(idx + 1);
/*     */           } 
/* 214 */           if (id.length() == 0)
/*     */             continue; 
/* 216 */           if (isClass) { addClass(id); continue; }
/* 217 */            addPackage(id);
/*     */         }
/*     */       
/*     */       } 
/*     */     } finally {
/*     */       
/* 223 */       if (is != null) {
/*     */         try {
/* 225 */           is.close();
/* 226 */         } catch (IOException iOException) {}
/* 227 */         is = null;
/*     */       } 
/* 229 */       if (r != null) {
/*     */         try {
/* 231 */           r.close();
/* 232 */         } catch (IOException iOException) {}
/* 233 */         r = null;
/*     */       } 
/* 235 */       if (br != null) {
/*     */         try {
/* 237 */           br.close();
/* 238 */         } catch (IOException iOException) {}
/* 239 */         br = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/script/ImportInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */