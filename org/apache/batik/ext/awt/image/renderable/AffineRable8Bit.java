/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AffineRable8Bit
/*     */   extends AbstractRable
/*     */   implements AffineRable, PaintRable
/*     */ {
/*     */   AffineTransform affine;
/*     */   AffineTransform invAffine;
/*     */   
/*     */   public AffineRable8Bit(Filter src, AffineTransform affine) {
/*  47 */     init(src);
/*  48 */     setAffine(affine);
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/*  52 */     Filter src = getSource();
/*  53 */     Rectangle2D r = src.getBounds2D();
/*  54 */     return this.affine.createTransformedShape(r).getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getSource() {
/*  60 */     return this.srcs.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSource(Filter src) {
/*  68 */     init(src);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAffine(AffineTransform affine) {
/*  76 */     touch();
/*  77 */     this.affine = affine;
/*     */     try {
/*  79 */       this.invAffine = affine.createInverse();
/*  80 */     } catch (NoninvertibleTransformException e) {
/*  81 */       this.invAffine = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getAffine() {
/*  90 */     return (AffineTransform)this.affine.clone();
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
/*     */   public boolean paintRable(Graphics2D g2d) {
/* 103 */     AffineTransform at = g2d.getTransform();
/*     */     
/* 105 */     g2d.transform(getAffine());
/* 106 */     GraphicsUtil.drawImage(g2d, getSource());
/*     */     
/* 108 */     g2d.setTransform(at);
/*     */     
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderedImage createRendering(RenderContext rc) {
/* 116 */     if (this.invAffine == null) return null;
/*     */ 
/*     */     
/* 119 */     RenderingHints rh = rc.getRenderingHints();
/* 120 */     if (rh == null) rh = new RenderingHints(null);
/*     */ 
/*     */     
/* 123 */     Shape aoi = rc.getAreaOfInterest();
/* 124 */     if (aoi != null) {
/* 125 */       aoi = this.invAffine.createTransformedShape(aoi);
/*     */     }
/*     */     
/* 128 */     AffineTransform at = rc.getTransform();
/* 129 */     at.concatenate(this.affine);
/*     */ 
/*     */     
/* 132 */     return getSource().createRendering(new RenderContext(at, aoi, rh));
/*     */   }
/*     */   
/*     */   public Shape getDependencyRegion(int srcIndex, Rectangle2D outputRgn) {
/* 136 */     if (srcIndex != 0)
/* 137 */       throw new IndexOutOfBoundsException("Affine only has one input"); 
/* 138 */     if (this.invAffine == null)
/* 139 */       return null; 
/* 140 */     return this.invAffine.createTransformedShape(outputRgn);
/*     */   }
/*     */   
/*     */   public Shape getDirtyRegion(int srcIndex, Rectangle2D inputRgn) {
/* 144 */     if (srcIndex != 0)
/* 145 */       throw new IndexOutOfBoundsException("Affine only has one input"); 
/* 146 */     return this.affine.createTransformedShape(inputRgn);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/AffineRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */