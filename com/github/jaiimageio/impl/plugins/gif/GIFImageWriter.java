/*      */ package com.github.jaiimageio.impl.plugins.gif;
/*      */ 
/*      */ import com.github.jaiimageio.impl.common.LZWCompressor;
/*      */ import com.github.jaiimageio.impl.common.PaletteBuilder;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentSampleModel;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.IndexColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.io.IOException;
/*      */ import java.nio.ByteOrder;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ import javax.imageio.IIOException;
/*      */ import javax.imageio.IIOImage;
/*      */ import javax.imageio.ImageTypeSpecifier;
/*      */ import javax.imageio.ImageWriteParam;
/*      */ import javax.imageio.ImageWriter;
/*      */ import javax.imageio.metadata.IIOInvalidTreeException;
/*      */ import javax.imageio.metadata.IIOMetadata;
/*      */ import javax.imageio.metadata.IIOMetadataNode;
/*      */ import javax.imageio.stream.ImageOutputStream;
/*      */ import org.w3c.dom.Node;
/*      */ import org.w3c.dom.NodeList;
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
/*      */ public class GIFImageWriter
/*      */   extends ImageWriter
/*      */ {
/*      */   private static final boolean DEBUG = false;
/*      */   static final String STANDARD_METADATA_NAME = "javax_imageio_1.0";
/*      */   static final String STREAM_METADATA_NAME = "javax_imageio_gif_stream_1.0";
/*      */   static final String IMAGE_METADATA_NAME = "javax_imageio_gif_image_1.0";
/*   97 */   private ImageOutputStream stream = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isWritingSequence = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean wroteSequenceHeader = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  112 */   private GIFWritableStreamMetadata theStreamMetadata = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  117 */   private int imageIndex = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getNumBits(int value) throws IOException {
/*      */     int numBits;
/*  125 */     switch (value) {
/*      */       case 2:
/*  127 */         numBits = 1;
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
/*  154 */         return numBits;case 4: numBits = 2; return numBits;case 8: numBits = 3; return numBits;case 16: numBits = 4; return numBits;case 32: numBits = 5; return numBits;case 64: numBits = 6; return numBits;case 128: numBits = 7; return numBits;case 256: numBits = 8; return numBits;
/*      */     } 
/*      */     throw new IOException("Bad palette length: " + value + "!");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void computeRegions(Rectangle sourceBounds, Dimension destSize, ImageWriteParam p) {
/*  165 */     int periodX = 1;
/*  166 */     int periodY = 1;
/*  167 */     if (p != null) {
/*  168 */       int[] sourceBands = p.getSourceBands();
/*  169 */       if (sourceBands != null && (sourceBands.length != 1 || sourceBands[0] != 0))
/*      */       {
/*      */         
/*  172 */         throw new IllegalArgumentException("Cannot sub-band image!");
/*      */       }
/*      */ 
/*      */       
/*  176 */       Rectangle sourceRegion = p.getSourceRegion();
/*  177 */       if (sourceRegion != null) {
/*      */         
/*  179 */         sourceRegion = sourceRegion.intersection(sourceBounds);
/*  180 */         sourceBounds.setBounds(sourceRegion);
/*      */       } 
/*      */ 
/*      */       
/*  184 */       int gridX = p.getSubsamplingXOffset();
/*  185 */       int gridY = p.getSubsamplingYOffset();
/*  186 */       sourceBounds.x += gridX;
/*  187 */       sourceBounds.y += gridY;
/*  188 */       sourceBounds.width -= gridX;
/*  189 */       sourceBounds.height -= gridY;
/*      */ 
/*      */       
/*  192 */       periodX = p.getSourceXSubsampling();
/*  193 */       periodY = p.getSourceYSubsampling();
/*      */     } 
/*      */ 
/*      */     
/*  197 */     destSize.setSize((sourceBounds.width + periodX - 1) / periodX, (sourceBounds.height + periodY - 1) / periodY);
/*      */     
/*  199 */     if (destSize.width <= 0 || destSize.height <= 0) {
/*  200 */       throw new IllegalArgumentException("Empty source region!");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static byte[] createColorTable(ColorModel colorModel, SampleModel sampleModel) {
/*      */     byte[] colorTable;
/*  211 */     if (colorModel instanceof IndexColorModel) {
/*  212 */       IndexColorModel icm = (IndexColorModel)colorModel;
/*  213 */       int mapSize = icm.getMapSize();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  220 */       int ctSize = getGifPaletteSize(mapSize);
/*      */       
/*  222 */       byte[] reds = new byte[ctSize];
/*  223 */       byte[] greens = new byte[ctSize];
/*  224 */       byte[] blues = new byte[ctSize];
/*  225 */       icm.getReds(reds);
/*  226 */       icm.getGreens(greens);
/*  227 */       icm.getBlues(blues);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  233 */       for (int i = mapSize; i < ctSize; i++) {
/*  234 */         reds[i] = reds[0];
/*  235 */         greens[i] = greens[0];
/*  236 */         blues[i] = blues[0];
/*      */       } 
/*      */       
/*  239 */       colorTable = new byte[3 * ctSize];
/*  240 */       int idx = 0;
/*  241 */       for (int j = 0; j < ctSize; j++) {
/*  242 */         colorTable[idx++] = reds[j];
/*  243 */         colorTable[idx++] = greens[j];
/*  244 */         colorTable[idx++] = blues[j];
/*      */       } 
/*  246 */     } else if (sampleModel.getNumBands() == 1) {
/*      */       
/*  248 */       int numBits = sampleModel.getSampleSize()[0];
/*  249 */       if (numBits > 8) {
/*  250 */         numBits = 8;
/*      */       }
/*  252 */       int colorTableLength = 3 * (1 << numBits);
/*  253 */       colorTable = new byte[colorTableLength];
/*  254 */       for (int i = 0; i < colorTableLength; i++) {
/*  255 */         colorTable[i] = (byte)(i / 3);
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  260 */       colorTable = null;
/*      */     } 
/*      */     
/*  263 */     return colorTable;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getGifPaletteSize(int x) {
/*  271 */     if (x <= 2) {
/*  272 */       return 2;
/*      */     }
/*  274 */     x--;
/*  275 */     x |= x >> 1;
/*  276 */     x |= x >> 2;
/*  277 */     x |= x >> 4;
/*  278 */     x |= x >> 8;
/*  279 */     x |= x >> 16;
/*  280 */     return x + 1;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public GIFImageWriter(GIFImageWriterSpi originatingProvider) {
/*  286 */     super(originatingProvider);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canWriteSequence() {
/*  293 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void convertMetadata(String metadataFormatName, IIOMetadata inData, IIOMetadata outData) {
/*  304 */     String formatName = null;
/*      */     
/*  306 */     String nativeFormatName = inData.getNativeMetadataFormatName();
/*  307 */     if (nativeFormatName != null && nativeFormatName
/*  308 */       .equals(metadataFormatName)) {
/*  309 */       formatName = metadataFormatName;
/*      */     } else {
/*  311 */       String[] extraFormatNames = inData.getExtraMetadataFormatNames();
/*      */       
/*  313 */       if (extraFormatNames != null) {
/*  314 */         for (int i = 0; i < extraFormatNames.length; i++) {
/*  315 */           if (extraFormatNames[i].equals(metadataFormatName)) {
/*  316 */             formatName = metadataFormatName;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*  323 */     if (formatName == null && inData
/*  324 */       .isStandardMetadataFormatSupported()) {
/*  325 */       formatName = "javax_imageio_1.0";
/*      */     }
/*      */     
/*  328 */     if (formatName != null) {
/*      */       try {
/*  330 */         Node root = inData.getAsTree(formatName);
/*  331 */         outData.mergeTree(formatName, root);
/*  332 */       } catch (IIOInvalidTreeException iIOInvalidTreeException) {}
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
/*      */   public IIOMetadata convertStreamMetadata(IIOMetadata inData, ImageWriteParam param) {
/*  344 */     if (inData == null) {
/*  345 */       throw new IllegalArgumentException("inData == null!");
/*      */     }
/*      */     
/*  348 */     IIOMetadata sm = getDefaultStreamMetadata(param);
/*      */     
/*  350 */     convertMetadata("javax_imageio_gif_stream_1.0", inData, sm);
/*      */     
/*  352 */     return sm;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public IIOMetadata convertImageMetadata(IIOMetadata inData, ImageTypeSpecifier imageType, ImageWriteParam param) {
/*  362 */     if (inData == null) {
/*  363 */       throw new IllegalArgumentException("inData == null!");
/*      */     }
/*  365 */     if (imageType == null) {
/*  366 */       throw new IllegalArgumentException("imageType == null!");
/*      */     }
/*      */ 
/*      */     
/*  370 */     GIFWritableImageMetadata im = (GIFWritableImageMetadata)getDefaultImageMetadata(imageType, param);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  375 */     boolean isProgressive = im.interlaceFlag;
/*      */     
/*  377 */     convertMetadata("javax_imageio_gif_image_1.0", inData, im);
/*      */ 
/*      */ 
/*      */     
/*  381 */     if (param != null && param.canWriteProgressive() && param
/*  382 */       .getProgressiveMode() != 3) {
/*  383 */       im.interlaceFlag = isProgressive;
/*      */     }
/*      */     
/*  386 */     return im;
/*      */   }
/*      */   
/*      */   public void endWriteSequence() throws IOException {
/*  390 */     if (this.stream == null) {
/*  391 */       throw new IllegalStateException("output == null!");
/*      */     }
/*  393 */     if (!this.isWritingSequence) {
/*  394 */       throw new IllegalStateException("prepareWriteSequence() was not invoked!");
/*      */     }
/*  396 */     writeTrailer();
/*  397 */     resetLocal();
/*      */   }
/*      */ 
/*      */   
/*      */   public IIOMetadata getDefaultImageMetadata(ImageTypeSpecifier imageType, ImageWriteParam param) {
/*  402 */     GIFWritableImageMetadata imageMetadata = new GIFWritableImageMetadata();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  407 */     SampleModel sampleModel = imageType.getSampleModel();
/*      */ 
/*      */     
/*  410 */     Rectangle sourceBounds = new Rectangle(sampleModel.getWidth(), sampleModel.getHeight());
/*  411 */     Dimension destSize = new Dimension();
/*  412 */     computeRegions(sourceBounds, destSize, param);
/*      */     
/*  414 */     imageMetadata.imageWidth = destSize.width;
/*  415 */     imageMetadata.imageHeight = destSize.height;
/*      */ 
/*      */ 
/*      */     
/*  419 */     if (param != null && param.canWriteProgressive() && param
/*  420 */       .getProgressiveMode() == 0) {
/*  421 */       imageMetadata.interlaceFlag = false;
/*      */     } else {
/*  423 */       imageMetadata.interlaceFlag = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  428 */     ColorModel colorModel = imageType.getColorModel();
/*      */     
/*  430 */     imageMetadata
/*  431 */       .localColorTable = createColorTable(colorModel, sampleModel);
/*      */ 
/*      */ 
/*      */     
/*  435 */     if (colorModel instanceof IndexColorModel) {
/*      */       
/*  437 */       int transparentIndex = ((IndexColorModel)colorModel).getTransparentPixel();
/*  438 */       if (transparentIndex != -1) {
/*  439 */         imageMetadata.transparentColorFlag = true;
/*  440 */         imageMetadata.transparentColorIndex = transparentIndex;
/*      */       } 
/*      */     } 
/*      */     
/*  444 */     return imageMetadata;
/*      */   }
/*      */   
/*      */   public IIOMetadata getDefaultStreamMetadata(ImageWriteParam param) {
/*  448 */     GIFWritableStreamMetadata streamMetadata = new GIFWritableStreamMetadata();
/*      */     
/*  450 */     streamMetadata.version = "89a";
/*  451 */     return streamMetadata;
/*      */   }
/*      */   
/*      */   public ImageWriteParam getDefaultWriteParam() {
/*  455 */     return new GIFImageWriteParam(getLocale());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void prepareWriteSequence(IIOMetadata streamMetadata) throws IOException {
/*  461 */     if (this.stream == null) {
/*  462 */       throw new IllegalStateException("Output is not set.");
/*      */     }
/*      */     
/*  465 */     resetLocal();
/*      */ 
/*      */     
/*  468 */     if (streamMetadata == null) {
/*  469 */       this
/*  470 */         .theStreamMetadata = (GIFWritableStreamMetadata)getDefaultStreamMetadata((ImageWriteParam)null);
/*      */     } else {
/*  472 */       this.theStreamMetadata = new GIFWritableStreamMetadata();
/*  473 */       convertMetadata("javax_imageio_gif_stream_1.0", streamMetadata, this.theStreamMetadata);
/*      */     } 
/*      */ 
/*      */     
/*  477 */     this.isWritingSequence = true;
/*      */   }
/*      */   
/*      */   public void reset() {
/*  481 */     super.reset();
/*  482 */     resetLocal();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void resetLocal() {
/*  489 */     this.isWritingSequence = false;
/*  490 */     this.wroteSequenceHeader = false;
/*  491 */     this.theStreamMetadata = null;
/*  492 */     this.imageIndex = 0;
/*      */   }
/*      */   
/*      */   public void setOutput(Object output) {
/*  496 */     super.setOutput(output);
/*  497 */     if (output != null) {
/*  498 */       if (!(output instanceof ImageOutputStream)) {
/*  499 */         throw new IllegalArgumentException("output is not an ImageOutputStream");
/*      */       }
/*      */       
/*  502 */       this.stream = (ImageOutputStream)output;
/*  503 */       this.stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
/*      */     } else {
/*  505 */       this.stream = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void write(IIOMetadata sm, IIOImage iioimage, ImageWriteParam p) throws IOException {
/*      */     GIFWritableStreamMetadata streamMetadata;
/*  512 */     if (this.stream == null) {
/*  513 */       throw new IllegalStateException("output == null!");
/*      */     }
/*  515 */     if (iioimage == null) {
/*  516 */       throw new IllegalArgumentException("iioimage == null!");
/*      */     }
/*  518 */     if (iioimage.hasRaster()) {
/*  519 */       throw new UnsupportedOperationException("canWriteRasters() == false!");
/*      */     }
/*      */     
/*  522 */     resetLocal();
/*      */ 
/*      */     
/*  525 */     if (sm == null) {
/*      */       
/*  527 */       streamMetadata = (GIFWritableStreamMetadata)getDefaultStreamMetadata(p);
/*      */     } else {
/*      */       
/*  530 */       streamMetadata = (GIFWritableStreamMetadata)convertStreamMetadata(sm, p);
/*      */     } 
/*      */     
/*  533 */     write(true, true, streamMetadata, iioimage, p);
/*      */   }
/*      */ 
/*      */   
/*      */   public void writeToSequence(IIOImage image, ImageWriteParam param) throws IOException {
/*  538 */     if (this.stream == null) {
/*  539 */       throw new IllegalStateException("output == null!");
/*      */     }
/*  541 */     if (image == null) {
/*  542 */       throw new IllegalArgumentException("image == null!");
/*      */     }
/*  544 */     if (image.hasRaster()) {
/*  545 */       throw new UnsupportedOperationException("canWriteRasters() == false!");
/*      */     }
/*  547 */     if (!this.isWritingSequence) {
/*  548 */       throw new IllegalStateException("prepareWriteSequence() was not invoked!");
/*      */     }
/*      */     
/*  551 */     write(!this.wroteSequenceHeader, false, this.theStreamMetadata, image, param);
/*      */ 
/*      */     
/*  554 */     if (!this.wroteSequenceHeader) {
/*  555 */       this.wroteSequenceHeader = true;
/*      */     }
/*      */     
/*  558 */     this.imageIndex++;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean needToCreateIndex(RenderedImage image) {
/*  564 */     SampleModel sampleModel = image.getSampleModel();
/*  565 */     ColorModel colorModel = image.getColorModel();
/*      */     
/*  567 */     return (sampleModel.getNumBands() != 1 || sampleModel
/*  568 */       .getSampleSize()[0] > 8 || colorModel
/*  569 */       .getComponentSize()[0] > 8);
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
/*      */   private void write(boolean writeHeader, boolean writeTrailer, IIOMetadata sm, IIOImage iioimage, ImageWriteParam p) throws IOException {
/*  599 */     clearAbortRequest();
/*      */     
/*  601 */     RenderedImage image = iioimage.getRenderedImage();
/*      */ 
/*      */     
/*  604 */     if (needToCreateIndex(image)) {
/*  605 */       image = PaletteBuilder.createIndexedImage(image);
/*  606 */       iioimage.setRenderedImage(image);
/*      */     } 
/*      */     
/*  609 */     ColorModel colorModel = image.getColorModel();
/*  610 */     SampleModel sampleModel = image.getSampleModel();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  616 */     Rectangle sourceBounds = new Rectangle(image.getMinX(), image.getMinY(), image.getWidth(), image.getHeight());
/*  617 */     Dimension destSize = new Dimension();
/*  618 */     computeRegions(sourceBounds, destSize, p);
/*      */ 
/*      */     
/*  621 */     GIFWritableImageMetadata imageMetadata = null;
/*  622 */     if (iioimage.getMetadata() != null) {
/*  623 */       imageMetadata = new GIFWritableImageMetadata();
/*  624 */       convertMetadata("javax_imageio_gif_image_1.0", iioimage.getMetadata(), imageMetadata);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  633 */       if (imageMetadata.localColorTable == null) {
/*  634 */         imageMetadata
/*  635 */           .localColorTable = createColorTable(colorModel, sampleModel);
/*      */ 
/*      */ 
/*      */         
/*  639 */         if (colorModel instanceof IndexColorModel) {
/*  640 */           IndexColorModel icm = (IndexColorModel)colorModel;
/*      */           
/*  642 */           int index = icm.getTransparentPixel();
/*  643 */           imageMetadata.transparentColorFlag = (index != -1);
/*  644 */           if (imageMetadata.transparentColorFlag) {
/*  645 */             imageMetadata.transparentColorIndex = index;
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  658 */     byte[] globalColorTable = null;
/*      */ 
/*      */ 
/*      */     
/*  662 */     if (writeHeader) {
/*  663 */       int bitsPerPixel; if (sm == null) {
/*  664 */         throw new IllegalArgumentException("Cannot write null header!");
/*      */       }
/*      */       
/*  667 */       GIFWritableStreamMetadata streamMetadata = (GIFWritableStreamMetadata)sm;
/*      */ 
/*      */ 
/*      */       
/*  671 */       if (streamMetadata.version == null) {
/*  672 */         streamMetadata.version = "89a";
/*      */       }
/*      */ 
/*      */       
/*  676 */       if (streamMetadata.logicalScreenWidth == -1)
/*      */       {
/*      */         
/*  679 */         streamMetadata.logicalScreenWidth = destSize.width;
/*      */       }
/*      */       
/*  682 */       if (streamMetadata.logicalScreenHeight == -1)
/*      */       {
/*      */         
/*  685 */         streamMetadata.logicalScreenHeight = destSize.height;
/*      */       }
/*      */       
/*  688 */       if (streamMetadata.colorResolution == -1)
/*      */       {
/*      */         
/*  691 */         streamMetadata
/*      */           
/*  693 */           .colorResolution = (colorModel != null) ? colorModel.getComponentSize()[0] : sampleModel.getSampleSize()[0];
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  698 */       if (streamMetadata.globalColorTable == null) {
/*  699 */         if (this.isWritingSequence && imageMetadata != null && imageMetadata.localColorTable != null) {
/*      */ 
/*      */ 
/*      */           
/*  703 */           streamMetadata.globalColorTable = imageMetadata.localColorTable;
/*      */         }
/*  705 */         else if (imageMetadata == null || imageMetadata.localColorTable == null) {
/*      */ 
/*      */           
/*  708 */           streamMetadata
/*  709 */             .globalColorTable = createColorTable(colorModel, sampleModel);
/*      */         } 
/*      */       }
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
/*  722 */       globalColorTable = streamMetadata.globalColorTable;
/*      */ 
/*      */ 
/*      */       
/*  726 */       if (globalColorTable != null) {
/*  727 */         bitsPerPixel = getNumBits(globalColorTable.length / 3);
/*  728 */       } else if (imageMetadata != null && imageMetadata.localColorTable != null) {
/*      */ 
/*      */         
/*  731 */         bitsPerPixel = getNumBits(imageMetadata.localColorTable.length / 3);
/*      */       } else {
/*  733 */         bitsPerPixel = sampleModel.getSampleSize(0);
/*      */       } 
/*  735 */       writeHeader(streamMetadata, bitsPerPixel);
/*  736 */     } else if (this.isWritingSequence) {
/*  737 */       globalColorTable = this.theStreamMetadata.globalColorTable;
/*      */     } else {
/*  739 */       throw new IllegalArgumentException("Must write header for single image!");
/*      */     } 
/*      */ 
/*      */     
/*  743 */     writeImage(iioimage.getRenderedImage(), imageMetadata, p, globalColorTable, sourceBounds, destSize);
/*      */ 
/*      */ 
/*      */     
/*  747 */     if (writeTrailer) {
/*  748 */       writeTrailer();
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
/*      */   private void writeImage(RenderedImage image, GIFWritableImageMetadata imageMetadata, ImageWriteParam param, byte[] globalColorTable, Rectangle sourceBounds, Dimension destSize) throws IOException {
/*      */     boolean writeGraphicsControlExtension;
/*  766 */     ColorModel colorModel = image.getColorModel();
/*  767 */     SampleModel sampleModel = image.getSampleModel();
/*      */ 
/*      */     
/*  770 */     if (imageMetadata == null) {
/*      */       
/*  772 */       imageMetadata = (GIFWritableImageMetadata)getDefaultImageMetadata(new ImageTypeSpecifier(image), param);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  777 */       writeGraphicsControlExtension = imageMetadata.transparentColorFlag;
/*      */     } else {
/*      */       
/*  780 */       NodeList list = null;
/*      */       
/*      */       try {
/*  783 */         IIOMetadataNode root = (IIOMetadataNode)imageMetadata.getAsTree("javax_imageio_gif_image_1.0");
/*  784 */         list = root.getElementsByTagName("GraphicControlExtension");
/*  785 */       } catch (IllegalArgumentException illegalArgumentException) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  791 */       writeGraphicsControlExtension = (list != null && list.getLength() > 0);
/*      */ 
/*      */ 
/*      */       
/*  795 */       if (param != null && param.canWriteProgressive()) {
/*  796 */         if (param.getProgressiveMode() == 0) {
/*      */           
/*  798 */           imageMetadata.interlaceFlag = false;
/*  799 */         } else if (param.getProgressiveMode() == 1) {
/*      */           
/*  801 */           imageMetadata.interlaceFlag = true;
/*      */         } 
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  807 */     if (Arrays.equals(globalColorTable, imageMetadata.localColorTable)) {
/*  808 */       imageMetadata.localColorTable = null;
/*      */     }
/*      */ 
/*      */     
/*  812 */     imageMetadata.imageWidth = destSize.width;
/*  813 */     imageMetadata.imageHeight = destSize.height;
/*      */ 
/*      */     
/*  816 */     if (writeGraphicsControlExtension) {
/*  817 */       writeGraphicControlExtension(imageMetadata);
/*      */     }
/*      */ 
/*      */     
/*  821 */     writePlainTextExtension(imageMetadata);
/*  822 */     writeApplicationExtension(imageMetadata);
/*  823 */     writeCommentExtension(imageMetadata);
/*      */ 
/*      */ 
/*      */     
/*  827 */     int bitsPerPixel = getNumBits((imageMetadata.localColorTable == null) ? ((globalColorTable == null) ? sampleModel
/*      */         
/*  829 */         .getSampleSize(0) : (globalColorTable.length / 3)) : (imageMetadata.localColorTable.length / 3));
/*      */ 
/*      */     
/*  832 */     writeImageDescriptor(imageMetadata, bitsPerPixel);
/*      */ 
/*      */     
/*  835 */     writeRasterData(image, sourceBounds, destSize, param, imageMetadata.interlaceFlag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeRows(RenderedImage image, LZWCompressor compressor, int sx, int sdx, int sy, int sdy, int sw, int dy, int ddy, int dw, int dh, int numRowsWritten, int progressReportRowPeriod) throws IOException {
/*  846 */     int[] sbuf = new int[sw];
/*  847 */     byte[] dbuf = new byte[dw];
/*      */ 
/*      */ 
/*      */     
/*  851 */     Raster raster = (image.getNumXTiles() == 1 && image.getNumYTiles() == 1) ? image.getTile(0, 0) : image.getData(); int y;
/*  852 */     for (y = dy; y < dh; y += ddy) {
/*  853 */       if (numRowsWritten % progressReportRowPeriod == 0) {
/*  854 */         if (abortRequested()) {
/*  855 */           processWriteAborted();
/*      */           return;
/*      */         } 
/*  858 */         processImageProgress(numRowsWritten * 100.0F / dh);
/*      */       } 
/*      */       
/*  861 */       raster.getSamples(sx, sy, sw, 1, 0, sbuf); int j;
/*  862 */       for (int i = 0; i < dw; i++, j += sdx) {
/*  863 */         dbuf[i] = (byte)sbuf[j];
/*      */       }
/*  865 */       compressor.compress(dbuf, 0, dw);
/*  866 */       numRowsWritten++;
/*  867 */       sy += sdy;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeRowsOpt(byte[] data, int offset, int lineStride, LZWCompressor compressor, int dy, int ddy, int dw, int dh, int numRowsWritten, int progressReportRowPeriod) throws IOException {
/*  878 */     offset += dy * lineStride;
/*  879 */     lineStride *= ddy; int y;
/*  880 */     for (y = dy; y < dh; y += ddy) {
/*  881 */       if (numRowsWritten % progressReportRowPeriod == 0) {
/*  882 */         if (abortRequested()) {
/*  883 */           processWriteAborted();
/*      */           return;
/*      */         } 
/*  886 */         processImageProgress(numRowsWritten * 100.0F / dh);
/*      */       } 
/*      */       
/*  889 */       compressor.compress(data, offset, dw);
/*  890 */       numRowsWritten++;
/*  891 */       offset += lineStride;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeRasterData(RenderedImage image, Rectangle sourceBounds, Dimension destSize, ImageWriteParam param, boolean interlaceFlag) throws IOException {
/*  901 */     int periodX, periodY, sourceXOffset = sourceBounds.x;
/*  902 */     int sourceYOffset = sourceBounds.y;
/*  903 */     int sourceWidth = sourceBounds.width;
/*  904 */     int sourceHeight = sourceBounds.height;
/*      */     
/*  906 */     int destWidth = destSize.width;
/*  907 */     int destHeight = destSize.height;
/*      */ 
/*      */ 
/*      */     
/*  911 */     if (param == null) {
/*  912 */       periodX = 1;
/*  913 */       periodY = 1;
/*      */     } else {
/*  915 */       periodX = param.getSourceXSubsampling();
/*  916 */       periodY = param.getSourceYSubsampling();
/*      */     } 
/*      */     
/*  919 */     SampleModel sampleModel = image.getSampleModel();
/*  920 */     int bitsPerPixel = sampleModel.getSampleSize()[0];
/*      */     
/*  922 */     int initCodeSize = bitsPerPixel;
/*  923 */     if (initCodeSize == 1) {
/*  924 */       initCodeSize++;
/*      */     }
/*  926 */     this.stream.write(initCodeSize);
/*      */     
/*  928 */     LZWCompressor compressor = new LZWCompressor(this.stream, initCodeSize, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  935 */     boolean isOptimizedCase = (periodX == 1 && periodY == 1 && sampleModel instanceof ComponentSampleModel && image.getNumXTiles() == 1 && image.getNumYTiles() == 1 && image.getTile(0, 0).getDataBuffer() instanceof DataBufferByte);
/*      */     
/*  937 */     int numRowsWritten = 0;
/*      */     
/*  939 */     int progressReportRowPeriod = Math.max(destHeight / 20, 1);
/*      */     
/*  941 */     processImageStarted(this.imageIndex);
/*      */     
/*  943 */     if (interlaceFlag) {
/*      */ 
/*      */       
/*  946 */       if (isOptimizedCase) {
/*  947 */         Raster tile = image.getTile(0, 0);
/*  948 */         byte[] data = ((DataBufferByte)tile.getDataBuffer()).getData();
/*      */         
/*  950 */         ComponentSampleModel csm = (ComponentSampleModel)tile.getSampleModel();
/*  951 */         int offset = csm.getOffset(sourceXOffset - tile
/*  952 */             .getSampleModelTranslateX(), sourceYOffset - tile
/*      */             
/*  954 */             .getSampleModelTranslateY(), 0);
/*      */         
/*  956 */         int lineStride = csm.getScanlineStride();
/*      */         
/*  958 */         writeRowsOpt(data, offset, lineStride, compressor, 0, 8, destWidth, destHeight, numRowsWritten, progressReportRowPeriod);
/*      */ 
/*      */ 
/*      */         
/*  962 */         if (abortRequested()) {
/*      */           return;
/*      */         }
/*      */         
/*  966 */         numRowsWritten += destHeight / 8;
/*      */         
/*  968 */         writeRowsOpt(data, offset, lineStride, compressor, 4, 8, destWidth, destHeight, numRowsWritten, progressReportRowPeriod);
/*      */ 
/*      */ 
/*      */         
/*  972 */         if (abortRequested()) {
/*      */           return;
/*      */         }
/*      */         
/*  976 */         numRowsWritten += (destHeight - 4) / 8;
/*      */         
/*  978 */         writeRowsOpt(data, offset, lineStride, compressor, 2, 4, destWidth, destHeight, numRowsWritten, progressReportRowPeriod);
/*      */ 
/*      */ 
/*      */         
/*  982 */         if (abortRequested()) {
/*      */           return;
/*      */         }
/*      */         
/*  986 */         numRowsWritten += (destHeight - 2) / 4;
/*      */         
/*  988 */         writeRowsOpt(data, offset, lineStride, compressor, 1, 2, destWidth, destHeight, numRowsWritten, progressReportRowPeriod);
/*      */       }
/*      */       else {
/*      */         
/*  992 */         writeRows(image, compressor, sourceXOffset, periodX, sourceYOffset, 8 * periodY, sourceWidth, 0, 8, destWidth, destHeight, numRowsWritten, progressReportRowPeriod);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  999 */         if (abortRequested()) {
/*      */           return;
/*      */         }
/*      */         
/* 1003 */         numRowsWritten += destHeight / 8;
/*      */         
/* 1005 */         writeRows(image, compressor, sourceXOffset, periodX, sourceYOffset + 4 * periodY, 8 * periodY, sourceWidth, 4, 8, destWidth, destHeight, numRowsWritten, progressReportRowPeriod);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1011 */         if (abortRequested()) {
/*      */           return;
/*      */         }
/*      */         
/* 1015 */         numRowsWritten += (destHeight - 4) / 8;
/*      */         
/* 1017 */         writeRows(image, compressor, sourceXOffset, periodX, sourceYOffset + 2 * periodY, 4 * periodY, sourceWidth, 2, 4, destWidth, destHeight, numRowsWritten, progressReportRowPeriod);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1023 */         if (abortRequested()) {
/*      */           return;
/*      */         }
/*      */         
/* 1027 */         numRowsWritten += (destHeight - 2) / 4;
/*      */         
/* 1029 */         writeRows(image, compressor, sourceXOffset, periodX, sourceYOffset + periodY, 2 * periodY, sourceWidth, 1, 2, destWidth, destHeight, numRowsWritten, progressReportRowPeriod);
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */     
/*      */     }
/* 1038 */     else if (isOptimizedCase) {
/* 1039 */       Raster tile = image.getTile(0, 0);
/* 1040 */       byte[] data = ((DataBufferByte)tile.getDataBuffer()).getData();
/*      */       
/* 1042 */       ComponentSampleModel csm = (ComponentSampleModel)tile.getSampleModel();
/* 1043 */       int offset = csm.getOffset(sourceXOffset - tile
/* 1044 */           .getSampleModelTranslateX(), sourceYOffset - tile
/*      */           
/* 1046 */           .getSampleModelTranslateY(), 0);
/*      */       
/* 1048 */       int lineStride = csm.getScanlineStride();
/*      */       
/* 1050 */       writeRowsOpt(data, offset, lineStride, compressor, 0, 1, destWidth, destHeight, numRowsWritten, progressReportRowPeriod);
/*      */     }
/*      */     else {
/*      */       
/* 1054 */       writeRows(image, compressor, sourceXOffset, periodX, sourceYOffset, periodY, sourceWidth, 0, 1, destWidth, destHeight, numRowsWritten, progressReportRowPeriod);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1063 */     if (abortRequested()) {
/*      */       return;
/*      */     }
/*      */     
/* 1067 */     processImageProgress(100.0F);
/*      */     
/* 1069 */     compressor.flush();
/*      */     
/* 1071 */     this.stream.write(0);
/*      */     
/* 1073 */     processImageComplete();
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
/*      */   private void writeHeader(String version, int logicalScreenWidth, int logicalScreenHeight, int colorResolution, int pixelAspectRatio, int backgroundColorIndex, boolean sortFlag, int bitsPerPixel, byte[] globalColorTable) throws IOException {
/*      */     try {
/* 1087 */       this.stream.writeBytes("GIF" + version);
/*      */ 
/*      */ 
/*      */       
/* 1091 */       this.stream.writeShort((short)logicalScreenWidth);
/*      */ 
/*      */       
/* 1094 */       this.stream.writeShort((short)logicalScreenHeight);
/*      */ 
/*      */ 
/*      */       
/* 1098 */       int packedFields = (globalColorTable != null) ? 128 : 0;
/* 1099 */       packedFields |= (colorResolution - 1 & 0x7) << 4;
/* 1100 */       if (sortFlag) {
/* 1101 */         packedFields |= 0x8;
/*      */       }
/* 1103 */       packedFields |= bitsPerPixel - 1;
/* 1104 */       this.stream.write(packedFields);
/*      */ 
/*      */       
/* 1107 */       this.stream.write(backgroundColorIndex);
/*      */ 
/*      */       
/* 1110 */       this.stream.write(pixelAspectRatio);
/*      */ 
/*      */       
/* 1113 */       if (globalColorTable != null) {
/* 1114 */         this.stream.write(globalColorTable);
/*      */       }
/* 1116 */     } catch (IOException e) {
/* 1117 */       throw new IIOException("I/O error writing header!", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeHeader(IIOMetadata streamMetadata, int bitsPerPixel) throws IOException {
/*      */     GIFWritableStreamMetadata sm;
/* 1125 */     if (streamMetadata instanceof GIFWritableStreamMetadata) {
/* 1126 */       sm = (GIFWritableStreamMetadata)streamMetadata;
/*      */     } else {
/* 1128 */       sm = new GIFWritableStreamMetadata();
/*      */       
/* 1130 */       Node root = streamMetadata.getAsTree("javax_imageio_gif_stream_1.0");
/* 1131 */       sm.setFromTree("javax_imageio_gif_stream_1.0", root);
/*      */     } 
/*      */     
/* 1134 */     writeHeader(sm.version, sm.logicalScreenWidth, sm.logicalScreenHeight, sm.colorResolution, sm.pixelAspectRatio, sm.backgroundColorIndex, sm.sortFlag, bitsPerPixel, sm.globalColorTable);
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
/*      */   private void writeGraphicControlExtension(int disposalMethod, boolean userInputFlag, boolean transparentColorFlag, int delayTime, int transparentColorIndex) throws IOException {
/*      */     try {
/* 1152 */       this.stream.write(33);
/* 1153 */       this.stream.write(249);
/*      */       
/* 1155 */       this.stream.write(4);
/*      */       
/* 1157 */       int packedFields = (disposalMethod & 0x3) << 2;
/* 1158 */       if (userInputFlag) {
/* 1159 */         packedFields |= 0x2;
/*      */       }
/* 1161 */       if (transparentColorFlag) {
/* 1162 */         packedFields |= 0x1;
/*      */       }
/* 1164 */       this.stream.write(packedFields);
/*      */       
/* 1166 */       this.stream.writeShort((short)delayTime);
/*      */       
/* 1168 */       this.stream.write(transparentColorIndex);
/* 1169 */       this.stream.write(0);
/* 1170 */     } catch (IOException e) {
/* 1171 */       throw new IIOException("I/O error writing Graphic Control Extension!", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeGraphicControlExtension(GIFWritableImageMetadata im) throws IOException {
/* 1177 */     writeGraphicControlExtension(im.disposalMethod, im.userInputFlag, im.transparentColorFlag, im.delayTime, im.transparentColorIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeBlocks(byte[] data) throws IOException {
/* 1185 */     if (data != null && data.length > 0) {
/* 1186 */       int offset = 0;
/* 1187 */       while (offset < data.length) {
/* 1188 */         int len = Math.min(data.length - offset, 255);
/* 1189 */         this.stream.write(len);
/* 1190 */         this.stream.write(data, offset, len);
/* 1191 */         offset += len;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void writePlainTextExtension(GIFWritableImageMetadata im) throws IOException {
/* 1198 */     if (im.hasPlainTextExtension) {
/*      */       try {
/* 1200 */         this.stream.write(33);
/* 1201 */         this.stream.write(1);
/*      */         
/* 1203 */         this.stream.write(12);
/*      */         
/* 1205 */         this.stream.writeShort(im.textGridLeft);
/* 1206 */         this.stream.writeShort(im.textGridTop);
/* 1207 */         this.stream.writeShort(im.textGridWidth);
/* 1208 */         this.stream.writeShort(im.textGridHeight);
/* 1209 */         this.stream.write(im.characterCellWidth);
/* 1210 */         this.stream.write(im.characterCellHeight);
/* 1211 */         this.stream.write(im.textForegroundColor);
/* 1212 */         this.stream.write(im.textBackgroundColor);
/*      */         
/* 1214 */         writeBlocks(im.text);
/*      */         
/* 1216 */         this.stream.write(0);
/* 1217 */       } catch (IOException e) {
/* 1218 */         throw new IIOException("I/O error writing Plain Text Extension!", e);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeApplicationExtension(GIFWritableImageMetadata im) throws IOException {
/* 1225 */     if (im.applicationIDs != null) {
/* 1226 */       Iterator<byte[]> iterIDs = im.applicationIDs.iterator();
/* 1227 */       Iterator<byte[]> iterCodes = im.authenticationCodes.iterator();
/* 1228 */       Iterator<byte[]> iterData = im.applicationData.iterator();
/*      */       
/* 1230 */       while (iterIDs.hasNext()) {
/*      */         try {
/* 1232 */           this.stream.write(33);
/* 1233 */           this.stream.write(255);
/*      */           
/* 1235 */           this.stream.write(11);
/* 1236 */           this.stream.write(iterIDs.next(), 0, 8);
/* 1237 */           this.stream.write(iterCodes.next(), 0, 3);
/*      */           
/* 1239 */           writeBlocks(iterData.next());
/*      */           
/* 1241 */           this.stream.write(0);
/* 1242 */         } catch (IOException e) {
/* 1243 */           throw new IIOException("I/O error writing Application Extension!", e);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void writeCommentExtension(GIFWritableImageMetadata im) throws IOException {
/* 1251 */     if (im.comments != null) {
/*      */       try {
/* 1253 */         Iterator<byte[]> iter = im.comments.iterator();
/* 1254 */         while (iter.hasNext()) {
/* 1255 */           this.stream.write(33);
/* 1256 */           this.stream.write(254);
/* 1257 */           writeBlocks(iter.next());
/* 1258 */           this.stream.write(0);
/*      */         } 
/* 1260 */       } catch (IOException e) {
/* 1261 */         throw new IIOException("I/O error writing Comment Extension!", e);
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
/*      */ 
/*      */   
/*      */   private void writeImageDescriptor(int imageLeftPosition, int imageTopPosition, int imageWidth, int imageHeight, boolean interlaceFlag, boolean sortFlag, int bitsPerPixel, byte[] localColorTable) throws IOException {
/*      */     try {
/* 1277 */       this.stream.write(44);
/*      */       
/* 1279 */       this.stream.writeShort((short)imageLeftPosition);
/* 1280 */       this.stream.writeShort((short)imageTopPosition);
/* 1281 */       this.stream.writeShort((short)imageWidth);
/* 1282 */       this.stream.writeShort((short)imageHeight);
/*      */       
/* 1284 */       int packedFields = (localColorTable != null) ? 128 : 0;
/* 1285 */       if (interlaceFlag) {
/* 1286 */         packedFields |= 0x40;
/*      */       }
/* 1288 */       if (sortFlag) {
/* 1289 */         packedFields |= 0x8;
/*      */       }
/* 1291 */       packedFields |= bitsPerPixel - 1;
/* 1292 */       this.stream.write(packedFields);
/*      */       
/* 1294 */       if (localColorTable != null) {
/* 1295 */         this.stream.write(localColorTable);
/*      */       }
/* 1297 */     } catch (IOException e) {
/* 1298 */       throw new IIOException("I/O error writing Image Descriptor!", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeImageDescriptor(GIFWritableImageMetadata imageMetadata, int bitsPerPixel) throws IOException {
/* 1306 */     writeImageDescriptor(imageMetadata.imageLeftPosition, imageMetadata.imageTopPosition, imageMetadata.imageWidth, imageMetadata.imageHeight, imageMetadata.interlaceFlag, imageMetadata.sortFlag, bitsPerPixel, imageMetadata.localColorTable);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeTrailer() throws IOException {
/* 1317 */     this.stream.write(59);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/gif/GIFImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */