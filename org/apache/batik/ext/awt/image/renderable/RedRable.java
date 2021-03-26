/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import org.apache.batik.ext.awt.image.rendered.AffineRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.TranslateRed;
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
/*     */ public class RedRable
/*     */   extends AbstractRable
/*     */ {
/*     */   CachableRed src;
/*     */   
/*     */   public RedRable(CachableRed src) {
/*  45 */     super((Filter)null);
/*  46 */     this.src = src;
/*     */   }
/*     */   
/*     */   public CachableRed getSource() {
/*  50 */     return this.src;
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/*  54 */     return this.src.getProperty(name);
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/*  58 */     return this.src.getPropertyNames();
/*     */   }
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/*  62 */     return getSource().getBounds();
/*     */   }
/*     */   
/*     */   public RenderedImage createDefaultRendering() {
/*  66 */     return (RenderedImage)getSource();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderedImage createRendering(RenderContext rc) {
/*     */     Rectangle aoiR;
/*  74 */     RenderingHints rh = rc.getRenderingHints();
/*  75 */     if (rh == null) rh = new RenderingHints(null);
/*     */     
/*  77 */     Shape aoi = rc.getAreaOfInterest();
/*     */     
/*  79 */     if (aoi != null) {
/*  80 */       aoiR = aoi.getBounds();
/*     */     } else {
/*  82 */       aoiR = getBounds2D().getBounds();
/*     */     } 
/*     */     
/*  85 */     AffineTransform at = rc.getTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  90 */     CachableRed cr = getSource();
/*     */     
/*  92 */     if (!aoiR.intersects(cr.getBounds())) {
/*  93 */       return null;
/*     */     }
/*  95 */     if (at.isIdentity())
/*     */     {
/*  97 */       return (RenderedImage)cr;
/*     */     }
/*     */     
/* 100 */     if (at.getScaleX() == 1.0D && at.getScaleY() == 1.0D && at.getShearX() == 0.0D && at.getShearY() == 0.0D) {
/*     */       
/* 102 */       int xloc = (int)(cr.getMinX() + at.getTranslateX());
/* 103 */       int yloc = (int)(cr.getMinY() + at.getTranslateY());
/* 104 */       double dx = xloc - cr.getMinX() + at.getTranslateX();
/* 105 */       double dy = yloc - cr.getMinY() + at.getTranslateY();
/* 106 */       if (dx > -1.0E-4D && dx < 1.0E-4D && dy > -1.0E-4D && dy < 1.0E-4D)
/*     */       {
/*     */         
/* 109 */         return (RenderedImage)new TranslateRed(cr, xloc, yloc);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 114 */     return (RenderedImage)new AffineRed(cr, at, rh);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/RedRable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */