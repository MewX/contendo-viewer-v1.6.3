/*      */ package org.apache.batik.ext.awt.image.codec.png;
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
/*      */ import java.util.TimeZone;
/*      */ import java.util.zip.Inflater;
/*      */ import java.util.zip.InflaterInputStream;
/*      */ import org.apache.batik.ext.awt.image.codec.util.PropertyUtil;
/*      */ import org.apache.batik.ext.awt.image.codec.util.SimpleRenderedImage;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */ {
/*      */   public static final int PNG_COLOR_GRAY = 0;
/*      */   public static final int PNG_COLOR_RGB = 2;
/*      */   public static final int PNG_COLOR_PALETTE = 3;
/*      */   public static final int PNG_COLOR_GRAY_ALPHA = 4;
/*      */   public static final int PNG_COLOR_RGB_ALPHA = 6;
/*  156 */   private static final String[] colorTypeNames = new String[] { "Grayscale", "Error", "Truecolor", "Index", "Grayscale with alpha", "Error", "Truecolor with alpha" };
/*      */   
/*      */   public static final int PNG_FILTER_NONE = 0;
/*      */   
/*      */   public static final int PNG_FILTER_SUB = 1;
/*      */   
/*      */   public static final int PNG_FILTER_UP = 2;
/*      */   
/*      */   public static final int PNG_FILTER_AVERAGE = 3;
/*      */   
/*      */   public static final int PNG_FILTER_PAETH = 4;
/*  167 */   private int[][] bandOffsets = new int[][] { null, { 0 }, { 0, 1 }, { 0, 1, 2 }, { 0, 1, 2, 3 } };
/*      */   
/*      */   private int bitDepth;
/*      */   
/*      */   private int colorType;
/*      */   
/*      */   private int compressionMethod;
/*      */   
/*      */   private int filterMethod;
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
/*      */   private int grayTransparentAlpha;
/*      */   private int redTransparentAlpha;
/*      */   private int greenTransparentAlpha;
/*      */   private int blueTransparentAlpha;
/*      */   private int maxOpacity;
/*  199 */   private int[] significantBits = null;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean suppressAlpha = false;
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean expandPalette = false;
/*      */ 
/*      */   
/*      */   private boolean output8BitGray = false;
/*      */ 
/*      */   
/*      */   private boolean outputHasAlphaPalette = false;
/*      */ 
/*      */   
/*      */   private boolean performGammaCorrection = false;
/*      */ 
/*      */   
/*      */   private boolean expandGrayAlpha = false;
/*      */ 
/*      */   
/*      */   private boolean generateEncodeParam = false;
/*      */ 
/*      */   
/*  225 */   private PNGDecodeParam decodeParam = null;
/*      */ 
/*      */   
/*  228 */   private PNGEncodeParam encodeParam = null;
/*      */   
/*      */   private boolean emitProperties = true;
/*      */   
/*  232 */   private float fileGamma = 0.45455F;
/*      */   
/*  234 */   private float userExponent = 1.0F;
/*      */   
/*  236 */   private float displayExponent = 2.2F;
/*      */   
/*  238 */   private float[] chromaticity = null;
/*      */   
/*  240 */   private int sRGBRenderingIntent = -1;
/*      */ 
/*      */   
/*  243 */   private int postProcess = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int POST_NONE = 0;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int POST_GAMMA = 1;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int POST_GRAY_LUT = 2;
/*      */ 
/*      */ 
/*      */   
/*      */   private static final int POST_GRAY_LUT_ADD_TRANS = 3;
/*      */ 
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
/*  296 */   private List streamVec = new ArrayList();
/*      */   
/*      */   private DataInputStream dataStream;
/*      */   
/*      */   private int bytesPerPixel;
/*      */   
/*      */   private int inputBands;
/*      */   private int outputBands;
/*  304 */   private int chunkIndex = 0;
/*      */   
/*  306 */   private List textKeys = new ArrayList();
/*  307 */   private List textStrings = new ArrayList();
/*      */   
/*  309 */   private List ztextKeys = new ArrayList();
/*  310 */   private List ztextStrings = new ArrayList();
/*      */   
/*      */   private WritableRaster theTile;
/*      */   
/*  314 */   private int[] gammaLut = null;
/*      */   
/*      */   private void initGammaLut(int bits) {
/*  317 */     double exp = this.userExponent / (this.fileGamma * this.displayExponent);
/*  318 */     int numSamples = 1 << bits;
/*  319 */     int maxOutSample = (bits == 16) ? 65535 : 255;
/*      */     
/*  321 */     this.gammaLut = new int[numSamples];
/*  322 */     for (int i = 0; i < numSamples; i++) {
/*  323 */       double gbright = i / (numSamples - 1);
/*  324 */       double gamma = Math.pow(gbright, exp);
/*  325 */       int igamma = (int)(gamma * maxOutSample + 0.5D);
/*  326 */       if (igamma > maxOutSample) {
/*  327 */         igamma = maxOutSample;
/*      */       }
/*  329 */       this.gammaLut[i] = igamma;
/*      */     } 
/*      */   }
/*      */   
/*  333 */   private final byte[][] expandBits = new byte[][] { null, { 0, -1 }, { 0, 85, -86, -1 }, null, { 0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1 } };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  344 */   private int[] grayLut = null;
/*      */   
/*      */   private void initGrayLut(int bits) {
/*  347 */     int len = 1 << bits;
/*  348 */     this.grayLut = new int[len];
/*      */     
/*  350 */     if (this.performGammaCorrection) {
/*  351 */       System.arraycopy(this.gammaLut, 0, this.grayLut, 0, len);
/*      */     } else {
/*  353 */       for (int i = 0; i < len; i++) {
/*  354 */         this.grayLut[i] = this.expandBits[bits][i];
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public PNGImage(InputStream stream, PNGDecodeParam decodeParam) throws IOException {
/*  362 */     if (!stream.markSupported()) {
/*  363 */       stream = new BufferedInputStream(stream);
/*      */     }
/*  365 */     DataInputStream distream = new DataInputStream(stream);
/*      */     
/*  367 */     if (decodeParam == null) {
/*  368 */       decodeParam = new PNGDecodeParam();
/*      */     }
/*  370 */     this.decodeParam = decodeParam;
/*      */ 
/*      */     
/*  373 */     this.suppressAlpha = decodeParam.getSuppressAlpha();
/*  374 */     this.expandPalette = decodeParam.getExpandPalette();
/*  375 */     this.output8BitGray = decodeParam.getOutput8BitGray();
/*  376 */     this.expandGrayAlpha = decodeParam.getExpandGrayAlpha();
/*  377 */     if (decodeParam.getPerformGammaCorrection()) {
/*  378 */       this.userExponent = decodeParam.getUserExponent();
/*  379 */       this.displayExponent = decodeParam.getDisplayExponent();
/*  380 */       this.performGammaCorrection = true;
/*  381 */       this.output8BitGray = true;
/*      */     } 
/*  383 */     this.generateEncodeParam = decodeParam.getGenerateEncodeParam();
/*      */     
/*  385 */     if (this.emitProperties) {
/*  386 */       this.properties.put("file_type", "PNG v. 1.0");
/*      */     }
/*      */     
/*      */     try {
/*  390 */       long magic = distream.readLong();
/*  391 */       if (magic != -8552249625308161526L) {
/*  392 */         String msg = PropertyUtil.getString("PNGImageDecoder0");
/*  393 */         throw new RuntimeException(msg);
/*      */       } 
/*  395 */     } catch (Exception e) {
/*  396 */       e.printStackTrace();
/*  397 */       String msg = PropertyUtil.getString("PNGImageDecoder1");
/*  398 */       throw new RuntimeException(msg);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*      */       while (true) {
/*  405 */         String chunkType = getChunkType(distream);
/*  406 */         if (chunkType.equals("IHDR")) {
/*  407 */           PNGChunk pNGChunk = readChunk(distream);
/*  408 */           parse_IHDR_chunk(pNGChunk); continue;
/*  409 */         }  if (chunkType.equals("PLTE")) {
/*  410 */           PNGChunk pNGChunk = readChunk(distream);
/*  411 */           parse_PLTE_chunk(pNGChunk); continue;
/*  412 */         }  if (chunkType.equals("IDAT")) {
/*  413 */           PNGChunk pNGChunk = readChunk(distream);
/*  414 */           this.streamVec.add(new ByteArrayInputStream(pNGChunk.getData())); continue;
/*  415 */         }  if (chunkType.equals("IEND")) {
/*  416 */           PNGChunk pNGChunk = readChunk(distream);
/*  417 */           parse_IEND_chunk(pNGChunk); break;
/*      */         } 
/*  419 */         if (chunkType.equals("bKGD")) {
/*  420 */           PNGChunk pNGChunk = readChunk(distream);
/*  421 */           parse_bKGD_chunk(pNGChunk); continue;
/*  422 */         }  if (chunkType.equals("cHRM")) {
/*  423 */           PNGChunk pNGChunk = readChunk(distream);
/*  424 */           parse_cHRM_chunk(pNGChunk); continue;
/*  425 */         }  if (chunkType.equals("gAMA")) {
/*  426 */           PNGChunk pNGChunk = readChunk(distream);
/*  427 */           parse_gAMA_chunk(pNGChunk); continue;
/*  428 */         }  if (chunkType.equals("hIST")) {
/*  429 */           PNGChunk pNGChunk = readChunk(distream);
/*  430 */           parse_hIST_chunk(pNGChunk); continue;
/*  431 */         }  if (chunkType.equals("iCCP")) {
/*  432 */           PNGChunk pNGChunk = readChunk(distream);
/*  433 */           parse_iCCP_chunk(pNGChunk); continue;
/*  434 */         }  if (chunkType.equals("pHYs")) {
/*  435 */           PNGChunk pNGChunk = readChunk(distream);
/*  436 */           parse_pHYs_chunk(pNGChunk); continue;
/*  437 */         }  if (chunkType.equals("sBIT")) {
/*  438 */           PNGChunk pNGChunk = readChunk(distream);
/*  439 */           parse_sBIT_chunk(pNGChunk); continue;
/*  440 */         }  if (chunkType.equals("sRGB")) {
/*  441 */           PNGChunk pNGChunk = readChunk(distream);
/*  442 */           parse_sRGB_chunk(pNGChunk); continue;
/*  443 */         }  if (chunkType.equals("tEXt")) {
/*  444 */           PNGChunk pNGChunk = readChunk(distream);
/*  445 */           parse_tEXt_chunk(pNGChunk); continue;
/*  446 */         }  if (chunkType.equals("tIME")) {
/*  447 */           PNGChunk pNGChunk = readChunk(distream);
/*  448 */           parse_tIME_chunk(pNGChunk); continue;
/*  449 */         }  if (chunkType.equals("tRNS")) {
/*  450 */           PNGChunk pNGChunk = readChunk(distream);
/*  451 */           parse_tRNS_chunk(pNGChunk); continue;
/*  452 */         }  if (chunkType.equals("zTXt")) {
/*  453 */           PNGChunk pNGChunk = readChunk(distream);
/*  454 */           parse_zTXt_chunk(pNGChunk); continue;
/*      */         } 
/*  456 */         PNGChunk chunk = readChunk(distream);
/*      */ 
/*      */         
/*  459 */         String type = chunk.getTypeString();
/*  460 */         byte[] data = chunk.getData();
/*  461 */         if (this.encodeParam != null) {
/*  462 */           this.encodeParam.addPrivateChunk(type, data);
/*      */         }
/*  464 */         if (this.emitProperties) {
/*  465 */           String key = "chunk_" + this.chunkIndex++ + ':' + type;
/*  466 */           this.properties.put(key.toLowerCase(), data);
/*      */         } 
/*      */       } 
/*  469 */     } catch (Exception e) {
/*  470 */       e.printStackTrace();
/*  471 */       String msg = PropertyUtil.getString("PNGImageDecoder2");
/*  472 */       throw new RuntimeException(msg);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  478 */     if (this.significantBits == null) {
/*  479 */       this.significantBits = new int[this.inputBands];
/*  480 */       for (int i = 0; i < this.inputBands; i++) {
/*  481 */         this.significantBits[i] = this.bitDepth;
/*      */       }
/*      */       
/*  484 */       if (this.emitProperties) {
/*  485 */         this.properties.put("significant_bits", this.significantBits);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private static String getChunkType(DataInputStream distream) {
/*      */     try {
/*  492 */       distream.mark(8);
/*  493 */       distream.readInt();
/*  494 */       int type = distream.readInt();
/*  495 */       distream.reset();
/*      */       
/*  497 */       String typeString = "";
/*  498 */       typeString = typeString + (char)(type >> 24);
/*  499 */       typeString = typeString + (char)(type >> 16 & 0xFF);
/*  500 */       typeString = typeString + (char)(type >> 8 & 0xFF);
/*  501 */       typeString = typeString + (char)(type & 0xFF);
/*  502 */       return typeString;
/*  503 */     } catch (Exception e) {
/*  504 */       e.printStackTrace();
/*  505 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static PNGChunk readChunk(DataInputStream distream) {
/*      */     try {
/*  511 */       int length = distream.readInt();
/*  512 */       int type = distream.readInt();
/*  513 */       byte[] data = new byte[length];
/*  514 */       distream.readFully(data);
/*  515 */       int crc = distream.readInt();
/*      */       
/*  517 */       return new PNGChunk(length, type, data, crc);
/*  518 */     } catch (Exception e) {
/*  519 */       e.printStackTrace();
/*  520 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parse_IHDR_chunk(PNGChunk chunk) {
/*  525 */     this.tileWidth = this.width = chunk.getInt4(0);
/*  526 */     this.tileHeight = this.height = chunk.getInt4(4);
/*      */     
/*  528 */     this.bitDepth = chunk.getInt1(8);
/*      */     
/*  530 */     if (this.bitDepth != 1 && this.bitDepth != 2 && this.bitDepth != 4 && this.bitDepth != 8 && this.bitDepth != 16) {
/*      */ 
/*      */       
/*  533 */       String msg = PropertyUtil.getString("PNGImageDecoder3");
/*  534 */       throw new RuntimeException(msg);
/*      */     } 
/*  536 */     this.maxOpacity = (1 << this.bitDepth) - 1;
/*      */     
/*  538 */     this.colorType = chunk.getInt1(9);
/*  539 */     if (this.colorType != 0 && this.colorType != 2 && this.colorType != 3 && this.colorType != 4 && this.colorType != 6)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  544 */       System.out.println(PropertyUtil.getString("PNGImageDecoder4"));
/*      */     }
/*      */     
/*  547 */     if (this.colorType == 2 && this.bitDepth < 8) {
/*      */       
/*  549 */       String msg = PropertyUtil.getString("PNGImageDecoder5");
/*  550 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  553 */     if (this.colorType == 3 && this.bitDepth == 16) {
/*      */       
/*  555 */       String msg = PropertyUtil.getString("PNGImageDecoder6");
/*  556 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  559 */     if (this.colorType == 4 && this.bitDepth < 8) {
/*      */       
/*  561 */       String msg = PropertyUtil.getString("PNGImageDecoder7");
/*  562 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  565 */     if (this.colorType == 6 && this.bitDepth < 8) {
/*      */       
/*  567 */       String msg = PropertyUtil.getString("PNGImageDecoder8");
/*  568 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  571 */     if (this.emitProperties) {
/*  572 */       this.properties.put("color_type", colorTypeNames[this.colorType]);
/*      */     }
/*      */     
/*  575 */     if (this.generateEncodeParam) {
/*  576 */       if (this.colorType == 3) {
/*  577 */         this.encodeParam = new PNGEncodeParam.Palette();
/*  578 */       } else if (this.colorType == 0 || this.colorType == 4) {
/*      */         
/*  580 */         this.encodeParam = new PNGEncodeParam.Gray();
/*      */       } else {
/*  582 */         this.encodeParam = new PNGEncodeParam.RGB();
/*      */       } 
/*  584 */       this.decodeParam.setEncodeParam(this.encodeParam);
/*      */     } 
/*      */     
/*  587 */     if (this.encodeParam != null) {
/*  588 */       this.encodeParam.setBitDepth(this.bitDepth);
/*      */     }
/*  590 */     if (this.emitProperties) {
/*  591 */       this.properties.put("bit_depth", Integer.valueOf(this.bitDepth));
/*      */     }
/*      */     
/*  594 */     if (this.performGammaCorrection) {
/*      */       
/*  596 */       float gamma = 0.45454544F * this.displayExponent / this.userExponent;
/*  597 */       if (this.encodeParam != null) {
/*  598 */         this.encodeParam.setGamma(gamma);
/*      */       }
/*  600 */       if (this.emitProperties) {
/*  601 */         this.properties.put("gamma", Float.valueOf(gamma));
/*      */       }
/*      */     } 
/*      */     
/*  605 */     this.compressionMethod = chunk.getInt1(10);
/*  606 */     if (this.compressionMethod != 0) {
/*      */       
/*  608 */       String msg = PropertyUtil.getString("PNGImageDecoder9");
/*  609 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  612 */     this.filterMethod = chunk.getInt1(11);
/*  613 */     if (this.filterMethod != 0) {
/*      */       
/*  615 */       String msg = PropertyUtil.getString("PNGImageDecoder10");
/*  616 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  619 */     this.interlaceMethod = chunk.getInt1(12);
/*  620 */     if (this.interlaceMethod == 0) {
/*  621 */       if (this.encodeParam != null) {
/*  622 */         this.encodeParam.setInterlacing(false);
/*      */       }
/*  624 */       if (this.emitProperties) {
/*  625 */         this.properties.put("interlace_method", "None");
/*      */       }
/*  627 */     } else if (this.interlaceMethod == 1) {
/*  628 */       if (this.encodeParam != null) {
/*  629 */         this.encodeParam.setInterlacing(true);
/*      */       }
/*  631 */       if (this.emitProperties) {
/*  632 */         this.properties.put("interlace_method", "Adam7");
/*      */       }
/*      */     } else {
/*      */       
/*  636 */       String msg = PropertyUtil.getString("PNGImageDecoder11");
/*  637 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  640 */     this.bytesPerPixel = (this.bitDepth == 16) ? 2 : 1;
/*      */     
/*  642 */     switch (this.colorType) {
/*      */       case 0:
/*  644 */         this.inputBands = 1;
/*  645 */         this.outputBands = 1;
/*      */         
/*  647 */         if (this.output8BitGray && this.bitDepth < 8) {
/*  648 */           this.postProcess = 2; break;
/*  649 */         }  if (this.performGammaCorrection) {
/*  650 */           this.postProcess = 1; break;
/*      */         } 
/*  652 */         this.postProcess = 0;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2:
/*  657 */         this.inputBands = 3;
/*  658 */         this.bytesPerPixel *= 3;
/*  659 */         this.outputBands = 3;
/*      */         
/*  661 */         if (this.performGammaCorrection) {
/*  662 */           this.postProcess = 1; break;
/*      */         } 
/*  664 */         this.postProcess = 0;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 3:
/*  669 */         this.inputBands = 1;
/*  670 */         this.bytesPerPixel = 1;
/*  671 */         this.outputBands = this.expandPalette ? 3 : 1;
/*      */         
/*  673 */         if (this.expandPalette) {
/*  674 */           this.postProcess = 4; break;
/*      */         } 
/*  676 */         this.postProcess = 0;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 4:
/*  681 */         this.inputBands = 2;
/*  682 */         this.bytesPerPixel *= 2;
/*      */         
/*  684 */         if (this.suppressAlpha) {
/*  685 */           this.outputBands = 1;
/*  686 */           this.postProcess = 8; break;
/*      */         } 
/*  688 */         if (this.performGammaCorrection) {
/*  689 */           this.postProcess = 1;
/*      */         } else {
/*  691 */           this.postProcess = 0;
/*      */         } 
/*  693 */         if (this.expandGrayAlpha) {
/*  694 */           this.postProcess |= 0x10;
/*  695 */           this.outputBands = 4; break;
/*      */         } 
/*  697 */         this.outputBands = 2;
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 6:
/*  703 */         this.inputBands = 4;
/*  704 */         this.bytesPerPixel *= 4;
/*  705 */         this.outputBands = !this.suppressAlpha ? 4 : 3;
/*      */         
/*  707 */         if (this.suppressAlpha) {
/*  708 */           this.postProcess = 9; break;
/*  709 */         }  if (this.performGammaCorrection) {
/*  710 */           this.postProcess = 1; break;
/*      */         } 
/*  712 */         this.postProcess = 0;
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void parse_IEND_chunk(PNGChunk chunk) throws Exception {
/*  720 */     int textLen = this.textKeys.size();
/*  721 */     String[] textArray = new String[2 * textLen];
/*  722 */     for (int i = 0; i < textLen; i++) {
/*  723 */       String key = this.textKeys.get(i);
/*  724 */       String val = this.textStrings.get(i);
/*  725 */       textArray[2 * i] = key;
/*  726 */       textArray[2 * i + 1] = val;
/*  727 */       if (this.emitProperties) {
/*  728 */         String uniqueKey = "text_" + i + ':' + key;
/*  729 */         this.properties.put(uniqueKey.toLowerCase(), val);
/*      */       } 
/*      */     } 
/*  732 */     if (this.encodeParam != null) {
/*  733 */       this.encodeParam.setText(textArray);
/*      */     }
/*      */ 
/*      */     
/*  737 */     int ztextLen = this.ztextKeys.size();
/*  738 */     String[] ztextArray = new String[2 * ztextLen];
/*  739 */     for (int j = 0; j < ztextLen; j++) {
/*  740 */       String key = this.ztextKeys.get(j);
/*  741 */       String val = this.ztextStrings.get(j);
/*  742 */       ztextArray[2 * j] = key;
/*  743 */       ztextArray[2 * j + 1] = val;
/*  744 */       if (this.emitProperties) {
/*  745 */         String uniqueKey = "ztext_" + j + ':' + key;
/*  746 */         this.properties.put(uniqueKey.toLowerCase(), val);
/*      */       } 
/*      */     } 
/*  749 */     if (this.encodeParam != null) {
/*  750 */       this.encodeParam.setCompressedText(ztextArray);
/*      */     }
/*      */ 
/*      */     
/*  754 */     InputStream seqStream = new SequenceInputStream(Collections.enumeration(this.streamVec));
/*      */     
/*  756 */     InputStream infStream = new InflaterInputStream(seqStream, new Inflater());
/*      */     
/*  758 */     this.dataStream = new DataInputStream(infStream);
/*      */ 
/*      */     
/*  761 */     int depth = this.bitDepth;
/*  762 */     if (this.colorType == 0 && this.bitDepth < 8 && this.output8BitGray)
/*      */     {
/*  764 */       depth = 8;
/*      */     }
/*  766 */     if (this.colorType == 3 && this.expandPalette) {
/*  767 */       depth = 8;
/*      */     }
/*  769 */     int bytesPerRow = (this.outputBands * this.width * depth + 7) / 8;
/*  770 */     int scanlineStride = (depth == 16) ? (bytesPerRow / 2) : bytesPerRow;
/*      */ 
/*      */     
/*  773 */     this.theTile = createRaster(this.width, this.height, this.outputBands, scanlineStride, depth);
/*      */ 
/*      */ 
/*      */     
/*  777 */     if (this.performGammaCorrection && this.gammaLut == null) {
/*  778 */       initGammaLut(this.bitDepth);
/*      */     }
/*  780 */     if (this.postProcess == 2 || this.postProcess == 3 || this.postProcess == 19)
/*      */     {
/*      */       
/*  783 */       initGrayLut(this.bitDepth);
/*      */     }
/*      */     
/*  786 */     decodeImage((this.interlaceMethod == 1));
/*  787 */     this.sampleModel = this.theTile.getSampleModel();
/*      */     
/*  789 */     if (this.colorType == 3 && !this.expandPalette) {
/*  790 */       if (this.outputHasAlphaPalette) {
/*  791 */         this.colorModel = new IndexColorModel(this.bitDepth, this.paletteEntries, this.redPalette, this.greenPalette, this.bluePalette, this.alphaPalette);
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  798 */         this.colorModel = new IndexColorModel(this.bitDepth, this.paletteEntries, this.redPalette, this.greenPalette, this.bluePalette);
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  804 */     else if (this.colorType == 0 && this.bitDepth < 8 && !this.output8BitGray) {
/*      */       
/*  806 */       byte[] palette = this.expandBits[this.bitDepth];
/*  807 */       this.colorModel = new IndexColorModel(this.bitDepth, palette.length, palette, palette, palette);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  813 */       this.colorModel = createComponentColorModel(this.sampleModel);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*  818 */   private static final int[] GrayBits8 = new int[] { 8 };
/*  819 */   private static final ComponentColorModel colorModelGray8 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayBits8, false, false, 1, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  825 */   private static final int[] GrayAlphaBits8 = new int[] { 8, 8 };
/*  826 */   private static final ComponentColorModel colorModelGrayAlpha8 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayAlphaBits8, true, false, 3, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  832 */   private static final int[] GrayBits16 = new int[] { 16 };
/*  833 */   private static final ComponentColorModel colorModelGray16 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayBits16, false, false, 1, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  839 */   private static final int[] GrayAlphaBits16 = new int[] { 16, 16 };
/*  840 */   private static final ComponentColorModel colorModelGrayAlpha16 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayAlphaBits16, true, false, 3, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  846 */   private static final int[] GrayBits32 = new int[] { 32 };
/*  847 */   private static final ComponentColorModel colorModelGray32 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayBits32, false, false, 1, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  853 */   private static final int[] GrayAlphaBits32 = new int[] { 32, 32 };
/*  854 */   private static final ComponentColorModel colorModelGrayAlpha32 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayAlphaBits32, true, false, 3, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  860 */   private static final int[] RGBBits8 = new int[] { 8, 8, 8 };
/*  861 */   private static final ComponentColorModel colorModelRGB8 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBBits8, false, false, 1, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  867 */   private static final int[] RGBABits8 = new int[] { 8, 8, 8, 8 };
/*  868 */   private static final ComponentColorModel colorModelRGBA8 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBABits8, true, false, 3, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  874 */   private static final int[] RGBBits16 = new int[] { 16, 16, 16 };
/*  875 */   private static final ComponentColorModel colorModelRGB16 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBBits16, false, false, 1, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  881 */   private static final int[] RGBABits16 = new int[] { 16, 16, 16, 16 };
/*  882 */   private static final ComponentColorModel colorModelRGBA16 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBABits16, true, false, 3, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  888 */   private static final int[] RGBBits32 = new int[] { 32, 32, 32 };
/*  889 */   private static final ComponentColorModel colorModelRGB32 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBBits32, false, false, 1, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  895 */   private static final int[] RGBABits32 = new int[] { 32, 32, 32, 32 };
/*  896 */   private static final ComponentColorModel colorModelRGBA32 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBABits32, true, false, 3, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  912 */     int type = sm.getDataType();
/*  913 */     int bands = sm.getNumBands();
/*  914 */     ComponentColorModel cm = null;
/*      */     
/*  916 */     if (type == 0) {
/*  917 */       switch (bands) {
/*      */         case 1:
/*  919 */           cm = colorModelGray8;
/*      */           break;
/*      */         case 2:
/*  922 */           cm = colorModelGrayAlpha8;
/*      */           break;
/*      */         case 3:
/*  925 */           cm = colorModelRGB8;
/*      */           break;
/*      */         case 4:
/*  928 */           cm = colorModelRGBA8;
/*      */           break;
/*      */       } 
/*  931 */     } else if (type == 1) {
/*  932 */       switch (bands) {
/*      */         case 1:
/*  934 */           cm = colorModelGray16;
/*      */           break;
/*      */         case 2:
/*  937 */           cm = colorModelGrayAlpha16;
/*      */           break;
/*      */         case 3:
/*  940 */           cm = colorModelRGB16;
/*      */           break;
/*      */         case 4:
/*  943 */           cm = colorModelRGBA16;
/*      */           break;
/*      */       } 
/*  946 */     } else if (type == 3) {
/*  947 */       switch (bands) {
/*      */         case 1:
/*  949 */           cm = colorModelGray32;
/*      */           break;
/*      */         case 2:
/*  952 */           cm = colorModelGrayAlpha32;
/*      */           break;
/*      */         case 3:
/*  955 */           cm = colorModelRGB32;
/*      */           break;
/*      */         case 4:
/*  958 */           cm = colorModelRGBA32;
/*      */           break;
/*      */       } 
/*      */     
/*      */     } 
/*  963 */     return cm;
/*      */   }
/*      */   
/*      */   private void parse_PLTE_chunk(PNGChunk chunk) {
/*  967 */     this.paletteEntries = chunk.getLength() / 3;
/*  968 */     this.redPalette = new byte[this.paletteEntries];
/*  969 */     this.greenPalette = new byte[this.paletteEntries];
/*  970 */     this.bluePalette = new byte[this.paletteEntries];
/*      */     
/*  972 */     int pltIndex = 0;
/*      */ 
/*      */     
/*  975 */     if (this.performGammaCorrection) {
/*  976 */       if (this.gammaLut == null) {
/*  977 */         initGammaLut((this.bitDepth == 16) ? 16 : 8);
/*      */       }
/*      */       
/*  980 */       for (int i = 0; i < this.paletteEntries; i++) {
/*  981 */         byte r = chunk.getByte(pltIndex++);
/*  982 */         byte g = chunk.getByte(pltIndex++);
/*  983 */         byte b = chunk.getByte(pltIndex++);
/*      */         
/*  985 */         this.redPalette[i] = (byte)this.gammaLut[r & 0xFF];
/*  986 */         this.greenPalette[i] = (byte)this.gammaLut[g & 0xFF];
/*  987 */         this.bluePalette[i] = (byte)this.gammaLut[b & 0xFF];
/*      */       } 
/*      */     } else {
/*  990 */       for (int i = 0; i < this.paletteEntries; i++) {
/*  991 */         this.redPalette[i] = chunk.getByte(pltIndex++);
/*  992 */         this.greenPalette[i] = chunk.getByte(pltIndex++);
/*  993 */         this.bluePalette[i] = chunk.getByte(pltIndex++);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   private void parse_bKGD_chunk(PNGChunk chunk) {
/*      */     int bkgdIndex, bkgdGray, bkgdRGB[];
/*  999 */     switch (this.colorType) {
/*      */       case 3:
/* 1001 */         bkgdIndex = chunk.getByte(0) & 0xFF;
/*      */         
/* 1003 */         this.bkgdRed = this.redPalette[bkgdIndex] & 0xFF;
/* 1004 */         this.bkgdGreen = this.greenPalette[bkgdIndex] & 0xFF;
/* 1005 */         this.bkgdBlue = this.bluePalette[bkgdIndex] & 0xFF;
/*      */         
/* 1007 */         if (this.encodeParam != null) {
/* 1008 */           ((PNGEncodeParam.Palette)this.encodeParam).setBackgroundPaletteIndex(bkgdIndex);
/*      */         }
/*      */         break;
/*      */       case 0:
/*      */       case 4:
/* 1013 */         bkgdGray = chunk.getInt2(0);
/* 1014 */         this.bkgdRed = this.bkgdGreen = this.bkgdBlue = bkgdGray;
/*      */         
/* 1016 */         if (this.encodeParam != null) {
/* 1017 */           ((PNGEncodeParam.Gray)this.encodeParam).setBackgroundGray(bkgdGray);
/*      */         }
/*      */         break;
/*      */       case 2:
/*      */       case 6:
/* 1022 */         this.bkgdRed = chunk.getInt2(0);
/* 1023 */         this.bkgdGreen = chunk.getInt2(2);
/* 1024 */         this.bkgdBlue = chunk.getInt2(4);
/*      */         
/* 1026 */         bkgdRGB = new int[3];
/* 1027 */         bkgdRGB[0] = this.bkgdRed;
/* 1028 */         bkgdRGB[1] = this.bkgdGreen;
/* 1029 */         bkgdRGB[2] = this.bkgdBlue;
/* 1030 */         if (this.encodeParam != null) {
/* 1031 */           ((PNGEncodeParam.RGB)this.encodeParam).setBackgroundRGB(bkgdRGB);
/*      */         }
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/* 1037 */     int r = 0, g = 0, b = 0;
/* 1038 */     if (this.bitDepth < 8) {
/* 1039 */       r = this.expandBits[this.bitDepth][this.bkgdRed];
/* 1040 */       g = this.expandBits[this.bitDepth][this.bkgdGreen];
/* 1041 */       b = this.expandBits[this.bitDepth][this.bkgdBlue];
/* 1042 */     } else if (this.bitDepth == 8) {
/* 1043 */       r = this.bkgdRed;
/* 1044 */       g = this.bkgdGreen;
/* 1045 */       b = this.bkgdBlue;
/* 1046 */     } else if (this.bitDepth == 16) {
/* 1047 */       r = this.bkgdRed >> 8;
/* 1048 */       g = this.bkgdGreen >> 8;
/* 1049 */       b = this.bkgdBlue >> 8;
/*      */     } 
/* 1051 */     if (this.emitProperties) {
/* 1052 */       this.properties.put("background_color", new Color(r, g, b));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void parse_cHRM_chunk(PNGChunk chunk) {
/* 1058 */     if (this.sRGBRenderingIntent != -1) {
/*      */       return;
/*      */     }
/*      */     
/* 1062 */     this.chromaticity = new float[8];
/* 1063 */     this.chromaticity[0] = chunk.getInt4(0) / 100000.0F;
/* 1064 */     this.chromaticity[1] = chunk.getInt4(4) / 100000.0F;
/* 1065 */     this.chromaticity[2] = chunk.getInt4(8) / 100000.0F;
/* 1066 */     this.chromaticity[3] = chunk.getInt4(12) / 100000.0F;
/* 1067 */     this.chromaticity[4] = chunk.getInt4(16) / 100000.0F;
/* 1068 */     this.chromaticity[5] = chunk.getInt4(20) / 100000.0F;
/* 1069 */     this.chromaticity[6] = chunk.getInt4(24) / 100000.0F;
/* 1070 */     this.chromaticity[7] = chunk.getInt4(28) / 100000.0F;
/*      */     
/* 1072 */     if (this.encodeParam != null) {
/* 1073 */       this.encodeParam.setChromaticity(this.chromaticity);
/*      */     }
/* 1075 */     if (this.emitProperties) {
/* 1076 */       this.properties.put("white_point_x", Float.valueOf(this.chromaticity[0]));
/* 1077 */       this.properties.put("white_point_y", Float.valueOf(this.chromaticity[1]));
/* 1078 */       this.properties.put("red_x", Float.valueOf(this.chromaticity[2]));
/* 1079 */       this.properties.put("red_y", Float.valueOf(this.chromaticity[3]));
/* 1080 */       this.properties.put("green_x", Float.valueOf(this.chromaticity[4]));
/* 1081 */       this.properties.put("green_y", Float.valueOf(this.chromaticity[5]));
/* 1082 */       this.properties.put("blue_x", Float.valueOf(this.chromaticity[6]));
/* 1083 */       this.properties.put("blue_y", Float.valueOf(this.chromaticity[7]));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void parse_gAMA_chunk(PNGChunk chunk) {
/* 1089 */     if (this.sRGBRenderingIntent != -1) {
/*      */       return;
/*      */     }
/*      */     
/* 1093 */     this.fileGamma = chunk.getInt4(0) / 100000.0F;
/*      */     
/* 1095 */     float exp = this.performGammaCorrection ? (this.displayExponent / this.userExponent) : 1.0F;
/*      */     
/* 1097 */     if (this.encodeParam != null) {
/* 1098 */       this.encodeParam.setGamma(this.fileGamma * exp);
/*      */     }
/* 1100 */     if (this.emitProperties) {
/* 1101 */       this.properties.put("gamma", Float.valueOf(this.fileGamma * exp));
/*      */     }
/*      */   }
/*      */   
/*      */   private void parse_hIST_chunk(PNGChunk chunk) {
/* 1106 */     if (this.redPalette == null) {
/* 1107 */       String msg = PropertyUtil.getString("PNGImageDecoder18");
/* 1108 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/* 1111 */     int length = this.redPalette.length;
/* 1112 */     int[] hist = new int[length];
/* 1113 */     for (int i = 0; i < length; i++) {
/* 1114 */       hist[i] = chunk.getInt2(2 * i);
/*      */     }
/*      */     
/* 1117 */     if (this.encodeParam != null) {
/* 1118 */       this.encodeParam.setPaletteHistogram(hist);
/*      */     }
/*      */   }
/*      */   
/*      */   private void parse_iCCP_chunk(PNGChunk chunk) {
/* 1123 */     String name = "";
/*      */ 
/*      */     
/* 1126 */     int textIndex = 0; byte b;
/* 1127 */     while ((b = chunk.getByte(textIndex++)) != 0) {
/* 1128 */       name = name + (char)b;
/*      */     }
/*      */   }
/*      */   
/*      */   private void parse_pHYs_chunk(PNGChunk chunk) {
/* 1133 */     int xPixelsPerUnit = chunk.getInt4(0);
/* 1134 */     int yPixelsPerUnit = chunk.getInt4(4);
/* 1135 */     int unitSpecifier = chunk.getInt1(8);
/*      */     
/* 1137 */     if (this.encodeParam != null) {
/* 1138 */       this.encodeParam.setPhysicalDimension(xPixelsPerUnit, yPixelsPerUnit, unitSpecifier);
/*      */     }
/*      */ 
/*      */     
/* 1142 */     if (this.emitProperties) {
/* 1143 */       this.properties.put("x_pixels_per_unit", Integer.valueOf(xPixelsPerUnit));
/* 1144 */       this.properties.put("y_pixels_per_unit", Integer.valueOf(yPixelsPerUnit));
/* 1145 */       this.properties.put("pixel_aspect_ratio", Float.valueOf(xPixelsPerUnit / yPixelsPerUnit));
/*      */       
/* 1147 */       if (unitSpecifier == 1) {
/* 1148 */         this.properties.put("pixel_units", "Meters");
/* 1149 */       } else if (unitSpecifier != 0) {
/*      */         
/* 1151 */         String msg = PropertyUtil.getString("PNGImageDecoder12");
/* 1152 */         throw new RuntimeException(msg);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parse_sBIT_chunk(PNGChunk chunk) {
/* 1158 */     if (this.colorType == 3) {
/* 1159 */       this.significantBits = new int[3];
/*      */     } else {
/* 1161 */       this.significantBits = new int[this.inputBands];
/*      */     } 
/* 1163 */     for (int i = 0; i < this.significantBits.length; i++) {
/* 1164 */       int bits = chunk.getByte(i);
/* 1165 */       int depth = (this.colorType == 3) ? 8 : this.bitDepth;
/* 1166 */       if (bits <= 0 || bits > depth) {
/*      */ 
/*      */         
/* 1169 */         String msg = PropertyUtil.getString("PNGImageDecoder13");
/* 1170 */         throw new RuntimeException(msg);
/*      */       } 
/* 1172 */       this.significantBits[i] = bits;
/*      */     } 
/*      */     
/* 1175 */     if (this.encodeParam != null) {
/* 1176 */       this.encodeParam.setSignificantBits(this.significantBits);
/*      */     }
/* 1178 */     if (this.emitProperties) {
/* 1179 */       this.properties.put("significant_bits", this.significantBits);
/*      */     }
/*      */   }
/*      */   
/*      */   private void parse_sRGB_chunk(PNGChunk chunk) {
/* 1184 */     this.sRGBRenderingIntent = chunk.getByte(0);
/*      */ 
/*      */ 
/*      */     
/* 1188 */     this.fileGamma = 0.45455F;
/*      */     
/* 1190 */     this.chromaticity = new float[8];
/* 1191 */     this.chromaticity[0] = 3.127F;
/* 1192 */     this.chromaticity[1] = 3.29F;
/* 1193 */     this.chromaticity[2] = 6.4F;
/* 1194 */     this.chromaticity[3] = 3.3F;
/* 1195 */     this.chromaticity[4] = 3.0F;
/* 1196 */     this.chromaticity[5] = 6.0F;
/* 1197 */     this.chromaticity[6] = 1.5F;
/* 1198 */     this.chromaticity[7] = 0.6F;
/*      */     
/* 1200 */     if (this.performGammaCorrection) {
/*      */       
/* 1202 */       float gamma = this.fileGamma * this.displayExponent / this.userExponent;
/* 1203 */       if (this.encodeParam != null) {
/* 1204 */         this.encodeParam.setGamma(gamma);
/* 1205 */         this.encodeParam.setChromaticity(this.chromaticity);
/*      */       } 
/* 1207 */       if (this.emitProperties) {
/* 1208 */         this.properties.put("gamma", Float.valueOf(gamma));
/* 1209 */         this.properties.put("white_point_x", Float.valueOf(this.chromaticity[0]));
/* 1210 */         this.properties.put("white_point_y", Float.valueOf(this.chromaticity[1]));
/* 1211 */         this.properties.put("red_x", Float.valueOf(this.chromaticity[2]));
/* 1212 */         this.properties.put("red_y", Float.valueOf(this.chromaticity[3]));
/* 1213 */         this.properties.put("green_x", Float.valueOf(this.chromaticity[4]));
/* 1214 */         this.properties.put("green_y", Float.valueOf(this.chromaticity[5]));
/* 1215 */         this.properties.put("blue_x", Float.valueOf(this.chromaticity[6]));
/* 1216 */         this.properties.put("blue_y", Float.valueOf(this.chromaticity[7]));
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void parse_tEXt_chunk(PNGChunk chunk) {
/* 1224 */     StringBuffer key = new StringBuffer();
/* 1225 */     int textIndex = 0; byte b;
/* 1226 */     while ((b = chunk.getByte(textIndex++)) != 0) {
/* 1227 */       key.append((char)b);
/*      */     }
/*      */     
/* 1230 */     StringBuffer value = new StringBuffer();
/* 1231 */     for (int i = textIndex; i < chunk.getLength(); i++) {
/* 1232 */       value.append((char)chunk.getByte(i));
/*      */     }
/*      */     
/* 1235 */     this.textKeys.add(key.toString());
/* 1236 */     this.textStrings.add(value.toString());
/*      */   }
/*      */   
/*      */   private void parse_tIME_chunk(PNGChunk chunk) {
/* 1240 */     int year = chunk.getInt2(0);
/* 1241 */     int month = chunk.getInt1(2) - 1;
/* 1242 */     int day = chunk.getInt1(3);
/* 1243 */     int hour = chunk.getInt1(4);
/* 1244 */     int minute = chunk.getInt1(5);
/* 1245 */     int second = chunk.getInt1(6);
/*      */     
/* 1247 */     TimeZone gmt = TimeZone.getTimeZone("GMT");
/*      */     
/* 1249 */     GregorianCalendar cal = new GregorianCalendar(gmt);
/* 1250 */     cal.set(year, month, day, hour, minute, second);
/*      */     
/* 1252 */     Date date = cal.getTime();
/*      */     
/* 1254 */     if (this.encodeParam != null) {
/* 1255 */       this.encodeParam.setModificationTime(date);
/*      */     }
/* 1257 */     if (this.emitProperties) {
/* 1258 */       this.properties.put("timestamp", date);
/*      */     }
/*      */   }
/*      */   
/*      */   private void parse_tRNS_chunk(PNGChunk chunk) {
/* 1263 */     if (this.colorType == 3) {
/* 1264 */       int entries = chunk.getLength();
/* 1265 */       if (entries > this.paletteEntries) {
/*      */         
/* 1267 */         String msg = PropertyUtil.getString("PNGImageDecoder14");
/* 1268 */         throw new RuntimeException(msg);
/*      */       } 
/*      */ 
/*      */       
/* 1272 */       this.alphaPalette = new byte[this.paletteEntries]; int i;
/* 1273 */       for (i = 0; i < entries; i++) {
/* 1274 */         this.alphaPalette[i] = chunk.getByte(i);
/*      */       }
/*      */ 
/*      */       
/* 1278 */       for (i = entries; i < this.paletteEntries; i++) {
/* 1279 */         this.alphaPalette[i] = -1;
/*      */       }
/*      */       
/* 1282 */       if (!this.suppressAlpha) {
/* 1283 */         if (this.expandPalette) {
/* 1284 */           this.postProcess = 5;
/* 1285 */           this.outputBands = 4;
/*      */         } else {
/* 1287 */           this.outputHasAlphaPalette = true;
/*      */         } 
/*      */       }
/* 1290 */     } else if (this.colorType == 0) {
/* 1291 */       this.grayTransparentAlpha = chunk.getInt2(0);
/*      */       
/* 1293 */       if (!this.suppressAlpha) {
/* 1294 */         if (this.bitDepth < 8) {
/* 1295 */           this.output8BitGray = true;
/* 1296 */           this.maxOpacity = 255;
/* 1297 */           this.postProcess = 3;
/*      */         } else {
/* 1299 */           this.postProcess = 6;
/*      */         } 
/*      */         
/* 1302 */         if (this.expandGrayAlpha) {
/* 1303 */           this.outputBands = 4;
/* 1304 */           this.postProcess |= 0x10;
/*      */         } else {
/* 1306 */           this.outputBands = 2;
/*      */         } 
/*      */         
/* 1309 */         if (this.encodeParam != null) {
/* 1310 */           ((PNGEncodeParam.Gray)this.encodeParam).setTransparentGray(this.grayTransparentAlpha);
/*      */         }
/*      */       }
/*      */     
/* 1314 */     } else if (this.colorType == 2) {
/* 1315 */       this.redTransparentAlpha = chunk.getInt2(0);
/* 1316 */       this.greenTransparentAlpha = chunk.getInt2(2);
/* 1317 */       this.blueTransparentAlpha = chunk.getInt2(4);
/*      */       
/* 1319 */       if (!this.suppressAlpha) {
/* 1320 */         this.outputBands = 4;
/* 1321 */         this.postProcess = 7;
/*      */         
/* 1323 */         if (this.encodeParam != null) {
/* 1324 */           int[] rgbTrans = new int[3];
/* 1325 */           rgbTrans[0] = this.redTransparentAlpha;
/* 1326 */           rgbTrans[1] = this.greenTransparentAlpha;
/* 1327 */           rgbTrans[2] = this.blueTransparentAlpha;
/* 1328 */           ((PNGEncodeParam.RGB)this.encodeParam).setTransparentRGB(rgbTrans);
/*      */         }
/*      */       
/*      */       } 
/* 1332 */     } else if (this.colorType == 4 || this.colorType == 6) {
/*      */ 
/*      */       
/* 1335 */       String msg = PropertyUtil.getString("PNGImageDecoder15");
/* 1336 */       throw new RuntimeException(msg);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void parse_zTXt_chunk(PNGChunk chunk) {
/* 1342 */     int textIndex = 0;
/* 1343 */     StringBuffer key = new StringBuffer();
/*      */     byte b;
/* 1345 */     while ((b = chunk.getByte(textIndex++)) != 0) {
/* 1346 */       key.append((char)b);
/*      */     }
/* 1348 */     chunk.getByte(textIndex++);
/*      */     
/* 1350 */     StringBuffer value = new StringBuffer();
/*      */     try {
/* 1352 */       int length = chunk.getLength() - textIndex;
/* 1353 */       byte[] data = chunk.getData();
/* 1354 */       InputStream cis = new ByteArrayInputStream(data, textIndex, length);
/*      */       
/* 1356 */       InputStream iis = new InflaterInputStream(cis);
/*      */       
/*      */       int c;
/* 1359 */       while ((c = iis.read()) != -1) {
/* 1360 */         value.append((char)c);
/*      */       }
/*      */       
/* 1363 */       this.ztextKeys.add(key.toString());
/* 1364 */       this.ztextStrings.add(value.toString());
/* 1365 */     } catch (Exception e) {
/* 1366 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private WritableRaster createRaster(int width, int height, int bands, int scanlineStride, int bitDepth) {
/* 1375 */     WritableRaster ras = null;
/* 1376 */     Point origin = new Point(0, 0);
/* 1377 */     if (bitDepth < 8 && bands == 1) {
/* 1378 */       DataBuffer dataBuffer = new DataBufferByte(height * scanlineStride);
/* 1379 */       ras = Raster.createPackedRaster(dataBuffer, width, height, bitDepth, origin);
/*      */ 
/*      */     
/*      */     }
/* 1383 */     else if (bitDepth <= 8) {
/* 1384 */       DataBuffer dataBuffer = new DataBufferByte(height * scanlineStride);
/* 1385 */       ras = Raster.createInterleavedRaster(dataBuffer, width, height, scanlineStride, bands, this.bandOffsets[bands], origin);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1392 */       DataBuffer dataBuffer = new DataBufferUShort(height * scanlineStride);
/* 1393 */       ras = Raster.createInterleavedRaster(dataBuffer, width, height, scanlineStride, bands, this.bandOffsets[bands], origin);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1401 */     return ras;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void decodeSubFilter(byte[] curr, int count, int bpp) {
/* 1407 */     for (int i = bpp; i < count; i++) {
/*      */ 
/*      */       
/* 1410 */       int val = curr[i] & 0xFF;
/* 1411 */       val += curr[i - bpp] & 0xFF;
/*      */       
/* 1413 */       curr[i] = (byte)val;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void decodeUpFilter(byte[] curr, byte[] prev, int count) {
/* 1419 */     for (int i = 0; i < count; i++) {
/* 1420 */       int raw = curr[i] & 0xFF;
/* 1421 */       int prior = prev[i] & 0xFF;
/*      */       
/* 1423 */       curr[i] = (byte)(raw + prior);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void decodeAverageFilter(byte[] curr, byte[] prev, int count, int bpp) {
/*      */     int i;
/* 1431 */     for (i = 0; i < bpp; i++) {
/* 1432 */       int raw = curr[i] & 0xFF;
/* 1433 */       int priorRow = prev[i] & 0xFF;
/*      */       
/* 1435 */       curr[i] = (byte)(raw + priorRow / 2);
/*      */     } 
/*      */     
/* 1438 */     for (i = bpp; i < count; i++) {
/* 1439 */       int raw = curr[i] & 0xFF;
/* 1440 */       int priorPixel = curr[i - bpp] & 0xFF;
/* 1441 */       int priorRow = prev[i] & 0xFF;
/*      */       
/* 1443 */       curr[i] = (byte)(raw + (priorPixel + priorRow) / 2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void decodePaethFilter(byte[] curr, byte[] prev, int count, int bpp) {
/*      */     int i;
/* 1451 */     for (i = 0; i < bpp; i++) {
/* 1452 */       int raw = curr[i] & 0xFF;
/* 1453 */       int priorRow = prev[i] & 0xFF;
/*      */       
/* 1455 */       curr[i] = (byte)(raw + priorRow);
/*      */     } 
/*      */     
/* 1458 */     for (i = bpp; i < count; i++) {
/* 1459 */       int raw = curr[i] & 0xFF;
/* 1460 */       int priorPixel = curr[i - bpp] & 0xFF;
/* 1461 */       int priorRow = prev[i] & 0xFF;
/* 1462 */       int priorRowPixel = prev[i - bpp] & 0xFF;
/*      */       
/* 1464 */       curr[i] = (byte)(raw + PNGEncodeParam.paethPredictor(priorPixel, priorRow, priorRowPixel));
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
/*      */   private void processPixels(int process, Raster src, WritableRaster dst, int xOffset, int step, int y, int width) {
/* 1476 */     int srcX, ps[] = src.getPixel(0, 0, (int[])null);
/* 1477 */     int[] pd = dst.getPixel(0, 0, (int[])null);
/*      */     
/* 1479 */     int dstX = xOffset;
/* 1480 */     switch (process) {
/*      */       case 0:
/* 1482 */         for (srcX = 0; srcX < width; srcX++) {
/* 1483 */           src.getPixel(srcX, 0, ps);
/* 1484 */           dst.setPixel(dstX, y, ps);
/* 1485 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 1:
/* 1490 */         for (srcX = 0; srcX < width; srcX++) {
/* 1491 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1493 */           for (int i = 0; i < this.inputBands; i++) {
/* 1494 */             int x = ps[i];
/* 1495 */             ps[i] = this.gammaLut[x];
/*      */           } 
/*      */           
/* 1498 */           dst.setPixel(dstX, y, ps);
/* 1499 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 2:
/* 1504 */         for (srcX = 0; srcX < width; srcX++) {
/* 1505 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1507 */           pd[0] = this.grayLut[ps[0]];
/*      */           
/* 1509 */           dst.setPixel(dstX, y, pd);
/* 1510 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 3:
/* 1515 */         for (srcX = 0; srcX < width; srcX++) {
/* 1516 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1518 */           int val = ps[0];
/* 1519 */           pd[0] = this.grayLut[val];
/* 1520 */           if (val == this.grayTransparentAlpha) {
/* 1521 */             pd[1] = 0;
/*      */           } else {
/* 1523 */             pd[1] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1526 */           dst.setPixel(dstX, y, pd);
/* 1527 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 4:
/* 1532 */         for (srcX = 0; srcX < width; srcX++) {
/* 1533 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1535 */           int val = ps[0];
/* 1536 */           pd[0] = this.redPalette[val];
/* 1537 */           pd[1] = this.greenPalette[val];
/* 1538 */           pd[2] = this.bluePalette[val];
/*      */           
/* 1540 */           dst.setPixel(dstX, y, pd);
/* 1541 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 5:
/* 1546 */         for (srcX = 0; srcX < width; srcX++) {
/* 1547 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1549 */           int val = ps[0];
/* 1550 */           pd[0] = this.redPalette[val];
/* 1551 */           pd[1] = this.greenPalette[val];
/* 1552 */           pd[2] = this.bluePalette[val];
/* 1553 */           pd[3] = this.alphaPalette[val];
/*      */           
/* 1555 */           dst.setPixel(dstX, y, pd);
/* 1556 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 6:
/* 1561 */         for (srcX = 0; srcX < width; srcX++) {
/* 1562 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1564 */           int val = ps[0];
/* 1565 */           if (this.performGammaCorrection) {
/* 1566 */             val = this.gammaLut[val];
/*      */           }
/* 1568 */           pd[0] = val;
/* 1569 */           if (val == this.grayTransparentAlpha) {
/* 1570 */             pd[1] = 0;
/*      */           } else {
/* 1572 */             pd[1] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1575 */           dst.setPixel(dstX, y, pd);
/* 1576 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 7:
/* 1581 */         for (srcX = 0; srcX < width; srcX++) {
/* 1582 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1584 */           int r = ps[0];
/* 1585 */           int g = ps[1];
/* 1586 */           int b = ps[2];
/* 1587 */           if (this.performGammaCorrection) {
/* 1588 */             pd[0] = this.gammaLut[r];
/* 1589 */             pd[1] = this.gammaLut[g];
/* 1590 */             pd[2] = this.gammaLut[b];
/*      */           } else {
/* 1592 */             pd[0] = r;
/* 1593 */             pd[1] = g;
/* 1594 */             pd[2] = b;
/*      */           } 
/* 1596 */           if (r == this.redTransparentAlpha && g == this.greenTransparentAlpha && b == this.blueTransparentAlpha) {
/*      */ 
/*      */             
/* 1599 */             pd[3] = 0;
/*      */           } else {
/* 1601 */             pd[3] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1604 */           dst.setPixel(dstX, y, pd);
/* 1605 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 8:
/* 1610 */         for (srcX = 0; srcX < width; srcX++) {
/* 1611 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1613 */           int g = ps[0];
/* 1614 */           if (this.performGammaCorrection) {
/* 1615 */             pd[0] = this.gammaLut[g];
/*      */           } else {
/* 1617 */             pd[0] = g;
/*      */           } 
/*      */           
/* 1620 */           dst.setPixel(dstX, y, pd);
/* 1621 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 9:
/* 1626 */         for (srcX = 0; srcX < width; srcX++) {
/* 1627 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1629 */           int r = ps[0];
/* 1630 */           int g = ps[1];
/* 1631 */           int b = ps[2];
/* 1632 */           if (this.performGammaCorrection) {
/* 1633 */             pd[0] = this.gammaLut[r];
/* 1634 */             pd[1] = this.gammaLut[g];
/* 1635 */             pd[2] = this.gammaLut[b];
/*      */           } else {
/* 1637 */             pd[0] = r;
/* 1638 */             pd[1] = g;
/* 1639 */             pd[2] = b;
/*      */           } 
/*      */           
/* 1642 */           dst.setPixel(dstX, y, pd);
/* 1643 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 17:
/* 1648 */         for (srcX = 0; srcX < width; srcX++) {
/* 1649 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1651 */           int val = ps[0];
/* 1652 */           int alpha = ps[1];
/* 1653 */           int gamma = this.gammaLut[val];
/* 1654 */           pd[0] = gamma;
/* 1655 */           pd[1] = gamma;
/* 1656 */           pd[2] = gamma;
/* 1657 */           pd[3] = alpha;
/*      */           
/* 1659 */           dst.setPixel(dstX, y, pd);
/* 1660 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 16:
/* 1665 */         for (srcX = 0; srcX < width; srcX++) {
/* 1666 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1668 */           int val = ps[0];
/* 1669 */           int alpha = ps[1];
/* 1670 */           pd[0] = val;
/* 1671 */           pd[1] = val;
/* 1672 */           pd[2] = val;
/* 1673 */           pd[3] = alpha;
/*      */           
/* 1675 */           dst.setPixel(dstX, y, pd);
/* 1676 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 22:
/* 1681 */         for (srcX = 0; srcX < width; srcX++) {
/* 1682 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1684 */           int val = ps[0];
/* 1685 */           if (this.performGammaCorrection) {
/* 1686 */             val = this.gammaLut[val];
/*      */           }
/* 1688 */           pd[0] = val;
/* 1689 */           pd[1] = val;
/* 1690 */           pd[2] = val;
/* 1691 */           if (val == this.grayTransparentAlpha) {
/* 1692 */             pd[3] = 0;
/*      */           } else {
/* 1694 */             pd[3] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1697 */           dst.setPixel(dstX, y, pd);
/* 1698 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 19:
/* 1703 */         for (srcX = 0; srcX < width; srcX++) {
/* 1704 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1706 */           int val = ps[0];
/* 1707 */           int val2 = this.grayLut[val];
/* 1708 */           pd[0] = val2;
/* 1709 */           pd[1] = val2;
/* 1710 */           pd[2] = val2;
/* 1711 */           if (val == this.grayTransparentAlpha) {
/* 1712 */             pd[3] = 0;
/*      */           } else {
/* 1714 */             pd[3] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1717 */           dst.setPixel(dstX, y, pd);
/* 1718 */           dstX += step;
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
/* 1732 */     if (passWidth == 0 || passHeight == 0) {
/*      */       return;
/*      */     }
/*      */     
/* 1736 */     int bytesPerRow = (this.inputBands * passWidth * this.bitDepth + 7) / 8;
/* 1737 */     int eltsPerRow = (this.bitDepth == 16) ? (bytesPerRow / 2) : bytesPerRow;
/* 1738 */     byte[] curr = new byte[bytesPerRow];
/* 1739 */     byte[] prior = new byte[bytesPerRow];
/*      */ 
/*      */     
/* 1742 */     WritableRaster passRow = createRaster(passWidth, 1, this.inputBands, eltsPerRow, this.bitDepth);
/*      */ 
/*      */ 
/*      */     
/* 1746 */     DataBuffer dataBuffer = passRow.getDataBuffer();
/* 1747 */     int type = dataBuffer.getDataType();
/* 1748 */     byte[] byteData = null;
/* 1749 */     short[] shortData = null;
/* 1750 */     if (type == 0) {
/* 1751 */       byteData = ((DataBufferByte)dataBuffer).getData();
/*      */     } else {
/* 1753 */       shortData = ((DataBufferUShort)dataBuffer).getData();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1758 */     int srcY = 0, dstY = yOffset;
/* 1759 */     for (; srcY < passHeight; 
/* 1760 */       srcY++, dstY += yStep) {
/*      */       String msg;
/* 1762 */       int filter = 0;
/*      */       try {
/* 1764 */         filter = this.dataStream.read();
/* 1765 */         this.dataStream.readFully(curr, 0, bytesPerRow);
/* 1766 */       } catch (Exception e) {
/* 1767 */         e.printStackTrace();
/*      */       } 
/*      */       
/* 1770 */       switch (filter) {
/*      */         case 0:
/*      */           break;
/*      */         case 1:
/* 1774 */           decodeSubFilter(curr, bytesPerRow, this.bytesPerPixel);
/*      */           break;
/*      */         case 2:
/* 1777 */           decodeUpFilter(curr, prior, bytesPerRow);
/*      */           break;
/*      */         case 3:
/* 1780 */           decodeAverageFilter(curr, prior, bytesPerRow, this.bytesPerPixel);
/*      */           break;
/*      */         case 4:
/* 1783 */           decodePaethFilter(curr, prior, bytesPerRow, this.bytesPerPixel);
/*      */           break;
/*      */         
/*      */         default:
/* 1787 */           msg = PropertyUtil.getString("PNGImageDecoder16");
/* 1788 */           throw new RuntimeException(msg);
/*      */       } 
/*      */ 
/*      */       
/* 1792 */       if (this.bitDepth < 16) {
/* 1793 */         System.arraycopy(curr, 0, byteData, 0, bytesPerRow);
/*      */       } else {
/* 1795 */         int idx = 0;
/* 1796 */         for (int j = 0; j < eltsPerRow; j++) {
/* 1797 */           shortData[j] = (short)(curr[idx] << 8 | curr[idx + 1] & 0xFF);
/*      */           
/* 1799 */           idx += 2;
/*      */         } 
/*      */       } 
/*      */       
/* 1803 */       processPixels(this.postProcess, passRow, imRas, xOffset, xStep, dstY, passWidth);
/*      */ 
/*      */ 
/*      */       
/* 1807 */       byte[] tmp = prior;
/* 1808 */       prior = curr;
/* 1809 */       curr = tmp;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void decodeImage(boolean useInterlacing) {
/* 1814 */     if (!useInterlacing) {
/* 1815 */       decodePass(this.theTile, 0, 0, 1, 1, this.width, this.height);
/*      */     } else {
/* 1817 */       decodePass(this.theTile, 0, 0, 8, 8, (this.width + 7) / 8, (this.height + 7) / 8);
/* 1818 */       decodePass(this.theTile, 4, 0, 8, 8, (this.width + 3) / 8, (this.height + 7) / 8);
/* 1819 */       decodePass(this.theTile, 0, 4, 4, 8, (this.width + 3) / 4, (this.height + 3) / 8);
/* 1820 */       decodePass(this.theTile, 2, 0, 4, 4, (this.width + 1) / 4, (this.height + 3) / 4);
/* 1821 */       decodePass(this.theTile, 0, 2, 2, 4, (this.width + 1) / 2, (this.height + 1) / 4);
/* 1822 */       decodePass(this.theTile, 1, 0, 2, 2, this.width / 2, (this.height + 1) / 2);
/* 1823 */       decodePass(this.theTile, 0, 1, 1, 2, this.width, this.height / 2);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Raster getTile(int tileX, int tileY) {
/* 1830 */     if (tileX != 0 || tileY != 0) {
/*      */       
/* 1832 */       String msg = PropertyUtil.getString("PNGImageDecoder17");
/* 1833 */       throw new IllegalArgumentException(msg);
/*      */     } 
/* 1835 */     return this.theTile;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/png/PNGImage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */