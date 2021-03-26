/*     */ package org.apache.pdfbox.pdmodel.graphics.state;
/*     */ 
/*     */ import java.awt.Composite;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDLineDashPattern;
/*     */ import org.apache.pdfbox.pdmodel.graphics.blend.BlendComposite;
/*     */ import org.apache.pdfbox.pdmodel.graphics.blend.BlendMode;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
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
/*     */ 
/*     */ public class PDGraphicsState
/*     */   implements Cloneable
/*     */ {
/*     */   private boolean isClippingPathDirty;
/*     */   private Area clippingPath;
/*  43 */   private Matrix currentTransformationMatrix = new Matrix();
/*  44 */   private PDColor strokingColor = PDDeviceGray.INSTANCE.getInitialColor();
/*  45 */   private PDColor nonStrokingColor = PDDeviceGray.INSTANCE.getInitialColor();
/*  46 */   private PDColorSpace strokingColorSpace = (PDColorSpace)PDDeviceGray.INSTANCE;
/*  47 */   private PDColorSpace nonStrokingColorSpace = (PDColorSpace)PDDeviceGray.INSTANCE;
/*  48 */   private PDTextState textState = new PDTextState();
/*  49 */   private float lineWidth = 1.0F;
/*  50 */   private int lineCap = 0;
/*  51 */   private int lineJoin = 0;
/*  52 */   private float miterLimit = 10.0F;
/*  53 */   private PDLineDashPattern lineDashPattern = new PDLineDashPattern();
/*     */   private RenderingIntent renderingIntent;
/*     */   private boolean strokeAdjustment = false;
/*  56 */   private BlendMode blendMode = (BlendMode)BlendMode.COMPATIBLE;
/*     */   private PDSoftMask softMask;
/*  58 */   private double alphaConstant = 1.0D;
/*  59 */   private double nonStrokingAlphaConstant = 1.0D;
/*     */   
/*     */   private boolean alphaSource = false;
/*     */   
/*     */   private boolean overprint = false;
/*     */   private boolean nonStrokingOverprint = false;
/*  65 */   private double overprintMode = 0.0D;
/*     */ 
/*     */   
/*  68 */   private COSBase transfer = null;
/*     */   
/*  70 */   private double flatness = 1.0D;
/*  71 */   private double smoothness = 0.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDGraphicsState(PDRectangle page) {
/*  79 */     this.clippingPath = new Area(page.toGeneralPath());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix getCurrentTransformationMatrix() {
/*  89 */     return this.currentTransformationMatrix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentTransformationMatrix(Matrix value) {
/*  99 */     this.currentTransformationMatrix = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLineWidth() {
/* 109 */     return this.lineWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineWidth(float value) {
/* 119 */     this.lineWidth = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLineCap() {
/* 129 */     return this.lineCap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineCap(int value) {
/* 139 */     this.lineCap = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLineJoin() {
/* 149 */     return this.lineJoin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineJoin(int value) {
/* 159 */     this.lineJoin = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getMiterLimit() {
/* 169 */     return this.miterLimit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMiterLimit(float value) {
/* 179 */     this.miterLimit = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStrokeAdjustment() {
/* 189 */     return this.strokeAdjustment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrokeAdjustment(boolean value) {
/* 199 */     this.strokeAdjustment = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getAlphaConstant() {
/* 209 */     return this.alphaConstant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlphaConstant(double value) {
/* 219 */     this.alphaConstant = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public double getNonStrokeAlphaConstants() {
/* 231 */     return this.nonStrokingAlphaConstant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setNonStrokeAlphaConstants(double value) {
/* 243 */     this.nonStrokingAlphaConstant = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getNonStrokeAlphaConstant() {
/* 253 */     return this.nonStrokingAlphaConstant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNonStrokeAlphaConstant(double value) {
/* 263 */     this.nonStrokingAlphaConstant = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAlphaSource() {
/* 273 */     return this.alphaSource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlphaSource(boolean value) {
/* 283 */     this.alphaSource = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSoftMask getSoftMask() {
/* 293 */     return this.softMask;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSoftMask(PDSoftMask softMask) {
/* 304 */     this.softMask = softMask;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlendMode getBlendMode() {
/* 314 */     return this.blendMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlendMode(BlendMode blendMode) {
/* 324 */     this.blendMode = blendMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOverprint() {
/* 334 */     return this.overprint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOverprint(boolean value) {
/* 344 */     this.overprint = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNonStrokingOverprint() {
/* 354 */     return this.nonStrokingOverprint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNonStrokingOverprint(boolean value) {
/* 364 */     this.nonStrokingOverprint = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getOverprintMode() {
/* 374 */     return this.overprintMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOverprintMode(double value) {
/* 384 */     this.overprintMode = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getFlatness() {
/* 394 */     return this.flatness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFlatness(double value) {
/* 404 */     this.flatness = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getSmoothness() {
/* 414 */     return this.smoothness;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSmoothness(double value) {
/* 424 */     this.smoothness = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDTextState getTextState() {
/* 434 */     return this.textState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTextState(PDTextState value) {
/* 444 */     this.textState = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDLineDashPattern getLineDashPattern() {
/* 454 */     return this.lineDashPattern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLineDashPattern(PDLineDashPattern value) {
/* 464 */     this.lineDashPattern = value;
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
/*     */   public RenderingIntent getRenderingIntent() {
/* 476 */     return this.renderingIntent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenderingIntent(RenderingIntent value) {
/* 486 */     this.renderingIntent = value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDGraphicsState clone() {
/*     */     try {
/* 494 */       PDGraphicsState clone = (PDGraphicsState)super.clone();
/* 495 */       clone.textState = this.textState.clone();
/* 496 */       clone.currentTransformationMatrix = this.currentTransformationMatrix.clone();
/* 497 */       clone.strokingColor = this.strokingColor;
/* 498 */       clone.nonStrokingColor = this.nonStrokingColor;
/* 499 */       clone.lineDashPattern = this.lineDashPattern;
/* 500 */       clone.clippingPath = this.clippingPath;
/* 501 */       clone.isClippingPathDirty = false;
/* 502 */       return clone;
/*     */     }
/* 504 */     catch (CloneNotSupportedException e) {
/*     */ 
/*     */       
/* 507 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getStrokingColor() {
/* 518 */     return this.strokingColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrokingColor(PDColor color) {
/* 528 */     this.strokingColor = color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getNonStrokingColor() {
/* 538 */     return this.nonStrokingColor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNonStrokingColor(PDColor color) {
/* 548 */     this.nonStrokingColor = color;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColorSpace getStrokingColorSpace() {
/* 558 */     return this.strokingColorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrokingColorSpace(PDColorSpace colorSpace) {
/* 568 */     this.strokingColorSpace = colorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColorSpace getNonStrokingColorSpace() {
/* 578 */     return this.nonStrokingColorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNonStrokingColorSpace(PDColorSpace colorSpace) {
/* 588 */     this.nonStrokingColorSpace = colorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void intersectClippingPath(GeneralPath path) {
/* 597 */     intersectClippingPath(new Area(path));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void intersectClippingPath(Area area) {
/* 607 */     if (!this.isClippingPathDirty) {
/*     */ 
/*     */       
/* 610 */       Area cloned = new Area();
/* 611 */       cloned.add(this.clippingPath);
/* 612 */       this.clippingPath = cloned;
/*     */       
/* 614 */       this.isClippingPathDirty = true;
/*     */     } 
/*     */ 
/*     */     
/* 618 */     this.clippingPath.intersect(area);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Area getCurrentClippingPath() {
/* 628 */     return this.clippingPath;
/*     */   }
/*     */ 
/*     */   
/*     */   public Composite getStrokingJavaComposite() {
/* 633 */     return BlendComposite.getInstance(this.blendMode, (float)this.alphaConstant);
/*     */   }
/*     */ 
/*     */   
/*     */   public Composite getNonStrokingJavaComposite() {
/* 638 */     return BlendComposite.getInstance(this.blendMode, (float)this.nonStrokingAlphaConstant);
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
/*     */   public COSBase getTransfer() {
/* 652 */     return this.transfer;
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
/*     */   public void setTransfer(COSBase transfer) {
/* 666 */     this.transfer = transfer;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/state/PDGraphicsState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */