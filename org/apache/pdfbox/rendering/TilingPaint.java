/*     */ package org.apache.pdfbox.rendering;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.PaintContext;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.TexturePaint;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.io.IOException;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.RoundingMode;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.pattern.PDTilingPattern;
/*     */ import org.apache.pdfbox.util.Matrix;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class TilingPaint
/*     */   implements Paint
/*     */ {
/*  48 */   private static final Log LOG = LogFactory.getLog(TilingPaint.class);
/*     */   
/*     */   private final Paint paint;
/*     */   
/*     */   private final Matrix patternMatrix;
/*     */ 
/*     */   
/*     */   static {
/*  56 */     String s = System.getProperty("pdfbox.rendering.tilingpaint.maxedge", "3000");
/*     */ 
/*     */     
/*     */     try {
/*  60 */       val = Integer.parseInt(s);
/*     */     }
/*  62 */     catch (NumberFormatException ex) {
/*     */       
/*  64 */       LOG.error("Default will be used", ex);
/*  65 */       val = Integer.parseInt("3000");
/*     */     } 
/*  67 */     MAXEDGE = val;
/*     */   }
/*     */ 
/*     */   
/*     */   private static final int MAXEDGE;
/*     */   
/*     */   private static final String DEFAULTMAXEDGE = "3000";
/*     */ 
/*     */   
/*     */   static {
/*     */     int val;
/*     */   }
/*     */ 
/*     */   
/*     */   TilingPaint(PageDrawer drawer, PDTilingPattern pattern, AffineTransform xform) throws IOException {
/*  82 */     this(drawer, pattern, null, null, xform);
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
/*     */   TilingPaint(PageDrawer drawer, PDTilingPattern pattern, PDColorSpace colorSpace, PDColor color, AffineTransform xform) throws IOException {
/* 101 */     this.patternMatrix = Matrix.concatenate(drawer.getInitialMatrix(), pattern.getMatrix());
/* 102 */     Rectangle2D anchorRect = getAnchorRect(pattern);
/* 103 */     this.paint = new TexturePaint(getImage(drawer, pattern, colorSpace, color, xform, anchorRect), anchorRect);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PaintContext createContext(ColorModel cm, Rectangle deviceBounds, Rectangle2D userBounds, AffineTransform xform, RenderingHints hints) {
/* 113 */     AffineTransform xformPattern = (AffineTransform)xform.clone();
/*     */ 
/*     */     
/* 116 */     AffineTransform patternNoScale = this.patternMatrix.createAffineTransform();
/* 117 */     patternNoScale.scale((1.0F / this.patternMatrix.getScalingFactorX()), (1.0F / this.patternMatrix
/* 118 */         .getScalingFactorY()));
/* 119 */     xformPattern.concatenate(patternNoScale);
/*     */     
/* 121 */     return this.paint.createContext(cm, deviceBounds, userBounds, xformPattern, hints);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage getImage(PageDrawer drawer, PDTilingPattern pattern, PDColorSpace colorSpace, PDColor color, AffineTransform xform, Rectangle2D anchorRect) throws IOException {
/* 130 */     float width = (float)Math.abs(anchorRect.getWidth());
/* 131 */     float height = (float)Math.abs(anchorRect.getHeight());
/*     */ 
/*     */     
/* 134 */     Matrix xformMatrix = new Matrix(xform);
/* 135 */     float xScale = Math.abs(xformMatrix.getScalingFactorX());
/* 136 */     float yScale = Math.abs(xformMatrix.getScalingFactorY());
/* 137 */     width *= xScale;
/* 138 */     height *= yScale;
/*     */     
/* 140 */     int rasterWidth = Math.max(1, ceiling(width));
/* 141 */     int rasterHeight = Math.max(1, ceiling(height));
/*     */     
/* 143 */     BufferedImage image = new BufferedImage(rasterWidth, rasterHeight, 2);
/*     */     
/* 145 */     Graphics2D graphics = image.createGraphics();
/*     */ 
/*     */     
/* 148 */     if (pattern.getYStep() < 0.0F) {
/*     */       
/* 150 */       graphics.translate(0, rasterHeight);
/* 151 */       graphics.scale(1.0D, -1.0D);
/*     */     } 
/*     */ 
/*     */     
/* 155 */     if (pattern.getXStep() < 0.0F) {
/*     */       
/* 157 */       graphics.translate(rasterWidth, 0);
/* 158 */       graphics.scale(-1.0D, 1.0D);
/*     */     } 
/*     */ 
/*     */     
/* 162 */     graphics.scale(xScale, yScale);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     Matrix newPatternMatrix = Matrix.getScaleInstance(
/* 168 */         Math.abs(this.patternMatrix.getScalingFactorX()), 
/* 169 */         Math.abs(this.patternMatrix.getScalingFactorY()));
/*     */ 
/*     */     
/* 172 */     newPatternMatrix.concatenate(
/* 173 */         Matrix.getTranslateInstance(-pattern.getBBox().getLowerLeftX(), 
/* 174 */           -pattern.getBBox().getLowerLeftY()));
/*     */ 
/*     */     
/* 177 */     drawer.drawTilingPattern(graphics, pattern, colorSpace, color, newPatternMatrix);
/* 178 */     graphics.dispose();
/*     */     
/* 180 */     return image;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int ceiling(double num) {
/* 189 */     BigDecimal decimal = new BigDecimal(num);
/* 190 */     decimal = decimal.setScale(5, RoundingMode.CEILING);
/* 191 */     return decimal.intValue();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTransparency() {
/* 197 */     return 3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Rectangle2D getAnchorRect(PDTilingPattern pattern) {
/* 205 */     float xStep = pattern.getXStep();
/* 206 */     if (xStep == 0.0F)
/*     */     {
/* 208 */       xStep = pattern.getBBox().getWidth();
/*     */     }
/*     */     
/* 211 */     float yStep = pattern.getYStep();
/* 212 */     if (yStep == 0.0F)
/*     */     {
/* 214 */       yStep = pattern.getBBox().getHeight();
/*     */     }
/*     */     
/* 217 */     float xScale = this.patternMatrix.getScalingFactorX();
/* 218 */     float yScale = this.patternMatrix.getScalingFactorY();
/* 219 */     float width = xStep * xScale;
/* 220 */     float height = yStep * yScale;
/*     */     
/* 222 */     if (Math.abs(width * height) > (MAXEDGE * MAXEDGE)) {
/*     */ 
/*     */       
/* 225 */       LOG.info("Pattern surface is too large, will be clipped");
/* 226 */       LOG.info("width: " + width + ", height: " + height);
/* 227 */       LOG.info("XStep: " + xStep + ", YStep: " + yStep);
/* 228 */       LOG.info("bbox: " + pattern.getBBox());
/* 229 */       LOG.info("pattern matrix: " + pattern.getMatrix());
/* 230 */       LOG.info("concatenated matrix: " + this.patternMatrix);
/* 231 */       width = Math.min(MAXEDGE, Math.abs(width)) * Math.signum(width);
/* 232 */       height = Math.min(MAXEDGE, Math.abs(height)) * Math.signum(height);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 237 */     PDRectangle anchor = pattern.getBBox();
/* 238 */     return new Rectangle2D.Float(anchor.getLowerLeftX() * xScale, anchor
/* 239 */         .getLowerLeftY() * yScale, width, height);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/rendering/TilingPaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */