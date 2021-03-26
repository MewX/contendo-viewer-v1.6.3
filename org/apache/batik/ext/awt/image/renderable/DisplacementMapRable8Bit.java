/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.ARGBChannel;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.rendered.AffineRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.DisplacementMapRed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DisplacementMapRable8Bit
/*     */   extends AbstractColorInterpolationRable
/*     */   implements DisplacementMapRable
/*     */ {
/*     */   private double scale;
/*     */   private ARGBChannel xChannelSelector;
/*     */   private ARGBChannel yChannelSelector;
/*     */   
/*     */   public DisplacementMapRable8Bit(List sources, double scale, ARGBChannel xChannelSelector, ARGBChannel yChannelSelector) {
/*  67 */     setSources(sources);
/*  68 */     setScale(scale);
/*  69 */     setXChannelSelector(xChannelSelector);
/*  70 */     setYChannelSelector(yChannelSelector);
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/*  74 */     return ((Filter)getSources().get(0)).getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScale(double scale) {
/*  82 */     touch();
/*  83 */     this.scale = scale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getScale() {
/*  90 */     return this.scale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSources(List sources) {
/*  97 */     if (sources.size() != 2) {
/*  98 */       throw new IllegalArgumentException();
/*     */     }
/* 100 */     init(sources, (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXChannelSelector(ARGBChannel xChannelSelector) {
/* 110 */     if (xChannelSelector == null) {
/* 111 */       throw new IllegalArgumentException();
/*     */     }
/* 113 */     touch();
/* 114 */     this.xChannelSelector = xChannelSelector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ARGBChannel getXChannelSelector() {
/* 121 */     return this.xChannelSelector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setYChannelSelector(ARGBChannel yChannelSelector) {
/* 131 */     if (yChannelSelector == null) {
/* 132 */       throw new IllegalArgumentException();
/*     */     }
/* 134 */     touch();
/* 135 */     this.yChannelSelector = yChannelSelector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ARGBChannel getYChannelSelector() {
/* 142 */     return this.yChannelSelector;
/*     */   }
/*     */   
/*     */   public RenderedImage createRendering(RenderContext rc) {
/*     */     AffineRed affineRed;
/* 147 */     Filter displaced = getSources().get(0);
/*     */     
/* 149 */     Filter map = getSources().get(1);
/*     */     
/* 151 */     RenderingHints rh = rc.getRenderingHints();
/* 152 */     if (rh == null) rh = new RenderingHints(null);
/*     */ 
/*     */     
/* 155 */     AffineTransform at = rc.getTransform();
/*     */ 
/*     */ 
/*     */     
/* 159 */     double sx = at.getScaleX();
/* 160 */     double sy = at.getScaleY();
/*     */     
/* 162 */     double shx = at.getShearX();
/* 163 */     double shy = at.getShearY();
/*     */     
/* 165 */     double tx = at.getTranslateX();
/* 166 */     double ty = at.getTranslateY();
/*     */ 
/*     */     
/* 169 */     double atScaleX = Math.sqrt(sx * sx + shy * shy);
/* 170 */     double atScaleY = Math.sqrt(sy * sy + shx * shx);
/*     */ 
/*     */ 
/*     */     
/* 174 */     float scaleX = (float)(this.scale * atScaleX);
/* 175 */     float scaleY = (float)(this.scale * atScaleY);
/*     */ 
/*     */ 
/*     */     
/* 179 */     if (scaleX == 0.0F && scaleY == 0.0F) {
/* 180 */       return displaced.createRendering(rc);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 186 */     AffineTransform srcAt = AffineTransform.getScaleInstance(atScaleX, atScaleY);
/*     */ 
/*     */     
/* 189 */     Shape origAOI = rc.getAreaOfInterest();
/* 190 */     if (origAOI == null) {
/* 191 */       origAOI = getBounds2D();
/*     */     }
/* 193 */     Rectangle2D aoiR = origAOI.getBounds2D();
/*     */     
/* 195 */     RenderContext srcRc = new RenderContext(srcAt, aoiR, rh);
/* 196 */     RenderedImage mapRed = map.createRendering(srcRc);
/*     */     
/* 198 */     if (mapRed == null) return null;
/*     */ 
/*     */ 
/*     */     
/* 202 */     aoiR = new Rectangle2D.Double(aoiR.getX() - this.scale / 2.0D, aoiR.getY() - this.scale / 2.0D, aoiR.getWidth() + this.scale, aoiR.getHeight() + this.scale);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 207 */     Rectangle2D displacedRect = displaced.getBounds2D();
/* 208 */     if (!aoiR.intersects(displacedRect)) {
/* 209 */       return null;
/*     */     }
/* 211 */     aoiR = aoiR.createIntersection(displacedRect);
/* 212 */     srcRc = new RenderContext(srcAt, aoiR, rh);
/* 213 */     RenderedImage displacedRed = displaced.createRendering(srcRc);
/*     */     
/* 215 */     if (displacedRed == null) return null;
/*     */     
/* 217 */     CachableRed cachableRed = convertSourceCS(mapRed);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 223 */     DisplacementMapRed displacementMapRed = new DisplacementMapRed(GraphicsUtil.wrap(displacedRed), GraphicsUtil.wrap((RenderedImage)cachableRed), this.xChannelSelector, this.yChannelSelector, scaleX, scaleY, rh);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 232 */     AffineTransform resAt = new AffineTransform(sx / atScaleX, shy / atScaleX, shx / atScaleY, sy / atScaleY, tx, ty);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 237 */     if (!resAt.isIdentity()) {
/* 238 */       affineRed = new AffineRed((CachableRed)displacementMapRed, resAt, rh);
/*     */     }
/* 240 */     return (RenderedImage)affineRed;
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
/* 257 */     return super.getDependencyRegion(srcIndex, outputRgn);
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
/* 275 */     return super.getDirtyRegion(srcIndex, inputRgn);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/DisplacementMapRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */