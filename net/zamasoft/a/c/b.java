/*     */ package net.zamasoft.a.c;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import net.zamasoft.a.b.o;
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
/*     */ public class b
/*     */   extends c
/*     */ {
/*     */   private List<a> h;
/*     */   
/*     */   public b(o parentTable, RandomAccessFile raf) throws IOException {
/*  41 */     super(parentTable, -1, raf);
/*     */     
/*     */     a comp;
/*     */     this.h = new ArrayList<>();
/*  45 */     int firstIndex = 0;
/*  46 */     int firstContour = 0;
/*     */     do {
/*  48 */       comp = new a(firstIndex, firstContour, raf);
/*  49 */       this.h.add(comp);
/*     */ 
/*     */       
/*  52 */       c desc = parentTable.a(comp.f());
/*  53 */       if (desc == null)
/*  54 */         continue;  firstIndex += desc.c();
/*  55 */       firstContour += desc.d();
/*     */     }
/*  57 */     while ((comp.e() & 0x20) != 0);
/*     */ 
/*     */     
/*  60 */     if ((comp.e() & 0x100) != 0) {
/*  61 */       a(raf, raf.read() << 8 | raf.read());
/*     */     }
/*     */   }
/*     */   
/*     */   public int a(int i) {
/*  66 */     a a = g(i);
/*  67 */     if (a != null) {
/*  68 */       c gd = this.g.a(a.f());
/*  69 */       return gd.a(i - a.b()) + a.a();
/*     */     } 
/*  71 */     return 0;
/*     */   }
/*     */   
/*     */   public byte b(int i) {
/*  75 */     a a = f(i);
/*  76 */     if (a != null) {
/*  77 */       c gd = this.g.a(a.f());
/*  78 */       return gd.b(i - a.a());
/*     */     } 
/*  80 */     return 0;
/*     */   }
/*     */   
/*     */   public short c(int i) {
/*  84 */     a a = f(i);
/*  85 */     if (a != null) {
/*  86 */       c gd = this.g.a(a.f());
/*  87 */       int n = i - a.a();
/*  88 */       int x = gd.c(n);
/*  89 */       int y = gd.d(n);
/*  90 */       short x1 = (short)a.a(x, y);
/*  91 */       x1 = (short)(x1 + a.k());
/*  92 */       return x1;
/*     */     } 
/*  94 */     return 0;
/*     */   }
/*     */   
/*     */   public short d(int i) {
/*  98 */     a a = f(i);
/*  99 */     if (a != null) {
/* 100 */       c gd = this.g.a(a.f());
/* 101 */       int n = i - a.a();
/* 102 */       int x = gd.c(n);
/* 103 */       int y = gd.d(n);
/* 104 */       short y1 = (short)a.b(x, y);
/* 105 */       y1 = (short)(y1 + a.l());
/* 106 */       return y1;
/*     */     } 
/* 108 */     return 0;
/*     */   }
/*     */   
/*     */   public boolean a() {
/* 112 */     return true;
/*     */   }
/*     */   
/*     */   public int c() {
/* 116 */     a a = this.h.get(this.h.size() - 1);
/* 117 */     c gd = this.g.a(a.f());
/* 118 */     return a.a() + ((gd == null) ? 0 : gd.c());
/*     */   }
/*     */   
/*     */   public int d() {
/* 122 */     a a = this.h.get(this.h.size() - 1);
/* 123 */     c gd = this.g.a(a.f());
/* 124 */     return a.b() + ((gd == null) ? 0 : gd.d());
/*     */   }
/*     */   
/*     */   public int e(int i) {
/* 128 */     return ((a)this.h.get(i)).a();
/*     */   }
/*     */   
/*     */   public int e() {
/* 132 */     return this.h.size();
/*     */   }
/*     */ 
/*     */   
/*     */   protected a f(int i) {
/* 137 */     for (int n = 0; n < this.h.size(); n++) {
/* 138 */       a a = this.h.get(n);
/* 139 */       c gd = this.g.a(a.f());
/* 140 */       if (a.a() <= i && i < a.a() + gd.c()) {
/* 141 */         return a;
/*     */       }
/*     */     } 
/* 144 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected a g(int i) {
/* 149 */     for (int j = 0; j < this.h.size(); j++) {
/* 150 */       a a = this.h.get(j);
/* 151 */       c gd = this.g.a(a.f());
/* 152 */       if (a.b() <= i && i < a.b() + gd.d()) {
/* 153 */         return a;
/*     */       }
/*     */     } 
/* 156 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/c/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */