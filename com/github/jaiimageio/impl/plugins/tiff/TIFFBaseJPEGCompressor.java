/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import com.github.jaiimageio.plugins.tiff.TIFFCompressor;
/*     */ import java.awt.Point;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.PixelInterleavedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.imageio.IIOException;
/*     */ import javax.imageio.IIOImage;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageWriteParam;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.metadata.IIOInvalidTreeException;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.metadata.IIOMetadataNode;
/*     */ import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
/*     */ import javax.imageio.spi.ImageWriterSpi;
/*     */ import javax.imageio.stream.ImageOutputStream;
/*     */ import javax.imageio.stream.MemoryCacheImageOutputStream;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TIFFBaseJPEGCompressor
/*     */   extends TIFFCompressor
/*     */ {
/*     */   private static final boolean DEBUG = false;
/*     */   protected static final String STREAM_METADATA_NAME = "javax_imageio_jpeg_stream_1.0";
/*     */   protected static final String IMAGE_METADATA_NAME = "javax_imageio_jpeg_image_1.0";
/* 100 */   private ImageWriteParam param = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 106 */   protected JPEGImageWriteParam JPEGParam = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 112 */   protected ImageWriter JPEGWriter = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean writeAbbreviatedStream = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   protected IIOMetadata JPEGStreamMetadata = null;
/*     */ 
/*     */   
/* 130 */   private IIOMetadata JPEGImageMetadata = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean usingCodecLib;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private IIOByteArrayOutputStream baos;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void pruneNodes(Node tree, boolean pruneTables) {
/* 152 */     if (tree == null) {
/* 153 */       throw new IllegalArgumentException("tree == null!");
/*     */     }
/* 155 */     if (!tree.getNodeName().equals("javax_imageio_jpeg_image_1.0")) {
/* 156 */       throw new IllegalArgumentException("root node name is not javax_imageio_jpeg_image_1.0!");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 164 */     List<String> wantedNodes = new ArrayList();
/* 165 */     wantedNodes.addAll(Arrays.asList(new String[] { "JPEGvariety", "markerSequence", "sof", "componentSpec", "sos", "scanComponentSpec" }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 172 */     if (!pruneTables) {
/* 173 */       wantedNodes.add("dht");
/* 174 */       wantedNodes.add("dhtable");
/* 175 */       wantedNodes.add("dqt");
/* 176 */       wantedNodes.add("dqtable");
/*     */     } 
/*     */     
/* 179 */     IIOMetadataNode iioTree = (IIOMetadataNode)tree;
/*     */     
/* 181 */     List<Node> nodes = getAllNodes(iioTree, (List)null);
/* 182 */     int numNodes = nodes.size();
/*     */     
/* 184 */     for (int i = 0; i < numNodes; i++) {
/* 185 */       Node node = nodes.get(i);
/* 186 */       if (!wantedNodes.contains(node.getNodeName()))
/*     */       {
/*     */ 
/*     */         
/* 190 */         node.getParentNode().removeChild(node);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static List getAllNodes(IIOMetadataNode root, List<Node> nodes) {
/* 196 */     if (nodes == null) nodes = new ArrayList();
/*     */     
/* 198 */     if (root.hasChildNodes()) {
/* 199 */       Node sibling = root.getFirstChild();
/* 200 */       while (sibling != null) {
/* 201 */         nodes.add(sibling);
/* 202 */         nodes = getAllNodes((IIOMetadataNode)sibling, nodes);
/* 203 */         sibling = sibling.getNextSibling();
/*     */       } 
/*     */     } 
/*     */     
/* 207 */     return nodes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TIFFBaseJPEGCompressor(String compressionType, int compressionTagValue, boolean isCompressionLossless, ImageWriteParam param) {
/* 214 */     super(compressionType, compressionTagValue, isCompressionLossless);
/*     */     
/* 216 */     this.param = param;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class IIOByteArrayOutputStream
/*     */     extends ByteArrayOutputStream
/*     */   {
/*     */     IIOByteArrayOutputStream() {}
/*     */ 
/*     */ 
/*     */     
/*     */     IIOByteArrayOutputStream(int size) {
/* 229 */       super(size);
/*     */     }
/*     */ 
/*     */     
/*     */     public synchronized void writeTo(ImageOutputStream ios) throws IOException {
/* 234 */       ios.write(this.buf, 0, this.count);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initJPEGWriter(boolean supportsStreamMetadata, boolean supportsImageMetadata) {
/* 251 */     if (this.JPEGWriter != null && (supportsStreamMetadata || supportsImageMetadata)) {
/*     */       
/* 253 */       ImageWriterSpi spi = this.JPEGWriter.getOriginatingProvider();
/* 254 */       if (supportsStreamMetadata) {
/* 255 */         String smName = spi.getNativeStreamMetadataFormatName();
/* 256 */         if (smName == null || !smName.equals("javax_imageio_jpeg_stream_1.0")) {
/* 257 */           this.JPEGWriter = null;
/*     */         }
/*     */       } 
/* 260 */       if (this.JPEGWriter != null && supportsImageMetadata) {
/* 261 */         String imName = spi.getNativeImageMetadataFormatName();
/* 262 */         if (imName == null || !imName.equals("javax_imageio_jpeg_image_1.0")) {
/* 263 */           this.JPEGWriter = null;
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 269 */     if (this.JPEGWriter == null) {
/* 270 */       Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");
/*     */       
/* 272 */       while (iter.hasNext()) {
/*     */         
/* 274 */         ImageWriter writer = iter.next();
/*     */ 
/*     */         
/* 277 */         if (supportsStreamMetadata || supportsImageMetadata) {
/* 278 */           ImageWriterSpi spi = writer.getOriginatingProvider();
/* 279 */           if (supportsStreamMetadata) {
/*     */             
/* 281 */             String smName = spi.getNativeStreamMetadataFormatName();
/* 282 */             if (smName == null || 
/* 283 */               !smName.equals("javax_imageio_jpeg_stream_1.0")) {
/*     */               continue;
/*     */             }
/*     */           } 
/*     */           
/* 288 */           if (supportsImageMetadata) {
/*     */             
/* 290 */             String imName = spi.getNativeImageMetadataFormatName();
/* 291 */             if (imName == null || 
/* 292 */               !imName.equals("javax_imageio_jpeg_image_1.0")) {
/*     */               continue;
/*     */             }
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 300 */         this.JPEGWriter = writer;
/*     */       } 
/*     */ 
/*     */       
/* 304 */       if (this.JPEGWriter == null)
/*     */       {
/* 306 */         throw new IllegalStateException("No appropriate JPEG writers found!");
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 311 */     this
/* 312 */       .usingCodecLib = this.JPEGWriter.getClass().getName().startsWith("com.sun.media");
/*     */ 
/*     */ 
/*     */     
/* 316 */     if (this.JPEGParam == null) {
/* 317 */       if (this.param != null && this.param instanceof JPEGImageWriteParam) {
/* 318 */         this.JPEGParam = (JPEGImageWriteParam)this.param;
/*     */       } else {
/* 320 */         this
/*     */           
/* 322 */           .JPEGParam = new JPEGImageWriteParam((this.writer != null) ? this.writer.getLocale() : null);
/* 323 */         if (this.param.getCompressionMode() == 2) {
/*     */           
/* 325 */           this.JPEGParam.setCompressionMode(2);
/* 326 */           this.JPEGParam.setCompressionQuality(this.param.getCompressionQuality());
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private IIOMetadata getImageMetadata(boolean pruneTables) throws IIOException {
/* 340 */     if (this.JPEGImageMetadata == null && "javax_imageio_jpeg_image_1.0"
/* 341 */       .equals(this.JPEGWriter.getOriginatingProvider().getNativeImageMetadataFormatName())) {
/* 342 */       TIFFImageWriter tiffWriter = (TIFFImageWriter)this.writer;
/*     */ 
/*     */       
/* 345 */       this
/* 346 */         .JPEGImageMetadata = this.JPEGWriter.getDefaultImageMetadata(tiffWriter.imageType, this.JPEGParam);
/*     */ 
/*     */ 
/*     */       
/* 350 */       Node tree = this.JPEGImageMetadata.getAsTree("javax_imageio_jpeg_image_1.0");
/*     */ 
/*     */       
/*     */       try {
/* 354 */         pruneNodes(tree, pruneTables);
/* 355 */       } catch (IllegalArgumentException e) {
/* 356 */         throw new IIOException("Error pruning unwanted nodes", e);
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 361 */         this.JPEGImageMetadata.setFromTree("javax_imageio_jpeg_image_1.0", tree);
/* 362 */       } catch (IIOInvalidTreeException e) {
/*     */ 
/*     */ 
/*     */         
/* 366 */         throw new IIOException("Cannot set pruned image metadata!", e);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 371 */     return this.JPEGImageMetadata; } public final int encode(byte[] b, int off, int width, int height, int[] bitsPerSample, int scanlineStride) throws IOException {
/*     */     ImageOutputStream ios;
/*     */     long initialStreamPosition;
/*     */     DataBufferByte dbb;
/*     */     int[] offsets;
/*     */     ColorSpace cs;
/*     */     int compDataLength;
/* 378 */     if (this.JPEGWriter == null) {
/* 379 */       throw new IIOException("JPEG writer has not been initialized!");
/*     */     }
/*     */     
/* 382 */     if ((bitsPerSample.length != 3 || bitsPerSample[0] != 8 || bitsPerSample[1] != 8 || bitsPerSample[2] != 8) && (bitsPerSample.length != 1 || bitsPerSample[0] != 8))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 388 */       throw new IIOException("Can only JPEG compress 8- and 24-bit images!");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 395 */     if (this.usingCodecLib && !this.writeAbbreviatedStream) {
/* 396 */       ios = this.stream;
/* 397 */       initialStreamPosition = this.stream.getStreamPosition();
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 404 */       if (this.baos == null) {
/* 405 */         this.baos = new IIOByteArrayOutputStream();
/*     */       } else {
/* 407 */         this.baos.reset();
/*     */       } 
/* 409 */       ios = new MemoryCacheImageOutputStream(this.baos);
/* 410 */       initialStreamPosition = 0L;
/*     */     } 
/* 412 */     this.JPEGWriter.setOutput(ios);
/*     */ 
/*     */ 
/*     */     
/* 416 */     if (off == 0 || this.usingCodecLib) {
/* 417 */       dbb = new DataBufferByte(b, b.length);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 423 */       int bytesPerSegment = scanlineStride * height;
/* 424 */       byte[] btmp = new byte[bytesPerSegment];
/* 425 */       System.arraycopy(b, off, btmp, 0, bytesPerSegment);
/* 426 */       dbb = new DataBufferByte(btmp, bytesPerSegment);
/* 427 */       off = 0;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 433 */     if (bitsPerSample.length == 3) {
/* 434 */       offsets = new int[] { off, off + 1, off + 2 };
/* 435 */       cs = ColorSpace.getInstance(1000);
/*     */     } else {
/* 437 */       offsets = new int[] { off };
/* 438 */       cs = ColorSpace.getInstance(1003);
/*     */     } 
/*     */ 
/*     */     
/* 442 */     ColorModel cm = new ComponentColorModel(cs, false, false, 1, 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 449 */     SampleModel sm = new PixelInterleavedSampleModel(0, width, height, bitsPerSample.length, scanlineStride, offsets);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 458 */     WritableRaster wras = Raster.createWritableRaster(sm, dbb, new Point(0, 0));
/*     */ 
/*     */     
/* 461 */     BufferedImage bi = new BufferedImage(cm, wras, false, null);
/*     */ 
/*     */     
/* 464 */     IIOMetadata imageMetadata = getImageMetadata(this.writeAbbreviatedStream);
/*     */ 
/*     */ 
/*     */     
/* 468 */     if (this.usingCodecLib && !this.writeAbbreviatedStream) {
/*     */       
/* 470 */       this.JPEGWriter.write(null, new IIOImage(bi, null, imageMetadata), this.JPEGParam);
/*     */ 
/*     */ 
/*     */       
/* 474 */       compDataLength = (int)(this.stream.getStreamPosition() - initialStreamPosition);
/*     */     } else {
/* 476 */       if (this.writeAbbreviatedStream) {
/*     */ 
/*     */ 
/*     */         
/* 480 */         this.JPEGWriter.prepareWriteSequence(this.JPEGStreamMetadata);
/* 481 */         ios.flush();
/*     */ 
/*     */         
/* 484 */         this.baos.reset();
/*     */ 
/*     */         
/* 487 */         IIOImage image = new IIOImage(bi, null, imageMetadata);
/* 488 */         this.JPEGWriter.writeToSequence(image, this.JPEGParam);
/* 489 */         this.JPEGWriter.endWriteSequence();
/*     */       } else {
/*     */         
/* 492 */         this.JPEGWriter.write(null, new IIOImage(bi, null, imageMetadata), this.JPEGParam);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 497 */       compDataLength = this.baos.size();
/* 498 */       this.baos.writeTo(this.stream);
/* 499 */       this.baos.reset();
/*     */     } 
/*     */     
/* 502 */     return compDataLength;
/*     */   }
/*     */   
/*     */   protected void finalize() throws Throwable {
/* 506 */     super.finalize();
/* 507 */     if (this.JPEGWriter != null)
/* 508 */       this.JPEGWriter.dispose(); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFBaseJPEGCompressor.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */