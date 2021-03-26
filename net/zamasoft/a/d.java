/*     */ package net.zamasoft.a;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import net.zamasoft.a.a.a;
/*     */ import net.zamasoft.a.a.c;
/*     */ import net.zamasoft.a.b.F;
/*     */ import net.zamasoft.a.b.K;
/*     */ import net.zamasoft.a.b.aa;
/*     */ import net.zamasoft.a.b.ab;
/*     */ import net.zamasoft.a.b.ac;
/*     */ import net.zamasoft.a.b.ad;
/*     */ import net.zamasoft.a.b.ae;
/*     */ import net.zamasoft.a.b.i;
/*     */ import net.zamasoft.a.b.o;
/*     */ import net.zamasoft.a.b.r;
/*     */ import net.zamasoft.a.b.s;
/*     */ import net.zamasoft.a.b.t;
/*     */ import net.zamasoft.a.c.e;
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
/*     */ public class d
/*     */ {
/*  48 */   private Logger a = Logger.getLogger(d.class.getName());
/*     */   
/*  50 */   private ab b = null;
/*     */   
/*     */   private aa[] c;
/*     */   
/*     */   private r d;
/*     */   
/*     */   private s e;
/*     */   
/*     */   private t f;
/*     */   
/*     */   private ad g;
/*     */   
/*     */   private ae h;
/*     */   
/*     */   private K i;
/*     */   
/*     */   private c j;
/*     */   
/*     */   private RandomAccessFile k;
/*     */   
/*     */   public d(RandomAccessFile raf) throws IOException {
/*  71 */     this.k = raf;
/*  72 */     this.b = new ab(raf);
/*  73 */     this.c = new aa[this.b.b()];
/*     */ 
/*     */     
/*  76 */     this.d = (r)a(1751474532);
/*  77 */     this.e = (s)a(1751672161);
/*  78 */     this.f = (t)a(1752003704);
/*  79 */     this.g = (ad)a(1986553185);
/*  80 */     this.h = (ae)a(1986884728);
/*  81 */     this.i = (K)a(1835104368);
/*     */ 
/*     */     
/*  84 */     this.f.a(this.e.k(), this.i.o() - this.e.k());
/*  85 */     if (this.h != null) {
/*  86 */       this.h.a(this.g.k(), this.i.o() - this.g.k());
/*     */     }
/*     */     
/*  89 */     o glyf = (o)a(1735162214);
/*  90 */     if (glyf != null) {
/*     */       
/*  92 */       F loca = (F)a(1819239265);
/*  93 */       glyf.a(loca);
/*  94 */       loca.a(this.i.o(), (this.d.h() == 0));
/*  95 */       this.j = (c)new e(glyf, this.d, this.i);
/*     */     } else {
/*  97 */       c cff = (c)a(1128678944);
/*  98 */       if (cff != null) {
/*     */         
/* 100 */         cff.b();
/* 101 */         this.j = (c)new a(cff, this.d, this.i);
/*     */       } else {
/* 103 */         throw new IOException("Unsupported font file format.");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public aa a(int tableType) {
/* 110 */     for (int i = 0; i < this.b.b(); i++) {
/* 111 */       i entry = this.b.a(i);
/* 112 */       if (entry.d() == tableType) {
/* 113 */         if (this.c[i] == null) {
/*     */           try {
/* 115 */             this.c[i] = ac.a(this, entry, this.k);
/* 116 */           } catch (IOException e) {
/* 117 */             this.a.log(Level.WARNING, "Can't read font file.", e);
/* 118 */             return null;
/*     */           } 
/*     */         }
/* 121 */         return this.c[i];
/*     */       } 
/*     */     } 
/* 124 */     return null;
/*     */   }
/*     */   
/*     */   public int a() {
/* 128 */     return this.e.c();
/*     */   }
/*     */   
/*     */   public int b() {
/* 132 */     return this.e.f();
/*     */   }
/*     */   
/*     */   public int c() {
/* 136 */     return this.i.o();
/*     */   }
/*     */   
/*     */   public b b(int i) {
/* 140 */     return this.j.a(i);
/*     */   }
/*     */   
/*     */   public ab d() {
/* 144 */     return this.b;
/*     */   }
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 148 */     super.finalize();
/*     */     try {
/* 150 */       this.k.close();
/* 151 */     } catch (Exception exception) {}
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */