/*     */ package c.a.j;
/*     */ 
/*     */ import c.a.f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ {
/*     */   public static final int a = 0;
/*     */   public static final int b = 2;
/*     */   public static final int c = 1;
/*     */   public static final byte d = 0;
/*     */   public static final byte e = 1;
/*     */   public static final byte f = 2;
/*     */   public static final byte g = 3;
/*     */   private byte[] h;
/*     */   private int i;
/*     */   private int j;
/*     */   private int[] k;
/*     */   private int[] l;
/*     */   
/*     */   public c(int nc, int dec, int lev) {
/* 137 */     this.i = dec;
/* 138 */     this.j = lev;
/* 139 */     this.h = new byte[nc];
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
/*     */   public void a(int n, int dec, int lev) {
/* 158 */     if (dec < 0 && lev < 0) {
/* 159 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 162 */     this.h[n] = 1;
/* 163 */     if (this.k == null) {
/* 164 */       this.k = new int[this.h.length];
/* 165 */       this.l = new int[this.h.length];
/*     */     } 
/* 167 */     this.k[n] = (dec >= 0) ? dec : this.i;
/* 168 */     this.l[n] = (lev >= 0) ? lev : this.j;
/*     */ 
/*     */     
/* 171 */     throw new f("Currently, in JJ2000, all components and tiles must have the same decomposition type and number of levels");
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
/*     */   public byte a(int n) {
/* 194 */     return this.h[n];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/* 205 */     return this.i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/* 216 */     return this.j;
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
/*     */   public int b(int n) {
/* 234 */     switch (this.h[n]) {
/*     */       case 0:
/* 236 */         return this.i;
/*     */       case 1:
/* 238 */         return this.k[n];
/*     */       case 2:
/* 240 */         throw new f();
/*     */       case 3:
/* 242 */         throw new f();
/*     */     } 
/* 244 */     throw new Error("Internal JJ2000 error");
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
/*     */   public int c(int n) {
/* 263 */     switch (this.h[n]) {
/*     */       case 0:
/* 265 */         return this.j;
/*     */       case 1:
/* 267 */         return this.l[n];
/*     */       case 2:
/* 269 */         throw new f();
/*     */       case 3:
/* 271 */         throw new f();
/*     */     } 
/* 273 */     throw new Error("Internal JJ2000 error");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/j/c.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */