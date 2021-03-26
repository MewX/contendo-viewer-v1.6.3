/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.Light;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.rendered.AffineRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.BumpMap;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.PadRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.SpecularLightingRed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SpecularLightingRable8Bit
/*     */   extends AbstractColorInterpolationRable
/*     */   implements SpecularLightingRable
/*     */ {
/*     */   private double surfaceScale;
/*     */   private double ks;
/*     */   private double specularExponent;
/*     */   private Light light;
/*     */   private Rectangle2D litRegion;
/*  75 */   private float[] kernelUnitLength = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SpecularLightingRable8Bit(Filter src, Rectangle2D litRegion, Light light, double ks, double specularExponent, double surfaceScale, double[] kernelUnitLength) {
/*  84 */     super(src, (Map)null);
/*  85 */     setLight(light);
/*  86 */     setKs(ks);
/*  87 */     setSpecularExponent(specularExponent);
/*  88 */     setSurfaceScale(surfaceScale);
/*  89 */     setLitRegion(litRegion);
/*  90 */     setKernelUnitLength(kernelUnitLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getSource() {
/*  97 */     return getSources().get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSource(Filter src) {
/* 104 */     init(src, (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 111 */     return (Rectangle2D)this.litRegion.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getLitRegion() {
/* 118 */     return getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLitRegion(Rectangle2D litRegion) {
/* 125 */     touch();
/* 126 */     this.litRegion = litRegion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Light getLight() {
/* 133 */     return this.light;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLight(Light light) {
/* 140 */     touch();
/* 141 */     this.light = light;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getSurfaceScale() {
/* 148 */     return this.surfaceScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSurfaceScale(double surfaceScale) {
/* 155 */     touch();
/* 156 */     this.surfaceScale = surfaceScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getKs() {
/* 163 */     return this.ks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKs(double ks) {
/* 170 */     touch();
/* 171 */     this.ks = ks;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getSpecularExponent() {
/* 178 */     return this.specularExponent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpecularExponent(double specularExponent) {
/* 185 */     touch();
/* 186 */     this.specularExponent = specularExponent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getKernelUnitLength() {
/* 194 */     if (this.kernelUnitLength == null) {
/* 195 */       return null;
/*     */     }
/* 197 */     double[] ret = new double[2];
/* 198 */     ret[0] = this.kernelUnitLength[0];
/* 199 */     ret[1] = this.kernelUnitLength[1];
/* 200 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKernelUnitLength(double[] kernelUnitLength) {
/* 208 */     touch();
/* 209 */     if (kernelUnitLength == null) {
/* 210 */       this.kernelUnitLength = null;
/*     */       
/*     */       return;
/*     */     } 
/* 214 */     if (this.kernelUnitLength == null) {
/* 215 */       this.kernelUnitLength = new float[2];
/*     */     }
/* 217 */     this.kernelUnitLength[0] = (float)kernelUnitLength[0];
/* 218 */     this.kernelUnitLength[1] = (float)kernelUnitLength[1];
/*     */   }
/*     */   public RenderedImage createRendering(RenderContext rc) {
/*     */     AffineRed affineRed;
/* 222 */     Shape aoi = rc.getAreaOfInterest();
/* 223 */     if (aoi == null) {
/* 224 */       aoi = getBounds2D();
/*     */     }
/* 226 */     Rectangle2D aoiR = aoi.getBounds2D();
/* 227 */     Rectangle2D.intersect(aoiR, getBounds2D(), aoiR);
/*     */     
/* 229 */     AffineTransform at = rc.getTransform();
/* 230 */     Rectangle devRect = at.createTransformedShape(aoiR).getBounds();
/*     */     
/* 232 */     if (devRect.width == 0 || devRect.height == 0) {
/* 233 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 245 */     double sx = at.getScaleX();
/* 246 */     double sy = at.getScaleY();
/*     */     
/* 248 */     double shx = at.getShearX();
/* 249 */     double shy = at.getShearY();
/*     */     
/* 251 */     double tx = at.getTranslateX();
/* 252 */     double ty = at.getTranslateY();
/*     */ 
/*     */     
/* 255 */     double scaleX = Math.sqrt(sx * sx + shy * shy);
/* 256 */     double scaleY = Math.sqrt(sy * sy + shx * shx);
/*     */     
/* 258 */     if (scaleX == 0.0D || scaleY == 0.0D)
/*     */     {
/* 260 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 265 */     if (this.kernelUnitLength != null) {
/* 266 */       if (scaleX >= (1.0F / this.kernelUnitLength[0])) {
/* 267 */         scaleX = (1.0F / this.kernelUnitLength[0]);
/*     */       }
/* 269 */       if (scaleY >= (1.0F / this.kernelUnitLength[1])) {
/* 270 */         scaleY = (1.0F / this.kernelUnitLength[1]);
/*     */       }
/*     */     } 
/* 273 */     AffineTransform scale = AffineTransform.getScaleInstance(scaleX, scaleY);
/*     */ 
/*     */     
/* 276 */     devRect = scale.createTransformedShape(aoiR).getBounds();
/*     */ 
/*     */     
/* 279 */     aoiR.setRect(aoiR.getX() - 2.0D / scaleX, aoiR.getY() - 2.0D / scaleY, aoiR.getWidth() + 4.0D / scaleX, aoiR.getHeight() + 4.0D / scaleY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 286 */     rc = (RenderContext)rc.clone();
/* 287 */     rc.setAreaOfInterest(aoiR);
/* 288 */     rc.setTransform(scale);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 293 */     CachableRed cr = GraphicsUtil.wrap(getSource().createRendering(rc));
/*     */     
/* 295 */     BumpMap bumpMap = new BumpMap((RenderedImage)cr, this.surfaceScale, scaleX, scaleY);
/*     */     
/* 297 */     SpecularLightingRed specularLightingRed = new SpecularLightingRed(this.ks, this.specularExponent, this.light, bumpMap, devRect, 1.0D / scaleX, 1.0D / scaleY, isColorSpaceLinear());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 302 */     AffineTransform shearAt = new AffineTransform(sx / scaleX, shy / scaleX, shx / scaleY, sy / scaleY, tx, ty);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 307 */     if (!shearAt.isIdentity()) {
/* 308 */       RenderingHints rh = rc.getRenderingHints();
/* 309 */       Rectangle padRect = new Rectangle(devRect.x - 1, devRect.y - 1, devRect.width + 2, devRect.height + 2);
/*     */ 
/*     */       
/* 312 */       PadRed padRed = new PadRed((CachableRed)specularLightingRed, padRect, PadMode.REPLICATE, rh);
/*     */       
/* 314 */       affineRed = new AffineRed((CachableRed)padRed, shearAt, rh);
/*     */     } 
/*     */     
/* 317 */     return (RenderedImage)affineRed;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/SpecularLightingRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */