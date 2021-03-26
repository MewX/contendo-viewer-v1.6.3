/*      */ package org.apache.batik.ext.awt.image.codec.png;
/*      */ 
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.IndexColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.TimeZone;
/*      */ import java.util.zip.Deflater;
/*      */ import java.util.zip.DeflaterOutputStream;
/*      */ import org.apache.batik.ext.awt.image.codec.util.ImageEncoderImpl;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PNGImageEncoder
/*      */   extends ImageEncoderImpl
/*      */ {
/*      */   private static final int PNG_COLOR_GRAY = 0;
/*      */   private static final int PNG_COLOR_RGB = 2;
/*      */   private static final int PNG_COLOR_PALETTE = 3;
/*      */   private static final int PNG_COLOR_GRAY_ALPHA = 4;
/*      */   private static final int PNG_COLOR_RGB_ALPHA = 6;
/*  274 */   private static final byte[] magic = new byte[] { -119, 80, 78, 71, 13, 10, 26, 10 };
/*      */   
/*      */   private PNGEncodeParam param;
/*      */   
/*      */   private RenderedImage image;
/*      */   
/*      */   private int width;
/*      */   
/*      */   private int height;
/*      */   
/*      */   private int bitDepth;
/*      */   
/*      */   private int bitShift;
/*      */   
/*      */   private int numBands;
/*      */   
/*      */   private int colorType;
/*      */   
/*      */   private int bpp;
/*      */   private boolean skipAlpha = false;
/*      */   private boolean compressGray = false;
/*      */   private boolean interlace;
/*  296 */   private byte[] redPalette = null;
/*  297 */   private byte[] greenPalette = null;
/*  298 */   private byte[] bluePalette = null;
/*  299 */   private byte[] alphaPalette = null;
/*      */   private DataOutputStream dataOutput;
/*      */   private byte[] prevRow;
/*      */   private byte[] currRow;
/*      */   private byte[][] filteredRows;
/*      */   
/*  305 */   public PNGImageEncoder(OutputStream output, PNGEncodeParam param) { super(output, param);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  331 */     this.prevRow = null;
/*  332 */     this.currRow = null;
/*      */     
/*  334 */     this.filteredRows = (byte[][])null; if (param != null)
/*      */       this.param = param; 
/*      */     this.dataOutput = new DataOutputStream(output); } private void writeMagic() throws IOException { this.dataOutput.write(magic); }
/*  337 */   private static int clamp(int val, int maxValue) { return (val > maxValue) ? maxValue : val; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void encodePass(OutputStream os, Raster ras, int xOffset, int yOffset, int xSkip, int ySkip) throws IOException {
/*  344 */     int minX = ras.getMinX();
/*  345 */     int minY = ras.getMinY();
/*  346 */     int width = ras.getWidth();
/*  347 */     int height = ras.getHeight();
/*      */     
/*  349 */     xOffset *= this.numBands;
/*  350 */     xSkip *= this.numBands;
/*      */     
/*  352 */     int samplesPerByte = 8 / this.bitDepth;
/*      */     
/*  354 */     int numSamples = width * this.numBands;
/*  355 */     int[] samples = new int[numSamples];
/*      */     
/*  357 */     int pixels = (numSamples - xOffset + xSkip - 1) / xSkip;
/*  358 */     int bytesPerRow = pixels * this.numBands;
/*  359 */     if (this.bitDepth < 8) {
/*  360 */       bytesPerRow = (bytesPerRow + samplesPerByte - 1) / samplesPerByte;
/*  361 */     } else if (this.bitDepth == 16) {
/*  362 */       bytesPerRow *= 2;
/*      */     } 
/*      */     
/*  365 */     if (bytesPerRow == 0) {
/*      */       return;
/*      */     }
/*      */     
/*  369 */     this.currRow = new byte[bytesPerRow + this.bpp];
/*  370 */     this.prevRow = new byte[bytesPerRow + this.bpp];
/*      */     
/*  372 */     this.filteredRows = new byte[5][bytesPerRow + this.bpp];
/*      */     
/*  374 */     int maxValue = (1 << this.bitDepth) - 1;
/*      */     int row;
/*  376 */     for (row = minY + yOffset; row < minY + height; row += ySkip) {
/*  377 */       int mask, s; ras.getPixels(minX, row, width, 1, samples);
/*      */       
/*  379 */       if (this.compressGray) {
/*  380 */         int shift = 8 - this.bitDepth;
/*  381 */         for (int i = 0; i < width; i++) {
/*  382 */           samples[i] = samples[i] >> shift;
/*      */         }
/*      */       } 
/*      */       
/*  386 */       int count = this.bpp;
/*  387 */       int pos = 0;
/*  388 */       int tmp = 0;
/*      */       
/*  390 */       switch (this.bitDepth) {
/*      */         case 1:
/*      */         case 2:
/*      */         case 4:
/*  394 */           mask = samplesPerByte - 1;
/*  395 */           for (s = xOffset; s < numSamples; s += xSkip) {
/*  396 */             int val = clamp(samples[s] >> this.bitShift, maxValue);
/*  397 */             tmp = tmp << this.bitDepth | val;
/*      */             
/*  399 */             if (pos++ == mask) {
/*  400 */               this.currRow[count++] = (byte)tmp;
/*  401 */               tmp = 0;
/*  402 */               pos = 0;
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/*  407 */           if (pos != 0) {
/*  408 */             tmp <<= (samplesPerByte - pos) * this.bitDepth;
/*  409 */             this.currRow[count++] = (byte)tmp;
/*      */           } 
/*      */           break;
/*      */         
/*      */         case 8:
/*  414 */           for (s = xOffset; s < numSamples; s += xSkip) {
/*  415 */             for (int b = 0; b < this.numBands; b++) {
/*  416 */               this.currRow[count++] = (byte)clamp(samples[s + b] >> this.bitShift, maxValue);
/*      */             }
/*      */           } 
/*      */           break;
/*      */ 
/*      */         
/*      */         case 16:
/*  423 */           for (s = xOffset; s < numSamples; s += xSkip) {
/*  424 */             for (int b = 0; b < this.numBands; b++) {
/*  425 */               int val = clamp(samples[s + b] >> this.bitShift, maxValue);
/*  426 */               this.currRow[count++] = (byte)(val >> 8);
/*  427 */               this.currRow[count++] = (byte)(val & 0xFF);
/*      */             } 
/*      */           } 
/*      */           break;
/*      */       } 
/*      */ 
/*      */       
/*  434 */       int filterType = this.param.filterRow(this.currRow, this.prevRow, this.filteredRows, bytesPerRow, this.bpp);
/*      */ 
/*      */ 
/*      */       
/*  438 */       os.write(filterType);
/*  439 */       os.write(this.filteredRows[filterType], this.bpp, bytesPerRow);
/*      */ 
/*      */       
/*  442 */       byte[] swap = this.currRow;
/*  443 */       this.currRow = this.prevRow;
/*  444 */       this.prevRow = swap;
/*      */     } 
/*      */   } private void writeIHDR() throws IOException { ChunkStream cs = new ChunkStream("IHDR"); cs.writeInt(this.width); cs.writeInt(this.height); cs.writeByte((byte)this.bitDepth); cs.writeByte((byte)this.colorType); cs.writeByte(0); cs.writeByte(0);
/*      */     cs.writeByte(this.interlace ? 1 : 0);
/*      */     cs.writeToStream(this.dataOutput);
/*  449 */     cs.close(); } private void writeIDAT() throws IOException { IDATOutputStream ios = new IDATOutputStream(this.dataOutput, 8192);
/*  450 */     DeflaterOutputStream dos = new DeflaterOutputStream(ios, new Deflater(9));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  460 */     Raster ras = this.image.getData(new Rectangle(this.image.getMinX(), this.image.getMinY(), this.image.getWidth(), this.image.getHeight()));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  475 */     if (this.skipAlpha) {
/*  476 */       int numBands = ras.getNumBands() - 1;
/*  477 */       int[] bandList = new int[numBands];
/*  478 */       for (int i = 0; i < numBands; i++) {
/*  479 */         bandList[i] = i;
/*      */       }
/*  481 */       ras = ras.createChild(0, 0, ras.getWidth(), ras.getHeight(), 0, 0, bandList);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  487 */     if (this.interlace) {
/*      */       
/*  489 */       encodePass(dos, ras, 0, 0, 8, 8);
/*      */       
/*  491 */       encodePass(dos, ras, 4, 0, 8, 8);
/*      */       
/*  493 */       encodePass(dos, ras, 0, 4, 4, 8);
/*      */       
/*  495 */       encodePass(dos, ras, 2, 0, 4, 4);
/*      */       
/*  497 */       encodePass(dos, ras, 0, 2, 2, 4);
/*      */       
/*  499 */       encodePass(dos, ras, 1, 0, 2, 2);
/*      */       
/*  501 */       encodePass(dos, ras, 0, 1, 1, 2);
/*      */     } else {
/*  503 */       encodePass(dos, ras, 0, 0, 1, 1);
/*      */     } 
/*      */     
/*  506 */     dos.finish();
/*  507 */     dos.close();
/*  508 */     ios.flush();
/*  509 */     ios.close(); }
/*      */ 
/*      */   
/*      */   private void writeIEND() throws IOException {
/*  513 */     ChunkStream cs = new ChunkStream("IEND");
/*  514 */     cs.writeToStream(this.dataOutput);
/*  515 */     cs.close();
/*      */   }
/*      */   
/*  518 */   private static final float[] srgbChroma = new float[] { 0.3127F, 0.329F, 0.64F, 0.33F, 0.3F, 0.6F, 0.15F, 0.06F };
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeCHRM() throws IOException {
/*  523 */     if (this.param.isChromaticitySet() || this.param.isSRGBIntentSet()) {
/*  524 */       float[] chroma; ChunkStream cs = new ChunkStream("cHRM");
/*      */ 
/*      */       
/*  527 */       if (!this.param.isSRGBIntentSet()) {
/*  528 */         chroma = this.param.getChromaticity();
/*      */       } else {
/*  530 */         chroma = srgbChroma;
/*      */       } 
/*      */       
/*  533 */       for (int i = 0; i < 8; i++) {
/*  534 */         cs.writeInt((int)(chroma[i] * 100000.0F));
/*      */       }
/*  536 */       cs.writeToStream(this.dataOutput);
/*  537 */       cs.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeGAMA() throws IOException {
/*  542 */     if (this.param.isGammaSet() || this.param.isSRGBIntentSet()) {
/*  543 */       float gamma; ChunkStream cs = new ChunkStream("gAMA");
/*      */ 
/*      */       
/*  546 */       if (!this.param.isSRGBIntentSet()) {
/*  547 */         gamma = this.param.getGamma();
/*      */       } else {
/*  549 */         gamma = 0.45454544F;
/*      */       } 
/*      */ 
/*      */       
/*  553 */       cs.writeInt((int)(gamma * 100000.0F));
/*  554 */       cs.writeToStream(this.dataOutput);
/*  555 */       cs.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeICCP() throws IOException {
/*  560 */     if (this.param.isICCProfileDataSet()) {
/*  561 */       ChunkStream cs = new ChunkStream("iCCP");
/*  562 */       byte[] ICCProfileData = this.param.getICCProfileData();
/*  563 */       cs.write(ICCProfileData);
/*  564 */       cs.writeToStream(this.dataOutput);
/*  565 */       cs.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeSBIT() throws IOException {
/*  570 */     if (this.param.isSignificantBitsSet()) {
/*  571 */       ChunkStream cs = new ChunkStream("sBIT");
/*  572 */       int[] significantBits = this.param.getSignificantBits();
/*  573 */       int len = significantBits.length;
/*  574 */       for (int significantBit : significantBits) {
/*  575 */         cs.writeByte(significantBit);
/*      */       }
/*  577 */       cs.writeToStream(this.dataOutput);
/*  578 */       cs.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeSRGB() throws IOException {
/*  583 */     if (this.param.isSRGBIntentSet()) {
/*  584 */       ChunkStream cs = new ChunkStream("sRGB");
/*      */       
/*  586 */       int intent = this.param.getSRGBIntent();
/*  587 */       cs.write(intent);
/*  588 */       cs.writeToStream(this.dataOutput);
/*  589 */       cs.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writePLTE() throws IOException {
/*  594 */     if (this.redPalette == null) {
/*      */       return;
/*      */     }
/*      */     
/*  598 */     ChunkStream cs = new ChunkStream("PLTE");
/*  599 */     for (int i = 0; i < this.redPalette.length; i++) {
/*  600 */       cs.writeByte(this.redPalette[i]);
/*  601 */       cs.writeByte(this.greenPalette[i]);
/*  602 */       cs.writeByte(this.bluePalette[i]);
/*      */     } 
/*      */     
/*  605 */     cs.writeToStream(this.dataOutput);
/*  606 */     cs.close();
/*      */   }
/*      */   
/*      */   private void writeBKGD() throws IOException {
/*  610 */     if (this.param.isBackgroundSet()) {
/*  611 */       int gray, index, rgb[]; ChunkStream cs = new ChunkStream("bKGD");
/*      */       
/*  613 */       switch (this.colorType) {
/*      */         case 0:
/*      */         case 4:
/*  616 */           gray = ((PNGEncodeParam.Gray)this.param).getBackgroundGray();
/*  617 */           cs.writeShort(gray);
/*      */           break;
/*      */         
/*      */         case 3:
/*  621 */           index = ((PNGEncodeParam.Palette)this.param).getBackgroundPaletteIndex();
/*      */           
/*  623 */           cs.writeByte(index);
/*      */           break;
/*      */         
/*      */         case 2:
/*      */         case 6:
/*  628 */           rgb = ((PNGEncodeParam.RGB)this.param).getBackgroundRGB();
/*  629 */           cs.writeShort(rgb[0]);
/*  630 */           cs.writeShort(rgb[1]);
/*  631 */           cs.writeShort(rgb[2]);
/*      */           break;
/*      */       } 
/*      */       
/*  635 */       cs.writeToStream(this.dataOutput);
/*  636 */       cs.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeHIST() throws IOException {
/*  641 */     if (this.param.isPaletteHistogramSet()) {
/*  642 */       ChunkStream cs = new ChunkStream("hIST");
/*      */       
/*  644 */       int[] hist = this.param.getPaletteHistogram();
/*  645 */       for (int aHist : hist) {
/*  646 */         cs.writeShort(aHist);
/*      */       }
/*      */       
/*  649 */       cs.writeToStream(this.dataOutput);
/*  650 */       cs.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeTRNS() throws IOException {
/*  655 */     if (this.param.isTransparencySet() && this.colorType != 4 && this.colorType != 6) {
/*      */ 
/*      */       
/*  658 */       ChunkStream cs = new ChunkStream("tRNS");
/*      */       
/*  660 */       if (this.param instanceof PNGEncodeParam.Palette) {
/*  661 */         byte[] t = ((PNGEncodeParam.Palette)this.param).getPaletteTransparency();
/*      */         
/*  663 */         for (byte aT : t) {
/*  664 */           cs.writeByte(aT);
/*      */         }
/*  666 */       } else if (this.param instanceof PNGEncodeParam.Gray) {
/*  667 */         int t = ((PNGEncodeParam.Gray)this.param).getTransparentGray();
/*  668 */         cs.writeShort(t);
/*  669 */       } else if (this.param instanceof PNGEncodeParam.RGB) {
/*  670 */         int[] t = ((PNGEncodeParam.RGB)this.param).getTransparentRGB();
/*  671 */         cs.writeShort(t[0]);
/*  672 */         cs.writeShort(t[1]);
/*  673 */         cs.writeShort(t[2]);
/*      */       } 
/*      */       
/*  676 */       cs.writeToStream(this.dataOutput);
/*  677 */       cs.close();
/*  678 */     } else if (this.colorType == 3) {
/*  679 */       int lastEntry = Math.min(255, this.alphaPalette.length - 1);
/*      */       int nonOpaque;
/*  681 */       for (nonOpaque = lastEntry; nonOpaque >= 0 && 
/*  682 */         this.alphaPalette[nonOpaque] == -1; nonOpaque--);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  687 */       if (nonOpaque >= 0) {
/*  688 */         ChunkStream cs = new ChunkStream("tRNS");
/*  689 */         for (int i = 0; i <= nonOpaque; i++) {
/*  690 */           cs.writeByte(this.alphaPalette[i]);
/*      */         }
/*  692 */         cs.writeToStream(this.dataOutput);
/*  693 */         cs.close();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writePHYS() throws IOException {
/*  699 */     if (this.param.isPhysicalDimensionSet()) {
/*  700 */       ChunkStream cs = new ChunkStream("pHYs");
/*      */       
/*  702 */       int[] dims = this.param.getPhysicalDimension();
/*  703 */       cs.writeInt(dims[0]);
/*  704 */       cs.writeInt(dims[1]);
/*  705 */       cs.writeByte((byte)dims[2]);
/*      */       
/*  707 */       cs.writeToStream(this.dataOutput);
/*  708 */       cs.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeSPLT() throws IOException {
/*  713 */     if (this.param.isSuggestedPaletteSet()) {
/*  714 */       ChunkStream cs = new ChunkStream("sPLT");
/*      */       
/*  716 */       System.out.println("sPLT not supported yet.");
/*      */       
/*  718 */       cs.writeToStream(this.dataOutput);
/*  719 */       cs.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeTIME() throws IOException {
/*  724 */     if (this.param.isModificationTimeSet()) {
/*  725 */       ChunkStream cs = new ChunkStream("tIME");
/*      */       
/*  727 */       Date date = this.param.getModificationTime();
/*  728 */       TimeZone gmt = TimeZone.getTimeZone("GMT");
/*      */       
/*  730 */       GregorianCalendar cal = new GregorianCalendar(gmt);
/*  731 */       cal.setTime(date);
/*      */       
/*  733 */       int year = cal.get(1);
/*  734 */       int month = cal.get(2);
/*  735 */       int day = cal.get(5);
/*  736 */       int hour = cal.get(11);
/*  737 */       int minute = cal.get(12);
/*  738 */       int second = cal.get(13);
/*      */       
/*  740 */       cs.writeShort(year);
/*  741 */       cs.writeByte(month + 1);
/*  742 */       cs.writeByte(day);
/*  743 */       cs.writeByte(hour);
/*  744 */       cs.writeByte(minute);
/*  745 */       cs.writeByte(second);
/*      */       
/*  747 */       cs.writeToStream(this.dataOutput);
/*  748 */       cs.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeTEXT() throws IOException {
/*  753 */     if (this.param.isTextSet()) {
/*  754 */       String[] text = this.param.getText();
/*      */       
/*  756 */       for (int i = 0; i < text.length / 2; i++) {
/*  757 */         byte[] keyword = text[2 * i].getBytes();
/*  758 */         byte[] value = text[2 * i + 1].getBytes();
/*      */         
/*  760 */         ChunkStream cs = new ChunkStream("tEXt");
/*      */         
/*  762 */         cs.write(keyword, 0, Math.min(keyword.length, 79));
/*  763 */         cs.write(0);
/*  764 */         cs.write(value);
/*      */         
/*  766 */         cs.writeToStream(this.dataOutput);
/*  767 */         cs.close();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeZTXT() throws IOException {
/*  773 */     if (this.param.isCompressedTextSet()) {
/*  774 */       String[] text = this.param.getCompressedText();
/*      */       
/*  776 */       for (int i = 0; i < text.length / 2; i++) {
/*  777 */         byte[] keyword = text[2 * i].getBytes();
/*  778 */         byte[] value = text[2 * i + 1].getBytes();
/*      */         
/*  780 */         ChunkStream cs = new ChunkStream("zTXt");
/*      */         
/*  782 */         cs.write(keyword, 0, Math.min(keyword.length, 79));
/*  783 */         cs.write(0);
/*  784 */         cs.write(0);
/*      */         
/*  786 */         DeflaterOutputStream dos = new DeflaterOutputStream(cs);
/*  787 */         dos.write(value);
/*  788 */         dos.finish();
/*  789 */         dos.close();
/*      */         
/*  791 */         cs.writeToStream(this.dataOutput);
/*  792 */         cs.close();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writePrivateChunks() throws IOException {
/*  798 */     int numChunks = this.param.getNumPrivateChunks();
/*  799 */     for (int i = 0; i < numChunks; i++) {
/*  800 */       String type = this.param.getPrivateChunkType(i);
/*  801 */       byte[] data = this.param.getPrivateChunkData(i);
/*      */       
/*  803 */       ChunkStream cs = new ChunkStream(type);
/*  804 */       cs.write(data);
/*  805 */       cs.writeToStream(this.dataOutput);
/*  806 */       cs.close();
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
/*      */   private PNGEncodeParam.Gray createGrayParam(byte[] redPalette, byte[] greenPalette, byte[] bluePalette, byte[] alphaPalette) {
/*  822 */     PNGEncodeParam.Gray param = new PNGEncodeParam.Gray();
/*  823 */     int numTransparent = 0;
/*      */     
/*  825 */     int grayFactor = 255 / ((1 << this.bitDepth) - 1);
/*  826 */     int entries = 1 << this.bitDepth;
/*  827 */     for (int i = 0; i < entries; i++) {
/*  828 */       byte red = redPalette[i];
/*  829 */       if (red != i * grayFactor || red != greenPalette[i] || red != bluePalette[i])
/*      */       {
/*      */         
/*  832 */         return null;
/*      */       }
/*      */ 
/*      */       
/*  836 */       byte alpha = alphaPalette[i];
/*  837 */       if (alpha == 0) {
/*  838 */         param.setTransparentGray(i);
/*      */         
/*  840 */         numTransparent++;
/*  841 */         if (numTransparent > 1) {
/*  842 */           return null;
/*      */         }
/*  844 */       } else if (alpha != -1) {
/*  845 */         return null;
/*      */       } 
/*      */     } 
/*      */     
/*  849 */     return param;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void encode(RenderedImage im) throws IOException {
/*  859 */     this.image = im;
/*  860 */     this.width = this.image.getWidth();
/*  861 */     this.height = this.image.getHeight();
/*      */     
/*  863 */     SampleModel sampleModel = this.image.getSampleModel();
/*      */     
/*  865 */     int[] sampleSize = sampleModel.getSampleSize();
/*      */ 
/*      */     
/*  868 */     this.bitDepth = -1;
/*  869 */     this.bitShift = 0;
/*      */ 
/*      */     
/*  872 */     if (this.param instanceof PNGEncodeParam.Gray) {
/*  873 */       PNGEncodeParam.Gray paramg = (PNGEncodeParam.Gray)this.param;
/*  874 */       if (paramg.isBitDepthSet()) {
/*  875 */         this.bitDepth = paramg.getBitDepth();
/*      */       }
/*      */       
/*  878 */       if (paramg.isBitShiftSet()) {
/*  879 */         this.bitShift = paramg.getBitShift();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  884 */     if (this.bitDepth == -1) {
/*      */ 
/*      */       
/*  887 */       this.bitDepth = sampleSize[0];
/*      */       
/*  889 */       for (int i = 1; i < sampleSize.length; i++) {
/*  890 */         if (sampleSize[i] != this.bitDepth) {
/*  891 */           throw new RuntimeException();
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  896 */       if (this.bitDepth > 2 && this.bitDepth < 4) {
/*  897 */         this.bitDepth = 4;
/*  898 */       } else if (this.bitDepth > 4 && this.bitDepth < 8) {
/*  899 */         this.bitDepth = 8;
/*  900 */       } else if (this.bitDepth > 8 && this.bitDepth < 16) {
/*  901 */         this.bitDepth = 16;
/*  902 */       } else if (this.bitDepth > 16) {
/*  903 */         throw new RuntimeException();
/*      */       } 
/*      */     } 
/*      */     
/*  907 */     this.numBands = sampleModel.getNumBands();
/*  908 */     this.bpp = this.numBands * ((this.bitDepth == 16) ? 2 : 1);
/*      */     
/*  910 */     ColorModel colorModel = this.image.getColorModel();
/*  911 */     if (colorModel instanceof IndexColorModel) {
/*  912 */       if (this.bitDepth < 1 || this.bitDepth > 8) {
/*  913 */         throw new RuntimeException();
/*      */       }
/*  915 */       if (sampleModel.getNumBands() != 1) {
/*  916 */         throw new RuntimeException();
/*      */       }
/*      */       
/*  919 */       IndexColorModel icm = (IndexColorModel)colorModel;
/*  920 */       int size = icm.getMapSize();
/*      */       
/*  922 */       this.redPalette = new byte[size];
/*  923 */       this.greenPalette = new byte[size];
/*  924 */       this.bluePalette = new byte[size];
/*  925 */       this.alphaPalette = new byte[size];
/*      */       
/*  927 */       icm.getReds(this.redPalette);
/*  928 */       icm.getGreens(this.greenPalette);
/*  929 */       icm.getBlues(this.bluePalette);
/*  930 */       icm.getAlphas(this.alphaPalette);
/*      */       
/*  932 */       this.bpp = 1;
/*      */       
/*  934 */       if (this.param == null) {
/*  935 */         this.param = createGrayParam(this.redPalette, this.greenPalette, this.bluePalette, this.alphaPalette);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  942 */       if (this.param == null) {
/*  943 */         this.param = new PNGEncodeParam.Palette();
/*      */       }
/*      */       
/*  946 */       if (this.param instanceof PNGEncodeParam.Palette) {
/*      */         
/*  948 */         PNGEncodeParam.Palette parami = (PNGEncodeParam.Palette)this.param;
/*  949 */         if (parami.isPaletteSet()) {
/*  950 */           int[] palette = parami.getPalette();
/*  951 */           size = palette.length / 3;
/*      */           
/*  953 */           int index = 0;
/*  954 */           for (int i = 0; i < size; i++) {
/*  955 */             this.redPalette[i] = (byte)palette[index++];
/*  956 */             this.greenPalette[i] = (byte)palette[index++];
/*  957 */             this.bluePalette[i] = (byte)palette[index++];
/*  958 */             this.alphaPalette[i] = -1;
/*      */           } 
/*      */         } 
/*  961 */         this.colorType = 3;
/*  962 */       } else if (this.param instanceof PNGEncodeParam.Gray) {
/*  963 */         this.redPalette = this.greenPalette = this.bluePalette = this.alphaPalette = null;
/*  964 */         this.colorType = 0;
/*      */       } else {
/*  966 */         throw new RuntimeException();
/*      */       } 
/*  968 */     } else if (this.numBands == 1) {
/*  969 */       if (this.param == null) {
/*  970 */         this.param = new PNGEncodeParam.Gray();
/*      */       }
/*  972 */       this.colorType = 0;
/*  973 */     } else if (this.numBands == 2) {
/*  974 */       if (this.param == null) {
/*  975 */         this.param = new PNGEncodeParam.Gray();
/*      */       }
/*      */       
/*  978 */       if (this.param.isTransparencySet()) {
/*  979 */         this.skipAlpha = true;
/*  980 */         this.numBands = 1;
/*  981 */         if (sampleSize[0] == 8 && this.bitDepth < 8) {
/*  982 */           this.compressGray = true;
/*      */         }
/*  984 */         this.bpp = (this.bitDepth == 16) ? 2 : 1;
/*  985 */         this.colorType = 0;
/*      */       } else {
/*  987 */         if (this.bitDepth < 8) {
/*  988 */           this.bitDepth = 8;
/*      */         }
/*  990 */         this.colorType = 4;
/*      */       } 
/*  992 */     } else if (this.numBands == 3) {
/*  993 */       if (this.param == null) {
/*  994 */         this.param = new PNGEncodeParam.RGB();
/*      */       }
/*  996 */       this.colorType = 2;
/*  997 */     } else if (this.numBands == 4) {
/*  998 */       if (this.param == null) {
/*  999 */         this.param = new PNGEncodeParam.RGB();
/*      */       }
/* 1001 */       if (this.param.isTransparencySet()) {
/* 1002 */         this.skipAlpha = true;
/* 1003 */         this.numBands = 3;
/* 1004 */         this.bpp = (this.bitDepth == 16) ? 6 : 3;
/* 1005 */         this.colorType = 2;
/*      */       } else {
/* 1007 */         this.colorType = 6;
/*      */       } 
/*      */     } 
/*      */     
/* 1011 */     this.interlace = this.param.getInterlacing();
/*      */     
/* 1013 */     writeMagic();
/*      */     
/* 1015 */     writeIHDR();
/*      */     
/* 1017 */     writeCHRM();
/* 1018 */     writeGAMA();
/* 1019 */     writeICCP();
/* 1020 */     writeSBIT();
/* 1021 */     writeSRGB();
/*      */     
/* 1023 */     writePLTE();
/*      */     
/* 1025 */     writeHIST();
/* 1026 */     writeTRNS();
/* 1027 */     writeBKGD();
/*      */     
/* 1029 */     writePHYS();
/* 1030 */     writeSPLT();
/* 1031 */     writeTIME();
/* 1032 */     writeTEXT();
/* 1033 */     writeZTXT();
/*      */     
/* 1035 */     writePrivateChunks();
/*      */     
/* 1037 */     writeIDAT();
/*      */     
/* 1039 */     writeIEND();
/*      */     
/* 1041 */     this.dataOutput.flush();
/* 1042 */     this.dataOutput.close();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/png/PNGImageEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */