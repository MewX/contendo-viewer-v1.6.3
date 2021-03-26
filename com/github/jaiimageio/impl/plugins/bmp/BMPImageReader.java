/*      */ package com.github.jaiimageio.impl.plugins.bmp;
/*      */ 
/*      */ import com.github.jaiimageio.impl.common.ImageUtil;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.color.ICC_ColorSpace;
/*      */ import java.awt.color.ICC_Profile;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentSampleModel;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.DirectColorModel;
/*      */ import java.awt.image.IndexColorModel;
/*      */ import java.awt.image.MultiPixelPackedSampleModel;
/*      */ import java.awt.image.PixelInterleavedSampleModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.SinglePixelPackedSampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.IOException;
/*      */ import java.nio.ByteOrder;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.imageio.ImageReadParam;
/*      */ import javax.imageio.ImageReader;
/*      */ import javax.imageio.ImageTypeSpecifier;
/*      */ import javax.imageio.event.IIOReadProgressListener;
/*      */ import javax.imageio.event.IIOReadUpdateListener;
/*      */ import javax.imageio.event.IIOReadWarningListener;
/*      */ import javax.imageio.metadata.IIOMetadata;
/*      */ import javax.imageio.spi.ImageReaderSpi;
/*      */ import javax.imageio.stream.ImageInputStream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BMPImageReader
/*      */   extends ImageReader
/*      */   implements BMPConstants
/*      */ {
/*      */   private static final int VERSION_2_1_BIT = 0;
/*      */   private static final int VERSION_2_4_BIT = 1;
/*      */   private static final int VERSION_2_8_BIT = 2;
/*      */   private static final int VERSION_2_24_BIT = 3;
/*      */   private static final int VERSION_3_1_BIT = 4;
/*      */   private static final int VERSION_3_4_BIT = 5;
/*      */   private static final int VERSION_3_8_BIT = 6;
/*      */   private static final int VERSION_3_24_BIT = 7;
/*      */   private static final int VERSION_3_NT_16_BIT = 8;
/*      */   private static final int VERSION_3_NT_32_BIT = 9;
/*      */   private static final int VERSION_4_1_BIT = 10;
/*      */   private static final int VERSION_4_4_BIT = 11;
/*      */   private static final int VERSION_4_8_BIT = 12;
/*      */   private static final int VERSION_4_16_BIT = 13;
/*      */   private static final int VERSION_4_24_BIT = 14;
/*      */   private static final int VERSION_4_32_BIT = 15;
/*      */   private static final int VERSION_3_XP_EMBEDDED = 16;
/*      */   private static final int VERSION_4_XP_EMBEDDED = 17;
/*      */   private static final int VERSION_5_XP_EMBEDDED = 18;
/*      */   private long bitmapFileSize;
/*      */   private long bitmapOffset;
/*      */   private long compression;
/*      */   private long imageSize;
/*      */   private byte[] palette;
/*      */   private int imageType;
/*      */   private int numBands;
/*      */   private boolean isBottomUp;
/*      */   private int bitsPerPixel;
/*      */   private int redMask;
/*      */   private int greenMask;
/*      */   private int blueMask;
/*      */   private int alphaMask;
/*      */   private SampleModel sampleModel;
/*      */   private SampleModel originalSampleModel;
/*      */   private ColorModel colorModel;
/*      */   private ColorModel originalColorModel;
/*  141 */   private ImageInputStream iis = null;
/*      */ 
/*      */   
/*      */   private boolean gotHeader = false;
/*      */ 
/*      */   
/*      */   private long imageDataOffset;
/*      */ 
/*      */   
/*      */   private int width;
/*      */ 
/*      */   
/*      */   private int height;
/*      */ 
/*      */   
/*      */   private Rectangle destinationRegion;
/*      */ 
/*      */   
/*      */   private Rectangle sourceRegion;
/*      */ 
/*      */   
/*      */   private BMPMetadata metadata;
/*      */ 
/*      */   
/*      */   private BufferedImage bi;
/*      */ 
/*      */   
/*      */   private boolean noTransform = true;
/*      */ 
/*      */   
/*      */   private boolean seleBand = false;
/*      */ 
/*      */   
/*      */   private int scaleX;
/*      */ 
/*      */   
/*      */   private int scaleY;
/*      */   
/*      */   private int[] sourceBands;
/*      */   
/*      */   private int[] destBands;
/*      */ 
/*      */   
/*      */   public BMPImageReader(ImageReaderSpi originator) {
/*  185 */     super(originator);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInput(Object input, boolean seekForwardOnly, boolean ignoreMetadata) {
/*  192 */     super.setInput(input, seekForwardOnly, ignoreMetadata);
/*  193 */     this.iis = (ImageInputStream)input;
/*  194 */     if (this.iis != null)
/*  195 */       this.iis.setByteOrder(ByteOrder.LITTLE_ENDIAN); 
/*  196 */     resetHeaderInfo();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getNumImages(boolean allowSearch) throws IOException {
/*  201 */     if (this.iis == null) {
/*  202 */       throw new IllegalStateException(I18N.getString("GetNumImages0"));
/*      */     }
/*  204 */     if (this.seekForwardOnly && allowSearch) {
/*  205 */       throw new IllegalStateException(I18N.getString("GetNumImages1"));
/*      */     }
/*  207 */     return 1;
/*      */   }
/*      */   
/*      */   public int getWidth(int imageIndex) throws IOException {
/*  211 */     checkIndex(imageIndex);
/*  212 */     readHeader();
/*  213 */     return this.width;
/*      */   }
/*      */   
/*      */   public int getHeight(int imageIndex) throws IOException {
/*  217 */     checkIndex(imageIndex);
/*  218 */     readHeader();
/*  219 */     return this.height;
/*      */   }
/*      */   
/*      */   private void checkIndex(int imageIndex) {
/*  223 */     if (imageIndex != 0) {
/*  224 */       throw new IndexOutOfBoundsException(I18N.getString("BMPImageReader0"));
/*      */     }
/*      */   }
/*      */   
/*      */   public void readHeader() throws IOException {
/*  229 */     if (this.gotHeader) {
/*      */ 
/*      */       
/*  232 */       this.iis.seek(this.imageDataOffset);
/*      */       
/*      */       return;
/*      */     } 
/*  236 */     if (this.iis == null) {
/*  237 */       throw new IllegalStateException(I18N.getString("BMPImageReader5"));
/*      */     }
/*  239 */     int profileData = 0, profileSize = 0;
/*      */     
/*  241 */     this.metadata = new BMPMetadata();
/*  242 */     this.iis.mark();
/*      */ 
/*      */     
/*  245 */     byte[] marker = new byte[2];
/*  246 */     this.iis.read(marker);
/*  247 */     if (marker[0] != 66 || marker[1] != 77) {
/*  248 */       throw new IllegalArgumentException(I18N.getString("BMPImageReader1"));
/*      */     }
/*      */     
/*  251 */     this.bitmapFileSize = this.iis.readUnsignedInt();
/*      */     
/*  253 */     this.iis.skipBytes(4);
/*      */ 
/*      */     
/*  256 */     this.bitmapOffset = this.iis.readUnsignedInt();
/*      */ 
/*      */ 
/*      */     
/*  260 */     long size = this.iis.readUnsignedInt();
/*      */     
/*  262 */     if (size == 12L) {
/*  263 */       this.width = this.iis.readShort();
/*  264 */       this.height = this.iis.readShort();
/*      */     } else {
/*  266 */       this.width = this.iis.readInt();
/*  267 */       this.height = this.iis.readInt();
/*      */     } 
/*      */     
/*  270 */     this.metadata.width = this.width;
/*  271 */     this.metadata.height = this.height;
/*      */     
/*  273 */     int planes = this.iis.readUnsignedShort();
/*  274 */     this.bitsPerPixel = this.iis.readUnsignedShort();
/*      */ 
/*      */     
/*  277 */     this.metadata.bitsPerPixel = (short)this.bitsPerPixel;
/*      */ 
/*      */ 
/*      */     
/*  281 */     this.numBands = 3;
/*      */     
/*  283 */     if (size == 12L) {
/*      */       
/*  285 */       this.metadata.bmpVersion = "BMP v. 2.x";
/*      */ 
/*      */       
/*  288 */       if (this.bitsPerPixel == 1) {
/*  289 */         this.imageType = 0;
/*  290 */       } else if (this.bitsPerPixel == 4) {
/*  291 */         this.imageType = 1;
/*  292 */       } else if (this.bitsPerPixel == 8) {
/*  293 */         this.imageType = 2;
/*  294 */       } else if (this.bitsPerPixel == 24) {
/*  295 */         this.imageType = 3;
/*      */       } 
/*      */ 
/*      */       
/*  299 */       int numberOfEntries = (int)((this.bitmapOffset - 14L - size) / 3L);
/*  300 */       int sizeOfPalette = numberOfEntries * 3;
/*  301 */       this.palette = new byte[sizeOfPalette];
/*  302 */       this.iis.readFully(this.palette, 0, sizeOfPalette);
/*  303 */       this.metadata.palette = this.palette;
/*  304 */       this.metadata.paletteSize = numberOfEntries;
/*      */     } else {
/*  306 */       this.compression = this.iis.readUnsignedInt();
/*  307 */       this.imageSize = this.iis.readUnsignedInt();
/*  308 */       long xPelsPerMeter = this.iis.readInt();
/*  309 */       long yPelsPerMeter = this.iis.readInt();
/*  310 */       long colorsUsed = this.iis.readUnsignedInt();
/*  311 */       long colorsImportant = this.iis.readUnsignedInt();
/*      */       
/*  313 */       this.metadata.compression = (int)this.compression;
/*  314 */       this.metadata.imageSize = (int)this.imageSize;
/*  315 */       this.metadata.xPixelsPerMeter = (int)xPelsPerMeter;
/*  316 */       this.metadata.yPixelsPerMeter = (int)yPelsPerMeter;
/*  317 */       this.metadata.colorsUsed = (int)colorsUsed;
/*  318 */       this.metadata.colorsImportant = (int)colorsImportant;
/*      */       
/*  320 */       if (size == 40L) {
/*      */         int numberOfEntries; int sizeOfPalette;
/*  322 */         switch ((int)this.compression) {
/*      */           
/*      */           case 4:
/*      */           case 5:
/*  326 */             this.metadata.bmpVersion = "BMP v. 3.x";
/*  327 */             this.imageType = 16;
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 0:
/*      */           case 1:
/*      */           case 2:
/*  335 */             numberOfEntries = (int)((this.bitmapOffset - 14L - size) / 4L);
/*  336 */             sizeOfPalette = numberOfEntries * 4;
/*  337 */             this.palette = new byte[sizeOfPalette];
/*  338 */             this.iis.readFully(this.palette, 0, sizeOfPalette);
/*      */             
/*  340 */             this.metadata.palette = this.palette;
/*  341 */             this.metadata.paletteSize = numberOfEntries;
/*      */             
/*  343 */             if (this.bitsPerPixel == 1) {
/*  344 */               this.imageType = 4;
/*  345 */             } else if (this.bitsPerPixel == 4) {
/*  346 */               this.imageType = 5;
/*  347 */             } else if (this.bitsPerPixel == 8) {
/*  348 */               this.imageType = 6;
/*  349 */             } else if (this.bitsPerPixel == 24) {
/*  350 */               this.imageType = 7;
/*  351 */             } else if (this.bitsPerPixel == 16) {
/*  352 */               this.imageType = 8;
/*      */               
/*  354 */               this.redMask = 31744;
/*  355 */               this.greenMask = 992;
/*  356 */               this.blueMask = 31;
/*  357 */               this.metadata.redMask = this.redMask;
/*  358 */               this.metadata.greenMask = this.greenMask;
/*  359 */               this.metadata.blueMask = this.blueMask;
/*  360 */             } else if (this.bitsPerPixel == 32) {
/*  361 */               this.imageType = 9;
/*  362 */               this.redMask = 16711680;
/*  363 */               this.greenMask = 65280;
/*  364 */               this.blueMask = 255;
/*  365 */               this.metadata.redMask = this.redMask;
/*  366 */               this.metadata.greenMask = this.greenMask;
/*  367 */               this.metadata.blueMask = this.blueMask;
/*      */             } 
/*      */             
/*  370 */             this.metadata.bmpVersion = "BMP v. 3.x";
/*      */             break;
/*      */ 
/*      */           
/*      */           case 3:
/*  375 */             if (this.bitsPerPixel == 16) {
/*  376 */               this.imageType = 8;
/*  377 */             } else if (this.bitsPerPixel == 32) {
/*  378 */               this.imageType = 9;
/*      */             } 
/*      */ 
/*      */             
/*  382 */             this.redMask = (int)this.iis.readUnsignedInt();
/*  383 */             this.greenMask = (int)this.iis.readUnsignedInt();
/*  384 */             this.blueMask = (int)this.iis.readUnsignedInt();
/*  385 */             this.metadata.redMask = this.redMask;
/*  386 */             this.metadata.greenMask = this.greenMask;
/*  387 */             this.metadata.blueMask = this.blueMask;
/*      */             
/*  389 */             if (colorsUsed != 0L) {
/*      */               
/*  391 */               sizeOfPalette = (int)colorsUsed * 4;
/*  392 */               this.palette = new byte[sizeOfPalette];
/*  393 */               this.iis.readFully(this.palette, 0, sizeOfPalette);
/*  394 */               this.metadata.palette = this.palette;
/*  395 */               this.metadata.paletteSize = (int)colorsUsed;
/*      */             } 
/*  397 */             this.metadata.bmpVersion = "BMP v. 3.x NT";
/*      */             break;
/*      */           
/*      */           default:
/*  401 */             throw new RuntimeException(
/*  402 */                 I18N.getString("BMPImageReader2"));
/*      */         } 
/*  404 */       } else if (size == 108L || size == 124L) {
/*      */         
/*  406 */         if (size == 108L) {
/*  407 */           this.metadata.bmpVersion = "BMP v. 4.x";
/*  408 */         } else if (size == 124L) {
/*  409 */           this.metadata.bmpVersion = "BMP v. 5.x";
/*      */         } 
/*      */         
/*  412 */         this.redMask = (int)this.iis.readUnsignedInt();
/*  413 */         this.greenMask = (int)this.iis.readUnsignedInt();
/*  414 */         this.blueMask = (int)this.iis.readUnsignedInt();
/*      */         
/*  416 */         this.alphaMask = (int)this.iis.readUnsignedInt();
/*  417 */         long csType = this.iis.readUnsignedInt();
/*  418 */         int redX = this.iis.readInt();
/*  419 */         int redY = this.iis.readInt();
/*  420 */         int redZ = this.iis.readInt();
/*  421 */         int greenX = this.iis.readInt();
/*  422 */         int greenY = this.iis.readInt();
/*  423 */         int greenZ = this.iis.readInt();
/*  424 */         int blueX = this.iis.readInt();
/*  425 */         int blueY = this.iis.readInt();
/*  426 */         int blueZ = this.iis.readInt();
/*  427 */         long gammaRed = this.iis.readUnsignedInt();
/*  428 */         long gammaGreen = this.iis.readUnsignedInt();
/*  429 */         long gammaBlue = this.iis.readUnsignedInt();
/*      */         
/*  431 */         if (size == 124L) {
/*  432 */           this.metadata.intent = this.iis.readInt();
/*  433 */           profileData = this.iis.readInt();
/*  434 */           profileSize = this.iis.readInt();
/*  435 */           this.iis.skipBytes(4);
/*      */         } 
/*      */         
/*  438 */         this.metadata.colorSpace = (int)csType;
/*      */         
/*  440 */         if (csType == 0L) {
/*      */           
/*  442 */           this.metadata.redX = redX;
/*  443 */           this.metadata.redY = redY;
/*  444 */           this.metadata.redZ = redZ;
/*  445 */           this.metadata.greenX = greenX;
/*  446 */           this.metadata.greenY = greenY;
/*  447 */           this.metadata.greenZ = greenZ;
/*  448 */           this.metadata.blueX = blueX;
/*  449 */           this.metadata.blueY = blueY;
/*  450 */           this.metadata.blueZ = blueZ;
/*  451 */           this.metadata.gammaRed = (int)gammaRed;
/*  452 */           this.metadata.gammaGreen = (int)gammaGreen;
/*  453 */           this.metadata.gammaBlue = (int)gammaBlue;
/*      */         } 
/*      */ 
/*      */         
/*  457 */         int numberOfEntries = (int)((this.bitmapOffset - 14L - size) / 4L);
/*  458 */         int sizeOfPalette = numberOfEntries * 4;
/*  459 */         this.palette = new byte[sizeOfPalette];
/*  460 */         this.iis.readFully(this.palette, 0, sizeOfPalette);
/*  461 */         this.metadata.palette = this.palette;
/*  462 */         this.metadata.paletteSize = numberOfEntries;
/*      */         
/*  464 */         switch ((int)this.compression) {
/*      */           case 4:
/*      */           case 5:
/*  467 */             if (size == 108L) {
/*  468 */               this.imageType = 17; break;
/*  469 */             }  if (size == 124L) {
/*  470 */               this.imageType = 18;
/*      */             }
/*      */             break;
/*      */           default:
/*  474 */             if (this.bitsPerPixel == 1) {
/*  475 */               this.imageType = 10;
/*  476 */             } else if (this.bitsPerPixel == 4) {
/*  477 */               this.imageType = 11;
/*  478 */             } else if (this.bitsPerPixel == 8) {
/*  479 */               this.imageType = 12;
/*  480 */             } else if (this.bitsPerPixel == 16) {
/*  481 */               this.imageType = 13;
/*  482 */               if ((int)this.compression == 0) {
/*  483 */                 this.redMask = 31744;
/*  484 */                 this.greenMask = 992;
/*  485 */                 this.blueMask = 31;
/*      */               } 
/*  487 */             } else if (this.bitsPerPixel == 24) {
/*  488 */               this.imageType = 14;
/*  489 */             } else if (this.bitsPerPixel == 32) {
/*  490 */               this.imageType = 15;
/*  491 */               if ((int)this.compression == 0) {
/*  492 */                 this.redMask = 16711680;
/*  493 */                 this.greenMask = 65280;
/*  494 */                 this.blueMask = 255;
/*      */               } 
/*      */             } 
/*      */             
/*  498 */             this.metadata.redMask = this.redMask;
/*  499 */             this.metadata.greenMask = this.greenMask;
/*  500 */             this.metadata.blueMask = this.blueMask;
/*  501 */             this.metadata.alphaMask = this.alphaMask; break;
/*      */         } 
/*      */       } else {
/*  504 */         throw new RuntimeException(
/*  505 */             I18N.getString("BMPImageReader3"));
/*      */       } 
/*      */     } 
/*      */     
/*  509 */     if (this.height > 0) {
/*      */       
/*  511 */       this.isBottomUp = true;
/*      */     } else {
/*      */       
/*  514 */       this.isBottomUp = false;
/*  515 */       this.height = Math.abs(this.height);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  520 */     ColorSpace colorSpace = ColorSpace.getInstance(1000);
/*  521 */     if (this.metadata.colorSpace == 3 || this.metadata.colorSpace == 4) {
/*      */ 
/*      */       
/*  524 */       this.iis.mark();
/*  525 */       this.iis.skipBytes(profileData - size);
/*  526 */       byte[] profile = new byte[profileSize];
/*  527 */       this.iis.readFully(profile, 0, profileSize);
/*  528 */       this.iis.reset();
/*      */       
/*      */       try {
/*  531 */         if (this.metadata.colorSpace == 3)
/*      */         
/*  533 */         { colorSpace = new ICC_ColorSpace(ICC_Profile.getInstance(new String(profile))); }
/*      */         else
/*      */         
/*  536 */         { colorSpace = new ICC_ColorSpace(ICC_Profile.getInstance(profile)); } 
/*  537 */       } catch (Exception e) {
/*  538 */         colorSpace = ColorSpace.getInstance(1000);
/*      */       } 
/*      */     } 
/*      */     
/*  542 */     if (this.bitsPerPixel == 0 || this.compression == 4L || this.compression == 5L) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  547 */       this.colorModel = null;
/*  548 */       this.sampleModel = null;
/*  549 */     } else if (this.bitsPerPixel == 1 || this.bitsPerPixel == 4 || this.bitsPerPixel == 8) {
/*      */       byte[] r, g, b;
/*  551 */       this.numBands = 1;
/*      */       
/*  553 */       if (this.bitsPerPixel == 8) {
/*  554 */         int[] bandOffsets = new int[this.numBands];
/*  555 */         for (int i = 0; i < this.numBands; i++) {
/*  556 */           bandOffsets[i] = this.numBands - 1 - i;
/*      */         }
/*  558 */         this.sampleModel = new PixelInterleavedSampleModel(0, this.width, this.height, this.numBands, this.numBands * this.width, bandOffsets);
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */ 
/*      */         
/*  566 */         this.sampleModel = new MultiPixelPackedSampleModel(0, this.width, this.height, this.bitsPerPixel);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  574 */       if (this.imageType == 0 || this.imageType == 1 || this.imageType == 2) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  579 */         size = (this.palette.length / 3);
/*      */         
/*  581 */         if (size > 256L) {
/*  582 */           size = 256L;
/*      */         }
/*      */ 
/*      */         
/*  586 */         r = new byte[(int)size];
/*  587 */         g = new byte[(int)size];
/*  588 */         b = new byte[(int)size];
/*  589 */         for (int i = 0; i < (int)size; i++) {
/*  590 */           int off = 3 * i;
/*  591 */           b[i] = this.palette[off];
/*  592 */           g[i] = this.palette[off + 1];
/*  593 */           r[i] = this.palette[off + 2];
/*      */         } 
/*      */       } else {
/*  596 */         size = (this.palette.length / 4);
/*      */         
/*  598 */         if (size > 256L) {
/*  599 */           size = 256L;
/*      */         }
/*      */ 
/*      */         
/*  603 */         r = new byte[(int)size];
/*  604 */         g = new byte[(int)size];
/*  605 */         b = new byte[(int)size];
/*  606 */         for (int i = 0; i < size; i++) {
/*  607 */           int off = 4 * i;
/*  608 */           b[i] = this.palette[off];
/*  609 */           g[i] = this.palette[off + 1];
/*  610 */           r[i] = this.palette[off + 2];
/*      */         } 
/*      */       } 
/*      */       
/*  614 */       if (ImageUtil.isIndicesForGrayscale(r, g, b))
/*  615 */       { this
/*  616 */           .colorModel = ImageUtil.createColorModel(null, this.sampleModel); }
/*      */       else
/*  618 */       { this.colorModel = new IndexColorModel(this.bitsPerPixel, (int)size, r, g, b); } 
/*  619 */     } else if (this.bitsPerPixel == 16) {
/*  620 */       this.numBands = 3;
/*  621 */       this.sampleModel = new SinglePixelPackedSampleModel(1, this.width, this.height, new int[] { this.redMask, this.greenMask, this.blueMask });
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  626 */       this.colorModel = new DirectColorModel(colorSpace, 16, this.redMask, this.greenMask, this.blueMask, 0, false, 1);
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  631 */     else if (this.bitsPerPixel == 32) {
/*  632 */       this.numBands = (this.alphaMask == 0) ? 3 : 4;
/*      */       
/*  634 */       if (this.redMask == 0 || this.greenMask == 0 || this.blueMask == 0) {
/*  635 */         this.redMask = 16711680;
/*  636 */         this.greenMask = 65280;
/*  637 */         this.blueMask = 255;
/*  638 */         this.alphaMask = -16777216;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  643 */       (new int[3])[0] = this.redMask; (new int[3])[1] = this.greenMask; (new int[3])[2] = this.blueMask; (new int[4])[0] = this.redMask; (new int[4])[1] = this.greenMask; (new int[4])[2] = this.blueMask; (new int[4])[3] = this.alphaMask; int[] bitMasks = (this.numBands == 3) ? new int[3] : new int[4];
/*      */ 
/*      */ 
/*      */       
/*  647 */       this.sampleModel = new SinglePixelPackedSampleModel(3, this.width, this.height, bitMasks);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  652 */       this.colorModel = new DirectColorModel(colorSpace, 32, this.redMask, this.greenMask, this.blueMask, this.alphaMask, false, 3);
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  657 */       this.numBands = 3;
/*      */       
/*  659 */       int[] bandOffsets = new int[this.numBands];
/*  660 */       for (int i = 0; i < this.numBands; i++) {
/*  661 */         bandOffsets[i] = this.numBands - 1 - i;
/*      */       }
/*      */       
/*  664 */       this.sampleModel = new PixelInterleavedSampleModel(0, this.width, this.height, this.numBands, this.numBands * this.width, bandOffsets);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  671 */       this
/*  672 */         .colorModel = ImageUtil.createColorModel(colorSpace, this.sampleModel);
/*      */     } 
/*      */     
/*  675 */     this.originalSampleModel = this.sampleModel;
/*  676 */     this.originalColorModel = this.colorModel;
/*      */ 
/*      */ 
/*      */     
/*  680 */     this.iis.reset();
/*  681 */     this.iis.skipBytes(this.bitmapOffset);
/*  682 */     this.gotHeader = true;
/*      */ 
/*      */     
/*  685 */     this.imageDataOffset = this.iis.getStreamPosition();
/*      */   }
/*      */ 
/*      */   
/*      */   public Iterator getImageTypes(int imageIndex) throws IOException {
/*  690 */     checkIndex(imageIndex);
/*  691 */     readHeader();
/*  692 */     ArrayList<ImageTypeSpecifier> list = new ArrayList(1);
/*  693 */     list.add(new ImageTypeSpecifier(this.originalColorModel, this.originalSampleModel));
/*      */     
/*  695 */     return list.iterator();
/*      */   }
/*      */   
/*      */   public ImageReadParam getDefaultReadParam() {
/*  699 */     return new ImageReadParam();
/*      */   }
/*      */ 
/*      */   
/*      */   public IIOMetadata getImageMetadata(int imageIndex) throws IOException {
/*  704 */     checkIndex(imageIndex);
/*  705 */     if (this.metadata == null) {
/*  706 */       readHeader();
/*      */     }
/*  708 */     return this.metadata;
/*      */   }
/*      */   
/*      */   public IIOMetadata getStreamMetadata() throws IOException {
/*  712 */     return null;
/*      */   }
/*      */   
/*      */   public boolean isRandomAccessEasy(int imageIndex) throws IOException {
/*  716 */     checkIndex(imageIndex);
/*  717 */     readHeader();
/*  718 */     return (this.metadata.compression == 0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
/*  724 */     if (this.iis == null) {
/*  725 */       throw new IllegalStateException(I18N.getString("BMPImageReader5"));
/*      */     }
/*      */     
/*  728 */     checkIndex(imageIndex);
/*  729 */     clearAbortRequest();
/*  730 */     processImageStarted(imageIndex);
/*      */     
/*  732 */     if (param == null) {
/*  733 */       param = getDefaultReadParam();
/*      */     }
/*      */     
/*  736 */     readHeader();
/*      */     
/*  738 */     this.sourceRegion = new Rectangle(0, 0, 0, 0);
/*  739 */     this.destinationRegion = new Rectangle(0, 0, 0, 0);
/*      */     
/*  741 */     computeRegions(param, this.width, this.height, param
/*  742 */         .getDestination(), this.sourceRegion, this.destinationRegion);
/*      */ 
/*      */ 
/*      */     
/*  746 */     this.scaleX = param.getSourceXSubsampling();
/*  747 */     this.scaleY = param.getSourceYSubsampling();
/*      */ 
/*      */     
/*  750 */     this.sourceBands = param.getSourceBands();
/*  751 */     this.destBands = param.getDestinationBands();
/*      */     
/*  753 */     this.seleBand = (this.sourceBands != null && this.destBands != null);
/*  754 */     this
/*  755 */       .noTransform = (this.destinationRegion.equals(new Rectangle(0, 0, this.width, this.height)) || this.seleBand);
/*      */ 
/*      */     
/*  758 */     if (!this.seleBand) {
/*  759 */       this.sourceBands = new int[this.numBands];
/*  760 */       this.destBands = new int[this.numBands];
/*  761 */       for (int i = 0; i < this.numBands; i++) {
/*  762 */         this.sourceBands[i] = i; this.destBands[i] = i;
/*      */       } 
/*      */     } 
/*      */     
/*  766 */     this.bi = param.getDestination();
/*      */ 
/*      */     
/*  769 */     WritableRaster raster = null;
/*      */     
/*  771 */     if (this.bi == null) {
/*  772 */       if (this.sampleModel != null && this.colorModel != null) {
/*  773 */         this
/*  774 */           .sampleModel = this.sampleModel.createCompatibleSampleModel(this.destinationRegion.x + this.destinationRegion.width, this.destinationRegion.y + this.destinationRegion.height);
/*      */ 
/*      */ 
/*      */         
/*  778 */         if (this.seleBand)
/*  779 */           this.sampleModel = this.sampleModel.createSubsetSampleModel(this.sourceBands); 
/*  780 */         raster = Raster.createWritableRaster(this.sampleModel, new Point());
/*  781 */         this.bi = new BufferedImage(this.colorModel, raster, false, null);
/*      */       } 
/*      */     } else {
/*  784 */       raster = this.bi.getWritableTile(0, 0);
/*  785 */       this.sampleModel = this.bi.getSampleModel();
/*  786 */       this.colorModel = this.bi.getColorModel();
/*      */       
/*  788 */       this.noTransform &= this.destinationRegion.equals(raster.getBounds());
/*      */     } 
/*      */     
/*  791 */     byte[] bdata = null;
/*  792 */     short[] sdata = null;
/*  793 */     int[] idata = null;
/*      */ 
/*      */     
/*  796 */     if (this.sampleModel != null) {
/*  797 */       if (this.sampleModel.getDataType() == 0) {
/*      */         
/*  799 */         bdata = ((DataBufferByte)raster.getDataBuffer()).getData();
/*  800 */       } else if (this.sampleModel.getDataType() == 1) {
/*      */         
/*  802 */         sdata = ((DataBufferUShort)raster.getDataBuffer()).getData();
/*  803 */       } else if (this.sampleModel.getDataType() == 3) {
/*      */         
/*  805 */         idata = ((DataBufferInt)raster.getDataBuffer()).getData();
/*      */       } 
/*      */     }
/*      */     
/*  809 */     switch (this.imageType) {
/*      */ 
/*      */       
/*      */       case 0:
/*  813 */         read1Bit(bdata);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 1:
/*  818 */         read4Bit(bdata);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2:
/*  823 */         read8Bit(bdata);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 3:
/*  828 */         read24Bit(bdata);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 4:
/*  833 */         read1Bit(bdata);
/*      */         break;
/*      */       
/*      */       case 5:
/*  837 */         switch ((int)this.compression) {
/*      */           case 0:
/*  839 */             read4Bit(bdata);
/*      */             break;
/*      */           
/*      */           case 2:
/*  843 */             readRLE4(bdata);
/*      */             break;
/*      */         } 
/*      */         
/*  847 */         throw new RuntimeException(
/*  848 */             I18N.getString("BMPImageReader1"));
/*      */ 
/*      */ 
/*      */       
/*      */       case 6:
/*  853 */         switch ((int)this.compression) {
/*      */           case 0:
/*  855 */             read8Bit(bdata);
/*      */             break;
/*      */           
/*      */           case 1:
/*  859 */             readRLE8(bdata);
/*      */             break;
/*      */         } 
/*      */         
/*  863 */         throw new RuntimeException(
/*  864 */             I18N.getString("BMPImageReader1"));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 7:
/*  871 */         read24Bit(bdata);
/*      */         break;
/*      */       
/*      */       case 8:
/*  875 */         read16Bit(sdata);
/*      */         break;
/*      */       
/*      */       case 9:
/*  879 */         read32Bit(idata);
/*      */         break;
/*      */       
/*      */       case 16:
/*      */       case 17:
/*      */       case 18:
/*  885 */         this.bi = readEmbedded((int)this.compression, this.bi, param);
/*      */         break;
/*      */       
/*      */       case 10:
/*  889 */         read1Bit(bdata);
/*      */         break;
/*      */       
/*      */       case 11:
/*  893 */         switch ((int)this.compression) {
/*      */           
/*      */           case 0:
/*  896 */             read4Bit(bdata);
/*      */             break;
/*      */           
/*      */           case 2:
/*  900 */             readRLE4(bdata);
/*      */             break;
/*      */           
/*      */           default:
/*  904 */             throw new RuntimeException(
/*  905 */                 I18N.getString("BMPImageReader1"));
/*      */         } 
/*      */       
/*      */       case 12:
/*  909 */         switch ((int)this.compression) {
/*      */           
/*      */           case 0:
/*  912 */             read8Bit(bdata);
/*      */             break;
/*      */           
/*      */           case 1:
/*  916 */             readRLE8(bdata);
/*      */             break;
/*      */         } 
/*      */         
/*  920 */         throw new RuntimeException(
/*  921 */             I18N.getString("BMPImageReader1"));
/*      */ 
/*      */ 
/*      */       
/*      */       case 13:
/*  926 */         read16Bit(sdata);
/*      */         break;
/*      */       
/*      */       case 14:
/*  930 */         read24Bit(bdata);
/*      */         break;
/*      */       
/*      */       case 15:
/*  934 */         read32Bit(idata);
/*      */         break;
/*      */     } 
/*      */     
/*  938 */     if (abortRequested()) {
/*  939 */       processReadAborted();
/*      */     } else {
/*  941 */       processImageComplete();
/*      */     } 
/*  943 */     return this.bi;
/*      */   }
/*      */   
/*      */   public boolean canReadRaster() {
/*  947 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public Raster readRaster(int imageIndex, ImageReadParam param) throws IOException {
/*  952 */     BufferedImage bi = read(imageIndex, param);
/*  953 */     return bi.getData();
/*      */   }
/*      */   
/*      */   private void resetHeaderInfo() {
/*  957 */     this.gotHeader = false;
/*  958 */     this.bi = null;
/*  959 */     this.sampleModel = this.originalSampleModel = null;
/*  960 */     this.colorModel = this.originalColorModel = null;
/*      */   }
/*      */   
/*      */   public void reset() {
/*  964 */     super.reset();
/*  965 */     this.iis = null;
/*  966 */     resetHeaderInfo();
/*      */   }
/*      */ 
/*      */   
/*      */   private void read1Bit(byte[] bdata) throws IOException {
/*  971 */     int bytesPerScanline = (this.width + 7) / 8;
/*  972 */     int padding = bytesPerScanline % 4;
/*  973 */     if (padding != 0) {
/*  974 */       padding = 4 - padding;
/*      */     }
/*      */     
/*  977 */     int lineLength = bytesPerScanline + padding;
/*      */     
/*  979 */     if (this.noTransform) {
/*  980 */       int j = this.isBottomUp ? ((this.height - 1) * bytesPerScanline) : 0;
/*      */       
/*  982 */       for (int i = 0; i < this.height && 
/*  983 */         !abortRequested(); i++) {
/*      */ 
/*      */         
/*  986 */         this.iis.readFully(bdata, j, bytesPerScanline);
/*  987 */         this.iis.skipBytes(padding);
/*  988 */         j += this.isBottomUp ? -bytesPerScanline : bytesPerScanline;
/*  989 */         processImageUpdate(this.bi, 0, i, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */         
/*  992 */         processImageProgress(100.0F * i / this.destinationRegion.height);
/*      */       } 
/*      */     } else {
/*  995 */       byte[] buf = new byte[lineLength];
/*      */       
/*  997 */       int lineStride = ((MultiPixelPackedSampleModel)this.sampleModel).getScanlineStride();
/*      */       
/*  999 */       if (this.isBottomUp) {
/* 1000 */         int lastLine = this.sourceRegion.y + (this.destinationRegion.height - 1) * this.scaleY;
/*      */         
/* 1002 */         this.iis.skipBytes(lineLength * (this.height - 1 - lastLine));
/*      */       } else {
/* 1004 */         this.iis.skipBytes(lineLength * this.sourceRegion.y);
/*      */       } 
/* 1006 */       int skipLength = lineLength * (this.scaleY - 1);
/*      */ 
/*      */       
/* 1009 */       int[] srcOff = new int[this.destinationRegion.width];
/* 1010 */       int[] destOff = new int[this.destinationRegion.width];
/* 1011 */       int[] srcPos = new int[this.destinationRegion.width];
/* 1012 */       int[] destPos = new int[this.destinationRegion.width];
/*      */       
/* 1014 */       int i = this.destinationRegion.x, x = this.sourceRegion.x, m = 0;
/* 1015 */       for (; i < this.destinationRegion.x + this.destinationRegion.width; 
/* 1016 */         i++, m++, x += this.scaleX) {
/* 1017 */         srcPos[m] = x >> 3;
/* 1018 */         srcOff[m] = 7 - (x & 0x7);
/* 1019 */         destPos[m] = i >> 3;
/* 1020 */         destOff[m] = 7 - (i & 0x7);
/*      */       } 
/*      */       
/* 1023 */       int k = this.destinationRegion.y * lineStride;
/* 1024 */       if (this.isBottomUp) {
/* 1025 */         k += (this.destinationRegion.height - 1) * lineStride;
/*      */       }
/* 1027 */       int j = 0, y = this.sourceRegion.y;
/* 1028 */       for (; j < this.destinationRegion.height; j++, y += this.scaleY) {
/*      */         
/* 1030 */         if (abortRequested())
/*      */           break; 
/* 1032 */         this.iis.read(buf, 0, lineLength);
/* 1033 */         for (int n = 0; n < this.destinationRegion.width; n++) {
/*      */           
/* 1035 */           int v = buf[srcPos[n]] >> srcOff[n] & 0x1;
/* 1036 */           bdata[k + destPos[n]] = (byte)(bdata[k + destPos[n]] | v << destOff[n]);
/*      */         } 
/*      */         
/* 1039 */         k += this.isBottomUp ? -lineStride : lineStride;
/* 1040 */         this.iis.skipBytes(skipLength);
/* 1041 */         processImageUpdate(this.bi, 0, j, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */         
/* 1044 */         processImageProgress(100.0F * j / this.destinationRegion.height);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void read4Bit(byte[] bdata) throws IOException {
/* 1052 */     int bytesPerScanline = (this.width + 1) / 2;
/*      */ 
/*      */     
/* 1055 */     int padding = bytesPerScanline % 4;
/* 1056 */     if (padding != 0) {
/* 1057 */       padding = 4 - padding;
/*      */     }
/* 1059 */     int lineLength = bytesPerScanline + padding;
/*      */     
/* 1061 */     if (this.noTransform) {
/* 1062 */       int j = this.isBottomUp ? ((this.height - 1) * bytesPerScanline) : 0;
/*      */       
/* 1064 */       for (int i = 0; i < this.height && 
/* 1065 */         !abortRequested(); i++) {
/*      */ 
/*      */         
/* 1068 */         this.iis.readFully(bdata, j, bytesPerScanline);
/* 1069 */         this.iis.skipBytes(padding);
/* 1070 */         j += this.isBottomUp ? -bytesPerScanline : bytesPerScanline;
/* 1071 */         processImageUpdate(this.bi, 0, i, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */         
/* 1074 */         processImageProgress(100.0F * i / this.destinationRegion.height);
/*      */       } 
/*      */     } else {
/* 1077 */       byte[] buf = new byte[lineLength];
/*      */       
/* 1079 */       int lineStride = ((MultiPixelPackedSampleModel)this.sampleModel).getScanlineStride();
/*      */       
/* 1081 */       if (this.isBottomUp) {
/* 1082 */         int lastLine = this.sourceRegion.y + (this.destinationRegion.height - 1) * this.scaleY;
/*      */         
/* 1084 */         this.iis.skipBytes(lineLength * (this.height - 1 - lastLine));
/*      */       } else {
/* 1086 */         this.iis.skipBytes(lineLength * this.sourceRegion.y);
/*      */       } 
/* 1088 */       int skipLength = lineLength * (this.scaleY - 1);
/*      */ 
/*      */       
/* 1091 */       int[] srcOff = new int[this.destinationRegion.width];
/* 1092 */       int[] destOff = new int[this.destinationRegion.width];
/* 1093 */       int[] srcPos = new int[this.destinationRegion.width];
/* 1094 */       int[] destPos = new int[this.destinationRegion.width];
/*      */       
/* 1096 */       int i = this.destinationRegion.x, x = this.sourceRegion.x, m = 0;
/* 1097 */       for (; i < this.destinationRegion.x + this.destinationRegion.width; 
/* 1098 */         i++, m++, x += this.scaleX) {
/* 1099 */         srcPos[m] = x >> 1;
/* 1100 */         srcOff[m] = 1 - (x & 0x1) << 2;
/* 1101 */         destPos[m] = i >> 1;
/* 1102 */         destOff[m] = 1 - (i & 0x1) << 2;
/*      */       } 
/*      */       
/* 1105 */       int k = this.destinationRegion.y * lineStride;
/* 1106 */       if (this.isBottomUp) {
/* 1107 */         k += (this.destinationRegion.height - 1) * lineStride;
/*      */       }
/* 1109 */       int j = 0, y = this.sourceRegion.y;
/* 1110 */       for (; j < this.destinationRegion.height; j++, y += this.scaleY) {
/*      */         
/* 1112 */         if (abortRequested())
/*      */           break; 
/* 1114 */         this.iis.read(buf, 0, lineLength);
/* 1115 */         for (int n = 0; n < this.destinationRegion.width; n++) {
/*      */           
/* 1117 */           int v = buf[srcPos[n]] >> srcOff[n] & 0xF;
/* 1118 */           bdata[k + destPos[n]] = (byte)(bdata[k + destPos[n]] | v << destOff[n]);
/*      */         } 
/*      */         
/* 1121 */         k += this.isBottomUp ? -lineStride : lineStride;
/* 1122 */         this.iis.skipBytes(skipLength);
/* 1123 */         processImageUpdate(this.bi, 0, j, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */         
/* 1126 */         processImageProgress(100.0F * j / this.destinationRegion.height);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void read8Bit(byte[] bdata) throws IOException {
/* 1135 */     int padding = this.width % 4;
/* 1136 */     if (padding != 0) {
/* 1137 */       padding = 4 - padding;
/*      */     }
/*      */     
/* 1140 */     int lineLength = this.width + padding;
/*      */     
/* 1142 */     if (this.noTransform) {
/* 1143 */       int j = this.isBottomUp ? ((this.height - 1) * this.width) : 0;
/*      */       
/* 1145 */       for (int i = 0; i < this.height && 
/* 1146 */         !abortRequested(); i++) {
/*      */ 
/*      */         
/* 1149 */         this.iis.readFully(bdata, j, this.width);
/* 1150 */         this.iis.skipBytes(padding);
/* 1151 */         j += this.isBottomUp ? -this.width : this.width;
/* 1152 */         processImageUpdate(this.bi, 0, i, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */         
/* 1155 */         processImageProgress(100.0F * i / this.destinationRegion.height);
/*      */       } 
/*      */     } else {
/* 1158 */       byte[] buf = new byte[lineLength];
/*      */       
/* 1160 */       int lineStride = ((ComponentSampleModel)this.sampleModel).getScanlineStride();
/*      */       
/* 1162 */       if (this.isBottomUp) {
/* 1163 */         int lastLine = this.sourceRegion.y + (this.destinationRegion.height - 1) * this.scaleY;
/*      */         
/* 1165 */         this.iis.skipBytes(lineLength * (this.height - 1 - lastLine));
/*      */       } else {
/* 1167 */         this.iis.skipBytes(lineLength * this.sourceRegion.y);
/*      */       } 
/* 1169 */       int skipLength = lineLength * (this.scaleY - 1);
/*      */       
/* 1171 */       int k = this.destinationRegion.y * lineStride;
/* 1172 */       if (this.isBottomUp)
/* 1173 */         k += (this.destinationRegion.height - 1) * lineStride; 
/* 1174 */       k += this.destinationRegion.x;
/*      */       
/* 1176 */       int j = 0, y = this.sourceRegion.y;
/* 1177 */       for (; j < this.destinationRegion.height; j++, y += this.scaleY) {
/*      */         
/* 1179 */         if (abortRequested())
/*      */           break; 
/* 1181 */         this.iis.read(buf, 0, lineLength);
/* 1182 */         int i = 0, m = this.sourceRegion.x;
/* 1183 */         for (; i < this.destinationRegion.width; i++, m += this.scaleX)
/*      */         {
/* 1185 */           bdata[k + i] = buf[m];
/*      */         }
/*      */         
/* 1188 */         k += this.isBottomUp ? -lineStride : lineStride;
/* 1189 */         this.iis.skipBytes(skipLength);
/* 1190 */         processImageUpdate(this.bi, 0, j, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */         
/* 1193 */         processImageProgress(100.0F * j / this.destinationRegion.height);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void read24Bit(byte[] bdata) throws IOException {
/* 1202 */     int padding = this.width * 3 % 4;
/* 1203 */     if (padding != 0) {
/* 1204 */       padding = 4 - padding;
/*      */     }
/* 1206 */     int lineStride = this.width * 3;
/* 1207 */     int lineLength = lineStride + padding;
/*      */     
/* 1209 */     if (this.noTransform) {
/* 1210 */       int j = this.isBottomUp ? ((this.height - 1) * this.width * 3) : 0;
/*      */       
/* 1212 */       for (int i = 0; i < this.height && 
/* 1213 */         !abortRequested(); i++) {
/*      */ 
/*      */         
/* 1216 */         this.iis.readFully(bdata, j, lineStride);
/* 1217 */         this.iis.skipBytes(padding);
/* 1218 */         j += this.isBottomUp ? -lineStride : lineStride;
/* 1219 */         processImageUpdate(this.bi, 0, i, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */         
/* 1222 */         processImageProgress(100.0F * i / this.destinationRegion.height);
/*      */       } 
/*      */     } else {
/* 1225 */       byte[] buf = new byte[lineLength];
/*      */       
/* 1227 */       lineStride = ((ComponentSampleModel)this.sampleModel).getScanlineStride();
/*      */       
/* 1229 */       if (this.isBottomUp) {
/* 1230 */         int lastLine = this.sourceRegion.y + (this.destinationRegion.height - 1) * this.scaleY;
/*      */         
/* 1232 */         this.iis.skipBytes(lineLength * (this.height - 1 - lastLine));
/*      */       } else {
/* 1234 */         this.iis.skipBytes(lineLength * this.sourceRegion.y);
/*      */       } 
/* 1236 */       int skipLength = lineLength * (this.scaleY - 1);
/*      */       
/* 1238 */       int k = this.destinationRegion.y * lineStride;
/* 1239 */       if (this.isBottomUp)
/* 1240 */         k += (this.destinationRegion.height - 1) * lineStride; 
/* 1241 */       k += this.destinationRegion.x * 3;
/*      */       
/* 1243 */       int j = 0, y = this.sourceRegion.y;
/* 1244 */       for (; j < this.destinationRegion.height; j++, y += this.scaleY) {
/*      */         
/* 1246 */         if (abortRequested())
/*      */           break; 
/* 1248 */         this.iis.read(buf, 0, lineLength);
/* 1249 */         int i = 0, m = 3 * this.sourceRegion.x;
/* 1250 */         for (; i < this.destinationRegion.width; i++, m += 3 * this.scaleX) {
/*      */           
/* 1252 */           int n = 3 * i + k;
/* 1253 */           for (int b = 0; b < this.destBands.length; b++) {
/* 1254 */             bdata[n + this.destBands[b]] = buf[m + this.sourceBands[b]];
/*      */           }
/*      */         } 
/* 1257 */         k += this.isBottomUp ? -lineStride : lineStride;
/* 1258 */         this.iis.skipBytes(skipLength);
/* 1259 */         processImageUpdate(this.bi, 0, j, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */         
/* 1262 */         processImageProgress(100.0F * j / this.destinationRegion.height);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void read16Bit(short[] sdata) throws IOException {
/* 1270 */     int padding = this.width * 2 % 4;
/*      */     
/* 1272 */     if (padding != 0) {
/* 1273 */       padding = 4 - padding;
/*      */     }
/* 1275 */     int lineLength = this.width + padding / 2;
/*      */     
/* 1277 */     if (this.noTransform) {
/* 1278 */       int j = this.isBottomUp ? ((this.height - 1) * this.width) : 0;
/* 1279 */       for (int i = 0; i < this.height && 
/* 1280 */         !abortRequested(); i++) {
/*      */ 
/*      */ 
/*      */         
/* 1284 */         this.iis.readFully(sdata, j, this.width);
/* 1285 */         this.iis.skipBytes(padding);
/* 1286 */         j += this.isBottomUp ? -this.width : this.width;
/* 1287 */         processImageUpdate(this.bi, 0, i, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */         
/* 1290 */         processImageProgress(100.0F * i / this.destinationRegion.height);
/*      */       } 
/*      */     } else {
/* 1293 */       short[] buf = new short[lineLength];
/*      */       
/* 1295 */       int lineStride = ((SinglePixelPackedSampleModel)this.sampleModel).getScanlineStride();
/*      */       
/* 1297 */       if (this.isBottomUp) {
/* 1298 */         int lastLine = this.sourceRegion.y + (this.destinationRegion.height - 1) * this.scaleY;
/*      */         
/* 1300 */         this.iis.skipBytes(lineLength * (this.height - 1 - lastLine) << 1);
/*      */       } else {
/* 1302 */         this.iis.skipBytes(lineLength * this.sourceRegion.y << 1);
/*      */       } 
/* 1304 */       int skipLength = lineLength * (this.scaleY - 1) << 1;
/*      */       
/* 1306 */       int k = this.destinationRegion.y * lineStride;
/* 1307 */       if (this.isBottomUp)
/* 1308 */         k += (this.destinationRegion.height - 1) * lineStride; 
/* 1309 */       k += this.destinationRegion.x;
/*      */       
/* 1311 */       int j = 0, y = this.sourceRegion.y;
/* 1312 */       for (; j < this.destinationRegion.height; j++, y += this.scaleY) {
/*      */         
/* 1314 */         if (abortRequested())
/*      */           break; 
/* 1316 */         this.iis.readFully(buf, 0, lineLength);
/* 1317 */         int i = 0, m = this.sourceRegion.x;
/* 1318 */         for (; i < this.destinationRegion.width; i++, m += this.scaleX)
/*      */         {
/* 1320 */           sdata[k + i] = buf[m];
/*      */         }
/*      */         
/* 1323 */         k += this.isBottomUp ? -lineStride : lineStride;
/* 1324 */         this.iis.skipBytes(skipLength);
/* 1325 */         processImageUpdate(this.bi, 0, j, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */         
/* 1328 */         processImageProgress(100.0F * j / this.destinationRegion.height);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void read32Bit(int[] idata) throws IOException {
/* 1334 */     if (this.noTransform) {
/* 1335 */       int j = this.isBottomUp ? ((this.height - 1) * this.width) : 0;
/*      */       
/* 1337 */       for (int i = 0; i < this.height && 
/* 1338 */         !abortRequested(); i++) {
/*      */ 
/*      */         
/* 1341 */         this.iis.readFully(idata, j, this.width);
/* 1342 */         j += this.isBottomUp ? -this.width : this.width;
/* 1343 */         processImageUpdate(this.bi, 0, i, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */         
/* 1346 */         processImageProgress(100.0F * i / this.destinationRegion.height);
/*      */       } 
/*      */     } else {
/* 1349 */       int[] buf = new int[this.width];
/*      */       
/* 1351 */       int lineStride = ((SinglePixelPackedSampleModel)this.sampleModel).getScanlineStride();
/*      */       
/* 1353 */       if (this.isBottomUp) {
/* 1354 */         int lastLine = this.sourceRegion.y + (this.destinationRegion.height - 1) * this.scaleY;
/*      */         
/* 1356 */         this.iis.skipBytes(this.width * (this.height - 1 - lastLine) << 2);
/*      */       } else {
/* 1358 */         this.iis.skipBytes(this.width * this.sourceRegion.y << 2);
/*      */       } 
/* 1360 */       int skipLength = this.width * (this.scaleY - 1) << 2;
/*      */       
/* 1362 */       int k = this.destinationRegion.y * lineStride;
/* 1363 */       if (this.isBottomUp)
/* 1364 */         k += (this.destinationRegion.height - 1) * lineStride; 
/* 1365 */       k += this.destinationRegion.x;
/*      */       
/* 1367 */       int j = 0, y = this.sourceRegion.y;
/* 1368 */       for (; j < this.destinationRegion.height; j++, y += this.scaleY) {
/*      */         
/* 1370 */         if (abortRequested())
/*      */           break; 
/* 1372 */         this.iis.readFully(buf, 0, this.width);
/* 1373 */         int i = 0, m = this.sourceRegion.x;
/* 1374 */         for (; i < this.destinationRegion.width; i++, m += this.scaleX)
/*      */         {
/* 1376 */           idata[k + i] = buf[m];
/*      */         }
/*      */         
/* 1379 */         k += this.isBottomUp ? -lineStride : lineStride;
/* 1380 */         this.iis.skipBytes(skipLength);
/* 1381 */         processImageUpdate(this.bi, 0, j, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */         
/* 1384 */         processImageProgress(100.0F * j / this.destinationRegion.height);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void readRLE8(byte[] bdata) throws IOException {
/* 1391 */     int imSize = (int)this.imageSize;
/* 1392 */     if (imSize == 0) {
/* 1393 */       imSize = (int)(this.bitmapFileSize - this.bitmapOffset);
/*      */     }
/*      */     
/* 1396 */     int padding = 0;
/*      */ 
/*      */     
/* 1399 */     int remainder = this.width % 4;
/* 1400 */     if (remainder != 0) {
/* 1401 */       padding = 4 - remainder;
/*      */     }
/*      */ 
/*      */     
/* 1405 */     byte[] values = new byte[imSize];
/* 1406 */     int bytesRead = 0;
/* 1407 */     this.iis.readFully(values, 0, imSize);
/*      */ 
/*      */     
/* 1410 */     decodeRLE8(imSize, padding, values, bdata);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void decodeRLE8(int imSize, int padding, byte[] values, byte[] bdata) throws IOException {
/* 1418 */     byte[] val = new byte[this.width * this.height];
/* 1419 */     int count = 0, l = 0;
/*      */     
/* 1421 */     boolean flag = false;
/* 1422 */     int lineNo = this.isBottomUp ? (this.height - 1) : 0;
/*      */     
/* 1424 */     int lineStride = ((ComponentSampleModel)this.sampleModel).getScanlineStride();
/* 1425 */     int finished = 0;
/*      */     
/* 1427 */     while (count != imSize) {
/* 1428 */       int value = values[count++] & 0xFF;
/* 1429 */       if (value == 0) {
/* 1430 */         int xoff; int yoff; int end; int i; switch (values[count++] & 0xFF) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 0:
/*      */           case 1:
/* 1437 */             if (lineNo >= this.sourceRegion.y && lineNo < this.sourceRegion.y + this.sourceRegion.height)
/*      */             {
/* 1439 */               if (this.noTransform) {
/* 1440 */                 int pos = lineNo * this.width;
/* 1441 */                 for (int j = 0; j < this.width; j++)
/* 1442 */                   bdata[pos++] = val[j]; 
/* 1443 */                 processImageUpdate(this.bi, 0, lineNo, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */                 
/* 1446 */                 finished++;
/* 1447 */               } else if ((lineNo - this.sourceRegion.y) % this.scaleY == 0) {
/* 1448 */                 int currentLine = (lineNo - this.sourceRegion.y) / this.scaleY + this.destinationRegion.y;
/*      */                 
/* 1450 */                 int pos = currentLine * lineStride;
/* 1451 */                 pos += this.destinationRegion.x;
/* 1452 */                 int j = this.sourceRegion.x;
/* 1453 */                 for (; j < this.sourceRegion.x + this.sourceRegion.width; 
/* 1454 */                   j += this.scaleX)
/* 1455 */                   bdata[pos++] = val[j]; 
/* 1456 */                 processImageUpdate(this.bi, 0, currentLine, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */                 
/* 1459 */                 finished++;
/*      */               } 
/*      */             }
/* 1462 */             processImageProgress(100.0F * finished / this.destinationRegion.height);
/* 1463 */             lineNo += this.isBottomUp ? -1 : 1;
/* 1464 */             l = 0;
/*      */             
/* 1466 */             if (abortRequested()) {
/*      */               break;
/*      */             }
/*      */ 
/*      */             
/* 1471 */             if ((values[count - 1] & 0xFF) == 1) {
/* 1472 */               flag = true;
/*      */             }
/*      */             break;
/*      */ 
/*      */           
/*      */           case 2:
/* 1478 */             xoff = values[count++] & 0xFF;
/* 1479 */             yoff = values[count] & 0xFF;
/*      */             
/* 1481 */             l += xoff + yoff * this.width;
/*      */             break;
/*      */           
/*      */           default:
/* 1485 */             end = values[count - 1] & 0xFF;
/* 1486 */             for (i = 0; i < end; i++) {
/* 1487 */               val[l++] = (byte)(values[count++] & 0xFF);
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1492 */             if ((end & 0x1) == 1)
/* 1493 */               count++; 
/*      */             break;
/*      */         } 
/*      */       } else {
/* 1497 */         for (int i = 0; i < value; i++) {
/* 1498 */           val[l++] = (byte)(values[count] & 0xFF);
/*      */         }
/*      */         
/* 1501 */         count++;
/*      */       } 
/*      */ 
/*      */       
/* 1505 */       if (flag) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void readRLE4(byte[] bdata) throws IOException {
/* 1514 */     int imSize = (int)this.imageSize;
/* 1515 */     if (imSize == 0) {
/* 1516 */       imSize = (int)(this.bitmapFileSize - this.bitmapOffset);
/*      */     }
/*      */     
/* 1519 */     int padding = 0;
/*      */ 
/*      */     
/* 1522 */     int remainder = this.width % 4;
/* 1523 */     if (remainder != 0) {
/* 1524 */       padding = 4 - remainder;
/*      */     }
/*      */ 
/*      */     
/* 1528 */     byte[] values = new byte[imSize];
/* 1529 */     this.iis.readFully(values, 0, imSize);
/*      */ 
/*      */     
/* 1532 */     decodeRLE4(imSize, padding, values, bdata);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void decodeRLE4(int imSize, int padding, byte[] values, byte[] bdata) throws IOException {
/* 1539 */     byte[] val = new byte[this.width];
/* 1540 */     int count = 0, l = 0;
/*      */     
/* 1542 */     boolean flag = false;
/* 1543 */     int lineNo = this.isBottomUp ? (this.height - 1) : 0;
/*      */     
/* 1545 */     int lineStride = ((MultiPixelPackedSampleModel)this.sampleModel).getScanlineStride();
/* 1546 */     int finished = 0;
/*      */     
/* 1548 */     while (count != imSize) {
/*      */       
/* 1550 */       int value = values[count++] & 0xFF;
/* 1551 */       if (value == 0) {
/*      */         int xoff; int yoff;
/*      */         int end;
/*      */         int i;
/* 1555 */         switch (values[count++] & 0xFF) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           case 0:
/*      */           case 1:
/* 1562 */             if (lineNo >= this.sourceRegion.y && lineNo < this.sourceRegion.y + this.sourceRegion.height)
/*      */             {
/* 1564 */               if (this.noTransform) {
/* 1565 */                 int pos = lineNo * (this.width + 1 >> 1);
/* 1566 */                 for (int k = 0, j = 0; k < this.width >> 1; k++) {
/* 1567 */                   bdata[pos++] = (byte)(val[j++] << 4 | val[j++]);
/*      */                 }
/* 1569 */                 if ((this.width & 0x1) == 1) {
/* 1570 */                   bdata[pos] = (byte)(bdata[pos] | val[this.width - 1] << 4);
/*      */                 }
/* 1572 */                 processImageUpdate(this.bi, 0, lineNo, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */                 
/* 1575 */                 finished++;
/* 1576 */               } else if ((lineNo - this.sourceRegion.y) % this.scaleY == 0) {
/* 1577 */                 int currentLine = (lineNo - this.sourceRegion.y) / this.scaleY + this.destinationRegion.y;
/*      */                 
/* 1579 */                 int pos = currentLine * lineStride;
/* 1580 */                 pos += this.destinationRegion.x >> 1;
/* 1581 */                 int shift = 1 - (this.destinationRegion.x & 0x1) << 2;
/* 1582 */                 int j = this.sourceRegion.x;
/* 1583 */                 for (; j < this.sourceRegion.x + this.sourceRegion.width; 
/* 1584 */                   j += this.scaleX) {
/* 1585 */                   bdata[pos] = (byte)(bdata[pos] | val[j] << shift);
/* 1586 */                   shift += 4;
/* 1587 */                   if (shift == 4) {
/* 1588 */                     pos++;
/*      */                   }
/* 1590 */                   shift &= 0x7;
/*      */                 } 
/* 1592 */                 processImageUpdate(this.bi, 0, currentLine, this.destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*      */ 
/*      */                 
/* 1595 */                 finished++;
/*      */               } 
/*      */             }
/* 1598 */             processImageProgress(100.0F * finished / this.destinationRegion.height);
/* 1599 */             lineNo += this.isBottomUp ? -1 : 1;
/* 1600 */             l = 0;
/*      */             
/* 1602 */             if (abortRequested()) {
/*      */               break;
/*      */             }
/*      */ 
/*      */             
/* 1607 */             if ((values[count - 1] & 0xFF) == 1) {
/* 1608 */               flag = true;
/*      */             }
/*      */             break;
/*      */           
/*      */           case 2:
/* 1613 */             xoff = values[count++] & 0xFF;
/* 1614 */             yoff = values[count] & 0xFF;
/*      */             
/* 1616 */             l += xoff + yoff * this.width;
/*      */             break;
/*      */           
/*      */           default:
/* 1620 */             end = values[count - 1] & 0xFF;
/* 1621 */             for (i = 0; i < end; i++) {
/* 1622 */               val[l++] = (byte)(((i & 0x1) == 0) ? ((values[count] & 0xF0) >> 4) : (values[count++] & 0xF));
/*      */             }
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1628 */             if ((end & 0x1) == 1) {
/* 1629 */               count++;
/*      */             }
/*      */ 
/*      */ 
/*      */             
/* 1634 */             if (((int)Math.ceil((end / 2)) & 0x1) == 1) {
/* 1635 */               count++;
/*      */             }
/*      */             break;
/*      */         } 
/*      */       
/*      */       } else {
/* 1641 */         int[] alternate = { (values[count] & 0xF0) >> 4, values[count] & 0xF };
/*      */         
/* 1643 */         for (int i = 0; i < value && l < this.width; i++) {
/* 1644 */           val[l++] = (byte)alternate[i & 0x1];
/*      */         }
/*      */         
/* 1647 */         count++;
/*      */       } 
/*      */ 
/*      */       
/* 1651 */       if (flag) {
/*      */         break;
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
/*      */ 
/*      */   
/*      */   private BufferedImage readEmbedded(int type, BufferedImage bi, ImageReadParam bmpParam) throws IOException {
/*      */     String format;
/* 1670 */     switch (type) {
/*      */       case 4:
/* 1672 */         format = "JPEG";
/*      */         break;
/*      */       case 5:
/* 1675 */         format = "PNG";
/*      */         break;
/*      */       default:
/* 1678 */         throw new IOException("Unexpected compression type: " + type);
/*      */     } 
/*      */ 
/*      */     
/* 1682 */     ImageReader reader = ImageIO.getImageReadersByFormatName(format).next();
/* 1683 */     if (reader == null) {
/* 1684 */       throw new RuntimeException(I18N.getString("BMPImageReader4") + " " + format);
/*      */     }
/*      */ 
/*      */     
/* 1688 */     byte[] buff = new byte[(int)this.imageSize];
/* 1689 */     this.iis.read(buff);
/* 1690 */     reader.setInput(ImageIO.createImageInputStream(new ByteArrayInputStream(buff)));
/* 1691 */     if (bi == null) {
/* 1692 */       ImageTypeSpecifier embType = reader.getImageTypes(0).next();
/* 1693 */       bi = embType.createBufferedImage(this.destinationRegion.x + this.destinationRegion.width, this.destinationRegion.y + this.destinationRegion.height);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1699 */     reader.addIIOReadProgressListener(new EmbeddedProgressAdapter()
/*      */         {
/*      */           public void imageProgress(ImageReader source, float percentageDone)
/*      */           {
/* 1703 */             BMPImageReader.this.processImageProgress(percentageDone);
/*      */           }
/*      */         });
/*      */     
/* 1707 */     reader.addIIOReadUpdateListener(new IIOReadUpdateListener()
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void imageUpdate(ImageReader source, BufferedImage theImage, int minX, int minY, int width, int height, int periodX, int periodY, int[] bands)
/*      */           {
/* 1715 */             BMPImageReader.this.processImageUpdate(theImage, minX, minY, width, height, periodX, periodY, bands);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void passComplete(ImageReader source, BufferedImage theImage) {
/* 1722 */             BMPImageReader.this.processPassComplete(theImage);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void passStarted(ImageReader source, BufferedImage theImage, int pass, int minPass, int maxPass, int minX, int minY, int periodX, int periodY, int[] bands) {
/* 1732 */             BMPImageReader.this.processPassStarted(theImage, pass, minPass, maxPass, minX, minY, periodX, periodY, bands);
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void thumbnailPassComplete(ImageReader source, BufferedImage thumb) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void thumbnailPassStarted(ImageReader source, BufferedImage thumb, int pass, int minPass, int maxPass, int minX, int minY, int periodX, int periodY, int[] bands) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           public void thumbnailUpdate(ImageReader source, BufferedImage theThumbnail, int minX, int minY, int width, int height, int periodX, int periodY, int[] bands) {}
/*      */         });
/* 1753 */     reader.addIIOReadWarningListener(new IIOReadWarningListener()
/*      */         {
/*      */           public void warningOccurred(ImageReader source, String warning) {
/* 1756 */             BMPImageReader.this.processWarningOccurred(warning);
/*      */           }
/*      */         });
/*      */     
/* 1760 */     ImageReadParam param = reader.getDefaultReadParam();
/* 1761 */     param.setDestination(bi);
/* 1762 */     param.setDestinationBands(bmpParam.getDestinationBands());
/* 1763 */     param.setDestinationOffset(bmpParam.getDestinationOffset());
/* 1764 */     param.setSourceBands(bmpParam.getSourceBands());
/* 1765 */     param.setSourceRegion(bmpParam.getSourceRegion());
/* 1766 */     param.setSourceSubsampling(bmpParam.getSourceXSubsampling(), bmpParam
/* 1767 */         .getSourceYSubsampling(), bmpParam
/* 1768 */         .getSubsamplingXOffset(), bmpParam
/* 1769 */         .getSubsamplingYOffset());
/* 1770 */     reader.read(0, param);
/* 1771 */     return bi;
/*      */   }
/*      */   
/*      */   private class EmbeddedProgressAdapter implements IIOReadProgressListener {
/*      */     private EmbeddedProgressAdapter() {}
/*      */     
/*      */     public void imageComplete(ImageReader src) {}
/*      */     
/*      */     public void imageProgress(ImageReader src, float percentageDone) {}
/*      */     
/*      */     public void imageStarted(ImageReader src, int imageIndex) {}
/*      */     
/*      */     public void thumbnailComplete(ImageReader src) {}
/*      */     
/*      */     public void thumbnailProgress(ImageReader src, float percentageDone) {}
/*      */     
/*      */     public void thumbnailStarted(ImageReader src, int iIdx, int tIdx) {}
/*      */     
/*      */     public void sequenceComplete(ImageReader src) {}
/*      */     
/*      */     public void sequenceStarted(ImageReader src, int minIndex) {}
/*      */     
/*      */     public void readAborted(ImageReader src) {}
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/bmp/BMPImageReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */