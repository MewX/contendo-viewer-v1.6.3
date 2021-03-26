/*      */ package com.github.jaiimageio.impl.plugins.raw;
/*      */ 
/*      */ import com.github.jaiimageio.impl.common.ImageUtil;
/*      */ import com.github.jaiimageio.impl.common.SimpleRenderedImage;
/*      */ import com.github.jaiimageio.stream.RawImageInputStream;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.ComponentSampleModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferDouble;
/*      */ import java.awt.image.DataBufferFloat;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DataBufferShort;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.MultiPixelPackedSampleModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.SinglePixelPackedSampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.io.IOException;
/*      */ import javax.imageio.ImageReadParam;
/*      */ import javax.imageio.ImageTypeSpecifier;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class RawRenderedImage
/*      */   extends SimpleRenderedImage
/*      */ {
/*      */   private SampleModel originalSampleModel;
/*      */   private Raster currentTile;
/*      */   private Point currentTileGrid;
/*   95 */   private RawImageInputStream iis = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private RawImageReader reader;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  105 */   private ImageReadParam param = null;
/*      */   
/*      */   private int imageIndex;
/*      */   
/*      */   private Rectangle destinationRegion;
/*      */   private Rectangle originalRegion;
/*      */   private Point sourceOrigin;
/*      */   private Dimension originalDimension;
/*      */   private int maxXTile;
/*      */   private int maxYTile;
/*      */   private int scaleX;
/*      */   private int scaleY;
/*      */   private int xOffset;
/*      */   private int yOffset;
/*  119 */   private int[] destinationBands = null;
/*  120 */   private int[] sourceBands = null;
/*      */ 
/*      */   
/*      */   private int nComp;
/*      */ 
/*      */   
/*      */   private boolean noTransform = true;
/*      */ 
/*      */   
/*      */   private WritableRaster rasForATile;
/*      */ 
/*      */   
/*      */   private BufferedImage destImage;
/*      */ 
/*      */   
/*      */   private long position;
/*      */ 
/*      */   
/*      */   private long tileDataSize;
/*      */ 
/*      */   
/*      */   private int originalNumXTiles;
/*      */ 
/*      */ 
/*      */   
/*      */   public RawRenderedImage(RawImageInputStream iis, RawImageReader reader, ImageReadParam param, int imageIndex) throws IOException {
/*  146 */     this.iis = iis;
/*  147 */     this.reader = reader;
/*  148 */     this.param = param;
/*  149 */     this.imageIndex = imageIndex;
/*  150 */     this.position = iis.getImageOffset(imageIndex);
/*  151 */     this.originalDimension = iis.getImageDimension(imageIndex);
/*      */     
/*  153 */     ImageTypeSpecifier type = iis.getImageType();
/*  154 */     this.sampleModel = this.originalSampleModel = type.getSampleModel();
/*  155 */     this.colorModel = type.getColorModel();
/*      */ 
/*      */     
/*  158 */     this.sourceBands = (param == null) ? null : param.getSourceBands();
/*      */     
/*  160 */     if (this.sourceBands == null) {
/*  161 */       this.nComp = this.originalSampleModel.getNumBands();
/*  162 */       this.sourceBands = new int[this.nComp];
/*  163 */       for (int i = 0; i < this.nComp; i++)
/*  164 */         this.sourceBands[i] = i; 
/*      */     } else {
/*  166 */       this
/*  167 */         .sampleModel = this.originalSampleModel.createSubsetSampleModel(this.sourceBands);
/*  168 */       this.colorModel = ImageUtil.createColorModel(null, this.sampleModel);
/*      */     } 
/*      */     
/*  171 */     this.nComp = this.sourceBands.length;
/*      */     
/*  173 */     this.destinationBands = (param == null) ? null : param.getDestinationBands();
/*  174 */     if (this.destinationBands == null) {
/*  175 */       this.destinationBands = new int[this.nComp];
/*  176 */       for (int i = 0; i < this.nComp; i++) {
/*  177 */         this.destinationBands[i] = i;
/*      */       }
/*      */     } 
/*  180 */     Dimension dim = iis.getImageDimension(imageIndex);
/*  181 */     this.width = dim.width;
/*  182 */     this.height = dim.height;
/*      */     
/*  184 */     Rectangle sourceRegion = new Rectangle(0, 0, this.width, this.height);
/*      */ 
/*      */     
/*  187 */     this.originalRegion = (Rectangle)sourceRegion.clone();
/*      */     
/*  189 */     this.destinationRegion = (Rectangle)sourceRegion.clone();
/*      */     
/*  191 */     if (param != null) {
/*  192 */       RawImageReader.computeRegionsWrapper(param, this.width, this.height, param
/*      */           
/*  194 */           .getDestination(), sourceRegion, this.destinationRegion);
/*      */ 
/*      */       
/*  197 */       this.scaleX = param.getSourceXSubsampling();
/*  198 */       this.scaleY = param.getSourceYSubsampling();
/*  199 */       this.xOffset = param.getSubsamplingXOffset();
/*  200 */       this.yOffset = param.getSubsamplingYOffset();
/*      */     } 
/*      */     
/*  203 */     this.sourceOrigin = new Point(sourceRegion.x, sourceRegion.y);
/*  204 */     if (!this.destinationRegion.equals(sourceRegion)) {
/*  205 */       this.noTransform = false;
/*      */     }
/*  207 */     this.tileDataSize = ImageUtil.getTileSize(this.originalSampleModel);
/*      */     
/*  209 */     this.tileWidth = this.originalSampleModel.getWidth();
/*  210 */     this.tileHeight = this.originalSampleModel.getHeight();
/*  211 */     this.tileGridXOffset = this.destinationRegion.x;
/*  212 */     this.tileGridYOffset = this.destinationRegion.y;
/*  213 */     this.originalNumXTiles = getNumXTiles();
/*      */     
/*  215 */     this.width = this.destinationRegion.width;
/*  216 */     this.height = this.destinationRegion.height;
/*  217 */     this.minX = this.destinationRegion.x;
/*  218 */     this.minY = this.destinationRegion.y;
/*      */     
/*  220 */     this
/*  221 */       .sampleModel = this.sampleModel.createCompatibleSampleModel(this.tileWidth, this.tileHeight);
/*      */     
/*  223 */     this.maxXTile = this.originalDimension.width / this.tileWidth;
/*  224 */     this.maxYTile = this.originalDimension.height / this.tileHeight;
/*      */   }
/*      */   
/*      */   public synchronized Raster getTile(int tileX, int tileY) {
/*  228 */     if (this.currentTile != null && this.currentTileGrid.x == tileX && this.currentTileGrid.y == tileY)
/*      */     {
/*      */       
/*  231 */       return this.currentTile;
/*      */     }
/*  233 */     if (tileX >= getNumXTiles() || tileY >= getNumYTiles()) {
/*  234 */       throw new IllegalArgumentException(I18N.getString("RawRenderedImage0"));
/*      */     }
/*      */     try {
/*  237 */       this.iis.seek(this.position + (tileY * this.originalNumXTiles + tileX) * this.tileDataSize);
/*      */       
/*  239 */       int x = tileXToX(tileX);
/*  240 */       int y = tileYToY(tileY);
/*  241 */       this.currentTile = Raster.createWritableRaster(this.sampleModel, new Point(x, y));
/*      */       
/*  243 */       if (this.noTransform) {
/*  244 */         byte[][] buf; int i; short[][] sbuf; int j; short[][] usbuf; int k; int[][] ibuf; int m; float[][] fbuf; int n; double[][] dbuf; int i1; switch (this.sampleModel.getDataType()) {
/*      */           
/*      */           case 0:
/*  247 */             buf = ((DataBufferByte)this.currentTile.getDataBuffer()).getBankData();
/*  248 */             for (i = 0; i < buf.length; i++) {
/*  249 */               this.iis.readFully(buf[i], 0, (buf[i]).length);
/*      */             }
/*      */             break;
/*      */           
/*      */           case 2:
/*  254 */             sbuf = ((DataBufferShort)this.currentTile.getDataBuffer()).getBankData();
/*  255 */             for (j = 0; j < sbuf.length; j++) {
/*  256 */               this.iis.readFully(sbuf[j], 0, (sbuf[j]).length);
/*      */             }
/*      */             break;
/*      */           
/*      */           case 1:
/*  261 */             usbuf = ((DataBufferUShort)this.currentTile.getDataBuffer()).getBankData();
/*  262 */             for (k = 0; k < usbuf.length; k++) {
/*  263 */               this.iis.readFully(usbuf[k], 0, (usbuf[k]).length);
/*      */             }
/*      */             break;
/*      */           case 3:
/*  267 */             ibuf = ((DataBufferInt)this.currentTile.getDataBuffer()).getBankData();
/*  268 */             for (m = 0; m < ibuf.length; m++) {
/*  269 */               this.iis.readFully(ibuf[m], 0, (ibuf[m]).length);
/*      */             }
/*      */             break;
/*      */           case 4:
/*  273 */             fbuf = ((DataBufferFloat)this.currentTile.getDataBuffer()).getBankData();
/*  274 */             for (n = 0; n < fbuf.length; n++) {
/*  275 */               this.iis.readFully(fbuf[n], 0, (fbuf[n]).length);
/*      */             }
/*      */             break;
/*      */           case 5:
/*  279 */             dbuf = ((DataBufferDouble)this.currentTile.getDataBuffer()).getBankData();
/*  280 */             for (i1 = 0; i1 < dbuf.length; i1++)
/*  281 */               this.iis.readFully(dbuf[i1], 0, (dbuf[i1]).length); 
/*      */             break;
/*      */         } 
/*      */       } else {
/*  285 */         this.currentTile = readSubsampledRaster((WritableRaster)this.currentTile);
/*      */       } 
/*  287 */     } catch (IOException e) {
/*  288 */       throw new RuntimeException(e);
/*      */     } 
/*      */     
/*  291 */     if (this.currentTileGrid == null) {
/*  292 */       this.currentTileGrid = new Point(tileX, tileY);
/*      */     } else {
/*  294 */       this.currentTileGrid.x = tileX;
/*  295 */       this.currentTileGrid.y = tileY;
/*      */     } 
/*      */     
/*  298 */     return this.currentTile;
/*      */   }
/*      */   
/*      */   public void readAsRaster(WritableRaster raster) throws IOException {
/*  302 */     readSubsampledRaster(raster);
/*      */   }
/*      */   
/*      */   private Raster readSubsampledRaster(WritableRaster raster) throws IOException {
/*  306 */     if (raster == null) {
/*  307 */       raster = Raster.createWritableRaster(this.sampleModel
/*  308 */           .createCompatibleSampleModel(this.destinationRegion.x + this.destinationRegion.width, this.destinationRegion.y + this.destinationRegion.height), new Point(this.destinationRegion.x, this.destinationRegion.y));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  314 */     int numBands = this.sourceBands.length;
/*  315 */     int dataType = this.sampleModel.getDataType();
/*  316 */     int sampleSizeBit = DataBuffer.getDataTypeSize(dataType);
/*  317 */     int sampleSizeByte = (sampleSizeBit + 7) / 8;
/*      */     
/*  319 */     Rectangle destRect = raster.getBounds().intersection(this.destinationRegion);
/*      */     
/*  321 */     int offx = this.destinationRegion.x;
/*  322 */     int offy = this.destinationRegion.y;
/*      */     
/*  324 */     int sourceSX = (destRect.x - offx) * this.scaleX + this.sourceOrigin.x;
/*  325 */     int sourceSY = (destRect.y - offy) * this.scaleY + this.sourceOrigin.y;
/*  326 */     int sourceEX = (destRect.width - 1) * this.scaleX + sourceSX;
/*  327 */     int sourceEY = (destRect.height - 1) * this.scaleY + sourceSY;
/*  328 */     int startXTile = sourceSX / this.tileWidth;
/*  329 */     int startYTile = sourceSY / this.tileHeight;
/*  330 */     int endXTile = sourceEX / this.tileWidth;
/*  331 */     int endYTile = sourceEY / this.tileHeight;
/*      */     
/*  333 */     startXTile = clip(startXTile, 0, this.maxXTile);
/*  334 */     startYTile = clip(startYTile, 0, this.maxYTile);
/*  335 */     endXTile = clip(endXTile, 0, this.maxXTile);
/*  336 */     endYTile = clip(endYTile, 0, this.maxYTile);
/*      */     
/*  338 */     int totalXTiles = getNumXTiles();
/*  339 */     int totalYTiles = getNumYTiles();
/*  340 */     int totalTiles = totalXTiles * totalYTiles;
/*      */ 
/*      */     
/*  343 */     byte[] pixbuf = null;
/*  344 */     short[] spixbuf = null;
/*  345 */     int[] ipixbuf = null;
/*  346 */     float[] fpixbuf = null;
/*  347 */     double[] dpixbuf = null;
/*      */ 
/*      */     
/*  350 */     boolean singleBank = true;
/*  351 */     int pixelStride = 0;
/*  352 */     int scanlineStride = 0;
/*  353 */     int bandStride = 0;
/*  354 */     int[] bandOffsets = null;
/*  355 */     int[] bankIndices = null;
/*      */     
/*  357 */     if (this.originalSampleModel instanceof ComponentSampleModel) {
/*  358 */       ComponentSampleModel csm = (ComponentSampleModel)this.originalSampleModel;
/*  359 */       bankIndices = csm.getBankIndices();
/*  360 */       int maxBank = 0; int i;
/*  361 */       for (i = 0; i < bankIndices.length; i++) {
/*  362 */         if (maxBank > bankIndices[i])
/*  363 */           maxBank = bankIndices[i]; 
/*      */       } 
/*  365 */       if (maxBank > 0)
/*  366 */         singleBank = false; 
/*  367 */       pixelStride = csm.getPixelStride();
/*      */       
/*  369 */       scanlineStride = csm.getScanlineStride();
/*  370 */       bandOffsets = csm.getBandOffsets();
/*  371 */       for (i = 0; i < bandOffsets.length; i++)
/*  372 */       { if (bandStride < bandOffsets[i])
/*  373 */           bandStride = bandOffsets[i];  } 
/*  374 */     } else if (this.originalSampleModel instanceof MultiPixelPackedSampleModel) {
/*      */       
/*  376 */       scanlineStride = ((MultiPixelPackedSampleModel)this.originalSampleModel).getScanlineStride();
/*  377 */     } else if (this.originalSampleModel instanceof SinglePixelPackedSampleModel) {
/*  378 */       pixelStride = 1;
/*      */       
/*  380 */       scanlineStride = ((SinglePixelPackedSampleModel)this.originalSampleModel).getScanlineStride();
/*      */     } 
/*      */ 
/*      */     
/*  384 */     byte[] destPixbuf = null;
/*  385 */     short[] destSPixbuf = null;
/*  386 */     int[] destIPixbuf = null;
/*  387 */     float[] destFPixbuf = null;
/*  388 */     double[] destDPixbuf = null;
/*  389 */     int[] destBandOffsets = null;
/*  390 */     int destPixelStride = 0;
/*  391 */     int destScanlineStride = 0;
/*  392 */     int destSX = 0;
/*      */     
/*  394 */     if (raster.getSampleModel() instanceof ComponentSampleModel) {
/*      */       
/*  396 */       ComponentSampleModel csm = (ComponentSampleModel)raster.getSampleModel();
/*  397 */       bankIndices = csm.getBankIndices();
/*  398 */       destBandOffsets = csm.getBandOffsets();
/*  399 */       destPixelStride = csm.getPixelStride();
/*  400 */       destScanlineStride = csm.getScanlineStride();
/*  401 */       destSX = csm.getOffset(raster.getMinX() - raster
/*  402 */           .getSampleModelTranslateX(), raster
/*  403 */           .getMinY() - raster
/*  404 */           .getSampleModelTranslateY()) - destBandOffsets[0];
/*      */ 
/*      */       
/*  407 */       switch (dataType) {
/*      */         case 0:
/*  409 */           destPixbuf = ((DataBufferByte)raster.getDataBuffer()).getData();
/*      */           break;
/*      */         
/*      */         case 2:
/*  413 */           destSPixbuf = ((DataBufferShort)raster.getDataBuffer()).getData();
/*      */           break;
/*      */ 
/*      */         
/*      */         case 1:
/*  418 */           destSPixbuf = ((DataBufferUShort)raster.getDataBuffer()).getData();
/*      */           break;
/*      */ 
/*      */         
/*      */         case 3:
/*  423 */           destIPixbuf = ((DataBufferInt)raster.getDataBuffer()).getData();
/*      */           break;
/*      */ 
/*      */         
/*      */         case 4:
/*  428 */           destFPixbuf = ((DataBufferFloat)raster.getDataBuffer()).getData();
/*      */           break;
/*      */ 
/*      */         
/*      */         case 5:
/*  433 */           destDPixbuf = ((DataBufferDouble)raster.getDataBuffer()).getData();
/*      */           break;
/*      */       } 
/*  436 */     } else if (raster.getSampleModel() instanceof SinglePixelPackedSampleModel) {
/*  437 */       numBands = 1;
/*  438 */       bankIndices = new int[] { 0 };
/*  439 */       destBandOffsets = new int[numBands];
/*  440 */       for (int i = 0; i < numBands; i++)
/*  441 */         destBandOffsets[i] = 0; 
/*  442 */       destPixelStride = 1;
/*      */       
/*  444 */       destScanlineStride = ((SinglePixelPackedSampleModel)raster.getSampleModel()).getScanlineStride();
/*      */     } 
/*      */ 
/*      */     
/*  448 */     for (int y = startYTile; y <= endYTile && 
/*  449 */       !this.reader.getAbortRequest(); y++) {
/*      */ 
/*      */ 
/*      */       
/*  453 */       for (int x = startXTile; x <= endXTile && 
/*  454 */         !this.reader.getAbortRequest(); x++) {
/*      */ 
/*      */         
/*  457 */         long tilePosition = this.position + (y * this.originalNumXTiles + x) * this.tileDataSize;
/*      */         
/*  459 */         this.iis.seek(tilePosition);
/*  460 */         float percentage = ((x - startXTile + y * totalXTiles) / totalXTiles);
/*      */ 
/*      */         
/*  463 */         int startX = x * this.tileWidth;
/*  464 */         int startY = y * this.tileHeight;
/*      */         
/*  466 */         int cTileHeight = this.tileHeight;
/*  467 */         int cTileWidth = this.tileWidth;
/*      */         
/*  469 */         if (startY + cTileHeight >= this.originalDimension.height) {
/*  470 */           cTileHeight = this.originalDimension.height - startY;
/*      */         }
/*  472 */         if (startX + cTileWidth >= this.originalDimension.width) {
/*  473 */           cTileWidth = this.originalDimension.width - startX;
/*      */         }
/*  475 */         int tx = startX;
/*  476 */         int ty = startY;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  481 */         if (sourceSX > startX) {
/*  482 */           cTileWidth += startX - sourceSX;
/*  483 */           tx = sourceSX;
/*  484 */           startX = sourceSX;
/*      */         } 
/*      */         
/*  487 */         if (sourceSY > startY) {
/*  488 */           cTileHeight += startY - sourceSY;
/*  489 */           ty = sourceSY;
/*  490 */           startY = sourceSY;
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  496 */         if (sourceEX < startX + cTileWidth - 1) {
/*  497 */           cTileWidth += sourceEX - startX - cTileWidth + 1;
/*      */         }
/*      */         
/*  500 */         if (sourceEY < startY + cTileHeight - 1) {
/*  501 */           cTileHeight += sourceEY - startY - cTileHeight + 1;
/*      */         }
/*      */ 
/*      */         
/*  505 */         int x1 = (startX + this.scaleX - 1 - this.sourceOrigin.x) / this.scaleX;
/*  506 */         int x2 = (startX + this.scaleX - 1 + cTileWidth - this.sourceOrigin.x) / this.scaleX;
/*      */         
/*  508 */         int lineLength = x2 - x1;
/*  509 */         x2 = (x2 - 1) * this.scaleX + this.sourceOrigin.x;
/*      */         
/*  511 */         int y1 = (startY + this.scaleY - 1 - this.sourceOrigin.y) / this.scaleY;
/*  512 */         startX = x1 * this.scaleX + this.sourceOrigin.x;
/*  513 */         startY = y1 * this.scaleY + this.sourceOrigin.y;
/*      */ 
/*      */         
/*  516 */         x1 += offx;
/*  517 */         y1 += offy;
/*      */         
/*  519 */         tx -= x * this.tileWidth;
/*  520 */         ty -= y * this.tileHeight;
/*      */         
/*  522 */         if (this.sampleModel instanceof MultiPixelPackedSampleModel) {
/*  523 */           MultiPixelPackedSampleModel mppsm = (MultiPixelPackedSampleModel)this.originalSampleModel;
/*      */ 
/*      */           
/*  526 */           this.iis.skipBytes(mppsm.getOffset(tx, ty) * sampleSizeByte);
/*      */ 
/*      */           
/*  529 */           int readBytes = (mppsm.getOffset(x2, 0) - mppsm.getOffset(startX, 0) + 1) * sampleSizeByte;
/*      */ 
/*      */           
/*  532 */           int skipLength = (scanlineStride * this.scaleY - readBytes) * sampleSizeByte;
/*      */           
/*  534 */           readBytes *= sampleSizeByte;
/*      */           
/*  536 */           if (pixbuf == null || pixbuf.length < readBytes) {
/*  537 */             pixbuf = new byte[readBytes];
/*      */           }
/*  539 */           int bitoff = mppsm.getBitOffset(tx);
/*      */           
/*  541 */           for (int l = 0, m = y1; l < cTileHeight; 
/*  542 */             l += this.scaleY, m++) {
/*  543 */             if (this.reader.getAbortRequest())
/*      */               break; 
/*  545 */             this.iis.readFully(pixbuf, 0, readBytes);
/*  546 */             if (this.scaleX == 1) {
/*      */               
/*  548 */               if (bitoff != 0) {
/*  549 */                 int mask1 = 255 << bitoff & 0xFF;
/*  550 */                 int mask2 = (mask1 ^ 0xFFFFFFFF) & 0xFF;
/*  551 */                 int shift = 8 - bitoff;
/*      */                 
/*  553 */                 int n = 0;
/*  554 */                 for (; n < readBytes - 1; n++) {
/*  555 */                   pixbuf[n] = (byte)((pixbuf[n] & mask2) << shift | (pixbuf[n + 1] & mask1) >> bitoff);
/*      */                 }
/*  557 */                 pixbuf[n] = (byte)((pixbuf[n] & mask2) << shift);
/*      */               } 
/*      */             } else {
/*      */               
/*  561 */               int bit = 7;
/*  562 */               int pos = 0;
/*  563 */               int mask = 128;
/*      */               
/*  565 */               int n = 0, n1 = startX & 0x7;
/*  566 */               for (; n < lineLength; n++, n1 += this.scaleX) {
/*  567 */                 pixbuf[pos] = (byte)(pixbuf[pos] & (1 << bit ^ 0xFFFFFFFF) | (pixbuf[n1 >> 3] >> 7 - (n1 & 0x7) & 0x1) << bit);
/*      */                 
/*  569 */                 bit--;
/*  570 */                 if (bit == -1) {
/*  571 */                   bit = 7;
/*  572 */                   pos++;
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */             
/*  577 */             ImageUtil.setPackedBinaryData(pixbuf, raster, new Rectangle(x1, m, lineLength, 1));
/*      */ 
/*      */ 
/*      */             
/*  581 */             this.iis.skipBytes(skipLength);
/*  582 */             if (this.destImage != null) {
/*  583 */               this.reader.processImageUpdateWrapper(this.destImage, x1, m, cTileWidth, 1, 1, 1, this.destinationBands);
/*      */             }
/*      */ 
/*      */             
/*  587 */             this.reader.processImageProgressWrapper(percentage + ((l - startY) + 1.0F) / cTileHeight / totalTiles);
/*      */           } 
/*      */         } else {
/*      */           int readLength;
/*      */           
/*      */           int skipLength;
/*      */           
/*  594 */           if (pixelStride < scanlineStride) {
/*  595 */             readLength = cTileWidth * pixelStride;
/*  596 */             skipLength = (scanlineStride * this.scaleY - readLength) * sampleSizeByte;
/*      */           } else {
/*      */             
/*  599 */             readLength = cTileHeight * scanlineStride;
/*  600 */             skipLength = (pixelStride * this.scaleX - readLength) * sampleSizeByte;
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  605 */           switch (this.sampleModel.getDataType()) {
/*      */             case 0:
/*  607 */               if (pixbuf == null || pixbuf.length < readLength) {
/*  608 */                 pixbuf = new byte[readLength];
/*      */               }
/*      */               break;
/*      */             case 1:
/*      */             case 2:
/*  613 */               if (spixbuf == null || spixbuf.length < readLength) {
/*  614 */                 spixbuf = new short[readLength];
/*      */               }
/*      */               break;
/*      */             case 3:
/*  618 */               if (ipixbuf == null || ipixbuf.length < readLength) {
/*  619 */                 ipixbuf = new int[readLength];
/*      */               }
/*      */               break;
/*      */             case 4:
/*  623 */               if (fpixbuf == null || fpixbuf.length < readLength) {
/*  624 */                 fpixbuf = new float[readLength];
/*      */               }
/*      */               break;
/*      */             case 5:
/*  628 */               if (dpixbuf == null || dpixbuf.length < readLength) {
/*  629 */                 dpixbuf = new double[readLength];
/*      */               }
/*      */               break;
/*      */           } 
/*  633 */           if (this.sampleModel instanceof java.awt.image.PixelInterleavedSampleModel) {
/*  634 */             int outerFirst, outerSecond, outerStep, outerBound, innerStep, innerStep1, outerStep1; this.iis.skipBytes((tx * pixelStride + ty * scanlineStride) * sampleSizeByte);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  640 */             if (pixelStride < scanlineStride) {
/*  641 */               outerFirst = 0;
/*  642 */               outerSecond = y1;
/*  643 */               outerStep = this.scaleY;
/*  644 */               outerBound = cTileHeight;
/*  645 */               innerStep = this.scaleX * pixelStride;
/*  646 */               innerStep1 = destPixelStride;
/*  647 */               outerStep1 = destScanlineStride;
/*      */             } else {
/*  649 */               outerFirst = 0;
/*  650 */               outerSecond = x1;
/*  651 */               outerStep = this.scaleX;
/*  652 */               outerBound = cTileWidth;
/*  653 */               innerStep = this.scaleY * scanlineStride;
/*  654 */               innerStep1 = destScanlineStride;
/*  655 */               outerStep1 = destPixelStride;
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  661 */             int destPos = destSX + (y1 - raster.getSampleModelTranslateY()) * destScanlineStride + (x1 - raster.getSampleModelTranslateX()) * destPixelStride;
/*      */ 
/*      */             
/*  664 */             for (int l = outerFirst, m = outerSecond; l < outerBound; 
/*  665 */               l += outerStep, m++) {
/*  666 */               if (this.reader.getAbortRequest()) {
/*      */                 break;
/*      */               }
/*  669 */               switch (dataType) {
/*      */                 case 0:
/*  671 */                   if (innerStep == numBands && innerStep1 == numBands) {
/*      */                     
/*  673 */                     this.iis.readFully(destPixbuf, destPos, readLength); break;
/*      */                   } 
/*  675 */                   this.iis.readFully(pixbuf, 0, readLength);
/*      */                   break;
/*      */                 case 1:
/*      */                 case 2:
/*  679 */                   if (innerStep == numBands && innerStep1 == numBands) {
/*      */                     
/*  681 */                     this.iis.readFully(destSPixbuf, destPos, readLength); break;
/*      */                   } 
/*  683 */                   this.iis.readFully(spixbuf, 0, readLength);
/*      */                   break;
/*      */                 case 3:
/*  686 */                   if (innerStep == numBands && innerStep1 == numBands) {
/*      */                     
/*  688 */                     this.iis.readFully(destIPixbuf, destPos, readLength); break;
/*      */                   } 
/*  690 */                   this.iis.readFully(ipixbuf, 0, readLength);
/*      */                   break;
/*      */                 case 4:
/*  693 */                   if (innerStep == numBands && innerStep1 == numBands) {
/*      */                     
/*  695 */                     this.iis.readFully(destFPixbuf, destPos, readLength); break;
/*      */                   } 
/*  697 */                   this.iis.readFully(fpixbuf, 0, readLength);
/*      */                   break;
/*      */                 case 5:
/*  700 */                   if (innerStep == numBands && innerStep1 == numBands) {
/*      */                     
/*  702 */                     this.iis.readFully(destDPixbuf, destPos, readLength); break;
/*      */                   } 
/*  704 */                   this.iis.readFully(dpixbuf, 0, readLength);
/*      */                   break;
/*      */               } 
/*      */               
/*  708 */               if (innerStep != numBands || innerStep1 != numBands) {
/*  709 */                 for (int b = 0; b < numBands; b++) {
/*  710 */                   int m1, n, destBandOffset = destBandOffsets[this.destinationBands[b]];
/*      */                   
/*  712 */                   destPos += destBandOffset;
/*      */                   
/*  714 */                   int sourceBandOffset = bandOffsets[this.sourceBands[b]];
/*      */ 
/*      */                   
/*  717 */                   switch (dataType) {
/*      */                     case 0:
/*  719 */                       for (m1 = 0, n = destPos; m1 < readLength; 
/*  720 */                         m1 += innerStep, n += innerStep1) {
/*  721 */                         destPixbuf[n] = pixbuf[m1 + sourceBandOffset];
/*      */                       }
/*      */                       break;
/*      */                     
/*      */                     case 1:
/*      */                     case 2:
/*  727 */                       for (m1 = 0, n = destPos; m1 < readLength; 
/*  728 */                         m1 += innerStep, n += innerStep1) {
/*  729 */                         destSPixbuf[n] = spixbuf[m1 + sourceBandOffset];
/*      */                       }
/*      */                       break;
/*      */                     
/*      */                     case 3:
/*  734 */                       for (m1 = 0, n = destPos; m1 < readLength; 
/*  735 */                         m1 += innerStep, n += innerStep1) {
/*  736 */                         destIPixbuf[n] = ipixbuf[m1 + sourceBandOffset];
/*      */                       }
/*      */                       break;
/*      */                     
/*      */                     case 4:
/*  741 */                       for (m1 = 0, n = destPos; m1 < readLength; 
/*  742 */                         m1 += innerStep, n += innerStep1) {
/*  743 */                         destFPixbuf[n] = fpixbuf[m1 + sourceBandOffset];
/*      */                       }
/*      */                       break;
/*      */                     
/*      */                     case 5:
/*  748 */                       for (m1 = 0, n = destPos; m1 < readLength; 
/*  749 */                         m1 += innerStep, n += innerStep1) {
/*  750 */                         destDPixbuf[n] = dpixbuf[m1 + sourceBandOffset];
/*      */                       }
/*      */                       break;
/*      */                   } 
/*      */                   
/*  755 */                   destPos -= destBandOffset;
/*      */                 } 
/*      */               }
/*  758 */               this.iis.skipBytes(skipLength);
/*  759 */               destPos += outerStep1;
/*      */               
/*  761 */               if (this.destImage != null) {
/*  762 */                 if (pixelStride < scanlineStride) {
/*  763 */                   this.reader.processImageUpdateWrapper(this.destImage, x1, m, outerBound, 1, 1, 1, this.destinationBands);
/*      */                 
/*      */                 }
/*      */                 else {
/*      */ 
/*      */                   
/*  769 */                   this.reader.processImageUpdateWrapper(this.destImage, m, y1, 1, outerBound, 1, 1, this.destinationBands);
/*      */                 } 
/*      */               }
/*      */ 
/*      */ 
/*      */               
/*  775 */               this.reader.processImageProgressWrapper(percentage + (l + 1.0F) / outerBound / totalTiles);
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  780 */           else if (this.sampleModel instanceof java.awt.image.BandedSampleModel || this.sampleModel instanceof SinglePixelPackedSampleModel || bandStride == 0) {
/*      */ 
/*      */             
/*  783 */             boolean isBanded = this.sampleModel instanceof java.awt.image.BandedSampleModel;
/*      */ 
/*      */             
/*  786 */             int bandSize = (int)ImageUtil.getBandSize(this.originalSampleModel);
/*      */             
/*  788 */             for (int b = 0; b < numBands; b++) {
/*  789 */               int outerFirst, outerSecond, outerStep, outerBound, innerStep, innerStep1, outerStep1; this.iis.seek(tilePosition + (bandSize * this.sourceBands[b] * sampleSizeByte));
/*      */               
/*  791 */               int destBandOffset = destBandOffsets[this.destinationBands[b]];
/*      */ 
/*      */               
/*  794 */               this.iis.skipBytes((ty * scanlineStride + tx * pixelStride) * sampleSizeByte);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  800 */               if (pixelStride < scanlineStride) {
/*  801 */                 outerFirst = 0;
/*  802 */                 outerSecond = y1;
/*  803 */                 outerStep = this.scaleY;
/*  804 */                 outerBound = cTileHeight;
/*  805 */                 innerStep = this.scaleX * pixelStride;
/*  806 */                 innerStep1 = destPixelStride;
/*  807 */                 outerStep1 = destScanlineStride;
/*      */               } else {
/*  809 */                 outerFirst = 0;
/*  810 */                 outerSecond = x1;
/*  811 */                 outerStep = this.scaleX;
/*  812 */                 outerBound = cTileWidth;
/*  813 */                 innerStep = this.scaleY * scanlineStride;
/*  814 */                 innerStep1 = destScanlineStride;
/*  815 */                 outerStep1 = destPixelStride;
/*      */               } 
/*      */ 
/*      */               
/*  819 */               int destPos = destSX + (y1 - raster.getSampleModelTranslateY()) * destScanlineStride + (x1 - raster.getSampleModelTranslateX()) * destPixelStride + destBandOffset;
/*      */               
/*  821 */               int bank = bankIndices[this.destinationBands[b]];
/*      */               
/*  823 */               switch (dataType) {
/*      */                 
/*      */                 case 0:
/*  826 */                   destPixbuf = ((DataBufferByte)raster.getDataBuffer()).getData(bank);
/*      */                   break;
/*      */                 
/*      */                 case 2:
/*  830 */                   destSPixbuf = ((DataBufferShort)raster.getDataBuffer()).getData(bank);
/*      */                   break;
/*      */ 
/*      */                 
/*      */                 case 1:
/*  835 */                   destSPixbuf = ((DataBufferUShort)raster.getDataBuffer()).getData(bank);
/*      */                   break;
/*      */ 
/*      */                 
/*      */                 case 3:
/*  840 */                   destIPixbuf = ((DataBufferInt)raster.getDataBuffer()).getData(bank);
/*      */                   break;
/*      */ 
/*      */                 
/*      */                 case 4:
/*  845 */                   destFPixbuf = ((DataBufferFloat)raster.getDataBuffer()).getData(bank);
/*      */                   break;
/*      */ 
/*      */                 
/*      */                 case 5:
/*  850 */                   destDPixbuf = ((DataBufferDouble)raster.getDataBuffer()).getData(bank);
/*      */                   break;
/*      */               } 
/*      */               
/*  854 */               for (int l = outerFirst, m = outerSecond; l < outerBound; 
/*  855 */                 l += outerStep, m++) {
/*  856 */                 int m1, n; if (this.reader.getAbortRequest()) {
/*      */                   break;
/*      */                 }
/*  859 */                 switch (dataType) {
/*      */                   case 0:
/*  861 */                     if (innerStep == 1 && innerStep1 == 1) {
/*  862 */                       this.iis.readFully(destPixbuf, destPos, readLength); break;
/*      */                     } 
/*  864 */                     this.iis.readFully(pixbuf, 0, readLength);
/*  865 */                     for (m1 = 0, n = destPos; m1 < readLength; 
/*  866 */                       m1 += innerStep, n += innerStep1) {
/*  867 */                       destPixbuf[n] = pixbuf[m1];
/*      */                     }
/*      */                     break;
/*      */                   
/*      */                   case 1:
/*      */                   case 2:
/*  873 */                     if (innerStep == 1 && innerStep1 == 1) {
/*  874 */                       this.iis.readFully(destSPixbuf, destPos, readLength); break;
/*      */                     } 
/*  876 */                     this.iis.readFully(spixbuf, 0, readLength);
/*  877 */                     for (m1 = 0, n = destPos; m1 < readLength; 
/*  878 */                       m1 += innerStep, n += innerStep1) {
/*  879 */                       destSPixbuf[n] = spixbuf[m1];
/*      */                     }
/*      */                     break;
/*      */                   
/*      */                   case 3:
/*  884 */                     if (innerStep == 1 && innerStep1 == 1) {
/*  885 */                       this.iis.readFully(destIPixbuf, destPos, readLength); break;
/*      */                     } 
/*  887 */                     this.iis.readFully(ipixbuf, 0, readLength);
/*  888 */                     for (m1 = 0, n = destPos; m1 < readLength; 
/*  889 */                       m1 += innerStep, n += innerStep1) {
/*  890 */                       destIPixbuf[n] = ipixbuf[m1];
/*      */                     }
/*      */                     break;
/*      */                   
/*      */                   case 4:
/*  895 */                     if (innerStep == 1 && innerStep1 == 1) {
/*  896 */                       this.iis.readFully(destFPixbuf, destPos, readLength); break;
/*      */                     } 
/*  898 */                     this.iis.readFully(fpixbuf, 0, readLength);
/*  899 */                     for (m1 = 0, n = destPos; m1 < readLength; 
/*  900 */                       m1 += innerStep, n += innerStep1) {
/*  901 */                       destFPixbuf[n] = fpixbuf[m1];
/*      */                     }
/*      */                     break;
/*      */                   
/*      */                   case 5:
/*  906 */                     if (innerStep == 1 && innerStep1 == 1) {
/*  907 */                       this.iis.readFully(destDPixbuf, destPos, readLength); break;
/*      */                     } 
/*  909 */                     this.iis.readFully(dpixbuf, 0, readLength);
/*  910 */                     for (m1 = 0, n = destPos; m1 < readLength; 
/*  911 */                       m1 += innerStep, n += innerStep1) {
/*  912 */                       destDPixbuf[n] = dpixbuf[m1];
/*      */                     }
/*      */                     break;
/*      */                 } 
/*      */ 
/*      */                 
/*  918 */                 this.iis.skipBytes(skipLength);
/*  919 */                 destPos += outerStep1;
/*      */                 
/*  921 */                 if (this.destImage != null) {
/*  922 */                   int[] destBands = { this.destinationBands[b] };
/*      */                   
/*  924 */                   if (pixelStride < scanlineStride) {
/*  925 */                     this.reader.processImageUpdateWrapper(this.destImage, x1, m, outerBound, 1, 1, 1, destBands);
/*      */                   
/*      */                   }
/*      */                   else {
/*      */ 
/*      */                     
/*  931 */                     this.reader.processImageUpdateWrapper(this.destImage, m, y1, 1, outerBound, 1, 1, destBands);
/*      */                   } 
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  938 */                 this.reader.processImageProgressWrapper((percentage + (l + 1.0F) / outerBound / numBands / totalTiles) * 100.0F);
/*      */               }
/*      */             
/*      */             }
/*      */           
/*      */           }
/*  944 */           else if (this.sampleModel instanceof ComponentSampleModel) {
/*      */ 
/*      */             
/*  947 */             int bufferSize = (int)this.tileDataSize;
/*      */             
/*  949 */             switch (this.sampleModel.getDataType()) {
/*      */               case 0:
/*  951 */                 if (pixbuf == null || pixbuf.length < this.tileDataSize)
/*  952 */                   pixbuf = new byte[(int)this.tileDataSize]; 
/*  953 */                 this.iis.readFully(pixbuf, 0, (int)this.tileDataSize);
/*      */                 break;
/*      */               
/*      */               case 1:
/*      */               case 2:
/*  958 */                 bufferSize /= 2;
/*  959 */                 if (spixbuf == null || spixbuf.length < bufferSize)
/*  960 */                   spixbuf = new short[bufferSize]; 
/*  961 */                 this.iis.readFully(spixbuf, 0, bufferSize);
/*      */                 break;
/*      */               
/*      */               case 3:
/*  965 */                 bufferSize /= 4;
/*  966 */                 if (ipixbuf == null || ipixbuf.length < bufferSize)
/*  967 */                   ipixbuf = new int[bufferSize]; 
/*  968 */                 this.iis.readFully(ipixbuf, 0, bufferSize);
/*      */                 break;
/*      */               
/*      */               case 4:
/*  972 */                 bufferSize /= 4;
/*  973 */                 if (fpixbuf == null || fpixbuf.length < bufferSize)
/*  974 */                   fpixbuf = new float[bufferSize]; 
/*  975 */                 this.iis.readFully(fpixbuf, 0, bufferSize);
/*      */                 break;
/*      */               
/*      */               case 5:
/*  979 */                 bufferSize /= 8;
/*  980 */                 if (dpixbuf == null || dpixbuf.length < bufferSize)
/*  981 */                   dpixbuf = new double[bufferSize]; 
/*  982 */                 this.iis.readFully(dpixbuf, 0, bufferSize);
/*      */                 break;
/*      */             } 
/*      */             
/*  986 */             for (int b = 0; b < numBands; b++) {
/*  987 */               int destBandOffset = destBandOffsets[this.destinationBands[b]];
/*      */ 
/*      */ 
/*      */               
/*  991 */               int destPos = ((ComponentSampleModel)raster.getSampleModel()).getOffset(x1 - raster
/*  992 */                   .getSampleModelTranslateX(), y1 - raster
/*  993 */                   .getSampleModelTranslateY(), this.destinationBands[b]);
/*      */ 
/*      */               
/*  996 */               int bank = bankIndices[this.destinationBands[b]];
/*      */               
/*  998 */               switch (dataType) {
/*      */                 
/*      */                 case 0:
/* 1001 */                   destPixbuf = ((DataBufferByte)raster.getDataBuffer()).getData(bank);
/*      */                   break;
/*      */                 
/*      */                 case 2:
/* 1005 */                   destSPixbuf = ((DataBufferShort)raster.getDataBuffer()).getData(bank);
/*      */                   break;
/*      */ 
/*      */                 
/*      */                 case 1:
/* 1010 */                   destSPixbuf = ((DataBufferUShort)raster.getDataBuffer()).getData(bank);
/*      */                   break;
/*      */ 
/*      */                 
/*      */                 case 3:
/* 1015 */                   destIPixbuf = ((DataBufferInt)raster.getDataBuffer()).getData(bank);
/*      */                   break;
/*      */ 
/*      */                 
/*      */                 case 4:
/* 1020 */                   destFPixbuf = ((DataBufferFloat)raster.getDataBuffer()).getData(bank);
/*      */                   break;
/*      */ 
/*      */                 
/*      */                 case 5:
/* 1025 */                   destDPixbuf = ((DataBufferDouble)raster.getDataBuffer()).getData(bank);
/*      */                   break;
/*      */               } 
/*      */ 
/*      */               
/* 1030 */               int srcPos = ((ComponentSampleModel)this.originalSampleModel).getOffset(tx, ty, this.sourceBands[b]);
/* 1031 */               int skipX = this.scaleX * pixelStride;
/* 1032 */               for (int l = 0, m = y1; l < cTileHeight; 
/* 1033 */                 l += this.scaleY, m++) {
/* 1034 */                 int n, m1, m2; if (this.reader.getAbortRequest()) {
/*      */                   break;
/*      */                 }
/* 1037 */                 switch (dataType) {
/*      */                   case 0:
/* 1039 */                     n = 0; m1 = srcPos; m2 = destPos;
/* 1040 */                     for (; n < lineLength; 
/* 1041 */                       n++, m1 += skipX, m2 += destPixelStride)
/* 1042 */                       destPixbuf[m2] = pixbuf[m1]; 
/*      */                     break;
/*      */                   case 1:
/*      */                   case 2:
/* 1046 */                     n = 0; m1 = srcPos; m2 = destPos;
/* 1047 */                     for (; n < lineLength; 
/* 1048 */                       n++, m1 += skipX, m2 += destPixelStride)
/* 1049 */                       destSPixbuf[m2] = spixbuf[m1]; 
/*      */                     break;
/*      */                   case 3:
/* 1052 */                     n = 0; m1 = srcPos; m2 = destPos;
/* 1053 */                     for (; n < lineLength; 
/* 1054 */                       n++, m1 += skipX, m2 += destPixelStride)
/* 1055 */                       destIPixbuf[m2] = ipixbuf[m1]; 
/*      */                     break;
/*      */                   case 4:
/* 1058 */                     n = 0; m1 = srcPos; m2 = destPos;
/* 1059 */                     for (; n < lineLength; 
/* 1060 */                       n++, m1 += skipX, m2 += destPixelStride)
/* 1061 */                       destFPixbuf[m2] = fpixbuf[m1]; 
/*      */                     break;
/*      */                   case 5:
/* 1064 */                     n = 0; m1 = srcPos; m2 = destPos;
/* 1065 */                     for (; n < lineLength; 
/* 1066 */                       n++, m1 += skipX, m2 += destPixelStride) {
/* 1067 */                       destDPixbuf[m2] = dpixbuf[m1];
/*      */                     }
/*      */                     break;
/*      */                 } 
/* 1071 */                 destPos += destScanlineStride;
/* 1072 */                 srcPos += scanlineStride * this.scaleY;
/*      */                 
/* 1074 */                 if (this.destImage != null) {
/* 1075 */                   int[] destBands = { this.destinationBands[b] };
/*      */                   
/* 1077 */                   this.reader.processImageUpdateWrapper(this.destImage, x1, m, cTileHeight, 1, 1, 1, destBands);
/*      */                 } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/* 1084 */                 this.reader.processImageProgressWrapper(percentage + (l + 1.0F) / cTileHeight / numBands / totalTiles);
/*      */               }
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */           else {
/*      */             
/* 1092 */             throw new IllegalArgumentException(I18N.getString("RawRenderedImage1"));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 1098 */     return raster;
/*      */   }
/*      */   
/*      */   public void setDestImage(BufferedImage image) {
/* 1102 */     this.destImage = image;
/*      */   }
/*      */   
/*      */   public void clearDestImage() {
/* 1106 */     this.destImage = null;
/*      */   }
/*      */   
/*      */   private int getTileNum(int x, int y) {
/* 1110 */     int num = (y - getMinTileY()) * getNumXTiles() + x - getMinTileX();
/*      */     
/* 1112 */     if (num < 0 || num >= getNumXTiles() * getNumYTiles()) {
/* 1113 */       throw new IllegalArgumentException(I18N.getString("RawRenderedImage0"));
/*      */     }
/* 1115 */     return num;
/*      */   }
/*      */   
/*      */   private int clip(int value, int min, int max) {
/* 1119 */     if (value < min)
/* 1120 */       value = min; 
/* 1121 */     if (value > max)
/* 1122 */       value = max; 
/* 1123 */     return value;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/raw/RawRenderedImage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */