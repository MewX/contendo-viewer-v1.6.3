/*     */ package com.a.a.d;
/*     */ 
/*     */ import com.a.a.i.g;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.File;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   implements Comparable<b>
/*     */ {
/*     */   final String a;
/*     */   final String b;
/*     */   private static boolean c = false;
/*     */   private static boolean d = false;
/*     */   private static boolean e = true;
/*     */   private static boolean f = true;
/*  24 */   private static Object g = null;
/*  25 */   private static Object h = null;
/*  26 */   private static Class<?> i = null;
/*     */   private static Method j;
/*     */   private static Method k;
/*     */   private static Method l;
/*     */   private static Method m;
/*     */   private static Method n;
/*     */   private static Method o;
/*     */   private static Object p;
/*     */   
/*     */   public static void a(boolean bUseMagic) {
/*  36 */     e = bUseMagic;
/*     */   }
/*     */   
/*     */   public static void b(boolean bUseExtention) {
/*  40 */     f = bUseExtention;
/*     */   }
/*     */   
/*     */   private void c() {
/*  44 */     if (!c) {
/*  45 */       synchronized (b.class) {
/*  46 */         if (!c) {
/*  47 */           Class<?> clazz = com.a.a.i.b.a("eu.medsea.mimeutil.MimeUtil2", new String[0]);
/*  48 */           i = com.a.a.i.b.a("eu.medsea.mimeutil.MimeType", new String[0]);
/*  49 */           if (clazz != null && i != null) {
/*     */             try {
/*  51 */               Method method = clazz.getMethod("registerMimeDetector", new Class[] { String.class });
/*  52 */               j = clazz.getMethod("getMimeTypes", new Class[] { InputStream.class });
/*  53 */               k = clazz.getMethod("getMimeTypes", new Class[] { URL.class });
/*  54 */               l = clazz.getMethod("getMimeTypes", new Class[] { File.class });
/*  55 */               m = clazz.getMethod("getMimeTypes", new Class[] { String.class });
/*  56 */               p = clazz.getDeclaredField("UNKNOWN_MIME_TYPE").get(null);
/*  57 */               n = i.getMethod("getMediaType", new Class[0]);
/*  58 */               o = i.getMethod("getSubType", new Class[0]);
/*  59 */               if (e) {
/*  60 */                 g = com.a.a.i.b.a(clazz, new Object[0]);
/*  61 */                 method.invoke(g, new Object[] { "eu.medsea.mimeutil.detector.MagicMimeMimeDetector" });
/*     */               } 
/*  63 */               if (f) {
/*  64 */                 h = com.a.a.i.b.a(clazz, new Object[0]);
/*  65 */                 method.invoke(h, new Object[] { "eu.medsea.mimeutil.detector.ExtensionMimeDetector" });
/*     */               } 
/*  67 */               d = !(!e && !f);
/*  68 */             } catch (Exception exception) {}
/*     */           }
/*     */           
/*  71 */           c = true;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static b a(Collection<b> mimes) {
/*  78 */     b m = null;
/*  79 */     if (mimes != null && !mimes.isEmpty())
/*     */     {
/*  81 */       if (mimes instanceof List) {
/*  82 */         m = ((List<b>)mimes).get(0);
/*     */       } else {
/*  84 */         m = mimes.iterator().next();
/*     */       } 
/*     */     }
/*  87 */     return m;
/*     */   }
/*     */   
/*     */   private static Collection<b> a(ArrayList<b> result, Method method, Object parm) {
/*  91 */     if (g != null) {
/*  92 */       if (h != null && method != j) {
/*  93 */         Collection<?> mimeTemp = 
/*  94 */           (Collection)g.a(method, g, new Object[] { parm });
/*  95 */         if (mimeTemp != null) {
/*  96 */           for (Object m : mimeTemp) {
/*  97 */             if (m.equals(p))
/*  98 */               break;  result.add(new b(m));
/*     */           } 
/*     */         }
/* 101 */         if (result.isEmpty()) {
/* 102 */           mimeTemp = 
/* 103 */             (Collection)g.a(method, h, new Object[] { parm });
/* 104 */           if (mimeTemp != null) {
/* 105 */             for (Object m : mimeTemp) {
/* 106 */               result.add(new b(m));
/*     */             }
/*     */           }
/*     */         } 
/*     */       } else {
/* 111 */         Collection<?> mimeTemp = 
/* 112 */           (Collection)g.a(method, g, new Object[] { parm });
/* 113 */         if (mimeTemp != null) {
/* 114 */           for (Object m : mimeTemp) {
/* 115 */             result.add(new b(m));
/*     */           }
/*     */         }
/*     */       } 
/* 119 */     } else if (h != null) {
/* 120 */       Collection<?> mimeTemp = 
/* 121 */         (Collection)g.a(method, h, new Object[] { parm });
/* 122 */       if (mimeTemp != null) {
/* 123 */         for (Object m : mimeTemp) {
/* 124 */           result.add(new b(m));
/*     */         }
/*     */       }
/*     */     } 
/* 128 */     return result;
/*     */   }
/*     */   
/*     */   public static Collection<b> a(InputStream is) {
/* 132 */     ArrayList<b> result = new ArrayList<b>();
/* 133 */     if (is != null && d) {
/*     */       try {
/* 135 */         if (!is.markSupported()) {
/* 136 */           is = new BufferedInputStream(is);
/*     */         }
/* 138 */         a(result, j, is);
/*     */       } finally {
/* 140 */         com.a.a.b.b.b.a(is);
/*     */       } 
/*     */     }
/* 143 */     return result;
/*     */   }
/*     */   
/*     */   public static Collection<b> a(URL url) {
/* 147 */     ArrayList<b> result = new ArrayList<b>();
/* 148 */     a(result, k, url);
/* 149 */     return result;
/*     */   }
/*     */   
/*     */   public static Collection<b> a(File file) {
/* 153 */     ArrayList<b> result = new ArrayList<b>();
/* 154 */     a(result, l, file);
/* 155 */     return result;
/*     */   }
/*     */   
/*     */   public static Collection<b> a(String filename) {
/* 159 */     ArrayList<b> result = new ArrayList<b>();
/* 160 */     a(result, m, filename);
/* 161 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b(Object type) {
/* 169 */     c();
/* 170 */     if (type == null) {
/* 171 */       this.a = "*";
/* 172 */       this.b = "*";
/* 173 */     } else if (i != null && i.isAssignableFrom(type.getClass())) {
/* 174 */       this.a = b((String)g.a(n, type, new Object[0]));
/* 175 */       this.b = b((String)g.a(o, type, new Object[0]));
/*     */     } else {
/* 177 */       String str = type.toString();
/* 178 */       int sep = str.indexOf("/");
/* 179 */       if (sep >= 0) {
/* 180 */         this.a = b(str.substring(0, sep));
/* 181 */         this.b = b(str.substring(sep + 1));
/*     */       } else {
/* 183 */         this.a = "*";
/* 184 */         this.b = "*";
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b(String type, String subtype) {
/* 195 */     c();
/* 196 */     this.a = b(type);
/* 197 */     this.b = b(subtype);
/*     */   }
/*     */   
/*     */   private String b(String txt) {
/* 201 */     if (txt == null || txt.length() == 0) {
/* 202 */       return "*";
/*     */     }
/* 204 */     return txt.intern();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String a() {
/* 212 */     return this.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String b() {
/* 219 */     return this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 226 */     return String.valueOf(this.a) + "/" + this.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(b o) {
/* 234 */     int c = 0;
/* 235 */     if (!this.a.equals("*") && !o.a.equals("*")) {
/* 236 */       c = this.a.compareTo(o.a);
/*     */     }
/* 238 */     if (c == 0 && 
/* 239 */       !this.b.equals("*") && !o.b.equals("*")) {
/* 240 */       c = this.b.compareTo(o.b);
/*     */     }
/*     */     
/* 243 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 251 */     if (obj instanceof b) return (a((b)obj) == 0); 
/* 252 */     return (a(new b(obj.toString())) == 0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/d/b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */