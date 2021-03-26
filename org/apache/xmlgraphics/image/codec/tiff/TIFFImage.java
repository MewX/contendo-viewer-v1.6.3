/*      */ package org.apache.xmlgraphics.image.codec.tiff;
/*      */ 
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentColorModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferInt;
/*      */ import java.awt.image.DataBufferShort;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.IndexColorModel;
/*      */ import java.awt.image.MultiPixelPackedSampleModel;
/*      */ import java.awt.image.PixelInterleavedSampleModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.io.IOException;
/*      */ import java.util.HashMap;
/*      */ import java.util.Map;
/*      */ import java.util.zip.DataFormatException;
/*      */ import java.util.zip.Inflater;
/*      */ import org.apache.xmlgraphics.image.codec.util.PropertyUtil;
/*      */ import org.apache.xmlgraphics.image.codec.util.SeekableStream;
/*      */ import org.apache.xmlgraphics.image.rendered.AbstractRed;
/*      */ import org.apache.xmlgraphics.image.rendered.CachableRed;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TIFFImage
/*      */   extends AbstractRed
/*      */ {
/*      */   public static final int COMP_NONE = 1;
/*      */   public static final int COMP_FAX_G3_1D = 2;
/*      */   public static final int COMP_FAX_G3_2D = 3;
/*      */   public static final int COMP_FAX_G4_2D = 4;
/*      */   public static final int COMP_LZW = 5;
/*      */   public static final int COMP_JPEG_OLD = 6;
/*      */   public static final int COMP_JPEG_TTN2 = 7;
/*      */   public static final int COMP_PACKBITS = 32773;
/*      */   public static final int COMP_DEFLATE = 32946;
/*      */   private static final int TYPE_UNSUPPORTED = -1;
/*      */   private static final int TYPE_BILEVEL = 0;
/*      */   private static final int TYPE_GRAY_4BIT = 1;
/*      */   private static final int TYPE_GRAY = 2;
/*      */   private static final int TYPE_GRAY_ALPHA = 3;
/*      */   private static final int TYPE_PALETTE = 4;
/*      */   private static final int TYPE_RGB = 5;
/*      */   private static final int TYPE_RGB_ALPHA = 6;
/*      */   private static final int TYPE_YCBCR_SUB = 7;
/*      */   private static final int TYPE_GENERIC = 8;
/*      */   private static final int TIFF_JPEG_TABLES = 347;
/*      */   private static final int TIFF_YCBCR_SUBSAMPLING = 530;
/*      */   SeekableStream stream;
/*      */   int tileSize;
/*      */   int tilesX;
/*      */   int tilesY;
/*      */   long[] tileOffsets;
/*      */   long[] tileByteCounts;
/*      */   char[] colormap;
/*      */   int sampleSize;
/*      */   int compression;
/*      */   byte[] palette;
/*      */   int numBands;
/*      */   int chromaSubH;
/*      */   int chromaSubV;
/*      */   long tiffT4Options;
/*      */   long tiffT6Options;
/*      */   int fillOrder;
/*      */   int predictor;
/*      */   Inflater inflater;
/*      */   boolean isBigEndian;
/*      */   int imageType;
/*      */   boolean isWhiteZero;
/*      */   int dataType;
/*      */   boolean decodePaletteAsShorts;
/*      */   boolean tiled;
/*      */   private TIFFFaxDecoder decoder;
/*      */   private TIFFLZWDecoder lzwDecoder;
/*      */   
/*      */   private void inflate(byte[] deflated, byte[] inflated) {
/*  129 */     this.inflater.setInput(deflated);
/*      */     try {
/*  131 */       this.inflater.inflate(inflated);
/*  132 */     } catch (DataFormatException dfe) {
/*  133 */       throw new RuntimeException(PropertyUtil.getString("TIFFImage17") + ": " + dfe.getMessage());
/*      */     } 
/*      */     
/*  136 */     this.inflater.reset();
/*      */   }
/*      */ 
/*      */   
/*      */   private static SampleModel createPixelInterleavedSampleModel(int dataType, int tileWidth, int tileHeight, int bands) {
/*  141 */     int[] bandOffsets = new int[bands];
/*  142 */     for (int i = 0; i < bands; i++) {
/*  143 */       bandOffsets[i] = i;
/*      */     }
/*  145 */     return new PixelInterleavedSampleModel(dataType, tileWidth, tileHeight, bands, tileWidth * bands, bandOffsets);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private long[] getFieldAsLongs(TIFFField field) {
/*  154 */     long[] value = null;
/*      */     
/*  156 */     if (field.getType() == 3) {
/*  157 */       char[] charValue = field.getAsChars();
/*  158 */       value = new long[charValue.length];
/*  159 */       for (int i = 0; i < charValue.length; i++) {
/*  160 */         value[i] = (charValue[i] & Character.MAX_VALUE);
/*      */       }
/*  162 */     } else if (field.getType() == 4) {
/*  163 */       value = field.getAsLongs();
/*      */     } else {
/*  165 */       throw new RuntimeException(PropertyUtil.getString("TIFFImage18") + ": " + field.getType());
/*      */     } 
/*      */ 
/*      */     
/*  169 */     return value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TIFFImage(SeekableStream stream, TIFFDecodeParam param, int directory) throws IOException {
/*      */     int photometricType, tileWidth, tileHeight;
/*      */     TIFFField predictorField;
/*      */     byte[] map;
/*      */     int reverseOffsets[], i, transparency, bandOffsets[], j;
/*      */     TIFFField cfield;
/*      */     int bandLength;
/*      */     byte[] r, g, b;
/*      */     int gIndex, bIndex;
/*  186 */     this.stream = stream;
/*  187 */     if (param == null) {
/*  188 */       param = new TIFFDecodeParam();
/*      */     }
/*      */     
/*  191 */     this.decodePaletteAsShorts = param.getDecodePaletteAsShorts();
/*      */ 
/*      */     
/*  194 */     TIFFDirectory dir = (param.getIFDOffset() == null) ? new TIFFDirectory(stream, directory) : new TIFFDirectory(stream, param.getIFDOffset().longValue(), directory);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  200 */     TIFFField sfield = dir.getField(277);
/*  201 */     int samplesPerPixel = (sfield == null) ? 1 : (int)sfield.getAsLong(0);
/*      */ 
/*      */     
/*  204 */     TIFFField planarConfigurationField = dir.getField(284);
/*      */     
/*  206 */     (new char[1])[0] = '\001'; char[] planarConfiguration = (planarConfigurationField == null) ? new char[1] : planarConfigurationField.getAsChars();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  211 */     if (planarConfiguration[0] != '\001' && samplesPerPixel != 1) {
/*  212 */       throw new RuntimeException(PropertyUtil.getString("TIFFImage0"));
/*      */     }
/*      */ 
/*      */     
/*  216 */     TIFFField bitsField = dir.getField(258);
/*      */     
/*  218 */     char[] bitsPerSample = null;
/*  219 */     if (bitsField != null) {
/*  220 */       bitsPerSample = bitsField.getAsChars();
/*      */     } else {
/*  222 */       bitsPerSample = new char[] { '\001' };
/*      */ 
/*      */       
/*  225 */       for (int k = 1; k < bitsPerSample.length; k++) {
/*  226 */         if (bitsPerSample[k] != bitsPerSample[0]) {
/*  227 */           throw new RuntimeException(PropertyUtil.getString("TIFFImage1"));
/*      */         }
/*      */       } 
/*      */     } 
/*  231 */     this.sampleSize = bitsPerSample[0];
/*      */ 
/*      */ 
/*      */     
/*  235 */     TIFFField sampleFormatField = dir.getField(339);
/*      */ 
/*      */     
/*  238 */     char[] sampleFormat = null;
/*  239 */     if (sampleFormatField != null) {
/*  240 */       sampleFormat = sampleFormatField.getAsChars();
/*      */ 
/*      */       
/*  243 */       for (int l = 1; l < sampleFormat.length; l++) {
/*  244 */         if (sampleFormat[l] != sampleFormat[0]) {
/*  245 */           throw new RuntimeException(PropertyUtil.getString("TIFFImage2"));
/*      */         }
/*      */       } 
/*      */     } else {
/*      */       
/*  250 */       sampleFormat = new char[] { '\001' };
/*      */     } 
/*      */ 
/*      */     
/*  254 */     boolean isValidDataFormat = false;
/*  255 */     switch (this.sampleSize) {
/*      */       case 1:
/*      */       case 4:
/*      */       case 8:
/*  259 */         if (sampleFormat[0] != '\003') {
/*      */           
/*  261 */           this.dataType = 0;
/*  262 */           isValidDataFormat = true;
/*      */         } 
/*      */         break;
/*      */       case 16:
/*  266 */         if (sampleFormat[0] != '\003') {
/*  267 */           this.dataType = (sampleFormat[0] == '\002') ? 2 : 1;
/*      */           
/*  269 */           isValidDataFormat = true;
/*      */         } 
/*      */         break;
/*      */       case 32:
/*  273 */         if (sampleFormat[0] == '\003') {
/*  274 */           isValidDataFormat = false; break;
/*      */         } 
/*  276 */         this.dataType = 3;
/*  277 */         isValidDataFormat = true;
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  282 */     if (!isValidDataFormat) {
/*  283 */       throw new RuntimeException(PropertyUtil.getString("TIFFImage3"));
/*      */     }
/*      */ 
/*      */     
/*  287 */     TIFFField compField = dir.getField(259);
/*  288 */     this.compression = (compField == null) ? 1 : compField.getAsInt(0);
/*      */ 
/*      */ 
/*      */     
/*  292 */     TIFFField photometricTypeField = dir.getField(262);
/*      */     
/*  294 */     if (photometricTypeField == null) {
/*  295 */       photometricType = 0;
/*      */     } else {
/*  297 */       photometricType = photometricTypeField.getAsInt(0);
/*      */     } 
/*      */ 
/*      */     
/*  301 */     this.imageType = -1;
/*  302 */     switch (photometricType) {
/*      */       case 0:
/*  304 */         this.isWhiteZero = true;
/*      */       case 1:
/*  306 */         if (this.sampleSize == 1 && samplesPerPixel == 1) {
/*  307 */           this.imageType = 0; break;
/*  308 */         }  if (this.sampleSize == 4 && samplesPerPixel == 1) {
/*  309 */           this.imageType = 1; break;
/*  310 */         }  if (this.sampleSize % 8 == 0) {
/*  311 */           if (samplesPerPixel == 1) {
/*  312 */             this.imageType = 2; break;
/*  313 */           }  if (samplesPerPixel == 2) {
/*  314 */             this.imageType = 3; break;
/*      */           } 
/*  316 */           this.imageType = 8;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 2:
/*  321 */         if (this.sampleSize % 8 == 0) {
/*  322 */           if (samplesPerPixel == 3) {
/*  323 */             this.imageType = 5; break;
/*  324 */           }  if (samplesPerPixel == 4) {
/*  325 */             this.imageType = 6; break;
/*      */           } 
/*  327 */           this.imageType = 8;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 3:
/*  332 */         if (samplesPerPixel == 1 && (this.sampleSize == 4 || this.sampleSize == 8 || this.sampleSize == 16))
/*      */         {
/*  334 */           this.imageType = 4;
/*      */         }
/*      */         break;
/*      */       case 4:
/*  338 */         if (this.sampleSize == 1 && samplesPerPixel == 1) {
/*  339 */           this.imageType = 0;
/*      */         }
/*      */         break;
/*      */       default:
/*  343 */         if (this.sampleSize % 8 == 0) {
/*  344 */           this.imageType = 8;
/*      */         }
/*      */         break;
/*      */     } 
/*      */     
/*  349 */     if (this.imageType == -1) {
/*  350 */       throw new RuntimeException(PropertyUtil.getString("TIFFImage4") + ": " + this.imageType);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  355 */     Rectangle bounds = new Rectangle(0, 0, (int)dir.getFieldAsLong(256), (int)dir.getFieldAsLong(257));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  361 */     this.numBands = samplesPerPixel;
/*      */ 
/*      */     
/*  364 */     TIFFField efield = dir.getField(338);
/*  365 */     int extraSamples = (efield == null) ? 0 : (int)efield.getAsLong(0);
/*      */ 
/*      */ 
/*      */     
/*  369 */     if (dir.getField(324) != null) {
/*  370 */       this.tiled = true;
/*      */       
/*  372 */       tileWidth = (int)dir.getFieldAsLong(322);
/*      */       
/*  374 */       tileHeight = (int)dir.getFieldAsLong(323);
/*      */       
/*  376 */       this.tileOffsets = dir.getField(324).getAsLongs();
/*      */       
/*  378 */       this.tileByteCounts = getFieldAsLongs(dir.getField(325));
/*      */     }
/*      */     else {
/*      */       
/*  382 */       this.tiled = false;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  389 */       tileWidth = (dir.getField(322) != null) ? (int)dir.getFieldAsLong(322) : bounds.width;
/*      */ 
/*      */ 
/*      */       
/*  393 */       TIFFField field = dir.getField(278);
/*      */       
/*  395 */       if (field == null) {
/*      */ 
/*      */         
/*  398 */         tileHeight = (dir.getField(323) != null) ? (int)dir.getFieldAsLong(323) : bounds.height;
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  403 */         long l = field.getAsLong(0);
/*  404 */         long infinity = 1L;
/*  405 */         infinity = (infinity << 32L) - 1L;
/*  406 */         if (l == infinity) {
/*      */           
/*  408 */           tileHeight = bounds.height;
/*      */         } else {
/*  410 */           tileHeight = (int)l;
/*      */         } 
/*      */       } 
/*      */       
/*  414 */       TIFFField tileOffsetsField = dir.getField(273);
/*      */       
/*  416 */       if (tileOffsetsField == null) {
/*  417 */         throw new RuntimeException(PropertyUtil.getString("TIFFImage5"));
/*      */       }
/*  419 */       this.tileOffsets = getFieldAsLongs(tileOffsetsField);
/*      */ 
/*      */       
/*  422 */       TIFFField tileByteCountsField = dir.getField(279);
/*      */       
/*  424 */       if (tileByteCountsField == null) {
/*  425 */         throw new RuntimeException(PropertyUtil.getString("TIFFImage6"));
/*      */       }
/*  427 */       this.tileByteCounts = getFieldAsLongs(tileByteCountsField);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  432 */     this.tilesX = (bounds.width + tileWidth - 1) / tileWidth;
/*  433 */     this.tilesY = (bounds.height + tileHeight - 1) / tileHeight;
/*  434 */     this.tileSize = tileWidth * tileHeight * this.numBands;
/*      */ 
/*      */     
/*  437 */     this.isBigEndian = dir.isBigEndian();
/*      */     
/*  439 */     TIFFField fillOrderField = dir.getField(266);
/*      */     
/*  441 */     if (fillOrderField != null) {
/*  442 */       this.fillOrder = fillOrderField.getAsInt(0);
/*      */     } else {
/*      */       
/*  445 */       this.fillOrder = 1;
/*      */     } 
/*      */     
/*  448 */     switch (this.compression) {
/*      */       case 1:
/*      */       case 32773:
/*      */         break;
/*      */       
/*      */       case 32946:
/*  454 */         this.inflater = new Inflater();
/*      */         break;
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*  459 */         if (this.sampleSize != 1) {
/*  460 */           throw new RuntimeException(PropertyUtil.getString("TIFFImage7"));
/*      */         }
/*      */ 
/*      */         
/*  464 */         if (this.compression == 3) {
/*  465 */           TIFFField t4OptionsField = dir.getField(292);
/*      */           
/*  467 */           if (t4OptionsField != null) {
/*  468 */             this.tiffT4Options = t4OptionsField.getAsLong(0);
/*      */           } else {
/*      */             
/*  471 */             this.tiffT4Options = 0L;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  476 */         if (this.compression == 4) {
/*  477 */           TIFFField t6OptionsField = dir.getField(293);
/*      */           
/*  479 */           if (t6OptionsField != null) {
/*  480 */             this.tiffT6Options = t6OptionsField.getAsLong(0);
/*      */           } else {
/*      */             
/*  483 */             this.tiffT6Options = 0L;
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  488 */         this.decoder = new TIFFFaxDecoder(this.fillOrder, tileWidth, tileHeight);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 5:
/*  494 */         predictorField = dir.getField(317);
/*      */ 
/*      */         
/*  497 */         if (predictorField == null) {
/*  498 */           this.predictor = 1;
/*      */         } else {
/*  500 */           this.predictor = predictorField.getAsInt(0);
/*      */           
/*  502 */           if (this.predictor != 1 && this.predictor != 2) {
/*  503 */             throw new RuntimeException(PropertyUtil.getString("TIFFImage8"));
/*      */           }
/*      */           
/*  506 */           if (this.predictor == 2 && this.sampleSize != 8) {
/*  507 */             throw new RuntimeException(PropertyUtil.getString("TIFFImage9"));
/*      */           }
/*      */         } 
/*      */         
/*  511 */         this.lzwDecoder = new TIFFLZWDecoder(tileWidth, this.predictor, samplesPerPixel);
/*      */         break;
/*      */ 
/*      */       
/*      */       case 6:
/*  516 */         throw new RuntimeException(PropertyUtil.getString("TIFFImage15"));
/*      */       
/*      */       default:
/*  519 */         throw new RuntimeException(PropertyUtil.getString("TIFFImage10") + ": " + this.compression);
/*      */     } 
/*      */ 
/*      */     
/*  523 */     ColorModel colorModel = null;
/*  524 */     SampleModel sampleModel = null;
/*  525 */     switch (this.imageType) {
/*      */       case 0:
/*      */       case 1:
/*  528 */         sampleModel = new MultiPixelPackedSampleModel(this.dataType, tileWidth, tileHeight, this.sampleSize);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  533 */         if (this.imageType == 0) {
/*  534 */           byte[] arrayOfByte = { (byte)(this.isWhiteZero ? 255 : 0), (byte)(this.isWhiteZero ? 0 : 255) };
/*      */           
/*  536 */           colorModel = new IndexColorModel(1, 2, arrayOfByte, arrayOfByte, arrayOfByte); break;
/*      */         } 
/*  538 */         map = new byte[16];
/*  539 */         if (this.isWhiteZero) {
/*  540 */           for (int k = 0; k < map.length; k++) {
/*  541 */             map[k] = (byte)(255 - 16 * k);
/*      */           }
/*      */         } else {
/*  544 */           for (int k = 0; k < map.length; k++) {
/*  545 */             map[k] = (byte)(16 * k);
/*      */           }
/*      */         } 
/*  548 */         colorModel = new IndexColorModel(4, 16, map, map, map);
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 2:
/*      */       case 3:
/*      */       case 5:
/*      */       case 6:
/*  558 */         reverseOffsets = new int[this.numBands];
/*  559 */         for (i = 0; i < this.numBands; i++) {
/*  560 */           reverseOffsets[i] = this.numBands - 1 - i;
/*      */         }
/*  562 */         sampleModel = new PixelInterleavedSampleModel(this.dataType, tileWidth, tileHeight, this.numBands, this.numBands * tileWidth, reverseOffsets);
/*      */ 
/*      */ 
/*      */         
/*  566 */         if (this.imageType == 2) {
/*  567 */           colorModel = new ComponentColorModel(ColorSpace.getInstance(1003), new int[] { this.sampleSize }, false, false, 1, this.dataType);
/*      */           
/*      */           break;
/*      */         } 
/*  571 */         if (this.imageType == 5) {
/*  572 */           colorModel = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { this.sampleSize, this.sampleSize, this.sampleSize }, false, false, 1, this.dataType);
/*      */ 
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  581 */         transparency = 1;
/*  582 */         if (extraSamples == 1) {
/*  583 */           transparency = 3;
/*  584 */         } else if (extraSamples == 2) {
/*  585 */           transparency = 2;
/*      */         } 
/*      */         
/*  588 */         colorModel = createAlphaComponentColorModel(this.dataType, this.numBands, (extraSamples == 1), transparency);
/*      */         break;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       case 7:
/*      */       case 8:
/*  602 */         bandOffsets = new int[this.numBands];
/*  603 */         for (j = 0; j < this.numBands; j++) {
/*  604 */           bandOffsets[j] = j;
/*      */         }
/*      */         
/*  607 */         sampleModel = new PixelInterleavedSampleModel(this.dataType, tileWidth, tileHeight, this.numBands, this.numBands * tileWidth, bandOffsets);
/*      */ 
/*      */         
/*  610 */         colorModel = null;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 4:
/*  615 */         cfield = dir.getField(320);
/*  616 */         if (cfield == null) {
/*  617 */           throw new RuntimeException(PropertyUtil.getString("TIFFImage11"));
/*      */         }
/*  619 */         this.colormap = cfield.getAsChars();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  624 */         if (this.decodePaletteAsShorts) {
/*  625 */           this.numBands = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  632 */           if (this.dataType == 0) {
/*  633 */             this.dataType = 1;
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  639 */           sampleModel = createPixelInterleavedSampleModel(this.dataType, tileWidth, tileHeight, this.numBands);
/*      */ 
/*      */           
/*  642 */           colorModel = new ComponentColorModel(ColorSpace.getInstance(1000), new int[] { 16, 16, 16 }, false, false, 1, this.dataType);
/*      */ 
/*      */           
/*      */           break;
/*      */         } 
/*      */ 
/*      */         
/*  649 */         this.numBands = 1;
/*      */         
/*  651 */         if (this.sampleSize == 4) {
/*      */ 
/*      */ 
/*      */           
/*  655 */           sampleModel = new MultiPixelPackedSampleModel(0, tileWidth, tileHeight, this.sampleSize);
/*      */         
/*      */         }
/*  658 */         else if (this.sampleSize == 8) {
/*      */           
/*  660 */           sampleModel = createPixelInterleavedSampleModel(0, tileWidth, tileHeight, this.numBands);
/*      */         
/*      */         }
/*  663 */         else if (this.sampleSize == 16) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  670 */           this.dataType = 1;
/*  671 */           sampleModel = createPixelInterleavedSampleModel(1, tileWidth, tileHeight, this.numBands);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  676 */         bandLength = this.colormap.length / 3;
/*  677 */         r = new byte[bandLength];
/*  678 */         g = new byte[bandLength];
/*  679 */         b = new byte[bandLength];
/*      */         
/*  681 */         gIndex = bandLength;
/*  682 */         bIndex = bandLength * 2;
/*      */         
/*  684 */         if (this.dataType == 2) {
/*      */           
/*  686 */           for (int k = 0; k < bandLength; k++) {
/*  687 */             r[k] = param.decodeSigned16BitsTo8Bits((short)this.colormap[k]);
/*      */             
/*  689 */             g[k] = param.decodeSigned16BitsTo8Bits((short)this.colormap[gIndex + k]);
/*      */             
/*  691 */             b[k] = param.decodeSigned16BitsTo8Bits((short)this.colormap[bIndex + k]);
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/*  697 */           for (int k = 0; k < bandLength; k++) {
/*  698 */             r[k] = param.decode16BitsTo8Bits(this.colormap[k] & Character.MAX_VALUE);
/*      */             
/*  700 */             g[k] = param.decode16BitsTo8Bits(this.colormap[gIndex + k] & Character.MAX_VALUE);
/*      */             
/*  702 */             b[k] = param.decode16BitsTo8Bits(this.colormap[bIndex + k] & Character.MAX_VALUE);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  708 */         colorModel = new IndexColorModel(this.sampleSize, bandLength, r, g, b);
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       default:
/*  714 */         throw new RuntimeException(PropertyUtil.getString("TIFFImage4") + ": " + this.imageType);
/*      */     } 
/*      */ 
/*      */     
/*  718 */     Map<Object, Object> properties = new HashMap<Object, Object>();
/*      */     
/*  720 */     properties.put("tiff_directory", dir);
/*      */ 
/*      */ 
/*      */     
/*  724 */     init((CachableRed)null, bounds, colorModel, sampleModel, 0, 0, properties);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TIFFDirectory getPrivateIFD(long offset) throws IOException {
/*  734 */     return new TIFFDirectory(this.stream, offset, 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public WritableRaster copyData(WritableRaster wr) {
/*  739 */     copyToRaster(wr);
/*  740 */     return wr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized Raster getTile(int tileX, int tileY) {
/*      */     Rectangle newRect;
/*  748 */     if (tileX < 0 || tileX >= this.tilesX || tileY < 0 || tileY >= this.tilesY)
/*      */     {
/*  750 */       throw new IllegalArgumentException(PropertyUtil.getString("TIFFImage12"));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  757 */     byte[] bdata = null;
/*  758 */     short[] sdata = null;
/*  759 */     int[] idata = null;
/*      */     
/*  761 */     SampleModel sampleModel = getSampleModel();
/*  762 */     WritableRaster tile = makeTile(tileX, tileY);
/*      */     
/*  764 */     DataBuffer buffer = tile.getDataBuffer();
/*      */     
/*  766 */     int dataType = sampleModel.getDataType();
/*  767 */     if (dataType == 0) {
/*  768 */       bdata = ((DataBufferByte)buffer).getData();
/*  769 */     } else if (dataType == 1) {
/*  770 */       sdata = ((DataBufferUShort)buffer).getData();
/*  771 */     } else if (dataType == 2) {
/*  772 */       sdata = ((DataBufferShort)buffer).getData();
/*  773 */     } else if (dataType == 3) {
/*  774 */       idata = ((DataBufferInt)buffer).getData();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  783 */     long saveOffset = 0L;
/*      */     try {
/*  785 */       saveOffset = this.stream.getFilePointer();
/*  786 */       this.stream.seek(this.tileOffsets[tileY * this.tilesX + tileX]);
/*  787 */     } catch (IOException ioe) {
/*  788 */       throw new RuntimeException(PropertyUtil.getString("TIFFImage13") + ": " + ioe.getMessage());
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  793 */     int byteCount = (int)this.tileByteCounts[tileY * this.tilesX + tileX];
/*      */ 
/*      */ 
/*      */     
/*  797 */     if (!this.tiled) {
/*  798 */       newRect = tile.getBounds();
/*      */     } else {
/*  800 */       newRect = new Rectangle(tile.getMinX(), tile.getMinY(), this.tileWidth, this.tileHeight);
/*      */     } 
/*      */ 
/*      */     
/*  804 */     int unitsInThisTile = newRect.width * newRect.height * this.numBands;
/*      */ 
/*      */     
/*  807 */     byte[] data = (this.compression != 1 || this.imageType == 4) ? new byte[byteCount] : null;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  812 */     if (this.imageType == 0) {
/*      */       try {
/*  814 */         if (this.compression == 32773) {
/*  815 */           int bytesInThisTile; this.stream.readFully(data, 0, byteCount);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  820 */           if (newRect.width % 8 == 0) {
/*  821 */             bytesInThisTile = newRect.width / 8 * newRect.height;
/*      */           } else {
/*  823 */             bytesInThisTile = (newRect.width / 8 + 1) * newRect.height;
/*      */           } 
/*      */           
/*  826 */           decodePackbits(data, bytesInThisTile, bdata);
/*  827 */         } else if (this.compression == 5) {
/*  828 */           this.stream.readFully(data, 0, byteCount);
/*  829 */           this.lzwDecoder.decode(data, bdata, newRect.height);
/*  830 */         } else if (this.compression == 2) {
/*  831 */           this.stream.readFully(data, 0, byteCount);
/*  832 */           this.decoder.decode1D(bdata, data, 0, newRect.height);
/*  833 */         } else if (this.compression == 3) {
/*  834 */           this.stream.readFully(data, 0, byteCount);
/*  835 */           this.decoder.decode2D(bdata, data, 0, newRect.height, this.tiffT4Options);
/*      */         }
/*  837 */         else if (this.compression == 4) {
/*  838 */           this.stream.readFully(data, 0, byteCount);
/*  839 */           this.decoder.decodeT6(bdata, data, 0, newRect.height, this.tiffT6Options);
/*      */         }
/*  841 */         else if (this.compression == 32946) {
/*  842 */           this.stream.readFully(data, 0, byteCount);
/*  843 */           inflate(data, bdata);
/*  844 */         } else if (this.compression == 1) {
/*  845 */           this.stream.readFully(bdata, 0, byteCount);
/*      */         } 
/*      */         
/*  848 */         this.stream.seek(saveOffset);
/*  849 */       } catch (IOException ioe) {
/*  850 */         throw new RuntimeException(PropertyUtil.getString("TIFFImage13") + ": " + ioe.getMessage());
/*      */       }
/*      */     
/*  853 */     } else if (this.imageType == 4) {
/*  854 */       if (this.sampleSize == 16) {
/*      */         
/*  856 */         if (this.decodePaletteAsShorts) {
/*      */           
/*  858 */           short[] tempData = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  864 */           int unitsBeforeLookup = unitsInThisTile / 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  870 */           int entries = unitsBeforeLookup * 2;
/*      */ 
/*      */ 
/*      */           
/*      */           try {
/*  875 */             if (this.compression == 32773) {
/*      */               
/*  877 */               this.stream.readFully(data, 0, byteCount);
/*      */               
/*  879 */               byte[] byteArray = new byte[entries];
/*  880 */               decodePackbits(data, entries, byteArray);
/*  881 */               tempData = new short[unitsBeforeLookup];
/*  882 */               interpretBytesAsShorts(byteArray, tempData, unitsBeforeLookup);
/*      */             
/*      */             }
/*  885 */             else if (this.compression == 5) {
/*      */ 
/*      */               
/*  888 */               this.stream.readFully(data, 0, byteCount);
/*      */               
/*  890 */               byte[] byteArray = new byte[entries];
/*  891 */               this.lzwDecoder.decode(data, byteArray, newRect.height);
/*  892 */               tempData = new short[unitsBeforeLookup];
/*  893 */               interpretBytesAsShorts(byteArray, tempData, unitsBeforeLookup);
/*      */             
/*      */             }
/*  896 */             else if (this.compression == 32946) {
/*      */               
/*  898 */               this.stream.readFully(data, 0, byteCount);
/*  899 */               byte[] byteArray = new byte[entries];
/*  900 */               inflate(data, byteArray);
/*  901 */               tempData = new short[unitsBeforeLookup];
/*  902 */               interpretBytesAsShorts(byteArray, tempData, unitsBeforeLookup);
/*      */             
/*      */             }
/*  905 */             else if (this.compression == 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  911 */               tempData = new short[byteCount / 2];
/*  912 */               readShorts(byteCount / 2, tempData);
/*      */             } 
/*      */             
/*  915 */             this.stream.seek(saveOffset);
/*      */           }
/*  917 */           catch (IOException ioe) {
/*  918 */             throw new RuntimeException(PropertyUtil.getString("TIFFImage13") + ": " + ioe.getMessage());
/*      */           } 
/*      */ 
/*      */           
/*  922 */           if (dataType == 1) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  927 */             int count = 0;
/*      */             
/*  929 */             int len = this.colormap.length / 3;
/*  930 */             int len2 = len * 2;
/*  931 */             for (int i = 0; i < unitsBeforeLookup; i++)
/*      */             {
/*  933 */               int lookup = tempData[i] & 0xFFFF;
/*      */               
/*  935 */               int cmapValue = this.colormap[lookup + len2];
/*  936 */               sdata[count++] = (short)(cmapValue & 0xFFFF);
/*      */               
/*  938 */               cmapValue = this.colormap[lookup + len];
/*  939 */               sdata[count++] = (short)(cmapValue & 0xFFFF);
/*      */               
/*  941 */               cmapValue = this.colormap[lookup];
/*  942 */               sdata[count++] = (short)(cmapValue & 0xFFFF);
/*      */             }
/*      */           
/*  945 */           } else if (dataType == 2) {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  950 */             int count = 0;
/*      */             
/*  952 */             int len = this.colormap.length / 3;
/*  953 */             int len2 = len * 2;
/*  954 */             for (int i = 0; i < unitsBeforeLookup; i++) {
/*      */               
/*  956 */               int lookup = tempData[i] & 0xFFFF;
/*      */               
/*  958 */               int cmapValue = this.colormap[lookup + len2];
/*  959 */               sdata[count++] = (short)cmapValue;
/*      */               
/*  961 */               cmapValue = this.colormap[lookup + len];
/*  962 */               sdata[count++] = (short)cmapValue;
/*      */               
/*  964 */               cmapValue = this.colormap[lookup];
/*  965 */               sdata[count++] = (short)cmapValue;
/*      */             } 
/*      */           } 
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  976 */             if (this.compression == 32773) {
/*      */               
/*  978 */               this.stream.readFully(data, 0, byteCount);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  985 */               int bytesInThisTile = unitsInThisTile * 2;
/*      */               
/*  987 */               byte[] byteArray = new byte[bytesInThisTile];
/*  988 */               decodePackbits(data, bytesInThisTile, byteArray);
/*  989 */               interpretBytesAsShorts(byteArray, sdata, unitsInThisTile);
/*      */             
/*      */             }
/*  992 */             else if (this.compression == 5) {
/*      */               
/*  994 */               this.stream.readFully(data, 0, byteCount);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/* 1001 */               byte[] byteArray = new byte[unitsInThisTile * 2];
/* 1002 */               this.lzwDecoder.decode(data, byteArray, newRect.height);
/* 1003 */               interpretBytesAsShorts(byteArray, sdata, unitsInThisTile);
/*      */             
/*      */             }
/* 1006 */             else if (this.compression == 32946) {
/*      */               
/* 1008 */               this.stream.readFully(data, 0, byteCount);
/* 1009 */               byte[] byteArray = new byte[unitsInThisTile * 2];
/* 1010 */               inflate(data, byteArray);
/* 1011 */               interpretBytesAsShorts(byteArray, sdata, unitsInThisTile);
/*      */             
/*      */             }
/* 1014 */             else if (this.compression == 1) {
/*      */               
/* 1016 */               readShorts(byteCount / 2, sdata);
/*      */             } 
/*      */             
/* 1019 */             this.stream.seek(saveOffset);
/*      */           }
/* 1021 */           catch (IOException ioe) {
/* 1022 */             throw new RuntimeException(PropertyUtil.getString("TIFFImage13") + ": " + ioe.getMessage());
/*      */           }
/*      */         
/*      */         }
/*      */       
/* 1027 */       } else if (this.sampleSize == 8) {
/*      */         
/* 1029 */         if (this.decodePaletteAsShorts) {
/*      */           
/* 1031 */           byte[] tempData = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1037 */           int unitsBeforeLookup = unitsInThisTile / 3;
/*      */ 
/*      */ 
/*      */           
/*      */           try {
/* 1042 */             if (this.compression == 32773) {
/*      */               
/* 1044 */               this.stream.readFully(data, 0, byteCount);
/* 1045 */               tempData = new byte[unitsBeforeLookup];
/* 1046 */               decodePackbits(data, unitsBeforeLookup, tempData);
/*      */             }
/* 1048 */             else if (this.compression == 5) {
/*      */               
/* 1050 */               this.stream.readFully(data, 0, byteCount);
/* 1051 */               tempData = new byte[unitsBeforeLookup];
/* 1052 */               this.lzwDecoder.decode(data, tempData, newRect.height);
/*      */             }
/* 1054 */             else if (this.compression == 32946) {
/*      */               
/* 1056 */               this.stream.readFully(data, 0, byteCount);
/* 1057 */               tempData = new byte[unitsBeforeLookup];
/* 1058 */               inflate(data, tempData);
/*      */             }
/* 1060 */             else if (this.compression == 1) {
/*      */               
/* 1062 */               tempData = new byte[byteCount];
/* 1063 */               this.stream.readFully(tempData, 0, byteCount);
/*      */             } else {
/* 1065 */               throw new RuntimeException(PropertyUtil.getString("IFFImage10") + ": " + this.compression);
/*      */             } 
/*      */ 
/*      */             
/* 1069 */             this.stream.seek(saveOffset);
/*      */           }
/* 1071 */           catch (IOException ioe) {
/* 1072 */             throw new RuntimeException(PropertyUtil.getString("TIFFImage13") + ": " + ioe.getMessage());
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1079 */           int count = 0;
/*      */           
/* 1081 */           int len = this.colormap.length / 3;
/* 1082 */           int len2 = len * 2;
/* 1083 */           for (int i = 0; i < unitsBeforeLookup; i++) {
/*      */             
/* 1085 */             int lookup = tempData[i] & 0xFF;
/*      */             
/* 1087 */             int cmapValue = this.colormap[lookup + len2];
/* 1088 */             sdata[count++] = (short)(cmapValue & 0xFFFF);
/*      */             
/* 1090 */             cmapValue = this.colormap[lookup + len];
/* 1091 */             sdata[count++] = (short)(cmapValue & 0xFFFF);
/*      */             
/* 1093 */             cmapValue = this.colormap[lookup];
/* 1094 */             sdata[count++] = (short)(cmapValue & 0xFFFF);
/*      */           } 
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/*      */ 
/*      */ 
/*      */             
/* 1103 */             if (this.compression == 32773) {
/*      */               
/* 1105 */               this.stream.readFully(data, 0, byteCount);
/* 1106 */               decodePackbits(data, unitsInThisTile, bdata);
/*      */             }
/* 1108 */             else if (this.compression == 5) {
/*      */               
/* 1110 */               this.stream.readFully(data, 0, byteCount);
/* 1111 */               this.lzwDecoder.decode(data, bdata, newRect.height);
/*      */             }
/* 1113 */             else if (this.compression == 32946) {
/*      */               
/* 1115 */               this.stream.readFully(data, 0, byteCount);
/* 1116 */               inflate(data, bdata);
/*      */             }
/* 1118 */             else if (this.compression == 1) {
/*      */               
/* 1120 */               this.stream.readFully(bdata, 0, byteCount);
/*      */             } else {
/*      */               
/* 1123 */               throw new RuntimeException(PropertyUtil.getString("TIFFImage10") + ": " + this.compression);
/*      */             } 
/*      */ 
/*      */             
/* 1127 */             this.stream.seek(saveOffset);
/*      */           }
/* 1129 */           catch (IOException ioe) {
/* 1130 */             throw new RuntimeException(PropertyUtil.getString("TIFFImage13") + ": " + ioe.getMessage());
/*      */           }
/*      */         
/*      */         }
/*      */       
/* 1135 */       } else if (this.sampleSize == 4) {
/*      */         
/* 1137 */         int padding = (newRect.width % 2 == 0) ? 0 : 1;
/* 1138 */         int bytesPostDecoding = (newRect.width / 2 + padding) * newRect.height;
/*      */ 
/*      */         
/* 1141 */         if (this.decodePaletteAsShorts) {
/*      */           
/* 1143 */           byte[] tempData = null;
/*      */           
/*      */           try {
/* 1146 */             this.stream.readFully(data, 0, byteCount);
/* 1147 */             this.stream.seek(saveOffset);
/* 1148 */           } catch (IOException ioe) {
/* 1149 */             throw new RuntimeException(PropertyUtil.getString("TIFFImage13") + ": " + ioe.getMessage());
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1154 */           if (this.compression == 32773) {
/*      */             
/* 1156 */             tempData = new byte[bytesPostDecoding];
/* 1157 */             decodePackbits(data, bytesPostDecoding, tempData);
/*      */           }
/* 1159 */           else if (this.compression == 5) {
/*      */             
/* 1161 */             tempData = new byte[bytesPostDecoding];
/* 1162 */             this.lzwDecoder.decode(data, tempData, newRect.height);
/*      */           }
/* 1164 */           else if (this.compression == 32946) {
/*      */             
/* 1166 */             tempData = new byte[bytesPostDecoding];
/* 1167 */             inflate(data, tempData);
/*      */           }
/* 1169 */           else if (this.compression == 1) {
/*      */             
/* 1171 */             tempData = data;
/*      */           } 
/*      */           
/* 1174 */           int bytes = unitsInThisTile / 3;
/*      */ 
/*      */           
/* 1177 */           data = new byte[bytes];
/*      */           
/* 1179 */           int srcCount = 0;
/* 1180 */           int dstCount = 0;
/* 1181 */           for (int j = 0; j < newRect.height; j++) {
/* 1182 */             for (int k = 0; k < newRect.width / 2; k++) {
/* 1183 */               data[dstCount++] = (byte)((tempData[srcCount] & 0xF0) >> 4);
/*      */               
/* 1185 */               data[dstCount++] = (byte)(tempData[srcCount++] & 0xF);
/*      */             } 
/*      */ 
/*      */             
/* 1189 */             if (padding == 1) {
/* 1190 */               data[dstCount++] = (byte)((tempData[srcCount++] & 0xF0) >> 4);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/* 1195 */           int len = this.colormap.length / 3;
/* 1196 */           int len2 = len * 2;
/*      */ 
/*      */           
/* 1199 */           int count = 0;
/* 1200 */           for (int i = 0; i < bytes; i++) {
/* 1201 */             int lookup = data[i] & 0xFF;
/* 1202 */             int cmapValue = this.colormap[lookup + len2];
/* 1203 */             sdata[count++] = (short)(cmapValue & 0xFFFF);
/* 1204 */             cmapValue = this.colormap[lookup + len];
/* 1205 */             sdata[count++] = (short)(cmapValue & 0xFFFF);
/* 1206 */             cmapValue = this.colormap[lookup];
/* 1207 */             sdata[count++] = (short)(cmapValue & 0xFFFF);
/*      */           } 
/*      */         } else {
/*      */ 
/*      */           
/*      */           try {
/*      */ 
/*      */             
/* 1215 */             if (this.compression == 32773) {
/*      */               
/* 1217 */               this.stream.readFully(data, 0, byteCount);
/* 1218 */               decodePackbits(data, bytesPostDecoding, bdata);
/*      */             }
/* 1220 */             else if (this.compression == 5) {
/*      */               
/* 1222 */               this.stream.readFully(data, 0, byteCount);
/* 1223 */               this.lzwDecoder.decode(data, bdata, newRect.height);
/*      */             }
/* 1225 */             else if (this.compression == 32946) {
/*      */               
/* 1227 */               this.stream.readFully(data, 0, byteCount);
/* 1228 */               inflate(data, bdata);
/*      */             }
/* 1230 */             else if (this.compression == 1) {
/*      */               
/* 1232 */               this.stream.readFully(bdata, 0, byteCount);
/*      */             } 
/*      */             
/* 1235 */             this.stream.seek(saveOffset);
/*      */           }
/* 1237 */           catch (IOException ioe) {
/* 1238 */             throw new RuntimeException(PropertyUtil.getString("TIFFImage13") + ": " + ioe.getMessage());
/*      */           }
/*      */         
/*      */         } 
/*      */       } 
/* 1243 */     } else if (this.imageType == 1) {
/*      */       try {
/* 1245 */         if (this.compression == 32773) {
/*      */           int bytesInThisTile;
/* 1247 */           this.stream.readFully(data, 0, byteCount);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1252 */           if (newRect.width % 8 == 0) {
/* 1253 */             bytesInThisTile = newRect.width / 2 * newRect.height;
/*      */           } else {
/* 1255 */             bytesInThisTile = (newRect.width / 2 + 1) * newRect.height;
/*      */           } 
/*      */ 
/*      */           
/* 1259 */           decodePackbits(data, bytesInThisTile, bdata);
/*      */         }
/* 1261 */         else if (this.compression == 5) {
/*      */           
/* 1263 */           this.stream.readFully(data, 0, byteCount);
/* 1264 */           this.lzwDecoder.decode(data, bdata, newRect.height);
/*      */         }
/* 1266 */         else if (this.compression == 32946) {
/*      */           
/* 1268 */           this.stream.readFully(data, 0, byteCount);
/* 1269 */           inflate(data, bdata);
/*      */         }
/*      */         else {
/*      */           
/* 1273 */           this.stream.readFully(bdata, 0, byteCount);
/*      */         } 
/*      */         
/* 1276 */         this.stream.seek(saveOffset);
/* 1277 */       } catch (IOException ioe) {
/* 1278 */         throw new RuntimeException(PropertyUtil.getString("TIFFImage13") + ": " + ioe.getMessage());
/*      */       } 
/*      */     } else {
/*      */       int pixelsPerDataUnit; int numH; int numV; byte[] tempData; int samplesPerDataUnit; int[] pixels; int bOffset; int offsetCb; int offsetCr; int y;
/*      */       int j;
/*      */       try {
/* 1284 */         if (this.sampleSize == 8) {
/*      */           
/* 1286 */           if (this.compression == 1) {
/* 1287 */             this.stream.readFully(bdata, 0, byteCount);
/*      */           }
/* 1289 */           else if (this.compression == 5) {
/*      */             
/* 1291 */             this.stream.readFully(data, 0, byteCount);
/* 1292 */             this.lzwDecoder.decode(data, bdata, newRect.height);
/*      */           }
/* 1294 */           else if (this.compression == 32773) {
/*      */             
/* 1296 */             this.stream.readFully(data, 0, byteCount);
/* 1297 */             decodePackbits(data, unitsInThisTile, bdata);
/*      */           }
/* 1299 */           else if (this.compression == 32946) {
/*      */             
/* 1301 */             this.stream.readFully(data, 0, byteCount);
/* 1302 */             inflate(data, bdata);
/*      */           } else {
/*      */             
/* 1305 */             throw new RuntimeException(PropertyUtil.getString("TIFFImage10") + ": " + this.compression);
/*      */           }
/*      */         
/*      */         }
/* 1309 */         else if (this.sampleSize == 16) {
/*      */           
/* 1311 */           if (this.compression == 1)
/*      */           {
/* 1313 */             readShorts(byteCount / 2, sdata);
/*      */           }
/* 1315 */           else if (this.compression == 5)
/*      */           {
/* 1317 */             this.stream.readFully(data, 0, byteCount);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1324 */             byte[] byteArray = new byte[unitsInThisTile * 2];
/* 1325 */             this.lzwDecoder.decode(data, byteArray, newRect.height);
/* 1326 */             interpretBytesAsShorts(byteArray, sdata, unitsInThisTile);
/*      */           
/*      */           }
/* 1329 */           else if (this.compression == 32773)
/*      */           {
/* 1331 */             this.stream.readFully(data, 0, byteCount);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1338 */             int bytesInThisTile = unitsInThisTile * 2;
/*      */             
/* 1340 */             byte[] byteArray = new byte[bytesInThisTile];
/* 1341 */             decodePackbits(data, bytesInThisTile, byteArray);
/* 1342 */             interpretBytesAsShorts(byteArray, sdata, unitsInThisTile);
/*      */           }
/* 1344 */           else if (this.compression == 32946)
/*      */           {
/* 1346 */             this.stream.readFully(data, 0, byteCount);
/* 1347 */             byte[] byteArray = new byte[unitsInThisTile * 2];
/* 1348 */             inflate(data, byteArray);
/* 1349 */             interpretBytesAsShorts(byteArray, sdata, unitsInThisTile);
/*      */           }
/*      */         
/*      */         }
/* 1353 */         else if (this.sampleSize == 32 && dataType == 3) {
/*      */           
/* 1355 */           if (this.compression == 1) {
/*      */             
/* 1357 */             readInts(byteCount / 4, idata);
/*      */           }
/* 1359 */           else if (this.compression == 5) {
/*      */             
/* 1361 */             this.stream.readFully(data, 0, byteCount);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1368 */             byte[] byteArray = new byte[unitsInThisTile * 4];
/* 1369 */             this.lzwDecoder.decode(data, byteArray, newRect.height);
/* 1370 */             interpretBytesAsInts(byteArray, idata, unitsInThisTile);
/*      */           
/*      */           }
/* 1373 */           else if (this.compression == 32773) {
/*      */             
/* 1375 */             this.stream.readFully(data, 0, byteCount);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1382 */             int bytesInThisTile = unitsInThisTile * 4;
/*      */             
/* 1384 */             byte[] byteArray = new byte[bytesInThisTile];
/* 1385 */             decodePackbits(data, bytesInThisTile, byteArray);
/* 1386 */             interpretBytesAsInts(byteArray, idata, unitsInThisTile);
/*      */           }
/* 1388 */           else if (this.compression == 32946) {
/*      */             
/* 1390 */             this.stream.readFully(data, 0, byteCount);
/* 1391 */             byte[] byteArray = new byte[unitsInThisTile * 4];
/* 1392 */             inflate(data, byteArray);
/* 1393 */             interpretBytesAsInts(byteArray, idata, unitsInThisTile);
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/* 1399 */         this.stream.seek(saveOffset);
/*      */       }
/* 1401 */       catch (IOException ioe) {
/* 1402 */         throw new RuntimeException(PropertyUtil.getString("TIFFImage13") + ": " + ioe.getMessage());
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1407 */       switch (this.imageType) {
/*      */         case 2:
/*      */         case 3:
/* 1410 */           if (this.isWhiteZero) {
/*      */ 
/*      */ 
/*      */             
/* 1414 */             if (dataType == 0 && !(getColorModel() instanceof IndexColorModel)) {
/*      */               int l;
/*      */               
/* 1417 */               for (l = 0; l < bdata.length; l += this.numBands)
/* 1418 */                 bdata[l] = (byte)(255 - bdata[l]);  break;
/*      */             } 
/* 1420 */             if (dataType == 1) {
/*      */               
/* 1422 */               int ushortMax = 65535; int l;
/* 1423 */               for (l = 0; l < sdata.length; l += this.numBands)
/* 1424 */                 sdata[l] = (short)(ushortMax - sdata[l]); 
/*      */               break;
/*      */             } 
/* 1427 */             if (dataType == 2) {
/*      */               int l;
/* 1429 */               for (l = 0; l < sdata.length; l += this.numBands)
/* 1430 */                 sdata[l] = (short)(sdata[l] ^ 0xFFFFFFFF);  break;
/*      */             } 
/* 1432 */             if (dataType == 3) {
/*      */               
/* 1434 */               long uintMax = 4294967295L;
/*      */               int l;
/* 1436 */               for (l = 0; l < idata.length; l += this.numBands) {
/* 1437 */                 idata[l] = (int)(uintMax - idata[l]);
/*      */               }
/*      */             } 
/*      */           } 
/*      */           break;
/*      */ 
/*      */         
/*      */         case 5:
/* 1445 */           if (this.sampleSize == 8 && this.compression != 7) {
/* 1446 */             for (int i = 0; i < unitsInThisTile; i += 3) {
/* 1447 */               byte bswap = bdata[i];
/* 1448 */               bdata[i] = bdata[i + 2];
/* 1449 */               bdata[i + 2] = bswap;
/*      */             }  break;
/* 1451 */           }  if (this.sampleSize == 16) {
/* 1452 */             for (int i = 0; i < unitsInThisTile; i += 3) {
/* 1453 */               short sswap = sdata[i];
/* 1454 */               sdata[i] = sdata[i + 2];
/* 1455 */               sdata[i + 2] = sswap;
/*      */             }  break;
/* 1457 */           }  if (this.sampleSize == 32 && 
/* 1458 */             dataType == 3) {
/* 1459 */             for (int i = 0; i < unitsInThisTile; i += 3) {
/* 1460 */               int iswap = idata[i];
/* 1461 */               idata[i] = idata[i + 2];
/* 1462 */               idata[i + 2] = iswap;
/*      */             } 
/*      */           }
/*      */           break;
/*      */ 
/*      */         
/*      */         case 6:
/* 1469 */           if (this.sampleSize == 8) {
/* 1470 */             for (int i = 0; i < unitsInThisTile; i += 4) {
/*      */               
/* 1472 */               byte bswap = bdata[i];
/* 1473 */               bdata[i] = bdata[i + 3];
/* 1474 */               bdata[i + 3] = bswap;
/*      */ 
/*      */               
/* 1477 */               bswap = bdata[i + 1];
/* 1478 */               bdata[i + 1] = bdata[i + 2];
/* 1479 */               bdata[i + 2] = bswap;
/*      */             }  break;
/* 1481 */           }  if (this.sampleSize == 16) {
/* 1482 */             for (int i = 0; i < unitsInThisTile; i += 4) {
/*      */               
/* 1484 */               short sswap = sdata[i];
/* 1485 */               sdata[i] = sdata[i + 3];
/* 1486 */               sdata[i + 3] = sswap;
/*      */ 
/*      */               
/* 1489 */               sswap = sdata[i + 1];
/* 1490 */               sdata[i + 1] = sdata[i + 2];
/* 1491 */               sdata[i + 2] = sswap;
/*      */             }  break;
/* 1493 */           }  if (this.sampleSize == 32 && 
/* 1494 */             dataType == 3) {
/* 1495 */             for (int i = 0; i < unitsInThisTile; i += 4) {
/*      */               
/* 1497 */               int iswap = idata[i];
/* 1498 */               idata[i] = idata[i + 3];
/* 1499 */               idata[i + 3] = iswap;
/*      */ 
/*      */               
/* 1502 */               iswap = idata[i + 1];
/* 1503 */               idata[i + 1] = idata[i + 2];
/* 1504 */               idata[i + 2] = iswap;
/*      */             } 
/*      */           }
/*      */           break;
/*      */ 
/*      */ 
/*      */         
/*      */         case 7:
/* 1512 */           pixelsPerDataUnit = this.chromaSubH * this.chromaSubV;
/*      */           
/* 1514 */           numH = newRect.width / this.chromaSubH;
/* 1515 */           numV = newRect.height / this.chromaSubV;
/*      */           
/* 1517 */           tempData = new byte[numH * numV * (pixelsPerDataUnit + 2)];
/* 1518 */           System.arraycopy(bdata, 0, tempData, 0, tempData.length);
/*      */           
/* 1520 */           samplesPerDataUnit = pixelsPerDataUnit * 3;
/* 1521 */           pixels = new int[samplesPerDataUnit];
/*      */           
/* 1523 */           bOffset = 0;
/* 1524 */           offsetCb = pixelsPerDataUnit;
/* 1525 */           offsetCr = offsetCb + 1;
/*      */           
/* 1527 */           y = newRect.y;
/* 1528 */           for (j = 0; j < numV; j++) {
/* 1529 */             int x = newRect.x;
/* 1530 */             for (int i = 0; i < numH; i++) {
/* 1531 */               int cb = tempData[bOffset + offsetCb];
/* 1532 */               int cr = tempData[bOffset + offsetCr];
/* 1533 */               int k = 0;
/* 1534 */               while (k < samplesPerDataUnit) {
/* 1535 */                 pixels[k++] = tempData[bOffset++];
/* 1536 */                 pixels[k++] = cb;
/* 1537 */                 pixels[k++] = cr;
/*      */               } 
/* 1539 */               bOffset += 2;
/* 1540 */               tile.setPixels(x, y, this.chromaSubH, this.chromaSubV, pixels);
/* 1541 */               x += this.chromaSubH;
/*      */             } 
/* 1543 */             y += this.chromaSubV;
/*      */           } 
/*      */           break;
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/* 1550 */     return tile;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readShorts(int shortCount, short[] shortArray) {
/* 1557 */     int byteCount = 2 * shortCount;
/* 1558 */     byte[] byteArray = new byte[byteCount];
/*      */     
/*      */     try {
/* 1561 */       this.stream.readFully(byteArray, 0, byteCount);
/* 1562 */     } catch (IOException ioe) {
/* 1563 */       throw new RuntimeException(PropertyUtil.getString("TIFFImage13") + ": " + ioe.getMessage());
/*      */     } 
/*      */ 
/*      */     
/* 1567 */     interpretBytesAsShorts(byteArray, shortArray, shortCount);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void readInts(int intCount, int[] intArray) {
/* 1574 */     int byteCount = 4 * intCount;
/* 1575 */     byte[] byteArray = new byte[byteCount];
/*      */     
/*      */     try {
/* 1578 */       this.stream.readFully(byteArray, 0, byteCount);
/* 1579 */     } catch (IOException ioe) {
/* 1580 */       throw new RuntimeException(PropertyUtil.getString("TIFFImage13") + ": " + ioe.getMessage());
/*      */     } 
/*      */ 
/*      */     
/* 1584 */     interpretBytesAsInts(byteArray, intArray, intCount);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void interpretBytesAsShorts(byte[] byteArray, short[] shortArray, int shortCount) {
/* 1593 */     int j = 0;
/*      */ 
/*      */ 
/*      */     
/* 1597 */     if (this.isBigEndian) {
/*      */       
/* 1599 */       for (int i = 0; i < shortCount; i++) {
/* 1600 */         int firstByte = byteArray[j++] & 0xFF;
/* 1601 */         int secondByte = byteArray[j++] & 0xFF;
/* 1602 */         shortArray[i] = (short)((firstByte << 8) + secondByte);
/*      */       }
/*      */     
/*      */     } else {
/*      */       
/* 1607 */       for (int i = 0; i < shortCount; i++) {
/* 1608 */         int firstByte = byteArray[j++] & 0xFF;
/* 1609 */         int secondByte = byteArray[j++] & 0xFF;
/* 1610 */         shortArray[i] = (short)((secondByte << 8) + firstByte);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void interpretBytesAsInts(byte[] byteArray, int[] intArray, int intCount) {
/* 1621 */     int j = 0;
/*      */     
/* 1623 */     if (this.isBigEndian) {
/*      */       
/* 1625 */       for (int i = 0; i < intCount; i++) {
/* 1626 */         intArray[i] = (byteArray[j++] & 0xFF) << 24 | (byteArray[j++] & 0xFF) << 16 | (byteArray[j++] & 0xFF) << 8 | byteArray[j++] & 0xFF;
/*      */       
/*      */       }
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1634 */       for (int i = 0; i < intCount; i++) {
/* 1635 */         intArray[i] = byteArray[j++] & 0xFF | (byteArray[j++] & 0xFF) << 8 | (byteArray[j++] & 0xFF) << 16 | (byteArray[j++] & 0xFF) << 24;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private byte[] decodePackbits(byte[] data, int arraySize, byte[] dst) {
/* 1646 */     if (dst == null) {
/* 1647 */       dst = new byte[arraySize];
/*      */     }
/*      */     
/* 1650 */     int srcCount = 0;
/* 1651 */     int dstCount = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1657 */       while (dstCount < arraySize)
/*      */       {
/* 1659 */         byte b = data[srcCount++];
/*      */         
/* 1661 */         if (b >= 0 && b <= Byte.MAX_VALUE) {
/*      */ 
/*      */           
/* 1664 */           for (int i = 0; i < b + 1; i++)
/* 1665 */             dst[dstCount++] = data[srcCount++]; 
/*      */           continue;
/*      */         } 
/* 1668 */         if (b <= -1 && b >= -127) {
/*      */ 
/*      */           
/* 1671 */           byte repeat = data[srcCount++];
/* 1672 */           for (int i = 0; i < -b + 1; i++) {
/* 1673 */             dst[dstCount++] = repeat;
/*      */           }
/*      */           
/*      */           continue;
/*      */         } 
/* 1678 */         srcCount++;
/*      */       }
/*      */     
/* 1681 */     } catch (ArrayIndexOutOfBoundsException ae) {
/* 1682 */       throw new RuntimeException(PropertyUtil.getString("TIFFImage14") + ": " + ae.getMessage());
/*      */     } 
/*      */ 
/*      */     
/* 1686 */     return dst;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private ComponentColorModel createAlphaComponentColorModel(int dataType, int numBands, boolean isAlphaPremultiplied, int transparency) {
/* 1695 */     ComponentColorModel ccm = null;
/* 1696 */     int[] rgbBits = null;
/* 1697 */     ColorSpace cs = null;
/* 1698 */     switch (numBands) {
/*      */       case 2:
/* 1700 */         cs = ColorSpace.getInstance(1003);
/*      */         break;
/*      */       case 4:
/* 1703 */         cs = ColorSpace.getInstance(1000);
/*      */         break;
/*      */       default:
/* 1706 */         throw new IllegalArgumentException(PropertyUtil.getString("TIFFImage19") + ": " + numBands);
/*      */     } 
/*      */ 
/*      */     
/* 1710 */     int componentSize = 0;
/* 1711 */     switch (dataType) {
/*      */       case 0:
/* 1713 */         componentSize = 8;
/*      */         break;
/*      */       case 1:
/*      */       case 2:
/* 1717 */         componentSize = 16;
/*      */         break;
/*      */       case 3:
/* 1720 */         componentSize = 32;
/*      */         break;
/*      */       default:
/* 1723 */         throw new IllegalArgumentException(PropertyUtil.getString("TIFFImage20") + ": " + dataType);
/*      */     } 
/*      */ 
/*      */     
/* 1727 */     rgbBits = new int[numBands];
/* 1728 */     for (int i = 0; i < numBands; i++) {
/* 1729 */       rgbBits[i] = componentSize;
/*      */     }
/*      */     
/* 1732 */     ccm = new ComponentColorModel(cs, rgbBits, true, isAlphaPremultiplied, transparency, dataType);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1740 */     return ccm;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/tiff/TIFFImage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */