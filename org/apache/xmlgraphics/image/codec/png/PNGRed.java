/*      */ package org.apache.xmlgraphics.image.codec.png;
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
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.TimeZone;
/*      */ import java.util.zip.Inflater;
/*      */ import java.util.zip.InflaterInputStream;
/*      */ import org.apache.xmlgraphics.image.GraphicsUtil;
/*      */ import org.apache.xmlgraphics.image.codec.util.PropertyUtil;
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
/*      */     String typeString;
/*      */     
/*      */     public PNGChunk(int length, int type, byte[] data, int crc) {
/*   81 */       this.length = length;
/*   82 */       this.type = type;
/*   83 */       this.data = data;
/*      */       
/*   85 */       this.typeString = "";
/*   86 */       this.typeString += (char)(type >> 24);
/*   87 */       this.typeString += (char)(type >> 16 & 0xFF);
/*   88 */       this.typeString += (char)(type >> 8 & 0xFF);
/*   89 */       this.typeString += (char)(type & 0xFF);
/*      */     }
/*      */     
/*      */     public int getLength() {
/*   93 */       return this.length;
/*      */     }
/*      */     
/*      */     public int getType() {
/*   97 */       return this.type;
/*      */     }
/*      */     
/*      */     public String getTypeString() {
/*  101 */       return this.typeString;
/*      */     }
/*      */     
/*      */     public byte[] getData() {
/*  105 */       return this.data;
/*      */     }
/*      */     
/*      */     public byte getByte(int offset) {
/*  109 */       return this.data[offset];
/*      */     }
/*      */     
/*      */     public int getInt1(int offset) {
/*  113 */       return this.data[offset] & 0xFF;
/*      */     }
/*      */     
/*      */     public int getInt2(int offset) {
/*  117 */       return (this.data[offset] & 0xFF) << 8 | this.data[offset + 1] & 0xFF;
/*      */     }
/*      */ 
/*      */     
/*      */     public int getInt4(int offset) {
/*  122 */       return (this.data[offset] & 0xFF) << 24 | (this.data[offset + 1] & 0xFF) << 16 | (this.data[offset + 2] & 0xFF) << 8 | this.data[offset + 3] & 0xFF;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getString4(int offset) {
/*  129 */       String s = "";
/*  130 */       s = s + (char)this.data[offset];
/*  131 */       s = s + (char)this.data[offset + 1];
/*  132 */       s = s + (char)this.data[offset + 2];
/*  133 */       s = s + (char)this.data[offset + 3];
/*  134 */       return s;
/*      */     }
/*      */     
/*      */     public boolean isType(String typeName) {
/*  138 */       return this.typeString.equals(typeName);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  148 */   private static final String[] colorTypeNames = new String[] { "Grayscale", "Error", "Truecolor", "Index", "Grayscale with alpha", "Error", "Truecolor with alpha" };
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
/*  159 */   private int[][] bandOffsets = new int[][] { null, { 0 }, { 0, 1 }, { 0, 1, 2 }, { 0, 1, 2, 3 } };
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
/*  224 */   private float fileGamma = 0.45455F;
/*      */   
/*  226 */   private float userExponent = 1.0F;
/*      */   
/*  228 */   private float displayExponent = 2.2F;
/*      */   
/*      */   private float[] chromaticity;
/*      */   
/*  232 */   private int sRGBRenderingIntent = -1;
/*      */ 
/*      */   
/*  235 */   private int postProcess = 0;
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
/*  288 */   private List<InputStream> streamVec = new ArrayList<InputStream>();
/*      */   
/*      */   private DataInputStream dataStream;
/*      */   
/*      */   private int bytesPerPixel;
/*      */   
/*      */   private int inputBands;
/*      */   
/*      */   private int outputBands;
/*      */   private int chunkIndex;
/*  298 */   private List textKeys = new ArrayList();
/*  299 */   private List textStrings = new ArrayList();
/*      */   
/*  301 */   private List ztextKeys = new ArrayList();
/*  302 */   private List ztextStrings = new ArrayList();
/*      */   
/*      */   private WritableRaster theTile;
/*      */   
/*      */   private Rectangle bounds;
/*      */   
/*  308 */   private Map<String, Object> properties = new HashMap<String, Object>();
/*      */   
/*      */   private int[] gammaLut;
/*      */ 
/*      */   
/*      */   private void initGammaLut(int bits) {
/*  314 */     double exp = this.userExponent / (this.fileGamma * this.displayExponent);
/*  315 */     int numSamples = 1 << bits;
/*  316 */     int maxOutSample = (bits == 16) ? 65535 : 255;
/*      */     
/*  318 */     this.gammaLut = new int[numSamples];
/*  319 */     for (int i = 0; i < numSamples; i++) {
/*  320 */       double gbright = i / (numSamples - 1);
/*  321 */       double gamma = Math.pow(gbright, exp);
/*  322 */       int igamma = (int)(gamma * maxOutSample + 0.5D);
/*  323 */       if (igamma > maxOutSample) {
/*  324 */         igamma = maxOutSample;
/*      */       }
/*  326 */       this.gammaLut[i] = igamma;
/*      */     } 
/*      */   }
/*      */   
/*  330 */   private final byte[][] expandBits = new byte[][] { null, { 0, -1 }, { 0, 85, -86, -1 }, null, { 0, 17, 34, 51, 68, 85, 102, 119, -120, -103, -86, -69, -52, -35, -18, -1 } };
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
/*  344 */     int len = 1 << bits;
/*  345 */     this.grayLut = new int[len];
/*      */     
/*  347 */     if (this.performGammaCorrection) {
/*  348 */       System.arraycopy(this.gammaLut, 0, this.grayLut, 0, len);
/*      */     } else {
/*  350 */       for (int i = 0; i < len; i++) {
/*  351 */         this.grayLut[i] = this.expandBits[bits][i];
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public PNGRed(InputStream stream) throws IOException {
/*  357 */     this(stream, null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public PNGRed(InputStream stream, PNGDecodeParam decodeParam) throws IOException {
/*  363 */     if (!stream.markSupported()) {
/*  364 */       stream = new BufferedInputStream(stream);
/*      */     }
/*  366 */     DataInputStream distream = new DataInputStream(stream);
/*      */     
/*  368 */     if (decodeParam == null) {
/*  369 */       decodeParam = new PNGDecodeParam();
/*      */     }
/*  371 */     this.decodeParam = decodeParam;
/*      */ 
/*      */     
/*  374 */     this.suppressAlpha = decodeParam.getSuppressAlpha();
/*  375 */     this.expandPalette = decodeParam.getExpandPalette();
/*  376 */     this.output8BitGray = decodeParam.getOutput8BitGray();
/*  377 */     this.expandGrayAlpha = decodeParam.getExpandGrayAlpha();
/*  378 */     if (decodeParam.getPerformGammaCorrection()) {
/*  379 */       this.userExponent = decodeParam.getUserExponent();
/*  380 */       this.displayExponent = decodeParam.getDisplayExponent();
/*  381 */       this.performGammaCorrection = true;
/*  382 */       this.output8BitGray = true;
/*      */     } 
/*  384 */     this.generateEncodeParam = decodeParam.getGenerateEncodeParam();
/*      */     
/*  386 */     if (this.emitProperties) {
/*  387 */       this.properties.put("file_type", "PNG v. 1.0");
/*      */     }
/*      */     
/*  390 */     long magic = distream.readLong();
/*  391 */     if (magic != -8552249625308161526L) {
/*  392 */       String msg = PropertyUtil.getString("PNGImageDecoder0");
/*  393 */       throw new RuntimeException(msg);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     while (true) {
/*  399 */       String chunkType = getChunkType(distream);
/*  400 */       if (chunkType.equals("IHDR")) {
/*  401 */         PNGChunk pNGChunk = readChunk(distream);
/*  402 */         parse_IHDR_chunk(pNGChunk); continue;
/*  403 */       }  if (chunkType.equals("PLTE")) {
/*  404 */         PNGChunk pNGChunk = readChunk(distream);
/*  405 */         parse_PLTE_chunk(pNGChunk); continue;
/*  406 */       }  if (chunkType.equals("IDAT")) {
/*  407 */         PNGChunk pNGChunk = readChunk(distream);
/*  408 */         this.streamVec.add(new ByteArrayInputStream(pNGChunk.getData())); continue;
/*  409 */       }  if (chunkType.equals("IEND")) {
/*  410 */         PNGChunk pNGChunk = readChunk(distream);
/*      */         try {
/*  412 */           parse_IEND_chunk(pNGChunk);
/*  413 */         } catch (Exception e) {
/*  414 */           e.printStackTrace();
/*  415 */           String msg = PropertyUtil.getString("PNGImageDecoder2");
/*  416 */           throw new RuntimeException(msg);
/*      */         }  break;
/*      */       } 
/*  419 */       if (chunkType.equals("bKGD")) {
/*  420 */         PNGChunk pNGChunk = readChunk(distream);
/*  421 */         parse_bKGD_chunk(pNGChunk); continue;
/*  422 */       }  if (chunkType.equals("cHRM")) {
/*  423 */         PNGChunk pNGChunk = readChunk(distream);
/*  424 */         parse_cHRM_chunk(pNGChunk); continue;
/*  425 */       }  if (chunkType.equals("gAMA")) {
/*  426 */         PNGChunk pNGChunk = readChunk(distream);
/*  427 */         parse_gAMA_chunk(pNGChunk); continue;
/*  428 */       }  if (chunkType.equals("hIST")) {
/*  429 */         PNGChunk pNGChunk = readChunk(distream);
/*  430 */         parse_hIST_chunk(pNGChunk); continue;
/*  431 */       }  if (chunkType.equals("iCCP")) {
/*  432 */         PNGChunk pNGChunk = readChunk(distream); continue;
/*  433 */       }  if (chunkType.equals("pHYs")) {
/*  434 */         PNGChunk pNGChunk = readChunk(distream);
/*  435 */         parse_pHYs_chunk(pNGChunk); continue;
/*  436 */       }  if (chunkType.equals("sBIT")) {
/*  437 */         PNGChunk pNGChunk = readChunk(distream);
/*  438 */         parse_sBIT_chunk(pNGChunk); continue;
/*  439 */       }  if (chunkType.equals("sRGB")) {
/*  440 */         PNGChunk pNGChunk = readChunk(distream);
/*  441 */         parse_sRGB_chunk(pNGChunk); continue;
/*  442 */       }  if (chunkType.equals("tEXt")) {
/*  443 */         PNGChunk pNGChunk = readChunk(distream);
/*  444 */         parse_tEXt_chunk(pNGChunk); continue;
/*  445 */       }  if (chunkType.equals("tIME")) {
/*  446 */         PNGChunk pNGChunk = readChunk(distream);
/*  447 */         parse_tIME_chunk(pNGChunk); continue;
/*  448 */       }  if (chunkType.equals("tRNS")) {
/*  449 */         PNGChunk pNGChunk = readChunk(distream);
/*  450 */         parse_tRNS_chunk(pNGChunk); continue;
/*  451 */       }  if (chunkType.equals("zTXt")) {
/*  452 */         PNGChunk pNGChunk = readChunk(distream);
/*  453 */         parse_zTXt_chunk(pNGChunk); continue;
/*      */       } 
/*  455 */       PNGChunk chunk = readChunk(distream);
/*      */ 
/*      */       
/*  458 */       String type = chunk.getTypeString();
/*  459 */       byte[] data = chunk.getData();
/*  460 */       if (this.encodeParam != null) {
/*  461 */         this.encodeParam.addPrivateChunk(type, data);
/*      */       }
/*  463 */       if (this.emitProperties) {
/*  464 */         String key = "chunk_" + this.chunkIndex++ + ':' + type;
/*  465 */         this.properties.put(key.toLowerCase(Locale.getDefault()), data);
/*      */       } 
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
/*  727 */         this.properties.put(uniqueKey.toLowerCase(Locale.getDefault()), val);
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
/*  744 */         this.properties.put(uniqueKey.toLowerCase(Locale.getDefault()), val);
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
/* 1018 */         if (this.encodeParam != null)
/* 1019 */           ((PNGEncodeParam.Palette)this.encodeParam).setBackgroundPaletteIndex(bkgdIndex); 
/*      */         break;
/*      */       case 0:
/*      */       case 4:
/* 1023 */         bkgdGray = chunk.getInt2(0);
/* 1024 */         this.bkgdRed = this.bkgdGreen = this.bkgdBlue = bkgdGray;
/*      */         
/* 1026 */         if (this.encodeParam != null)
/* 1027 */           ((PNGEncodeParam.Gray)this.encodeParam).setBackgroundGray(bkgdGray); 
/*      */         break;
/*      */       case 2:
/*      */       case 6:
/* 1031 */         this.bkgdRed = chunk.getInt2(0);
/* 1032 */         this.bkgdGreen = chunk.getInt2(2);
/* 1033 */         this.bkgdBlue = chunk.getInt2(4);
/*      */         
/* 1035 */         bkgdRGB = new int[3];
/* 1036 */         bkgdRGB[0] = this.bkgdRed;
/* 1037 */         bkgdRGB[1] = this.bkgdGreen;
/* 1038 */         bkgdRGB[2] = this.bkgdBlue;
/* 1039 */         if (this.encodeParam != null) {
/* 1040 */           ((PNGEncodeParam.RGB)this.encodeParam).setBackgroundRGB(bkgdRGB);
/*      */         }
/*      */         break;
/*      */     } 
/*      */     
/* 1045 */     if (this.emitProperties) {
/* 1046 */       int r = 0;
/* 1047 */       int g = 0;
/* 1048 */       int b = 0;
/* 1049 */       if (this.colorType == 3 || this.bitDepth == 8) {
/* 1050 */         r = this.bkgdRed;
/* 1051 */         g = this.bkgdGreen;
/* 1052 */         b = this.bkgdBlue;
/* 1053 */       } else if (this.bitDepth < 8) {
/* 1054 */         r = this.expandBits[this.bitDepth][this.bkgdRed];
/* 1055 */         g = this.expandBits[this.bitDepth][this.bkgdGreen];
/* 1056 */         b = this.expandBits[this.bitDepth][this.bkgdBlue];
/* 1057 */       } else if (this.bitDepth == 16) {
/* 1058 */         r = this.bkgdRed >> 8;
/* 1059 */         g = this.bkgdGreen >> 8;
/* 1060 */         b = this.bkgdBlue >> 8;
/*      */       } 
/* 1062 */       this.properties.put("background_color", new Color(r, g, b));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void parse_cHRM_chunk(PNGChunk chunk) {
/* 1068 */     if (this.sRGBRenderingIntent != -1) {
/*      */       return;
/*      */     }
/*      */     
/* 1072 */     this.chromaticity = new float[8];
/* 1073 */     this.chromaticity[0] = chunk.getInt4(0) / 100000.0F;
/* 1074 */     this.chromaticity[1] = chunk.getInt4(4) / 100000.0F;
/* 1075 */     this.chromaticity[2] = chunk.getInt4(8) / 100000.0F;
/* 1076 */     this.chromaticity[3] = chunk.getInt4(12) / 100000.0F;
/* 1077 */     this.chromaticity[4] = chunk.getInt4(16) / 100000.0F;
/* 1078 */     this.chromaticity[5] = chunk.getInt4(20) / 100000.0F;
/* 1079 */     this.chromaticity[6] = chunk.getInt4(24) / 100000.0F;
/* 1080 */     this.chromaticity[7] = chunk.getInt4(28) / 100000.0F;
/*      */     
/* 1082 */     if (this.encodeParam != null) {
/* 1083 */       this.encodeParam.setChromaticity(this.chromaticity);
/*      */     }
/* 1085 */     if (this.emitProperties) {
/* 1086 */       this.properties.put("white_point_x", Float.valueOf(this.chromaticity[0]));
/* 1087 */       this.properties.put("white_point_y", Float.valueOf(this.chromaticity[1]));
/* 1088 */       this.properties.put("red_x", Float.valueOf(this.chromaticity[2]));
/* 1089 */       this.properties.put("red_y", Float.valueOf(this.chromaticity[3]));
/* 1090 */       this.properties.put("green_x", Float.valueOf(this.chromaticity[4]));
/* 1091 */       this.properties.put("green_y", Float.valueOf(this.chromaticity[5]));
/* 1092 */       this.properties.put("blue_x", Float.valueOf(this.chromaticity[6]));
/* 1093 */       this.properties.put("blue_y", Float.valueOf(this.chromaticity[7]));
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void parse_gAMA_chunk(PNGChunk chunk) {
/* 1099 */     if (this.sRGBRenderingIntent != -1) {
/*      */       return;
/*      */     }
/*      */     
/* 1103 */     this.fileGamma = chunk.getInt4(0) / 100000.0F;
/*      */     
/* 1105 */     float exp = this.performGammaCorrection ? (this.displayExponent / this.userExponent) : 1.0F;
/*      */     
/* 1107 */     if (this.encodeParam != null) {
/* 1108 */       this.encodeParam.setGamma(this.fileGamma * exp);
/*      */     }
/* 1110 */     if (this.emitProperties) {
/* 1111 */       this.properties.put("gamma", Float.valueOf(this.fileGamma * exp));
/*      */     }
/*      */   }
/*      */   
/*      */   private void parse_hIST_chunk(PNGChunk chunk) {
/* 1116 */     if (this.redPalette == null) {
/* 1117 */       String msg = PropertyUtil.getString("PNGImageDecoder18");
/* 1118 */       throw new RuntimeException(msg);
/*      */     } 
/*      */     
/* 1121 */     int length = this.redPalette.length;
/* 1122 */     int[] hist = new int[length];
/* 1123 */     for (int i = 0; i < length; i++) {
/* 1124 */       hist[i] = chunk.getInt2(2 * i);
/*      */     }
/*      */     
/* 1127 */     if (this.encodeParam != null) {
/* 1128 */       this.encodeParam.setPaletteHistogram(hist);
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
/*      */   private void parse_tEXt_chunk(PNGChunk chunk) {
/* 1222 */     StringBuffer key = new StringBuffer();
/* 1223 */     StringBuffer value = new StringBuffer();
/*      */ 
/*      */     
/* 1226 */     int textIndex = 0; byte b;
/* 1227 */     while ((b = chunk.getByte(textIndex++)) != 0) {
/* 1228 */       key.append((char)b);
/*      */     }
/*      */     
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
/* 1313 */     } else if (this.colorType == 2) {
/* 1314 */       this.redTransparentAlpha = chunk.getInt2(0);
/* 1315 */       this.greenTransparentAlpha = chunk.getInt2(2);
/* 1316 */       this.blueTransparentAlpha = chunk.getInt2(4);
/*      */       
/* 1318 */       if (!this.suppressAlpha) {
/* 1319 */         this.outputBands = 4;
/* 1320 */         this.postProcess = 7;
/*      */         
/* 1322 */         if (this.encodeParam != null) {
/* 1323 */           int[] rgbTrans = new int[3];
/* 1324 */           rgbTrans[0] = this.redTransparentAlpha;
/* 1325 */           rgbTrans[1] = this.greenTransparentAlpha;
/* 1326 */           rgbTrans[2] = this.blueTransparentAlpha;
/* 1327 */           ((PNGEncodeParam.RGB)this.encodeParam).setTransparentRGB(rgbTrans);
/*      */         } 
/*      */       } 
/* 1330 */     } else if (this.colorType == 4 || this.colorType == 6) {
/*      */ 
/*      */       
/* 1333 */       String msg = PropertyUtil.getString("PNGImageDecoder15");
/* 1334 */       throw new RuntimeException(msg);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void parse_zTXt_chunk(PNGChunk chunk) {
/* 1339 */     StringBuffer key = new StringBuffer();
/* 1340 */     StringBuffer value = new StringBuffer();
/*      */ 
/*      */     
/* 1343 */     int textIndex = 0; byte b;
/* 1344 */     while ((b = chunk.getByte(textIndex++)) != 0) {
/* 1345 */       key.append((char)b);
/*      */     }
/*      */ 
/*      */     
/* 1349 */     textIndex++;
/*      */     
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
/*      */   private static void decodeAverageFilter(byte[] curr, byte[] prev, int count, int bpp) {
/*      */     int i;
/* 1429 */     for (i = 0; i < bpp; i++) {
/* 1430 */       int raw = curr[i] & 0xFF;
/* 1431 */       int priorRow = prev[i] & 0xFF;
/*      */       
/* 1433 */       curr[i] = (byte)(raw + priorRow / 2);
/*      */     } 
/*      */     
/* 1436 */     for (i = bpp; i < count; i++) {
/* 1437 */       int raw = curr[i] & 0xFF;
/* 1438 */       int priorPixel = curr[i - bpp] & 0xFF;
/* 1439 */       int priorRow = prev[i] & 0xFF;
/*      */       
/* 1441 */       curr[i] = (byte)(raw + (priorPixel + priorRow) / 2);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static int paethPredictor(int a, int b, int c) {
/* 1446 */     int p = a + b - c;
/* 1447 */     int pa = Math.abs(p - a);
/* 1448 */     int pb = Math.abs(p - b);
/* 1449 */     int pc = Math.abs(p - c);
/*      */     
/* 1451 */     if (pa <= pb && pa <= pc)
/* 1452 */       return a; 
/* 1453 */     if (pb <= pc) {
/* 1454 */       return b;
/*      */     }
/* 1456 */     return c;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static void decodePaethFilter(byte[] curr, byte[] prev, int count, int bpp) {
/*      */     int i;
/* 1465 */     for (i = 0; i < bpp; i++) {
/* 1466 */       int raw = curr[i] & 0xFF;
/* 1467 */       int priorRow = prev[i] & 0xFF;
/*      */       
/* 1469 */       curr[i] = (byte)(raw + priorRow);
/*      */     } 
/*      */     
/* 1472 */     for (i = bpp; i < count; i++) {
/* 1473 */       int raw = curr[i] & 0xFF;
/* 1474 */       int priorPixel = curr[i - bpp] & 0xFF;
/* 1475 */       int priorRow = prev[i] & 0xFF;
/* 1476 */       int priorRowPixel = prev[i - bpp] & 0xFF;
/*      */       
/* 1478 */       curr[i] = (byte)(raw + paethPredictor(priorPixel, priorRow, priorRowPixel));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void processPixels(int process, Raster src, WritableRaster dst, int xOffset, int step, int y, int width) {
/*      */     int srcX;
/*      */     boolean flagGammaCorrection;
/* 1491 */     int[] workGammaLut, ps = src.getPixel(0, 0, (int[])null);
/* 1492 */     int[] pd = dst.getPixel(0, 0, (int[])null);
/*      */     
/* 1494 */     int dstX = xOffset;
/* 1495 */     switch (process) {
/*      */       case 0:
/* 1497 */         for (srcX = 0; srcX < width; srcX++) {
/* 1498 */           src.getPixel(srcX, 0, ps);
/* 1499 */           dst.setPixel(dstX, y, ps);
/* 1500 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 1:
/* 1505 */         for (srcX = 0; srcX < width; srcX++) {
/* 1506 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1508 */           for (int i = 0; i < this.inputBands; i++) {
/* 1509 */             int x = ps[i];
/* 1510 */             ps[i] = this.gammaLut[x];
/*      */           } 
/*      */           
/* 1513 */           dst.setPixel(dstX, y, ps);
/* 1514 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 2:
/* 1519 */         for (srcX = 0; srcX < width; srcX++) {
/* 1520 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1522 */           pd[0] = this.grayLut[ps[0]];
/*      */           
/* 1524 */           dst.setPixel(dstX, y, pd);
/* 1525 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 3:
/* 1530 */         for (srcX = 0; srcX < width; srcX++) {
/* 1531 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1533 */           int val = ps[0];
/* 1534 */           pd[0] = this.grayLut[val];
/* 1535 */           if (val == this.grayTransparentAlpha) {
/* 1536 */             pd[1] = 0;
/*      */           } else {
/* 1538 */             pd[1] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1541 */           dst.setPixel(dstX, y, pd);
/* 1542 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 4:
/* 1547 */         for (srcX = 0; srcX < width; srcX++) {
/* 1548 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1550 */           int val = ps[0];
/* 1551 */           pd[0] = this.redPalette[val];
/* 1552 */           pd[1] = this.greenPalette[val];
/* 1553 */           pd[2] = this.bluePalette[val];
/*      */           
/* 1555 */           dst.setPixel(dstX, y, pd);
/* 1556 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 5:
/* 1561 */         for (srcX = 0; srcX < width; srcX++) {
/* 1562 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1564 */           int val = ps[0];
/* 1565 */           pd[0] = this.redPalette[val];
/* 1566 */           pd[1] = this.greenPalette[val];
/* 1567 */           pd[2] = this.bluePalette[val];
/* 1568 */           pd[3] = this.alphaPalette[val];
/*      */           
/* 1570 */           dst.setPixel(dstX, y, pd);
/* 1571 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 6:
/* 1576 */         for (srcX = 0; srcX < width; srcX++) {
/* 1577 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1579 */           int val = ps[0];
/* 1580 */           if (this.performGammaCorrection) {
/* 1581 */             val = this.gammaLut[val];
/*      */           }
/* 1583 */           pd[0] = val;
/* 1584 */           if (val == this.grayTransparentAlpha) {
/* 1585 */             pd[1] = 0;
/*      */           } else {
/* 1587 */             pd[1] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1590 */           dst.setPixel(dstX, y, pd);
/* 1591 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 7:
/* 1596 */         flagGammaCorrection = this.performGammaCorrection;
/* 1597 */         workGammaLut = this.gammaLut;
/* 1598 */         for (srcX = 0; srcX < width; srcX++) {
/* 1599 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1601 */           int r = ps[0];
/* 1602 */           int g = ps[1];
/* 1603 */           int b = ps[2];
/* 1604 */           if (flagGammaCorrection) {
/* 1605 */             pd[0] = workGammaLut[r];
/* 1606 */             pd[1] = workGammaLut[g];
/* 1607 */             pd[2] = workGammaLut[b];
/*      */           } else {
/* 1609 */             pd[0] = r;
/* 1610 */             pd[1] = g;
/* 1611 */             pd[2] = b;
/*      */           } 
/* 1613 */           if (r == this.redTransparentAlpha && g == this.greenTransparentAlpha && b == this.blueTransparentAlpha) {
/*      */ 
/*      */             
/* 1616 */             pd[3] = 0;
/*      */           } else {
/* 1618 */             pd[3] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1621 */           dst.setPixel(dstX, y, pd);
/* 1622 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 8:
/* 1627 */         for (srcX = 0; srcX < width; srcX++) {
/* 1628 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1630 */           int g = ps[0];
/* 1631 */           if (this.performGammaCorrection) {
/* 1632 */             pd[0] = this.gammaLut[g];
/*      */           } else {
/* 1634 */             pd[0] = g;
/*      */           } 
/*      */           
/* 1637 */           dst.setPixel(dstX, y, pd);
/* 1638 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 9:
/* 1643 */         for (srcX = 0; srcX < width; srcX++) {
/* 1644 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1646 */           int r = ps[0];
/* 1647 */           int g = ps[1];
/* 1648 */           int b = ps[2];
/* 1649 */           if (this.performGammaCorrection) {
/* 1650 */             pd[0] = this.gammaLut[r];
/* 1651 */             pd[1] = this.gammaLut[g];
/* 1652 */             pd[2] = this.gammaLut[b];
/*      */           } else {
/* 1654 */             pd[0] = r;
/* 1655 */             pd[1] = g;
/* 1656 */             pd[2] = b;
/*      */           } 
/*      */           
/* 1659 */           dst.setPixel(dstX, y, pd);
/* 1660 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 17:
/* 1665 */         for (srcX = 0; srcX < width; srcX++) {
/* 1666 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1668 */           int val = ps[0];
/* 1669 */           int alpha = ps[1];
/* 1670 */           int gamma = this.gammaLut[val];
/* 1671 */           pd[0] = gamma;
/* 1672 */           pd[1] = gamma;
/* 1673 */           pd[2] = gamma;
/* 1674 */           pd[3] = alpha;
/*      */           
/* 1676 */           dst.setPixel(dstX, y, pd);
/* 1677 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 16:
/* 1682 */         for (srcX = 0; srcX < width; srcX++) {
/* 1683 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1685 */           int val = ps[0];
/* 1686 */           int alpha = ps[1];
/* 1687 */           pd[0] = val;
/* 1688 */           pd[1] = val;
/* 1689 */           pd[2] = val;
/* 1690 */           pd[3] = alpha;
/*      */           
/* 1692 */           dst.setPixel(dstX, y, pd);
/* 1693 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 22:
/* 1698 */         for (srcX = 0; srcX < width; srcX++) {
/* 1699 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1701 */           int val = ps[0];
/* 1702 */           if (this.performGammaCorrection) {
/* 1703 */             val = this.gammaLut[val];
/*      */           }
/* 1705 */           pd[0] = val;
/* 1706 */           pd[1] = val;
/* 1707 */           pd[2] = val;
/* 1708 */           if (val == this.grayTransparentAlpha) {
/* 1709 */             pd[3] = 0;
/*      */           } else {
/* 1711 */             pd[3] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1714 */           dst.setPixel(dstX, y, pd);
/* 1715 */           dstX += step;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 19:
/* 1720 */         for (srcX = 0; srcX < width; srcX++) {
/* 1721 */           src.getPixel(srcX, 0, ps);
/*      */           
/* 1723 */           int val = ps[0];
/* 1724 */           int val2 = this.grayLut[val];
/* 1725 */           pd[0] = val2;
/* 1726 */           pd[1] = val2;
/* 1727 */           pd[2] = val2;
/* 1728 */           if (val == this.grayTransparentAlpha) {
/* 1729 */             pd[3] = 0;
/*      */           } else {
/* 1731 */             pd[3] = this.maxOpacity;
/*      */           } 
/*      */           
/* 1734 */           dst.setPixel(dstX, y, pd);
/* 1735 */           dstX += step;
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
/* 1749 */     if (passWidth == 0 || passHeight == 0) {
/*      */       return;
/*      */     }
/*      */     
/* 1753 */     int bytesPerRow = (this.inputBands * passWidth * this.bitDepth + 7) / 8;
/* 1754 */     int eltsPerRow = (this.bitDepth == 16) ? (bytesPerRow / 2) : bytesPerRow;
/* 1755 */     byte[] curr = new byte[bytesPerRow];
/* 1756 */     byte[] prior = new byte[bytesPerRow];
/*      */ 
/*      */     
/* 1759 */     WritableRaster passRow = createRaster(passWidth, 1, this.inputBands, eltsPerRow, this.bitDepth);
/*      */ 
/*      */ 
/*      */     
/* 1763 */     DataBuffer dataBuffer = passRow.getDataBuffer();
/* 1764 */     int type = dataBuffer.getDataType();
/* 1765 */     byte[] byteData = null;
/* 1766 */     short[] shortData = null;
/* 1767 */     if (type == 0) {
/* 1768 */       byteData = ((DataBufferByte)dataBuffer).getData();
/*      */     } else {
/* 1770 */       shortData = ((DataBufferUShort)dataBuffer).getData();
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1776 */     int srcY = 0, dstY = yOffset;
/* 1777 */     for (; srcY < passHeight; 
/* 1778 */       srcY++, dstY += yStep) {
/*      */       String msg;
/* 1780 */       int filter = 0;
/*      */       try {
/* 1782 */         filter = this.dataStream.read();
/* 1783 */         this.dataStream.readFully(curr, 0, bytesPerRow);
/* 1784 */       } catch (Exception e) {
/* 1785 */         e.printStackTrace();
/*      */       } 
/*      */       
/* 1788 */       switch (filter) {
/*      */         case 0:
/*      */           break;
/*      */         case 1:
/* 1792 */           decodeSubFilter(curr, bytesPerRow, this.bytesPerPixel);
/*      */           break;
/*      */         case 2:
/* 1795 */           decodeUpFilter(curr, prior, bytesPerRow);
/*      */           break;
/*      */         case 3:
/* 1798 */           decodeAverageFilter(curr, prior, bytesPerRow, this.bytesPerPixel);
/*      */           break;
/*      */         case 4:
/* 1801 */           decodePaethFilter(curr, prior, bytesPerRow, this.bytesPerPixel);
/*      */           break;
/*      */         
/*      */         default:
/* 1805 */           msg = PropertyUtil.getString("PNGImageDecoder16");
/* 1806 */           throw new RuntimeException(msg);
/*      */       } 
/*      */ 
/*      */       
/* 1810 */       if (this.bitDepth < 16) {
/* 1811 */         System.arraycopy(curr, 0, byteData, 0, bytesPerRow);
/*      */       } else {
/* 1813 */         int idx = 0;
/* 1814 */         for (int j = 0; j < eltsPerRow; j++) {
/* 1815 */           shortData[j] = (short)(curr[idx] << 8 | curr[idx + 1] & 0xFF);
/*      */           
/* 1817 */           idx += 2;
/*      */         } 
/*      */       } 
/*      */       
/* 1821 */       processPixels(this.postProcess, passRow, imRas, xOffset, xStep, dstY, passWidth);
/*      */ 
/*      */ 
/*      */       
/* 1825 */       byte[] tmp = prior;
/* 1826 */       prior = curr;
/* 1827 */       curr = tmp;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void decodeImage(boolean useInterlacing) {
/* 1832 */     int width = this.bounds.width;
/* 1833 */     int height = this.bounds.height;
/*      */     
/* 1835 */     if (!useInterlacing) {
/* 1836 */       decodePass(this.theTile, 0, 0, 1, 1, width, height);
/*      */     } else {
/* 1838 */       decodePass(this.theTile, 0, 0, 8, 8, (width + 7) / 8, (height + 7) / 8);
/* 1839 */       decodePass(this.theTile, 4, 0, 8, 8, (width + 3) / 8, (height + 7) / 8);
/* 1840 */       decodePass(this.theTile, 0, 4, 4, 8, (width + 3) / 4, (height + 3) / 8);
/* 1841 */       decodePass(this.theTile, 2, 0, 4, 4, (width + 1) / 4, (height + 3) / 4);
/* 1842 */       decodePass(this.theTile, 0, 2, 2, 4, (width + 1) / 2, (height + 1) / 4);
/* 1843 */       decodePass(this.theTile, 1, 0, 2, 2, width / 2, (height + 1) / 2);
/* 1844 */       decodePass(this.theTile, 0, 1, 1, 2, width, height / 2);
/*      */     } 
/*      */   }
/*      */   
/*      */   public WritableRaster copyData(WritableRaster wr) {
/* 1849 */     GraphicsUtil.copyData(this.theTile, wr);
/* 1850 */     return wr;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Raster getTile(int tileX, int tileY) {
/* 1856 */     if (tileX != 0 || tileY != 0) {
/*      */       
/* 1858 */       String msg = PropertyUtil.getString("PNGImageDecoder17");
/* 1859 */       throw new IllegalArgumentException(msg);
/*      */     } 
/* 1861 */     return this.theTile;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/png/PNGRed.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */