/*      */ package com.github.jaiimageio.impl.plugins.jpeg;
/*      */ 
/*      */ import com.github.jaiimageio.plugins.tiff.BaselineTIFFTagSet;
/*      */ import com.github.jaiimageio.plugins.tiff.EXIFParentTIFFTagSet;
/*      */ import com.github.jaiimageio.plugins.tiff.TIFFDirectory;
/*      */ import com.github.jaiimageio.plugins.tiff.TIFFField;
/*      */ import com.github.jaiimageio.plugins.tiff.TIFFTagSet;
/*      */ import java.awt.Point;
/*      */ import java.awt.color.ColorSpace;
/*      */ import java.awt.color.ICC_Profile;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentColorModel;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.IndexColorModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.EOFException;
/*      */ import java.io.IOException;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.nio.ByteOrder;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import javax.imageio.IIOException;
/*      */ import javax.imageio.IIOImage;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.imageio.ImageReader;
/*      */ import javax.imageio.metadata.IIOInvalidTreeException;
/*      */ import javax.imageio.metadata.IIOMetadata;
/*      */ import javax.imageio.metadata.IIOMetadataNode;
/*      */ import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
/*      */ import javax.imageio.plugins.jpeg.JPEGQTable;
/*      */ import javax.imageio.stream.ImageInputStream;
/*      */ import javax.imageio.stream.MemoryCacheImageInputStream;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CLibJPEGMetadata
/*      */   extends IIOMetadata
/*      */ {
/*      */   static final String NATIVE_FORMAT = "javax_imageio_jpeg_image_1.0";
/*      */   static final String NATIVE_FORMAT_CLASS = "com.sun.imageio.plugins.jpeg.JPEGImageMetadataFormat";
/*      */   static final String TIFF_FORMAT = "com_sun_media_imageio_plugins_tiff_image_1.0";
/*      */   static final String TIFF_FORMAT_CLASS = "com.github.jaiimageio.impl.plugins.tiff.TIFFImageMetadataFormat";
/*      */   static final int TEM = 1;
/*      */   static final int SOF0 = 192;
/*      */   static final int SOF1 = 193;
/*      */   static final int SOF2 = 194;
/*      */   static final int SOF3 = 195;
/*      */   static final int DHT = 196;
/*      */   static final int SOF5 = 197;
/*      */   static final int SOF6 = 198;
/*      */   static final int SOF7 = 199;
/*      */   static final int JPG = 200;
/*      */   static final int SOF9 = 201;
/*      */   static final int SOF10 = 202;
/*      */   static final int SOF11 = 203;
/*      */   static final int DAC = 204;
/*      */   static final int SOF13 = 205;
/*      */   static final int SOF14 = 206;
/*      */   static final int SOF15 = 207;
/*      */   static final int RST0 = 208;
/*      */   static final int RST1 = 209;
/*      */   static final int RST2 = 210;
/*      */   static final int RST3 = 211;
/*      */   static final int RST4 = 212;
/*      */   static final int RST5 = 213;
/*      */   static final int RST6 = 214;
/*      */   static final int RST7 = 215;
/*      */   static final int RESTART_RANGE = 8;
/*      */   static final int SOI = 216;
/*      */   static final int EOI = 217;
/*      */   static final int SOS = 218;
/*      */   static final int DQT = 219;
/*      */   static final int DNL = 220;
/*      */   static final int DRI = 221;
/*      */   static final int DHP = 222;
/*      */   static final int EXP = 223;
/*      */   static final int APP0 = 224;
/*      */   static final int APP1 = 225;
/*      */   static final int APP2 = 226;
/*      */   static final int APP3 = 227;
/*      */   static final int APP4 = 228;
/*      */   static final int APP5 = 229;
/*      */   static final int APP6 = 230;
/*      */   static final int APP7 = 231;
/*      */   static final int APP8 = 232;
/*      */   static final int APP9 = 233;
/*      */   static final int APP10 = 234;
/*      */   static final int APP11 = 235;
/*      */   static final int APP12 = 236;
/*      */   static final int APP13 = 237;
/*      */   static final int APP14 = 238;
/*      */   static final int APP15 = 239;
/*      */   static final int COM = 254;
/*      */   static final int SOF55 = 247;
/*      */   static final int LSE = 242;
/*      */   static final int APPN_MIN = 224;
/*      */   static final int APPN_MAX = 239;
/*      */   static final int SOFN_MIN = 192;
/*      */   static final int SOFN_MAX = 207;
/*      */   static final int RST_MIN = 208;
/*      */   static final int RST_MAX = 215;
/*      */   static final int APP0_JFIF = 57344;
/*      */   static final int APP0_JFXX = 57345;
/*      */   static final int APP1_EXIF = 57600;
/*      */   static final int APP2_ICC = 57856;
/*      */   static final int APP14_ADOBE = 60928;
/*      */   static final int UNKNOWN_MARKER = 65535;
/*      */   static final int SOF_MARKER = 49152;
/*      */   static final int JFIF_RESUNITS_ASPECT = 0;
/*      */   static final int JFIF_RESUNITS_DPI = 1;
/*      */   static final int JFIF_RESUNITS_DPC = 2;
/*      */   static final int THUMBNAIL_JPEG = 16;
/*      */   static final int THUMBNAIL_PALETTE = 17;
/*      */   static final int THUMBNAIL_RGB = 18;
/*      */   static final int ADOBE_TRANSFORM_UNKNOWN = 0;
/*      */   static final int ADOBE_TRANSFORM_YCC = 1;
/*      */   static final int ADOBE_TRANSFORM_YCCK = 2;
/*  271 */   static final int[] zigzag = new int[] { 0, 1, 5, 6, 14, 15, 27, 28, 2, 4, 7, 13, 16, 26, 29, 42, 3, 8, 12, 17, 25, 30, 41, 43, 9, 11, 18, 24, 31, 40, 44, 53, 10, 19, 23, 32, 39, 45, 52, 54, 20, 22, 33, 38, 46, 51, 55, 60, 21, 34, 37, 47, 50, 56, 59, 61, 35, 36, 48, 49, 57, 58, 62, 63 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static IIOImage getThumbnail(ImageInputStream stream, int len, int thumbnailType, int w, int h) throws IOException {
/*      */     IIOImage result;
/*  290 */     long startPos = stream.getStreamPosition();
/*      */     
/*  292 */     if (thumbnailType == 16) {
/*  293 */       Iterator<ImageReader> readers = ImageIO.getImageReaders(stream);
/*  294 */       if (readers == null || !readers.hasNext()) return null; 
/*  295 */       ImageReader reader = readers.next();
/*  296 */       reader.setInput(stream);
/*  297 */       BufferedImage image = reader.read(0, null);
/*  298 */       IIOMetadata metadata = null;
/*      */       try {
/*  300 */         metadata = reader.getImageMetadata(0);
/*  301 */       } catch (Exception exception) {}
/*      */ 
/*      */       
/*  304 */       result = new IIOImage(image, null, metadata);
/*      */     } else {
/*      */       int numBands;
/*      */       ColorModel cm;
/*  308 */       if (thumbnailType == 17) {
/*  309 */         if (len < 768 + w * h) {
/*  310 */           return null;
/*      */         }
/*      */         
/*  313 */         numBands = 1;
/*      */         
/*  315 */         byte[] palette = new byte[768];
/*  316 */         stream.readFully(palette);
/*  317 */         byte[] r = new byte[256];
/*  318 */         byte[] g = new byte[256];
/*  319 */         byte[] b = new byte[256];
/*  320 */         for (int i = 0, off = 0; i < 256; i++) {
/*  321 */           r[i] = palette[off++];
/*  322 */           g[i] = palette[off++];
/*  323 */           b[i] = palette[off++];
/*      */         } 
/*      */         
/*  326 */         cm = new IndexColorModel(8, 256, r, g, b);
/*      */       } else {
/*  328 */         if (len < 3 * w * h) {
/*  329 */           return null;
/*      */         }
/*      */         
/*  332 */         numBands = 3;
/*      */         
/*  334 */         ColorSpace cs = ColorSpace.getInstance(1000);
/*  335 */         cm = new ComponentColorModel(cs, false, false, 1, 0);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  340 */       byte[] data = new byte[w * h * numBands];
/*  341 */       stream.readFully(data);
/*  342 */       DataBufferByte db = new DataBufferByte(data, data.length);
/*      */       
/*  344 */       WritableRaster wr = Raster.createInterleavedRaster(db, w, h, w * numBands, numBands, new int[] { 0, 1, 2 }, (Point)null);
/*      */       
/*  346 */       BufferedImage image = new BufferedImage(cm, wr, false, null);
/*  347 */       result = new IIOImage(image, null, null);
/*      */     } 
/*      */     
/*  350 */     stream.seek(startPos + len);
/*      */     
/*  352 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isReadOnly = true;
/*      */ 
/*      */   
/*      */   boolean app0JFIFPresent;
/*      */   
/*  362 */   int majorVersion = 1;
/*  363 */   int minorVersion = 2;
/*      */   int resUnits;
/*  365 */   int Xdensity = 1;
/*  366 */   int Ydensity = 1;
/*  367 */   int thumbWidth = 0;
/*  368 */   int thumbHeight = 0;
/*      */   
/*      */   BufferedImage jfifThumbnail;
/*      */   
/*      */   boolean app0JFXXPresent;
/*      */   
/*      */   List extensionCodes;
/*      */   
/*      */   List jfxxThumbnails;
/*      */   boolean app2ICCPresent;
/*  378 */   ICC_Profile profile = null;
/*      */   
/*      */   boolean dqtPresent;
/*      */   
/*      */   List qtables;
/*      */   
/*      */   boolean dhtPresent;
/*      */   
/*      */   List htables;
/*      */   
/*      */   boolean driPresent;
/*      */   
/*      */   int driInterval;
/*      */   
/*      */   boolean comPresent;
/*      */   
/*      */   List comments;
/*      */   
/*      */   boolean unknownPresent;
/*      */   
/*      */   List markerTags;
/*      */   
/*      */   List unknownData;
/*      */   
/*      */   boolean app14AdobePresent;
/*  403 */   int version = 100;
/*  404 */   int flags0 = 0;
/*  405 */   int flags1 = 0;
/*      */   
/*      */   int transform;
/*      */   
/*      */   boolean sofPresent;
/*      */   int sofProcess;
/*  411 */   int samplePrecision = 8;
/*      */   
/*      */   int numLines;
/*      */   
/*      */   int samplesPerLine;
/*      */   
/*      */   int numFrameComponents;
/*      */   
/*      */   int[] componentId;
/*      */   int[] hSamplingFactor;
/*      */   int[] vSamplingFactor;
/*      */   int[] qtableSelector;
/*      */   boolean sosPresent;
/*      */   int numScanComponents;
/*      */   int[] componentSelector;
/*      */   int[] dcHuffTable;
/*      */   int[] acHuffTable;
/*      */   int startSpectralSelection;
/*      */   int endSpectralSelection;
/*      */   int approxHigh;
/*      */   int approxLow;
/*  432 */   byte[] exifData = null;
/*      */ 
/*      */   
/*  435 */   private List markers = null;
/*      */ 
/*      */   
/*      */   private boolean hasAlpha = false;
/*      */   
/*      */   private boolean thumbnailsInitialized = false;
/*      */   
/*  442 */   private List thumbnails = new ArrayList();
/*      */   
/*      */   CLibJPEGMetadata() {
/*  445 */     super(true, "javax_imageio_jpeg_image_1.0", "com.sun.imageio.plugins.jpeg.JPEGImageMetadataFormat", new String[] { "com_sun_media_imageio_plugins_tiff_image_1.0" }, new String[] { "com.github.jaiimageio.impl.plugins.tiff.TIFFImageMetadataFormat" });
/*      */ 
/*      */     
/*  448 */     this.isReadOnly = this.isReadOnly;
/*      */   }
/*      */ 
/*      */   
/*      */   CLibJPEGMetadata(ImageInputStream stream) throws IIOException {
/*  453 */     this();
/*      */     
/*      */     try {
/*  456 */       initializeFromStream(stream);
/*  457 */     } catch (IOException e) {
/*  458 */       throw new IIOException("Cannot initialize JPEG metadata!", e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private class QTable
/*      */   {
/*      */     private static final int QTABLE_SIZE = 64;
/*      */     int elementPrecision;
/*      */     int tableID;
/*      */     JPEGQTable table;
/*      */     int length;
/*      */     
/*      */     QTable(ImageInputStream stream) throws IOException {
/*  472 */       this.elementPrecision = (int)stream.readBits(4);
/*  473 */       this.tableID = (int)stream.readBits(4);
/*  474 */       byte[] tmp = new byte[64];
/*  475 */       stream.readFully(tmp);
/*  476 */       int[] data = new int[64];
/*  477 */       for (int i = 0; i < 64; i++) {
/*  478 */         data[i] = tmp[CLibJPEGMetadata.zigzag[i]] & 0xFF;
/*      */       }
/*  480 */       this.table = new JPEGQTable(data);
/*  481 */       this.length = data.length + 1;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private class HuffmanTable
/*      */   {
/*      */     private static final int NUM_LENGTHS = 16;
/*      */     int tableClass;
/*      */     int tableID;
/*      */     JPEGHuffmanTable table;
/*      */     int length;
/*      */     
/*      */     HuffmanTable(ImageInputStream stream) throws IOException {
/*  495 */       this.tableClass = (int)stream.readBits(4);
/*  496 */       this.tableID = (int)stream.readBits(4);
/*  497 */       short[] lengths = new short[16];
/*  498 */       for (int i = 0; i < 16; i++) {
/*  499 */         lengths[i] = (short)stream.read();
/*      */       }
/*  501 */       int numValues = 0;
/*  502 */       for (int j = 0; j < 16; j++) {
/*  503 */         numValues += lengths[j];
/*      */       }
/*  505 */       short[] values = new short[numValues];
/*  506 */       for (int k = 0; k < numValues; k++) {
/*  507 */         values[k] = (short)stream.read();
/*      */       }
/*  509 */       this.table = new JPEGHuffmanTable(lengths, values);
/*      */       
/*  511 */       this.length = 17 + values.length;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private synchronized void initializeFromStream(ImageInputStream iis) throws IOException {
/*  517 */     iis.mark();
/*  518 */     iis.setByteOrder(ByteOrder.BIG_ENDIAN);
/*      */     
/*  520 */     this.markers = new ArrayList();
/*      */     
/*  522 */     boolean isICCProfileValid = true;
/*  523 */     int numICCProfileChunks = 0;
/*  524 */     long[] iccProfileChunkOffsets = null;
/*  525 */     int[] iccProfileChunkLengths = null;
/*      */ 
/*      */     
/*      */     try {
/*      */       while (true) {
/*  530 */         if (iis.read() == 255) {
/*      */           
/*  532 */           int code = iis.read();
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  537 */           if (code == 0 || code == 255 || code == 216 || code == 1 || (code >= 208 && code <= 215)) {
/*      */             continue;
/*      */           }
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  544 */           if (code == 217) {
/*      */             break;
/*      */           }
/*      */ 
/*      */           
/*  549 */           int dataLength = iis.readUnsignedShort() - 2;
/*      */           
/*  551 */           if (224 <= code && code <= 239) {
/*  552 */             long pos = iis.getStreamPosition();
/*  553 */             boolean appnAdded = false;
/*      */             
/*  555 */             switch (code) {
/*      */               case 224:
/*  557 */                 if (dataLength >= 5) {
/*  558 */                   byte[] b = new byte[5];
/*  559 */                   iis.readFully(b);
/*  560 */                   String id = new String(b);
/*  561 */                   if (id.startsWith("JFIF") && !this.app0JFIFPresent) {
/*      */                     
/*  563 */                     this.app0JFIFPresent = true;
/*  564 */                     this.markers.add(new Integer(57344));
/*  565 */                     this.majorVersion = iis.read();
/*  566 */                     this.minorVersion = iis.read();
/*  567 */                     this.resUnits = iis.read();
/*  568 */                     this.Xdensity = iis.readUnsignedShort();
/*  569 */                     this.Ydensity = iis.readUnsignedShort();
/*  570 */                     this.thumbWidth = iis.read();
/*  571 */                     this.thumbHeight = iis.read();
/*  572 */                     if (this.thumbWidth > 0 && this.thumbHeight > 0) {
/*      */                       
/*  574 */                       IIOImage imiio = getThumbnail(iis, dataLength - 14, 18, this.thumbWidth, this.thumbHeight);
/*      */ 
/*      */ 
/*      */                       
/*  578 */                       if (imiio != null) {
/*  579 */                         this
/*  580 */                           .jfifThumbnail = (BufferedImage)imiio.getRenderedImage();
/*      */                       }
/*      */                     } 
/*  583 */                     appnAdded = true; break;
/*  584 */                   }  if (id.startsWith("JFXX")) {
/*  585 */                     if (!this.app0JFXXPresent) {
/*  586 */                       this.extensionCodes = new ArrayList(1);
/*  587 */                       this.jfxxThumbnails = new ArrayList(1);
/*  588 */                       this.app0JFXXPresent = true;
/*      */                     } 
/*  590 */                     this.markers.add(new Integer(57345));
/*  591 */                     int extCode = iis.read();
/*  592 */                     this.extensionCodes.add(new Integer(extCode));
/*  593 */                     int w = 0, h = 0, offset = 6;
/*  594 */                     if (extCode != 16) {
/*  595 */                       w = iis.read();
/*  596 */                       h = iis.read();
/*  597 */                       offset += 2;
/*      */                     } 
/*      */                     
/*  600 */                     IIOImage imiio = getThumbnail(iis, dataLength - offset, extCode, w, h);
/*      */                     
/*  602 */                     if (imiio != null) {
/*  603 */                       this.jfxxThumbnails.add(imiio);
/*      */                     }
/*  605 */                     appnAdded = true;
/*      */                   } 
/*      */                 } 
/*      */                 break;
/*      */               case 225:
/*  610 */                 if (dataLength >= 6) {
/*  611 */                   byte[] b = new byte[6];
/*  612 */                   iis.readFully(b);
/*  613 */                   if (b[0] == 69 && b[1] == 120 && b[2] == 105 && b[3] == 102 && b[4] == 0 && b[5] == 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                     
/*  619 */                     this.exifData = new byte[dataLength - 6];
/*  620 */                     iis.readFully(this.exifData);
/*      */                   } 
/*      */                 } 
/*      */               case 226:
/*  624 */                 if (dataLength >= 12) {
/*  625 */                   byte[] b = new byte[12];
/*  626 */                   iis.readFully(b);
/*  627 */                   String id = new String(b);
/*  628 */                   if (id.startsWith("ICC_PROFILE")) {
/*  629 */                     if (!isICCProfileValid) {
/*  630 */                       iis.skipBytes(dataLength - 12);
/*      */                       
/*      */                       continue;
/*      */                     } 
/*  634 */                     int chunkNum = iis.read();
/*  635 */                     int numChunks = iis.read();
/*  636 */                     if (numChunks == 0 || chunkNum == 0 || chunkNum > numChunks || (this.app2ICCPresent && (numChunks != numICCProfileChunks || iccProfileChunkOffsets[chunkNum] != 0L))) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                       
/*  643 */                       isICCProfileValid = false;
/*  644 */                       iis.skipBytes(dataLength - 14);
/*      */                       
/*      */                       continue;
/*      */                     } 
/*  648 */                     if (!this.app2ICCPresent) {
/*  649 */                       this.app2ICCPresent = true;
/*      */ 
/*      */                       
/*  652 */                       this.markers.add(new Integer(57856));
/*      */                       
/*  654 */                       numICCProfileChunks = numChunks;
/*      */                       
/*  656 */                       if (numChunks == 1) {
/*  657 */                         b = new byte[dataLength - 14];
/*  658 */                         iis.readFully(b);
/*  659 */                         this
/*  660 */                           .profile = ICC_Profile.getInstance(b);
/*      */                       } else {
/*  662 */                         iccProfileChunkOffsets = new long[numChunks + 1];
/*      */                         
/*  664 */                         iccProfileChunkLengths = new int[numChunks + 1];
/*      */                         
/*  666 */                         iccProfileChunkOffsets[chunkNum] = iis
/*  667 */                           .getStreamPosition();
/*  668 */                         iccProfileChunkLengths[chunkNum] = dataLength - 14;
/*      */                         
/*  670 */                         iis.skipBytes(dataLength - 14);
/*      */                       } 
/*      */                     } else {
/*  673 */                       iccProfileChunkOffsets[chunkNum] = iis
/*  674 */                         .getStreamPosition();
/*  675 */                       iccProfileChunkLengths[chunkNum] = dataLength - 14;
/*      */                       
/*  677 */                       iis.skipBytes(dataLength - 14);
/*      */                     } 
/*      */                     
/*  680 */                     appnAdded = true;
/*      */                   } 
/*      */                 } 
/*      */                 break;
/*      */               case 238:
/*  685 */                 if (dataLength >= 5) {
/*  686 */                   byte[] b = new byte[5];
/*  687 */                   iis.readFully(b);
/*  688 */                   String id = new String(b);
/*  689 */                   if (id.startsWith("Adobe") && !this.app14AdobePresent) {
/*      */                     
/*  691 */                     this.app14AdobePresent = true;
/*  692 */                     this.markers.add(new Integer(60928));
/*  693 */                     this.version = iis.readUnsignedShort();
/*  694 */                     this.flags0 = iis.readUnsignedShort();
/*  695 */                     this.flags1 = iis.readUnsignedShort();
/*  696 */                     this.transform = iis.read();
/*  697 */                     iis.skipBytes(dataLength - 12);
/*  698 */                     appnAdded = true;
/*      */                   } 
/*      */                 } 
/*      */                 break;
/*      */               default:
/*  703 */                 appnAdded = false;
/*      */                 break;
/*      */             } 
/*      */             
/*  707 */             if (!appnAdded) {
/*  708 */               iis.seek(pos);
/*  709 */               addUnknownMarkerSegment(iis, code, dataLength);
/*      */             }  continue;
/*  711 */           }  if (code == 219) {
/*  712 */             if (!this.dqtPresent) {
/*  713 */               this.dqtPresent = true;
/*  714 */               this.qtables = new ArrayList(1);
/*      */             } 
/*  716 */             this.markers.add(new Integer(219));
/*  717 */             List<QTable> l = new ArrayList(1);
/*      */             while (true)
/*  719 */             { QTable t = new QTable(iis);
/*  720 */               l.add(t);
/*  721 */               dataLength -= t.length;
/*  722 */               if (dataLength <= 0)
/*  723 */                 this.qtables.add(l);  } 
/*  724 */           }  if (code == 196) {
/*  725 */             if (!this.dhtPresent) {
/*  726 */               this.dhtPresent = true;
/*  727 */               this.htables = new ArrayList(1);
/*      */             } 
/*  729 */             this.markers.add(new Integer(196));
/*  730 */             List<HuffmanTable> l = new ArrayList(1);
/*      */             while (true)
/*  732 */             { HuffmanTable t = new HuffmanTable(iis);
/*  733 */               l.add(t);
/*  734 */               dataLength -= t.length;
/*  735 */               if (dataLength <= 0)
/*  736 */                 this.htables.add(l);  } 
/*  737 */           }  if (code == 221) {
/*  738 */             if (!this.driPresent) {
/*  739 */               this.driPresent = true;
/*      */             }
/*  741 */             this.markers.add(new Integer(221));
/*  742 */             this.driInterval = iis.readUnsignedShort(); continue;
/*  743 */           }  if (code == 254) {
/*  744 */             if (!this.comPresent) {
/*  745 */               this.comPresent = true;
/*  746 */               this.comments = new ArrayList(1);
/*      */             } 
/*  748 */             this.markers.add(new Integer(254));
/*  749 */             byte[] b = new byte[dataLength];
/*  750 */             iis.readFully(b);
/*  751 */             this.comments.add(b); continue;
/*  752 */           }  if ((code >= 192 && code <= 207) || code == 247) {
/*      */             
/*  754 */             if (!this.sofPresent) {
/*  755 */               this.sofPresent = true;
/*  756 */               this.sofProcess = code - 192;
/*  757 */               this.samplePrecision = iis.read();
/*  758 */               this.numLines = iis.readUnsignedShort();
/*  759 */               this.samplesPerLine = iis.readUnsignedShort();
/*  760 */               this.numFrameComponents = iis.read();
/*  761 */               this.componentId = new int[this.numFrameComponents];
/*  762 */               this.hSamplingFactor = new int[this.numFrameComponents];
/*  763 */               this.vSamplingFactor = new int[this.numFrameComponents];
/*  764 */               this.qtableSelector = new int[this.numFrameComponents];
/*  765 */               for (int i = 0; i < this.numFrameComponents; i++) {
/*  766 */                 this.componentId[i] = iis.read();
/*  767 */                 this.hSamplingFactor[i] = (int)iis.readBits(4);
/*  768 */                 this.vSamplingFactor[i] = (int)iis.readBits(4);
/*  769 */                 this.qtableSelector[i] = iis.read();
/*      */               } 
/*  771 */               this.markers.add(new Integer(49152));
/*      */             }  continue;
/*  773 */           }  if (code == 218) {
/*  774 */             if (!this.sosPresent) {
/*  775 */               this.sosPresent = true;
/*  776 */               this.numScanComponents = iis.read();
/*  777 */               this.componentSelector = new int[this.numScanComponents];
/*  778 */               this.dcHuffTable = new int[this.numScanComponents];
/*  779 */               this.acHuffTable = new int[this.numScanComponents];
/*  780 */               for (int i = 0; i < this.numScanComponents; i++) {
/*  781 */                 this.componentSelector[i] = iis.read();
/*  782 */                 this.dcHuffTable[i] = (int)iis.readBits(4);
/*  783 */                 this.acHuffTable[i] = (int)iis.readBits(4);
/*      */               } 
/*  785 */               this.startSpectralSelection = iis.read();
/*  786 */               this.endSpectralSelection = iis.read();
/*  787 */               this.approxHigh = (int)iis.readBits(4);
/*  788 */               this.approxLow = (int)iis.readBits(4);
/*  789 */               this.markers.add(new Integer(218));
/*      */             } 
/*      */             break;
/*      */           } 
/*  793 */           addUnknownMarkerSegment(iis, code, dataLength);
/*      */         } 
/*      */       } 
/*  796 */     } catch (EOFException eofe) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  802 */     if (this.app2ICCPresent && isICCProfileValid && this.profile == null) {
/*  803 */       int profileDataLength = 0;
/*  804 */       for (int i = 1; i <= numICCProfileChunks; i++) {
/*  805 */         if (iccProfileChunkOffsets[i] == 0L) {
/*  806 */           isICCProfileValid = false;
/*      */           break;
/*      */         } 
/*  809 */         profileDataLength += iccProfileChunkLengths[i];
/*      */       } 
/*      */       
/*  812 */       if (isICCProfileValid) {
/*  813 */         byte[] b = new byte[profileDataLength];
/*  814 */         int off = 0;
/*  815 */         for (int j = 1; j <= numICCProfileChunks; j++) {
/*  816 */           iis.seek(iccProfileChunkOffsets[j]);
/*  817 */           iis.read(b, off, iccProfileChunkLengths[j]);
/*  818 */           off += iccProfileChunkLengths[j];
/*      */         } 
/*      */         
/*  821 */         this.profile = ICC_Profile.getInstance(b);
/*      */       } 
/*      */     } 
/*      */     
/*  825 */     iis.reset();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void addUnknownMarkerSegment(ImageInputStream stream, int code, int len) throws IOException {
/*  831 */     if (!this.unknownPresent) {
/*  832 */       this.unknownPresent = true;
/*  833 */       this.markerTags = new ArrayList(1);
/*  834 */       this.unknownData = new ArrayList(1);
/*      */     } 
/*  836 */     this.markerTags.add(new Integer(code));
/*  837 */     byte[] b = new byte[len];
/*  838 */     stream.readFully(b);
/*  839 */     this.unknownData.add(b);
/*  840 */     this.markers.add(new Integer(65535));
/*      */   }
/*      */   
/*      */   public boolean isReadOnly() {
/*  844 */     return this.isReadOnly;
/*      */   }
/*      */   
/*      */   public Node getAsTree(String formatName) {
/*  848 */     if (formatName.equals(this.nativeMetadataFormatName))
/*  849 */       return getNativeTree(); 
/*  850 */     if (formatName
/*  851 */       .equals("javax_imageio_1.0"))
/*  852 */       return getStandardTree(); 
/*  853 */     if (formatName.equals("com_sun_media_imageio_plugins_tiff_image_1.0")) {
/*  854 */       return getTIFFTree();
/*      */     }
/*  856 */     throw new IllegalArgumentException("Not a recognized format!");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void mergeTree(String formatName, Node root) throws IIOInvalidTreeException {
/*  862 */     if (this.isReadOnly) {
/*  863 */       throw new IllegalStateException("isReadOnly() == true!");
/*      */     }
/*      */   }
/*      */   
/*      */   public void reset() {
/*  868 */     if (this.isReadOnly) {
/*  869 */       throw new IllegalStateException("isReadOnly() == true!");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Node getNativeTree() {
/*  876 */     int jfxxIndex = 0;
/*  877 */     int dqtIndex = 0;
/*  878 */     int dhtIndex = 0;
/*  879 */     int comIndex = 0;
/*  880 */     int unknownIndex = 0;
/*      */     
/*  882 */     IIOMetadataNode root = new IIOMetadataNode(this.nativeMetadataFormatName);
/*      */     
/*  884 */     IIOMetadataNode JPEGvariety = new IIOMetadataNode("JPEGvariety");
/*  885 */     root.appendChild(JPEGvariety);
/*      */     
/*  887 */     IIOMetadataNode markerSequence = new IIOMetadataNode("markerSequence");
/*  888 */     root.appendChild(markerSequence);
/*      */     
/*  890 */     IIOMetadataNode app0JFIF = null;
/*  891 */     if (this.app0JFIFPresent || this.app0JFXXPresent || this.app2ICCPresent) {
/*  892 */       app0JFIF = new IIOMetadataNode("app0JFIF");
/*  893 */       app0JFIF.setAttribute("majorVersion", 
/*  894 */           Integer.toString(this.majorVersion));
/*  895 */       app0JFIF.setAttribute("minorVersion", 
/*  896 */           Integer.toString(this.minorVersion));
/*  897 */       app0JFIF.setAttribute("resUnits", 
/*  898 */           Integer.toString(this.resUnits));
/*  899 */       app0JFIF.setAttribute("Xdensity", 
/*  900 */           Integer.toString(this.Xdensity));
/*  901 */       app0JFIF.setAttribute("Ydensity", 
/*  902 */           Integer.toString(this.Ydensity));
/*  903 */       app0JFIF.setAttribute("thumbWidth", 
/*  904 */           Integer.toString(this.thumbWidth));
/*  905 */       app0JFIF.setAttribute("thumbHeight", 
/*  906 */           Integer.toString(this.thumbHeight));
/*  907 */       JPEGvariety.appendChild(app0JFIF);
/*      */     } 
/*      */     
/*  910 */     IIOMetadataNode JFXX = null;
/*  911 */     if (this.app0JFXXPresent) {
/*  912 */       JFXX = new IIOMetadataNode("JFXX");
/*  913 */       app0JFIF.appendChild(JFXX);
/*      */     } 
/*      */     
/*  916 */     Iterator<Integer> markerIter = this.markers.iterator();
/*  917 */     while (markerIter.hasNext()) {
/*  918 */       IIOMetadataNode app0JFXX; Integer extensionCode; IIOMetadataNode JFIFthumb, app2ICC, dqt; List<QTable> list; List<HuffmanTable> tables; int numTables, j; IIOMetadataNode dht; int k; IIOMetadataNode dri, com, unknown; Integer markerTag; IIOMetadataNode app14Adobe, sof; int i; IIOMetadataNode sos; int m, marker = ((Integer)markerIter.next()).intValue();
/*  919 */       switch (marker) {
/*      */ 
/*      */ 
/*      */         
/*      */         case 57345:
/*  924 */           app0JFXX = new IIOMetadataNode("app0JFXX");
/*  925 */           extensionCode = this.extensionCodes.get(jfxxIndex);
/*  926 */           app0JFXX.setAttribute("extensionCode", extensionCode
/*  927 */               .toString());
/*  928 */           JFIFthumb = null;
/*  929 */           switch (extensionCode.intValue()) {
/*      */             case 16:
/*  931 */               JFIFthumb = new IIOMetadataNode("JFIFthumbJPEG");
/*      */               break;
/*      */             case 17:
/*  934 */               JFIFthumb = new IIOMetadataNode("JFIFthumbPalette");
/*      */               break;
/*      */             case 18:
/*  937 */               JFIFthumb = new IIOMetadataNode("JFIFthumbRGB");
/*      */               break;
/*      */           } 
/*      */ 
/*      */           
/*  942 */           if (JFIFthumb != null) {
/*  943 */             IIOImage img = this.jfxxThumbnails.get(jfxxIndex++);
/*  944 */             if (extensionCode.intValue() == 16) {
/*  945 */               IIOMetadata thumbMetadata = img.getMetadata();
/*  946 */               if (thumbMetadata != null) {
/*      */                 
/*  948 */                 Node thumbTree = thumbMetadata.getAsTree(this.nativeMetadataFormatName);
/*  949 */                 if (thumbTree instanceof IIOMetadataNode) {
/*  950 */                   IIOMetadataNode elt = (IIOMetadataNode)thumbTree;
/*      */ 
/*      */                   
/*  953 */                   NodeList elts = elt.getElementsByTagName("markerSequence");
/*  954 */                   if (elts.getLength() > 0) {
/*  955 */                     JFIFthumb.appendChild(elts.item(0));
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */             } else {
/*      */               
/*  961 */               BufferedImage thumb = (BufferedImage)img.getRenderedImage();
/*  962 */               JFIFthumb.setAttribute("thumbWidth", 
/*  963 */                   Integer.toString(thumb.getWidth()));
/*  964 */               JFIFthumb.setAttribute("thumbHeight", 
/*  965 */                   Integer.toString(thumb.getHeight()));
/*      */             } 
/*      */ 
/*      */             
/*  969 */             JFIFthumb.setUserObject(img);
/*  970 */             app0JFXX.appendChild(JFIFthumb);
/*      */           } 
/*  972 */           JFXX.appendChild(app0JFXX);
/*      */         
/*      */         case 57856:
/*  975 */           app2ICC = new IIOMetadataNode("app2ICC");
/*  976 */           app2ICC.setUserObject(this.profile);
/*  977 */           app0JFIF.appendChild(app2ICC);
/*      */         
/*      */         case 219:
/*  980 */           dqt = new IIOMetadataNode("dqt");
/*  981 */           list = this.qtables.get(dqtIndex++);
/*  982 */           numTables = list.size();
/*  983 */           for (j = 0; j < numTables; j++) {
/*  984 */             IIOMetadataNode dqtable = new IIOMetadataNode("dqtable");
/*  985 */             QTable t = list.get(j);
/*  986 */             dqtable.setAttribute("elementPrecision", 
/*  987 */                 Integer.toString(t.elementPrecision));
/*  988 */             dqtable.setAttribute("qtableId", 
/*  989 */                 Integer.toString(t.tableID));
/*  990 */             dqtable.setUserObject(t.table);
/*  991 */             dqt.appendChild(dqtable);
/*      */           } 
/*  993 */           markerSequence.appendChild(dqt);
/*      */         
/*      */         case 196:
/*  996 */           dht = new IIOMetadataNode("dht");
/*  997 */           tables = this.htables.get(dhtIndex++);
/*  998 */           numTables = tables.size();
/*  999 */           for (k = 0; k < numTables; k++) {
/* 1000 */             IIOMetadataNode dhtable = new IIOMetadataNode("dhtable");
/* 1001 */             HuffmanTable t = tables.get(k);
/* 1002 */             dhtable.setAttribute("class", 
/* 1003 */                 Integer.toString(t.tableClass));
/* 1004 */             dhtable.setAttribute("htableId", 
/* 1005 */                 Integer.toString(t.tableID));
/* 1006 */             dhtable.setUserObject(t.table);
/* 1007 */             dht.appendChild(dhtable);
/*      */           } 
/* 1009 */           markerSequence.appendChild(dht);
/*      */         
/*      */         case 221:
/* 1012 */           dri = new IIOMetadataNode("dri");
/* 1013 */           dri.setAttribute("interval", Integer.toString(this.driInterval));
/* 1014 */           markerSequence.appendChild(dri);
/*      */         
/*      */         case 254:
/* 1017 */           com = new IIOMetadataNode("com");
/* 1018 */           com.setUserObject(this.comments.get(comIndex++));
/* 1019 */           markerSequence.appendChild(com);
/*      */         
/*      */         case 65535:
/* 1022 */           unknown = new IIOMetadataNode("unknown");
/* 1023 */           markerTag = this.markerTags.get(unknownIndex);
/* 1024 */           unknown.setAttribute("MarkerTag", markerTag.toString());
/* 1025 */           unknown.setUserObject(this.unknownData.get(unknownIndex++));
/* 1026 */           markerSequence.appendChild(unknown);
/*      */         
/*      */         case 60928:
/* 1029 */           app14Adobe = new IIOMetadataNode("app14Adobe");
/* 1030 */           app14Adobe.setAttribute("version", Integer.toString(this.version));
/* 1031 */           app14Adobe.setAttribute("flags0", Integer.toString(this.flags0));
/* 1032 */           app14Adobe.setAttribute("flags1", Integer.toString(this.flags1));
/* 1033 */           app14Adobe.setAttribute("transform", 
/* 1034 */               Integer.toString(this.transform));
/* 1035 */           markerSequence.appendChild(app14Adobe);
/*      */         
/*      */         case 49152:
/* 1038 */           sof = new IIOMetadataNode("sof");
/* 1039 */           sof.setAttribute("process", Integer.toString(this.sofProcess));
/* 1040 */           sof.setAttribute("samplePrecision", 
/* 1041 */               Integer.toString(this.samplePrecision));
/* 1042 */           sof.setAttribute("numLines", Integer.toString(this.numLines));
/* 1043 */           sof.setAttribute("samplesPerLine", 
/* 1044 */               Integer.toString(this.samplesPerLine));
/* 1045 */           sof.setAttribute("numFrameComponents", 
/* 1046 */               Integer.toString(this.numFrameComponents));
/* 1047 */           for (i = 0; i < this.numFrameComponents; i++) {
/* 1048 */             IIOMetadataNode componentSpec = new IIOMetadataNode("componentSpec");
/*      */             
/* 1050 */             componentSpec.setAttribute("componentId", 
/* 1051 */                 Integer.toString(this.componentId[i]));
/* 1052 */             componentSpec.setAttribute("HsamplingFactor", 
/* 1053 */                 Integer.toString(this.hSamplingFactor[i]));
/* 1054 */             componentSpec.setAttribute("VsamplingFactor", 
/* 1055 */                 Integer.toString(this.vSamplingFactor[i]));
/* 1056 */             componentSpec.setAttribute("QtableSelector", 
/* 1057 */                 Integer.toString(this.qtableSelector[i]));
/* 1058 */             sof.appendChild(componentSpec);
/*      */           } 
/* 1060 */           markerSequence.appendChild(sof);
/*      */         
/*      */         case 218:
/* 1063 */           sos = new IIOMetadataNode("sos");
/* 1064 */           sos.setAttribute("numScanComponents", 
/* 1065 */               Integer.toString(this.numScanComponents));
/* 1066 */           sos.setAttribute("startSpectralSelection", 
/* 1067 */               Integer.toString(this.startSpectralSelection));
/* 1068 */           sos.setAttribute("endSpectralSelection", 
/* 1069 */               Integer.toString(this.endSpectralSelection));
/* 1070 */           sos.setAttribute("approxHigh", Integer.toString(this.approxHigh));
/* 1071 */           sos.setAttribute("approxLow", Integer.toString(this.approxLow));
/* 1072 */           for (m = 0; m < this.numScanComponents; m++) {
/* 1073 */             IIOMetadataNode scanComponentSpec = new IIOMetadataNode("scanComponentSpec");
/*      */             
/* 1075 */             scanComponentSpec.setAttribute("componentSelector", 
/* 1076 */                 Integer.toString(this.componentSelector[m]));
/* 1077 */             scanComponentSpec.setAttribute("dcHuffTable", 
/* 1078 */                 Integer.toString(this.dcHuffTable[m]));
/* 1079 */             scanComponentSpec.setAttribute("acHuffTable", 
/* 1080 */                 Integer.toString(this.acHuffTable[m]));
/* 1081 */             sos.appendChild(scanComponentSpec);
/*      */           } 
/* 1083 */           markerSequence.appendChild(sos);
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/* 1088 */     return root;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected IIOMetadataNode getStandardChromaNode() {
/* 1094 */     if (!this.sofPresent)
/*      */     {
/* 1096 */       return null;
/*      */     }
/*      */     
/* 1099 */     IIOMetadataNode chroma = new IIOMetadataNode("Chroma");
/* 1100 */     IIOMetadataNode csType = new IIOMetadataNode("ColorSpaceType");
/* 1101 */     chroma.appendChild(csType);
/*      */     
/* 1103 */     IIOMetadataNode numChanNode = new IIOMetadataNode("NumChannels");
/* 1104 */     chroma.appendChild(numChanNode);
/* 1105 */     numChanNode.setAttribute("value", 
/* 1106 */         Integer.toString(this.numFrameComponents));
/*      */ 
/*      */     
/* 1109 */     if (this.app0JFIFPresent) {
/* 1110 */       if (this.numFrameComponents == 1) {
/* 1111 */         csType.setAttribute("name", "GRAY");
/*      */       } else {
/* 1113 */         csType.setAttribute("name", "YCbCr");
/*      */       } 
/* 1115 */       return chroma;
/*      */     } 
/*      */ 
/*      */     
/* 1119 */     if (this.app14AdobePresent) {
/* 1120 */       switch (this.transform) {
/*      */         case 2:
/* 1122 */           csType.setAttribute("name", "YCCK");
/*      */           break;
/*      */         case 1:
/* 1125 */           csType.setAttribute("name", "YCbCr");
/*      */           break;
/*      */         case 0:
/* 1128 */           if (this.numFrameComponents == 3) {
/* 1129 */             csType.setAttribute("name", "RGB"); break;
/* 1130 */           }  if (this.numFrameComponents == 4) {
/* 1131 */             csType.setAttribute("name", "CMYK");
/*      */           }
/*      */           break;
/*      */       } 
/* 1135 */       return chroma;
/*      */     } 
/*      */ 
/*      */     
/* 1139 */     this.hasAlpha = false;
/*      */ 
/*      */     
/* 1142 */     if (this.numFrameComponents < 3) {
/* 1143 */       csType.setAttribute("name", "GRAY");
/* 1144 */       if (this.numFrameComponents == 2) {
/* 1145 */         this.hasAlpha = true;
/*      */       }
/* 1147 */       return chroma;
/*      */     } 
/*      */     
/* 1150 */     boolean idsAreJFIF = true;
/*      */     
/* 1152 */     for (int i = 0; i < this.componentId.length; i++) {
/* 1153 */       int id = this.componentId[i];
/* 1154 */       if (id < 1 || id >= this.componentId.length) {
/* 1155 */         idsAreJFIF = false;
/*      */       }
/*      */     } 
/*      */     
/* 1159 */     if (idsAreJFIF) {
/* 1160 */       csType.setAttribute("name", "YCbCr");
/* 1161 */       if (this.numFrameComponents == 4) {
/* 1162 */         this.hasAlpha = true;
/*      */       }
/* 1164 */       return chroma;
/*      */     } 
/*      */ 
/*      */     
/* 1168 */     if (this.componentId[0] == 82 && this.componentId[1] == 71 && this.componentId[2] == 66) {
/*      */ 
/*      */       
/* 1171 */       csType.setAttribute("name", "RGB");
/* 1172 */       if (this.numFrameComponents == 4 && this.componentId[3] == 65) {
/* 1173 */         this.hasAlpha = true;
/*      */       }
/* 1175 */       return chroma;
/*      */     } 
/*      */     
/* 1178 */     if (this.componentId[0] == 89 && this.componentId[1] == 67 && this.componentId[2] == 99) {
/*      */ 
/*      */       
/* 1181 */       csType.setAttribute("name", "PhotoYCC");
/* 1182 */       if (this.numFrameComponents == 4 && this.componentId[3] == 65)
/*      */       {
/* 1184 */         this.hasAlpha = true;
/*      */       }
/* 1186 */       return chroma;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1192 */     boolean subsampled = false;
/*      */     
/* 1194 */     int hfactor = this.hSamplingFactor[0];
/* 1195 */     int vfactor = this.vSamplingFactor[0];
/*      */     
/* 1197 */     for (int j = 1; j < this.componentId.length; j++) {
/* 1198 */       if (this.hSamplingFactor[j] != hfactor || this.vSamplingFactor[j] != vfactor) {
/*      */         
/* 1200 */         subsampled = true;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/* 1205 */     if (subsampled) {
/* 1206 */       csType.setAttribute("name", "YCbCr");
/* 1207 */       if (this.numFrameComponents == 4) {
/* 1208 */         this.hasAlpha = true;
/*      */       }
/* 1210 */       return chroma;
/*      */     } 
/*      */ 
/*      */     
/* 1214 */     if (this.numFrameComponents == 3) {
/* 1215 */       csType.setAttribute("name", "RGB");
/*      */     } else {
/* 1217 */       csType.setAttribute("name", "CMYK");
/*      */     } 
/*      */     
/* 1220 */     return chroma;
/*      */   }
/*      */   
/*      */   protected IIOMetadataNode getStandardCompressionNode() {
/* 1224 */     IIOMetadataNode compression = null;
/*      */     
/* 1226 */     if (this.sofPresent || this.sosPresent) {
/* 1227 */       compression = new IIOMetadataNode("Compression");
/*      */       
/* 1229 */       if (this.sofPresent) {
/*      */         
/* 1231 */         boolean isLossless = (this.sofProcess == 3 || this.sofProcess == 7 || this.sofProcess == 11 || this.sofProcess == 15 || this.sofProcess == 55);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1236 */         IIOMetadataNode name = new IIOMetadataNode("CompressionTypeName");
/*      */         
/* 1238 */         String compressionType = isLossless ? ((this.sofProcess == 55) ? "JPEG-LS" : "JPEG-LOSSLESS") : "JPEG";
/*      */         
/* 1240 */         name.setAttribute("value", compressionType);
/* 1241 */         compression.appendChild(name);
/*      */ 
/*      */         
/* 1244 */         IIOMetadataNode lossless = new IIOMetadataNode("Lossless");
/* 1245 */         lossless.setAttribute("value", isLossless ? "true" : "false");
/* 1246 */         compression.appendChild(lossless);
/*      */       } 
/*      */       
/* 1249 */       if (this.sosPresent) {
/* 1250 */         IIOMetadataNode prog = new IIOMetadataNode("NumProgressiveScans");
/*      */         
/* 1252 */         prog.setAttribute("value", "1");
/* 1253 */         compression.appendChild(prog);
/*      */       } 
/*      */     } 
/*      */     
/* 1257 */     return compression;
/*      */   }
/*      */   
/*      */   protected IIOMetadataNode getStandardDimensionNode() {
/* 1261 */     IIOMetadataNode dim = new IIOMetadataNode("Dimension");
/* 1262 */     IIOMetadataNode orient = new IIOMetadataNode("ImageOrientation");
/* 1263 */     orient.setAttribute("value", "normal");
/* 1264 */     dim.appendChild(orient);
/*      */     
/* 1266 */     if (this.app0JFIFPresent) {
/*      */       float aspectRatio;
/* 1268 */       if (this.resUnits == 0) {
/*      */         
/* 1270 */         aspectRatio = this.Xdensity / this.Ydensity;
/*      */       } else {
/*      */         
/* 1273 */         aspectRatio = this.Ydensity / this.Xdensity;
/*      */       } 
/* 1275 */       IIOMetadataNode aspect = new IIOMetadataNode("PixelAspectRatio");
/* 1276 */       aspect.setAttribute("value", Float.toString(aspectRatio));
/* 1277 */       dim.insertBefore(aspect, orient);
/*      */       
/* 1279 */       if (this.resUnits != 0) {
/*      */         
/* 1281 */         float scale = (this.resUnits == 1) ? 25.4F : 10.0F;
/*      */         
/* 1283 */         IIOMetadataNode horiz = new IIOMetadataNode("HorizontalPixelSize");
/*      */         
/* 1285 */         horiz.setAttribute("value", 
/* 1286 */             Float.toString(scale / this.Xdensity));
/* 1287 */         dim.appendChild(horiz);
/*      */         
/* 1289 */         IIOMetadataNode vert = new IIOMetadataNode("VerticalPixelSize");
/*      */         
/* 1291 */         vert.setAttribute("value", 
/* 1292 */             Float.toString(scale / this.Ydensity));
/* 1293 */         dim.appendChild(vert);
/*      */       } 
/*      */     } 
/* 1296 */     return dim;
/*      */   }
/*      */   
/*      */   protected IIOMetadataNode getStandardTextNode() {
/* 1300 */     IIOMetadataNode text = null;
/* 1301 */     if (this.comPresent) {
/* 1302 */       text = new IIOMetadataNode("Text");
/* 1303 */       Iterator<byte[]> iter = this.comments.iterator();
/* 1304 */       while (iter.hasNext()) {
/* 1305 */         IIOMetadataNode entry = new IIOMetadataNode("TextEntry");
/* 1306 */         entry.setAttribute("keyword", "comment");
/* 1307 */         byte[] data = iter.next();
/*      */         try {
/* 1309 */           entry.setAttribute("value", new String(data, "ISO-8859-1"));
/*      */         }
/* 1311 */         catch (UnsupportedEncodingException e) {
/* 1312 */           entry.setAttribute("value", new String(data));
/*      */         } 
/* 1314 */         text.appendChild(entry);
/*      */       } 
/*      */     } 
/* 1317 */     return text;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected IIOMetadataNode getStandardTransparencyNode() {
/* 1323 */     IIOMetadataNode trans = null;
/* 1324 */     if (this.hasAlpha == true) {
/* 1325 */       trans = new IIOMetadataNode("Transparency");
/* 1326 */       IIOMetadataNode alpha = new IIOMetadataNode("Alpha");
/* 1327 */       alpha.setAttribute("value", "nonpremultiplied");
/* 1328 */       trans.appendChild(alpha);
/*      */     } 
/* 1330 */     return trans;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private Node getTIFFTree() {
/* 1336 */     String metadataName = "com_sun_media_imageio_plugins_tiff_image_1.0";
/*      */     
/* 1338 */     BaselineTIFFTagSet base = BaselineTIFFTagSet.getInstance();
/*      */ 
/*      */ 
/*      */     
/* 1342 */     TIFFDirectory dir = new TIFFDirectory(new TIFFTagSet[] { (TIFFTagSet)base, (TIFFTagSet)EXIFParentTIFFTagSet.getInstance() }, null);
/*      */ 
/*      */     
/* 1345 */     if (this.sofPresent) {
/*      */       
/* 1347 */       int compression = 7;
/*      */       
/* 1349 */       TIFFField compressionField = new TIFFField(base.getTag(259), compression);
/*      */       
/* 1351 */       dir.addTIFFField(compressionField);
/*      */ 
/*      */       
/* 1354 */       char[] bitsPerSample = new char[this.numFrameComponents];
/* 1355 */       Arrays.fill(bitsPerSample, (char)(this.samplePrecision & 0xFF));
/*      */ 
/*      */       
/* 1358 */       TIFFField bitsPerSampleField = new TIFFField(base.getTag(258), 3, bitsPerSample.length, bitsPerSample);
/*      */ 
/*      */ 
/*      */       
/* 1362 */       dir.addTIFFField(bitsPerSampleField);
/*      */ 
/*      */ 
/*      */       
/* 1366 */       TIFFField imageLengthField = new TIFFField(base.getTag(257), this.numLines);
/*      */       
/* 1368 */       dir.addTIFFField(imageLengthField);
/*      */ 
/*      */ 
/*      */       
/* 1372 */       TIFFField imageWidthField = new TIFFField(base.getTag(256), this.samplesPerLine);
/*      */       
/* 1374 */       dir.addTIFFField(imageWidthField);
/*      */ 
/*      */ 
/*      */       
/* 1378 */       TIFFField samplesPerPixelField = new TIFFField(base.getTag(277), this.numFrameComponents);
/*      */       
/* 1380 */       dir.addTIFFField(samplesPerPixelField);
/*      */ 
/*      */       
/* 1383 */       IIOMetadataNode chroma = getStandardChromaNode();
/* 1384 */       if (chroma != null) {
/*      */         
/* 1386 */         IIOMetadataNode csType = (IIOMetadataNode)chroma.getElementsByTagName("ColorSpaceType").item(0);
/* 1387 */         String name = csType.getAttribute("name");
/* 1388 */         int photometricInterpretation = -1;
/* 1389 */         if (name.equals("GRAY")) {
/* 1390 */           photometricInterpretation = 1;
/*      */         }
/* 1392 */         else if (name.equals("YCbCr") || name.equals("PhotoYCC")) {
/*      */           
/* 1394 */           photometricInterpretation = 6;
/*      */         }
/* 1396 */         else if (name.equals("RGB")) {
/* 1397 */           photometricInterpretation = 2;
/*      */         }
/* 1399 */         else if (name.equals("CMYK") || name.equals("YCCK")) {
/*      */           
/* 1401 */           photometricInterpretation = 5;
/*      */         } 
/*      */ 
/*      */         
/* 1405 */         if (photometricInterpretation != -1) {
/*      */           
/* 1407 */           TIFFField photometricInterpretationField = new TIFFField(base.getTag(262), photometricInterpretation);
/*      */           
/* 1409 */           dir.addTIFFField(photometricInterpretationField);
/*      */         } 
/*      */         
/* 1412 */         if (this.hasAlpha) {
/* 1413 */           char[] extraSamples = { '\001' };
/*      */ 
/*      */ 
/*      */           
/* 1417 */           TIFFField extraSamplesField = new TIFFField(base.getTag(338), 3, extraSamples.length, extraSamples);
/*      */ 
/*      */ 
/*      */           
/* 1421 */           dir.addTIFFField(extraSamplesField);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1427 */     if (this.app0JFIFPresent) {
/* 1428 */       long[][] xResolution = { { this.Xdensity, 1L } };
/*      */       
/* 1430 */       TIFFField XResolutionField = new TIFFField(base.getTag(282), 5, 1, xResolution);
/*      */ 
/*      */ 
/*      */       
/* 1434 */       dir.addTIFFField(XResolutionField);
/*      */       
/* 1436 */       long[][] yResolution = { { this.Ydensity, 1L } };
/*      */       
/* 1438 */       TIFFField YResolutionField = new TIFFField(base.getTag(283), 5, 1, yResolution);
/*      */ 
/*      */ 
/*      */       
/* 1442 */       dir.addTIFFField(YResolutionField);
/*      */       
/* 1444 */       int resolutionUnit = 1;
/* 1445 */       switch (this.resUnits) {
/*      */         case 0:
/* 1447 */           resolutionUnit = 1;
/*      */         case 1:
/* 1449 */           resolutionUnit = 2;
/*      */           break;
/*      */         case 2:
/* 1452 */           resolutionUnit = 3;
/*      */           break;
/*      */       } 
/*      */ 
/*      */       
/* 1457 */       TIFFField ResolutionUnitField = new TIFFField(base.getTag(296), resolutionUnit);
/*      */       
/* 1459 */       dir.addTIFFField(ResolutionUnitField);
/*      */     } 
/*      */ 
/*      */     
/* 1463 */     byte[] jpegTablesData = null;
/* 1464 */     if (this.dqtPresent || this.dqtPresent) {
/*      */       
/* 1466 */       int jpegTablesLength = 2;
/* 1467 */       if (this.dqtPresent) {
/* 1468 */         Iterator<List> dqts = this.qtables.iterator();
/* 1469 */         while (dqts.hasNext()) {
/* 1470 */           Iterator<QTable> qtiter = ((List)dqts.next()).iterator();
/* 1471 */           while (qtiter.hasNext()) {
/* 1472 */             QTable qt = qtiter.next();
/* 1473 */             jpegTablesLength += 4 + qt.length;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1477 */       if (this.dhtPresent) {
/* 1478 */         Iterator<List> dhts = this.htables.iterator();
/* 1479 */         while (dhts.hasNext()) {
/* 1480 */           Iterator<HuffmanTable> htiter = ((List)dhts.next()).iterator();
/* 1481 */           while (htiter.hasNext()) {
/* 1482 */             HuffmanTable ht = htiter.next();
/* 1483 */             jpegTablesLength += 4 + ht.length;
/*      */           } 
/*      */         } 
/*      */       } 
/* 1487 */       jpegTablesLength += 2;
/*      */ 
/*      */       
/* 1490 */       jpegTablesData = new byte[jpegTablesLength];
/*      */ 
/*      */       
/* 1493 */       jpegTablesData[0] = -1;
/* 1494 */       jpegTablesData[1] = -40;
/* 1495 */       int jpoff = 2;
/*      */       
/* 1497 */       if (this.dqtPresent) {
/* 1498 */         Iterator<List> dqts = this.qtables.iterator();
/* 1499 */         while (dqts.hasNext()) {
/* 1500 */           Iterator<QTable> qtiter = ((List)dqts.next()).iterator();
/* 1501 */           while (qtiter.hasNext()) {
/* 1502 */             jpegTablesData[jpoff++] = -1;
/* 1503 */             jpegTablesData[jpoff++] = -37;
/* 1504 */             QTable qt = qtiter.next();
/* 1505 */             int qtlength = qt.length + 2;
/* 1506 */             jpegTablesData[jpoff++] = (byte)((qtlength & 0xFF00) >> 8);
/*      */             
/* 1508 */             jpegTablesData[jpoff++] = (byte)(qtlength & 0xFF);
/* 1509 */             jpegTablesData[jpoff++] = (byte)((qt.elementPrecision & 0xF0) << 4 | qt.tableID & 0xF);
/*      */ 
/*      */             
/* 1512 */             int[] table = qt.table.getTable();
/* 1513 */             int qlen = table.length;
/* 1514 */             for (int i = 0; i < qlen; i++) {
/* 1515 */               jpegTablesData[jpoff + zigzag[i]] = (byte)table[i];
/*      */             }
/* 1517 */             jpoff += qlen;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1522 */       if (this.dhtPresent) {
/* 1523 */         Iterator<List> dhts = this.htables.iterator();
/* 1524 */         while (dhts.hasNext()) {
/* 1525 */           Iterator<HuffmanTable> htiter = ((List)dhts.next()).iterator();
/* 1526 */           while (htiter.hasNext()) {
/* 1527 */             jpegTablesData[jpoff++] = -1;
/* 1528 */             jpegTablesData[jpoff++] = -60;
/* 1529 */             HuffmanTable ht = htiter.next();
/* 1530 */             int htlength = ht.length + 2;
/* 1531 */             jpegTablesData[jpoff++] = (byte)((htlength & 0xFF00) >> 8);
/*      */             
/* 1533 */             jpegTablesData[jpoff++] = (byte)(htlength & 0xFF);
/* 1534 */             jpegTablesData[jpoff++] = (byte)((ht.tableClass & 0xF) << 4 | ht.tableID & 0xF);
/*      */ 
/*      */             
/* 1537 */             short[] lengths = ht.table.getLengths();
/* 1538 */             int numLengths = lengths.length;
/* 1539 */             for (int i = 0; i < numLengths; i++) {
/* 1540 */               jpegTablesData[jpoff++] = (byte)lengths[i];
/*      */             }
/* 1542 */             short[] values = ht.table.getValues();
/* 1543 */             int numValues = values.length;
/* 1544 */             for (int j = 0; j < numValues; j++) {
/* 1545 */               jpegTablesData[jpoff++] = (byte)values[j];
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1551 */       jpegTablesData[jpoff++] = -1;
/* 1552 */       jpegTablesData[jpoff] = -39;
/*      */     } 
/* 1554 */     if (jpegTablesData != null) {
/*      */       
/* 1556 */       TIFFField JPEGTablesField = new TIFFField(base.getTag(347), 7, jpegTablesData.length, jpegTablesData);
/*      */ 
/*      */ 
/*      */       
/* 1560 */       dir.addTIFFField(JPEGTablesField);
/*      */     } 
/*      */     
/* 1563 */     IIOMetadata tiffMetadata = dir.getAsMetadata();
/*      */     
/* 1565 */     if (this.exifData != null) {
/*      */       
/*      */       try {
/* 1568 */         Iterator<ImageReader> tiffReaders = ImageIO.getImageReadersByFormatName("TIFF");
/* 1569 */         if (tiffReaders != null && tiffReaders.hasNext()) {
/* 1570 */           ImageReader tiffReader = tiffReaders.next();
/* 1571 */           ByteArrayInputStream bais = new ByteArrayInputStream(this.exifData);
/*      */           
/* 1573 */           ImageInputStream exifStream = new MemoryCacheImageInputStream(bais);
/*      */           
/* 1575 */           tiffReader.setInput(exifStream);
/* 1576 */           IIOMetadata exifMetadata = tiffReader.getImageMetadata(0);
/* 1577 */           tiffMetadata.mergeTree(metadataName, exifMetadata
/* 1578 */               .getAsTree(metadataName));
/* 1579 */           tiffReader.reset();
/*      */         } 
/* 1581 */       } catch (IOException iOException) {}
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1586 */     return tiffMetadata.getAsTree(metadataName);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void initializeThumbnails() {
/* 1592 */     synchronized (this.thumbnails) {
/* 1593 */       if (!this.thumbnailsInitialized) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1598 */         if (this.app0JFIFPresent && this.jfifThumbnail != null) {
/* 1599 */           this.thumbnails.add(this.jfifThumbnail);
/*      */         }
/*      */ 
/*      */         
/* 1603 */         if (this.app0JFXXPresent && this.jfxxThumbnails != null) {
/* 1604 */           int numJFXX = this.jfxxThumbnails.size();
/* 1605 */           for (int i = 0; i < numJFXX; i++) {
/* 1606 */             IIOImage img = this.jfxxThumbnails.get(i);
/*      */             
/* 1608 */             BufferedImage jfxxThumbnail = (BufferedImage)img.getRenderedImage();
/* 1609 */             this.thumbnails.add(jfxxThumbnail);
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/* 1614 */         if (this.exifData != null) {
/*      */           
/*      */           try {
/* 1617 */             Iterator<ImageReader> tiffReaders = ImageIO.getImageReadersByFormatName("TIFF");
/* 1618 */             if (tiffReaders != null && tiffReaders.hasNext()) {
/*      */               
/* 1620 */               ImageReader tiffReader = tiffReaders.next();
/* 1621 */               ByteArrayInputStream bais = new ByteArrayInputStream(this.exifData);
/*      */               
/* 1623 */               ImageInputStream exifStream = new MemoryCacheImageInputStream(bais);
/*      */               
/* 1625 */               tiffReader.setInput(exifStream);
/* 1626 */               if (tiffReader.getNumImages(true) > 1) {
/*      */                 
/* 1628 */                 BufferedImage exifThumbnail = tiffReader.read(1, null);
/* 1629 */                 this.thumbnails.add(exifThumbnail);
/*      */               } 
/* 1631 */               tiffReader.reset();
/*      */             } 
/* 1633 */           } catch (IOException iOException) {}
/*      */         }
/*      */ 
/*      */ 
/*      */         
/* 1638 */         this.thumbnailsInitialized = true;
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   int getNumThumbnails() throws IOException {
/* 1644 */     initializeThumbnails();
/* 1645 */     return this.thumbnails.size();
/*      */   }
/*      */   
/*      */   BufferedImage getThumbnail(int thumbnailIndex) throws IOException {
/* 1649 */     if (thumbnailIndex < 0) {
/* 1650 */       throw new IndexOutOfBoundsException("thumbnailIndex < 0!");
/*      */     }
/*      */     
/* 1653 */     initializeThumbnails();
/*      */     
/* 1655 */     if (thumbnailIndex >= this.thumbnails.size()) {
/* 1656 */       throw new IndexOutOfBoundsException("thumbnailIndex > getNumThumbnails()");
/*      */     }
/*      */ 
/*      */     
/* 1660 */     return this.thumbnails.get(thumbnailIndex);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/jpeg/CLibJPEGMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */