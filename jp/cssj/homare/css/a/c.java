/*     */ package jp.cssj.homare.css.a;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.StringTokenizer;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.homare.css.a;
/*     */ import jp.cssj.homare.css.c.j;
/*     */ import jp.cssj.homare.css.e.e;
/*     */ import jp.cssj.homare.css.e.f;
/*     */ import jp.cssj.homare.css.e.k;
/*     */ import jp.cssj.homare.css.f.E;
/*     */ import jp.cssj.homare.css.f.G;
/*     */ import jp.cssj.homare.css.f.M;
/*     */ import jp.cssj.homare.css.f.O;
/*     */ import jp.cssj.homare.css.f.X;
/*     */ import jp.cssj.homare.css.f.a;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.css.f.af;
/*     */ import jp.cssj.homare.css.f.c.a;
/*     */ import jp.cssj.homare.css.f.d;
/*     */ import jp.cssj.homare.css.f.i;
/*     */ import jp.cssj.homare.css.f.k;
/*     */ import jp.cssj.homare.css.f.n;
/*     */ import jp.cssj.homare.css.f.x;
/*     */ import jp.cssj.homare.impl.a.c.I;
/*     */ import jp.cssj.homare.impl.a.c.L;
/*     */ import jp.cssj.homare.impl.a.c.S;
/*     */ import jp.cssj.homare.impl.a.c.T;
/*     */ import jp.cssj.homare.impl.a.c.U;
/*     */ import jp.cssj.homare.impl.a.c.V;
/*     */ import jp.cssj.homare.impl.a.c.am;
/*     */ import jp.cssj.homare.impl.a.c.as;
/*     */ import jp.cssj.homare.impl.a.c.aw;
/*     */ import jp.cssj.homare.impl.a.c.b;
/*     */ import jp.cssj.homare.impl.a.c.b.b;
/*     */ import jp.cssj.homare.impl.a.c.c.b;
/*     */ import jp.cssj.homare.impl.a.c.g;
/*     */ import jp.cssj.homare.impl.a.c.h;
/*     */ import jp.cssj.homare.impl.a.c.k;
/*     */ import jp.cssj.homare.impl.a.c.l;
/*     */ import jp.cssj.homare.impl.a.c.n;
/*     */ import jp.cssj.homare.impl.a.c.o;
/*     */ import jp.cssj.homare.impl.a.c.r;
/*     */ import jp.cssj.homare.impl.a.c.s;
/*     */ import jp.cssj.homare.impl.a.c.u;
/*     */ import jp.cssj.homare.impl.a.c.v;
/*     */ import jp.cssj.homare.impl.a.c.w;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.c.a.b;
/*     */ import jp.cssj.sakae.e.d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class c
/*     */ {
/*     */   static final byte a = 1;
/*     */   static final byte b = 2;
/*     */   static final byte c = 3;
/*     */   static final byte d = 4;
/*     */   static final byte e = 5;
/*     */   static final byte f = 6;
/*     */   static final byte g = 7;
/*     */   static final byte h = 8;
/*     */   static final byte i = 9;
/*     */   static final byte j = 10;
/*     */   
/*     */   static byte a(String type) {
/*  75 */     if (type == null || type.length() == 0) {
/*  76 */       return 1;
/*     */     }
/*  78 */     switch (type.charAt(0)) {
/*     */       case 'P':
/*     */       case 'p':
/*  81 */         if (type.equalsIgnoreCase("password")) {
/*  82 */           return 2;
/*     */         }
/*     */         break;
/*     */       case 'C':
/*     */       case 'c':
/*  87 */         if (type.equalsIgnoreCase("checkbox")) {
/*  88 */           return 3;
/*     */         }
/*     */         break;
/*     */       case 'R':
/*     */       case 'r':
/*  93 */         if (type.equalsIgnoreCase("radio"))
/*  94 */           return 4; 
/*  95 */         if (type.equalsIgnoreCase("reset")) {
/*  96 */           return 8;
/*     */         }
/*     */         break;
/*     */       case 'F':
/*     */       case 'f':
/* 101 */         if (type.equalsIgnoreCase("file")) {
/* 102 */           return 5;
/*     */         }
/*     */         break;
/*     */       case 'H':
/*     */       case 'h':
/* 107 */         if (type.equalsIgnoreCase("hidden")) {
/* 108 */           return 6;
/*     */         }
/*     */         break;
/*     */       case 'S':
/*     */       case 's':
/* 113 */         if (type.equalsIgnoreCase("submit")) {
/* 114 */           return 7;
/*     */         }
/*     */         break;
/*     */       case 'B':
/*     */       case 'b':
/* 119 */         if (type.equalsIgnoreCase("button")) {
/* 120 */           return 9;
/*     */         }
/*     */         break;
/*     */       case 'I':
/*     */       case 'i':
/* 125 */         if (type.equalsIgnoreCase("image")) {
/* 126 */           return 10;
/*     */         }
/*     */         break;
/*     */     } 
/* 130 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void a(jp.cssj.homare.css.c style, E length) {
/* 140 */     jp.cssj.homare.css.c pStyle = style.c();
/* 141 */     if (pStyle != null && ((b.c(pStyle) == 1 && 
/* 142 */       e.a(jp.cssj.homare.impl.a.c.a.c.c(pStyle))) || 
/* 143 */       b.c(pStyle) == 3)) {
/*     */       
/* 145 */       style.a(V.a, (ad)length);
/*     */     } else {
/*     */       
/* 148 */       style.a(T.a, (ad)length);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void b(jp.cssj.homare.css.c style, E length) {
/* 159 */     jp.cssj.homare.css.c pStyle = style.c();
/* 160 */     if (pStyle != null && ((b.c(pStyle) == 1 && 
/* 161 */       e.a(jp.cssj.homare.impl.a.c.a.c.c(pStyle))) || 
/* 162 */       b.c(pStyle) == 3)) {
/*     */       
/* 164 */       style.a(V.a, (ad)length);
/* 165 */       style.a(S.a, (ad)length);
/*     */     } else {
/*     */       
/* 168 */       style.a(T.a, (ad)length);
/* 169 */       style.a(U.a, (ad)length);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(String elem, jp.cssj.homare.css.c style) {
/* 179 */     m ua = style.b();
/* 180 */     a ce = style.a();
/* 181 */     String width = ce.H.getValue("width");
/* 182 */     if (width != null) {
/*     */       try {
/* 184 */         O length = a(ua, width);
/* 185 */         if (length.d()) {
/* 186 */           throw new NumberFormatException();
/*     */         }
/* 188 */         style.a(aw.a, (ad)length);
/* 189 */       } catch (Exception e) {
/* 190 */         ua.a((short)10248, elem, "width", width);
/*     */       } 
/*     */     }
/* 193 */     String height = ce.H.getValue("height");
/* 194 */     if (height != null) {
/*     */       try {
/* 196 */         O length = a(ua, height);
/* 197 */         if (length.d()) {
/* 198 */           throw new NumberFormatException();
/*     */         }
/* 200 */         style.a(L.a, (ad)length);
/* 201 */       } catch (Exception e) {
/* 202 */         ua.a((short)10248, elem, "height", height);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void b(String elem, jp.cssj.homare.css.c style) {
/* 213 */     m ua = style.b();
/* 214 */     a ce = style.a();
/* 215 */     String hspace = ce.H.getValue("hspace");
/* 216 */     if (hspace != null) {
/*     */       try {
/* 218 */         O length = a(ua, hspace);
/* 219 */         if (length.d()) {
/* 220 */           throw new NumberFormatException();
/*     */         }
/* 222 */         style.a(T.a, (ad)length);
/* 223 */         style.a(U.a, (ad)length);
/* 224 */       } catch (Exception e) {
/* 225 */         ua.a((short)10248, elem, "hspace", hspace);
/*     */       } 
/*     */     }
/* 228 */     String vspace = ce.H.getValue("vspace");
/* 229 */     if (vspace != null) {
/*     */       try {
/* 231 */         O length = a(ua, vspace);
/* 232 */         if (length.d()) {
/* 233 */           throw new NumberFormatException();
/*     */         }
/* 235 */         style.a(V.a, (ad)length);
/* 236 */         style.a(S.a, (ad)length);
/* 237 */       } catch (Exception e) {
/* 238 */         ua.a((short)10248, elem, "vspace", vspace);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void c(String elem, jp.cssj.homare.css.c style) {
/* 249 */     m ua = style.b();
/* 250 */     a ce = style.a();
/*     */     
/* 252 */     String str = ce.H.getValue("marginwidth");
/* 253 */     if (str != null) {
/*     */       try {
/* 255 */         O length = a(ua, str);
/* 256 */         if (length.d()) {
/* 257 */           throw new NumberFormatException();
/*     */         }
/* 259 */         style.a(T.a, (ad)length);
/* 260 */         style.a(U.a, (ad)length);
/* 261 */       } catch (Exception e) {
/* 262 */         ua.a((short)10248, elem, "marginwidth", str);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 267 */     str = ce.H.getValue("marginheight");
/* 268 */     if (str != null) {
/*     */       try {
/* 270 */         O length = a(ua, str);
/* 271 */         if (length.d()) {
/* 272 */           throw new NumberFormatException();
/*     */         }
/* 274 */         style.a(V.a, (ad)length);
/* 275 */         style.a(S.a, (ad)length);
/* 276 */       } catch (Exception e) {
/* 277 */         ua.a((short)10248, elem, "marginheight", str);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static void d(String elem, jp.cssj.homare.css.c style) {
/*     */     a a;
/* 284 */     m ua = style.b();
/* 285 */     a ce = style.a();
/*     */     
/* 287 */     String str = ce.H.getValue("border");
/* 288 */     if (str != null) {
/*     */       try {
/* 290 */         a = a.a(ua, d.a(str), (short)17);
/* 291 */         if (a.d()) {
/* 292 */           throw new NumberFormatException();
/*     */         }
/* 294 */       } catch (Exception e) {
/* 295 */         ua.a((short)10248, elem, "border", str);
/*     */         return;
/*     */       } 
/*     */     } else {
/* 299 */       a = a.a;
/* 300 */       for (jp.cssj.homare.css.c parentStyle = style.c(); parentStyle != null; 
/* 301 */         parentStyle = parentStyle.c()) {
/* 302 */         if (parentStyle.a().a((byte)7)) {
/* 303 */           a = ua.c((byte)2);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 308 */     if (!a.e()) {
/* 309 */       style.a(s.a, (ad)a);
/* 310 */       style.a(o.a, (ad)a);
/* 311 */       style.a(h.a, (ad)a);
/* 312 */       style.a(l.a, (ad)a);
/* 313 */       style.a(r.a, (ad)i.n);
/* 314 */       style.a(n.a, (ad)i.n);
/* 315 */       style.a((j)g.a, (ad)i.n);
/* 316 */       style.a((j)k.a, (ad)i.n);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void e(String elem, jp.cssj.homare.css.c style) {
/* 326 */     m ua = style.b();
/* 327 */     a ce = style.a();
/* 328 */     String align = ce.H.getValue("align");
/* 329 */     if (align != null) {
/* 330 */       align = align.trim();
/* 331 */       if (align.length() > 0) {
/* 332 */         switch (align.charAt(0)) {
/*     */           case 'A':
/*     */           case 'a':
/* 335 */             if (align.equalsIgnoreCase("absbottom")) {
/* 336 */               style.a(as.a, (ad)af.f);
/* 337 */             } else if (align.equalsIgnoreCase("absmiddle")) {
/* 338 */               style.a(as.a, (ad)af.b);
/*     */             } 
/*     */             return;
/*     */           case 'B':
/*     */           case 'b':
/* 343 */             if (align.equalsIgnoreCase("bottom")) {
/* 344 */               style.a(as.a, (ad)af.h);
/* 345 */             } else if (align.equalsIgnoreCase("baseline")) {
/* 346 */               style.a(as.a, (ad)af.a);
/*     */             } 
/*     */             return;
/*     */           case 'C':
/*     */           case 'c':
/* 351 */             if (align.equalsIgnoreCase("center")) {
/* 352 */               style.a(as.a, (ad)af.b);
/*     */             }
/*     */             return;
/*     */           case 'L':
/*     */           case 'l':
/* 357 */             if (align.equalsIgnoreCase("left")) {
/* 358 */               style.a(v.a, (ad)k.g);
/*     */             }
/*     */             return;
/*     */           case 'M':
/*     */           case 'm':
/* 363 */             if (align.equalsIgnoreCase("middle")) {
/* 364 */               style.a(as.a, (ad)af.b);
/*     */             }
/*     */             return;
/*     */           case 'R':
/*     */           case 'r':
/* 369 */             if (align.equalsIgnoreCase("right")) {
/* 370 */               style.a(v.a, (ad)k.h);
/*     */             }
/*     */             return;
/*     */           case 'T':
/*     */           case 't':
/* 375 */             if (align.equalsIgnoreCase("top")) {
/* 376 */               style.a(as.a, (ad)af.g);
/* 377 */             } else if (align.equalsIgnoreCase("texttop")) {
/* 378 */               style.a(as.a, (ad)af.e);
/*     */             } 
/*     */             return;
/*     */         } 
/* 382 */         ua.a((short)10248, elem, "align", align);
/*     */       }
/*     */       else {
/*     */         
/* 386 */         ua.a((short)10248, elem, "align", align);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void f(String elem, jp.cssj.homare.css.c style) {
/* 397 */     m ua = style.b();
/* 398 */     a ce = style.a();
/* 399 */     String align = ce.H.getValue("align");
/* 400 */     if (align != null) {
/* 401 */       align = align.trim();
/* 402 */       if (align.length() > 0) {
/* 403 */         switch (align.charAt(0)) {
/*     */           case 'C':
/*     */           case 'c':
/* 406 */             if (align.equalsIgnoreCase("center")) {
/* 407 */               style.a(T.a, (ad)d.a);
/* 408 */               style.a(U.a, (ad)d.a);
/*     */             } 
/*     */             return;
/*     */           case 'L':
/*     */           case 'l':
/* 413 */             if (align.equalsIgnoreCase("left")) {
/* 414 */               style.a(v.a, (ad)k.g);
/*     */             }
/*     */             return;
/*     */           case 'R':
/*     */           case 'r':
/* 419 */             if (align.equalsIgnoreCase("right")) {
/* 420 */               style.a(v.a, (ad)k.h);
/*     */             }
/*     */             return;
/*     */         } 
/* 424 */         ua.a((short)10248, elem, "align", align);
/*     */       }
/*     */       else {
/*     */         
/* 428 */         ua.a((short)10248, elem, "align", align);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void g(String elem, jp.cssj.homare.css.c style) {
/* 439 */     m ua = style.b();
/* 440 */     a ce = style.a();
/* 441 */     String align = ce.H.getValue("align");
/* 442 */     if (align != null) {
/* 443 */       align = align.trim();
/* 444 */       if (align.length() > 0) {
/* 445 */         if (align.equalsIgnoreCase("center") || align.equalsIgnoreCase("middle")) {
/* 446 */           style.a(am.a, (ad)X.j);
/* 447 */           style.a(b.a, (ad)a.c);
/* 448 */         } else if (align.equalsIgnoreCase("left")) {
/* 449 */           style.a(am.a, (ad)X.h);
/* 450 */           style.a(b.a, (ad)a.a);
/* 451 */         } else if (align.equalsIgnoreCase("right")) {
/* 452 */           style.a(am.a, (ad)X.i);
/* 453 */           style.a(b.a, (ad)a.b);
/* 454 */         } else if (align.equalsIgnoreCase("justify")) {
/* 455 */           style.a(am.a, (ad)X.k);
/* 456 */           style.a(b.a, (ad)a.a);
/*     */         } 
/*     */       } else {
/* 459 */         ua.a((short)10248, elem, "align", align);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void a(String elem, jp.cssj.homare.css.c style, String valign) {
/* 470 */     if (valign != null) {
/* 471 */       m ua = style.b();
/* 472 */       valign = valign.trim();
/* 473 */       if (valign.length() > 0) {
/* 474 */         if (valign.equalsIgnoreCase("baseline")) {
/* 475 */           style.a(as.a, (ad)af.a);
/* 476 */         } else if (valign.equalsIgnoreCase("bottom")) {
/* 477 */           style.a(as.a, (ad)af.h);
/* 478 */         } else if (valign.equalsIgnoreCase("center") || valign.equalsIgnoreCase("middle")) {
/* 479 */           style.a(as.a, (ad)af.b);
/* 480 */         } else if (valign.equalsIgnoreCase("top")) {
/* 481 */           style.a(as.a, (ad)af.g);
/*     */         } 
/*     */       } else {
/* 484 */         ua.a((short)10248, elem, "valign", valign);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void h(String elem, jp.cssj.homare.css.c style) {
/* 495 */     m ua = style.b();
/* 496 */     a ce = style.a();
/* 497 */     String size = ce.H.getValue("size");
/* 498 */     if (size != null) {
/* 499 */       size = size.trim();
/*     */       try {
/* 501 */         if (size.startsWith("+")) {
/* 502 */           int sizeNum = Integer.parseInt(size.substring(1));
/* 503 */           double normal = ua.d((byte)4);
/* 504 */           switch (sizeNum) {
/*     */             case 1:
/* 506 */               style.a(I.a, (ad)a.a(ua, normal * 1.2D));
/*     */               break;
/*     */             case 2:
/* 509 */               style.a(I.a, (ad)a.a(ua, normal * 1.44D));
/*     */               break;
/*     */             case 3:
/* 512 */               style.a(I.a, (ad)a.a(ua, normal * 1.73D));
/*     */               break;
/*     */             case 4:
/* 515 */               style.a(I.a, (ad)a.a(ua, normal * 2.07D));
/*     */               break;
/*     */             case 5:
/* 518 */               style.a(I.a, (ad)a.a(ua, normal * 2.48D));
/*     */               break;
/*     */             case 6:
/* 521 */               style.a(I.a, (ad)a.a(ua, normal * 2.99D));
/*     */               break;
/*     */           } 
/*     */         
/* 525 */         } else if (size.startsWith("-")) {
/* 526 */           int sizeNum = Integer.parseInt(size.substring(1));
/* 527 */           double normal = ua.d((byte)4);
/* 528 */           switch (sizeNum) {
/*     */             case 1:
/* 530 */               style.a(I.a, (ad)a.a(ua, normal * 0.83D));
/*     */               break;
/*     */             case 2:
/* 533 */               style.a(I.a, (ad)a.a(ua, normal * 0.69D));
/*     */               break;
/*     */             case 3:
/* 536 */               style.a(I.a, (ad)a.a(ua, normal * 0.58D));
/*     */               break;
/*     */             case 4:
/* 539 */               style.a(I.a, (ad)a.a(ua, normal * 0.48D));
/*     */               break;
/*     */             case 5:
/* 542 */               style.a(I.a, (ad)a.a(ua, normal * 0.4D));
/*     */               break;
/*     */             case 6:
/* 545 */               style.a(I.a, (ad)a.a(ua, normal * 0.33D));
/*     */               break;
/*     */           } 
/*     */         
/*     */         } else {
/* 550 */           int sizeNum = Integer.parseInt(size);
/* 551 */           switch (sizeNum) {
/*     */             case 1:
/* 553 */               style.a(I.a, 
/* 554 */                   (ad)a.a(ua, ua.d((byte)1)));
/*     */               break;
/*     */             case 2:
/* 557 */               style.a(I.a, 
/* 558 */                   (ad)a.a(ua, ua.d((byte)3)));
/*     */               break;
/*     */             case 3:
/* 561 */               style.a(I.a, 
/* 562 */                   (ad)a.a(ua, ua.d((byte)4)));
/*     */               break;
/*     */             case 4:
/* 565 */               style.a(I.a, 
/* 566 */                   (ad)a.a(ua, ua.d((byte)5)));
/*     */               break;
/*     */             case 5:
/* 569 */               style.a(I.a, 
/* 570 */                   (ad)a.a(ua, ua.d((byte)6)));
/*     */               break;
/*     */             case 6:
/*     */             case 7:
/* 574 */               style.a(I.a, 
/* 575 */                   (ad)a.a(ua, ua.d((byte)7)));
/*     */               break;
/*     */           } 
/*     */         
/*     */         } 
/* 580 */       } catch (NumberFormatException e) {
/* 581 */         ua.a((short)10248, elem, "size", size);
/*     */       } 
/*     */     } else {
/* 584 */       String pointSize = ce.H.getValue("point-size");
/* 585 */       if (pointSize != null) {
/*     */         try {
/* 587 */           style.a(I.a, 
/* 588 */               (ad)a.a(ua, d.a(pointSize), (short)21));
/* 589 */         } catch (NumberFormatException e) {
/* 590 */           ua.a((short)10248, elem, "point-size", size);
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void a(jp.cssj.homare.css.c style) {
/* 602 */     m ua = style.b();
/* 603 */     a ce = style.a();
/* 604 */     String faces = ce.H.getValue("face");
/* 605 */     if (faces != null) {
/* 606 */       faces = faces.trim();
/* 607 */       List<b> list = new ArrayList<>();
/* 608 */       for (StringTokenizer st = new StringTokenizer(faces, ","); st.hasMoreTokens(); ) {
/* 609 */         String face = st.nextToken();
/* 610 */         list.add(b.a(face));
/*     */       } 
/* 612 */       x defaultFamily = ua.i();
/* 613 */       for (int i = 0; i < defaultFamily.b(); i++) {
/* 614 */         list.add(defaultFamily.a(i));
/*     */       }
/* 616 */       style.a((j)w.a, (ad)new x(list
/* 617 */             .<b>toArray(new b[list.size()])));
/*     */     } 
/*     */   }
/*     */   
/*     */   static n b(String color) {
/* 622 */     color = color.trim();
/* 623 */     n value = jp.cssj.homare.css.e.c.a(color);
/* 624 */     if (value != null) {
/* 625 */       return value;
/*     */     }
/* 627 */     if (color.startsWith("#")) {
/* 628 */       color = color.substring(1).trim();
/*     */     }
/* 630 */     return jp.cssj.homare.css.e.c.b(color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void i(String elem, jp.cssj.homare.css.c style) {
/* 639 */     m ua = style.b();
/* 640 */     a ce = style.a();
/* 641 */     String color = ce.H.getValue("color");
/* 642 */     if (color != null) {
/* 643 */       n value = b(color);
/* 644 */       if (value == null) {
/* 645 */         ua.a((short)10248, elem, "color", color);
/*     */         return;
/*     */       } 
/* 648 */       style.a(u.a, (ad)value);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void j(String elem, jp.cssj.homare.css.c style) {
/* 658 */     m ua = style.b();
/* 659 */     a ce = style.a();
/* 660 */     String bgcolor = ce.H.getValue("bgcolor");
/* 661 */     if (bgcolor != null) {
/* 662 */       n value = b(bgcolor);
/* 663 */       if (value == null) {
/* 664 */         ua.a((short)10248, elem, "bgcolor", bgcolor);
/*     */         return;
/*     */       } 
/* 667 */       style.a(b.a, (ad)value);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static void k(String elem, jp.cssj.homare.css.c style) {
/* 677 */     m ua = style.b();
/* 678 */     a ce = style.a();
/* 679 */     String background = ce.H.getValue("background");
/* 680 */     if (background != null) {
/* 681 */       background = background.trim();
/* 682 */       if (background == null) {
/* 683 */         ua.a((short)10248, elem, "background", background);
/*     */         return;
/*     */       } 
/*     */       try {
/* 687 */         style.a(jp.cssj.homare.impl.a.c.c.a, (ad)k.a(ua.c().c(), ua
/* 688 */               .c().a(), background));
/* 689 */       } catch (Exception e) {
/* 690 */         ua.a((short)10248, elem, "background", background);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   static O a(m ua, String str) {
/* 696 */     if (str.endsWith("%")) {
/* 697 */       double percentage = d.a(str.substring(0, str.length() - 1));
/* 698 */       return (O)M.a(percentage);
/*     */     } 
/*     */     try {
/* 701 */       return (O)f.a(ua, str);
/* 702 */     } catch (Exception exception) {
/*     */ 
/*     */       
/* 705 */       StringBuffer buff = new StringBuffer(str.length());
/* 706 */       int i = 0;
/* 707 */       for (; i < str.length(); i++) {
/* 708 */         char c1 = str.charAt(i);
/* 709 */         if (a(c1)) {
/*     */           break;
/*     */         }
/*     */       } 
/* 713 */       while (i < str.length()) {
/* 714 */         char c1 = str.charAt(i);
/* 715 */         if (a(c1)) {
/* 716 */           buff.append(c1);
/*     */           
/*     */           i++;
/*     */         } 
/*     */       } 
/* 721 */       return (O)a.a(ua, d.a(buff.toString()), (short)17);
/*     */     } 
/*     */   }
/*     */   private static boolean a(char c1) {
/* 725 */     return ((c1 >= '0' && c1 <= '9') || c1 == '-' || c1 == '+' || c1 == '.' || c1 == 'e');
/*     */   }
/*     */   
/*     */   static G c(String ident) {
/* 729 */     if (ident.length() > 0) {
/* 730 */       switch (ident.charAt(0)) {
/*     */         case '1':
/* 732 */           return G.B;
/*     */         case 'a':
/* 734 */           return G.H;
/*     */         case 'A':
/* 736 */           return G.J;
/*     */         case 'i':
/* 738 */           return G.D;
/*     */         case 'I':
/* 740 */           return G.E;
/*     */       } 
/*     */     }
/* 743 */     return e.a(ident);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */