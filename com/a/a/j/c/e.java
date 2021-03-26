/*     */ package com.a.a.j.c;
/*     */ 
/*     */ import com.a.a.d.b;
/*     */ import com.a.a.e.b;
/*     */ import com.a.a.j.d;
/*     */ import com.a.a.j.e.a;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class e
/*     */   extends i
/*     */   implements g
/*     */ {
/*     */   protected m c;
/*     */   protected String d;
/*     */   
/*     */   public e(m folder, String path) {
/*  22 */     this.c = folder;
/*  23 */     int s = 0;
/*  24 */     int j = path.length();
/*  25 */     if (path.startsWith("/")) s++; 
/*  26 */     if (path.endsWith("/")) j--; 
/*  27 */     this.d = path.substring(s, j).intern();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b e() throws IOException {
/*  35 */     return (b)new a(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String b() {
/*  42 */     return this.d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean h() {
/*  50 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean i() {
/*  58 */     return h();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public m c() {
/*  66 */     return this.c;
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
/*     */   public String a_() {
/*  78 */     return this.c.b(this.d);
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
/*     */   public int a(f o) {
/*  94 */     if (o instanceof g) {
/*  95 */       g g1 = (g)o;
/*  96 */       int c = c().compareTo(g1.c());
/*  97 */       if (c == 0) {
/*  98 */         if (o instanceof e) {
/*  99 */           c = this.d.compareTo(((e)o).d);
/*     */         } else {
/* 101 */           c = a_().compareTo(g1.a_());
/*     */         } 
/*     */       }
/* 104 */       return c;
/*     */     } 
/* 106 */     return a_().compareTo(o.a_());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static b a(g file) {
/* 114 */     b mimetype = null;
/* 115 */     if (file.g()) {
/* 116 */       mimetype = d.d;
/*     */     } else {
/*     */       try {
/* 119 */         URL url = file.o();
/* 120 */         mimetype = b.a(b.a(url));
/* 121 */       } catch (IOException iOException) {}
/*     */       
/* 123 */       if (mimetype == null) {
/* 124 */         mimetype = d.e;
/*     */       }
/*     */     } 
/* 127 */     return mimetype;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b j() {
/* 134 */     if (this.e == null) {
/* 135 */       this.e = a(this);
/*     */     }
/* 137 */     return this.e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static URL b(g file) throws MalformedURLException {
/* 146 */     return new URL("vfile", "localhost", 0, file.a_(), new j(file));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL o() throws MalformedURLException {
/* 154 */     return b(this);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/c/e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */