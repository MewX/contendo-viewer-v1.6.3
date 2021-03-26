/*     */ package org.apache.batik.gvt.renderer;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Collection;
/*     */ import org.apache.batik.ext.awt.geom.RectListManager;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.PadRed;
/*     */ import org.apache.batik.util.HaltingThread;
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
/*     */ public class DynamicRenderer
/*     */   extends StaticRenderer
/*     */ {
/*     */   static final int COPY_OVERHEAD = 1000;
/*     */   static final int COPY_LINE_OVERHEAD = 10;
/*     */   RectListManager damagedAreas;
/*     */   
/*     */   public DynamicRenderer() {}
/*     */   
/*     */   public DynamicRenderer(RenderingHints rh, AffineTransform at) {
/*  59 */     super(rh, at);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected CachableRed setupCache(CachableRed img) {
/*  66 */     return img;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush(Rectangle r) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush(Collection areas) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateWorkingBuffers() {
/*  83 */     if (this.rootFilter == null) {
/*  84 */       this.rootFilter = this.rootGN.getGraphicsNodeRable(true);
/*  85 */       this.rootCR = null;
/*     */     } 
/*     */     
/*  88 */     this.rootCR = renderGNR();
/*  89 */     if (this.rootCR == null) {
/*     */       
/*  91 */       this.workingRaster = null;
/*  92 */       this.workingOffScreen = null;
/*  93 */       this.workingBaseRaster = null;
/*     */       
/*  95 */       this.currentOffScreen = null;
/*  96 */       this.currentBaseRaster = null;
/*  97 */       this.currentRaster = null;
/*     */       
/*     */       return;
/*     */     } 
/* 101 */     SampleModel sm = this.rootCR.getSampleModel();
/* 102 */     int w = this.offScreenWidth;
/* 103 */     int h = this.offScreenHeight;
/*     */     
/* 105 */     if (this.workingBaseRaster == null || this.workingBaseRaster.getWidth() < w || this.workingBaseRaster.getHeight() < h) {
/*     */ 
/*     */ 
/*     */       
/* 109 */       sm = sm.createCompatibleSampleModel(w, h);
/*     */       
/* 111 */       this.workingBaseRaster = Raster.createWritableRaster(sm, new Point(0, 0));
/*     */ 
/*     */       
/* 114 */       this.workingRaster = this.workingBaseRaster.createWritableChild(0, 0, w, h, 0, 0, (int[])null);
/*     */ 
/*     */       
/* 117 */       this.workingOffScreen = new BufferedImage(this.rootCR.getColorModel(), this.workingRaster, this.rootCR.getColorModel().isAlphaPremultiplied(), null);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 123 */     if (!this.isDoubleBuffered) {
/* 124 */       this.currentOffScreen = this.workingOffScreen;
/* 125 */       this.currentBaseRaster = this.workingBaseRaster;
/* 126 */       this.currentRaster = this.workingRaster;
/*     */     } 
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
/*     */   public void repaint(RectListManager devRLM) {
/*     */     PadRed padRed;
/* 145 */     if (devRLM == null) {
/*     */       return;
/*     */     }
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
/* 158 */     updateWorkingBuffers();
/* 159 */     if (this.rootCR == null || this.workingBaseRaster == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 165 */     CachableRed cr = this.rootCR;
/* 166 */     WritableRaster syncRaster = this.workingBaseRaster;
/* 167 */     WritableRaster copyRaster = this.workingRaster;
/*     */     
/* 169 */     Rectangle srcR = this.rootCR.getBounds();
/*     */     
/* 171 */     Rectangle dstR = this.workingRaster.getBounds();
/* 172 */     if (dstR.x < srcR.x || dstR.y < srcR.y || dstR.x + dstR.width > srcR.x + srcR.width || dstR.y + dstR.height > srcR.y + srcR.height)
/*     */     {
/*     */ 
/*     */       
/* 176 */       padRed = new PadRed(cr, dstR, PadMode.ZERO_PAD, null);
/*     */     }
/* 178 */     boolean repaintAll = false;
/*     */     
/* 180 */     Rectangle dr = copyRaster.getBounds();
/* 181 */     Rectangle sr = null;
/* 182 */     if (this.currentRaster != null) {
/* 183 */       sr = this.currentRaster.getBounds();
/*     */     }
/*     */ 
/*     */     
/* 187 */     synchronized (syncRaster) {
/*     */       
/* 189 */       if (repaintAll) {
/*     */         
/* 191 */         padRed.copyData(copyRaster);
/*     */       } else {
/* 193 */         Graphics2D g2d = null;
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
/* 205 */         if (this.isDoubleBuffered && this.currentRaster != null && this.damagedAreas != null) {
/*     */ 
/*     */           
/* 208 */           this.damagedAreas.subtract(devRLM, 1000, 10);
/*     */           
/* 210 */           this.damagedAreas.mergeRects(1000, 10);
/*     */ 
/*     */ 
/*     */           
/* 214 */           Color color1 = new Color(0, 0, 255, 50);
/* 215 */           Color color2 = new Color(0, 0, 0, 50);
/*     */           
/* 217 */           for (Object damagedArea : this.damagedAreas) {
/* 218 */             Rectangle r = (Rectangle)damagedArea;
/* 219 */             if (!dr.intersects(r))
/* 220 */               continue;  r = dr.intersection(r);
/* 221 */             if (sr != null && !sr.intersects(r))
/* 222 */               continue;  r = sr.intersection(r);
/*     */             
/* 224 */             Raster src = this.currentRaster.createWritableChild(r.x, r.y, r.width, r.height, r.x, r.y, (int[])null);
/*     */             
/* 226 */             GraphicsUtil.copyData(src, copyRaster);
/* 227 */             if (g2d != null) {
/* 228 */               g2d.setPaint(color1);
/* 229 */               g2d.fill(r);
/* 230 */               g2d.setPaint(color2);
/* 231 */               g2d.draw(r);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 236 */         Color fillColor = new Color(255, 0, 0, 50);
/* 237 */         Color borderColor = new Color(0, 0, 0, 50);
/*     */         
/* 239 */         for (Object aDevRLM : devRLM) {
/* 240 */           Rectangle r = (Rectangle)aDevRLM;
/* 241 */           if (!dr.intersects(r))
/* 242 */             continue;  r = dr.intersection(r);
/*     */ 
/*     */           
/* 245 */           WritableRaster dst = copyRaster.createWritableChild(r.x, r.y, r.width, r.height, r.x, r.y, (int[])null);
/*     */           
/* 247 */           padRed.copyData(dst);
/* 248 */           if (g2d != null) {
/* 249 */             g2d.setPaint(fillColor);
/* 250 */             g2d.fill(r);
/* 251 */             g2d.setPaint(borderColor);
/* 252 */             g2d.draw(r);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 258 */     if (HaltingThread.hasBeenHalted()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 266 */     BufferedImage tmpBI = this.workingOffScreen;
/*     */     
/* 268 */     this.workingBaseRaster = this.currentBaseRaster;
/* 269 */     this.workingRaster = this.currentRaster;
/* 270 */     this.workingOffScreen = this.currentOffScreen;
/*     */     
/* 272 */     this.currentRaster = copyRaster;
/* 273 */     this.currentBaseRaster = syncRaster;
/* 274 */     this.currentOffScreen = tmpBI;
/*     */     
/* 276 */     this.damagedAreas = devRLM;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/renderer/DynamicRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */