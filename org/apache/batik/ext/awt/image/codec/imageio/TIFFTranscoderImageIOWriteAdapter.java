/*     */ package org.apache.batik.ext.awt.image.codec.imageio;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.PixelInterleavedSampleModel;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.rendered.FormatRed;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriter;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriterParams;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriterRegistry;
/*     */ import org.apache.batik.transcoder.TranscoderException;
/*     */ import org.apache.batik.transcoder.TranscoderOutput;
/*     */ import org.apache.batik.transcoder.TranscodingHints;
/*     */ import org.apache.batik.transcoder.image.TIFFTranscoder;
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
/*     */ public class TIFFTranscoderImageIOWriteAdapter
/*     */   implements TIFFTranscoder.WriteAdapter
/*     */ {
/*     */   public void writeImage(TIFFTranscoder transcoder, BufferedImage img, TranscoderOutput output) throws TranscoderException {
/*  57 */     TranscodingHints hints = transcoder.getTranscodingHints();
/*     */     
/*  59 */     ImageWriter writer = ImageWriterRegistry.getInstance().getWriterFor("image/tiff");
/*     */     
/*  61 */     ImageWriterParams params = new ImageWriterParams();
/*     */     
/*  63 */     float PixSzMM = transcoder.getUserAgent().getPixelUnitToMillimeter();
/*  64 */     int PixSzInch = (int)(25.4D / PixSzMM + 0.5D);
/*  65 */     params.setResolution(PixSzInch);
/*     */     
/*  67 */     if (hints.containsKey(TIFFTranscoder.KEY_COMPRESSION_METHOD)) {
/*  68 */       String method = (String)hints.get(TIFFTranscoder.KEY_COMPRESSION_METHOD);
/*     */       
/*  70 */       if ("packbits".equals(method)) {
/*  71 */         params.setCompressionMethod("PackBits");
/*  72 */       } else if ("deflate".equals(method)) {
/*  73 */         params.setCompressionMethod("Deflate");
/*  74 */       } else if ("lzw".equals(method)) {
/*  75 */         params.setCompressionMethod("LZW");
/*  76 */       } else if ("jpeg".equals(method)) {
/*  77 */         params.setCompressionMethod("JPEG");
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  84 */       OutputStream ostream = output.getOutputStream();
/*  85 */       int w = img.getWidth();
/*  86 */       int h = img.getHeight();
/*     */       
/*  88 */       SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)img.getSampleModel();
/*  89 */       int bands = sppsm.getNumBands();
/*  90 */       int[] off = new int[bands];
/*  91 */       for (int i = 0; i < bands; i++)
/*  92 */         off[i] = i; 
/*  93 */       SampleModel sm = new PixelInterleavedSampleModel(0, w, h, bands, w * bands, off);
/*     */ 
/*     */       
/*  96 */       FormatRed formatRed = new FormatRed(GraphicsUtil.wrap(img), sm);
/*  97 */       writer.writeImage((RenderedImage)formatRed, ostream, params);
/*  98 */       ostream.flush();
/*  99 */     } catch (IOException ex) {
/* 100 */       throw new TranscoderException(ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/imageio/TIFFTranscoderImageIOWriteAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */