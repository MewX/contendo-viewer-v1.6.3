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
/*     */ import org.apache.batik.ext.awt.image.rendered.DiffuseLightingRed;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DiffuseLightingRable8Bit
/*     */   extends AbstractColorInterpolationRable
/*     */   implements DiffuseLightingRable
/*     */ {
/*     */   private double surfaceScale;
/*     */   private double kd;
/*     */   private Light light;
/*     */   private Rectangle2D litRegion;
/*  70 */   private float[] kernelUnitLength = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DiffuseLightingRable8Bit(Filter src, Rectangle2D litRegion, Light light, double kd, double surfaceScale, double[] kernelUnitLength) {
/*  78 */     super(src, (Map)null);
/*  79 */     setLight(light);
/*  80 */     setKd(kd);
/*  81 */     setSurfaceScale(surfaceScale);
/*  82 */     setLitRegion(litRegion);
/*  83 */     setKernelUnitLength(kernelUnitLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getSource() {
/*  90 */     return getSources().get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSource(Filter src) {
/*  97 */     init(src, (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 104 */     return (Rectangle2D)this.litRegion.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getLitRegion() {
/* 111 */     return getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLitRegion(Rectangle2D litRegion) {
/* 118 */     touch();
/* 119 */     this.litRegion = litRegion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Light getLight() {
/* 126 */     return this.light;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLight(Light light) {
/* 133 */     touch();
/* 134 */     this.light = light;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getSurfaceScale() {
/* 141 */     return this.surfaceScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSurfaceScale(double surfaceScale) {
/* 148 */     touch();
/* 149 */     this.surfaceScale = surfaceScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getKd() {
/* 156 */     return this.kd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKd(double kd) {
/* 163 */     touch();
/* 164 */     this.kd = kd;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getKernelUnitLength() {
/* 172 */     if (this.kernelUnitLength == null) {
/* 173 */       return null;
/*     */     }
/* 175 */     double[] ret = new double[2];
/* 176 */     ret[0] = this.kernelUnitLength[0];
/* 177 */     ret[1] = this.kernelUnitLength[1];
/* 178 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKernelUnitLength(double[] kernelUnitLength) {
/* 186 */     touch();
/* 187 */     if (kernelUnitLength == null) {
/* 188 */       this.kernelUnitLength = null;
/*     */       
/*     */       return;
/*     */     } 
/* 192 */     if (this.kernelUnitLength == null) {
/* 193 */       this.kernelUnitLength = new float[2];
/*     */     }
/* 195 */     this.kernelUnitLength[0] = (float)kernelUnitLength[0];
/* 196 */     this.kernelUnitLength[1] = (float)kernelUnitLength[1];
/*     */   }
/*     */   public RenderedImage createRendering(RenderContext rc) {
/*     */     AffineRed affineRed;
/* 200 */     Shape aoi = rc.getAreaOfInterest();
/* 201 */     if (aoi == null) {
/* 202 */       aoi = getBounds2D();
/*     */     }
/* 204 */     Rectangle2D aoiR = aoi.getBounds2D();
/* 205 */     Rectangle2D.intersect(aoiR, getBounds2D(), aoiR);
/*     */     
/* 207 */     AffineTransform at = rc.getTransform();
/* 208 */     Rectangle devRect = at.createTransformedShape(aoiR).getBounds();
/*     */     
/* 210 */     if (devRect.width == 0 || devRect.height == 0) {
/* 211 */       return null;
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
/* 223 */     double sx = at.getScaleX();
/* 224 */     double sy = at.getScaleY();
/*     */     
/* 226 */     double shx = at.getShearX();
/* 227 */     double shy = at.getShearY();
/*     */     
/* 229 */     double tx = at.getTranslateX();
/* 230 */     double ty = at.getTranslateY();
/*     */ 
/*     */     
/* 233 */     double scaleX = Math.sqrt(sx * sx + shy * shy);
/* 234 */     double scaleY = Math.sqrt(sy * sy + shx * shx);
/*     */     
/* 236 */     if (scaleX == 0.0D || scaleY == 0.0D)
/*     */     {
/* 238 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 243 */     if (this.kernelUnitLength != null) {
/* 244 */       if (this.kernelUnitLength[0] > 0.0F && scaleX > (1.0F / this.kernelUnitLength[0]))
/*     */       {
/* 246 */         scaleX = (1.0F / this.kernelUnitLength[0]);
/*     */       }
/* 248 */       if (this.kernelUnitLength[1] > 0.0F && scaleY > (1.0F / this.kernelUnitLength[1]))
/*     */       {
/* 250 */         scaleY = (1.0F / this.kernelUnitLength[1]);
/*     */       }
/*     */     } 
/* 253 */     AffineTransform scale = AffineTransform.getScaleInstance(scaleX, scaleY);
/*     */ 
/*     */     
/* 256 */     devRect = scale.createTransformedShape(aoiR).getBounds();
/*     */ 
/*     */     
/* 259 */     aoiR.setRect(aoiR.getX() - 2.0D / scaleX, aoiR.getY() - 2.0D / scaleY, aoiR.getWidth() + 4.0D / scaleX, aoiR.getHeight() + 4.0D / scaleY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 266 */     rc = (RenderContext)rc.clone();
/* 267 */     rc.setAreaOfInterest(aoiR);
/* 268 */     rc.setTransform(scale);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 273 */     CachableRed cr = GraphicsUtil.wrap(getSource().createRendering(rc));
/*     */     
/* 275 */     BumpMap bumpMap = new BumpMap((RenderedImage)cr, this.surfaceScale, scaleX, scaleY);
/*     */     
/* 277 */     DiffuseLightingRed diffuseLightingRed = new DiffuseLightingRed(this.kd, this.light, bumpMap, devRect, 1.0D / scaleX, 1.0D / scaleY, isColorSpaceLinear());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 282 */     AffineTransform shearAt = new AffineTransform(sx / scaleX, shy / scaleX, shx / scaleY, sy / scaleY, tx, ty);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 287 */     if (!shearAt.isIdentity()) {
/* 288 */       RenderingHints rh = rc.getRenderingHints();
/* 289 */       Rectangle padRect = new Rectangle(devRect.x - 1, devRect.y - 1, devRect.width + 2, devRect.height + 2);
/*     */ 
/*     */       
/* 292 */       PadRed padRed = new PadRed((CachableRed)diffuseLightingRed, padRect, PadMode.REPLICATE, rh);
/*     */       
/* 294 */       affineRed = new AffineRed((CachableRed)padRed, shearAt, rh);
/*     */     } 
/*     */     
/* 297 */     return (RenderedImage)affineRed;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/DiffuseLightingRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */