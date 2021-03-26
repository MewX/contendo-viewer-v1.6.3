/*     */ package c.a.h.a;
/*     */ 
/*     */ import c.a.j.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   extends c
/*     */ {
/*     */   private int[] e;
/*     */   private int[] f;
/*     */   private int[] g;
/*     */   private int[] h;
/*     */   private int[] i;
/*     */   private g[] j;
/*     */   
/*     */   public e(b[] ROIs, int nrc) {
/* 102 */     super(ROIs, nrc);
/* 103 */     int nr = ROIs.length;
/*     */     
/* 105 */     this.i = new int[nrc];
/* 106 */     this.j = new g[nrc];
/*     */ 
/*     */     
/* 109 */     for (int r = nr - 1; r >= 0; r--) {
/* 110 */       this.i[(ROIs[r]).d] = this.i[(ROIs[r]).d] + 1;
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
/*     */   public boolean a(c.a.e.e db, b sb, int magbits, int i) {
/* 135 */     int x = db.e;
/* 136 */     int y = db.f;
/* 137 */     int w = db.g;
/* 138 */     int h = db.h;
/* 139 */     int[] mask = db.c();
/*     */     
/* 141 */     int ulx = 0, uly = 0, lrx = 0, lry = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 152 */     if (!this.c[i]) {
/* 153 */       a(sb, magbits, i);
/* 154 */       this.c[i] = true;
/*     */     } 
/*     */     
/* 157 */     if (!this.d) {
/* 158 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 162 */     g srm = (g)this.j[i].a(x, y);
/* 163 */     int[] culxs = srm.j;
/* 164 */     int[] culys = srm.k;
/* 165 */     int[] clrxs = srm.l;
/* 166 */     int[] clrys = srm.m;
/* 167 */     int maxROI = culxs.length - 1;
/*     */ 
/*     */ 
/*     */     
/* 171 */     x -= srm.f;
/* 172 */     y -= srm.g;
/* 173 */     for (int r = maxROI; r >= 0; r--) {
/* 174 */       ulx = culxs[r] - x;
/* 175 */       if (ulx < 0) {
/* 176 */         ulx = 0;
/* 177 */       } else if (ulx >= w) {
/* 178 */         ulx = w;
/*     */       } 
/*     */       
/* 181 */       uly = culys[r] - y;
/* 182 */       if (uly < 0) {
/* 183 */         uly = 0;
/* 184 */       } else if (uly >= h) {
/* 185 */         uly = h;
/*     */       } 
/*     */       
/* 188 */       lrx = clrxs[r] - x;
/* 189 */       if (lrx < 0) {
/* 190 */         lrx = -1;
/* 191 */       } else if (lrx >= w) {
/* 192 */         lrx = w - 1;
/*     */       } 
/*     */       
/* 195 */       lry = clrys[r] - y;
/* 196 */       if (lry < 0) {
/* 197 */         lry = -1;
/* 198 */       } else if (lry >= h) {
/* 199 */         lry = h - 1;
/*     */       } 
/*     */ 
/*     */       
/* 203 */       int j = w * lry + lrx;
/* 204 */       int maxj = lrx - ulx;
/* 205 */       int wrap = w - maxj - 1;
/* 206 */       int maxk = lry - uly;
/*     */       
/* 208 */       for (int k = maxk; k >= 0; k--) {
/* 209 */         for (int m = maxj; m >= 0; m--, j--)
/* 210 */           mask[j] = magbits; 
/* 211 */         j -= wrap;
/*     */       } 
/*     */     } 
/* 214 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 221 */     return "Fast rectangular ROI mask generator";
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
/*     */   public void a(b sb, int magbits, int n) {
/* 234 */     int nr = this.i[n];
/*     */ 
/*     */     
/* 237 */     int tileulx = sb.l;
/* 238 */     int tileuly = sb.m;
/* 239 */     int tilew = sb.p;
/* 240 */     int tileh = sb.q;
/* 241 */     b[] ROIs = this.a;
/*     */     
/* 243 */     this.e = new int[nr];
/* 244 */     this.f = new int[nr];
/* 245 */     this.g = new int[nr];
/* 246 */     this.h = new int[nr];
/*     */     
/* 248 */     nr = 0;
/*     */     
/* 250 */     for (int r = ROIs.length - 1; r >= 0; r--) {
/* 251 */       if ((ROIs[r]).d == n) {
/* 252 */         int ulx = (ROIs[r]).e;
/* 253 */         int uly = (ROIs[r]).f;
/* 254 */         int lrx = (ROIs[r]).g + ulx - 1;
/* 255 */         int lry = (ROIs[r]).h + uly - 1;
/*     */         
/* 257 */         if (ulx <= tileulx + tilew - 1 && uly <= tileuly + tileh - 1 && lrx >= tileulx && lry >= tileuly) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 263 */           ulx -= tileulx;
/* 264 */           lrx -= tileulx;
/* 265 */           uly -= tileuly;
/* 266 */           lry -= tileuly;
/*     */           
/* 268 */           ulx = (ulx < 0) ? 0 : ulx;
/* 269 */           uly = (uly < 0) ? 0 : uly;
/* 270 */           lrx = (lrx > tilew - 1) ? (tilew - 1) : lrx;
/* 271 */           lry = (lry > tileh - 1) ? (tileh - 1) : lry;
/*     */           
/* 273 */           this.e[nr] = ulx;
/* 274 */           this.f[nr] = uly;
/* 275 */           this.g[nr] = lrx;
/* 276 */           this.h[nr] = lry;
/* 277 */           nr++;
/*     */         } 
/*     */       } 
/* 280 */     }  if (nr == 0) {
/* 281 */       this.d = false;
/*     */     } else {
/*     */       
/* 284 */       this.d = true;
/*     */     } 
/* 286 */     this.j[n] = new g(sb, this.e, this.f, this.g, this.h, nr);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/h/a/e.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */