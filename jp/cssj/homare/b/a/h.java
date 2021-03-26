/*     */ package jp.cssj.homare.b.a;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.a.a.k;
/*     */ import jp.cssj.homare.b.a.b.h;
/*     */ import jp.cssj.homare.b.a.b.i;
/*     */ import jp.cssj.homare.b.a.b.n;
/*     */ import jp.cssj.homare.b.a.c.L;
/*     */ import jp.cssj.homare.b.a.c.a;
/*     */ import jp.cssj.homare.b.a.c.c;
/*     */ import jp.cssj.homare.b.a.c.f;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.c.l;
/*     */ import jp.cssj.homare.b.a.c.r;
/*     */ import jp.cssj.homare.b.c.f;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.sakae.c.d.e;
/*     */ import jp.cssj.sakae.c.d.g;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class h
/*     */   extends b
/*     */ {
/*     */   public static class a
/*     */   {
/*     */     public final n a;
/*  54 */     public double b = 0.0D;
/*     */     
/*     */     public a(n box) {
/*  57 */       this.a = box;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  61 */       return this.a.toString();
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
/*  75 */   protected List<Object> i = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   protected jp.cssj.homare.b.f.b j = null;
/*     */   
/*  82 */   protected double k = 0.0D;
/*     */   
/*  84 */   protected double l = 0.0D; private static final boolean a = false; public static final byte d = 1; public static final byte e = 2; public static final byte f = 3; public static final byte g = 4;
/*     */   protected l h;
/*     */   protected double m;
/*     */   
/*     */   public abstract f g();
/*     */   
/*     */   protected final void a(l decoration) {
/*  91 */     byte flags = (byte)((g()).N & 0x7);
/*     */ 
/*     */ 
/*     */     
/*  95 */     if (decoration == null) {
/*  96 */       if (flags == 0) {
/*     */         return;
/*     */       }
/*  99 */       underline = overline = lineThrough = null;
/*     */     } else {
/* 101 */       underline = decoration.a;
/* 102 */       overline = decoration.b;
/* 103 */       lineThrough = decoration.c;
/*     */     } 
/* 105 */     jp.cssj.sakae.c.c.b color = (g()).M;
/* 106 */     jp.cssj.sakae.c.c.b underline = ((flags & 0x1) != 0) ? color : underline;
/* 107 */     jp.cssj.sakae.c.c.b overline = ((flags & 0x2) != 0) ? color : overline;
/* 108 */     jp.cssj.sakae.c.c.b lineThrough = ((flags & 0x4) != 0) ? color : lineThrough;
/* 109 */     this.h = new l(underline, overline, lineThrough);
/*     */   }
/*     */   
/*     */   protected final void a(Object content, byte type) {
/* 113 */     if (!n && type == 1 && !(content instanceof jp.cssj.sakae.c.d.h)) throw new AssertionError(); 
/* 114 */     if (!n && type == 2 && !(content instanceof jp.cssj.sakae.c.d.b.a.a)) throw new AssertionError(); 
/* 115 */     if (!n && type == 3 && !(content instanceof a)) throw new AssertionError(); 
/* 116 */     if (!n && type == 4 && !(content instanceof i)) throw new AssertionError(); 
/* 117 */     if (this.j == null) {
/* 118 */       this.i = new ArrayList();
/* 119 */       this.j = new jp.cssj.homare.b.f.b();
/*     */     } 
/* 121 */     this.i.add(content);
/* 122 */     this.j.a(type);
/*     */   }
/*     */   
/*     */   public final double h() {
/* 126 */     return this.m;
/*     */   }
/*     */   
/*     */   public final double i() {
/* 130 */     return this.k + this.l;
/*     */   }
/*     */   
/*     */   public final double p() {
/* 134 */     if (e.a((g()).D))
/*     */     {
/* 136 */       return i();
/*     */     }
/*     */     
/* 139 */     return this.m;
/*     */   }
/*     */ 
/*     */   
/*     */   public final double q() {
/* 144 */     if (e.a((g()).D))
/*     */     {
/* 146 */       return this.m;
/*     */     }
/*     */     
/* 149 */     return i();
/*     */   }
/*     */ 
/*     */   
/*     */   public double s() {
/* 154 */     return p();
/*     */   }
/*     */   
/*     */   public double t() {
/* 158 */     return q();
/*     */   }
/*     */   
/*     */   public final void a(jp.cssj.sakae.c.d.h text) {
/* 162 */     if (!n && text.l() <= 0) throw new AssertionError(); 
/* 163 */     a(text, (byte)1);
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(jp.cssj.sakae.c.d.b.a.a control) {
/* 168 */     a(control, (byte)2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void a(n box) {
/* 177 */     if (box.a() == 4) {
/* 178 */       if (!n && (b()).al == (box.b()).al) throw new AssertionError((box.b()).al + "\n" + 
/* 179 */             b() + "\n" + box.b()); 
/* 180 */       i inline = (i)box;
/* 181 */       inline.a(this.h);
/*     */     } 
/* 183 */     a(new a(box), (byte)3);
/*     */   }
/*     */   
/*     */   public final void a(i box) {
/* 187 */     a(box, (byte)4);
/*     */   }
/*     */   
/*     */   public final void a(double advance) {
/* 191 */     this.m += advance;
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
/*     */   protected final int a(k state) {
/* 203 */     if (this.j == null) {
/* 204 */       return 0;
/*     */     }
/* 206 */     jp.cssj.sakae.c.d.a.a hyph = (g()).G;
/* 207 */     int count = 0;
/* 208 */     for (int i = 0; i < this.j.b(); i++) {
/* 209 */       jp.cssj.sakae.c.d.h text; int glen; char[] ch; byte[] clens; int m; int j; a content; switch (this.j.a(i)) {
/*     */         
/*     */         case 1:
/* 212 */           text = (jp.cssj.sakae.c.d.h)this.i.get(i);
/* 213 */           glen = text.l();
/* 214 */           if (glen <= 0) {
/*     */             break;
/*     */           }
/* 217 */           ch = text.h();
/* 218 */           clens = text.k();
/* 219 */           m = 0;
/* 220 */           for (j = 0; j < glen; j++) {
/* 221 */             char c1 = ch[m];
/* 222 */             m += clens[j];
/* 223 */             char c2 = ch[m - 1];
/* 224 */             if (state.a != '\000' && hyph.b(state.a, c1)) {
/* 225 */               count++;
/*     */             }
/* 227 */             state.a = c2;
/*     */           } 
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/* 233 */           content = (a)this.i.get(i);
/* 234 */           if (content.a.a() == 4) {
/* 235 */             i inline = (i)content.a;
/* 236 */             count += inline.a(state);
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 2:
/* 241 */           if (i > 0) {
/* 242 */             jp.cssj.sakae.c.d.b.a.a ctrl = (jp.cssj.sakae.c.d.b.a.a)this.i.get(i);
/* 243 */             state.a = ctrl.e();
/*     */           } 
/*     */           break;
/*     */         case 4:
/*     */           break;
/*     */         default:
/* 249 */           throw new IllegalStateException();
/*     */       } 
/*     */     } 
/* 252 */     return count;
/*     */   }
/*     */   
/*     */   protected final void a(double unitSpacing, k state) {
/* 256 */     if (this.j == null) {
/*     */       return;
/*     */     }
/* 259 */     jp.cssj.sakae.c.d.a.a hyph = (g()).G;
/* 260 */     for (int i = 0; i < this.j.b(); i++) {
/* 261 */       jp.cssj.sakae.c.d.h text; a inline; int glen; char[] ch; byte[] clens; double[] xadvances; int m, j; double da = 0.0D;
/* 262 */       switch (this.j.a(i)) {
/*     */         
/*     */         case 1:
/* 265 */           text = (jp.cssj.sakae.c.d.h)this.i.get(i);
/* 266 */           glen = text.l();
/* 267 */           if (glen <= 0) {
/*     */             break;
/*     */           }
/* 270 */           ch = text.h();
/* 271 */           clens = text.k();
/* 272 */           xadvances = text.a(true);
/* 273 */           m = 0;
/* 274 */           for (j = 0; j < glen; j++) {
/* 275 */             char c1 = ch[m];
/* 276 */             m += clens[j];
/* 277 */             char c2 = ch[m - 1];
/* 278 */             if (state.a != '\000' && hyph.b(state.a, c1)) {
/* 279 */               xadvances[j] = xadvances[j] + unitSpacing;
/* 280 */               da += unitSpacing;
/*     */             } 
/* 282 */             state.a = c2;
/*     */           } 
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 3:
/* 289 */           inline = (a)this.i.get(i);
/* 290 */           if (inline.a.a() == 4) {
/* 291 */             i inlineBox = (i)inline.a;
/* 292 */             da = inlineBox.h();
/* 293 */             inlineBox.a(unitSpacing, state);
/* 294 */             da = inlineBox.h() - da;
/*     */           } 
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/* 300 */           if (i > 0) {
/* 301 */             jp.cssj.sakae.c.d.b.a.a ctrl = (jp.cssj.sakae.c.d.b.a.a)this.i.get(i);
/* 302 */             state.a = ctrl.e();
/*     */           } 
/*     */           break;
/*     */         case 4:
/*     */           break;
/*     */         default:
/* 308 */           throw new IllegalStateException();
/*     */       } 
/* 310 */       if (da != 0.0D) {
/* 311 */         a(da);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract boolean j();
/*     */   
/*     */   public void a(m containerBox) {
/* 319 */     if (this.j == null) {
/*     */       return;
/*     */     }
/* 322 */     if (j()) {
/* 323 */       containerBox = (m)this;
/*     */     }
/* 325 */     for (int i = 0; i < this.j.b(); i++) {
/* 326 */       i absoluteBox; a inline; n inlineBox; switch (this.j.a(i)) {
/*     */         case 1:
/*     */         case 2:
/*     */           break;
/*     */ 
/*     */         
/*     */         case 4:
/* 333 */           absoluteBox = (i)this.i.get(i);
/* 334 */           absoluteBox.a(containerBox);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 3:
/* 340 */           inline = (a)this.i.get(i);
/* 341 */           inlineBox = inline.a;
/* 342 */           inlineBox.a(containerBox);
/*     */           break;
/*     */ 
/*     */         
/*     */         default:
/* 347 */           throw new IllegalStateException();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void a(e lineBox, double baseline) {
/* 353 */     if (this.j == null) {
/*     */       return;
/*     */     }
/* 356 */     c lineParams = lineBox.c();
/* 357 */     for (int i = 0; i < this.j.b(); i++) {
/* 358 */       a inline; n inlineBox; r pos; double ascent; double descent; i i1; c box; i params; switch (this.j.a(i)) {
/*     */         case 1:
/*     */         case 2:
/*     */         case 4:
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 3:
/* 367 */           inline = (a)this.i.get(i);
/* 368 */           inlineBox = inline.a;
/* 369 */           pos = inlineBox.c();
/*     */ 
/*     */           
/* 372 */           switch (inlineBox.a()) {
/*     */             
/*     */             case 4:
/* 375 */               i1 = (i)inlineBox;
/* 376 */               ascent = i1.k();
/* 377 */               descent = i1.l();
/*     */               break;
/*     */ 
/*     */             
/*     */             case 5:
/* 382 */               box = (c)inlineBox;
/* 383 */               params = box.c_();
/* 384 */               if (e.a(lineParams.D)) {
/*     */                 
/* 386 */                 if (e.a(params.D)) {
/* 387 */                   descent = box.o();
/* 388 */                   if (e.a(descent)) {
/* 389 */                     descent = inlineBox.p() / 2.0D;
/*     */                   }
/*     */                 } else {
/*     */                   
/* 393 */                   descent = inlineBox.p() / 2.0D;
/*     */                 } 
/* 395 */                 ascent = inlineBox.p() - descent;
/*     */                 break;
/*     */               } 
/* 398 */               if (e.a(params.D)) {
/*     */                 
/* 400 */                 descent = 0.0D;
/*     */               } else {
/* 402 */                 descent = box.o();
/* 403 */                 if (e.a(descent)) {
/* 404 */                   descent = 0.0D;
/*     */                 }
/*     */               } 
/* 407 */               ascent = inlineBox.q() - descent;
/*     */               break;
/*     */ 
/*     */ 
/*     */             
/*     */             case 6:
/* 413 */               if (e.a(lineParams.D))
/*     */               {
/* 415 */                 ascent = descent = inlineBox.p() / 2.0D;
/*     */               }
/*     */               
/* 418 */               descent = 0.0D;
/* 419 */               ascent = inlineBox.q();
/*     */               break;
/*     */ 
/*     */             
/*     */             default:
/* 424 */               throw new IllegalStateException();
/*     */           } 
/* 426 */           inline.b = pos.a.a(this, lineBox, ascent, descent, pos.b, baseline);
/*     */           
/* 428 */           if (inlineBox.a() == 4) {
/* 429 */             ((i)inlineBox).a(lineBox, baseline + inline.b);
/*     */           }
/*     */           break;
/*     */ 
/*     */         
/*     */         default:
/* 435 */           throw new IllegalStateException();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected static class c extends jp.cssj.homare.b.c.b { protected final List<Object> a;
/*     */     protected final int b;
/*     */     protected final int c;
/*     */     protected final f d;
/*     */     protected final double e;
/*     */     protected final double f;
/*     */     
/*     */     public c(n pageBox, Shape clip, AffineTransform transform, List<Object> contents, int off, int len, f params, double ascent, double descent) {
/* 448 */       super(pageBox, clip, params.ao, transform);
/* 449 */       this.a = contents;
/* 450 */       this.b = off;
/* 451 */       this.c = len;
/* 452 */       this.d = params;
/* 453 */       this.e = ascent;
/* 454 */       this.f = descent;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(jp.cssj.sakae.c.b gc, double x, double y) throws jp.cssj.sakae.c.c {
/* 459 */       if (this.d.R != null) {
/* 460 */         for (int i = this.d.R.length - 1; i >= 0; i--) {
/* 461 */           L shadow = this.d.R[i];
/* 462 */           gc.d();
/* 463 */           gc.b(shadow.c);
/* 464 */           c(gc, x + shadow.a, y + shadow.b);
/* 465 */           gc.e();
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 470 */       gc.d();
/* 471 */       jp.cssj.sakae.c.c.b color = this.d.M;
/* 472 */       if (color != null) {
/* 473 */         gc.b(color);
/*     */       }
/* 475 */       if (this.d.P != 0.0D) {
/* 476 */         gc.a(jp.cssj.sakae.c.b.t);
/* 477 */         gc.a(this.d.P);
/* 478 */         gc.a(this.d.Q);
/* 479 */         gc.c((short)2);
/*     */       } 
/* 481 */       c(gc, x, y);
/* 482 */       gc.e();
/*     */     }
/*     */     
/*     */     private void c(jp.cssj.sakae.c.b gc, double x, double y) {
/* 486 */       double xx = x, yy = y;
/* 487 */       if (e.a(this.d.D)) {
/*     */         
/* 489 */         for (int i = 0; i < this.c; i++) {
/* 490 */           jp.cssj.sakae.c.d.h text = (jp.cssj.sakae.c.d.h)this.a.get(i + this.b);
/* 491 */           if (text.d().b() == jp.cssj.sakae.pdf.c.a.d.b.j) {
/* 492 */             this.h.u().a((short)10271, new String(text
/* 493 */                   .h(), 0, text.i()));
/*     */           }
/* 495 */           gc.a(text, x + this.f, y);
/* 496 */           y += text.c();
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 506 */         for (int i = 0; i < this.c; i++) {
/* 507 */           jp.cssj.sakae.c.d.h text = (jp.cssj.sakae.c.d.h)this.a.get(i + this.b);
/* 508 */           if (text.d().b() == jp.cssj.sakae.pdf.c.a.d.b.i) {
/* 509 */             this.h.u().a((short)10271, new String(text
/* 510 */                   .h(), 0, text.i()));
/*     */           }
/* 512 */           gc.a(text, x, y + this.e);
/* 513 */           x += text.c();
/*     */         } 
/*     */       } 
/*     */     } }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static class b
/*     */     extends jp.cssj.homare.b.c.b
/*     */   {
/*     */     protected final f a;
/*     */     
/*     */     protected final l b;
/*     */     
/*     */     protected final double c;
/*     */     protected final double d;
/*     */     protected final double e;
/*     */     protected final double f;
/*     */     
/*     */     public b(n pageBox, Shape clip, AffineTransform transform, f params, l decoration, double ascent, double descent, double width, double height) {
/* 533 */       super(pageBox, clip, params.ao, transform);
/* 534 */       this.a = params;
/* 535 */       this.b = decoration;
/* 536 */       this.c = ascent;
/* 537 */       this.d = descent;
/* 538 */       this.e = width;
/* 539 */       this.f = height;
/*     */     }
/*     */     
/*     */     public void a(jp.cssj.sakae.c.b gc, double x, double y) throws jp.cssj.sakae.c.c {
/* 543 */       gc.d();
/*     */       
/* 545 */       jp.cssj.sakae.c.c.b color = this.a.M;
/* 546 */       if (color != null) {
/* 547 */         gc.a(color);
/* 548 */         gc.b(color);
/*     */       } 
/*     */ 
/*     */       
/* 552 */       double fontSize = this.a.C.e();
/* 553 */       double strokeSize = fontSize * this.a.O;
/* 554 */       gc.a(strokeSize);
/* 555 */       if (e.a(this.a.D)) {
/*     */         
/* 557 */         x += this.d;
/* 558 */         double lineAxis = this.f;
/* 559 */         if (this.b.a != null) {
/*     */           
/* 561 */           gc.a(this.b.a);
/* 562 */           double lineX = x - this.a.a().d();
/* 563 */           Line2D line = new Line2D.Double(lineX, y, lineX, y + lineAxis);
/* 564 */           gc.c(line);
/*     */         } 
/*     */         
/* 567 */         if (this.b.b != null) {
/*     */           
/* 569 */           gc.a(this.b.b);
/* 570 */           double lineX = x + this.a.a().c();
/* 571 */           Line2D line = new Line2D.Double(lineX, y, lineX, y + lineAxis);
/* 572 */           gc.c(line);
/*     */         } 
/* 574 */         if (this.b.c != null) {
/*     */           
/* 576 */           gc.a(this.b.c);
/* 577 */           Line2D line = new Line2D.Double(x, y, x, y + lineAxis);
/* 578 */           gc.c(line);
/*     */         } 
/*     */       } else {
/*     */         
/* 582 */         y += this.c;
/* 583 */         double lineAxis = this.e;
/* 584 */         if (this.b.a != null) {
/*     */           
/* 586 */           gc.a(this.b.a);
/* 587 */           double descent = this.a.a().d();
/* 588 */           double lineY = y + descent;
/*     */           
/* 590 */           lineY = Math.min(y + this.d - strokeSize, lineY);
/* 591 */           Line2D line = new Line2D.Double(x, lineY, x + lineAxis, lineY);
/* 592 */           gc.c(line);
/*     */         } 
/* 594 */         if (this.b.b != null) {
/*     */           
/* 596 */           gc.a(this.b.b);
/* 597 */           double ascent = this.a.a().c();
/* 598 */           double lineY = y - ascent;
/*     */           
/* 600 */           lineY = Math.max(y - this.c + strokeSize, lineY);
/* 601 */           Line2D line = new Line2D.Double(x, lineY, x + lineAxis, lineY);
/* 602 */           gc.c(line);
/*     */         } 
/* 604 */         if (this.b.c != null) {
/*     */           
/* 606 */           gc.a(this.b.c);
/* 607 */           double xHeight = this.a.a().e();
/* 608 */           double lineY = y - xHeight / 2.0D;
/* 609 */           Line2D line = new Line2D.Double(x, lineY, x + lineAxis, lineY);
/* 610 */           gc.c(line);
/*     */         } 
/*     */       } 
/*     */       
/* 614 */       gc.e();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private final f a(n pageBox, Shape clip, AffineTransform transform, int off, int len) {
/* 620 */     f params = g();
/* 621 */     return (f)new c(pageBox, clip, transform, this.i, off, len, params, this.k, this.l);
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(StringBuffer textBuff) {
/* 626 */     if (this.j != null && !this.j.d()) {
/* 627 */       for (int i = 0; i < this.j.b(); i++) {
/* 628 */         jp.cssj.sakae.c.d.h text; a inline; i absoluteBox; jp.cssj.sakae.c.d.b.a.a control; switch (this.j.a(i)) {
/*     */           case 1:
/* 630 */             text = (jp.cssj.sakae.c.d.h)this.i.get(i);
/* 631 */             textBuff.append(text.h(), 0, text.i());
/*     */             break;
/*     */ 
/*     */           
/*     */           case 3:
/* 636 */             inline = (a)this.i.get(i);
/* 637 */             inline.a.a(textBuff);
/*     */             break;
/*     */ 
/*     */           
/*     */           case 4:
/* 642 */             absoluteBox = (i)this.i.get(i);
/* 643 */             absoluteBox.a(textBuff);
/*     */             break;
/*     */ 
/*     */ 
/*     */           
/*     */           case 2:
/* 649 */             control = (jp.cssj.sakae.c.d.b.a.a)this.i.get(i);
/* 650 */             textBuff.append(control.e());
/*     */             break;
/*     */ 
/*     */           
/*     */           default:
/* 655 */             throw new IllegalStateException();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/* 663 */     if (this.j == null || this.j.d()) {
/*     */       return;
/*     */     }
/* 666 */     int off = 0;
/* 667 */     int len = 0;
/* 668 */     double xx = x, yy = y;
/* 669 */     double tx = 0.0D, ty = 0.0D;
/*     */     
/* 671 */     boolean decoration = false;
/* 672 */     double dx = 0.0D, dy = 0.0D;
/* 673 */     f lineParams = g();
/* 674 */     boolean vertical = e.a(lineParams.D);
/*     */     
/* 676 */     for (int i = 0; i < this.j.b(); i++) {
/* 677 */       jp.cssj.sakae.c.d.h text; a inline; double xxx; jp.cssj.sakae.c.d.b.a.a control; n inlineBox; double ascent; double yyy; i box; double descent; double voffset; i absoluteBox; a pos; c c; i params; switch (this.j.a(i)) {
/*     */         
/*     */         case 1:
/* 680 */           if (len == 0) {
/* 681 */             off = i;
/* 682 */             tx = xx;
/* 683 */             ty = yy;
/*     */           } 
/* 685 */           if (!decoration) {
/* 686 */             dx = xx;
/* 687 */             dy = yy;
/* 688 */             decoration = true;
/*     */           } 
/* 690 */           text = (jp.cssj.sakae.c.d.h)this.i.get(i);
/* 691 */           len++;
/* 692 */           if (vertical) {
/*     */             
/* 694 */             yy += text.c();
/*     */             break;
/*     */           } 
/* 697 */           xx += text.c();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 3:
/* 704 */           if (lineParams.ao != 0.0F && len > 0) {
/* 705 */             drawer.a(a(pageBox, clip, transform, off, len), tx, ty);
/* 706 */             len = 0;
/*     */           } 
/* 708 */           if (decoration) {
/*     */             
/* 710 */             if (this.h != null) {
/* 711 */               double width = xx - dx;
/* 712 */               double height = yy - dy;
/* 713 */               if ((vertical && height > 0.0D) || (!vertical && width > 0.0D)) {
/* 714 */                 b b1 = new b(pageBox, clip, transform, lineParams, this.h, this.k, this.l, width, height);
/*     */                 
/* 716 */                 drawer.a((f)b1, dx, dy);
/*     */               } 
/*     */             } 
/* 719 */             decoration = false;
/*     */           } 
/* 721 */           inline = (a)this.i.get(i);
/* 722 */           inlineBox = inline.a;
/*     */           
/* 724 */           switch (inlineBox.a()) {
/*     */             
/*     */             case 4:
/* 727 */               box = (i)inlineBox;
/* 728 */               ascent = box.k();
/*     */               break;
/*     */ 
/*     */ 
/*     */             
/*     */             case 5:
/* 734 */               c = (c)inlineBox;
/* 735 */               params = c.c_();
/* 736 */               if (vertical) {
/*     */                 
/* 738 */                 if (params.D == 2 || params.D == 3) {
/* 739 */                   descent = c.o();
/* 740 */                   if (e.a(descent)) {
/* 741 */                     descent = inlineBox.p() / 2.0D;
/*     */                   }
/*     */                 } else {
/*     */                   
/* 745 */                   descent = inlineBox.p() / 2.0D;
/*     */                 } 
/* 747 */                 ascent = inlineBox.p() - descent;
/*     */                 break;
/*     */               } 
/* 750 */               if (params.D == 1) {
/* 751 */                 descent = c.o();
/* 752 */                 if (e.a(descent)) {
/* 753 */                   descent = 0.0D;
/*     */                 }
/*     */               } else {
/*     */                 
/* 757 */                 descent = 0.0D;
/*     */               } 
/* 759 */               ascent = inlineBox.q() - descent;
/*     */               break;
/*     */ 
/*     */ 
/*     */             
/*     */             case 6:
/* 765 */               if (vertical) {
/*     */                 
/* 767 */                 ascent = inlineBox.p() / 2.0D;
/*     */                 break;
/*     */               } 
/* 770 */               ascent = inlineBox.q();
/*     */               break;
/*     */ 
/*     */             
/*     */             default:
/* 775 */               throw new IllegalStateException();
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 781 */           voffset = ascent - this.k;
/*     */ 
/*     */           
/* 784 */           if (vertical) {
/*     */             
/* 786 */             voffset += p() - inlineBox.p();
/* 787 */             inlineBox.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, xx + voffset + inline.b, yy);
/*     */             
/* 789 */             yy += inlineBox.q();
/*     */             break;
/*     */           } 
/* 792 */           inlineBox.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, xx, yy - voffset - inline.b);
/*     */           
/* 794 */           xx += inlineBox.p();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 4:
/* 801 */           if (lineParams.ao != 0.0F && len > 0) {
/* 802 */             drawer.a(a(pageBox, clip, transform, off, len), tx, ty);
/* 803 */             len = 0;
/*     */           } 
/*     */           
/* 806 */           absoluteBox = (i)this.i.get(i);
/* 807 */           pos = absoluteBox.c();
/* 808 */           if (pos.a.d() != 3 || pos.a.b() != 3) {
/* 809 */             xxx = contextX;
/*     */           } else {
/* 811 */             xxx = xx;
/*     */           } 
/* 813 */           if (pos.a.a() != 3 || pos.a.c() != 3) {
/* 814 */             yyy = contextY;
/*     */           } else {
/* 816 */             yyy = yy;
/*     */           } 
/* 818 */           absoluteBox.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, xxx, yyy);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 824 */           if (lineParams.ao != 0.0F && len > 0) {
/* 825 */             drawer.a(a(pageBox, clip, transform, off, len), tx, ty);
/* 826 */             len = 0;
/*     */           } 
/* 828 */           if (!decoration) {
/* 829 */             dx = xx;
/* 830 */             dy = yy;
/* 831 */             decoration = true;
/*     */           } 
/* 833 */           control = (jp.cssj.sakae.c.d.b.a.a)this.i.get(i);
/* 834 */           if (vertical) {
/*     */             
/* 836 */             yy += control.c();
/*     */             break;
/*     */           } 
/* 839 */           xx += control.c();
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 845 */           throw new IllegalStateException();
/*     */       } 
/*     */     } 
/* 848 */     if (lineParams.ao != 0.0F && len > 0) {
/* 849 */       drawer.a(a(pageBox, clip, transform, off, len), tx, ty);
/* 850 */       len = 0;
/*     */     } 
/* 852 */     if (decoration && this.h != null) {
/* 853 */       double width = xx - dx;
/* 854 */       double height = yy - dy;
/* 855 */       if ((vertical && height > 0.0D) || (!vertical && width > 0.0D)) {
/* 856 */         b b1 = new b(pageBox, clip, transform, lineParams, this.h, this.k, this.l, width, height);
/*     */         
/* 858 */         drawer.a((f)b1, dx, dy);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final double k() {
/* 864 */     return this.k;
/*     */   }
/*     */   
/*     */   public final double l() {
/* 868 */     return this.l;
/*     */   }
/*     */   
/*     */   public void a(e gh, boolean widow) {
/* 872 */     if (this.j == null) {
/*     */       return;
/*     */     }
/* 875 */     for (int i = 0; i < this.j.b(); i++) {
/*     */       jp.cssj.sakae.c.d.h text; a content; i absoluteBox; jp.cssj.sakae.c.d.b.a.a control; i i1; f replacedBox; h inlineBox; jp.cssj.homare.b.b.b quad; jp.cssj.homare.b.b.b b1;
/* 877 */       switch (this.j.a(i)) {
/*     */         
/*     */         case 1:
/* 880 */           text = (jp.cssj.sakae.c.d.h)this.i.get(i);
/* 881 */           if (!n && text.l() <= 0) throw new AssertionError(); 
/* 882 */           text.a(gh);
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 3:
/* 888 */           content = (a)this.i.get(i);
/* 889 */           switch (content.a.a()) {
/*     */             case 4:
/* 891 */               i1 = (i)content.a;
/* 892 */               i1.a(gh, (widow && i == 0));
/*     */               break;
/*     */ 
/*     */             
/*     */             case 6:
/* 897 */               replacedBox = (f)content.a;
/* 898 */               b1 = jp.cssj.homare.b.b.b.a(replacedBox);
/* 899 */               gh.a((g)b1);
/*     */               break;
/*     */ 
/*     */             
/*     */             case 5:
/* 904 */               inlineBox = (h)content.a;
/* 905 */               b1 = jp.cssj.homare.b.b.b.a(inlineBox);
/* 906 */               gh.a((g)b1);
/*     */               break;
/*     */           } 
/*     */ 
/*     */           
/* 911 */           throw new IllegalStateException();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 4:
/* 917 */           absoluteBox = (i)this.i.get(i);
/* 918 */           quad = jp.cssj.homare.b.b.b.a(absoluteBox);
/* 919 */           gh.a((g)quad);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/* 924 */           control = (jp.cssj.sakae.c.d.b.a.a)this.i.get(i);
/* 925 */           gh.a((g)control);
/*     */           break;
/*     */ 
/*     */         
/*     */         default:
/* 930 */           throw new IllegalStateException();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final Object a(int ix) {
/* 936 */     return this.i.get(ix);
/*     */   }
/*     */   
/*     */   public final byte b(int ix) {
/* 940 */     return this.j.a(ix);
/*     */   }
/*     */   
/*     */   public final int n() {
/* 944 */     if (this.j == null) {
/* 945 */       return 0;
/*     */     }
/* 947 */     return this.j.b();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 951 */     StringBuffer buff = new StringBuffer();
/* 952 */     if (this.i != null) {
/* 953 */       for (int i = 0; i < this.i.size(); i++) {
/* 954 */         buff.append(this.i.get(i));
/*     */       }
/*     */     }
/* 957 */     return buff.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */