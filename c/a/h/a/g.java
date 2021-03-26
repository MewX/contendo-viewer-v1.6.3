/*     */ package c.a.h.a;
/*     */ 
/*     */ import c.a.j.b;
/*     */ import c.a.j.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class g
/*     */   extends f
/*     */ {
/*     */   public int[] j;
/*     */   public int[] k;
/*     */   public int[] l;
/*     */   public int[] m;
/*     */   
/*     */   public g(b sb, int[] ulxs, int[] ulys, int[] lrxs, int[] lrys, int nr) {
/*  91 */     super(sb.n, sb.o, sb.p, sb.q);
/*  92 */     this.j = ulxs;
/*  93 */     this.k = ulys;
/*  94 */     this.l = lrxs;
/*  95 */     this.m = lrys;
/*     */ 
/*     */     
/*  98 */     if (sb.e) {
/*  99 */       this.e = true;
/*     */       
/* 101 */       int horEvenLow = sb.l % 2;
/* 102 */       int verEvenLow = sb.m % 2;
/*     */ 
/*     */       
/* 105 */       e hFilter = sb.i();
/* 106 */       e vFilter = sb.j();
/* 107 */       int hlnSup = hFilter.e();
/* 108 */       int hhnSup = hFilter.g();
/* 109 */       int hlpSup = hFilter.f();
/* 110 */       int hhpSup = hFilter.h();
/* 111 */       int vlnSup = vFilter.e();
/* 112 */       int vhnSup = vFilter.g();
/* 113 */       int vlpSup = vFilter.f();
/* 114 */       int vhpSup = vFilter.h();
/*     */ 
/*     */ 
/*     */       
/* 118 */       int[] lulxs = new int[nr];
/* 119 */       int[] lulys = new int[nr];
/* 120 */       int[] llrxs = new int[nr];
/* 121 */       int[] llrys = new int[nr];
/* 122 */       int[] hulxs = new int[nr];
/* 123 */       int[] hulys = new int[nr];
/* 124 */       int[] hlrxs = new int[nr];
/* 125 */       int[] hlrys = new int[nr];
/* 126 */       for (int r = nr - 1; r >= 0; r--) {
/*     */         
/* 128 */         int x = ulxs[r];
/* 129 */         if (horEvenLow == 0) {
/* 130 */           lulxs[r] = (x + 1 - hlnSup) / 2;
/* 131 */           hulxs[r] = (x - hhnSup) / 2;
/*     */         } else {
/*     */           
/* 134 */           lulxs[r] = (x - hlnSup) / 2;
/* 135 */           hulxs[r] = (x + 1 - hhnSup) / 2;
/*     */         } 
/*     */         
/* 138 */         int y = ulys[r];
/* 139 */         if (verEvenLow == 0) {
/* 140 */           lulys[r] = (y + 1 - vlnSup) / 2;
/* 141 */           hulys[r] = (y - vhnSup) / 2;
/*     */         } else {
/*     */           
/* 144 */           lulys[r] = (y - vlnSup) / 2;
/* 145 */           hulys[r] = (y + 1 - vhnSup) / 2;
/*     */         } 
/*     */         
/* 148 */         x = lrxs[r];
/* 149 */         if (horEvenLow == 0) {
/* 150 */           llrxs[r] = (x + hlpSup) / 2;
/* 151 */           hlrxs[r] = (x - 1 + hhpSup) / 2;
/*     */         } else {
/*     */           
/* 154 */           llrxs[r] = (x - 1 + hlpSup) / 2;
/* 155 */           hlrxs[r] = (x + hhpSup) / 2;
/*     */         } 
/*     */         
/* 158 */         y = lrys[r];
/* 159 */         if (verEvenLow == 0) {
/* 160 */           llrys[r] = (y + vlpSup) / 2;
/* 161 */           hlrys[r] = (y - 1 + vhpSup) / 2;
/*     */         } else {
/*     */           
/* 164 */           llrys[r] = (y - 1 + vlpSup) / 2;
/* 165 */           hlrys[r] = (y + vhpSup) / 2;
/*     */         } 
/*     */       } 
/*     */       
/* 169 */       this.d = new g(sb.e(), hulxs, hulys, hlrxs, hlrys, nr);
/* 170 */       this.b = new g(sb.d(), lulxs, hulys, llrxs, hlrys, nr);
/* 171 */       this.c = new g(sb.c(), hulxs, lulys, hlrxs, llrys, nr);
/* 172 */       this.a = new g(sb.b(), lulxs, lulys, llrxs, llrys, nr);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/h/a/g.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */