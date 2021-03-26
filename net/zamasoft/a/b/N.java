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
/*     */ public class N
/*     */   implements aa
/*     */ {
/*     */   private int a;
/*     */   private short b;
/*     */   private int c;
/*     */   private int d;
/*     */   private short e;
/*     */   private short f;
/*     */   private short g;
/*     */   private short h;
/*     */   private short i;
/*     */   private short j;
/*     */   private short k;
/*     */   private short l;
/*     */   private short m;
/*     */   private short n;
/*     */   private short o;
/*     */   private short p;
/*     */   private O q;
/*     */   private int r;
/*     */   private int s;
/*     */   private int t;
/*     */   private int u;
/*     */   private int v;
/*     */   private short w;
/*     */   private int x;
/*     */   private int y;
/*     */   private short z;
/*     */   private short A;
/*     */   private short B;
/*     */   private int C;
/*     */   private int D;
/*     */   private int E;
/*     */   private int F;
/*     */   
/*     */   protected N(i de, RandomAccessFile raf) throws IOException {
/*  94 */     synchronized (raf) {
/*  95 */       raf.seek(de.c());
/*  96 */       this.a = raf.readUnsignedShort();
/*  97 */       this.b = raf.readShort();
/*  98 */       this.c = raf.readUnsignedShort();
/*  99 */       this.d = raf.readUnsignedShort();
/* 100 */       this.e = raf.readShort();
/* 101 */       this.f = raf.readShort();
/* 102 */       this.g = raf.readShort();
/* 103 */       this.h = raf.readShort();
/* 104 */       this.i = raf.readShort();
/* 105 */       this.j = raf.readShort();
/* 106 */       this.k = raf.readShort();
/* 107 */       this.l = raf.readShort();
/* 108 */       this.m = raf.readShort();
/* 109 */       this.n = raf.readShort();
/* 110 */       this.o = raf.readShort();
/* 111 */       this.p = raf.readShort();
/* 112 */       byte[] buf = new byte[10];
/* 113 */       raf.read(buf);
/* 114 */       this.q = new O(buf);
/* 115 */       this.r = raf.readInt();
/* 116 */       this.s = raf.readInt();
/* 117 */       this.t = raf.readInt();
/* 118 */       this.u = raf.readInt();
/* 119 */       this.v = raf.readInt();
/* 120 */       this.w = raf.readShort();
/* 121 */       this.x = raf.readUnsignedShort();
/* 122 */       this.y = raf.readUnsignedShort();
/* 123 */       this.z = raf.readShort();
/* 124 */       this.A = raf.readShort();
/* 125 */       this.B = raf.readShort();
/* 126 */       this.C = raf.readUnsignedShort();
/* 127 */       this.D = raf.readUnsignedShort();
/* 128 */       this.E = raf.readInt();
/* 129 */       this.F = raf.readInt();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int b() {
/* 134 */     return this.a;
/*     */   }
/*     */   
/*     */   public short c() {
/* 138 */     return this.b;
/*     */   }
/*     */   
/*     */   public int d() {
/* 142 */     return this.c;
/*     */   }
/*     */   
/*     */   public int e() {
/* 146 */     return this.d;
/*     */   }
/*     */   
/*     */   public short f() {
/* 150 */     return this.e;
/*     */   }
/*     */   
/*     */   public short g() {
/* 154 */     return this.f;
/*     */   }
/*     */   
/*     */   public short h() {
/* 158 */     return this.g;
/*     */   }
/*     */   
/*     */   public short i() {
/* 162 */     return this.h;
/*     */   }
/*     */   
/*     */   public short j() {
/* 166 */     return this.i;
/*     */   }
/*     */   
/*     */   public short k() {
/* 170 */     return this.j;
/*     */   }
/*     */   
/*     */   public short l() {
/* 174 */     return this.k;
/*     */   }
/*     */   
/*     */   public short m() {
/* 178 */     return this.l;
/*     */   }
/*     */   
/*     */   public short n() {
/* 182 */     return this.m;
/*     */   }
/*     */   
/*     */   public short o() {
/* 186 */     return this.n;
/*     */   }
/*     */   
/*     */   public short p() {
/* 190 */     return this.o;
/*     */   }
/*     */   
/*     */   public short q() {
/* 194 */     return this.p;
/*     */   }
/*     */   
/*     */   public O r() {
/* 198 */     return this.q;
/*     */   }
/*     */   
/*     */   public int s() {
/* 202 */     return this.r;
/*     */   }
/*     */   
/*     */   public int t() {
/* 206 */     return this.s;
/*     */   }
/*     */   
/*     */   public int u() {
/* 210 */     return this.t;
/*     */   }
/*     */   
/*     */   public int v() {
/* 214 */     return this.u;
/*     */   }
/*     */   
/*     */   public int w() {
/* 218 */     return this.v;
/*     */   }
/*     */   
/*     */   public short x() {
/* 222 */     return this.w;
/*     */   }
/*     */   
/*     */   public int y() {
/* 226 */     return this.x;
/*     */   }
/*     */   
/*     */   public int z() {
/* 230 */     return this.y;
/*     */   }
/*     */   
/*     */   public short A() {
/* 234 */     return this.z;
/*     */   }
/*     */   
/*     */   public short B() {
/* 238 */     return this.A;
/*     */   }
/*     */   
/*     */   public short C() {
/* 242 */     return this.B;
/*     */   }
/*     */   
/*     */   public int D() {
/* 246 */     return this.C;
/*     */   }
/*     */   
/*     */   public int E() {
/* 250 */     return this.D;
/*     */   }
/*     */   
/*     */   public int F() {
/* 254 */     return this.E;
/*     */   }
/*     */   
/*     */   public int G() {
/* 258 */     return this.F;
/*     */   }
/*     */   
/*     */   public int a() {
/* 262 */     return 1330851634;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/N.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */