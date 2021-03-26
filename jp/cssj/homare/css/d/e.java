/*      */ package jp.cssj.homare.css.d;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.net.URI;
/*      */ import java.net.URISyntaxException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import jp.cssj.e.b;
/*      */ import jp.cssj.homare.b.a;
/*      */ import jp.cssj.homare.b.a.a;
/*      */ import jp.cssj.homare.b.a.b.a;
/*      */ import jp.cssj.homare.b.a.b.b;
/*      */ import jp.cssj.homare.b.a.b.d;
/*      */ import jp.cssj.homare.b.a.b.f;
/*      */ import jp.cssj.homare.b.a.b.g;
/*      */ import jp.cssj.homare.b.a.b.h;
/*      */ import jp.cssj.homare.b.a.b.i;
/*      */ import jp.cssj.homare.b.a.b.j;
/*      */ import jp.cssj.homare.b.a.b.l;
/*      */ import jp.cssj.homare.b.a.b.n;
/*      */ import jp.cssj.homare.b.a.b.o;
/*      */ import jp.cssj.homare.b.a.b.p;
/*      */ import jp.cssj.homare.b.a.b.r;
/*      */ import jp.cssj.homare.b.a.b.s;
/*      */ import jp.cssj.homare.b.a.b.t;
/*      */ import jp.cssj.homare.b.a.b.u;
/*      */ import jp.cssj.homare.b.a.b.v;
/*      */ import jp.cssj.homare.b.a.b.w;
/*      */ import jp.cssj.homare.b.a.c;
/*      */ import jp.cssj.homare.b.a.c.A;
/*      */ import jp.cssj.homare.b.a.c.B;
/*      */ import jp.cssj.homare.b.a.c.C;
/*      */ import jp.cssj.homare.b.a.c.D;
/*      */ import jp.cssj.homare.b.a.c.E;
/*      */ import jp.cssj.homare.b.a.c.F;
/*      */ import jp.cssj.homare.b.a.c.G;
/*      */ import jp.cssj.homare.b.a.c.I;
/*      */ import jp.cssj.homare.b.a.c.J;
/*      */ import jp.cssj.homare.b.a.c.a;
/*      */ import jp.cssj.homare.b.a.c.c;
/*      */ import jp.cssj.homare.b.a.c.f;
/*      */ import jp.cssj.homare.b.a.c.g;
/*      */ import jp.cssj.homare.b.a.c.h;
/*      */ import jp.cssj.homare.b.a.c.i;
/*      */ import jp.cssj.homare.b.a.c.j;
/*      */ import jp.cssj.homare.b.a.c.k;
/*      */ import jp.cssj.homare.b.a.c.m;
/*      */ import jp.cssj.homare.b.a.c.n;
/*      */ import jp.cssj.homare.b.a.c.o;
/*      */ import jp.cssj.homare.b.a.c.p;
/*      */ import jp.cssj.homare.b.a.c.q;
/*      */ import jp.cssj.homare.b.a.c.r;
/*      */ import jp.cssj.homare.b.a.c.s;
/*      */ import jp.cssj.homare.b.a.c.t;
/*      */ import jp.cssj.homare.b.a.c.w;
/*      */ import jp.cssj.homare.b.a.c.y;
/*      */ import jp.cssj.homare.b.a.f;
/*      */ import jp.cssj.homare.b.a.j;
/*      */ import jp.cssj.homare.b.a.o;
/*      */ import jp.cssj.homare.b.b.e;
/*      */ import jp.cssj.homare.b.c.g;
/*      */ import jp.cssj.homare.b.d.b;
/*      */ import jp.cssj.homare.b.e.a;
/*      */ import jp.cssj.homare.b.f.d;
/*      */ import jp.cssj.homare.b.f.f;
/*      */ import jp.cssj.homare.b.g.a;
/*      */ import jp.cssj.homare.b.g.b;
/*      */ import jp.cssj.homare.css.a;
/*      */ import jp.cssj.homare.css.a.b;
/*      */ import jp.cssj.homare.css.b.a;
/*      */ import jp.cssj.homare.css.b.b;
/*      */ import jp.cssj.homare.css.c;
/*      */ import jp.cssj.homare.css.d;
/*      */ import jp.cssj.homare.css.e.b;
/*      */ import jp.cssj.homare.css.e.l;
/*      */ import jp.cssj.homare.css.f;
/*      */ import jp.cssj.homare.css.f.C;
/*      */ import jp.cssj.homare.css.f.H;
/*      */ import jp.cssj.homare.css.f.M;
/*      */ import jp.cssj.homare.css.f.N;
/*      */ import jp.cssj.homare.css.f.P;
/*      */ import jp.cssj.homare.css.f.Q;
/*      */ import jp.cssj.homare.css.f.V;
/*      */ import jp.cssj.homare.css.f.X;
/*      */ import jp.cssj.homare.css.f.a;
/*      */ import jp.cssj.homare.css.f.ab;
/*      */ import jp.cssj.homare.css.f.ad;
/*      */ import jp.cssj.homare.css.f.ae;
/*      */ import jp.cssj.homare.css.f.b.c;
/*      */ import jp.cssj.homare.css.f.b.g;
/*      */ import jp.cssj.homare.css.f.b.h;
/*      */ import jp.cssj.homare.css.f.c;
/*      */ import jp.cssj.homare.css.f.k;
/*      */ import jp.cssj.homare.css.f.o;
/*      */ import jp.cssj.homare.css.f.p;
/*      */ import jp.cssj.homare.css.f.q;
/*      */ import jp.cssj.homare.css.f.r;
/*      */ import jp.cssj.homare.css.f.t;
/*      */ import jp.cssj.homare.css.f.u;
/*      */ import jp.cssj.homare.css.l;
/*      */ import jp.cssj.homare.impl.a.c.A;
/*      */ import jp.cssj.homare.impl.a.c.C;
/*      */ import jp.cssj.homare.impl.a.c.D;
/*      */ import jp.cssj.homare.impl.a.c.E;
/*      */ import jp.cssj.homare.impl.a.c.F;
/*      */ import jp.cssj.homare.impl.a.c.G;
/*      */ import jp.cssj.homare.impl.a.c.H;
/*      */ import jp.cssj.homare.impl.a.c.I;
/*      */ import jp.cssj.homare.impl.a.c.L;
/*      */ import jp.cssj.homare.impl.a.c.M;
/*      */ import jp.cssj.homare.impl.a.c.N;
/*      */ import jp.cssj.homare.impl.a.c.O;
/*      */ import jp.cssj.homare.impl.a.c.P;
/*      */ import jp.cssj.homare.impl.a.c.Q;
/*      */ import jp.cssj.homare.impl.a.c.R;
/*      */ import jp.cssj.homare.impl.a.c.S;
/*      */ import jp.cssj.homare.impl.a.c.T;
/*      */ import jp.cssj.homare.impl.a.c.U;
/*      */ import jp.cssj.homare.impl.a.c.V;
/*      */ import jp.cssj.homare.impl.a.c.W;
/*      */ import jp.cssj.homare.impl.a.c.X;
/*      */ import jp.cssj.homare.impl.a.c.Y;
/*      */ import jp.cssj.homare.impl.a.c.Z;
/*      */ import jp.cssj.homare.impl.a.c.a.A;
/*      */ import jp.cssj.homare.impl.a.c.a.B;
/*      */ import jp.cssj.homare.impl.a.c.a.C;
/*      */ import jp.cssj.homare.impl.a.c.a.D;
/*      */ import jp.cssj.homare.impl.a.c.a.F;
/*      */ import jp.cssj.homare.impl.a.c.a.G;
/*      */ import jp.cssj.homare.impl.a.c.a.H;
/*      */ import jp.cssj.homare.impl.a.c.a.J;
/*      */ import jp.cssj.homare.impl.a.c.a.b;
/*      */ import jp.cssj.homare.impl.a.c.a.c;
/*      */ import jp.cssj.homare.impl.a.c.a.d;
/*      */ import jp.cssj.homare.impl.a.c.a.g;
/*      */ import jp.cssj.homare.impl.a.c.a.h;
/*      */ import jp.cssj.homare.impl.a.c.a.i;
/*      */ import jp.cssj.homare.impl.a.c.a.k;
/*      */ import jp.cssj.homare.impl.a.c.a.l;
/*      */ import jp.cssj.homare.impl.a.c.a.m;
/*      */ import jp.cssj.homare.impl.a.c.a.n;
/*      */ import jp.cssj.homare.impl.a.c.a.p;
/*      */ import jp.cssj.homare.impl.a.c.a.q;
/*      */ import jp.cssj.homare.impl.a.c.a.r;
/*      */ import jp.cssj.homare.impl.a.c.a.s;
/*      */ import jp.cssj.homare.impl.a.c.a.u;
/*      */ import jp.cssj.homare.impl.a.c.a.w;
/*      */ import jp.cssj.homare.impl.a.c.a.y;
/*      */ import jp.cssj.homare.impl.a.c.aa;
/*      */ import jp.cssj.homare.impl.a.c.ab;
/*      */ import jp.cssj.homare.impl.a.c.ac;
/*      */ import jp.cssj.homare.impl.a.c.ad;
/*      */ import jp.cssj.homare.impl.a.c.ae;
/*      */ import jp.cssj.homare.impl.a.c.af;
/*      */ import jp.cssj.homare.impl.a.c.ag;
/*      */ import jp.cssj.homare.impl.a.c.ah;
/*      */ import jp.cssj.homare.impl.a.c.ai;
/*      */ import jp.cssj.homare.impl.a.c.aj;
/*      */ import jp.cssj.homare.impl.a.c.ak;
/*      */ import jp.cssj.homare.impl.a.c.al;
/*      */ import jp.cssj.homare.impl.a.c.am;
/*      */ import jp.cssj.homare.impl.a.c.an;
/*      */ import jp.cssj.homare.impl.a.c.ao;
/*      */ import jp.cssj.homare.impl.a.c.ap;
/*      */ import jp.cssj.homare.impl.a.c.aq;
/*      */ import jp.cssj.homare.impl.a.c.as;
/*      */ import jp.cssj.homare.impl.a.c.at;
/*      */ import jp.cssj.homare.impl.a.c.au;
/*      */ import jp.cssj.homare.impl.a.c.av;
/*      */ import jp.cssj.homare.impl.a.c.aw;
/*      */ import jp.cssj.homare.impl.a.c.ax;
/*      */ import jp.cssj.homare.impl.a.c.ay;
/*      */ import jp.cssj.homare.impl.a.c.b.b;
/*      */ import jp.cssj.homare.impl.a.c.b.f;
/*      */ import jp.cssj.homare.impl.a.c.b.g;
/*      */ import jp.cssj.homare.impl.a.c.b.h;
/*      */ import jp.cssj.homare.impl.a.c.c.b;
/*      */ import jp.cssj.homare.impl.a.c.g;
/*      */ import jp.cssj.homare.impl.a.c.h;
/*      */ import jp.cssj.homare.impl.a.c.i;
/*      */ import jp.cssj.homare.impl.a.c.j;
/*      */ import jp.cssj.homare.impl.a.c.k;
/*      */ import jp.cssj.homare.impl.a.c.l;
/*      */ import jp.cssj.homare.impl.a.c.m;
/*      */ import jp.cssj.homare.impl.a.c.n;
/*      */ import jp.cssj.homare.impl.a.c.o;
/*      */ import jp.cssj.homare.impl.a.c.p;
/*      */ import jp.cssj.homare.impl.a.c.q;
/*      */ import jp.cssj.homare.impl.a.c.r;
/*      */ import jp.cssj.homare.impl.a.c.s;
/*      */ import jp.cssj.homare.impl.a.c.t;
/*      */ import jp.cssj.homare.impl.a.c.u;
/*      */ import jp.cssj.homare.impl.a.c.v;
/*      */ import jp.cssj.homare.impl.a.c.y;
/*      */ import jp.cssj.homare.impl.a.c.z;
/*      */ import jp.cssj.homare.ua.a;
/*      */ import jp.cssj.homare.ua.a.B;
/*      */ import jp.cssj.homare.ua.h;
/*      */ import jp.cssj.homare.ua.i;
/*      */ import jp.cssj.homare.ua.k;
/*      */ import jp.cssj.homare.ua.m;
/*      */ import jp.cssj.homare.xml.c.a;
/*      */ import jp.cssj.sakae.c.b;
/*      */ import jp.cssj.sakae.c.b.b;
/*      */ import jp.cssj.sakae.c.c;
/*      */ import jp.cssj.sakae.c.c.b;
/*      */ 
/*      */ public class e implements e {
/*      */   private static final boolean e = false;
/*      */   private static final Logger f;
/*      */   private static final ae g;
/*      */   private static final u h;
/*      */   private static final u i;
/*      */   private static final u j;
/*      */   private final m k;
/*      */   private final a l;
/*      */   private final b m;
/*      */   private final a[] n;
/*      */   private l o;
/*      */   private c p;
/*      */   private a q;
/*      */   private int r;
/*      */   private int s;
/*      */   private f t;
/*      */   private g u;
/*      */   private byte v;
/*      */   private boolean w;
/*      */   private boolean x;
/*      */   private boolean y;
/*      */   private boolean z;
/*      */   private int A;
/*      */   private int B;
/*      */   private final List<int[]> C;
/*      */   private a D;
/*      */   private final Map<a, String[]> E;
/*      */   private final Map<a, b> F;
/*      */   private final Map<String, b> G;
/*      */   private final List<b> H;
/*      */   private d I;
/*      */   private static final byte J = 1;
/*      */   private byte K;
/*      */   
/*      */   static {
/*  249 */     f = Logger.getLogger(e.class.getName());
/*      */     
/*  251 */     g = new ae(new ad[] { (ad)new V("\n") });
/*      */     
/*  253 */     h = u.a(1.618D);
/*  254 */     i = u.a(1.414D);
/*  255 */     j = u.a(1.4D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public e(l styleContext, m ua, b imposition) {
/*      */     a[] margins;
/*  263 */     this.q = null;
/*  264 */     this.r = 0;
/*  265 */     this.s = Integer.MAX_VALUE;
/*      */     
/*  267 */     this.t = null;
/*  268 */     this.u = null;
/*  269 */     this.v = 1;
/*  270 */     this.w = false;
/*  271 */     this.x = false;
/*  272 */     this.y = false;
/*  273 */     this.z = false;
/*      */     
/*  275 */     this.A = 0;
/*  276 */     this.B = 0;
/*      */ 
/*      */     
/*  279 */     this.C = (List)new ArrayList<>();
/*      */     
/*  281 */     this.D = null;
/*      */     
/*  283 */     this.E = (Map)new HashMap<>();
/*  284 */     this.F = new HashMap<>();
/*  285 */     this.G = new HashMap<>();
/*  286 */     this.H = new ArrayList<>();
/*      */     
/*  288 */     this.I = null;
/*      */ 
/*      */ 
/*      */     
/*  292 */     this.K = 0;
/*      */ 
/*      */     
/*  295 */     this.o = styleContext;
/*  296 */     this.k = ua;
/*  297 */     this.r = ua.b().c();
/*  298 */     this.m = imposition;
/*  299 */     this.l = new a(this);
/*      */     
/*  301 */     byte pageMode = 0;
/*      */     
/*  303 */     if (B.G.a(ua)) {
/*  304 */       pageMode = (byte)(pageMode | 0x1);
/*      */     }
/*      */ 
/*      */     
/*  308 */     if (B.I.a(ua)) {
/*  309 */       pageMode = (byte)(pageMode | 0x2);
/*      */     }
/*  311 */     this.l.a(pageMode);
/*      */ 
/*      */ 
/*      */     
/*  315 */     String s = B.s.a(ua);
/*  316 */     a length = l.b(this.k, false, s);
/*  317 */     if (length != null) {
/*  318 */       double d1 = length.c();
/*  319 */       this.m.c(d1);
/*  320 */       if (this.m.v() != null) {
/*  321 */         this.m.a(this.m.v() + " / width " + s);
/*      */       }
/*      */     } else {
/*  324 */       this.k.a((short)10244, B.s.a, s);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  330 */     s = B.t.a(ua);
/*  331 */     length = l.b(this.k, false, s);
/*  332 */     if (length != null) {
/*  333 */       double d1 = length.c();
/*  334 */       this.m.d(d1);
/*  335 */       if (this.m.v() != null) {
/*  336 */         this.m.a(this.m.v() + " / height " + s);
/*      */       }
/*      */     } else {
/*  339 */       this.k.a((short)10244, B.t.a, s);
/*      */     } 
/*      */     
/*  342 */     jp.cssj.homare.b.f.e.a(this.k, this.m);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  347 */     String str1 = B.u.a(ua);
/*  348 */     if (str1 != null) {
/*  349 */       String[] values = str1.split("[\\s]+");
/*  350 */       if (values.length <= 0 || values.length > 4) {
/*  351 */         ua.a((short)10244, B.u.a, str1);
/*  352 */         margins = null;
/*      */       } else {
/*  354 */         margins = new a[values.length];
/*  355 */         for (int i = 0; i < values.length; i++) {
/*  356 */           a a1 = l.b(ua, false, values[i]);
/*  357 */           if (a1 != null) {
/*  358 */             margins[i] = a1;
/*      */           } else {
/*  360 */             ua.a((short)10244, B.u.a, str1);
/*  361 */             margins = null;
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*  367 */       margins = null;
/*      */     } 
/*  369 */     this.n = margins;
/*      */ 
/*      */ 
/*      */     
/*  373 */     this.s = B.N.a(ua);
/*      */ 
/*      */     
/*  376 */     for (d.a cpc : this.o.b()) {
/*  377 */       c style = c.a(this.k, null, a.s);
/*  378 */       style.a(G.a, (ad)t.r, (byte)1);
/*  379 */       style.a(jp.cssj.homare.impl.a.c.b.e.a, (ad)H.a, (byte)1);
/*  380 */       style.a(g.a, (ad)H.a, (byte)1);
/*  381 */       style.a(y.a, (ad)N.j, (byte)1);
/*  382 */       byte[] pages = null;
/*  383 */       if (cpc.b != null) {
/*  384 */         String ident = cpc.b;
/*  385 */         if (ident.equals("first")) {
/*  386 */           pages = new byte[] { 1 };
/*  387 */         } else if (ident.equals("right")) {
/*  388 */           pages = new byte[] { 3 };
/*  389 */         } else if (ident.equals("left")) {
/*  390 */           pages = new byte[] { 2 };
/*  391 */         } else if (ident.equals("single")) {
/*  392 */           pages = new byte[] { 0 };
/*      */         } 
/*      */       } 
/*  395 */       cpc.c.a(style);
/*  396 */       b pc = new b(this.o, pages, cpc.a);
/*  397 */       pc.a(style);
/*  398 */       pc.b(style);
/*  399 */       this.G.put(pc.c, pc);
/*      */     } 
/*      */   }
/*      */   
/*      */   public m a() {
/*  404 */     return this.k;
/*      */   }
/*      */   
/*      */   public a b() {
/*  408 */     return this.q;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(jp.cssj.homare.b.a.c.e pos, c style) {
/*  418 */     if (y.c(style) != 0) {
/*  419 */       pos.d = e(style);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(r pos, c style) {
/*  430 */     a((jp.cssj.homare.b.a.c.e)pos, style);
/*  431 */     pos.a = as.c(style);
/*  432 */     pos.b = O.c(style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(a pos, c style) {
/*  442 */     ad top = aq.c(style);
/*  443 */     ad right = ak.c(style);
/*  444 */     ad bottom = t.c(style);
/*  445 */     ad left = M.c(style);
/*  446 */     pos.a = b.a(top, right, bottom, left);
/*      */     
/*  448 */     switch (y.c(style)) {
/*      */       case 2:
/*  450 */         pos.c = 1;
/*  451 */         switch (G.c(style)) {
/*      */           case 3:
/*      */           case 7:
/*  454 */             pos.b = 2;
/*      */             break;
/*      */           
/*      */           case 1:
/*      */           case 4:
/*      */           case 6:
/*  460 */             pos.b = 1;
/*      */             break;
/*      */         } 
/*  463 */         throw new IllegalStateException(style.a(G.a).toString());
/*      */ 
/*      */       
/*      */       case 3:
/*  467 */         pos.c = 2;
/*  468 */         pos.b = 1;
/*      */         break;
/*      */       case 4:
/*  471 */         pos.c = 3;
/*  472 */         pos.b = 1;
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(p pos, c style) {
/*  484 */     a((jp.cssj.homare.b.a.c.e)pos, style);
/*  485 */     pos.c = A.c(style);
/*  486 */     pos.a = a(ah.c(style), style);
/*  487 */     pos.b = a(ag.c(style), style);
/*  488 */     pos.h = r.c(style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(o pos, c style) {
/*  498 */     a((jp.cssj.homare.b.a.c.e)pos, style);
/*  499 */     byte floating = v.c(style);
/*  500 */     switch (floating) {
/*      */       case 1:
/*      */       case 3:
/*  503 */         pos.e = 1;
/*      */         break;
/*      */       
/*      */       case 2:
/*      */       case 4:
/*  508 */         pos.e = 2;
/*      */         break;
/*      */       
/*      */       default:
/*  512 */         throw new IllegalStateException();
/*      */     } 
/*  514 */     pos.c = A.c(style);
/*  515 */     pos.a = a(ah.c(style), style);
/*  516 */     pos.b = a(ag.c(style), style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(y params, c style) {
/*  526 */     params.al = style.a();
/*  527 */     if (at.c(style) == 0) {
/*  528 */       params.ao = u.c(style);
/*      */     } else {
/*  530 */       params.ao = 0.0F;
/*      */     } 
/*  532 */     params.ap = G.c(style);
/*  533 */     params.aq = H.c(style);
/*  534 */     params.an = ay.d(style);
/*  535 */     if (params.an == 1) {
/*  536 */       params.am = ay.c(style);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(f params, c style) {
/*  547 */     a((y)params, style);
/*  548 */     params.K = au.c(style);
/*  549 */     params.L = J.c(style);
/*  550 */     params.M = B.c(style);
/*  551 */     params.N = an.c(style);
/*  552 */     params.O = 1.0D / style.b().d((byte)4) / 2.0D;
/*  553 */     params.P = F.c(style);
/*  554 */     params.Q = D.c(style);
/*  555 */     params.R = C.c(style);
/*  556 */     params.H = N.c(style);
/*  557 */     params.I = ax.c(style);
/*  558 */     params.J = ap.c(style);
/*  559 */     params.C = style.i();
/*  560 */     params.F = this.k.q();
/*      */     
/*  562 */     a lang = b.a((style.a()).G);
/*  563 */     params.G = lang.a(style);
/*  564 */     params.E = F.d(style);
/*  565 */     params.D = c.c(style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(b image, C params, c style) {
/*  576 */     a((f)params, style);
/*  577 */     params.a = image;
/*      */     
/*  579 */     params.b = b.a(aw.c(style), L.c(style));
/*  580 */     params.c = b.a(Z.c(style), Y.c(style));
/*  581 */     params.d = b.a(X.c(style), W.c(style));
/*  582 */     params.e = i.c(style);
/*      */     
/*  584 */     params.f = d(style);
/*  585 */     params.M = u.c(style);
/*  586 */     params.g = O.c(style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(c params, c style) {
/*  596 */     a((f)params, style);
/*  597 */     params.h = ao.c(style);
/*  598 */     params.f = am.c(style);
/*  599 */     params.g = w.c(style);
/*  600 */     params.i = O.c(style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(n params, c style) {
/*  610 */     a((c)params, style);
/*  611 */     if (style.a() == a.q) {
/*  612 */       params.S = b(style);
/*      */     }
/*      */   }
/*      */   
/*      */   private void a(i params, c style) {
/*  617 */     a((c)params, style);
/*  618 */     params.U = ai.c(style);
/*  619 */     params.V = (byte)Math.min(127, aa.c(style));
/*  620 */     params.W = (byte)Math.min(127, av.c(style));
/*      */ 
/*      */     
/*  623 */     this.o.a(a.q);
/*  624 */     f declaration = this.o.a(null);
/*  625 */     this.o.a();
/*  626 */     if (declaration != null) {
/*  627 */       c firstLineStyle = c.a(this.k, this.p, a.q);
/*  628 */       declaration.a(firstLineStyle);
/*  629 */       if (G.c(firstLineStyle) != 0) {
/*  630 */         params.T = new n();
/*  631 */         a(params.T, firstLineStyle);
/*      */       } 
/*      */     } 
/*      */     
/*  635 */     params.X = b.a(aw.c(style), L.c(style));
/*  636 */     params.Y = b.a(Z.c(style), Y.c(style));
/*  637 */     params.Z = b.a(X.c(style), W.c(style));
/*  638 */     params.aa = i.c(style);
/*      */     
/*  640 */     params.ab = ab.c(style);
/*  641 */     params.S = d(style);
/*      */     
/*  643 */     byte columnCount = (byte)Math.min(127, k.c(style));
/*  644 */     double columnWidth = s.c(style);
/*  645 */     if (columnCount >= 2 || !jp.cssj.homare.b.f.e.a(columnWidth)) {
/*  646 */       params
/*      */         
/*  648 */         .ac = new k(columnCount, columnWidth, m.c(style), j.a(p.c(style), q.c(style), n.c(style)), l.c(style));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(q params, c style) {
/*  659 */     a((f)params, style);
/*  660 */     params.a = d(style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(G params, c style) {
/*  670 */     a((i)params, style);
/*  671 */     params.av = p.c(style);
/*  672 */     params.aw = p.d(style);
/*  673 */     params.ax = i.c(style);
/*  674 */     params.ay = al.c(style);
/*      */   }
/*      */   
/*      */   private void a(s params, c style) {
/*  678 */     a((y)params, style);
/*  679 */     params.a = b(style);
/*  680 */     params.b = c(style);
/*  681 */     params.f = ai.c(style);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void a(D pos, c style) {
/*  691 */     a((p)pos, style);
/*  692 */     switch (z.c(style)) {
/*      */       case 1:
/*      */       case 3:
/*  695 */         pos.v = 1;
/*      */         return;
/*      */       case 2:
/*      */       case 4:
/*  699 */         pos.v = 2;
/*      */         return;
/*      */     } 
/*  702 */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */   
/*      */   private void a(s params, I pos, c style, byte rowGroupType) {
/*  707 */     a(params, style);
/*  708 */     if (jp.cssj.homare.b.f.e.a(c.c(style.c()))) {
/*  709 */       params.c = aw.d(style);
/*  710 */       params.d = Z.d(style);
/*  711 */       params.e = X.d(style);
/*      */     } else {
/*  713 */       params.c = L.d(style);
/*  714 */       params.d = Y.d(style);
/*  715 */       params.e = W.d(style);
/*      */     } 
/*  717 */     pos.c = rowGroupType;
/*  718 */     pos.a = a(ah.c(style), style);
/*  719 */     pos.b = a(ag.c(style), style);
/*      */   }
/*      */   
/*      */   private void a(s params, F pos, c style) {
/*  723 */     a(params, style);
/*  724 */     if (jp.cssj.homare.b.f.e.a(c.c(style.c()))) {
/*  725 */       params.c = L.d(style);
/*  726 */       params.d = Y.d(style);
/*  727 */       params.e = W.d(style);
/*      */     } else {
/*  729 */       params.c = aw.d(style);
/*  730 */       params.d = Z.d(style);
/*  731 */       params.e = X.d(style);
/*      */     } 
/*      */     
/*  734 */     a ce = style.a();
/*  735 */     if (ce.H == null) {
/*      */       return;
/*      */     }
/*  738 */     String span = ce.H.getValue(a.I.b);
/*  739 */     if (span == null) {
/*      */       return;
/*      */     }
/*      */     try {
/*  743 */       pos.a = Integer.parseInt(span);
/*  744 */       if (pos.a <= 0) {
/*  745 */         pos.a = 1;
/*      */       }
/*  747 */     } catch (NumberFormatException numberFormatException) {
/*  748 */       pos.a = 1;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void a(s params, J pos, c style) {
/*  753 */     a(params, style);
/*  754 */     if (jp.cssj.homare.b.f.e.a(c.c(style.c()))) {
/*  755 */       params.c = aw.d(style);
/*  756 */       params.d = Z.d(style);
/*  757 */       params.e = X.d(style);
/*      */     } else {
/*  759 */       params.c = L.d(style);
/*  760 */       params.d = Y.d(style);
/*  761 */       params.e = W.d(style);
/*      */     } 
/*  763 */     pos.a = a(ah.c(style), style);
/*  764 */     pos.b = a(ag.c(style), style);
/*      */   }
/*      */   
/*      */   private void a(E pos, c style) {
/*  768 */     a((jp.cssj.homare.b.a.c.e)pos, style);
/*  769 */     pos.f = H.c(style);
/*  770 */     pos.g = as.d(style);
/*      */     
/*  772 */     a ce = style.a();
/*  773 */     if (ce.H != null) {
/*  774 */       String colspan = ce.H.getValue(a.J.b);
/*  775 */       if (colspan != null) {
/*      */         try {
/*  777 */           pos.c = Integer.parseInt(colspan);
/*  778 */           if (pos.c <= 0) {
/*  779 */             pos.c = 1;
/*      */           }
/*  781 */         } catch (NumberFormatException numberFormatException) {
/*  782 */           pos.c = 1;
/*      */         } 
/*      */       }
/*  785 */       String rowspan = ce.H.getValue(a.K.b);
/*  786 */       if (rowspan != null) {
/*      */         try {
/*  788 */           pos.e = Integer.parseInt(rowspan);
/*  789 */           if (pos.e <= 0) {
/*  790 */             pos.e = 1;
/*      */           }
/*  792 */         } catch (NumberFormatException numberFormatException) {
/*  793 */           pos.e = 1;
/*      */         } 
/*      */       }
/*      */     } 
/*  797 */     pos.a = a(ah.c(style), style);
/*  798 */     pos.b = a(ag.c(style), style);
/*  799 */     if (y.c(style) != 0) {
/*  800 */       pos.d = e(style);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private g b(c style) {
/*      */     h backgroundImage;
/*  811 */     b image = c.c(style);
/*      */     
/*  813 */     if (image != null) {
/*  814 */       backgroundImage = h.a(image, jp.cssj.homare.impl.a.c.e.c(style), 
/*  815 */           a.c(style), d.c(style), b.a(style, image));
/*      */     } else {
/*  817 */       backgroundImage = null;
/*      */     } 
/*  819 */     g background = g.a((b)b.c(style), backgroundImage);
/*  820 */     return background;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private A c(c style) {
/*  830 */     j top = j.a(r.c(style), s.c(style), 
/*  831 */         q.c(style));
/*  832 */     j right = j.a(n.c(style), o.c(style), 
/*  833 */         m.c(style));
/*  834 */     j bottom = j.a(g.c(style), h.c(style), 
/*  835 */         f.c(style));
/*  836 */     j left = j.a(k.c(style), l.c(style), 
/*  837 */         j.c(style));
/*      */     
/*  839 */     A.a topLeft = g.c(style);
/*  840 */     A.a topRight = h.c(style);
/*  841 */     A.a bottomLeft = d.c(style);
/*  842 */     A.a bottomRight = jp.cssj.homare.impl.a.c.a.e.c(style);
/*      */     
/*  844 */     A border = A.a(top, right, bottom, left, topLeft, topRight, bottomLeft, bottomRight);
/*      */     
/*  846 */     return border;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private B d(c style) {
/*  856 */     A border = c(style);
/*  857 */     g background = b(style);
/*      */ 
/*      */     
/*  860 */     if (!this.x) {
/*  861 */       a ce = style.a();
/*  862 */       if (a.c.a(ce) || a.g.a(ce)) {
/*      */ 
/*      */         
/*  865 */         if (this.u == null && background != g.a) {
/*  866 */           this.u = background;
/*  867 */           background = g.a;
/*      */         } 
/*  869 */         this.v = c.c(style);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  876 */     ad top = V.c(style);
/*  877 */     ad right = U.c(style);
/*  878 */     ad bottom = S.c(style);
/*  879 */     ad left = T.c(style);
/*  880 */     t margin = b.a(top, right, bottom, left);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  886 */     ad ad1 = af.c(style);
/*  887 */     ad ad2 = ae.c(style);
/*  888 */     ad ad3 = ac.c(style);
/*  889 */     ad ad4 = ad.c(style);
/*  890 */     t padding = b.a(ad1, ad2, ad3, ad4);
/*      */     
/*  892 */     B frame = B.a(margin, border, background, padding);
/*  893 */     return frame;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private w e(c style) {
/*      */     double x, y;
/*      */     short xType, yType;
/*  903 */     ad top = aq.c(style);
/*  904 */     ad right = ak.c(style);
/*  905 */     ad bottom = t.c(style);
/*  906 */     ad left = M.c(style);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  911 */     switch (top.a()) {
/*      */       case 1000:
/*  913 */         yType = 1;
/*  914 */         y = ((a)top).c();
/*      */         break;
/*      */       case 23:
/*  917 */         yType = 2;
/*  918 */         y = ((M)top).c();
/*      */         break;
/*      */       case 1006:
/*  921 */         switch (bottom.a()) {
/*      */           case 1000:
/*  923 */             yType = 1;
/*  924 */             y = -((a)bottom).c();
/*      */             break;
/*      */           case 23:
/*  927 */             yType = 2;
/*  928 */             y = -((M)bottom).c();
/*      */             break;
/*      */           case 1006:
/*  931 */             yType = 3;
/*  932 */             y = 0.0D;
/*      */             break;
/*      */         } 
/*  935 */         throw new IllegalStateException();
/*      */ 
/*      */       
/*      */       default:
/*  939 */         throw new IllegalStateException();
/*      */     } 
/*      */     
/*  942 */     switch (left.a()) {
/*      */       case 1000:
/*  944 */         xType = 1;
/*  945 */         x = ((a)left).c();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  973 */         return w.a(x, y, xType, yType);case 23: xType = 2; x = ((M)left).c(); return w.a(x, y, xType, yType);case 1006: switch (right.a()) { case 1000: xType = 1; x = -((a)right).c(); return w.a(x, y, xType, yType);case 23: xType = 2; x = -((M)right).c(); return w.a(x, y, xType, yType);case 1006: xType = 3; x = 0.0D; return w.a(x, y, xType, yType); }
/*      */         
/*      */         throw new IllegalStateException();
/*      */     } 
/*      */     throw new IllegalStateException(); } private void a(byte direction, byte progression) {
/*  978 */     if (!this.x) {
/*  979 */       boolean right; this.x = true;
/*  980 */       if (this.t != null) {
/*      */         
/*  982 */         i params = this.t.c_();
/*  983 */         params.E = direction;
/*  984 */         params.D = progression;
/*      */       } 
/*  986 */       this.v = progression;
/*      */ 
/*      */       
/*  989 */       int printMode = B.x.a(this.k);
/*  990 */       if (printMode == 3) {
/*  991 */         right = false;
/*  992 */       } else if (printMode == 4) {
/*  993 */         right = true;
/*      */       } else {
/*  995 */         right = (direction == 2 || progression == 2);
/*      */       } 
/*  997 */       if (right) {
/*  998 */         this.m.a((byte)2);
/*  999 */         this.w = true;
/*      */       } 
/* 1001 */       if (this.t != null) {
/* 1002 */         this.l.a((o)this.t);
/* 1003 */         this.t = null;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private a a(c style, i params, byte position, byte display, byte floating) {
/*      */     f f1;
/* 1011 */     if (position == 2 || position == 3 || position == 4) {
/*      */       
/* 1013 */       a pos = new a();
/* 1014 */       a(pos, style);
/* 1015 */       a a1 = new a(params, pos);
/* 1016 */     } else if (display == 3 || display == 7) {
/* 1017 */       r pos = new r();
/* 1018 */       a(pos, style);
/* 1019 */       if (h.c(style) == 1) {
/* 1020 */         p p = new p(params, pos);
/*      */       } else {
/* 1022 */         h h = new h(params, pos);
/*      */       } 
/* 1024 */     } else if (floating != 0) {
/* 1025 */       o pos = new o();
/* 1026 */       a(pos, style);
/* 1027 */       d d1 = new d(params, pos);
/*      */     } else {
/* 1029 */       p pos = new p();
/* 1030 */       a(pos, style);
/* 1031 */       c parentStyle = style.c();
/* 1032 */       if (parentStyle != null) {
/* 1033 */         pos.g = b.c(parentStyle);
/*      */       }
/* 1035 */       if (h.c(style) == 2) {
/* 1036 */         o o = new o(params, pos);
/*      */       } else {
/* 1038 */         f1 = new f(params, pos);
/*      */       } 
/*      */     } 
/* 1041 */     return (a)f1;
/*      */   }
/*      */   
/*      */   public c c() {
/* 1045 */     return this.p;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(c style) {
/* 1052 */     a ce = style.a();
/*      */ 
/*      */     
/* 1055 */     boolean regenerate = false;
/* 1056 */     String pageContentName = jp.cssj.homare.impl.a.c.b.e.c(style);
/* 1057 */     if (pageContentName == null) {
/* 1058 */       pageContentName = g.c(style);
/* 1059 */       if (pageContentName != null) {
/* 1060 */         regenerate = true;
/*      */       }
/*      */     } 
/* 1063 */     if (pageContentName != null) {
/* 1064 */       style.a(G.a, (ad)t.r, (byte)1);
/*      */     }
/*      */     
/* 1067 */     short explDisplay = (short)G.c(style);
/*      */ 
/*      */     
/* 1070 */     boolean inRunIn = false;
/* 1071 */     if (this.I != null) {
/* 1072 */       if (this.I.a() == 0) {
/* 1073 */         d buff; switch (explDisplay) {
/*      */           
/*      */           case 6:
/*      */           case 8:
/*      */           case 9:
/*      */           case 10:
/*      */           case 11:
/*      */           case 12:
/*      */           case 13:
/*      */           case 14:
/* 1083 */             buff = this.I;
/* 1084 */             this.I = null;
/* 1085 */             this.K = 1;
/* 1086 */             buff.a(this);
/* 1087 */             this.K = 0;
/*      */             break;
/*      */ 
/*      */           
/*      */           case 5:
/* 1092 */             style.a(G.a, (ad)t.s, (byte)1);
/* 1093 */             explDisplay = 2; break;
/*      */         } 
/*      */       } else {
/* 1096 */         d buff; switch (explDisplay) {
/*      */           
/*      */           case 1:
/*      */           case 4:
/*      */           case 6:
/* 1101 */             buff = this.I;
/* 1102 */             this.I = null;
/* 1103 */             this.K = 1;
/* 1104 */             buff.a(this);
/* 1105 */             this.K = 0;
/*      */             break;
/*      */ 
/*      */           
/*      */           case 5:
/* 1110 */             style.a(G.a, (ad)t.s, (byte)1);
/*      */           default:
/* 1112 */             this.I.a(style);
/* 1113 */             this.p = style;
/* 1114 */             inRunIn = true;
/*      */             break;
/*      */         } 
/*      */       } 
/*      */     }
/* 1119 */     if (!inRunIn) {
/*      */       
/* 1121 */       String[] pageContentClearNames = f.c(style);
/* 1122 */       if (pageContentClearNames != null) {
/* 1123 */         this.E.put(ce, pageContentClearNames);
/*      */       }
/*      */ 
/*      */       
/* 1127 */       a lang = b.a((style.a()).G);
/*      */       
/* 1129 */       if (pageContentName != null) {
/*      */         b pageContent;
/* 1131 */         if (regenerate) {
/* 1132 */           pageContent = new c(this.o.a(1));
/*      */         } else {
/* 1134 */           style.a(jp.cssj.homare.impl.a.c.b.e.a, (ad)H.a, (byte)1);
/* 1135 */           style.a(g.a, (ad)H.a, (byte)1);
/* 1136 */           style.a(y.a, (ad)N.j, (byte)1);
/* 1137 */           pageContent = new b(this.o.a(1), jp.cssj.homare.impl.a.c.b.e.d(style), pageContentName);
/*      */         } 
/*      */         
/* 1140 */         r pos = new r();
/* 1141 */         q params = new q();
/* 1142 */         params.al = ce;
/* 1143 */         params.C = style.i();
/* 1144 */         params.F = this.k.q();
/* 1145 */         params.G = lang.a(style);
/* 1146 */         i inlineBox = new i(params, pos);
/* 1147 */         this.l.a((o)inlineBox);
/* 1148 */         this.l.b();
/* 1149 */         this.F.put(ce, pageContent);
/* 1150 */         this.H.add(pageContent);
/*      */       } 
/*      */       
/* 1153 */       if (!this.H.isEmpty()) {
/*      */         
/* 1155 */         b pageContent = this.H.get(this.H.size() - 1);
/* 1156 */         pageContent.a(style);
/* 1157 */         this.p = style;
/*      */       }
/* 1159 */       else if (explDisplay == 5) {
/*      */         
/* 1161 */         style.a(G.a, (ad)t.s, (byte)1);
/* 1162 */         this.I = new d();
/* 1163 */         this.I.a(style);
/* 1164 */         this.p = style;
/*      */       } else {
/* 1166 */         if (this.p != null) {
/* 1167 */           while (this.p.g()) {
/*      */ 
/*      */ 
/*      */             
/* 1171 */             byte pos = y.c(style);
/* 1172 */             if (pos != 0 && pos != 1) {
/*      */               break;
/*      */             }
/*      */             
/* 1176 */             byte anonRuby = h.c(this.p);
/* 1177 */             if (anonRuby == 2) {
/*      */               
/* 1179 */               byte ruby = h.c(style);
/* 1180 */               if (ruby != 3) {
/*      */                 break;
/*      */               }
/*      */             } else {
/*      */               c parent;
/* 1185 */               short anonDisplay = (short)G.c(this.p);
/* 1186 */               switch (explDisplay) {
/*      */                 case 8:
/*      */                 case 11:
/*      */                 case 12:
/* 1190 */                   switch (anonDisplay) {
/*      */                     case 8:
/*      */                     case 13:
/*      */                       break;
/*      */                   } 
/*      */                   
/*      */                   break;
/*      */                 
/*      */                 case 14:
/* 1199 */                   switch (anonDisplay) {
/*      */                     case 6:
/*      */                     case 7:
/*      */                     case 8:
/*      */                       break;
/*      */                   } 
/*      */                   
/*      */                   break;
/*      */                 
/*      */                 case 1:
/*      */                 case 2:
/*      */                 case 3:
/*      */                 case 4:
/*      */                 case 6:
/*      */                 case 7:
/* 1214 */                   switch (anonDisplay) {
/*      */                     case 13:
/* 1216 */                       parent = this.p.c();
/* 1217 */                       if (!parent.g() || !parent.c().g()) {
/*      */                         break;
/*      */                       }
/*      */                       break;
/*      */                     
/*      */                     case 6:
/*      */                     case 7:
/*      */                     case 8:
/*      */                       break;
/*      */                   } 
/*      */                   break;
/*      */                 default:
/*      */                   break;
/*      */               } 
/*      */             } 
/* 1232 */             if (style.c() == this.p) {
/* 1233 */               style.f();
/*      */             }
/* 1235 */             i();
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/* 1240 */         if (a.C.a(ce)) {
/*      */           
/* 1242 */           byte clear = A.c(style);
/* 1243 */           byte pageBreakBefore = a(ah.c(style), style);
/* 1244 */           byte pageBreakAfter = a(ag.c(style), style);
/* 1245 */           if (clear != 0 || pageBreakBefore != 0 || pageBreakAfter != 0) {
/*      */ 
/*      */             
/* 1248 */             p pos = new p();
/* 1249 */             pos.c = clear;
/* 1250 */             pos.a = pageBreakBefore;
/* 1251 */             pos.b = pageBreakAfter;
/* 1252 */             i params = new i();
/* 1253 */             params.C = style.i();
/* 1254 */             params.F = this.k.q();
/* 1255 */             params.G = lang.a(style);
/* 1256 */             params.E = F.d(style);
/* 1257 */             params.D = c.c(style);
/* 1258 */             params.al = ce;
/* 1259 */             t margin = t.a(0.0D, 0.0D, -O.c(style), 0.0D, (short)1, (short)1, (short)1, (short)1);
/*      */             
/* 1261 */             params.S = B.a(margin, A.a, g.a, t.d);
/*      */ 
/*      */             
/* 1264 */             f flowBox = new f(params, pos);
/* 1265 */             this.l.a((o)flowBox);
/* 1266 */             this.l.b();
/*      */           } 
/*      */         } 
/*      */         
/* 1270 */         g(style);
/*      */         
/* 1272 */         this.z = true;
/* 1273 */         if (!ce.a()) {
/* 1274 */           this.A++;
/*      */         }
/* 1276 */         int depth = this.A;
/*      */ 
/*      */         
/* 1279 */         ad[] resets = E.c(style);
/* 1280 */         if (resets != null) {
/* 1281 */           i pc = this.k.b();
/* 1282 */           for (int i = 0; i < resets.length; i++) {
/* 1283 */             o counterSet = (o)resets[i];
/* 1284 */             String name = counterSet.b();
/* 1285 */             int value = counterSet.c();
/* 1286 */             jp.cssj.homare.ua.e scope = pc.a(0, false);
/* 1287 */             if (scope != null && scope.a(name)) {
/* 1288 */               scope.a(name, value);
/*      */             } else {
/*      */               
/* 1291 */               scope = pc.a(depth, true);
/* 1292 */               scope.a(name, value);
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/* 1297 */         ad[] increments = D.c(style);
/* 1298 */         if (increments != null) {
/* 1299 */           i pc = this.k.b();
/* 1300 */           for (int i = 0; i < increments.length; i++) {
/* 1301 */             o counterSet = (o)increments[i];
/* 1302 */             String name = counterSet.b();
/* 1303 */             int delta = counterSet.c();
/* 1304 */             int level = depth;
/* 1305 */             for (; level > 0; level--) {
/* 1306 */               jp.cssj.homare.ua.e scope = pc.a(level, false);
/* 1307 */               if (scope != null && scope.a(name)) {
/*      */                 break;
/*      */               }
/*      */             } 
/* 1311 */             pc.a(level, true).b(name, delta);
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 1316 */         if (explDisplay == 4) {
/* 1317 */           int[] counter = null;
/* 1318 */           if (!this.C.isEmpty()) {
/* 1319 */             counter = this.C.get(this.C.size() - 1);
/* 1320 */             if (counter[0] == depth) {
/* 1321 */               counter[1] = counter[1] + 1;
/*      */             } else {
/* 1323 */               counter = null;
/*      */             } 
/*      */           } 
/* 1326 */           if (counter == null) {
/* 1327 */             int start = 1;
/* 1328 */             c parentStyle = style;
/*      */             
/* 1330 */             for (parentStyle = parentStyle.c(); parentStyle != null; 
/* 1331 */               parentStyle = parentStyle.c()) {
/* 1332 */               a parentCe = parentStyle.a();
/* 1333 */               if (parentCe != null) {
/*      */ 
/*      */                 
/* 1336 */                 if (a.q.a(parentCe)) {
/*      */                   break;
/*      */                 }
/* 1339 */                 if (a.p.a(parentCe)) {
/* 1340 */                   String str = parentCe.H.getValue("start");
/* 1341 */                   if (str != null)
/*      */                     try {
/* 1343 */                       start = Integer.parseInt(str);
/* 1344 */                     } catch (NumberFormatException numberFormatException) {
/* 1345 */                       this.k.a((short)10248, "OL", "start" + str);
/*      */                     }  
/*      */                   break;
/*      */                 } 
/*      */               } 
/*      */             } 
/* 1351 */             counter = new int[] { depth, start };
/* 1352 */             this.C.add(counter);
/*      */           } 
/* 1354 */           if (style.a() != null && a.r.a(style.a())) {
/* 1355 */             String value = (style.a()).H.getValue("value");
/* 1356 */             if (value != null) {
/*      */               try {
/* 1358 */                 counter[1] = Integer.parseInt(value);
/* 1359 */               } catch (NumberFormatException numberFormatException) {
/* 1360 */                 this.k.a((short)10248, "LI", "value" + value);
/*      */               } 
/*      */             }
/*      */           } 
/*      */           
/* 1365 */           int number = counter[1];
/* 1366 */           r pos = new r();
/* 1367 */           i params = new i();
/* 1368 */           a(params, style);
/* 1369 */           a(pos, style);
/* 1370 */           params.S = B.a;
/* 1371 */           short listStyleType = R.c(style);
/* 1372 */           b image = P.c(style);
/* 1373 */           if (image == null) {
/* 1374 */             image = jp.cssj.homare.css.e.e.a(listStyleType, params.M, params.C);
/*      */           }
/* 1376 */           this.D = null;
/* 1377 */           a marker = null;
/* 1378 */           if (image == null) {
/* 1379 */             String str = jp.cssj.homare.css.e.e.a(number, listStyleType);
/* 1380 */             if (str != null) {
/* 1381 */               marker = new a();
/* 1382 */               String dot = jp.cssj.homare.css.e.e.a(listStyleType);
/* 1383 */               marker.b = (str + dot + ' ').toCharArray();
/*      */             } 
/*      */           } else {
/* 1386 */             marker = new a();
/* 1387 */             C rparams = new C();
/* 1388 */             a((y)rparams, style);
/* 1389 */             rparams.a = image;
/* 1390 */             marker.c = new j(rparams, pos);
/*      */           } 
/* 1392 */           if (marker != null) {
/* 1393 */             switch (Q.c(style)) {
/*      */               
/*      */               case 0:
/* 1396 */                 marker.a = new h(params, pos);
/* 1397 */                 a(marker);
/*      */                 break;
/*      */               
/*      */               case 1:
/* 1401 */                 marker.a = (h)new m(params, pos);
/* 1402 */                 this.D = marker;
/*      */                 break;
/*      */               default:
/* 1405 */                 throw new IllegalStateException();
/*      */             } 
/*      */ 
/*      */           
/*      */           }
/*      */         } 
/* 1411 */         if (ce == a.t || ce == a.s) {
/* 1412 */           ad[] contents = C.c(style);
/* 1413 */           if (contents != null) {
/* 1414 */             for (int i = 0; i < contents.length; i++) {
/* 1415 */               String str; ab uriValue; p counter; q counters; P quote; c attr; jp.cssj.homare.css.f.b.e e1; c header; k state; g pageRefFunc; URI uri; String name; ad[] quotesList; a a1; k k1; String str1; a parentCe; String id; short counterStyle; String delim; int level, number; short s1; String str2; i pc; boolean first; int j; i i1; int k; ad v = contents[i];
/* 1416 */               switch (v.a()) {
/*      */                 
/*      */                 case 1018:
/* 1419 */                   str = ((V)v).b();
/* 1420 */                   if (str.length() > 0) {
/* 1421 */                     char[] ch = str.toCharArray();
/* 1422 */                     h();
/* 1423 */                     this.l.a(-1, ch, 0, ch.length, true);
/*      */                   } 
/*      */                   break;
/*      */ 
/*      */                 
/*      */                 case 1020:
/* 1429 */                   uriValue = (ab)v;
/* 1430 */                   uri = uriValue.b();
/*      */                   try {
/* 1432 */                     b source = this.k.b(uri);
/*      */                     try {
/* 1434 */                       b image = this.k.c(source);
/* 1435 */                       C rparams = new C();
/* 1436 */                       a((y)rparams, style);
/* 1437 */                       rparams.a = image;
/* 1438 */                       j j1 = new j(rparams, new r());
/*      */                       
/* 1440 */                       h();
/* 1441 */                       this.l.a((f)j1);
/*      */                     } finally {
/* 1443 */                       this.k.a(source);
/*      */                     } 
/* 1445 */                   } catch (Exception exception) {
/* 1446 */                     f.log(Level.FINE, "Missing image", exception);
/* 1447 */                     this.k.a((short)10257, uri.toString());
/*      */                   } 
/*      */                   break;
/*      */ 
/*      */ 
/*      */                 
/*      */                 case 1042:
/* 1454 */                   counter = (p)v;
/* 1455 */                   name = counter.b();
/* 1456 */                   counterStyle = counter.c();
/* 1457 */                   number = 0;
/* 1458 */                   pc = this.k.b();
/* 1459 */                   for (j = depth; j >= 0; j--) {
/* 1460 */                     jp.cssj.homare.ua.e scope = pc.a(j, false);
/* 1461 */                     if (scope != null && scope.a(name)) {
/* 1462 */                       number = scope.b(name);
/*      */                       break;
/*      */                     } 
/*      */                   } 
/* 1466 */                   a(number, counterStyle, style);
/*      */                   break;
/*      */ 
/*      */ 
/*      */                 
/*      */                 case 1043:
/* 1472 */                   counters = (q)v;
/* 1473 */                   name = counters.b();
/* 1474 */                   delim = counters.d();
/* 1475 */                   s1 = counters.c();
/* 1476 */                   first = true;
/* 1477 */                   i1 = this.k.b();
/* 1478 */                   for (k = 0; k <= depth; k++) {
/* 1479 */                     jp.cssj.homare.ua.e scope = i1.a(k, false);
/* 1480 */                     if (scope != null && scope.a(name)) {
/* 1481 */                       if (!first && delim != null && delim.length() > 0) {
/* 1482 */                         char[] ch = delim.toCharArray();
/* 1483 */                         h();
/* 1484 */                         this.l.a(-1, ch, 0, ch.length, true);
/*      */                       } 
/* 1486 */                       first = false;
/* 1487 */                       int n = scope.b(name);
/* 1488 */                       a(n, s1, style);
/*      */                     } 
/*      */                   } 
/*      */                   break;
/*      */ 
/*      */ 
/*      */                 
/*      */                 case 1040:
/* 1496 */                   quote = (P)v;
/* 1497 */                   quotesList = aj.c(style);
/*      */                   
/* 1499 */                   switch (quote.b()) {
/*      */                     case 0:
/* 1501 */                       if (quotesList != null) {
/*      */                         
/* 1503 */                         String str3 = ((Q)quotesList[Math.min(this.B, quotesList.length - 1)]).b();
/* 1504 */                         if (str3.length() > 0) {
/* 1505 */                           char[] ch = str3.toCharArray();
/* 1506 */                           h();
/* 1507 */                           this.l.a(-1, ch, 0, ch.length, true);
/*      */                         } 
/*      */                       } 
/* 1510 */                       this.B++;
/*      */                       break;
/*      */ 
/*      */                     
/*      */                     case 1:
/* 1515 */                       if (this.B > 0) {
/* 1516 */                         this.B--;
/* 1517 */                         if (quotesList != null) {
/*      */                           
/* 1519 */                           String str3 = ((Q)quotesList[Math.min(this.B, quotesList.length - 1)]).c();
/* 1520 */                           if (str3.length() > 0) {
/* 1521 */                             char[] ch = str3.toCharArray();
/* 1522 */                             h();
/* 1523 */                             this.l.a(-1, ch, 0, ch.length, true);
/*      */                           } 
/*      */                         } 
/*      */                       } 
/*      */                       break;
/*      */ 
/*      */                     
/*      */                     case 2:
/* 1531 */                       this.B++;
/*      */                       break;
/*      */ 
/*      */                     
/*      */                     case 3:
/* 1536 */                       if (this.B > 0) {
/* 1537 */                         this.B--;
/*      */                       }
/*      */                       break;
/*      */                   } 
/*      */ 
/*      */                   
/* 1543 */                   throw new IllegalStateException();
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*      */                 case 1044:
/* 1549 */                   attr = (c)v;
/* 1550 */                   a1 = style.c().a();
/* 1551 */                   if (a1.H != null) {
/* 1552 */                     String str3 = a1.H.getValue(attr.b());
/* 1553 */                     if (str3 != null && str3.length() > 0) {
/* 1554 */                       char[] ch = str3.toCharArray();
/* 1555 */                       h();
/* 1556 */                       this.l.a(-1, ch, 0, ch.length, true);
/*      */                     } 
/*      */                   } 
/*      */                   break;
/*      */ 
/*      */                 
/*      */                 case 2001:
/* 1563 */                   e1 = (jp.cssj.homare.css.f.b.e)v;
/* 1564 */                   k1 = this.k.b().a();
/* 1565 */                   level = e1.b() - 1;
/* 1566 */                   str2 = k1.g[(level >= k1.g.length) ? (k1.g.length - 1) : level];
/*      */ 
/*      */                   
/* 1569 */                   if (str2 != null && str2.length() > 0) {
/* 1570 */                     char[] ch = str2.toCharArray();
/* 1571 */                     h();
/* 1572 */                     this.l.a(-1, ch, 0, ch.length, true);
/*      */                   } 
/*      */                   break;
/*      */ 
/*      */                 
/*      */                 case 2009:
/* 1578 */                   header = (c)v;
/* 1579 */                   k1 = this.k.b().a();
/* 1580 */                   level = header.b() - 1;
/* 1581 */                   str2 = k1.e[(level >= k1.e.length) ? (k1.e.length - 1) : level];
/*      */ 
/*      */                   
/* 1584 */                   if (str2 != null && str2.length() > 0) {
/* 1585 */                     char[] ch = str2.toCharArray();
/* 1586 */                     h();
/* 1587 */                     this.l.a(-1, ch, 0, ch.length, true);
/*      */                   } 
/*      */                   break;
/*      */ 
/*      */                 
/*      */                 case 2008:
/* 1593 */                   state = this.k.b().a();
/* 1594 */                   str1 = state.a;
/* 1595 */                   if (str1 != null && str1.length() > 0) {
/* 1596 */                     char[] ch = str1.toCharArray();
/* 1597 */                     h();
/* 1598 */                     this.l.a(-1, ch, 0, ch.length, true);
/*      */                   } 
/*      */                   break;
/*      */ 
/*      */                 
/*      */                 case 2004:
/* 1604 */                   pageRefFunc = (g)v;
/* 1605 */                   switch (pageRefFunc.b()) {
/*      */                     
/*      */                     case 2:
/* 1608 */                       parentCe = style.c().a();
/* 1609 */                       if (parentCe.H != null) {
/* 1610 */                         String str3 = pageRefFunc.c();
/* 1611 */                         str2 = parentCe.H.getValue(str3);
/* 1612 */                         if (str2 != null) {
/* 1613 */                           if (!str3.equals("href") && str2.indexOf("#") == -1)
/*      */                           {
/* 1615 */                             str2 = "#" + str2;
/*      */                           }
/* 1617 */                           a(pageRefFunc, str2);
/*      */                         } 
/*      */                       } 
/*      */                       break;
/*      */ 
/*      */                     
/*      */                     case 1:
/* 1624 */                       id = pageRefFunc.c();
/* 1625 */                       if (id.indexOf("#") == -1)
/*      */                       {
/* 1627 */                         id = "#" + id;
/*      */                       }
/* 1629 */                       a(pageRefFunc, id);
/*      */                       break;
/*      */                   } 
/*      */                   
/* 1633 */                   throw new IllegalStateException();
/*      */ 
/*      */ 
/*      */                 
/*      */                 default:
/* 1638 */                   throw new IllegalStateException(String.valueOf(v));
/*      */               } 
/*      */ 
/*      */             
/*      */             } 
/*      */           }
/*      */         } 
/* 1645 */         if (this.I != null) {
/* 1646 */           d buff = this.I;
/* 1647 */           this.I = null;
/* 1648 */           style = this.p;
/* 1649 */           this.K = 1;
/* 1650 */           buff.a(this);
/* 1651 */           this.K = 0;
/* 1652 */           this.p = style;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1659 */     if (this.K != 1 && ce != a.t && ce != a.s && 
/* 1660 */       jp.cssj.homare.impl.a.c.c.e.c(style) == null) {
/*      */       
/* 1662 */       a beforeCe = a.s;
/* 1663 */       this.o.a(beforeCe);
/* 1664 */       f beforeDeclaration = this.o.a(null);
/* 1665 */       if (beforeDeclaration != null || b.a(ce)) {
/* 1666 */         c beforeStyle = c.a(this.k, style, beforeCe);
/* 1667 */         b.a(beforeStyle);
/* 1668 */         if (beforeDeclaration != null) {
/* 1669 */           beforeDeclaration.a(beforeStyle);
/*      */         }
/* 1671 */         if (C.c(beforeStyle) != null && G.c(beforeStyle) != 0) {
/* 1672 */           a(beforeStyle);
/* 1673 */           d();
/*      */         } 
/*      */       } 
/* 1676 */       this.o.a();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void a(int number, short counterStyle, c style) {
/* 1681 */     String str = jp.cssj.homare.css.e.e.a(number, counterStyle);
/* 1682 */     if (str != null) {
/* 1683 */       char[] ch = str.toCharArray();
/* 1684 */       h();
/*      */       
/* 1686 */       this.l.a(-1, ch, 0, ch.length, true);
/*      */     } else {
/* 1688 */       C rparams = new C();
/* 1689 */       a((y)rparams, style);
/* 1690 */       rparams.a = jp.cssj.homare.css.e.e.a(counterStyle, u.c(style), style.i());
/* 1691 */       if (rparams.a != null) {
/* 1692 */         j j = new j(rparams, new r());
/* 1693 */         h();
/* 1694 */         this.l.a((f)j);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void a(g pageRefFunc, String ref) {
/* 1700 */     h pageRef = this.k.a().c();
/* 1701 */     if (pageRef == null) {
/*      */       return;
/*      */     }
/*      */     try {
/*      */       char[] ch;
/* 1706 */       URI uri = d.a(this.k.c().c(), this.k
/* 1707 */           .c().a(), ref);
/* 1708 */       String sep = pageRefFunc.f();
/* 1709 */       String counter = pageRefFunc.d();
/*      */       
/* 1711 */       if (sep == null) {
/* 1712 */         h.a frag = pageRef.a(uri);
/* 1713 */         if (frag == null) {
/*      */           return;
/*      */         }
/* 1716 */         int count = frag.a(counter);
/* 1717 */         String str = jp.cssj.homare.css.e.e.a(count, pageRefFunc.e());
/* 1718 */         if (str == null) {
/*      */           return;
/*      */         }
/* 1721 */         ch = str.toCharArray();
/*      */       } else {
/* 1723 */         Collection<?> frags = pageRef.b(uri);
/* 1724 */         if (frags == null || frags.isEmpty()) {
/*      */           return;
/*      */         }
/* 1727 */         d counts = new d();
/* 1728 */         for (Iterator<?> j = frags.iterator(); j.hasNext(); ) {
/* 1729 */           h.a fragment = (h.a)j.next();
/* 1730 */           int count = fragment.a(counter);
/* 1731 */           if (!counts.d(count)) {
/* 1732 */             counts.a(count);
/*      */           }
/*      */         } 
/* 1735 */         StringBuffer buff = new StringBuffer();
/* 1736 */         for (int i = 0; i < counts.b(); i++) {
/* 1737 */           if (buff.length() > 0) {
/* 1738 */             buff.append(sep);
/*      */           }
/* 1740 */           String str = jp.cssj.homare.css.e.e.a(counts.b(i), pageRefFunc.e());
/* 1741 */           if (str != null) {
/* 1742 */             buff.append(str);
/*      */           }
/*      */         } 
/* 1745 */         if (buff.length() <= 0) {
/*      */           return;
/*      */         }
/* 1748 */         ch = buff.toString().toCharArray();
/*      */       } 
/* 1750 */       h();
/*      */       
/* 1752 */       this.l.a(-1, ch, 0, ch.length, true);
/* 1753 */     } catch (URISyntaxException uRISyntaxException) {
/* 1754 */       this.k.a((short)10252, uRISyntaxException.getMessage());
/*      */     } 
/*      */   }
/*      */   
/*      */   private c a(c style, c box) {
/* 1759 */     int i = jp.cssj.homare.b.f.e.b(box);
/* 1760 */     if (i > 1) {
/* 1761 */       i params = box.c_();
/* 1762 */       i mcParams = new i();
/* 1763 */       p mcPos = new p();
/* 1764 */       c mc = style.b(a.u);
/* 1765 */       a(mcParams, mc);
/* 1766 */       a(mcPos, mc);
/* 1767 */       mcParams.ac = params.ac;
/* 1768 */       if (params.X.a() != 3) {
/* 1769 */         if (params.X.b() != 3) {
/* 1770 */           mcParams.X = m.a(1.0D, 1.0D, (byte)2, (byte)2);
/*      */         } else {
/* 1772 */           mcParams.X = m.a(1.0D, 0.0D, (byte)2, (byte)3);
/*      */         } 
/* 1774 */       } else if (params.X.b() != 3) {
/* 1775 */         mcParams.X = m.a(0.0D, 1.0D, (byte)3, (byte)2);
/*      */       } 
/* 1777 */       l mcBox = new l(mcParams, mcPos);
/* 1778 */       this.l.a((o)mcBox);
/* 1779 */       style = mc;
/*      */     } 
/* 1781 */     return style;
/*      */   }
/*      */   
/*      */   private void f(c style) {
/* 1785 */     style.a(G.a, (ad)t.r);
/* 1786 */     style.a(h.a, (ad)h.g);
/* 1787 */     style.a(O.a, (ad)M.c);
/* 1788 */     style.a(am.a, (ad)X.n);
/* 1789 */     c pStyle = style.c();
/* 1790 */     if (pStyle != null && ((b.c(pStyle) == 1 && 
/* 1791 */       jp.cssj.homare.b.f.e.a(c.c(pStyle))) || 
/* 1792 */       b.c(pStyle) == 3)) {
/*      */       
/* 1794 */       style.a(aw.a, (ad)a.a);
/*      */     } else {
/*      */       
/* 1797 */       style.a(L.a, (ad)a.a);
/*      */     } 
/* 1799 */     g(style); } private void g(c style) { b image; i i1; G params; D d1; F f1; I i; J j; E pos; r r; a listItem, blockBox; i i3; s s; i i2; q q; r table; f caption; u columnGroup;
/*      */     t column;
/*      */     w rowGroup;
/*      */     v row;
/*      */     s cell;
/*      */     i inline;
/* 1805 */     boolean htmlRoot = false;
/* 1806 */     if (!this.x && this.t == null) {
/* 1807 */       a ce = style.a();
/* 1808 */       if (a.c.a(ce)) {
/* 1809 */         htmlRoot = true;
/*      */       }
/* 1811 */       style.a(G.a, (ad)t.r, (byte)1);
/* 1812 */       style.a(v.a, (ad)k.f, (byte)1);
/* 1813 */       byte b1 = y.c(style);
/* 1814 */       if (b1 == 2 || b1 == 3) {
/* 1815 */         style.a(y.a, (ad)N.f, (byte)1);
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1820 */     byte display = G.c(style);
/* 1821 */     byte position = y.c(style);
/* 1822 */     if (position == 0 || position == 1) {
/*      */       
/* 1824 */       c parentStyle = style.c();
/* 1825 */       if (parentStyle != null) {
/* 1826 */         c anon; byte ruby = h.c(style);
/* 1827 */         if (ruby != 3 && ruby != 2) {
/*      */           
/* 1829 */           byte parentRuby = h.c(parentStyle);
/* 1830 */           if (parentRuby == 1) {
/* 1831 */             f(style.a(a.w));
/*      */           }
/*      */         } 
/*      */         
/* 1835 */         short parentDisplay = (short)G.c(parentStyle);
/* 1836 */         switch (display) {
/*      */ 
/*      */           
/*      */           case 14:
/* 1840 */             if (parentDisplay != 13) {
/* 1841 */               c c1 = style.a(a.z);
/* 1842 */               c1.a(G.a, (ad)t.D);
/* 1843 */               g(c1);
/*      */             } 
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 13:
/* 1851 */             if (parentDisplay != 8 && parentDisplay != 11 && parentDisplay != 12) {
/*      */ 
/*      */               
/* 1854 */               c c1 = style.a(a.y);
/* 1855 */               c1.a(G.a, (ad)t.y);
/* 1856 */               g(c1);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case 10:
/* 1862 */             if (parentDisplay == 10 || parentDisplay == 9) {
/*      */               break;
/*      */             }
/*      */ 
/*      */ 
/*      */           
/*      */           case 8:
/*      */           case 11:
/*      */           case 12:
/* 1871 */             if (parentDisplay != 6 && parentDisplay != 7) {
/* 1872 */               c c1 = style.a(a.y);
/* 1873 */               if (parentDisplay == 2) {
/* 1874 */                 c1.a(G.a, (ad)t.x);
/*      */               } else {
/* 1876 */                 c1.a(G.a, (ad)t.w);
/*      */               } 
/* 1878 */               g(c1);
/*      */             } 
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 9:
/* 1885 */             if (parentDisplay != 6 && parentDisplay != 7 && parentDisplay != 10) {
/*      */               
/* 1887 */               c c1 = style.a(a.x);
/* 1888 */               if (parentDisplay == 2) {
/* 1889 */                 c1.a(G.a, (ad)t.x);
/*      */               } else {
/* 1891 */                 c1.a(G.a, (ad)t.w);
/*      */               } 
/* 1893 */               g(c1);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case 15:
/* 1899 */             switch (parentDisplay) {
/*      */               case 6:
/*      */               case 7:
/*      */               case 8:
/*      */               case 11:
/*      */               case 12:
/*      */               case 13:
/*      */                 break;
/*      */             } 
/*      */             
/* 1909 */             style.a(G.a, (ad)t.r, (byte)1);
/*      */             break;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 1:
/*      */           case 2:
/*      */           case 3:
/*      */           case 4:
/*      */           case 6:
/*      */           case 7:
/* 1921 */             switch (parentDisplay) {
/*      */               case 6:
/*      */               case 7:
/*      */               case 8:
/*      */               case 11:
/*      */               case 12:
/*      */               case 13:
/* 1928 */                 anon = style.a(a.A);
/* 1929 */                 anon.a(G.a, (ad)t.E);
/* 1930 */                 g(anon);
/*      */                 break;
/*      */             } 
/*      */             break;
/*      */           default:
/* 1935 */             throw new IllegalStateException();
/*      */         } 
/*      */ 
/*      */       
/*      */       } 
/*      */     } 
/* 1941 */     byte floating = v.c(style);
/*      */ 
/*      */     
/* 1944 */     switch (display) {
/*      */       
/*      */       case 1:
/*      */       case 3:
/* 1948 */         image = jp.cssj.homare.impl.a.c.c.e.c(style);
/* 1949 */         if (image != null) {
/*      */           g g1;
/*      */           
/* 1952 */           boolean bool = false;
/*      */           
/* 1954 */           if (position == 2 || position == 3 || position == 4) {
/*      */             
/* 1956 */             a a1 = new a();
/* 1957 */             C c1 = new C();
/* 1958 */             a(image, c1, style);
/* 1959 */             a(a1, style);
/* 1960 */             b b1 = new b(c1, a1);
/* 1961 */           } else if (display == 3) {
/* 1962 */             r r1 = new r();
/* 1963 */             C c1 = new C();
/* 1964 */             a(image, c1, style);
/* 1965 */             a(r1, style);
/* 1966 */             bool = true;
/* 1967 */             j j1 = new j(c1, r1);
/* 1968 */           } else if (floating != 0) {
/* 1969 */             o o = new o();
/* 1970 */             C c1 = new C();
/* 1971 */             a(image, c1, style);
/* 1972 */             a(o, style);
/* 1973 */             jp.cssj.homare.b.a.b.e e1 = new jp.cssj.homare.b.a.b.e(c1, o);
/*      */           } else {
/* 1975 */             p p = new p();
/* 1976 */             C c1 = new C();
/* 1977 */             a(image, c1, style);
/* 1978 */             a(p, style);
/* 1979 */             c parentStyle = style.c();
/* 1980 */             if (parentStyle != null) {
/* 1981 */               p.g = b.c(parentStyle);
/*      */             }
/* 1983 */             g1 = new g(c1, p);
/*      */           } 
/* 1985 */           a((byte)1, (byte)1);
/* 1986 */           if (bool) {
/* 1987 */             h();
/*      */           }
/* 1989 */           this.l.a((f)g1);
/*      */         } else {
/*      */           
/* 1992 */           i i4 = new i();
/* 1993 */           a(i4, style);
/* 1994 */           a a1 = a(style, i4, position, display, floating);
/*      */           
/* 1996 */           if (a1.b_().a() == 4 && htmlRoot) {
/* 1997 */             this.t = (f)a1;
/*      */             break;
/*      */           } 
/* 2000 */           a(i4.E, i4.D);
/* 2001 */           if (a1.b_().a() == 5) {
/* 2002 */             h();
/*      */           }
/* 2004 */           this.l.a((o)a1);
/*      */ 
/*      */           
/* 2007 */           style = a(style, (c)a1);
/*      */         } 
/* 2009 */         this.y = false;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2:
/* 2014 */         image = jp.cssj.homare.impl.a.c.c.e.c(style);
/* 2015 */         r = new r();
/* 2016 */         if (image != null) {
/*      */           
/* 2018 */           C c1 = new C();
/* 2019 */           a(image, c1, style);
/* 2020 */           a(r, style);
/* 2021 */           j j1 = new j(c1, r);
/* 2022 */           a((byte)1, (byte)1);
/* 2023 */           h();
/* 2024 */           this.l.a((f)j1);
/*      */           break;
/*      */         } 
/* 2027 */         q = new q();
/* 2028 */         a(q, style);
/* 2029 */         a(r, style);
/* 2030 */         inline = new i(q, r);
/* 2031 */         a(q.E, q.D);
/* 2032 */         this.l.a((o)inline);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 4:
/* 2038 */         i1 = new i();
/* 2039 */         a(i1, style);
/* 2040 */         listItem = a(style, i1, position, display, floating);
/* 2041 */         a(i1.E, i1.D);
/* 2042 */         this.l.a((o)listItem);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 6:
/*      */       case 7:
/* 2049 */         params = new G();
/* 2050 */         a(params, style);
/* 2051 */         blockBox = a(style, (i)params, position, display, floating);
/* 2052 */         if (blockBox.b_().a() == 4 && 
/* 2053 */           b.c(style) == 3) {
/* 2054 */           ((p)blockBox.b_()).g = 3;
/*      */         }
/*      */         
/* 2057 */         table = new r(params, blockBox);
/* 2058 */         a((byte)1, (byte)1);
/* 2059 */         this.l.a((o)table);
/* 2060 */         this.y = false;
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 15:
/* 2066 */         d1 = new D();
/* 2067 */         i3 = new i();
/* 2068 */         a(d1, style);
/* 2069 */         a(i3, style);
/* 2070 */         i3.U = 1;
/* 2071 */         switch (d1.v) {
/*      */           case 1:
/* 2073 */             d1.b = 1;
/*      */             break;
/*      */           case 2:
/* 2076 */             d1.a = 1;
/*      */             break;
/*      */           default:
/* 2079 */             throw new IllegalStateException();
/*      */         } 
/* 2081 */         caption = new f(i3, (p)d1);
/* 2082 */         a(i3.E, i3.D);
/* 2083 */         this.l.a((o)caption);
/* 2084 */         this.y = false;
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 10:
/* 2090 */         f1 = new F();
/* 2091 */         s = new s();
/* 2092 */         a(s, f1, style);
/* 2093 */         columnGroup = new u(s, f1);
/* 2094 */         this.l.a((o)columnGroup);
/* 2095 */         this.y = false;
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 9:
/* 2101 */         f1 = new F();
/* 2102 */         s = new s();
/* 2103 */         a(s, f1, style);
/* 2104 */         column = new t(s, f1);
/* 2105 */         this.l.a((o)column);
/* 2106 */         this.y = false;
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 11:
/* 2112 */         i = new I();
/* 2113 */         s = new s();
/* 2114 */         a(s, i, style, (byte)1);
/* 2115 */         rowGroup = new w(s, i);
/* 2116 */         this.l.a((o)rowGroup);
/* 2117 */         this.y = false;
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 8:
/* 2123 */         i = new I();
/* 2124 */         s = new s();
/* 2125 */         a(s, i, style, (byte)2);
/* 2126 */         rowGroup = new w(s, i);
/* 2127 */         this.l.a((o)rowGroup);
/* 2128 */         this.y = false;
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 12:
/* 2134 */         i = new I();
/* 2135 */         s = new s();
/* 2136 */         a(s, i, style, (byte)3);
/* 2137 */         rowGroup = new w(s, i);
/* 2138 */         this.l.a((o)rowGroup);
/* 2139 */         this.y = false;
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 13:
/* 2145 */         j = new J();
/* 2146 */         s = new s();
/* 2147 */         a(s, j, style);
/* 2148 */         row = new v(s, j);
/* 2149 */         this.l.a((o)row);
/* 2150 */         this.y = false;
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 14:
/* 2156 */         pos = new E();
/* 2157 */         i2 = new i();
/* 2158 */         a(pos, style);
/* 2159 */         a(i2, style);
/* 2160 */         cell = new s(i2, pos, (g)new i());
/* 2161 */         this.l.a((o)cell);
/* 2162 */         this.y = false;
/*      */ 
/*      */         
/* 2165 */         style = a(style, (c)cell);
/*      */         break;
/*      */ 
/*      */       
/*      */       default:
/* 2170 */         throw new IllegalStateException();
/*      */     } 
/*      */     
/* 2173 */     this.p = style; }
/*      */ 
/*      */   
/*      */   public void a(int charOffset, char[] ch, int off, int len) {
/* 2177 */     if (!a && len <= 0) throw new AssertionError();
/*      */     
/* 2179 */     if (this.I != null) {
/* 2180 */       this.I.a(charOffset, ch, off, len);
/*      */       
/*      */       return;
/*      */     } 
/* 2184 */     if (!this.H.isEmpty()) {
/* 2185 */       b pageContent = this.H.get(this.H.size() - 1);
/* 2186 */       pageContent.a(charOffset, ch, off, len);
/*      */       return;
/*      */     } 
/* 2189 */     if (this.t == null && this.p != null) {
/*      */       
/* 2191 */       if (!this.y) {
/*      */         int i;
/*      */ 
/*      */         
/* 2195 */         c style = this.p;
/* 2196 */         switch (au.c(style)) {
/*      */           
/*      */           case 1:
/*      */           case 3:
/* 2200 */             i = 0; while (true) { if (i < len) {
/* 2201 */                 char c1 = ch[i + off];
/* 2202 */                 if (!f.a(c1))
/*      */                   break;  i++;
/*      */                 continue;
/*      */               } 
/*      */               return; }
/*      */             
/*      */             break;
/*      */           case 5:
/* 2210 */             i = 0; while (true) { if (i < len) {
/* 2211 */                 char c1 = ch[i + off];
/* 2212 */                 if (!f.a(c1) || c1 == '\n')
/*      */                   break;  i++; continue;
/*      */               }  return; }
/*      */             
/*      */             break;
/*      */           case 2:
/*      */           case 4:
/*      */             break;
/*      */           default:
/* 2221 */             throw new IllegalStateException();
/*      */         } 
/* 2223 */         this.y = true;
/*      */       } 
/*      */ 
/*      */       
/* 2227 */       byte parentRuby = h.c(this.p);
/* 2228 */       if (parentRuby == 1) {
/* 2229 */         f(this.p.b(a.w));
/*      */       }
/*      */       
/* 2232 */       if (this.z) {
/* 2233 */         this.z = false;
/*      */ 
/*      */         
/* 2236 */         this.o.a(a.r);
/* 2237 */         f declaration = this.o.a(null);
/* 2238 */         this.o.a();
/* 2239 */         if (declaration != null) {
/* 2240 */           c firstLetterStyle = c.a(this.k, this.p, a.r);
/*      */           
/* 2242 */           declaration.a(firstLetterStyle);
/* 2243 */           if (G.c(firstLetterStyle) != 0) {
/* 2244 */             a(firstLetterStyle);
/*      */             
/* 2246 */             a lang = b.a((this.p.a()).G);
/* 2247 */             int first = lang.a(ch, off, len);
/* 2248 */             h();
/* 2249 */             this.l.a(charOffset, ch, off, first, false);
/* 2250 */             len -= first;
/* 2251 */             off += first;
/* 2252 */             charOffset += first;
/* 2253 */             d();
/*      */           } 
/* 2255 */           if (len == 0) {
/*      */             return;
/*      */           }
/*      */         } 
/*      */       } 
/* 2260 */       h();
/*      */       
/* 2262 */       if (this.p != null) {
/* 2263 */         while (this.p.g()) {
/*      */           c parent;
/* 2265 */           short anonDisplay = (short)G.c(this.p);
/* 2266 */           switch (anonDisplay) {
/*      */             case 13:
/* 2268 */               parent = this.p.c();
/* 2269 */               if (!parent.g() || !parent.c().g())
/*      */                 break; 
/*      */               break;
/*      */             case 6:
/*      */             case 7:
/*      */             case 8:
/*      */               break;
/*      */             default:
/*      */               break;
/*      */           } 
/* 2279 */           i();
/*      */         } 
/*      */       }
/*      */       
/* 2283 */       String em = A.c(this.p);
/* 2284 */       if (em == null || em.length() == 0) {
/* 2285 */         this.l.a(charOffset, ch, off, len, false);
/*      */       } else {
/*      */         
/* 2288 */         char[] emc = em.toCharArray();
/* 2289 */         boolean vert = jp.cssj.homare.b.f.e.a(c.c(this.p));
/*      */         
/* 2291 */         boolean logVert = ((b.c(this.p) == 1 && vert) || b.c(this.p) == 3);
/* 2292 */         ad color = this.p.a(y.a);
/* 2293 */         if (color == r.a) {
/* 2294 */           color = this.p.a(u.a);
/*      */         }
/* 2296 */         for (int i = 0; i < len; i++) {
/* 2297 */           c eb = this.p.b(a.u);
/* 2298 */           eb.a(G.a, (ad)t.t);
/* 2299 */           eb.a(y.a, (ad)N.g);
/* 2300 */           eb.a(ao.a, (ad)a.a);
/* 2301 */           if (vert) {
/* 2302 */             eb.a(O.a, (ad)h);
/*      */           } else {
/* 2304 */             eb.a(O.a, (ad)e.i);
/*      */           } 
/* 2306 */           g(eb);
/* 2307 */           c et = eb.b(a.u);
/* 2308 */           et.a(G.a, (ad)t.t);
/* 2309 */           et.a(y.a, (ad)N.h);
/* 2310 */           et.a(ao.a, (ad)a.a);
/* 2311 */           et.a(u.a, color);
/* 2312 */           et.a(I.a, (ad)M.b);
/* 2313 */           if (logVert) {
/* 2314 */             et.a(L.a, (ad)M.c);
/* 2315 */             et.a(M.a, (ad)j);
/*      */           } else {
/* 2317 */             et.a(aw.a, (ad)M.c);
/* 2318 */             et.a(t.a, (ad)j);
/*      */           } 
/* 2320 */           et.a(am.a, (ad)X.j);
/* 2321 */           g(et);
/* 2322 */           this.l.a(-1, emc, 0, 1, false);
/* 2323 */           i();
/* 2324 */           this.l.a(charOffset, ch, i + off, 1, false);
/* 2325 */           i();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private byte a(byte pageBreak, c style) {
/* 2332 */     switch (pageBreak) {
/*      */       case 0:
/* 2334 */         return 0;
/*      */       case 1:
/* 2336 */         return 1;
/*      */       case 2:
/* 2338 */         return 4;
/*      */       case 3:
/* 2340 */         if ((this.w && b.c(style) == 1) || 
/* 2341 */           b.c(style) == 3) {
/* 2342 */           return 6;
/*      */         }
/* 2344 */         return 5;
/*      */       case 4:
/* 2346 */         if ((this.w && b.c(style) == 1) || 
/* 2347 */           b.c(style) == 3) {
/* 2348 */           return 5;
/*      */         }
/* 2350 */         return 6;
/*      */       case 11:
/* 2352 */         if ((this.w && b.c(style) == 1) || 
/* 2353 */           b.c(style) == 3) {
/* 2354 */           return 9;
/*      */         }
/* 2356 */         return 8;
/*      */       case 12:
/* 2358 */         if ((this.w && b.c(style) == 1) || 
/* 2359 */           b.c(style) == 3) {
/* 2360 */           return 8;
/*      */         }
/* 2362 */         return 9;
/*      */       case 5:
/* 2364 */         return 4;
/*      */       case 6:
/* 2366 */         return 7;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 9:
/* 2372 */         return 5;
/*      */       case 10:
/* 2374 */         return 6;
/*      */       case 13:
/* 2376 */         return 8;
/*      */       case 14:
/* 2378 */         return 9;
/*      */     } 
/* 2380 */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void h() {
/* 2386 */     if (this.D == null) {
/*      */       return;
/*      */     }
/*      */     
/* 2390 */     a marker = this.D;
/* 2391 */     this.D = null;
/* 2392 */     a(marker);
/*      */   }
/*      */   
/*      */   private void a(a marker) {
/* 2396 */     this.l.a((o)marker.a);
/* 2397 */     if (marker.b != null) {
/*      */       
/* 2399 */       this.l.a(-1, marker.b, 0, marker.b.length, false);
/* 2400 */     } else if (marker.c != null) {
/* 2401 */       this.l.a((f)marker.c);
/*      */     } 
/* 2403 */     this.l.b();
/*      */   }
/*      */   
/*      */   private void i() {
/* 2407 */     c style = this.p;
/*      */     
/* 2409 */     if (!this.x) {
/* 2410 */       this.x = true;
/* 2411 */       g(style);
/*      */     } 
/* 2413 */     if (jp.cssj.homare.impl.a.c.c.e.c(style) == null) {
/* 2414 */       this.l.b();
/*      */     }
/* 2416 */     switch (G.c(style)) {
/*      */       case 1:
/*      */       case 4:
/*      */       case 6:
/*      */       case 7:
/*      */       case 8:
/*      */       case 9:
/*      */       case 10:
/*      */       case 11:
/*      */       case 12:
/*      */       case 13:
/*      */       case 14:
/*      */       case 15:
/* 2429 */         this.y = false;
/*      */         break;
/*      */       
/*      */       case 3:
/* 2433 */         this.y = true;
/*      */         break;
/*      */       
/*      */       case 2:
/*      */         break;
/*      */       
/*      */       default:
/* 2440 */         throw new IllegalStateException();
/*      */     } 
/*      */     
/* 2443 */     this.p = style.c();
/*      */   }
/*      */   
/*      */   public void d() {
/* 2447 */     c style = this.p;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2452 */     a ce = style.a();
/* 2453 */     if (this.K != 1 && ce != a.t && ce != a.s && 
/* 2454 */       jp.cssj.homare.impl.a.c.c.e.c(style) == null) {
/*      */       
/* 2456 */       boolean br = a.C.a(ce);
/* 2457 */       a afterCe = a.t;
/* 2458 */       this.o.a(afterCe);
/* 2459 */       f afterDeclaration = this.o.a(null);
/* 2460 */       if (afterDeclaration != null || br || b.b(ce)) {
/* 2461 */         c afterStyle = c.a(this.k, style, afterCe);
/* 2462 */         b.b(afterStyle);
/* 2463 */         if (br) {
/* 2464 */           afterStyle.a(C.a, (ad)g);
/* 2465 */           afterStyle.a(A.a, (ad)C.a);
/*      */         } 
/* 2467 */         if (afterDeclaration != null) {
/* 2468 */           afterDeclaration.a(afterStyle);
/*      */         }
/* 2470 */         if (br && G.c(afterStyle) == 2) {
/* 2471 */           byte pageBreakBefore = a(ah.c(afterStyle), afterStyle);
/* 2472 */           byte pageBreakAfter = a(ag.c(afterStyle), afterStyle);
/* 2473 */           if ((pageBreakBefore != 0 && pageBreakBefore != 1) || (pageBreakAfter != 0 && pageBreakAfter != 1))
/*      */           {
/*      */ 
/*      */             
/* 2477 */             afterStyle.a(G.a, (ad)t.r);
/*      */           }
/*      */         } 
/* 2480 */         if (C.c(afterStyle) != null && G.c(afterStyle) != 0) {
/* 2481 */           a(afterStyle);
/* 2482 */           d();
/*      */         } 
/*      */       } 
/* 2485 */       this.o.a();
/*      */     } 
/*      */     
/* 2488 */     if (this.I != null) {
/*      */       
/* 2490 */       this.I.b(this.p);
/* 2491 */       this.p = this.p.c();
/*      */       
/*      */       return;
/*      */     } 
/* 2495 */     if (!this.H.isEmpty()) {
/*      */       
/* 2497 */       b pageContent = this.H.get(this.H.size() - 1);
/* 2498 */       pageContent.b(this.p);
/* 2499 */       if (pageContent.a() == 0) {
/* 2500 */         this.H.remove(this.H.size() - 1);
/*      */       }
/* 2502 */       this.p = this.p.c();
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 2507 */     while (this.p.g()) {
/* 2508 */       i();
/*      */     }
/*      */ 
/*      */     
/* 2512 */     style = this.p;
/* 2513 */     i();
/* 2514 */     if (this.p != null) {
/* 2515 */       short explDisplay = (short)G.c(style);
/* 2516 */       while (this.p.h()) {
/*      */         
/* 2518 */         short anonDisplay = (short)G.c(this.p);
/* 2519 */         switch (explDisplay) {
/*      */           case 14:
/* 2521 */             switch (anonDisplay) {
/*      */               case 13:
/*      */                 break;
/*      */             } 
/*      */             
/*      */             break;
/*      */           case 13:
/* 2528 */             switch (anonDisplay) {
/*      */               case 8:
/*      */                 break;
/*      */             } 
/*      */             
/*      */             break;
/*      */           case 1:
/*      */           case 2:
/*      */           case 3:
/*      */           case 4:
/*      */           case 6:
/*      */           case 7:
/* 2540 */             switch (anonDisplay) {
/*      */               case 13:
/*      */                 break;
/*      */             } 
/*      */ 
/*      */             
/* 2546 */             if (h.c(this.p) == 2) {
/*      */               break;
/*      */             }
/*      */             break;
/*      */         } 
/* 2551 */         if (style.c() == this.p) {
/* 2552 */           style.f();
/*      */         }
/* 2554 */         i();
/*      */       } 
/*      */     } 
/*      */     
/* 2558 */     if (!style.a().a()) {
/*      */       
/* 2560 */       if (!this.C.isEmpty()) {
/* 2561 */         int[] counter = this.C.get(this.C.size() - 1);
/* 2562 */         if (counter[0] > this.A) {
/* 2563 */           this.C.remove(this.C.size() - 1);
/*      */         }
/*      */       } 
/* 2566 */       this.A--;
/*      */     } 
/* 2568 */     this.z = false;
/*      */   }
/*      */   
/*      */   public byte e() {
/* 2572 */     if (this.q.a((byte)4)) {
/* 2573 */       return 5;
/*      */     }
/* 2575 */     if (this.q.a((byte)5)) {
/* 2576 */       return 6;
/*      */     }
/* 2578 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public n f() {
/* 2583 */     this.q = this.m.f();
/* 2584 */     f declaration = this.o.b(this.q);
/* 2585 */     c pageStyle = c.a(this.k, null, this.q);
/*      */ 
/*      */     
/* 2588 */     if (this.n != null) {
/* 2589 */       switch (this.n.length) {
/*      */         case 1:
/* 2591 */           pageStyle.a(V.a, (ad)this.n[0]);
/* 2592 */           pageStyle.a(U.a, (ad)this.n[0]);
/* 2593 */           pageStyle.a(S.a, (ad)this.n[0]);
/* 2594 */           pageStyle.a(T.a, (ad)this.n[0]);
/*      */           break;
/*      */         case 2:
/* 2597 */           pageStyle.a(V.a, (ad)this.n[0]);
/* 2598 */           pageStyle.a(U.a, (ad)this.n[1]);
/* 2599 */           pageStyle.a(S.a, (ad)this.n[0]);
/* 2600 */           pageStyle.a(T.a, (ad)this.n[1]);
/*      */           break;
/*      */         case 3:
/* 2603 */           pageStyle.a(V.a, (ad)this.n[1]);
/* 2604 */           pageStyle.a(U.a, (ad)this.n[2]);
/* 2605 */           pageStyle.a(S.a, (ad)this.n[3]);
/* 2606 */           pageStyle.a(T.a, (ad)this.n[2]);
/*      */           break;
/*      */         case 4:
/* 2609 */           pageStyle.a(V.a, (ad)this.n[0]);
/* 2610 */           pageStyle.a(U.a, (ad)this.n[1]);
/* 2611 */           pageStyle.a(S.a, (ad)this.n[2]);
/* 2612 */           pageStyle.a(T.a, (ad)this.n[3]);
/*      */           break;
/*      */       } 
/*      */     
/*      */     }
/* 2617 */     declaration.a(pageStyle);
/*      */ 
/*      */     
/* 2620 */     ad[] resets = E.c(pageStyle);
/* 2621 */     if (resets != null) {
/* 2622 */       for (int i = 0; i < resets.length; i++) {
/* 2623 */         o counterSet = (o)resets[i];
/* 2624 */         String name = counterSet.b();
/* 2625 */         int value = counterSet.c();
/* 2626 */         this.k.b().a(0, true).a(name, value);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2631 */     ad[] increments = D.c(pageStyle);
/* 2632 */     if (increments != null) {
/* 2633 */       i pc = this.k.b();
/* 2634 */       for (int i = 0; i < increments.length; i++) {
/* 2635 */         o counterSet = (o)increments[i];
/* 2636 */         String name = counterSet.b();
/* 2637 */         int delta = counterSet.c();
/* 2638 */         pc.a(0, true).b(name, delta);
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 2643 */     if (this.u == null) {
/* 2644 */       this.u = g.a;
/*      */     }
/*      */     
/* 2647 */     i params = new i();
/* 2648 */     params.D = this.v;
/* 2649 */     params.C = pageStyle.i();
/* 2650 */     params.F = this.k.q();
/*      */     
/* 2652 */     a lang = b.a((pageStyle.a()).G);
/* 2653 */     params.G = lang.a(pageStyle);
/*      */ 
/*      */     
/* 2656 */     double width = this.m.p();
/* 2657 */     double height = this.m.r();
/*      */     
/* 2659 */     if ((this.l.a() & 0x1) != 0) {
/* 2660 */       if (this.m.g() == 1) {
/*      */         
/* 2662 */         params.X = m.a(width, height, (byte)1, (byte)3);
/*      */       } else {
/*      */         
/* 2665 */         params.X = m.a(width, height, (byte)3, (byte)1);
/*      */       } 
/*      */     } else {
/* 2668 */       params.X = m.a(width, height, (byte)1, (byte)1);
/*      */     } 
/* 2670 */     params.ab = 0;
/*      */ 
/*      */     
/* 2673 */     ad marginTop = V.c(pageStyle);
/* 2674 */     ad marginRight = U.c(pageStyle);
/* 2675 */     ad marginBottom = S.c(pageStyle);
/* 2676 */     ad marginLeft = T.c(pageStyle);
/* 2677 */     t margin = b.a(marginTop, marginRight, marginBottom, marginLeft);
/*      */     
/* 2679 */     params.S = B.a(margin, A.a, this.u, t.d);
/*      */     
/* 2681 */     this.r++;
/* 2682 */     if (this.s != -1 && this.r > this.s) {
/* 2683 */       short code = 14341;
/* 2684 */       String[] args = { String.valueOf(this.s) };
/* 2685 */       this.k.a(code, args);
/* 2686 */       if (B.O.a(this.k) == 2) {
/* 2687 */         throw new a((byte)1);
/*      */       }
/* 2689 */       throw new a((byte)2);
/*      */     } 
/* 2691 */     this.k.a((short)6145, String.valueOf(this.r));
/* 2692 */     return new n(params, this.k);
/*      */   }
/*      */   
/*      */   public void a(n pageBox) throws c {
/*      */     AffineTransform marginT;
/* 2697 */     if (B.H.a(this.k)) {
/* 2698 */       this.m.c(pageBox.v());
/* 2699 */       this.m.d(pageBox.w());
/*      */     } else {
/* 2701 */       this.m.c(pageBox.p());
/* 2702 */       this.m.d(pageBox.q());
/*      */     } 
/* 2704 */     if (B.v.a(this.k) == null) {
/* 2705 */       this.m.q();
/*      */     }
/* 2707 */     if (B.w.a(this.k) == null) {
/* 2708 */       this.m.s();
/*      */     }
/*      */     
/* 2711 */     if ((this.l.a() & 0x1) != 0)
/*      */     {
/* 2713 */       this.k.a((short)6150, String.valueOf(pageBox.q()));
/*      */     }
/*      */ 
/*      */     
/* 2717 */     b gc = this.m.a();
/*      */     
/* 2719 */     if (B.H.a(this.k)) {
/* 2720 */       if (pageBox.v() > pageBox.p()) {
/* 2721 */         gc.a(AffineTransform.getTranslateInstance(pageBox.v() - pageBox.p(), 0.0D));
/*      */       }
/* 2723 */       this.m.c(pageBox.p());
/* 2724 */       this.m.d(pageBox.q());
/* 2725 */       if (B.v.a(this.k) == null) {
/* 2726 */         this.m.q();
/*      */       }
/* 2728 */       if (B.w.a(this.k) == null) {
/* 2729 */         this.m.s();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2734 */     if (gc != null) {
/* 2735 */       a margin = (pageBox.m()).b;
/* 2736 */       double xoff = margin.d;
/* 2737 */       double yoff = margin.a;
/* 2738 */       if (xoff != 0.0D || yoff != 0.0D) {
/* 2739 */         marginT = AffineTransform.getTranslateInstance(xoff, yoff);
/*      */       } else {
/* 2741 */         marginT = null;
/*      */       } 
/* 2743 */       if (marginT != null) {
/* 2744 */         gc.d();
/* 2745 */         gc.a(marginT);
/*      */       } 
/*      */     } else {
/* 2748 */       marginT = null;
/*      */     } 
/*      */     
/* 2751 */     a visitor = this.k.b(gc);
/* 2752 */     b b1 = new b(this, visitor) {
/*      */         public void a(AffineTransform transform, j box, double x, double y) {
/* 2754 */           super.a(transform, box, x, y);
/* 2755 */           Object key = (box.b()).al;
/* 2756 */           String[] pageContentClearNames = (String[])e.a(this.a).remove(key);
/* 2757 */           if (pageContentClearNames != null) {
/* 2758 */             for (int i = 0; i < pageContentClearNames.length; i++) {
/* 2759 */               e.b(this.a).remove(pageContentClearNames[i]);
/*      */             }
/*      */           }
/*      */           
/* 2763 */           b pageContent = (b)e.c(this.a).remove(key);
/* 2764 */           if (pageContent != null) {
/* 2765 */             e.b(this.a).put(pageContent.c, pageContent);
/*      */           }
/*      */         }
/*      */       };
/* 2769 */     b1.g();
/*      */     
/* 2771 */     g drawer = new g(0);
/*      */ 
/*      */     
/* 2774 */     pageBox.a(drawer, (a)b1);
/*      */     
/* 2776 */     if (gc != null) {
/*      */       
/* 2778 */       pageBox.b(drawer, (a)b1);
/*      */ 
/*      */       
/* 2781 */       if (!this.G.isEmpty()) {
/* 2782 */         for (Iterator<b> i = this.G.values().iterator(); i.hasNext(); ) {
/* 2783 */           boolean apply; b pageContent = i.next();
/*      */           
/* 2785 */           if (pageContent.a != null) {
/*      */ 
/*      */             
/* 2788 */             apply = false;
/* 2789 */             for (int j = 0; j < pageContent.a.length; j++) {
/* 2790 */               byte page = pageContent.a[j];
/* 2791 */               if (page == 0 && (this.q == a.p || this.q == a.o)) {
/*      */                 
/* 2793 */                 apply = true;
/*      */                 break;
/*      */               } 
/* 2796 */               if (this.q.a(page)) {
/* 2797 */                 apply = true;
/*      */                 break;
/*      */               } 
/*      */             } 
/*      */           } else {
/* 2802 */             apply = true;
/*      */           } 
/* 2804 */           if (apply) {
/* 2805 */             c style = this.p;
/* 2806 */             l styleContext = this.o;
/* 2807 */             this.o = pageContent.b;
/* 2808 */             pageContent.a(this);
/* 2809 */             this.o = styleContext;
/* 2810 */             this.p = style;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/* 2815 */       pageBox.c(drawer, (a)b1);
/* 2816 */       b1.h();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2822 */     if (gc != null) {
/* 2823 */       drawer.a(gc);
/* 2824 */       if (marginT != null) {
/* 2825 */         gc.e();
/*      */       }
/*      */     } 
/* 2828 */     this.m.b();
/*      */   }
/*      */   
/*      */   public void g() throws c {
/* 2832 */     this.l.c();
/* 2833 */     this.m.z();
/* 2834 */     this.k.b().a(this.r);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/d/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */