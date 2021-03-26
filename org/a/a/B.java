/*     */ package org.a.a;
/*     */ 
/*     */ import java.io.FilterInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import javax.json.JsonException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class B
/*     */   extends FilterInputStream
/*     */ {
/*  58 */   private static final Charset a = Charset.forName("UTF-32LE");
/*  59 */   private static final Charset b = Charset.forName("UTF-32BE");
/*     */   
/*     */   private static final byte c = -1;
/*     */   
/*     */   private static final byte d = -2;
/*     */   private static final byte e = -17;
/*     */   private static final byte f = -69;
/*     */   private static final byte g = -65;
/*     */   private static final byte h = 0;
/*  68 */   private final byte[] i = new byte[4];
/*     */   private int j;
/*     */   private int k;
/*     */   private final Charset l;
/*     */   
/*     */   B(InputStream is) {
/*  74 */     super(is);
/*  75 */     this.l = c();
/*     */   }
/*     */   
/*     */   Charset a() {
/*  79 */     return this.l;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void b() {
/*     */     try {
/*  89 */       int b1 = this.in.read();
/*  90 */       if (b1 == -1) {
/*     */         return;
/*     */       }
/*     */       
/*  94 */       int b2 = this.in.read();
/*  95 */       if (b2 == -1) {
/*  96 */         this.j = 1;
/*  97 */         this.i[0] = (byte)b1;
/*     */         
/*     */         return;
/*     */       } 
/* 101 */       int b3 = this.in.read();
/* 102 */       if (b3 == -1) {
/* 103 */         this.j = 2;
/* 104 */         this.i[0] = (byte)b1;
/* 105 */         this.i[1] = (byte)b2;
/*     */         
/*     */         return;
/*     */       } 
/* 109 */       int b4 = this.in.read();
/* 110 */       if (b4 == -1) {
/* 111 */         this.j = 3;
/* 112 */         this.i[0] = (byte)b1;
/* 113 */         this.i[1] = (byte)b2;
/* 114 */         this.i[2] = (byte)b3;
/*     */         return;
/*     */       } 
/* 117 */       this.j = 4;
/* 118 */       this.i[0] = (byte)b1;
/* 119 */       this.i[1] = (byte)b2;
/* 120 */       this.i[2] = (byte)b3;
/* 121 */       this.i[3] = (byte)b4;
/* 122 */     } catch (IOException ioe) {
/* 123 */       throw new JsonException(h.f(), ioe);
/*     */     } 
/*     */   }
/*     */   
/*     */   private Charset c() {
/* 128 */     b();
/* 129 */     if (this.j < 2)
/* 130 */       throw new JsonException(h.e()); 
/* 131 */     if (this.j == 4) {
/*     */       
/* 133 */       if (this.i[0] == 0 && this.i[1] == 0 && this.i[2] == -2 && this.i[3] == -1) {
/* 134 */         this.k = 4;
/* 135 */         return b;
/* 136 */       }  if (this.i[0] == -1 && this.i[1] == -2 && this.i[2] == 0 && this.i[3] == 0) {
/* 137 */         this.k = 4;
/* 138 */         return a;
/* 139 */       }  if (this.i[0] == -2 && this.i[1] == -1) {
/* 140 */         this.k = 2;
/* 141 */         return StandardCharsets.UTF_16BE;
/* 142 */       }  if (this.i[0] == -1 && this.i[1] == -2) {
/* 143 */         this.k = 2;
/* 144 */         return StandardCharsets.UTF_16LE;
/* 145 */       }  if (this.i[0] == -17 && this.i[1] == -69 && this.i[2] == -65) {
/* 146 */         this.k = 3;
/* 147 */         return StandardCharsets.UTF_8;
/*     */       } 
/*     */       
/* 150 */       if (this.i[0] == 0 && this.i[1] == 0 && this.i[2] == 0)
/* 151 */         return b; 
/* 152 */       if (this.i[0] == 0 && this.i[2] == 0)
/* 153 */         return StandardCharsets.UTF_16BE; 
/* 154 */       if (this.i[1] == 0 && this.i[2] == 0 && this.i[3] == 0)
/* 155 */         return a; 
/* 156 */       if (this.i[1] == 0 && this.i[3] == 0) {
/* 157 */         return StandardCharsets.UTF_16LE;
/*     */       }
/*     */     } 
/* 160 */     return StandardCharsets.UTF_8;
/*     */   }
/*     */ 
/*     */   
/*     */   public int read() throws IOException {
/* 165 */     if (this.k < this.j) {
/* 166 */       return this.i[this.k++];
/*     */     }
/* 168 */     return this.in.read();
/*     */   }
/*     */ 
/*     */   
/*     */   public int read(byte[] b, int off, int len) throws IOException {
/* 173 */     if (this.k < this.j) {
/* 174 */       if (len == 0) {
/* 175 */         return 0;
/*     */       }
/* 177 */       if (off < 0 || len < 0 || len > b.length - off) {
/* 178 */         throw new IndexOutOfBoundsException();
/*     */       }
/* 180 */       int min = Math.min(this.j - this.k, len);
/* 181 */       System.arraycopy(this.i, this.k, b, off, min);
/* 182 */       this.k += min;
/* 183 */       return min;
/*     */     } 
/* 185 */     return this.in.read(b, off, len);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/B.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */