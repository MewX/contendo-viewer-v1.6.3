/*      */ package com.github.jaiimageio.impl.plugins.tiff;
/*      */ 
/*      */ import com.github.jaiimageio.impl.common.ImageUtil;
/*      */ import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
/*      */ import com.github.jaiimageio.plugins.tiff.TIFFColorConverter;
/*      */ import com.github.jaiimageio.plugins.tiff.TIFFDecompressor;
/*      */ import com.github.jaiimageio.plugins.tiff.TIFFField;
/*      */ import com.github.jaiimageio.plugins.tiff.TIFFImageReadParam;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.color.ICC_ColorSpace;
/*      */ import java.awt.color.ICC_Profile;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.io.IOException;
/*      */ import java.nio.ByteOrder;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import javax.imageio.IIOException;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.imageio.ImageReadParam;
/*      */ import javax.imageio.ImageReader;
/*      */ import javax.imageio.ImageTypeSpecifier;
/*      */ import javax.imageio.metadata.IIOMetadata;
/*      */ import javax.imageio.spi.ImageReaderSpi;
/*      */ import javax.imageio.stream.ImageInputStream;
/*      */ import org.w3c.dom.Node;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TIFFImageReader
/*      */   extends ImageReader
/*      */ {
/*      */   private static final boolean DEBUG = false;
/*  100 */   ImageInputStream stream = null;
/*      */ 
/*      */   
/*      */   boolean gotHeader = false;
/*      */   
/*  105 */   ImageReadParam imageReadParam = getDefaultReadParam();
/*      */ 
/*      */   
/*  108 */   TIFFStreamMetadata streamMetadata = null;
/*      */ 
/*      */   
/*  111 */   int currIndex = -1;
/*      */ 
/*      */   
/*  114 */   TIFFImageMetadata imageMetadata = null;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  119 */   List imageStartPosition = new ArrayList();
/*      */ 
/*      */   
/*  122 */   int numImages = -1;
/*      */ 
/*      */ 
/*      */   
/*  126 */   HashMap imageTypeMap = new HashMap<Object, Object>();
/*      */   
/*  128 */   BufferedImage theImage = null;
/*      */   
/*  130 */   int width = -1;
/*  131 */   int height = -1;
/*  132 */   int numBands = -1;
/*  133 */   int tileOrStripWidth = -1, tileOrStripHeight = -1;
/*      */   
/*  135 */   int planarConfiguration = 1;
/*      */   
/*  137 */   int rowsDone = 0;
/*      */   
/*      */   int compression;
/*      */   
/*      */   int photometricInterpretation;
/*      */   int samplesPerPixel;
/*      */   int[] sampleFormat;
/*      */   int[] bitsPerSample;
/*      */   int[] extraSamples;
/*      */   char[] colorMap;
/*      */   int sourceXOffset;
/*      */   int sourceYOffset;
/*      */   int srcXSubsampling;
/*      */   int srcYSubsampling;
/*      */   int dstWidth;
/*      */   int dstHeight;
/*      */   int dstMinX;
/*      */   int dstMinY;
/*      */   int dstXOffset;
/*      */   int dstYOffset;
/*      */   int tilesAcross;
/*      */   int tilesDown;
/*      */   int pixelsRead;
/*      */   int pixelsToRead;
/*      */   private int[] sourceBands;
/*      */   private int[] destinationBands;
/*      */   private TIFFDecompressor decompressor;
/*      */   
/*      */   public TIFFImageReader(ImageReaderSpi originatingProvider) {
/*  166 */     super(originatingProvider);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInput(Object input, boolean seekForwardOnly, boolean ignoreMetadata) {
/*  172 */     super.setInput(input, seekForwardOnly, ignoreMetadata);
/*      */ 
/*      */     
/*  175 */     resetLocal();
/*      */     
/*  177 */     if (input != null) {
/*  178 */       if (!(input instanceof ImageInputStream)) {
/*  179 */         throw new IllegalArgumentException("input not an ImageInputStream!");
/*      */       }
/*      */       
/*  182 */       this.stream = (ImageInputStream)input;
/*      */     } else {
/*  184 */       this.stream = null;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void readHeader() throws IIOException {
/*  191 */     if (this.gotHeader) {
/*      */       return;
/*      */     }
/*  194 */     if (this.stream == null) {
/*  195 */       throw new IllegalStateException("Input not set!");
/*      */     }
/*      */ 
/*      */     
/*  199 */     this.streamMetadata = new TIFFStreamMetadata();
/*      */     
/*      */     try {
/*  202 */       int byteOrder = this.stream.readUnsignedShort();
/*  203 */       if (byteOrder == 19789) {
/*  204 */         this.streamMetadata.byteOrder = ByteOrder.BIG_ENDIAN;
/*  205 */         this.stream.setByteOrder(ByteOrder.BIG_ENDIAN);
/*  206 */       } else if (byteOrder == 18761) {
/*  207 */         this.streamMetadata.byteOrder = ByteOrder.LITTLE_ENDIAN;
/*  208 */         this.stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
/*      */       } else {
/*  210 */         processWarningOccurred("Bad byte order in header, assuming little-endian");
/*      */         
/*  212 */         this.streamMetadata.byteOrder = ByteOrder.LITTLE_ENDIAN;
/*  213 */         this.stream.setByteOrder(ByteOrder.LITTLE_ENDIAN);
/*      */       } 
/*      */       
/*  216 */       int magic = this.stream.readUnsignedShort();
/*  217 */       if (magic != 42) {
/*  218 */         processWarningOccurred("Bad magic number in header, continuing");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  223 */       long offset = this.stream.readUnsignedInt();
/*  224 */       this.imageStartPosition.add(new Long(offset));
/*  225 */       this.stream.seek(offset);
/*  226 */     } catch (IOException e) {
/*  227 */       throw new IIOException("I/O error reading header!", e);
/*      */     } 
/*      */     
/*  230 */     this.gotHeader = true;
/*      */   }
/*      */   
/*      */   private int locateImage(int imageIndex) throws IIOException {
/*  234 */     readHeader();
/*      */ 
/*      */     
/*      */     try {
/*  238 */       int index = Math.min(imageIndex, this.imageStartPosition.size() - 1);
/*      */ 
/*      */       
/*  241 */       Long l = this.imageStartPosition.get(index);
/*  242 */       this.stream.seek(l.longValue());
/*      */ 
/*      */       
/*  245 */       while (index < imageIndex) {
/*  246 */         int count = this.stream.readUnsignedShort();
/*  247 */         this.stream.skipBytes(12 * count);
/*      */         
/*  249 */         long offset = this.stream.readUnsignedInt();
/*  250 */         if (offset == 0L) {
/*  251 */           return index;
/*      */         }
/*      */         
/*  254 */         this.imageStartPosition.add(new Long(offset));
/*  255 */         this.stream.seek(offset);
/*  256 */         index++;
/*      */       } 
/*  258 */     } catch (IOException e) {
/*  259 */       throw new IIOException("Couldn't seek!", e);
/*      */     } 
/*      */     
/*  262 */     if (this.currIndex != imageIndex) {
/*  263 */       this.imageMetadata = null;
/*      */     }
/*  265 */     this.currIndex = imageIndex;
/*  266 */     return imageIndex;
/*      */   }
/*      */   
/*      */   public int getNumImages(boolean allowSearch) throws IOException {
/*  270 */     if (this.stream == null) {
/*  271 */       throw new IllegalStateException("Input not set!");
/*      */     }
/*  273 */     if (this.seekForwardOnly && allowSearch) {
/*  274 */       throw new IllegalStateException("seekForwardOnly and allowSearch can't both be true!");
/*      */     }
/*      */ 
/*      */     
/*  278 */     if (this.numImages > 0) {
/*  279 */       return this.numImages;
/*      */     }
/*  281 */     if (allowSearch) {
/*  282 */       this.numImages = locateImage(2147483647) + 1;
/*      */     }
/*  284 */     return this.numImages;
/*      */   }
/*      */   
/*      */   public IIOMetadata getStreamMetadata() throws IIOException {
/*  288 */     readHeader();
/*  289 */     return this.streamMetadata;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void checkIndex(int imageIndex) {
/*  295 */     if (imageIndex < this.minIndex) {
/*  296 */       throw new IndexOutOfBoundsException("imageIndex < minIndex!");
/*      */     }
/*  298 */     if (this.seekForwardOnly) {
/*  299 */       this.minIndex = imageIndex;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void seekToImage(int imageIndex) throws IIOException {
/*  306 */     checkIndex(imageIndex);
/*      */     
/*  308 */     int index = locateImage(imageIndex);
/*  309 */     if (index != imageIndex) {
/*  310 */       throw new IndexOutOfBoundsException("imageIndex out of bounds!");
/*      */     }
/*      */     
/*  313 */     readMetadata();
/*      */     
/*  315 */     initializeFromMetadata();
/*      */   }
/*      */ 
/*      */   
/*      */   private void readMetadata() throws IIOException {
/*  320 */     if (this.stream == null) {
/*  321 */       throw new IllegalStateException("Input not set!");
/*      */     }
/*      */     
/*  324 */     if (this.imageMetadata != null) {
/*      */       return;
/*      */     }
/*      */     
/*      */     try {
/*      */       List<BaselineTIFFTagSet> tagSets;
/*  330 */       if (this.imageReadParam instanceof TIFFImageReadParam) {
/*      */         
/*  332 */         tagSets = ((TIFFImageReadParam)this.imageReadParam).getAllowedTagSets();
/*      */       } else {
/*  334 */         tagSets = new ArrayList(1);
/*  335 */         tagSets.add(BaselineTIFFTagSet.getInstance());
/*      */       } 
/*      */       
/*  338 */       this.imageMetadata = new TIFFImageMetadata(tagSets);
/*  339 */       this.imageMetadata.initializeFromStream(this.stream, this.ignoreMetadata);
/*  340 */     } catch (IIOException iioe) {
/*  341 */       throw iioe;
/*  342 */     } catch (IOException ioe) {
/*  343 */       throw new IIOException("I/O error reading image metadata!", ioe);
/*      */     } 
/*      */   }
/*      */   
/*      */   private int getWidth() {
/*  348 */     return this.width;
/*      */   }
/*      */   
/*      */   private int getHeight() {
/*  352 */     return this.height;
/*      */   }
/*      */   
/*      */   private int getNumBands() {
/*  356 */     return this.numBands;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int getTileOrStripWidth() {
/*  362 */     TIFFField f = this.imageMetadata.getTIFFField(322);
/*  363 */     return (f == null) ? getWidth() : f.getAsInt(0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int getTileOrStripHeight() {
/*  369 */     TIFFField f = this.imageMetadata.getTIFFField(323);
/*  370 */     if (f != null) {
/*  371 */       return f.getAsInt(0);
/*      */     }
/*      */     
/*  374 */     f = this.imageMetadata.getTIFFField(278);
/*      */     
/*  376 */     int h = (f == null) ? -1 : f.getAsInt(0);
/*  377 */     return (h == -1) ? getHeight() : h;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getPlanarConfiguration() {
/*  382 */     TIFFField f = this.imageMetadata.getTIFFField(284);
/*  383 */     if (f != null) {
/*  384 */       int planarConfigurationValue = f.getAsInt(0);
/*  385 */       if (planarConfigurationValue == 2)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  391 */         if (getCompression() == 6 && this.imageMetadata
/*      */           
/*  393 */           .getTIFFField(513) != null) {
/*      */ 
/*      */ 
/*      */           
/*  397 */           processWarningOccurred("PlanarConfiguration \"Planar\" value inconsistent with JPEGInterchangeFormat; resetting to \"Chunky\".");
/*  398 */           planarConfigurationValue = 1;
/*      */         }
/*      */         else {
/*      */           
/*  402 */           TIFFField offsetField = this.imageMetadata.getTIFFField(324);
/*  403 */           if (offsetField == null) {
/*      */ 
/*      */             
/*  406 */             offsetField = this.imageMetadata.getTIFFField(273);
/*  407 */             int tw = getTileOrStripWidth();
/*  408 */             int th = getTileOrStripHeight();
/*  409 */             int tAcross = (getWidth() + tw - 1) / tw;
/*  410 */             int tDown = (getHeight() + th - 1) / th;
/*  411 */             int tilesPerImage = tAcross * tDown;
/*  412 */             long[] offsetArray = offsetField.getAsLongs();
/*  413 */             if (offsetArray != null && offsetArray.length == tilesPerImage)
/*      */             {
/*      */ 
/*      */ 
/*      */               
/*  418 */               processWarningOccurred("PlanarConfiguration \"Planar\" value inconsistent with TileOffsets field value count; resetting to \"Chunky\".");
/*  419 */               planarConfigurationValue = 1;
/*      */             }
/*      */           
/*      */           } else {
/*      */             
/*  424 */             int rowsPerStrip = getTileOrStripHeight();
/*      */             
/*  426 */             int stripsPerImage = (getHeight() + rowsPerStrip - 1) / rowsPerStrip;
/*  427 */             long[] offsetArray = offsetField.getAsLongs();
/*  428 */             if (offsetArray != null && offsetArray.length == stripsPerImage) {
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  433 */               processWarningOccurred("PlanarConfiguration \"Planar\" value inconsistent with StripOffsets field value count; resetting to \"Chunky\".");
/*  434 */               planarConfigurationValue = 1;
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*  440 */       return planarConfigurationValue;
/*      */     } 
/*      */     
/*  443 */     return 1;
/*      */   }
/*      */ 
/*      */   
/*      */   private long getTileOrStripOffset(int tileIndex) throws IIOException {
/*  448 */     TIFFField f = this.imageMetadata.getTIFFField(324);
/*  449 */     if (f == null) {
/*  450 */       f = this.imageMetadata.getTIFFField(273);
/*      */     }
/*  452 */     if (f == null) {
/*  453 */       f = this.imageMetadata.getTIFFField(513);
/*      */     }
/*      */     
/*  456 */     if (f == null) {
/*  457 */       throw new IIOException("Missing required strip or tile offsets field.");
/*      */     }
/*      */ 
/*      */     
/*  461 */     return f.getAsLong(tileIndex);
/*      */   }
/*      */   
/*      */   private long getTileOrStripByteCount(int tileIndex) throws IOException {
/*      */     long tileOrStripByteCount;
/*  466 */     TIFFField f = this.imageMetadata.getTIFFField(325);
/*  467 */     if (f == null)
/*      */     {
/*  469 */       f = this.imageMetadata.getTIFFField(279);
/*      */     }
/*  471 */     if (f == null) {
/*  472 */       f = this.imageMetadata.getTIFFField(514);
/*      */     }
/*      */ 
/*      */     
/*  476 */     if (f != null) {
/*  477 */       tileOrStripByteCount = f.getAsLong(tileIndex);
/*      */     } else {
/*  479 */       processWarningOccurred("TIFF directory contains neither StripByteCounts nor TileByteCounts field: attempting to calculate from strip or tile width and height.");
/*      */ 
/*      */ 
/*      */       
/*  483 */       int bitsPerPixel = this.bitsPerSample[0];
/*  484 */       for (int i = 1; i < this.samplesPerPixel; i++) {
/*  485 */         bitsPerPixel += this.bitsPerSample[i];
/*      */       }
/*  487 */       int bytesPerRow = (getTileOrStripWidth() * bitsPerPixel + 7) / 8;
/*  488 */       tileOrStripByteCount = (bytesPerRow * getTileOrStripHeight());
/*      */ 
/*      */       
/*  491 */       long streamLength = this.stream.length();
/*  492 */       if (streamLength != -1L) {
/*      */         
/*  494 */         tileOrStripByteCount = Math.min(tileOrStripByteCount, streamLength - 
/*  495 */             getTileOrStripOffset(tileIndex));
/*      */       } else {
/*  497 */         processWarningOccurred("Stream length is unknown: cannot clamp estimated strip or tile byte count to EOF.");
/*      */       } 
/*      */     } 
/*      */     
/*  501 */     return tileOrStripByteCount;
/*      */   }
/*      */ 
/*      */   
/*      */   private int getCompression() {
/*  506 */     TIFFField f = this.imageMetadata.getTIFFField(259);
/*  507 */     if (f == null) {
/*  508 */       return 1;
/*      */     }
/*  510 */     return f.getAsInt(0);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getWidth(int imageIndex) throws IOException {
/*  515 */     seekToImage(imageIndex);
/*  516 */     return getWidth();
/*      */   }
/*      */   
/*      */   public int getHeight(int imageIndex) throws IOException {
/*  520 */     seekToImage(imageIndex);
/*  521 */     return getHeight();
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
/*      */   private void initializeFromMetadata() {
/*  545 */     TIFFField f = this.imageMetadata.getTIFFField(259);
/*  546 */     if (f == null) {
/*      */       
/*  548 */       processWarningOccurred("Compression field is missing; assuming no compression");
/*  549 */       this.compression = 1;
/*      */     } else {
/*  551 */       this.compression = f.getAsInt(0);
/*      */     } 
/*      */ 
/*      */     
/*  555 */     boolean isMissingDimension = false;
/*      */ 
/*      */     
/*  558 */     f = this.imageMetadata.getTIFFField(256);
/*  559 */     if (f != null) {
/*  560 */       this.width = f.getAsInt(0);
/*      */     } else {
/*  562 */       processWarningOccurred("ImageWidth field is missing.");
/*  563 */       isMissingDimension = true;
/*      */     } 
/*      */ 
/*      */     
/*  567 */     f = this.imageMetadata.getTIFFField(257);
/*  568 */     if (f != null) {
/*  569 */       this.height = f.getAsInt(0);
/*      */     } else {
/*  571 */       processWarningOccurred("ImageLength field is missing.");
/*  572 */       isMissingDimension = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  577 */     f = this.imageMetadata.getTIFFField(277);
/*  578 */     if (f != null) {
/*  579 */       this.samplesPerPixel = f.getAsInt(0);
/*      */     } else {
/*  581 */       this.samplesPerPixel = 1;
/*  582 */       isMissingDimension = true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  587 */     int defaultBitDepth = 1;
/*  588 */     if (isMissingDimension && (
/*  589 */       f = this.imageMetadata.getTIFFField(513)) != null) {
/*  590 */       Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("JPEG");
/*  591 */       if (iter != null && iter.hasNext()) {
/*  592 */         ImageReader jreader = iter.next();
/*      */         try {
/*  594 */           this.stream.mark();
/*  595 */           this.stream.seek(f.getAsLong(0));
/*  596 */           jreader.setInput(this.stream);
/*  597 */           if (this.imageMetadata.getTIFFField(256) == null) {
/*  598 */             this.width = jreader.getWidth(0);
/*      */           }
/*  600 */           if (this.imageMetadata.getTIFFField(257) == null) {
/*  601 */             this.height = jreader.getHeight(0);
/*      */           }
/*  603 */           ImageTypeSpecifier imageType = jreader.getRawImageType(0);
/*  604 */           if (this.imageMetadata.getTIFFField(277) == null) {
/*  605 */             this
/*  606 */               .samplesPerPixel = imageType.getSampleModel().getNumBands();
/*      */           }
/*  608 */           this.stream.reset();
/*      */           
/*  610 */           defaultBitDepth = imageType.getColorModel().getComponentSize(0);
/*  611 */         } catch (IOException iOException) {}
/*      */ 
/*      */         
/*  614 */         jreader.dispose();
/*      */       } 
/*      */     } 
/*      */     
/*  618 */     if (this.samplesPerPixel < 1) {
/*  619 */       processWarningOccurred("Samples per pixel < 1!");
/*      */     }
/*      */ 
/*      */     
/*  623 */     this.numBands = this.samplesPerPixel;
/*      */ 
/*      */     
/*  626 */     this.colorMap = null;
/*  627 */     f = this.imageMetadata.getTIFFField(320);
/*  628 */     if (f != null)
/*      */     {
/*  630 */       this.colorMap = f.getAsChars();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  635 */     f = this.imageMetadata.getTIFFField(262);
/*  636 */     if (f == null) {
/*  637 */       if (this.compression == 2 || this.compression == 3 || this.compression == 4) {
/*      */ 
/*      */ 
/*      */         
/*  641 */         processWarningOccurred("PhotometricInterpretation field is missing; assuming WhiteIsZero");
/*      */         
/*  643 */         this.photometricInterpretation = 0;
/*      */       }
/*  645 */       else if (this.colorMap != null) {
/*  646 */         this.photometricInterpretation = 3;
/*      */       }
/*  648 */       else if (this.samplesPerPixel == 3 || this.samplesPerPixel == 4) {
/*  649 */         this.photometricInterpretation = 2;
/*      */       }
/*      */       else {
/*      */         
/*  653 */         processWarningOccurred("PhotometricInterpretation field is missing; assuming BlackIsZero");
/*      */         
/*  655 */         this.photometricInterpretation = 1;
/*      */       } 
/*      */     } else {
/*      */       
/*  659 */       this.photometricInterpretation = f.getAsInt(0);
/*      */     } 
/*      */ 
/*      */     
/*  663 */     boolean replicateFirst = false;
/*  664 */     int first = -1;
/*      */     
/*  666 */     f = this.imageMetadata.getTIFFField(339);
/*  667 */     this.sampleFormat = new int[this.samplesPerPixel];
/*  668 */     replicateFirst = false;
/*  669 */     if (f == null) {
/*  670 */       replicateFirst = true;
/*  671 */       first = 4;
/*  672 */     } else if (f.getCount() != this.samplesPerPixel) {
/*  673 */       replicateFirst = true;
/*  674 */       first = f.getAsInt(0);
/*      */     } 
/*      */     int i;
/*  677 */     for (i = 0; i < this.samplesPerPixel; i++) {
/*  678 */       this.sampleFormat[i] = replicateFirst ? first : f.getAsInt(i);
/*  679 */       if (this.sampleFormat[i] != 1 && this.sampleFormat[i] != 2 && this.sampleFormat[i] != 3 && this.sampleFormat[i] != 4) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  687 */         processWarningOccurred("Illegal value for SAMPLE_FORMAT, assuming SAMPLE_FORMAT_UNDEFINED");
/*      */         
/*  689 */         this.sampleFormat[i] = 4;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  694 */     f = this.imageMetadata.getTIFFField(258);
/*  695 */     this.bitsPerSample = new int[this.samplesPerPixel];
/*  696 */     replicateFirst = false;
/*  697 */     if (f == null) {
/*  698 */       replicateFirst = true;
/*  699 */       first = defaultBitDepth;
/*  700 */     } else if (f.getCount() != this.samplesPerPixel) {
/*  701 */       replicateFirst = true;
/*  702 */       first = f.getAsInt(0);
/*      */     } 
/*      */     
/*  705 */     for (i = 0; i < this.samplesPerPixel; i++)
/*      */     {
/*  707 */       this.bitsPerSample[i] = replicateFirst ? first : f.getAsInt(i);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  716 */     this.extraSamples = null;
/*  717 */     f = this.imageMetadata.getTIFFField(338);
/*  718 */     if (f != null) {
/*  719 */       this.extraSamples = f.getAsInts();
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
/*      */   public Iterator getImageTypes(int imageIndex) throws IIOException {
/*      */     List<ImageTypeSpecifier> l;
/*  734 */     Integer imageIndexInteger = new Integer(imageIndex);
/*  735 */     if (this.imageTypeMap.containsKey(imageIndexInteger)) {
/*      */       
/*  737 */       l = (List)this.imageTypeMap.get(imageIndexInteger);
/*      */     } else {
/*      */       
/*  740 */       l = new ArrayList(1);
/*      */ 
/*      */ 
/*      */       
/*  744 */       seekToImage(imageIndex);
/*      */ 
/*      */       
/*  747 */       ImageTypeSpecifier itsRaw = TIFFDecompressor.getRawImageTypeSpecifier(this.photometricInterpretation, this.compression, this.samplesPerPixel, this.bitsPerSample, this.sampleFormat, this.extraSamples, this.colorMap);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  757 */       TIFFField iccProfileField = this.imageMetadata.getTIFFField(34675);
/*      */ 
/*      */ 
/*      */       
/*  761 */       if (iccProfileField != null && itsRaw
/*  762 */         .getColorModel() instanceof ComponentColorModel) {
/*      */         
/*  764 */         byte[] iccProfileValue = iccProfileField.getAsBytes();
/*      */         
/*  766 */         ICC_Profile iccProfile = ICC_Profile.getInstance(iccProfileValue);
/*  767 */         ICC_ColorSpace iccColorSpace = new ICC_ColorSpace(iccProfile);
/*      */ 
/*      */ 
/*      */         
/*  771 */         ColorModel cmRaw = itsRaw.getColorModel();
/*  772 */         ColorSpace csRaw = cmRaw.getColorSpace();
/*  773 */         SampleModel smRaw = itsRaw.getSampleModel();
/*      */ 
/*      */ 
/*      */         
/*  777 */         int numBands = smRaw.getNumBands();
/*  778 */         int numComponents = iccColorSpace.getNumComponents();
/*      */ 
/*      */ 
/*      */         
/*  782 */         if (numBands == numComponents || numBands == numComponents + 1) {
/*      */ 
/*      */           
/*  785 */           boolean hasAlpha = (numComponents != numBands);
/*      */           
/*  787 */           boolean isAlphaPre = (hasAlpha && cmRaw.isAlphaPremultiplied());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  797 */           ColorModel iccColorModel = new ComponentColorModel(iccColorSpace, cmRaw.getComponentSize(), hasAlpha, isAlphaPre, cmRaw.getTransparency(), cmRaw.getTransferType());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  804 */           l.add(new ImageTypeSpecifier(iccColorModel, smRaw));
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  809 */           if (csRaw.getType() == iccColorSpace.getType() && csRaw
/*  810 */             .getNumComponents() == iccColorSpace
/*  811 */             .getNumComponents()) {
/*  812 */             l.add(itsRaw);
/*      */           }
/*      */         } else {
/*      */           
/*  816 */           l.add(itsRaw);
/*      */         } 
/*      */       } else {
/*      */         
/*  820 */         l.add(itsRaw);
/*      */       } 
/*      */ 
/*      */       
/*  824 */       this.imageTypeMap.put(imageIndexInteger, l);
/*      */     } 
/*      */     
/*  827 */     return l.iterator();
/*      */   }
/*      */   
/*      */   public IIOMetadata getImageMetadata(int imageIndex) throws IIOException {
/*  831 */     seekToImage(imageIndex);
/*      */     
/*  833 */     TIFFImageMetadata im = new TIFFImageMetadata(this.imageMetadata.getRootIFD().getTagSetList());
/*      */     
/*  835 */     Node root = this.imageMetadata.getAsTree("com_sun_media_imageio_plugins_tiff_image_1.0");
/*  836 */     im.setFromTree("com_sun_media_imageio_plugins_tiff_image_1.0", root);
/*  837 */     return im;
/*      */   }
/*      */   
/*      */   public IIOMetadata getStreamMetadata(int imageIndex) throws IIOException {
/*  841 */     readHeader();
/*  842 */     TIFFStreamMetadata sm = new TIFFStreamMetadata();
/*  843 */     Node root = sm.getAsTree("com_sun_media_imageio_plugins_tiff_stream_1.0");
/*  844 */     sm.setFromTree("com_sun_media_imageio_plugins_tiff_stream_1.0", root);
/*  845 */     return sm;
/*      */   }
/*      */   
/*      */   public boolean isRandomAccessEasy(int imageIndex) throws IOException {
/*  849 */     if (this.currIndex != -1) {
/*  850 */       seekToImage(this.currIndex);
/*  851 */       return (getCompression() == 1);
/*      */     } 
/*  853 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean readSupportsThumbnails() {
/*  860 */     return false;
/*      */   }
/*      */   
/*      */   public boolean hasThumbnails(int imageIndex) {
/*  864 */     return false;
/*      */   }
/*      */   
/*      */   public int getNumThumbnails(int imageIndex) throws IOException {
/*  868 */     return 0;
/*      */   }
/*      */   
/*      */   public ImageReadParam getDefaultReadParam() {
/*  872 */     return (ImageReadParam)new TIFFImageReadParam();
/*      */   }
/*      */   
/*      */   public boolean isImageTiled(int imageIndex) throws IOException {
/*  876 */     seekToImage(imageIndex);
/*      */ 
/*      */     
/*  879 */     TIFFField f = this.imageMetadata.getTIFFField(322);
/*  880 */     return (f != null);
/*      */   }
/*      */   
/*      */   public int getTileWidth(int imageIndex) throws IOException {
/*  884 */     seekToImage(imageIndex);
/*  885 */     return getTileOrStripWidth();
/*      */   }
/*      */   
/*      */   public int getTileHeight(int imageIndex) throws IOException {
/*  889 */     seekToImage(imageIndex);
/*  890 */     return getTileOrStripHeight();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public BufferedImage readTile(int imageIndex, int tileX, int tileY) throws IOException {
/*  896 */     int w = getWidth(imageIndex);
/*  897 */     int h = getHeight(imageIndex);
/*  898 */     int tw = getTileWidth(imageIndex);
/*  899 */     int th = getTileHeight(imageIndex);
/*      */     
/*  901 */     int x = tw * tileX;
/*  902 */     int y = th * tileY;
/*      */     
/*  904 */     if (tileX < 0 || tileY < 0 || x >= w || y >= h) {
/*  905 */       throw new IllegalArgumentException("Tile indices are out of bounds!");
/*      */     }
/*      */ 
/*      */     
/*  909 */     if (x + tw > w) {
/*  910 */       tw = w - x;
/*      */     }
/*      */     
/*  913 */     if (y + th > h) {
/*  914 */       th = h - y;
/*      */     }
/*      */     
/*  917 */     ImageReadParam param = getDefaultReadParam();
/*  918 */     Rectangle tileRect = new Rectangle(x, y, tw, th);
/*  919 */     param.setSourceRegion(tileRect);
/*      */     
/*  921 */     return read(imageIndex, param);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canReadRaster() {
/*  926 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Raster readRaster(int imageIndex, ImageReadParam param) throws IOException {
/*  932 */     throw new UnsupportedOperationException();
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
/*      */   private static int ifloor(int num, int den) {
/*  947 */     if (num < 0) {
/*  948 */       num -= den - 1;
/*      */     }
/*  950 */     return num / den;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int iceil(int num, int den) {
/*  955 */     if (num > 0) {
/*  956 */       num += den - 1;
/*      */     }
/*  958 */     return num / den;
/*      */   }
/*      */ 
/*      */   
/*      */   private void prepareRead(int imageIndex, ImageReadParam param) throws IOException {
/*  963 */     if (this.stream == null) {
/*  964 */       throw new IllegalStateException("Input not set!");
/*      */     }
/*      */ 
/*      */     
/*  968 */     if (param == null) {
/*  969 */       param = getDefaultReadParam();
/*      */     }
/*      */     
/*  972 */     this.imageReadParam = param;
/*      */     
/*  974 */     seekToImage(imageIndex);
/*      */     
/*  976 */     this.tileOrStripWidth = getTileOrStripWidth();
/*  977 */     this.tileOrStripHeight = getTileOrStripHeight();
/*  978 */     this.planarConfiguration = getPlanarConfiguration();
/*      */     
/*  980 */     this.sourceBands = param.getSourceBands();
/*  981 */     if (this.sourceBands == null) {
/*  982 */       this.sourceBands = new int[this.numBands];
/*  983 */       for (int j = 0; j < this.numBands; j++) {
/*  984 */         this.sourceBands[j] = j;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  989 */     Iterator imageTypes = getImageTypes(imageIndex);
/*      */     
/*  991 */     ImageTypeSpecifier theImageType = ImageUtil.getDestinationType(param, imageTypes);
/*      */     
/*  993 */     int destNumBands = theImageType.getSampleModel().getNumBands();
/*      */     
/*  995 */     this.destinationBands = param.getDestinationBands();
/*  996 */     if (this.destinationBands == null) {
/*  997 */       this.destinationBands = new int[destNumBands];
/*  998 */       for (int j = 0; j < destNumBands; j++) {
/*  999 */         this.destinationBands[j] = j;
/*      */       }
/*      */     } 
/*      */     
/* 1003 */     if (this.sourceBands.length != this.destinationBands.length) {
/* 1004 */       throw new IllegalArgumentException("sourceBands.length != destinationBands.length");
/*      */     }
/*      */ 
/*      */     
/* 1008 */     for (int i = 0; i < this.sourceBands.length; i++) {
/* 1009 */       int sb = this.sourceBands[i];
/* 1010 */       if (sb < 0 || sb >= this.numBands) {
/* 1011 */         throw new IllegalArgumentException("Source band out of range!");
/*      */       }
/*      */       
/* 1014 */       int db = this.destinationBands[i];
/* 1015 */       if (db < 0 || db >= destNumBands) {
/* 1016 */         throw new IllegalArgumentException("Destination band out of range!");
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RenderedImage readAsRenderedImage(int imageIndex, ImageReadParam param) throws IOException {
/* 1025 */     prepareRead(imageIndex, param);
/* 1026 */     return new TIFFRenderedImage(this, imageIndex, this.imageReadParam, this.width, this.height);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void decodeTile(int ti, int tj, int band) throws IOException {
/* 1036 */     Rectangle tileRect = new Rectangle(ti * this.tileOrStripWidth, tj * this.tileOrStripHeight, this.tileOrStripWidth, this.tileOrStripHeight);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1043 */     if (!isImageTiled(this.currIndex))
/*      */     {
/* 1045 */       tileRect = tileRect.intersection(new Rectangle(0, 0, this.width, this.height));
/*      */     }
/*      */ 
/*      */     
/* 1049 */     if (tileRect.width <= 0 || tileRect.height <= 0) {
/*      */       return;
/*      */     }
/*      */     
/* 1053 */     int srcMinX = tileRect.x;
/* 1054 */     int srcMinY = tileRect.y;
/* 1055 */     int srcWidth = tileRect.width;
/* 1056 */     int srcHeight = tileRect.height;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1061 */     this.dstMinX = iceil(srcMinX - this.sourceXOffset, this.srcXSubsampling);
/* 1062 */     int dstMaxX = ifloor(srcMinX + srcWidth - 1 - this.sourceXOffset, this.srcXSubsampling);
/*      */ 
/*      */     
/* 1065 */     this.dstMinY = iceil(srcMinY - this.sourceYOffset, this.srcYSubsampling);
/* 1066 */     int dstMaxY = ifloor(srcMinY + srcHeight - 1 - this.sourceYOffset, this.srcYSubsampling);
/*      */ 
/*      */     
/* 1069 */     this.dstWidth = dstMaxX - this.dstMinX + 1;
/* 1070 */     this.dstHeight = dstMaxY - this.dstMinY + 1;
/*      */     
/* 1072 */     this.dstMinX += this.dstXOffset;
/* 1073 */     this.dstMinY += this.dstYOffset;
/*      */ 
/*      */ 
/*      */     
/* 1077 */     Rectangle dstRect = new Rectangle(this.dstMinX, this.dstMinY, this.dstWidth, this.dstHeight);
/*      */ 
/*      */     
/* 1080 */     dstRect = dstRect.intersection(this.theImage.getRaster().getBounds());
/*      */     
/* 1082 */     this.dstMinX = dstRect.x;
/* 1083 */     this.dstMinY = dstRect.y;
/* 1084 */     this.dstWidth = dstRect.width;
/* 1085 */     this.dstHeight = dstRect.height;
/*      */     
/* 1087 */     if (this.dstWidth <= 0 || this.dstHeight <= 0) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1094 */     int activeSrcMinX = (this.dstMinX - this.dstXOffset) * this.srcXSubsampling + this.sourceXOffset;
/*      */     
/* 1096 */     int sxmax = (this.dstMinX + this.dstWidth - 1 - this.dstXOffset) * this.srcXSubsampling + this.sourceXOffset;
/*      */ 
/*      */     
/* 1099 */     int activeSrcWidth = sxmax - activeSrcMinX + 1;
/*      */     
/* 1101 */     int activeSrcMinY = (this.dstMinY - this.dstYOffset) * this.srcYSubsampling + this.sourceYOffset;
/*      */     
/* 1103 */     int symax = (this.dstMinY + this.dstHeight - 1 - this.dstYOffset) * this.srcYSubsampling + this.sourceYOffset;
/*      */ 
/*      */     
/* 1106 */     int activeSrcHeight = symax - activeSrcMinY + 1;
/*      */     
/* 1108 */     this.decompressor.setSrcMinX(srcMinX);
/* 1109 */     this.decompressor.setSrcMinY(srcMinY);
/* 1110 */     this.decompressor.setSrcWidth(srcWidth);
/* 1111 */     this.decompressor.setSrcHeight(srcHeight);
/*      */     
/* 1113 */     this.decompressor.setDstMinX(this.dstMinX);
/* 1114 */     this.decompressor.setDstMinY(this.dstMinY);
/* 1115 */     this.decompressor.setDstWidth(this.dstWidth);
/* 1116 */     this.decompressor.setDstHeight(this.dstHeight);
/*      */     
/* 1118 */     this.decompressor.setActiveSrcMinX(activeSrcMinX);
/* 1119 */     this.decompressor.setActiveSrcMinY(activeSrcMinY);
/* 1120 */     this.decompressor.setActiveSrcWidth(activeSrcWidth);
/* 1121 */     this.decompressor.setActiveSrcHeight(activeSrcHeight);
/*      */     
/* 1123 */     int tileIndex = tj * this.tilesAcross + ti;
/*      */     
/* 1125 */     if (this.planarConfiguration == 2)
/*      */     {
/* 1127 */       tileIndex += band * this.tilesAcross * this.tilesDown;
/*      */     }
/*      */     
/* 1130 */     long offset = getTileOrStripOffset(tileIndex);
/* 1131 */     long byteCount = getTileOrStripByteCount(tileIndex);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1140 */     long streamLength = this.stream.length();
/*      */     
/* 1142 */     processWarningOccurred("Attempting to process truncated stream.");
/* 1143 */     if (streamLength > 0L && offset + byteCount > streamLength && Math.max(byteCount = streamLength - offset, 0L) == 0L) {
/* 1144 */       processWarningOccurred("No bytes in strip/tile: skipping.");
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1149 */     this.decompressor.setStream(this.stream);
/* 1150 */     this.decompressor.setOffset(offset);
/* 1151 */     this.decompressor.setByteCount((int)byteCount);
/*      */     
/* 1153 */     this.decompressor.beginDecoding();
/*      */     
/* 1155 */     this.stream.mark();
/* 1156 */     this.decompressor.decode();
/* 1157 */     this.stream.reset();
/*      */   }
/*      */ 
/*      */   
/*      */   private void reportProgress() {
/* 1162 */     this.pixelsRead += this.dstWidth * this.dstHeight;
/* 1163 */     processImageProgress(100.0F * this.pixelsRead / this.pixelsToRead);
/* 1164 */     processImageUpdate(this.theImage, this.dstMinX, this.dstMinY, this.dstWidth, this.dstHeight, 1, 1, this.destinationBands);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
/* 1172 */     prepareRead(imageIndex, param);
/* 1173 */     this.theImage = ImageReader.getDestination(param, 
/* 1174 */         getImageTypes(imageIndex), this.width, this.height);
/*      */ 
/*      */     
/* 1177 */     this.srcXSubsampling = this.imageReadParam.getSourceXSubsampling();
/* 1178 */     this.srcYSubsampling = this.imageReadParam.getSourceYSubsampling();
/*      */     
/* 1180 */     Point p = this.imageReadParam.getDestinationOffset();
/* 1181 */     this.dstXOffset = p.x;
/* 1182 */     this.dstYOffset = p.y;
/*      */ 
/*      */     
/* 1185 */     Rectangle srcRegion = new Rectangle(0, 0, 0, 0);
/* 1186 */     Rectangle destRegion = new Rectangle(0, 0, 0, 0);
/*      */     
/* 1188 */     computeRegions(this.imageReadParam, this.width, this.height, this.theImage, srcRegion, destRegion);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1193 */     this.sourceXOffset = srcRegion.x;
/* 1194 */     this.sourceYOffset = srcRegion.y;
/*      */     
/* 1196 */     this.pixelsToRead = destRegion.width * destRegion.height;
/* 1197 */     this.pixelsRead = 0;
/*      */     
/* 1199 */     processImageStarted(imageIndex);
/* 1200 */     processImageProgress(0.0F);
/*      */     
/* 1202 */     this.tilesAcross = (this.width + this.tileOrStripWidth - 1) / this.tileOrStripWidth;
/* 1203 */     this.tilesDown = (this.height + this.tileOrStripHeight - 1) / this.tileOrStripHeight;
/*      */     
/* 1205 */     int compression = getCompression();
/*      */ 
/*      */ 
/*      */     
/* 1209 */     TIFFColorConverter colorConverter = null;
/* 1210 */     if (this.imageReadParam instanceof TIFFImageReadParam) {
/* 1211 */       TIFFImageReadParam tparam = (TIFFImageReadParam)this.imageReadParam;
/*      */       
/* 1213 */       this.decompressor = tparam.getTIFFDecompressor();
/* 1214 */       colorConverter = tparam.getColorConverter();
/*      */     } 
/*      */ 
/*      */     
/* 1218 */     if (this.decompressor == null) {
/* 1219 */       if (compression == 1) {
/*      */ 
/*      */ 
/*      */         
/* 1223 */         TIFFField fillOrderField = this.imageMetadata.getTIFFField(266);
/*      */ 
/*      */         
/* 1226 */         if (fillOrderField != null && fillOrderField.getAsInt(0) == 2) {
/* 1227 */           this.decompressor = new TIFFLSBDecompressor();
/*      */         } else {
/* 1229 */           this.decompressor = new TIFFNullDecompressor();
/*      */         } 
/* 1231 */       } else if (compression == 4) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1236 */         if (this.decompressor == null)
/*      */         {
/*      */ 
/*      */           
/* 1240 */           this.decompressor = new TIFFFaxDecompressor();
/*      */         }
/* 1242 */       } else if (compression == 3) {
/*      */ 
/*      */ 
/*      */         
/* 1246 */         if (this.decompressor == null)
/*      */         {
/*      */ 
/*      */           
/* 1250 */           this.decompressor = new TIFFFaxDecompressor();
/*      */         }
/* 1252 */       } else if (compression == 2) {
/*      */         
/* 1254 */         this.decompressor = new TIFFFaxDecompressor();
/* 1255 */       } else if (compression == 32773) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1260 */         this.decompressor = new TIFFPackBitsDecompressor();
/* 1261 */       } else if (compression == 5) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1267 */         TIFFField predictorField = this.imageMetadata.getTIFFField(317);
/*      */ 
/*      */         
/* 1270 */         int predictor = (predictorField == null) ? 1 : predictorField.getAsInt(0);
/* 1271 */         this.decompressor = new TIFFLZWDecompressor(predictor);
/* 1272 */       } else if (compression == 7) {
/*      */         
/* 1274 */         this.decompressor = new TIFFJPEGDecompressor();
/* 1275 */       } else if (compression == 8 || compression == 32946) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1280 */         TIFFField predictorField = this.imageMetadata.getTIFFField(317);
/*      */ 
/*      */         
/* 1283 */         int predictor = (predictorField == null) ? 1 : predictorField.getAsInt(0);
/* 1284 */         this.decompressor = new TIFFDeflateDecompressor(predictor);
/* 1285 */       } else if (compression == 6) {
/*      */ 
/*      */         
/* 1288 */         TIFFField JPEGProcField = this.imageMetadata.getTIFFField(512);
/* 1289 */         if (JPEGProcField == null) {
/*      */           
/* 1291 */           processWarningOccurred("JPEGProc field missing; assuming baseline sequential JPEG process.");
/* 1292 */         } else if (JPEGProcField.getAsInt(0) != 1) {
/*      */           
/* 1294 */           throw new IIOException("Old-style JPEG supported for baseline sequential JPEG process only!");
/*      */         } 
/*      */         
/* 1297 */         this.decompressor = new TIFFOldJPEGDecompressor();
/*      */       } else {
/*      */         
/* 1300 */         throw new IIOException("Unsupported compression type (tag number = " + compression + ")!");
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1305 */       if (this.photometricInterpretation == 6 && compression != 7 && compression != 6) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1310 */         boolean convertYCbCrToRGB = (this.theImage.getColorModel().getColorSpace().getType() == 5);
/*      */         
/* 1312 */         TIFFDecompressor wrappedDecompressor = (this.decompressor instanceof TIFFNullDecompressor) ? null : this.decompressor;
/*      */ 
/*      */         
/* 1315 */         this.decompressor = new TIFFYCbCrDecompressor(wrappedDecompressor, convertYCbCrToRGB);
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
/* 1326 */     if (colorConverter == null) {
/* 1327 */       if (this.photometricInterpretation == 8 && this.theImage
/*      */         
/* 1329 */         .getColorModel().getColorSpace().getType() == 5) {
/*      */         
/* 1331 */         colorConverter = new TIFFCIELabColorConverter();
/* 1332 */       } else if (this.photometricInterpretation == 6 && !(this.decompressor instanceof TIFFYCbCrDecompressor) && compression != 7 && compression != 6) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1337 */         colorConverter = new TIFFYCbCrColorConverter(this.imageMetadata);
/*      */       } 
/*      */     }
/*      */     
/* 1341 */     this.decompressor.setReader(this);
/* 1342 */     this.decompressor.setMetadata(this.imageMetadata);
/* 1343 */     this.decompressor.setImage(this.theImage);
/*      */     
/* 1345 */     this.decompressor.setPhotometricInterpretation(this.photometricInterpretation);
/* 1346 */     this.decompressor.setCompression(compression);
/* 1347 */     this.decompressor.setSamplesPerPixel(this.samplesPerPixel);
/* 1348 */     this.decompressor.setBitsPerSample(this.bitsPerSample);
/* 1349 */     this.decompressor.setSampleFormat(this.sampleFormat);
/* 1350 */     this.decompressor.setExtraSamples(this.extraSamples);
/* 1351 */     this.decompressor.setColorMap(this.colorMap);
/*      */     
/* 1353 */     this.decompressor.setColorConverter(colorConverter);
/*      */     
/* 1355 */     this.decompressor.setSourceXOffset(this.sourceXOffset);
/* 1356 */     this.decompressor.setSourceYOffset(this.sourceYOffset);
/* 1357 */     this.decompressor.setSubsampleX(this.srcXSubsampling);
/* 1358 */     this.decompressor.setSubsampleY(this.srcYSubsampling);
/*      */     
/* 1360 */     this.decompressor.setDstXOffset(this.dstXOffset);
/* 1361 */     this.decompressor.setDstYOffset(this.dstYOffset);
/*      */     
/* 1363 */     this.decompressor.setSourceBands(this.sourceBands);
/* 1364 */     this.decompressor.setDestinationBands(this.destinationBands);
/*      */ 
/*      */ 
/*      */     
/* 1368 */     int minTileX = TIFFImageWriter.XToTileX(srcRegion.x, 0, this.tileOrStripWidth);
/*      */     
/* 1370 */     int minTileY = TIFFImageWriter.YToTileY(srcRegion.y, 0, this.tileOrStripHeight);
/*      */     
/* 1372 */     int maxTileX = TIFFImageWriter.XToTileX(srcRegion.x + srcRegion.width - 1, 0, this.tileOrStripWidth);
/*      */ 
/*      */     
/* 1375 */     int maxTileY = TIFFImageWriter.YToTileY(srcRegion.y + srcRegion.height - 1, 0, this.tileOrStripHeight);
/*      */ 
/*      */     
/* 1378 */     boolean isAbortRequested = false;
/* 1379 */     if (this.planarConfiguration == 2) {
/*      */ 
/*      */       
/* 1382 */       this.decompressor.setPlanar(true);
/*      */       
/* 1384 */       int[] sb = new int[1];
/* 1385 */       int[] db = new int[1];
/* 1386 */       for (int tj = minTileY; tj <= maxTileY; tj++) {
/* 1387 */         for (int ti = minTileX; ti <= maxTileX; ti++) {
/* 1388 */           for (int band = 0; band < this.numBands; band++) {
/* 1389 */             sb[0] = this.sourceBands[band];
/* 1390 */             this.decompressor.setSourceBands(sb);
/* 1391 */             db[0] = this.destinationBands[band];
/* 1392 */             this.decompressor.setDestinationBands(db);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1398 */             if (abortRequested()) {
/* 1399 */               isAbortRequested = true;
/*      */               
/*      */               break;
/*      */             } 
/* 1403 */             decodeTile(ti, tj, band);
/*      */           } 
/*      */           
/* 1406 */           if (isAbortRequested)
/*      */             break; 
/* 1408 */           reportProgress();
/*      */         } 
/*      */         
/* 1411 */         if (isAbortRequested) {
/*      */           break;
/*      */         }
/*      */       } 
/*      */     } else {
/* 1416 */       for (int tj = minTileY; tj <= maxTileY; tj++) {
/* 1417 */         for (int ti = minTileX; ti <= maxTileX; ti++) {
/*      */ 
/*      */ 
/*      */           
/* 1421 */           if (abortRequested()) {
/* 1422 */             isAbortRequested = true;
/*      */             
/*      */             break;
/*      */           } 
/* 1426 */           decodeTile(ti, tj, -1);
/*      */           
/* 1428 */           reportProgress();
/*      */         } 
/*      */         
/* 1431 */         if (isAbortRequested)
/*      */           break; 
/*      */       } 
/*      */     } 
/* 1435 */     if (isAbortRequested) {
/* 1436 */       processReadAborted();
/*      */     } else {
/* 1438 */       processImageComplete();
/*      */     } 
/*      */     
/* 1441 */     return this.theImage;
/*      */   }
/*      */   
/*      */   public void reset() {
/* 1445 */     super.reset();
/* 1446 */     resetLocal();
/*      */   }
/*      */   
/*      */   protected void resetLocal() {
/* 1450 */     this.stream = null;
/* 1451 */     this.gotHeader = false;
/* 1452 */     this.imageReadParam = getDefaultReadParam();
/* 1453 */     this.streamMetadata = null;
/* 1454 */     this.currIndex = -1;
/* 1455 */     this.imageMetadata = null;
/* 1456 */     this.imageStartPosition = new ArrayList();
/* 1457 */     this.numImages = -1;
/* 1458 */     this.imageTypeMap = new HashMap<Object, Object>();
/* 1459 */     this.width = -1;
/* 1460 */     this.height = -1;
/* 1461 */     this.numBands = -1;
/* 1462 */     this.tileOrStripWidth = -1;
/* 1463 */     this.tileOrStripHeight = -1;
/* 1464 */     this.planarConfiguration = 1;
/* 1465 */     this.rowsDone = 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void forwardWarningMessage(String warning) {
/* 1473 */     processWarningOccurred(warning);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static BufferedImage getDestination(ImageReadParam param, Iterator<ImageTypeSpecifier> imageTypes, int width, int height) throws IIOException {
/* 1480 */     if (imageTypes == null || !imageTypes.hasNext()) {
/* 1481 */       throw new IllegalArgumentException("imageTypes null or empty!");
/*      */     }
/*      */     
/* 1484 */     BufferedImage dest = null;
/* 1485 */     ImageTypeSpecifier imageType = null;
/*      */ 
/*      */     
/* 1488 */     if (param != null) {
/*      */       
/* 1490 */       dest = param.getDestination();
/* 1491 */       if (dest != null) {
/* 1492 */         return dest;
/*      */       }
/*      */ 
/*      */       
/* 1496 */       imageType = param.getDestinationType();
/*      */     } 
/*      */ 
/*      */     
/* 1500 */     if (imageType == null) {
/* 1501 */       Object o = imageTypes.next();
/* 1502 */       if (!(o instanceof ImageTypeSpecifier)) {
/* 1503 */         throw new IllegalArgumentException("Non-ImageTypeSpecifier retrieved from imageTypes!");
/*      */       }
/*      */       
/* 1506 */       imageType = (ImageTypeSpecifier)o;
/*      */     } else {
/* 1508 */       boolean foundIt = false;
/* 1509 */       while (imageTypes.hasNext()) {
/*      */         
/* 1511 */         ImageTypeSpecifier type = imageTypes.next();
/* 1512 */         if (type.equals(imageType)) {
/* 1513 */           foundIt = true;
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/* 1518 */       if (!foundIt) {
/* 1519 */         throw new IIOException("Destination type from ImageReadParam does not match!");
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1524 */     Rectangle srcRegion = new Rectangle(0, 0, 0, 0);
/* 1525 */     Rectangle destRegion = new Rectangle(0, 0, 0, 0);
/* 1526 */     computeRegions(param, width, height, null, srcRegion, destRegion);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1533 */     int destWidth = destRegion.x + destRegion.width;
/* 1534 */     int destHeight = destRegion.y + destRegion.height;
/*      */ 
/*      */     
/* 1537 */     if (destWidth * destHeight > 2147483647L) {
/* 1538 */       throw new IllegalArgumentException("width*height > Integer.MAX_VALUE!");
/*      */     }
/*      */ 
/*      */     
/* 1542 */     return imageType.createBufferedImage(destWidth, destHeight);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFImageReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */