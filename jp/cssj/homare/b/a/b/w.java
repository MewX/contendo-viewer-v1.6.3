/*     */ package jp.cssj.homare.b.a.b;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.a.a.d;
/*     */ import jp.cssj.homare.b.a.c.I;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.c.s;
/*     */ import jp.cssj.homare.b.a.c.z;
/*     */ import jp.cssj.homare.b.a.d;
/*     */ import jp.cssj.homare.b.a.j;
/*     */ import jp.cssj.homare.b.a.m;
/*     */ import jp.cssj.homare.b.a.p;
/*     */ import jp.cssj.homare.b.c.c;
/*     */ import jp.cssj.homare.b.c.f;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.homare.b.g.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class w
/*     */   extends d
/*     */   implements p
/*     */ {
/*  40 */   protected List<v> f = null; protected final I e;
/*     */   
/*     */   public w(s params, I pos) {
/*  43 */     super(params);
/*  44 */     this.e = pos;
/*     */   }
/*     */   private static final boolean h = false;
/*     */   public final byte a() {
/*  48 */     return 10;
/*     */   }
/*     */   
/*     */   public final z b_() {
/*  52 */     return (z)this.e;
/*     */   }
/*     */   
/*     */   public final I g() {
/*  56 */     return this.e;
/*     */   }
/*     */   
/*     */   public final void a(v row) {
/*  60 */     if (!g && row == null) throw new AssertionError(); 
/*  61 */     if (this.f == null) {
/*  62 */       this.f = new ArrayList<>();
/*     */     }
/*  64 */     this.f.add(row);
/*  65 */     this.d += row.f();
/*  66 */     if (row.e() > this.c) {
/*  67 */       this.c = row.e();
/*     */     }
/*     */   }
/*     */   
/*     */   public final int h() {
/*  72 */     if (this.f == null) {
/*  73 */       return 0;
/*     */     }
/*  75 */     return this.f.size();
/*     */   }
/*     */   
/*     */   public final v a(int i) {
/*  79 */     if (this.f == null) {
/*  80 */       throw new ArrayIndexOutOfBoundsException(i);
/*     */     }
/*  82 */     return this.f.get(i);
/*     */   }
/*     */   
/*     */   public final void a(m containerBox) {
/*  86 */     for (int j = 0; j < h(); j++) {
/*  87 */       v rowBox = a(j);
/*  88 */       rowBox.a(containerBox);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(n pageBox, g drawer, Shape clip, AffineTransform transform, double x, double y) {
/*  94 */     if (this.a.ao == 0.0F) {
/*     */       return;
/*     */     }
/*  97 */     if (this.a.a.c()) {
/*     */       
/*  99 */       c c = new c(pageBox, clip, this.a.ao, transform, this.a.a, this.a.b, p(), q());
/* 100 */       drawer.a((f)c, x, y);
/*     */     } 
/* 102 */     if (this.f == null) {
/*     */       return;
/*     */     }
/* 105 */     if (e.a(this.b.D)) {
/*     */       
/* 107 */       x += this.d;
/* 108 */       for (int i = 0; i < this.f.size(); i++) {
/* 109 */         v row = this.f.get(i);
/* 110 */         x -= row.f();
/* 111 */         row.a(pageBox, drawer, clip, transform, x, y);
/*     */       } 
/*     */     } else {
/*     */       
/* 115 */       for (int i = 0; i < this.f.size(); i++) {
/* 116 */         v row = this.f.get(i);
/* 117 */         row.a(pageBox, drawer, clip, transform, x, y);
/* 118 */         y += row.f();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void b(n pageBox, g drawer, a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 125 */     if (this.a.ao == 0.0F) {
/*     */       return;
/*     */     }
/* 128 */     if (this.f == null) {
/*     */       return;
/*     */     }
/* 131 */     if (e.a(this.b.D)) {
/*     */       
/* 133 */       x += this.d;
/* 134 */       for (int i = 0; i < this.f.size(); i++) {
/* 135 */         v row = this.f.get(i);
/* 136 */         x -= row.f();
/* 137 */         row.b(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */       } 
/*     */     } else {
/*     */       
/* 141 */       for (int i = 0; i < this.f.size(); i++) {
/* 142 */         v row = this.f.get(i);
/* 143 */         row.b(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/* 144 */         y += row.f();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(n pageBox, g drawer, a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 151 */     visitor.a(transform, (j)this, x, y);
/*     */     
/* 153 */     if (this.a.ao == 0.0F) {
/*     */       return;
/*     */     }
/* 156 */     if (this.a.an == 1) {
/* 157 */       g newDrawer = new g(this.a.am);
/* 158 */       drawer.a(newDrawer);
/* 159 */       drawer = newDrawer;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 165 */     if (this.f == null) {
/*     */       return;
/*     */     }
/* 168 */     if (e.a(this.b.D)) {
/*     */       
/* 170 */       x += this.d;
/* 171 */       for (int i = 0; i < this.f.size(); i++) {
/* 172 */         v row = this.f.get(i);
/* 173 */         x -= row.f();
/* 174 */         row.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*     */       } 
/*     */     } else {
/*     */       
/* 178 */       for (int i = 0; i < this.f.size(); i++) {
/* 179 */         v row = this.f.get(i);
/* 180 */         row.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/* 181 */         y += row.f();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void a(StringBuffer textBuff) {
/* 187 */     if (this.f == null) {
/*     */       return;
/*     */     }
/* 190 */     for (int i = 0; i < this.f.size(); i++) {
/*     */       
/* 192 */       j box = (j)this.f.get(i);
/* 193 */       box.a(textBuff);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final p b(double pageLimit, d mode, byte flags) {
/* 198 */     if (!g && (flags & 0x2) != 0) throw new AssertionError();
/*     */ 
/*     */ 
/*     */     
/* 202 */     if (mode.a() == 1) {
/*     */       
/* 204 */       d.c force = (d.c)mode;
/* 205 */       w w1 = i();
/* 206 */       int row = force.h;
/* 207 */       if (row != -1) {
/* 208 */         int k; for (k = row + 1; k < this.f.size(); k++) {
/* 209 */           v rowBox = this.f.get(k);
/* 210 */           this.d -= rowBox.f();
/* 211 */           w1.a(rowBox);
/*     */         } 
/* 213 */         for (k = this.f.size() - 1; k > row; k--) {
/* 214 */           this.f.remove(k);
/*     */         }
/*     */       } 
/* 217 */       return w1;
/*     */     } 
/*     */     
/* 220 */     if (e.a(pageLimit, 0.0D) < 0)
/*     */     {
/* 222 */       return null;
/*     */     }
/* 224 */     if (e.a(pageLimit, f()) >= 0)
/*     */     {
/* 226 */       return null;
/*     */     }
/* 228 */     s con = this.a;
/* 229 */     if ((flags & 0x1) == 0 && (con.f == 1 || 
/* 230 */       e.a(pageLimit, 0.0D) < 0))
/*     */     {
/* 232 */       return this;
/*     */     }
/*     */ 
/*     */     
/* 236 */     if (this.f == null || this.f.isEmpty()) {
/* 237 */       if ((flags & 0x1) != 0) {
/* 238 */         return null;
/*     */       }
/* 240 */       return this;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 246 */     w nextRowGroup = null;
/*     */     
/* 248 */     boolean ignoreBreakAvoid = false;
/* 249 */     double savePageLimit = pageLimit;
/*     */     int i;
/* 251 */     for (i = 0; i < this.f.size(); i++) {
/* 252 */       v prevRow = this.f.get(i);
/* 253 */       double prevRowSize = prevRow.f();
/* 254 */       if (i < this.f.size() - 1 && e.a(pageLimit, prevRowSize) > 0) {
/*     */         
/* 256 */         pageLimit -= prevRowSize;
/*     */         continue;
/*     */       } 
/* 259 */       byte xflags = (byte)(flags & 0x5);
/* 260 */       if ((xflags & 0x1) != 0)
/*     */       {
/* 262 */         if (i > 0) {
/*     */           
/* 264 */           xflags = (byte)(xflags ^ 0x1);
/* 265 */           boolean first = false;
/*     */           
/* 267 */           v topRow = this.f.get(0);
/* 268 */           for (int k = 0; k < prevRow.h(); k++) {
/* 269 */             v.b prevCell = prevRow.a(k);
/* 270 */             if (k < topRow.h()) {
/*     */ 
/*     */               
/* 273 */               v.b topCell = topRow.a(k);
/* 274 */               if (prevCell.d().b() == topCell.d().b()) {
/* 275 */                 first = true; break;
/*     */               } 
/*     */             } 
/*     */           } 
/* 279 */           if (first) {
/* 280 */             xflags = (byte)(xflags | 0x8);
/*     */           }
/*     */         } else {
/*     */           
/* 284 */           xflags = (byte)(xflags | 0x8);
/*     */         } 
/*     */       }
/* 287 */       v nextRow = (v)prevRow.b(pageLimit, mode, xflags);
/*     */ 
/*     */ 
/*     */       
/* 291 */       if (nextRow == null) {
/* 292 */         if (!ignoreBreakAvoid && i == 0 && (flags & 0x1) != 0) {
/*     */           
/* 294 */           ignoreBreakAvoid = true;
/* 295 */           pageLimit = savePageLimit;
/* 296 */           i = -1;
/*     */         } else {
/*     */           
/* 299 */           pageLimit -= prevRowSize;
/*     */         } 
/*     */         continue;
/*     */       } 
/* 303 */       if (nextRow == prevRow) {
/* 304 */         if (i == 0) {
/*     */           
/* 306 */           if (!g && (xflags & 0x1) != 0) throw new AssertionError(); 
/* 307 */           return this;
/*     */         } 
/* 309 */         v beforeRow = this.f.get(i - 1);
/* 310 */         if (!ignoreBreakAvoid) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 316 */           boolean bool = ((beforeRow.g()).b == 1 || (prevRow.g()).a == 1);
/*     */           
/* 318 */           if (!bool && (i != 1 || (flags & 0x1) == 0)) {
/*     */             
/* 320 */             for (int m = 0; m < beforeRow.h(); m++) {
/* 321 */               v.b cell = beforeRow.a(m);
/* 322 */               i cellParams = cell.d().c_();
/* 323 */               if (cellParams.U != 0 || 
/* 324 */                 e.a(cellParams.D) != e.a(this.b.D))
/*     */               {
/*     */                 
/* 327 */                 if (cell.a() != null) {
/* 328 */                   bool = true;
/*     */                   break;
/*     */                 }  } 
/*     */             } 
/* 332 */           } else if (i == 1 && (flags & 0x1) != 0) {
/* 333 */             for (int m = 0; m < beforeRow.h(); m++) {
/* 334 */               v.b cell = beforeRow.a(m);
/* 335 */               if (cell.a() != null) {
/*     */ 
/*     */                 
/* 338 */                 i cellParams = cell.d().c_();
/* 339 */                 if (e.a(cellParams.D) == 
/* 340 */                   e.a(this.b.D)) {
/* 341 */                   bool = false;
/*     */                 }
/*     */                 else {
/*     */                   
/* 345 */                   bool = true; break;
/*     */                 } 
/*     */               } 
/*     */             } 
/* 349 */           }  if (bool) {
/*     */             
/* 351 */             if (!ignoreBreakAvoid && (xflags & 0x8) != 0) {
/*     */               
/* 353 */               ignoreBreakAvoid = true;
/* 354 */               pageLimit = savePageLimit;
/* 355 */               i = -1;
/*     */             }
/*     */             else {
/*     */               
/* 359 */               pageLimit = beforeRow.f() - 1.0D;
/* 360 */               i -= 2;
/*     */             } 
/*     */             
/*     */             continue;
/*     */           } 
/*     */         } 
/* 366 */         boolean breakAvoid = false;
/* 367 */         for (int k = 0; k < prevRow.h(); ) {
/* 368 */           v.b cell = prevRow.a(k);
/* 369 */           i cellParams = cell.d().c_();
/* 370 */           if (e.a(cellParams.D) == e.a(this.b.D)) {
/*     */             k++; continue;
/*     */           } 
/* 373 */           breakAvoid = true;
/*     */         } 
/*     */         
/* 376 */         if (breakAvoid) {
/* 377 */           return null;
/*     */         }
/*     */ 
/*     */         
/* 381 */         prevRow.i();
/* 382 */         nextRowGroup = i();
/*     */         break;
/*     */       } 
/* 385 */       nextRowGroup = i();
/* 386 */       prevRowSize -= prevRow.f();
/* 387 */       this.d -= prevRowSize;
/*     */ 
/*     */       
/* 390 */       nextRowGroup.a(nextRow);
/* 391 */       i++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 396 */     if (nextRowGroup == null) {
/* 397 */       if ((flags & 0x1) != 0) {
/* 398 */         return null;
/*     */       }
/* 400 */       return this;
/*     */     } 
/*     */     
/* 403 */     int remove = 0; int j;
/* 404 */     for (j = i; j < this.f.size(); j++) {
/* 405 */       v prevRow = this.f.get(j);
/* 406 */       this.d -= prevRow.f();
/* 407 */       nextRowGroup.a(prevRow);
/* 408 */       remove++;
/*     */     } 
/* 410 */     for (j = 0; j < remove; j++) {
/* 411 */       this.f.remove(this.f.size() - 1);
/*     */     }
/* 413 */     return nextRowGroup;
/*     */   }
/*     */   
/*     */   private w i() {
/* 417 */     w nextRowGroup = new w(this.a, this.e);
/* 418 */     nextRowGroup.a(this.b);
/* 419 */     nextRowGroup.c = this.c;
/* 420 */     return nextRowGroup;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/w.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */