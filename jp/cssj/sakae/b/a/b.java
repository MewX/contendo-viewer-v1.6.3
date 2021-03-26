/*     */ package jp.cssj.sakae.b.a;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import jp.cssj.sakae.a.e;
/*     */ import jp.cssj.sakae.a.f;
/*     */ import jp.cssj.sakae.c.a.e;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.c.d;
/*     */ import jp.cssj.sakae.c.c.e;
/*     */ import jp.cssj.sakae.c.c.f;
/*     */ import jp.cssj.sakae.c.c.i;
/*     */ import jp.cssj.sakae.c.d.h;
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
/*     */ public class b
/*     */   implements b
/*     */ {
/*     */   protected final Graphics2D a;
/*     */   
/*     */   protected static class b
/*     */   {
/*     */     public final Shape a;
/*     */     public final AffineTransform b;
/*     */     public final Stroke c;
/*     */     public final Object d;
/*     */     public final Object e;
/*     */     public final Color f;
/*     */     public final Paint g;
/*     */     public final AffineTransform h;
/*     */     public final AffineTransform i;
/*     */     public final float j;
/*     */     public final short k;
/*     */     public final Composite l;
/*     */     
/*     */     public b(b gc) {
/*  63 */       Graphics2D g = gc.a;
/*  64 */       this.b = g.getTransform();
/*  65 */       this.a = g.getClip();
/*  66 */       this.d = gc.c;
/*  67 */       this.e = gc.d;
/*  68 */       this.c = g.getStroke();
/*  69 */       this.f = g.getColor();
/*  70 */       this.l = g.getComposite();
/*  71 */       this.g = gc.e;
/*  72 */       this.h = gc.f;
/*  73 */       this.j = gc.h;
/*  74 */       this.i = gc.g;
/*  75 */       this.k = gc.i;
/*     */     }
/*     */     
/*     */     public void a(b gc) {
/*  79 */       Graphics2D g = gc.a;
/*  80 */       g.setTransform(this.b);
/*  81 */       g.setClip(this.a);
/*  82 */       g.setStroke(this.c);
/*  83 */       g.setColor(this.f);
/*  84 */       g.setComposite(this.l);
/*  85 */       gc.d = this.e;
/*  86 */       gc.c = this.d;
/*  87 */       gc.e = this.g;
/*  88 */       gc.f = this.h;
/*  89 */       gc.h = this.j;
/*  90 */       gc.g = this.i;
/*  91 */       gc.i = this.k;
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean b = false;
/*     */   
/*     */   protected Object c;
/*     */   
/*     */   protected Object d;
/*     */   
/*     */   protected Paint e;
/*     */   
/*     */   protected AffineTransform f;
/*     */   
/*     */   protected AffineTransform g;
/*     */   
/* 107 */   protected float h = 1.0F;
/*     */   
/* 109 */   protected short i = 0;
/*     */   
/* 111 */   protected ArrayList<b> j = new ArrayList<>();
/*     */   
/*     */   protected final e k;
/*     */   
/*     */   public b(Graphics2D g, e fm) {
/* 116 */     this.a = g;
/* 117 */     this.a.setStroke(new BasicStroke(1.0F, 0, 0));
/* 118 */     this.e = this.a.getPaint();
/* 119 */     this.k = fm;
/*     */   }
/*     */   
/*     */   public e a() {
/* 123 */     return this.k;
/*     */   }
/*     */   
/*     */   public Graphics2D b() {
/* 127 */     return this.a;
/*     */   }
/*     */   
/*     */   public boolean c() {
/* 131 */     return this.b;
/*     */   }
/*     */   
/*     */   public void d() {
/* 135 */     if (this.j.isEmpty()) {
/* 136 */       this.b = false;
/*     */     }
/* 138 */     this.j.add(new b(this));
/*     */   }
/*     */   
/*     */   public void e() {
/* 142 */     b state = this.j.remove(this.j.size() - 1);
/* 143 */     state.a(this);
/*     */   }
/*     */   
/*     */   public void a(double width) {
/* 147 */     BasicStroke stroke = (BasicStroke)this.a.getStroke();
/* 148 */     float fwidth = (float)width;
/* 149 */     this.a.setStroke(new BasicStroke(fwidth, stroke.getEndCap(), stroke.getLineJoin(), stroke.getMiterLimit(), stroke
/* 150 */           .getDashArray(), stroke.getDashPhase()));
/*     */   }
/*     */   
/*     */   public double f() {
/* 154 */     return ((BasicStroke)this.a.getStroke()).getLineWidth();
/*     */   }
/*     */   public void a(double[] pattern) {
/*     */     float[] fpattern;
/* 158 */     BasicStroke stroke = (BasicStroke)this.a.getStroke();
/*     */     
/* 160 */     if (pattern != null && pattern.length > 0) {
/* 161 */       fpattern = new float[pattern.length];
/* 162 */       for (int i = 0; i < pattern.length; i++) {
/* 163 */         fpattern[i] = (float)pattern[i];
/*     */       }
/*     */     } else {
/* 166 */       fpattern = null;
/*     */     } 
/* 168 */     this.a.setStroke(new BasicStroke(stroke.getLineWidth(), stroke.getEndCap(), stroke.getLineJoin(), stroke
/* 169 */           .getMiterLimit(), fpattern, stroke.getDashPhase()));
/*     */   }
/*     */   
/*     */   public double[] g() {
/* 173 */     float[] da = ((BasicStroke)this.a.getStroke()).getDashArray();
/* 174 */     double[] pattern = new double[da.length];
/* 175 */     for (int i = 0; i < da.length; i++) {
/* 176 */       pattern[i] = da[i];
/*     */     }
/* 178 */     return pattern;
/*     */   }
/*     */   
/*     */   public void a(short style) {
/* 182 */     BasicStroke stroke = (BasicStroke)this.a.getStroke();
/* 183 */     this.a.setStroke(new BasicStroke(stroke.getLineWidth(), stroke.getEndCap(), style, stroke.getMiterLimit(), stroke
/* 184 */           .getDashArray(), stroke.getDashPhase()));
/*     */   }
/*     */   
/*     */   public short h() {
/* 188 */     return (short)((BasicStroke)this.a.getStroke()).getLineJoin();
/*     */   }
/*     */   
/*     */   public void b(short style) {
/* 192 */     BasicStroke stroke = (BasicStroke)this.a.getStroke();
/* 193 */     this.a.setStroke(new BasicStroke(stroke.getLineWidth(), style, stroke.getLineJoin(), stroke.getMiterLimit(), stroke
/* 194 */           .getDashArray(), stroke.getDashPhase()));
/*     */   }
/*     */   
/*     */   public short i() {
/* 198 */     return (short)((BasicStroke)this.a.getStroke()).getEndCap();
/*     */   }
/*     */   protected void a(Object _paint, boolean fill) throws c {
/*     */     Paint awtPaint;
/*     */     AffineTransform at;
/*     */     f pattern;
/* 204 */     e paint = (e)_paint;
/*     */     
/* 206 */     switch (paint.b()) {
/*     */       case 1:
/* 208 */         awtPaint = jp.cssj.sakae.b.c.a.a((jp.cssj.sakae.c.c.b)paint);
/* 209 */         at = null;
/*     */         break;
/*     */       
/*     */       case 2:
/* 213 */         pattern = (f)paint;
/* 214 */         awtPaint = jp.cssj.sakae.b.c.a.a(pattern, this);
/* 215 */         at = pattern.a();
/*     */         break;
/*     */       
/*     */       case 3:
/* 219 */         awtPaint = jp.cssj.sakae.b.c.a.a((d)paint);
/* 220 */         at = null;
/*     */         break;
/*     */       case 4:
/* 223 */         awtPaint = jp.cssj.sakae.b.c.a.a((i)paint);
/* 224 */         at = null;
/*     */         break;
/*     */       default:
/* 227 */         throw new IllegalStateException();
/*     */     } 
/* 229 */     if (fill) {
/* 230 */       this.d = _paint;
/* 231 */       this.e = awtPaint;
/* 232 */       this.f = at;
/*     */     } else {
/* 234 */       this.a.setPaint(awtPaint);
/* 235 */       this.c = _paint;
/* 236 */       this.g = at;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(Object paint) throws c {
/* 241 */     a(paint, false);
/*     */   }
/*     */   
/*     */   public Object j() {
/* 245 */     return this.c;
/*     */   }
/*     */   
/*     */   public void b(Object paint) throws c {
/* 249 */     a(paint, true);
/*     */   }
/*     */   
/*     */   public Object k() {
/* 253 */     return this.d;
/*     */   }
/*     */   
/*     */   public void a(float alpha) {
/* 257 */     if (alpha == 1.0F) {
/* 258 */       this.a.setPaintMode();
/*     */       return;
/*     */     } 
/* 261 */     AlphaComposite comp = AlphaComposite.getInstance(3, alpha);
/* 262 */     this.a.setComposite(comp);
/*     */   }
/*     */   
/*     */   public float l() {
/* 266 */     return ((AlphaComposite)this.a.getComposite()).getAlpha();
/*     */   }
/*     */   
/*     */   public void b(float alpha) {
/* 270 */     this.h = alpha;
/*     */   }
/*     */   
/*     */   public float m() {
/* 274 */     return this.h;
/*     */   }
/*     */   
/*     */   public void c(short textMode) {
/* 278 */     this.i = textMode;
/*     */   }
/*     */   
/*     */   public short n() {
/* 282 */     return this.i;
/*     */   }
/*     */   
/*     */   public void a(AffineTransform at) {
/* 286 */     this.a.transform(at);
/*     */   }
/*     */   
/*     */   public AffineTransform o() {
/* 290 */     return this.a.getTransform();
/*     */   }
/*     */   
/*     */   public void a(Shape shape) {
/* 294 */     this.a.clip(shape);
/*     */   }
/*     */   
/*     */   public void p() {
/* 298 */     b state = this.j.get(this.j.size() - 1);
/* 299 */     state.a(this);
/*     */   }
/*     */   
/*     */   public void a(jp.cssj.sakae.c.b.b image) throws c {
/* 303 */     this.b = true;
/*     */     
/* 305 */     Composite composite = this.a.getComposite();
/* 306 */     if (this.h == 1.0F) {
/* 307 */       this.a.setPaintMode();
/*     */     } else {
/* 309 */       this.a.setComposite(AlphaComposite.getInstance(3, this.h));
/*     */     } 
/* 311 */     image.a(this);
/* 312 */     this.a.setComposite(composite);
/*     */   }
/*     */   
/*     */   public void b(Shape shape) {
/* 316 */     this.b = true;
/* 317 */     Paint paint = this.a.getPaint();
/* 318 */     this.a.setPaint(this.e);
/*     */     
/* 320 */     Composite composite = this.a.getComposite();
/* 321 */     if (this.h == 1.0F) {
/* 322 */       this.a.setPaintMode();
/*     */     } else {
/* 324 */       this.a.setComposite(AlphaComposite.getInstance(3, this.h));
/*     */     } 
/*     */     
/* 327 */     if (this.f != null) {
/* 328 */       AffineTransform saveAt = this.a.getTransform();
/* 329 */       this.a.transform(this.f);
/*     */       try {
/* 331 */         shape = this.f.createInverse().createTransformedShape(shape);
/* 332 */       } catch (NoninvertibleTransformException noninvertibleTransformException) {
/* 333 */         throw new RuntimeException(noninvertibleTransformException);
/*     */       } 
/* 335 */       this.a.fill(shape);
/* 336 */       this.a.setTransform(saveAt);
/*     */     } else {
/* 338 */       this.a.fill(shape);
/*     */     } 
/*     */     
/* 341 */     this.a.setPaint(paint);
/* 342 */     this.a.setComposite(composite);
/*     */   }
/*     */   
/*     */   public void c(Shape shape) {
/* 346 */     this.b = true;
/* 347 */     if (this.g != null) {
/* 348 */       AffineTransform saveAt = this.a.getTransform();
/* 349 */       this.a.transform(this.g);
/*     */       try {
/* 351 */         shape = this.g.createInverse().createTransformedShape(shape);
/* 352 */       } catch (NoninvertibleTransformException noninvertibleTransformException) {
/* 353 */         throw new RuntimeException(noninvertibleTransformException);
/*     */       } 
/* 355 */       this.a.draw(shape);
/* 356 */       this.a.setTransform(saveAt);
/*     */     } else {
/* 358 */       this.a.draw(shape);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void d(Shape shape) {
/* 363 */     b(shape);
/* 364 */     c(shape);
/*     */   }
/*     */   
/*     */   public void a(h text, double x, double y) throws c {
/* 368 */     this.b = true;
/*     */     
/* 370 */     d();
/* 371 */     a(AffineTransform.getTranslateInstance(x, y));
/* 372 */     e font = ((f)text.d()).a();
/*     */     try {
/* 374 */       font.a(this, text);
/* 375 */     } catch (IOException iOException) {
/* 376 */       throw new c(iOException);
/*     */     } 
/* 378 */     e();
/*     */   }
/*     */   
/*     */   private static class a extends b implements jp.cssj.sakae.c.b.a {
/*     */     final BufferedImage l;
/*     */     final AffineTransform m;
/*     */     
/*     */     a(Graphics2D g2d, e fm, BufferedImage image, AffineTransform at) {
/* 386 */       super(g2d, fm);
/* 387 */       this.l = image;
/*     */       try {
/* 389 */         at = (at == null) ? null : at.createInverse();
/* 390 */       } catch (NoninvertibleTransformException noninvertibleTransformException) {
/* 391 */         at = null;
/*     */       } 
/* 393 */       this.m = at;
/*     */     }
/*     */     public jp.cssj.sakae.c.b.b q() throws c {
/*     */       jp.cssj.sakae.c.b.a.a a1;
/* 397 */       jp.cssj.sakae.b.b.b b1 = new jp.cssj.sakae.b.b.b(this.l);
/* 398 */       if (this.m != null && !this.m.isIdentity()) {
/* 399 */         a1 = new jp.cssj.sakae.c.b.a.a((jp.cssj.sakae.c.b.b)b1, this.m);
/*     */       }
/* 401 */       return (jp.cssj.sakae.c.b.b)a1;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public jp.cssj.sakae.c.b.a a(double width, double height) throws c {
/* 407 */     Point2D size = new Point2D.Double(width, height);
/* 408 */     AffineTransform at = this.a.getTransform();
/* 409 */     if (at != null) {
/* 410 */       at.transform(size, size);
/*     */     }
/*     */     
/* 413 */     int w = (int)size.getX();
/* 414 */     int h = (int)size.getY();
/* 415 */     BufferedImage image = new BufferedImage(w, h, 2);
/* 416 */     Graphics2D g2d = (Graphics2D)image.getGraphics();
/* 417 */     g2d.setRenderingHints(this.a.getRenderingHints());
/* 418 */     a gc = new a(g2d, a(), image, at);
/* 419 */     b state = new b(this);
/* 420 */     state.a(gc);
/* 421 */     gc.j = (ArrayList<b>)this.j.clone();
/* 422 */     return gc;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/b/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */