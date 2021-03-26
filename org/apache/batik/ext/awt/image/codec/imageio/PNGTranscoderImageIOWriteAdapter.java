/*     */ package org.apache.batik.ext.awt.image.codec.imageio;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.batik.ext.awt.image.rendered.IndexImage;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriter;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriterParams;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriterRegistry;
/*     */ import org.apache.batik.transcoder.TranscoderException;
/*     */ import org.apache.batik.transcoder.TranscoderOutput;
/*     */ import org.apache.batik.transcoder.TranscodingHints;
/*     */ import org.apache.batik.transcoder.image.PNGTranscoder;
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
/*     */ public class PNGTranscoderImageIOWriteAdapter
/*     */   implements PNGTranscoder.WriteAdapter
/*     */ {
/*     */   public void writeImage(PNGTranscoder transcoder, BufferedImage img, TranscoderOutput output) throws TranscoderException {
/*  52 */     TranscodingHints hints = transcoder.getTranscodingHints();
/*     */     
/*  54 */     int n = -1;
/*  55 */     if (hints.containsKey(PNGTranscoder.KEY_INDEXED)) {
/*  56 */       n = ((Integer)hints.get(PNGTranscoder.KEY_INDEXED)).intValue();
/*  57 */       if (n == 1 || n == 2 || n == 4 || n == 8)
/*     */       {
/*  59 */         img = IndexImage.getIndexedImage(img, 1 << n);
/*     */       }
/*     */     } 
/*  62 */     ImageWriter writer = ImageWriterRegistry.getInstance().getWriterFor("image/png");
/*     */     
/*  64 */     ImageWriterParams params = new ImageWriterParams();
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
/*  91 */     float PixSzMM = transcoder.getUserAgent().getPixelUnitToMillimeter();
/*  92 */     int PixSzInch = (int)(25.4D / PixSzMM + 0.5D);
/*  93 */     params.setResolution(PixSzInch);
/*     */     
/*     */     try {
/*  96 */       OutputStream ostream = output.getOutputStream();
/*  97 */       writer.writeImage(img, ostream, params);
/*  98 */       ostream.flush();
/*  99 */     } catch (IOException ex) {
/* 100 */       throw new TranscoderException(ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/imageio/PNGTranscoderImageIOWriteAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */