/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import org.apache.batik.ext.awt.image.rendered.TurbulencePatternRed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TurbulenceRable8Bit
/*     */   extends AbstractColorInterpolationRable
/*     */   implements TurbulenceRable
/*     */ {
/*  43 */   int seed = 0;
/*  44 */   int numOctaves = 1;
/*  45 */   double baseFreqX = 0.0D;
/*  46 */   double baseFreqY = 0.0D;
/*     */   
/*     */   boolean stitched = false;
/*     */   
/*     */   boolean fractalNoise = false;
/*     */   Rectangle2D region;
/*     */   
/*     */   public TurbulenceRable8Bit(Rectangle2D region) {
/*  54 */     this.region = region;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TurbulenceRable8Bit(Rectangle2D region, int seed, int numOctaves, double baseFreqX, double baseFreqY, boolean stitched, boolean fractalNoise) {
/*  65 */     this.seed = seed;
/*  66 */     this.numOctaves = numOctaves;
/*  67 */     this.baseFreqX = baseFreqX;
/*  68 */     this.baseFreqY = baseFreqY;
/*  69 */     this.stitched = stitched;
/*  70 */     this.fractalNoise = fractalNoise;
/*  71 */     this.region = region;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getTurbulenceRegion() {
/*  78 */     return (Rectangle2D)this.region.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/*  85 */     return (Rectangle2D)this.region.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSeed() {
/*  93 */     return this.seed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumOctaves() {
/* 101 */     return this.numOctaves;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getBaseFrequencyX() {
/* 109 */     return this.baseFreqX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getBaseFrequencyY() {
/* 117 */     return this.baseFreqY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStitched() {
/* 125 */     return this.stitched;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFractalNoise() {
/* 135 */     return this.fractalNoise;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTurbulenceRegion(Rectangle2D turbulenceRegion) {
/* 143 */     touch();
/* 144 */     this.region = turbulenceRegion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSeed(int seed) {
/* 152 */     touch();
/* 153 */     this.seed = seed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNumOctaves(int numOctaves) {
/* 161 */     touch();
/* 162 */     this.numOctaves = numOctaves;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseFrequencyX(double baseFreqX) {
/* 170 */     touch();
/* 171 */     this.baseFreqX = baseFreqX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBaseFrequencyY(double baseFreqY) {
/* 179 */     touch();
/* 180 */     this.baseFreqY = baseFreqY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStitched(boolean stitched) {
/* 188 */     touch();
/* 189 */     this.stitched = stitched;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFractalNoise(boolean fractalNoise) {
/* 197 */     touch();
/* 198 */     this.fractalNoise = fractalNoise;
/*     */   }
/*     */ 
/*     */   
/*     */   public RenderedImage createRendering(RenderContext rc) {
/*     */     Rectangle2D aoiRect;
/* 204 */     Shape aoi = rc.getAreaOfInterest();
/* 205 */     if (aoi == null) {
/* 206 */       aoiRect = getBounds2D();
/*     */     } else {
/* 208 */       Rectangle2D rect = getBounds2D();
/* 209 */       aoiRect = aoi.getBounds2D();
/* 210 */       if (!aoiRect.intersects(rect))
/* 211 */         return null; 
/* 212 */       Rectangle2D.intersect(aoiRect, rect, aoiRect);
/*     */     } 
/*     */     
/* 215 */     AffineTransform usr2dev = rc.getTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 221 */     Rectangle devRect = usr2dev.createTransformedShape(aoiRect).getBounds();
/*     */ 
/*     */     
/* 224 */     if (devRect.width <= 0 || devRect.height <= 0)
/*     */     {
/* 226 */       return null;
/*     */     }
/* 228 */     ColorSpace cs = getOperationColorSpace();
/*     */     
/* 230 */     Rectangle2D tile = null;
/* 231 */     if (this.stitched) {
/* 232 */       tile = (Rectangle2D)this.region.clone();
/*     */     }
/* 234 */     AffineTransform patternTxf = new AffineTransform();
/*     */     try {
/* 236 */       patternTxf = usr2dev.createInverse();
/* 237 */     } catch (NoninvertibleTransformException noninvertibleTransformException) {}
/*     */ 
/*     */     
/* 240 */     return (RenderedImage)new TurbulencePatternRed(this.baseFreqX, this.baseFreqY, this.numOctaves, this.seed, this.fractalNoise, tile, patternTxf, devRect, cs, true);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/TurbulenceRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */