/*    */ package com.a.a.h.a;
/*    */ 
/*    */ public class a extends Random {
/*    */   private static final long j = 1L;
/*    */   static final int a = 624;
/*    */   static final int b = 397;
/*    */   static final int c = -2147483648;
/*    */   static final int d = 2147483647;
/*    */   static final int e = -1727483681;
/*    */   int g;
/*    */   int h;
/* 12 */   int[] f = new int[624]; int i;
/*    */   
/*    */   public a() {
/* 15 */     setSeed(System.currentTimeMillis()); }
/* 16 */   public a(long seed) { setSeed(seed); } public a(int[] seeds) {
/* 17 */     a(seeds);
/*    */   }
/*    */   public synchronized void setSeed(long seed) {
/* 20 */     if (this.f == null)
/* 21 */       return;  this.f[0] = (int)seed;
/* 22 */     for (int i = 1; i < 624; i++)
/* 23 */       this.f[i] = 1812433253 * (this.f[i - 1] ^ this.f[i - 1] >>> 30) + i; 
/* 24 */     this.g = 0; this.h = 1; this.i = 397;
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized void a(int[] seeds) {
/* 29 */     setSeed(19650218L);
/* 30 */     int i = 1, j = 0; int k;
/* 31 */     for (k = 0; k < Math.max(624, seeds.length); k++) {
/* 32 */       this.f[i] = this.f[i] ^ (this.f[i - 1] ^ this.f[i - 1] >>> 30) * 1664525;
/* 33 */       this.f[i] = this.f[i] + seeds[j] + j;
/* 34 */       if (++i >= 624) { this.f[0] = this.f[623]; i = 1; }
/* 35 */        if (++j >= seeds.length) j = 0; 
/*    */     } 
/* 37 */     for (k = 0; k < 623; k++) {
/* 38 */       this.f[i] = this.f[i] ^ (this.f[i - 1] ^ this.f[i - 1] >>> 30) * 1566083941;
/* 39 */       this.f[i] = this.f[i] - i;
/* 40 */       if (++i >= 624) { this.f[0] = this.f[623]; i = 1; }
/*    */     
/* 42 */     }  this.f[0] = Integer.MIN_VALUE;
/*    */   }
/*    */   
/*    */   protected synchronized int next(int bits) {
/* 46 */     int y = this.f[this.g] & Integer.MIN_VALUE | this.f[this.h] & Integer.MAX_VALUE;
/* 47 */     y = this.f[this.g] = this.f[this.i] ^ y >>> 1 ^ (y & 0x1) * -1727483681;
/* 48 */     if (++this.g == 624) this.g = 0; 
/* 49 */     if (++this.h == 624) this.h = 0; 
/* 50 */     if (++this.i == 624) this.i = 0;
/*    */     
/* 52 */     y ^= y >>> 11;
/* 53 */     y ^= y << 7 & 0x9D2C5680;
/* 54 */     y ^= y << 15 & 0xEFC60000;
/* 55 */     y ^= y >>> 18;
/* 56 */     return y >>> 32 - bits;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/h/a/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */