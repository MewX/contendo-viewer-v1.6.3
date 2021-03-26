/*     */ package c.a.j.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   extends d
/*     */ {
/*  64 */   private static final float[] e = new float[] { 0.5F, 1.0F, 0.5F };
/*     */ 
/*     */ 
/*     */   
/*  68 */   private static final float[] f = new float[] { -0.125F, -0.25F, 0.75F, -0.25F, -0.125F };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int[] inSig, int inOff, int inLen, int inStep, int[] lowSig, int lowOff, int lowStep, int[] highSig, int highOff, int highStep) {
/* 116 */     int iStep = 2 * inStep;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 126 */     int ik = inOff + inStep;
/* 127 */     int hk = highOff;
/*     */     
/*     */     int i;
/* 130 */     for (i = 1; i < inLen - 1; i += 2) {
/* 131 */       highSig[hk] = inSig[ik] - (inSig[ik - inStep] + inSig[ik + inStep] >> 1);
/*     */ 
/*     */       
/* 134 */       ik += iStep;
/* 135 */       hk += highStep;
/*     */     } 
/*     */ 
/*     */     
/* 139 */     if (inLen % 2 == 0) {
/* 140 */       highSig[hk] = inSig[ik] - (2 * inSig[ik - inStep] >> 1);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     ik = inOff;
/* 149 */     int lk = lowOff;
/* 150 */     hk = highOff;
/*     */     
/* 152 */     if (inLen > 1) {
/* 153 */       lowSig[lk] = inSig[ik] + (highSig[hk] + 1 >> 1);
/*     */     } else {
/*     */       
/* 156 */       lowSig[lk] = inSig[ik];
/*     */     } 
/*     */     
/* 159 */     ik += iStep;
/* 160 */     lk += lowStep;
/* 161 */     hk += highStep;
/*     */ 
/*     */     
/* 164 */     for (i = 2; i < inLen - 1; i += 2) {
/* 165 */       lowSig[lk] = inSig[ik] + (highSig[hk - highStep] + highSig[hk] + 2 >> 2);
/*     */ 
/*     */       
/* 168 */       ik += iStep;
/* 169 */       lk += lowStep;
/* 170 */       hk += highStep;
/*     */     } 
/*     */ 
/*     */     
/* 174 */     if (inLen % 2 == 1 && 
/* 175 */       inLen > 2) {
/* 176 */       lowSig[lk] = inSig[ik] + (2 * highSig[hk - highStep] + 2 >> 2);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(int[] inSig, int inOff, int inLen, int inStep, int[] lowSig, int lowOff, int lowStep, int[] highSig, int highOff, int highStep) {
/* 228 */     int iStep = 2 * inStep;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 238 */     int ik = inOff;
/* 239 */     int hk = highOff;
/*     */     
/* 241 */     if (inLen > 1) {
/*     */       
/* 243 */       highSig[hk] = inSig[ik] - inSig[ik + inStep];
/*     */     }
/*     */     else {
/*     */       
/* 247 */       highSig[hk] = inSig[ik] << 1;
/*     */     } 
/*     */     
/* 250 */     ik += iStep;
/* 251 */     hk += highStep;
/*     */ 
/*     */     
/* 254 */     if (inLen > 3) {
/* 255 */       for (int j = 2; j < inLen - 1; j += 2) {
/* 256 */         highSig[hk] = inSig[ik] - (inSig[ik - inStep] + inSig[ik + inStep] >> 1);
/*     */         
/* 258 */         ik += iStep;
/* 259 */         hk += highStep;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 265 */     if (inLen % 2 == 1 && inLen > 1) {
/* 266 */       highSig[hk] = inSig[ik] - inSig[ik - inStep];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 274 */     ik = inOff + inStep;
/* 275 */     int lk = lowOff;
/* 276 */     hk = highOff;
/*     */     
/* 278 */     for (int i = 1; i < inLen - 1; i += 2) {
/*     */       
/* 280 */       lowSig[lk] = inSig[ik] + (highSig[hk] + highSig[hk + highStep] + 2 >> 2);
/*     */ 
/*     */       
/* 283 */       ik += iStep;
/* 284 */       lk += lowStep;
/* 285 */       hk += highStep;
/*     */     } 
/*     */     
/* 288 */     if (inLen > 1 && inLen % 2 == 0)
/*     */     {
/* 290 */       lowSig[lk] = inSig[ik] + (2 * highSig[hk] + 2 >> 2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/* 301 */     return 2;
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
/* 312 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int c() {
/* 323 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int d() {
/* 334 */     return 1;
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
/*     */   public int e() {
/* 347 */     return 1;
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
/*     */   public int f() {
/* 360 */     return 1;
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
/*     */   public int g() {
/* 373 */     return 2;
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
/*     */   public int h() {
/* 386 */     return 2;
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
/*     */   public float[] l() {
/* 402 */     return e;
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
/*     */   public float[] m() {
/* 418 */     return f;
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
/*     */   public int i() {
/* 430 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean k() {
/* 441 */     return true;
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
/*     */   public boolean a(int tailOvrlp, int headOvrlp, int inLen) {
/* 473 */     if (inLen % 2 == 0) {
/* 474 */       if (tailOvrlp >= 2 && headOvrlp >= 1) return true; 
/* 475 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 479 */     if (tailOvrlp >= 2 && headOvrlp >= 2) return true; 
/* 480 */     return false;
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
/*     */   public boolean equals(Object obj) {
/* 496 */     return (obj == this || obj instanceof e);
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
/*     */   public int n() {
/* 509 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 514 */     return "w5x3";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/j/a/e.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */