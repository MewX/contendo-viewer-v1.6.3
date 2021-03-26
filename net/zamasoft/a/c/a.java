/*     */ package net.zamasoft.a.c;
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
/*     */ public class a
/*     */ {
/*     */   public static final short a = 1;
/*     */   public static final short b = 2;
/*     */   public static final short c = 4;
/*     */   public static final short d = 8;
/*     */   public static final short e = 32;
/*     */   public static final short f = 64;
/*     */   public static final short g = 128;
/*     */   public static final short h = 256;
/*     */   public static final short i = 512;
/*     */   private int j;
/*     */   private int k;
/*     */   private short l;
/*     */   private short m;
/*     */   private short n;
/*     */   private short o;
/*  59 */   private double p = 1.0D;
/*     */   
/*  61 */   private double q = 1.0D;
/*     */   
/*  63 */   private double r = 0.0D;
/*     */   
/*  65 */   private double s = 0.0D;
/*     */   
/*  67 */   private int t = 0;
/*     */   
/*  69 */   private int u = 0;
/*     */   
/*     */   protected a(int firstIndex, int firstContour, RandomAccessFile raf) throws IOException {
/*  72 */     this.j = firstIndex;
/*  73 */     this.k = firstContour;
/*  74 */     this.n = (short)(raf.read() << 8 | raf.read());
/*  75 */     this.o = (short)Math.abs((short)(raf.read() << 8 | raf.read()));
/*     */ 
/*     */     
/*  78 */     if ((this.n & 0x1) != 0) {
/*  79 */       this.l = (short)(raf.read() << 8 | raf.read());
/*  80 */       this.m = (short)(raf.read() << 8 | raf.read());
/*     */     } else {
/*  82 */       this.l = (short)raf.read();
/*  83 */       this.m = (short)raf.read();
/*     */     } 
/*     */ 
/*     */     
/*  87 */     if ((this.n & 0x2) != 0) {
/*  88 */       this.t = this.l;
/*  89 */       this.u = this.m;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  94 */     int i = (short)(raf.read() << 8 | raf.read());
/*  95 */     this.p = this.q = i / 16384.0D;
/*  96 */     if ((this.n & 0x40) != 0) {
/*  97 */       short s = (short)(raf.read() << 8 | raf.read());
/*  98 */       this.p = s / 16384.0D;
/*  99 */       s = (short)(raf.read() << 8 | raf.read());
/* 100 */       this.q = s / 16384.0D;
/* 101 */     } else if ((this.n & 0x80) != 0) {
/* 102 */       i = (short)(raf.read() << 8 | raf.read());
/* 103 */       this.p = i / 16384.0D;
/* 104 */       i = (short)(raf.read() << 8 | raf.read());
/* 105 */       this.r = i / 16384.0D;
/* 106 */       i = (short)(raf.read() << 8 | raf.read());
/* 107 */       this.s = i / 16384.0D;
/* 108 */       i = (short)(raf.read() << 8 | raf.read());
/* 109 */       this.q = i / 16384.0D;
/*     */     } 
/*     */   }
/*     */   
/*     */   public int a() {
/* 114 */     return this.j;
/*     */   }
/*     */   
/*     */   public int b() {
/* 118 */     return this.k;
/*     */   }
/*     */   
/*     */   public short c() {
/* 122 */     return this.l;
/*     */   }
/*     */   
/*     */   public short d() {
/* 126 */     return this.m;
/*     */   }
/*     */   
/*     */   public short e() {
/* 130 */     return this.n;
/*     */   }
/*     */   
/*     */   public short f() {
/* 134 */     return this.o;
/*     */   }
/*     */   
/*     */   public double g() {
/* 138 */     return this.r;
/*     */   }
/*     */   
/*     */   public double h() {
/* 142 */     return this.s;
/*     */   }
/*     */   
/*     */   public double i() {
/* 146 */     return this.p;
/*     */   }
/*     */   
/*     */   public double j() {
/* 150 */     return this.q;
/*     */   }
/*     */   
/*     */   public int k() {
/* 154 */     return this.t;
/*     */   }
/*     */   
/*     */   public int l() {
/* 158 */     return this.u;
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
/*     */   public int a(int x, int y) {
/* 171 */     return Math.round((float)(x * this.p + y * this.s));
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
/*     */   public int b(int x, int y) {
/* 184 */     return Math.round((float)(x * this.r + y * this.q));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/c/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */