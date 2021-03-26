/*     */ package com.levigo.jbig2.decoder.arithmetic;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArithmeticDecoder
/*     */ {
/*  29 */   private static final int[][] QE = new int[][] { { 22017, 1, 1, 1 }, { 13313, 2, 6, 0 }, { 6145, 3, 9, 0 }, { 2753, 4, 12, 0 }, { 1313, 5, 29, 0 }, { 545, 38, 33, 0 }, { 22017, 7, 6, 1 }, { 21505, 8, 14, 0 }, { 18433, 9, 14, 0 }, { 14337, 10, 14, 0 }, { 12289, 11, 17, 0 }, { 9217, 12, 18, 0 }, { 7169, 13, 20, 0 }, { 5633, 29, 21, 0 }, { 22017, 15, 14, 1 }, { 21505, 16, 14, 0 }, { 20737, 17, 15, 0 }, { 18433, 18, 16, 0 }, { 14337, 19, 17, 0 }, { 13313, 20, 18, 0 }, { 12289, 21, 19, 0 }, { 10241, 22, 19, 0 }, { 9217, 23, 20, 0 }, { 8705, 24, 21, 0 }, { 7169, 25, 22, 0 }, { 6145, 26, 23, 0 }, { 5633, 27, 24, 0 }, { 5121, 28, 25, 0 }, { 4609, 29, 26, 0 }, { 4353, 30, 27, 0 }, { 2753, 31, 28, 0 }, { 2497, 32, 29, 0 }, { 2209, 33, 30, 0 }, { 1313, 34, 31, 0 }, { 1089, 35, 32, 0 }, { 673, 36, 33, 0 }, { 545, 37, 34, 0 }, { 321, 38, 35, 0 }, { 273, 39, 36, 0 }, { 133, 40, 37, 0 }, { 73, 41, 38, 0 }, { 37, 42, 39, 0 }, { 21, 43, 40, 0 }, { 9, 44, 41, 0 }, { 5, 45, 42, 0 }, { 1, 45, 43, 0 }, { 22017, 46, 46, 0 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long c;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int ct;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long streamPos0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final ImageInputStream iis;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArithmeticDecoder(ImageInputStream paramImageInputStream) throws IOException {
/* 138 */     this.iis = paramImageInputStream;
/* 139 */     init();
/*     */   }
/*     */   
/*     */   private void init() throws IOException {
/* 143 */     this.streamPos0 = this.iis.getStreamPosition();
/* 144 */     this.b = this.iis.read();
/*     */     
/* 146 */     this.c = (this.b << 16);
/*     */     
/* 148 */     byteIn();
/*     */     
/* 150 */     this.c <<= 7L;
/* 151 */     this.ct -= 7;
/* 152 */     this.a = 32768;
/*     */   }
/*     */ 
/*     */   
/*     */   public int decode(CX paramCX) throws IOException {
/* 157 */     int i, j = QE[paramCX.cx()][0];
/* 158 */     int k = paramCX.cx();
/*     */     
/* 160 */     this.a -= j;
/*     */     
/* 162 */     if (this.c >> 16L < j) {
/* 163 */       i = lpsExchange(paramCX, k, j);
/* 164 */       renormalize();
/*     */     } else {
/* 166 */       this.c -= (j << 16);
/* 167 */       if ((this.a & 0x8000) == 0) {
/* 168 */         i = mpsExchange(paramCX, k);
/* 169 */         renormalize();
/*     */       } else {
/* 171 */         return paramCX.mps();
/*     */       } 
/*     */     } 
/*     */     
/* 175 */     return i;
/*     */   }
/*     */   
/*     */   private void byteIn() throws IOException {
/* 179 */     if (this.iis.getStreamPosition() > this.streamPos0) {
/* 180 */       this.iis.seek(this.iis.getStreamPosition() - 1L);
/*     */     }
/*     */     
/* 183 */     this.b = this.iis.read();
/*     */     
/* 185 */     if (this.b == 255) {
/* 186 */       int i = this.iis.read();
/* 187 */       if (i > 143) {
/* 188 */         this.c += 65280L;
/* 189 */         this.ct = 8;
/* 190 */         this.iis.seek(this.iis.getStreamPosition() - 2L);
/*     */       } else {
/* 192 */         this.c += (i << 9);
/* 193 */         this.ct = 7;
/*     */       } 
/*     */     } else {
/* 196 */       this.b = this.iis.read();
/* 197 */       this.c += (this.b << 8);
/* 198 */       this.ct = 8;
/*     */     } 
/*     */     
/* 201 */     this.c &= 0xFFFFFFFFL;
/*     */   }
/*     */   
/*     */   private void renormalize() throws IOException {
/*     */     do {
/* 206 */       if (this.ct == 0) {
/* 207 */         byteIn();
/*     */       }
/*     */       
/* 210 */       this.a <<= 1;
/* 211 */       this.c <<= 1L;
/* 212 */       this.ct--;
/*     */     }
/* 214 */     while ((this.a & 0x8000) == 0);
/*     */     
/* 216 */     this.c &= 0xFFFFFFFFL;
/*     */   }
/*     */   
/*     */   private int mpsExchange(CX paramCX, int paramInt) {
/* 220 */     byte b = paramCX.mps();
/*     */     
/* 222 */     if (this.a < QE[paramInt][0]) {
/*     */       
/* 224 */       if (QE[paramInt][3] == 1) {
/* 225 */         paramCX.toggleMps();
/*     */       }
/*     */       
/* 228 */       paramCX.setCx(QE[paramInt][2]);
/* 229 */       return 1 - b;
/*     */     } 
/* 231 */     paramCX.setCx(QE[paramInt][1]);
/* 232 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   private int lpsExchange(CX paramCX, int paramInt1, int paramInt2) {
/* 237 */     byte b = paramCX.mps();
/*     */     
/* 239 */     if (this.a < paramInt2) {
/* 240 */       paramCX.setCx(QE[paramInt1][1]);
/* 241 */       this.a = paramInt2;
/*     */       
/* 243 */       return b;
/*     */     } 
/* 245 */     if (QE[paramInt1][3] == 1) {
/* 246 */       paramCX.toggleMps();
/*     */     }
/*     */     
/* 249 */     paramCX.setCx(QE[paramInt1][2]);
/* 250 */     this.a = paramInt2;
/* 251 */     return 1 - b;
/*     */   }
/*     */ 
/*     */   
/*     */   int getA() {
/* 256 */     return this.a;
/*     */   }
/*     */   
/*     */   long getC() {
/* 260 */     return this.c;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/decoder/arithmetic/ArithmeticDecoder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */