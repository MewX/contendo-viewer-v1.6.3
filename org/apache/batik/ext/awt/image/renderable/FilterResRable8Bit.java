/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.lang.ref.Reference;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.CompositeRule;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.SVGComposite;
/*     */ import org.apache.batik.ext.awt.image.rendered.AffineRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.TileCacheRed;
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
/*     */ public class FilterResRable8Bit
/*     */   extends AbstractRable
/*     */   implements FilterResRable, PaintRable
/*     */ {
/*  55 */   private int filterResolutionX = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   private int filterResolutionY = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Reference resRed;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float resScale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getSource() {
/*  77 */     return this.srcs.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSource(Filter src) {
/*  85 */     init(src, (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFilterResolutionX() {
/*  92 */     return this.filterResolutionX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterResolutionX(int filterResolutionX) {
/* 102 */     if (filterResolutionX < 0) {
/* 103 */       throw new IllegalArgumentException();
/*     */     }
/* 105 */     touch();
/* 106 */     this.filterResolutionX = filterResolutionX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFilterResolutionY() {
/* 113 */     return this.filterResolutionY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterResolutionY(int filterResolutionY) {
/* 123 */     touch();
/* 124 */     this.filterResolutionY = filterResolutionY;
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
/*     */   public boolean allPaintRable(RenderableImage ri) {
/* 141 */     if (!(ri instanceof PaintRable)) {
/* 142 */       return false;
/*     */     }
/* 144 */     List<RenderableImage> v = ri.getSources();
/*     */     
/* 146 */     if (v == null) return true;
/*     */     
/* 148 */     for (RenderableImage aV : v) {
/* 149 */       RenderableImage nri = aV;
/*     */       
/* 151 */       if (!allPaintRable(nri)) return false;
/*     */     
/*     */     } 
/* 154 */     return true;
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
/*     */   public boolean distributeAcross(RenderableImage src, Graphics2D g2d) {
/* 171 */     if (src instanceof PadRable) {
/* 172 */       PadRable pad = (PadRable)src;
/* 173 */       Shape clip = g2d.getClip();
/* 174 */       g2d.clip(pad.getPadRect());
/* 175 */       boolean ret = distributeAcross(pad.getSource(), g2d);
/* 176 */       g2d.setClip(clip);
/* 177 */       return ret;
/*     */     } 
/*     */     
/* 180 */     if (src instanceof CompositeRable) {
/* 181 */       CompositeRable comp = (CompositeRable)src;
/* 182 */       if (comp.getCompositeRule() != CompositeRule.OVER) {
/* 183 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 194 */       List<RenderableImage> v = comp.getSources();
/* 195 */       if (v == null) return true; 
/* 196 */       ListIterator<RenderableImage> li = v.listIterator(v.size());
/* 197 */       while (li.hasPrevious()) {
/* 198 */         RenderableImage csrc = li.previous();
/* 199 */         if (!allPaintRable(csrc)) {
/* 200 */           li.next();
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 205 */       if (!li.hasPrevious()) {
/*     */ 
/*     */         
/* 208 */         GraphicsUtil.drawImage(g2d, comp);
/* 209 */         return true;
/*     */       } 
/*     */       
/* 212 */       if (!li.hasNext())
/*     */       {
/*     */         
/* 215 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 222 */       int idx = li.nextIndex();
/* 223 */       Filter f = new CompositeRable8Bit(v.subList(0, idx), comp.getCompositeRule(), comp.isColorSpaceLinear());
/*     */ 
/*     */       
/* 226 */       f = new FilterResRable8Bit(f, getFilterResolutionX(), getFilterResolutionY());
/*     */       
/* 228 */       GraphicsUtil.drawImage(g2d, f);
/* 229 */       while (li.hasNext()) {
/* 230 */         PaintRable pr = (PaintRable)li.next();
/* 231 */         if (!pr.paintRable(g2d)) {
/*     */           
/* 233 */           Filter prf = (Filter)pr;
/* 234 */           prf = new FilterResRable8Bit(prf, getFilterResolutionX(), getFilterResolutionY());
/*     */           
/* 236 */           GraphicsUtil.drawImage(g2d, prf);
/*     */         } 
/*     */       } 
/* 239 */       return true;
/*     */     } 
/* 241 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean paintRable(Graphics2D g2d) {
/* 267 */     Composite c = g2d.getComposite();
/* 268 */     if (!SVGComposite.OVER.equals(c)) {
/* 269 */       return false;
/*     */     }
/* 271 */     Filter src = getSource();
/* 272 */     return distributeAcross(src, g2d);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FilterResRable8Bit() {
/* 278 */     this.resRed = null;
/* 279 */     this.resScale = 0.0F; } public FilterResRable8Bit(Filter src, int filterResX, int filterResY) { this.resRed = null; this.resScale = 0.0F;
/*     */     init(src, (Map)null);
/*     */     setFilterResolutionX(filterResX);
/* 282 */     setFilterResolutionY(filterResY); } private float getResScale() { return this.resScale; }
/*     */ 
/*     */   
/*     */   private RenderedImage getResRed(RenderingHints hints) {
/* 286 */     Rectangle2D imageRect = getBounds2D();
/* 287 */     double resScaleX = getFilterResolutionX() / imageRect.getWidth();
/* 288 */     double resScaleY = getFilterResolutionY() / imageRect.getHeight();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 294 */     float resScale = (float)Math.min(resScaleX, resScaleY);
/*     */ 
/*     */     
/* 297 */     if (resScale == this.resScale) {
/*     */       
/* 299 */       RenderedImage renderedImage = this.resRed.get();
/* 300 */       if (renderedImage != null) {
/* 301 */         return renderedImage;
/*     */       }
/*     */     } 
/*     */     
/* 305 */     AffineTransform resUsr2Dev = AffineTransform.getScaleInstance(resScale, resScale);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 310 */     RenderContext newRC = new RenderContext(resUsr2Dev, null, hints);
/*     */     
/* 312 */     RenderedImage ret = getSource().createRendering(newRC);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 318 */     TileCacheRed tileCacheRed = new TileCacheRed(GraphicsUtil.wrap(ret));
/* 319 */     this.resScale = resScale;
/* 320 */     this.resRed = new SoftReference<TileCacheRed>(tileCacheRed);
/*     */     
/* 322 */     return (RenderedImage)tileCacheRed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderedImage createRendering(RenderContext renderContext) {
/* 332 */     AffineTransform usr2dev = renderContext.getTransform();
/* 333 */     if (usr2dev == null) {
/* 334 */       usr2dev = new AffineTransform();
/*     */     }
/*     */     
/* 337 */     RenderingHints hints = renderContext.getRenderingHints();
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
/* 351 */     int filterResolutionX = getFilterResolutionX();
/* 352 */     int filterResolutionY = getFilterResolutionY();
/*     */ 
/*     */ 
/*     */     
/* 356 */     if (filterResolutionX <= 0 || filterResolutionY == 0) {
/* 357 */       return null;
/*     */     }
/*     */     
/* 360 */     Rectangle2D imageRect = getBounds2D();
/*     */     
/* 362 */     Rectangle devRect = usr2dev.createTransformedShape(imageRect).getBounds();
/*     */ 
/*     */ 
/*     */     
/* 366 */     float scaleX = 1.0F;
/* 367 */     if (filterResolutionX < devRect.width) {
/* 368 */       scaleX = filterResolutionX / devRect.width;
/*     */     }
/* 370 */     float scaleY = 1.0F;
/* 371 */     if (filterResolutionY < 0) {
/* 372 */       scaleY = scaleX;
/* 373 */     } else if (filterResolutionY < devRect.height) {
/* 374 */       scaleY = filterResolutionY / devRect.height;
/*     */     } 
/*     */ 
/*     */     
/* 378 */     if (scaleX >= 1.0F && scaleY >= 1.0F) {
/* 379 */       return getSource().createRendering(renderContext);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 385 */     RenderedImage resRed = getResRed(hints);
/* 386 */     float resScale = getResScale();
/*     */ 
/*     */     
/* 389 */     AffineTransform residualAT = new AffineTransform(usr2dev.getScaleX() / resScale, usr2dev.getShearY() / resScale, usr2dev.getShearX() / resScale, usr2dev.getScaleY() / resScale, usr2dev.getTranslateX(), usr2dev.getTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 398 */     return (RenderedImage)new AffineRed(GraphicsUtil.wrap(resRed), residualAT, hints);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/FilterResRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */