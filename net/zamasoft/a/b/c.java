/*     */ package net.zamasoft.a.b;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import net.zamasoft.a.d;
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
/*     */ public class c
/*     */   implements aa
/*     */ {
/*     */   private final int a;
/*     */   private final b[] b;
/*     */   private final a[] c;
/*     */   private final d d;
/*     */   private final RandomAccessFile e;
/*     */   private final long f;
/*     */   
/*     */   protected c(d otf, i de, RandomAccessFile raf) throws IOException {
/*  44 */     synchronized (raf) {
/*  45 */       raf.seek(de.c());
/*  46 */       this.f = raf.getFilePointer();
/*  47 */       raf.readUnsignedShort();
/*  48 */       this.a = raf.readUnsignedShort();
/*  49 */       this.b = new b[this.a];
/*  50 */       this.c = new a[this.a];
/*     */ 
/*     */       
/*  53 */       for (int j = 0; j < this.a; j++) {
/*  54 */         this.b[j] = new b(raf);
/*     */       }
/*     */       
/*  57 */       this.e = raf;
/*  58 */       this.d = otf;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public a a(short platformId, short encodingId) {
/*  64 */     for (int i = 0; i < this.a; i++) {
/*  65 */       if (this.b[i].c() == platformId && (encodingId == -1 || this.b[i]
/*  66 */         .a() == encodingId)) {
/*  67 */         return a(i);
/*     */       }
/*     */     } 
/*  70 */     return null;
/*     */   }
/*     */   
/*     */   public synchronized a a(int ix) {
/*  74 */     if (this.c[ix] == null) {
/*  75 */       synchronized (this.e) {
/*     */         try {
/*  77 */           this.e.seek(this.f + this.b[ix].b());
/*  78 */           int format = this.e.readUnsignedShort();
/*  79 */           this.c[ix] = new a(format, this.d.c(), this.e);
/*  80 */         } catch (IOException e) {
/*  81 */           throw new RuntimeException(e);
/*     */         } 
/*     */       } 
/*     */     }
/*  85 */     return this.c[ix];
/*     */   }
/*     */   
/*     */   public int b() {
/*  89 */     return this.a;
/*     */   }
/*     */   
/*     */   public int a() {
/*  93 */     return 1668112752;
/*     */   }
/*     */   
/*     */   public String toString() {
/*  97 */     StringBuffer sb = (new StringBuffer()).append("cmap\n");
/*     */     
/*     */     int i;
/* 100 */     for (i = 0; i < this.a; i++) {
/* 101 */       sb.append("\t").append(this.b[i]).append("\n");
/*     */     }
/*     */ 
/*     */     
/* 105 */     for (i = 0; i < this.a; i++) {
/* 106 */       sb.append("\t").append(this.c[i]).append("\n");
/*     */     }
/* 108 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/b/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */