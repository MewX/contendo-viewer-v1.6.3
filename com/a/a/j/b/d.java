/*     */ package com.a.a.j.b;
/*     */ 
/*     */ import com.a.a.b.b.b;
/*     */ import com.a.a.b.c.b;
/*     */ import com.a.a.d.b;
/*     */ import com.a.a.e.b;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class d
/*     */   implements c
/*     */ {
/*  29 */   private static final List<b> a = Arrays.asList(new b[] { new b("application", "zip") });
/*     */ 
/*     */   
/*  32 */   private static final Logger b = LoggerFactory.getLogger(d.class);
/*     */ 
/*     */   
/*     */   private class a
/*     */     implements b
/*     */   {
/*     */     b.b a;
/*     */     
/*     */     protected a(d this$0, b.b entry) {
/*  41 */       this.a = entry;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public InputStream a(com.a.a.j.a handler) throws IOException {
/*  49 */       return this.a.i();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public b b(com.a.a.j.a handler) throws IOException {
/*  57 */       b buf = this.a.j();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  68 */       return buf;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void c(com.a.a.j.a handler) throws IOException {
/*  76 */       if (!f(handler)) {
/*  77 */         d.c().debug("getFile() -start- " + this.a.e());
/*  78 */         InputStream is = a(handler);
/*     */         try {
/*  80 */           OutputStream os = handler.c(new File(this.a.e()));
/*     */           try {
/*  82 */             handler.a(is, os);
/*  83 */           } catch (IOException e) {
/*  84 */             handler.a(os);
/*  85 */             throw e;
/*     */           } finally {
/*  87 */             b.a(os);
/*     */           } 
/*     */         } finally {
/*  90 */           b.a(is);
/*  91 */           d.c().debug("getFile() - end - " + this.a.e());
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public long d(com.a.a.j.a handler) {
/* 101 */       return this.a.b();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public long e(com.a.a.j.a handler) {
/* 108 */       return this.a.a();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean f(com.a.a.j.a handler) {
/* 116 */       return this.a.h();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean g(com.a.a.j.a handler) {
/* 124 */       return ((this.a.c() & 0x8) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public InputStream h(com.a.a.j.a handler) throws IOException {
/* 133 */       if (g(handler)) {
/* 134 */         return this.a.a(true);
/*     */       }
/* 136 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public com.a.a.b.c.a i(com.a.a.j.a handler) throws IOException {
/* 145 */       return (com.a.a.b.c.a)this.a;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 150 */   private HashMap<String, b> c = new HashMap<String, b>();
/* 151 */   private b d = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<b> a() {
/* 158 */     return a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b() {
/* 165 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(com.a.a.j.a handler) {
/*     */     try {
/* 175 */       this.d = new b(handler.b());
/* 176 */       Enumeration<? extends b.b> en = this.d.b();
/* 177 */       while (en.hasMoreElements()) {
/* 178 */         b.b entry = en.nextElement();
/*     */         
/* 180 */         File f = new File(entry.e());
/* 181 */         while (f != null) {
/* 182 */           String n = f.getAbsolutePath();
/* 183 */           if (this.c.containsKey(n))
/* 184 */             break;  if (entry == null) entry = new b.b(); 
/* 185 */           this.c.put(n, new a(this, entry));
/* 186 */           entry = null;
/* 187 */           f = f.getParentFile();
/*     */         } 
/*     */       } 
/* 190 */       return (this.c.size() > 0);
/* 191 */     } catch (Exception e) {
/* 192 */       b.error("isExtractable", e);
/*     */       
/* 194 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b a(com.a.a.j.a handler, File file) {
/*     */     try {
/* 203 */       String path = file.getAbsolutePath();
/* 204 */       b entry = this.c.get(path);
/* 205 */       if (entry == null) {
/* 206 */         b.warn("extract() not found in map :" + path);
/* 207 */         for (String p : this.c.keySet()) {
/* 208 */           File f = (new File(p)).getParentFile();
/* 209 */           if (f != null && f.equals(file)) {
/* 210 */             b.warn("extract() make directory");
/* 211 */             entry = a.a();
/* 212 */             this.c.put(path, entry);
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 218 */       return entry;
/* 219 */     } catch (Exception e) {
/* 220 */       b.error("extract", e);
/*     */       
/* 222 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public File[] b(com.a.a.j.a handler, File file) {
/* 230 */     String path = (file == null) ? null : file.getAbsolutePath();
/* 231 */     ArrayList<File> list = new ArrayList<File>();
/* 232 */     for (String key : this.c.keySet()) {
/* 233 */       if (path == null || key.startsWith(path)) {
/* 234 */         list.add(new File(key));
/*     */       }
/*     */     } 
/* 237 */     return list.<File>toArray(new File[list.size()]);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/b/d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */