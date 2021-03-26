/*     */ package com.github.jaiimageio.jpeg2000.impl;
/*     */ 
/*     */ import c.a.a.b.b;
/*     */ import c.a.a.b.c;
/*     */ import c.a.a.b.d;
/*     */ import c.a.c.b.d;
/*     */ import c.a.c.b.g;
/*     */ import c.a.c.b.j;
/*     */ import c.a.d.b.a;
/*     */ import c.a.e.a;
/*     */ import c.a.e.a.a;
/*     */ import c.a.e.f;
/*     */ import c.a.e.h;
/*     */ import c.a.e.j;
/*     */ import c.a.g.b.a;
/*     */ import c.a.g.b.b;
/*     */ import c.a.h.a.d;
/*     */ import c.a.i.b;
/*     */ import c.a.j.a.j;
/*     */ import c.a.j.a.n;
/*     */ import com.github.jaiimageio.impl.common.ImageUtil;
/*     */ import com.github.jaiimageio.jpeg2000.J2KImageWriteParam;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.imageio.IIOException;
/*     */ import javax.imageio.IIOImage;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.ImageWriteParam;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.metadata.IIOInvalidTreeException;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.spi.ImageWriterSpi;
/*     */ import javax.imageio.stream.ImageOutputStream;
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
/*     */ public class J2KImageWriter
/*     */   extends ImageWriter
/*     */ {
/*     */   public void processImageProgressWrapper(float percentageDone) {
/* 109 */     processImageProgress(percentageDone);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 116 */   public static String WRITE_ABORTED = "Write aborted.";
/*     */ 
/*     */   
/* 119 */   private ImageOutputStream stream = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public J2KImageWriter(ImageWriterSpi originator) {
/* 125 */     super(originator);
/*     */   }
/*     */   
/*     */   public void setOutput(Object output) {
/* 129 */     super.setOutput(output);
/* 130 */     if (output != null) {
/* 131 */       if (!(output instanceof ImageOutputStream))
/* 132 */         throw new IllegalArgumentException(I18N.getString("J2KImageWriter0")); 
/* 133 */       this.stream = (ImageOutputStream)output;
/*     */     } else {
/* 135 */       this.stream = null;
/*     */     } 
/*     */   }
/*     */   public ImageWriteParam getDefaultWriteParam() {
/* 139 */     return (ImageWriteParam)new J2KImageWriteParam();
/*     */   }
/*     */   
/*     */   public IIOMetadata getDefaultStreamMetadata(ImageWriteParam param) {
/* 143 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageType, ImageWriteParam param) {
/* 148 */     return new J2KMetadata(imageType, param, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadata convertStreamMetadata(IIOMetadata inData, ImageWriteParam param) {
/* 153 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IIOMetadata convertImageMetadata(IIOMetadata inData, ImageTypeSpecifier imageType, ImageWriteParam param) {
/* 160 */     if (inData == null) {
/* 161 */       throw new IllegalArgumentException("inData == null!");
/*     */     }
/* 163 */     if (imageType == null) {
/* 164 */       throw new IllegalArgumentException("imageType == null!");
/*     */     }
/*     */ 
/*     */     
/* 168 */     if (inData instanceof J2KMetadata) {
/* 169 */       return (IIOMetadata)((J2KMetadata)inData).clone();
/*     */     }
/*     */     
/*     */     try {
/* 173 */       J2KMetadata outData = new J2KMetadata();
/*     */       
/* 175 */       List<String> formats = Arrays.asList(inData.getMetadataFormatNames());
/*     */       
/* 177 */       String format = null;
/* 178 */       if (formats.contains("com_sun_media_imageio_plugins_jpeg2000_image_1.0")) {
/*     */         
/* 180 */         format = "com_sun_media_imageio_plugins_jpeg2000_image_1.0";
/* 181 */       } else if (inData.isStandardMetadataFormatSupported()) {
/*     */         
/* 183 */         format = "javax_imageio_1.0";
/*     */       } 
/*     */       
/* 186 */       if (format != null) {
/* 187 */         outData.setFromTree(format, inData.getAsTree(format));
/* 188 */         return outData;
/*     */       } 
/* 190 */     } catch (IIOInvalidTreeException e) {
/* 191 */       return null;
/*     */     } 
/*     */     
/* 194 */     return null;
/*     */   }
/*     */   
/*     */   public boolean canWriteRasters() {
/* 198 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void write(IIOMetadata streamMetadata, IIOImage image, ImageWriteParam param) throws IOException {
/* 204 */     if (this.stream == null) {
/* 205 */       throw new IllegalStateException(I18N.getString("J2KImageWriter7"));
/*     */     }
/* 207 */     if (image == null) {
/* 208 */       throw new IllegalArgumentException(I18N.getString("J2KImageWriter8"));
/*     */     }
/*     */     
/* 211 */     clearAbortRequest();
/* 212 */     processImageStarted(0);
/* 213 */     RenderedImage input = null;
/*     */     
/* 215 */     boolean writeRaster = image.hasRaster();
/* 216 */     Raster raster = null;
/*     */     
/* 218 */     SampleModel sampleModel = null;
/* 219 */     if (writeRaster) {
/* 220 */       raster = image.getRaster();
/* 221 */       sampleModel = raster.getSampleModel();
/*     */     } else {
/* 223 */       input = image.getRenderedImage();
/* 224 */       sampleModel = input.getSampleModel();
/*     */     } 
/*     */     
/* 227 */     checkSampleModel(sampleModel);
/* 228 */     if (param == null) {
/* 229 */       param = getDefaultWriteParam();
/*     */     }
/* 231 */     J2KImageWriteParamJava j2kwparam = new J2KImageWriteParamJava(image, param);
/*     */ 
/*     */ 
/*     */     
/* 235 */     if (j2kwparam.getPackPacketHeaderInTile() && j2kwparam
/* 236 */       .getPackPacketHeaderInMain()) {
/* 237 */       throw new IllegalArgumentException(I18N.getString("J2KImageWriter1"));
/*     */     }
/*     */     
/* 240 */     if (j2kwparam.getLossless() && j2kwparam
/* 241 */       .getEncodingRate() != Double.MAX_VALUE) {
/* 242 */       throw new IllegalArgumentException(I18N.getString("J2KImageWriter2"));
/*     */     }
/*     */ 
/*     */     
/* 246 */     if ((!writeRaster && input.getColorModel() instanceof java.awt.image.IndexColorModel) || (writeRaster && raster
/*     */       
/* 248 */       .getSampleModel() instanceof java.awt.image.MultiPixelPackedSampleModel)) {
/* 249 */       j2kwparam.setDecompositionLevel("0");
/* 250 */       j2kwparam.setLossless(true);
/* 251 */       j2kwparam.setEncodingRate(Double.MAX_VALUE);
/* 252 */       j2kwparam.setQuantizationType("reversible");
/* 253 */       j2kwparam.setFilters("w5x3");
/* 254 */     } else if (j2kwparam.getEncodingRate() == Double.MAX_VALUE) {
/* 255 */       j2kwparam.setLossless(true);
/* 256 */       j2kwparam.setQuantizationType("reversible");
/* 257 */       j2kwparam.setFilters("w5x3");
/*     */     } 
/*     */ 
/*     */     
/* 261 */     boolean pphTile = j2kwparam.getPackPacketHeaderInTile();
/* 262 */     boolean pphMain = j2kwparam.getPackPacketHeaderInMain();
/* 263 */     boolean tempSop = false;
/* 264 */     boolean tempEph = false;
/*     */     
/* 266 */     int[] bands = param.getSourceBands();
/* 267 */     int ncomp = sampleModel.getNumBands();
/*     */     
/* 269 */     if (bands != null) {
/* 270 */       ncomp = bands.length;
/*     */     }
/*     */     
/* 273 */     RenderedImageSrc imgsrc = null;
/* 274 */     if (writeRaster) {
/* 275 */       imgsrc = new RenderedImageSrc(raster, j2kwparam, this);
/*     */     } else {
/* 277 */       imgsrc = new RenderedImageSrc(input, j2kwparam, this);
/*     */     } 
/*     */     
/* 280 */     boolean[] imsigned = new boolean[ncomp];
/* 281 */     if (bands != null) {
/* 282 */       for (int i = 0; i < ncomp; i++)
/* 283 */         imsigned[i] = imgsrc.isOrigSigned(bands[i]); 
/*     */     } else {
/* 285 */       for (int i = 0; i < ncomp; i++) {
/* 286 */         imsigned[i] = imgsrc.isOrigSigned(i);
/*     */       }
/*     */     } 
/*     */     
/* 290 */     int tw = j2kwparam.getTileWidth();
/* 291 */     int th = j2kwparam.getTileHeight();
/*     */ 
/*     */     
/* 294 */     int refx = j2kwparam.getMinX();
/* 295 */     int refy = j2kwparam.getMinY();
/* 296 */     if (refx < 0 || refy < 0) {
/* 297 */       throw new IIOException(I18N.getString("J2KImageWriter3"));
/*     */     }
/*     */     
/* 300 */     int trefx = j2kwparam.getTileGridXOffset();
/* 301 */     int trefy = j2kwparam.getTileGridYOffset();
/* 302 */     if (trefx < 0 || trefy < 0 || trefx > refx || trefy > refy) {
/* 303 */       throw new IIOException(I18N.getString("J2KImageWriter4"));
/*     */     }
/*     */     
/* 306 */     j imgtiler = new j(imgsrc, refx, refy, trefx, trefy, tw, th);
/*     */ 
/*     */     
/* 309 */     a fctransf = new a((a)imgtiler, j2kwparam);
/*     */ 
/*     */     
/* 312 */     h converter = new h((a)fctransf);
/*     */ 
/*     */     
/* 315 */     n dwt = n.a((a)converter, j2kwparam);
/*     */ 
/*     */     
/* 318 */     b quant = b.a((j)dwt, j2kwparam);
/*     */ 
/*     */     
/* 321 */     d rois = d.a(quant, j2kwparam);
/*     */ 
/*     */ 
/*     */     
/* 325 */     g ecoder = g.a((a)rois, j2kwparam, j2kwparam
/* 326 */         .getCodeBlockSize(), j2kwparam
/* 327 */         .getPrecinctPartition(), j2kwparam
/* 328 */         .getBypass(), j2kwparam
/* 329 */         .getResetMQ(), j2kwparam
/* 330 */         .getTerminateOnByte(), j2kwparam
/* 331 */         .getCausalCXInfo(), j2kwparam
/* 332 */         .getCodeSegSymbol(), j2kwparam
/* 333 */         .getMethodForMQLengthCalc(), j2kwparam
/* 334 */         .getMethodForMQTermination());
/*     */ 
/*     */     
/* 337 */     File tmpFile = File.createTempFile("jiio-", ".tmp");
/* 338 */     tmpFile.deleteOnExit();
/*     */ 
/*     */     
/* 341 */     c bwriter = new c(tmpFile, 2147483647);
/*     */ 
/*     */ 
/*     */     
/* 345 */     float rate = (float)j2kwparam.getEncodingRate();
/*     */     
/* 347 */     j ralloc = j.a((d)ecoder, rate, (b)bwriter, j2kwparam);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 353 */     d headenc = new d((f)imgsrc, imsigned, dwt, imgtiler, j2kwparam, rois, ralloc);
/*     */ 
/*     */ 
/*     */     
/* 357 */     ralloc.a(headenc);
/*     */ 
/*     */     
/* 360 */     headenc.f();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 365 */       ralloc.b();
/* 366 */     } catch (RuntimeException e) {
/* 367 */       if (WRITE_ABORTED.equals(e.getMessage())) {
/* 368 */         bwriter.c();
/* 369 */         tmpFile.delete();
/* 370 */         processWriteAborted(); return;
/*     */       } 
/* 372 */       throw e;
/*     */     } 
/*     */ 
/*     */     
/* 376 */     headenc.a();
/* 377 */     headenc.f();
/*     */ 
/*     */     
/* 380 */     bwriter.a(headenc);
/*     */ 
/*     */     
/* 383 */     ralloc.a();
/*     */ 
/*     */     
/* 386 */     bwriter.c();
/*     */ 
/*     */     
/* 389 */     int fileLength = bwriter.b();
/*     */ 
/*     */     
/* 392 */     int pktspertp = j2kwparam.getPacketPerTilePart();
/* 393 */     int ntiles = imgtiler.getNumTiles();
/* 394 */     if (pktspertp > 0 || pphTile || pphMain) {
/* 395 */       b cm = new b(tmpFile, ntiles, pktspertp, pphMain, pphTile, tempSop, tempEph);
/*     */ 
/*     */ 
/*     */       
/* 399 */       fileLength += cm.a();
/*     */     } 
/*     */ 
/*     */     
/* 403 */     int nc = imgsrc.getNumComps();
/* 404 */     int[] bpc = new int[nc];
/* 405 */     for (int comp = 0; comp < nc; comp++) {
/* 406 */       bpc[comp] = imgsrc.getNomRangeBits(comp);
/*     */     }
/* 408 */     ColorModel colorModel = (input != null) ? input.getColorModel() : null;
/* 409 */     if (bands != null) {
/* 410 */       ImageTypeSpecifier type = param.getDestinationType();
/* 411 */       if (type != null) {
/* 412 */         colorModel = type.getColorModel();
/*     */       }
/*     */     } 
/*     */     
/* 416 */     if (colorModel == null) {
/* 417 */       colorModel = ImageUtil.createColorModel(sampleModel);
/*     */     }
/*     */     
/* 420 */     J2KMetadata metadata = null;
/*     */     
/* 422 */     if (param instanceof J2KImageWriteParam && 
/* 423 */       !((J2KImageWriteParam)param).getWriteCodeStreamOnly()) {
/* 424 */       IIOMetadata inMetadata = image.getMetadata();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 429 */       J2KMetadata metadata1 = new J2KMetadata(colorModel, sampleModel, imgsrc.getImgWidth(), imgsrc.getImgHeight(), param, this);
/*     */ 
/*     */ 
/*     */       
/* 433 */       if (inMetadata == null) {
/* 434 */         metadata = metadata1;
/*     */       } else {
/*     */         
/* 437 */         if (colorModel != null) {
/* 438 */           ImageTypeSpecifier imageType = new ImageTypeSpecifier(colorModel, sampleModel);
/*     */ 
/*     */           
/* 441 */           metadata = (J2KMetadata)convertImageMetadata(inMetadata, imageType, param);
/*     */         }
/*     */         else {
/*     */           
/* 445 */           String metaFormat = null;
/*     */           
/* 447 */           List<String> metaFormats = Arrays.asList(inMetadata.getMetadataFormatNames());
/* 448 */           if (metaFormats.contains("com_sun_media_imageio_plugins_jpeg2000_image_1.0")) {
/*     */             
/* 450 */             metaFormat = "com_sun_media_imageio_plugins_jpeg2000_image_1.0";
/* 451 */           } else if (inMetadata.isStandardMetadataFormatSupported()) {
/*     */ 
/*     */             
/* 454 */             metaFormat = "javax_imageio_1.0";
/*     */           } 
/*     */ 
/*     */           
/* 458 */           metadata = new J2KMetadata();
/* 459 */           if (metaFormat != null) {
/* 460 */             metadata.setFromTree(metaFormat, inMetadata
/* 461 */                 .getAsTree(metaFormat));
/*     */           }
/*     */         } 
/*     */         
/* 465 */         metadata.mergeTree("com_sun_media_imageio_plugins_jpeg2000_image_1.0", metadata1
/* 466 */             .getAsTree("com_sun_media_imageio_plugins_jpeg2000_image_1.0"));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 473 */     a ffw = new a(tmpFile, this.stream, imgsrc.getImgHeight(), imgsrc.getImgWidth(), nc, bpc, fileLength, colorModel, sampleModel, metadata);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 478 */     fileLength += ffw.a();
/* 479 */     tmpFile.delete();
/*     */     
/* 481 */     processImageComplete();
/*     */   }
/*     */   
/*     */   public synchronized void abort() {
/* 485 */     super.abort();
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 490 */     super.reset();
/* 491 */     this.stream = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getAbortRequest() {
/* 498 */     return abortRequested();
/*     */   }
/*     */   
/*     */   private void checkSampleModel(SampleModel sm) {
/* 502 */     int type = sm.getDataType();
/*     */     
/* 504 */     if (type < 0 || type > 3)
/* 505 */       throw new IllegalArgumentException(I18N.getString("J2KImageWriter5")); 
/* 506 */     if (sm.getNumBands() > 16384)
/* 507 */       throw new IllegalArgumentException(I18N.getString("J2KImageWriter6")); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/J2KImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */