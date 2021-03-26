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
/*     */ public class L
/*     */ {
/*     */   private short a;
/*     */   private short b;
/*     */   private short c;
/*     */   private short d;
/*     */   private short e;
/*     */   private short f;
/*     */   private String g;
/*     */   
/*     */   protected L(RandomAccessFile raf) throws IOException {
/*  44 */     this.a = raf.readShort();
/*  45 */     this.b = raf.readShort();
/*  46 */     this.c = raf.readShort();
/*  47 */     this.d = raf.readShort();
/*  48 */     this.e = raf.readShort();
/*  49 */     this.f = raf.readShort();
/*     */   }
/*     */   
/*     */   public short a() {
/*  53 */     return this.b;
/*     */   }
/*     */   
/*     */   public short b() {
/*  57 */     return this.c;
/*     */   }
/*     */   
/*     */   public short c() {
/*  61 */     return this.d;
/*     */   }
/*     */   
/*     */   public short d() {
/*  65 */     return this.a;
/*     */   }
/*     */   
/*     */   public String e() {
/*  69 */     return this.g;
/*     */   }
/*     */   
/*     */   protected void a(RandomAccessFile raf, int stringStorageOffset) throws IOException {
/*  73 */     StringBuffer sb = new StringBuffer();
/*  74 */     synchronized (raf) {
/*  75 */       raf.seek((stringStorageOffset + this.f));
/*  76 */       if (this.a == 0) {
/*     */ 
/*     */         
/*  79 */         for (int i = 0; i < this.e / 2; i++) {
/*  80 */           sb.append(raf.readChar());
/*     */         }
/*  82 */       } else if (this.a == 1) {
/*     */ 
/*     */         
/*  85 */         for (int i = 0; i < this.e; i++) {
/*  86 */           sb.append((char)raf.readByte());
/*     */         }
/*  88 */       } else if (this.a == 2) {
/*     */ 
/*     */         
/*  91 */         for (int i = 0; i < this.e; i++) {
/*  92 */           sb.append((char)raf.readByte());
/*     */         }
/*  94 */       } else if (this.a == 3) {
/*     */ 
/*     */ 
/*     */         
/*  98 */         for (int i = 0; i < this.e / 2; i++) {
/*  99 */           char c = raf.readChar();
/* 100 */           sb.append(c);
/*     */         } 
/*     */       } 
/*     */     } 
/* 104 */     this.g = sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/L.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */