/*     */ package jp.cssj.sakae.pdf.c.b;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import jp.cssj.sakae.a.a;
/*     */ import jp.cssj.sakae.a.b;
/*     */ import jp.cssj.sakae.a.e;
/*     */ import jp.cssj.sakae.a.g;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.c.d.b;
/*     */ import jp.cssj.sakae.pdf.c.e;
/*     */ import jp.cssj.sakae.pdf.c.f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class c
/*     */   extends a
/*     */   implements f
/*     */ {
/*     */   private static final long k = 1L;
/*     */   protected final a i;
/*     */   private final b l;
/*     */   private final short m;
/*  31 */   protected transient Font j = null;
/*     */   
/*     */   protected c(a fontInfo) {
/*  34 */     this.i = fontInfo;
/*     */     
/*  36 */     Set<String> aliases = new TreeSet<>();
/*  37 */     if (fontInfo.c != null) {
/*  38 */       aliases.add(fontInfo.c);
/*     */     }
/*  40 */     if (fontInfo.b != null) {
/*  41 */       aliases.add(fontInfo.b);
/*     */     }
/*  43 */     this.X_ = aliases.<String>toArray(new String[aliases.size()]);
/*     */     
/*  45 */     this.g = fontInfo.k;
/*  46 */     this.f = fontInfo.j;
/*  47 */     this.l = fontInfo.l;
/*  48 */     this.m = ((a.a)fontInfo.m.get("space")).c;
/*     */   }
/*     */   
/*     */   protected abstract e[] p();
/*     */   
/*     */   protected synchronized Font q() {
/*  54 */     if (this.j == null) {
/*  55 */       this.j = b.a((g)this);
/*     */     }
/*  57 */     return this.j;
/*     */   }
/*     */   
/*     */   public byte e() {
/*  61 */     return 1;
/*     */   }
/*     */   
/*     */   public String d() {
/*  65 */     return this.i.a;
/*     */   }
/*     */   
/*     */   public byte h_() {
/*  69 */     return 1;
/*     */   }
/*     */   
/*     */   short b(int gid) {
/*  73 */     if (!d(gid)) {
/*  74 */       return 0;
/*     */     }
/*  76 */     e gi = p()[gid];
/*  77 */     return gi.a;
/*     */   }
/*     */   
/*     */   short a(int gid, int pgid) {
/*  81 */     if (!d(gid)) {
/*  82 */       return 0;
/*     */     }
/*  84 */     e gi = p()[gid];
/*  85 */     return gi.a(pgid);
/*     */   }
/*     */   
/*     */   int b(int gid, int pgid) {
/*  89 */     if (!d(gid)) {
/*  90 */       return -1;
/*     */     }
/*  92 */     e gi = p()[gid];
/*  93 */     return gi.b(pgid);
/*     */   }
/*     */   
/*     */   abstract int c(int paramInt);
/*     */   
/*     */   abstract String r();
/*     */   
/*     */   public boolean a(int i) {
/* 101 */     int gid = c(i);
/* 102 */     return d(gid);
/*     */   }
/*     */   
/*     */   private boolean d(int gid) {
/* 106 */     if (gid < 0 || gid >= (p()).length) {
/* 107 */       return false;
/*     */     }
/* 109 */     e gi = p()[gid];
/* 110 */     if (gi == null) {
/* 111 */       return false;
/*     */     }
/* 113 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b f() {
/* 120 */     return this.l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short g() {
/* 127 */     return this.i.d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short h() {
/* 134 */     return this.i.f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short i() {
/* 141 */     return this.i.e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short j() {
/* 148 */     return this.i.h;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short k() {
/* 155 */     return this.i.g;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short l() {
/* 162 */     return this.i.i;
/*     */   }
/*     */   
/*     */   public short m() {
/* 166 */     return this.m;
/*     */   }
/*     */   
/*     */   public e a(String name, b fontRef) {
/* 170 */     return new i(this, name, r(), fontRef);
/*     */   }
/*     */   
/*     */   public e n() {
/* 174 */     return (e)a((String)null, (b)null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/b/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */