/*      */ package org.apache.xmlgraphics.image.codec.png;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Point;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentColorModel;
/*      */ import java.awt.image.DataBuffer;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.DataBufferUShort;
/*      */ import java.awt.image.IndexColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.DataInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.SequenceInputStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.TimeZone;
/*      */ import java.util.zip.Inflater;
/*      */ import java.util.zip.InflaterInputStream;
/*      */ import org.apache.xmlgraphics.image.codec.util.PropertyUtil;
/*      */ import org.apache.xmlgraphics.image.codec.util.SimpleRenderedImage;
/*      */ import org.apache.xmlgraphics.image.loader.impl.PNGConstants;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ class PNGImage
/*      */   extends SimpleRenderedImage
/*      */   implements PNGConstants
/*      */ {
/*  119 */   private static final String[] colorTypeNames = new String[] { "Grayscale", "Error", "Truecolor", "Index", "Grayscale with alpha", "Error", "Truecolor with alpha" };
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  124 */   private int[][] bandOffsets = new int[][] { null, { 0 }, { 0, 1 }, { 0, 1, 2 }, { 0, 1, 2, 3 } };
/*      */ 
/*      */   
/*      */   private int bitDepth;
/*      */ 
/*      */   
/*      */   private int colorType;
/*      */ 
/*      */   
/*      */   private int compressionMethod;
/*      */ 
/*      */   
/*      */   private int filterMethod;
/*      */ 
/*      */   
/*      */   private int interlaceMethod;
/*      */   
/*      */   private int paletteEntries;
/*      */   
/*      */   private byte[] redPalette;
/*      */   
/*      */   private byte[] greenPalette;
/*      */   
/*      */   private byte[] bluePalette;
/*      */   
/*      */   private byte[] alphaPalette;
/*      */   
/*      */   private int bkgdRed;
/*      */   
/*      */   private int bkgdGreen;
/*      */   
/*      */   private int bkgdBlue;
/*      */   
/*      */   private int grayTransparentAlpha;
/*      */   
/*      */   private int redTransparentAlpha;
/*      */   
/*      */   private int greenTransparentAlpha;
/*      */   
/*      */   private int blueTransparentAlpha;
/*      */   
/*      */   private int maxOpacity;
/*      */   
/*      */   private int[] significantBits;
/*      */   
/*      */   private boolean suppressAlpha;
/*      */   
/*      */   private boolean expandPalette;
/*      */   
/*      */   private boolean output8BitGray;
/*      */   
/*      */   private boolean outputHasAlphaPalette;
/*      */   
/*      */   private boolean performGammaCorrection;
/*      */   
/*      */   private boolean expandGrayAlpha;
/*      */   
/*      */   private boolean generateEncodeParam;
/*      */   
/*      */   private PNGDecodeParam decodeParam;
/*      */   
/*      */   private PNGEncodeParam encodeParam;
/*      */   
/*      */   private boolean emitProperties = true;
/*      */   
/*  189 */   private float fileGamma = 0.45455F;
/*      */   
/*  191 */   private float userExponent = 1.0F;
/*      */   
/*  193 */   private float displayExponent = 2.2F;
/*      */   
/*      */   private float[] chromaticity;
/*      */   
/*  197 */   private int sRGBRenderingIntent = -1;
/*      */ 
/*      */   
/*  200 */   private int postProcess = 0;
/*      */ 
/*      */   
/*      */   protected int xPixelsPerUnit;
/*      */ 
/*      */   
/*      */   protected int yPixelsPerUnit;
/*      */ 
/*      */   
/*      */   protected int unitSpecifier;
/*      */ 
/*      */   
/*      */   private static final int POST_NONE = 0;
/*      */ 
/*      */   
/*      */   private static final int POST_GAMMA = 1;
/*      */ 
/*      */   
/*      */   private static final int POST_GRAY_LUT = 2;
/*      */ 
/*      */   
/*      */   private static final int POST_GRAY_LUT_ADD_TRANS = 3;
/*      */ 
/*      */   
/*      */   private static final int POST_PALETTE_TO_RGB = 4;
/*      */ 
/*      */   
/*      */   private static final int POST_PALETTE_TO_RGBA = 5;
/*      */ 
/*      */   
/*      */   private static final int POST_ADD_GRAY_TRANS = 6;
/*      */ 
/*      */   
/*      */   private static final int POST_ADD_RGB_TRANS = 7;
/*      */ 
/*      */   
/*      */   private static final int POST_REMOVE_GRAY_TRANS = 8;
/*      */ 
/*      */   
/*      */   private static final int POST_REMOVE_RGB_TRANS = 9;
/*      */ 
/*      */   
/*      */   private static final int POST_EXP_MASK = 16;
/*      */ 
/*      */   
/*      */   private static final int POST_GRAY_ALPHA_EXP = 16;
/*      */ 
/*      */   
/*      */   private static final int POST_GAMMA_EXP = 17;
/*      */ 
/*      */   
/*      */   private static final int POST_GRAY_LUT_ADD_TRANS_EXP = 19;
/*      */ 
/*      */   
/*      */   private static final int POST_ADD_GRAY_TRANS_EXP = 22;
/*      */ 
/*      */   
/*  257 */   private List<InputStream> streamVec = new ArrayList<InputStream>();
/*      */   
/*      */   private DataInputStream dataStream;
/*      */   
/*      */   private int bytesPerPixel;
/*      */   
/*      */   private int inputBands;
/*      */   
/*      */   private int outputBands;
/*      */   private int chunkIndex;
/*  267 */   private List textKeys = new ArrayList();
/*  268 */   private List textStrings = new ArrayList();
/*      */   
/*  270 */   private List ztextKeys = new ArrayList();
/*  271 */   private List ztextStrings = new ArrayList();
/*      */   
/*      */   private WritableRaster theTile;
/*      */   
/*      */   private int[] gammaLut;
/*      */   
/*      */   private void initGammaLut(int bits) {
/*  278 */     double exp = this.userExponent / (this.fileGamma * this.displayExponent);
/*  279 */     int numSamples = 1 << bits;
/*  280 */     int maxOutSample = (bits == 16) ? 65535 : 255;
/*      */     
/*  282 */     this.gammaLut = new int[numSamples];
/*  283 */     for (int i = 0; i < numSamples; i++) {
/*  284 */       double gbright = i / (numSamples - 1);
/*  285 */       double gamma = Math.pow(gbright, exp);
/*  286 */       int igamma = (int)(gamma * maxOutSample + 0.5D);
/*  287 */       if (igamma > maxOutSample) {
/*  288 */         igamma = maxOutSample;
/*      */       }
/*  290 */       this.gammaLut[i] = igamma;
/*      */     } 
/*      */   }
/*      */   
/*  294 */   private final byte[][] expandBits = new byte[][] { null, { 0, -1 }, { 0, 85, -86, -1 }, null, { 0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1 } };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int[] grayLut;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initGrayLut(int bits) {
/*  308 */     int len = 1 << bits;
/*  309 */     this.grayLut = new int[len];
/*      */     
/*  311 */     if (this.performGammaCorrection) {
/*  312 */       System.arraycopy(this.gammaLut, 0, this.grayLut, 0, len);
/*      */     } else {
/*  314 */       for (int i = 0; i < len; i++) {
/*  315 */         this.grayLut[i] = this.expandBits[bits][i];
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public PNGImage(InputStream stream) throws IOException {
/*  321 */     DataInputStream distream = new DataInputStream(stream);
/*  322 */     long magic = distream.readLong();
/*  323 */     if (magic != -8552249625308161526L) {
/*  324 */       throw new IOException("Not a png file");
/*      */     }
/*      */     while (true) {
/*  327 */       String chunkType = PNGChunk.getChunkType(distream);
/*  328 */       if (chunkType.equals(PNGChunk.ChunkType.IHDR.name())) {
/*  329 */         PNGChunk chunk = PNGChunk.readChunk(distream);
/*  330 */         parse_IHDR_chunk(chunk); continue;
/*  331 */       }  if (chunkType.equals(PNGChunk.ChunkType.pHYs.name())) {
/*  332 */         PNGChunk chunk = PNGChunk.readChunk(distream);
/*  333 */         parse_pHYs_chunk(chunk); return;
/*      */       } 
/*  335 */       if (chunkType.equals(PNGChunk.ChunkType.IEND.name())) {
/*      */         return;
/*      */       }
/*  338 */       PNGChunk.readChunk(distream);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PNGImage(InputStream stream, PNGDecodeParam decodeParam) throws IOException {
/*  346 */     if (!stream.markSupported()) {
/*  347 */       stream = new BufferedInputStream(stream);
/*      */     }
/*  349 */     DataInputStream distream = new DataInputStream(stream);
/*      */     
/*  351 */     if (decodeParam == null) {
/*  352 */       decodeParam = new PNGDecodeParam();
/*      */     }
/*  354 */     this.decodeParam = decodeParam;
/*      */ 
/*      */     
/*  357 */     this.suppressAlpha = decodeParam.getSuppressAlpha();
/*  358 */     this.expandPalette = decodeParam.getExpandPalette();
/*  359 */     this.output8BitGray = decodeParam.getOutput8BitGray();
/*  360 */     this.expandGrayAlpha = decodeParam.getExpandGrayAlpha();
/*  361 */     if (decodeParam.getPerformGammaCorrection()) {
/*  362 */       this.userExponent = decodeParam.getUserExponent();
/*  363 */       this.displayExponent = decodeParam.getDisplayExponent();
/*  364 */       this.performGammaCorrection = true;
/*  365 */       this.output8BitGray = true;
/*      */     } 
/*  367 */     this.generateEncodeParam = decodeParam.getGenerateEncodeParam();
/*      */     
/*  369 */     if (this.emitProperties) {
/*  370 */       this.properties.put("file_type", "PNG v. 1.0");
/*      */     }
/*      */     
/*      */     try {
/*  374 */       long magic = distream.readLong();
/*  375 */       if (magic != -8552249625308161526L) {
/*  376 */         String msg = PropertyUtil.getString("PNGImageDecoder0");
/*  377 */         throw new RuntimeException(msg);
/*      */       } 
/*  379 */     } catch (IOException ioe) {
/*  380 */       ioe.printStackTrace();
/*  381 */       String msg = PropertyUtil.getString("PNGImageDecoder1");
/*  382 */       throw new RuntimeException(msg);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     while (true) {
/*  389 */       String chunkType = PNGChunk.getChunkType(distream);
/*  390 */       if (chunkType.equals(PNGChunk.ChunkType.IHDR.name())) {
/*  391 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*  392 */         parse_IHDR_chunk(pNGChunk); continue;
/*  393 */       }  if (chunkType.equals(PNGChunk.ChunkType.PLTE.name())) {
/*  394 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*  395 */         parse_PLTE_chunk(pNGChunk); continue;
/*  396 */       }  if (chunkType.equals(PNGChunk.ChunkType.IDAT.name())) {
/*  397 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*  398 */         this.streamVec.add(new ByteArrayInputStream(pNGChunk.getData())); continue;
/*  399 */       }  if (chunkType.equals(PNGChunk.ChunkType.IEND.name())) {
/*  400 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*      */         try {
/*  402 */           parse_IEND_chunk(pNGChunk);
/*  403 */         } catch (Exception e) {
/*  404 */           e.printStackTrace();
/*  405 */           String msg = PropertyUtil.getString("PNGImageDecoder2");
/*  406 */           throw new RuntimeException(msg);
/*      */         }  break;
/*      */       } 
/*  409 */       if (chunkType.equals(PNGChunk.ChunkType.bKGD.name())) {
/*  410 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*  411 */         parse_bKGD_chunk(pNGChunk); continue;
/*  412 */       }  if (chunkType.equals(PNGChunk.ChunkType.cHRM.name())) {
/*  413 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*  414 */         parse_cHRM_chunk(pNGChunk); continue;
/*  415 */       }  if (chunkType.equals(PNGChunk.ChunkType.gAMA.name())) {
/*  416 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*  417 */         parse_gAMA_chunk(pNGChunk); continue;
/*  418 */       }  if (chunkType.equals(PNGChunk.ChunkType.hIST.name())) {
/*  419 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*  420 */         parse_hIST_chunk(pNGChunk); continue;
/*  421 */       }  if (chunkType.equals(PNGChunk.ChunkType.iCCP.name())) {
/*  422 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*  423 */         parse_iCCP_chunk(pNGChunk); continue;
/*  424 */       }  if (chunkType.equals(PNGChunk.ChunkType.pHYs.name())) {
/*  425 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*  426 */         parse_pHYs_chunk(pNGChunk); continue;
/*  427 */       }  if (chunkType.equals(PNGChunk.ChunkType.sBIT.name())) {
/*  428 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*  429 */         parse_sBIT_chunk(pNGChunk); continue;
/*  430 */       }  if (chunkType.equals(PNGChunk.ChunkType.sRGB.name())) {
/*  431 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*  432 */         parse_sRGB_chunk(pNGChunk); continue;
/*  433 */       }  if (chunkType.equals(PNGChunk.ChunkType.tEXt.name())) {
/*  434 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*  435 */         parse_tEXt_chunk(pNGChunk); continue;
/*  436 */       }  if (chunkType.equals(PNGChunk.ChunkType.tIME.name())) {
/*  437 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*  438 */         parse_tIME_chunk(pNGChunk); continue;
/*  439 */       }  if (chunkType.equals(PNGChunk.ChunkType.tRNS.name())) {
/*  440 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*  441 */         parse_tRNS_chunk(pNGChunk); continue;
/*  442 */       }  if (chunkType.equals(PNGChunk.ChunkType.zTXt.name())) {
/*  443 */         PNGChunk pNGChunk = PNGChunk.readChunk(distream);
/*  444 */         parse_zTXt_chunk(pNGChunk); continue;
/*      */       } 
/*  446 */       PNGChunk chunk = PNGChunk.readChunk(distream);
/*      */ 
/*      */       
/*  449 */       String type = chunk.getTypeString();
/*  450 */       byte[] data = chunk.getData();
/*  451 */       if (this.encodeParam != null) {
/*  452 */         this.encodeParam.addPrivateChunk(type, data);
/*      */       }
/*  454 */       if (this.emitProperties) {
/*  455 */         String key = "chunk_" + this.chunkIndex++ + ':' + type;
/*  456 */         this.properties.put(key.toLowerCase(Locale.getDefault()), data);
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
/*      */     
/*  468 */     if (this.significantBits == null) {
/*  469 */       this.significantBits = new int[this.inputBands];
/*  470 */       for (int i = 0; i < this.inputBands; i++) {
/*  471 */         this.significantBits[i] = this.bitDepth;
/*      */       }
/*      */       
/*  474 */       if (this.emitProperties) {
/*  475 */         this.properties.put("significant_bits", this.significantBits);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parse_IHDR_chunk(PNGChunk chunk) {
/*  481 */     this.tileWidth = this.width = chunk.getInt4(0);
/*  482 */     this.tileHeight = this.height = chunk.getInt4(4);
/*      */     
/*  484 */     this.bitDepth = chunk.getInt1(8);
/*      */     
/*  486 */     if (this.bitDepth != 1 && this.bitDepth != 2 && this.bitDepth != 4 && this.bitDepth != 8 && this.bitDepth != 16) {
/*      */ 
/*      */       
/*  489 */       String msg = PropertyUtil.getString("PNGImageDecoder3");
/*  490 */       throw new RuntimeException(msg);
/*      */     } 
/*  492 */     this.maxOpacity = (1 << this.bitDepth) - 1;
/*      */     
/*  494 */     this.colorType = chunk.getInt1(9);
/*  495 */     if (this.colorType != 0 && this.colorType != 2 && this.colorType != 3 && this.colorType != 4 && this.colorType != 6)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  500 */       System.out.println(PropertyUtil.getString("PNGImageDecoder4"));
/*      */     }
/*      */     
/*  503 */     if (this.colorType == 2 && this.bitDepth < 8) {
/*      */       
/*  505 */       String msg = PropertyUtil.getString("PNGImageDecoder5");
/*  506 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  509 */     if (this.colorType == 3 && this.bitDepth == 16) {
/*      */       
/*  511 */       String msg = PropertyUtil.getString("PNGImageDecoder6");
/*  512 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  515 */     if (this.colorType == 4 && this.bitDepth < 8) {
/*      */       
/*  517 */       String msg = PropertyUtil.getString("PNGImageDecoder7");
/*  518 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  521 */     if (this.colorType == 6 && this.bitDepth < 8) {
/*      */       
/*  523 */       String msg = PropertyUtil.getString("PNGImageDecoder8");
/*  524 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  527 */     if (this.emitProperties) {
/*  528 */       this.properties.put("color_type", colorTypeNames[this.colorType]);
/*      */     }
/*      */     
/*  531 */     if (this.generateEncodeParam) {
/*  532 */       if (this.colorType == 3) {
/*  533 */         this.encodeParam = new PNGEncodeParam.Palette();
/*  534 */       } else if (this.colorType == 0 || this.colorType == 4) {
/*      */         
/*  536 */         this.encodeParam = new PNGEncodeParam.Gray();
/*      */       } else {
/*  538 */         this.encodeParam = new PNGEncodeParam.RGB();
/*      */       } 
/*  540 */       this.decodeParam.setEncodeParam(this.encodeParam);
/*      */     } 
/*      */     
/*  543 */     if (this.encodeParam != null) {
/*  544 */       this.encodeParam.setBitDepth(this.bitDepth);
/*      */     }
/*  546 */     if (this.emitProperties) {
/*  547 */       this.properties.put("bit_depth", Integer.valueOf(this.bitDepth));
/*      */     }
/*      */     
/*  550 */     if (this.performGammaCorrection) {
/*      */       
/*  552 */       float gamma = 0.45454544F * this.displayExponent / this.userExponent;
/*  553 */       if (this.encodeParam != null) {
/*  554 */         this.encodeParam.setGamma(gamma);
/*      */       }
/*  556 */       if (this.emitProperties) {
/*  557 */         this.properties.put("gamma", Float.valueOf(gamma));
/*      */       }
/*      */     } 
/*      */     
/*  561 */     this.compressionMethod = chunk.getInt1(10);
/*  562 */     if (this.compressionMethod != 0) {
/*      */       
/*  564 */       String msg = PropertyUtil.getString("PNGImageDecoder9");
/*  565 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  568 */     this.filterMethod = chunk.getInt1(11);
/*  569 */     if (this.filterMethod != 0) {
/*      */       
/*  571 */       String msg = PropertyUtil.getString("PNGImageDecoder10");
/*  572 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  575 */     this.interlaceMethod = chunk.getInt1(12);
/*  576 */     if (this.interlaceMethod == 0) {
/*  577 */       if (this.encodeParam != null) {
/*  578 */         this.encodeParam.setInterlacing(false);
/*      */       }
/*  580 */       if (this.emitProperties) {
/*  581 */         this.properties.put("interlace_method", "None");
/*      */       }
/*  583 */     } else if (this.interlaceMethod == 1) {
/*  584 */       if (this.encodeParam != null) {
/*  585 */         this.encodeParam.setInterlacing(true);
/*      */       }
/*  587 */       if (this.emitProperties) {
/*  588 */         this.properties.put("interlace_method", "Adam7");
/*      */       }
/*      */     } else {
/*      */       
/*  592 */       String msg = PropertyUtil.getString("PNGImageDecoder11");
/*  593 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  596 */     this.bytesPerPixel = (this.bitDepth == 16) ? 2 : 1;
/*      */     
/*  598 */     switch (this.colorType) {
/*      */       case 0:
/*  600 */         this.inputBands = 1;
/*  601 */         this.outputBands = 1;
/*      */         
/*  603 */         if (this.output8BitGray && this.bitDepth < 8) {
/*  604 */           this.postProcess = 2; break;
/*  605 */         }  if (this.performGammaCorrection) {
/*  606 */           this.postProcess = 1; break;
/*      */         } 
/*  608 */         this.postProcess = 0;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2:
/*  613 */         this.inputBands = 3;
/*  614 */         this.bytesPerPixel *= 3;
/*  615 */         this.outputBands = 3;
/*      */         
/*  617 */         if (this.performGammaCorrection) {
/*  618 */           this.postProcess = 1; break;
/*      */         } 
/*  620 */         this.postProcess = 0;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 3:
/*  625 */         this.inputBands = 1;
/*  626 */         this.bytesPerPixel = 1;
/*  627 */         this.outputBands = this.expandPalette ? 3 : 1;
/*      */         
/*  629 */         if (this.expandPalette) {
/*  630 */           this.postProcess = 4; break;
/*      */         } 
/*  632 */         this.postProcess = 0;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 4:
/*  637 */         this.inputBands = 2;
/*  638 */         this.bytesPerPixel *= 2;
/*      */         
/*  640 */         if (this.suppressAlpha) {
/*  641 */           this.outputBands = 1;
/*  642 */           this.postProcess = 8; break;
/*      */         } 
/*  644 */         if (this.performGammaCorrection) {
/*  645 */           this.postProcess = 1;
/*      */         } else {
/*  647 */           this.postProcess = 0;
/*      */         } 
/*  649 */         if (this.expandGrayAlpha) {
/*  650 */           this.postProcess |= 0x10;
/*  651 */           this.outputBands = 4; break;
/*      */         } 
/*  653 */         this.outputBands = 2;
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 6:
/*  659 */         this.inputBands = 4;
/*  660 */         this.bytesPerPixel *= 4;
/*  661 */         this.outputBands = !this.suppressAlpha ? 4 : 3;
/*      */         
/*  663 */         if (this.suppressAlpha) {
/*  664 */           this.postProcess = 9; break;
/*  665 */         }  if (this.performGammaCorrection) {
/*  666 */           this.postProcess = 1; break;
/*      */         } 
/*  668 */         this.postProcess = 0;
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void parse_IEND_chunk(PNGChunk chunk) throws Exception {
/*  676 */     int textLen = this.textKeys.size();
/*  677 */     String[] textArray = new String[2 * textLen];
/*  678 */     for (int i = 0; i < textLen; i++) {
/*  679 */       String key = this.textKeys.get(i);
/*  680 */       String val = this.textStrings.get(i);
/*  681 */       textArray[2 * i] = key;
/*  682 */       textArray[2 * i + 1] = val;
/*  683 */       if (this.emitProperties) {
/*  684 */         String uniqueKey = "text_" + i + ':' + key;
/*  685 */         this.properties.put(uniqueKey.toLowerCase(Locale.getDefault()), val);
/*      */       } 
/*      */     } 
/*  688 */     if (this.encodeParam != null) {
/*  689 */       this.encodeParam.setText(textArray);
/*      */     }
/*      */ 
/*      */     
/*  693 */     int ztextLen = this.ztextKeys.size();
/*  694 */     String[] ztextArray = new String[2 * ztextLen];
/*  695 */     for (int j = 0; j < ztextLen; j++) {
/*  696 */       String key = this.ztextKeys.get(j);
/*  697 */       String val = this.ztextStrings.get(j);
/*  698 */       ztextArray[2 * j] = key;
/*  699 */       ztextArray[2 * j + 1] = val;
/*  700 */       if (this.emitProperties) {
/*  701 */         String uniqueKey = "ztext_" + j + ':' + key;
/*  702 */         this.properties.put(uniqueKey.toLowerCase(Locale.getDefault()), val);
/*      */       } 
/*      */     } 
/*  705 */     if (this.encodeParam != null) {
/*  706 */       this.encodeParam.setCompressedText(ztextArray);
/*      */     }
/*      */ 
/*      */     
/*  710 */     InputStream seqStream = new SequenceInputStream(Collections.enumeration(this.streamVec));
/*      */     
/*  712 */     InputStream infStream = new InflaterInputStream(seqStream, new Inflater());
/*      */     
/*  714 */     this.dataStream = new DataInputStream(infStream);
/*      */ 
/*      */     
/*  717 */     int depth = this.bitDepth;
/*  718 */     if (this.colorType == 0 && this.bitDepth < 8 && this.output8BitGray)
/*      */     {
/*  720 */       depth = 8;
/*      */     }
/*  722 */     if (this.colorType == 3 && this.expandPalette) {
/*  723 */       depth = 8;
/*      */     }
/*  725 */     int bytesPerRow = (this.outputBands * this.width * depth + 7) / 8;
/*  726 */     int scanlineStride = (depth == 16) ? (bytesPerRow / 2) : bytesPerRow;
/*      */ 
/*      */     
/*  729 */     this.theTile = createRaster(this.width, this.height, this.outputBands, scanlineStride, depth);
/*      */ 
/*      */ 
/*      */     
/*  733 */     if (this.performGammaCorrection && this.gammaLut == null) {
/*  734 */       initGammaLut(this.bitDepth);
/*      */     }
/*  736 */     if (this.postProcess == 2 || this.postProcess == 3 || this.postProcess == 19)
/*      */     {
/*      */       
/*  739 */       initGrayLut(this.bitDepth);
/*      */     }
/*      */     
/*  742 */     decodeImage((this.interlaceMethod == 1));
/*  743 */     this.sampleModel = this.theTile.getSampleModel();
/*      */     
/*  745 */     if (this.colorType == 3 && !this.expandPalette) {
/*  746 */       if (this.outputHasAlphaPalette) {
/*  747 */         this.colorModel = new IndexColorModel(this.bitDepth, this.paletteEntries, this.redPalette, this.greenPalette, this.bluePalette, this.alphaPalette);
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  754 */         this.colorModel = new IndexColorModel(this.bitDepth, this.paletteEntries, this.redPalette, this.greenPalette, this.bluePalette);
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  760 */     else if (this.colorType == 0 && this.bitDepth < 8 && !this.output8BitGray) {
/*      */       
/*  762 */       byte[] palette = this.expandBits[this.bitDepth];
/*  763 */       this.colorModel = new IndexColorModel(this.bitDepth, palette.length, palette, palette, palette);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  769 */       this.colorModel = createComponentColorModel(this.sampleModel);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*  774 */   private static final int[] GrayBits8 = new int[] { 8 };
/*  775 */   private static final ComponentColorModel colorModelGray8 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayBits8, false, false, 1, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  781 */   private static final int[] GrayAlphaBits8 = new int[] { 8, 8 };
/*  782 */   private static final ComponentColorModel colorModelGrayAlpha8 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayAlphaBits8, true, false, 3, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  788 */   private static final int[] GrayBits16 = new int[] { 16 };
/*  789 */   private static final ComponentColorModel colorModelGray16 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayBits16, false, false, 1, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  795 */   private static final int[] GrayAlphaBits16 = new int[] { 16, 16 };
/*  796 */   private static final ComponentColorModel colorModelGrayAlpha16 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayAlphaBits16, true, false, 3, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  802 */   private static final int[] GrayBits32 = new int[] { 32 };
/*  803 */   private static final ComponentColorModel colorModelGray32 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayBits32, false, false, 1, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  809 */   private static final int[] GrayAlphaBits32 = new int[] { 32, 32 };
/*  810 */   private static final ComponentColorModel colorModelGrayAlpha32 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayAlphaBits32, true, false, 3, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  816 */   private static final int[] RGBBits8 = new int[] { 8, 8, 8 };
/*  817 */   private static final ComponentColorModel colorModelRGB8 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBBits8, false, false, 1, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  823 */   private static final int[] RGBABits8 = new int[] { 8, 8, 8, 8 };
/*  824 */   private static final ComponentColorModel colorModelRGBA8 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBABits8, true, false, 3, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  830 */   private static final int[] RGBBits16 = new int[] { 16, 16, 16 };
/*  831 */   private static final ComponentColorModel colorModelRGB16 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBBits16, false, false, 1, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  837 */   private static final int[] RGBABits16 = new int[] { 16, 16, 16, 16 };
/*  838 */   private static final ComponentColorModel colorModelRGBA16 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBABits16, true, false, 3, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  844 */   private static final int[] RGBBits32 = new int[] { 32, 32, 32 };
/*  845 */   private static final ComponentColorModel colorModelRGB32 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBBits32, false, false, 1, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  851 */   private static final int[] RGBABits32 = new int[] { 32, 32, 32, 32 };
/*  852 */   private static final ComponentColorModel colorModelRGBA32 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBABits32, true, false, 3, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static ColorModel createComponentColorModel(SampleModel sm) {
/*  868 */     int type = sm.getDataType();
/*  869 */     int bands = sm.getNumBands();
/*  870 */     ComponentColorModel cm = null;
/*      */     
/*  872 */     if (type == 0) {
/*  873 */       switch (bands) {
/*      */         case 1:
/*  875 */           cm = colorModelGray8;
/*      */           break;
/*      */         case 2:
/*  878 */           cm = colorModelGrayAlpha8;
/*      */           break;
/*      */         case 3:
/*  881 */           cm = colorModelRGB8;
/*      */           break;
/*      */         case 4:
/*  884 */           cm = colorModelRGBA8;
/*      */           break;
/*      */       } 
/*  887 */     } else if (type == 1) {
/*  888 */       switch (bands) {
/*      */         case 1:
/*  890 */           cm = colorModelGray16;
/*      */           break;
/*      */         case 2:
/*  893 */           cm = colorModelGrayAlpha16;
/*      */           break;
/*      */         case 3:
/*  896 */           cm = colorModelRGB16;
/*      */           break;
/*      */         case 4:
/*  899 */           cm = colorModelRGBA16;
/*      */           break;
/*      */       } 
/*  902 */     } else if (type == 3) {
/*  903 */       switch (bands) {
/*      */         case 1:
/*  905 */           cm = colorModelGray32;
/*      */           break;
/*      */         case 2:
/*  908 */           cm = colorModelGrayAlpha32;
/*      */           break;
/*      */         case 3:
/*  911 */           cm = colorModelRGB32;
/*      */           break;
/*      */         case 4:
/*  914 */           cm = colorModelRGBA32;
/*      */           break;
/*      */       } 
/*      */     
/*      */     } 
/*  919 */     return cm;
/*      */   }
/*      */   
/*      */   private void parse_PLTE_chunk(PNGChunk chunk) {
/*  923 */     this.paletteEntries = chunk.getLength() / 3;
/*  924 */     this.redPalette = new byte[this.paletteEntries];
/*  925 */     this.greenPalette = new byte[this.paletteEntries];
/*  926 */     this.bluePalette = new byte[this.paletteEntries];
/*      */     
/*  928 */     int pltIndex = 0;
/*      */ 
/*      */     
/*  931 */     if (this.performGammaCorrection) {
/*  932 */       if (this.gammaLut == null) {
/*  933 */         initGammaLut((this.bitDepth == 16) ? 16 : 8);
/*      */       }
/*      */       
/*  936 */       for (int i = 0; i < this.paletteEntries; i++) {
/*  937 */         byte r = chunk.getByte(pltIndex++);
/*  938 */         byte g = chunk.getByte(pltIndex++);
/*  939 */         byte b = chunk.getByte(pltIndex++);
/*      */         
/*  941 */         this.redPalette[i] = (byte)this.gammaLut[r & 0xFF];
/*  942 */         this.greenPalette[i] = (byte)this.gammaLut[g & 0xFF];
/*  943 */         this.bluePalette[i] = (byte)this.gammaLut[b & 0xFF];
/*      */       } 
/*      */     } else {
/*  946 */       for (int i = 0; i < this.paletteEntries; i++) {
/*  947 */         this.redPalette[i] = chunk.getByte(pltIndex++);
/*  948 */         this.greenPalette[i] = chunk.getByte(pltIndex++);
/*  949 */         this.bluePalette[i] = chunk.getByte(pltIndex++);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   private void parse_bKGD_chunk(PNGChunk chunk) {
/*      */     int bkgdIndex, bkgdGray, bkgdRGB[];
/*  955 */     switch (this.colorType) {
/*      */       case 3:
/*  957 */         bkgdIndex = chunk.getByte(0) & 0xFF;
/*      */         
/*  959 */         this.bkgdRed = this.redPalette[bkgdIndex] & 0xFF;
/*  960 */         this.bkgdGreen = this.greenPalette[bkgdIndex] & 0xFF;
/*  961 */         this.bkgdBlue = this.bluePalette[bkgdIndex] & 0xFF;
/*      */         
/*  963 */         if (this.encodeParam != null)
/*  964 */           ((PNGEncodeParam.Palette)this.encodeParam).setBackgroundPaletteIndex(bkgdIndex); 
/*      */         break;
/*      */       case 0:
/*      */       case 4:
/*  968 */         bkgdGray = chunk.getInt2(0);
/*  969 */         this.bkgdRed = this.bkgdGreen = this.bkgdBlue = bkgdGray;
/*      */         
/*  971 */         if (this.encodeParam != null)
/*  972 */           ((PNGEncodeParam.Gray)this.encodeParam).setBackgroundGray(bkgdGray); 
/*      */         break;
/*      */       case 2:
/*      */       case 6:
/*  976 */         this.bkgdRed = chunk.getInt2(0);
/*  977 */         this.bkgdGreen = chunk.getInt2(2);
/*  978 */         this.bkgdBlue = chunk.getInt2(4);
/*      */         
/*  980 */         bkgdRGB = new int[3];
/*  981 */         bkgdRGB[0] = this.bkgdRed;
/*  982 */         bkgdRGB[1] = this.bkgdGreen;
/*  983 */         bkgdRGB[2] = this.bkgdBlue;
/*  984 */         if (this.encodeParam != null) {
/*  985 */           ((PNGEncodeParam.RGB)this.encodeParam).setBackgroundRGB(bkgdRGB);
/*      */         }
/*      */         break;
/*      */     } 
/*      */     
/*  990 */     int r = 0;
/*  991 */     int g = 0;
/*  992 */     int b = 0;
/*  993 */     if (this.bitDepth < 8) {
/*  994 */       r = this.expandBits[this.bitDepth][this.bkgdRed];
/*  995 */       g = this.expandBits[this.bitDepth][this.bkgdGreen];
/*  996 */       b = this.expandBits[this.bitDepth][this.bkgdBlue];
/*  997 */     } else if (this.bitDepth == 8) {
/*  998 */       r = this.bkgdRed;
/*  999 */       g = this.bkgdGreen;
/* 1000 */       b = this.bkgdBlue;
/* 1001 */     } else if (this.bitDepth == 16) {
/* 1002 */       r = this.bkgdRed >> 8;
/* 1003 */       g = this.bkgdGreen >> 8;
/* 1004 */       b = this.bkgdBlue >> 8;
/*      */     } 
/* 1006 */     if (this.emitProperties) {
/* 1007 */       this.properties.put("background_color", new Color(r, g, b));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void parse_cHRM_chunk(PNGChunk chunk) {
/* 1013 */     if (this.sRGBRenderingIntent != -1) {
/*      */       return;
/*      */     }
/*      */     
/* 1017 */     this.chromaticity = new float[8];
/* 1018 */     this.chromaticity[0] = chunk.getInt4(0) / 100000.0F;
/* 1019 */     this.chromaticity[1] = chunk.getInt4(4) / 100000.0F;
/* 1020 */     this.chromaticity[2] = chunk.getInt4(8) / 100000.0F;
/* 1021 */     this.chromaticity[3] = chunk.getInt4(12) / 100000.0F;
/* 1022 */     this.chromaticity[4] = chunk.getInt4(16) / 100000.0F;
/* 1023 */     this.chromaticity[5] = chunk.getInt4(20) / 100000.0F;
/* 1024 */     this.chromaticity[6] = chunk.getInt4(24) / 100000.0F;
/* 1025 */     this.chromaticity[7] = chunk.getInt4(28) / 100000.0F;
/*      */     
/* 1027 */     if (this.encodeParam != null) {
/* 1028 */       this.encodeParam.setChromaticity(this.chromaticity);
/*      */     }
/* 1030 */     if (this.emitProperties) {
/* 1031 */       this.properties.put("white_point_x", Float.valueOf(this.chromaticity[0]));
/* 1032 */       this.properties.put("white_point_y", Float.valueOf(this.chromaticity[1]));
/* 1033 */       this.properties.put("red_x", Float.valueOf(this.chromaticity[2]));
/* 1034 */       this.properties.put("red_y", Float.valueOf(this.chromaticity[3]));
/* 1035 */       this.properties.put("green_x", Float.valueOf(this.chromaticity[4]));
/* 1036 */       this.properties.put("green_y", Float.valueOf(this.chromaticity[5]));
/* 1037 */       this.properties.put("blue_x", Float.valueOf(this.chromaticity[6]));
/* 1038 */       this.properties.put("blue_y", Float.valueOf(this.chromaticity[7]));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void parse_gAMA_chunk(PNGChunk chunk) {
/* 1044 */     if (this.sRGBRenderingIntent != -1) {
/*      */       return;
/*      */     }
/*      */     
/* 1048 */     this.fileGamma = chunk.getInt4(0) / 100000.0F;
/*      */     
/* 1050 */     float exp = this.performGammaCorrection ? (this.displayExponent / this.userExponent) : 1.0F;
/*      */     
/* 1052 */     if (this.encodeParam != null) {
/* 1053 */       this.encodeParam.setGamma(this.fileGamma * exp);
/*      */     }
/* 1055 */     if (this.emitProperties) {
/* 1056 */       this.properties.put("gamma", Float.valueOf(this.fileGamma * exp));
/*      */     }
/*      */   }
/*      */   
/*      */   private void parse_hIST_chunk(PNGChunk chunk) {
/* 1061 */     if (this.redPalette == null) {
/* 1062 */       String msg = PropertyUtil.getString("PNGImageDecoder18");
/* 1063 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/* 1066 */     int length = this.redPalette.length;
/* 1067 */     int[] hist = new int[length];
/* 1068 */     for (int i = 0; i < length; i++) {
/* 1069 */       hist[i] = chunk.getInt2(2 * i);
/*      */     }
/*      */     
/* 1072 */     if (this.encodeParam != null) {
/* 1073 */       this.encodeParam.setPaletteHistogram(hist);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void parse_iCCP_chunk(PNGChunk chunk) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void parse_pHYs_chunk(PNGChunk chunk) {
/* 1088 */     this.xPixelsPerUnit = chunk.getInt4(0);
/* 1089 */     this.yPixelsPerUnit = chunk.getInt4(4);
/* 1090 */     this.unitSpecifier = chunk.getInt1(8);
/*      */     
/* 1092 */     if (this.encodeParam != null) {
/* 1093 */       this.encodeParam.setPhysicalDimension(this.xPixelsPerUnit, this.yPixelsPerUnit, this.unitSpecifier);
/*      */     }
/*      */ 
/*      */     
/* 1097 */     if (this.emitProperties) {
/* 1098 */       this.properties.put("x_pixels_per_unit", Integer.valueOf(this.xPixelsPerUnit));
/* 1099 */       this.properties.put("y_pixels_per_unit", Integer.valueOf(this.yPixelsPerUnit));
/* 1100 */       this.properties.put("pixel_aspect_ratio", Float.valueOf(this.xPixelsPerUnit / this.yPixelsPerUnit));
/*      */       
/* 1102 */       if (this.unitSpecifier == 1) {
/* 1103 */         this.properties.put("pixel_units", "Meters");
/* 1104 */       } else if (this.unitSpecifier != 0) {
/*      */         
/* 1106 */         String msg = PropertyUtil.getString("PNGImageDecoder12");
/* 1107 */         throw new RuntimeException(msg);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parse_sBIT_chunk(PNGChunk chunk) {
/* 1113 */     if (this.colorType == 3) {
/* 1114 */       this.significantBits = new int[3];
/*      */     } else {
/* 1116 */       this.significantBits = new int[this.inputBands];
/*      */     } 
/* 1118 */     for (int i = 0; i < this.significantBits.length; i++) {
/* 1119 */       int bits = chunk.getByte(i);
/* 1120 */       int depth = (this.colorType == 3) ? 8 : this.bitDepth;
/* 1121 */       if (bits <= 0 || bits > depth) {
/*      */ 
/*      */         
/* 1124 */         String msg = PropertyUtil.getString("PNGImageDecoder13");
/* 1125 */         throw new RuntimeException(msg);
/*      */       } 
/* 1127 */       this.significantBits[i] = bits;
/*      */     } 
/*      */     
/* 1130 */     if (this.encodeParam != null) {
/* 1131 */       this.encodeParam.setSignificantBits(this.significantBits);
/*      */     }
/* 1133 */     if (this.emitProperties) {
/* 1134 */       this.properties.put("significant_bits", this.significantBits);
/*      */     }
/*      */   }
/*      */   
/*      */   private void parse_sRGB_chunk(PNGChunk chunk) {
/* 1139 */     this.sRGBRenderingIntent = chunk.getByte(0);
/*      */ 
/*      */ 
/*      */     
/* 1143 */     this.fileGamma = 0.45455F;
/*      */     
/* 1145 */     this.chromaticity = new float[8];
/* 1146 */     this.chromaticity[0] = 3.127F;
/* 1147 */     this.chromaticity[1] = 3.29F;
/* 1148 */     this.chromaticity[2] = 6.4F;
/* 1149 */     this.chromaticity[3] = 3.3F;
/* 1150 */     this.chromaticity[4] = 3.0F;
/* 1151 */     this.chromaticity[5] = 6.0F;
/* 1152 */     this.chromaticity[6] = 1.5F;
/* 1153 */     this.chromaticity[7] = 0.6F;
/*      */     
/* 1155 */     if (this.performGammaCorrection) {
/*      */       
/* 1157 */       float gamma = this.fileGamma * this.displayExponent / this.userExponent;
/* 1158 */       if (this.encodeParam != null) {
/* 1159 */         this.encodeParam.setGamma(gamma);
/* 1160 */         this.encodeParam.setChromaticity(this.chromaticity);
/*      */       } 
/* 1162 */       if (this.emitProperties) {
/* 1163 */         this.properties.put("gamma", Float.valueOf(gamma));
/* 1164 */         this.properties.put("white_point_x", Float.valueOf(this.chromaticity[0]));
/* 1165 */         this.properties.put("white_point_y", Float.valueOf(this.chromaticity[1]));
/* 1166 */         this.properties.put("red_x", Float.valueOf(this.chromaticity[2]));
/* 1167 */         this.properties.put("red_y", Float.valueOf(this.chromaticity[3]));
/* 1168 */         this.properties.put("green_x", Float.valueOf(this.chromaticity[4]));
/* 1169 */         this.properties.put("green_y", Float.valueOf(this.chromaticity[5]));
/* 1170 */         this.properties.put("blue_x", Float.valueOf(this.chromaticity[6]));
/* 1171 */         this.properties.put("blue_y", Float.valueOf(this.chromaticity[7]));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void parse_tEXt_chunk(PNGChunk chunk) {
/* 1179 */     StringBuffer key = new StringBuffer();
/* 1180 */     int textIndex = 0; byte b;
/* 1181 */     while ((b = chunk.getByte(textIndex++)) != 0) {
/* 1182 */       key.append((char)b);
/*      */     }
/*      */     
/* 1185 */     StringBuilder value = new StringBuilder();
/* 1186 */     for (int i = textIndex; i < chunk.getLength(); i++) {
/* 1187 */       value.append((char)chunk.getByte(i));
/*      */     }
/*      */     
/* 1190 */     this.textKeys.add(key.toString());
/* 1191 */     this.textStrings.add(value.toString());
/*      */   }
/*      */   
/*      */   private void parse_tIME_chunk(PNGChunk chunk) {
/* 1195 */     int year = chunk.getInt2(0);
/* 1196 */     int month = chunk.getInt1(2) - 1;
/* 1197 */     int day = chunk.getInt1(3);
/* 1198 */     int hour = chunk.getInt1(4);
/* 1199 */     int minute = chunk.getInt1(5);
/* 1200 */     int second = chunk.getInt1(6);
/*      */     
/* 1202 */     TimeZone gmt = TimeZone.getTimeZone("GMT");
/*      */     
/* 1204 */     GregorianCalendar cal = new GregorianCalendar(gmt);
/* 1205 */     cal.set(year, month, day, hour, minute, second);
/*      */     
/* 1207 */     Date date = cal.getTime();
/*      */     
/* 1209 */     if (this.encodeParam != null) {
/* 1210 */       this.encodeParam.setModificationTime(date);
/*      */     }
/* 1212 */     if (this.emitProperties) {
/* 1213 */       this.properties.put("timestamp", date);
/*      */     }
/*      */   }
/*      */   
/*      */   private void parse_tRNS_chunk(PNGChunk chunk) {
/* 1218 */     if (this.colorType == 3) {
/* 1219 */       int entries = chunk.getLength();
/* 1220 */       if (entries > this.paletteEntries) {
/*      */         
/* 1222 */         String msg = PropertyUtil.getString("PNGImageDecoder14");
/* 1223 */         throw new RuntimeException(msg);
/*      */       } 
/*      */ 
/*      */       
/* 1227 */       this.alphaPalette = new byte[this.paletteEntries]; int i;
/* 1228 */       for (i = 0; i < entries; i++) {
/* 1229 */         this.alphaPalette[i] = chunk.getByte(i);
/*      */       }
/*      */ 
/*      */       
/* 1233 */       for (i = entries; i < this.paletteEntries; i++) {
/* 1234 */         this.alphaPalette[i] = -1;
/*      */       }
/*      */       
/* 1237 */       if (!this.suppressAlpha) {
/* 1238 */         if (this.expandPalette) {
/* 1239 */           this.postProcess = 5;
/* 1240 */           this.outputBands = 4;
/*      */         } else {
/* 1242 */           this.outputHasAlphaPalette = true;
/*      */         } 
/*      */       }
/* 1245 */     } else if (this.colorType == 0) {
/* 1246 */       this.grayTransparentAlpha = chunk.getInt2(0);
/*      */       
/* 1248 */       if (!this.suppressAlpha) {
/* 1249 */         if (this.bitDepth < 8) {
/* 1250 */           this.output8BitGray = true;
/* 1251 */           this.maxOpacity = 255;
/* 1252 */           this.postProcess = 3;
/*      */         } else {
/* 1254 */           this.postProcess = 6;
/*      */         } 
/*      */         
/* 1257 */         if (this.expandGrayAlpha) {
/* 1258 */           this.outputBands = 4;
/* 1259 */           this.postProcess |= 0x10;
/*      */         } else {
/* 1261 */           this.outputBands = 2;
/*      */         } 
/*      */         
/* 1264 */         if (this.encodeParam != null) {
/* 1265 */           ((PNGEncodeParam.Gray)this.encodeParam).setTransparentGray(this.grayTransparentAlpha);
/*      */         }
/*      */       } 
/* 1268 */     } else if (this.colorType == 2) {
/* 1269 */       this.redTransparentAlpha = chunk.getInt2(0);
/* 1270 */       this.greenTransparentAlpha = chunk.getInt2(2);
/* 1271 */       this.blueTransparentAlpha = chunk.getInt2(4);
/*      */       
/* 1273 */       if (!this.suppressAlpha) {
/* 1274 */         this.outputBands = 4;
/* 1275 */         this.postProcess = 7;
/*      */         
/* 1277 */         if (this.encodeParam != null) {
/* 1278 */           int[] rgbTrans = new int[3];
/* 1279 */           rgbTrans[0] = this.redTransparentAlpha;
/* 1280 */           rgbTrans[1] = this.greenTransparentAlpha;
/* 1281 */           rgbTrans[2] = this.blueTransparentAlpha;
/* 1282 */           ((PNGEncodeParam.RGB)this.encodeParam).setTransparentRGB(rgbTrans);
/*      */         } 
/*      */       } 
/* 1285 */     } else if (this.colorType == 4 || this.colorType == 6) {
/*      */ 
/*      */       
/* 1288 */       String msg = PropertyUtil.getString("PNGImageDecoder15");
/* 1289 */       throw new RuntimeException(msg);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void parse_zTXt_chunk(PNGChunk chunk) {
/* 1295 */     int textIndex = 0;
/* 1296 */     StringBuffer key = new StringBuffer();
/*      */     byte b;
/* 1298 */     while ((b = chunk.getByte(textIndex++)) != 0) {
/* 1299 */       key.append((char)b);
/*      */     }
/*      */ 
/*      */     
/* 1303 */     textIndex++;
/*      */     
/* 1305 */     StringBuffer value = new StringBuffer();
/*      */     try {
/* 1307 */       int length = chunk.getLength() - textIndex;
/* 1308 */       byte[] data = chunk.getData();
/* 1309 */       InputStream cis = new ByteArrayInputStream(data, textIndex, length);
/*      */       
/* 1311 */       InputStream iis = new InflaterInputStream(cis);
/*      */       
/*      */       int c;
/* 1314 */       while ((c = iis.read()) != -1) {
/* 1315 */         value.append((char)c);
/*      */       }
/*      */       
/* 1318 */       this.ztextKeys.add(key.toString());
/* 1319 */       this.ztextStrings.add(value.toString());
/* 1320 */     } catch (Exception e) {
/* 1321 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private WritableRaster createRaster(int width, int height, int bands, int scanlineStride, int bitDepth) {
/* 1330 */     WritableRaster ras = null;
/* 1331 */     Point origin = new Point(0, 0);
/* 1332 */     if (bitDepth < 8 && bands == 1) {
/* 1333 */       DataBuffer dataBuffer = new DataBufferByte(height * scanlineStride);
/* 1334 */       ras = Raster.createPackedRaster(dataBuffer, width, height, bitDepth, origin);
/*      */ 
/*      */     
/*      */     }
/* 1338 */     else if (bitDepth <= 8) {
/* 1339 */       DataBuffer dataBuffer = new DataBufferByte(height * scanlineStride);
/* 1340 */       ras = Raster.createInterleavedRaster(dataBuffer, width, height, scanlineStride, bands, this.bandOffsets[bands], origin);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1347 */       DataBuffer dataBuffer = new DataBufferUShort(height * scanlineStride);
/* 1348 */       ras = Raster.createInterleavedRaster(dataBuffer, width, height, scanlineStride, bands, this.bandOffsets[bands], origin);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1356 */     return ras;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void decodeSubFilter(byte[] curr, int count, int bpp) {
/* 1362 */     for (int i = bpp; i < count; i++) {
/*      */ 
/*      */       
/* 1365 */       int val = curr[i] & 0xFF;
/* 1366 */       val += curr[i - bpp] & 0xFF;
/*      */       
/* 1368 */       curr[i] = (byte)val;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void decodeUpFilter(byte[] curr, byte[] prev, int count) {
/* 1374 */     for (int i = 0; i < count; i++) {
/* 1375 */       int raw = curr[i] & 0xFF;
/* 1376 */       int prior = prev[i] & 0xFF;
/*      */       
/* 1378 */       curr[i] = (byte)(raw + prior);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void decodeAverageFilter(byte[] curr, byte[] prev, int count, int bpp) {
/*      */     int i;
/* 1388 */     for (i = 0; i < bpp; i++) {
/* 1389 */       int raw = curr[i] & 0xFF;
/* 1390 */       int priorRow = prev[i] & 0xFF;
/*      */       
/* 1392 */       curr[i] = (byte)(raw + priorRow / 2);
/*      */     } 
/*      */     
/* 1395 */     for (i = bpp; i < count; i++) {
/* 1396 */       int raw = curr[i] & 0xFF;
/* 1397 */       int priorPixel = curr[i - bpp] & 0xFF;
/* 1398 */       int priorRow = prev[i] & 0xFF;
/*      */       
/* 1400 */       curr[i] = (byte)(raw + (priorPixel + priorRow) / 2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void decodePaethFilter(byte[] curr, byte[] prev, int count, int bpp) {
/*      */     int i;
/* 1411 */     for (i = 0; i < bpp; i++) {
/* 1412 */       int raw = curr[i] & 0xFF;
/* 1413 */       int priorRow = prev[i] & 0xFF;
/*      */       
/* 1415 */       curr[i] = (byte)(raw + priorRow);
/*      */     } 
/*      */     
/* 1418 */     for (i = bpp; i < count; i++) {
/* 1419 */       int raw = curr[i] & 0xFF;
/* 1420 */       int priorPixel = curr[i - bpp] & 0xFF;
/* 1421 */       int priorRow = prev[i] & 0xFF;
/* 1422 */       int priorRowPixel = prev[i - bpp] & 0xFF;
/*      */       
/* 1424 */       curr[i] = (byte)(raw + PNGEncodeParam.paethPredictor(priorPixel, priorRow, priorRowPixel));
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
/*      */   private void processPixels(int process, Raster src, WritableRaster dst, int xOffset, int step, int y, int width) {
/* 1437 */     int srcX, ps[] = src.getPixel(0, 0, (int[])null);
/* 1438 */     int[] pd = dst.getPixel(0, 0, (int[])null);
/*      */     
/* 1440 */     int dstX = xOffset;
/* 1441 */     switch (process) {
/*      */       case 0:
/* 1443 */         for (srcX = 0; srcX < width; srcX++) {
/* 1444 */           src.getPixel(srcX, 0, ps);
/* 1445 */           dst.setPixel(dstX, y, ps);
/* 1446 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 1:
/* 1451 */         for (srcX = 0; srcX < width; srcX++) {
/* 1452 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1454 */           for (int i = 0; i < this.inputBands; i++) {
/* 1455 */             int x = ps[i];
/* 1456 */             ps[i] = this.gammaLut[x];
/*      */           } 
/*      */           
/* 1459 */           dst.setPixel(dstX, y, ps);
/* 1460 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 2:
/* 1465 */         for (srcX = 0; srcX < width; srcX++) {
/* 1466 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1468 */           pd[0] = this.grayLut[ps[0]];
/*      */           
/* 1470 */           dst.setPixel(dstX, y, pd);
/* 1471 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 3:
/* 1476 */         for (srcX = 0; srcX < width; srcX++) {
/* 1477 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1479 */           int val = ps[0];
/* 1480 */           pd[0] = this.grayLut[val];
/* 1481 */           if (val == this.grayTransparentAlpha) {
/* 1482 */             pd[1] = 0;
/*      */           } else {
/* 1484 */             pd[1] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1487 */           dst.setPixel(dstX, y, pd);
/* 1488 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 4:
/* 1493 */         for (srcX = 0; srcX < width; srcX++) {
/* 1494 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1496 */           int val = ps[0];
/* 1497 */           pd[0] = this.redPalette[val];
/* 1498 */           pd[1] = this.greenPalette[val];
/* 1499 */           pd[2] = this.bluePalette[val];
/*      */           
/* 1501 */           dst.setPixel(dstX, y, pd);
/* 1502 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 5:
/* 1507 */         for (srcX = 0; srcX < width; srcX++) {
/* 1508 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1510 */           int val = ps[0];
/* 1511 */           pd[0] = this.redPalette[val];
/* 1512 */           pd[1] = this.greenPalette[val];
/* 1513 */           pd[2] = this.bluePalette[val];
/* 1514 */           pd[3] = this.alphaPalette[val];
/*      */           
/* 1516 */           dst.setPixel(dstX, y, pd);
/* 1517 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 6:
/* 1522 */         for (srcX = 0; srcX < width; srcX++) {
/* 1523 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1525 */           int val = ps[0];
/* 1526 */           if (this.performGammaCorrection) {
/* 1527 */             val = this.gammaLut[val];
/*      */           }
/* 1529 */           pd[0] = val;
/* 1530 */           if (val == this.grayTransparentAlpha) {
/* 1531 */             pd[1] = 0;
/*      */           } else {
/* 1533 */             pd[1] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1536 */           dst.setPixel(dstX, y, pd);
/* 1537 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 7:
/* 1542 */         for (srcX = 0; srcX < width; srcX++) {
/* 1543 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1545 */           int r = ps[0];
/* 1546 */           int g = ps[1];
/* 1547 */           int b = ps[2];
/* 1548 */           if (this.performGammaCorrection) {
/* 1549 */             pd[0] = this.gammaLut[r];
/* 1550 */             pd[1] = this.gammaLut[g];
/* 1551 */             pd[2] = this.gammaLut[b];
/*      */           } else {
/* 1553 */             pd[0] = r;
/* 1554 */             pd[1] = g;
/* 1555 */             pd[2] = b;
/*      */           } 
/* 1557 */           if (r == this.redTransparentAlpha && g == this.greenTransparentAlpha && b == this.blueTransparentAlpha) {
/*      */ 
/*      */             
/* 1560 */             pd[3] = 0;
/*      */           } else {
/* 1562 */             pd[3] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1565 */           dst.setPixel(dstX, y, pd);
/* 1566 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 8:
/* 1571 */         for (srcX = 0; srcX < width; srcX++) {
/* 1572 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1574 */           int g = ps[0];
/* 1575 */           if (this.performGammaCorrection) {
/* 1576 */             pd[0] = this.gammaLut[g];
/*      */           } else {
/* 1578 */             pd[0] = g;
/*      */           } 
/*      */           
/* 1581 */           dst.setPixel(dstX, y, pd);
/* 1582 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 9:
/* 1587 */         for (srcX = 0; srcX < width; srcX++) {
/* 1588 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1590 */           int r = ps[0];
/* 1591 */           int g = ps[1];
/* 1592 */           int b = ps[2];
/* 1593 */           if (this.performGammaCorrection) {
/* 1594 */             pd[0] = this.gammaLut[r];
/* 1595 */             pd[1] = this.gammaLut[g];
/* 1596 */             pd[2] = this.gammaLut[b];
/*      */           } else {
/* 1598 */             pd[0] = r;
/* 1599 */             pd[1] = g;
/* 1600 */             pd[2] = b;
/*      */           } 
/*      */           
/* 1603 */           dst.setPixel(dstX, y, pd);
/* 1604 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 17:
/* 1609 */         for (srcX = 0; srcX < width; srcX++) {
/* 1610 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1612 */           int val = ps[0];
/* 1613 */           int alpha = ps[1];
/* 1614 */           int gamma = this.gammaLut[val];
/* 1615 */           pd[0] = gamma;
/* 1616 */           pd[1] = gamma;
/* 1617 */           pd[2] = gamma;
/* 1618 */           pd[3] = alpha;
/*      */           
/* 1620 */           dst.setPixel(dstX, y, pd);
/* 1621 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 16:
/* 1626 */         for (srcX = 0; srcX < width; srcX++) {
/* 1627 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1629 */           int val = ps[0];
/* 1630 */           int alpha = ps[1];
/* 1631 */           pd[0] = val;
/* 1632 */           pd[1] = val;
/* 1633 */           pd[2] = val;
/* 1634 */           pd[3] = alpha;
/*      */           
/* 1636 */           dst.setPixel(dstX, y, pd);
/* 1637 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 22:
/* 1642 */         for (srcX = 0; srcX < width; srcX++) {
/* 1643 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1645 */           int val = ps[0];
/* 1646 */           if (this.performGammaCorrection) {
/* 1647 */             val = this.gammaLut[val];
/*      */           }
/* 1649 */           pd[0] = val;
/* 1650 */           pd[1] = val;
/* 1651 */           pd[2] = val;
/* 1652 */           if (val == this.grayTransparentAlpha) {
/* 1653 */             pd[3] = 0;
/*      */           } else {
/* 1655 */             pd[3] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1658 */           dst.setPixel(dstX, y, pd);
/* 1659 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 19:
/* 1664 */         for (srcX = 0; srcX < width; srcX++) {
/* 1665 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1667 */           int val = ps[0];
/* 1668 */           int val2 = this.grayLut[val];
/* 1669 */           pd[0] = val2;
/* 1670 */           pd[1] = val2;
/* 1671 */           pd[2] = val2;
/* 1672 */           if (val == this.grayTransparentAlpha) {
/* 1673 */             pd[3] = 0;
/*      */           } else {
/* 1675 */             pd[3] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1678 */           dst.setPixel(dstX, y, pd);
/* 1679 */           dstX += step;
/*      */         } 
/*      */         break;
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
/*      */   private void decodePass(WritableRaster imRas, int xOffset, int yOffset, int xStep, int yStep, int passWidth, int passHeight) {
/* 1693 */     if (passWidth == 0 || passHeight == 0) {
/*      */       return;
/*      */     }
/*      */     
/* 1697 */     int bytesPerRow = (this.inputBands * passWidth * this.bitDepth + 7) / 8;
/* 1698 */     int eltsPerRow = (this.bitDepth == 16) ? (bytesPerRow / 2) : bytesPerRow;
/* 1699 */     byte[] curr = new byte[bytesPerRow];
/* 1700 */     byte[] prior = new byte[bytesPerRow];
/*      */ 
/*      */     
/* 1703 */     WritableRaster passRow = createRaster(passWidth, 1, this.inputBands, eltsPerRow, this.bitDepth);
/*      */ 
/*      */ 
/*      */     
/* 1707 */     DataBuffer dataBuffer = passRow.getDataBuffer();
/* 1708 */     int type = dataBuffer.getDataType();
/* 1709 */     byte[] byteData = null;
/* 1710 */     short[] shortData = null;
/* 1711 */     if (type == 0) {
/* 1712 */       byteData = ((DataBufferByte)dataBuffer).getData();
/*      */     } else {
/* 1714 */       shortData = ((DataBufferUShort)dataBuffer).getData();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1720 */     int srcY = 0, dstY = yOffset;
/* 1721 */     for (; srcY < passHeight; 
/* 1722 */       srcY++, dstY += yStep) {
/*      */       String msg;
/* 1724 */       int filter = 0;
/*      */       try {
/* 1726 */         filter = this.dataStream.read();
/* 1727 */         this.dataStream.readFully(curr, 0, bytesPerRow);
/* 1728 */       } catch (Exception e) {
/* 1729 */         e.printStackTrace();
/*      */       } 
/*      */       
/* 1732 */       switch (filter) {
/*      */         case 0:
/*      */           break;
/*      */         case 1:
/* 1736 */           decodeSubFilter(curr, bytesPerRow, this.bytesPerPixel);
/*      */           break;
/*      */         case 2:
/* 1739 */           decodeUpFilter(curr, prior, bytesPerRow);
/*      */           break;
/*      */         case 3:
/* 1742 */           decodeAverageFilter(curr, prior, bytesPerRow, this.bytesPerPixel);
/*      */           break;
/*      */         case 4:
/* 1745 */           decodePaethFilter(curr, prior, bytesPerRow, this.bytesPerPixel);
/*      */           break;
/*      */         
/*      */         default:
/* 1749 */           msg = PropertyUtil.getString("PNGImageDecoder16");
/* 1750 */           throw new RuntimeException(msg);
/*      */       } 
/*      */ 
/*      */       
/* 1754 */       if (this.bitDepth < 16) {
/* 1755 */         System.arraycopy(curr, 0, byteData, 0, bytesPerRow);
/*      */       } else {
/* 1757 */         int idx = 0;
/* 1758 */         for (int j = 0; j < eltsPerRow; j++) {
/* 1759 */           shortData[j] = (short)(curr[idx] << 8 | curr[idx + 1] & 0xFF);
/*      */           
/* 1761 */           idx += 2;
/*      */         } 
/*      */       } 
/*      */       
/* 1765 */       processPixels(this.postProcess, passRow, imRas, xOffset, xStep, dstY, passWidth);
/*      */ 
/*      */ 
/*      */       
/* 1769 */       byte[] tmp = prior;
/* 1770 */       prior = curr;
/* 1771 */       curr = tmp;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void decodeImage(boolean useInterlacing) {
/* 1776 */     if (!useInterlacing) {
/* 1777 */       decodePass(this.theTile, 0, 0, 1, 1, this.width, this.height);
/*      */     } else {
/* 1779 */       decodePass(this.theTile, 0, 0, 8, 8, (this.width + 7) / 8, (this.height + 7) / 8);
/* 1780 */       decodePass(this.theTile, 4, 0, 8, 8, (this.width + 3) / 8, (this.height + 7) / 8);
/* 1781 */       decodePass(this.theTile, 0, 4, 4, 8, (this.width + 3) / 4, (this.height + 3) / 8);
/* 1782 */       decodePass(this.theTile, 2, 0, 4, 4, (this.width + 1) / 4, (this.height + 3) / 4);
/* 1783 */       decodePass(this.theTile, 0, 2, 2, 4, (this.width + 1) / 2, (this.height + 1) / 4);
/* 1784 */       decodePass(this.theTile, 1, 0, 2, 2, this.width / 2, (this.height + 1) / 2);
/* 1785 */       decodePass(this.theTile, 0, 1, 1, 2, this.width, this.height / 2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Raster getTile(int tileX, int tileY) {
/* 1792 */     if (tileX != 0 || tileY != 0) {
/*      */       
/* 1794 */       String msg = PropertyUtil.getString("PNGImageDecoder17");
/* 1795 */       throw new IllegalArgumentException(msg);
/*      */     } 
/* 1797 */     return this.theTile;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/png/PNGImage.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */