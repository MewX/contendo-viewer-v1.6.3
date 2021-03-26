/*      */ package org.apache.xmlgraphics.image.codec.tiff;
/*      */ 
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.image.ColorModel;
/*      */ import java.awt.image.ComponentSampleModel;
/*      */ import java.awt.image.DataBufferByte;
/*      */ import java.awt.image.MultiPixelPackedSampleModel;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.RenderedImage;
/*      */ import java.awt.image.SampleModel;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStream;
/*      */ import java.io.RandomAccessFile;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.SortedSet;
/*      */ import java.util.TreeSet;
/*      */ import java.util.zip.Deflater;
/*      */ import org.apache.xmlgraphics.image.codec.util.ImageEncodeParam;
/*      */ import org.apache.xmlgraphics.image.codec.util.ImageEncoderImpl;
/*      */ import org.apache.xmlgraphics.image.codec.util.PropertyUtil;
/*      */ import org.apache.xmlgraphics.image.codec.util.SeekableOutputStream;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TIFFImageEncoder
/*      */   extends ImageEncoderImpl
/*      */ {
/*      */   private static final int TIFF_JPEG_TABLES = 347;
/*      */   private static final int TIFF_YCBCR_SUBSAMPLING = 530;
/*      */   private static final int TIFF_YCBCR_POSITIONING = 531;
/*      */   private static final int TIFF_REF_BLACK_WHITE = 532;
/*      */   
/*      */   public TIFFImageEncoder(OutputStream output, ImageEncodeParam param) {
/*   73 */     super(output, param);
/*   74 */     if (this.param == null) {
/*   75 */       this.param = new TIFFEncodeParam();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void encode(RenderedImage im) throws IOException {
/*   85 */     writeFileHeader();
/*      */ 
/*      */     
/*   88 */     TIFFEncodeParam encodeParam = (TIFFEncodeParam)this.param;
/*      */     
/*   90 */     Iterator iter = encodeParam.getExtraImages();
/*   91 */     if (iter != null) {
/*   92 */       boolean hasNext; int ifdOffset = 8;
/*   93 */       RenderedImage nextImage = im;
/*   94 */       TIFFEncodeParam nextParam = encodeParam;
/*      */       
/*      */       do {
/*   97 */         hasNext = iter.hasNext();
/*   98 */         ifdOffset = encode(nextImage, nextParam, ifdOffset, !hasNext);
/*   99 */         if (!hasNext)
/*  100 */           continue;  Object obj = iter.next();
/*  101 */         if (obj instanceof RenderedImage) {
/*  102 */           nextImage = (RenderedImage)obj;
/*  103 */           nextParam = encodeParam;
/*  104 */         } else if (obj instanceof Object[]) {
/*  105 */           Object[] o = (Object[])obj;
/*  106 */           nextImage = (RenderedImage)o[0];
/*  107 */           nextParam = (TIFFEncodeParam)o[1];
/*      */         }
/*      */       
/*  110 */       } while (hasNext);
/*      */     } else {
/*  112 */       encode(im, encodeParam, 8, true);
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
/*      */   public Object encodeMultiple(Object context, RenderedImage img) throws IOException {
/*  130 */     TIFFEncodeParam encodeParam = (TIFFEncodeParam)this.param;
/*  131 */     if (encodeParam.getExtraImages() != null) {
/*  132 */       throw new IllegalStateException(PropertyUtil.getString("TIFFImageEncoder11"));
/*      */     }
/*      */     
/*  135 */     Context c = (Context)context;
/*  136 */     if (c == null) {
/*  137 */       c = new Context();
/*      */       
/*  139 */       writeFileHeader();
/*      */     } else {
/*      */       
/*  142 */       c.ifdOffset = encode(c.nextImage, encodeParam, c.ifdOffset, false);
/*      */     } 
/*  144 */     c.nextImage = img;
/*  145 */     return c;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void finishMultiple(Object context) throws IOException {
/*  155 */     if (context == null) {
/*  156 */       throw new NullPointerException();
/*      */     }
/*  158 */     Context c = (Context)context;
/*      */     
/*  160 */     TIFFEncodeParam encodeParam = (TIFFEncodeParam)this.param;
/*      */ 
/*      */     
/*  163 */     c.ifdOffset = encode(c.nextImage, encodeParam, c.ifdOffset, true);
/*      */   }
/*      */   
/*      */   private static class Context { private RenderedImage nextImage;
/*      */     
/*      */     private Context() {}
/*      */     
/*  170 */     private int ifdOffset = 8; }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int encode(RenderedImage im, TIFFEncodeParam encodeParam, int ifdOffset, boolean isLast) throws IOException {
/*  176 */     CompressionValue compression = encodeParam.getCompression();
/*      */     
/*  178 */     if (compression == CompressionValue.JPEG_TTN2) {
/*  179 */       throw new IllegalArgumentException(PropertyUtil.getString("TIFFImageEncoder12"));
/*      */     }
/*      */ 
/*      */     
/*  183 */     boolean isTiled = encodeParam.getWriteTiled();
/*      */ 
/*      */     
/*  186 */     int minX = im.getMinX();
/*  187 */     int minY = im.getMinY();
/*  188 */     int width = im.getWidth();
/*  189 */     int height = im.getHeight();
/*      */ 
/*      */     
/*  192 */     SampleModel sampleModel = im.getSampleModel();
/*  193 */     ColorModel colorModel = im.getColorModel();
/*  194 */     int[] sampleSize = sampleModel.getSampleSize();
/*  195 */     int dataTypeSize = sampleSize[0];
/*  196 */     int numBands = sampleModel.getNumBands();
/*  197 */     int dataType = sampleModel.getDataType();
/*  198 */     validateImage(dataTypeSize, sampleSize, numBands, dataType, colorModel);
/*      */     
/*  200 */     boolean dataTypeIsShort = (dataType == 2 || dataType == 1);
/*      */ 
/*      */ 
/*      */     
/*  204 */     ImageInfo imageInfo = ImageInfo.newInstance(im, dataTypeSize, numBands, colorModel, encodeParam);
/*      */ 
/*      */     
/*  207 */     if (imageInfo.getType() == ImageType.UNSUPPORTED) {
/*  208 */       throw new RuntimeException(PropertyUtil.getString("TIFFImageEncoder8"));
/*      */     }
/*      */     
/*  211 */     int numTiles = imageInfo.getNumTiles();
/*  212 */     long bytesPerTile = imageInfo.getBytesPerTile();
/*  213 */     long bytesPerRow = imageInfo.getBytesPerRow();
/*  214 */     int tileHeight = imageInfo.getTileHeight();
/*  215 */     int tileWidth = imageInfo.getTileWidth();
/*      */     
/*  217 */     long[] tileByteCounts = new long[numTiles];
/*  218 */     for (int i = 0; i < numTiles; i++) {
/*  219 */       tileByteCounts[i] = bytesPerTile;
/*      */     }
/*      */     
/*  222 */     if (!isTiled) {
/*      */       
/*  224 */       long lastStripRows = (height - tileHeight * (numTiles - 1));
/*  225 */       tileByteCounts[numTiles - 1] = lastStripRows * bytesPerRow;
/*      */     } 
/*  227 */     long totalBytesOfData = bytesPerTile * (numTiles - 1) + tileByteCounts[numTiles - 1];
/*  228 */     long[] tileOffsets = new long[numTiles];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  244 */     SortedSet<TIFFField> fields = new TreeSet<TIFFField>();
/*      */ 
/*      */     
/*  247 */     fields.add(new TIFFField(256, 4, 1, new long[] { width }));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  252 */     fields.add(new TIFFField(257, 4, 1, new long[] { height }));
/*      */ 
/*      */ 
/*      */     
/*  256 */     char[] shortSampleSize = new char[numBands];
/*  257 */     for (int j = 0; j < numBands; j++) {
/*  258 */       shortSampleSize[j] = (char)dataTypeSize;
/*      */     }
/*  260 */     fields.add(new TIFFField(258, 3, numBands, shortSampleSize));
/*      */ 
/*      */ 
/*      */     
/*  264 */     fields.add(new TIFFField(259, 3, 1, new char[] { (char)compression.getValue() }));
/*      */ 
/*      */ 
/*      */     
/*  268 */     fields.add(new TIFFField(262, 3, 1, new char[] { (char)imageInfo.getType().getPhotometricInterpretation() }));
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  273 */     if (!isTiled) {
/*  274 */       fields.add(new TIFFField(273, 4, numTiles, tileOffsets));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  279 */     fields.add(new TIFFField(277, 3, 1, new char[] { (char)numBands }));
/*      */ 
/*      */ 
/*      */     
/*  283 */     if (!isTiled) {
/*  284 */       fields.add(new TIFFField(278, 4, 1, new long[] { tileHeight }));
/*      */ 
/*      */ 
/*      */       
/*  288 */       fields.add(new TIFFField(279, 4, numTiles, tileByteCounts));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  293 */     if (imageInfo.getColormap() != null) {
/*  294 */       fields.add(new TIFFField(320, 3, imageInfo.getColormapSize(), imageInfo.getColormap()));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  299 */     if (isTiled) {
/*  300 */       fields.add(new TIFFField(322, 4, 1, new long[] { tileWidth }));
/*      */ 
/*      */ 
/*      */       
/*  304 */       fields.add(new TIFFField(323, 4, 1, new long[] { tileHeight }));
/*      */ 
/*      */ 
/*      */       
/*  308 */       fields.add(new TIFFField(324, 4, numTiles, tileOffsets));
/*      */ 
/*      */ 
/*      */       
/*  312 */       fields.add(new TIFFField(325, 4, numTiles, tileByteCounts));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  317 */     if (imageInfo.getNumberOfExtraSamples() > 0) {
/*  318 */       char[] extraSamples = new char[imageInfo.getNumberOfExtraSamples()];
/*  319 */       for (int m = 0; m < imageInfo.getNumberOfExtraSamples(); m++) {
/*  320 */         extraSamples[m] = (char)imageInfo.getExtraSamplesType().getValue();
/*      */       }
/*  322 */       fields.add(new TIFFField(338, 3, imageInfo.getNumberOfExtraSamples(), extraSamples));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  328 */     if (dataType != 0) {
/*      */       
/*  330 */       char[] sampleFormat = new char[numBands];
/*  331 */       if (dataType == 4) {
/*  332 */         sampleFormat[0] = '\003';
/*  333 */       } else if (dataType == 1) {
/*  334 */         sampleFormat[0] = '\001';
/*      */       } else {
/*  336 */         sampleFormat[0] = '\002';
/*      */       } 
/*  338 */       for (int b = 1; b < numBands; b++) {
/*  339 */         sampleFormat[b] = sampleFormat[0];
/*      */       }
/*  341 */       fields.add(new TIFFField(339, 3, numBands, sampleFormat));
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
/*  352 */     if (imageInfo.getType() == ImageType.YCBCR) {
/*      */ 
/*      */       
/*  355 */       char subsampleH = '\001';
/*  356 */       char subsampleV = '\001';
/*      */       
/*  358 */       fields.add(new TIFFField(530, 3, 2, new char[] { '\001', '\001' }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  364 */       fields.add(new TIFFField(531, 3, 1, new char[] { (char)((compression == CompressionValue.JPEG_TTN2) ? '\001' : '\002') }));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  371 */       long[][] refbw = { { 15L, 1L }, { 235L, 1L }, { 128L, 1L }, { 240L, 1L }, { 128L, 1L }, { 240L, 1L } };
/*      */ 
/*      */       
/*  374 */       fields.add(new TIFFField(532, 5, 6, refbw));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  383 */     TIFFField[] extraFields = encodeParam.getExtraFields();
/*  384 */     List<Integer> extantTags = new ArrayList(fields.size());
/*  385 */     Iterator<TIFFField> fieldIter = fields.iterator();
/*  386 */     while (fieldIter.hasNext()) {
/*  387 */       TIFFField fld = fieldIter.next();
/*  388 */       extantTags.add(Integer.valueOf(fld.getTag()));
/*      */     } 
/*      */     
/*  391 */     int numExtraFields = extraFields.length;
/*  392 */     for (int k = 0; k < numExtraFields; k++) {
/*  393 */       TIFFField fld = extraFields[k];
/*  394 */       Integer tagValue = Integer.valueOf(fld.getTag());
/*  395 */       if (!extantTags.contains(tagValue)) {
/*  396 */         fields.add(fld);
/*  397 */         extantTags.add(tagValue);
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  406 */     int dirSize = getDirectorySize(fields);
/*      */ 
/*      */ 
/*      */     
/*  410 */     tileOffsets[0] = (ifdOffset + dirSize);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  421 */     OutputStream outCache = null;
/*  422 */     byte[] compressBuf = null;
/*  423 */     File tempFile = null;
/*      */     
/*  425 */     int nextIFDOffset = 0;
/*  426 */     boolean skipByte = false;
/*      */     
/*  428 */     Deflater deflater = null;
/*  429 */     boolean jpegRGBToYCbCr = false;
/*      */     
/*  431 */     if (compression == CompressionValue.NONE) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  436 */       int numBytesPadding = 0;
/*  437 */       if (dataTypeSize == 16 && tileOffsets[0] % 2L != 0L) {
/*  438 */         numBytesPadding = 1;
/*  439 */         tileOffsets[0] = tileOffsets[0] + 1L;
/*  440 */       } else if (dataTypeSize == 32 && tileOffsets[0] % 4L != 0L) {
/*  441 */         numBytesPadding = (int)(4L - tileOffsets[0] % 4L);
/*  442 */         tileOffsets[0] = tileOffsets[0] + numBytesPadding;
/*      */       } 
/*      */ 
/*      */       
/*  446 */       for (int m = 1; m < numTiles; m++) {
/*  447 */         tileOffsets[m] = tileOffsets[m - 1] + tileByteCounts[m - 1];
/*      */       }
/*      */       
/*  450 */       if (!isLast) {
/*      */         
/*  452 */         nextIFDOffset = (int)(tileOffsets[0] + totalBytesOfData);
/*      */ 
/*      */         
/*  455 */         if ((nextIFDOffset & 0x1) != 0) {
/*  456 */           nextIFDOffset++;
/*  457 */           skipByte = true;
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  462 */       writeDirectory(ifdOffset, fields, nextIFDOffset);
/*      */ 
/*      */ 
/*      */       
/*  466 */       if (numBytesPadding != 0) {
/*  467 */         for (int padding = 0; padding < numBytesPadding; padding++) {
/*  468 */           this.output.write(0);
/*      */         
/*      */         }
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/*  475 */       if (this.output instanceof SeekableOutputStream) {
/*      */         
/*  477 */         ((SeekableOutputStream)this.output).seek(tileOffsets[0]);
/*      */       } else {
/*      */         
/*  480 */         outCache = this.output;
/*      */ 
/*      */         
/*      */         try {
/*  484 */           tempFile = File.createTempFile("jai-SOS-", ".tmp");
/*  485 */           tempFile.deleteOnExit();
/*  486 */           RandomAccessFile raFile = new RandomAccessFile(tempFile, "rw");
/*  487 */           this.output = (OutputStream)new SeekableOutputStream(raFile);
/*      */         
/*      */         }
/*  490 */         catch (IOException e) {
/*      */           
/*  492 */           this.output = new ByteArrayOutputStream((int)totalBytesOfData);
/*      */         } 
/*      */       } 
/*      */       
/*  496 */       int bufSize = 0;
/*  497 */       switch (compression) {
/*      */         case PACKBITS:
/*  499 */           bufSize = (int)(bytesPerTile + (bytesPerRow + 127L) / 128L * tileHeight);
/*      */           break;
/*      */         case DEFLATE:
/*  502 */           bufSize = (int)bytesPerTile;
/*  503 */           deflater = new Deflater(encodeParam.getDeflateLevel());
/*      */           break;
/*      */         default:
/*  506 */           bufSize = 0; break;
/*      */       } 
/*  508 */       if (bufSize != 0) {
/*  509 */         compressBuf = new byte[bufSize];
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  516 */     int[] pixels = null;
/*  517 */     float[] fpixels = null;
/*      */ 
/*      */     
/*  520 */     boolean checkContiguous = ((dataTypeSize == 1 && sampleModel instanceof MultiPixelPackedSampleModel && dataType == 0) || (dataTypeSize == 8 && sampleModel instanceof ComponentSampleModel));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  529 */     byte[] bpixels = null;
/*  530 */     if (compression != CompressionValue.JPEG_TTN2) {
/*  531 */       if (dataType == 0) {
/*  532 */         bpixels = new byte[tileHeight * tileWidth * numBands];
/*  533 */       } else if (dataTypeIsShort) {
/*  534 */         bpixels = new byte[2 * tileHeight * tileWidth * numBands];
/*  535 */       } else if (dataType == 3 || dataType == 4) {
/*      */         
/*  537 */         bpixels = new byte[4 * tileHeight * tileWidth * numBands];
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  542 */     int lastRow = minY + height;
/*  543 */     int lastCol = minX + width;
/*  544 */     int tileNum = 0; int row;
/*  545 */     for (row = minY; row < lastRow; row += tileHeight) {
/*  546 */       int rows = isTiled ? tileHeight : Math.min(tileHeight, lastRow - row);
/*      */       
/*  548 */       int size = rows * tileWidth * numBands;
/*      */       int col;
/*  550 */       for (col = minX; col < lastCol; col += tileWidth) {
/*      */         int index, n, ls, i1;
/*  552 */         Raster src = im.getData(new Rectangle(col, row, tileWidth, rows));
/*      */ 
/*      */         
/*  555 */         boolean useDataBuffer = false;
/*  556 */         if (compression != CompressionValue.JPEG_TTN2) {
/*  557 */           if (checkContiguous) {
/*  558 */             if (dataTypeSize == 8) {
/*  559 */               ComponentSampleModel csm = (ComponentSampleModel)src.getSampleModel();
/*      */               
/*  561 */               int[] bankIndices = csm.getBankIndices();
/*  562 */               int[] bandOffsets = csm.getBandOffsets();
/*  563 */               int pixelStride = csm.getPixelStride();
/*  564 */               int lineStride = csm.getScanlineStride();
/*      */               
/*  566 */               if (pixelStride != numBands || lineStride != bytesPerRow) {
/*      */                 
/*  568 */                 useDataBuffer = false;
/*      */               } else {
/*  570 */                 useDataBuffer = true;
/*  571 */                 int i2 = 0;
/*  572 */                 for (; useDataBuffer && i2 < numBands; 
/*  573 */                   i2++) {
/*  574 */                   if (bankIndices[i2] != 0 || bandOffsets[i2] != i2)
/*      */                   {
/*  576 */                     useDataBuffer = false;
/*      */                   }
/*      */                 } 
/*      */               } 
/*      */             } else {
/*  581 */               MultiPixelPackedSampleModel mpp = (MultiPixelPackedSampleModel)src.getSampleModel();
/*      */               
/*  583 */               if (mpp.getNumBands() == 1 && mpp.getDataBitOffset() == 0 && mpp.getPixelBitStride() == 1)
/*      */               {
/*      */                 
/*  586 */                 useDataBuffer = true;
/*      */               }
/*      */             } 
/*      */           }
/*      */           
/*  591 */           if (!useDataBuffer) {
/*  592 */             if (dataType == 4) {
/*  593 */               fpixels = src.getPixels(col, row, tileWidth, rows, fpixels);
/*      */             } else {
/*      */               
/*  596 */               pixels = src.getPixels(col, row, tileWidth, rows, pixels);
/*      */             } 
/*      */           }
/*      */         } 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  604 */         int pixel = 0;
/*  605 */         int m = 0;
/*  606 */         switch (dataTypeSize) {
/*      */ 
/*      */           
/*      */           case 1:
/*  610 */             if (useDataBuffer) {
/*  611 */               byte[] btmp = ((DataBufferByte)src.getDataBuffer()).getData();
/*      */               
/*  613 */               MultiPixelPackedSampleModel mpp = (MultiPixelPackedSampleModel)src.getSampleModel();
/*      */               
/*  615 */               int lineStride = mpp.getScanlineStride();
/*  616 */               int inOffset = mpp.getOffset(col - src.getSampleModelTranslateX(), row - src.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  621 */               if (lineStride == bytesPerRow) {
/*  622 */                 System.arraycopy(btmp, inOffset, bpixels, 0, (int)bytesPerRow * rows);
/*      */               }
/*      */               else {
/*      */                 
/*  626 */                 int outOffset = 0;
/*  627 */                 for (int i2 = 0; i2 < rows; i2++) {
/*  628 */                   System.arraycopy(btmp, inOffset, bpixels, outOffset, (int)bytesPerRow);
/*      */ 
/*      */                   
/*  631 */                   inOffset += lineStride;
/*  632 */                   outOffset = (int)(outOffset + bytesPerRow);
/*      */                 } 
/*      */               } 
/*      */             } else {
/*  636 */               int i2 = 0;
/*      */ 
/*      */               
/*  639 */               for (int i3 = 0; i3 < rows; i3++) {
/*      */                 int i4;
/*      */                 
/*  642 */                 for (i4 = 0; i4 < tileWidth / 8; i4++) {
/*      */                   
/*  644 */                   pixel = pixels[i2++] << 7 | pixels[i2++] << 6 | pixels[i2++] << 5 | pixels[i2++] << 4 | pixels[i2++] << 3 | pixels[i2++] << 2 | pixels[i2++] << 1 | pixels[i2++];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/*  653 */                   bpixels[m++] = (byte)pixel;
/*      */                 } 
/*      */ 
/*      */                 
/*  657 */                 if (tileWidth % 8 > 0) {
/*  658 */                   pixel = 0;
/*  659 */                   for (i4 = 0; i4 < tileWidth % 8; i4++) {
/*  660 */                     pixel |= pixels[i2++] << 7 - i4;
/*      */                   }
/*  662 */                   bpixels[m++] = (byte)pixel;
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */             
/*  667 */             if (compression == CompressionValue.NONE) {
/*  668 */               this.output.write(bpixels, 0, rows * (tileWidth + 7) / 8); break;
/*  669 */             }  if (compression == CompressionValue.PACKBITS) {
/*  670 */               int numCompressedBytes = compressPackBits(bpixels, rows, bytesPerRow, compressBuf);
/*      */ 
/*      */ 
/*      */               
/*  674 */               tileByteCounts[tileNum++] = numCompressedBytes;
/*  675 */               this.output.write(compressBuf, 0, numCompressedBytes); break;
/*  676 */             }  if (compression == CompressionValue.DEFLATE) {
/*  677 */               int numCompressedBytes = deflate(deflater, bpixels, compressBuf);
/*      */               
/*  679 */               tileByteCounts[tileNum++] = numCompressedBytes;
/*  680 */               this.output.write(compressBuf, 0, numCompressedBytes);
/*      */             } 
/*      */             break;
/*      */ 
/*      */ 
/*      */           
/*      */           case 4:
/*  687 */             index = 0;
/*      */ 
/*      */             
/*  690 */             for (n = 0; n < rows; n++) {
/*      */ 
/*      */ 
/*      */               
/*  694 */               for (int i2 = 0; i2 < tileWidth / 2; i2++) {
/*  695 */                 pixel = pixels[index++] << 4 | pixels[index++];
/*  696 */                 bpixels[m++] = (byte)pixel;
/*      */               } 
/*      */ 
/*      */               
/*  700 */               if ((tileWidth & 0x1) == 1) {
/*  701 */                 pixel = pixels[index++] << 4;
/*  702 */                 bpixels[m++] = (byte)pixel;
/*      */               } 
/*      */             } 
/*      */             
/*  706 */             if (compression == CompressionValue.NONE) {
/*  707 */               this.output.write(bpixels, 0, rows * (tileWidth + 1) / 2); break;
/*  708 */             }  if (compression == CompressionValue.PACKBITS) {
/*  709 */               int numCompressedBytes = compressPackBits(bpixels, rows, bytesPerRow, compressBuf);
/*      */ 
/*      */ 
/*      */               
/*  713 */               tileByteCounts[tileNum++] = numCompressedBytes;
/*  714 */               this.output.write(compressBuf, 0, numCompressedBytes); break;
/*  715 */             }  if (compression == CompressionValue.DEFLATE) {
/*  716 */               int numCompressedBytes = deflate(deflater, bpixels, compressBuf);
/*      */               
/*  718 */               tileByteCounts[tileNum++] = numCompressedBytes;
/*  719 */               this.output.write(compressBuf, 0, numCompressedBytes);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case 8:
/*  725 */             if (compression != CompressionValue.JPEG_TTN2) {
/*  726 */               if (useDataBuffer) {
/*  727 */                 byte[] btmp = ((DataBufferByte)src.getDataBuffer()).getData();
/*      */                 
/*  729 */                 ComponentSampleModel csm = (ComponentSampleModel)src.getSampleModel();
/*      */                 
/*  731 */                 int inOffset = csm.getOffset(col - src.getSampleModelTranslateX(), row - src.getSampleModelTranslateY());
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  736 */                 int lineStride = csm.getScanlineStride();
/*  737 */                 if (lineStride == bytesPerRow) {
/*  738 */                   System.arraycopy(btmp, inOffset, bpixels, 0, (int)bytesPerRow * rows);
/*      */                 
/*      */                 }
/*      */                 else {
/*      */                   
/*  743 */                   int outOffset = 0;
/*  744 */                   for (int i2 = 0; i2 < rows; i2++) {
/*  745 */                     System.arraycopy(btmp, inOffset, bpixels, outOffset, (int)bytesPerRow);
/*      */ 
/*      */                     
/*  748 */                     inOffset += lineStride;
/*  749 */                     outOffset = (int)(outOffset + bytesPerRow);
/*      */                   } 
/*      */                 } 
/*      */               } else {
/*  753 */                 for (n = 0; n < size; n++) {
/*  754 */                   bpixels[n] = (byte)pixels[n];
/*      */                 }
/*      */               } 
/*      */             }
/*      */             
/*  759 */             if (compression == CompressionValue.NONE) {
/*  760 */               this.output.write(bpixels, 0, size); break;
/*  761 */             }  if (compression == CompressionValue.PACKBITS) {
/*  762 */               int numCompressedBytes = compressPackBits(bpixels, rows, bytesPerRow, compressBuf);
/*      */ 
/*      */ 
/*      */               
/*  766 */               tileByteCounts[tileNum++] = numCompressedBytes;
/*  767 */               this.output.write(compressBuf, 0, numCompressedBytes); break;
/*  768 */             }  if (compression == CompressionValue.DEFLATE) {
/*  769 */               int numCompressedBytes = deflate(deflater, bpixels, compressBuf);
/*      */               
/*  771 */               tileByteCounts[tileNum++] = numCompressedBytes;
/*  772 */               this.output.write(compressBuf, 0, numCompressedBytes);
/*      */             } 
/*      */             break;
/*      */ 
/*      */           
/*      */           case 16:
/*  778 */             ls = 0;
/*  779 */             for (i1 = 0; i1 < size; i1++) {
/*  780 */               int value = pixels[i1];
/*  781 */               bpixels[ls++] = (byte)((value & 0xFF00) >> 8);
/*  782 */               bpixels[ls++] = (byte)(value & 0xFF);
/*      */             } 
/*      */             
/*  785 */             if (compression == CompressionValue.NONE) {
/*  786 */               this.output.write(bpixels, 0, size * 2); break;
/*  787 */             }  if (compression == CompressionValue.PACKBITS) {
/*  788 */               int numCompressedBytes = compressPackBits(bpixels, rows, bytesPerRow, compressBuf);
/*      */ 
/*      */ 
/*      */               
/*  792 */               tileByteCounts[tileNum++] = numCompressedBytes;
/*  793 */               this.output.write(compressBuf, 0, numCompressedBytes); break;
/*  794 */             }  if (compression == CompressionValue.DEFLATE) {
/*  795 */               int numCompressedBytes = deflate(deflater, bpixels, compressBuf);
/*      */               
/*  797 */               tileByteCounts[tileNum++] = numCompressedBytes;
/*  798 */               this.output.write(compressBuf, 0, numCompressedBytes);
/*      */             } 
/*      */             break;
/*      */           
/*      */           case 32:
/*  803 */             if (dataType == 3) {
/*  804 */               int li = 0;
/*  805 */               for (int i2 = 0; i2 < size; i2++) {
/*  806 */                 int value = pixels[i2];
/*  807 */                 bpixels[li++] = (byte)((value & 0xFF000000) >>> 24);
/*  808 */                 bpixels[li++] = (byte)((value & 0xFF0000) >>> 16);
/*  809 */                 bpixels[li++] = (byte)((value & 0xFF00) >>> 8);
/*  810 */                 bpixels[li++] = (byte)(value & 0xFF);
/*      */               } 
/*      */             } else {
/*  813 */               int lf = 0;
/*  814 */               for (int i2 = 0; i2 < size; i2++) {
/*  815 */                 int value = Float.floatToIntBits(fpixels[i2]);
/*  816 */                 bpixels[lf++] = (byte)((value & 0xFF000000) >>> 24);
/*  817 */                 bpixels[lf++] = (byte)((value & 0xFF0000) >>> 16);
/*  818 */                 bpixels[lf++] = (byte)((value & 0xFF00) >>> 8);
/*  819 */                 bpixels[lf++] = (byte)(value & 0xFF);
/*      */               } 
/*      */             } 
/*  822 */             if (compression == CompressionValue.NONE) {
/*  823 */               this.output.write(bpixels, 0, size * 4); break;
/*  824 */             }  if (compression == CompressionValue.PACKBITS) {
/*  825 */               int numCompressedBytes = compressPackBits(bpixels, rows, bytesPerRow, compressBuf);
/*      */ 
/*      */ 
/*      */               
/*  829 */               tileByteCounts[tileNum++] = numCompressedBytes;
/*  830 */               this.output.write(compressBuf, 0, numCompressedBytes); break;
/*  831 */             }  if (compression == CompressionValue.DEFLATE) {
/*  832 */               int numCompressedBytes = deflate(deflater, bpixels, compressBuf);
/*      */               
/*  834 */               tileByteCounts[tileNum++] = numCompressedBytes;
/*  835 */               this.output.write(compressBuf, 0, numCompressedBytes);
/*      */             } 
/*      */             break;
/*      */         } 
/*      */ 
/*      */ 
/*      */       
/*      */       } 
/*      */     } 
/*  844 */     if (compression == CompressionValue.NONE) {
/*      */       
/*  846 */       if (skipByte) {
/*  847 */         this.output.write(0);
/*      */       }
/*      */     } else {
/*      */       
/*  851 */       int totalBytes = 0;
/*  852 */       for (int m = 1; m < numTiles; m++) {
/*  853 */         int numBytes = (int)tileByteCounts[m - 1];
/*  854 */         totalBytes += numBytes;
/*  855 */         tileOffsets[m] = tileOffsets[m - 1] + numBytes;
/*      */       } 
/*  857 */       totalBytes += (int)tileByteCounts[numTiles - 1];
/*      */       
/*  859 */       nextIFDOffset = isLast ? 0 : (ifdOffset + dirSize + totalBytes);
/*      */       
/*  861 */       if ((nextIFDOffset & 0x1) != 0) {
/*  862 */         nextIFDOffset++;
/*  863 */         skipByte = true;
/*      */       } 
/*      */       
/*  866 */       if (outCache == null) {
/*      */ 
/*      */ 
/*      */         
/*  870 */         if (skipByte) {
/*  871 */           this.output.write(0);
/*      */         }
/*      */         
/*  874 */         SeekableOutputStream sos = (SeekableOutputStream)this.output;
/*      */ 
/*      */         
/*  877 */         long savePos = sos.getFilePointer();
/*      */ 
/*      */         
/*  880 */         sos.seek(ifdOffset);
/*  881 */         writeDirectory(ifdOffset, fields, nextIFDOffset);
/*      */ 
/*      */         
/*  884 */         sos.seek(savePos);
/*  885 */       } else if (tempFile != null) {
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  890 */         FileInputStream fileStream = new FileInputStream(tempFile);
/*      */         
/*      */         try {
/*  893 */           this.output.close();
/*      */ 
/*      */           
/*  896 */           this.output = outCache;
/*      */ 
/*      */           
/*  899 */           writeDirectory(ifdOffset, fields, nextIFDOffset);
/*      */ 
/*      */           
/*  902 */           byte[] copyBuffer = new byte[8192];
/*  903 */           int bytesCopied = 0;
/*  904 */           while (bytesCopied < totalBytes) {
/*  905 */             int bytesRead = fileStream.read(copyBuffer);
/*  906 */             if (bytesRead == -1) {
/*      */               break;
/*      */             }
/*  909 */             this.output.write(copyBuffer, 0, bytesRead);
/*  910 */             bytesCopied += bytesRead;
/*      */           } 
/*      */         } finally {
/*      */           
/*  914 */           fileStream.close();
/*      */         } 
/*  916 */         boolean isDeleted = tempFile.delete();
/*  917 */         assert isDeleted;
/*      */ 
/*      */         
/*  920 */         if (skipByte) {
/*  921 */           this.output.write(0);
/*      */         }
/*  923 */       } else if (this.output instanceof ByteArrayOutputStream) {
/*      */ 
/*      */ 
/*      */         
/*  927 */         ByteArrayOutputStream memoryStream = (ByteArrayOutputStream)this.output;
/*      */ 
/*      */         
/*  930 */         this.output = outCache;
/*      */ 
/*      */         
/*  933 */         writeDirectory(ifdOffset, fields, nextIFDOffset);
/*      */ 
/*      */         
/*  936 */         memoryStream.writeTo(this.output);
/*      */ 
/*      */         
/*  939 */         if (skipByte) {
/*  940 */           this.output.write(0);
/*      */         }
/*      */       } else {
/*      */         
/*  944 */         throw new IllegalStateException(PropertyUtil.getString("TIFFImageEncoder13"));
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  949 */     return nextIFDOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void validateImage(int dataTypeSize, int[] sampleSize, int numBands, int dataType, ColorModel colorModel) {
/*  955 */     for (int i = 1; i < sampleSize.length; i++) {
/*  956 */       if (sampleSize[i] != dataTypeSize) {
/*  957 */         throw new RuntimeException(PropertyUtil.getString("TIFFImageEncoder0"));
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/*  962 */     if ((dataTypeSize == 1 || dataTypeSize == 4) && numBands != 1) {
/*  963 */       throw new RuntimeException(PropertyUtil.getString("TIFFImageEncoder1"));
/*      */     }
/*      */ 
/*      */     
/*  967 */     switch (dataType) {
/*      */       case 0:
/*  969 */         if (dataTypeSize == 4) {
/*  970 */           throw new RuntimeException(PropertyUtil.getString("TIFFImageEncoder2"));
/*      */         }
/*      */         break;
/*      */       case 1:
/*      */       case 2:
/*  975 */         if (dataTypeSize != 16) {
/*  976 */           throw new RuntimeException(PropertyUtil.getString("TIFFImageEncoder3"));
/*      */         }
/*      */         break;
/*      */       case 3:
/*      */       case 4:
/*  981 */         if (dataTypeSize != 32) {
/*  982 */           throw new RuntimeException(PropertyUtil.getString("TIFFImageEncoder4"));
/*      */         }
/*      */         break;
/*      */       default:
/*  986 */         throw new RuntimeException(PropertyUtil.getString("TIFFImageEncoder5"));
/*      */     } 
/*      */     
/*  989 */     if (colorModel instanceof java.awt.image.IndexColorModel && dataType != 0)
/*      */     {
/*  991 */       throw new RuntimeException(PropertyUtil.getString("TIFFImageEncoder6"));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int getDirectorySize(SortedSet fields) {
/* 1000 */     int numEntries = fields.size();
/*      */ 
/*      */     
/* 1003 */     int dirSize = 2 + numEntries * 12 + 4;
/*      */ 
/*      */     
/* 1006 */     Iterator<TIFFField> iter = fields.iterator();
/* 1007 */     while (iter.hasNext()) {
/*      */       
/* 1009 */       TIFFField field = iter.next();
/*      */ 
/*      */       
/* 1012 */       int valueSize = field.getCount() * SIZE_OF_TYPE[field.getType()];
/*      */ 
/*      */       
/* 1015 */       if (valueSize > 4) {
/* 1016 */         dirSize += valueSize;
/*      */       }
/*      */     } 
/*      */     
/* 1020 */     return dirSize;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeFileHeader() throws IOException {
/* 1027 */     this.output.write(77);
/* 1028 */     this.output.write(77);
/*      */ 
/*      */     
/* 1031 */     this.output.write(0);
/* 1032 */     this.output.write(42);
/*      */ 
/*      */     
/* 1035 */     writeLong(8L);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeDirectory(int thisIFDOffset, SortedSet fields, int nextIFDOffset) throws IOException {
/* 1043 */     int numEntries = fields.size();
/*      */     
/* 1045 */     long offsetBeyondIFD = (thisIFDOffset + 12 * numEntries + 4 + 2);
/* 1046 */     List<TIFFField> tooBig = new ArrayList();
/*      */ 
/*      */     
/* 1049 */     writeUnsignedShort(numEntries);
/*      */     
/* 1051 */     Iterator<TIFFField> iter = fields.iterator();
/* 1052 */     while (iter.hasNext()) {
/*      */ 
/*      */       
/* 1055 */       TIFFField field = iter.next();
/*      */ 
/*      */       
/* 1058 */       int tag = field.getTag();
/* 1059 */       writeUnsignedShort(tag);
/*      */ 
/*      */       
/* 1062 */       int type = field.getType();
/* 1063 */       writeUnsignedShort(type);
/*      */ 
/*      */ 
/*      */       
/* 1067 */       int count = field.getCount();
/* 1068 */       int valueSize = getValueSize(field);
/* 1069 */       writeLong((type == 2) ? valueSize : count);
/*      */ 
/*      */       
/* 1072 */       if (valueSize > 4) {
/*      */ 
/*      */         
/* 1075 */         writeLong(offsetBeyondIFD);
/* 1076 */         offsetBeyondIFD += valueSize;
/* 1077 */         tooBig.add(field);
/*      */         continue;
/*      */       } 
/* 1080 */       writeValuesAsFourBytes(field);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1086 */     writeLong(nextIFDOffset);
/*      */ 
/*      */     
/* 1089 */     for (int i = 0; i < tooBig.size(); i++) {
/* 1090 */       writeValues(tooBig.get(i));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getValueSize(TIFFField field) throws UnsupportedEncodingException {
/* 1098 */     int type = field.getType();
/* 1099 */     int count = field.getCount();
/* 1100 */     int valueSize = 0;
/* 1101 */     if (type == 2) {
/* 1102 */       for (int i = 0; i < count; i++) {
/* 1103 */         byte[] stringBytes = field.getAsString(i).getBytes("UTF-8");
/* 1104 */         valueSize += stringBytes.length;
/* 1105 */         if (stringBytes[stringBytes.length - 1] != 0) {
/* 1106 */           valueSize++;
/*      */         }
/*      */       } 
/*      */     } else {
/* 1110 */       valueSize = count * SIZE_OF_TYPE[type];
/*      */     } 
/* 1112 */     return valueSize;
/*      */   }
/*      */   
/* 1115 */   private static final int[] SIZE_OF_TYPE = new int[] { 0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeValuesAsFourBytes(TIFFField field) throws IOException {
/*      */     byte[] bytes;
/*      */     int i;
/*      */     char[] chars;
/*      */     int j;
/*      */     long[] longs;
/* 1133 */     int k, dataType = field.getType();
/* 1134 */     int count = field.getCount();
/*      */     
/* 1136 */     switch (dataType) {
/*      */ 
/*      */       
/*      */       case 1:
/* 1140 */         bytes = field.getAsBytes();
/* 1141 */         if (count > 4) {
/* 1142 */           count = 4;
/*      */         }
/* 1144 */         for (i = 0; i < count; i++) {
/* 1145 */           this.output.write(bytes[i]);
/*      */         }
/*      */         
/* 1148 */         for (i = 0; i < 4 - count; i++) {
/* 1149 */           this.output.write(0);
/*      */         }
/*      */         break;
/*      */ 
/*      */       
/*      */       case 3:
/* 1155 */         chars = field.getAsChars();
/* 1156 */         if (count > 2) {
/* 1157 */           count = 2;
/*      */         }
/* 1159 */         for (j = 0; j < count; j++) {
/* 1160 */           writeUnsignedShort(chars[j]);
/*      */         }
/* 1162 */         for (j = 0; j < 2 - count; j++) {
/* 1163 */           writeUnsignedShort(0);
/*      */         }
/*      */         break;
/*      */ 
/*      */ 
/*      */       
/*      */       case 4:
/* 1170 */         longs = field.getAsLongs();
/*      */         
/* 1172 */         for (k = 0; k < count; k++)
/* 1173 */           writeLong(longs[k]);  break;
/*      */     }  } private void writeValues(TIFFField field) throws IOException { byte[] bytes; int i; char[] chars; int j; short[] shorts; int k;
/*      */     long[] longs;
/*      */     int m;
/*      */     float[] floats;
/*      */     int n;
/*      */     double[] doubles;
/*      */     int i1;
/*      */     long[][] rationals;
/* 1182 */     int i2, dataType = field.getType();
/* 1183 */     int count = field.getCount();
/*      */     
/* 1185 */     switch (dataType) {
/*      */ 
/*      */       
/*      */       case 1:
/*      */       case 6:
/*      */       case 7:
/* 1191 */         bytes = field.getAsBytes();
/* 1192 */         for (i = 0; i < count; i++) {
/* 1193 */           this.output.write(bytes[i]);
/*      */         }
/*      */         return;
/*      */ 
/*      */       
/*      */       case 3:
/* 1199 */         chars = field.getAsChars();
/* 1200 */         for (j = 0; j < count; j++) {
/* 1201 */           writeUnsignedShort(chars[j]);
/*      */         }
/*      */         return;
/*      */       case 8:
/* 1205 */         shorts = field.getAsShorts();
/* 1206 */         for (k = 0; k < count; k++) {
/* 1207 */           writeUnsignedShort(shorts[k]);
/*      */         }
/*      */         return;
/*      */ 
/*      */       
/*      */       case 4:
/*      */       case 9:
/* 1214 */         longs = field.getAsLongs();
/* 1215 */         for (m = 0; m < count; m++) {
/* 1216 */           writeLong(longs[m]);
/*      */         }
/*      */         return;
/*      */       
/*      */       case 11:
/* 1221 */         floats = field.getAsFloats();
/* 1222 */         for (n = 0; n < count; n++) {
/* 1223 */           int intBits = Float.floatToIntBits(floats[n]);
/* 1224 */           writeLong(intBits);
/*      */         } 
/*      */         return;
/*      */       
/*      */       case 12:
/* 1229 */         doubles = field.getAsDoubles();
/* 1230 */         for (i1 = 0; i1 < count; i1++) {
/* 1231 */           long longBits = Double.doubleToLongBits(doubles[i1]);
/* 1232 */           writeLong(longBits >>> 32L);
/* 1233 */           writeLong(longBits & 0xFFFFFFFFL);
/*      */         } 
/*      */         return;
/*      */       
/*      */       case 5:
/*      */       case 10:
/* 1239 */         rationals = field.getAsRationals();
/* 1240 */         for (i2 = 0; i2 < count; i2++) {
/* 1241 */           writeLong(rationals[i2][0]);
/* 1242 */           writeLong(rationals[i2][1]);
/*      */         } 
/*      */         return;
/*      */       
/*      */       case 2:
/* 1247 */         for (i2 = 0; i2 < count; i2++) {
/* 1248 */           byte[] stringBytes = field.getAsString(i2).getBytes("UTF-8");
/* 1249 */           this.output.write(stringBytes);
/* 1250 */           if (stringBytes[stringBytes.length - 1] != 0) {
/* 1251 */             this.output.write(0);
/*      */           }
/*      */         } 
/*      */         return;
/*      */     } 
/*      */     
/* 1257 */     throw new RuntimeException(PropertyUtil.getString("TIFFImageEncoder10")); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeUnsignedShort(int s) throws IOException {
/* 1266 */     this.output.write((s & 0xFF00) >>> 8);
/* 1267 */     this.output.write(s & 0xFF);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeLong(long l) throws IOException {
/* 1276 */     this.output.write((int)((l & 0xFFFFFFFFFF000000L) >>> 24L));
/* 1277 */     this.output.write((int)((l & 0xFF0000L) >>> 16L));
/* 1278 */     this.output.write((int)((l & 0xFF00L) >>> 8L));
/* 1279 */     this.output.write((int)(l & 0xFFL));
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
/*      */   private static int compressPackBits(byte[] data, int numRows, long bytesPerRow, byte[] compData) {
/* 1302 */     int inOffset = 0;
/* 1303 */     int outOffset = 0;
/*      */     
/* 1305 */     for (int i = 0; i < numRows; i++) {
/* 1306 */       outOffset = packBits(data, inOffset, (int)bytesPerRow, compData, outOffset);
/*      */       
/* 1308 */       inOffset = (int)(inOffset + bytesPerRow);
/*      */     } 
/*      */     
/* 1311 */     return outOffset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int packBits(byte[] input, int inOffset, int inCount, byte[] output, int outOffset) {
/* 1321 */     int inMax = inOffset + inCount - 1;
/* 1322 */     int inMaxMinus1 = inMax - 1;
/*      */     
/* 1324 */     while (inOffset <= inMax) {
/* 1325 */       int run = 1;
/* 1326 */       byte replicate = input[inOffset];
/*      */       
/* 1328 */       while (run < 127 && inOffset < inMax && input[inOffset] == input[inOffset + 1]) {
/* 1329 */         run++;
/* 1330 */         inOffset++;
/*      */       } 
/* 1332 */       if (run > 1) {
/* 1333 */         inOffset++;
/* 1334 */         output[outOffset++] = (byte)-(run - 1);
/* 1335 */         output[outOffset++] = replicate;
/*      */       } 
/*      */       
/* 1338 */       run = 0;
/* 1339 */       int saveOffset = outOffset;
/*      */       
/* 1341 */       while (run < 128 && ((inOffset < inMax && input[inOffset] != input[inOffset + 1]) || (inOffset < inMaxMinus1 && input[inOffset] != input[inOffset + 2]))) {
/*      */ 
/*      */ 
/*      */         
/* 1345 */         run++;
/* 1346 */         output[++outOffset] = input[inOffset++];
/*      */       } 
/* 1348 */       if (run > 0) {
/* 1349 */         output[saveOffset] = (byte)(run - 1);
/* 1350 */         outOffset++;
/*      */       } 
/*      */       
/* 1353 */       if (inOffset == inMax) {
/* 1354 */         if (run > 0 && run < 128) {
/* 1355 */           output[saveOffset] = (byte)(output[saveOffset] + 1);
/* 1356 */           output[outOffset++] = input[inOffset++]; continue;
/*      */         } 
/* 1358 */         output[outOffset++] = 0;
/* 1359 */         output[outOffset++] = input[inOffset++];
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1364 */     return outOffset;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int deflate(Deflater deflater, byte[] inflated, byte[] deflated) {
/* 1369 */     deflater.setInput(inflated);
/* 1370 */     deflater.finish();
/* 1371 */     int numCompressedBytes = deflater.deflate(deflated);
/* 1372 */     deflater.reset();
/* 1373 */     return numCompressedBytes;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/tiff/TIFFImageEncoder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */