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
/*     */ 
/*     */ public class c
/*     */   extends b
/*     */ {
/*  65 */   private static final float[] k = new float[] { -0.091272F, -0.057544F, 0.591272F, 1.115087F, 0.591272F, -0.057544F, -0.091272F };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   private static final float[] l = new float[] { 0.026749F, 0.016864F, -0.078223F, -0.266864F, 0.602949F, -0.266864F, -0.078223F, 0.016864F, 0.026749F };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final float e = -1.5861343F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final float f = -0.052980117F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final float g = 0.8829111F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final float h = 0.44356853F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final float i = 0.8128931F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final float j = 1.2301741F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(float[] inSig, int inOff, int inLen, int inStep, float[] lowSig, int lowOff, int lowStep, float[] highSig, int highOff, int highStep) {
/* 145 */     int iStep = 2 * inStep;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 153 */     int ik = inOff + inStep;
/* 154 */     int lk = lowOff;
/* 155 */     int hk = highOff;
/*     */     
/*     */     int i, maxi;
/* 158 */     for (i = 1, maxi = inLen - 1; i < maxi; i += 2) {
/* 159 */       highSig[hk] = inSig[ik] + -1.5861343F * (inSig[ik - inStep] + inSig[ik + inStep]);
/*     */ 
/*     */       
/* 162 */       ik += iStep;
/* 163 */       hk += highStep;
/*     */     } 
/*     */ 
/*     */     
/* 167 */     if (inLen % 2 == 0) {
/* 168 */       highSig[hk] = inSig[ik] + -3.1722686F * inSig[ik - inStep];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     ik = inOff;
/* 175 */     lk = lowOff;
/* 176 */     hk = highOff;
/*     */     
/* 178 */     if (inLen > 1) {
/* 179 */       lowSig[lk] = inSig[ik] + -0.105960235F * highSig[hk];
/*     */     } else {
/*     */       
/* 182 */       lowSig[lk] = inSig[ik];
/*     */     } 
/*     */     
/* 185 */     ik += iStep;
/* 186 */     lk += lowStep;
/* 187 */     hk += highStep;
/*     */ 
/*     */     
/* 190 */     for (i = 2, maxi = inLen - 1; i < maxi; i += 2) {
/* 191 */       lowSig[lk] = inSig[ik] + -0.052980117F * (highSig[hk - highStep] + highSig[hk]);
/*     */ 
/*     */       
/* 194 */       ik += iStep;
/* 195 */       lk += lowStep;
/* 196 */       hk += highStep;
/*     */     } 
/*     */ 
/*     */     
/* 200 */     if (inLen % 2 == 1 && inLen > 2) {
/* 201 */       lowSig[lk] = inSig[ik] + -0.105960235F * highSig[hk - highStep];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 207 */     lk = lowOff;
/* 208 */     hk = highOff;
/*     */ 
/*     */     
/* 211 */     for (i = 1, maxi = inLen - 1; i < maxi; i += 2) {
/* 212 */       highSig[hk] = highSig[hk] + 0.8829111F * (lowSig[lk] + lowSig[lk + lowStep]);
/*     */       
/* 214 */       lk += lowStep;
/* 215 */       hk += highStep;
/*     */     } 
/*     */ 
/*     */     
/* 219 */     if (inLen % 2 == 0) {
/* 220 */       highSig[hk] = highSig[hk] + 1.7658222F * lowSig[lk];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 226 */     lk = lowOff;
/* 227 */     hk = highOff;
/*     */ 
/*     */ 
/*     */     
/* 231 */     if (inLen > 1) {
/* 232 */       lowSig[lk] = lowSig[lk] + 0.88713706F * highSig[hk];
/*     */     }
/*     */     
/* 235 */     lk += lowStep;
/* 236 */     hk += highStep;
/*     */ 
/*     */     
/* 239 */     for (i = 2, maxi = inLen - 1; i < maxi; i += 2) {
/* 240 */       lowSig[lk] = lowSig[lk] + 0.44356853F * (highSig[hk - highStep] + highSig[hk]);
/*     */ 
/*     */       
/* 243 */       lk += lowStep;
/* 244 */       hk += highStep;
/*     */     } 
/*     */ 
/*     */     
/* 248 */     if (inLen % 2 == 1 && inLen > 2) {
/* 249 */       lowSig[lk] = lowSig[lk] + 0.88713706F * highSig[hk - highStep];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 255 */     lk = lowOff;
/* 256 */     hk = highOff;
/*     */ 
/*     */     
/* 259 */     for (i = 0; i < inLen >> 1; i++) {
/* 260 */       lowSig[lk] = lowSig[lk] * 0.8128931F;
/* 261 */       highSig[hk] = highSig[hk] * 1.2301741F;
/* 262 */       lk += lowStep;
/* 263 */       hk += highStep;
/*     */     } 
/*     */ 
/*     */     
/* 267 */     if (inLen % 2 == 1 && inLen != 1) {
/* 268 */       lowSig[lk] = lowSig[lk] * 0.8128931F;
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
/*     */   public void b(float[] inSig, int inOff, int inLen, int inStep, float[] lowSig, int lowOff, int lowStep, float[] highSig, int highOff, int highStep) {
/* 326 */     int iStep = 2 * inStep;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 334 */     int ik = inOff;
/* 335 */     int lk = lowOff;
/* 336 */     int hk = highOff;
/*     */     
/* 338 */     if (inLen > 1) {
/*     */       
/* 340 */       highSig[hk] = inSig[ik] + -3.1722686F * inSig[ik + inStep];
/*     */     }
/*     */     else {
/*     */       
/* 344 */       highSig[hk] = inSig[ik] * 2.0F;
/*     */     } 
/*     */     
/* 347 */     ik += iStep;
/* 348 */     hk += highStep;
/*     */     
/*     */     int i;
/* 351 */     for (i = 2; i < inLen - 1; i += 2) {
/* 352 */       highSig[hk] = inSig[ik] + -1.5861343F * (inSig[ik - inStep] + inSig[ik + inStep]);
/*     */       
/* 354 */       ik += iStep;
/* 355 */       hk += highStep;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 360 */     if (inLen % 2 == 1 && inLen > 1) {
/* 361 */       highSig[hk] = inSig[ik] + -3.1722686F * inSig[ik - inStep];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 368 */     ik = inOff + inStep;
/* 369 */     lk = lowOff;
/* 370 */     hk = highOff;
/*     */ 
/*     */ 
/*     */     
/* 374 */     for (i = 1; i < inLen - 1; i += 2) {
/* 375 */       lowSig[lk] = inSig[ik] + -0.052980117F * (highSig[hk] + highSig[hk + highStep]);
/*     */ 
/*     */       
/* 378 */       ik += iStep;
/* 379 */       lk += lowStep;
/* 380 */       hk += highStep;
/*     */     } 
/* 382 */     if (inLen > 1 && inLen % 2 == 0)
/*     */     {
/* 384 */       lowSig[lk] = inSig[ik] + -0.105960235F * highSig[hk];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 390 */     lk = lowOff;
/* 391 */     hk = highOff;
/*     */     
/* 393 */     if (inLen > 1)
/*     */     {
/* 395 */       highSig[hk] = highSig[hk] + 1.7658222F * lowSig[lk];
/*     */     }
/*     */     
/* 398 */     hk += highStep;
/*     */ 
/*     */     
/* 401 */     for (i = 2; i < inLen - 1; i += 2) {
/* 402 */       highSig[hk] = highSig[hk] + 0.8829111F * (lowSig[lk] + lowSig[lk + lowStep]);
/* 403 */       lk += lowStep;
/* 404 */       hk += highStep;
/*     */     } 
/*     */ 
/*     */     
/* 408 */     if (inLen > 1 && inLen % 2 == 1)
/*     */     {
/* 410 */       highSig[hk] = highSig[hk] + 1.7658222F * lowSig[lk];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 416 */     lk = lowOff;
/* 417 */     hk = highOff;
/*     */ 
/*     */     
/* 420 */     for (i = 1; i < inLen - 1; i += 2) {
/* 421 */       lowSig[lk] = lowSig[lk] + 0.44356853F * (highSig[hk] + highSig[hk + highStep]);
/* 422 */       lk += lowStep;
/* 423 */       hk += highStep;
/*     */     } 
/*     */     
/* 426 */     if (inLen > 1 && inLen % 2 == 0) {
/* 427 */       lowSig[lk] = lowSig[lk] + 0.88713706F * highSig[hk];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 433 */     lk = lowOff;
/* 434 */     hk = highOff;
/*     */ 
/*     */     
/* 437 */     for (i = 0; i < inLen >> 1; i++) {
/* 438 */       lowSig[lk] = lowSig[lk] * 0.8128931F;
/* 439 */       highSig[hk] = highSig[hk] * 1.2301741F;
/* 440 */       lk += lowStep;
/* 441 */       hk += highStep;
/*     */     } 
/*     */ 
/*     */     
/* 445 */     if (inLen % 2 == 1 && inLen != 1) {
/* 446 */       highSig[hk] = highSig[hk] * 1.2301741F;
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
/*     */   public int a() {
/* 458 */     return 4;
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
/* 470 */     return 4;
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
/*     */   public int c() {
/* 482 */     return 3;
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
/*     */   public int d() {
/* 494 */     return 3;
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
/*     */   public int e() {
/* 508 */     return 3;
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
/*     */   public int f() {
/* 522 */     return 3;
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
/*     */   public int g() {
/* 536 */     return 4;
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
/*     */   public int h() {
/* 550 */     return 4;
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
/*     */   public float[] l() {
/* 568 */     return k;
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
/*     */   public float[] m() {
/* 587 */     return l;
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
/* 598 */     return 1;
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
/* 609 */     return false;
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
/*     */   public boolean a(int tailOvrlp, int headOvrlp, int inLen) {
/* 644 */     if (inLen % 2 == 0) {
/* 645 */       if (tailOvrlp >= 4 && headOvrlp >= 3) return true; 
/* 646 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 650 */     if (tailOvrlp >= 4 && headOvrlp >= 4) return true; 
/* 651 */     return false;
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
/* 667 */     return (obj == this || obj instanceof c);
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
/* 680 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 685 */     return "w9x7";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/j/a/c.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */