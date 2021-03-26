/*     */ package c.a.a.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ {
/*     */   byte[] a;
/*     */   int b;
/*  68 */   int c = 8;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int d = 16;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int e = 32;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public a() {
/*  84 */     this.a = new byte[32];
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
/*     */   public void a() {
/*  96 */     this.b = 0;
/*  97 */     this.c = 8;
/*  98 */     c.a.i.a.a(this.a, (byte)0);
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
/*     */   public final void a(int bit) {
/* 112 */     this.a[this.b] = (byte)(this.a[this.b] | bit << --this.c);
/* 113 */     if (this.c > 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 118 */     if (this.a[this.b] != -1) {
/* 119 */       this.c = 8;
/*     */     } else {
/*     */       
/* 122 */       this.c = 7;
/*     */     } 
/* 124 */     this.b++;
/* 125 */     if (this.b == this.a.length) {
/*     */       
/* 127 */       byte[] oldbuf = this.a;
/* 128 */       this.a = new byte[oldbuf.length + 16];
/* 129 */       System.arraycopy(oldbuf, 0, this.a, 0, oldbuf.length);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void a(int bits, int n) {
/* 151 */     if ((this.a.length - this.b << 3) - 8 + this.c <= n + 2) {
/*     */       
/* 153 */       byte[] oldbuf = this.a;
/* 154 */       this.a = new byte[oldbuf.length + 16];
/* 155 */       System.arraycopy(oldbuf, 0, this.a, 0, oldbuf.length);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 160 */     if (n >= this.c) {
/*     */       
/* 162 */       n -= this.c;
/* 163 */       this.a[this.b] = (byte)(this.a[this.b] | bits >> n);
/* 164 */       if (this.a[this.b] != -1) {
/* 165 */         this.c = 8;
/*     */       } else {
/*     */         
/* 168 */         this.c = 7;
/*     */       } 
/* 170 */       this.b++;
/*     */       
/* 172 */       while (n >= this.c) {
/* 173 */         n -= this.c;
/* 174 */         this.a[this.b] = (byte)(this.a[this.b] | bits >> n & (1 << this.c ^ 0xFFFFFFFF));
/* 175 */         if (this.a[this.b] != -1) {
/*     */           
/* 177 */           this.c = 8;
/*     */         } else {
/*     */           
/* 180 */           this.c = 7;
/*     */         } 
/* 182 */         this.b++;
/*     */       } 
/*     */     } 
/*     */     
/* 186 */     if (n > 0) {
/* 187 */       this.c -= n;
/* 188 */       this.a[this.b] = (byte)(this.a[this.b] | (bits & (1 << n) - 1) << this.c);
/*     */     } 
/* 190 */     if (this.c == 0) {
/* 191 */       if (this.a[this.b] != -1) {
/* 192 */         this.c = 8;
/*     */       } else {
/*     */         
/* 195 */         this.c = 7;
/*     */       } 
/* 197 */       this.b++;
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
/*     */   public final int b() {
/* 209 */     if (this.c == 8) {
/* 210 */       return this.b;
/*     */     }
/*     */     
/* 213 */     return this.b + 1;
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
/*     */   public final byte[] c() {
/* 227 */     return this.a;
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
/*     */   public byte[] a(byte[] data) {
/* 243 */     if (data == null) {
/* 244 */       data = new byte[(this.c == 8) ? this.b : (this.b + 1)];
/*     */     }
/* 246 */     System.arraycopy(this.a, 0, data, 0, (this.c == 8) ? this.b : (this.b + 1));
/* 247 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 256 */     return "bits written = " + (this.b * 8 + 8 - this.c) + ", curbyte = " + this.b + ", avbits = " + this.c;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/a/b/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */