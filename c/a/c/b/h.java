/*     */ package c.a.c.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class h
/*     */ {
/*     */   private static final int f = 10;
/*     */   private static final int g = 5;
/*  87 */   int a = 1;
/*     */ 
/*     */ 
/*     */   
/*     */   float b;
/*     */ 
/*     */   
/*     */   int c;
/*     */ 
/*     */   
/*  97 */   float[] d = new float[10];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   int[] e = new int[10];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public h(float brate) {
/* 117 */     if (brate <= 0.0F) {
/* 118 */       throw new IllegalArgumentException("Overall target bitrate must be a positive number");
/*     */     }
/*     */     
/* 121 */     this.b = brate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float a() {
/* 132 */     return this.b;
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
/*     */   public int b() {
/* 144 */     return this.a;
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
/*     */   public int c() {
/* 157 */     return this.c + 1;
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
/*     */   public float a(int n) {
/* 171 */     return (n < this.c) ? this.d[n] : this.b;
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
/*     */   public int b(int n) {
/* 188 */     return (n < this.c) ? this.e[n] : 0;
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
/*     */   public void a(float brate, int elyrs) {
/* 206 */     if (brate <= 0.0F) {
/* 207 */       throw new IllegalArgumentException("Target bitrate must be positive");
/*     */     }
/*     */     
/* 210 */     if (elyrs < 0) {
/* 211 */       throw new IllegalArgumentException("The number of extra layers must be 0 or more");
/*     */     }
/*     */     
/* 214 */     if (this.c > 0 && this.d[this.c - 1] >= brate) {
/* 215 */       throw new IllegalArgumentException("New optimization point must have a target bitrate higher than the preceding one");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 221 */     if (this.d.length == this.c) {
/* 222 */       float[] tbr = this.d;
/* 223 */       int[] tel = this.e;
/*     */       
/* 225 */       this.d = new float[this.d.length + 5];
/* 226 */       this.e = new int[this.e.length + 5];
/* 227 */       System.arraycopy(tbr, 0, this.d, 0, this.c);
/* 228 */       System.arraycopy(tel, 0, this.e, 0, this.c);
/*     */     } 
/*     */     
/* 231 */     this.d[this.c] = brate;
/* 232 */     this.e[this.c] = elyrs;
/* 233 */     this.c++;
/*     */     
/* 235 */     this.a += 1 + elyrs;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/c/b/h.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */