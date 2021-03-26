/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.FloodRed;
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
/*     */ public class FloodRable8Bit
/*     */   extends AbstractRable
/*     */   implements FloodRable
/*     */ {
/*     */   Paint floodPaint;
/*     */   Rectangle2D floodRegion;
/*     */   
/*     */   public FloodRable8Bit(Rectangle2D floodRegion, Paint floodPaint) {
/*  62 */     setFloodPaint(floodPaint);
/*  63 */     setFloodRegion(floodRegion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFloodPaint(Paint paint) {
/*  71 */     touch();
/*  72 */     if (paint == null) {
/*     */       
/*  74 */       this.floodPaint = new Color(0, 0, 0, 0);
/*     */     } else {
/*  76 */       this.floodPaint = paint;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Paint getFloodPaint() {
/*  86 */     return this.floodPaint;
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/*  91 */     return (Rectangle2D)this.floodRegion.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getFloodRegion() {
/*  98 */     return (Rectangle2D)this.floodRegion.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFloodRegion(Rectangle2D floodRegion) {
/* 105 */     if (floodRegion == null) {
/* 106 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/* 109 */     touch();
/* 110 */     this.floodRegion = floodRegion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderedImage createRendering(RenderContext rc) {
/*     */     Rectangle2D userAOI;
/* 122 */     AffineTransform usr2dev = rc.getTransform();
/* 123 */     if (usr2dev == null) {
/* 124 */       usr2dev = new AffineTransform();
/*     */     }
/*     */     
/* 127 */     Rectangle2D imageRect = getBounds2D();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     Shape aoi = rc.getAreaOfInterest();
/* 133 */     if (aoi == null) {
/* 134 */       aoi = imageRect;
/* 135 */       userAOI = imageRect;
/*     */     } else {
/* 137 */       userAOI = aoi.getBounds2D();
/*     */ 
/*     */       
/* 140 */       if (!imageRect.intersects(userAOI)) {
/* 141 */         return null;
/*     */       }
/*     */       
/* 144 */       Rectangle2D.intersect(imageRect, userAOI, userAOI);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 149 */     Rectangle renderedArea = usr2dev.createTransformedShape(userAOI).getBounds();
/*     */ 
/*     */     
/* 152 */     if (renderedArea.width <= 0 || renderedArea.height <= 0)
/*     */     {
/* 154 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 158 */     FloodRed floodRed = new FloodRed(renderedArea, getFloodPaint());
/*     */ 
/*     */ 
/*     */     
/* 162 */     return (RenderedImage)new PadRed((CachableRed)floodRed, renderedArea, PadMode.ZERO_PAD, null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/FloodRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */