/*     */ package c.a.i;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class e
/*     */ {
/*     */   public static int a(int x) {
/*  66 */     if (x <= 0) {
/*  67 */       throw new IllegalArgumentException("" + x + " <= 0");
/*     */     }
/*     */     
/*  70 */     int v = x;
/*  71 */     int y = -1;
/*  72 */     while (v > 0) {
/*  73 */       v >>= 1;
/*  74 */       y++;
/*     */     } 
/*  76 */     return y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int a(int x1, int x2) {
/*     */     int max;
/*     */     int min;
/*  88 */     if (x1 <= 0 || x2 <= 0) {
/*  89 */       throw new IllegalArgumentException("Cannot compute the least common multiple of two numbers if one, at least,is negative.");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  95 */     if (x1 > x2) {
/*  96 */       max = x1;
/*  97 */       min = x2;
/*     */     } else {
/*  99 */       max = x2;
/* 100 */       min = x1;
/*     */     } 
/* 102 */     for (int i = 1; i <= min; i++) {
/* 103 */       if (max * i % min == 0) {
/* 104 */         return i * max;
/*     */       }
/*     */     } 
/* 107 */     throw new Error("Cannot find the least common multiple of numbers " + x1 + " and " + x2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int a(int[] x) {
/* 118 */     if (x.length < 2) {
/* 119 */       throw new Error("Do not use this method if there are less than two numbers.");
/*     */     }
/*     */     
/* 122 */     int tmp = a(x[x.length - 1], x[x.length - 2]);
/* 123 */     for (int i = x.length - 3; i >= 0; i--) {
/* 124 */       if (x[i] <= 0) {
/* 125 */         throw new IllegalArgumentException("Cannot compute the least common multiple of several numbers where one, at least,is negative.");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 131 */       tmp = a(tmp, x[i]);
/*     */     } 
/* 133 */     return tmp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int b(int x1, int x2) {
/*     */     int a, b;
/* 141 */     if (x1 < 0 || x2 < 0) {
/* 142 */       throw new IllegalArgumentException("Cannot compute the GCD if one integer is negative.");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 147 */     if (x1 > x2) {
/* 148 */       a = x1;
/* 149 */       b = x2;
/*     */     } else {
/* 151 */       a = x2;
/* 152 */       b = x1;
/*     */     } 
/*     */     
/* 155 */     if (b == 0) return 0;
/*     */     
/* 157 */     int g = b;
/* 158 */     while (g != 0) {
/* 159 */       int z = a % g;
/* 160 */       a = g;
/* 161 */       g = z;
/*     */     } 
/* 163 */     return a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int b(int[] x) {
/* 173 */     if (x.length < 2) {
/* 174 */       throw new Error("Do not use this method if there are less than two numbers.");
/*     */     }
/*     */     
/* 177 */     int tmp = b(x[x.length - 1], x[x.length - 2]);
/* 178 */     for (int i = x.length - 3; i >= 0; i--) {
/* 179 */       if (x[i] < 0) {
/* 180 */         throw new IllegalArgumentException("Cannot compute the least common multiple of several numbers where one, at least,is negative.");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 186 */       tmp = b(tmp, x[i]);
/*     */     } 
/* 188 */     return tmp;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/i/e.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */