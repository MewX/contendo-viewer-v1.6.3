/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.image.AffineTransformOp;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.DirectColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
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
/*     */ public class AffineRed
/*     */   extends AbstractRed
/*     */ {
/*     */   RenderingHints hints;
/*     */   AffineTransform src2me;
/*     */   AffineTransform me2src;
/*     */   
/*     */   public AffineTransform getTransform() {
/*  55 */     return (AffineTransform)this.src2me.clone();
/*     */   }
/*     */   
/*     */   public CachableRed getSource() {
/*  59 */     return getSources().get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineRed(CachableRed src, AffineTransform src2me, RenderingHints hints) {
/*  67 */     this.src2me = src2me;
/*  68 */     this.hints = hints;
/*     */     
/*     */     try {
/*  71 */       this.me2src = src2me.createInverse();
/*  72 */     } catch (NoninvertibleTransformException nite) {
/*  73 */       this.me2src = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  78 */     Rectangle srcBounds = src.getBounds();
/*     */ 
/*     */     
/*  81 */     Rectangle myBounds = src2me.createTransformedShape(srcBounds).getBounds();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  87 */     ColorModel cm = fixColorModel(src);
/*     */ 
/*     */     
/*  90 */     SampleModel sm = fixSampleModel(src, cm, myBounds);
/*     */     
/*  92 */     Point2D pt = new Point2D.Float(src.getTileGridXOffset(), src.getTileGridYOffset());
/*     */     
/*  94 */     pt = src2me.transform(pt, null);
/*     */ 
/*     */     
/*  97 */     init(src, myBounds, cm, sm, (int)pt.getX(), (int)pt.getY(), (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 106 */     PadRed.ZeroRecter zr = PadRed.ZeroRecter.getZeroRecter(wr);
/* 107 */     zr.zeroRect(new Rectangle(wr.getMinX(), wr.getMinY(), wr.getWidth(), wr.getHeight()));
/*     */     
/* 109 */     genRect(wr);
/* 110 */     return wr;
/*     */   }
/*     */   
/*     */   public Raster getTile(int x, int y) {
/* 114 */     if (this.me2src == null) {
/* 115 */       return null;
/*     */     }
/* 117 */     int tx = this.tileGridXOff + x * this.tileWidth;
/* 118 */     int ty = this.tileGridYOff + y * this.tileHeight;
/* 119 */     Point pt = new Point(tx, ty);
/* 120 */     WritableRaster wr = Raster.createWritableRaster(this.sm, pt);
/* 121 */     genRect(wr);
/*     */     
/* 123 */     return wr;
/*     */   }
/*     */   
/*     */   public void genRect(WritableRaster wr) {
/* 127 */     if (this.me2src == null) {
/*     */       return;
/*     */     }
/* 130 */     Rectangle srcR = this.me2src.createTransformedShape(wr.getBounds()).getBounds();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 137 */     srcR.setBounds(srcR.x - 1, srcR.y - 1, srcR.width + 2, srcR.height + 2);
/*     */ 
/*     */     
/* 140 */     CachableRed src = getSources().get(0);
/*     */ 
/*     */ 
/*     */     
/* 144 */     if (!srcR.intersects(src.getBounds()))
/*     */       return; 
/* 146 */     Raster srcRas = src.getData(srcR.intersection(src.getBounds()));
/*     */     
/* 148 */     if (srcRas == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 153 */     AffineTransform aff = (AffineTransform)this.src2me.clone();
/*     */ 
/*     */ 
/*     */     
/* 157 */     aff.concatenate(AffineTransform.getTranslateInstance(srcRas.getMinX(), srcRas.getMinY()));
/*     */ 
/*     */     
/* 160 */     Point2D srcPt = new Point2D.Float(wr.getMinX(), wr.getMinY());
/* 161 */     srcPt = this.me2src.transform(srcPt, null);
/*     */     
/* 163 */     Point2D destPt = new Point2D.Double(srcPt.getX() - srcRas.getMinX(), srcPt.getY() - srcRas.getMinY());
/*     */ 
/*     */     
/* 166 */     destPt = aff.transform(destPt, null);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     aff.preConcatenate(AffineTransform.getTranslateInstance(-destPt.getX(), -destPt.getY()));
/*     */ 
/*     */     
/* 174 */     AffineTransformOp op = new AffineTransformOp(aff, this.hints);
/*     */ 
/*     */     
/* 177 */     ColorModel srcCM = src.getColorModel();
/* 178 */     ColorModel myCM = getColorModel();
/*     */     
/* 180 */     WritableRaster srcWR = (WritableRaster)srcRas;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     srcCM = GraphicsUtil.coerceData(srcWR, srcCM, true);
/* 186 */     BufferedImage srcBI = new BufferedImage(srcCM, srcWR.createWritableTranslatedChild(0, 0), srcCM.isAlphaPremultiplied(), null);
/*     */ 
/*     */ 
/*     */     
/* 190 */     BufferedImage myBI = new BufferedImage(myCM, wr.createWritableTranslatedChild(0, 0), myCM.isAlphaPremultiplied(), null);
/*     */ 
/*     */     
/* 193 */     op.filter(srcBI.getRaster(), myBI.getRaster());
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
/*     */   protected static ColorModel fixColorModel(CachableRed src) {
/* 205 */     ColorModel cm = src.getColorModel();
/*     */     
/* 207 */     if (cm.hasAlpha()) {
/* 208 */       if (!cm.isAlphaPremultiplied())
/* 209 */         cm = GraphicsUtil.coerceColorModel(cm, true); 
/* 210 */       return cm;
/*     */     } 
/*     */     
/* 213 */     ColorSpace cs = cm.getColorSpace();
/*     */     
/* 215 */     int b = src.getSampleModel().getNumBands() + 1;
/* 216 */     if (b == 4) {
/* 217 */       int[] masks = new int[4];
/* 218 */       for (int j = 0; j < b - 1; j++)
/* 219 */         masks[j] = 16711680 >> 8 * j; 
/* 220 */       masks[3] = 255 << 8 * (b - 1);
/*     */       
/* 222 */       return new DirectColorModel(cs, 8 * b, masks[0], masks[1], masks[2], masks[3], true, 3);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 227 */     int[] bits = new int[b];
/* 228 */     for (int i = 0; i < b; i++)
/* 229 */       bits[i] = 8; 
/* 230 */     return new ComponentColorModel(cs, bits, true, true, 3, 3);
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
/*     */   protected SampleModel fixSampleModel(CachableRed src, ColorModel cm, Rectangle bounds) {
/* 244 */     SampleModel sm = src.getSampleModel();
/* 245 */     int defSz = AbstractTiledRed.getDefaultTileSize();
/*     */     
/* 247 */     int w = sm.getWidth();
/* 248 */     if (w < defSz) w = defSz; 
/* 249 */     if (w > bounds.width) w = bounds.width; 
/* 250 */     int h = sm.getHeight();
/* 251 */     if (h < defSz) h = defSz; 
/* 252 */     if (h > bounds.height) h = bounds.height;
/*     */     
/* 254 */     if (w <= 0 || h <= 0) {
/* 255 */       w = 1;
/* 256 */       h = 1;
/*     */     } 
/*     */     
/* 259 */     return cm.createCompatibleSampleModel(w, h);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/AffineRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */