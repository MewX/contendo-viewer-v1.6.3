/*      */ package jp.cssj.homare.css.a;
/*      */ 
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.Path2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.net.URI;
/*      */ import java.net.URISyntaxException;
/*      */ import java.util.Map;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import jp.cssj.e.e.c;
/*      */ import jp.cssj.e.e.d;
/*      */ import jp.cssj.homare.b.f.e;
/*      */ import jp.cssj.homare.css.a;
/*      */ import jp.cssj.homare.css.c;
/*      */ import jp.cssj.homare.css.c.j;
/*      */ import jp.cssj.homare.css.e.c;
/*      */ import jp.cssj.homare.css.e.f;
/*      */ import jp.cssj.homare.css.e.l;
/*      */ import jp.cssj.homare.css.f.A;
/*      */ import jp.cssj.homare.css.f.E;
/*      */ import jp.cssj.homare.css.f.G;
/*      */ import jp.cssj.homare.css.f.J;
/*      */ import jp.cssj.homare.css.f.K;
/*      */ import jp.cssj.homare.css.f.L;
/*      */ import jp.cssj.homare.css.f.M;
/*      */ import jp.cssj.homare.css.f.N;
/*      */ import jp.cssj.homare.css.f.O;
/*      */ import jp.cssj.homare.css.f.P;
/*      */ import jp.cssj.homare.css.f.S;
/*      */ import jp.cssj.homare.css.f.U;
/*      */ import jp.cssj.homare.css.f.V;
/*      */ import jp.cssj.homare.css.f.X;
/*      */ import jp.cssj.homare.css.f.Y;
/*      */ import jp.cssj.homare.css.f.a;
/*      */ import jp.cssj.homare.css.f.ac;
/*      */ import jp.cssj.homare.css.f.ad;
/*      */ import jp.cssj.homare.css.f.ae;
/*      */ import jp.cssj.homare.css.f.af;
/*      */ import jp.cssj.homare.css.f.ah;
/*      */ import jp.cssj.homare.css.f.b.h;
/*      */ import jp.cssj.homare.css.f.c.a;
/*      */ import jp.cssj.homare.css.f.d;
/*      */ import jp.cssj.homare.css.f.e;
/*      */ import jp.cssj.homare.css.f.h;
/*      */ import jp.cssj.homare.css.f.i;
/*      */ import jp.cssj.homare.css.f.l;
/*      */ import jp.cssj.homare.css.f.m;
/*      */ import jp.cssj.homare.css.f.n;
/*      */ import jp.cssj.homare.css.f.s;
/*      */ import jp.cssj.homare.css.f.t;
/*      */ import jp.cssj.homare.css.f.u;
/*      */ import jp.cssj.homare.css.f.w;
/*      */ import jp.cssj.homare.css.f.x;
/*      */ import jp.cssj.homare.css.f.y;
/*      */ import jp.cssj.homare.impl.a.b.a;
/*      */ import jp.cssj.homare.impl.a.b.e;
/*      */ import jp.cssj.homare.impl.a.b.f;
/*      */ import jp.cssj.homare.impl.a.b.g;
/*      */ import jp.cssj.homare.impl.a.b.i;
/*      */ import jp.cssj.homare.impl.a.c.A;
/*      */ import jp.cssj.homare.impl.a.c.C;
/*      */ import jp.cssj.homare.impl.a.c.F;
/*      */ import jp.cssj.homare.impl.a.c.G;
/*      */ import jp.cssj.homare.impl.a.c.I;
/*      */ import jp.cssj.homare.impl.a.c.K;
/*      */ import jp.cssj.homare.impl.a.c.L;
/*      */ import jp.cssj.homare.impl.a.c.O;
/*      */ import jp.cssj.homare.impl.a.c.R;
/*      */ import jp.cssj.homare.impl.a.c.S;
/*      */ import jp.cssj.homare.impl.a.c.T;
/*      */ import jp.cssj.homare.impl.a.c.U;
/*      */ import jp.cssj.homare.impl.a.c.V;
/*      */ import jp.cssj.homare.impl.a.c.a;
/*      */ import jp.cssj.homare.impl.a.c.a.c;
/*      */ import jp.cssj.homare.impl.a.c.ab;
/*      */ import jp.cssj.homare.impl.a.c.ac;
/*      */ import jp.cssj.homare.impl.a.c.ad;
/*      */ import jp.cssj.homare.impl.a.c.ae;
/*      */ import jp.cssj.homare.impl.a.c.af;
/*      */ import jp.cssj.homare.impl.a.c.ag;
/*      */ import jp.cssj.homare.impl.a.c.ah;
/*      */ import jp.cssj.homare.impl.a.c.ai;
/*      */ import jp.cssj.homare.impl.a.c.ak;
/*      */ import jp.cssj.homare.impl.a.c.am;
/*      */ import jp.cssj.homare.impl.a.c.an;
/*      */ import jp.cssj.homare.impl.a.c.ao;
/*      */ import jp.cssj.homare.impl.a.c.aq;
/*      */ import jp.cssj.homare.impl.a.c.ar;
/*      */ import jp.cssj.homare.impl.a.c.as;
/*      */ import jp.cssj.homare.impl.a.c.au;
/*      */ import jp.cssj.homare.impl.a.c.aw;
/*      */ import jp.cssj.homare.impl.a.c.b.h;
/*      */ import jp.cssj.homare.impl.a.c.c.a;
/*      */ import jp.cssj.homare.impl.a.c.c.c;
/*      */ import jp.cssj.homare.impl.a.c.c.d;
/*      */ import jp.cssj.homare.impl.a.c.c.e;
/*      */ import jp.cssj.homare.impl.a.c.f;
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
/*      */ import jp.cssj.homare.impl.a.c.u;
/*      */ import jp.cssj.homare.impl.a.c.w;
/*      */ import jp.cssj.homare.impl.a.c.x;
/*      */ import jp.cssj.homare.impl.a.c.y;
/*      */ import jp.cssj.homare.impl.a.c.z;
/*      */ import jp.cssj.homare.ua.a.B;
/*      */ import jp.cssj.homare.ua.g;
/*      */ import jp.cssj.homare.ua.m;
/*      */ import jp.cssj.sakae.e.d;
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
/*      */ public class b
/*      */ {
/*      */   static {
/*  138 */     b = Logger.getLogger(b.class.getName());
/*      */     
/*  140 */     c = w.a(20.0D);
/*  141 */     d = u.a(4.0D);
/*  142 */     e = u.a(1.12D);
/*  143 */     f = u.a(1.0D);
/*  144 */     g = u.a(0.5D);
/*  145 */     h = u.a(-1.0D);
/*  146 */     i = u.a(-0.9D);
/*  147 */     j = S.a(1.618D);
/*  148 */     k = S.a(1.414D);
/*  149 */     l = new ae(new ad[] { (ad)new V("​") });
/*  150 */     m = new ae(new ad[] { (ad)P.e });
/*  151 */     n = new ae(new ad[] { (ad)P.f });
/*  152 */     o = new ae(new ad[] { (ad)new V("") });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void a(c style, E length, short code) {
/*  161 */     if (style.c() == null) {
/*      */       return;
/*      */     }
/*      */     
/*  165 */     c pStyle = style.c();
/*  166 */     if (pStyle != null && ((jp.cssj.homare.impl.a.c.b.b.c(pStyle) == 1 && 
/*  167 */       e.a(c.c(pStyle))) || 
/*  168 */       jp.cssj.homare.impl.a.c.b.b.c(pStyle) == 3)) {
/*      */       
/*  170 */       style.a(T.a, (ad)length);
/*  171 */       style.a(U.a, (ad)length);
/*      */     } else {
/*      */       
/*  174 */       style.a(V.a, (ad)length);
/*  175 */       style.a(S.a, (ad)length);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void a(String elem, c style) {
/*  185 */     m ua = style.b();
/*  186 */     a ce = style.a();
/*  187 */     style.a(G.a, (ad)t.E);
/*  188 */     style.a(ai.a, (ad)K.b);
/*      */     
/*  190 */     style.a(as.a, (ad)af.b);
/*      */     
/*  192 */     String str = ce.H.getValue("valign");
/*  193 */     if (str != null) {
/*  194 */       c.a(elem, style, str);
/*      */     } else {
/*  196 */       c parentStyle = style.c();
/*  197 */       while (parentStyle != null) {
/*  198 */         a parentCe = parentStyle.a();
/*  199 */         switch (a.a(parentCe)) {
/*      */           case 1801:
/*      */           case 1802:
/*      */           case 1805:
/*      */           case 1807:
/*      */           case 1809:
/*  205 */             str = parentCe.H.getValue("valign");
/*  206 */             if (str == null) {
/*      */               break;
/*      */             }
/*  209 */             c.a(elem, style, str);
/*      */             break;
/*      */         } 
/*  212 */         parentStyle = parentStyle.c();
/*      */       } 
/*      */     } 
/*      */     
/*  216 */     c.a(elem, style);
/*  217 */     c.j(elem, style);
/*  218 */     c.k(elem, style);
/*  219 */     if (ce.H.getValue("nowrap") != null) {
/*  220 */       style.a(au.a, (ad)ah.c);
/*      */     }
/*  222 */     E cellpadding = c.c(style);
/*  223 */     style.a(af.a, (ad)cellpadding, (byte)-1);
/*  224 */     style.a(ae.a, (ad)cellpadding, (byte)-1);
/*  225 */     style.a(ac.a, (ad)cellpadding, (byte)-1);
/*  226 */     style.a(ad.a, (ad)cellpadding, (byte)-1);
/*  227 */     jp.cssj.homare.css.f.c.b border = d.c(style);
/*  228 */     if (!border.b().e()) {
/*  229 */       i borderStyle; n borderColor = border.c();
/*      */       
/*  231 */       if (borderColor == null) {
/*  232 */         borderStyle = i.t;
/*      */       } else {
/*  234 */         borderStyle = i.n;
/*  235 */         style.a(q.a, (ad)borderColor);
/*  236 */         style.a(m.a, (ad)borderColor);
/*  237 */         style.a(f.a, (ad)borderColor);
/*  238 */         style.a(j.a, (ad)borderColor);
/*      */       } 
/*  240 */       a a = ua.c((byte)1);
/*  241 */       style.a(r.a, (ad)borderStyle);
/*  242 */       style.a(s.a, (ad)a);
/*  243 */       style.a(n.a, (ad)borderStyle);
/*  244 */       style.a(o.a, (ad)a);
/*  245 */       style.a((j)g.a, (ad)borderStyle);
/*  246 */       style.a(h.a, (ad)a);
/*  247 */       style.a((j)k.a, (ad)borderStyle);
/*  248 */       style.a(l.a, (ad)a);
/*      */     } 
/*  250 */     c.g(elem, style);
/*  251 */     c parent = style.c();
/*  252 */     for (; parent != null; parent = parent.c()) {
/*  253 */       a parentCe = parent.a();
/*  254 */       if (a.a(parentCe) == 1801) {
/*  255 */         String rules = parentCe.H.getValue("rules");
/*  256 */         if (rules != null) {
/*  257 */           if (rules.equalsIgnoreCase("all")) {
/*  258 */             style.a(n.a, (ad)i.n);
/*  259 */             style.a(o.a, (ad)ua.c((byte)1));
/*  260 */             style.a((j)k.a, (ad)i.n);
/*  261 */             style.a(l.a, (ad)ua.c((byte)1));
/*  262 */             style.a(r.a, (ad)i.n);
/*  263 */             style.a(s.a, (ad)ua.c((byte)1));
/*  264 */             style.a((j)g.a, (ad)i.n);
/*  265 */             style.a(h.a, (ad)ua.c((byte)1)); break;
/*  266 */           }  if (rules.equalsIgnoreCase("cols")) {
/*  267 */             style.a(n.a, (ad)i.n);
/*  268 */             style.a(o.a, (ad)ua.c((byte)1));
/*  269 */             style.a((j)k.a, (ad)i.n);
/*  270 */             style.a(l.a, (ad)ua.c((byte)1));
/*  271 */             style.a(r.a, (ad)i.k);
/*  272 */             style.a((j)g.a, (ad)i.k); break;
/*      */           } 
/*  274 */           style.a(r.a, (ad)i.k);
/*  275 */           style.a((j)g.a, (ad)i.k);
/*  276 */           style.a(n.a, (ad)i.k);
/*  277 */           style.a((j)k.a, (ad)i.k);
/*      */         } 
/*      */         break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void a(c style, String src, String type, String alt) {
/*  286 */     if (src != null) {
/*  287 */       m ua = style.b();
/*      */       
/*      */       try {
/*  290 */         URI uri = d.a(ua.c().c(), ua.c().a(), src);
/*      */         
/*  292 */         jp.cssj.e.b source = ua.b(uri);
/*      */         try {
/*  294 */           c c1 = new c(source, type) {
/*      */               public String c() throws IOException {
/*  296 */                 return (this.a == null) ? super.c() : this.a;
/*      */               }
/*      */             };
/*  299 */           jp.cssj.sakae.c.b.b image = ua.c((jp.cssj.e.b)c1);
/*  300 */           if (image != null) {
/*  301 */             e.a(style, image);
/*      */             return;
/*      */           } 
/*      */         } finally {
/*  305 */           ua.a(source);
/*      */         } 
/*  307 */       } catch (Exception e) {
/*  308 */         b.log(Level.FINE, "Missing image", e);
/*  309 */         ua.a((short)10257, src);
/*      */       } 
/*  311 */       a(style, alt);
/*      */     } 
/*  313 */     if (alt != null) {
/*  314 */       style.a(C.a, (ad)new ae(new ad[] { (ad)new V(alt) }));
/*      */     }
/*      */   }
/*      */   
/*      */   private static void a(c style, String alt) {
/*  319 */     m ua = style.b();
/*  320 */     int brokenimage = B.R.a(ua);
/*  321 */     if (brokenimage == 4 && B.ae
/*  322 */       .a(ua) == 8) {
/*  323 */       ua.a((short)10258, B.R.a, "annotation", "PDF/X-1a");
/*      */       
/*  325 */       brokenimage = 3;
/*      */     } 
/*      */     
/*  328 */     switch (brokenimage) {
/*      */       case 4:
/*  330 */         e.a(style, (jp.cssj.sakae.c.b.b)new i(ua, alt));
/*      */         return;
/*      */       case 3:
/*  333 */         e.a(style, (jp.cssj.sakae.c.b.b)new a(ua, alt));
/*      */         return;
/*      */       case 2:
/*  336 */         e.a(style, (jp.cssj.sakae.c.b.b)new e(alt));
/*      */         return;
/*      */       case 1:
/*  339 */         if (alt != null) {
/*  340 */           e.a(style, alt);
/*      */         }
/*      */         return;
/*      */     } 
/*  344 */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */   
/*      */   private static void a(c style, boolean disabled) {
/*  349 */     m ua = style.b();
/*  350 */     style.a(G.a, (ad)t.t);
/*  351 */     style.a(L.a, (ad)f);
/*  352 */     if (disabled) {
/*  353 */       style.a(u.a, (ad)c.ad);
/*      */     }
/*  355 */     style.a(am.a, (ad)X.j);
/*  356 */     style.a(jp.cssj.homare.impl.a.c.b.a, (ad)c.aD);
/*  357 */     a thin = ua.c((byte)1);
/*  358 */     style.a(r.a, (ad)i.r);
/*  359 */     style.a(s.a, (ad)thin);
/*  360 */     style.a((j)k.a, (ad)i.r);
/*  361 */     style.a(l.a, (ad)thin);
/*  362 */     style.a((j)g.a, (ad)i.r);
/*  363 */     style.a(h.a, (ad)thin);
/*  364 */     style.a(n.a, (ad)i.r);
/*  365 */     style.a(o.a, (ad)thin);
/*  366 */     style.a(af.a, (ad)thin);
/*  367 */     style.a(ac.a, (ad)thin);
/*  368 */     style.a(ad.a, (ad)thin);
/*  369 */     style.a(ae.a, (ad)thin);
/*  370 */     style.a(au.a, (ad)ah.c);
/*      */   }
/*      */   
/*      */   private static void a(c style, boolean disabled, String size) {
/*  374 */     m ua = style.b();
/*  375 */     style.a(G.a, (ad)t.t);
/*  376 */     style.a(a.a, (ad)c);
/*  377 */     if (size != null) {
/*      */       try {
/*  379 */         style.a(a.a, (ad)w.a(d.a(size)));
/*  380 */       } catch (NumberFormatException e) {
/*  381 */         ua.a((short)10248, "INPUT", "size", size);
/*      */       } 
/*      */     }
/*      */     
/*  385 */     style.a(L.a, (ad)f);
/*  386 */     if (disabled) {
/*  387 */       style.a(u.a, (ad)c.ad);
/*  388 */       style.a(jp.cssj.homare.impl.a.c.b.a, (ad)c.aD);
/*      */     } else {
/*  390 */       style.a(jp.cssj.homare.impl.a.c.b.a, (ad)c.o);
/*      */     } 
/*  392 */     a a = ua.c((byte)1);
/*  393 */     style.a(r.a, (ad)i.t);
/*  394 */     style.a(s.a, (ad)a);
/*  395 */     style.a((j)k.a, (ad)i.t);
/*  396 */     style.a(l.a, (ad)a);
/*  397 */     style.a((j)g.a, (ad)i.t);
/*  398 */     style.a(h.a, (ad)a);
/*  399 */     style.a(n.a, (ad)i.t);
/*  400 */     style.a(o.a, (ad)a);
/*  401 */     style.a(af.a, (ad)a);
/*  402 */     style.a(ac.a, (ad)a);
/*  403 */     style.a(ad.a, (ad)a);
/*  404 */     style.a(ae.a, (ad)a);
/*  405 */     style.a(au.a, (ad)ah.c);
/*      */   }
/*      */   
/*      */   private static void b(String elem, c style) {
/*  409 */     style.a(jp.cssj.homare.impl.a.c.c.b.a, (ad)a.a);
/*  410 */     m ua = style.b();
/*  411 */     c.g(elem, style);
/*  412 */     c.j(elem, style);
/*  413 */     jp.cssj.homare.css.f.c.b border = d.c(style);
/*  414 */     if (!border.b().e()) {
/*  415 */       c parent = style.c();
/*  416 */       for (; parent != null; parent = parent.c()) {
/*  417 */         a parentCe = parent.a();
/*  418 */         if (a.a(parentCe) == 1801) {
/*  419 */           if ("groups".equalsIgnoreCase(parentCe.H.getValue("rules"))) {
/*  420 */             style.a(r.a, (ad)i.n);
/*  421 */             style.a(s.a, (ad)ua.c((byte)1));
/*  422 */             style.a((j)g.a, (ad)i.n);
/*  423 */             style.a(h.a, (ad)ua.c((byte)1));
/*      */           } 
/*      */           break;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void c(String elem, c style) {
/*  432 */     a ce = style.a();
/*  433 */     m ua = style.b();
/*  434 */     c.j(elem, style);
/*  435 */     String width = ce.H.getValue("width");
/*  436 */     if (width != null) {
/*      */       try {
/*  438 */         O length = c.a(ua, width);
/*  439 */         if (length.d()) {
/*  440 */           throw new NumberFormatException();
/*      */         }
/*  442 */         style.a(aw.a, (ad)length);
/*  443 */       } catch (Exception e) {
/*  444 */         ua.a((short)10248, elem, "width", width);
/*      */       } 
/*      */     }
/*  447 */     c.g(elem, style);
/*  448 */     c.a(elem, style, ce.H.getValue("valign"));
/*      */     
/*  450 */     E cellpadding = c.c(style);
/*  451 */     style.a(af.a, (ad)cellpadding, (byte)-1);
/*  452 */     style.a(ae.a, (ad)cellpadding, (byte)-1);
/*  453 */     style.a(ac.a, (ad)cellpadding, (byte)-1);
/*  454 */     style.a(ad.a, (ad)cellpadding, (byte)-1);
/*      */   }
/*      */   
/*      */   public static boolean a(a ce) {
/*  458 */     short code = a.a(ce);
/*  459 */     switch (code) {
/*      */       case 211:
/*      */       case 805:
/*      */       case 807:
/*      */       case 1501:
/*  464 */         return true;
/*      */     } 
/*  466 */     return false;
/*      */   }
/*      */   
/*      */   public static boolean b(a ce) {
/*  470 */     short code = a.a(ce);
/*  471 */     switch (code) {
/*      */       case 805:
/*      */       case 807:
/*      */       case 1501:
/*      */       case 1704:
/*      */       case 2101:
/*  477 */         return true;
/*      */     } 
/*  479 */     return false;
/*      */   }
/*      */   
/*      */   public static void a(c style) { byte type;
/*      */     String prompt;
/*  484 */     if (!a && style.a() != a.s) throw new AssertionError(); 
/*  485 */     a parentCe = style.c().a();
/*  486 */     short code = a.a(parentCe);
/*  487 */     switch (code) {
/*      */       
/*      */       case 211:
/*  490 */         style.a(C.a, (ad)l);
/*      */         break;
/*      */       
/*      */       case 805:
/*  494 */         type = c.a(parentCe.H.getValue("type"));
/*  495 */         if (type == 5) {
/*  496 */           a(style, (parentCe.H.getValue("disabled") != null), parentCe.H
/*  497 */               .getValue("size"));
/*  498 */           style.a(C.a, (ad)l);
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 807:
/*  503 */         prompt = parentCe.H.getValue("prompt");
/*  504 */         if (prompt != null) {
/*  505 */           style.a(C.a, (ad)new ae(new ad[] { (ad)new V(prompt) }));
/*      */         }
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1501:
/*  511 */         style.a(C.a, (ad)m);
/*      */         break;
/*      */     }  } public static void b(c style) {
/*      */     byte type;
/*      */     String value;
/*      */     m ua;
/*      */     c parent;
/*      */     double size, border;
/*  519 */     if (!a && style.a() != a.t) throw new AssertionError(); 
/*  520 */     a parentCe = style.c().a();
/*  521 */     short code = a.a(parentCe);
/*  522 */     switch (code) {
/*      */       
/*      */       case 805:
/*  525 */         type = c.a(parentCe.H.getValue("type"));
/*  526 */         switch (type) {
/*      */           case 2:
/*  528 */             value = parentCe.H.getValue("value");
/*  529 */             if (value != null) {
/*  530 */               char[] chars = new char[value.length()];
/*  531 */               for (int i = 0; i < chars.length; i++) {
/*  532 */                 chars[i] = '*';
/*      */               }
/*  534 */               style.a(C.a, (ad)new ae(new ad[] { (ad)new V(new String(chars)) })); break;
/*      */             } 
/*  536 */             style.a(C.a, (ad)l);
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 5:
/*  542 */             a(style, (parentCe.H.getValue("disabled") != null));
/*  543 */             style.a(C.a, (ad)new ae(new ad[] { (ad)new V("選択...") }));
/*      */             break;
/*      */ 
/*      */           
/*      */           case 1:
/*      */           case 7:
/*      */           case 8:
/*      */           case 9:
/*  551 */             value = parentCe.H.getValue("value");
/*  552 */             if (value != null) {
/*  553 */               style.a(C.a, (ad)new ae(new ad[] { (ad)new V(value) })); break;
/*      */             } 
/*  555 */             style.a(C.a, (ad)l);
/*      */             break;
/*      */         } 
/*      */ 
/*      */         
/*      */         break;
/*      */       
/*      */       case 807:
/*  563 */         a(style, false, (String)null);
/*  564 */         style.a(C.a, (ad)l);
/*      */         break;
/*      */       
/*      */       case 1501:
/*  568 */         style.a(C.a, (ad)n);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2101:
/*  573 */         style.a(C.a, (ad)l);
/*      */         break;
/*      */       
/*      */       case 1704:
/*  577 */         ua = style.b();
/*  578 */         parent = style.c();
/*  579 */         size = f.a(ua, L.d(parent).b(), (short)21, (short)17);
/*      */         
/*  581 */         style.a(y.a, (ad)N.h);
/*  582 */         border = s.c(parent);
/*  583 */         style.a(aq.a, (ad)a.a(ua, -border * 2.0D));
/*  584 */         style.a(ak.a, (ad)a.a(ua, -L.d(parent).b() - border));
/*  585 */         e.a(style, (jp.cssj.sakae.c.b.b)new g((parentCe.H.getValue("disabled") != null), size));
/*  586 */         style.a(C.a, (ad)o);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*  592 */   private n p = null;
/*      */   
/*  594 */   private g q = null; public void c(c style) { String href, str8; a px8; String clear, align; c c2; String str7, str6; a a4; String str5; u u1; boolean border; String str4; byte b1; c c1; String str3; Map<Object, g> imageMaps; String src, type, wrap; c pStyle; String str2; a a3; ad cellspacing; E e1; a a2; String str1; c parent; String str; a a1; int depth; String poster, shape, str15, str14; n color; String str13, alt, str12, mapName, str11, str10; a a5; c c3; String str9, coords, str19, str18; E size; String str17, str16; E cellpadding; a a6; Shape realShape; String str21; O width; String str20; n borderColor; String str24; c c4; String str23;
/*      */     i borderStyle;
/*      */     String str22, str25;
/*  597 */     m ua = style.b();
/*  598 */     a ce = style.a();
/*  599 */     if (!a && (ce == a.s || ce == a.t)) throw new AssertionError();
/*      */ 
/*      */ 
/*      */     
/*  603 */     String dir = ce.H.getValue("dir");
/*  604 */     if (dir != null) {
/*  605 */       if (dir.equalsIgnoreCase("ltr")) {
/*  606 */         style.a(ar.a, (ad)ac.e);
/*  607 */         style.a(F.a, (ad)s.a);
/*  608 */       } else if (dir.equalsIgnoreCase("rtl")) {
/*  609 */         style.a(ar.a, (ad)ac.e);
/*  610 */         style.a(F.a, (ad)s.b);
/*      */       } else {
/*  612 */         ua.a((short)10248, "*", "dir", dir);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  617 */     short code = a.a(ce);
/*  618 */     switch (code) {
/*      */       
/*      */       case 101:
/*  621 */         if (ce.a((byte)7)) {
/*      */           
/*  623 */           style.a(an.a, (ad)Y.a((byte)1));
/*  624 */           if (this.p == null) {
/*  625 */             this.p = c.c;
/*      */           }
/*  627 */           style.a(u.a, (ad)this.p);
/*      */         } 
/*      */         break;
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
/*      */       case 104:
/*  641 */         style.a(G.a, (ad)t.r);
/*  642 */         style.a(x.a, (ad)y.b);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 105:
/*  647 */         c.a("APPLET", style);
/*  648 */         c.b("APPLET", style);
/*  649 */         c.e("APPLET", style);
/*  650 */         style.a(G.a, (ad)t.t);
/*  651 */         a(style, ce.H.getValue("alt"));
/*      */         break;
/*      */ 
/*      */       
/*      */       case 106:
/*  656 */         style.a(G.a, (ad)t.s);
/*  657 */         if (this.q == null) {
/*      */           break;
/*      */         }
/*  660 */         href = ce.H.getValue("href");
/*  661 */         if (href == null) {
/*      */           break;
/*      */         }
/*  664 */         shape = ce.H.getValue("shape");
/*  665 */         coords = ce.H.getValue("coords");
/*  666 */         realShape = null;
/*  667 */         if (shape == null || shape.equalsIgnoreCase("default") || coords == null) {
/*  668 */           realShape = null;
/*      */         } else {
/*  670 */           shape = shape.toLowerCase();
/*  671 */           String[] coordsArray = coords.split(",");
/*  672 */           double[] realCoords = new double[coordsArray.length];
/*  673 */           for (int i = 0; i < realCoords.length; i++) {
/*      */             try {
/*  675 */               realCoords[i] = Double.parseDouble(coordsArray[i].trim());
/*  676 */             } catch (NumberFormatException e) {
/*  677 */               ua.a((short)10248, "AREA", "coords", coords);
/*  678 */               realCoords[i] = 0.0D;
/*      */             } 
/*      */           } 
/*      */           try {
/*  682 */             if (shape.startsWith("circ")) {
/*  683 */               realShape = new Ellipse2D.Double(realCoords[0] - realCoords[2] / 2.0D, realCoords[1] - realCoords[2] / 2.0D, realCoords[2] * 2.0D, realCoords[2] * 2.0D);
/*      */             }
/*  685 */             else if (shape.startsWith("rect")) {
/*  686 */               realShape = new Rectangle2D.Double(realCoords[0], realCoords[1], realCoords[2] - realCoords[0], realCoords[3] - realCoords[1]);
/*      */             }
/*  688 */             else if (shape.startsWith("poly")) {
/*  689 */               Path2D.Double path = new Path2D.Double();
/*  690 */               path.moveTo(realCoords[0], realCoords[1]);
/*  691 */               for (int j = 2; j < realCoords.length; j += 2) {
/*  692 */                 path.lineTo(realCoords[j], realCoords[j + 1]);
/*      */               }
/*  694 */               path.closePath();
/*  695 */               realShape = path;
/*      */             } else {
/*  697 */               ua.a((short)10248, "AREA", "shape", shape);
/*      */             } 
/*  699 */           } catch (ArrayIndexOutOfBoundsException e) {
/*  700 */             ua.a((short)10248, "AREA", "coords", coords);
/*      */           } 
/*      */         } 
/*      */         try {
/*  704 */           g.a area = new g.a(realShape, d.a(ua.c().c(), ua
/*  705 */                 .c().a(), href));
/*  706 */           this.q.add(area);
/*  707 */         } catch (URISyntaxException e) {
/*  708 */           ua.a((short)10248, "AREA", "href", shape);
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       case 201:
/*  714 */         style.a(K.a, (ad)A.p);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 202:
/*  719 */         style.a(G.a, (ad)t.q);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 203:
/*  724 */         c.h("BASEFONT", style);
/*  725 */         c.a(style);
/*  726 */         c.i("BASEFONT", style);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 205:
/*  731 */         style.a(G.a, (ad)t.q);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 204:
/*  736 */         str8 = ce.H.getValue("dir");
/*  737 */         if (str8 != null) {
/*  738 */           if (str8.equalsIgnoreCase("ltr")) {
/*  739 */             style.a(ar.a, (ad)ac.f);
/*  740 */             style.a(F.a, (ad)s.a); break;
/*  741 */           }  if (str8.equalsIgnoreCase("rtl")) {
/*  742 */             style.a(ar.a, (ad)ac.f);
/*  743 */             style.a(F.a, (ad)s.b); break;
/*      */           } 
/*  745 */           ua.a((short)10248, "BDO", "dir", str8);
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 206:
/*  752 */         style.a(I.a, (ad)U.c);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 207:
/*  757 */         style.a(an.a, (ad)Y.a((byte)8));
/*      */         break;
/*      */ 
/*      */       
/*      */       case 208:
/*  762 */         style.a(G.a, (ad)t.r);
/*  763 */         a(style, (E)b.e, code);
/*  764 */         c.b(style, (E)a.a(ua, 40.0D, (short)17));
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 209:
/*  773 */         style.a(G.a, (ad)t.r);
/*  774 */         px8 = a.a(ua, 8.0D, (short)17);
/*  775 */         style.a(V.a, (ad)px8);
/*  776 */         style.a(U.a, (ad)px8);
/*  777 */         style.a(S.a, (ad)px8);
/*  778 */         style.a(T.a, (ad)px8);
/*  779 */         c.c("BODY", style);
/*      */         
/*  781 */         str15 = ce.H.getValue("topmargin");
/*  782 */         if (str15 != null) {
/*      */           try {
/*  784 */             O o = c.a(ua, str15);
/*  785 */             style.a(V.a, (ad)o);
/*  786 */           } catch (Exception e) {
/*  787 */             ua.a((short)10248, "BODY", "topmargin", str15);
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/*  792 */         str15 = ce.H.getValue("rightmargin");
/*  793 */         if (str15 != null) {
/*      */           try {
/*  795 */             O o = c.a(ua, str15);
/*  796 */             style.a(U.a, (ad)o);
/*  797 */           } catch (Exception e) {
/*  798 */             ua.a((short)10248, "BODY", "rightmargin", str15);
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/*  803 */         str15 = ce.H.getValue("leftmargin");
/*  804 */         if (str15 != null) {
/*      */           try {
/*  806 */             O o = c.a(ua, str15);
/*  807 */             style.a(T.a, (ad)o);
/*  808 */           } catch (Exception e) {
/*  809 */             ua.a((short)10248, "BODY", "leftmargin", str15);
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/*  814 */         str15 = ce.H.getValue("bottommargin");
/*  815 */         if (str15 != null) {
/*      */           try {
/*  817 */             O o = c.a(ua, str15);
/*  818 */             style.a(S.a, (ad)o);
/*  819 */           } catch (Exception e) {
/*  820 */             ua.a((short)10248, "BODY", "bottommargin", str15);
/*      */           } 
/*      */         }
/*      */         
/*  824 */         c.j("BODY", style);
/*  825 */         c.k("BODY", style);
/*      */         
/*  827 */         str15 = ce.H.getValue("bgproperties");
/*  828 */         if (str15 != null && str15.equalsIgnoreCase("fixed")) {
/*  829 */           style.a(a.a, (ad)e.b);
/*      */         }
/*      */ 
/*      */         
/*  833 */         str15 = ce.H.getValue("link");
/*  834 */         if (str15 != null) {
/*  835 */           this.p = c.b(str15);
/*  836 */           if (this.p == null) {
/*  837 */             ua.a((short)10248, "BODY", "link", str15);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/*  842 */         str15 = ce.H.getValue("text");
/*  843 */         if (str15 != null) {
/*  844 */           n n1 = c.b(str15);
/*  845 */           style.a(u.a, (ad)n1);
/*  846 */           if (n1 == null) {
/*  847 */             ua.a((short)10248, "BODY", "text" + str15);
/*      */           }
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 210:
/*  855 */         clear = ce.H.getValue("clear");
/*  856 */         if (clear != null) {
/*  857 */           if (clear.equalsIgnoreCase("all") || clear.equalsIgnoreCase("both")) {
/*  858 */             style.a(A.a, (ad)m.f); break;
/*  859 */           }  if (clear.equalsIgnoreCase("left")) {
/*  860 */             style.a(A.a, (ad)m.b); break;
/*  861 */           }  if (clear.equalsIgnoreCase("right")) {
/*  862 */             style.a(A.a, (ad)m.c);
/*      */           }
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       case 211:
/*  869 */         style.a(I.a, (ad)a.a(ua, ua.d((byte)4)));
/*  870 */         a(style, (ce.H.getValue("disabled") != null));
/*      */         break;
/*      */ 
/*      */       
/*      */       case 301:
/*  875 */         style.a(G.a, (ad)t.F);
/*  876 */         style.a(am.a, (ad)X.j);
/*  877 */         align = ce.H.getValue("align");
/*  878 */         if (align == null) {
/*  879 */           align = ce.H.getValue("valign");
/*      */         }
/*  881 */         if (align != null && align.equals("bottom")) {
/*  882 */           style.a(z.a, (ad)l.f);
/*  883 */           style.a(ah.a, (ad)L.p); break;
/*      */         } 
/*  885 */         style.a(ag.a, (ad)L.p);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 302:
/*  891 */         style.a(G.a, (ad)t.r);
/*  892 */         style.a(am.a, (ad)X.j);
/*  893 */         style.a(jp.cssj.homare.impl.a.c.c.b.a, (ad)a.c);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 303:
/*  898 */         style.a(x.a, (ad)y.b);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 304:
/*  903 */         style.a((j)w.a, (ad)x.e);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 306:
/*  908 */         c2 = style.c();
/*  909 */         for (; c2 != null; c2 = c2.c()) {
/*  910 */           a parentCe = c2.a();
/*  911 */           jp.cssj.homare.css.f.c.b b2 = d.c(style);
/*  912 */           if (!b2.b().e())
/*      */           {
/*  914 */             if (a.a(parentCe) == 1801) {
/*  915 */               if ("groups".equalsIgnoreCase(parentCe.H.getValue("rules"))) {
/*  916 */                 style.a(n.a, (ad)i.n);
/*  917 */                 style.a(o.a, (ad)ua.c((byte)1));
/*  918 */                 style.a((j)k.a, (ad)i.n);
/*  919 */                 style.a(l.a, (ad)ua.c((byte)1));
/*      */               } 
/*      */               break;
/*      */             } 
/*      */           }
/*      */         } 
/*  925 */         style.a(G.a, (ad)t.A);
/*  926 */         c("COLGROUP", style);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 305:
/*  931 */         style.a(G.a, (ad)t.z);
/*  932 */         c("COL", style);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 307:
/*  937 */         style.a(G.a, (ad)t.q);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 401:
/*  942 */         style.a(G.a, (ad)t.r);
/*  943 */         style.a(ah.a, (ad)L.p);
/*  944 */         c.a(style, (E)a.a(ua, 40.0D, (short)17));
/*      */         break;
/*      */ 
/*      */       
/*      */       case 402:
/*  949 */         style.a(an.a, (ad)Y.a((byte)4));
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 404:
/*  958 */         style.a(G.a, (ad)t.r);
/*  959 */         a(style, (E)b.e, code);
/*  960 */         c.a(style, (E)a.a(ua, 40.0D, (short)17));
/*  961 */         str7 = ce.H.getValue("type");
/*  962 */         if (str7 != null) {
/*  963 */           G g1 = c.c(str7);
/*  964 */           if (g1 != null) {
/*  965 */             style.a(R.a, (ad)g1); break;
/*      */           } 
/*  967 */           ua.a((short)10248, "DIR", "type", str7);
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 405:
/*  974 */         style.a(G.a, (ad)t.r);
/*  975 */         c.g("DIV", style);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 406:
/*  980 */         style.a(G.a, (ad)t.r);
/*  981 */         a(style, (E)b.e, code);
/*  982 */         style.a(ah.a, (ad)L.p);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 407:
/*  987 */         style.a(G.a, (ad)t.r);
/*  988 */         style.a(ag.a, (ad)L.p);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 501:
/*  993 */         style.a(x.a, (ad)y.b);
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 502:
/* 1001 */         c.a("EMBED", style);
/* 1002 */         c.b("EMBED", style);
/* 1003 */         c.d("EMBED", style);
/* 1004 */         str6 = ce.H.getValue("src");
/* 1005 */         str14 = ce.H.getValue("type");
/* 1006 */         str19 = ce.H.getValue("alt");
/* 1007 */         a(style, str6, str14, str19);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 601:
/* 1012 */         style.a(G.a, (ad)t.r);
/* 1013 */         a(style, (E)b.e, code);
/* 1014 */         style.a(af.a, (ad)g);
/* 1015 */         style.a(ae.a, (ad)g);
/* 1016 */         style.a(ac.a, (ad)g);
/* 1017 */         style.a(ad.a, (ad)g);
/* 1018 */         c.g("FIELDSET", style);
/* 1019 */         a4 = ua.c((byte)1);
/* 1020 */         style.a(r.a, (ad)i.s);
/* 1021 */         style.a(s.a, (ad)a4);
/* 1022 */         style.a(n.a, (ad)i.s);
/* 1023 */         style.a(o.a, (ad)a4);
/* 1024 */         style.a((j)g.a, (ad)i.s);
/* 1025 */         style.a(h.a, (ad)a4);
/* 1026 */         style.a((j)k.a, (ad)i.s);
/* 1027 */         style.a(l.a, (ad)a4);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 602:
/* 1032 */         c.h("FONT", style);
/* 1033 */         c.i("FONT", style);
/* 1034 */         c.a(style);
/*      */         
/* 1036 */         str5 = ce.H.getValue("font-weight");
/* 1037 */         if (str5 != null) {
/*      */           try {
/* 1039 */             int fontWeight = Integer.parseInt(str5);
/* 1040 */             fontWeight = Math.max(100, fontWeight);
/* 1041 */             fontWeight = Math.min(900, fontWeight);
/* 1042 */             style.a(K.a, (ad)A.a(fontWeight));
/* 1043 */           } catch (NumberFormatException e) {
/* 1044 */             ua.a((short)10248, "FONT", "font-weight", str5);
/*      */           } 
/*      */         }
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 603:
/* 1053 */         style.a(G.a, (ad)t.r);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 604:
/* 1058 */         style.a(G.a, (ad)t.q);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 605:
/* 1063 */         style.a(G.a, (ad)t.q);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 701:
/* 1068 */         style.a(G.a, (ad)t.r);
/* 1069 */         style.a(K.a, (ad)A.p);
/* 1070 */         style.a(I.a, (ad)u.a(2.0D));
/* 1071 */         style.a(ag.a, (ad)L.p);
/* 1072 */         a(style, (E)u.a(0.67D), code);
/* 1073 */         c.g("H1", style);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 702:
/* 1078 */         style.a(G.a, (ad)t.r);
/* 1079 */         style.a(K.a, (ad)A.p);
/* 1080 */         style.a(I.a, (ad)u.a(1.5D));
/* 1081 */         style.a(ag.a, (ad)L.p);
/* 1082 */         a(style, (E)u.a(0.75D), code);
/* 1083 */         c.g("H2", style);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 703:
/* 1088 */         style.a(G.a, (ad)t.r);
/* 1089 */         style.a(K.a, (ad)A.p);
/* 1090 */         style.a(I.a, (ad)u.a(1.17D));
/* 1091 */         style.a(ag.a, (ad)L.p);
/* 1092 */         a(style, (E)u.a(0.83D), code);
/* 1093 */         c.g("H3", style);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 704:
/* 1098 */         style.a(G.a, (ad)t.r);
/* 1099 */         style.a(K.a, (ad)A.p);
/* 1100 */         style.a(ag.a, (ad)L.p);
/* 1101 */         a(style, (E)b.e, code);
/* 1102 */         c.g("H4", style);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 705:
/* 1107 */         style.a(G.a, (ad)t.r);
/* 1108 */         style.a(K.a, (ad)A.p);
/* 1109 */         style.a(ag.a, (ad)L.p);
/* 1110 */         style.a(I.a, (ad)u.a(0.83D));
/* 1111 */         a(style, (E)u.a(1.5D), code);
/* 1112 */         c.g("H5", style);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 706:
/* 1117 */         style.a(G.a, (ad)t.r);
/* 1118 */         style.a(K.a, (ad)A.p);
/* 1119 */         style.a(I.a, (ad)u.a(0.75D));
/* 1120 */         style.a(ag.a, (ad)L.p);
/* 1121 */         a(style, (E)u.a(1.67D), code);
/* 1122 */         c.g("H6", style);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 707:
/* 1127 */         style.a(G.a, (ad)t.q);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 708:
/* 1132 */         style.a(G.a, (ad)t.r);
/* 1133 */         u1 = u.a(0.5D);
/* 1134 */         a(style, (E)u1, code);
/*      */         
/* 1136 */         color = null;
/*      */         
/* 1138 */         str18 = ce.H.getValue("color");
/* 1139 */         if (str18 != null) {
/* 1140 */           color = c.b(str18);
/* 1141 */           if (color == null) {
/* 1142 */             ua.a((short)10248, "HR", "color", str18);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1147 */         size = null;
/*      */         
/* 1149 */         str21 = ce.H.getValue("size");
/* 1150 */         if (str21 != null) {
/* 1151 */           size = l.a(ua, true, str21);
/* 1152 */           if (size == null) {
/* 1153 */             ua.a((short)10248, "HR", "size", str21);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1158 */         width = null;
/*      */         
/* 1160 */         str24 = ce.H.getValue("width");
/* 1161 */         if (str24 != null) {
/*      */           try {
/* 1163 */             width = c.a(ua, str24);
/* 1164 */             if (width.d()) {
/* 1165 */               throw new NumberFormatException();
/*      */             }
/* 1167 */           } catch (Exception e) {
/* 1168 */             ua.a((short)10248, "HR", "width", str24);
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/* 1173 */         c4 = style.c();
/* 1174 */         if (ce.H.getValue("noshade") == null && color == null) {
/* 1175 */           a a = a.a(ua, 1.0D, (short)17);
/* 1176 */           style.a(r.a, (ad)i.t);
/* 1177 */           style.a(s.a, (ad)a);
/* 1178 */           style.a(n.a, (ad)i.t);
/* 1179 */           style.a(o.a, (ad)a);
/* 1180 */           style.a((j)g.a, (ad)i.t);
/* 1181 */           style.a(h.a, (ad)a);
/* 1182 */           style.a((j)k.a, (ad)i.t);
/* 1183 */           style.a(l.a, (ad)a);
/*      */           
/* 1185 */           if (c4 != null && ((jp.cssj.homare.impl.a.c.b.b.c(c4) == 1 && 
/* 1186 */             e.a(c.c(c4))) || 
/* 1187 */             jp.cssj.homare.impl.a.c.b.b.c(c4) == 3)) {
/*      */             
/* 1189 */             if (size != null) {
/* 1190 */               style.a(aw.a, (ad)size);
/*      */             }
/* 1192 */             if (width != null) {
/* 1193 */               style.a(L.a, (ad)width);
/*      */             }
/*      */           } else {
/*      */             
/* 1197 */             if (size != null) {
/* 1198 */               style.a(L.a, (ad)size);
/*      */             }
/* 1200 */             if (width != null) {
/* 1201 */               style.a(aw.a, (ad)width);
/*      */             }
/*      */           }
/*      */         
/* 1205 */         } else if (c4 != null && ((jp.cssj.homare.impl.a.c.b.b.c(c4) == 1 && 
/* 1206 */           e.a(c.c(c4))) || 
/* 1207 */           jp.cssj.homare.impl.a.c.b.b.c(c4) == 3)) {
/*      */           
/* 1209 */           style.a((j)k.a, (ad)i.n);
/* 1210 */           if (size != null) {
/* 1211 */             style.a(l.a, (ad)size);
/*      */           }
/* 1213 */           if (color != null) {
/* 1214 */             style.a(j.a, (ad)color);
/*      */           }
/* 1216 */           if (width != null) {
/* 1217 */             style.a(L.a, (ad)width);
/*      */           }
/*      */         } else {
/*      */           
/* 1221 */           style.a((j)g.a, (ad)i.n);
/* 1222 */           if (size != null) {
/* 1223 */             style.a(h.a, (ad)size);
/*      */           }
/* 1225 */           if (color != null) {
/* 1226 */             style.a(f.a, (ad)color);
/*      */           }
/* 1228 */           if (width != null) {
/* 1229 */             style.a(aw.a, (ad)width);
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/* 1234 */         str25 = ce.H.getValue("align");
/* 1235 */         if ("right".equalsIgnoreCase(str25)) {
/* 1236 */           style.a(T.a, (ad)d.a); break;
/* 1237 */         }  if ("left".equalsIgnoreCase(str25)) {
/* 1238 */           style.a(U.a, (ad)d.a); break;
/*      */         } 
/* 1240 */         style.a(T.a, (ad)d.a);
/* 1241 */         style.a(U.a, (ad)d.a);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 709:
/* 1247 */         style.a(G.a, (ad)t.r);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 801:
/* 1252 */         style.a(x.a, (ad)y.b);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 802:
/* 1257 */         style.a(G.a, (ad)t.t);
/* 1258 */         c.a("IFRAME", style);
/* 1259 */         c.b("IFRAME", style);
/* 1260 */         c.e("IFRAME", style);
/* 1261 */         c.c("IFRAME", style);
/* 1262 */         border = true;
/*      */         
/* 1264 */         str13 = ce.H.getValue("frameborder");
/* 1265 */         if (str13 != null) {
/* 1266 */           str13 = str13.trim().toLowerCase();
/*      */           try {
/* 1268 */             if (str13.equals("1") || str13.equals("yes")) {
/* 1269 */               border = true;
/* 1270 */             } else if (str13.equals("0") || str13.equals("no")) {
/* 1271 */               border = false;
/*      */             } 
/* 1273 */           } catch (Exception e) {
/* 1274 */             ua.a((short)10248, "IFRAME", "frameborder", str13);
/*      */           } 
/*      */         } 
/*      */         
/* 1278 */         if (border) {
/* 1279 */           a a = ua.c((byte)2);
/* 1280 */           style.a(r.a, (ad)i.t);
/* 1281 */           style.a(s.a, (ad)a);
/* 1282 */           style.a((j)k.a, (ad)i.t);
/* 1283 */           style.a(l.a, (ad)a);
/* 1284 */           style.a((j)g.a, (ad)i.t);
/* 1285 */           style.a(h.a, (ad)a);
/* 1286 */           style.a(n.a, (ad)i.t);
/* 1287 */           style.a(o.a, (ad)a);
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       case 804:
/* 1293 */         style.a(G.a, (ad)t.s);
/* 1294 */         c.a("IMG", style);
/* 1295 */         c.b("IMG", style);
/* 1296 */         c.e("IMG", style);
/* 1297 */         str4 = ce.H.getValue("src");
/* 1298 */         alt = ce.H.getValue("alt");
/* 1299 */         a(style, str4, null, alt);
/* 1300 */         c.d("IMG", style);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 805:
/* 1305 */         style.a(I.a, (ad)a.a(ua, ua.d((byte)4)));
/* 1306 */         b1 = c.a(ce.H.getValue("type"));
/* 1307 */         switch (b1) {
/*      */           case 7:
/*      */           case 8:
/*      */           case 9:
/* 1311 */             a(style, (ce.H.getValue("disabled") != null));
/* 1312 */             c.e("INPUT", style);
/*      */             break;
/*      */           
/*      */           case 10:
/* 1316 */             c.a("INPUT", style);
/* 1317 */             str12 = ce.H.getValue("src");
/* 1318 */             str17 = ce.H.getValue("alt");
/* 1319 */             a(style, str12, null, str17);
/* 1320 */             c.e("INPUT", style);
/* 1321 */             c.d("INPUT", style);
/*      */             break;
/*      */           
/*      */           case 6:
/* 1325 */             style.a(G.a, (ad)t.q);
/*      */             break;
/*      */           
/*      */           case 3:
/* 1329 */             e.a(style, (jp.cssj.sakae.c.b.b)new jp.cssj.homare.impl.a.b.b(
/* 1330 */                   (ce.H.getValue("checked") != null), (ce.H.getValue("disabled") != null)));
/* 1331 */             c.e("INPUT", style);
/*      */             break;
/*      */           
/*      */           case 4:
/* 1335 */             e.a(style, (jp.cssj.sakae.c.b.b)new f((ce.H.getValue("checked") != null), 
/* 1336 */                   (ce.H.getValue("disabled") != null)));
/* 1337 */             c.e("INPUT", style);
/*      */             break;
/*      */           
/*      */           case 1:
/*      */           case 2:
/* 1342 */             a(style, (ce.H.getValue("disabled") != null), ce.H.getValue("size"));
/* 1343 */             c.e("INPUT", style);
/*      */             break;
/*      */           
/*      */           case 5:
/*      */             break;
/*      */         } 
/* 1349 */         throw new IllegalStateException();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 806:
/* 1355 */         style.a(an.a, (ad)Y.a((byte)1));
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 901:
/* 1364 */         style.a((j)w.a, (ad)x.e);
/*      */         break;
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
/*      */       case 1003:
/* 1377 */         style.a(y.a, (ad)N.h);
/* 1378 */         style.a(V.a, (ad)h);
/* 1379 */         c1 = style;
/*      */         while (true) {
/* 1381 */           n n1 = jp.cssj.homare.impl.a.c.b.c(c1);
/* 1382 */           if (n1 != null) {
/* 1383 */             style.a(jp.cssj.homare.impl.a.c.b.a, (ad)n1);
/*      */             break;
/*      */           } 
/* 1386 */           c1 = c1.c();
/* 1387 */           if (c1 == null) {
/* 1388 */             style.a(jp.cssj.homare.impl.a.c.b.a, (ad)ua.h());
/*      */             break;
/*      */           } 
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 1004:
/* 1397 */         style.a(G.a, (ad)t.u);
/* 1398 */         str3 = ce.H.getValue("type");
/* 1399 */         if (str3 != null) {
/* 1400 */           G g1 = c.c(str3);
/* 1401 */           if (g1 != null) {
/* 1402 */             style.a(R.a, (ad)g1); break;
/*      */           } 
/* 1404 */           ua.a((short)10248, "LI", "type", str3);
/*      */         } 
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 1006:
/* 1411 */         style.a(G.a, (ad)t.r);
/* 1412 */         style.a((j)w.a, (ad)x.e);
/* 1413 */         style.a(au.a, (ad)ah.b);
/* 1414 */         style.a(am.a, (ad)X.l);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1005:
/* 1419 */         style.a(G.a, (ad)t.q);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1101:
/* 1424 */         style.a(G.a, (ad)t.s);
/*      */         
/* 1426 */         imageMaps = style.b().a().d();
/* 1427 */         mapName = ce.H.getValue("name");
/* 1428 */         if (mapName != null && !imageMaps.containsKey(mapName)) {
/* 1429 */           this.q = new g();
/* 1430 */           imageMaps.put(mapName, this.q); break;
/*      */         } 
/* 1432 */         this.q = null;
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 1102:
/* 1438 */         style.a(G.a, (ad)t.r);
/* 1439 */         c.j("MARQUEE", style);
/* 1440 */         c.a("MARQUEE", style);
/* 1441 */         c.b("MARQUEE", style);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1103:
/* 1446 */         style.a(G.a, (ad)t.r);
/* 1447 */         a(style, (E)b.e, code);
/* 1448 */         c.a(style, (E)a.a(ua, 40.0D, (short)17));
/* 1449 */         style.a(ah.a, (ad)L.p);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1104:
/* 1454 */         style.a(G.a, (ad)t.q);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1201:
/* 1459 */         style.a(G.a, (ad)t.q);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1202:
/* 1464 */         style.a(au.a, (ad)ah.c);
/*      */         break;
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
/*      */       case 1301:
/* 1478 */         c.a("OBJECT", style);
/* 1479 */         c.b("OBJECT", style);
/* 1480 */         c.e("OBJECT", style);
/* 1481 */         src = ce.H.getValue("data");
/* 1482 */         str11 = ce.H.getValue("type");
/* 1483 */         str17 = ce.H.getValue("alt");
/* 1484 */         a(style, src, str11, str17);
/* 1485 */         c.d("OBJECT", style);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 1302:
/* 1491 */         style.a(G.a, (ad)t.r);
/* 1492 */         a(style, (E)b.e, code);
/* 1493 */         c.a(style, (E)a.a(ua, 40.0D, (short)17));
/* 1494 */         type = ce.H.getValue("type");
/* 1495 */         if (type != null) {
/* 1496 */           G g1 = c.c(type);
/* 1497 */           if (g1 != null) {
/* 1498 */             style.a(R.a, (ad)g1);
/*      */           } else {
/* 1500 */             ua.a((short)10248, "OL", "type" + type);
/*      */           } 
/*      */         } else {
/* 1503 */           style.a(R.a, (ad)G.B);
/*      */         } 
/* 1505 */         style.a(ah.a, (ad)L.p);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1303:
/* 1510 */         style.a(G.a, (ad)t.r);
/* 1511 */         style.a(T.a, (ad)f);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1304:
/* 1516 */         style.a(G.a, (ad)t.r);
/* 1517 */         style.a(L.a, (ad)f);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1401:
/* 1522 */         style.a(G.a, (ad)t.r);
/* 1523 */         a(style, (E)b.e, code);
/* 1524 */         c.g("P", style);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1402:
/* 1529 */         style.a(G.a, (ad)t.q);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1403:
/* 1534 */         style.a(G.a, (ad)t.r);
/* 1535 */         style.a((j)w.a, (ad)x.e);
/* 1536 */         style.a(au.a, (ad)ah.b);
/* 1537 */         style.a(am.a, (ad)X.l);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1404:
/* 1542 */         style.a(G.a, (ad)t.r);
/* 1543 */         style.a((j)w.a, (ad)x.e);
/* 1544 */         wrap = ce.H.getValue("wrap");
/* 1545 */         if (wrap != null) {
/* 1546 */           style.a(au.a, (ad)ah.d);
/*      */         } else {
/* 1548 */           style.a(au.a, (ad)ah.b);
/*      */         } 
/* 1550 */         style.a(am.a, (ad)X.l);
/*      */         
/* 1552 */         str10 = ce.H.getValue("cols");
/* 1553 */         if (str10 != null) {
/*      */           try {
/* 1555 */             u u2 = u.a(d.a(str10));
/* 1556 */             style.a(aw.a, (ad)u2);
/* 1557 */           } catch (Exception e) {
/* 1558 */             ua.a((short)10248, "PRE", "cols", str10);
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/* 1563 */         str10 = ce.H.getValue("width");
/* 1564 */         if (str10 != null) {
/*      */           try {
/* 1566 */             O length = c.a(ua, str10);
/* 1567 */             if (length.d()) {
/* 1568 */               throw new NumberFormatException();
/*      */             }
/* 1570 */             style.a(aw.a, (ad)length);
/* 1571 */           } catch (Exception e) {
/* 1572 */             ua.a((short)10248, "PRE", "width", str10);
/*      */           } 
/*      */         }
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1604:
/* 1584 */         style.a(G.a, (ad)t.t);
/* 1585 */         style.a(h.a, (ad)h.f);
/* 1586 */         style.a(ao.a, (ad)a.a);
/* 1587 */         pStyle = style.c();
/* 1588 */         if (pStyle != null && e.a(c.c(pStyle))) {
/*      */           
/* 1590 */           style.a(O.a, (ad)j); break;
/*      */         } 
/* 1592 */         style.a(O.a, (ad)k);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 1601:
/* 1598 */         style.a(G.a, (ad)t.r);
/* 1599 */         style.a(h.a, (ad)h.g);
/* 1600 */         style.a(O.a, (ad)S.b);
/* 1601 */         style.a(am.a, (ad)X.n);
/* 1602 */         pStyle = style.c();
/* 1603 */         if (pStyle != null && ((jp.cssj.homare.impl.a.c.b.b.c(pStyle) == 1 && 
/* 1604 */           e.a(c.c(pStyle))) || 
/* 1605 */           jp.cssj.homare.impl.a.c.b.b.c(pStyle) == 3)) {
/*      */           
/* 1607 */           style.a(aw.a, (ad)a.a);
/*      */           break;
/*      */         } 
/* 1610 */         style.a(L.a, (ad)a.a);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 1603:
/* 1616 */         style.a(G.a, (ad)t.r);
/* 1617 */         style.a(h.a, (ad)h.h);
/* 1618 */         style.a(O.a, (ad)S.b);
/* 1619 */         style.a(am.a, (ad)X.n);
/* 1620 */         style.a(I.a, (ad)M.b);
/* 1621 */         pStyle = style.c();
/* 1622 */         if (pStyle != null && ((jp.cssj.homare.impl.a.c.b.b.c(pStyle) == 1 && 
/* 1623 */           e.a(c.c(pStyle))) || 
/* 1624 */           jp.cssj.homare.impl.a.c.b.b.c(pStyle) == 3)) {
/*      */           
/* 1626 */           style.a(U.a, (ad)b.i);
/* 1627 */           style.a(aw.a, (ad)a.a);
/*      */           break;
/*      */         } 
/* 1630 */         style.a(V.a, (ad)b.i);
/* 1631 */         style.a(L.a, (ad)a.a);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 1602:
/* 1637 */         style.a(G.a, (ad)t.q);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1701:
/* 1642 */         style.a(an.a, (ad)Y.a((byte)4));
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1702:
/* 1647 */         style.a((j)w.a, (ad)x.e);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1703:
/* 1652 */         style.a(G.a, (ad)t.q);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1704:
/* 1657 */         style.a(G.a, (ad)t.t);
/* 1658 */         style.a(y.a, (ad)N.g);
/* 1659 */         style.a(I.a, (ad)a.a(ua, ua.d((byte)4)));
/* 1660 */         style.a(L.a, (ad)f);
/*      */         
/* 1662 */         str2 = ce.H.getValue("size");
/* 1663 */         if (str2 != null) {
/*      */           try {
/* 1665 */             style.a(L.a, (ad)u.a(d.a(str2)));
/* 1666 */           } catch (NumberFormatException e) {
/* 1667 */             ua.a((short)10248, "SELECT", "size", str2);
/*      */           } 
/*      */         }
/*      */         
/* 1671 */         style.a(ab.a, (ad)J.b);
/* 1672 */         style.a(O.a, (ad)S.a(1.0D));
/*      */         
/* 1674 */         if (ce.H.getValue("disabled") != null) {
/* 1675 */           style.a(u.a, (ad)c.ad);
/* 1676 */           style.a(jp.cssj.homare.impl.a.c.b.a, (ad)c.aD);
/*      */         } else {
/* 1678 */           style.a(jp.cssj.homare.impl.a.c.b.a, (ad)c.o);
/*      */         } 
/* 1680 */         a3 = ua.c((byte)1);
/* 1681 */         style.a(r.a, (ad)i.t);
/* 1682 */         style.a(s.a, (ad)a3);
/* 1683 */         style.a((j)k.a, (ad)i.t);
/* 1684 */         style.a(l.a, (ad)a3);
/* 1685 */         style.a((j)g.a, (ad)i.t);
/* 1686 */         style.a(h.a, (ad)a3);
/* 1687 */         style.a(n.a, (ad)i.t);
/* 1688 */         style.a(o.a, (ad)a3);
/* 1689 */         style.a(af.a, (ad)a3, (byte)1);
/* 1690 */         style.a(ae.a, (ad)a.a(ua, L.d(style).b()), (byte)1);
/*      */         
/* 1692 */         style.a(ac.a, (ad)a3, (byte)1);
/* 1693 */         style.a(ad.a, (ad)a3, (byte)1);
/* 1694 */         style.a(au.a, (ad)ah.c);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1705:
/* 1699 */         style.a(G.a, (ad)t.q);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1706:
/* 1704 */         style.a(I.a, (ad)u.a(0.83D));
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1709:
/* 1713 */         style.a(an.a, (ad)Y.a((byte)4));
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1710:
/* 1718 */         style.a(K.a, (ad)A.p);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1711:
/* 1723 */         style.a(G.a, (ad)t.q);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1712:
/* 1728 */         style.a(I.a, (ad)u.a(0.83D));
/* 1729 */         style.a(as.a, (ad)af.c);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1713:
/* 1734 */         style.a(I.a, (ad)u.a(0.83D));
/* 1735 */         style.a(as.a, (ad)af.d);
/*      */         break;
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
/*      */       case 1801:
/* 1748 */         style.a(G.a, (ad)t.w);
/*      */         
/* 1750 */         cellspacing = null;
/*      */         
/* 1752 */         str10 = ce.H.getValue("cellspacing");
/* 1753 */         if (str10 != null) {
/* 1754 */           e1 = l.a(ua, true, str10);
/* 1755 */           if (e1 == null) {
/* 1756 */             ua.a((short)10248, "TABLE", "cellspacing" + str10);
/*      */           }
/*      */         } 
/*      */         
/* 1760 */         if (e1 == null) {
/* 1761 */           a2 = a.a(ua, 2.0D, (short)17);
/*      */         }
/* 1763 */         style.a(p.a, (ad)a2);
/* 1764 */         style.a(p.b, (ad)a2);
/* 1765 */         a5 = a.a;
/*      */         
/* 1767 */         str16 = ce.H.getValue("border");
/* 1768 */         if (str16 != null) {
/* 1769 */           if (str16.length() == 0) {
/* 1770 */             a5 = ua.c((byte)1);
/*      */           } else {
/* 1772 */             E e = l.a(ua, true, str16);
/* 1773 */             if (e == null) {
/* 1774 */               ua.a((short)10248, "TABLE", "border", str16);
/* 1775 */               a5 = ua.c((byte)1);
/*      */             } 
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/* 1781 */         c.a("TABLE", style);
/* 1782 */         c.b("TABLE", style);
/* 1783 */         c.j("TABLE", style);
/* 1784 */         c.k("TABLE", style);
/* 1785 */         c.f("TABLE", style);
/*      */         
/* 1787 */         str16 = ce.H.getValue("frame");
/* 1788 */         if (str16 != null) {
/* 1789 */           if (str16.equalsIgnoreCase("void")) {
/* 1790 */             style.a(r.a, (ad)i.k);
/* 1791 */             style.a(n.a, (ad)i.k);
/* 1792 */             style.a((j)g.a, (ad)i.k);
/* 1793 */             style.a((j)k.a, (ad)i.k);
/* 1794 */           } else if (str16.equalsIgnoreCase("above")) {
/* 1795 */             style.a(r.a, (ad)i.r);
/* 1796 */             style.a(n.a, (ad)i.k);
/* 1797 */             style.a((j)g.a, (ad)i.k);
/* 1798 */             style.a((j)k.a, (ad)i.k);
/* 1799 */           } else if (str16.equalsIgnoreCase("below")) {
/* 1800 */             style.a(r.a, (ad)i.k);
/* 1801 */             style.a(n.a, (ad)i.k);
/* 1802 */             style.a((j)g.a, (ad)i.r);
/* 1803 */             style.a((j)k.a, (ad)i.k);
/* 1804 */           } else if (str16.equalsIgnoreCase("hsides")) {
/* 1805 */             style.a(r.a, (ad)i.k);
/* 1806 */             style.a(n.a, (ad)i.r);
/* 1807 */             style.a((j)g.a, (ad)i.k);
/* 1808 */             style.a((j)k.a, (ad)i.r);
/* 1809 */           } else if (str16.equalsIgnoreCase("vsides")) {
/* 1810 */             style.a(r.a, (ad)i.r);
/* 1811 */             style.a(n.a, (ad)i.k);
/* 1812 */             style.a((j)g.a, (ad)i.r);
/* 1813 */             style.a((j)k.a, (ad)i.k);
/* 1814 */           } else if (str16.equalsIgnoreCase("lhs")) {
/* 1815 */             style.a(r.a, (ad)i.k);
/* 1816 */             style.a(n.a, (ad)i.k);
/* 1817 */             style.a((j)g.a, (ad)i.k);
/* 1818 */             style.a((j)k.a, (ad)i.r);
/* 1819 */           } else if (str16.equalsIgnoreCase("rhs")) {
/* 1820 */             style.a(r.a, (ad)i.k);
/* 1821 */             style.a(n.a, (ad)i.r);
/* 1822 */             style.a((j)g.a, (ad)i.k);
/* 1823 */             style.a((j)k.a, (ad)i.k);
/*      */           } 
/*      */         }
/*      */         
/* 1827 */         style.a(am.a, (ad)X.h);
/* 1828 */         if (style.b().c().b() >= 2) {
/* 1829 */           style.a(I.a, (ad)a.a(ua, ua.d((byte)4)));
/*      */         }
/* 1831 */         style.a(ao.a, (ad)a.a);
/* 1832 */         cellpadding = null;
/*      */         
/* 1834 */         str20 = ce.H.getValue("cellpadding");
/* 1835 */         if (str20 != null) {
/* 1836 */           cellpadding = l.a(ua, true, str20);
/* 1837 */           if (cellpadding == null) {
/* 1838 */             ua.a((short)10248, "TABLE", "cellpadding", str20);
/*      */           }
/*      */         } 
/*      */         
/* 1842 */         if (cellpadding == null) {
/* 1843 */           a6 = a.a(ua, 1.0D, (short)17);
/*      */         }
/* 1845 */         c.a(style, (E)a6);
/* 1846 */         borderColor = null;
/*      */         
/* 1848 */         str23 = ce.H.getValue("bordercolor");
/* 1849 */         if (str23 != null) {
/* 1850 */           borderColor = c.b(str23);
/* 1851 */           if (borderColor == null) {
/* 1852 */             ua.a((short)10248, "TABLE", "bordercolor", str23);
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1858 */         if (borderColor == null) {
/* 1859 */           borderStyle = i.r;
/*      */         } else {
/* 1861 */           borderStyle = i.n;
/* 1862 */           style.a(q.a, (ad)borderColor);
/* 1863 */           style.a(m.a, (ad)borderColor);
/* 1864 */           style.a(f.a, (ad)borderColor);
/* 1865 */           style.a(j.a, (ad)borderColor);
/*      */         } 
/* 1867 */         d.a(style, new jp.cssj.homare.css.f.c.b((E)a5, borderColor));
/*      */         
/* 1869 */         style.a(r.a, (ad)borderStyle);
/* 1870 */         style.a(s.a, (ad)a5);
/* 1871 */         style.a(n.a, (ad)borderStyle);
/* 1872 */         style.a(o.a, (ad)a5);
/* 1873 */         style.a((j)g.a, (ad)borderStyle);
/* 1874 */         style.a(h.a, (ad)a5);
/* 1875 */         style.a((j)k.a, (ad)borderStyle);
/* 1876 */         style.a(l.a, (ad)a5);
/*      */ 
/*      */ 
/*      */         
/* 1880 */         str22 = ce.H.getValue("rules");
/* 1881 */         if (str22 != null && (
/* 1882 */           str22.equalsIgnoreCase("all") || str22.equalsIgnoreCase("groups") || str22.equalsIgnoreCase("rows") || str22
/* 1883 */           .equalsIgnoreCase("cols") || str22.equalsIgnoreCase("none"))) {
/* 1884 */           style.a(i.a, (ad)h.b);
/*      */         }
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1802:
/* 1892 */         style.a(G.a, (ad)t.y);
/* 1893 */         b("TBODY", style);
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1803:
/* 1900 */         style.a(jp.cssj.homare.impl.a.c.c.b.a, (ad)a.a);
/* 1901 */         a("TD", style);
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 1806:
/* 1908 */         style.a(K.a, (ad)A.o);
/* 1909 */         style.a(am.a, (ad)X.j);
/* 1910 */         style.a(jp.cssj.homare.impl.a.c.c.b.a, (ad)a.a);
/* 1911 */         a("TH", style);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1805:
/* 1916 */         style.a(G.a, (ad)t.C);
/* 1917 */         b("TFOOT", style);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1807:
/* 1922 */         style.a(G.a, (ad)t.B);
/* 1923 */         b("THEAD", style);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 1809:
/* 1929 */         style.a(G.a, (ad)t.D);
/* 1930 */         style.a(jp.cssj.homare.impl.a.c.c.b.a, (ad)a.a);
/* 1931 */         c.g("TR", style);
/* 1932 */         c.k("TR", style);
/* 1933 */         c.j("TR", style);
/*      */         
/* 1935 */         str1 = ce.H.getValue("height");
/* 1936 */         if (str1 != null) {
/*      */           try {
/* 1938 */             O o = c.a(ua, str1);
/* 1939 */             style.a(L.a, (ad)o);
/* 1940 */           } catch (Exception e) {
/* 1941 */             ua.a((short)10248, "TR", "height", str1);
/*      */           } 
/*      */         }
/*      */         
/* 1945 */         parent = style.c();
/* 1946 */         for (; parent != null; parent = parent.c()) {
/* 1947 */           a parentCe = parent.a();
/* 1948 */           if (a.a(parentCe) == 1801) {
/* 1949 */             if ("rows".equalsIgnoreCase(parentCe.H.getValue("rules"))) {
/* 1950 */               style.a(r.a, (ad)i.n);
/* 1951 */               style.a(s.a, (ad)ua.c((byte)1));
/* 1952 */               style.a((j)g.a, (ad)i.n);
/* 1953 */               style.a(h.a, (ad)ua.c((byte)1));
/*      */             } 
/*      */             break;
/*      */           } 
/*      */         } 
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1810:
/* 1962 */         style.a((j)w.a, (ad)x.e);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1804:
/* 1967 */         style.a(G.a, (ad)t.t);
/* 1968 */         style.a(aw.a, (ad)c);
/*      */         
/* 1970 */         str = ce.H.getValue("cols");
/* 1971 */         if (str != null) {
/*      */           try {
/* 1973 */             style.a(aw.a, (ad)w.a(d.a(str)));
/* 1974 */           } catch (NumberFormatException e) {
/* 1975 */             ua.a((short)10248, "TEXTAREA", "cols", str);
/*      */           } 
/*      */         }
/*      */         
/* 1979 */         style.a(L.a, (ad)d);
/*      */         
/* 1981 */         str = ce.H.getValue("rows");
/* 1982 */         if (str != null) {
/*      */           try {
/* 1984 */             style.a(L.a, (ad)u.a(d.a(str)));
/* 1985 */           } catch (NumberFormatException e) {
/* 1986 */             ua.a((short)10248, "TEXTAREA", "rows", str);
/*      */           } 
/*      */         }
/*      */         
/* 1990 */         if (ce.H.getValue("disabled") != null) {
/* 1991 */           style.a(u.a, (ad)c.f);
/*      */         }
/* 1993 */         style.a(jp.cssj.homare.impl.a.c.b.a, (ad)c.o);
/* 1994 */         a1 = ua.c((byte)1);
/* 1995 */         style.a(r.a, (ad)i.t);
/* 1996 */         style.a(s.a, (ad)a1);
/* 1997 */         style.a((j)k.a, (ad)i.t);
/* 1998 */         style.a(l.a, (ad)a1);
/* 1999 */         style.a((j)g.a, (ad)i.t);
/* 2000 */         style.a(h.a, (ad)a1);
/* 2001 */         style.a(n.a, (ad)i.t);
/* 2002 */         style.a(o.a, (ad)a1);
/* 2003 */         style.a(af.a, (ad)a1);
/* 2004 */         style.a(ae.a, (ad)a1);
/* 2005 */         style.a(ac.a, (ad)a1);
/* 2006 */         style.a(ad.a, (ad)a1);
/* 2007 */         if (ce.H.getValue("wrap") != null) {
/* 2008 */           style.a(au.a, (ad)ah.d); break;
/*      */         } 
/* 2010 */         style.a(au.a, (ad)ah.c);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 1808:
/* 2016 */         style.a(G.a, (ad)t.q);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1901:
/* 2021 */         style.a(an.a, (ad)Y.a((byte)1));
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1902:
/* 2026 */         depth = 0;
/* 2027 */         for (c3 = style.c(); c3 != null; c3 = c3.c()) {
/* 2028 */           if (a.a(c3.a()) == 1902) {
/* 2029 */             depth++;
/*      */           }
/*      */         } 
/*      */         
/* 2033 */         style.a(G.a, (ad)t.r);
/* 2034 */         if (depth == 0) {
/* 2035 */           a(style, (E)b.e, code);
/*      */         }
/* 2037 */         c.a(style, (E)a.a(ua, 40.0D, (short)17));
/* 2038 */         str9 = ce.H.getValue("type");
/* 2039 */         if (str9 != null) {
/* 2040 */           G g1 = c.c(str9);
/* 2041 */           if (g1 != null) {
/* 2042 */             style.a(R.a, (ad)g1);
/*      */           } else {
/* 2044 */             ua.a((short)10248, "UL", "type", str9);
/*      */           } 
/*      */         } else {
/*      */           G listStyle;
/* 2048 */           switch (depth) {
/*      */             case 0:
/* 2050 */               listStyle = G.y;
/*      */               break;
/*      */             case 1:
/* 2053 */               listStyle = G.z;
/*      */               break;
/*      */             default:
/* 2056 */               listStyle = G.A;
/*      */               break;
/*      */           } 
/* 2059 */           style.a(R.a, (ad)listStyle);
/*      */         } 
/* 2061 */         style.a(ah.a, (ad)L.p);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2001:
/* 2066 */         style.a(x.a, (ad)y.b);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2002:
/* 2071 */         c.a("VIDEO", style);
/* 2072 */         style.a(G.a, (ad)t.t);
/* 2073 */         poster = ce.H.getValue("poster");
/* 2074 */         if (poster != null) {
/* 2075 */           a(style, poster, null, "");
/*      */         }
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 2201:
/* 2085 */         style.a(G.a, (ad)t.r);
/* 2086 */         style.a((j)w.a, (ad)x.e);
/* 2087 */         style.a(au.a, (ad)ah.b);
/* 2088 */         style.a(am.a, (ad)X.l);
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2095 */     String hidden = ce.H.getValue("hidden");
/* 2096 */     if (hidden != null)
/* 2097 */       style.a(G.a, (ad)t.q);  }
/*      */ 
/*      */   
/*      */   private static final Logger b;
/*      */   private static final w c;
/*      */   private static final u d;
/*      */   private static final u e;
/*      */   private static final u f;
/*      */   private static final u g;
/*      */   private static final u h;
/*      */   private static final u i;
/*      */   private static final S j;
/*      */   private static final S k;
/*      */   private static final ae l;
/*      */   private static final ae m;
/*      */   private static final ae n;
/*      */   private static final ae o;
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */