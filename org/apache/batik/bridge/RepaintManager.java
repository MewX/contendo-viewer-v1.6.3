/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.apache.batik.ext.awt.geom.RectListManager;
/*     */ import org.apache.batik.gvt.renderer.ImageRenderer;
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
/*     */ public class RepaintManager
/*     */ {
/*     */   static final int COPY_OVERHEAD = 10000;
/*     */   static final int COPY_LINE_OVERHEAD = 10;
/*     */   protected ImageRenderer renderer;
/*     */   
/*     */   public RepaintManager(ImageRenderer r) {
/*  53 */     this.renderer = r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection updateRendering(Collection areas) throws InterruptedException {
/*  63 */     this.renderer.flush(areas);
/*  64 */     List<Rectangle> rects = new ArrayList(areas.size());
/*  65 */     AffineTransform at = this.renderer.getTransform();
/*     */     
/*  67 */     for (Object area : areas) {
/*  68 */       Shape s = (Shape)area;
/*  69 */       s = at.createTransformedShape(s);
/*  70 */       Rectangle2D r2d = s.getBounds2D();
/*  71 */       int x0 = (int)Math.floor(r2d.getX());
/*  72 */       int y0 = (int)Math.floor(r2d.getY());
/*  73 */       int x1 = (int)Math.ceil(r2d.getX() + r2d.getWidth());
/*  74 */       int y1 = (int)Math.ceil(r2d.getY() + r2d.getHeight());
/*     */ 
/*     */       
/*  77 */       Rectangle r = new Rectangle(x0 - 1, y0 - 1, x1 - x0 + 3, y1 - y0 + 3);
/*     */       
/*  79 */       rects.add(r);
/*     */     } 
/*  81 */     RectListManager devRLM = null;
/*     */     try {
/*  83 */       devRLM = new RectListManager(rects);
/*  84 */       devRLM.mergeRects(10000, 10);
/*  85 */     } catch (Exception e) {
/*  86 */       e.printStackTrace();
/*     */     } 
/*     */     
/*  89 */     this.renderer.repaint(devRLM);
/*  90 */     return (Collection)devRLM;
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
/*     */   public void setupRenderer(AffineTransform u2d, boolean dbr, Shape aoi, int width, int height) {
/* 108 */     this.renderer.setTransform(u2d);
/* 109 */     this.renderer.setDoubleBuffered(dbr);
/* 110 */     this.renderer.updateOffScreen(width, height);
/* 111 */     this.renderer.clearOffScreen();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getOffScreen() {
/* 119 */     return this.renderer.getOffScreen();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/RepaintManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */