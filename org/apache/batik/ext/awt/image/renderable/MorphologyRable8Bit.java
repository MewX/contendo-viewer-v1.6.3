/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.rendered.AffineRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.BufferedImageCachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.MorphologyOp;
/*     */ import org.apache.batik.ext.awt.image.rendered.PadRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.RenderedImageCachableRed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MorphologyRable8Bit
/*     */   extends AbstractRable
/*     */   implements MorphologyRable
/*     */ {
/*     */   private double radiusX;
/*     */   private double radiusY;
/*     */   private boolean doDilation;
/*     */   
/*     */   public MorphologyRable8Bit(Filter src, double radiusX, double radiusY, boolean doDilation) {
/*  66 */     super(src, (Map)null);
/*  67 */     setRadiusX(radiusX);
/*  68 */     setRadiusY(radiusY);
/*  69 */     setDoDilation(doDilation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getSource() {
/*  76 */     return getSources().get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSource(Filter src) {
/*  84 */     init(src, (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/*  91 */     return getSource().getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRadiusX(double radiusX) {
/*  99 */     if (radiusX <= 0.0D) {
/* 100 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 103 */     touch();
/* 104 */     this.radiusX = radiusX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRadiusY(double radiusY) {
/* 112 */     if (radiusY <= 0.0D) {
/* 113 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 116 */     touch();
/* 117 */     this.radiusY = radiusY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoDilation(boolean doDilation) {
/* 126 */     touch();
/* 127 */     this.doDilation = doDilation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDoDilation() {
/* 134 */     return this.doDilation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getRadiusX() {
/* 141 */     return this.radiusX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getRadiusY() {
/* 148 */     return this.radiusY;
/*     */   }
/*     */   public RenderedImage createRendering(RenderContext rc) {
/*     */     AffineRed affineRed;
/*     */     BufferedImage destBI;
/* 153 */     RenderingHints rh = rc.getRenderingHints();
/* 154 */     if (rh == null) rh = new RenderingHints(null);
/*     */ 
/*     */     
/* 157 */     AffineTransform at = rc.getTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     double sx = at.getScaleX();
/* 163 */     double sy = at.getScaleY();
/*     */     
/* 165 */     double shx = at.getShearX();
/* 166 */     double shy = at.getShearY();
/*     */     
/* 168 */     double tx = at.getTranslateX();
/* 169 */     double ty = at.getTranslateY();
/*     */ 
/*     */     
/* 172 */     double scaleX = Math.sqrt(sx * sx + shy * shy);
/* 173 */     double scaleY = Math.sqrt(sy * sy + shx * shx);
/*     */ 
/*     */     
/* 176 */     AffineTransform srcAt = AffineTransform.getScaleInstance(scaleX, scaleY);
/*     */     
/* 178 */     int radX = (int)Math.round(this.radiusX * scaleX);
/* 179 */     int radY = (int)Math.round(this.radiusY * scaleY);
/*     */     
/* 181 */     MorphologyOp op = null;
/* 182 */     if (radX > 0 && radY > 0) {
/* 183 */       op = new MorphologyOp(radX, radY, this.doDilation);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 191 */     AffineTransform resAt = new AffineTransform(sx / scaleX, shy / scaleX, shx / scaleY, sy / scaleY, tx, ty);
/*     */ 
/*     */ 
/*     */     
/* 195 */     Shape aoi = rc.getAreaOfInterest();
/* 196 */     if (aoi == null) {
/* 197 */       aoi = getBounds2D();
/*     */     }
/*     */     
/* 200 */     Rectangle2D r = aoi.getBounds2D();
/* 201 */     r = new Rectangle2D.Double(r.getX() - radX / scaleX, r.getY() - radY / scaleY, r.getWidth() + (2 * radX) / scaleX, r.getHeight() + (2 * radY) / scaleY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 207 */     RenderedImage ri = getSource().createRendering(new RenderContext(srcAt, r, rh));
/* 208 */     if (ri == null) {
/* 209 */       return null;
/*     */     }
/*     */     
/* 212 */     RenderedImageCachableRed renderedImageCachableRed = new RenderedImageCachableRed(ri);
/*     */     
/* 214 */     Shape devShape = srcAt.createTransformedShape(aoi.getBounds2D());
/* 215 */     r = devShape.getBounds2D();
/* 216 */     r = new Rectangle2D.Double(r.getX() - radX, r.getY() - radY, r.getWidth() + (2 * radX), r.getHeight() + (2 * radY));
/*     */ 
/*     */ 
/*     */     
/* 220 */     PadRed padRed = new PadRed((CachableRed)renderedImageCachableRed, r.getBounds(), PadMode.ZERO_PAD, rh);
/*     */ 
/*     */ 
/*     */     
/* 224 */     ColorModel cm = ri.getColorModel();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 229 */     Raster rr = padRed.getData();
/* 230 */     Point pt = new Point(0, 0);
/* 231 */     WritableRaster wr = Raster.createWritableRaster(rr.getSampleModel(), rr.getDataBuffer(), pt);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 236 */     BufferedImage srcBI = new BufferedImage(cm, wr, cm.isAlphaPremultiplied(), null);
/*     */ 
/*     */     
/* 239 */     if (op != null) {
/* 240 */       destBI = op.filter(srcBI, null);
/*     */     } else {
/*     */       
/* 243 */       destBI = srcBI;
/*     */     } 
/*     */     
/* 246 */     int rrMinX = padRed.getMinX();
/* 247 */     int rrMinY = padRed.getMinY();
/*     */     
/* 249 */     BufferedImageCachableRed bufferedImageCachableRed = new BufferedImageCachableRed(destBI, rrMinX, rrMinY);
/*     */     
/* 251 */     if (!resAt.isIdentity()) {
/* 252 */       affineRed = new AffineRed((CachableRed)bufferedImageCachableRed, resAt, rh);
/*     */     }
/*     */ 
/*     */     
/* 256 */     return (RenderedImage)affineRed;
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
/*     */   public Shape getDependencyRegion(int srcIndex, Rectangle2D outputRgn) {
/* 273 */     return super.getDependencyRegion(srcIndex, outputRgn);
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
/*     */   public Shape getDirtyRegion(int srcIndex, Rectangle2D inputRgn) {
/* 291 */     return super.getDirtyRegion(srcIndex, inputRgn);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/MorphologyRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */