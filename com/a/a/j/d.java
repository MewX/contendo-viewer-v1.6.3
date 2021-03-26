/*     */ package com.a.a.j;
/*     */ 
/*     */ import com.a.a.j.c.g;
/*     */ import com.a.a.j.c.h;
/*     */ import com.a.a.j.c.k;
/*     */ import com.a.a.j.d.a.c;
/*     */ import com.a.a.j.d.d;
/*     */ import com.a.a.j.d.h;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.URI;
/*     */ import java.security.SecureRandom;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.WeakHashMap;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class d
/*     */   implements d
/*     */ {
/*     */   protected static final Logger a;
/*     */   protected static final boolean b;
/*  35 */   protected static final char[] c = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-()".toCharArray();
/*     */ 
/*     */   
/*  38 */   public static final com.a.a.d.b d = new com.a.a.d.b("application", "vdr.essg.sdc.direcory");
/*     */   
/*  40 */   public static final com.a.a.d.b e = new com.a.a.d.b("application", "vdr.essg.sdc.unknown");
/*     */ 
/*     */   
/*  43 */   private static WeakHashMap<Object, d> h = new WeakHashMap<Object, d>();
/*     */ 
/*     */   
/*  46 */   protected static Random f = null;
/*     */ 
/*     */   
/*  49 */   protected static File g = null;
/*     */   
/*  51 */   private static b i = null;
/*     */   
/*     */   static {
/*  54 */     a = LoggerFactory.getLogger(d.class);
/*  55 */     b = (a == null) ? false : a.isDebugEnabled();
/*     */     
/*  57 */     Random tr = new Random(System.currentTimeMillis());
/*  58 */     byte[] seeds = new byte[64];
/*  59 */     tr.nextBytes(seeds);
/*  60 */     f = new SecureRandom(seeds);
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
/*     */   public static d a(Object context) {
/*  87 */     return a(context, (d)null, (c<d>)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized d a(Object context, c<d> callback) {
/*  96 */     return a(context, (d)null, callback);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized d a(Object context, d keyStore) {
/* 105 */     return a(context, keyStore, (c<d>)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized d a(Object context, d keyStore, c<d> callback) {
/* 116 */     d manager = h.get(context);
/* 117 */     if (manager == null) {
/* 118 */       a.debug("create instance (" + context.toString() + ")");
/*     */       try {
/* 120 */         manager = a().a(context, keyStore);
/* 121 */       } catch (IOException e) {
/* 122 */         throw new RuntimeException(e);
/*     */       } 
/* 124 */       if (callback != null) {
/* 125 */         manager = callback.a(manager, context, keyStore);
/*     */       }
/* 127 */       h.put(context, manager);
/*     */     } 
/* 129 */     return manager;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized b a() {
/* 136 */     if (i == null) i = new e.a(); 
/* 137 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static synchronized void a(b factory) {
/* 144 */     i = factory;
/*     */   } public abstract File c();
/*     */   public abstract void b(File paramFile);
/*     */   public abstract void a(b paramb);
/*     */   public abstract List<g> a(File paramFile, boolean paramBoolean1, boolean paramBoolean2) throws IOException;
/*     */   public abstract List<g> a(String paramString, boolean paramBoolean1, boolean paramBoolean2) throws IOException;
/*     */   public abstract List<g> a(URI paramURI, boolean paramBoolean1, boolean paramBoolean2) throws IOException;
/*     */   public static void a(File cacheDir) {
/* 152 */     g = cacheDir;
/*     */   } public abstract URI a(String paramString); public abstract URI a(URI paramURI); public abstract g b(String paramString) throws IOException; public abstract g c(File paramFile) throws IOException; public abstract g b(URI paramURI) throws IOException; public abstract void a(d paramd); public abstract void b(d paramd);
/*     */   public abstract URI d();
/*     */   public abstract String e();
/*     */   public abstract String c(URI paramURI) throws IOException;
/*     */   public abstract String[] f();
/*     */   public abstract Object a(String[] paramArrayOfString);
/*     */   protected static File b() {
/* 160 */     if (g == null) {
/* 161 */       String path = System.getProperty("java.io.tmpdir");
/* 162 */       if (path == null || path.length() == 0) {
/* 163 */         path = ".";
/*     */       }
/* 165 */       return new File(path);
/*     */     } 
/* 167 */     return g;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract Collection<com.a.a.d.b> d(URI paramURI);
/*     */   
/*     */   public abstract boolean a(String paramString1, String paramString2);
/*     */   
/*     */   public abstract h b(String paramString1, String paramString2) throws c;
/*     */   
/*     */   public abstract void a(k.a parama);
/*     */   
/*     */   public abstract h g();
/*     */   
/*     */   public static class a
/*     */     implements d
/*     */   {
/*     */     public a(String path, char[] password) {
/* 185 */       this.a = path;
/* 186 */       this.b = password;
/*     */     }
/*     */ 
/*     */     
/*     */     private String a;
/*     */     private char[] b;
/*     */     
/*     */     public InputStream a(Object context) {
/* 194 */       return getClass().getClassLoader().getResourceAsStream(this.a);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public char[] b(Object context) {
/* 202 */       return this.b;
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface b {
/*     */     File a(Object param1Object);
/*     */   }
/*     */   
/*     */   public static interface c<T> {
/*     */     T a(T param1T, Object param1Object, d.d param1d);
/*     */   }
/*     */   
/*     */   public static interface d {
/*     */     InputStream a(Object param1Object);
/*     */     
/*     */     char[] b(Object param1Object);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */