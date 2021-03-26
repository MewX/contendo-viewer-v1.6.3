/*      */ package org.apache.batik.ext.awt.image.codec.png;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
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
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.TimeZone;
/*      */ import java.util.zip.Inflater;
/*      */ import java.util.zip.InflaterInputStream;
/*      */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*      */ import org.apache.batik.ext.awt.image.codec.util.PropertyUtil;
/*      */ import org.apache.batik.ext.awt.image.rendered.AbstractRed;
/*      */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PNGRed
/*      */   extends AbstractRed
/*      */ {
/*      */   public static final int PNG_COLOR_GRAY = 0;
/*      */   public static final int PNG_COLOR_RGB = 2;
/*      */   public static final int PNG_COLOR_PALETTE = 3;
/*      */   public static final int PNG_COLOR_GRAY_ALPHA = 4;
/*      */   public static final int PNG_COLOR_RGB_ALPHA = 6;
/*      */   
/*      */   static class PNGChunk
/*      */   {
/*      */     int length;
/*      */     int type;
/*      */     byte[] data;
/*      */     int crc;
/*      */     String typeString;
/*      */     
/*      */     public PNGChunk(int length, int type, byte[] data, int crc) {
/*   73 */       this.length = length;
/*   74 */       this.type = type;
/*   75 */       this.data = data;
/*   76 */       this.crc = crc;
/*      */       
/*   78 */       this.typeString = "";
/*   79 */       this.typeString += (char)(type >> 24);
/*   80 */       this.typeString += (char)(type >> 16 & 0xFF);
/*   81 */       this.typeString += (char)(type >> 8 & 0xFF);
/*   82 */       this.typeString += (char)(type & 0xFF);
/*      */     }
/*      */     
/*      */     public int getLength() {
/*   86 */       return this.length;
/*      */     }
/*      */     
/*      */     public int getType() {
/*   90 */       return this.type;
/*      */     }
/*      */     
/*      */     public String getTypeString() {
/*   94 */       return this.typeString;
/*      */     }
/*      */     
/*      */     public byte[] getData() {
/*   98 */       return this.data;
/*      */     }
/*      */     
/*      */     public byte getByte(int offset) {
/*  102 */       return this.data[offset];
/*      */     }
/*      */     
/*      */     public int getInt1(int offset) {
/*  106 */       return this.data[offset] & 0xFF;
/*      */     }
/*      */     
/*      */     public int getInt2(int offset) {
/*  110 */       return (this.data[offset] & 0xFF) << 8 | this.data[offset + 1] & 0xFF;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getInt4(int offset) {
/*  115 */       return (this.data[offset] & 0xFF) << 24 | (this.data[offset + 1] & 0xFF) << 16 | (this.data[offset + 2] & 0xFF) << 8 | this.data[offset + 3] & 0xFF;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getString4(int offset) {
/*  122 */       String s = new String();
/*  123 */       s = s + (char)this.data[offset];
/*  124 */       s = s + (char)this.data[offset + 1];
/*  125 */       s = s + (char)this.data[offset + 2];
/*  126 */       s = s + (char)this.data[offset + 3];
/*  127 */       return s;
/*      */     }
/*      */     
/*      */     public boolean isType(String typeName) {
/*  131 */       return this.typeString.equals(typeName);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  141 */   private static final String[] colorTypeNames = new String[] { "Grayscale", "Error", "Truecolor", "Index", "Grayscale with alpha", "Error", "Truecolor with alpha" };
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
/*  152 */   private int[][] bandOffsets = new int[][] { null, { 0 }, { 0, 1 }, { 0, 1, 2 }, { 0, 1, 2, 3 } };
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
/*  184 */   private int[] significantBits = null;
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
/*  210 */   private PNGDecodeParam decodeParam = null;
/*      */ 
/*      */   
/*  213 */   private PNGEncodeParam encodeParam = null;
/*      */   
/*      */   private boolean emitProperties = true;
/*      */   
/*  217 */   private float fileGamma = 0.45455F;
/*      */   
/*  219 */   private float userExponent = 1.0F;
/*      */   
/*  221 */   private float displayExponent = 2.2F;
/*      */   
/*  223 */   private float[] chromaticity = null;
/*      */   
/*  225 */   private int sRGBRenderingIntent = -1;
/*      */ 
/*      */   
/*  228 */   private int postProcess = 0;
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
/*  281 */   private List streamVec = new ArrayList();
/*      */   
/*      */   private DataInputStream dataStream;
/*      */   
/*      */   private int bytesPerPixel;
/*      */   
/*      */   private int inputBands;
/*      */   private int outputBands;
/*  289 */   private int chunkIndex = 0;
/*      */   
/*  291 */   private List textKeys = new ArrayList();
/*  292 */   private List textStrings = new ArrayList();
/*      */   
/*  294 */   private List ztextKeys = new ArrayList();
/*  295 */   private List ztextStrings = new ArrayList();
/*      */   
/*      */   private WritableRaster theTile;
/*      */   
/*      */   private Rectangle bounds;
/*      */   
/*  301 */   private Map properties = new HashMap<Object, Object>();
/*      */ 
/*      */   
/*  304 */   private int[] gammaLut = null;
/*      */   
/*      */   private void initGammaLut(int bits) {
/*  307 */     double exp = this.userExponent / (this.fileGamma * this.displayExponent);
/*  308 */     int numSamples = 1 << bits;
/*  309 */     int maxOutSample = (bits == 16) ? 65535 : 255;
/*      */     
/*  311 */     this.gammaLut = new int[numSamples];
/*  312 */     for (int i = 0; i < numSamples; i++) {
/*  313 */       double gbright = i / (numSamples - 1);
/*  314 */       double gamma = Math.pow(gbright, exp);
/*  315 */       int igamma = (int)(gamma * maxOutSample + 0.5D);
/*  316 */       if (igamma > maxOutSample) {
/*  317 */         igamma = maxOutSample;
/*      */       }
/*  319 */       this.gammaLut[i] = igamma;
/*      */     } 
/*      */   }
/*      */   
/*  323 */   private final byte[][] expandBits = new byte[][] { null, { 0, -1 }, { 0, 85, -86, -1 }, null, { 0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1 } };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  334 */   private int[] grayLut = null;
/*      */   
/*      */   private void initGrayLut(int bits) {
/*  337 */     int len = 1 << bits;
/*  338 */     this.grayLut = new int[len];
/*      */     
/*  340 */     if (this.performGammaCorrection) {
/*  341 */       System.arraycopy(this.gammaLut, 0, this.grayLut, 0, len);
/*      */     } else {
/*  343 */       for (int i = 0; i < len; i++) {
/*  344 */         this.grayLut[i] = this.expandBits[bits][i];
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public PNGRed(InputStream stream) throws IOException {
/*  350 */     this(stream, null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public PNGRed(InputStream stream, PNGDecodeParam decodeParam) throws IOException {
/*  356 */     if (!stream.markSupported()) {
/*  357 */       stream = new BufferedInputStream(stream);
/*      */     }
/*  359 */     DataInputStream distream = new DataInputStream(stream);
/*      */     
/*  361 */     if (decodeParam == null) {
/*  362 */       decodeParam = new PNGDecodeParam();
/*      */     }
/*  364 */     this.decodeParam = decodeParam;
/*      */ 
/*      */     
/*  367 */     this.suppressAlpha = decodeParam.getSuppressAlpha();
/*  368 */     this.expandPalette = decodeParam.getExpandPalette();
/*  369 */     this.output8BitGray = decodeParam.getOutput8BitGray();
/*  370 */     this.expandGrayAlpha = decodeParam.getExpandGrayAlpha();
/*  371 */     if (decodeParam.getPerformGammaCorrection()) {
/*  372 */       this.userExponent = decodeParam.getUserExponent();
/*  373 */       this.displayExponent = decodeParam.getDisplayExponent();
/*  374 */       this.performGammaCorrection = true;
/*  375 */       this.output8BitGray = true;
/*      */     } 
/*  377 */     this.generateEncodeParam = decodeParam.getGenerateEncodeParam();
/*      */     
/*  379 */     if (this.emitProperties) {
/*  380 */       this.properties.put("file_type", "PNG v. 1.0");
/*      */     }
/*      */     
/*      */     try {
/*  384 */       long magic = distream.readLong();
/*  385 */       if (magic != -8552249625308161526L) {
/*  386 */         String msg = PropertyUtil.getString("PNGImageDecoder0");
/*  387 */         throw new RuntimeException(msg);
/*      */       } 
/*  389 */     } catch (Exception e) {
/*  390 */       e.printStackTrace();
/*  391 */       String msg = PropertyUtil.getString("PNGImageDecoder1");
/*  392 */       throw new RuntimeException(msg);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/*      */       while (true) {
/*  399 */         String chunkType = getChunkType(distream);
/*  400 */         if (chunkType.equals("IHDR")) {
/*  401 */           PNGChunk pNGChunk = readChunk(distream);
/*  402 */           parse_IHDR_chunk(pNGChunk); continue;
/*  403 */         }  if (chunkType.equals("PLTE")) {
/*  404 */           PNGChunk pNGChunk = readChunk(distream);
/*  405 */           parse_PLTE_chunk(pNGChunk); continue;
/*  406 */         }  if (chunkType.equals("IDAT")) {
/*  407 */           PNGChunk pNGChunk = readChunk(distream);
/*  408 */           this.streamVec.add(new ByteArrayInputStream(pNGChunk.getData())); continue;
/*  409 */         }  if (chunkType.equals("IEND")) {
/*  410 */           PNGChunk pNGChunk = readChunk(distream);
/*  411 */           parse_IEND_chunk(pNGChunk); break;
/*      */         } 
/*  413 */         if (chunkType.equals("bKGD")) {
/*  414 */           PNGChunk pNGChunk = readChunk(distream);
/*  415 */           parse_bKGD_chunk(pNGChunk); continue;
/*  416 */         }  if (chunkType.equals("cHRM")) {
/*  417 */           PNGChunk pNGChunk = readChunk(distream);
/*  418 */           parse_cHRM_chunk(pNGChunk); continue;
/*  419 */         }  if (chunkType.equals("gAMA")) {
/*  420 */           PNGChunk pNGChunk = readChunk(distream);
/*  421 */           parse_gAMA_chunk(pNGChunk); continue;
/*  422 */         }  if (chunkType.equals("hIST")) {
/*  423 */           PNGChunk pNGChunk = readChunk(distream);
/*  424 */           parse_hIST_chunk(pNGChunk); continue;
/*  425 */         }  if (chunkType.equals("iCCP")) {
/*  426 */           PNGChunk pNGChunk = readChunk(distream);
/*  427 */           parse_iCCP_chunk(pNGChunk); continue;
/*  428 */         }  if (chunkType.equals("pHYs")) {
/*  429 */           PNGChunk pNGChunk = readChunk(distream);
/*  430 */           parse_pHYs_chunk(pNGChunk); continue;
/*  431 */         }  if (chunkType.equals("sBIT")) {
/*  432 */           PNGChunk pNGChunk = readChunk(distream);
/*  433 */           parse_sBIT_chunk(pNGChunk); continue;
/*  434 */         }  if (chunkType.equals("sRGB")) {
/*  435 */           PNGChunk pNGChunk = readChunk(distream);
/*  436 */           parse_sRGB_chunk(pNGChunk); continue;
/*  437 */         }  if (chunkType.equals("tEXt")) {
/*  438 */           PNGChunk pNGChunk = readChunk(distream);
/*  439 */           parse_tEXt_chunk(pNGChunk); continue;
/*  440 */         }  if (chunkType.equals("tIME")) {
/*  441 */           PNGChunk pNGChunk = readChunk(distream);
/*  442 */           parse_tIME_chunk(pNGChunk); continue;
/*  443 */         }  if (chunkType.equals("tRNS")) {
/*  444 */           PNGChunk pNGChunk = readChunk(distream);
/*  445 */           parse_tRNS_chunk(pNGChunk); continue;
/*  446 */         }  if (chunkType.equals("zTXt")) {
/*  447 */           PNGChunk pNGChunk = readChunk(distream);
/*  448 */           parse_zTXt_chunk(pNGChunk); continue;
/*      */         } 
/*  450 */         PNGChunk chunk = readChunk(distream);
/*      */ 
/*      */         
/*  453 */         String type = chunk.getTypeString();
/*  454 */         byte[] data = chunk.getData();
/*  455 */         if (this.encodeParam != null) {
/*  456 */           this.encodeParam.addPrivateChunk(type, data);
/*      */         }
/*  458 */         if (this.emitProperties) {
/*  459 */           String key = "chunk_" + this.chunkIndex++ + ':' + type;
/*  460 */           this.properties.put(key.toLowerCase(), data);
/*      */         } 
/*      */       } 
/*  463 */     } catch (Exception e) {
/*  464 */       e.printStackTrace();
/*  465 */       String msg = PropertyUtil.getString("PNGImageDecoder2");
/*  466 */       throw new RuntimeException(msg);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  472 */     if (this.significantBits == null) {
/*  473 */       this.significantBits = new int[this.inputBands];
/*  474 */       for (int i = 0; i < this.inputBands; i++) {
/*  475 */         this.significantBits[i] = this.bitDepth;
/*      */       }
/*      */       
/*  478 */       if (this.emitProperties) {
/*  479 */         this.properties.put("significant_bits", this.significantBits);
/*      */       }
/*      */     } 
/*  482 */     distream.close();
/*  483 */     stream.close();
/*      */   }
/*      */   
/*      */   private static String getChunkType(DataInputStream distream) {
/*      */     try {
/*  488 */       distream.mark(8);
/*  489 */       distream.readInt();
/*  490 */       int type = distream.readInt();
/*  491 */       distream.reset();
/*      */       
/*  493 */       String typeString = "" + (char)(type >> 24 & 0xFF) + (char)(type >> 16 & 0xFF) + (char)(type >> 8 & 0xFF) + (char)(type & 0xFF);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  498 */       return typeString;
/*  499 */     } catch (Exception e) {
/*  500 */       e.printStackTrace();
/*  501 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static PNGChunk readChunk(DataInputStream distream) {
/*      */     try {
/*  507 */       int length = distream.readInt();
/*  508 */       int type = distream.readInt();
/*  509 */       byte[] data = new byte[length];
/*  510 */       distream.readFully(data);
/*  511 */       int crc = distream.readInt();
/*      */       
/*  513 */       return new PNGChunk(length, type, data, crc);
/*  514 */     } catch (Exception e) {
/*  515 */       e.printStackTrace();
/*  516 */       return null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parse_IHDR_chunk(PNGChunk chunk) {
/*  521 */     int width = chunk.getInt4(0);
/*  522 */     int height = chunk.getInt4(4);
/*      */     
/*  524 */     this.bounds = new Rectangle(0, 0, width, height);
/*      */     
/*  526 */     this.bitDepth = chunk.getInt1(8);
/*      */     
/*  528 */     int validMask = 65814;
/*  529 */     if ((1 << this.bitDepth & validMask) == 0) {
/*      */       
/*  531 */       String msg = PropertyUtil.getString("PNGImageDecoder3");
/*  532 */       throw new RuntimeException(msg);
/*      */     } 
/*  534 */     this.maxOpacity = (1 << this.bitDepth) - 1;
/*      */     
/*  536 */     this.colorType = chunk.getInt1(9);
/*  537 */     if (this.colorType != 0 && this.colorType != 2 && this.colorType != 3 && this.colorType != 4 && this.colorType != 6)
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  542 */       System.out.println(PropertyUtil.getString("PNGImageDecoder4"));
/*      */     }
/*      */     
/*  545 */     if (this.colorType == 2 && this.bitDepth < 8) {
/*      */       
/*  547 */       String msg = PropertyUtil.getString("PNGImageDecoder5");
/*  548 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  551 */     if (this.colorType == 3 && this.bitDepth == 16) {
/*      */       
/*  553 */       String msg = PropertyUtil.getString("PNGImageDecoder6");
/*  554 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  557 */     if (this.colorType == 4 && this.bitDepth < 8) {
/*      */       
/*  559 */       String msg = PropertyUtil.getString("PNGImageDecoder7");
/*  560 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  563 */     if (this.colorType == 6 && this.bitDepth < 8) {
/*      */       
/*  565 */       String msg = PropertyUtil.getString("PNGImageDecoder8");
/*  566 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  569 */     if (this.emitProperties) {
/*  570 */       this.properties.put("color_type", colorTypeNames[this.colorType]);
/*      */     }
/*      */     
/*  573 */     if (this.generateEncodeParam) {
/*  574 */       if (this.colorType == 3) {
/*  575 */         this.encodeParam = new PNGEncodeParam.Palette();
/*  576 */       } else if (this.colorType == 0 || this.colorType == 4) {
/*      */         
/*  578 */         this.encodeParam = new PNGEncodeParam.Gray();
/*      */       } else {
/*  580 */         this.encodeParam = new PNGEncodeParam.RGB();
/*      */       } 
/*  582 */       this.decodeParam.setEncodeParam(this.encodeParam);
/*      */     } 
/*      */     
/*  585 */     if (this.encodeParam != null) {
/*  586 */       this.encodeParam.setBitDepth(this.bitDepth);
/*      */     }
/*  588 */     if (this.emitProperties) {
/*  589 */       this.properties.put("bit_depth", Integer.valueOf(this.bitDepth));
/*      */     }
/*      */     
/*  592 */     if (this.performGammaCorrection) {
/*      */       
/*  594 */       float gamma = 0.45454544F * this.displayExponent / this.userExponent;
/*  595 */       if (this.encodeParam != null) {
/*  596 */         this.encodeParam.setGamma(gamma);
/*      */       }
/*  598 */       if (this.emitProperties) {
/*  599 */         this.properties.put("gamma", Float.valueOf(gamma));
/*      */       }
/*      */     } 
/*      */     
/*  603 */     this.compressionMethod = chunk.getInt1(10);
/*  604 */     if (this.compressionMethod != 0) {
/*      */       
/*  606 */       String msg = PropertyUtil.getString("PNGImageDecoder9");
/*  607 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  610 */     this.filterMethod = chunk.getInt1(11);
/*  611 */     if (this.filterMethod != 0) {
/*      */       
/*  613 */       String msg = PropertyUtil.getString("PNGImageDecoder10");
/*  614 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  617 */     this.interlaceMethod = chunk.getInt1(12);
/*  618 */     if (this.interlaceMethod == 0) {
/*  619 */       if (this.encodeParam != null) {
/*  620 */         this.encodeParam.setInterlacing(false);
/*      */       }
/*  622 */       if (this.emitProperties) {
/*  623 */         this.properties.put("interlace_method", "None");
/*      */       }
/*  625 */     } else if (this.interlaceMethod == 1) {
/*  626 */       if (this.encodeParam != null) {
/*  627 */         this.encodeParam.setInterlacing(true);
/*      */       }
/*  629 */       if (this.emitProperties) {
/*  630 */         this.properties.put("interlace_method", "Adam7");
/*      */       }
/*      */     } else {
/*      */       
/*  634 */       String msg = PropertyUtil.getString("PNGImageDecoder11");
/*  635 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/*  638 */     this.bytesPerPixel = (this.bitDepth == 16) ? 2 : 1;
/*      */     
/*  640 */     switch (this.colorType) {
/*      */       case 0:
/*  642 */         this.inputBands = 1;
/*  643 */         this.outputBands = 1;
/*      */         
/*  645 */         if (this.output8BitGray && this.bitDepth < 8) {
/*  646 */           this.postProcess = 2; break;
/*  647 */         }  if (this.performGammaCorrection) {
/*  648 */           this.postProcess = 1; break;
/*      */         } 
/*  650 */         this.postProcess = 0;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 2:
/*  655 */         this.inputBands = 3;
/*  656 */         this.bytesPerPixel *= 3;
/*  657 */         this.outputBands = 3;
/*      */         
/*  659 */         if (this.performGammaCorrection) {
/*  660 */           this.postProcess = 1; break;
/*      */         } 
/*  662 */         this.postProcess = 0;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 3:
/*  667 */         this.inputBands = 1;
/*  668 */         this.bytesPerPixel = 1;
/*  669 */         this.outputBands = this.expandPalette ? 3 : 1;
/*      */         
/*  671 */         if (this.expandPalette) {
/*  672 */           this.postProcess = 4; break;
/*      */         } 
/*  674 */         this.postProcess = 0;
/*      */         break;
/*      */ 
/*      */       
/*      */       case 4:
/*  679 */         this.inputBands = 2;
/*  680 */         this.bytesPerPixel *= 2;
/*      */         
/*  682 */         if (this.suppressAlpha) {
/*  683 */           this.outputBands = 1;
/*  684 */           this.postProcess = 8; break;
/*      */         } 
/*  686 */         if (this.performGammaCorrection) {
/*  687 */           this.postProcess = 1;
/*      */         } else {
/*  689 */           this.postProcess = 0;
/*      */         } 
/*  691 */         if (this.expandGrayAlpha) {
/*  692 */           this.postProcess |= 0x10;
/*  693 */           this.outputBands = 4; break;
/*      */         } 
/*  695 */         this.outputBands = 2;
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 6:
/*  701 */         this.inputBands = 4;
/*  702 */         this.bytesPerPixel *= 4;
/*  703 */         this.outputBands = !this.suppressAlpha ? 4 : 3;
/*      */         
/*  705 */         if (this.suppressAlpha) {
/*  706 */           this.postProcess = 9; break;
/*  707 */         }  if (this.performGammaCorrection) {
/*  708 */           this.postProcess = 1; break;
/*      */         } 
/*  710 */         this.postProcess = 0;
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void parse_IEND_chunk(PNGChunk chunk) throws Exception {
/*      */     ColorModel cm;
/*  718 */     int textLen = this.textKeys.size();
/*  719 */     String[] textArray = new String[2 * textLen];
/*  720 */     for (int i = 0; i < textLen; i++) {
/*  721 */       String key = this.textKeys.get(i);
/*  722 */       String val = this.textStrings.get(i);
/*  723 */       textArray[2 * i] = key;
/*  724 */       textArray[2 * i + 1] = val;
/*  725 */       if (this.emitProperties) {
/*  726 */         String uniqueKey = "text_" + i + ':' + key;
/*  727 */         this.properties.put(uniqueKey.toLowerCase(), val);
/*      */       } 
/*      */     } 
/*  730 */     if (this.encodeParam != null) {
/*  731 */       this.encodeParam.setText(textArray);
/*      */     }
/*      */ 
/*      */     
/*  735 */     int ztextLen = this.ztextKeys.size();
/*  736 */     String[] ztextArray = new String[2 * ztextLen];
/*  737 */     for (int j = 0; j < ztextLen; j++) {
/*  738 */       String key = this.ztextKeys.get(j);
/*  739 */       String val = this.ztextStrings.get(j);
/*  740 */       ztextArray[2 * j] = key;
/*  741 */       ztextArray[2 * j + 1] = val;
/*  742 */       if (this.emitProperties) {
/*  743 */         String uniqueKey = "ztext_" + j + ':' + key;
/*  744 */         this.properties.put(uniqueKey.toLowerCase(), val);
/*      */       } 
/*      */     } 
/*  747 */     if (this.encodeParam != null) {
/*  748 */       this.encodeParam.setCompressedText(ztextArray);
/*      */     }
/*      */ 
/*      */     
/*  752 */     InputStream seqStream = new SequenceInputStream(Collections.enumeration(this.streamVec));
/*      */     
/*  754 */     InputStream infStream = new InflaterInputStream(seqStream, new Inflater());
/*      */     
/*  756 */     this.dataStream = new DataInputStream(infStream);
/*      */ 
/*      */     
/*  759 */     int depth = this.bitDepth;
/*  760 */     if (this.colorType == 0 && this.bitDepth < 8 && this.output8BitGray)
/*      */     {
/*  762 */       depth = 8;
/*      */     }
/*  764 */     if (this.colorType == 3 && this.expandPalette) {
/*  765 */       depth = 8;
/*      */     }
/*  767 */     int width = this.bounds.width;
/*  768 */     int height = this.bounds.height;
/*      */     
/*  770 */     int bytesPerRow = (this.outputBands * width * depth + 7) / 8;
/*  771 */     int scanlineStride = (depth == 16) ? (bytesPerRow / 2) : bytesPerRow;
/*      */ 
/*      */     
/*  774 */     this.theTile = createRaster(width, height, this.outputBands, scanlineStride, depth);
/*      */ 
/*      */ 
/*      */     
/*  778 */     if (this.performGammaCorrection && this.gammaLut == null) {
/*  779 */       initGammaLut(this.bitDepth);
/*      */     }
/*  781 */     if (this.postProcess == 2 || this.postProcess == 3 || this.postProcess == 19)
/*      */     {
/*      */       
/*  784 */       initGrayLut(this.bitDepth);
/*      */     }
/*      */     
/*  787 */     decodeImage((this.interlaceMethod == 1));
/*      */ 
/*      */     
/*  790 */     this.dataStream.close();
/*  791 */     infStream.close();
/*  792 */     seqStream.close();
/*  793 */     this.streamVec = null;
/*      */     
/*  795 */     SampleModel sm = this.theTile.getSampleModel();
/*      */ 
/*      */     
/*  798 */     if (this.colorType == 3 && !this.expandPalette) {
/*  799 */       if (this.outputHasAlphaPalette) {
/*  800 */         cm = new IndexColorModel(this.bitDepth, this.paletteEntries, this.redPalette, this.greenPalette, this.bluePalette, this.alphaPalette);
/*      */ 
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/*  807 */         cm = new IndexColorModel(this.bitDepth, this.paletteEntries, this.redPalette, this.greenPalette, this.bluePalette);
/*      */       
/*      */       }
/*      */ 
/*      */     
/*      */     }
/*  813 */     else if (this.colorType == 0 && this.bitDepth < 8 && !this.output8BitGray) {
/*      */       
/*  815 */       byte[] palette = this.expandBits[this.bitDepth];
/*  816 */       cm = new IndexColorModel(this.bitDepth, palette.length, palette, palette, palette);
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  822 */       cm = createComponentColorModel(sm);
/*      */     } 
/*      */ 
/*      */     
/*  826 */     init((CachableRed)null, this.bounds, cm, sm, 0, 0, this.properties);
/*      */   }
/*      */   
/*  829 */   private static final int[] GrayBits8 = new int[] { 8 };
/*  830 */   private static final ComponentColorModel colorModelGray8 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayBits8, false, false, 1, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  836 */   private static final int[] GrayAlphaBits8 = new int[] { 8, 8 };
/*  837 */   private static final ComponentColorModel colorModelGrayAlpha8 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayAlphaBits8, true, false, 3, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  843 */   private static final int[] GrayBits16 = new int[] { 16 };
/*  844 */   private static final ComponentColorModel colorModelGray16 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayBits16, false, false, 1, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  850 */   private static final int[] GrayAlphaBits16 = new int[] { 16, 16 };
/*  851 */   private static final ComponentColorModel colorModelGrayAlpha16 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayAlphaBits16, true, false, 3, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  857 */   private static final int[] GrayBits32 = new int[] { 32 };
/*  858 */   private static final ComponentColorModel colorModelGray32 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayBits32, false, false, 1, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  864 */   private static final int[] GrayAlphaBits32 = new int[] { 32, 32 };
/*  865 */   private static final ComponentColorModel colorModelGrayAlpha32 = new ComponentColorModel(ColorSpace.getInstance(1003), GrayAlphaBits32, true, false, 3, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  871 */   private static final int[] RGBBits8 = new int[] { 8, 8, 8 };
/*  872 */   private static final ComponentColorModel colorModelRGB8 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBBits8, false, false, 1, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  878 */   private static final int[] RGBABits8 = new int[] { 8, 8, 8, 8 };
/*  879 */   private static final ComponentColorModel colorModelRGBA8 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBABits8, true, false, 3, 0);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  885 */   private static final int[] RGBBits16 = new int[] { 16, 16, 16 };
/*  886 */   private static final ComponentColorModel colorModelRGB16 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBBits16, false, false, 1, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  892 */   private static final int[] RGBABits16 = new int[] { 16, 16, 16, 16 };
/*  893 */   private static final ComponentColorModel colorModelRGBA16 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBABits16, true, false, 3, 1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  899 */   private static final int[] RGBBits32 = new int[] { 32, 32, 32 };
/*  900 */   private static final ComponentColorModel colorModelRGB32 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBBits32, false, false, 1, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  906 */   private static final int[] RGBABits32 = new int[] { 32, 32, 32, 32 };
/*  907 */   private static final ComponentColorModel colorModelRGBA32 = new ComponentColorModel(ColorSpace.getInstance(1000), RGBABits32, true, false, 3, 3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  923 */     int type = sm.getDataType();
/*  924 */     int bands = sm.getNumBands();
/*  925 */     ComponentColorModel cm = null;
/*      */     
/*  927 */     if (type == 0) {
/*  928 */       switch (bands) {
/*      */         case 1:
/*  930 */           cm = colorModelGray8;
/*      */           break;
/*      */         case 2:
/*  933 */           cm = colorModelGrayAlpha8;
/*      */           break;
/*      */         case 3:
/*  936 */           cm = colorModelRGB8;
/*      */           break;
/*      */         case 4:
/*  939 */           cm = colorModelRGBA8;
/*      */           break;
/*      */       } 
/*  942 */     } else if (type == 1) {
/*  943 */       switch (bands) {
/*      */         case 1:
/*  945 */           cm = colorModelGray16;
/*      */           break;
/*      */         case 2:
/*  948 */           cm = colorModelGrayAlpha16;
/*      */           break;
/*      */         case 3:
/*  951 */           cm = colorModelRGB16;
/*      */           break;
/*      */         case 4:
/*  954 */           cm = colorModelRGBA16;
/*      */           break;
/*      */       } 
/*  957 */     } else if (type == 3) {
/*  958 */       switch (bands) {
/*      */         case 1:
/*  960 */           cm = colorModelGray32;
/*      */           break;
/*      */         case 2:
/*  963 */           cm = colorModelGrayAlpha32;
/*      */           break;
/*      */         case 3:
/*  966 */           cm = colorModelRGB32;
/*      */           break;
/*      */         case 4:
/*  969 */           cm = colorModelRGBA32;
/*      */           break;
/*      */       } 
/*      */     
/*      */     } 
/*  974 */     return cm;
/*      */   }
/*      */   
/*      */   private void parse_PLTE_chunk(PNGChunk chunk) {
/*  978 */     this.paletteEntries = chunk.getLength() / 3;
/*  979 */     this.redPalette = new byte[this.paletteEntries];
/*  980 */     this.greenPalette = new byte[this.paletteEntries];
/*  981 */     this.bluePalette = new byte[this.paletteEntries];
/*      */     
/*  983 */     int pltIndex = 0;
/*      */ 
/*      */     
/*  986 */     if (this.performGammaCorrection) {
/*  987 */       if (this.gammaLut == null) {
/*  988 */         initGammaLut((this.bitDepth == 16) ? 16 : 8);
/*      */       }
/*      */       
/*  991 */       for (int i = 0; i < this.paletteEntries; i++) {
/*  992 */         byte r = chunk.getByte(pltIndex++);
/*  993 */         byte g = chunk.getByte(pltIndex++);
/*  994 */         byte b = chunk.getByte(pltIndex++);
/*      */         
/*  996 */         this.redPalette[i] = (byte)this.gammaLut[r & 0xFF];
/*  997 */         this.greenPalette[i] = (byte)this.gammaLut[g & 0xFF];
/*  998 */         this.bluePalette[i] = (byte)this.gammaLut[b & 0xFF];
/*      */       } 
/*      */     } else {
/* 1001 */       for (int i = 0; i < this.paletteEntries; i++) {
/* 1002 */         this.redPalette[i] = chunk.getByte(pltIndex++);
/* 1003 */         this.greenPalette[i] = chunk.getByte(pltIndex++);
/* 1004 */         this.bluePalette[i] = chunk.getByte(pltIndex++);
/*      */       } 
/*      */     }  } private void parse_bKGD_chunk(PNGChunk chunk) {
/*      */     int bkgdIndex;
/*      */     int bkgdGray;
/*      */     int[] bkgdRGB;
/* 1010 */     switch (this.colorType) {
/*      */       case 3:
/* 1012 */         bkgdIndex = chunk.getByte(0) & 0xFF;
/*      */         
/* 1014 */         this.bkgdRed = this.redPalette[bkgdIndex] & 0xFF;
/* 1015 */         this.bkgdGreen = this.greenPalette[bkgdIndex] & 0xFF;
/* 1016 */         this.bkgdBlue = this.bluePalette[bkgdIndex] & 0xFF;
/*      */         
/* 1018 */         if (this.encodeParam != null) {
/* 1019 */           ((PNGEncodeParam.Palette)this.encodeParam).setBackgroundPaletteIndex(bkgdIndex);
/*      */         }
/*      */         break;
/*      */       case 0:
/*      */       case 4:
/* 1024 */         bkgdGray = chunk.getInt2(0);
/* 1025 */         this.bkgdRed = this.bkgdGreen = this.bkgdBlue = bkgdGray;
/*      */         
/* 1027 */         if (this.encodeParam != null) {
/* 1028 */           ((PNGEncodeParam.Gray)this.encodeParam).setBackgroundGray(bkgdGray);
/*      */         }
/*      */         break;
/*      */       case 2:
/*      */       case 6:
/* 1033 */         this.bkgdRed = chunk.getInt2(0);
/* 1034 */         this.bkgdGreen = chunk.getInt2(2);
/* 1035 */         this.bkgdBlue = chunk.getInt2(4);
/*      */         
/* 1037 */         bkgdRGB = new int[3];
/* 1038 */         bkgdRGB[0] = this.bkgdRed;
/* 1039 */         bkgdRGB[1] = this.bkgdGreen;
/* 1040 */         bkgdRGB[2] = this.bkgdBlue;
/* 1041 */         if (this.encodeParam != null) {
/* 1042 */           ((PNGEncodeParam.RGB)this.encodeParam).setBackgroundRGB(bkgdRGB);
/*      */         }
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/* 1048 */     if (this.emitProperties) {
/* 1049 */       int r = 0, g = 0, b = 0;
/* 1050 */       if (this.colorType == 3 || this.bitDepth == 8) {
/* 1051 */         r = this.bkgdRed;
/* 1052 */         g = this.bkgdGreen;
/* 1053 */         b = this.bkgdBlue;
/* 1054 */       } else if (this.bitDepth < 8) {
/* 1055 */         r = this.expandBits[this.bitDepth][this.bkgdRed];
/* 1056 */         g = this.expandBits[this.bitDepth][this.bkgdGreen];
/* 1057 */         b = this.expandBits[this.bitDepth][this.bkgdBlue];
/* 1058 */       } else if (this.bitDepth == 16) {
/* 1059 */         r = this.bkgdRed >> 8;
/* 1060 */         g = this.bkgdGreen >> 8;
/* 1061 */         b = this.bkgdBlue >> 8;
/*      */       } 
/* 1063 */       this.properties.put("background_color", new Color(r, g, b));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void parse_cHRM_chunk(PNGChunk chunk) {
/* 1069 */     if (this.sRGBRenderingIntent != -1) {
/*      */       return;
/*      */     }
/*      */     
/* 1073 */     this.chromaticity = new float[8];
/* 1074 */     this.chromaticity[0] = chunk.getInt4(0) / 100000.0F;
/* 1075 */     this.chromaticity[1] = chunk.getInt4(4) / 100000.0F;
/* 1076 */     this.chromaticity[2] = chunk.getInt4(8) / 100000.0F;
/* 1077 */     this.chromaticity[3] = chunk.getInt4(12) / 100000.0F;
/* 1078 */     this.chromaticity[4] = chunk.getInt4(16) / 100000.0F;
/* 1079 */     this.chromaticity[5] = chunk.getInt4(20) / 100000.0F;
/* 1080 */     this.chromaticity[6] = chunk.getInt4(24) / 100000.0F;
/* 1081 */     this.chromaticity[7] = chunk.getInt4(28) / 100000.0F;
/*      */     
/* 1083 */     if (this.encodeParam != null) {
/* 1084 */       this.encodeParam.setChromaticity(this.chromaticity);
/*      */     }
/* 1086 */     if (this.emitProperties) {
/* 1087 */       this.properties.put("white_point_x", Float.valueOf(this.chromaticity[0]));
/* 1088 */       this.properties.put("white_point_y", Float.valueOf(this.chromaticity[1]));
/* 1089 */       this.properties.put("red_x", Float.valueOf(this.chromaticity[2]));
/* 1090 */       this.properties.put("red_y", Float.valueOf(this.chromaticity[3]));
/* 1091 */       this.properties.put("green_x", Float.valueOf(this.chromaticity[4]));
/* 1092 */       this.properties.put("green_y", Float.valueOf(this.chromaticity[5]));
/* 1093 */       this.properties.put("blue_x", Float.valueOf(this.chromaticity[6]));
/* 1094 */       this.properties.put("blue_y", Float.valueOf(this.chromaticity[7]));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void parse_gAMA_chunk(PNGChunk chunk) {
/* 1100 */     if (this.sRGBRenderingIntent != -1) {
/*      */       return;
/*      */     }
/*      */     
/* 1104 */     this.fileGamma = chunk.getInt4(0) / 100000.0F;
/*      */     
/* 1106 */     float exp = this.performGammaCorrection ? (this.displayExponent / this.userExponent) : 1.0F;
/*      */     
/* 1108 */     if (this.encodeParam != null) {
/* 1109 */       this.encodeParam.setGamma(this.fileGamma * exp);
/*      */     }
/* 1111 */     if (this.emitProperties) {
/* 1112 */       this.properties.put("gamma", Float.valueOf(this.fileGamma * exp));
/*      */     }
/*      */   }
/*      */   
/*      */   private void parse_hIST_chunk(PNGChunk chunk) {
/* 1117 */     if (this.redPalette == null) {
/* 1118 */       String msg = PropertyUtil.getString("PNGImageDecoder18");
/* 1119 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/* 1122 */     int length = this.redPalette.length;
/* 1123 */     int[] hist = new int[length];
/* 1124 */     for (int i = 0; i < length; i++) {
/* 1125 */       hist[i] = chunk.getInt2(2 * i);
/*      */     }
/*      */     
/* 1128 */     if (this.encodeParam != null) {
/* 1129 */       this.encodeParam.setPaletteHistogram(hist);
/*      */     }
/*      */   }
/*      */   
/*      */   private void parse_iCCP_chunk(PNGChunk chunk) {
/* 1134 */     String name = "";
/*      */ 
/*      */     
/* 1137 */     int textIndex = 0; byte b;
/* 1138 */     while ((b = chunk.getByte(textIndex++)) != 0) {
/* 1139 */       name = name + (char)b;
/*      */     }
/*      */   }
/*      */   
/*      */   private void parse_pHYs_chunk(PNGChunk chunk) {
/* 1144 */     int xPixelsPerUnit = chunk.getInt4(0);
/* 1145 */     int yPixelsPerUnit = chunk.getInt4(4);
/* 1146 */     int unitSpecifier = chunk.getInt1(8);
/*      */     
/* 1148 */     if (this.encodeParam != null) {
/* 1149 */       this.encodeParam.setPhysicalDimension(xPixelsPerUnit, yPixelsPerUnit, unitSpecifier);
/*      */     }
/*      */ 
/*      */     
/* 1153 */     if (this.emitProperties) {
/* 1154 */       this.properties.put("x_pixels_per_unit", Integer.valueOf(xPixelsPerUnit));
/* 1155 */       this.properties.put("y_pixels_per_unit", Integer.valueOf(yPixelsPerUnit));
/* 1156 */       this.properties.put("pixel_aspect_ratio", Float.valueOf(xPixelsPerUnit / yPixelsPerUnit));
/*      */       
/* 1158 */       if (unitSpecifier == 1) {
/* 1159 */         this.properties.put("pixel_units", "Meters");
/* 1160 */       } else if (unitSpecifier != 0) {
/*      */         
/* 1162 */         String msg = PropertyUtil.getString("PNGImageDecoder12");
/* 1163 */         throw new RuntimeException(msg);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parse_sBIT_chunk(PNGChunk chunk) {
/* 1169 */     if (this.colorType == 3) {
/* 1170 */       this.significantBits = new int[3];
/*      */     } else {
/* 1172 */       this.significantBits = new int[this.inputBands];
/*      */     } 
/* 1174 */     for (int i = 0; i < this.significantBits.length; i++) {
/* 1175 */       int bits = chunk.getByte(i);
/* 1176 */       int depth = (this.colorType == 3) ? 8 : this.bitDepth;
/* 1177 */       if (bits <= 0 || bits > depth) {
/*      */ 
/*      */         
/* 1180 */         String msg = PropertyUtil.getString("PNGImageDecoder13");
/* 1181 */         throw new RuntimeException(msg);
/*      */       } 
/* 1183 */       this.significantBits[i] = bits;
/*      */     } 
/*      */     
/* 1186 */     if (this.encodeParam != null) {
/* 1187 */       this.encodeParam.setSignificantBits(this.significantBits);
/*      */     }
/* 1189 */     if (this.emitProperties) {
/* 1190 */       this.properties.put("significant_bits", this.significantBits);
/*      */     }
/*      */   }
/*      */   
/*      */   private void parse_sRGB_chunk(PNGChunk chunk) {
/* 1195 */     this.sRGBRenderingIntent = chunk.getByte(0);
/*      */ 
/*      */ 
/*      */     
/* 1199 */     this.fileGamma = 0.45455F;
/*      */     
/* 1201 */     this.chromaticity = new float[8];
/* 1202 */     this.chromaticity[0] = 3.127F;
/* 1203 */     this.chromaticity[1] = 3.29F;
/* 1204 */     this.chromaticity[2] = 6.4F;
/* 1205 */     this.chromaticity[3] = 3.3F;
/* 1206 */     this.chromaticity[4] = 3.0F;
/* 1207 */     this.chromaticity[5] = 6.0F;
/* 1208 */     this.chromaticity[6] = 1.5F;
/* 1209 */     this.chromaticity[7] = 0.6F;
/*      */     
/* 1211 */     if (this.performGammaCorrection) {
/*      */       
/* 1213 */       float gamma = this.fileGamma * this.displayExponent / this.userExponent;
/* 1214 */       if (this.encodeParam != null) {
/* 1215 */         this.encodeParam.setGamma(gamma);
/* 1216 */         this.encodeParam.setChromaticity(this.chromaticity);
/*      */       } 
/* 1218 */       if (this.emitProperties) {
/* 1219 */         this.properties.put("gamma", Float.valueOf(gamma));
/* 1220 */         this.properties.put("white_point_x", Float.valueOf(this.chromaticity[0]));
/* 1221 */         this.properties.put("white_point_y", Float.valueOf(this.chromaticity[1]));
/* 1222 */         this.properties.put("red_x", Float.valueOf(this.chromaticity[2]));
/* 1223 */         this.properties.put("red_y", Float.valueOf(this.chromaticity[3]));
/* 1224 */         this.properties.put("green_x", Float.valueOf(this.chromaticity[4]));
/* 1225 */         this.properties.put("green_y", Float.valueOf(this.chromaticity[5]));
/* 1226 */         this.properties.put("blue_x", Float.valueOf(this.chromaticity[6]));
/* 1227 */         this.properties.put("blue_y", Float.valueOf(this.chromaticity[7]));
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parse_tEXt_chunk(PNGChunk chunk) {
/* 1233 */     StringBuffer key = new StringBuffer();
/* 1234 */     StringBuffer value = new StringBuffer();
/*      */ 
/*      */     
/* 1237 */     int textIndex = 0; byte b;
/* 1238 */     while ((b = chunk.getByte(textIndex++)) != 0) {
/* 1239 */       key.append((char)b);
/*      */     }
/*      */     
/* 1242 */     for (int i = textIndex; i < chunk.getLength(); i++) {
/* 1243 */       value.append((char)chunk.getByte(i));
/*      */     }
/*      */     
/* 1246 */     this.textKeys.add(key.toString());
/* 1247 */     this.textStrings.add(value.toString());
/*      */   }
/*      */   
/*      */   private void parse_tIME_chunk(PNGChunk chunk) {
/* 1251 */     int year = chunk.getInt2(0);
/* 1252 */     int month = chunk.getInt1(2) - 1;
/* 1253 */     int day = chunk.getInt1(3);
/* 1254 */     int hour = chunk.getInt1(4);
/* 1255 */     int minute = chunk.getInt1(5);
/* 1256 */     int second = chunk.getInt1(6);
/*      */     
/* 1258 */     TimeZone gmt = TimeZone.getTimeZone("GMT");
/*      */     
/* 1260 */     GregorianCalendar cal = new GregorianCalendar(gmt);
/* 1261 */     cal.set(year, month, day, hour, minute, second);
/*      */     
/* 1263 */     Date date = cal.getTime();
/*      */     
/* 1265 */     if (this.encodeParam != null) {
/* 1266 */       this.encodeParam.setModificationTime(date);
/*      */     }
/* 1268 */     if (this.emitProperties) {
/* 1269 */       this.properties.put("timestamp", date);
/*      */     }
/*      */   }
/*      */   
/*      */   private void parse_tRNS_chunk(PNGChunk chunk) {
/* 1274 */     if (this.colorType == 3) {
/* 1275 */       int entries = chunk.getLength();
/* 1276 */       if (entries > this.paletteEntries) {
/*      */         
/* 1278 */         String msg = PropertyUtil.getString("PNGImageDecoder14");
/* 1279 */         throw new RuntimeException(msg);
/*      */       } 
/*      */ 
/*      */       
/* 1283 */       this.alphaPalette = new byte[this.paletteEntries]; int i;
/* 1284 */       for (i = 0; i < entries; i++) {
/* 1285 */         this.alphaPalette[i] = chunk.getByte(i);
/*      */       }
/*      */ 
/*      */       
/* 1289 */       for (i = entries; i < this.paletteEntries; i++) {
/* 1290 */         this.alphaPalette[i] = -1;
/*      */       }
/*      */       
/* 1293 */       if (!this.suppressAlpha) {
/* 1294 */         if (this.expandPalette) {
/* 1295 */           this.postProcess = 5;
/* 1296 */           this.outputBands = 4;
/*      */         } else {
/* 1298 */           this.outputHasAlphaPalette = true;
/*      */         } 
/*      */       }
/* 1301 */     } else if (this.colorType == 0) {
/* 1302 */       this.grayTransparentAlpha = chunk.getInt2(0);
/*      */       
/* 1304 */       if (!this.suppressAlpha) {
/* 1305 */         if (this.bitDepth < 8) {
/* 1306 */           this.output8BitGray = true;
/* 1307 */           this.maxOpacity = 255;
/* 1308 */           this.postProcess = 3;
/*      */         } else {
/* 1310 */           this.postProcess = 6;
/*      */         } 
/*      */         
/* 1313 */         if (this.expandGrayAlpha) {
/* 1314 */           this.outputBands = 4;
/* 1315 */           this.postProcess |= 0x10;
/*      */         } else {
/* 1317 */           this.outputBands = 2;
/*      */         } 
/*      */         
/* 1320 */         if (this.encodeParam != null) {
/* 1321 */           ((PNGEncodeParam.Gray)this.encodeParam).setTransparentGray(this.grayTransparentAlpha);
/*      */         }
/*      */       }
/*      */     
/* 1325 */     } else if (this.colorType == 2) {
/* 1326 */       this.redTransparentAlpha = chunk.getInt2(0);
/* 1327 */       this.greenTransparentAlpha = chunk.getInt2(2);
/* 1328 */       this.blueTransparentAlpha = chunk.getInt2(4);
/*      */       
/* 1330 */       if (!this.suppressAlpha) {
/* 1331 */         this.outputBands = 4;
/* 1332 */         this.postProcess = 7;
/*      */         
/* 1334 */         if (this.encodeParam != null) {
/* 1335 */           int[] rgbTrans = new int[3];
/* 1336 */           rgbTrans[0] = this.redTransparentAlpha;
/* 1337 */           rgbTrans[1] = this.greenTransparentAlpha;
/* 1338 */           rgbTrans[2] = this.blueTransparentAlpha;
/* 1339 */           ((PNGEncodeParam.RGB)this.encodeParam).setTransparentRGB(rgbTrans);
/*      */         }
/*      */       
/*      */       } 
/* 1343 */     } else if (this.colorType == 4 || this.colorType == 6) {
/*      */ 
/*      */       
/* 1346 */       String msg = PropertyUtil.getString("PNGImageDecoder15");
/* 1347 */       throw new RuntimeException(msg);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parse_zTXt_chunk(PNGChunk chunk) {
/* 1352 */     StringBuffer key = new StringBuffer();
/* 1353 */     StringBuffer value = new StringBuffer();
/*      */ 
/*      */     
/* 1356 */     int textIndex = 0; byte b;
/* 1357 */     while ((b = chunk.getByte(textIndex++)) != 0) {
/* 1358 */       key.append((char)b);
/*      */     }
/* 1360 */     chunk.getByte(textIndex++);
/*      */     
/*      */     try {
/* 1363 */       int length = chunk.getLength() - textIndex;
/* 1364 */       byte[] data = chunk.getData();
/* 1365 */       InputStream cis = new ByteArrayInputStream(data, textIndex, length);
/*      */       
/* 1367 */       InputStream iis = new InflaterInputStream(cis);
/*      */       
/*      */       int c;
/* 1370 */       while ((c = iis.read()) != -1) {
/* 1371 */         value.append((char)c);
/*      */       }
/*      */       
/* 1374 */       this.ztextKeys.add(key.toString());
/* 1375 */       this.ztextStrings.add(value.toString());
/* 1376 */     } catch (Exception e) {
/* 1377 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private WritableRaster createRaster(int width, int height, int bands, int scanlineStride, int bitDepth) {
/* 1386 */     WritableRaster ras = null;
/* 1387 */     Point origin = new Point(0, 0);
/* 1388 */     if (bitDepth < 8 && bands == 1) {
/* 1389 */       DataBuffer dataBuffer = new DataBufferByte(height * scanlineStride);
/* 1390 */       ras = Raster.createPackedRaster(dataBuffer, width, height, bitDepth, origin);
/*      */ 
/*      */     
/*      */     }
/* 1394 */     else if (bitDepth <= 8) {
/* 1395 */       DataBuffer dataBuffer = new DataBufferByte(height * scanlineStride);
/* 1396 */       ras = Raster.createInterleavedRaster(dataBuffer, width, height, scanlineStride, bands, this.bandOffsets[bands], origin);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/* 1403 */       DataBuffer dataBuffer = new DataBufferUShort(height * scanlineStride);
/* 1404 */       ras = Raster.createInterleavedRaster(dataBuffer, width, height, scanlineStride, bands, this.bandOffsets[bands], origin);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1412 */     return ras;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static void decodeSubFilter(byte[] curr, int count, int bpp) {
/* 1418 */     for (int i = bpp; i < count; i++) {
/*      */ 
/*      */       
/* 1421 */       int val = curr[i] & 0xFF;
/* 1422 */       val += curr[i - bpp] & 0xFF;
/*      */       
/* 1424 */       curr[i] = (byte)val;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static void decodeUpFilter(byte[] curr, byte[] prev, int count) {
/* 1430 */     for (int i = 0; i < count; i++) {
/* 1431 */       int raw = curr[i] & 0xFF;
/* 1432 */       int prior = prev[i] & 0xFF;
/*      */       
/* 1434 */       curr[i] = (byte)(raw + prior);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void decodeAverageFilter(byte[] curr, byte[] prev, int count, int bpp) {
/*      */     int i;
/* 1440 */     for (i = 0; i < bpp; i++) {
/* 1441 */       int raw = curr[i] & 0xFF;
/* 1442 */       int priorRow = prev[i] & 0xFF;
/*      */       
/* 1444 */       curr[i] = (byte)(raw + priorRow / 2);
/*      */     } 
/*      */     
/* 1447 */     for (i = bpp; i < count; i++) {
/* 1448 */       int raw = curr[i] & 0xFF;
/* 1449 */       int priorPixel = curr[i - bpp] & 0xFF;
/* 1450 */       int priorRow = prev[i] & 0xFF;
/*      */       
/* 1452 */       curr[i] = (byte)(raw + (priorPixel + priorRow) / 2);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static int paethPredictor(int a, int b, int c) {
/* 1457 */     int p = a + b - c;
/* 1458 */     int pa = Math.abs(p - a);
/* 1459 */     int pb = Math.abs(p - b);
/* 1460 */     int pc = Math.abs(p - c);
/*      */     
/* 1462 */     if (pa <= pb && pa <= pc)
/* 1463 */       return a; 
/* 1464 */     if (pb <= pc) {
/* 1465 */       return b;
/*      */     }
/* 1467 */     return c;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void decodePaethFilter(byte[] curr, byte[] prev, int count, int bpp) {
/*      */     int i;
/* 1475 */     for (i = 0; i < bpp; i++) {
/* 1476 */       int raw = curr[i] & 0xFF;
/* 1477 */       int priorRow = prev[i] & 0xFF;
/*      */       
/* 1479 */       curr[i] = (byte)(raw + priorRow);
/*      */     } 
/*      */     
/* 1482 */     for (i = bpp; i < count; i++) {
/* 1483 */       int raw = curr[i] & 0xFF;
/* 1484 */       int priorPixel = curr[i - bpp] & 0xFF;
/* 1485 */       int priorRow = prev[i] & 0xFF;
/* 1486 */       int priorRowPixel = prev[i - bpp] & 0xFF;
/*      */       
/* 1488 */       curr[i] = (byte)(raw + paethPredictor(priorPixel, priorRow, priorRowPixel));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processPixels(int process, Raster src, WritableRaster dst, int xOffset, int step, int y, int width) {
/*      */     int srcX;
/*      */     boolean flagGammaCorrection;
/* 1500 */     int[] workGammaLut, ps = src.getPixel(0, 0, (int[])null);
/* 1501 */     int[] pd = dst.getPixel(0, 0, (int[])null);
/*      */     
/* 1503 */     int dstX = xOffset;
/* 1504 */     switch (process) {
/*      */       case 0:
/* 1506 */         for (srcX = 0; srcX < width; srcX++) {
/* 1507 */           src.getPixel(srcX, 0, ps);
/* 1508 */           dst.setPixel(dstX, y, ps);
/* 1509 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 1:
/* 1514 */         for (srcX = 0; srcX < width; srcX++) {
/* 1515 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1517 */           for (int i = 0; i < this.inputBands; i++) {
/* 1518 */             int x = ps[i];
/* 1519 */             ps[i] = this.gammaLut[x];
/*      */           } 
/*      */           
/* 1522 */           dst.setPixel(dstX, y, ps);
/* 1523 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 2:
/* 1528 */         for (srcX = 0; srcX < width; srcX++) {
/* 1529 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1531 */           pd[0] = this.grayLut[ps[0]];
/*      */           
/* 1533 */           dst.setPixel(dstX, y, pd);
/* 1534 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 3:
/* 1539 */         for (srcX = 0; srcX < width; srcX++) {
/* 1540 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1542 */           int val = ps[0];
/* 1543 */           pd[0] = this.grayLut[val];
/* 1544 */           if (val == this.grayTransparentAlpha) {
/* 1545 */             pd[1] = 0;
/*      */           } else {
/* 1547 */             pd[1] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1550 */           dst.setPixel(dstX, y, pd);
/* 1551 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 4:
/* 1556 */         for (srcX = 0; srcX < width; srcX++) {
/* 1557 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1559 */           int val = ps[0];
/* 1560 */           pd[0] = this.redPalette[val];
/* 1561 */           pd[1] = this.greenPalette[val];
/* 1562 */           pd[2] = this.bluePalette[val];
/*      */           
/* 1564 */           dst.setPixel(dstX, y, pd);
/* 1565 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 5:
/* 1570 */         for (srcX = 0; srcX < width; srcX++) {
/* 1571 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1573 */           int val = ps[0];
/* 1574 */           pd[0] = this.redPalette[val];
/* 1575 */           pd[1] = this.greenPalette[val];
/* 1576 */           pd[2] = this.bluePalette[val];
/* 1577 */           pd[3] = this.alphaPalette[val];
/*      */           
/* 1579 */           dst.setPixel(dstX, y, pd);
/* 1580 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 6:
/* 1585 */         for (srcX = 0; srcX < width; srcX++) {
/* 1586 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1588 */           int val = ps[0];
/* 1589 */           if (this.performGammaCorrection) {
/* 1590 */             val = this.gammaLut[val];
/*      */           }
/* 1592 */           pd[0] = val;
/* 1593 */           if (val == this.grayTransparentAlpha) {
/* 1594 */             pd[1] = 0;
/*      */           } else {
/* 1596 */             pd[1] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1599 */           dst.setPixel(dstX, y, pd);
/* 1600 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 7:
/* 1605 */         flagGammaCorrection = this.performGammaCorrection;
/* 1606 */         workGammaLut = this.gammaLut;
/* 1607 */         for (srcX = 0; srcX < width; srcX++) {
/* 1608 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1610 */           int r = ps[0];
/* 1611 */           int g = ps[1];
/* 1612 */           int b = ps[2];
/* 1613 */           if (flagGammaCorrection) {
/* 1614 */             pd[0] = workGammaLut[r];
/* 1615 */             pd[1] = workGammaLut[g];
/* 1616 */             pd[2] = workGammaLut[b];
/*      */           } else {
/* 1618 */             pd[0] = r;
/* 1619 */             pd[1] = g;
/* 1620 */             pd[2] = b;
/*      */           } 
/* 1622 */           if (r == this.redTransparentAlpha && g == this.greenTransparentAlpha && b == this.blueTransparentAlpha) {
/*      */ 
/*      */             
/* 1625 */             pd[3] = 0;
/*      */           } else {
/* 1627 */             pd[3] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1630 */           dst.setPixel(dstX, y, pd);
/* 1631 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 8:
/* 1636 */         for (srcX = 0; srcX < width; srcX++) {
/* 1637 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1639 */           int g = ps[0];
/* 1640 */           if (this.performGammaCorrection) {
/* 1641 */             pd[0] = this.gammaLut[g];
/*      */           } else {
/* 1643 */             pd[0] = g;
/*      */           } 
/*      */           
/* 1646 */           dst.setPixel(dstX, y, pd);
/* 1647 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 9:
/* 1652 */         for (srcX = 0; srcX < width; srcX++) {
/* 1653 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1655 */           int r = ps[0];
/* 1656 */           int g = ps[1];
/* 1657 */           int b = ps[2];
/* 1658 */           if (this.performGammaCorrection) {
/* 1659 */             pd[0] = this.gammaLut[r];
/* 1660 */             pd[1] = this.gammaLut[g];
/* 1661 */             pd[2] = this.gammaLut[b];
/*      */           } else {
/* 1663 */             pd[0] = r;
/* 1664 */             pd[1] = g;
/* 1665 */             pd[2] = b;
/*      */           } 
/*      */           
/* 1668 */           dst.setPixel(dstX, y, pd);
/* 1669 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 17:
/* 1674 */         for (srcX = 0; srcX < width; srcX++) {
/* 1675 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1677 */           int val = ps[0];
/* 1678 */           int alpha = ps[1];
/* 1679 */           int gamma = this.gammaLut[val];
/* 1680 */           pd[0] = gamma;
/* 1681 */           pd[1] = gamma;
/* 1682 */           pd[2] = gamma;
/* 1683 */           pd[3] = alpha;
/*      */           
/* 1685 */           dst.setPixel(dstX, y, pd);
/* 1686 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 16:
/* 1691 */         for (srcX = 0; srcX < width; srcX++) {
/* 1692 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1694 */           int val = ps[0];
/* 1695 */           int alpha = ps[1];
/* 1696 */           pd[0] = val;
/* 1697 */           pd[1] = val;
/* 1698 */           pd[2] = val;
/* 1699 */           pd[3] = alpha;
/*      */           
/* 1701 */           dst.setPixel(dstX, y, pd);
/* 1702 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 22:
/* 1707 */         for (srcX = 0; srcX < width; srcX++) {
/* 1708 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1710 */           int val = ps[0];
/* 1711 */           if (this.performGammaCorrection) {
/* 1712 */             val = this.gammaLut[val];
/*      */           }
/* 1714 */           pd[0] = val;
/* 1715 */           pd[1] = val;
/* 1716 */           pd[2] = val;
/* 1717 */           if (val == this.grayTransparentAlpha) {
/* 1718 */             pd[3] = 0;
/*      */           } else {
/* 1720 */             pd[3] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1723 */           dst.setPixel(dstX, y, pd);
/* 1724 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 19:
/* 1729 */         for (srcX = 0; srcX < width; srcX++) {
/* 1730 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1732 */           int val = ps[0];
/* 1733 */           int val2 = this.grayLut[val];
/* 1734 */           pd[0] = val2;
/* 1735 */           pd[1] = val2;
/* 1736 */           pd[2] = val2;
/* 1737 */           if (val == this.grayTransparentAlpha) {
/* 1738 */             pd[3] = 0;
/*      */           } else {
/* 1740 */             pd[3] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1743 */           dst.setPixel(dstX, y, pd);
/* 1744 */           dstX += step;
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
/* 1758 */     if (passWidth == 0 || passHeight == 0) {
/*      */       return;
/*      */     }
/*      */     
/* 1762 */     int bytesPerRow = (this.inputBands * passWidth * this.bitDepth + 7) / 8;
/* 1763 */     int eltsPerRow = (this.bitDepth == 16) ? (bytesPerRow / 2) : bytesPerRow;
/* 1764 */     byte[] curr = new byte[bytesPerRow];
/* 1765 */     byte[] prior = new byte[bytesPerRow];
/*      */ 
/*      */     
/* 1768 */     WritableRaster passRow = createRaster(passWidth, 1, this.inputBands, eltsPerRow, this.bitDepth);
/*      */ 
/*      */ 
/*      */     
/* 1772 */     DataBuffer dataBuffer = passRow.getDataBuffer();
/* 1773 */     int type = dataBuffer.getDataType();
/* 1774 */     byte[] byteData = null;
/* 1775 */     short[] shortData = null;
/* 1776 */     if (type == 0) {
/* 1777 */       byteData = ((DataBufferByte)dataBuffer).getData();
/*      */     } else {
/* 1779 */       shortData = ((DataBufferUShort)dataBuffer).getData();
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1784 */     int srcY = 0, dstY = yOffset;
/* 1785 */     for (; srcY < passHeight; 
/* 1786 */       srcY++, dstY += yStep) {
/*      */       String msg;
/* 1788 */       int filter = 0;
/*      */       try {
/* 1790 */         filter = this.dataStream.read();
/* 1791 */         this.dataStream.readFully(curr, 0, bytesPerRow);
/* 1792 */       } catch (Exception e) {
/* 1793 */         e.printStackTrace();
/*      */       } 
/*      */       
/* 1796 */       switch (filter) {
/*      */         case 0:
/*      */           break;
/*      */         case 1:
/* 1800 */           decodeSubFilter(curr, bytesPerRow, this.bytesPerPixel);
/*      */           break;
/*      */         case 2:
/* 1803 */           decodeUpFilter(curr, prior, bytesPerRow);
/*      */           break;
/*      */         case 3:
/* 1806 */           decodeAverageFilter(curr, prior, bytesPerRow, this.bytesPerPixel);
/*      */           break;
/*      */         case 4:
/* 1809 */           decodePaethFilter(curr, prior, bytesPerRow, this.bytesPerPixel);
/*      */           break;
/*      */         
/*      */         default:
/* 1813 */           msg = PropertyUtil.getString("PNGImageDecoder16");
/* 1814 */           throw new RuntimeException(msg);
/*      */       } 
/*      */ 
/*      */       
/* 1818 */       if (this.bitDepth < 16) {
/* 1819 */         System.arraycopy(curr, 0, byteData, 0, bytesPerRow);
/*      */       } else {
/* 1821 */         int idx = 0;
/* 1822 */         for (int j = 0; j < eltsPerRow; j++) {
/* 1823 */           shortData[j] = (short)(curr[idx] << 8 | curr[idx + 1] & 0xFF);
/*      */           
/* 1825 */           idx += 2;
/*      */         } 
/*      */       } 
/*      */       
/* 1829 */       processPixels(this.postProcess, passRow, imRas, xOffset, xStep, dstY, passWidth);
/*      */ 
/*      */ 
/*      */       
/* 1833 */       byte[] tmp = prior;
/* 1834 */       prior = curr;
/* 1835 */       curr = tmp;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void decodeImage(boolean useInterlacing) {
/* 1840 */     int width = this.bounds.width;
/* 1841 */     int height = this.bounds.height;
/*      */     
/* 1843 */     if (!useInterlacing) {
/* 1844 */       decodePass(this.theTile, 0, 0, 1, 1, width, height);
/*      */     } else {
/* 1846 */       decodePass(this.theTile, 0, 0, 8, 8, (width + 7) / 8, (height + 7) / 8);
/* 1847 */       decodePass(this.theTile, 4, 0, 8, 8, (width + 3) / 8, (height + 7) / 8);
/* 1848 */       decodePass(this.theTile, 0, 4, 4, 8, (width + 3) / 4, (height + 3) / 8);
/* 1849 */       decodePass(this.theTile, 2, 0, 4, 4, (width + 1) / 4, (height + 3) / 4);
/* 1850 */       decodePass(this.theTile, 0, 2, 2, 4, (width + 1) / 2, (height + 1) / 4);
/* 1851 */       decodePass(this.theTile, 1, 0, 2, 2, width / 2, (height + 1) / 2);
/* 1852 */       decodePass(this.theTile, 0, 1, 1, 2, width, height / 2);
/*      */     } 
/*      */   }
/*      */   
/*      */   public WritableRaster copyData(WritableRaster wr) {
/* 1857 */     GraphicsUtil.copyData(this.theTile, wr);
/* 1858 */     return wr;
/*      */   }
/*      */ 
/*      */   
/*      */   public Raster getTile(int tileX, int tileY) {
/* 1863 */     if (tileX != 0 || tileY != 0) {
/*      */       
/* 1865 */       String msg = PropertyUtil.getString("PNGImageDecoder17");
/* 1866 */       throw new IllegalArgumentException(msg);
/*      */     } 
/* 1868 */     return this.theTile;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/png/PNGRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */