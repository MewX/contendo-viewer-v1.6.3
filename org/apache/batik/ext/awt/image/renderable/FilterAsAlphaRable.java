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
/*    */ import org.apache.batik.ext.awt.image.rendered.FilterAsAlphaRed;
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
/*    */ public class FilterAsAlphaRable
/*    */   extends AbstractRable
/*    */ {
/*    */   public FilterAsAlphaRable(Filter src) {
/* 47 */     super(src, (Map)null);
/*    */   }
/*    */   
/*    */   public Filter getSource() {
/* 51 */     return getSources().get(0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Rectangle2D getBounds2D() {
/* 58 */     return getSource().getBounds2D();
/*    */   }
/*    */ 
/*    */   
/*    */   public RenderedImage createRendering(RenderContext rc) {
/* 63 */     AffineTransform at = rc.getTransform();
/*    */ 
/*    */     
/* 66 */     RenderingHints rh = rc.getRenderingHints();
/* 67 */     if (rh == null) rh = new RenderingHints(null);
/*    */ 
/*    */     
/* 70 */     Shape aoi = rc.getAreaOfInterest();
/* 71 */     if (aoi == null) {
/* 72 */       aoi = getBounds2D();
/*    */     }
/*    */     
/* 75 */     rh.put(RenderingHintsKeyExt.KEY_COLORSPACE, ColorSpaceHintKey.VALUE_COLORSPACE_ALPHA_CONVERT);
/*    */ 
/*    */ 
/*    */     
/* 79 */     RenderedImage ri = getSource().createRendering(new RenderContext(at, aoi, rh));
/* 80 */     if (ri == null) {
/* 81 */       return null;
/*    */     }
/* 83 */     CachableRed cr = RenderedImageCachableRed.wrap(ri);
/*    */     
/* 85 */     Object val = cr.getProperty("org.apache.batik.gvt.filter.Colorspace");
/* 86 */     if (val == ColorSpaceHintKey.VALUE_COLORSPACE_ALPHA_CONVERT) {
/* 87 */       return (RenderedImage)cr;
/*    */     }
/* 89 */     return (RenderedImage)new FilterAsAlphaRed(cr);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/FilterAsAlphaRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */