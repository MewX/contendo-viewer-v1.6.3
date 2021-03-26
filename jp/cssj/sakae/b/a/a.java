/*     */ package jp.cssj.sakae.b.a;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.Image;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.BufferedImageOp;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.AttributedString;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Map;
/*     */ import jp.cssj.sakae.b.b.b;
/*     */ import jp.cssj.sakae.c.a.c;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.b.b;
/*     */ import jp.cssj.sakae.c.c.b;
/*     */ import jp.cssj.sakae.c.c.e;
/*     */ import jp.cssj.sakae.c.d.a.b;
/*     */ import jp.cssj.sakae.c.d.b.c;
/*     */ import jp.cssj.sakae.c.d.e;
/*     */ import jp.cssj.sakae.c.d.j;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class a
/*     */   extends Graphics2D
/*     */   implements Cloneable
/*     */ {
/*     */   protected b a;
/*     */   protected final GraphicsConfiguration b;
/*  63 */   protected static final AffineTransform c = new AffineTransform();
/*     */   
/*  65 */   protected AffineTransform d = new AffineTransform();
/*     */   
/*  67 */   protected Shape e = null;
/*     */   
/*     */   protected Composite f;
/*     */   
/*  71 */   protected Paint g = Color.black;
/*     */   
/*  73 */   protected Color h = Color.BLACK;
/*     */   
/*  75 */   protected Color i = Color.WHITE;
/*     */   
/*  77 */   protected Stroke j = new BasicStroke();
/*     */   
/*  79 */   protected RenderingHints k = new RenderingHints(null);
/*     */   
/*  81 */   protected Font l = new Font("serif", 0, 12);
/*     */   
/*     */   public a(b gc, GraphicsConfiguration config) {
/*  84 */     this.a = gc;
/*  85 */     this.b = config;
/*     */   }
/*     */   
/*     */   public a(b gc) {
/*  89 */     this(gc, null);
/*     */   }
/*     */   
/*     */   public b a() {
/*  93 */     return this.a;
/*     */   }
/*     */   
/*     */   private void b() {
/*  97 */     this.a.p();
/*  98 */     b gcolor = jp.cssj.sakae.b.c.a.a(this.h);
/*  99 */     this.a.a(gcolor);
/* 100 */     this.a.b(gcolor);
/* 101 */     if (this.j instanceof BasicStroke) {
/* 102 */       BasicStroke bs = (BasicStroke)this.j;
/* 103 */       this.a.a(bs.getLineWidth());
/* 104 */       float[] da = bs.getDashArray();
/* 105 */       if (da != null) {
/* 106 */         double[] dda = new double[da.length];
/* 107 */         for (int i = 0; i < da.length; i++) {
/* 108 */           dda[i] = da[i];
/*     */         }
/* 110 */         this.a.a(dda);
/*     */       } else {
/* 112 */         this.a.a(null);
/*     */       } 
/* 114 */       this.a.b((short)bs.getEndCap());
/* 115 */       this.a.a((short)bs.getLineJoin());
/*     */     } else {
/* 117 */       throw new IllegalStateException();
/*     */     } 
/* 119 */     if (this.d != null) {
/* 120 */       this.a.a(this.d);
/*     */     }
/* 122 */     if (this.e != null) {
/* 123 */       Shape s = this.e;
/* 124 */       if (this.d != null) {
/*     */         try {
/* 126 */           s = this.d.createInverse().createTransformedShape(s);
/* 127 */         } catch (NoninvertibleTransformException e) {
/* 128 */           s = null;
/*     */         } 
/*     */       }
/* 131 */       this.a.a(s);
/*     */     } 
/*     */   }
/*     */   
/*     */   public GraphicsConfiguration getDeviceConfiguration() {
/* 136 */     return this.b;
/*     */   }
/*     */   
/*     */   public void setRenderingHint(RenderingHints.Key key, Object value) {
/* 140 */     this.k.put(key, value);
/*     */   }
/*     */   
/*     */   public Object getRenderingHint(RenderingHints.Key key) {
/* 144 */     return this.k.get(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRenderingHints(Map<?, ?> hints) {
/* 149 */     this.k = new RenderingHints((Map)hints);
/*     */   }
/*     */   
/*     */   public void addRenderingHints(Map<?, ?> hints) {
/* 153 */     this.k.putAll(hints);
/*     */   }
/*     */   
/*     */   public RenderingHints getRenderingHints() {
/* 157 */     return this.k;
/*     */   }
/*     */   
/*     */   public void transform(AffineTransform at) {
/* 161 */     this.d.concatenate(at);
/* 162 */     this.a.a(at);
/*     */   }
/*     */   
/*     */   public void setTransform(AffineTransform at) {
/* 166 */     this.d = new AffineTransform(at);
/* 167 */     b();
/*     */   }
/*     */   
/*     */   public AffineTransform getTransform() {
/* 171 */     return new AffineTransform(this.d);
/*     */   }
/*     */   
/*     */   public void setPaint(Paint paint) {
/* 175 */     if (paint == null) {
/*     */       return;
/*     */     }
/* 178 */     this.g = paint;
/* 179 */     if (paint instanceof Color) {
/* 180 */       this.h = (Color)paint;
/*     */     }
/* 182 */     e spaint = jp.cssj.sakae.b.c.a.a(paint);
/* 183 */     if (spaint != null) {
/* 184 */       this.a.a(spaint);
/* 185 */       this.a.b(spaint);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Paint getPaint() {
/* 190 */     return this.g;
/*     */   }
/*     */   
/*     */   public void setComposite(Composite composite) {
/* 194 */     this.f = composite;
/* 195 */     if (composite instanceof AlphaComposite) {
/* 196 */       float alpha = ((AlphaComposite)composite).getAlpha();
/* 197 */       this.a.b(alpha);
/* 198 */       this.a.a(alpha);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Composite getComposite() {
/* 203 */     return this.f;
/*     */   }
/*     */   
/*     */   public void setBackground(Color background) {
/* 207 */     this.i = background;
/*     */   }
/*     */   
/*     */   public Color getBackground() {
/* 211 */     return this.i;
/*     */   }
/*     */   
/*     */   public void setStroke(Stroke stroke) {
/* 215 */     this.j = stroke;
/* 216 */     if (stroke instanceof BasicStroke) {
/* 217 */       BasicStroke bs = (BasicStroke)stroke;
/* 218 */       this.a.a(bs.getLineWidth());
/* 219 */       float[] da = bs.getDashArray();
/* 220 */       if (da != null) {
/* 221 */         double[] dda = new double[da.length];
/* 222 */         for (int i = 0; i < da.length; i++) {
/* 223 */           dda[i] = da[i];
/*     */         }
/* 225 */         this.a.a(dda);
/*     */       } else {
/* 227 */         this.a.a(null);
/*     */       } 
/* 229 */       this.a.b((short)bs.getEndCap());
/* 230 */       this.a.a((short)bs.getLineJoin());
/*     */     } 
/*     */   }
/*     */   
/*     */   public Stroke getStroke() {
/* 235 */     return this.j;
/*     */   }
/*     */   
/*     */   public Color getColor() {
/* 239 */     return this.h;
/*     */   }
/*     */   
/*     */   public void setColor(Color color) {
/* 243 */     setPaint(color);
/*     */   }
/*     */   
/*     */   public void clip(Shape clip) {
/* 247 */     Shape s = clip;
/* 248 */     if (s != null) {
/* 249 */       s = this.d.createTransformedShape(s);
/*     */     }
/* 251 */     if (this.e != null) {
/* 252 */       Area newClip = new Area(this.e);
/* 253 */       newClip.intersect(new Area(s));
/* 254 */       this.e = new GeneralPath(newClip);
/*     */     } else {
/* 256 */       this.e = s;
/*     */     } 
/*     */     
/* 259 */     this.a.a(clip);
/*     */   }
/*     */   
/*     */   public void clipRect(int x, int y, int width, int height) {
/* 263 */     clip(new Rectangle(x, y, width, height));
/*     */   }
/*     */   
/*     */   public void setClip(Shape clip) {
/* 267 */     if (clip != null) {
/* 268 */       this.e = this.d.createTransformedShape(clip);
/*     */     } else {
/* 270 */       this.e = null;
/*     */     } 
/* 272 */     b();
/*     */   }
/*     */   
/*     */   public void setClip(int x, int y, int width, int height) {
/* 276 */     setClip(new Rectangle(x, y, width, height));
/*     */   }
/*     */   
/*     */   public Shape getClip() {
/*     */     try {
/* 281 */       return this.d.createInverse().createTransformedShape(this.e);
/* 282 */     } catch (NoninvertibleTransformException e) {
/* 283 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void draw(Shape shape) {
/* 288 */     Stroke stroke = getStroke();
/* 289 */     if (stroke instanceof BasicStroke) {
/* 290 */       this.a.c(shape);
/*     */     } else {
/* 292 */       this.a.b(stroke.createStrokedShape(shape));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fill(Shape shape) {
/* 297 */     this.a.b(shape);
/*     */   }
/*     */   
/*     */   public FontRenderContext getFontRenderContext() {
/* 301 */     Object antialiasingHint = this.k.get(RenderingHints.KEY_TEXT_ANTIALIASING);
/* 302 */     boolean isAntialiased = true;
/* 303 */     if (antialiasingHint != RenderingHints.VALUE_TEXT_ANTIALIAS_ON && antialiasingHint != RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT)
/*     */     {
/* 305 */       if (antialiasingHint != RenderingHints.VALUE_TEXT_ANTIALIAS_OFF) {
/* 306 */         antialiasingHint = this.k.get(RenderingHints.KEY_ANTIALIASING);
/* 307 */         if (antialiasingHint != RenderingHints.VALUE_ANTIALIAS_ON && antialiasingHint != RenderingHints.VALUE_ANTIALIAS_DEFAULT)
/*     */         {
/* 309 */           if (antialiasingHint == RenderingHints.VALUE_ANTIALIAS_OFF) {
/* 310 */             isAntialiased = false;
/*     */           }
/*     */         }
/*     */       } else {
/* 314 */         isAntialiased = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 319 */     boolean useFractionalMetrics = true;
/* 320 */     if (this.k.get(RenderingHints.KEY_FRACTIONALMETRICS) == RenderingHints.VALUE_FRACTIONALMETRICS_OFF) {
/* 321 */       useFractionalMetrics = false;
/*     */     }
/*     */     
/* 324 */     FontRenderContext frc = new FontRenderContext(c, isAntialiased, useFractionalMetrics);
/* 325 */     return frc;
/*     */   }
/*     */   
/*     */   public Font getFont() {
/* 329 */     return this.l;
/*     */   }
/*     */   
/*     */   public void setFont(Font font) {
/* 333 */     this.l = font;
/*     */   }
/*     */   
/*     */   class a
/*     */     extends FontMetrics {
/*     */     private static final long b = 1L;
/*     */     
/*     */     a(a this$0, Font font) {
/* 341 */       super(font);
/*     */     }
/*     */     
/*     */     public int stringWidth(String text) {
/* 345 */       c lgh = new c();
/* 346 */       a.a(this.a, (e)lgh, (new AttributedString(text, (Map)getFont().getAttributes())).getIterator());
/* 347 */       return (int)lgh.d();
/*     */     }
/*     */   }
/*     */   
/*     */   public FontMetrics getFontMetrics(Font font) {
/* 352 */     return new a(this, font);
/*     */   }
/*     */   
/*     */   public void drawString(String text, int x, int y) {
/* 356 */     drawString(text, x, y);
/*     */   }
/*     */   
/*     */   public void drawString(String text, float x, float y) {
/* 360 */     drawString((new AttributedString(text, (Map)getFont().getAttributes())).getIterator(), x, y);
/*     */   }
/*     */   
/*     */   public void drawString(AttributedCharacterIterator aci, int x, int y) {
/* 364 */     drawString(aci, x, y);
/*     */   }
/*     */   
/*     */   private void a(e gh, AttributedCharacterIterator aci) {
/* 368 */     j tlf = new j(this.a, b.a("ja"), gh);
/* 369 */     Map<TextAttribute, ?> atts = this.l.getAttributes();
/* 370 */     tlf.a(c.a(this.l.getFamily()));
/* 371 */     int style = this.l.getStyle();
/* 372 */     tlf.a(jp.cssj.sakae.c.d.c.a.a((Float)atts.get(TextAttribute.WEIGHT), ((style & 0x1) != 0) ? 600 : 400));
/*     */     
/* 374 */     tlf.a(this.l.getSize2D());
/* 375 */     tlf.a(jp.cssj.sakae.c.d.c.a.a((Float)atts.get(TextAttribute.POSTURE), ((style & 0x2) != 0) ? 2 : 1));
/*     */     
/* 377 */     tlf.a(aci);
/* 378 */     tlf.a();
/*     */   }
/*     */   
/*     */   public void drawString(AttributedCharacterIterator aci, float x, float y) {
/* 382 */     this.a.d();
/* 383 */     this.a.a(AffineTransform.getTranslateInstance(x, y));
/*     */     
/* 385 */     c lgh = new c();
/* 386 */     lgh.a(this.a);
/* 387 */     a((e)lgh, aci);
/*     */     
/* 389 */     this.a.e();
/*     */   }
/*     */   
/*     */   public void drawGlyphVector(GlyphVector gv, float x, float y) {
/* 393 */     Shape glyphOutline = gv.getOutline(x, y);
/* 394 */     this.a.b(glyphOutline);
/*     */   }
/*     */   
/*     */   protected void a(BufferedImage image, AffineTransform at) {
/* 398 */     this.a.d();
/* 399 */     this.a.a(at);
/* 400 */     this.a.a((b)new b(image));
/* 401 */     this.a.e();
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawRenderedImage(RenderedImage image, AffineTransform at) {
/*     */     BufferedImage bimage;
/* 407 */     if (image instanceof BufferedImage) {
/* 408 */       bimage = (BufferedImage)image;
/*     */     } else {
/* 410 */       Hashtable<?, ?> props = new Hashtable<>();
/* 411 */       bimage = new BufferedImage(image.getColorModel(), image.copyData(null), true, props);
/*     */     } 
/* 413 */     a(bimage, at);
/*     */   }
/*     */   
/*     */   public void drawRenderableImage(RenderableImage image, AffineTransform at) {
/* 417 */     drawRenderedImage(image.createDefaultRendering(), at);
/*     */   }
/*     */   
/*     */   public void drawImage(BufferedImage image, BufferedImageOp op, int x, int y) {
/* 421 */     AffineTransform at = AffineTransform.getTranslateInstance(x, y);
/* 422 */     if (op != null) {
/* 423 */       image = op.createCompatibleDestImage(image, image.getColorModel());
/*     */     }
/* 425 */     drawRenderedImage(image, at);
/*     */   }
/*     */   
/*     */   public boolean drawImage(Image image, AffineTransform at, ImageObserver obs) {
/* 429 */     if (image instanceof RenderedImage) {
/* 430 */       drawRenderedImage((RenderedImage)image, at);
/* 431 */     } else if (image instanceof RenderableImage) {
/* 432 */       drawRenderableImage((RenderableImage)image, at);
/*     */     } else {
/* 434 */       BufferedImage bimage = new BufferedImage(image.getWidth(obs), image.getHeight(obs), 2);
/*     */       
/* 436 */       bimage.getGraphics().drawImage(image, 0, 0, null);
/* 437 */       a(bimage, at);
/*     */     } 
/* 439 */     return true;
/*     */   }
/*     */   
/*     */   public boolean drawImage(Image image, int x, int y, ImageObserver obs) {
/* 443 */     return drawImage(image, x, y, image.getWidth(null), image.getHeight(null), obs);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean drawImage(Image image, int x, int y, int width, int height, ImageObserver obs) {
/* 448 */     AffineTransform at = new AffineTransform(width / image.getWidth(null), 0.0D, 0.0D, height / image.getHeight(null), x, y);
/* 449 */     return drawImage(image, at, obs);
/*     */   }
/*     */   
/*     */   public boolean drawImage(Image image, int x, int y, Color color, ImageObserver obs) {
/* 453 */     return drawImage(image, x, y, image.getWidth(null), image.getHeight(null), obs);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean drawImage(Image image, int x, int y, int width, int height, Color color, ImageObserver obs) {
/* 458 */     Paint paint = getPaint();
/* 459 */     setPaint(color);
/* 460 */     fillRect(x, y, width, height);
/* 461 */     setPaint(paint);
/* 462 */     return drawImage(image, x, y, width, height, obs);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean drawImage(Image image, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver obs) {
/* 467 */     int w = dx2 - dx1, h = dy2 - dy1;
/* 468 */     BufferedImage bimage = new BufferedImage(w, h, 2);
/* 469 */     bimage.getGraphics().drawImage(image, 0, 0, w, h, sx1, sy1, sx2, sy2, null);
/* 470 */     return drawImage(bimage, dx1, dy1, obs);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean drawImage(Image image, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color color, ImageObserver obs) {
/* 475 */     if (color != null) {
/* 476 */       Paint paint = getPaint();
/* 477 */       setPaint(color);
/* 478 */       fillRect(dx1, dy1, dx2 - dx1, dy2 - dy1);
/* 479 */       setPaint(paint);
/*     */     } 
/* 481 */     return drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, obs);
/*     */   }
/*     */   
/*     */   public Graphics create() {
/*     */     try {
/* 486 */       a g = (a)clone();
/* 487 */       return g;
/* 488 */     } catch (CloneNotSupportedException e) {
/* 489 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setPaintMode() {
/* 494 */     setComposite(null);
/*     */   }
/*     */   
/*     */   public void setXORMode(Color color) {
/* 498 */     setComposite(AlphaComposite.Xor);
/*     */   }
/*     */   
/*     */   public Rectangle getClipBounds() {
/* 502 */     Shape clip = getClip();
/* 503 */     if (clip == null) {
/* 504 */       return null;
/*     */     }
/* 506 */     return clip.getBounds();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hit(Rectangle rect, Shape shape, boolean onStroke) {
/* 512 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void translate(int x, int y) {
/* 516 */     translate(x, y);
/*     */   }
/*     */   
/*     */   public void translate(double x, double y) {
/* 520 */     transform(AffineTransform.getTranslateInstance(x, y));
/*     */   }
/*     */   
/*     */   public void rotate(double theta) {
/* 524 */     transform(AffineTransform.getRotateInstance(theta));
/*     */   }
/*     */   
/*     */   public void rotate(double theta, double x, double y) {
/* 528 */     transform(AffineTransform.getRotateInstance(theta, x, y));
/*     */   }
/*     */   
/*     */   public void scale(double sx, double sy) {
/* 532 */     transform(AffineTransform.getScaleInstance(sx, sy));
/*     */   }
/*     */   
/*     */   public void shear(double shx, double shy) {
/* 536 */     transform(AffineTransform.getShearInstance(shx, shy));
/*     */   }
/*     */   
/*     */   public void copyArea(int x, int y, int width, int height, int dx, int dy) {
/* 540 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void drawLine(int x1, int y1, int x2, int y2) {
/* 544 */     this.a.b(new Line2D.Float(x1, y1, x2, y2));
/*     */   }
/*     */   
/*     */   public void drawRect(int x, int y, int width, int height) {
/* 548 */     draw(new Rectangle(x, y, width, height));
/*     */   }
/*     */   
/*     */   public void fillRect(int x, int y, int width, int height) {
/* 552 */     this.a.b(new Rectangle(x, y, width, height));
/*     */   }
/*     */   
/*     */   public void clearRect(int x, int y, int width, int height) {
/* 556 */     this.a.b(jp.cssj.sakae.b.c.a.a(getBackground()));
/* 557 */     this.a.b(new Rectangle(x, y, width, height));
/* 558 */     this.a.a(jp.cssj.sakae.b.c.a.a(getColor()));
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawRoundRect(int x, int y, int width, int height, int rx, int ry) {
/* 563 */     draw(new RoundRectangle2D.Float(x, y, width, height, rx, ry));
/*     */   }
/*     */   
/*     */   public void fillRoundRect(int x, int y, int width, int height, int rx, int ry) {
/* 567 */     this.a.b(new RoundRectangle2D.Float(x, y, width, height, rx, ry));
/*     */   }
/*     */   
/*     */   public void drawOval(int x, int y, int width, int height) {
/* 571 */     draw(new Ellipse2D.Float(x, y, width, height));
/*     */   }
/*     */   
/*     */   public void fillOval(int x, int y, int width, int height) {
/* 575 */     this.a.b(new Ellipse2D.Float(x, y, width, height));
/*     */   }
/*     */   
/*     */   public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
/* 579 */     draw(new Arc2D.Float(x, y, width, height, startAngle, arcAngle, 1));
/*     */   }
/*     */   
/*     */   public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
/* 583 */     this.a.b(new Arc2D.Float(x, y, width, height, startAngle, arcAngle, 2));
/*     */   }
/*     */   
/*     */   public void drawPolyline(int[] xpoints, int[] ypoints, int npoints) {
/* 587 */     if (npoints <= 0) {
/*     */       return;
/*     */     }
/* 590 */     GeneralPath path = new GeneralPath();
/* 591 */     path.moveTo(xpoints[0], ypoints[0]);
/* 592 */     for (int i = 0; i < npoints; i++) {
/* 593 */       path.lineTo(xpoints[i], ypoints[i]);
/*     */     }
/* 595 */     draw(path);
/*     */   }
/*     */   
/*     */   public void drawPolygon(int[] xpoints, int[] ypoints, int npoints) {
/* 599 */     draw(new Polygon(xpoints, ypoints, npoints));
/*     */   }
/*     */   
/*     */   public void fillPolygon(int[] xpoints, int[] ypoints, int npoints) {
/* 603 */     this.a.b(new Polygon(xpoints, ypoints, npoints));
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 607 */     if (this.a != null) {
/* 608 */       this.a = null;
/*     */     }
/*     */   }
/*     */   
/*     */   protected Object clone() throws CloneNotSupportedException {
/* 613 */     a clone = (a)super.clone();
/* 614 */     clone.d = new AffineTransform(clone.d);
/* 615 */     return clone;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/b/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */