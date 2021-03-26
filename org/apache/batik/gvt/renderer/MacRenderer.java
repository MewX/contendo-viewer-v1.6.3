/*     */ package org.apache.batik.gvt.renderer;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.batik.ext.awt.geom.RectListManager;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.gvt.GraphicsNode;
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
/*     */ public class MacRenderer
/*     */   implements ImageRenderer
/*     */ {
/*     */   static final int COPY_OVERHEAD = 1000;
/*     */   static final int COPY_LINE_OVERHEAD = 10;
/*  47 */   static final AffineTransform IDENTITY = new AffineTransform();
/*     */   
/*     */   protected RenderingHints renderingHints;
/*     */   
/*     */   protected AffineTransform usr2dev;
/*     */   
/*     */   protected GraphicsNode rootGN;
/*     */   
/*     */   protected int offScreenWidth;
/*     */   protected int offScreenHeight;
/*     */   protected boolean isDoubleBuffered;
/*     */   protected BufferedImage currImg;
/*     */   protected BufferedImage workImg;
/*     */   protected RectListManager damagedAreas;
/*  61 */   public static int IMAGE_TYPE = 3;
/*  62 */   public static Color TRANSPARENT_WHITE = new Color(255, 255, 255, 0);
/*     */ 
/*     */ 
/*     */   
/*  66 */   protected static RenderingHints defaultRenderingHints = new RenderingHints(null); static {
/*  67 */     defaultRenderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */ 
/*     */     
/*  70 */     defaultRenderingHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MacRenderer() {
/*  78 */     this.renderingHints = new RenderingHints(null);
/*  79 */     this.renderingHints.add(defaultRenderingHints);
/*  80 */     this.usr2dev = new AffineTransform();
/*     */   }
/*     */ 
/*     */   
/*     */   public MacRenderer(RenderingHints rh, AffineTransform at) {
/*  85 */     this.renderingHints = new RenderingHints(null);
/*  86 */     this.renderingHints.add(rh);
/*  87 */     if (at == null) { this.usr2dev = new AffineTransform(); }
/*  88 */     else { this.usr2dev = new AffineTransform(at); }
/*     */   
/*     */   }
/*     */   public void dispose() {
/*  92 */     this.rootGN = null;
/*  93 */     this.currImg = null;
/*  94 */     this.workImg = null;
/*  95 */     this.renderingHints = null;
/*  96 */     this.usr2dev = null;
/*  97 */     if (this.damagedAreas != null) {
/*  98 */       this.damagedAreas.clear();
/*     */     }
/* 100 */     this.damagedAreas = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTree(GraphicsNode treeRoot) {
/* 108 */     this.rootGN = treeRoot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsNode getTree() {
/* 115 */     return this.rootGN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransform(AffineTransform usr2dev) {
/* 123 */     if (usr2dev == null) {
/* 124 */       this.usr2dev = new AffineTransform();
/*     */     } else {
/* 126 */       this.usr2dev = new AffineTransform(usr2dev);
/* 127 */     }  if (this.workImg == null)
/* 128 */       return;  synchronized (this.workImg) {
/* 129 */       Graphics2D g2d = this.workImg.createGraphics();
/* 130 */       g2d.setComposite(AlphaComposite.Clear);
/* 131 */       g2d.fillRect(0, 0, this.workImg.getWidth(), this.workImg.getHeight());
/* 132 */       g2d.dispose();
/*     */     } 
/* 134 */     this.damagedAreas = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getTransform() {
/* 142 */     return this.usr2dev;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenderingHints(RenderingHints rh) {
/* 149 */     this.renderingHints = new RenderingHints(null);
/* 150 */     this.renderingHints.add(rh);
/* 151 */     this.damagedAreas = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderingHints getRenderingHints() {
/* 159 */     return this.renderingHints;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDoubleBuffered() {
/* 168 */     return this.isDoubleBuffered;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDoubleBuffered(boolean isDoubleBuffered) {
/* 179 */     if (this.isDoubleBuffered == isDoubleBuffered) {
/*     */       return;
/*     */     }
/* 182 */     this.isDoubleBuffered = isDoubleBuffered;
/* 183 */     if (isDoubleBuffered) {
/* 184 */       this.workImg = null;
/*     */     } else {
/*     */       
/* 187 */       this.workImg = this.currImg;
/* 188 */       this.damagedAreas = null;
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
/*     */   public void updateOffScreen(int width, int height) {
/* 201 */     this.offScreenWidth = width;
/* 202 */     this.offScreenHeight = height;
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
/*     */   public BufferedImage getOffScreen() {
/* 217 */     if (this.rootGN == null) {
/* 218 */       return null;
/*     */     }
/* 220 */     return this.currImg;
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
/*     */   public void clearOffScreen() {
/* 237 */     if (this.isDoubleBuffered) {
/*     */       return;
/*     */     }
/* 240 */     updateWorkingBuffers();
/* 241 */     if (this.workImg == null)
/*     */       return; 
/* 243 */     synchronized (this.workImg) {
/* 244 */       Graphics2D g2d = this.workImg.createGraphics();
/* 245 */       g2d.setComposite(AlphaComposite.Clear);
/* 246 */       g2d.fillRect(0, 0, this.workImg.getWidth(), this.workImg.getHeight());
/* 247 */       g2d.dispose();
/*     */     } 
/* 249 */     this.damagedAreas = null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush(Rectangle r) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush(Collection areas) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateWorkingBuffers() {
/* 267 */     if (this.rootGN == null) {
/* 268 */       this.currImg = null;
/* 269 */       this.workImg = null;
/*     */       
/*     */       return;
/*     */     } 
/* 273 */     int w = this.offScreenWidth;
/* 274 */     int h = this.offScreenHeight;
/* 275 */     if (this.workImg == null || this.workImg.getWidth() < w || this.workImg.getHeight() < h)
/*     */     {
/*     */       
/* 278 */       this.workImg = new BufferedImage(w, h, IMAGE_TYPE);
/*     */     }
/*     */ 
/*     */     
/* 282 */     if (!this.isDoubleBuffered) {
/* 283 */       this.currImg = this.workImg;
/*     */     }
/*     */   }
/*     */   
/*     */   public void repaint(Shape area) {
/* 288 */     if (area == null)
/* 289 */       return;  RectListManager rlm = new RectListManager();
/* 290 */     rlm.add(this.usr2dev.createTransformedShape(area).getBounds());
/* 291 */     repaint(rlm);
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
/*     */   public void repaint(RectListManager devRLM) {
/* 309 */     if (devRLM == null) {
/*     */       return;
/*     */     }
/* 312 */     updateWorkingBuffers();
/* 313 */     if (this.rootGN == null || this.workImg == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 318 */     try { synchronized (this.workImg) {
/* 319 */         Graphics2D g2d = GraphicsUtil.createGraphics(this.workImg, this.renderingHints);
/*     */ 
/*     */ 
/*     */         
/* 323 */         Rectangle dr = new Rectangle(0, 0, this.offScreenWidth, this.offScreenHeight);
/*     */         
/* 325 */         if (this.isDoubleBuffered && this.currImg != null && this.damagedAreas != null) {
/*     */ 
/*     */ 
/*     */           
/* 329 */           this.damagedAreas.subtract(devRLM, 1000, 10);
/*     */ 
/*     */           
/* 332 */           this.damagedAreas.mergeRects(1000, 10);
/*     */ 
/*     */           
/* 335 */           Iterator<Rectangle> iter = this.damagedAreas.iterator();
/* 336 */           g2d.setComposite(AlphaComposite.Src);
/* 337 */           while (iter.hasNext()) {
/* 338 */             Rectangle r = iter.next();
/* 339 */             if (!dr.intersects(r))
/* 340 */               continue;  r = dr.intersection(r);
/* 341 */             g2d.setClip(r.x, r.y, r.width, r.height);
/* 342 */             g2d.setComposite(AlphaComposite.Clear);
/* 343 */             g2d.fillRect(r.x, r.y, r.width, r.height);
/* 344 */             g2d.setComposite(AlphaComposite.SrcOver);
/* 345 */             g2d.drawImage(this.currImg, 0, 0, (ImageObserver)null);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 350 */         for (Object aDevRLM : devRLM) {
/* 351 */           Rectangle r = (Rectangle)aDevRLM;
/* 352 */           if (!dr.intersects(r))
/* 353 */             continue;  r = dr.intersection(r);
/* 354 */           g2d.setTransform(IDENTITY);
/* 355 */           g2d.setClip(r.x, r.y, r.width, r.height);
/* 356 */           g2d.setComposite(AlphaComposite.Clear);
/* 357 */           g2d.fillRect(r.x, r.y, r.width, r.height);
/* 358 */           g2d.setComposite(AlphaComposite.SrcOver);
/* 359 */           g2d.transform(this.usr2dev);
/* 360 */           this.rootGN.paint(g2d);
/*     */         } 
/* 362 */         g2d.dispose();
/*     */       }  }
/* 364 */     catch (Throwable t) { t.printStackTrace(); }
/* 365 */      if (HaltingThread.hasBeenHalted()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 372 */     if (this.isDoubleBuffered) {
/* 373 */       BufferedImage tmpImg = this.workImg;
/* 374 */       this.workImg = this.currImg;
/* 375 */       this.currImg = tmpImg;
/* 376 */       this.damagedAreas = devRLM;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/renderer/MacRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */