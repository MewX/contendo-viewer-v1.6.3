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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class r
/*     */   implements aa
/*     */ {
/*     */   private int a;
/*     */   private int b;
/*     */   private int c;
/*     */   private int d;
/*     */   private short e;
/*     */   private short f;
/*     */   private long g;
/*     */   private long h;
/*     */   private short i;
/*     */   private short j;
/*     */   private short k;
/*     */   private short l;
/*     */   private short m;
/*     */   private short n;
/*     */   private short o;
/*     */   private short p;
/*     */   private short q;
/*     */   
/*     */   protected r(i de, RandomAccessFile raf) throws IOException {
/*  64 */     synchronized (raf) {
/*  65 */       raf.seek(de.c());
/*  66 */       this.a = raf.readInt();
/*  67 */       this.b = raf.readInt();
/*  68 */       this.c = raf.readInt();
/*  69 */       this.d = raf.readInt();
/*  70 */       this.e = raf.readShort();
/*  71 */       this.f = raf.readShort();
/*  72 */       this.g = raf.readLong();
/*  73 */       this.h = raf.readLong();
/*  74 */       this.i = raf.readShort();
/*  75 */       this.j = raf.readShort();
/*  76 */       this.k = raf.readShort();
/*  77 */       this.l = raf.readShort();
/*  78 */       this.m = raf.readShort();
/*  79 */       this.n = raf.readShort();
/*  80 */       this.o = raf.readShort();
/*  81 */       this.p = raf.readShort();
/*  82 */       this.q = raf.readShort();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int b() {
/*  87 */     return this.c;
/*     */   }
/*     */   
/*     */   public long c() {
/*  91 */     return this.g;
/*     */   }
/*     */   
/*     */   public short d() {
/*  95 */     return this.e;
/*     */   }
/*     */   
/*     */   public short e() {
/*  99 */     return this.o;
/*     */   }
/*     */   
/*     */   public int f() {
/* 103 */     return this.b;
/*     */   }
/*     */   
/*     */   public short g() {
/* 107 */     return this.q;
/*     */   }
/*     */   
/*     */   public short h() {
/* 111 */     return this.p;
/*     */   }
/*     */   
/*     */   public short i() {
/* 115 */     return this.n;
/*     */   }
/*     */   
/*     */   public short j() {
/* 119 */     return this.m;
/*     */   }
/*     */   
/*     */   public long k() {
/* 123 */     return this.h;
/*     */   }
/*     */   
/*     */   public int a() {
/* 127 */     return 1751474532;
/*     */   }
/*     */   
/*     */   public short l() {
/* 131 */     return this.f;
/*     */   }
/*     */   
/*     */   public int m() {
/* 135 */     return this.a;
/*     */   }
/*     */   
/*     */   public short n() {
/* 139 */     return this.k;
/*     */   }
/*     */   
/*     */   public short o() {
/* 143 */     return this.i;
/*     */   }
/*     */   
/*     */   public short p() {
/* 147 */     return this.l;
/*     */   }
/*     */   
/*     */   public short q() {
/* 151 */     return this.j;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 155 */     return "head\n\tversionNumber: " + this.a + "\n\tfontRevision: " + this.b + 
/* 156 */       "\n\tcheckSumAdjustment: " + this.c + "\n\tmagicNumber: " + 
/* 157 */       this.d + "\n\tflags: " + this.e + "\n\tunitsPerEm: " + 
/* 158 */       this.f + "\n\tcreated: " + this.g + "\n\tmodified: " + 
/* 159 */       this.h + "\n\txMin: " + this.i + ", yMin: " + this.j + 
/* 160 */       "\n\txMax: " + this.k + ", yMax: " + this.l + "\n\tmacStyle: " + this.m + 
/* 161 */       "\n\tlowestRecPPEM: " + this.n + "\n\tfontDirectionHint: " + this.o + 
/* 162 */       "\n\tindexToLocFormat: " + this.p + "\n\tglyphDataFormat: " + 
/* 163 */       this.q;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/r.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */