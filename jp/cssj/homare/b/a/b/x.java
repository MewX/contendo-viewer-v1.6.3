/*     */ package jp.cssj.homare.b.a.b;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.a.a.d;
/*     */ import jp.cssj.homare.b.a.b;
/*     */ import jp.cssj.homare.b.a.c.K;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.c.y;
/*     */ import jp.cssj.homare.b.a.c.z;
/*     */ import jp.cssj.homare.b.a.e;
/*     */ import jp.cssj.homare.b.a.j;
/*     */ import jp.cssj.homare.b.a.l;
/*     */ import jp.cssj.homare.b.a.m;
/*     */ import jp.cssj.homare.b.a.p;
/*     */ import jp.cssj.homare.b.b.a.c;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.homare.impl.a.a.c;
/*     */ import jp.cssj.sakae.c.d.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class x
/*     */   extends b
/*     */   implements l, p
/*     */ {
/*     */   protected static class a
/*     */   {
/*     */     public final e a;
/*     */     public final double b;
/*     */     
/*     */     public a(e line, double pageAxis) {
/*  53 */       this.a = line;
/*  54 */       this.b = pageAxis;
/*     */     }
/*     */     
/*     */     public double a() {
/*  58 */       return this.b + this.a.k() + this.a.l();
/*     */     }
/*     */     
/*     */     public String toString() {
/*  62 */       return this.a.toString();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   protected final List<a> b = new ArrayList<>();
/*     */   
/*  73 */   protected double c = 0.0D;
/*     */   
/*     */   private static final boolean f = false;
/*     */   
/*     */   protected final i a;
/*     */   protected final byte d;
/*     */   
/*     */   public x(i params, byte textState) {
/*  81 */     this.a = params;
/*  82 */     this.d = textState;
/*     */   }
/*     */   
/*     */   public final byte a() {
/*  86 */     return 2;
/*     */   }
/*     */   
/*     */   public final y b() {
/*  90 */     return (y)this.a;
/*     */   }
/*     */   
/*     */   public final i g() {
/*  94 */     return this.a;
/*     */   }
/*     */   
/*     */   public final z b_() {
/*  98 */     return (z)K.a;
/*     */   }
/*     */   
/*     */   public final double h() {
/* 102 */     double ascent = 0.0D;
/* 103 */     if (this.b != null && !this.b.isEmpty()) {
/* 104 */       a line = this.b.get(0);
/* 105 */       ascent += line.a.k();
/*     */     } 
/* 107 */     return ascent;
/*     */   }
/*     */   
/*     */   public final double i() {
/* 111 */     double descent = 0.0D;
/* 112 */     if (this.b != null && !this.b.isEmpty()) {
/* 113 */       a line = this.b.get(this.b.size() - 1);
/* 114 */       descent += line.a.l();
/*     */     } 
/* 116 */     return descent;
/*     */   }
/*     */   
/*     */   public final double j() {
/* 120 */     return this.c;
/*     */   }
/*     */   
/*     */   public final double k() {
/* 124 */     a line = this.b.get(this.b.size() - 1);
/* 125 */     return line.a();
/*     */   }
/*     */   
/*     */   public final double p() {
/* 129 */     if (e.a(this.a.D))
/*     */     {
/* 131 */       return k();
/*     */     }
/*     */     
/* 134 */     return this.c;
/*     */   }
/*     */ 
/*     */   
/*     */   public final double q() {
/* 139 */     if (e.a(this.a.D))
/*     */     {
/* 141 */       return this.c;
/*     */     }
/*     */     
/* 144 */     return k();
/*     */   }
/*     */ 
/*     */   
/*     */   public final double s() {
/* 149 */     return p();
/*     */   }
/*     */   
/*     */   public final double t() {
/* 153 */     return q();
/*     */   }
/*     */   
/*     */   public final void a(e lineBox, double pageAxis) {
/* 157 */     if (!e && e.a(pageAxis)) throw new AssertionError(); 
/* 158 */     this.b.add(new a(lineBox, pageAxis));
/*     */     
/* 160 */     this.c = Math.max(lineBox.h(), this.c);
/*     */   }
/*     */   
/*     */   public final void a(m containerBox) {
/* 164 */     for (int j = 0; j < this.b.size(); j++) {
/* 165 */       a line = this.b.get(j);
/* 166 */       line.a.a(containerBox);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void a(StringBuffer textBuff) {
/* 171 */     for (int j = 0; j < this.b.size(); j++) {
/* 172 */       a line = this.b.get(j);
/* 173 */       line.a.a(textBuff);
/*     */     } 
/*     */   }
/*     */   
/*     */   public final double a(double pageAxis) {
/* 178 */     if (this.b.isEmpty()) {
/* 179 */       return pageAxis;
/*     */     }
/* 181 */     if (e.a((g()).D)) {
/*     */       
/* 183 */       for (int j = 0; j < this.b.size(); j++) {
/* 184 */         a line = this.b.get(j);
/* 185 */         double bottom = line.b + line.a.p();
/* 186 */         if (e.a(bottom, pageAxis) >= 0) {
/* 187 */           pageAxis = bottom;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } else {
/* 193 */       for (int j = 0; j < this.b.size(); j++) {
/* 194 */         a line = this.b.get(j);
/* 195 */         double bottom = line.b + line.a.q();
/* 196 */         if (e.a(bottom, pageAxis) >= 0) {
/* 197 */           pageAxis = bottom;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 203 */     return pageAxis;
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double d1, double y) {
/* 208 */     if (!e && e.a(d1)) throw new AssertionError(); 
/* 209 */     if (!e && e.a(y)) throw new AssertionError(); 
/* 210 */     visitor.a(transform, (j)this, d1, y);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 216 */     for (int j = 0; j < this.b.size(); j++) {
/* 217 */       a line = this.b.get(j);
/* 218 */       e lineBox = line.a;
/*     */ 
/*     */       
/* 221 */       if (e.a(this.a.D)) {
/*     */         
/* 223 */         lineBox.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, d1 + 
/* 224 */             k() - line.a(), y);
/*     */       } else {
/*     */         
/* 227 */         lineBox.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, d1, y + line.b);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public final p b(double pageLimit, d mode, byte flags) {
/*     */     double pageSize;
/* 233 */     if (!e && this.b.isEmpty()) throw new AssertionError();
/*     */ 
/*     */ 
/*     */     
/* 237 */     if (!e && mode.a() == 1) throw new AssertionError();
/*     */ 
/*     */ 
/*     */     
/* 241 */     boolean vertical = e.a(this.a.D);
/*     */     
/* 243 */     if (vertical) {
/* 244 */       pageSize = p();
/*     */     } else {
/* 246 */       pageSize = q();
/*     */     } 
/* 248 */     if (e.a(pageLimit, pageSize) >= 0)
/*     */     {
/* 250 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 254 */     int nonZeroLines = 0;
/* 255 */     for (int j = 0; j < this.b.size(); j++) {
/* 256 */       a a = this.b.get(j);
/* 257 */       if ((a.b > 0.0D || a.a.i() > 0.0D) && 
/* 258 */         ++nonZeroLines >= 2) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/* 263 */     if (nonZeroLines >= 2) {
/* 264 */       if ((flags & 0x1) == 0) {
/* 265 */         a a = this.b.get(0);
/* 266 */         if (e.a(pageLimit, a.a()) < 0)
/*     */         {
/* 268 */           return this;
/*     */         }
/*     */       } 
/*     */     } else {
/*     */       
/* 273 */       if ((flags & 0x1) == 0) {
/* 274 */         return this;
/*     */       }
/* 276 */       return null;
/*     */     } 
/*     */     
/*     */     int lastOrphan;
/*     */     
/* 281 */     for (lastOrphan = this.b.size() - 1; lastOrphan > 0; lastOrphan--) {
/* 282 */       a a = this.b.get(lastOrphan);
/* 283 */       if (e.a(pageLimit, a.a()) >= 0) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 294 */     while (lastOrphan >= 0) {
/* 295 */       a a = this.b.get(lastOrphan);
/* 296 */       double virHeight = pageSize - a.a();
/* 297 */       int virWidows = (int)Math.round(virHeight / this.a.i);
/* 298 */       if (virWidows >= this.a.W) {
/*     */         break;
/*     */       }
/* 301 */       lastOrphan--;
/*     */     } 
/* 303 */     if (lastOrphan == -1) {
/* 304 */       if ((flags & 0x1) == 0) {
/* 305 */         return this;
/*     */       }
/* 307 */       lastOrphan = 0;
/*     */     } 
/* 309 */     if ((flags & 0x1) == 0) {
/*     */       
/* 311 */       a a = this.b.get(lastOrphan);
/* 312 */       int virOrphans = (int)Math.round(a.a() / this.a.i);
/* 313 */       if (virOrphans < this.a.V) {
/* 314 */         return this;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 319 */     int firstWidow = lastOrphan + 1;
/* 320 */     double top = ((a)this.b.get(firstWidow)).b;
/* 321 */     byte textState = 1;
/*     */     
/* 323 */     a line = this.b.get(lastOrphan);
/* 324 */     if (!line.a.f()) {
/* 325 */       textState = (byte)(textState | 0x2);
/*     */     }
/*     */     
/* 328 */     x nextTextBlock = new x(this.a, textState);
/* 329 */     for (int k = firstWidow; k < this.b.size(); k++) {
/* 330 */       a a = this.b.get(k);
/* 331 */       nextTextBlock.a(a.a, a.b - top);
/*     */     } 
/* 333 */     while (this.b.size() > firstWidow) {
/* 334 */       this.b.remove(this.b.size() - 1);
/*     */     }
/*     */     
/* 337 */     if (!e && this.b.isEmpty()) throw new AssertionError(mode); 
/* 338 */     if (!e && nextTextBlock.b.isEmpty()) throw new AssertionError();
/*     */ 
/*     */     
/* 341 */     return nextTextBlock;
/*     */   }
/*     */   
/*     */   public final int l() {
/* 345 */     return this.b.size();
/*     */   }
/*     */   
/*     */   public final void a(jp.cssj.homare.b.b.a.a builder) {
/* 349 */     if (!e && this.b.isEmpty()) throw new AssertionError(); 
/* 350 */     builder.a(this.d);
/* 351 */     c c = new c((jp.cssj.homare.b.b.a)builder);
/* 352 */     c c1 = new c(this.a.G);
/* 353 */     c1.a((e)c);
/*     */     
/* 355 */     for (int j = 0; j < this.b.size(); j++) {
/* 356 */       a line = this.b.get(j);
/* 357 */       line.a.a((e)c1, (j == 0));
/*     */     } 
/*     */     
/* 360 */     c1.b();
/*     */   }
/*     */   
/*     */   public final boolean f() {
/* 364 */     return false;
/*     */   }
/*     */   
/*     */   public final boolean c() {
/* 368 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 372 */     return super.toString() + "/lineCount=" + this.b.size();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/x.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */