/*     */ package jp.cssj.c;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */ {
/*  27 */   private static final Logger a = Logger.getLogger(b.class.getName());
/*  28 */   private static final File[] b = new File[0];
/*  29 */   private static final b c = new b();
/*     */   
/*     */   public static final b a() {
/*  32 */     return c;
/*     */   }
/*     */   
/*  35 */   private URLClassLoader d = null;
/*     */   
/*  37 */   private Map<Class<a<?>>, List<a<?>>> e = null;
/*     */   
/*  39 */   private File[] f = null;
/*  40 */   private long[] g = null;
/*     */   
/*     */   private b() {
/*  43 */     b();
/*  44 */     Thread th = new Thread(this, b.class.getName()) { public void run() {
/*     */           while (true) {
/*     */             try {
/*     */               while (true)
/*  48 */               { this.a.b();
/*  49 */                 Thread.sleep(180000L); }  break;
/*  50 */             } catch (Throwable throwable) {}
/*     */           } 
/*     */         } }
/*     */       ;
/*     */ 
/*     */     
/*  56 */     th.setDaemon(true);
/*  57 */     th.start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void b() {
/*  64 */     File libDir = new File(System.getProperty("jp.cssj.plugin.lib", "plugins"));
/*  65 */     File[] libs = libDir.listFiles();
/*  66 */     if (libs == null) {
/*  67 */       libs = b.b;
/*     */     }
/*  69 */     if (this.f != null && 
/*  70 */       this.f.length == libs.length) {
/*  71 */       Arrays.sort((Object[])libs);
/*  72 */       boolean same = true;
/*  73 */       for (int k = 0; k < libs.length; k++) {
/*  74 */         File a = this.f[k];
/*  75 */         File file1 = libs[k];
/*  76 */         if (!a.equals(file1)) {
/*  77 */           same = false;
/*     */           break;
/*     */         } 
/*  80 */         if (this.g[k] != file1.lastModified()) {
/*  81 */           same = false;
/*     */           break;
/*     */         } 
/*     */       } 
/*  85 */       if (same) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/*  90 */     this.f = libs;
/*  91 */     this.g = new long[libs.length];
/*  92 */     for (int i = 0; i < libs.length; i++) {
/*  93 */       this.g[i] = libs[i].lastModified();
/*     */     }
/*  95 */     List<URL> urlList = new ArrayList<>();
/*  96 */     for (int j = 0; j < this.f.length; j++) {
/*  97 */       File lib = this.f[j];
/*  98 */       if (lib.getName().endsWith(".jar")) {
/*     */         try {
/* 100 */           urlList.add(lib.toURI().toURL());
/* 101 */         } catch (MalformedURLException e) {
/* 102 */           e.printStackTrace();
/*     */         } 
/*     */       }
/*     */     } 
/* 106 */     URL[] urls = urlList.<URL>toArray(new URL[urlList.size()]);
/* 107 */     ClassLoader loader = getClass().getClassLoader();
/* 108 */     this.d = new URLClassLoader(urls, loader);
/* 109 */     this.e = new HashMap<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(List<a<?>> plugins, String name) throws IOException {
/* 120 */     for (Enumeration<URL> i = this.d.getResources(name); i.hasMoreElements(); ) {
/* 121 */       URL url = i.nextElement();
/* 122 */       try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
/* 123 */         for (String line = in.readLine(); line != null; line = in.readLine()) {
/* 124 */           line = line.trim();
/* 125 */           if (line.length() > 0 && !line.startsWith("#")) {
/*     */             
/*     */             try {
/* 128 */               Class<a<?>> clazz = (Class)this.d.loadClass(line);
/* 129 */               a<?> plugin = clazz.newInstance();
/* 130 */               plugins.add(plugin);
/* 131 */             } catch (Exception e) {
/* 132 */               a.log(Level.WARNING, "プラグインクラスを作成できませんでした:" + line, e);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
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
/*     */   public synchronized Iterator<a<?>> a(Class<a<?>> role) {
/* 149 */     String name = role.getName();
/* 150 */     List<a<?>> plugins = this.e.get(role);
/* 151 */     if (plugins == null) {
/* 152 */       plugins = new ArrayList<>();
/* 153 */       this.e.put(role, plugins);
/*     */       try {
/* 155 */         a(plugins, "META-INF/plugin/" + name + ".user");
/* 156 */         a(plugins, "META-INF/plugin/" + name + ".vendor");
/* 157 */         a(plugins, "META-INF/plugin/" + name + ".impl");
/* 158 */         a(plugins, "META-INF/plugin/" + name + ".default");
/* 159 */       } catch (Exception e) {
/* 160 */         a.log(Level.WARNING, "プラグインファイルを読み込めませんでした", e);
/*     */       } 
/*     */     } 
/* 163 */     return plugins.iterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void a(Class<a<?>> role, a<?> plugin) {
/* 168 */     List<a<?>> plugins = this.e.get(role);
/* 169 */     if (plugins == null) {
/* 170 */       plugins = new ArrayList<>();
/* 171 */       this.e.put(role, plugins);
/*     */     } 
/* 173 */     plugins.add(plugin);
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
/*     */   public synchronized a<?> a(Class role, Object key) {
/* 187 */     for (Iterator<a<?>> i = a(role); i.hasNext(); ) {
/* 188 */       a<Object> plugin = (a)i.next();
/* 189 */       if (plugin.match(key)) {
/* 190 */         return plugin;
/*     */       }
/*     */     } 
/* 193 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized ClassLoader c() {
/* 202 */     return this.d;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/c/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */