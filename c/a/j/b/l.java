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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class l
/*     */   extends k
/*     */ {
/*     */   public static final float d = -1.5861343F;
/*     */   public static final float e = -0.052980117F;
/*     */   public static final float f = 0.8829111F;
/*     */   public static final float g = 0.44350687F;
/*     */   public static final float h = 0.8128931F;
/*     */   public static final float i = 1.2301741F;
/*     */   
/*     */   public void a(float[] lowSig, int lowOff, int lowLen, int lowStep, float[] highSig, int highOff, int highLen, int highStep, float[] outSig, int outOff, int outStep) {
/* 140 */     int outLen = lowLen + highLen;
/* 141 */     int iStep = 2 * outStep;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 147 */     float sample = 0.0F;
/*     */ 
/*     */     
/* 150 */     int lk = lowOff;
/* 151 */     int hk = highOff;
/* 152 */     int ik = outOff;
/*     */ 
/*     */     
/* 155 */     if (outLen > 1) {
/* 156 */       outSig[ik] = lowSig[lk] / 0.8128931F - 0.88701373F * highSig[hk] / 1.2301741F;
/*     */     } else {
/*     */       
/* 159 */       outSig[ik] = lowSig[lk];
/*     */     } 
/*     */     
/* 162 */     lk += lowStep;
/* 163 */     hk += highStep;
/* 164 */     ik += iStep;
/*     */     
/*     */     int i;
/* 167 */     for (i = 2; i < outLen - 1; i += 2, ik += iStep, lk += lowStep, hk += highStep) {
/* 168 */       outSig[ik] = lowSig[lk] / 0.8128931F - 0.44350687F * (highSig[hk - highStep] + highSig[hk]) / 1.2301741F;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 173 */     if (outLen % 2 == 1 && 
/* 174 */       outLen > 2) {
/* 175 */       outSig[ik] = lowSig[lk] / 0.8128931F - 0.88701373F * highSig[hk - highStep] / 1.2301741F;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 183 */     lk = lowOff;
/* 184 */     hk = highOff;
/* 185 */     ik = outOff + outStep;
/*     */ 
/*     */     
/* 188 */     for (i = 1; i < outLen - 1; i += 2, ik += iStep, hk += highStep, lk += lowStep) {
/* 189 */       outSig[ik] = highSig[hk] / 1.2301741F - 0.8829111F * (outSig[ik - outStep] + outSig[ik + outStep]);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 194 */     if (outLen % 2 == 0) {
/* 195 */       outSig[ik] = highSig[hk] / 1.2301741F - 1.7658222F * outSig[ik - outStep];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 201 */     ik = outOff;
/*     */ 
/*     */ 
/*     */     
/* 205 */     if (outLen > 1) {
/* 206 */       outSig[ik] = outSig[ik] - -0.105960235F * outSig[ik + outStep];
/*     */     }
/* 208 */     ik += iStep;
/*     */ 
/*     */     
/* 211 */     for (i = 2; i < outLen - 1; i += 2, ik += iStep) {
/* 212 */       outSig[ik] = outSig[ik] - -0.052980117F * (outSig[ik - outStep] + outSig[ik + outStep]);
/*     */     }
/*     */ 
/*     */     
/* 216 */     if (outLen % 2 == 1 && outLen > 2) {
/* 217 */       outSig[ik] = outSig[ik] - -0.105960235F * outSig[ik - outStep];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 223 */     ik = outOff + outStep;
/*     */ 
/*     */     
/* 226 */     for (i = 1; i < outLen - 1; i += 2, ik += iStep) {
/* 227 */       outSig[ik] = outSig[ik] - -1.5861343F * (outSig[ik - outStep] + outSig[ik + outStep]);
/*     */     }
/*     */ 
/*     */     
/* 231 */     if (outLen % 2 == 0) {
/* 232 */       outSig[ik] = outSig[ik] - -3.1722686F * outSig[ik - outStep];
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(float[] lowSig, int lowOff, int lowLen, int lowStep, float[] highSig, int highOff, int highLen, int highStep, float[] outSig, int outOff, int outStep) {
/* 295 */     int outLen = lowLen + highLen;
/* 296 */     int iStep = 2 * outStep;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 302 */     int lk = lowOff;
/* 303 */     int hk = highOff;
/*     */     
/* 305 */     if (outLen != 1) {
/* 306 */       int outLen2 = outLen >> 1;
/*     */       
/* 308 */       for (int j = 0; j < outLen2; j++) {
/* 309 */         lowSig[lk] = lowSig[lk] / 0.8128931F;
/* 310 */         highSig[hk] = highSig[hk] / 1.2301741F;
/* 311 */         lk += lowStep;
/* 312 */         hk += highStep;
/*     */       } 
/*     */       
/* 315 */       if (outLen % 2 == 1) {
/* 316 */         highSig[hk] = highSig[hk] / 1.2301741F;
/*     */       }
/*     */     } else {
/*     */       
/* 320 */       highSig[highOff] = highSig[highOff] / 2.0F;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 326 */     lk = lowOff;
/* 327 */     hk = highOff;
/* 328 */     int ik = outOff + outStep;
/*     */     
/*     */     int i;
/* 331 */     for (i = 1; i < outLen - 1; i += 2) {
/* 332 */       outSig[ik] = lowSig[lk] - 0.44350687F * (highSig[hk] + highSig[hk + highStep]);
/*     */       
/* 334 */       ik += iStep;
/* 335 */       lk += lowStep;
/* 336 */       hk += highStep;
/*     */     } 
/*     */     
/* 339 */     if (outLen % 2 == 0 && outLen > 1)
/*     */     {
/* 341 */       outSig[ik] = lowSig[lk] - 0.88701373F * highSig[hk];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 347 */     hk = highOff;
/* 348 */     ik = outOff;
/*     */     
/* 350 */     if (outLen > 1) {
/* 351 */       outSig[ik] = highSig[hk] - 1.7658222F * outSig[ik + outStep];
/*     */     } else {
/* 353 */       outSig[ik] = highSig[hk];
/*     */     } 
/*     */     
/* 356 */     ik += iStep;
/* 357 */     hk += highStep;
/*     */ 
/*     */     
/* 360 */     for (i = 2; i < outLen - 1; i += 2) {
/* 361 */       outSig[ik] = highSig[hk] - 0.8829111F * (outSig[ik - outStep] + outSig[ik + outStep]);
/*     */       
/* 363 */       ik += iStep;
/* 364 */       hk += highStep;
/*     */     } 
/*     */ 
/*     */     
/* 368 */     if (outLen % 2 == 1 && outLen > 1)
/*     */     {
/* 370 */       outSig[ik] = highSig[hk] - 1.7658222F * outSig[ik - outStep];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 376 */     ik = outOff + outStep;
/*     */ 
/*     */     
/* 379 */     for (i = 1; i < outLen - 1; i += 2) {
/* 380 */       outSig[ik] = outSig[ik] - -0.052980117F * (outSig[ik - outStep] + outSig[ik + outStep]);
/* 381 */       ik += iStep;
/*     */     } 
/*     */     
/* 384 */     if (outLen % 2 == 0 && outLen > 1)
/*     */     {
/* 386 */       outSig[ik] = outSig[ik] - -0.105960235F * outSig[ik - outStep];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 392 */     ik = outOff;
/*     */     
/* 394 */     if (outLen > 1)
/*     */     {
/* 396 */       outSig[ik] = outSig[ik] - -3.1722686F * outSig[ik + outStep];
/*     */     }
/* 398 */     ik += iStep;
/*     */ 
/*     */     
/* 401 */     for (i = 2; i < outLen - 1; i += 2) {
/* 402 */       outSig[ik] = outSig[ik] - -1.5861343F * (outSig[ik - outStep] + outSig[ik + outStep]);
/* 403 */       ik += iStep;
/*     */     } 
/*     */ 
/*     */     
/* 407 */     if (outLen % 2 == 1 && outLen > 1)
/*     */     {
/* 409 */       outSig[ik] = outSig[ik] - -3.1722686F * outSig[ik - outStep];
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
/* 420 */     return 4;
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
/* 431 */     return 4;
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
/* 442 */     return 3;
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
/* 453 */     return 3;
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
/* 466 */     return 3;
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
/* 479 */     return 3;
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
/* 492 */     return 4;
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
/* 505 */     return 4;
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
/* 516 */     return 1;
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
/* 527 */     return false;
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
/*     */   public boolean a(int tailOvrlp, int headOvrlp, int inLen) {
/* 565 */     if (inLen % 2 == 0) {
/* 566 */       if (tailOvrlp >= 2 && headOvrlp >= 1) return true; 
/* 567 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 571 */     if (tailOvrlp >= 2 && headOvrlp >= 2) return true; 
/* 572 */     return false;
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
/*     */   public String toString() {
/* 584 */     return "w9x7 (lifting)";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/j/b/l.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */