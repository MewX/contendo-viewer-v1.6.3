/*      */ package org.apache.xmlgraphics.image.codec.png;
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
/*      */ import org.apache.xmlgraphics.image.codec.util.ImageEncoderImpl;
/*      */ import org.apache.xmlgraphics.image.codec.util.PropertyUtil;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  291 */   private static final byte[] MAGIC = new byte[] { -119, 80, 78, 71, 13, 10, 26, 10 };
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
/*      */   private int bpp;
/*      */   private boolean skipAlpha;
/*      */   private boolean compressGray;
/*      */   private boolean interlace;
/*      */   private byte[] redPalette;
/*      */   private byte[] greenPalette;
/*      */   private byte[] bluePalette;
/*      */   private byte[] alphaPalette;
/*      */   private DataOutputStream dataOutput;
/*      */   private byte[] prevRow;
/*      */   private byte[] currRow;
/*      */   private byte[][] filteredRows;
/*      */   
/*      */   public PNGImageEncoder(OutputStream output, PNGEncodeParam param) {
/*  322 */     super(output, param);
/*      */     
/*  324 */     if (param != null) {
/*  325 */       this.param = param;
/*      */     }
/*  327 */     this.dataOutput = new DataOutputStream(output);
/*      */   }
/*      */   
/*      */   private void writeMagic() throws IOException {
/*  331 */     this.dataOutput.write(MAGIC);
/*      */   }
/*      */   
/*      */   private void writeIHDR() throws IOException {
/*  335 */     ChunkStream cs = new ChunkStream("IHDR");
/*      */     try {
/*  337 */       cs.writeInt(this.width);
/*  338 */       cs.writeInt(this.height);
/*  339 */       cs.writeByte((byte)this.bitDepth);
/*  340 */       cs.writeByte((byte)this.colorType);
/*  341 */       cs.writeByte(0);
/*  342 */       cs.writeByte(0);
/*  343 */       cs.writeByte(this.interlace ? 1 : 0);
/*      */       
/*  345 */       cs.writeToStream(this.dataOutput);
/*      */     } finally {
/*  347 */       cs.close();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int clamp(int val, int maxValue) {
/*  357 */     return (val > maxValue) ? maxValue : val;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void encodePass(OutputStream os, Raster ras, int xOffset, int yOffset, int xSkip, int ySkip) throws IOException {
/*  364 */     int minX = ras.getMinX();
/*  365 */     int minY = ras.getMinY();
/*  366 */     int width = ras.getWidth();
/*  367 */     int height = ras.getHeight();
/*      */     
/*  369 */     xOffset *= this.numBands;
/*  370 */     xSkip *= this.numBands;
/*      */     
/*  372 */     int samplesPerByte = 8 / this.bitDepth;
/*      */     
/*  374 */     int numSamples = width * this.numBands;
/*  375 */     int[] samples = new int[numSamples];
/*      */     
/*  377 */     int pixels = (numSamples - xOffset + xSkip - 1) / xSkip;
/*  378 */     int bytesPerRow = pixels * this.numBands;
/*  379 */     if (this.bitDepth < 8) {
/*  380 */       bytesPerRow = (bytesPerRow + samplesPerByte - 1) / samplesPerByte;
/*  381 */     } else if (this.bitDepth == 16) {
/*  382 */       bytesPerRow *= 2;
/*      */     } 
/*      */     
/*  385 */     if (bytesPerRow == 0) {
/*      */       return;
/*      */     }
/*      */     
/*  389 */     this.currRow = new byte[bytesPerRow + this.bpp];
/*  390 */     this.prevRow = new byte[bytesPerRow + this.bpp];
/*      */     
/*  392 */     this.filteredRows = new byte[5][bytesPerRow + this.bpp];
/*      */     
/*  394 */     int maxValue = (1 << this.bitDepth) - 1;
/*      */     int row;
/*  396 */     for (row = minY + yOffset; row < minY + height; row += ySkip) {
/*  397 */       int mask, s; ras.getPixels(minX, row, width, 1, samples);
/*      */       
/*  399 */       if (this.compressGray) {
/*  400 */         int shift = 8 - this.bitDepth;
/*  401 */         for (int i = 0; i < width; i++) {
/*  402 */           samples[i] = samples[i] >> shift;
/*      */         }
/*      */       } 
/*      */       
/*  406 */       int count = this.bpp;
/*  407 */       int pos = 0;
/*  408 */       int tmp = 0;
/*      */       
/*  410 */       switch (this.bitDepth) {
/*      */         case 1:
/*      */         case 2:
/*      */         case 4:
/*  414 */           mask = samplesPerByte - 1;
/*  415 */           for (s = xOffset; s < numSamples; s += xSkip) {
/*  416 */             int val = clamp(samples[s] >> this.bitShift, maxValue);
/*  417 */             tmp = tmp << this.bitDepth | val;
/*      */             
/*  419 */             if (pos++ == mask) {
/*  420 */               this.currRow[count++] = (byte)tmp;
/*  421 */               tmp = 0;
/*  422 */               pos = 0;
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/*  427 */           if (pos != 0) {
/*  428 */             tmp <<= (samplesPerByte - pos) * this.bitDepth;
/*  429 */             this.currRow[count++] = (byte)tmp;
/*      */           } 
/*      */           break;
/*      */         
/*      */         case 8:
/*  434 */           for (s = xOffset; s < numSamples; s += xSkip) {
/*  435 */             for (int b = 0; b < this.numBands; b++) {
/*  436 */               this.currRow[count++] = (byte)clamp(samples[s + b] >> this.bitShift, maxValue);
/*      */             }
/*      */           } 
/*      */           break;
/*      */ 
/*      */         
/*      */         case 16:
/*  443 */           for (s = xOffset; s < numSamples; s += xSkip) {
/*  444 */             for (int b = 0; b < this.numBands; b++) {
/*  445 */               int val = clamp(samples[s + b] >> this.bitShift, maxValue);
/*  446 */               this.currRow[count++] = (byte)(val >> 8);
/*  447 */               this.currRow[count++] = (byte)(val & 0xFF);
/*      */             } 
/*      */           } 
/*      */           break;
/*      */       } 
/*      */ 
/*      */       
/*  454 */       int filterType = this.param.filterRow(this.currRow, this.prevRow, this.filteredRows, bytesPerRow, this.bpp);
/*      */ 
/*      */ 
/*      */       
/*  458 */       os.write(filterType);
/*  459 */       os.write(this.filteredRows[filterType], this.bpp, bytesPerRow);
/*      */ 
/*      */       
/*  462 */       byte[] swap = this.currRow;
/*  463 */       this.currRow = this.prevRow;
/*  464 */       this.prevRow = swap;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeIDAT() throws IOException {
/*  469 */     IDATOutputStream ios = new IDATOutputStream(this.dataOutput, 8192);
/*  470 */     DeflaterOutputStream dos = new DeflaterOutputStream(ios, new Deflater(9));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  480 */     Raster ras = this.image.getData(new Rectangle(this.image.getMinX(), this.image.getMinY(), this.image.getWidth(), this.image.getHeight()));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  495 */     if (this.skipAlpha) {
/*  496 */       int numBands = ras.getNumBands() - 1;
/*  497 */       int[] bandList = new int[numBands];
/*  498 */       for (int i = 0; i < numBands; i++) {
/*  499 */         bandList[i] = i;
/*      */       }
/*  501 */       ras = ras.createChild(0, 0, ras.getWidth(), ras.getHeight(), 0, 0, bandList);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  507 */     if (this.interlace) {
/*      */       
/*  509 */       encodePass(dos, ras, 0, 0, 8, 8);
/*      */       
/*  511 */       encodePass(dos, ras, 4, 0, 8, 8);
/*      */       
/*  513 */       encodePass(dos, ras, 0, 4, 4, 8);
/*      */       
/*  515 */       encodePass(dos, ras, 2, 0, 4, 4);
/*      */       
/*  517 */       encodePass(dos, ras, 0, 2, 2, 4);
/*      */       
/*  519 */       encodePass(dos, ras, 1, 0, 2, 2);
/*      */       
/*  521 */       encodePass(dos, ras, 0, 1, 1, 2);
/*      */     } else {
/*  523 */       encodePass(dos, ras, 0, 0, 1, 1);
/*      */     } 
/*      */     
/*  526 */     dos.finish();
/*  527 */     dos.close();
/*  528 */     ios.flush();
/*  529 */     ios.close();
/*      */   }
/*      */   
/*      */   private void writeIEND() throws IOException {
/*  533 */     ChunkStream cs = new ChunkStream("IEND");
/*      */     try {
/*  535 */       cs.writeToStream(this.dataOutput);
/*      */     } finally {
/*  537 */       cs.close();
/*      */     } 
/*      */   }
/*      */   
/*  541 */   private static final float[] SRGB_CHROMA = new float[] { 0.3127F, 0.329F, 0.64F, 0.33F, 0.3F, 0.6F, 0.15F, 0.06F };
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeCHRM() throws IOException {
/*  546 */     if (this.param.isChromaticitySet() || this.param.isSRGBIntentSet()) {
/*  547 */       ChunkStream cs = new ChunkStream("cHRM");
/*      */       try {
/*      */         float[] chroma;
/*  550 */         if (!this.param.isSRGBIntentSet()) {
/*  551 */           chroma = this.param.getChromaticity();
/*      */         } else {
/*  553 */           chroma = SRGB_CHROMA;
/*      */         } 
/*      */         
/*  556 */         for (int i = 0; i < 8; i++) {
/*  557 */           cs.writeInt((int)(chroma[i] * 100000.0F));
/*      */         }
/*  559 */         cs.writeToStream(this.dataOutput);
/*      */       } finally {
/*  561 */         cs.close();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeGAMA() throws IOException {
/*  567 */     if (this.param.isGammaSet() || this.param.isSRGBIntentSet()) {
/*  568 */       ChunkStream cs = new ChunkStream("gAMA");
/*      */       try {
/*      */         float gamma;
/*  571 */         if (!this.param.isSRGBIntentSet()) {
/*  572 */           gamma = this.param.getGamma();
/*      */         } else {
/*  574 */           gamma = 0.45454544F;
/*      */         } 
/*      */ 
/*      */         
/*  578 */         cs.writeInt((int)(gamma * 100000.0F));
/*  579 */         cs.writeToStream(this.dataOutput);
/*      */       } finally {
/*  581 */         cs.close();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeICCP() throws IOException {
/*  587 */     if (this.param.isICCProfileDataSet()) {
/*  588 */       ChunkStream cs = new ChunkStream("iCCP");
/*      */       try {
/*  590 */         byte[] iccProfileData = this.param.getICCProfileData();
/*  591 */         cs.write(iccProfileData);
/*  592 */         cs.writeToStream(this.dataOutput);
/*      */       } finally {
/*  594 */         cs.close();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeSBIT() throws IOException {
/*  600 */     if (this.param.isSignificantBitsSet()) {
/*  601 */       ChunkStream cs = new ChunkStream("sBIT");
/*      */       try {
/*  603 */         int[] significantBits = this.param.getSignificantBits();
/*  604 */         int len = significantBits.length;
/*  605 */         for (int i = 0; i < len; i++) {
/*  606 */           cs.writeByte(significantBits[i]);
/*      */         }
/*  608 */         cs.writeToStream(this.dataOutput);
/*      */       } finally {
/*  610 */         cs.close();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeSRGB() throws IOException {
/*  616 */     if (this.param.isSRGBIntentSet()) {
/*  617 */       ChunkStream cs = new ChunkStream("sRGB");
/*      */       try {
/*  619 */         int intent = this.param.getSRGBIntent();
/*  620 */         cs.write(intent);
/*  621 */         cs.writeToStream(this.dataOutput);
/*      */       } finally {
/*  623 */         cs.close();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writePLTE() throws IOException {
/*  629 */     if (this.redPalette == null) {
/*      */       return;
/*      */     }
/*      */     
/*  633 */     ChunkStream cs = new ChunkStream("PLTE");
/*      */     try {
/*  635 */       for (int i = 0; i < this.redPalette.length; i++) {
/*  636 */         cs.writeByte(this.redPalette[i]);
/*  637 */         cs.writeByte(this.greenPalette[i]);
/*  638 */         cs.writeByte(this.bluePalette[i]);
/*      */       } 
/*      */       
/*  641 */       cs.writeToStream(this.dataOutput);
/*      */     } finally {
/*  643 */       cs.close();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeBKGD() throws IOException {
/*  648 */     if (this.param.isBackgroundSet()) {
/*  649 */       ChunkStream cs = new ChunkStream("bKGD"); try {
/*      */         int gray, index, rgb[];
/*  651 */         switch (this.colorType) {
/*      */           case 0:
/*      */           case 4:
/*  654 */             gray = ((PNGEncodeParam.Gray)this.param).getBackgroundGray();
/*  655 */             cs.writeShort(gray);
/*      */             break;
/*      */           
/*      */           case 3:
/*  659 */             index = ((PNGEncodeParam.Palette)this.param).getBackgroundPaletteIndex();
/*      */             
/*  661 */             cs.writeByte(index);
/*      */             break;
/*      */           
/*      */           case 2:
/*      */           case 6:
/*  666 */             rgb = ((PNGEncodeParam.RGB)this.param).getBackgroundRGB();
/*  667 */             cs.writeShort(rgb[0]);
/*  668 */             cs.writeShort(rgb[1]);
/*  669 */             cs.writeShort(rgb[2]);
/*      */             break;
/*      */         } 
/*      */         
/*  673 */         cs.writeToStream(this.dataOutput);
/*      */       } finally {
/*  675 */         cs.close();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeHIST() throws IOException {
/*  681 */     if (this.param.isPaletteHistogramSet()) {
/*  682 */       ChunkStream cs = new ChunkStream("hIST");
/*      */       try {
/*  684 */         int[] hist = this.param.getPaletteHistogram();
/*  685 */         for (int i = 0; i < hist.length; i++) {
/*  686 */           cs.writeShort(hist[i]);
/*      */         }
/*      */         
/*  689 */         cs.writeToStream(this.dataOutput);
/*      */       } finally {
/*  691 */         cs.close();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeTRNS() throws IOException {
/*  697 */     if (this.param.isTransparencySet() && this.colorType != 4 && this.colorType != 6) {
/*      */ 
/*      */       
/*  700 */       ChunkStream cs = new ChunkStream("tRNS");
/*      */       try {
/*  702 */         if (this.param instanceof PNGEncodeParam.Palette) {
/*  703 */           byte[] t = ((PNGEncodeParam.Palette)this.param).getPaletteTransparency();
/*      */           
/*  705 */           for (int i = 0; i < t.length; i++) {
/*  706 */             cs.writeByte(t[i]);
/*      */           }
/*  708 */         } else if (this.param instanceof PNGEncodeParam.Gray) {
/*  709 */           int t = ((PNGEncodeParam.Gray)this.param).getTransparentGray();
/*  710 */           cs.writeShort(t);
/*  711 */         } else if (this.param instanceof PNGEncodeParam.RGB) {
/*  712 */           int[] t = ((PNGEncodeParam.RGB)this.param).getTransparentRGB();
/*  713 */           cs.writeShort(t[0]);
/*  714 */           cs.writeShort(t[1]);
/*  715 */           cs.writeShort(t[2]);
/*      */         } 
/*      */         
/*  718 */         cs.writeToStream(this.dataOutput);
/*      */       } finally {
/*  720 */         cs.close();
/*      */       } 
/*  722 */     } else if (this.colorType == 3) {
/*  723 */       int lastEntry = Math.min(255, this.alphaPalette.length - 1);
/*      */       int nonOpaque;
/*  725 */       for (nonOpaque = lastEntry; nonOpaque >= 0 && 
/*  726 */         this.alphaPalette[nonOpaque] == -1; nonOpaque--);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  731 */       if (nonOpaque >= 0) {
/*  732 */         ChunkStream cs = new ChunkStream("tRNS");
/*      */         try {
/*  734 */           for (int i = 0; i <= nonOpaque; i++) {
/*  735 */             cs.writeByte(this.alphaPalette[i]);
/*      */           }
/*  737 */           cs.writeToStream(this.dataOutput);
/*      */         } finally {
/*  739 */           cs.close();
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writePHYS() throws IOException {
/*  746 */     if (this.param.isPhysicalDimensionSet()) {
/*  747 */       ChunkStream cs = new ChunkStream("pHYs");
/*      */       try {
/*  749 */         int[] dims = this.param.getPhysicalDimension();
/*  750 */         cs.writeInt(dims[0]);
/*  751 */         cs.writeInt(dims[1]);
/*  752 */         cs.writeByte((byte)dims[2]);
/*      */         
/*  754 */         cs.writeToStream(this.dataOutput);
/*      */       } finally {
/*  756 */         cs.close();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeSPLT() throws IOException {
/*  762 */     if (this.param.isSuggestedPaletteSet()) {
/*  763 */       ChunkStream cs = new ChunkStream("sPLT");
/*      */       try {
/*  765 */         System.out.println("sPLT not supported yet.");
/*      */         
/*  767 */         cs.writeToStream(this.dataOutput);
/*      */       } finally {
/*  769 */         cs.close();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeTIME() throws IOException {
/*  775 */     if (this.param.isModificationTimeSet()) {
/*  776 */       ChunkStream cs = new ChunkStream("tIME");
/*      */       try {
/*  778 */         Date date = this.param.getModificationTime();
/*  779 */         TimeZone gmt = TimeZone.getTimeZone("GMT");
/*      */         
/*  781 */         GregorianCalendar cal = new GregorianCalendar(gmt);
/*  782 */         cal.setTime(date);
/*      */         
/*  784 */         int year = cal.get(1);
/*  785 */         int month = cal.get(2);
/*  786 */         int day = cal.get(5);
/*  787 */         int hour = cal.get(11);
/*  788 */         int minute = cal.get(12);
/*  789 */         int second = cal.get(13);
/*      */         
/*  791 */         cs.writeShort(year);
/*  792 */         cs.writeByte(month + 1);
/*  793 */         cs.writeByte(day);
/*  794 */         cs.writeByte(hour);
/*  795 */         cs.writeByte(minute);
/*  796 */         cs.writeByte(second);
/*      */         
/*  798 */         cs.writeToStream(this.dataOutput);
/*      */       } finally {
/*  800 */         cs.close();
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void writeTEXT() throws IOException {
/*  806 */     if (this.param.isTextSet()) {
/*  807 */       String[] text = this.param.getText();
/*      */       
/*  809 */       for (int i = 0; i < text.length / 2; i++) {
/*  810 */         byte[] keyword = text[2 * i].getBytes("UTF-8");
/*  811 */         byte[] value = text[2 * i + 1].getBytes("UTF-8");
/*      */         
/*  813 */         ChunkStream cs = new ChunkStream("tEXt");
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
/*      */   private void writeZTXT() throws IOException {
/*  828 */     if (this.param.isCompressedTextSet()) {
/*  829 */       String[] text = this.param.getCompressedText();
/*      */       
/*  831 */       for (int i = 0; i < text.length / 2; i++) {
/*  832 */         byte[] keyword = text[2 * i].getBytes("UTF-8");
/*  833 */         byte[] value = text[2 * i + 1].getBytes("UTF-8");
/*      */         
/*  835 */         ChunkStream cs = new ChunkStream("zTXt");
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writePrivateChunks() throws IOException {
/*  858 */     int numChunks = this.param.getNumPrivateChunks();
/*  859 */     for (int i = 0; i < numChunks; i++) {
/*  860 */       String type = this.param.getPrivateChunkType(i);
/*  861 */       byte[] data = this.param.getPrivateChunkData(i);
/*      */       
/*  863 */       ChunkStream cs = new ChunkStream(type);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private PNGEncodeParam.Gray createGrayParam(byte[] redPalette, byte[] greenPalette, byte[] bluePalette, byte[] alphaPalette) {
/*  885 */     PNGEncodeParam.Gray param = new PNGEncodeParam.Gray();
/*  886 */     int numTransparent = 0;
/*      */     
/*  888 */     int grayFactor = 255 / ((1 << this.bitDepth) - 1);
/*  889 */     int entries = 1 << this.bitDepth;
/*  890 */     for (int i = 0; i < entries; i++) {
/*  891 */       byte red = redPalette[i];
/*  892 */       if (red != i * grayFactor || red != greenPalette[i] || red != bluePalette[i])
/*      */       {
/*      */         
/*  895 */         return null;
/*      */       }
/*      */ 
/*      */       
/*  899 */       byte alpha = alphaPalette[i];
/*  900 */       if (alpha == 0) {
/*  901 */         param.setTransparentGray(i);
/*      */         
/*  903 */         numTransparent++;
/*  904 */         if (numTransparent > 1) {
/*  905 */           return null;
/*      */         }
/*  907 */       } else if (alpha != -1) {
/*  908 */         return null;
/*      */       } 
/*      */     } 
/*      */     
/*  912 */     return param;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void encode(RenderedImage im) throws IOException {
/*  923 */     this.image = im;
/*  924 */     this.width = this.image.getWidth();
/*  925 */     this.height = this.image.getHeight();
/*      */     
/*  927 */     SampleModel sampleModel = this.image.getSampleModel();
/*      */     
/*  929 */     int[] sampleSize = sampleModel.getSampleSize();
/*      */ 
/*      */     
/*  932 */     this.bitDepth = -1;
/*  933 */     this.bitShift = 0;
/*      */ 
/*      */     
/*  936 */     if (this.param instanceof PNGEncodeParam.Gray) {
/*  937 */       PNGEncodeParam.Gray paramg = (PNGEncodeParam.Gray)this.param;
/*  938 */       if (paramg.isBitDepthSet()) {
/*  939 */         this.bitDepth = paramg.getBitDepth();
/*      */       }
/*      */       
/*  942 */       if (paramg.isBitShiftSet()) {
/*  943 */         this.bitShift = paramg.getBitShift();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  948 */     if (this.bitDepth == -1) {
/*      */ 
/*      */       
/*  951 */       this.bitDepth = sampleSize[0];
/*      */       
/*  953 */       for (int i = 1; i < sampleSize.length; i++) {
/*  954 */         if (sampleSize[i] != this.bitDepth) {
/*  955 */           throw new RuntimeException(PropertyUtil.getString("PNGImageEncoder0"));
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  960 */       if (this.bitDepth > 2 && this.bitDepth < 4) {
/*  961 */         this.bitDepth = 4;
/*  962 */       } else if (this.bitDepth > 4 && this.bitDepth < 8) {
/*  963 */         this.bitDepth = 8;
/*  964 */       } else if (this.bitDepth > 8 && this.bitDepth < 16) {
/*  965 */         this.bitDepth = 16;
/*  966 */       } else if (this.bitDepth > 16) {
/*  967 */         throw new RuntimeException(PropertyUtil.getString("PNGImageEncoder1"));
/*      */       } 
/*      */     } 
/*      */     
/*  971 */     this.numBands = sampleModel.getNumBands();
/*  972 */     this.bpp = this.numBands * ((this.bitDepth == 16) ? 2 : 1);
/*      */     
/*  974 */     ColorModel colorModel = this.image.getColorModel();
/*  975 */     if (colorModel instanceof IndexColorModel) {
/*  976 */       if (this.bitDepth < 1 || this.bitDepth > 8) {
/*  977 */         throw new RuntimeException(PropertyUtil.getString("PNGImageEncoder2"));
/*      */       }
/*  979 */       if (sampleModel.getNumBands() != 1) {
/*  980 */         throw new RuntimeException(PropertyUtil.getString("PNGImageEncoder3"));
/*      */       }
/*      */       
/*  983 */       IndexColorModel icm = (IndexColorModel)colorModel;
/*  984 */       int size = icm.getMapSize();
/*      */       
/*  986 */       this.redPalette = new byte[size];
/*  987 */       this.greenPalette = new byte[size];
/*  988 */       this.bluePalette = new byte[size];
/*  989 */       this.alphaPalette = new byte[size];
/*      */       
/*  991 */       icm.getReds(this.redPalette);
/*  992 */       icm.getGreens(this.greenPalette);
/*  993 */       icm.getBlues(this.bluePalette);
/*  994 */       icm.getAlphas(this.alphaPalette);
/*      */       
/*  996 */       this.bpp = 1;
/*      */       
/*  998 */       if (this.param == null) {
/*  999 */         this.param = createGrayParam(this.redPalette, this.greenPalette, this.bluePalette, this.alphaPalette);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1006 */       if (this.param == null) {
/* 1007 */         this.param = new PNGEncodeParam.Palette();
/*      */       }
/*      */       
/* 1010 */       if (this.param instanceof PNGEncodeParam.Palette) {
/*      */         
/* 1012 */         PNGEncodeParam.Palette parami = (PNGEncodeParam.Palette)this.param;
/* 1013 */         if (parami.isPaletteSet()) {
/* 1014 */           int[] palette = parami.getPalette();
/* 1015 */           size = palette.length / 3;
/*      */           
/* 1017 */           int index = 0;
/* 1018 */           for (int i = 0; i < size; i++) {
/* 1019 */             this.redPalette[i] = (byte)palette[index++];
/* 1020 */             this.greenPalette[i] = (byte)palette[index++];
/* 1021 */             this.bluePalette[i] = (byte)palette[index++];
/* 1022 */             this.alphaPalette[i] = -1;
/*      */           } 
/*      */         } 
/* 1025 */         this.colorType = 3;
/* 1026 */       } else if (this.param instanceof PNGEncodeParam.Gray) {
/* 1027 */         this.redPalette = this.greenPalette = this.bluePalette = this.alphaPalette = null;
/* 1028 */         this.colorType = 0;
/*      */       } else {
/* 1030 */         throw new RuntimeException(PropertyUtil.getString("PNGImageEncoder4"));
/*      */       } 
/* 1032 */     } else if (this.numBands == 1) {
/* 1033 */       if (this.param == null) {
/* 1034 */         this.param = new PNGEncodeParam.Gray();
/*      */       }
/* 1036 */       this.colorType = 0;
/* 1037 */     } else if (this.numBands == 2) {
/* 1038 */       if (this.param == null) {
/* 1039 */         this.param = new PNGEncodeParam.Gray();
/*      */       }
/*      */       
/* 1042 */       if (this.param.isTransparencySet()) {
/* 1043 */         this.skipAlpha = true;
/* 1044 */         this.numBands = 1;
/* 1045 */         if (sampleSize[0] == 8 && this.bitDepth < 8) {
/* 1046 */           this.compressGray = true;
/*      */         }
/* 1048 */         this.bpp = (this.bitDepth == 16) ? 2 : 1;
/* 1049 */         this.colorType = 0;
/*      */       } else {
/* 1051 */         if (this.bitDepth < 8) {
/* 1052 */           this.bitDepth = 8;
/*      */         }
/* 1054 */         this.colorType = 4;
/*      */       } 
/* 1056 */     } else if (this.numBands == 3) {
/* 1057 */       if (this.param == null) {
/* 1058 */         this.param = new PNGEncodeParam.RGB();
/*      */       }
/* 1060 */       this.colorType = 2;
/* 1061 */     } else if (this.numBands == 4) {
/* 1062 */       if (this.param == null) {
/* 1063 */         this.param = new PNGEncodeParam.RGB();
/*      */       }
/* 1065 */       if (this.param.isTransparencySet()) {
/* 1066 */         this.skipAlpha = true;
/* 1067 */         this.numBands = 3;
/* 1068 */         this.bpp = (this.bitDepth == 16) ? 6 : 3;
/* 1069 */         this.colorType = 2;
/*      */       } else {
/* 1071 */         this.colorType = 6;
/*      */       } 
/*      */     } 
/*      */     
/* 1075 */     this.interlace = this.param.getInterlacing();
/*      */     
/* 1077 */     writeMagic();
/*      */     
/* 1079 */     writeIHDR();
/*      */     
/* 1081 */     writeCHRM();
/* 1082 */     writeGAMA();
/* 1083 */     writeICCP();
/* 1084 */     writeSBIT();
/* 1085 */     writeSRGB();
/*      */     
/* 1087 */     writePLTE();
/*      */     
/* 1089 */     writeHIST();
/* 1090 */     writeTRNS();
/* 1091 */     writeBKGD();
/*      */     
/* 1093 */     writePHYS();
/* 1094 */     writeSPLT();
/* 1095 */     writeTIME();
/* 1096 */     writeTEXT();
/* 1097 */     writeZTXT();
/*      */     
/* 1099 */     writePrivateChunks();
/*      */     
/* 1101 */     writeIDAT();
/*      */     
/* 1103 */     writeIEND();
/*      */     
/* 1105 */     this.dataOutput.flush();
/* 1106 */     this.dataOutput.close();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/png/PNGImageEncoder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */