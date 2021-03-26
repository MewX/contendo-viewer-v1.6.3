/*     */ package org.apache.batik.ext.awt.g2d;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Font;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class GraphicContext
/*     */   implements Cloneable
/*     */ {
/*  58 */   protected AffineTransform defaultTransform = new AffineTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   protected AffineTransform transform = new AffineTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   protected List transformStack = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean transformStackValid = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   protected Paint paint = Color.black;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  92 */   protected Stroke stroke = new BasicStroke();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   protected Composite composite = AlphaComposite.SrcOver;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   protected Shape clip = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   protected RenderingHints hints = new RenderingHints(null);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   protected Font font = new Font("sanserif", 0, 12);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   protected Color background = new Color(0, 0, 0, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   protected Color foreground = Color.black;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicContext() {
/* 129 */     this.hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicContext(AffineTransform defaultDeviceTransform) {
/* 137 */     this();
/* 138 */     this.defaultTransform = new AffineTransform(defaultDeviceTransform);
/* 139 */     this.transform = new AffineTransform(this.defaultTransform);
/* 140 */     if (!this.defaultTransform.isIdentity()) {
/* 141 */       this.transformStack.add(TransformStackElement.createGeneralTransformElement(this.defaultTransform));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 148 */     GraphicContext copyGc = new GraphicContext(this.defaultTransform);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 158 */     copyGc.transform = new AffineTransform(this.transform);
/*     */ 
/*     */     
/* 161 */     copyGc.transformStack = new ArrayList(this.transformStack.size());
/* 162 */     for (int i = 0; i < this.transformStack.size(); i++) {
/* 163 */       TransformStackElement stackElement = this.transformStack.get(i);
/*     */       
/* 165 */       copyGc.transformStack.add(stackElement.clone());
/*     */     } 
/*     */ 
/*     */     
/* 169 */     copyGc.transformStackValid = this.transformStackValid;
/*     */ 
/*     */     
/* 172 */     copyGc.paint = this.paint;
/*     */ 
/*     */     
/* 175 */     copyGc.stroke = this.stroke;
/*     */ 
/*     */     
/* 178 */     copyGc.composite = this.composite;
/*     */ 
/*     */     
/* 181 */     if (this.clip != null) {
/* 182 */       copyGc.clip = new GeneralPath(this.clip);
/*     */     } else {
/* 184 */       copyGc.clip = null;
/*     */     } 
/*     */     
/* 187 */     copyGc.hints = (RenderingHints)this.hints.clone();
/*     */ 
/*     */     
/* 190 */     copyGc.font = this.font;
/*     */ 
/*     */     
/* 193 */     copyGc.background = this.background;
/* 194 */     copyGc.foreground = this.foreground;
/*     */     
/* 196 */     return copyGc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getColor() {
/* 206 */     return this.foreground;
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
/*     */   public void setColor(Color c) {
/* 218 */     if (c == null) {
/*     */       return;
/*     */     }
/* 221 */     if (this.paint != c) {
/* 222 */       setPaint(c);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Font getFont() {
/* 232 */     return this.font;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 243 */     if (font != null) {
/* 244 */       this.font = font;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle getClipBounds() {
/* 265 */     Shape c = getClip();
/* 266 */     if (c == null) {
/* 267 */       return null;
/*     */     }
/* 269 */     return c.getBounds();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clipRect(int x, int y, int width, int height) {
/* 293 */     clip(new Rectangle(x, y, width, height));
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
/*     */   public void setClip(int x, int y, int width, int height) {
/* 312 */     setClip(new Rectangle(x, y, width, height));
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
/*     */ 
/*     */   
/*     */   public Shape getClip() {
/*     */     try {
/* 334 */       return this.transform.createInverse().createTransformedShape(this.clip);
/* 335 */     } catch (NoninvertibleTransformException e) {
/* 336 */       return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClip(Shape clip) {
/* 358 */     if (clip != null) {
/* 359 */       this.clip = this.transform.createTransformedShape(clip);
/*     */     } else {
/* 361 */       this.clip = null;
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
/*     */   public void setComposite(Composite comp) {
/* 390 */     this.composite = comp;
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
/*     */   public void setPaint(Paint paint) {
/* 407 */     if (paint == null) {
/*     */       return;
/*     */     }
/* 410 */     this.paint = paint;
/* 411 */     if (paint instanceof Color) {
/* 412 */       this.foreground = (Color)paint;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStroke(Stroke s) {
/* 423 */     this.stroke = s;
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
/*     */   public void setRenderingHint(RenderingHints.Key hintKey, Object hintValue) {
/* 438 */     this.hints.put(hintKey, hintValue);
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
/*     */   public Object getRenderingHint(RenderingHints.Key hintKey) {
/* 455 */     return this.hints.get(hintKey);
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
/*     */   public void setRenderingHints(Map<RenderingHints.Key, ?> hints) {
/* 473 */     this.hints = new RenderingHints(hints);
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
/*     */   public void addRenderingHints(Map<?, ?> hints) {
/* 492 */     this.hints.putAll(hints);
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
/*     */   public RenderingHints getRenderingHints() {
/* 509 */     return this.hints;
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
/*     */   public void translate(int x, int y) {
/* 524 */     if (x != 0 || y != 0) {
/* 525 */       this.transform.translate(x, y);
/* 526 */       this.transformStack.add(TransformStackElement.createTranslateElement(x, y));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(double tx, double ty) {
/* 548 */     this.transform.translate(tx, ty);
/* 549 */     this.transformStack.add(TransformStackElement.createTranslateElement(tx, ty));
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
/*     */   
/*     */   public void rotate(double theta) {
/* 569 */     this.transform.rotate(theta);
/* 570 */     this.transformStack.add(TransformStackElement.createRotateElement(theta));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rotate(double theta, double x, double y) {
/* 593 */     this.transform.rotate(theta, x, y);
/* 594 */     this.transformStack.add(TransformStackElement.createTranslateElement(x, y));
/* 595 */     this.transformStack.add(TransformStackElement.createRotateElement(theta));
/* 596 */     this.transformStack.add(TransformStackElement.createTranslateElement(-x, -y));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void scale(double sx, double sy) {
/* 619 */     this.transform.scale(sx, sy);
/* 620 */     this.transformStack.add(TransformStackElement.createScaleElement(sx, sy));
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void shear(double shx, double shy) {
/* 642 */     this.transform.shear(shx, shy);
/* 643 */     this.transformStack.add(TransformStackElement.createShearElement(shx, shy));
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
/*     */ 
/*     */   
/*     */   public void transform(AffineTransform Tx) {
/* 664 */     this.transform.concatenate(Tx);
/* 665 */     this.transformStack.add(TransformStackElement.createGeneralTransformElement(Tx));
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
/*     */   public void setTransform(AffineTransform Tx) {
/* 677 */     this.transform = new AffineTransform(Tx);
/* 678 */     invalidateTransformStack();
/* 679 */     if (!Tx.isIdentity()) {
/* 680 */       this.transformStack.add(TransformStackElement.createGeneralTransformElement(Tx));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void validateTransformStack() {
/* 690 */     this.transformStackValid = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTransformStackValid() {
/* 697 */     return this.transformStackValid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformStackElement[] getTransformStack() {
/* 705 */     TransformStackElement[] stack = new TransformStackElement[this.transformStack.size()];
/* 706 */     this.transformStack.toArray((Object[])stack);
/* 707 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void invalidateTransformStack() {
/* 717 */     this.transformStack.clear();
/* 718 */     this.transformStackValid = false;
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
/*     */   public AffineTransform getTransform() {
/* 730 */     return new AffineTransform(this.transform);
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
/*     */   public Paint getPaint() {
/* 742 */     return this.paint;
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
/*     */   public Composite getComposite() {
/* 754 */     return this.composite;
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
/*     */   
/*     */   public void setBackground(Color color) {
/* 774 */     if (color == null) {
/*     */       return;
/*     */     }
/* 777 */     this.background = color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/* 788 */     return this.background;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Stroke getStroke() {
/* 799 */     return this.stroke;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clip(Shape s) {
/* 823 */     if (s != null) {
/* 824 */       s = this.transform.createTransformedShape(s);
/*     */     }
/* 826 */     if (this.clip != null) {
/* 827 */       Area newClip = new Area(this.clip);
/* 828 */       newClip.intersect(new Area(s));
/* 829 */       this.clip = new GeneralPath(newClip);
/*     */     } else {
/* 831 */       this.clip = s;
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
/*     */   public FontRenderContext getFontRenderContext() {
/* 860 */     Object antialiasingHint = this.hints.get(RenderingHints.KEY_TEXT_ANTIALIASING);
/* 861 */     boolean isAntialiased = true;
/* 862 */     if (antialiasingHint != RenderingHints.VALUE_TEXT_ANTIALIAS_ON && antialiasingHint != RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 867 */       if (antialiasingHint != RenderingHints.VALUE_TEXT_ANTIALIAS_OFF) {
/* 868 */         antialiasingHint = this.hints.get(RenderingHints.KEY_ANTIALIASING);
/*     */ 
/*     */         
/* 871 */         if (antialiasingHint != RenderingHints.VALUE_ANTIALIAS_ON && antialiasingHint != RenderingHints.VALUE_ANTIALIAS_DEFAULT)
/*     */         {
/*     */ 
/*     */           
/* 875 */           if (antialiasingHint == RenderingHints.VALUE_ANTIALIAS_OFF) {
/* 876 */             isAntialiased = false;
/*     */           }
/*     */         }
/*     */       } else {
/* 880 */         isAntialiased = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 887 */     boolean useFractionalMetrics = true;
/* 888 */     if (this.hints.get(RenderingHints.KEY_FRACTIONALMETRICS) == RenderingHints.VALUE_FRACTIONALMETRICS_OFF)
/*     */     {
/* 890 */       useFractionalMetrics = false;
/*     */     }
/* 892 */     FontRenderContext frc = new FontRenderContext(this.defaultTransform, isAntialiased, useFractionalMetrics);
/*     */ 
/*     */     
/* 895 */     return frc;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/g2d/GraphicContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */