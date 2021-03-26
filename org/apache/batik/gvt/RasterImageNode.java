/*     */ package org.apache.batik.gvt;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
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
/*     */ public class RasterImageNode
/*     */   extends AbstractGraphicsNode
/*     */ {
/*     */   protected Filter image;
/*     */   
/*     */   public void setImage(Filter newImage) {
/*  57 */     fireGraphicsNodeChangeStarted();
/*  58 */     invalidateGeometryCache();
/*  59 */     this.image = newImage;
/*  60 */     fireGraphicsNodeChangeCompleted();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getImage() {
/*  69 */     return this.image;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getImageBounds() {
/*  78 */     if (this.image == null)
/*  79 */       return null; 
/*  80 */     return (Rectangle2D)this.image.getBounds2D().clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getGraphicsNodeRable() {
/*  89 */     return this.image;
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
/*     */   public void primitivePaint(Graphics2D g2d) {
/* 102 */     if (this.image == null)
/*     */       return; 
/* 104 */     GraphicsUtil.drawImage(g2d, (RenderableImage)this.image);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getPrimitiveBounds() {
/* 115 */     if (this.image == null)
/* 116 */       return null; 
/* 117 */     return this.image.getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getGeometryBounds() {
/* 126 */     if (this.image == null)
/* 127 */       return null; 
/* 128 */     return this.image.getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getSensitiveBounds() {
/* 137 */     if (this.image == null)
/* 138 */       return null; 
/* 139 */     return this.image.getBounds2D();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getOutline() {
/* 146 */     if (this.image == null)
/* 147 */       return null; 
/* 148 */     return this.image.getBounds2D();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/RasterImageNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */