/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.rendered.BufferedImageCachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.MultiplyAlphaRed;
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
/*     */ public class ClipRable8Bit
/*     */   extends AbstractRable
/*     */   implements ClipRable
/*     */ {
/*     */   protected boolean useAA;
/*     */   protected Shape clipPath;
/*     */   
/*     */   public ClipRable8Bit(Filter src, Shape clipPath) {
/*  58 */     super(src, (Map)null);
/*  59 */     setClipPath(clipPath);
/*  60 */     setUseAntialiasedClip(false);
/*     */   }
/*     */   
/*     */   public ClipRable8Bit(Filter src, Shape clipPath, boolean useAA) {
/*  64 */     super(src, (Map)null);
/*  65 */     setClipPath(clipPath);
/*  66 */     setUseAntialiasedClip(useAA);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSource(Filter src) {
/*  74 */     init(src, (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getSource() {
/*  82 */     return getSources().get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUseAntialiasedClip(boolean useAA) {
/*  90 */     touch();
/*  91 */     this.useAA = useAA;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getUseAntialiasedClip() {
/*  99 */     return this.useAA;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClipPath(Shape clipPath) {
/* 109 */     touch();
/* 110 */     this.clipPath = clipPath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getClipPath() {
/* 119 */     return this.clipPath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 126 */     return getSource().getBounds2D();
/*     */   }
/*     */ 
/*     */   
/*     */   public RenderedImage createRendering(RenderContext rc) {
/* 131 */     AffineTransform usr2dev = rc.getTransform();
/*     */ 
/*     */     
/* 134 */     RenderingHints rh = rc.getRenderingHints();
/* 135 */     if (rh == null) rh = new RenderingHints(null);
/*     */     
/* 137 */     Shape aoi = rc.getAreaOfInterest();
/* 138 */     if (aoi == null) aoi = getBounds2D();
/*     */     
/* 140 */     Rectangle2D rect = getBounds2D();
/* 141 */     Rectangle2D clipRect = this.clipPath.getBounds2D();
/* 142 */     Rectangle2D aoiRect = aoi.getBounds2D();
/*     */     
/* 144 */     if (!rect.intersects(clipRect))
/* 145 */       return null; 
/* 146 */     Rectangle2D.intersect(rect, clipRect, rect);
/*     */ 
/*     */     
/* 149 */     if (!rect.intersects(aoiRect))
/* 150 */       return null; 
/* 151 */     Rectangle2D.intersect(rect, aoi.getBounds2D(), rect);
/*     */     
/* 153 */     Rectangle devR = usr2dev.createTransformedShape(rect).getBounds();
/*     */     
/* 155 */     if (devR.width == 0 || devR.height == 0) {
/* 156 */       return null;
/*     */     }
/* 158 */     BufferedImage bi = new BufferedImage(devR.width, devR.height, 10);
/*     */ 
/*     */     
/* 161 */     Shape devShape = usr2dev.createTransformedShape(getClipPath());
/*     */     
/* 163 */     Rectangle devAOIR = usr2dev.createTransformedShape(aoi).getBounds();
/*     */     
/* 165 */     Graphics2D g2d = GraphicsUtil.createGraphics(bi, rh);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 173 */     g2d.translate(-devR.x, -devR.y);
/* 174 */     g2d.setPaint(Color.white);
/* 175 */     g2d.fill(devShape);
/* 176 */     g2d.dispose();
/*     */ 
/*     */     
/* 179 */     RenderedImage ri = getSource().createRendering(new RenderContext(usr2dev, rect, rh));
/*     */ 
/*     */     
/* 182 */     CachableRed cr = RenderedImageCachableRed.wrap(ri);
/* 183 */     BufferedImageCachableRed bufferedImageCachableRed = new BufferedImageCachableRed(bi, devR.x, devR.y);
/* 184 */     MultiplyAlphaRed multiplyAlphaRed = new MultiplyAlphaRed(cr, (CachableRed)bufferedImageCachableRed);
/*     */ 
/*     */     
/* 187 */     return (RenderedImage)new PadRed((CachableRed)multiplyAlphaRed, devAOIR, PadMode.ZERO_PAD, rh);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/ClipRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */