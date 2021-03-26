/*    */ package org.apache.batik.ext.awt.image.renderable;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.Shape;
/*    */ import java.awt.geom.AffineTransform;
/*    */ import java.awt.geom.Rectangle2D;
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.awt.image.renderable.RenderContext;
/*    */ import java.util.Map;
/*    */ import org.apache.batik.ext.awt.ColorSpaceHintKey;
/*    */ import org.apache.batik.ext.awt.RenderingHintsKeyExt;
/*    */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*    */ import org.apache.batik.ext.awt.image.rendered.FilterAlphaRed;
/*    */ import org.apache.batik.ext.awt.image.rendered.RenderedImageCachableRed;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FilterAlphaRable
/*    */   extends AbstractRable
/*    */ {
/*    */   public FilterAlphaRable(Filter src) {
/* 48 */     super(src, (Map)null);
/*    */   }
/*    */   
/*    */   public Filter getSource() {
/* 52 */     return getSources().get(0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Rectangle2D getBounds2D() {
/* 59 */     return getSource().getBounds2D();
/*    */   }
/*    */ 
/*    */   
/*    */   public RenderedImage createRendering(RenderContext rc) {
/* 64 */     AffineTransform at = rc.getTransform();
/*    */ 
/*    */     
/* 67 */     RenderingHints rh = rc.getRenderingHints();
/* 68 */     if (rh == null) rh = new RenderingHints(null);
/*    */ 
/*    */     
/* 71 */     Shape aoi = rc.getAreaOfInterest();
/* 72 */     if (aoi == null) {
/* 73 */       aoi = getBounds2D();
/*    */     }
/*    */     
/* 76 */     rh.put(RenderingHintsKeyExt.KEY_COLORSPACE, ColorSpaceHintKey.VALUE_COLORSPACE_ALPHA);
/*    */ 
/*    */ 
/*    */     
/* 80 */     RenderedImage ri = getSource().createRendering(new RenderContext(at, aoi, rh));
/*    */     
/* 82 */     if (ri == null) {
/* 83 */       return null;
/*    */     }
/*    */     
/* 86 */     CachableRed cr = RenderedImageCachableRed.wrap(ri);
/*    */     
/* 88 */     Object val = cr.getProperty("org.apache.batik.gvt.filter.Colorspace");
/* 89 */     if (val == ColorSpaceHintKey.VALUE_COLORSPACE_ALPHA) {
/* 90 */       return (RenderedImage)cr;
/*    */     }
/* 92 */     return (RenderedImage)new FilterAlphaRed(cr);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/FilterAlphaRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */