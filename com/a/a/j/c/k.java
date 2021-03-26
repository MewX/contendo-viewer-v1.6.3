/*     */ package com.a.a.j.c;
/*     */ 
/*     */ import com.a.a.d.b;
/*     */ import com.a.a.e.b;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Collection;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class k
/*     */   implements g
/*     */ {
/*  19 */   private static final Logger d = LoggerFactory.getLogger(k.class);
/*     */ 
/*     */   
/*     */   protected final g a;
/*     */ 
/*     */   
/*     */   protected final Object b;
/*     */   
/*  27 */   protected b c = null;
/*     */   
/*     */   public static g a(g source, Object context, Collection<a> creators) throws com.a.a.j.a.a {
/*  30 */     boolean bDir = source.g();
/*     */     
/*  32 */     g fi = null;
/*     */     do {
/*  34 */       for (a cr : creators) {
/*  35 */         if ((bDir && cr.a()) || (!bDir && cr.b())) {
/*     */           try {
/*  37 */             fi = cr.a(source, context);
/*  38 */             if (fi != null) {
/*     */               
/*  40 */               source = fi;
/*     */               break;
/*     */             } 
/*  43 */           } catch (com.a.a.j.a.a ae) {
/*  44 */             throw ae;
/*  45 */           } catch (Exception e) {
/*  46 */             d.error("filter()", e);
/*     */           } 
/*     */         }
/*     */       } 
/*  50 */     } while (fi != null);
/*     */     
/*  52 */     return source;
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
/*     */   protected k(g file, Object context) {
/*  89 */     this.a = file;
/*     */     
/*  91 */     this.b = context;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream d() throws IOException {
/*  99 */     return this.a.d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b e() throws IOException {
/* 107 */     return this.a.e();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long f() {
/* 115 */     return this.a.f();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b j() {
/* 123 */     if (this.c == null) {
/* 124 */       this.c = i.a(this);
/*     */     }
/* 126 */     return this.c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean k() {
/* 134 */     return this.a.k();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream l() throws IOException {
/* 142 */     return this.a.l();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long m() {
/* 150 */     return this.a.m();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String a_() {
/* 158 */     return this.a.a_();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(f o) {
/* 166 */     return this.a.compareTo(o);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String b() {
/* 174 */     return this.a.b();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public m c() {
/* 182 */     return this.a.c();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean g() {
/* 190 */     return this.a.g();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean h() {
/* 198 */     return this.a.h();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean i() {
/* 206 */     return this.a.i();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL o() throws MalformedURLException {
/* 214 */     return e.b(this);
/*     */   }
/*     */   
/*     */   public static interface a {
/*     */     g a(g param1g, Object param1Object) throws IOException;
/*     */     
/*     */     boolean a();
/*     */     
/*     */     boolean b();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/j/c/k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */