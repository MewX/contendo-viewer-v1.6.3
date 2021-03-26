/*     */ package org.apache.batik.extension.svg;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import org.apache.batik.ext.awt.image.LinearTransfer;
/*     */ import org.apache.batik.ext.awt.image.TransferFunction;
/*     */ import org.apache.batik.ext.awt.image.renderable.AbstractColorInterpolationRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.rendered.ComponentTransferRed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BatikHistogramNormalizationFilter8Bit
/*     */   extends AbstractColorInterpolationRable
/*     */   implements BatikHistogramNormalizationFilter
/*     */ {
/*  40 */   private float trim = 0.01F;
/*     */   protected int[] histo;
/*     */   protected float slope;
/*     */   protected float intercept;
/*     */   
/*     */   public void setSource(Filter src) {
/*  46 */     init(src, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getSource() {
/*  53 */     return getSources().get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getTrim() {
/*  60 */     return this.trim;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrim(float trim) {
/*  67 */     this.trim = trim;
/*  68 */     touch();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BatikHistogramNormalizationFilter8Bit(Filter src, float trim) {
/*  76 */     this.histo = null;
/*     */     setSource(src);
/*     */     setTrim(trim);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void computeHistogram(RenderContext rc) {
/*  87 */     if (this.histo != null) {
/*     */       return;
/*     */     }
/*  90 */     Filter src = getSource();
/*     */     
/*  92 */     float scale = 100.0F / src.getWidth();
/*  93 */     float yscale = 100.0F / src.getHeight();
/*     */     
/*  95 */     if (scale > yscale) scale = yscale;
/*     */     
/*  97 */     AffineTransform at = AffineTransform.getScaleInstance(scale, scale);
/*  98 */     rc = new RenderContext(at, rc.getRenderingHints());
/*  99 */     RenderedImage histRI = getSource().createRendering(rc);
/*     */     
/* 101 */     this.histo = (new HistogramRed(convertSourceCS(histRI))).getHistogram();
/*     */     
/* 103 */     int t = (int)(((histRI.getWidth() * histRI.getHeight()) * this.trim) + 0.5D);
/*     */     int c, i;
/* 105 */     for (c = 0, i = 0; i < 255; i++) {
/* 106 */       c += this.histo[i];
/*     */       
/* 108 */       if (c >= t)
/*     */         break; 
/* 110 */     }  int low = i;
/*     */     
/* 112 */     for (c = 0, i = 255; i > 0; i--) {
/* 113 */       c += this.histo[i];
/*     */       
/* 115 */       if (c >= t)
/*     */         break; 
/* 117 */     }  int hi = i;
/*     */     
/* 119 */     this.slope = 255.0F / (hi - low);
/* 120 */     this.intercept = this.slope * -low / 255.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderedImage createRendering(RenderContext rc) {
/* 128 */     RenderedImage srcRI = getSource().createRendering(rc);
/*     */     
/* 130 */     if (srcRI == null) {
/* 131 */       return null;
/*     */     }
/* 133 */     computeHistogram(rc);
/*     */     
/* 135 */     SampleModel sm = srcRI.getSampleModel();
/* 136 */     int bands = sm.getNumBands();
/*     */ 
/*     */     
/* 139 */     TransferFunction[] tfs = new TransferFunction[bands];
/* 140 */     LinearTransfer linearTransfer = new LinearTransfer(this.slope, this.intercept);
/* 141 */     for (int i = 0; i < tfs.length; i++) {
/* 142 */       tfs[i] = (TransferFunction)linearTransfer;
/*     */     }
/* 144 */     return (RenderedImage)new ComponentTransferRed(convertSourceCS(srcRI), tfs, null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/svg/BatikHistogramNormalizationFilter8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */