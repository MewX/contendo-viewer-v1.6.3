/*     */ package jp.cssj.homare.impl.ua.pdf;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.net.URI;
/*     */ import java.net.URISyntaxException;
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import jp.cssj.b.d;
/*     */ import jp.cssj.b.d.b;
/*     */ import jp.cssj.b.d.c;
/*     */ import jp.cssj.e.b;
/*     */ import jp.cssj.e.e.b;
/*     */ import jp.cssj.e.e.d;
/*     */ import jp.cssj.homare.impl.ua.a;
/*     */ import jp.cssj.homare.impl.ua.d;
/*     */ import jp.cssj.homare.ua.a.B;
/*     */ import jp.cssj.homare.ua.b;
/*     */ import jp.cssj.homare.ua.j;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.c.a.e;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.b.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.c.f;
/*     */ import jp.cssj.sakae.pdf.a.b;
/*     */ import jp.cssj.sakae.pdf.b.c;
/*     */ import jp.cssj.sakae.pdf.d;
/*     */ import jp.cssj.sakae.pdf.d.b;
/*     */ import jp.cssj.sakae.pdf.e;
/*     */ import jp.cssj.sakae.pdf.e.n;
/*     */ import jp.cssj.sakae.pdf.f.b;
/*     */ import jp.cssj.sakae.pdf.f.d;
/*     */ import jp.cssj.sakae.pdf.f.e;
/*     */ import jp.cssj.sakae.pdf.f.f;
/*     */ import jp.cssj.sakae.pdf.f.g;
/*     */ import jp.cssj.sakae.pdf.f.h;
/*     */ import jp.cssj.sakae.pdf.f.i;
/*     */ import jp.cssj.sakae.pdf.f.j;
/*     */ import jp.cssj.sakae.pdf.h;
/*     */ import jp.cssj.sakae.pdf.i;
/*     */ import jp.cssj.sakae.pdf.j;
/*     */ import org.apache.commons.collections.primitives.ArrayIntList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends a
/*     */   implements j
/*     */ {
/*  73 */   private static final Logger t = Logger.getLogger(a.class.getName()); private c u;
/*     */   private c v;
/*     */   private jp.cssj.f.a w;
/*     */   private jp.cssj.f.a x;
/*  77 */   private j y = null; private j z = null;
/*     */   private final e A;
/*  79 */   private f B = null;
/*  80 */   private b C = null;
/*     */   
/*  82 */   protected b c = null;
/*     */   
/*     */   private boolean D = false;
/*     */   
/*     */   protected a() {
/*  87 */     this.A = new e();
/*  88 */     this.A.d(jp.cssj.homare.a.a.g);
/*     */   }
/*     */   
/*     */   public void a(c results) {
/*  92 */     this.u = results;
/*     */   }
/*     */   
/*     */   public void f(byte mode) {
/*  96 */     super.f(mode);
/*  97 */     switch (mode) {
/*     */       case 0:
/*     */         return;
/*     */       case 1:
/* 101 */         if (this.u != b.a) {
/* 102 */           this.v = this.u;
/* 103 */           this.u = (c)b.a;
/* 104 */           this.z = this.y;
/* 105 */           this.x = this.w;
/*     */         } 
/* 107 */         w();
/*     */       
/*     */       case 2:
/* 110 */         this.u = this.v;
/* 111 */         this.v = null;
/* 112 */         if (this.z != null) {
/* 113 */           this.w = null;
/*     */         }
/* 115 */         w();
/* 116 */         if (this.z != null) {
/* 117 */           this.y = this.z;
/* 118 */           this.z = null;
/* 119 */           this.w = this.x;
/*     */         } 
/*     */     } 
/*     */     
/* 123 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */   
/*     */   private void w() {
/* 128 */     if (this.w != null) {
/* 129 */       this.w.e();
/* 130 */       this.w = null;
/*     */     } 
/* 132 */     this.c = null;
/* 133 */     this.D = false;
/* 134 */     this.y = null;
/*     */   }
/*     */   
/*     */   public void e(byte boundSide) {
/* 138 */     super.e(boundSide);
/*     */ 
/*     */     
/* 141 */     if (r() != 0 && this.y != null) {
/* 142 */       j vp = this.y.a().p();
/* 143 */       switch (r()) {
/*     */         case 1:
/* 145 */           vp.a((byte)1);
/*     */           return;
/*     */         case 2:
/* 148 */           vp.a((byte)2);
/*     */           return;
/*     */       } 
/* 151 */       throw new IllegalStateException();
/*     */     } 
/*     */   }
/*     */   private void x() throws IOException {
/*     */     g v1Params;
/*     */     d r2p;
/* 157 */     if (this.y != null) {
/*     */       return;
/*     */     }
/*     */     
/* 161 */     b params = new b();
/* 162 */     params.a(a().a());
/*     */ 
/*     */     
/* 165 */     switch (B.ae.a((m)this)) {
/*     */       case 1:
/* 167 */         params.d(1200);
/*     */         break;
/*     */       case 2:
/* 170 */         params.d(1300);
/*     */         break;
/*     */       case 3:
/* 173 */         params.d(1400);
/*     */         break;
/*     */       case 7:
/* 176 */         params.d(1412);
/*     */         break;
/*     */       case 8:
/* 179 */         params.d(1421);
/*     */         break;
/*     */       case 4:
/* 182 */         params.d(1500);
/*     */         break;
/*     */       case 5:
/* 185 */         params.d(1600);
/*     */         break;
/*     */       case 6:
/* 188 */         params.d(1700);
/*     */         break;
/*     */       default:
/* 191 */         throw new IllegalStateException();
/*     */     } 
/*     */ 
/*     */     
/* 195 */     String fileId = B.aC.a((m)this);
/* 196 */     if (fileId != null) {
/* 197 */       if (fileId.length() == 32) {
/* 198 */         byte[] id = new byte[16];
/*     */         try {
/* 200 */           for (int i = 0; i < fileId.length(); i += 2) {
/* 201 */             String hex = fileId.substring(i, i + 2);
/* 202 */             id[i / 2] = (byte)(Integer.parseInt(hex, 16) & 0xFF);
/*     */           } 
/* 204 */           params.a(id);
/* 205 */         } catch (NumberFormatException numberFormatException) {
/* 206 */           a((short)10244, new String[] { B.aC.a, fileId });
/*     */         } 
/*     */       } else {
/*     */         
/* 210 */         a((short)10244, new String[] { B.aC.a, fileId });
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 216 */     String creationDate = B.aD.a((m)this);
/* 217 */     String modDate = B.aE.a((m)this);
/* 218 */     if (creationDate != null || modDate != null) {
/* 219 */       DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
/* 220 */       DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/* 221 */       format1.setLenient(true);
/* 222 */       format2.setLenient(true);
/* 223 */       if (creationDate != null) {
/*     */         try {
/*     */           long time;
/*     */           try {
/* 227 */             time = format1.parse(creationDate).getTime();
/* 228 */           } catch (ParseException parseException) {
/*     */             try {
/* 230 */               int colon = creationDate.lastIndexOf(':');
/* 231 */               String s = creationDate.substring(0, colon) + creationDate.substring(colon + 1);
/* 232 */               time = format1.parse(s).getTime();
/* 233 */             } catch (Exception e2) {
/* 234 */               time = format2.parse(creationDate).getTime();
/*     */             } 
/*     */           } 
/* 237 */           this.A.a(time);
/* 238 */         } catch (ParseException parseException) {
/* 239 */           a((short)10244, new String[] { B.aD.a, creationDate });
/*     */         } 
/*     */       }
/*     */       
/* 243 */       if (modDate != null) {
/*     */         try {
/*     */           long time;
/*     */           try {
/* 247 */             time = format1.parse(modDate).getTime();
/* 248 */           } catch (ParseException parseException) {
/*     */             try {
/* 250 */               int colon = modDate.lastIndexOf(':');
/* 251 */               String s = modDate.substring(0, colon) + modDate.substring(colon + 1);
/* 252 */               time = format1.parse(s).getTime();
/* 253 */             } catch (Exception e2) {
/* 254 */               time = format2.parse(modDate).getTime();
/*     */             } 
/*     */           } 
/* 257 */           this.A.b(time);
/* 258 */         } catch (ParseException parseException) {
/* 259 */           a((short)10244, B.aE.a, modDate);
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 265 */     int color = B.S.a((m)this);
/* 266 */     if (params.h() == 1421 && color == 1) {
/* 267 */       a((short)10258, B.S.a, "rgb", "PDF/X-1a");
/* 268 */       color = 3;
/*     */     } 
/* 270 */     switch (color) {
/*     */       case 1:
/* 272 */         params.d((short)0);
/*     */         break;
/*     */       case 2:
/* 275 */         params.d((short)1);
/*     */         break;
/*     */       case 3:
/* 278 */         params.d((short)2);
/*     */         break;
/*     */       default:
/* 281 */         throw new IllegalStateException();
/*     */     } 
/*     */ 
/*     */     
/* 285 */     switch (B.Y.a((m)this)) {
/*     */       case 1:
/* 287 */         params.a((short)0);
/*     */         break;
/*     */       case 2:
/* 290 */         params.a((short)2);
/*     */         break;
/*     */       case 3:
/* 293 */         params.a((short)1);
/*     */         break;
/*     */       default:
/* 296 */         throw new IllegalStateException();
/*     */     } 
/*     */ 
/*     */     
/* 300 */     if (B.ar.a((m)this)) {
/* 301 */       params.a(true);
/*     */     }
/*     */ 
/*     */     
/* 305 */     switch (B.aw.a((m)this)) {
/*     */       case 1:
/* 307 */         params.b((short)0);
/*     */         break;
/*     */       case 2:
/*     */       case 3:
/* 311 */         params.b((short)1);
/*     */         break;
/*     */       default:
/* 314 */         throw new IllegalStateException();
/*     */     } 
/*     */ 
/*     */     
/* 318 */     switch (B.Z.a((m)this)) {
/*     */       case 1:
/* 320 */         params.c((short)0);
/*     */         break;
/*     */       case 2:
/* 323 */         params.c((short)1);
/*     */         break;
/*     */       case 3:
/* 326 */         params.c((short)2);
/*     */         break;
/*     */       default:
/* 329 */         throw new IllegalStateException();
/*     */     } 
/*     */ 
/*     */     
/* 333 */     params.a(B.aa.a((m)this));
/*     */ 
/*     */     
/* 336 */     params.b(B.ab.a((m)this));
/* 337 */     params.c(B.ac.a((m)this));
/*     */ 
/*     */     
/* 340 */     params.a(B.ax.a((m)this));
/*     */ 
/*     */     
/* 343 */     switch (B.af.a((m)this)) {
/*     */       case 1:
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/* 349 */         if (params.h() == 1412) {
/* 350 */           a((short)10258, B.af.a, "v1", "PDF/A-1"); break;
/*     */         } 
/* 352 */         if (params.h() == 1421) {
/* 353 */           a((short)10258, B.af.a, "v1", "PDF/X-1a");
/*     */           break;
/*     */         } 
/* 356 */         v1Params = new g();
/* 357 */         a((jp.cssj.sakae.pdf.f.a)v1Params);
/* 358 */         r2p = v1Params.d();
/* 359 */         a(r2p);
/* 360 */         params.a((jp.cssj.sakae.pdf.f.a)v1Params);
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 366 */         if (params.h() == 1412) {
/* 367 */           a((short)10258, B.af.a, "v2", "PDF/A-1"); break;
/*     */         } 
/* 369 */         if (params.h() == 1421) {
/* 370 */           a((short)10258, B.af.a, "v2", "PDF/X-1a"); break;
/*     */         } 
/* 372 */         if (params.h() >= 1300) {
/* 373 */           h v2Params = new h();
/* 374 */           a((jp.cssj.sakae.pdf.f.a)v2Params);
/* 375 */           int length = B.aq.a((m)this);
/*     */           try {
/* 377 */             v2Params.a(length);
/* 378 */           } catch (IllegalArgumentException illegalArgumentException) {
/* 379 */             a((short)10258, B.aq.a, 
/* 380 */                 String.valueOf(length), "V2 Encryption");
/*     */           } 
/* 382 */           e r3p = v2Params.d();
/* 383 */           a((d)r3p);
/* 384 */           a(r3p);
/* 385 */           params.a((jp.cssj.sakae.pdf.f.a)v2Params); break;
/*     */         } 
/* 387 */         a((short)10258, B.af.a, "v2", "1.2");
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 394 */         if (params.h() == 1412) {
/* 395 */           a((short)10258, B.af.a, "v4", "PDF/A-1"); break;
/*     */         } 
/* 397 */         if (params.h() == 1421) {
/* 398 */           a((short)10258, B.af.a, "v4", "PDF/X-1a"); break;
/*     */         } 
/* 400 */         if (params.h() >= 1500) {
/* 401 */           i v4Params = new i();
/* 402 */           a((jp.cssj.sakae.pdf.f.a)v4Params);
/* 403 */           switch (B.aG.a((m)this)) {
/*     */             case 1:
/* 405 */               v4Params.a((short)1);
/*     */               break;
/*     */             case 2:
/* 408 */               if (params.h() >= 1600) {
/* 409 */                 v4Params.a((short)2); break;
/*     */               } 
/* 411 */               a((short)10258, B.aG.a, "AESV2", "1.5");
/*     */               break;
/*     */ 
/*     */             
/*     */             default:
/* 416 */               throw new IllegalStateException();
/*     */           } 
/* 418 */           int length = B.aq.a((m)this);
/*     */           try {
/* 420 */             v4Params.a(length);
/* 421 */           } catch (IllegalArgumentException illegalArgumentException) {
/* 422 */             a((short)10258, B.aq.a, 
/* 423 */                 String.valueOf(length), "V4 Encryption");
/*     */           } 
/* 425 */           f f1 = v4Params.d();
/* 426 */           a((d)f1);
/* 427 */           a((e)f1);
/* 428 */           params.a((jp.cssj.sakae.pdf.f.a)v4Params); break;
/*     */         } 
/* 430 */         a((short)10258, B.af.a, "v4", "1.4");
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       default:
/* 436 */         throw new IllegalStateException();
/*     */     } 
/*     */     
/* 439 */     j vp = params.p();
/* 440 */     vp.a(B.aM.a((m)this));
/* 441 */     vp.b(B.aN.a((m)this));
/* 442 */     vp.c(B.aO.a((m)this));
/* 443 */     vp.d(B.aP.a((m)this));
/* 444 */     vp.e(B.aQ.a((m)this));
/* 445 */     if (B.aR.a((m)this)) {
/* 446 */       if (params.h() >= 1400) {
/* 447 */         vp.f(true);
/*     */       } else {
/* 449 */         a((short)10258, B.aR.a, "true", "1.3");
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 454 */     vp.b(
/* 455 */         (byte)B.aS.a((m)this));
/*     */     
/* 457 */     byte printScaling = (byte)B.aT.a((m)this);
/* 458 */     if (printScaling != 2) {
/* 459 */       if (params.h() >= 1600) {
/* 460 */         vp.g(printScaling);
/*     */       } else {
/* 462 */         a((short)10258, B.aT.a, 
/*     */             
/* 464 */             a(B.aT.a), "1.5");
/*     */       } 
/*     */     }
/*     */     
/* 468 */     byte duplex = (byte)B.aU.a((m)this);
/* 469 */     if (duplex != 1) {
/* 470 */       if (params.h() >= 1700) {
/* 471 */         vp.h(duplex);
/*     */       } else {
/* 473 */         a((short)10258, B.aU.a, 
/*     */             
/* 475 */             a(B.aU.a), "1.6");
/*     */       } 
/*     */     }
/*     */     
/* 479 */     if (B.aV.a((m)this)) {
/* 480 */       if (params.h() >= 1700) {
/* 481 */         vp.g(true);
/*     */       } else {
/* 483 */         a((short)10258, B.aV.a, "true", "1.6");
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 488 */     String pageRange = B.aW.a((m)this);
/* 489 */     if (pageRange != null) {
/* 490 */       if (params.h() >= 1700) {
/* 491 */         ArrayIntList arrayIntList = new ArrayIntList();
/*     */         try {
/* 493 */           for (StringTokenizer st = new StringTokenizer(pageRange, ", "); st.hasMoreTokens(); ) {
/* 494 */             String token = st.nextToken();
/* 495 */             int hyphen = token.indexOf('-');
/* 496 */             if (hyphen == -1) {
/* 497 */               int page = Integer.parseInt(token);
/* 498 */               arrayIntList.add(page);
/* 499 */               arrayIntList.add(page); continue;
/*     */             } 
/* 501 */             int i = Integer.parseInt(token.substring(0, hyphen));
/* 502 */             int k = Integer.parseInt(token.substring(hyphen + 1));
/* 503 */             arrayIntList.add(i);
/* 504 */             arrayIntList.add(k);
/*     */           } 
/*     */           
/* 507 */           vp.a(arrayIntList.toArray());
/* 508 */         } catch (NumberFormatException numberFormatException) {
/* 509 */           a((short)10244, B.aW.a, pageRange);
/*     */         } 
/*     */       } else {
/*     */         
/* 513 */         a((short)10258, B.aW.a, pageRange, "1.6");
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 518 */     int numCopies = B.aX.a((m)this);
/* 519 */     if (numCopies != 0) {
/* 520 */       if (params.h() >= 1700) {
/*     */         try {
/* 522 */           vp.a(numCopies);
/* 523 */         } catch (IllegalArgumentException illegalArgumentException) {
/* 524 */           a((short)10244, B.aX.a, 
/* 525 */               String.valueOf(numCopies));
/*     */         } 
/*     */       } else {
/* 528 */         a((short)10258, B.aX.a, 
/* 529 */             String.valueOf(numCopies), "1.6");
/*     */       } 
/*     */     }
/*     */     
/* 533 */     String javaScript = B.aY.a((m)this);
/* 534 */     if (javaScript != null) {
/* 535 */       if (params.h() >= 1300) {
/* 536 */         params.a((jp.cssj.sakae.pdf.a.a)new b(javaScript));
/*     */       } else {
/* 538 */         a((short)10258, B.aY.a, 
/* 539 */             String.valueOf(numCopies), "1.2");
/*     */       } 
/*     */     }
/*     */     
/* 543 */     b b1 = new b(d.a, "application/pdf");
/* 544 */     this.w = this.u.a((jp.cssj.e.a)b1);
/* 545 */     this.y = (j)new n(this.w, params);
/* 546 */     e(r());
/*     */   }
/*     */   
/*     */   private void a(jp.cssj.sakae.pdf.f.a params) {
/* 550 */     params.b(B.ag.a((m)this));
/* 551 */     params.a(B.ah.a((m)this));
/*     */   }
/*     */   
/*     */   private void a(d r2p) {
/* 555 */     r2p.d(B.ai.a((m)this));
/* 556 */     r2p.c(B.aj.a((m)this));
/* 557 */     r2p.b(B.ak.a((m)this));
/* 558 */     r2p.a(B.al.a((m)this));
/*     */   }
/*     */   
/*     */   private void a(e r3p) {
/* 562 */     r3p.g(B.am.a((m)this));
/* 563 */     r3p.f(B.an.a((m)this));
/* 564 */     r3p.e(B.ao.a((m)this));
/* 565 */     r3p.h(B.ap.a((m)this));
/*     */   }
/*     */   
/*     */   public e q() {
/*     */     try {
/* 570 */       x();
/* 571 */     } catch (IOException iOException) {
/* 572 */       throw new c(iOException);
/*     */     } 
/* 574 */     return this.y.b();
/*     */   }
/*     */   
/*     */   public void b(String name, String content) {
/* 578 */     if (name.equalsIgnoreCase("author")) {
/* 579 */       this.A.a(content);
/* 580 */     } else if (name.equalsIgnoreCase("creator") || name.equalsIgnoreCase("generator")) {
/* 581 */       this.A.b(content);
/* 582 */     } else if (name.equalsIgnoreCase("keywords")) {
/* 583 */       this.A.c(content);
/* 584 */     } else if (name.equalsIgnoreCase("producer")) {
/* 585 */       this.A.d(content);
/* 586 */     } else if (name.equalsIgnoreCase("subject") || name.equalsIgnoreCase("description")) {
/* 587 */       this.A.e(content);
/* 588 */     } else if (name.equalsIgnoreCase("title")) {
/* 589 */       a((short)6149, content);
/* 590 */       this.A.f(content);
/* 591 */       (b().a()).a = content;
/*     */     } 
/*     */   } public b c(b source) throws IOException {
/*     */     b image;
/*     */     jp.cssj.sakae.c.b.a.a a1;
/* 596 */     x();
/*     */     
/*     */     try {
/* 599 */       image = this.y.a(source);
/* 600 */     } catch (IOException iOException) {
/* 601 */       image = b(source);
/*     */     } 
/* 603 */     AffineTransform pixelToUnit = o();
/* 604 */     if (!pixelToUnit.isIdentity()) {
/* 605 */       a1 = new jp.cssj.sakae.c.b.a.a(image, pixelToUnit);
/*     */     }
/* 607 */     return (b)a1;
/*     */   }
/*     */   
/*     */   public boolean u() {
/* 611 */     return (this.u == b.a);
/*     */   }
/*     */   
/*     */   public b s() {
/* 615 */     b((byte)2);
/*     */     try {
/* 617 */       x();
/* 618 */       if (u()) {
/* 619 */         this.D = true;
/* 620 */         return null;
/*     */       } 
/* 622 */       double w = this.a;
/* 623 */       double h = this.b;
/* 624 */       if (w < 3.0D) {
/* 625 */         a((short)14338, "3.0(width)>", 
/* 626 */             String.valueOf(w));
/* 627 */         w = 3.0D;
/*     */       } 
/* 629 */       if (h < 3.0D) {
/* 630 */         a((short)14338, "3.0(height)>", 
/* 631 */             String.valueOf(h));
/* 632 */         h = 3.0D;
/*     */       } 
/* 634 */       if (w > 14400.0D) {
/* 635 */         a((short)14338, "14400.0(width)>", 
/* 636 */             String.valueOf(w));
/* 637 */         w = 14400.0D;
/*     */       } 
/* 639 */       if (h > 14400.0D) {
/* 640 */         a((short)14338, "14400.0(height)>", 
/* 641 */             String.valueOf(h));
/* 642 */         h = 14400.0D;
/*     */       } 
/*     */ 
/*     */       
/* 646 */       if (this.B == null) {
/* 647 */         String uri = B.aH.a((m)this);
/* 648 */         if (uri != null) {
/* 649 */           if (this.y.a().h() >= 1400) {
/*     */             try {
/* 651 */               b source = b(d.a("UTF-8", uri));
/*     */               try {
/* 653 */                 b image = c(source);
/* 654 */                 this.B = new f(image, null);
/*     */               } finally {
/* 656 */                 a(source);
/*     */               } 
/* 658 */             } catch (Exception exception) {
/* 659 */               t.log(Level.FINE, "Missing image", exception);
/* 660 */               a((short)10257, uri);
/*     */             } 
/*     */           } else {
/* 663 */             a((short)10258, B.aH.a, uri, "1.3");
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 668 */       i i = this.y.b(w, h);
/* 669 */       jp.cssj.sakae.pdf.d.a gc = new jp.cssj.sakae.pdf.d.a((d)i);
/* 670 */       this.D = true;
/* 671 */       if (this.B != null) {
/*     */         
/* 673 */         short mode = B.aI.a((m)this);
/* 674 */         if (mode == 1) {
/* 675 */           if (this.C == null) {
/* 676 */             i out = (i)gc.b();
/* 677 */             this.C = out.d().a(this.a, this.b);
/* 678 */             int flags = 0;
/* 679 */             if (!B.aK.a((m)this)) {
/* 680 */               if (this.y.a().h() >= 1500) {
/* 681 */                 flags |= 0x1;
/*     */               } else {
/* 683 */                 a((short)10258, B.aK.a, "false", "1.4");
/*     */               } 
/*     */             }
/*     */             
/* 687 */             if (!B.aL.a((m)this)) {
/* 688 */               if (this.y.a().h() >= 1500) {
/* 689 */                 flags |= 0x2;
/*     */               } else {
/* 691 */                 a((short)10258, B.aL.a, "false", "1.4");
/*     */               } 
/*     */             }
/*     */             
/* 695 */             if (flags != 0) {
/* 696 */               this.C.c(flags);
/*     */             }
/* 698 */             jp.cssj.sakae.pdf.d.a ggc = new jp.cssj.sakae.pdf.d.a((d)this.C);
/* 699 */             ggc.b(this.B);
/* 700 */             double opacity = B.aJ.a((m)this);
/* 701 */             if (opacity != 1.0D) {
/* 702 */               if (this.y.a().h() == 1412) {
/* 703 */                 a((short)10258, B.aJ.a, 
/* 704 */                     String.valueOf(opacity), "PDF/A-1");
/* 705 */               } else if (this.y.a().h() == 1421) {
/* 706 */                 a((short)10258, B.aJ.a, 
/* 707 */                     String.valueOf(opacity), "PDF/X-1a");
/*     */               } else {
/* 709 */                 ggc.b((float)opacity);
/*     */               } 
/*     */             }
/* 712 */             Rectangle2D mask = new Rectangle2D.Double(0.0D, 0.0D, w, h);
/* 713 */             ggc.b(mask);
/* 714 */             this.C.close();
/*     */           } 
/* 716 */           gc.a((b)this.C);
/*     */         } 
/*     */       } 
/* 719 */       return (b)gc;
/* 720 */     } catch (IOException iOException) {
/* 721 */       throw new c(iOException);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(b gc) throws IOException {
/* 726 */     super.a(gc);
/* 727 */     if (gc == null) {
/*     */       return;
/*     */     }
/* 730 */     jp.cssj.sakae.pdf.d.a pdfGc = (jp.cssj.sakae.pdf.d.a)gc;
/* 731 */     try (i out = (i)pdfGc.b()) {
/* 732 */       if (this.B != null) {
/* 733 */         short mode = B.aI.a((m)this);
/* 734 */         if (mode == 2) {
/*     */           
/* 736 */           Rectangle2D rect = new Rectangle2D.Double(0.0D, 0.0D, this.a, this.b);
/* 737 */           AffineTransform at = gc.o();
/* 738 */           if (at != null) {
/* 739 */             rect = at.createTransformedShape(rect).getBounds2D();
/*     */           }
/* 741 */           c annot = new c(this, at) {
/*     */               public void a(h out, i pageOut) throws IOException {
/* 743 */                 super.a(out, pageOut);
/*     */                 
/* 745 */                 Rectangle2D rect = a().getBounds2D();
/* 746 */                 b group = pageOut.d().a(rect.getWidth(), rect
/* 747 */                     .getHeight());
/* 748 */                 jp.cssj.sakae.pdf.d.a gc = new jp.cssj.sakae.pdf.d.a((d)group);
/* 749 */                 if (this.a != null) {
/* 750 */                   AffineTransform atd = new AffineTransform();
/* 751 */                   atd.scale(this.a.getScaleX(), this.a.getScaleY());
/* 752 */                   gc.a(atd);
/*     */                 } 
/*     */                 
/* 755 */                 gc.b(a.a(this.b));
/* 756 */                 double opacity = B.aJ.a((m)this.b);
/* 757 */                 if (opacity != 1.0D) {
/* 758 */                   b params = pageOut.d().a();
/* 759 */                   if (params.h() == 1412) {
/* 760 */                     this.b.a((short)10258, B.aJ.a, 
/* 761 */                         String.valueOf(opacity), "PDF/A-1");
/*     */                   }
/* 763 */                   else if (params.h() == 1421) {
/* 764 */                     this.b.a((short)10258, B.aJ.a, 
/* 765 */                         String.valueOf(opacity), "PDF/X-1a");
/*     */                   } else {
/*     */                     
/* 768 */                     gc.b((float)opacity);
/*     */                   } 
/*     */                 } 
/*     */                 
/* 772 */                 Rectangle2D mask = new Rectangle2D.Double(0.0D, 0.0D, a.b(this.b), a.c(this.b));
/* 773 */                 gc.b(mask);
/* 774 */                 group.close();
/*     */ 
/*     */                 
/* 777 */                 out.a("F");
/* 778 */                 int flags = 0;
/* 779 */                 if (!B.aK.a((m)this.b)) {
/* 780 */                   flags |= 0x20;
/*     */                 }
/* 782 */                 if (B.aL.a((m)this.b)) {
/* 783 */                   flags |= 0x4;
/*     */                 }
/* 785 */                 out.a(flags);
/* 786 */                 out.m();
/*     */                 
/* 788 */                 out.a("AP");
/* 789 */                 out.g();
/* 790 */                 out.a("N");
/* 791 */                 out.b(group.n());
/* 792 */                 out.h();
/* 793 */                 out.m();
/*     */               }
/*     */             };
/* 796 */           annot.a(rect);
/*     */           try {
/* 798 */             out.a((jp.cssj.sakae.pdf.b.a)annot);
/* 799 */           } catch (IOException iOException) {
/* 800 */             throw new c(iOException);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 807 */     b((byte)1);
/*     */   }
/*     */   
/*     */   public jp.cssj.homare.b.g.a b(b gc) {
/* 811 */     if (gc == null) {
/* 812 */       return (jp.cssj.homare.b.g.a)new d((m)this);
/*     */     }
/* 814 */     if (this.c == null) {
/* 815 */       this.c = new b((m)this);
/*     */     }
/* 817 */     this.c.a((jp.cssj.sakae.pdf.d.a)gc);
/* 818 */     return (jp.cssj.homare.b.g.a)this.c;
/*     */   }
/*     */   
/*     */   public void t() throws b, IOException {
/* 822 */     super.t();
/* 823 */     if (!this.D) {
/* 824 */       short code = 14349;
/* 825 */       String mes = jp.cssj.homare.a.a.b((short)14349, null);
/* 826 */       a((short)14349, mes);
/* 827 */       throw new d((byte)2, (short)14349, null, mes);
/*     */     } 
/*     */     
/*     */     try {
/* 831 */       this.y.a().a(this.A);
/*     */ 
/*     */ 
/*     */       
/* 835 */       byte[] buff = new byte[8192];
/* 836 */       for (int i = 0;; i++) {
/* 837 */         URI uri; String prefix = "output.pdf.attachments." + i + ".";
/* 838 */         String uriStr = a(prefix + "uri");
/* 839 */         if (uriStr == null) {
/*     */           break;
/*     */         }
/* 842 */         if (this.y.a().h() < 1400) {
/* 843 */           a((short)10258, prefix + "uri", uriStr, "1.3");
/*     */           break;
/*     */         } 
/* 846 */         if (this.y.a().h() == 1412) {
/* 847 */           a((short)10258, prefix + "uri", uriStr, "PDF/A-1");
/*     */           break;
/*     */         } 
/* 850 */         if (this.y.a().h() == 1421) {
/* 851 */           a((short)10258, prefix + "uri", uriStr, "PDF/X-1a");
/*     */           
/*     */           break;
/*     */         } 
/*     */         try {
/* 856 */           uri = d.a(c().c(), uriStr);
/* 857 */         } catch (URISyntaxException e1) {
/* 858 */           a((short)10256, uriStr);
/*     */         } 
/*     */         
/* 861 */         String name = a(prefix + "name");
/* 862 */         jp.cssj.sakae.pdf.a att = new jp.cssj.sakae.pdf.a();
/* 863 */         att.a = a(prefix + "description");
/* 864 */         att.b = a(prefix + "mime-type");
/* 865 */         if (name == null) {
/* 866 */           uriStr = uri.getPath();
/* 867 */           int slash = uriStr.lastIndexOf('/');
/* 868 */           if (slash == -1) {
/* 869 */             name = uriStr;
/*     */           } else {
/* 871 */             name = uriStr.substring(slash + 1);
/*     */           } 
/*     */         } 
/* 874 */         b attachmetSource = null;
/*     */         try {
/*     */         
/* 877 */         } catch (Exception exception) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 895 */       this.y.d();
/* 896 */       this.w.a();
/*     */     } finally {
/* 898 */       this.w.e();
/* 899 */       this.w = null;
/* 900 */       this.y = null;
/* 901 */       this.B = null;
/* 902 */       this.C = null;
/*     */     } 
/* 904 */     this.u.c();
/*     */   }
/*     */   
/*     */   public void v() {
/* 908 */     super.v();
/* 909 */     if (this.w != null) {
/* 910 */       this.w.e();
/* 911 */       this.w = null;
/*     */     } 
/* 913 */     this.y = null;
/* 914 */     this.B = null;
/* 915 */     this.C = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/ua/pdf/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */