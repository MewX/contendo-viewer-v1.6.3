/*      */ package org.apache.batik.ext.awt.image;
/*      */ 
/*      */ import java.awt.Composite;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.GraphicsDevice;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentSampleModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DataBufferShort;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.DirectColorModel;
/*      */ import java.awt.image.ImageObserver;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.SinglePixelPackedSampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.awt.image.renderable.RenderContext;
/*      */ import java.awt.image.renderable.RenderableImage;
/*      */ import java.lang.ref.Reference;
/*      */ import java.lang.ref.WeakReference;
/*      */ import org.apache.batik.ext.awt.RenderingHintsKeyExt;
/*      */ import org.apache.batik.ext.awt.image.renderable.PaintRable;
/*      */ import org.apache.batik.ext.awt.image.rendered.AffineRed;
/*      */ import org.apache.batik.ext.awt.image.rendered.Any2LsRGBRed;
/*      */ import org.apache.batik.ext.awt.image.rendered.Any2sRGBRed;
/*      */ import org.apache.batik.ext.awt.image.rendered.BufferedImageCachableRed;
/*      */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*      */ import org.apache.batik.ext.awt.image.rendered.FormatRed;
/*      */ import org.apache.batik.ext.awt.image.rendered.RenderedImageCachableRed;
/*      */ import org.apache.batik.ext.awt.image.rendered.TranslateRed;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class GraphicsUtil
/*      */ {
/*   73 */   public static AffineTransform IDENTITY = new AffineTransform();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final boolean WARN_DESTINATION;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawImage(Graphics2D g2d, RenderedImage ri) {
/*   85 */     drawImage(g2d, wrap(ri));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawImage(Graphics2D g2d, CachableRed cr) {
/*      */     TranslateRed translateRed;
/*      */     CachableRed cachableRed;
/*      */     AffineRed affineRed;
/*  101 */     AffineTransform at = null;
/*      */     while (true) {
/*  103 */       while (cr instanceof AffineRed) {
/*  104 */         AffineRed ar = (AffineRed)cr;
/*  105 */         if (at == null) {
/*  106 */           at = ar.getTransform();
/*      */         } else {
/*  108 */           at.concatenate(ar.getTransform());
/*  109 */         }  cr = ar.getSource();
/*      */       } 
/*  111 */       if (cr instanceof TranslateRed) {
/*  112 */         TranslateRed tr = (TranslateRed)cr;
/*      */         
/*  114 */         int dx = tr.getDeltaX();
/*  115 */         int dy = tr.getDeltaY();
/*  116 */         if (at == null) {
/*  117 */           at = AffineTransform.getTranslateInstance(dx, dy);
/*      */         } else {
/*  119 */           at.translate(dx, dy);
/*  120 */         }  cr = tr.getSource();
/*      */         continue;
/*      */       } 
/*      */       break;
/*      */     } 
/*  125 */     AffineTransform g2dAt = g2d.getTransform();
/*  126 */     if (at == null || at.isIdentity()) {
/*  127 */       at = g2dAt;
/*      */     } else {
/*  129 */       at.preConcatenate(g2dAt);
/*      */     } 
/*  131 */     ColorModel srcCM = cr.getColorModel();
/*  132 */     ColorModel g2dCM = getDestinationColorModel(g2d);
/*  133 */     ColorSpace g2dCS = null;
/*  134 */     if (g2dCM != null)
/*  135 */       g2dCS = g2dCM.getColorSpace(); 
/*  136 */     if (g2dCS == null)
/*      */     {
/*  138 */       g2dCS = ColorSpace.getInstance(1000);
/*      */     }
/*  140 */     ColorModel drawCM = g2dCM;
/*  141 */     if (g2dCM == null || !g2dCM.hasAlpha())
/*      */     {
/*      */ 
/*      */       
/*  145 */       drawCM = sRGB_Unpre;
/*      */     }
/*      */     
/*  148 */     if (cr instanceof BufferedImageCachableRed)
/*      */     {
/*      */ 
/*      */       
/*  152 */       if (g2dCS.equals(srcCM.getColorSpace()) && drawCM.equals(srcCM)) {
/*      */ 
/*      */         
/*  155 */         g2d.setTransform(at);
/*      */         
/*  157 */         BufferedImageCachableRed bicr = (BufferedImageCachableRed)cr;
/*  158 */         g2d.drawImage(bicr.getBufferedImage(), bicr.getMinX(), bicr.getMinY(), (ImageObserver)null);
/*      */         
/*  160 */         g2d.setTransform(g2dAt);
/*      */         
/*      */         return;
/*      */       } 
/*      */     }
/*      */     
/*  166 */     double determinant = at.getDeterminant();
/*  167 */     if (!at.isIdentity() && determinant <= 1.0D) {
/*  168 */       if (at.getType() != 1) {
/*  169 */         affineRed = new AffineRed(cr, at, g2d.getRenderingHints());
/*      */       } else {
/*  171 */         int xloc = affineRed.getMinX() + (int)at.getTranslateX();
/*  172 */         int yloc = affineRed.getMinY() + (int)at.getTranslateY();
/*  173 */         translateRed = new TranslateRed((CachableRed)affineRed, xloc, yloc);
/*      */       } 
/*      */     }
/*      */     
/*  177 */     if (g2dCS != srcCM.getColorSpace())
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  185 */       if (g2dCS == ColorSpace.getInstance(1000)) {
/*  186 */         cachableRed = convertTosRGB((CachableRed)translateRed);
/*  187 */       } else if (g2dCS == ColorSpace.getInstance(1004)) {
/*  188 */         cachableRed = convertToLsRGB(cachableRed);
/*      */       }  } 
/*  190 */     srcCM = cachableRed.getColorModel();
/*  191 */     if (!drawCM.equals(srcCM)) {
/*  192 */       cachableRed = FormatRed.construct(cachableRed, drawCM);
/*      */     }
/*      */     
/*  195 */     if (!at.isIdentity() && determinant > 1.0D) {
/*  196 */       affineRed = new AffineRed(cachableRed, at, g2d.getRenderingHints());
/*      */     }
/*      */     
/*  199 */     g2d.setTransform(IDENTITY);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  205 */     Composite g2dComposite = g2d.getComposite();
/*  206 */     if (g2d.getRenderingHint(RenderingHintsKeyExt.KEY_TRANSCODING) == "Printing")
/*      */     {
/*  208 */       if (SVGComposite.OVER.equals(g2dComposite)) {
/*  209 */         g2d.setComposite(SVGComposite.OVER);
/*      */       }
/*      */     }
/*  212 */     Rectangle crR = affineRed.getBounds();
/*  213 */     Shape clip = g2d.getClip();
/*      */     
/*      */     try {
/*      */       Rectangle clipR;
/*  217 */       if (clip == null) {
/*  218 */         clip = crR;
/*  219 */         clipR = crR;
/*      */       } else {
/*  221 */         clipR = clip.getBounds();
/*      */         
/*  223 */         if (!clipR.intersects(crR))
/*      */           return; 
/*  225 */         clipR = clipR.intersection(crR);
/*      */       } 
/*      */       
/*  228 */       Rectangle gcR = getDestinationBounds(g2d);
/*      */       
/*  230 */       if (gcR != null) {
/*  231 */         if (!clipR.intersects(gcR))
/*      */           return; 
/*  233 */         clipR = clipR.intersection(gcR);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  239 */       boolean useDrawRenderedImage = false;
/*      */       
/*  241 */       srcCM = affineRed.getColorModel();
/*  242 */       SampleModel srcSM = affineRed.getSampleModel();
/*  243 */       if (srcSM.getWidth() * srcSM.getHeight() >= clipR.width * clipR.height)
/*      */       {
/*      */ 
/*      */         
/*  247 */         useDrawRenderedImage = true;
/*      */       }
/*  249 */       Object atpHint = g2d.getRenderingHint(RenderingHintsKeyExt.KEY_AVOID_TILE_PAINTING);
/*      */ 
/*      */       
/*  252 */       if (atpHint == RenderingHintsKeyExt.VALUE_AVOID_TILE_PAINTING_ON) {
/*  253 */         useDrawRenderedImage = true;
/*      */       }
/*  255 */       if (atpHint == RenderingHintsKeyExt.VALUE_AVOID_TILE_PAINTING_OFF) {
/*  256 */         useDrawRenderedImage = false;
/*      */       }
/*      */ 
/*      */       
/*  260 */       if (useDrawRenderedImage) {
/*      */ 
/*      */ 
/*      */         
/*  264 */         Raster r = affineRed.getData(clipR);
/*  265 */         WritableRaster wr = ((WritableRaster)r).createWritableChild(clipR.x, clipR.y, clipR.width, clipR.height, 0, 0, (int[])null);
/*      */ 
/*      */ 
/*      */         
/*  269 */         BufferedImage bi = new BufferedImage(srcCM, wr, srcCM.isAlphaPremultiplied(), null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  276 */         g2d.drawImage(bi, clipR.x, clipR.y, (ImageObserver)null);
/*      */       } else {
/*      */         
/*  279 */         WritableRaster wr = Raster.createWritableRaster(srcSM, new Point(0, 0));
/*  280 */         BufferedImage bi = new BufferedImage(srcCM, wr, srcCM.isAlphaPremultiplied(), null);
/*      */ 
/*      */         
/*  283 */         int xt0 = affineRed.getMinTileX();
/*  284 */         int xt1 = xt0 + affineRed.getNumXTiles();
/*  285 */         int yt0 = affineRed.getMinTileY();
/*  286 */         int yt1 = yt0 + affineRed.getNumYTiles();
/*  287 */         int tw = srcSM.getWidth();
/*  288 */         int th = srcSM.getHeight();
/*      */         
/*  290 */         Rectangle tR = new Rectangle(0, 0, tw, th);
/*  291 */         Rectangle iR = new Rectangle(0, 0, 0, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  305 */         int yloc = yt0 * th + affineRed.getTileGridYOffset();
/*  306 */         int skip = (clipR.y - yloc) / th;
/*  307 */         if (skip < 0) skip = 0; 
/*  308 */         yt0 += skip;
/*      */         
/*  310 */         int xloc = xt0 * tw + affineRed.getTileGridXOffset();
/*  311 */         skip = (clipR.x - xloc) / tw;
/*  312 */         if (skip < 0) skip = 0; 
/*  313 */         xt0 += skip;
/*      */         
/*  315 */         int endX = clipR.x + clipR.width - 1;
/*  316 */         int endY = clipR.y + clipR.height - 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  329 */         yloc = yt0 * th + affineRed.getTileGridYOffset();
/*  330 */         int minX = xt0 * tw + affineRed.getTileGridXOffset();
/*  331 */         int xStep = tw;
/*  332 */         xloc = minX;
/*  333 */         for (int y = yt0; y < yt1 && 
/*  334 */           yloc <= endY; y++, yloc += th) {
/*  335 */           for (int x = xt0; x < xt1 && 
/*  336 */             xloc >= minX && xloc <= endX; x++, xloc += xStep) {
/*  337 */             tR.x = xloc;
/*  338 */             tR.y = yloc;
/*  339 */             Rectangle2D.intersect(crR, tR, iR);
/*      */ 
/*      */             
/*  342 */             WritableRaster twr = wr.createWritableChild(0, 0, iR.width, iR.height, iR.x, iR.y, (int[])null);
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  347 */             affineRed.copyData(twr);
/*      */ 
/*      */ 
/*      */             
/*  351 */             BufferedImage subBI = bi.getSubimage(0, 0, iR.width, iR.height);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  361 */             g2d.drawImage(subBI, iR.x, iR.y, (ImageObserver)null);
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  370 */           xStep = -xStep;
/*  371 */           xloc += xStep;
/*      */         }
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     finally {
/*      */       
/*  379 */       g2d.setTransform(g2dAt);
/*  380 */       g2d.setComposite(g2dComposite);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawImage(Graphics2D g2d, RenderableImage filter, RenderContext rc) {
/*  406 */     AffineTransform origDev = g2d.getTransform();
/*  407 */     Shape origClip = g2d.getClip();
/*  408 */     RenderingHints origRH = g2d.getRenderingHints();
/*      */     
/*  410 */     Shape clip = rc.getAreaOfInterest();
/*  411 */     if (clip != null)
/*  412 */       g2d.clip(clip); 
/*  413 */     g2d.transform(rc.getTransform());
/*  414 */     g2d.setRenderingHints(rc.getRenderingHints());
/*      */     
/*  416 */     drawImage(g2d, filter);
/*      */     
/*  418 */     g2d.setTransform(origDev);
/*  419 */     g2d.setClip(origClip);
/*  420 */     g2d.setRenderingHints(origRH);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void drawImage(Graphics2D g2d, RenderableImage filter) {
/*  438 */     if (filter instanceof PaintRable) {
/*  439 */       PaintRable pr = (PaintRable)filter;
/*  440 */       if (pr.paintRable(g2d)) {
/*      */         return;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  447 */     AffineTransform at = g2d.getTransform();
/*  448 */     RenderedImage ri = filter.createRendering(new RenderContext(at, g2d.getClip(), g2d.getRenderingHints()));
/*      */ 
/*      */     
/*  451 */     if (ri == null) {
/*      */       return;
/*      */     }
/*  454 */     g2d.setTransform(IDENTITY);
/*  455 */     drawImage(g2d, wrap(ri));
/*  456 */     g2d.setTransform(at);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Graphics2D createGraphics(BufferedImage bi, RenderingHints hints) {
/*  472 */     Graphics2D g2d = bi.createGraphics();
/*  473 */     if (hints != null)
/*  474 */       g2d.addRenderingHints(hints); 
/*  475 */     g2d.setRenderingHint(RenderingHintsKeyExt.KEY_BUFFERED_IMAGE, new WeakReference<BufferedImage>(bi));
/*      */     
/*  477 */     g2d.clip(new Rectangle(0, 0, bi.getWidth(), bi.getHeight()));
/*  478 */     return g2d;
/*      */   }
/*      */ 
/*      */   
/*      */   public static Graphics2D createGraphics(BufferedImage bi) {
/*  483 */     Graphics2D g2d = bi.createGraphics();
/*  484 */     g2d.setRenderingHint(RenderingHintsKeyExt.KEY_BUFFERED_IMAGE, new WeakReference<BufferedImage>(bi));
/*      */     
/*  486 */     g2d.clip(new Rectangle(0, 0, bi.getWidth(), bi.getHeight()));
/*  487 */     return g2d;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static {
/*  494 */     boolean warn = true;
/*      */     
/*  496 */     try { String s = System.getProperty("org.apache.batik.warn_destination", "true");
/*      */       
/*  498 */       warn = Boolean.valueOf(s).booleanValue(); }
/*  499 */     catch (SecurityException securityException) {  }
/*  500 */     catch (NumberFormatException numberFormatException) {  }
/*      */     finally
/*  502 */     { WARN_DESTINATION = warn; }
/*      */   
/*      */   }
/*      */   
/*      */   public static BufferedImage getDestination(Graphics2D g2d) {
/*  507 */     Object o = g2d.getRenderingHint(RenderingHintsKeyExt.KEY_BUFFERED_IMAGE);
/*      */     
/*  509 */     if (o != null) {
/*  510 */       return ((Reference<BufferedImage>)o).get();
/*      */     }
/*      */     
/*  513 */     GraphicsConfiguration gc = g2d.getDeviceConfiguration();
/*  514 */     if (gc == null) {
/*  515 */       return null;
/*      */     }
/*      */     
/*  518 */     GraphicsDevice gd = gc.getDevice();
/*  519 */     if (WARN_DESTINATION && gd.getType() == 2 && g2d.getRenderingHint(RenderingHintsKeyExt.KEY_TRANSCODING) != "Printing")
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  524 */       System.err.println("Graphics2D from BufferedImage lacks BUFFERED_IMAGE hint");
/*      */     }
/*      */     
/*  527 */     return null;
/*      */   }
/*      */   
/*      */   public static ColorModel getDestinationColorModel(Graphics2D g2d) {
/*  531 */     BufferedImage bi = getDestination(g2d);
/*  532 */     if (bi != null) {
/*  533 */       return bi.getColorModel();
/*      */     }
/*      */     
/*  536 */     GraphicsConfiguration gc = g2d.getDeviceConfiguration();
/*  537 */     if (gc == null) {
/*  538 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  543 */     if (gc.getDevice().getType() == 2) {
/*  544 */       if (g2d.getRenderingHint(RenderingHintsKeyExt.KEY_TRANSCODING) == "Printing")
/*      */       {
/*  546 */         return sRGB_Unpre;
/*      */       }
/*      */ 
/*      */       
/*  550 */       return null;
/*      */     } 
/*      */     
/*  553 */     return gc.getColorModel();
/*      */   }
/*      */   
/*      */   public static ColorSpace getDestinationColorSpace(Graphics2D g2d) {
/*  557 */     ColorModel cm = getDestinationColorModel(g2d);
/*  558 */     if (cm != null) return cm.getColorSpace();
/*      */     
/*  560 */     return null;
/*      */   }
/*      */   
/*      */   public static Rectangle getDestinationBounds(Graphics2D g2d) {
/*  564 */     BufferedImage bi = getDestination(g2d);
/*  565 */     if (bi != null) {
/*  566 */       return new Rectangle(0, 0, bi.getWidth(), bi.getHeight());
/*      */     }
/*      */     
/*  569 */     GraphicsConfiguration gc = g2d.getDeviceConfiguration();
/*  570 */     if (gc == null) {
/*  571 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  576 */     if (gc.getDevice().getType() == 2) {
/*  577 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  582 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  588 */   public static final ColorModel Linear_sRGB = new DirectColorModel(ColorSpace.getInstance(1004), 24, 16711680, 65280, 255, 0, false, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  597 */   public static final ColorModel Linear_sRGB_Pre = new DirectColorModel(ColorSpace.getInstance(1004), 32, 16711680, 65280, 255, -16777216, true, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  606 */   public static final ColorModel Linear_sRGB_Unpre = new DirectColorModel(ColorSpace.getInstance(1004), 32, 16711680, 65280, 255, -16777216, false, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  616 */   public static final ColorModel sRGB = new DirectColorModel(ColorSpace.getInstance(1000), 24, 16711680, 65280, 255, 0, false, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  625 */   public static final ColorModel sRGB_Pre = new DirectColorModel(ColorSpace.getInstance(1000), 32, 16711680, 65280, 255, -16777216, true, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  634 */   public static final ColorModel sRGB_Unpre = new DirectColorModel(ColorSpace.getInstance(1000), 32, 16711680, 65280, 255, -16777216, false, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ColorModel makeLinear_sRGBCM(boolean premult) {
/*  651 */     return premult ? Linear_sRGB_Pre : Linear_sRGB_Unpre;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BufferedImage makeLinearBufferedImage(int width, int height, boolean premult) {
/*  664 */     ColorModel cm = makeLinear_sRGBCM(premult);
/*  665 */     WritableRaster wr = cm.createCompatibleWritableRaster(width, height);
/*  666 */     return new BufferedImage(cm, wr, premult, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static CachableRed convertToLsRGB(CachableRed src) {
/*  681 */     ColorModel cm = src.getColorModel();
/*  682 */     ColorSpace cs = cm.getColorSpace();
/*  683 */     if (cs == ColorSpace.getInstance(1004)) {
/*  684 */       return src;
/*      */     }
/*  686 */     return (CachableRed)new Any2LsRGBRed(src);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static CachableRed convertTosRGB(CachableRed src) {
/*  700 */     ColorModel cm = src.getColorModel();
/*  701 */     ColorSpace cs = cm.getColorSpace();
/*  702 */     if (cs == ColorSpace.getInstance(1000)) {
/*  703 */       return src;
/*      */     }
/*  705 */     return (CachableRed)new Any2sRGBRed(src);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static CachableRed wrap(RenderedImage ri) {
/*  722 */     if (ri instanceof CachableRed)
/*  723 */       return (CachableRed)ri; 
/*  724 */     if (ri instanceof BufferedImage)
/*  725 */       return (CachableRed)new BufferedImageCachableRed((BufferedImage)ri); 
/*  726 */     return (CachableRed)new RenderedImageCachableRed(ri);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyData_INT_PACK(Raster src, WritableRaster dst) {
/*  742 */     int x0 = dst.getMinX();
/*  743 */     if (x0 < src.getMinX()) x0 = src.getMinX();
/*      */     
/*  745 */     int y0 = dst.getMinY();
/*  746 */     if (y0 < src.getMinY()) y0 = src.getMinY();
/*      */     
/*  748 */     int x1 = dst.getMinX() + dst.getWidth() - 1;
/*  749 */     if (x1 > src.getMinX() + src.getWidth() - 1) {
/*  750 */       x1 = src.getMinX() + src.getWidth() - 1;
/*      */     }
/*  752 */     int y1 = dst.getMinY() + dst.getHeight() - 1;
/*  753 */     if (y1 > src.getMinY() + src.getHeight() - 1) {
/*  754 */       y1 = src.getMinY() + src.getHeight() - 1;
/*      */     }
/*  756 */     int width = x1 - x0 + 1;
/*  757 */     int height = y1 - y0 + 1;
/*      */ 
/*      */     
/*  760 */     SinglePixelPackedSampleModel srcSPPSM = (SinglePixelPackedSampleModel)src.getSampleModel();
/*      */     
/*  762 */     int srcScanStride = srcSPPSM.getScanlineStride();
/*  763 */     DataBufferInt srcDB = (DataBufferInt)src.getDataBuffer();
/*  764 */     int[] srcPixels = srcDB.getBankData()[0];
/*  765 */     int srcBase = srcDB.getOffset() + srcSPPSM.getOffset(x0 - src.getSampleModelTranslateX(), y0 - src.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  772 */     SinglePixelPackedSampleModel dstSPPSM = (SinglePixelPackedSampleModel)dst.getSampleModel();
/*      */     
/*  774 */     int dstScanStride = dstSPPSM.getScanlineStride();
/*  775 */     DataBufferInt dstDB = (DataBufferInt)dst.getDataBuffer();
/*  776 */     int[] dstPixels = dstDB.getBankData()[0];
/*  777 */     int dstBase = dstDB.getOffset() + dstSPPSM.getOffset(x0 - dst.getSampleModelTranslateX(), y0 - dst.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  782 */     if (srcScanStride == dstScanStride && srcScanStride == width) {
/*      */ 
/*      */ 
/*      */       
/*  786 */       System.arraycopy(srcPixels, srcBase, dstPixels, dstBase, width * height);
/*      */     }
/*  788 */     else if (width > 128) {
/*  789 */       int srcSP = srcBase;
/*  790 */       int dstSP = dstBase;
/*  791 */       for (int y = 0; y < height; y++) {
/*  792 */         System.arraycopy(srcPixels, srcSP, dstPixels, dstSP, width);
/*  793 */         srcSP += srcScanStride;
/*  794 */         dstSP += dstScanStride;
/*      */       } 
/*      */     } else {
/*  797 */       for (int y = 0; y < height; y++) {
/*  798 */         int srcSP = srcBase + y * srcScanStride;
/*  799 */         int dstSP = dstBase + y * dstScanStride;
/*  800 */         for (int x = 0; x < width; x++) {
/*  801 */           dstPixels[dstSP++] = srcPixels[srcSP++];
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public static void copyData_FALLBACK(Raster src, WritableRaster dst) {
/*  809 */     int x0 = dst.getMinX();
/*  810 */     if (x0 < src.getMinX()) x0 = src.getMinX();
/*      */     
/*  812 */     int y0 = dst.getMinY();
/*  813 */     if (y0 < src.getMinY()) y0 = src.getMinY();
/*      */     
/*  815 */     int x1 = dst.getMinX() + dst.getWidth() - 1;
/*  816 */     if (x1 > src.getMinX() + src.getWidth() - 1) {
/*  817 */       x1 = src.getMinX() + src.getWidth() - 1;
/*      */     }
/*  819 */     int y1 = dst.getMinY() + dst.getHeight() - 1;
/*  820 */     if (y1 > src.getMinY() + src.getHeight() - 1) {
/*  821 */       y1 = src.getMinY() + src.getHeight() - 1;
/*      */     }
/*  823 */     int width = x1 - x0 + 1;
/*  824 */     int[] data = null;
/*      */     
/*  826 */     for (int y = y0; y <= y1; y++) {
/*  827 */       data = src.getPixels(x0, y, width, 1, data);
/*  828 */       dst.setPixels(x0, y, width, 1, data);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyData(Raster src, WritableRaster dst) {
/*  841 */     if (is_INT_PACK_Data(src.getSampleModel(), false) && is_INT_PACK_Data(dst.getSampleModel(), false)) {
/*      */       
/*  843 */       copyData_INT_PACK(src, dst);
/*      */       
/*      */       return;
/*      */     } 
/*  847 */     copyData_FALLBACK(src, dst);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static WritableRaster copyRaster(Raster ras) {
/*  862 */     return copyRaster(ras, ras.getMinX(), ras.getMinY());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static WritableRaster copyRaster(Raster ras, int minX, int minY) {
/*  887 */     WritableRaster ret = Raster.createWritableRaster(ras.getSampleModel(), new Point(0, 0));
/*      */ 
/*      */     
/*  890 */     ret = ret.createWritableChild(ras.getMinX() - ras.getSampleModelTranslateX(), ras.getMinY() - ras.getSampleModelTranslateY(), ras.getWidth(), ras.getHeight(), minX, minY, (int[])null);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  897 */     DataBuffer srcDB = ras.getDataBuffer();
/*  898 */     DataBuffer retDB = ret.getDataBuffer();
/*  899 */     if (srcDB.getDataType() != retDB.getDataType()) {
/*  900 */       throw new IllegalArgumentException("New DataBuffer doesn't match original");
/*      */     }
/*      */     
/*  903 */     int len = srcDB.getSize();
/*  904 */     int banks = srcDB.getNumBanks();
/*  905 */     int[] offsets = srcDB.getOffsets();
/*  906 */     for (int b = 0; b < banks; b++) {
/*  907 */       DataBufferByte dataBufferByte1; DataBufferInt dataBufferInt1; DataBufferShort dataBufferShort1; DataBufferUShort srcDBT; DataBufferByte dataBufferByte2; DataBufferInt dataBufferInt2; DataBufferShort dataBufferShort2; DataBufferUShort retDBT; switch (srcDB.getDataType()) {
/*      */         case 0:
/*  909 */           dataBufferByte1 = (DataBufferByte)srcDB;
/*  910 */           dataBufferByte2 = (DataBufferByte)retDB;
/*  911 */           System.arraycopy(dataBufferByte1.getData(b), offsets[b], dataBufferByte2.getData(b), offsets[b], len);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 3:
/*  916 */           dataBufferInt1 = (DataBufferInt)srcDB;
/*  917 */           dataBufferInt2 = (DataBufferInt)retDB;
/*  918 */           System.arraycopy(dataBufferInt1.getData(b), offsets[b], dataBufferInt2.getData(b), offsets[b], len);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 2:
/*  923 */           dataBufferShort1 = (DataBufferShort)srcDB;
/*  924 */           dataBufferShort2 = (DataBufferShort)retDB;
/*  925 */           System.arraycopy(dataBufferShort1.getData(b), offsets[b], dataBufferShort2.getData(b), offsets[b], len);
/*      */           break;
/*      */ 
/*      */         
/*      */         case 1:
/*  930 */           srcDBT = (DataBufferUShort)srcDB;
/*  931 */           retDBT = (DataBufferUShort)retDB;
/*  932 */           System.arraycopy(srcDBT.getData(b), offsets[b], retDBT.getData(b), offsets[b], len);
/*      */           break;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/*  939 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static WritableRaster makeRasterWritable(Raster ras) {
/*  957 */     return makeRasterWritable(ras, ras.getMinX(), ras.getMinY());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static WritableRaster makeRasterWritable(Raster ras, int minX, int minY) {
/*  989 */     WritableRaster ret = Raster.createWritableRaster(ras.getSampleModel(), ras.getDataBuffer(), new Point(0, 0));
/*      */ 
/*      */ 
/*      */     
/*  993 */     ret = ret.createWritableChild(ras.getMinX() - ras.getSampleModelTranslateX(), ras.getMinY() - ras.getSampleModelTranslateY(), ras.getWidth(), ras.getHeight(), minX, minY, (int[])null);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  998 */     return ret;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ColorModel coerceColorModel(ColorModel cm, boolean newAlphaPreMult) {
/* 1011 */     if (cm.isAlphaPremultiplied() == newAlphaPreMult) {
/* 1012 */       return cm;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1017 */     WritableRaster wr = cm.createCompatibleWritableRaster(1, 1);
/* 1018 */     return cm.coerceData(wr, newAlphaPreMult);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ColorModel coerceData(WritableRaster wr, ColorModel cm, boolean newAlphaPreMult) {
/* 1035 */     if (!cm.hasAlpha())
/*      */     {
/* 1037 */       return cm;
/*      */     }
/* 1039 */     if (cm.isAlphaPremultiplied() == newAlphaPreMult)
/*      */     {
/* 1041 */       return cm;
/*      */     }
/*      */ 
/*      */     
/* 1045 */     if (newAlphaPreMult) {
/* 1046 */       multiplyAlpha(wr);
/*      */     } else {
/* 1048 */       divideAlpha(wr);
/*      */     } 
/*      */     
/* 1051 */     return coerceColorModel(cm, newAlphaPreMult);
/*      */   }
/*      */   
/*      */   public static void multiplyAlpha(WritableRaster wr) {
/* 1055 */     if (is_BYTE_COMP_Data(wr.getSampleModel())) {
/* 1056 */       mult_BYTE_COMP_Data(wr);
/* 1057 */     } else if (is_INT_PACK_Data(wr.getSampleModel(), true)) {
/* 1058 */       mult_INT_PACK_Data(wr);
/*      */     } else {
/* 1060 */       int[] pixel = null;
/* 1061 */       int bands = wr.getNumBands();
/* 1062 */       float norm = 0.003921569F;
/*      */ 
/*      */       
/* 1065 */       int x0 = wr.getMinX();
/* 1066 */       int x1 = x0 + wr.getWidth();
/* 1067 */       int y0 = wr.getMinY();
/* 1068 */       int y1 = y0 + wr.getHeight();
/* 1069 */       for (int y = y0; y < y1; y++) {
/* 1070 */         for (int x = x0; x < x1; x++) {
/* 1071 */           pixel = wr.getPixel(x, y, pixel);
/* 1072 */           int a = pixel[bands - 1];
/* 1073 */           if (a >= 0 && a < 255) {
/* 1074 */             float alpha = a * norm;
/* 1075 */             for (int b = 0; b < bands - 1; b++)
/* 1076 */               pixel[b] = (int)(pixel[b] * alpha + 0.5F); 
/* 1077 */             wr.setPixel(x, y, pixel);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   public static void divideAlpha(WritableRaster wr) {
/* 1084 */     if (is_BYTE_COMP_Data(wr.getSampleModel())) {
/* 1085 */       divide_BYTE_COMP_Data(wr);
/* 1086 */     } else if (is_INT_PACK_Data(wr.getSampleModel(), true)) {
/* 1087 */       divide_INT_PACK_Data(wr);
/*      */     }
/*      */     else {
/*      */       
/* 1091 */       int bands = wr.getNumBands();
/* 1092 */       int[] pixel = null;
/*      */       
/* 1094 */       int x0 = wr.getMinX();
/* 1095 */       int x1 = x0 + wr.getWidth();
/* 1096 */       int y0 = wr.getMinY();
/* 1097 */       int y1 = y0 + wr.getHeight();
/* 1098 */       for (int y = y0; y < y1; y++) {
/* 1099 */         for (int x = x0; x < x1; x++) {
/* 1100 */           pixel = wr.getPixel(x, y, pixel);
/* 1101 */           int a = pixel[bands - 1];
/* 1102 */           if (a > 0 && a < 255) {
/* 1103 */             float ialpha = 255.0F / a;
/* 1104 */             for (int b = 0; b < bands - 1; b++)
/* 1105 */               pixel[b] = (int)(pixel[b] * ialpha + 0.5F); 
/* 1106 */             wr.setPixel(x, y, pixel);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyData(BufferedImage src, BufferedImage dst) {
/* 1121 */     Rectangle srcRect = new Rectangle(0, 0, src.getWidth(), src.getHeight());
/*      */     
/* 1123 */     copyData(src, srcRect, dst, new Point(0, 0));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyData(BufferedImage src, Rectangle srcRect, BufferedImage dst, Point destP) {
/* 1146 */     boolean srcAlpha = src.getColorModel().hasAlpha();
/* 1147 */     boolean dstAlpha = dst.getColorModel().hasAlpha();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1155 */     if (srcAlpha == dstAlpha && (
/* 1156 */       !srcAlpha || src.isAlphaPremultiplied() == dst.isAlphaPremultiplied())) {
/*      */ 
/*      */       
/* 1159 */       copyData(src.getRaster(), dst.getRaster());
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1165 */     int[] pixel = null;
/* 1166 */     Raster srcR = src.getRaster();
/* 1167 */     WritableRaster dstR = dst.getRaster();
/* 1168 */     int bands = dstR.getNumBands();
/*      */     
/* 1170 */     int dx = destP.x - srcRect.x;
/* 1171 */     int dy = destP.y - srcRect.y;
/*      */     
/* 1173 */     int w = srcRect.width;
/* 1174 */     int x0 = srcRect.x;
/* 1175 */     int y0 = srcRect.y;
/* 1176 */     int y1 = y0 + srcRect.height - 1;
/*      */     
/* 1178 */     if (!srcAlpha) {
/*      */ 
/*      */       
/* 1181 */       int[] oPix = new int[bands * w];
/* 1182 */       int out = w * bands - 1;
/* 1183 */       while (out >= 0) {
/*      */         
/* 1185 */         oPix[out] = 255;
/* 1186 */         out -= bands;
/*      */       } 
/*      */ 
/*      */       
/* 1190 */       for (int y = y0; y <= y1; y++) {
/* 1191 */         pixel = srcR.getPixels(x0, y, w, 1, pixel);
/* 1192 */         int in = w * (bands - 1) - 1;
/* 1193 */         out = w * bands - 2;
/* 1194 */         switch (bands) {
/*      */           case 4:
/* 1196 */             while (in >= 0) {
/* 1197 */               oPix[out--] = pixel[in--];
/* 1198 */               oPix[out--] = pixel[in--];
/* 1199 */               oPix[out--] = pixel[in--];
/* 1200 */               out--;
/*      */             } 
/*      */             break;
/*      */           default:
/* 1204 */             while (in >= 0) {
/* 1205 */               for (int b = 0; b < bands - 1; b++)
/* 1206 */                 oPix[out--] = pixel[in--]; 
/* 1207 */               out--;
/*      */             }  break;
/*      */         } 
/* 1210 */         dstR.setPixels(x0 + dx, y + dy, w, 1, oPix);
/*      */       } 
/* 1212 */     } else if (dstAlpha && dst.isAlphaPremultiplied()) {
/*      */ 
/*      */       
/* 1215 */       int fpNorm = 65793, pt5 = 8388608;
/* 1216 */       for (int y = y0; y <= y1; y++) {
/* 1217 */         pixel = srcR.getPixels(x0, y, w, 1, pixel);
/* 1218 */         int in = bands * w - 1;
/* 1219 */         switch (bands) {
/*      */           case 4:
/* 1221 */             while (in >= 0) {
/* 1222 */               int a = pixel[in];
/* 1223 */               if (a == 255) {
/* 1224 */                 in -= 4; continue;
/*      */               } 
/* 1226 */               in--;
/* 1227 */               int alpha = fpNorm * a;
/* 1228 */               pixel[in] = pixel[in] * alpha + pt5 >>> 24; in--;
/* 1229 */               pixel[in] = pixel[in] * alpha + pt5 >>> 24; in--;
/* 1230 */               pixel[in] = pixel[in] * alpha + pt5 >>> 24; in--;
/*      */             } 
/*      */             break;
/*      */           
/*      */           default:
/* 1235 */             while (in >= 0) {
/* 1236 */               int i = pixel[in];
/* 1237 */               if (i == 255) {
/* 1238 */                 in -= bands; continue;
/*      */               } 
/* 1240 */               in--;
/* 1241 */               int j = fpNorm * i;
/* 1242 */               for (int b = 0; b < bands - 1; b++) {
/* 1243 */                 pixel[in] = pixel[in] * j + pt5 >>> 24;
/* 1244 */                 in--;
/*      */               } 
/*      */             } 
/*      */             break;
/*      */         } 
/* 1249 */         dstR.setPixels(x0 + dx, y + dy, w, 1, pixel);
/*      */       } 
/* 1251 */     } else if (dstAlpha && !dst.isAlphaPremultiplied()) {
/*      */ 
/*      */       
/* 1254 */       int fpNorm = 16711680, pt5 = 32768;
/* 1255 */       for (int y = y0; y <= y1; y++) {
/* 1256 */         pixel = srcR.getPixels(x0, y, w, 1, pixel);
/* 1257 */         int in = bands * w - 1;
/* 1258 */         switch (bands) {
/*      */           case 4:
/* 1260 */             while (in >= 0) {
/* 1261 */               int a = pixel[in];
/* 1262 */               if (a <= 0 || a >= 255) {
/* 1263 */                 in -= 4; continue;
/*      */               } 
/* 1265 */               in--;
/* 1266 */               int ialpha = fpNorm / a;
/* 1267 */               pixel[in] = pixel[in] * ialpha + pt5 >>> 16; in--;
/* 1268 */               pixel[in] = pixel[in] * ialpha + pt5 >>> 16; in--;
/* 1269 */               pixel[in] = pixel[in] * ialpha + pt5 >>> 16; in--;
/*      */             } 
/*      */             break;
/*      */           
/*      */           default:
/* 1274 */             while (in >= 0) {
/* 1275 */               int i = pixel[in];
/* 1276 */               if (i <= 0 || i >= 255) {
/* 1277 */                 in -= bands; continue;
/*      */               } 
/* 1279 */               in--;
/* 1280 */               int j = fpNorm / i;
/* 1281 */               for (int b = 0; b < bands - 1; b++) {
/* 1282 */                 pixel[in] = pixel[in] * j + pt5 >>> 16;
/* 1283 */                 in--;
/*      */               } 
/*      */             } 
/*      */             break;
/*      */         } 
/* 1288 */         dstR.setPixels(x0 + dx, y + dy, w, 1, pixel);
/*      */       } 
/* 1290 */     } else if (src.isAlphaPremultiplied()) {
/* 1291 */       int[] oPix = new int[bands * w];
/*      */ 
/*      */       
/* 1294 */       int fpNorm = 16711680, pt5 = 32768;
/* 1295 */       for (int y = y0; y <= y1; y++) {
/* 1296 */         pixel = srcR.getPixels(x0, y, w, 1, pixel);
/* 1297 */         int in = (bands + 1) * w - 1;
/* 1298 */         int out = bands * w - 1;
/* 1299 */         while (in >= 0) {
/* 1300 */           int a = pixel[in]; in--;
/* 1301 */           if (a > 0) {
/* 1302 */             if (a < 255) {
/* 1303 */               int ialpha = fpNorm / a;
/* 1304 */               for (int j = 0; j < bands; j++)
/* 1305 */                 oPix[out--] = pixel[in--] * ialpha + pt5 >>> 16;  continue;
/*      */             } 
/* 1307 */             for (int i = 0; i < bands; i++)
/* 1308 */               oPix[out--] = pixel[in--];  continue;
/*      */           } 
/* 1310 */           in -= bands;
/* 1311 */           for (int b = 0; b < bands; b++) {
/* 1312 */             oPix[out--] = 255;
/*      */           }
/*      */         } 
/* 1315 */         dstR.setPixels(x0 + dx, y + dy, w, 1, oPix);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1320 */       Rectangle dstRect = new Rectangle(destP.x, destP.y, srcRect.width, srcRect.height);
/*      */       
/* 1322 */       for (int b = 0; b < bands; b++) {
/* 1323 */         copyBand(srcR, srcRect, b, dstR, dstRect, b);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static void copyBand(Raster src, int srcBand, WritableRaster dst, int dstBand) {
/* 1331 */     Rectangle sR = src.getBounds();
/* 1332 */     Rectangle dR = dst.getBounds();
/* 1333 */     Rectangle cpR = sR.intersection(dR);
/*      */     
/* 1335 */     copyBand(src, cpR, srcBand, dst, cpR, dstBand);
/*      */   }
/*      */ 
/*      */   
/*      */   public static void copyBand(Raster src, Rectangle sR, int sBand, WritableRaster dst, Rectangle dR, int dBand) {
/* 1340 */     int width, height, dy = dR.y - sR.y;
/* 1341 */     int dx = dR.x - sR.x;
/* 1342 */     sR = sR.intersection(src.getBounds());
/* 1343 */     dR = dR.intersection(dst.getBounds());
/*      */     
/* 1345 */     if (dR.width < sR.width) { width = dR.width; }
/* 1346 */     else { width = sR.width; }
/* 1347 */      if (dR.height < sR.height) { height = dR.height; }
/* 1348 */     else { height = sR.height; }
/*      */     
/* 1350 */     int x = sR.x + dx;
/* 1351 */     int[] samples = null;
/* 1352 */     for (int y = sR.y; y < sR.y + height; y++) {
/* 1353 */       samples = src.getSamples(sR.x, y, width, 1, sBand, samples);
/* 1354 */       dst.setSamples(x, y + dy, width, 1, dBand, samples);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean is_INT_PACK_Data(SampleModel sm, boolean requireAlpha) {
/* 1361 */     if (!(sm instanceof SinglePixelPackedSampleModel)) return false;
/*      */ 
/*      */     
/* 1364 */     if (sm.getDataType() != 3) return false;
/*      */ 
/*      */     
/* 1367 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)sm;
/*      */     
/* 1369 */     int[] masks = sppsm.getBitMasks();
/* 1370 */     if (masks.length == 3) {
/* 1371 */       if (requireAlpha) return false; 
/* 1372 */     } else if (masks.length != 4) {
/* 1373 */       return false;
/*      */     } 
/* 1375 */     if (masks[0] != 16711680) return false; 
/* 1376 */     if (masks[1] != 65280) return false; 
/* 1377 */     if (masks[2] != 255) return false; 
/* 1378 */     if (masks.length == 4 && masks[3] != -16777216) {
/* 1379 */       return false;
/*      */     }
/* 1381 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean is_BYTE_COMP_Data(SampleModel sm) {
/* 1386 */     if (!(sm instanceof ComponentSampleModel)) return false;
/*      */ 
/*      */     
/* 1389 */     if (sm.getDataType() != 0) return false;
/*      */     
/* 1391 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void divide_INT_PACK_Data(WritableRaster wr) {
/* 1398 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)wr.getSampleModel();
/*      */     
/* 1400 */     int width = wr.getWidth();
/*      */     
/* 1402 */     int scanStride = sppsm.getScanlineStride();
/* 1403 */     DataBufferInt db = (DataBufferInt)wr.getDataBuffer();
/* 1404 */     int base = db.getOffset() + sppsm.getOffset(wr.getMinX() - wr.getSampleModelTranslateX(), wr.getMinY() - wr.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1410 */     int[] pixels = db.getBankData()[0];
/* 1411 */     for (int y = 0; y < wr.getHeight(); y++) {
/* 1412 */       int sp = base + y * scanStride;
/* 1413 */       int end = sp + width;
/* 1414 */       while (sp < end) {
/* 1415 */         int pixel = pixels[sp];
/* 1416 */         int a = pixel >>> 24;
/* 1417 */         if (a <= 0) {
/* 1418 */           pixels[sp] = 16777215;
/* 1419 */         } else if (a < 255) {
/* 1420 */           int aFP = 16711680 / a;
/* 1421 */           pixels[sp] = a << 24 | ((pixel & 0xFF0000) >> 16) * aFP & 0xFF0000 | (((pixel & 0xFF00) >> 8) * aFP & 0xFF0000) >> 8 | ((pixel & 0xFF) * aFP & 0xFF0000) >> 16;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1427 */         sp++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void mult_INT_PACK_Data(WritableRaster wr) {
/* 1436 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)wr.getSampleModel();
/*      */     
/* 1438 */     int width = wr.getWidth();
/*      */     
/* 1440 */     int scanStride = sppsm.getScanlineStride();
/* 1441 */     DataBufferInt db = (DataBufferInt)wr.getDataBuffer();
/* 1442 */     int base = db.getOffset() + sppsm.getOffset(wr.getMinX() - wr.getSampleModelTranslateX(), wr.getMinY() - wr.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1447 */     int[] pixels = db.getBankData()[0];
/* 1448 */     for (int y = 0; y < wr.getHeight(); y++) {
/* 1449 */       int sp = base + y * scanStride;
/* 1450 */       int end = sp + width;
/* 1451 */       while (sp < end) {
/* 1452 */         int pixel = pixels[sp];
/* 1453 */         int a = pixel >>> 24;
/* 1454 */         if (a >= 0 && a < 255) {
/* 1455 */           pixels[sp] = a << 24 | (pixel & 0xFF0000) * a >> 8 & 0xFF0000 | (pixel & 0xFF00) * a >> 8 & 0xFF00 | (pixel & 0xFF) * a >> 8 & 0xFF;
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1460 */         sp++;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void divide_BYTE_COMP_Data(WritableRaster wr) {
/* 1470 */     ComponentSampleModel csm = (ComponentSampleModel)wr.getSampleModel();
/*      */     
/* 1472 */     int width = wr.getWidth();
/*      */     
/* 1474 */     int scanStride = csm.getScanlineStride();
/* 1475 */     int pixStride = csm.getPixelStride();
/* 1476 */     int[] bandOff = csm.getBandOffsets();
/*      */     
/* 1478 */     DataBufferByte db = (DataBufferByte)wr.getDataBuffer();
/* 1479 */     int base = db.getOffset() + csm.getOffset(wr.getMinX() - wr.getSampleModelTranslateX(), wr.getMinY() - wr.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1484 */     int aOff = bandOff[bandOff.length - 1];
/* 1485 */     int bands = bandOff.length - 1;
/*      */ 
/*      */     
/* 1488 */     byte[] pixels = db.getBankData()[0];
/* 1489 */     for (int y = 0; y < wr.getHeight(); y++) {
/* 1490 */       int sp = base + y * scanStride;
/* 1491 */       int end = sp + width * pixStride;
/* 1492 */       while (sp < end) {
/* 1493 */         int a = pixels[sp + aOff] & 0xFF;
/* 1494 */         if (a == 0) {
/* 1495 */           for (int b = 0; b < bands; b++)
/* 1496 */             pixels[sp + bandOff[b]] = -1; 
/* 1497 */         } else if (a < 255) {
/* 1498 */           int aFP = 16711680 / a;
/* 1499 */           for (int b = 0; b < bands; b++) {
/* 1500 */             int i = sp + bandOff[b];
/* 1501 */             pixels[i] = (byte)((pixels[i] & 0xFF) * aFP >>> 16);
/*      */           } 
/*      */         } 
/* 1504 */         sp += pixStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static void mult_BYTE_COMP_Data(WritableRaster wr) {
/* 1513 */     ComponentSampleModel csm = (ComponentSampleModel)wr.getSampleModel();
/*      */     
/* 1515 */     int width = wr.getWidth();
/*      */     
/* 1517 */     int scanStride = csm.getScanlineStride();
/* 1518 */     int pixStride = csm.getPixelStride();
/* 1519 */     int[] bandOff = csm.getBandOffsets();
/*      */     
/* 1521 */     DataBufferByte db = (DataBufferByte)wr.getDataBuffer();
/* 1522 */     int base = db.getOffset() + csm.getOffset(wr.getMinX() - wr.getSampleModelTranslateX(), wr.getMinY() - wr.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1528 */     int aOff = bandOff[bandOff.length - 1];
/* 1529 */     int bands = bandOff.length - 1;
/*      */ 
/*      */     
/* 1532 */     byte[] pixels = db.getBankData()[0];
/* 1533 */     for (int y = 0; y < wr.getHeight(); y++) {
/* 1534 */       int sp = base + y * scanStride;
/* 1535 */       int end = sp + width * pixStride;
/* 1536 */       while (sp < end) {
/* 1537 */         int a = pixels[sp + aOff] & 0xFF;
/* 1538 */         if (a != 255)
/* 1539 */           for (int b = 0; b < bands; b++) {
/* 1540 */             int i = sp + bandOff[b];
/* 1541 */             pixels[i] = (byte)((pixels[i] & 0xFF) * a >> 8);
/*      */           }  
/* 1543 */         sp += pixStride;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/GraphicsUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */