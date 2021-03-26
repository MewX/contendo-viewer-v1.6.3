/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
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
/*     */ import org.apache.batik.ext.awt.image.SVGComposite;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
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
/*     */ public class PadRable8Bit
/*     */   extends AbstractRable
/*     */   implements PadRable, PaintRable
/*     */ {
/*     */   PadMode padMode;
/*     */   Rectangle2D padRect;
/*     */   
/*     */   public PadRable8Bit(Filter src, Rectangle2D padRect, PadMode padMode) {
/*  53 */     init(src, (Map)null);
/*  54 */     this.padRect = padRect;
/*  55 */     this.padMode = padMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getSource() {
/*  62 */     return this.srcs.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSource(Filter src) {
/*  70 */     init(src, (Map)null);
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/*  74 */     return (Rectangle2D)this.padRect.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPadRect(Rectangle2D rect) {
/*  82 */     touch();
/*  83 */     this.padRect = rect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getPadRect() {
/*  91 */     return (Rectangle2D)this.padRect.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPadMode(PadMode padMode) {
/*  99 */     touch();
/* 100 */     this.padMode = padMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PadMode getPadMode() {
/* 108 */     return this.padMode;
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
/*     */   public boolean paintRable(Graphics2D g2d) {
/* 124 */     Composite c = g2d.getComposite();
/* 125 */     if (!SVGComposite.OVER.equals(c)) {
/* 126 */       return false;
/*     */     }
/* 128 */     if (getPadMode() != PadMode.ZERO_PAD) {
/* 129 */       return false;
/*     */     }
/* 131 */     Rectangle2D padBounds = getPadRect();
/*     */     
/* 133 */     Shape clip = g2d.getClip();
/* 134 */     g2d.clip(padBounds);
/* 135 */     GraphicsUtil.drawImage(g2d, getSource());
/* 136 */     g2d.setClip(clip);
/* 137 */     return true;
/*     */   }
/*     */   
/*     */   public RenderedImage createRendering(RenderContext rc) {
/* 141 */     RenderingHints rh = rc.getRenderingHints();
/* 142 */     if (rh == null) rh = new RenderingHints(null);
/*     */     
/* 144 */     Filter src = getSource();
/* 145 */     Shape aoi = rc.getAreaOfInterest();
/*     */     
/* 147 */     if (aoi == null) {
/* 148 */       aoi = getBounds2D();
/*     */     }
/*     */     
/* 151 */     AffineTransform usr2dev = rc.getTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     Rectangle2D srect = src.getBounds2D();
/* 157 */     Rectangle2D rect = getBounds2D();
/* 158 */     Rectangle2D arect = aoi.getBounds2D();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 163 */     if (!arect.intersects(rect))
/* 164 */       return null; 
/* 165 */     Rectangle2D.intersect(arect, rect, arect);
/*     */     
/* 167 */     RenderedImage ri = null;
/* 168 */     if (arect.intersects(srect)) {
/* 169 */       srect = (Rectangle2D)srect.clone();
/* 170 */       Rectangle2D.intersect(srect, arect, srect);
/*     */       
/* 172 */       RenderContext srcRC = new RenderContext(usr2dev, srect, rh);
/* 173 */       ri = src.createRendering(srcRC);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 180 */     if (ri == null) {
/* 181 */       ri = new BufferedImage(1, 1, 2);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 186 */     CachableRed cr = GraphicsUtil.wrap(ri);
/*     */     
/* 188 */     arect = usr2dev.createTransformedShape(arect).getBounds2D();
/*     */ 
/*     */ 
/*     */     
/* 192 */     return (RenderedImage)new PadRed(cr, arect.getBounds(), this.padMode, rh);
/*     */   }
/*     */ 
/*     */   
/*     */   public Shape getDependencyRegion(int srcIndex, Rectangle2D outputRgn) {
/* 197 */     if (srcIndex != 0) {
/* 198 */       throw new IndexOutOfBoundsException("Affine only has one input");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 203 */     Rectangle2D srect = getSource().getBounds2D();
/* 204 */     if (!srect.intersects(outputRgn))
/* 205 */       return new Rectangle2D.Float(); 
/* 206 */     Rectangle2D.intersect(srect, outputRgn, srect);
/*     */     
/* 208 */     Rectangle2D bounds = getBounds2D();
/* 209 */     if (!srect.intersects(bounds))
/* 210 */       return new Rectangle2D.Float(); 
/* 211 */     Rectangle2D.intersect(srect, bounds, srect);
/* 212 */     return srect;
/*     */   }
/*     */   
/*     */   public Shape getDirtyRegion(int srcIndex, Rectangle2D inputRgn) {
/* 216 */     if (srcIndex != 0) {
/* 217 */       throw new IndexOutOfBoundsException("Affine only has one input");
/*     */     }
/* 219 */     inputRgn = (Rectangle2D)inputRgn.clone();
/* 220 */     Rectangle2D bounds = getBounds2D();
/*     */ 
/*     */     
/* 223 */     if (!inputRgn.intersects(bounds))
/* 224 */       return new Rectangle2D.Float(); 
/* 225 */     Rectangle2D.intersect(inputRgn, bounds, inputRgn);
/* 226 */     return inputRgn;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/PadRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */