/*     */ package c.a.h;
/*     */ 
/*     */ import c.a.b.a;
/*     */ import c.a.e.c;
/*     */ import c.a.g.a.a;
/*     */ import c.a.j.b.g;
/*     */ import c.a.j.b.h;
/*     */ import c.a.j.b.i;
/*     */ import com.github.jaiimageio.jpeg2000.impl.J2KImageReadParamJava;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */   extends h
/*     */   implements a
/*     */ {
/*     */   private a b;
/*     */   public static final char a = 'R';
/*  81 */   private static final String[][] c = new String[][] { { "Rno_roi", null, "This argument makes sure that the no ROI de-scaling is performed. Decompression is done like there is no ROI in the image", null } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private a d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public b(a src, a mss) {
/* 101 */     super((g)src);
/* 102 */     this.d = src;
/* 103 */     this.b = mss;
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
/*     */   public i f(int t, int c) {
/* 121 */     return this.d.f(t, c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/* 129 */     return this.d.a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/* 137 */     return this.d.b();
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
/*     */   public static String[][] g() {
/* 154 */     return c;
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
/*     */   public c a(int j, int m, int n, i sb, c cblk) {
/* 202 */     return b(j, m, n, sb, cblk);
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
/*     */   public c b(int j, int m, int n, i sb, c cblk) {
/* 255 */     cblk = this.d.b(j, m, n, sb, cblk);
/*     */ 
/*     */     
/* 258 */     boolean noRoiInTile = false;
/* 259 */     if (this.b == null || this.b.a(e(), j) == null) {
/* 260 */       noRoiInTile = true;
/*     */     }
/* 262 */     if (noRoiInTile || cblk == null) {
/* 263 */       return cblk;
/*     */     }
/* 265 */     int[] data = (int[])cblk.b();
/* 266 */     int ulx = cblk.e;
/* 267 */     int uly = cblk.f;
/* 268 */     int w = cblk.g;
/* 269 */     int i2 = cblk.h;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 274 */     int boost = ((Integer)this.b.a(e(), j)).intValue();
/* 275 */     int mask = (1 << sb.A) - 1 << 31 - sb.A;
/* 276 */     int mask2 = (mask ^ 0xFFFFFFFF) & Integer.MAX_VALUE;
/*     */     
/* 278 */     int wrap = cblk.j - w;
/* 279 */     int k = cblk.i + cblk.j * (i2 - 1) + w - 1;
/* 280 */     for (int i1 = i2; i1 > 0; i1--) {
/* 281 */       for (int i3 = w; i3 > 0; i3--, k--) {
/* 282 */         int tmp = data[k];
/* 283 */         if ((tmp & mask) == 0) {
/* 284 */           data[k] = tmp & Integer.MIN_VALUE | tmp << boost;
/*     */         
/*     */         }
/* 287 */         else if ((tmp & mask2) != 0) {
/*     */ 
/*     */ 
/*     */           
/* 291 */           data[k] = tmp & (mask2 ^ 0xFFFFFFFF) | 1 << 30 - sb.A;
/*     */         } 
/*     */       } 
/*     */       
/* 295 */       k -= wrap;
/*     */     } 
/* 297 */     return cblk;
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
/*     */   public static b a(a src, J2KImageReadParamJava j2krparam, a decSpec) {
/* 318 */     boolean noRoi = j2krparam.getNoROIDescaling();
/* 319 */     if (noRoi || decSpec.b == null)
/*     */     {
/* 321 */       return new b(src, null);
/*     */     }
/*     */     
/* 324 */     return new b(src, decSpec.b);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/h/b.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */