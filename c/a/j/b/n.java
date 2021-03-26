/*     */ package c.a.j.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class n
/*     */   extends m
/*     */ {
/*     */   public void a(int[] lowSig, int lowOff, int lowLen, int lowStep, int[] highSig, int highOff, int highLen, int highStep, int[] outSig, int outOff, int outStep) {
/* 113 */     int outLen = lowLen + highLen;
/* 114 */     int iStep = 2 * outStep;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     int lk = lowOff;
/* 125 */     int hk = highOff;
/* 126 */     int ik = outOff;
/*     */ 
/*     */     
/* 129 */     if (outLen > 1) {
/* 130 */       outSig[ik] = lowSig[lk] - (highSig[hk] + 1 >> 1);
/*     */     } else {
/*     */       
/* 133 */       outSig[ik] = lowSig[lk];
/*     */     } 
/*     */     
/* 136 */     lk += lowStep;
/* 137 */     hk += highStep;
/* 138 */     ik += iStep;
/*     */     
/*     */     int i;
/* 141 */     for (i = 2; i < outLen - 1; i += 2) {
/* 142 */       outSig[ik] = lowSig[lk] - (highSig[hk - highStep] + highSig[hk] + 2 >> 2);
/*     */ 
/*     */       
/* 145 */       lk += lowStep;
/* 146 */       hk += highStep;
/* 147 */       ik += iStep;
/*     */     } 
/*     */ 
/*     */     
/* 151 */     if (outLen % 2 == 1 && outLen > 2) {
/* 152 */       outSig[ik] = lowSig[lk] - (2 * highSig[hk - highStep] + 2 >> 2);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     hk = highOff;
/* 161 */     ik = outOff + outStep;
/*     */ 
/*     */     
/* 164 */     for (i = 1; i < outLen - 1; i += 2) {
/*     */ 
/*     */       
/* 167 */       outSig[ik] = highSig[hk] + (outSig[ik - outStep] + outSig[ik + outStep] >> 1);
/*     */ 
/*     */       
/* 170 */       hk += highStep;
/* 171 */       ik += iStep;
/*     */     } 
/*     */ 
/*     */     
/* 175 */     if (outLen % 2 == 0 && outLen > 1) {
/* 176 */       outSig[ik] = highSig[hk] + outSig[ik - outStep];
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(int[] lowSig, int lowOff, int lowLen, int lowStep, int[] highSig, int highOff, int highLen, int highStep, int[] outSig, int outOff, int outStep) {
/* 231 */     int outLen = lowLen + highLen;
/* 232 */     int iStep = 2 * outStep;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 242 */     int lk = lowOff;
/* 243 */     int hk = highOff;
/* 244 */     int ik = outOff + outStep;
/*     */     
/*     */     int i;
/* 247 */     for (i = 1; i < outLen - 1; i += 2) {
/* 248 */       outSig[ik] = lowSig[lk] - (highSig[hk] + highSig[hk + highStep] + 2 >> 2);
/*     */ 
/*     */       
/* 251 */       lk += lowStep;
/* 252 */       hk += highStep;
/* 253 */       ik += iStep;
/*     */     } 
/*     */     
/* 256 */     if (outLen > 1 && outLen % 2 == 0)
/*     */     {
/* 258 */       outSig[ik] = lowSig[lk] - (2 * highSig[hk] + 2 >> 2);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 265 */     hk = highOff;
/* 266 */     ik = outOff;
/*     */     
/* 268 */     if (outLen > 1) {
/* 269 */       outSig[ik] = highSig[hk] + outSig[ik + outStep];
/*     */     }
/*     */     else {
/*     */       
/* 273 */       outSig[ik] = highSig[hk] >> 1;
/*     */     } 
/*     */     
/* 276 */     hk += highStep;
/* 277 */     ik += iStep;
/*     */ 
/*     */     
/* 280 */     for (i = 2; i < outLen - 1; i += 2) {
/*     */ 
/*     */       
/* 283 */       outSig[ik] = highSig[hk] + (outSig[ik - outStep] + outSig[ik + outStep] >> 1);
/*     */       
/* 285 */       hk += highStep;
/* 286 */       ik += iStep;
/*     */     } 
/*     */ 
/*     */     
/* 290 */     if (outLen % 2 == 1 && outLen > 1) {
/* 291 */       outSig[ik] = highSig[hk] + outSig[ik - outStep];
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
/* 302 */     return 2;
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
/* 313 */     return 2;
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
/* 324 */     return 1;
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
/* 335 */     return 1;
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
/* 348 */     return 1;
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
/* 361 */     return 1;
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
/* 374 */     return 2;
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
/* 387 */     return 2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int i() {
/* 398 */     return 0;
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
/* 409 */     return true;
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
/* 441 */     if (inLen % 2 == 0) {
/* 442 */       if (tailOvrlp >= 2 && headOvrlp >= 1) return true; 
/* 443 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 447 */     if (tailOvrlp >= 2 && headOvrlp >= 2) return true; 
/* 448 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 458 */     return "w5x3 (lifting)";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/j/b/n.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */