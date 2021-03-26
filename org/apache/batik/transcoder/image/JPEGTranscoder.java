/*     */ package org.apache.batik.transcoder.image;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriter;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriterParams;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriterRegistry;
/*     */ import org.apache.batik.transcoder.TranscoderException;
/*     */ import org.apache.batik.transcoder.TranscoderOutput;
/*     */ import org.apache.batik.transcoder.TranscodingHints;
/*     */ import org.apache.batik.transcoder.image.resources.Messages;
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
/*     */ public class JPEGTranscoder
/*     */   extends ImageTranscoder
/*     */ {
/*     */   public JPEGTranscoder() {
/*  46 */     this.hints.put(ImageTranscoder.KEY_BACKGROUND_COLOR, Color.white);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage createImage(int width, int height) {
/*  55 */     return new BufferedImage(width, height, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeImage(BufferedImage img, TranscoderOutput output) throws TranscoderException {
/*  66 */     OutputStream ostream = output.getOutputStream();
/*     */ 
/*     */ 
/*     */     
/*  70 */     ostream = new OutputStreamWrapper(ostream);
/*     */     
/*     */     try {
/*     */       float quality;
/*  74 */       if (this.hints.containsKey(KEY_QUALITY)) {
/*  75 */         quality = ((Float)this.hints.get(KEY_QUALITY)).floatValue();
/*     */       } else {
/*     */         
/*  78 */         TranscoderException te = new TranscoderException(Messages.formatMessage("jpeg.unspecifiedQuality", null));
/*     */         
/*  80 */         this.handler.error(te);
/*  81 */         quality = 0.75F;
/*     */       } 
/*     */       
/*  84 */       ImageWriter writer = ImageWriterRegistry.getInstance().getWriterFor("image/jpeg");
/*     */       
/*  86 */       ImageWriterParams params = new ImageWriterParams();
/*  87 */       params.setJPEGQuality(quality, true);
/*  88 */       float PixSzMM = this.userAgent.getPixelUnitToMillimeter();
/*  89 */       int PixSzInch = (int)(25.4D / PixSzMM + 0.5D);
/*  90 */       params.setResolution(PixSzInch);
/*  91 */       writer.writeImage(img, ostream, params);
/*  92 */       ostream.flush();
/*  93 */     } catch (IOException ex) {
/*  94 */       throw new TranscoderException(ex);
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
/* 127 */   public static final TranscodingHints.Key KEY_QUALITY = new QualityKey();
/*     */   
/*     */   private static class QualityKey
/*     */     extends TranscodingHints.Key
/*     */   {
/*     */     private QualityKey() {}
/*     */     
/*     */     public boolean isCompatibleValue(Object v) {
/* 135 */       if (v instanceof Float) {
/* 136 */         float q = ((Float)v).floatValue();
/* 137 */         return (q > 0.0F && q <= 1.0F);
/*     */       } 
/* 139 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class OutputStreamWrapper
/*     */     extends OutputStream
/*     */   {
/*     */     OutputStream os;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     OutputStreamWrapper(OutputStream os) {
/* 156 */       this.os = os;
/*     */     }
/*     */     
/*     */     public void close() throws IOException {
/* 160 */       if (this.os == null)
/*     */         return;  try {
/* 162 */         this.os.close();
/* 163 */       } catch (IOException ioe) {
/* 164 */         this.os = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void flush() throws IOException {
/* 169 */       if (this.os == null)
/*     */         return;  try {
/* 171 */         this.os.flush();
/* 172 */       } catch (IOException ioe) {
/* 173 */         this.os = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void write(byte[] b) throws IOException {
/* 178 */       if (this.os == null)
/*     */         return;  try {
/* 180 */         this.os.write(b);
/* 181 */       } catch (IOException ioe) {
/* 182 */         this.os = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void write(byte[] b, int off, int len) throws IOException {
/* 187 */       if (this.os == null)
/*     */         return;  try {
/* 189 */         this.os.write(b, off, len);
/* 190 */       } catch (IOException ioe) {
/* 191 */         this.os = null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public void write(int b) throws IOException {
/* 196 */       if (this.os == null)
/*     */         return;  try {
/* 198 */         this.os.write(b);
/* 199 */       } catch (IOException ioe) {
/* 200 */         this.os = null;
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/image/JPEGTranscoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */