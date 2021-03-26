/*     */ package c.a.a.a;
/*     */ 
/*     */ import c.a.i.a;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   protected int a;
/*     */   protected int b;
/*     */   protected int c;
/*     */   protected int[][] d;
/*     */   protected int[][] e;
/*     */   
/*     */   public h(int i, int w) {
/* 114 */     if (w < 0 || i < 0) {
/* 115 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 118 */     this.a = w;
/* 119 */     this.b = i;
/*     */     
/* 121 */     if (w == 0 || i == 0) {
/* 122 */       this.c = 0;
/*     */     } else {
/*     */       
/* 125 */       this.c = 1;
/* 126 */       while (i != 1 || w != 1) {
/* 127 */         w = w + 1 >> 1;
/* 128 */         i = i + 1 >> 1;
/* 129 */         this.c++;
/*     */       } 
/*     */     } 
/*     */     
/* 133 */     this.d = new int[this.c][];
/* 134 */     this.e = new int[this.c][];
/* 135 */     w = this.a;
/* 136 */     i = this.b;
/* 137 */     for (int j = 0; j < this.c; j++) {
/* 138 */       this.d[j] = new int[i * w];
/*     */       
/* 140 */       a.a(this.d[j], 2147483647);
/*     */ 
/*     */       
/* 143 */       this.e[j] = new int[i * w];
/* 144 */       w = w + 1 >> 1;
/* 145 */       i = i + 1 >> 1;
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
/*     */   public final int a() {
/* 157 */     return this.a;
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
/* 168 */     return this.b;
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
/*     */   public int a(int m, int n, int t, f in) throws IOException {
/*     */     int tv;
/* 202 */     if (m >= this.b || n >= this.a || t < 0) {
/* 203 */       throw new IllegalArgumentException();
/*     */     }
/*     */ 
/*     */     
/* 207 */     int k = this.c - 1;
/* 208 */     int tmin = this.e[k][0];
/*     */ 
/*     */     
/* 211 */     int idx = (m >> k) * (this.a + (1 << k) - 1 >> k) + (n >> k);
/*     */     
/*     */     while (true) {
/* 214 */       int ts = this.e[k][idx];
/* 215 */       tv = this.d[k][idx];
/* 216 */       if (ts < tmin) {
/* 217 */         ts = tmin;
/*     */       }
/* 219 */       while (t > ts) {
/* 220 */         if (tv >= ts) {
/* 221 */           if (in.a() == 0) {
/*     */             
/* 223 */             ts++;
/*     */             
/*     */             continue;
/*     */           } 
/* 227 */           tv = ts++;
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 232 */         ts = t;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 237 */       this.e[k][idx] = ts;
/* 238 */       this.d[k][idx] = tv;
/*     */       
/* 240 */       if (k > 0) {
/* 241 */         tmin = (ts < tv) ? ts : tv;
/* 242 */         k--;
/*     */         
/* 244 */         idx = (m >> k) * (this.a + (1 << k) - 1 >> k) + (n >> k); continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 248 */     return tv;
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
/*     */   public int a(int m, int n) {
/* 269 */     if (m >= this.b || n >= this.a) {
/* 270 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 273 */     return this.d[0][m * this.a + n];
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/a/a/h.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */