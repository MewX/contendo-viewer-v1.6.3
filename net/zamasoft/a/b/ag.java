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
/*     */ public abstract class ag
/*     */   implements aa
/*     */ {
/*     */   private short a;
/*     */   private short b;
/*     */   private short c;
/*     */   private short d;
/*     */   private short e;
/*     */   private short f;
/*     */   private short g;
/*     */   private short h;
/*     */   private short i;
/*     */   private short j;
/*     */   private int k;
/*     */   
/*     */   protected ag(i de, RandomAccessFile raf) throws IOException {
/*  52 */     synchronized (raf) {
/*  53 */       raf.seek(de.c());
/*  54 */       raf.readInt();
/*  55 */       this.a = raf.readShort();
/*  56 */       this.b = raf.readShort();
/*  57 */       this.c = raf.readShort();
/*  58 */       this.d = raf.readShort();
/*  59 */       this.e = raf.readShort();
/*  60 */       this.f = raf.readShort();
/*  61 */       this.g = raf.readShort();
/*  62 */       this.h = raf.readShort();
/*  63 */       this.i = raf.readShort();
/*  64 */       for (int j = 0; j < 5; j++) {
/*  65 */         raf.readShort();
/*     */       }
/*  67 */       this.j = raf.readShort();
/*  68 */       this.k = raf.readUnsignedShort();
/*     */     } 
/*     */   }
/*     */   
/*     */   public short b() {
/*  73 */     return this.d;
/*     */   }
/*     */   
/*     */   public short c() {
/*  77 */     return this.a;
/*     */   }
/*     */   
/*     */   public short d() {
/*  81 */     return this.h;
/*     */   }
/*     */   
/*     */   public short e() {
/*  85 */     return this.i;
/*     */   }
/*     */   
/*     */   public short f() {
/*  89 */     return this.b;
/*     */   }
/*     */   
/*     */   public short g() {
/*  93 */     return this.c;
/*     */   }
/*     */   
/*     */   public short h() {
/*  97 */     return this.j;
/*     */   }
/*     */   
/*     */   public short i() {
/* 101 */     return this.e;
/*     */   }
/*     */   
/*     */   public short j() {
/* 105 */     return this.f;
/*     */   }
/*     */   
/*     */   public int k() {
/* 109 */     return this.k;
/*     */   }
/*     */   
/*     */   public short l() {
/* 113 */     return this.g;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/ag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */