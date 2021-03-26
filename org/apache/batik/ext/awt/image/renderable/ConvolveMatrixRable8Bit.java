/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.BufferedImageOp;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ConvolveOp;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.DirectColorModel;
/*     */ import java.awt.image.Kernel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.rendered.AffineRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.BufferedImageCachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
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
/*     */ public class ConvolveMatrixRable8Bit
/*     */   extends AbstractColorInterpolationRable
/*     */   implements ConvolveMatrixRable
/*     */ {
/*     */   Kernel kernel;
/*     */   Point target;
/*     */   float bias;
/*     */   boolean kernelHasNegValues;
/*     */   PadMode edgeMode;
/*  68 */   float[] kernelUnitLength = new float[2];
/*     */   
/*     */   boolean preserveAlpha = false;
/*     */   
/*     */   public ConvolveMatrixRable8Bit(Filter source) {
/*  73 */     super(source);
/*     */   }
/*     */   
/*     */   public Filter getSource() {
/*  77 */     return getSources().get(0);
/*     */   }
/*     */   
/*     */   public void setSource(Filter src) {
/*  81 */     init(src);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Kernel getKernel() {
/*  89 */     return this.kernel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKernel(Kernel k) {
/*  97 */     touch();
/*  98 */     this.kernel = k;
/*  99 */     this.kernelHasNegValues = false;
/* 100 */     float[] kv = k.getKernelData(null);
/* 101 */     for (float aKv : kv) {
/* 102 */       if (aKv < 0.0F) {
/* 103 */         this.kernelHasNegValues = true;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public Point getTarget() {
/* 109 */     return (Point)this.target.clone();
/*     */   }
/*     */   
/*     */   public void setTarget(Point pt) {
/* 113 */     touch();
/* 114 */     this.target = (Point)pt.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getBias() {
/* 121 */     return this.bias;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBias(double bias) {
/* 128 */     touch();
/* 129 */     this.bias = (float)bias;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PadMode getEdgeMode() {
/* 136 */     return this.edgeMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEdgeMode(PadMode edgeMode) {
/* 143 */     touch();
/* 144 */     this.edgeMode = edgeMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getKernelUnitLength() {
/* 151 */     if (this.kernelUnitLength == null) {
/* 152 */       return null;
/*     */     }
/* 154 */     double[] ret = new double[2];
/* 155 */     ret[0] = this.kernelUnitLength[0];
/* 156 */     ret[1] = this.kernelUnitLength[1];
/* 157 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKernelUnitLength(double[] kernelUnitLength) {
/* 165 */     touch();
/* 166 */     if (kernelUnitLength == null) {
/* 167 */       this.kernelUnitLength = null;
/*     */       
/*     */       return;
/*     */     } 
/* 171 */     if (this.kernelUnitLength == null) {
/* 172 */       this.kernelUnitLength = new float[2];
/*     */     }
/* 174 */     this.kernelUnitLength[0] = (float)kernelUnitLength[0];
/* 175 */     this.kernelUnitLength[1] = (float)kernelUnitLength[1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getPreserveAlpha() {
/* 182 */     return this.preserveAlpha;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPreserveAlpha(boolean preserveAlpha) {
/* 191 */     touch();
/* 192 */     this.preserveAlpha = preserveAlpha;
/*     */   }
/*     */ 
/*     */   
/*     */   public void fixAlpha(BufferedImage bi) {
/* 197 */     if (!bi.getColorModel().hasAlpha() || !bi.isAlphaPremultiplied()) {
/*     */       return;
/*     */     }
/*     */     
/* 201 */     if (GraphicsUtil.is_INT_PACK_Data(bi.getSampleModel(), true)) {
/* 202 */       fixAlpha_INT_PACK(bi.getRaster());
/*     */     } else {
/* 204 */       fixAlpha_FALLBACK(bi.getRaster());
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fixAlpha_INT_PACK(WritableRaster wr) {
/* 209 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)wr.getSampleModel();
/*     */     
/* 211 */     int width = wr.getWidth();
/*     */     
/* 213 */     int scanStride = sppsm.getScanlineStride();
/* 214 */     DataBufferInt db = (DataBufferInt)wr.getDataBuffer();
/* 215 */     int base = db.getOffset() + sppsm.getOffset(wr.getMinX() - wr.getSampleModelTranslateX(), wr.getMinY() - wr.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 220 */     int[] pixels = db.getBankData()[0];
/* 221 */     for (int y = 0; y < wr.getHeight(); y++) {
/* 222 */       int sp = base + y * scanStride;
/* 223 */       int end = sp + width;
/* 224 */       while (sp < end) {
/* 225 */         int pixel = pixels[sp];
/* 226 */         int a = pixel >>> 24;
/* 227 */         int v = pixel >> 16 & 0xFF;
/* 228 */         if (a < v) a = v; 
/* 229 */         v = pixel >> 8 & 0xFF;
/* 230 */         if (a < v) a = v; 
/* 231 */         v = pixel & 0xFF;
/* 232 */         if (a < v) a = v; 
/* 233 */         pixels[sp] = pixel & 0xFFFFFF | a << 24;
/* 234 */         sp++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void fixAlpha_FALLBACK(WritableRaster wr) {
/* 240 */     int x0 = wr.getMinX();
/* 241 */     int w = wr.getWidth();
/* 242 */     int y0 = wr.getMinY();
/* 243 */     int y1 = y0 + wr.getHeight() - 1;
/* 244 */     int bands = wr.getNumBands();
/*     */     
/* 246 */     int[] pixel = null;
/* 247 */     for (int y = y0; y <= y1; y++) {
/* 248 */       pixel = wr.getPixels(x0, y, w, 1, pixel);
/* 249 */       int i = 0;
/* 250 */       for (int x = 0; x < w; x++) {
/* 251 */         int a = pixel[i];
/* 252 */         for (int b = 1; b < bands; b++) {
/* 253 */           if (pixel[i + b] > a) a = pixel[i + b]; 
/* 254 */         }  pixel[i + bands - 1] = a;
/* 255 */         i += bands;
/*     */       } 
/* 257 */       wr.setPixels(x0, y, w, 1, pixel);
/*     */     } 
/*     */   } public RenderedImage createRendering(RenderContext rc) {
/*     */     PadRed padRed2;
/*     */     AffineRed affineRed;
/*     */     BufferedImage destBI;
/* 263 */     RenderingHints rh = rc.getRenderingHints();
/* 264 */     if (rh == null) rh = new RenderingHints(null);
/*     */ 
/*     */     
/* 267 */     AffineTransform at = rc.getTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 273 */     double sx = at.getScaleX();
/* 274 */     double sy = at.getScaleY();
/*     */     
/* 276 */     double shx = at.getShearX();
/* 277 */     double shy = at.getShearY();
/*     */     
/* 279 */     double tx = at.getTranslateX();
/* 280 */     double ty = at.getTranslateY();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 286 */     double scaleX = Math.sqrt(sx * sx + shy * shy);
/* 287 */     double scaleY = Math.sqrt(sy * sy + shx * shx);
/*     */ 
/*     */ 
/*     */     
/* 291 */     if (this.kernelUnitLength != null) {
/* 292 */       if (this.kernelUnitLength[0] > 0.0D) {
/* 293 */         scaleX = (1.0F / this.kernelUnitLength[0]);
/*     */       }
/* 295 */       if (this.kernelUnitLength[1] > 0.0D) {
/* 296 */         scaleY = (1.0F / this.kernelUnitLength[1]);
/*     */       }
/*     */     } 
/* 299 */     Shape aoi = rc.getAreaOfInterest();
/* 300 */     if (aoi == null) {
/* 301 */       aoi = getBounds2D();
/*     */     }
/* 303 */     Rectangle2D r = aoi.getBounds2D();
/*     */     
/* 305 */     int kw = this.kernel.getWidth();
/* 306 */     int kh = this.kernel.getHeight();
/* 307 */     int kx = this.target.x;
/* 308 */     int ky = this.target.y;
/*     */ 
/*     */ 
/*     */     
/* 312 */     double rx0 = r.getX() - kx / scaleX;
/* 313 */     double ry0 = r.getY() - ky / scaleY;
/* 314 */     double rx1 = rx0 + r.getWidth() + (kw - 1) / scaleX;
/* 315 */     double ry1 = ry0 + r.getHeight() + (kh - 1) / scaleY;
/* 316 */     r = new Rectangle2D.Double(Math.floor(rx0), Math.floor(ry0), Math.ceil(rx1 - Math.floor(rx0)), Math.ceil(ry1 - Math.floor(ry0)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 325 */     AffineTransform srcAt = AffineTransform.getScaleInstance(scaleX, scaleY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 335 */     AffineTransform resAt = new AffineTransform(sx / scaleX, shy / scaleX, shx / scaleY, sy / scaleY, tx, ty);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 340 */     RenderedImage ri = getSource().createRendering(new RenderContext(srcAt, r, rh));
/* 341 */     if (ri == null) {
/* 342 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 348 */     CachableRed cr = convertSourceCS(ri);
/*     */     
/* 350 */     Shape devShape = srcAt.createTransformedShape(aoi);
/* 351 */     Rectangle2D devRect = devShape.getBounds2D();
/* 352 */     r = devRect;
/* 353 */     r = new Rectangle2D.Double(Math.floor(r.getX() - kx), Math.floor(r.getY() - ky), Math.ceil(r.getX() + r.getWidth()) - Math.floor(r.getX()) + (kw - 1), Math.ceil(r.getY() + r.getHeight()) - Math.floor(r.getY()) + (kh - 1));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 360 */     if (!r.getBounds().equals(cr.getBounds())) {
/* 361 */       if (this.edgeMode == PadMode.WRAP) {
/* 362 */         throw new IllegalArgumentException("edgeMode=\"wrap\" is not supported by ConvolveMatrix.");
/*     */       }
/* 364 */       padRed2 = new PadRed(cr, r.getBounds(), this.edgeMode, rh);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 371 */     if (this.bias != 0.0D) {
/* 372 */       throw new IllegalArgumentException("Only bias equal to zero is supported in ConvolveMatrix.");
/*     */     }
/*     */     
/* 375 */     BufferedImageOp op = new ConvolveOp(this.kernel, 1, rh);
/*     */ 
/*     */ 
/*     */     
/* 379 */     ColorModel cm = padRed2.getColorModel();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 384 */     Raster rr = padRed2.getData();
/* 385 */     WritableRaster wr = GraphicsUtil.makeRasterWritable(rr, 0, 0);
/*     */ 
/*     */ 
/*     */     
/* 389 */     int phaseShiftX = this.target.x - this.kernel.getXOrigin();
/* 390 */     int phaseShiftY = this.target.y - this.kernel.getYOrigin();
/* 391 */     int destX = (int)(r.getX() + phaseShiftX);
/* 392 */     int destY = (int)(r.getY() + phaseShiftY);
/*     */ 
/*     */     
/* 395 */     if (!this.preserveAlpha) {
/*     */ 
/*     */       
/* 398 */       cm = GraphicsUtil.coerceData(wr, cm, true);
/*     */ 
/*     */       
/* 401 */       BufferedImage srcBI = new BufferedImage(cm, wr, cm.isAlphaPremultiplied(), null);
/*     */ 
/*     */       
/* 404 */       destBI = op.filter(srcBI, null);
/*     */       
/* 406 */       if (this.kernelHasNegValues)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 412 */         fixAlpha(destBI);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 417 */       BufferedImage srcBI = new BufferedImage(cm, wr, cm.isAlphaPremultiplied(), null);
/*     */ 
/*     */       
/* 420 */       cm = new DirectColorModel(ColorSpace.getInstance(1004), 24, 16711680, 65280, 255, 0, false, 3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 429 */       BufferedImage tmpSrcBI = new BufferedImage(cm, cm.createCompatibleWritableRaster(wr.getWidth(), wr.getHeight()), cm.isAlphaPremultiplied(), null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 436 */       GraphicsUtil.copyData(srcBI, tmpSrcBI);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 442 */       ColorModel dstCM = GraphicsUtil.Linear_sRGB_Unpre;
/*     */       
/* 444 */       destBI = new BufferedImage(dstCM, dstCM.createCompatibleWritableRaster(wr.getWidth(), wr.getHeight()), dstCM.isAlphaPremultiplied(), null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 453 */       WritableRaster dstWR = Raster.createWritableRaster(cm.createCompatibleSampleModel(wr.getWidth(), wr.getHeight()), destBI.getRaster().getDataBuffer(), new Point(0, 0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 460 */       BufferedImage tmpDstBI = new BufferedImage(cm, dstWR, cm.isAlphaPremultiplied(), null);
/*     */ 
/*     */ 
/*     */       
/* 464 */       tmpDstBI = op.filter(tmpSrcBI, tmpDstBI);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 471 */       Rectangle srcRect = wr.getBounds();
/* 472 */       Rectangle dstRect = new Rectangle(srcRect.x - phaseShiftX, srcRect.y - phaseShiftY, srcRect.width, srcRect.height);
/*     */ 
/*     */       
/* 475 */       GraphicsUtil.copyBand(wr, srcRect, wr.getNumBands() - 1, destBI.getRaster(), dstRect, destBI.getRaster().getNumBands() - 1);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 481 */     BufferedImageCachableRed bufferedImageCachableRed = new BufferedImageCachableRed(destBI, destX, destY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 491 */     PadRed padRed1 = new PadRed((CachableRed)bufferedImageCachableRed, devRect.getBounds(), PadMode.ZERO_PAD, rh);
/*     */ 
/*     */     
/* 494 */     if (!resAt.isIdentity()) {
/* 495 */       affineRed = new AffineRed((CachableRed)padRed1, resAt, null);
/*     */     }
/*     */     
/* 498 */     return (RenderedImage)affineRed;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/ConvolveMatrixRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */