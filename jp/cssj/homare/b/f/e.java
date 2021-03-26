/*     */ package jp.cssj.homare.b.f;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import jp.cssj.homare.b.a.b.r;
/*     */ import jp.cssj.homare.b.a.c;
/*     */ import jp.cssj.homare.b.a.c.G;
/*     */ import jp.cssj.homare.b.a.c.i;
/*     */ import jp.cssj.homare.b.a.c.m;
/*     */ import jp.cssj.homare.b.a.c.t;
/*     */ import jp.cssj.homare.b.a.c.u;
/*     */ import jp.cssj.homare.b.a.c.w;
/*     */ import jp.cssj.homare.b.a.f;
/*     */ import jp.cssj.homare.b.a.j;
/*     */ import jp.cssj.homare.b.b.a;
/*     */ import jp.cssj.homare.b.d.b;
/*     */ import jp.cssj.homare.b.e.a;
/*     */ import jp.cssj.homare.css.e.l;
/*     */ import jp.cssj.homare.css.f.a;
/*     */ import jp.cssj.homare.ua.a.B;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.c.a.c;
/*     */ import jp.cssj.sakae.c.a.g;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.d.a.b;
/*     */ import jp.cssj.sakae.c.d.b.b;
/*     */ import jp.cssj.sakae.c.d.j;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class e
/*     */ {
/*     */   public static final double a = 1.722773839210782E308D;
/*     */   public static final double b = 0.5D;
/*     */   
/*     */   public static final boolean a(double v) {
/*  49 */     return (v == 1.722773839210782E308D);
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
/*     */   public static int a(double a, double b) {
/*  68 */     double diff = a - b;
/*  69 */     if (diff < 0.5D && diff > -0.5D) {
/*  70 */       return 0;
/*     */     }
/*  72 */     return (a < b) ? -1 : 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final boolean a(c containerBox, c blockBox) {
/*  83 */     if (blockBox.b_().a() == 7) {
/*  84 */       if (a((containerBox.c_()).D))
/*     */       {
/*  86 */         return ((blockBox.c_()).X.b() == 1);
/*     */       }
/*     */       
/*  89 */       return ((blockBox.c_()).X.a() == 1);
/*     */     } 
/*     */     
/*  92 */     if (a((containerBox.c_()).D))
/*     */     {
/*  94 */       return ((blockBox.c_()).X.b() != 3);
/*     */     }
/*     */     
/*  97 */     return ((blockBox.c_()).X.a() != 3);
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
/*     */   public static final boolean a(c containerBox, f replacedBox) {
/* 110 */     if (replacedBox.b_().a() == 7) {
/* 111 */       if (a((containerBox.c_()).D))
/*     */       {
/* 113 */         return ((replacedBox.d_()).b.b() == 1);
/*     */       }
/*     */       
/* 116 */       return ((replacedBox.d_()).b.a() == 1);
/*     */     } 
/*     */     
/* 119 */     if (a((containerBox.c_()).D))
/*     */     {
/* 121 */       return ((replacedBox.d_()).b.b() != 3);
/*     */     }
/*     */     
/* 124 */     return ((replacedBox.d_()).b.a() != 3);
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
/*     */   public static void a(b gc, g fontPolicy, double fontSize, String text, double x, double y, double width) throws c {
/* 141 */     if (!c && a(x)) throw new AssertionError(); 
/* 142 */     if (!c && a(y)) throw new AssertionError(); 
/* 143 */     if (!c && a(width)) throw new AssertionError(); 
/* 144 */     gc.d();
/* 145 */     gc.a(AffineTransform.getTranslateInstance(x, y));
/*     */     
/* 147 */     b lineHandler = new b();
/* 148 */     lineHandler.a(gc);
/* 149 */     lineHandler.e(width);
/*     */     
/* 151 */     j tlf = new j(gc, b.a(null), (jp.cssj.sakae.c.d.e)lineHandler);
/* 152 */     tlf.a(c.f);
/* 153 */     tlf.a(fontPolicy);
/* 154 */     tlf.a(fontSize);
/* 155 */     tlf.a(text);
/* 156 */     tlf.a();
/*     */     
/* 158 */     lineHandler.q();
/* 159 */     gc.e();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double a(u length, double ref) {
/* 170 */     switch (length.a()) {
/*     */       case 2:
/* 172 */         return length.b() * ref;
/*     */       case 1:
/* 174 */         return length.b();
/*     */       case 3:
/* 176 */         return 1.722773839210782E308D;
/*     */     } 
/* 178 */     throw new IllegalStateException();
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
/*     */   public static void a(a ainsets, t insets, double refSize) {
/*     */     double top, right, bottom, left;
/* 191 */     switch (insets.a()) {
/*     */       case 1:
/* 193 */         top = insets.f();
/*     */         break;
/*     */       case 2:
/* 196 */         top = insets.f() * refSize;
/*     */         break;
/*     */       case 3:
/* 199 */         top = 0.0D;
/*     */         break;
/*     */       default:
/* 202 */         throw new IllegalStateException();
/*     */     } 
/*     */     
/* 205 */     switch (insets.b()) {
/*     */       case 1:
/* 207 */         right = insets.g();
/*     */         break;
/*     */       case 2:
/* 210 */         right = insets.g() * refSize;
/*     */         break;
/*     */       case 3:
/* 213 */         right = 0.0D;
/*     */         break;
/*     */       default:
/* 216 */         throw new IllegalStateException();
/*     */     } 
/*     */     
/* 219 */     switch (insets.c()) {
/*     */       case 1:
/* 221 */         bottom = insets.h();
/*     */         break;
/*     */       case 2:
/* 224 */         bottom = insets.h() * refSize;
/*     */         break;
/*     */       case 3:
/* 227 */         bottom = 0.0D;
/*     */         break;
/*     */       default:
/* 230 */         throw new IllegalStateException();
/*     */     } 
/*     */     
/* 233 */     switch (insets.d()) {
/*     */       case 1:
/* 235 */         left = insets.i();
/*     */         break;
/*     */       case 2:
/* 238 */         left = insets.i() * refSize;
/*     */         break;
/*     */       case 3:
/* 241 */         left = 0.0D;
/*     */         break;
/*     */       default:
/* 244 */         throw new IllegalStateException();
/*     */     } 
/* 246 */     ainsets.a = top;
/* 247 */     ainsets.b = right;
/* 248 */     ainsets.c = bottom;
/* 249 */     ainsets.d = left;
/*     */   }
/*     */   
/*     */   public static void b(a ainsets, t insets, double refSize) {
/*     */     double top, right, bottom, left;
/* 254 */     switch (insets.a()) {
/*     */       case 1:
/* 256 */         top = insets.f();
/*     */         break;
/*     */       case 2:
/* 259 */         top = insets.f() * refSize;
/*     */         break;
/*     */       default:
/* 262 */         throw new IllegalStateException();
/*     */     } 
/*     */     
/* 265 */     switch (insets.b()) {
/*     */       case 1:
/* 267 */         right = insets.g();
/*     */         break;
/*     */       case 2:
/* 270 */         right = insets.g() * refSize;
/*     */         break;
/*     */       default:
/* 273 */         throw new IllegalStateException();
/*     */     } 
/*     */     
/* 276 */     switch (insets.c()) {
/*     */       case 1:
/* 278 */         bottom = insets.h();
/*     */         break;
/*     */       case 2:
/* 281 */         bottom = insets.h() * refSize;
/*     */         break;
/*     */       default:
/* 284 */         throw new IllegalStateException();
/*     */     } 
/*     */     
/* 287 */     switch (insets.d()) {
/*     */       case 1:
/* 289 */         left = insets.i();
/*     */         break;
/*     */       case 2:
/* 292 */         left = insets.i() * refSize;
/*     */         break;
/*     */       default:
/* 295 */         throw new IllegalStateException();
/*     */     } 
/* 297 */     ainsets.a = top;
/* 298 */     ainsets.b = right;
/* 299 */     ainsets.c = bottom;
/* 300 */     ainsets.d = left;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static double a(m size, double ref) {
/* 311 */     switch (size.a()) {
/*     */       case 2:
/* 313 */         if (ref == 1.722773839210782E308D) {
/* 314 */           return 1.722773839210782E308D;
/*     */         }
/* 316 */         return size.c() * ref;
/*     */       case 1:
/* 318 */         return size.c();
/*     */       case 3:
/* 320 */         return 1.722773839210782E308D;
/*     */     } 
/* 322 */     throw new IllegalStateException();
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
/*     */   public static double b(m size, double ref) {
/* 334 */     switch (size.b()) {
/*     */       case 2:
/* 336 */         if (ref == 1.722773839210782E308D) {
/* 337 */           return 1.722773839210782E308D;
/*     */         }
/* 339 */         return size.d() * ref;
/*     */       case 1:
/* 341 */         return size.d();
/*     */       case 3:
/* 343 */         return 1.722773839210782E308D;
/*     */     } 
/* 345 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */   
/*     */   public static double a(t insets, double ref) {
/* 350 */     switch (insets.a()) {
/*     */       case 1:
/* 352 */         return insets.f();
/*     */       case 2:
/* 354 */         return insets.f() * ref;
/*     */       case 3:
/* 356 */         return 1.722773839210782E308D;
/*     */     } 
/* 358 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */   
/*     */   public static double b(t insets, double ref) {
/* 363 */     switch (insets.d()) {
/*     */       case 1:
/* 365 */         return insets.i();
/*     */       case 2:
/* 367 */         return insets.i() * ref;
/*     */       case 3:
/* 369 */         return 1.722773839210782E308D;
/*     */     } 
/* 371 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */   
/*     */   public static double c(t insets, double ref) {
/* 376 */     switch (insets.b()) {
/*     */       case 1:
/* 378 */         return insets.g();
/*     */       case 2:
/* 380 */         return insets.g() * ref;
/*     */       case 3:
/* 382 */         return 1.722773839210782E308D;
/*     */     } 
/* 384 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */   
/*     */   public static double d(t insets, double ref) {
/* 389 */     switch (insets.c()) {
/*     */       case 1:
/* 391 */         return insets.h();
/*     */       case 2:
/* 393 */         return insets.h() * ref;
/*     */       case 3:
/* 395 */         return 1.722773839210782E308D;
/*     */     } 
/* 397 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(r box) {
/* 402 */     G params = box.g();
/* 403 */     if (params.ay == 0)
/*     */     {
/* 405 */       return true;
/*     */     }
/* 407 */     if (box.h().b_().a() != 4)
/*     */     {
/* 409 */       return true;
/*     */     }
/* 411 */     boolean vertical = a(params.D);
/* 412 */     if ((vertical ? params.X.a() : params.X.b()) != 3)
/*     */     {
/* 414 */       return true;
/*     */     }
/* 416 */     if ((vertical ? params.X.b() : params.X.a()) == 3)
/*     */     {
/* 418 */       return true;
/*     */     }
/* 420 */     return false;
/*     */   }
/*     */   
/*     */   public static double a(w offset, j containerBox) {
/* 424 */     switch (offset.a()) {
/*     */       case 1:
/* 426 */         return offset.c();
/*     */ 
/*     */       
/*     */       case 2:
/*     */       case 3:
/* 431 */         return 0.0D;
/*     */     } 
/* 433 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */   
/*     */   public static double b(w offset, j containerBox) {
/* 438 */     switch (offset.b()) {
/*     */       case 1:
/* 440 */         return offset.d();
/*     */ 
/*     */       
/*     */       case 2:
/*     */       case 3:
/* 445 */         return 0.0D;
/*     */     } 
/* 447 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(byte progression) {
/* 452 */     switch (progression) {
/*     */       
/*     */       case 1:
/* 455 */         return false;
/*     */       
/*     */       case 2:
/*     */       case 3:
/* 459 */         return true;
/*     */     } 
/* 461 */     throw new IllegalStateException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(a builder, f replacedBox) {
/* 470 */     c box, containerBox = builder.k();
/* 471 */     i params = containerBox.c_();
/* 472 */     double lineSize = containerBox.h();
/* 473 */     replacedBox.a(lineSize);
/*     */ 
/*     */ 
/*     */     
/* 477 */     if (containerBox == builder.l()) {
/* 478 */       box = builder.r();
/*     */     } else {
/* 480 */       box = builder.p();
/*     */     } 
/*     */     
/* 483 */     if (builder.l().a() == 12 && builder instanceof jp.cssj.homare.b.b.a.a) {
/*     */       return;
/*     */     }
/*     */     
/* 487 */     double refWidth = 1.722773839210782E308D, refMaxWidth = refWidth;
/* 488 */     double refHeight = 1.722773839210782E308D, refMaxHeight = refHeight;
/*     */     
/* 490 */     refWidth = (box.a() == 1) ? 1.722773839210782E308D : box.s();
/* 491 */     refMaxWidth = box.s();
/*     */ 
/*     */     
/* 494 */     refMaxHeight = refHeight = 1.722773839210782E308D;
/*     */ 
/*     */ 
/*     */     
/* 498 */     if (containerBox == builder.l()) {
/* 499 */       box = builder.s();
/*     */     } else {
/* 501 */       box = builder.q();
/*     */     } 
/*     */     
/* 504 */     refMaxHeight = refHeight = 1.722773839210782E308D;
/*     */     
/* 506 */     refMaxHeight = refHeight = box.h();
/*     */ 
/*     */     
/* 509 */     refMaxHeight = refHeight = lineSize;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 515 */     if (containerBox == builder.l()) {
/* 516 */       box = builder.s();
/*     */     } else {
/* 518 */       box = builder.q();
/*     */     } 
/*     */     
/* 521 */     if (builder.l().a() == 12 && builder instanceof jp.cssj.homare.b.b.a.a) {
/*     */       return;
/*     */     }
/*     */     
/* 525 */     refMaxHeight = refHeight = 1.722773839210782E308D;
/* 526 */     refMaxWidth = refWidth = 1.722773839210782E308D;
/*     */     
/* 528 */     refHeight = (box.a() == 1) ? 1.722773839210782E308D : box.t();
/* 529 */     refMaxHeight = box.t();
/*     */ 
/*     */     
/* 532 */     refMaxWidth = refWidth = 1.722773839210782E308D;
/*     */ 
/*     */ 
/*     */     
/* 536 */     if (containerBox == builder.l()) {
/* 537 */       box = builder.r();
/*     */     } else {
/* 539 */       box = builder.p();
/*     */     } 
/*     */     
/* 542 */     refMaxWidth = refWidth = 1.722773839210782E308D;
/*     */     
/* 544 */     refMaxWidth = refWidth = box.h();
/*     */ 
/*     */     
/* 547 */     refMaxWidth = refWidth = lineSize;
/*     */ 
/*     */ 
/*     */     
/* 551 */     replacedBox.a(refWidth, refHeight, refMaxWidth, refMaxHeight);
/*     */   }
/*     */   public static double a(c box) {
/*     */     double lineSize;
/* 555 */     i params = box.c_();
/*     */     
/* 557 */     if (a(params.D)) {
/*     */       
/* 559 */       lineSize = box.t();
/*     */     } else {
/*     */       
/* 562 */       lineSize = box.s();
/*     */     } 
/* 564 */     return lineSize;
/*     */   }
/*     */   
/*     */   public static int b(c box) {
/* 568 */     i params = box.c_();
/* 569 */     if (a(params.ac.d)) {
/* 570 */       return params.ac.c;
/*     */     }
/* 572 */     double lineSize = a(box);
/* 573 */     if (params.ac.d >= lineSize) {
/* 574 */       return 1;
/*     */     }
/* 576 */     return (int)Math.floor((lineSize + params.ac.e) / (params.ac.d + params.ac.e));
/*     */   }
/*     */   
/*     */   public static void a(m ua, b imposition) {
/* 580 */     imposition.c((byte)B.C.a(ua));
/* 581 */     imposition.b((byte)B.B.a(ua));
/*     */ 
/*     */ 
/*     */     
/* 585 */     String s = B.y.a(ua);
/* 586 */     a length = l.b(ua, false, s);
/* 587 */     if (length != null) {
/* 588 */       double l = length.c();
/* 589 */       imposition.a(imposition.j(), l, imposition.l(), l);
/*     */     } else {
/* 591 */       ua.a((short)10244, B.y.a, s);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 596 */     s = B.z.a(ua);
/* 597 */     length = l.b(ua, false, s);
/* 598 */     if (length != null) {
/* 599 */       double l = length.c();
/* 600 */       imposition.a(l, imposition.k(), l, imposition.m());
/*     */     } else {
/* 602 */       ua.a((short)10244, B.z.a, s);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 607 */     String str1 = B.A.a(ua);
/* 608 */     if (str1 != null) {
/* 609 */       double[] trims; String[] values = str1.split("[\\s]+");
/* 610 */       if (values.length <= 0 || values.length > 4) {
/* 611 */         ua.a((short)10244, B.A.a, str1);
/* 612 */         trims = null;
/*     */       } else {
/* 614 */         trims = new double[values.length];
/* 615 */         for (int i = 0; i < values.length; i++) {
/* 616 */           a a = l.b(ua, false, values[i]);
/* 617 */           if (a != null) {
/* 618 */             trims[i] = a.c();
/*     */           } else {
/* 620 */             ua.a((short)10244, B.A.a, str1);
/* 621 */             trims = null;
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/* 626 */       switch (trims.length) {
/*     */         case 1:
/* 628 */           imposition.a(trims[0], trims[0], trims[0], trims[0]);
/*     */           break;
/*     */         case 2:
/* 631 */           imposition.a(trims[0], trims[1], trims[0], trims[1]);
/*     */           break;
/*     */         case 3:
/* 634 */           imposition.a(trims[0], trims[1], trims[2], trims[1]);
/*     */           break;
/*     */         case 4:
/* 637 */           imposition.a(trims[0], trims[1], trims[2], trims[3]);
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 644 */     switch (B.P.a(ua)) {
/*     */       case 1:
/* 646 */         imposition.a(0.0D, 0.0D, 0.0D, 0.0D);
/* 647 */         imposition.a(0.0D);
/* 648 */         imposition.a(null);
/*     */         break;
/*     */       case 2:
/* 651 */         imposition.a(true);
/* 652 */         imposition.a("page {0}");
/*     */         break;
/*     */       case 3:
/* 655 */         imposition.b(true);
/* 656 */         imposition.a("page {0}");
/*     */         break;
/*     */       case 4:
/* 659 */         imposition.a(true);
/* 660 */         imposition.b(true);
/* 661 */         imposition.a("page {0}");
/*     */         break;
/*     */       case 5:
/* 664 */         imposition.a("page {0}");
/*     */         break;
/*     */       default:
/* 667 */         throw new IllegalStateException();
/*     */     } 
/* 669 */     imposition.c(B.D.a(ua));
/*     */ 
/*     */ 
/*     */     
/* 673 */     s = B.aF.a(ua);
/* 674 */     if (s != null) {
/* 675 */       a a = l.b(ua, false, s);
/* 676 */       if (a != null) {
/* 677 */         double l = a.c();
/* 678 */         imposition.b(l);
/* 679 */         if (imposition.v() != null) {
/* 680 */           imposition.a(imposition.v() + " / spine " + s);
/*     */         }
/*     */       } else {
/* 683 */         ua.a((short)10244, B.aF.a, s);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 690 */     s = B.v.a(ua);
/* 691 */     if (s != null) {
/* 692 */       a a = l.b(ua, false, s);
/* 693 */       if (a != null) {
/* 694 */         double l = a.c();
/* 695 */         imposition.e(l);
/*     */       } else {
/* 697 */         imposition.q();
/* 698 */         ua.a((short)10244, B.v.a, s);
/*     */       } 
/*     */     } else {
/* 701 */       imposition.q();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 707 */     s = B.w.a(ua);
/* 708 */     if (s != null) {
/* 709 */       a a = l.b(ua, false, s);
/* 710 */       if (a != null) {
/* 711 */         double l = a.c();
/* 712 */         imposition.f(l);
/*     */       } else {
/* 714 */         imposition.s();
/* 715 */         ua.a((short)10244, B.w.a, s);
/*     */       } 
/*     */     } else {
/* 718 */       imposition.s();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/f/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */