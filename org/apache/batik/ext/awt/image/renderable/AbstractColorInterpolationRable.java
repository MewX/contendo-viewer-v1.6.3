/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractColorInterpolationRable
/*     */   extends AbstractRable
/*     */ {
/*     */   protected boolean csLinear = true;
/*     */   
/*     */   protected AbstractColorInterpolationRable() {}
/*     */   
/*     */   protected AbstractColorInterpolationRable(Filter src) {
/*  61 */     super(src);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractColorInterpolationRable(Filter src, Map props) {
/*  71 */     super(src, props);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractColorInterpolationRable(List srcs) {
/*  82 */     super(srcs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractColorInterpolationRable(List srcs, Map props) {
/*  93 */     super(srcs, props);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isColorSpaceLinear() {
/* 101 */     return this.csLinear;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorSpaceLinear(boolean csLinear) {
/* 110 */     touch();
/* 111 */     this.csLinear = csLinear;
/*     */   }
/*     */   
/*     */   public ColorSpace getOperationColorSpace() {
/* 115 */     if (this.csLinear) {
/* 116 */       return ColorSpace.getInstance(1004);
/*     */     }
/* 118 */     return ColorSpace.getInstance(1000);
/*     */   }
/*     */   
/*     */   protected CachableRed convertSourceCS(CachableRed cr) {
/* 122 */     if (this.csLinear) {
/* 123 */       return GraphicsUtil.convertToLsRGB(cr);
/*     */     }
/* 125 */     return GraphicsUtil.convertTosRGB(cr);
/*     */   }
/*     */   
/*     */   protected CachableRed convertSourceCS(RenderedImage ri) {
/* 129 */     return convertSourceCS(GraphicsUtil.wrap(ri));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/AbstractColorInterpolationRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */