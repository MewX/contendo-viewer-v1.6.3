/*     */ package com.a.a.j;
/*     */ 
/*     */ import com.a.a.b.b;
/*     */ import com.a.a.b.b.b;
/*     */ import com.a.a.b.f;
/*     */ import com.a.a.e.b;
/*     */ import com.a.a.j.c.a.c;
/*     */ import com.a.a.j.c.g;
/*     */ import com.a.a.j.c.p;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   implements f, a
/*     */ {
/*     */   g a;
/*     */   c b;
/*  27 */   HashMap<String, p> c = new HashMap<String, p>();
/*     */ 
/*     */   
/*  30 */   HashMap<Closeable, String> d = new HashMap<Closeable, String>();
/*     */ 
/*     */   
/*  33 */   HashMap<String, p> e = new HashMap<String, p>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c(g archive, c cache) {
/*  41 */     this.a = archive;
/*  42 */     this.b = cache;
/*     */   }
/*     */   
/*     */   public g c(String path) {
/*  46 */     if (path == null || path.length() == 0) {
/*  47 */       return this.a;
/*     */     }
/*  49 */     g p = null;
/*  50 */     synchronized (this.c) {
/*  51 */       p = (g)this.c.get(path);
/*     */     } 
/*  53 */     return p;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void c() {
/*  60 */     synchronized (this.d) {
/*  61 */       for (Closeable out : this.d.keySet()) {
/*     */         try {
/*  63 */           out.close();
/*  64 */         } catch (Exception exception) {}
/*     */       } 
/*     */       
/*  67 */       this.d.clear();
/*  68 */       this.e.clear();
/*     */     } 
/*  70 */     synchronized (this.c) {
/*  71 */       this.c.clear();
/*     */     } 
/*  73 */     this.b.a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream a() throws IOException {
/*  81 */     return this.a.d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b b() throws IOException {
/*  89 */     return this.a.e();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream a(File file) throws IOException {
/*  97 */     g g1 = null;
/*  98 */     synchronized (this.c) {
/*  99 */       g1 = (g)this.c.get(file.getPath());
/*     */     } 
/* 101 */     if (g1 == null) throw new FileNotFoundException();
/*     */     
/* 103 */     return g1.d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b b(File file) throws IOException {
/* 111 */     g g1 = null;
/* 112 */     synchronized (this.c) {
/* 113 */       g1 = (g)this.c.get(file.getPath());
/*     */     } 
/* 115 */     if (g1 == null) throw new FileNotFoundException();
/*     */     
/* 117 */     return g1.e();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream c(File file) throws IOException {
/* 126 */     return a(file, file.getPath());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private OutputStream a(File fpath, String path) throws IOException {
/*     */     b b;
/* 137 */     path = path.intern();
/* 138 */     OutputStream os = null;
/*     */     
/* 140 */     p p = null;
/* 141 */     p = this.c.get(path);
/* 142 */     if (p == null) {
/* 143 */       p = this.b.a(path);
/*     */     }
/*     */ 
/*     */     
/* 147 */     os = p.n();
/* 148 */     if (!(os instanceof b)) {
/* 149 */       b = new b(os);
/*     */     }
/*     */     
/* 152 */     synchronized (this.d) {
/* 153 */       this.d.put(b, path);
/* 154 */       this.e.put(path, p);
/*     */     } 
/* 156 */     b.a(this);
/* 157 */     return (OutputStream)b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(OutputStream stream) {
/* 165 */     String path = null;
/* 166 */     synchronized (this.d) {
/* 167 */       path = this.d.remove(stream);
/*     */     } 
/* 169 */     if (path != null) {
/* 170 */       b(path);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void e(File file) {
/* 179 */     b(file.getAbsolutePath());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(String path) {
/* 187 */     g g1 = null;
/* 188 */     synchronized (this.d) {
/* 189 */       g1 = (g)this.e.remove(path);
/*     */     } 
/* 191 */     if (g1 == null) {
/* 192 */       synchronized (this.c) {
/* 193 */         this.c.remove(path);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(File file) throws FileNotFoundException {
/* 203 */     a(file.getAbsolutePath());
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
/*     */   public void a(String path) throws FileNotFoundException {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Closeable stream, boolean success) {
/* 247 */     String path = null;
/* 248 */     p p = null;
/* 249 */     synchronized (this.d) {
/* 250 */       path = this.d.remove(stream);
/* 251 */       p = this.e.remove(path);
/*     */     } 
/* 253 */     if (success && path != null && p != null) {
/* 254 */       synchronized (this.c) {
/* 255 */         this.c.put(path, p);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(InputStream istream, String opath) throws IOException {
/* 265 */     a(istream, new File(opath));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(InputStream istream, File ofile) throws IOException {
/* 274 */     OutputStream os = c(ofile);
/*     */     try {
/* 276 */       a(istream, os);
/* 277 */     } catch (IOException e) {
/* 278 */       a(os);
/* 279 */       throw e;
/*     */     } finally {
/* 281 */       os.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(InputStream istream, OutputStream ostream) throws IOException {
/* 291 */     b.a(istream, ostream);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */