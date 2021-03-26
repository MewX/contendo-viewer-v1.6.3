/*     */ package org.apache.batik.gvt.filter;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.AbstractRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.FilterAsAlphaRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.PadRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.MultiplyAlphaRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.RenderedImageCachableRed;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MaskRable8Bit
/*     */   extends AbstractRable
/*     */   implements Mask
/*     */ {
/*     */   protected GraphicsNode mask;
/*     */   protected Rectangle2D filterRegion;
/*     */   
/*     */   public MaskRable8Bit(Filter src, GraphicsNode mask, Rectangle2D filterRegion) {
/*  59 */     super(src, null);
/*  60 */     setMaskNode(mask);
/*  61 */     setFilterRegion(filterRegion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSource(Filter src) {
/*  69 */     init(src, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getSource() {
/*  77 */     return getSources().get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getFilterRegion() {
/*  84 */     return (Rectangle2D)this.filterRegion.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterRegion(Rectangle2D filterRegion) {
/*  91 */     if (filterRegion == null) {
/*  92 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  95 */     this.filterRegion = filterRegion;
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
/*     */   public void setMaskNode(GraphicsNode mask) {
/* 108 */     touch();
/* 109 */     this.mask = mask;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode getMaskNode() {
/* 118 */     return this.mask;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 125 */     return (Rectangle2D)this.filterRegion.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderedImage createRendering(RenderContext rc) {
/* 132 */     Filter maskSrc = getMaskNode().getGraphicsNodeRable(true);
/* 133 */     PadRable8Bit padRable8Bit1 = new PadRable8Bit(maskSrc, getBounds2D(), PadMode.ZERO_PAD);
/*     */     
/* 135 */     FilterAsAlphaRable filterAsAlphaRable = new FilterAsAlphaRable((Filter)padRable8Bit1);
/* 136 */     RenderedImage ri = filterAsAlphaRable.createRendering(rc);
/* 137 */     if (ri == null) {
/* 138 */       return null;
/*     */     }
/* 140 */     CachableRed maskCr = RenderedImageCachableRed.wrap(ri);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     PadRable8Bit padRable8Bit2 = new PadRable8Bit(getSource(), getBounds2D(), PadMode.ZERO_PAD);
/*     */ 
/*     */ 
/*     */     
/* 149 */     ri = padRable8Bit2.createRendering(rc);
/* 150 */     if (ri == null) {
/* 151 */       return null;
/*     */     }
/*     */     
/* 154 */     CachableRed cr = GraphicsUtil.wrap(ri);
/* 155 */     cr = GraphicsUtil.convertToLsRGB(cr);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     return (RenderedImage)new MultiplyAlphaRed(cr, maskCr);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/filter/MaskRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */