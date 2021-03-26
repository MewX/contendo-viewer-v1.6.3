/*      */ package jp.cssj.homare.b.f;
/*      */ 
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.Arc2D;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.RoundRectangle2D;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import jp.cssj.homare.b.a.c.A;
/*      */ import jp.cssj.homare.b.a.c.j;
/*      */ import jp.cssj.homare.b.e.c;
/*      */ import jp.cssj.sakae.c.b;
/*      */ import jp.cssj.sakae.c.c;
/*      */ import jp.cssj.sakae.c.c.b;
/*      */ import jp.cssj.sakae.c.c.h;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class a
/*      */ {
/*   29 */   public static a a = new a();
/*      */ 
/*      */   
/*   32 */   public static final double[] b = new double[0];
/*      */ 
/*      */   
/*   35 */   public static final double[] c = new double[] { 1.0D, 2.0D };
/*      */ 
/*      */   
/*   38 */   public static final double[] d = new double[] { 3.0D, 3.0D };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void a(b gc, double w, double[] pattern) {
/*   45 */     gc.a(w);
/*   46 */     if (pattern.length == 0 || w == 1.0D) {
/*   47 */       gc.a(pattern);
/*      */       return;
/*      */     } 
/*   50 */     double[] p = new double[pattern.length];
/*   51 */     for (int i = 0; i < p.length; i++) {
/*   52 */       p[i] = pattern[i] * w;
/*      */     }
/*   54 */     gc.a(p);
/*      */   }
/*      */   
/*      */   protected b a(b color) {
/*   58 */     float r = color.d();
/*   59 */     float g = color.e();
/*   60 */     float f1 = color.f();
/*   61 */     return (b)h.b(r + (1.0F - r) / 2.0F, g + (1.0F - g) / 2.0F, f1 + (1.0F - f1) / 2.0F);
/*      */   }
/*      */   
/*      */   protected b b(b color) {
/*   65 */     float r = color.d();
/*   66 */     float g = color.e();
/*   67 */     float f1 = color.f();
/*   68 */     return (b)h.b(r / 2.0F, g / 2.0F, f1 / 2.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(b gc, A border, double tx, double ty, double width, double height) throws c {
/*   73 */     double w = (border.c()).m / 3.0D;
/*   74 */     double y = height - (border.c()).m / 2.0D;
/*   75 */     gc.d();
/*   76 */     gc.a((border.c()).o);
/*   77 */     a(gc, w, b);
/*   78 */     gc.c(new Line2D.Double((border.d()).m / 6.0D + tx, y + w + ty, width - 
/*   79 */           (border.b()).m / 6.0D + tx, y + w + ty));
/*   80 */     gc.c(new Line2D.Double((border.d()).m / 6.0D + tx, y - w + ty, width - 
/*   81 */           (border.b()).m / 6.0D + tx, y - w + ty));
/*   82 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void b(b gc, A border, double tx, double ty, double width, double height) throws c {
/*   87 */     double w = (border.b()).m / 3.0D;
/*   88 */     double x = width - (border.b()).m / 2.0D;
/*   89 */     gc.d();
/*   90 */     gc.a((border.b()).o);
/*   91 */     a(gc, w, b);
/*   92 */     gc.c(new Line2D.Double(x + w + tx, (border.a()).m / 6.0D + ty, x + w + tx, height - 
/*   93 */           (border.c()).m / 6.0D + ty));
/*   94 */     gc.c(new Line2D.Double(x - w + tx, (border.a()).m / 6.0D + ty, x - w + tx, height - 
/*   95 */           (border.c()).m / 6.0D + ty));
/*   96 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void c(b gc, A border, double tx, double ty, double width, double height) throws c {
/*  101 */     double w = (border.a()).m / 3.0D;
/*  102 */     double y = (border.a()).m / 2.0D;
/*  103 */     gc.d();
/*  104 */     gc.a((border.a()).o);
/*  105 */     a(gc, w, b);
/*  106 */     gc.c(new Line2D.Double((border.d()).m / 6.0D + tx, y - w + ty, width - 
/*  107 */           (border.b()).m / 6.0D + tx, y - w + ty));
/*  108 */     gc.c(new Line2D.Double((border.d()).m / 6.0D + tx, y + w + ty, width - 
/*  109 */           (border.b()).m / 6.0D + tx, y + w + ty));
/*  110 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void d(b gc, A border, double tx, double ty, double width, double height) throws c {
/*  115 */     double w = (border.d()).m / 3.0D;
/*  116 */     double x = (border.d()).m / 2.0D;
/*  117 */     gc.d();
/*  118 */     gc.a((border.d()).o);
/*  119 */     a(gc, w, b);
/*  120 */     gc.c(new Line2D.Double(x - w + tx, (border.a()).m / 6.0D + ty, x - w + tx, height - 
/*  121 */           (border.c()).m / 6.0D + ty));
/*  122 */     gc.c(new Line2D.Double(x + w + tx, (border.a()).m / 6.0D + ty, x + w + tx, height - 
/*  123 */           (border.c()).m / 6.0D + ty));
/*  124 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void e(b gc, A border, double tx, double ty, double width, double height) throws c {
/*  129 */     gc.d();
/*  130 */     gc.a((border.c()).o);
/*  131 */     double y = height - (border.c()).m / 2.0D;
/*  132 */     gc.c(new Line2D.Double(tx, y + ty, width + tx, y + ty));
/*  133 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void f(b gc, A border, double tx, double ty, double width, double height) throws c {
/*  138 */     gc.d();
/*  139 */     gc.a((border.b()).o);
/*  140 */     double x = width - (border.b()).m / 2.0D;
/*  141 */     gc.c(new Line2D.Double(x + tx, ty, x + tx, height + ty));
/*  142 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void g(b gc, A border, double tx, double ty, double width, double height) throws c {
/*  147 */     gc.d();
/*  148 */     gc.a((border.a()).o);
/*  149 */     double y = (border.a()).m / 2.0D;
/*  150 */     gc.c(new Line2D.Double(tx, y + ty, width + tx, y + ty));
/*  151 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void h(b gc, A border, double tx, double ty, double width, double height) throws c {
/*  156 */     gc.d();
/*  157 */     gc.a((border.d()).o);
/*  158 */     double x = (border.d()).m / 2.0D;
/*  159 */     gc.c(new Line2D.Double(x + tx, ty, x + tx, height + ty));
/*  160 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(b gc, A border, boolean ridge, double tx, double ty, double width, double height) throws c {
/*  165 */     b color = (border.b()).o;
/*  166 */     double w = (border.b()).m / 2.0D;
/*  167 */     double x = width - (border.b()).m / 2.0D;
/*  168 */     gc.d();
/*  169 */     a(gc, w, b);
/*  170 */     gc.a(ridge ? b(color) : a(color));
/*  171 */     gc.c(new Line2D.Double(x + w / 2.0D + tx, (border.a()).m / 4.0D + ty, x + w / 2.0D + tx, height - 
/*  172 */           (border.c()).m / 4.0D + ty));
/*  173 */     gc.a(ridge ? a(color) : b(color));
/*  174 */     gc.c(new Line2D.Double(x - w / 2.0D + tx, (border.a()).m / 4.0D + ty, x - w / 2.0D + tx, height - 
/*  175 */           (border.c()).m / 4.0D + ty));
/*  176 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void b(b gc, A border, boolean outset, double tx, double ty, double width, double height) throws c {
/*  181 */     b color = (border.d()).o;
/*  182 */     gc.d();
/*  183 */     a(gc, (border.d()).m, b);
/*  184 */     double x = (border.d()).m / 2.0D;
/*  185 */     gc.a(outset ? a(color) : b(color));
/*  186 */     gc.c(new Line2D.Double(x + tx, (border.a()).m / 2.0D + ty, x + tx, height - 
/*  187 */           (border.c()).m / 2.0D + ty));
/*  188 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void c(b gc, A border, boolean ridge, double tx, double ty, double width, double height) throws c {
/*  193 */     b color = (border.c()).o;
/*  194 */     double w = (border.c()).m / 2.0D;
/*  195 */     double y = height - (border.c()).m / 2.0D;
/*  196 */     gc.d();
/*  197 */     a(gc, w, b);
/*  198 */     gc.a(ridge ? b(color) : a(color));
/*  199 */     gc.c(new Line2D.Double((border.d()).m / 4.0D + tx, y + w / 2.0D + ty, width - 
/*  200 */           (border.b()).m / 4.0D + tx, y + w / 2.0D + ty));
/*  201 */     gc.a(ridge ? a(color) : b(color));
/*  202 */     gc.c(new Line2D.Double((border.d()).m / 4.0D + tx, y - w / 2.0D + ty, width - 
/*  203 */           (border.b()).m / 4.0D + tx, y - w / 2.0D + ty));
/*  204 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void d(b gc, A border, boolean ridge, double tx, double ty, double width, double height) throws c {
/*  209 */     b color = (border.d()).o;
/*  210 */     double w = (border.d()).m / 2.0D;
/*  211 */     double x = (border.d()).m / 2.0D;
/*  212 */     gc.d();
/*  213 */     a(gc, w, b);
/*  214 */     gc.a(ridge ? a(color) : b(color));
/*  215 */     gc.c(new Line2D.Double(x - w / 2.0D + tx, (border.a()).m / 4.0D + ty, x - w / 2.0D + tx, height - 
/*  216 */           (border.c()).m / 4.0D + ty));
/*  217 */     gc.a(ridge ? b(color) : a(color));
/*  218 */     gc.c(new Line2D.Double(x + w / 2.0D + tx, (border.a()).m / 4.0D + ty, x + w / 2.0D + tx, height - 
/*  219 */           (border.c()).m / 4.0D + ty));
/*  220 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void e(b gc, A border, boolean outset, double tx, double ty, double width, double height) throws c {
/*  225 */     b color = (border.a()).o;
/*  226 */     gc.d();
/*  227 */     a(gc, (border.a()).m, b);
/*  228 */     gc.a(outset ? a(color) : b(color));
/*  229 */     double y = (border.a()).m / 2.0D;
/*  230 */     gc.c(new Line2D.Double((border.d()).m / 2.0D + tx, y + ty, width - (border.b()).m / 2.0D + tx, y + ty));
/*      */     
/*  232 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void f(b gc, A border, boolean outset, double tx, double ty, double width, double height) throws c {
/*  237 */     b color = (border.b()).o;
/*  238 */     gc.d();
/*  239 */     a(gc, (border.b()).m, b);
/*  240 */     gc.a(outset ? b(color) : a(color));
/*  241 */     double x = width - (border.b()).m / 2.0D;
/*  242 */     gc.c(new Line2D.Double(x + tx, (border.a()).m / 2.0D + ty, x + tx, height - 
/*  243 */           (border.c()).m / 2.0D + ty));
/*  244 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void g(b gc, A border, boolean ridge, double tx, double ty, double width, double height) throws c {
/*  249 */     b color = (border.a()).o;
/*  250 */     double w = (border.a()).m / 2.0D;
/*  251 */     double y = (border.a()).m / 2.0D;
/*  252 */     gc.d();
/*  253 */     a(gc, w, b);
/*  254 */     gc.a(ridge ? a(color) : b(color));
/*  255 */     gc.c(new Line2D.Double((border.d()).m / 4.0D + tx, y - w / 2.0D + ty, width - 
/*  256 */           (border.b()).m / 4.0D + tx, y - w / 2.0D + ty));
/*  257 */     gc.a(ridge ? b(color) : a(color));
/*  258 */     gc.c(new Line2D.Double((border.d()).m / 4.0D + tx, y + w / 2.0D + ty, width - 
/*  259 */           (border.b()).m / 4.0D + tx, y + w / 2.0D + ty));
/*  260 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void h(b gc, A border, boolean outset, double tx, double ty, double width, double height) throws c {
/*  265 */     b color = (border.c()).o;
/*  266 */     gc.d();
/*  267 */     a(gc, (border.c()).m, b);
/*  268 */     gc.a(outset ? b(color) : a(color));
/*  269 */     double y = height - (border.c()).m / 2.0D;
/*  270 */     gc.c(new Line2D.Double((border.d()).m / 2.0D + tx, y + ty, width - (border.b()).m / 2.0D + tx, y + ty));
/*      */     
/*  272 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(b gc, j border, A.a radius, double x, double y, double width, double height) throws c {
/*  277 */     short style = border.n;
/*  278 */     double w = border.m;
/*  279 */     b color = border.o;
/*  280 */     gc.d();
/*  281 */     switch (style) {
/*      */       case 3:
/*  283 */         a(gc, w, b);
/*      */         break;
/*      */       
/*      */       case 4:
/*  287 */         a(gc, w, d);
/*      */         break;
/*      */       
/*      */       case 5:
/*  291 */         a(gc, w, c);
/*      */         break;
/*      */       
/*      */       case 2:
/*  295 */         a(gc, w / 3.0D, b);
/*      */         break;
/*      */       default:
/*  298 */         throw new IllegalStateException();
/*      */     } 
/*  300 */     gc.a(color);
/*  301 */     if (style == 2) {
/*      */       Shape shape;
/*      */       
/*  304 */       if (radius.b == 0.0D && radius.c == 0.0D) {
/*  305 */         shape = new Rectangle2D.Double(w / 6.0D + x, w / 6.0D + y, width - w / 3.0D, height - w / 3.0D);
/*      */       } else {
/*  307 */         shape = new RoundRectangle2D.Double(w / 6.0D + x, w / 6.0D + y, width - w / 3.0D, height - w / 3.0D, radius.b * 2.0D - w / 6.0D, radius.c * 2.0D - w / 6.0D);
/*      */       } 
/*      */       
/*  310 */       gc.c(shape);
/*      */ 
/*      */ 
/*      */       
/*  314 */       if (radius.b == 0.0D && radius.c == 0.0D) {
/*  315 */         shape = new Rectangle2D.Double(w * 5.0D / 6.0D + x, w * 5.0D / 6.0D + y, width - w * 5.0D / 3.0D, height - w * 5.0D / 3.0D);
/*      */       } else {
/*      */         
/*  318 */         shape = new RoundRectangle2D.Double(w * 5.0D / 6.0D + x, w * 5.0D / 6.0D + y, width - w * 5.0D / 3.0D, height - w * 5.0D / 3.0D, radius.b * 2.0D - w * 5.0D / 6.0D, radius.c * 2.0D - w * 5.0D / 6.0D);
/*      */       } 
/*      */       
/*  321 */       gc.c(shape);
/*      */     } else {
/*      */       Shape shape;
/*      */       
/*  325 */       if (radius.b == 0.0D && radius.c == 0.0D) {
/*  326 */         shape = new Rectangle2D.Double(w / 2.0D + x, w / 2.0D + y, width - w, height - w);
/*      */       } else {
/*  328 */         shape = new RoundRectangle2D.Double(w / 2.0D + x, w / 2.0D + y, width - w, height - w, radius.b * 2.0D - w / 2.0D, radius.c * 2.0D - w / 2.0D);
/*      */       } 
/*      */       
/*  331 */       gc.c(shape);
/*      */     } 
/*  333 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(b gc, j border, double w1, double w2, double x, double y, double length) throws c {
/*  338 */     length -= w1 + w2;
/*  339 */     y += border.m;
/*  340 */     x += w1;
/*  341 */     GeneralPath path = new GeneralPath(0, 4);
/*  342 */     path.moveTo((float)x, (float)y);
/*  343 */     path.lineTo((float)(x + length), (float)y);
/*  344 */     path.lineTo((float)(x + length + w2), (float)(y - border.m));
/*  345 */     path.lineTo((float)(x - w1), (float)(y - border.m));
/*  346 */     gc.a(path);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void b(b gc, j border, double w1, double w2, double x, double y, double length) throws c {
/*  351 */     length -= w1 + w2;
/*  352 */     y -= border.m;
/*  353 */     x += w1;
/*  354 */     GeneralPath path = new GeneralPath(0, 4);
/*  355 */     path.moveTo((float)x, (float)y);
/*  356 */     path.lineTo((float)(x + length), (float)y);
/*  357 */     path.lineTo((float)(x + length + w2), (float)(y + border.m));
/*  358 */     path.lineTo((float)(x - w1), (float)(y + border.m));
/*  359 */     gc.a(path);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void c(b gc, j border, double w1, double w2, double x, double y, double length) throws c {
/*  364 */     length -= w1 + w2;
/*  365 */     x += border.m;
/*  366 */     y += w1;
/*  367 */     GeneralPath path = new GeneralPath(0, 4);
/*  368 */     path.moveTo((float)x, (float)y);
/*  369 */     path.lineTo((float)x, (float)(y + length));
/*  370 */     path.lineTo((float)(x - border.m), (float)(y + length + w2));
/*  371 */     path.lineTo((float)(x - border.m), (float)(y - w1));
/*  372 */     gc.a(path);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void d(b gc, j border, double w1, double w2, double x, double y, double length) throws c {
/*  377 */     length -= w1 + w2;
/*  378 */     x -= border.m;
/*  379 */     y += w1;
/*  380 */     GeneralPath path = new GeneralPath(0, 4);
/*  381 */     path.moveTo((float)x, (float)y);
/*  382 */     path.lineTo((float)x, (float)(y + length));
/*  383 */     path.lineTo((float)(x + border.m), (float)(y + length + w2));
/*  384 */     path.lineTo((float)(x + border.m), (float)(y - w1));
/*  385 */     gc.a(path);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(b gc, j border, double x, double y, double length) throws c {
/*  390 */     gc.d();
/*  391 */     gc.a(border.o);
/*  392 */     gc.c(new Line2D.Double(x, y, x + length, y));
/*  393 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void b(b gc, j border, double x, double y, double length) throws c {
/*  398 */     gc.d();
/*  399 */     gc.a(border.o);
/*  400 */     gc.c(new Line2D.Double(x, y, x, y + length));
/*  401 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void a(b gc, j border, double x, double y, double length, boolean ridge) throws c {
/*  406 */     b color = border.o;
/*  407 */     double w = border.m / 2.0D;
/*  408 */     gc.d();
/*  409 */     a(gc, w, b);
/*  410 */     gc.a(ridge ? b(color) : a(color));
/*  411 */     gc.c(new Line2D.Double(x, y + w / 2.0D, x + length, y + w / 2.0D));
/*  412 */     gc.a(ridge ? a(color) : b(color));
/*  413 */     gc.c(new Line2D.Double(x, y - w / 2.0D, x + length, y - w / 2.0D));
/*  414 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void b(b gc, j border, double x, double y, double length, boolean ridge) throws c {
/*  419 */     b color = border.o;
/*  420 */     double w = border.m / 2.0D;
/*  421 */     a(gc, w, b);
/*  422 */     gc.a(ridge ? b(color) : a(color));
/*  423 */     gc.c(new Line2D.Double(x + w / 2.0D, y, x + w / 2.0D, y + length));
/*  424 */     gc.a(ridge ? a(color) : b(color));
/*  425 */     gc.c(new Line2D.Double(x - w / 2.0D, y, x - w / 2.0D, y + length));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void c(b gc, j border, double x, double y, double length) throws c {
/*  430 */     b color = border.o;
/*  431 */     double w = border.m / 3.0D;
/*  432 */     gc.d();
/*  433 */     a(gc, w, b);
/*  434 */     gc.a(color);
/*  435 */     gc.c(new Line2D.Double(x, y + w, x + length, y + w));
/*  436 */     gc.c(new Line2D.Double(x, y - w, x + length, y - w));
/*  437 */     gc.e();
/*      */   }
/*      */ 
/*      */   
/*      */   protected void d(b gc, j border, double x, double y, double length) throws c {
/*  442 */     b color = border.o;
/*  443 */     double w = border.m / 3.0D;
/*  444 */     gc.d();
/*  445 */     a(gc, w, b);
/*  446 */     gc.a(color);
/*  447 */     gc.c(new Line2D.Double(x + w, y, x + w, y + length));
/*  448 */     gc.c(new Line2D.Double(x - w, y, x - w, y + length));
/*  449 */     gc.e();
/*      */   }
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
/*      */   public void a(b gc, c borders, double x, double y, boolean vertical) throws c {
/*  462 */     int cols = borders.a();
/*  463 */     int rows = borders.b();
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
/*  490 */     List<a> list = new ArrayList<>();
/*      */ 
/*      */     
/*  493 */     if (vertical) {
/*  494 */       double cy = y;
/*  495 */       for (int index = 0; index <= cols; index++) {
/*  496 */         double cx = x;
/*  497 */         for (int row = rows - 1; row >= 0; row--) {
/*  498 */           double ch = borders.b(row);
/*  499 */           j border = borders.b(row, index);
/*  500 */           if (border != null && border.a()) {
/*  501 */             double xx = cx;
/*  502 */             double yy = cy;
/*  503 */             double hh = ch;
/*      */ 
/*      */             
/*  506 */             j ul = (index == 0) ? null : borders.a(index - 1, row + 1);
/*  507 */             j ur = (index == cols) ? null : borders.a(index, row + 1);
/*  508 */             double bw = Math.max((ul == null) ? 0.0D : ul.m, (ur == null) ? 0.0D : ur.m) / 2.0D;
/*  509 */             if (bw != 0.0D) {
/*  510 */               xx -= bw;
/*  511 */               hh += bw;
/*      */             } 
/*      */ 
/*      */             
/*  515 */             j bl = (index == 0) ? null : borders.a(index - 1, row);
/*  516 */             j br = (index == cols) ? null : borders.a(index, row);
/*  517 */             bw = Math.max((bl == null) ? 0.0D : bl.m, (br == null) ? 0.0D : br.m) / 2.0D;
/*  518 */             if (bw != 0.0D) {
/*  519 */               hh += bw;
/*      */             }
/*      */ 
/*      */             
/*  523 */             switch (border.n) {
/*      */               case 0:
/*      */               case 1:
/*      */                 break;
/*      */               default:
/*  528 */                 list.add(new a(this, border, xx, yy, hh, false, gc));
/*      */                 break;
/*      */             } 
/*      */           } 
/*  532 */           cx += ch;
/*      */         } 
/*  534 */         if (index != cols) {
/*  535 */           cy += borders.a(index);
/*      */         }
/*      */       } 
/*      */     } else {
/*  539 */       double cx = x;
/*  540 */       for (int index = 0; index <= cols; index++) {
/*  541 */         double cy = y;
/*  542 */         for (int row = 0; row < rows; row++) {
/*  543 */           double ch = borders.b(row);
/*  544 */           j border = borders.b(row, index);
/*  545 */           if (border != null && border.a()) {
/*  546 */             double xx = cx;
/*  547 */             double yy = cy;
/*  548 */             double hh = ch;
/*      */ 
/*      */             
/*  551 */             j ul = (index == 0) ? null : borders.a(index - 1, row);
/*  552 */             j ur = (index == cols) ? null : borders.a(index, row);
/*  553 */             double bw = Math.max((ul == null) ? 0.0D : ul.m, (ur == null) ? 0.0D : ur.m) / 2.0D;
/*  554 */             if (bw != 0.0D) {
/*  555 */               yy -= bw;
/*  556 */               hh += bw;
/*      */             } 
/*      */ 
/*      */             
/*  560 */             j bl = (index == 0) ? null : borders.a(index - 1, row + 1);
/*  561 */             j br = (index == cols) ? null : borders.a(index, row + 1);
/*  562 */             bw = Math.max((bl == null) ? 0.0D : bl.m, (br == null) ? 0.0D : br.m) / 2.0D;
/*  563 */             if (bw != 0.0D) {
/*  564 */               hh += bw;
/*      */             }
/*      */ 
/*      */             
/*  568 */             switch (border.n) {
/*      */               case 0:
/*      */               case 1:
/*      */                 break;
/*      */               default:
/*  573 */                 list.add(new a(this, border, xx, yy, hh, true, gc));
/*      */                 break;
/*      */             } 
/*      */           } 
/*  577 */           cy += ch;
/*      */         } 
/*  579 */         if (index != cols) {
/*  580 */           cx += borders.a(index);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  586 */     if (vertical) {
/*  587 */       double cx = x;
/*  588 */       for (int index = rows; index >= 0; index--) {
/*  589 */         double cy = y;
/*  590 */         for (int col = 0; col < cols; col++) {
/*  591 */           double cw = borders.a(col);
/*  592 */           j border = borders.a(col, index);
/*  593 */           if (border != null && border.a()) {
/*  594 */             double xx = cx;
/*  595 */             double yy = cy;
/*  596 */             double ww = cw;
/*      */ 
/*      */             
/*  599 */             j lu = (index == 0) ? null : borders.b(index - 1, col);
/*  600 */             j lb = (index == rows) ? null : borders.b(index, col);
/*  601 */             double bw = Math.max((lu == null) ? 0.0D : lu.m, (lb == null) ? 0.0D : lb.m) / 2.0D;
/*  602 */             if (bw != 0.0D) {
/*  603 */               yy -= bw;
/*  604 */               ww += bw;
/*      */             } 
/*      */ 
/*      */             
/*  608 */             j ru = (index == 0) ? null : borders.b(index - 1, col + 1);
/*  609 */             j rb = (index == rows) ? null : borders.b(index, col + 1);
/*  610 */             bw = Math.max((ru == null) ? 0.0D : ru.m, (rb == null) ? 0.0D : rb.m) / 2.0D;
/*  611 */             if (bw != 0.0D) {
/*  612 */               ww += bw;
/*      */             }
/*      */ 
/*      */             
/*  616 */             switch (border.n) {
/*      */               case 0:
/*      */               case 1:
/*      */                 break;
/*      */               default:
/*  621 */                 list.add(new a(this, border, xx, yy, ww, true, gc));
/*      */                 break;
/*      */             } 
/*      */           } 
/*  625 */           cy += cw;
/*      */         } 
/*  627 */         if (index > 0) {
/*  628 */           cx += borders.b(index - 1);
/*      */         }
/*      */       } 
/*      */     } else {
/*  632 */       double cy = y;
/*  633 */       for (int index = 0; index <= rows; index++) {
/*  634 */         double cx = x;
/*  635 */         for (int col = 0; col < cols; col++) {
/*  636 */           double cw = borders.a(col);
/*  637 */           j border = borders.a(col, index);
/*  638 */           if (border != null && border.a()) {
/*  639 */             double xx = cx;
/*  640 */             double yy = cy;
/*  641 */             double ww = cw;
/*      */ 
/*      */             
/*  644 */             j lu = (index == 0) ? null : borders.b(index - 1, col);
/*  645 */             j lb = (index == rows) ? null : borders.b(index, col);
/*  646 */             double bw = Math.max((lu == null) ? 0.0D : lu.m, (lb == null) ? 0.0D : lb.m) / 2.0D;
/*  647 */             if (bw != 0.0D) {
/*  648 */               xx -= bw;
/*  649 */               ww += bw;
/*      */             } 
/*      */ 
/*      */             
/*  653 */             j ru = (index == 0) ? null : borders.b(index - 1, col + 1);
/*  654 */             j rb = (index == rows) ? null : borders.b(index, col + 1);
/*  655 */             bw = Math.max((ru == null) ? 0.0D : ru.m, (rb == null) ? 0.0D : rb.m) / 2.0D;
/*  656 */             if (bw != 0.0D) {
/*  657 */               ww += bw;
/*      */             }
/*      */ 
/*      */             
/*  661 */             switch (border.n) {
/*      */               case 0:
/*      */               case 1:
/*      */                 break;
/*      */               default:
/*  666 */                 list.add(new a(this, border, xx, yy, ww, false, gc));
/*      */                 break;
/*      */             } 
/*      */           } 
/*  670 */           cx += cw;
/*      */         } 
/*  672 */         if (index != rows) {
/*  673 */           cy += borders.b(index);
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  679 */     Collections.sort(list);
/*  680 */     for (int i = 0; i < list.size(); i++) {
/*  681 */       a tb = list.get(i);
/*  682 */       tb.a();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void a(b gc, j border, double x, double y, double w, double h, double start, double extent) {
/*  687 */     switch (border.n) {
/*      */       case 3:
/*  689 */         gc.a(border.o);
/*  690 */         a(gc, border.m, b);
/*      */         break;
/*      */       
/*      */       case 4:
/*  694 */         gc.a(border.o);
/*  695 */         a(gc, border.m, d);
/*      */         break;
/*      */       
/*      */       case 5:
/*  699 */         gc.a(border.o);
/*  700 */         a(gc, border.m, c);
/*      */         break;
/*      */       
/*      */       case 2:
/*  704 */         gc.a(border.o);
/*  705 */         gc.a(border.m / 3.0D);
/*  706 */         arc = new Arc2D.Double(x - border.m / 3.0D, y - border.m / 3.0D, w + border.m * 2.0D / 3.0D, h + border.m * 2.0D / 3.0D, start - 1.0D, extent + 2.0D, 0);
/*      */         
/*  708 */         gc.c(arc);
/*      */         
/*  710 */         arc = new Arc2D.Double(x + border.m / 3.0D, y + border.m / 3.0D, w - border.m * 2.0D / 3.0D, h - border.m * 2.0D / 3.0D, start - 1.0D, extent + 2.0D, 0);
/*      */         
/*  712 */         gc.c(arc);
/*      */         return;
/*      */ 
/*      */       
/*      */       case 6:
/*  717 */         gc.a(border.m / 2.0D);
/*  718 */         if (start >= 45.0D && start <= 180.0D) {
/*  719 */           gc.a(a(border.o));
/*      */         } else {
/*  721 */           gc.a(b(border.o));
/*      */         } 
/*  723 */         arc = new Arc2D.Double(x - border.m / 4.0D, y - border.m / 4.0D, w + border.m / 2.0D, h + border.m / 2.0D, start - 1.0D, extent + 2.0D, 0);
/*      */         
/*  725 */         gc.c(arc);
/*      */         
/*  727 */         if (start >= 45.0D && start <= 180.0D) {
/*  728 */           gc.a(b(border.o));
/*      */         } else {
/*  730 */           gc.a(a(border.o));
/*      */         } 
/*  732 */         arc = new Arc2D.Double(x + border.m / 4.0D, y + border.m / 4.0D, w - border.m / 2.0D, h - border.m / 2.0D, start - 1.0D, extent + 2.0D, 0);
/*      */         
/*  734 */         gc.c(arc);
/*      */         return;
/*      */ 
/*      */       
/*      */       case 8:
/*  739 */         gc.a(border.m / 2.0D);
/*  740 */         if (start >= 45.0D && start <= 180.0D) {
/*  741 */           gc.a(b(border.o));
/*      */         } else {
/*  743 */           gc.a(a(border.o));
/*      */         } 
/*  745 */         arc = new Arc2D.Double(x - border.m / 4.0D, y - border.m / 4.0D, w + border.m / 2.0D, h + border.m / 2.0D, start - 1.0D, extent + 2.0D, 0);
/*      */         
/*  747 */         gc.c(arc);
/*      */         
/*  749 */         if (start >= 45.0D && start <= 180.0D) {
/*  750 */           gc.a(a(border.o));
/*      */         } else {
/*  752 */           gc.a(b(border.o));
/*      */         } 
/*  754 */         arc = new Arc2D.Double(x + border.m / 4.0D, y + border.m / 4.0D, w - border.m / 2.0D, h - border.m / 2.0D, start - 1.0D, extent + 2.0D, 0);
/*      */         
/*  756 */         gc.c(arc);
/*      */         return;
/*      */ 
/*      */       
/*      */       case 7:
/*  761 */         if (start >= 45.0D && start <= 180.0D) {
/*  762 */           gc.a(a(border.o));
/*      */         } else {
/*  764 */           gc.a(b(border.o));
/*      */         } 
/*  766 */         gc.a(border.m);
/*      */         break;
/*      */       
/*      */       case 9:
/*  770 */         if (start >= 45.0D && start <= 180.0D) {
/*  771 */           gc.a(b(border.o));
/*      */         } else {
/*  773 */           gc.a(a(border.o));
/*      */         } 
/*  775 */         gc.a(border.m);
/*      */         break;
/*      */       
/*      */       default:
/*  779 */         gc.a(border.m);
/*      */         break;
/*      */     } 
/*  782 */     Arc2D arc = new Arc2D.Double(x, y, w, h, start - 1.0D, extent + 2.0D, 0);
/*  783 */     gc.c(arc);
/*      */   }
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
/*      */   public void e(b gc, j border, double x, double y, double l) {
/*  796 */     switch (border.n) {
/*      */       case 2:
/*  798 */         c(gc, border, x, y, l);
/*      */         return;
/*      */       
/*      */       case 3:
/*  802 */         gc.d();
/*  803 */         a(gc, border.m, b);
/*  804 */         a(gc, border, x, y, l);
/*  805 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 4:
/*  809 */         gc.d();
/*  810 */         a(gc, border.m, d);
/*  811 */         a(gc, border, x, y, l);
/*  812 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 5:
/*  816 */         gc.d();
/*  817 */         a(gc, border.m, c);
/*  818 */         a(gc, border, x, y, l);
/*  819 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 6:
/*      */       case 7:
/*  824 */         a(gc, border, x, y, l, true);
/*      */         return;
/*      */       
/*      */       case 8:
/*      */       case 9:
/*  829 */         a(gc, border, x, y, l, false);
/*      */         return;
/*      */     } 
/*      */     
/*  833 */     throw new IllegalStateException();
/*      */   }
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
/*      */   public void f(b gc, j border, double x, double y, double l) {
/*  847 */     switch (border.n) {
/*      */       case 2:
/*  849 */         d(gc, border, x, y, l);
/*      */         return;
/*      */       
/*      */       case 3:
/*  853 */         gc.d();
/*  854 */         a(gc, border.m, b);
/*  855 */         b(gc, border, x, y, l);
/*  856 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 4:
/*  860 */         gc.d();
/*  861 */         a(gc, border.m, d);
/*  862 */         b(gc, border, x, y, l);
/*  863 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 5:
/*  867 */         gc.d();
/*  868 */         a(gc, border.m, c);
/*  869 */         b(gc, border, x, y, l);
/*  870 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 6:
/*      */       case 7:
/*  875 */         b(gc, border, x, y, l, true);
/*      */         return;
/*      */       
/*      */       case 8:
/*      */       case 9:
/*  880 */         b(gc, border, x, y, l, false);
/*      */         return;
/*      */     } 
/*      */     
/*  884 */     throw new IllegalStateException();
/*      */   }
/*      */   class a implements Comparable<Object> {
/*      */     final j a;
/*      */     final double b; final double c; final double d; final boolean e; a(a this$0, j border, double x, double y, double l, boolean v, boolean param1Boolean) { this.a = border; this.b = x; this.c = y; this.d = l; this.e = v; } void a() throws c { if (this.e) { this.g.f(this.f, this.a, this.b, this.c, this.d); } else { this.g.e(this.f, this.a, this.b, this.c, this.d); }
/*  889 */        } public int compareTo(Object o) { a tb = (a)o; return -this.a.a(tb.a); } } public Shape a(A border, double x, double y, double w, double h) { A.a topLeft = border.e();
/*  890 */     A.a topRight = border.f();
/*  891 */     A.a bottomLeft = border.g();
/*  892 */     A.a bottomRight = border.h();
/*      */ 
/*      */     
/*  895 */     if (topLeft == A.a.a && topRight == A.a.a && bottomLeft == A.a.a && bottomRight == A.a.a)
/*      */     {
/*      */       
/*  898 */       return new Rectangle2D.Double(x, y, w, h);
/*      */     }
/*  900 */     if (topLeft.equals(topRight) && topLeft.equals(bottomLeft) && topLeft.equals(bottomRight)) {
/*      */       
/*  902 */       A.a radius = topLeft;
/*  903 */       return new RoundRectangle2D.Double(x, y, w, h, radius.b * 2.0D, radius.c * 2.0D);
/*      */     } 
/*      */ 
/*      */     
/*  907 */     double topLeftHr = Math.min(w / 2.0D, topLeft.b);
/*  908 */     double topLeftVr = Math.min(h / 2.0D, topLeft.c);
/*  909 */     double topRightHr = Math.min(w / 2.0D, topRight.b);
/*  910 */     double topRightVr = Math.min(h / 2.0D, topRight.c);
/*  911 */     double bottomLeftHr = Math.min(w / 2.0D, bottomLeft.b);
/*  912 */     double bottomLeftVr = Math.min(h / 2.0D, bottomLeft.c);
/*  913 */     double bottomRightHr = Math.min(w / 2.0D, bottomRight.b);
/*  914 */     double bottomRightVr = Math.min(h / 2.0D, bottomRight.c);
/*      */     
/*  916 */     GeneralPath path = new GeneralPath();
/*      */ 
/*      */     
/*  919 */     path.moveTo((float)x, (float)(y + topLeftVr));
/*  920 */     path.lineTo((float)x, (float)(y + h - bottomLeftVr));
/*  921 */     if (bottomLeftHr != 0.0D || bottomLeftVr != 0.0D) {
/*  922 */       Arc2D arc = new Arc2D.Double(x, y + h - bottomLeftVr * 2.0D, bottomLeftHr * 2.0D, bottomLeftVr * 2.0D, 180.0D, 45.0D, 0);
/*      */       
/*  924 */       path.append(arc, true);
/*      */     } 
/*      */ 
/*      */     
/*  928 */     if (bottomLeftHr != 0.0D || bottomLeftVr != 0.0D) {
/*  929 */       Arc2D arc = new Arc2D.Double(x, y + h - bottomLeftVr * 2.0D, bottomLeftHr * 2.0D, bottomLeftVr * 2.0D, 225.0D, 45.0D, 0);
/*      */       
/*  931 */       path.append(arc, true);
/*      */     } 
/*  933 */     path.lineTo((float)(x + w - bottomRightHr), (float)(y + h));
/*  934 */     if (bottomRightHr != 0.0D || bottomRightVr != 0.0D) {
/*  935 */       Arc2D arc = new Arc2D.Double(x + w - bottomRightHr * 2.0D, y + h - bottomRightVr * 2.0D, bottomRightHr * 2.0D, bottomRightVr * 2.0D, 270.0D, 45.0D, 0);
/*      */       
/*  937 */       path.append(arc, true);
/*      */     } 
/*      */ 
/*      */     
/*  941 */     if (bottomRightHr != 0.0D || bottomRightVr != 0.0D) {
/*  942 */       Arc2D arc = new Arc2D.Double(x + w - bottomRightHr * 2.0D, y + h - bottomRightVr * 2.0D, bottomRightHr * 2.0D, bottomRightVr * 2.0D, 315.0D, 45.0D, 0);
/*      */       
/*  944 */       path.append(arc, true);
/*      */     } 
/*  946 */     path.lineTo((float)(x + w), (float)(y + topRightVr));
/*  947 */     if (topRightHr != 0.0D || topRightVr != 0.0D) {
/*  948 */       Arc2D arc = new Arc2D.Double(x + w - topRightHr * 2.0D, y, topRightHr * 2.0D, topRightVr * 2.0D, 0.0D, 45.0D, 0);
/*  949 */       path.append(arc, true);
/*      */     } 
/*      */ 
/*      */     
/*  953 */     if (topRightHr != 0.0D || topRightVr != 0.0D) {
/*  954 */       Arc2D arc = new Arc2D.Double(x + w - topRightHr * 2.0D, y, topRightHr * 2.0D, topRightVr * 2.0D, 45.0D, 45.0D, 0);
/*  955 */       path.append(arc, true);
/*      */     } 
/*  957 */     path.lineTo((float)(x + topLeftHr), (float)y);
/*  958 */     if (topLeftHr != 0.0D || topLeftVr != 0.0D) {
/*  959 */       Arc2D arc = new Arc2D.Double(x, y, topLeftHr * 2.0D, topLeftVr * 2.0D, 90.0D, 45.0D, 0);
/*  960 */       path.append(arc, true);
/*      */     } 
/*      */     
/*  963 */     if (topLeftHr != 0.0D || topLeftVr != 0.0D) {
/*  964 */       Arc2D arc = new Arc2D.Double(x, y, topLeftHr * 2.0D, topLeftVr * 2.0D, 135.0D, 45.0D, 0);
/*  965 */       path.append(arc, true);
/*      */     } 
/*  967 */     path.closePath();
/*  968 */     return path; }
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
/*      */   public void i(b gc, A border, double x, double y, double w, double h) throws c {
/*  981 */     j left = border.d();
/*  982 */     j top = border.a();
/*  983 */     j right = border.b();
/*  984 */     j bottom = border.c();
/*      */     
/*  986 */     A.a topLeft = border.e();
/*  987 */     A.a topRight = border.f();
/*  988 */     A.a bottomLeft = border.g();
/*  989 */     A.a bottomRight = border.h();
/*      */     
/*  991 */     double topLeftHr = Math.min(w / 2.0D, topLeft.b);
/*  992 */     double topLeftVr = Math.min(h / 2.0D, topLeft.c);
/*  993 */     double topRightHr = Math.min(w / 2.0D, topRight.b);
/*  994 */     double topRightVr = Math.min(h / 2.0D, topRight.c);
/*  995 */     double bottomLeftHr = Math.min(w / 2.0D, bottomLeft.b);
/*  996 */     double bottomLeftVr = Math.min(h / 2.0D, bottomLeft.c);
/*  997 */     double bottomRightHr = Math.min(w / 2.0D, bottomRight.b);
/*  998 */     double bottomRightVr = Math.min(h / 2.0D, bottomRight.c);
/*      */     
/* 1000 */     if (left.a()) {
/*      */       
/* 1002 */       if (left.n == 3 || left.n == 5 || left.n == 4 || left.n == 2)
/*      */       {
/* 1004 */         if (left.equals(top) && left.equals(right) && left.equals(bottom) && topLeft.equals(topRight) && topLeft
/* 1005 */           .equals(bottomLeft) && topLeft.equals(bottomRight)) {
/*      */           
/* 1007 */           a(gc, left, topLeft, x, y, w, h);
/*      */           
/*      */           return;
/*      */         } 
/*      */       }
/* 1012 */       if (left.n >= 2) {
/*      */         
/* 1014 */         gc.d();
/* 1015 */         boolean tr = (topLeftHr != 0.0D || topLeftVr != 0.0D);
/* 1016 */         boolean br = (bottomLeftHr != 0.0D || bottomLeftVr != 0.0D);
/* 1017 */         if (tr) {
/* 1018 */           a(gc, left, x + left.m / 2.0D, y + top.m / 2.0D, topLeftHr * 2.0D, topLeftVr * 2.0D, 135.0D, 45.0D);
/*      */         }
/* 1020 */         if (br) {
/* 1021 */           a(gc, left, x + left.m / 2.0D, y + h - bottomLeftVr * 2.0D - bottom.m / 2.0D, bottomLeftHr * 2.0D, bottomLeftVr * 2.0D, 180.0D, 45.0D);
/*      */         }
/*      */ 
/*      */         
/* 1025 */         if (left.m > 1.0D) {
/* 1026 */           c(gc, left, tr ? 0.0D : top.m, br ? 0.0D : bottom.m, x, y + (tr ? (top.m / 2.0D) : 0.0D) + topLeftVr, h - (tr ? (top.m / 2.0D) : 0.0D) - (br ? (bottom.m / 2.0D) : 0.0D) - topLeftVr - bottomLeftVr);
/*      */         }
/*      */ 
/*      */         
/* 1030 */         a(gc, this, border, x, y + topLeftVr, w, h - topLeftVr - bottomLeftVr);
/* 1031 */         gc.e();
/*      */       } 
/*      */     } 
/* 1034 */     if (top.a() && top.n >= 2) {
/*      */       
/* 1036 */       gc.d();
/* 1037 */       boolean lr = (topLeftHr != 0.0D || topLeftVr != 0.0D);
/* 1038 */       boolean rr = (topRightHr != 0.0D || topRightVr != 0.0D);
/* 1039 */       if (lr) {
/* 1040 */         a(gc, top, x + left.m / 2.0D, y + top.m / 2.0D, topLeftHr * 2.0D, topLeftVr * 2.0D, 90.0D, 45.0D);
/*      */       }
/* 1042 */       if (rr) {
/* 1043 */         a(gc, top, x + w - topRightHr * 2.0D - right.m / 2.0D, y + top.m / 2.0D, topRightHr * 2.0D, topRightVr * 2.0D, 45.0D, 45.0D);
/*      */       }
/*      */ 
/*      */       
/* 1047 */       if (top.m > 1.0D) {
/* 1048 */         a(gc, top, lr ? 0.0D : left.m, rr ? 0.0D : right.m, x + (lr ? (left.m / 2.0D) : 0.0D) + topLeftHr, y, w - (lr ? (left.m / 2.0D) : 0.0D) - (rr ? (right.m / 2.0D) : 0.0D) - topLeftHr - topRightHr);
/*      */       }
/*      */ 
/*      */       
/* 1052 */       b(gc, this, border, x + topLeftHr, y, w - topLeftHr - topRightHr, h);
/* 1053 */       gc.e();
/*      */     } 
/* 1055 */     if (right.a() && right.n >= 2) {
/*      */       
/* 1057 */       gc.d();
/* 1058 */       boolean tr = (topRightHr != 0.0D || topRightVr != 0.0D);
/* 1059 */       boolean br = (bottomRightHr != 0.0D || bottomRightVr != 0.0D);
/* 1060 */       if (tr) {
/* 1061 */         a(gc, right, x + w - topRightHr * 2.0D - right.m / 2.0D, y + top.m / 2.0D, topRightHr * 2.0D, topRightVr * 2.0D, 0.0D, 45.0D);
/*      */       }
/*      */       
/* 1064 */       if (br) {
/* 1065 */         a(gc, right, x + w - bottomRightHr * 2.0D - right.m / 2.0D, y + h - bottomRightVr * 2.0D - bottom.m / 2.0D, bottomRightHr * 2.0D, bottomRightVr * 2.0D, 315.0D, 45.0D);
/*      */       }
/*      */ 
/*      */       
/* 1069 */       if (right.m > 1.0D) {
/* 1070 */         d(gc, right, tr ? 0.0D : top.m, br ? 0.0D : bottom.m, x + w, y + (tr ? (top.m / 2.0D) : 0.0D) + topRightVr, h - (tr ? (top.m / 2.0D) : 0.0D) - (br ? (bottom.m / 2.0D) : 0.0D) - topRightVr - bottomRightVr);
/*      */       }
/*      */ 
/*      */       
/* 1074 */       c(gc, this, border, x, y + topRightVr, w, h - topRightVr - bottomRightVr);
/* 1075 */       gc.e();
/*      */     } 
/* 1077 */     if (bottom.a() && bottom.n >= 2) {
/*      */       
/* 1079 */       gc.d();
/* 1080 */       boolean lr = (bottomLeftHr != 0.0D || bottomLeftVr != 0.0D);
/* 1081 */       boolean rr = (bottomRightHr != 0.0D || bottomRightVr != 0.0D);
/* 1082 */       if (lr) {
/* 1083 */         a(gc, bottom, x + left.m / 2.0D, y + h - bottomLeftVr * 2.0D - bottom.m / 2.0D, bottomLeftHr * 2.0D, bottomLeftVr * 2.0D, 225.0D, 45.0D);
/*      */       }
/*      */       
/* 1086 */       if (rr) {
/* 1087 */         a(gc, bottom, x + w - bottomRightHr * 2.0D - right.m / 2.0D, y + h - bottomRightVr * 2.0D - bottom.m / 2.0D, bottomRightHr * 2.0D, bottomRightVr * 2.0D, 270.0D, 45.0D);
/*      */       }
/*      */ 
/*      */       
/* 1091 */       if (bottom.m > 1.0D) {
/* 1092 */         b(gc, bottom, lr ? 0.0D : left.m, rr ? 0.0D : right.m, x + (lr ? (left.m / 2.0D) : 0.0D) + bottomLeftHr, y + h, w - (lr ? (left.m / 2.0D) : 0.0D) - (rr ? (right.m / 2.0D) : 0.0D) - bottomLeftHr - bottomRightHr);
/*      */       }
/*      */ 
/*      */       
/* 1096 */       d(gc, this, border, x + bottomLeftHr, y, w - bottomLeftHr - bottomRightHr, h);
/* 1097 */       gc.e();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void a(b gc, a renderer, A border, double x, double y, double w, double h) {
/*      */     j left;
/* 1103 */     switch ((border.d()).n) {
/*      */       case 2:
/* 1105 */         renderer.d(gc, border, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 3:
/* 1109 */         left = border.d();
/* 1110 */         gc.d();
/* 1111 */         a(gc, left.m, b);
/* 1112 */         renderer.h(gc, border, x, y, w, h);
/* 1113 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 4:
/* 1117 */         gc.d();
/* 1118 */         a(gc, (border.d()).m, d);
/* 1119 */         renderer.h(gc, border, x, y, w, h);
/* 1120 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 5:
/* 1124 */         gc.d();
/* 1125 */         a(gc, (border.d()).m, c);
/* 1126 */         renderer.h(gc, border, x, y, w, h);
/* 1127 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 6:
/* 1131 */         renderer.d(gc, border, true, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 7:
/* 1135 */         renderer.b(gc, border, true, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 8:
/* 1139 */         renderer.d(gc, border, false, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 9:
/* 1143 */         renderer.b(gc, border, false, x, y, w, h);
/*      */         return;
/*      */     } 
/*      */     
/* 1147 */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */   
/*      */   private void b(b gc, a renderer, A border, double x, double y, double w, double h) {
/*      */     j top;
/* 1153 */     switch ((border.a()).n) {
/*      */       case 2:
/* 1155 */         renderer.c(gc, border, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 3:
/* 1159 */         top = border.a();
/* 1160 */         gc.d();
/* 1161 */         a(gc, top.m, b);
/* 1162 */         renderer.g(gc, border, x, y, w, h);
/* 1163 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 4:
/* 1167 */         gc.d();
/* 1168 */         a(gc, (border.a()).m, d);
/* 1169 */         renderer.g(gc, border, x, y, w, h);
/* 1170 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 5:
/* 1174 */         gc.d();
/* 1175 */         a(gc, (border.a()).m, c);
/* 1176 */         renderer.g(gc, border, x, y, w, h);
/* 1177 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 6:
/* 1181 */         renderer.g(gc, border, true, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 7:
/* 1185 */         renderer.e(gc, border, true, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 8:
/* 1189 */         renderer.g(gc, border, false, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 9:
/* 1193 */         renderer.e(gc, border, false, x, y, w, h);
/*      */         return;
/*      */     } 
/*      */     
/* 1197 */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */   
/*      */   private void c(b gc, a renderer, A border, double x, double y, double w, double h) {
/*      */     j right;
/* 1203 */     switch ((border.b()).n) {
/*      */       case 2:
/* 1205 */         renderer.b(gc, border, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 3:
/* 1209 */         right = border.b();
/* 1210 */         gc.d();
/* 1211 */         a(gc, right.m, b);
/* 1212 */         renderer.f(gc, border, x, y, w, h);
/* 1213 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 4:
/* 1217 */         gc.d();
/* 1218 */         a(gc, (border.b()).m, d);
/* 1219 */         renderer.f(gc, border, x, y, w, h);
/* 1220 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 5:
/* 1224 */         gc.d();
/* 1225 */         a(gc, (border.b()).m, c);
/* 1226 */         renderer.f(gc, border, x, y, w, h);
/* 1227 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 6:
/* 1231 */         renderer.a(gc, border, true, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 7:
/* 1235 */         renderer.f(gc, border, true, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 8:
/* 1239 */         renderer.a(gc, border, false, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 9:
/* 1243 */         renderer.f(gc, border, false, x, y, w, h);
/*      */         return;
/*      */     } 
/*      */     
/* 1247 */     throw new IllegalStateException();
/*      */   }
/*      */ 
/*      */   
/*      */   private void d(b gc, a renderer, A border, double x, double y, double w, double h) {
/*      */     j bottom;
/* 1253 */     switch ((border.c()).n) {
/*      */       case 2:
/* 1255 */         renderer.a(gc, border, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 3:
/* 1259 */         bottom = border.c();
/* 1260 */         gc.d();
/* 1261 */         a(gc, bottom.m, b);
/* 1262 */         renderer.e(gc, border, x, y, w, h);
/* 1263 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 4:
/* 1267 */         gc.d();
/* 1268 */         a(gc, (border.c()).m, d);
/* 1269 */         renderer.e(gc, border, x, y, w, h);
/* 1270 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 5:
/* 1274 */         gc.d();
/* 1275 */         a(gc, (border.c()).m, c);
/* 1276 */         renderer.e(gc, border, x, y, w, h);
/* 1277 */         gc.e();
/*      */         return;
/*      */       
/*      */       case 6:
/* 1281 */         renderer.c(gc, border, true, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 7:
/* 1285 */         renderer.h(gc, border, true, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 8:
/* 1289 */         renderer.c(gc, border, false, x, y, w, h);
/*      */         return;
/*      */       
/*      */       case 9:
/* 1293 */         renderer.h(gc, border, false, x, y, w, h);
/*      */         return;
/*      */     } 
/*      */     
/* 1297 */     throw new IllegalStateException();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/f/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */