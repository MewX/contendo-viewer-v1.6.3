/*     */ package jp.cssj.sakae.pdf.g.a;
/*     */ 
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import jp.cssj.sakae.pdf.h;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   extends FilterOutputStream
/*     */ {
/*     */   private static final int a = 122;
/*  71 */   private static final byte[] b = new byte[] { 122 };
/*     */   
/*     */   private static final int c = 33;
/*     */   
/*  75 */   private static final byte[] d = new byte[] { 126, 62 };
/*     */   
/*     */   private static final long e = 85L;
/*     */   
/*     */   private static final long f = 7225L;
/*     */   
/*     */   private static final long g = 614125L;
/*     */   
/*     */   private static final long h = 52200625L;
/*     */   
/*  85 */   private int i = 0;
/*     */   
/*  87 */   private long j = 0L;
/*     */   
/*  89 */   private int k = 0;
/*     */   
/*     */   public a(OutputStream out) {
/*  92 */     super(out);
/*     */   }
/*     */   
/*     */   public void write(int b) throws IOException {
/*  96 */     if (this.i == 0) {
/*  97 */       this.j += (b << 24) & 0xFF000000L;
/*  98 */     } else if (this.i == 1) {
/*  99 */       this.j += (b << 16) & 0xFF0000L;
/* 100 */     } else if (this.i == 2) {
/* 101 */       this.j += (b << 8) & 0xFF00L;
/*     */     } else {
/* 103 */       this.j += b & 0xFFL;
/*     */     } 
/* 105 */     this.i++;
/*     */     
/* 107 */     if (this.i > 3) {
/* 108 */       a(a(this.j));
/* 109 */       this.j = 0L;
/* 110 */       this.i = 0;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(byte[] buf) throws IOException {
/* 115 */     a(buf, buf.length, false);
/*     */   }
/*     */   
/*     */   private void a(byte[] buf, boolean nosplit) throws IOException {
/* 119 */     a(buf, buf.length, nosplit);
/*     */   }
/*     */   
/*     */   private void a(byte[] buf, int len) throws IOException {
/* 123 */     a(buf, len, false);
/*     */   }
/*     */   
/*     */   private void a(byte[] buf, int len, boolean nosplit) throws IOException {
/* 127 */     if (this.k + len > 80) {
/* 128 */       int firstpart = nosplit ? 0 : (len - this.k + len - 80);
/* 129 */       if (firstpart > 0)
/* 130 */         this.out.write(buf, 0, firstpart); 
/* 131 */       this.out.write(h.d);
/* 132 */       int rest = len - firstpart;
/* 133 */       if (rest > 0)
/* 134 */         this.out.write(buf, firstpart, rest); 
/* 135 */       this.k = rest;
/*     */     } else {
/* 137 */       this.out.write(buf, 0, len);
/* 138 */       this.k += len;
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
/*     */   private byte[] a(long word) {
/* 152 */     word &= 0xFFFFFFFFFFFFFFFFL;
/*     */     
/* 154 */     if (word == 0L) {
/* 155 */       return b;
/*     */     }
/* 157 */     if (word < 0L) {
/* 158 */       word = -word;
/*     */     }
/* 160 */     byte c1 = (byte)(int)(word / 52200625L & 0xFFL);
/* 161 */     byte c2 = (byte)(int)((word - c1 * 52200625L) / 614125L & 0xFFL);
/* 162 */     byte c3 = (byte)(int)((word - c1 * 52200625L - c2 * 614125L) / 7225L & 0xFFL);
/* 163 */     byte c4 = (byte)(int)((word - c1 * 52200625L - c2 * 614125L - c3 * 7225L) / 85L & 0xFFL);
/* 164 */     byte c5 = (byte)(int)(word - c1 * 52200625L - c2 * 614125L - c3 * 7225L - c4 * 85L & 0xFFL);
/*     */     
/* 166 */     byte[] ret = { (byte)(c1 + 33), (byte)(c2 + 33), (byte)(c3 + 33), (byte)(c4 + 33), (byte)(c5 + 33) };
/*     */     
/* 168 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a() throws IOException {
/* 176 */     if (this.i > 0) {
/* 177 */       byte[] conv; int rest = this.i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 188 */       if (this.j != 0L) {
/* 189 */         conv = a(this.j);
/*     */       } else {
/* 191 */         conv = new byte[5];
/* 192 */         for (int j = 0; j < 5; j++) {
/* 193 */           conv[j] = 33;
/*     */         }
/*     */       } 
/*     */       
/* 197 */       a(conv, rest + 1);
/*     */     } 
/*     */     
/* 200 */     a(d, true);
/*     */     
/* 202 */     flush();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 206 */     a();
/* 207 */     super.close();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/g/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */