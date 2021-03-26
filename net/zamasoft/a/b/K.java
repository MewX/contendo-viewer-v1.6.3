/*     */ package net.zamasoft.a.b;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
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
/*     */ public class K
/*     */   implements aa
/*     */ {
/*     */   private int a;
/*     */   private int b;
/*     */   private int c;
/*     */   private int d;
/*     */   private int e;
/*     */   private int f;
/*     */   private int g;
/*     */   private int h;
/*     */   private int i;
/*     */   private int j;
/*     */   private int k;
/*     */   private int l;
/*     */   private int m;
/*     */   private int n;
/*     */   
/*     */   protected K(i de, RandomAccessFile raf) throws IOException {
/*  58 */     synchronized (raf) {
/*  59 */       raf.seek(de.c());
/*  60 */       raf.readInt();
/*  61 */       this.a = raf.readUnsignedShort();
/*  62 */       this.b = raf.readUnsignedShort();
/*  63 */       this.c = raf.readUnsignedShort();
/*  64 */       this.d = raf.readUnsignedShort();
/*  65 */       this.e = raf.readUnsignedShort();
/*  66 */       this.f = raf.readUnsignedShort();
/*  67 */       this.g = raf.readUnsignedShort();
/*  68 */       this.h = raf.readUnsignedShort();
/*  69 */       this.i = raf.readUnsignedShort();
/*  70 */       this.j = raf.readUnsignedShort();
/*  71 */       this.k = raf.readUnsignedShort();
/*  72 */       this.l = raf.readUnsignedShort();
/*  73 */       this.m = raf.readUnsignedShort();
/*  74 */       this.n = raf.readUnsignedShort();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int b() {
/*  79 */     return this.n;
/*     */   }
/*     */   
/*     */   public int c() {
/*  83 */     return this.m;
/*     */   }
/*     */   
/*     */   public int d() {
/*  87 */     return this.e;
/*     */   }
/*     */   
/*     */   public int e() {
/*  91 */     return this.d;
/*     */   }
/*     */   
/*     */   public int f() {
/*  95 */     return this.c;
/*     */   }
/*     */   
/*     */   public int g() {
/*  99 */     return this.i;
/*     */   }
/*     */   
/*     */   public int h() {
/* 103 */     return this.j;
/*     */   }
/*     */   
/*     */   public int i() {
/* 107 */     return this.b;
/*     */   }
/*     */   
/*     */   public int j() {
/* 111 */     return this.l;
/*     */   }
/*     */   
/*     */   public int k() {
/* 115 */     return this.k;
/*     */   }
/*     */   
/*     */   public int l() {
/* 119 */     return this.h;
/*     */   }
/*     */   
/*     */   public int m() {
/* 123 */     return this.g;
/*     */   }
/*     */   
/*     */   public int n() {
/* 127 */     return this.f;
/*     */   }
/*     */   
/*     */   public int o() {
/* 131 */     return this.a;
/*     */   }
/*     */   
/*     */   public int a() {
/* 135 */     return 1835104368;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/K.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */