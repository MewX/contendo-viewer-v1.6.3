/*    */ package org.apache.batik.ext.awt.image.codec.png;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import org.apache.batik.ext.awt.image.rendered.IndexImage;
/*    */ import org.apache.batik.transcoder.TranscoderException;
/*    */ import org.apache.batik.transcoder.TranscoderOutput;
/*    */ import org.apache.batik.transcoder.TranscodingHints;
/*    */ import org.apache.batik.transcoder.image.PNGTranscoder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PNGTranscoderInternalCodecWriteAdapter
/*    */   implements PNGTranscoder.WriteAdapter
/*    */ {
/*    */   public void writeImage(PNGTranscoder transcoder, BufferedImage img, TranscoderOutput output) throws TranscoderException {
/* 48 */     TranscodingHints hints = transcoder.getTranscodingHints();
/*    */     
/* 50 */     int n = -1;
/* 51 */     if (hints.containsKey(PNGTranscoder.KEY_INDEXED)) {
/* 52 */       n = ((Integer)hints.get(PNGTranscoder.KEY_INDEXED)).intValue();
/* 53 */       if (n == 1 || n == 2 || n == 4 || n == 8)
/*    */       {
/* 55 */         img = IndexImage.getIndexedImage(img, 1 << n);
/*    */       }
/*    */     } 
/* 58 */     PNGEncodeParam params = PNGEncodeParam.getDefaultEncodeParam(img);
/* 59 */     if (params instanceof PNGEncodeParam.RGB) {
/* 60 */       ((PNGEncodeParam.RGB)params).setBackgroundRGB(new int[] { 255, 255, 255 });
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 68 */     if (hints.containsKey(PNGTranscoder.KEY_GAMMA)) {
/* 69 */       float gamma = ((Float)hints.get(PNGTranscoder.KEY_GAMMA)).floatValue();
/* 70 */       if (gamma > 0.0F) {
/* 71 */         params.setGamma(gamma);
/*    */       }
/* 73 */       params.setChromaticity(PNGTranscoder.DEFAULT_CHROMA);
/*    */     }
/*    */     else {
/*    */       
/* 77 */       params.setSRGBIntent(0);
/*    */     } 
/*    */ 
/*    */     
/* 81 */     float PixSzMM = transcoder.getUserAgent().getPixelUnitToMillimeter();
/*    */     
/* 83 */     int numPix = (int)((1000.0F / PixSzMM) + 0.5D);
/* 84 */     params.setPhysicalDimension(numPix, numPix, 1);
/*    */     
/*    */     try {
/* 87 */       OutputStream ostream = output.getOutputStream();
/* 88 */       PNGImageEncoder pngEncoder = new PNGImageEncoder(ostream, params);
/* 89 */       pngEncoder.encode(img);
/* 90 */       ostream.flush();
/* 91 */     } catch (IOException ex) {
/* 92 */       throw new TranscoderException(ex);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/png/PNGTranscoderInternalCodecWriteAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */