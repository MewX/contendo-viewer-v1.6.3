/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.rendered.AffineRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.GaussianBlurRed8Bit;
/*     */ import org.apache.batik.ext.awt.image.rendered.PadRed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GaussianBlurRable8Bit
/*     */   extends AbstractColorInterpolationRable
/*     */   implements GaussianBlurRable
/*     */ {
/*     */   private double stdDeviationX;
/*     */   private double stdDeviationY;
/*     */   
/*     */   public GaussianBlurRable8Bit(Filter src, double stdevX, double stdevY) {
/*  58 */     super(src, (Map)null);
/*  59 */     setStdDeviationX(stdevX);
/*  60 */     setStdDeviationY(stdevY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStdDeviationX(double stdDeviationX) {
/*  68 */     if (stdDeviationX < 0.0D) {
/*  69 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  72 */     touch();
/*  73 */     this.stdDeviationX = stdDeviationX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStdDeviationY(double stdDeviationY) {
/*  81 */     if (stdDeviationY < 0.0D) {
/*  82 */       throw new IllegalArgumentException();
/*     */     }
/*  84 */     touch();
/*  85 */     this.stdDeviationY = stdDeviationY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getStdDeviationX() {
/*  92 */     return this.stdDeviationX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getStdDeviationY() {
/*  99 */     return this.stdDeviationY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSource(Filter src) {
/* 106 */     init(src, (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   static final double DSQRT2PI = Math.sqrt(6.283185307179586D) * 3.0D / 4.0D;
/*     */   
/*     */   public static final double eps = 1.0E-4D;
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 118 */     Rectangle2D src = getSource().getBounds2D();
/* 119 */     float dX = (float)(this.stdDeviationX * DSQRT2PI);
/* 120 */     float dY = (float)(this.stdDeviationY * DSQRT2PI);
/* 121 */     float radX = 3.0F * dX / 2.0F;
/* 122 */     float radY = 3.0F * dY / 2.0F;
/* 123 */     return new Rectangle2D.Float((float)(src.getMinX() - radX), (float)(src.getMinY() - radY), (float)(src.getWidth() + (2.0F * radX)), (float)(src.getHeight() + (2.0F * radY)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getSource() {
/* 134 */     return getSources().get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean eps_eq(double f1, double f2) {
/* 140 */     return (f1 >= f2 - 1.0E-4D && f1 <= f2 + 1.0E-4D);
/*     */   }
/*     */   
/* 143 */   public static boolean eps_abs_eq(double f1, double f2) { if (f1 < 0.0D) f1 = -f1; 
/* 144 */     if (f2 < 0.0D) f2 = -f2; 
/* 145 */     return eps_eq(f1, f2); } public RenderedImage createRendering(RenderContext rc) { AffineTransform srcAt, resAt;
/*     */     int outsetX, outsetY;
/*     */     Rectangle2D rectangle2D;
/*     */     PadRed padRed;
/*     */     AffineRed affineRed;
/* 150 */     RenderingHints rh = rc.getRenderingHints();
/* 151 */     if (rh == null) rh = new RenderingHints(null);
/*     */ 
/*     */     
/* 154 */     AffineTransform at = rc.getTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     double sx = at.getScaleX();
/* 161 */     double sy = at.getScaleY();
/*     */     
/* 163 */     double shx = at.getShearX();
/* 164 */     double shy = at.getShearY();
/*     */     
/* 166 */     double tx = at.getTranslateX();
/* 167 */     double ty = at.getTranslateY();
/*     */ 
/*     */     
/* 170 */     double scaleX = Math.sqrt(sx * sx + shy * shy);
/* 171 */     double scaleY = Math.sqrt(sy * sy + shx * shx);
/*     */     
/* 173 */     double sdx = this.stdDeviationX * scaleX;
/* 174 */     double sdy = this.stdDeviationY * scaleY;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     if (sdx < 10.0D && sdy < 10.0D && eps_eq(sdx, sdy) && eps_abs_eq(sx / scaleX, sy / scaleY)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 200 */       srcAt = at;
/* 201 */       resAt = null;
/* 202 */       outsetX = 0;
/* 203 */       outsetY = 0;
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 210 */       if (sdx > 10.0D) {
/* 211 */         scaleX = scaleX * 10.0D / sdx;
/* 212 */         sdx = 10.0D;
/*     */       } 
/* 214 */       if (sdy > 10.0D) {
/* 215 */         scaleY = scaleY * 10.0D / sdy;
/* 216 */         sdy = 10.0D;
/*     */       } 
/*     */ 
/*     */       
/* 220 */       srcAt = AffineTransform.getScaleInstance(scaleX, scaleY);
/*     */ 
/*     */ 
/*     */       
/* 224 */       resAt = new AffineTransform(sx / scaleX, shy / scaleX, shx / scaleY, sy / scaleY, tx, ty);
/*     */ 
/*     */ 
/*     */       
/* 228 */       outsetX = 1;
/* 229 */       outsetY = 1;
/*     */     } 
/*     */ 
/*     */     
/* 233 */     Shape aoi = rc.getAreaOfInterest();
/* 234 */     if (aoi == null) {
/* 235 */       aoi = getBounds2D();
/*     */     }
/* 237 */     Shape devShape = srcAt.createTransformedShape(aoi);
/* 238 */     Rectangle devRect = devShape.getBounds();
/*     */     
/* 240 */     outsetX += GaussianBlurRed8Bit.surroundPixels(sdx, rh);
/* 241 */     outsetY += GaussianBlurRed8Bit.surroundPixels(sdy, rh);
/*     */     
/* 243 */     devRect.x -= outsetX;
/* 244 */     devRect.y -= outsetY;
/* 245 */     devRect.width += 2 * outsetX;
/* 246 */     devRect.height += 2 * outsetY;
/*     */ 
/*     */     
/*     */     try {
/* 250 */       AffineTransform invSrcAt = srcAt.createInverse();
/* 251 */       rectangle2D = invSrcAt.createTransformedShape(devRect).getBounds2D();
/* 252 */     } catch (NoninvertibleTransformException nte) {
/*     */       
/* 254 */       rectangle2D = aoi.getBounds2D();
/* 255 */       rectangle2D = new Rectangle2D.Double(rectangle2D.getX() - outsetX / scaleX, rectangle2D.getY() - outsetY / scaleY, rectangle2D.getWidth() + (2 * outsetX) / scaleX, rectangle2D.getHeight() + (2 * outsetY) / scaleY);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 262 */     RenderedImage ri = getSource().createRendering(new RenderContext(srcAt, rectangle2D, rh));
/* 263 */     if (ri == null) {
/* 264 */       return null;
/*     */     }
/* 266 */     CachableRed cr = convertSourceCS(ri);
/*     */ 
/*     */ 
/*     */     
/* 270 */     if (!devRect.equals(cr.getBounds()))
/*     */     {
/*     */       
/* 273 */       padRed = new PadRed(cr, devRect, PadMode.ZERO_PAD, rh);
/*     */     }
/*     */     
/* 276 */     GaussianBlurRed8Bit gaussianBlurRed8Bit = new GaussianBlurRed8Bit((CachableRed)padRed, sdx, sdy, rh);
/*     */     
/* 278 */     if (resAt != null && !resAt.isIdentity()) {
/* 279 */       affineRed = new AffineRed((CachableRed)gaussianBlurRed8Bit, resAt, rh);
/*     */     }
/* 281 */     return (RenderedImage)affineRed; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getDependencyRegion(int srcIndex, Rectangle2D outputRgn) {
/* 295 */     if (srcIndex != 0) {
/* 296 */       outputRgn = null;
/*     */     } else {
/*     */       
/* 299 */       float dX = (float)(this.stdDeviationX * DSQRT2PI);
/* 300 */       float dY = (float)(this.stdDeviationY * DSQRT2PI);
/* 301 */       float radX = 3.0F * dX / 2.0F;
/* 302 */       float radY = 3.0F * dY / 2.0F;
/* 303 */       outputRgn = new Rectangle2D.Float((float)(outputRgn.getMinX() - radX), (float)(outputRgn.getMinY() - radY), (float)(outputRgn.getWidth() + (2.0F * radX)), (float)(outputRgn.getHeight() + (2.0F * radY)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 309 */       Rectangle2D bounds = getBounds2D();
/* 310 */       if (!outputRgn.intersects(bounds)) {
/* 311 */         return new Rectangle2D.Float();
/*     */       }
/* 313 */       outputRgn = outputRgn.createIntersection(bounds);
/*     */     } 
/*     */     
/* 316 */     return outputRgn;
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
/*     */   public Shape getDirtyRegion(int srcIndex, Rectangle2D inputRgn) {
/* 331 */     Rectangle2D dirtyRegion = null;
/* 332 */     if (srcIndex == 0) {
/* 333 */       float dX = (float)(this.stdDeviationX * DSQRT2PI);
/* 334 */       float dY = (float)(this.stdDeviationY * DSQRT2PI);
/* 335 */       float radX = 3.0F * dX / 2.0F;
/* 336 */       float radY = 3.0F * dY / 2.0F;
/* 337 */       inputRgn = new Rectangle2D.Float((float)(inputRgn.getMinX() - radX), (float)(inputRgn.getMinY() - radY), (float)(inputRgn.getWidth() + (2.0F * radX)), (float)(inputRgn.getHeight() + (2.0F * radY)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 343 */       Rectangle2D bounds = getBounds2D();
/* 344 */       if (!inputRgn.intersects(bounds)) {
/* 345 */         return new Rectangle2D.Float();
/*     */       }
/* 347 */       dirtyRegion = inputRgn.createIntersection(bounds);
/*     */     } 
/*     */     
/* 350 */     return dirtyRegion;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/GaussianBlurRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */