/*     */ package c.a.e.b;
/*     */ 
/*     */ import c.a.e.c;
/*     */ import c.a.e.e;
/*     */ import java.io.EOFException;
/*     */ import java.io.File;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   extends a
/*     */ {
/*  72 */   public static int d = 128;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private RandomAccessFile e;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int f;
/*     */ 
/*     */ 
/*     */   
/*     */   private int g;
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[] h;
/*     */ 
/*     */ 
/*     */   
/*     */   private e i;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b(File file) throws IOException {
/* 100 */     this(new RandomAccessFile(file, "r"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b(String fname) throws IOException {
/* 111 */     this(new RandomAccessFile(fname, "r"));
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
/*     */   public b(RandomAccessFile in) throws EOFException, IOException {
/* 124 */     this.e = in;
/*     */     
/* 126 */     c();
/* 127 */     d();
/* 128 */     this.a = e();
/* 129 */     d();
/* 130 */     this.b = e();
/* 131 */     d();
/*     */     
/* 133 */     e();
/* 134 */     this.c = 1;
/* 135 */     this.g = 8;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a() throws IOException {
/* 146 */     this.e.close();
/* 147 */     this.e = null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNomRangeBits(int c) {
/* 168 */     if (c != 0) {
/* 169 */       throw new IllegalArgumentException();
/*     */     }
/* 171 */     return this.g;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFixedPoint(int c) {
/* 187 */     if (c != 0)
/* 188 */       throw new IllegalArgumentException(); 
/* 189 */     return 0;
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
/*     */   public final c getInternCompData(c blk, int i) {
/*     */     e e1;
/* 240 */     if (i != 0) {
/* 241 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 244 */     if (blk.a() != 3) {
/* 245 */       if (this.i == null) {
/* 246 */         this.i = new e(blk.e, blk.f, blk.g, blk.h);
/*     */       } else {
/* 248 */         this.i.e = blk.e;
/* 249 */         this.i.f = blk.f;
/* 250 */         this.i.g = blk.g;
/* 251 */         this.i.h = blk.h;
/*     */       } 
/* 253 */       e1 = this.i;
/*     */     } 
/*     */ 
/*     */     
/* 257 */     int[] barr = (int[])e1.b();
/* 258 */     if (barr == null || barr.length < ((c)e1).g * ((c)e1).h) {
/* 259 */       barr = new int[((c)e1).g * ((c)e1).h];
/* 260 */       e1.a(barr);
/*     */     } 
/*     */ 
/*     */     
/* 264 */     if (this.h == null || this.h.length < ((c)e1).g) {
/* 265 */       this.h = new byte[((c)e1).g];
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/* 270 */       int mi = ((c)e1).f + ((c)e1).h;
/* 271 */       for (int j = ((c)e1).f; j < mi; j++)
/*     */       {
/* 273 */         this.e.seek((this.f + j * this.a + ((c)e1).e));
/* 274 */         this.e.read(this.h, 0, ((c)e1).g);
/* 275 */         int k = (j - ((c)e1).f) * ((c)e1).g + ((c)e1).g - 1, m = ((c)e1).g - 1;
/* 276 */         for (; m >= 0; m--, k--) {
/* 277 */           barr[k] = (this.h[m] & 0xFF) - d;
/*     */         }
/*     */       }
/*     */     
/* 281 */     } catch (IOException iOException) {
/* 282 */       c.a.b.a(iOException);
/*     */     } 
/*     */ 
/*     */     
/* 286 */     ((c)e1).k = false;
/*     */     
/* 288 */     ((c)e1).i = 0;
/* 289 */     ((c)e1).j = ((c)e1).g;
/* 290 */     return (c)e1;
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
/*     */   public c getCompData(c blk, int i) {
/* 338 */     return getInternCompData(blk, i);
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
/*     */ 
/*     */   
/*     */   private byte b() throws IOException, EOFException {
/* 353 */     this.f++;
/* 354 */     return this.e.readByte();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void c() throws IOException, EOFException {
/* 364 */     byte[] type = { 80, 53 };
/*     */ 
/*     */ 
/*     */     
/* 368 */     for (int i = 0; i < 2; i++) {
/* 369 */       byte b1 = b();
/* 370 */       if (b1 != type[i]) {
/* 371 */         if (i == 1 && b1 == 50) {
/* 372 */           throw new IllegalArgumentException("JJ2000 does not support ascii-PGM files. Use  raw-PGM file instead. ");
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 377 */         throw new IllegalArgumentException("Not a raw-PGM file");
/*     */       } 
/*     */     } 
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
/*     */   private void d() throws IOException, EOFException {
/* 392 */     boolean done = false;
/*     */ 
/*     */     
/* 395 */     while (!done) {
/* 396 */       byte b1 = b();
/* 397 */       if (b1 == 35) {
/* 398 */         while (b1 != 10 && b1 != 13)
/* 399 */           b1 = b();  continue;
/*     */       } 
/* 401 */       if (b1 != 9 && b1 != 10 && b1 != 13 && b1 != 32) {
/* 402 */         done = true;
/*     */       }
/*     */     } 
/*     */     
/* 406 */     this.f--;
/* 407 */     this.e.seek(this.f);
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
/*     */   private int e() throws IOException, EOFException {
/* 420 */     int res = 0;
/* 421 */     byte b1 = 0;
/*     */     
/* 423 */     b1 = b();
/* 424 */     while (b1 != 32 && b1 != 10 && b1 != 9 && b1 != 13) {
/* 425 */       res = res * 10 + b1 - 48;
/* 426 */       b1 = b();
/*     */     } 
/* 428 */     return res;
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
/*     */   
/*     */   public boolean a(int c) {
/* 442 */     if (c != 0)
/* 443 */       throw new IllegalArgumentException(); 
/* 444 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 455 */     return "ImgReaderPGM: WxH = " + this.a + "x" + this.b + ", Component = 0" + "\nUnderlying RandomAccessIO:\n" + this.e
/* 456 */       .toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/e/b/b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */