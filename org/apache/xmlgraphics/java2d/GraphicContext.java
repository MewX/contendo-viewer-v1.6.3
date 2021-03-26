/*     */ package org.apache.xmlgraphics.java2d;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GraphicContext
/*     */   implements Cloneable
/*     */ {
/*  63 */   protected AffineTransform defaultTransform = new AffineTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   protected AffineTransform transform = new AffineTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   protected List transformStack = new ArrayList();
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
/*  92 */   protected Paint paint = Color.black;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  97 */   protected Stroke stroke = new BasicStroke();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   protected Composite composite = AlphaComposite.SrcOver;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Shape clip;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   protected RenderingHints hints = new RenderingHints(null);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   protected Font font = new Font("sanserif", 0, 12);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   protected Color background = new Color(0, 0, 0, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   protected Color foreground = Color.black;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicContext() {
/* 134 */     this.hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_DEFAULT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicContext(AffineTransform defaultDeviceTransform) {
/* 142 */     this();
/* 143 */     this.defaultTransform = new AffineTransform(defaultDeviceTransform);
/* 144 */     this.transform = new AffineTransform(this.defaultTransform);
/* 145 */     if (!this.defaultTransform.isIdentity()) {
/* 146 */       this.transformStack.add(TransformStackElement.createGeneralTransformElement(this.defaultTransform));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GraphicContext(GraphicContext template) {
/* 155 */     this(template.defaultTransform);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     this.transform = new AffineTransform(template.transform);
/*     */ 
/*     */     
/* 167 */     this.transformStack = new ArrayList(template.transformStack.size());
/* 168 */     for (int i = 0; i < template.transformStack.size(); i++) {
/* 169 */       TransformStackElement stackElement = template.transformStack.get(i);
/*     */       
/* 171 */       this.transformStack.add(stackElement.clone());
/*     */     } 
/*     */ 
/*     */     
/* 175 */     this.transformStackValid = template.transformStackValid;
/*     */ 
/*     */     
/* 178 */     this.paint = template.paint;
/*     */ 
/*     */     
/* 181 */     this.stroke = template.stroke;
/*     */ 
/*     */     
/* 184 */     this.composite = template.composite;
/*     */ 
/*     */     
/* 187 */     if (template.clip != null) {
/* 188 */       this.clip = new GeneralPath(template.clip);
/*     */     } else {
/* 190 */       this.clip = null;
/*     */     } 
/*     */ 
/*     */     
/* 194 */     this.hints = (RenderingHints)template.hints.clone();
/*     */ 
/*     */     
/* 197 */     this.font = template.font;
/*     */ 
/*     */     
/* 200 */     this.background = template.background;
/* 201 */     this.foreground = template.foreground;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object clone() {
/* 208 */     return new GraphicContext(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getColor() {
/* 218 */     return this.foreground;
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
/* 230 */     if (c == null) {
/*     */       return;
/*     */     }
/*     */     
/* 234 */     if (this.paint != c) {
/* 235 */       setPaint(c);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Font getFont() {
/* 246 */     return this.font;
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
/* 257 */     if (font != null) {
/* 258 */       this.font = font;
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
/*     */   public Rectangle getClipBounds() {
/* 280 */     Shape c = getClip();
/* 281 */     if (c == null) {
/* 282 */       return null;
/*     */     }
/* 284 */     return c.getBounds();
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
/*     */   public void clipRect(int x, int y, int width, int height) {
/* 309 */     clip(new Rectangle(x, y, width, height));
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
/* 328 */     setClip(new Rectangle(x, y, width, height));
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
/* 350 */       return this.transform.createInverse().createTransformedShape(this.clip);
/* 351 */     } catch (NoninvertibleTransformException e) {
/* 352 */       return null;
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
/* 374 */     if (clip != null) {
/* 375 */       this.clip = this.transform.createTransformedShape(clip);
/*     */     } else {
/* 377 */       this.clip = null;
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
/*     */   
/*     */   public void setComposite(Composite comp) {
/* 407 */     this.composite = comp;
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
/* 424 */     if (paint == null) {
/*     */       return;
/*     */     }
/*     */     
/* 428 */     this.paint = paint;
/* 429 */     if (paint instanceof Color) {
/* 430 */       this.foreground = (Color)paint;
/*     */     } else {
/*     */       
/* 433 */       this.foreground = Color.black;
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
/*     */   public void setStroke(Stroke s) {
/* 445 */     this.stroke = s;
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
/* 460 */     this.hints.put(hintKey, hintValue);
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
/* 477 */     return this.hints.get(hintKey);
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
/* 495 */     this.hints = new RenderingHints(hints);
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
/* 514 */     this.hints.putAll(hints);
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
/* 531 */     return this.hints;
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
/* 546 */     if (x != 0 || y != 0) {
/* 547 */       this.transform.translate(x, y);
/* 548 */       this.transformStack.add(TransformStackElement.createTranslateElement(x, y));
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
/* 570 */     this.transform.translate(tx, ty);
/* 571 */     this.transformStack.add(TransformStackElement.createTranslateElement(tx, ty));
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
/* 591 */     this.transform.rotate(theta);
/* 592 */     this.transformStack.add(TransformStackElement.createRotateElement(theta));
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
/* 615 */     this.transform.rotate(theta, x, y);
/* 616 */     this.transformStack.add(TransformStackElement.createTranslateElement(x, y));
/* 617 */     this.transformStack.add(TransformStackElement.createRotateElement(theta));
/* 618 */     this.transformStack.add(TransformStackElement.createTranslateElement(-x, -y));
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
/* 641 */     this.transform.scale(sx, sy);
/* 642 */     this.transformStack.add(TransformStackElement.createScaleElement(sx, sy));
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
/* 664 */     this.transform.shear(shx, shy);
/* 665 */     this.transformStack.add(TransformStackElement.createShearElement(shx, shy));
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
/*     */   public void transform(AffineTransform tx) {
/* 686 */     this.transform.concatenate(tx);
/* 687 */     this.transformStack.add(TransformStackElement.createGeneralTransformElement(tx));
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
/*     */   public void setTransform(AffineTransform tx) {
/* 699 */     this.transform = new AffineTransform(tx);
/* 700 */     invalidateTransformStack();
/* 701 */     if (!tx.isIdentity()) {
/* 702 */       this.transformStack.add(TransformStackElement.createGeneralTransformElement(tx));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void validateTransformStack() {
/* 713 */     this.transformStackValid = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTransformStackValid() {
/* 721 */     return this.transformStackValid;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TransformStackElement[] getTransformStack() {
/* 729 */     TransformStackElement[] stack = new TransformStackElement[this.transformStack.size()];
/* 730 */     this.transformStack.toArray((Object[])stack);
/* 731 */     return stack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void invalidateTransformStack() {
/* 741 */     this.transformStack.clear();
/* 742 */     this.transformStackValid = false;
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
/* 754 */     return new AffineTransform(this.transform);
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
/* 766 */     return this.paint;
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
/* 778 */     return this.composite;
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
/* 798 */     if (color == null) {
/*     */       return;
/*     */     }
/*     */     
/* 802 */     this.background = color;
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
/* 813 */     return this.background;
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
/* 824 */     return this.stroke;
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
/* 848 */     if (s != null) {
/* 849 */       s = this.transform.createTransformedShape(s);
/*     */     }
/*     */     
/* 852 */     if (this.clip != null) {
/* 853 */       Area newClip = new Area(this.clip);
/* 854 */       newClip.intersect(new Area(s));
/* 855 */       this.clip = new GeneralPath(newClip);
/*     */     } else {
/* 857 */       this.clip = s;
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
/* 886 */     Object antialiasingHint = this.hints.get(RenderingHints.KEY_TEXT_ANTIALIASING);
/* 887 */     boolean isAntialiased = true;
/* 888 */     if (antialiasingHint != RenderingHints.VALUE_TEXT_ANTIALIAS_ON && antialiasingHint != RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 893 */       if (antialiasingHint != RenderingHints.VALUE_TEXT_ANTIALIAS_OFF) {
/* 894 */         antialiasingHint = this.hints.get(RenderingHints.KEY_ANTIALIASING);
/*     */ 
/*     */         
/* 897 */         if (antialiasingHint != RenderingHints.VALUE_ANTIALIAS_ON && antialiasingHint != RenderingHints.VALUE_ANTIALIAS_DEFAULT)
/*     */         {
/*     */ 
/*     */           
/* 901 */           if (antialiasingHint == RenderingHints.VALUE_ANTIALIAS_OFF) {
/* 902 */             isAntialiased = false;
/*     */           }
/*     */         }
/*     */       } else {
/* 906 */         isAntialiased = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 914 */     boolean useFractionalMetrics = true;
/* 915 */     if (this.hints.get(RenderingHints.KEY_FRACTIONALMETRICS) == RenderingHints.VALUE_FRACTIONALMETRICS_OFF)
/*     */     {
/* 917 */       useFractionalMetrics = false;
/*     */     }
/*     */     
/* 920 */     FontRenderContext frc = new FontRenderContext(this.defaultTransform, isAntialiased, useFractionalMetrics);
/*     */ 
/*     */     
/* 923 */     return frc;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/java2d/GraphicContext.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */