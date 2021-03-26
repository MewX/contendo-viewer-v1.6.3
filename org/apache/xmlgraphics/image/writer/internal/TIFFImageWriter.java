/*     */ package org.apache.xmlgraphics.image.writer.internal;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import org.apache.xmlgraphics.image.codec.tiff.CompressionValue;
/*     */ import org.apache.xmlgraphics.image.codec.tiff.TIFFEncodeParam;
/*     */ import org.apache.xmlgraphics.image.codec.tiff.TIFFField;
/*     */ import org.apache.xmlgraphics.image.codec.tiff.TIFFImageEncoder;
/*     */ import org.apache.xmlgraphics.image.codec.util.ImageEncodeParam;
/*     */ import org.apache.xmlgraphics.image.writer.AbstractImageWriter;
/*     */ import org.apache.xmlgraphics.image.writer.ImageWriterParams;
/*     */ import org.apache.xmlgraphics.image.writer.MultiImageWriter;
/*     */ import org.apache.xmlgraphics.image.writer.ResolutionUnit;
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
/*     */ public class TIFFImageWriter
/*     */   extends AbstractImageWriter
/*     */ {
/*     */   public void writeImage(RenderedImage image, OutputStream out) throws IOException {
/*  47 */     writeImage(image, out, null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeImage(RenderedImage image, OutputStream out, ImageWriterParams params) throws IOException {
/*  53 */     TIFFEncodeParam encodeParams = createTIFFEncodeParams(params);
/*  54 */     TIFFImageEncoder encoder = new TIFFImageEncoder(out, (ImageEncodeParam)encodeParams);
/*  55 */     encoder.encode(image);
/*     */   }
/*     */   
/*     */   private TIFFEncodeParam createTIFFEncodeParams(ImageWriterParams params) {
/*  59 */     TIFFEncodeParam encodeParams = new TIFFEncodeParam();
/*  60 */     if (params == null) {
/*  61 */       encodeParams.setCompression(CompressionValue.NONE);
/*     */     } else {
/*  63 */       encodeParams.setCompression(CompressionValue.getValue(params.getCompressionMethod()));
/*     */       
/*  65 */       if (params.getResolution() != null) {
/*     */         int numPixX, numPixY, denom;
/*     */ 
/*     */ 
/*     */         
/*  70 */         if (ResolutionUnit.INCH == params.getResolutionUnit()) {
/*  71 */           numPixX = params.getXResolution().intValue();
/*  72 */           numPixY = params.getYResolution().intValue();
/*  73 */           denom = 1;
/*     */         } else {
/*     */           
/*  76 */           float pixXSzMM = 25.4F / params.getXResolution().floatValue();
/*  77 */           float pixYSzMM = 25.4F / params.getYResolution().floatValue();
/*     */           
/*  79 */           numPixX = (int)((100000.0F / pixXSzMM) + 0.5D);
/*  80 */           numPixY = (int)((100000.0F / pixYSzMM) + 0.5D);
/*  81 */           denom = 10000;
/*     */         } 
/*     */         
/*  84 */         long[] xRational = { numPixX, denom };
/*  85 */         long[] yRational = { numPixY, denom };
/*  86 */         TIFFField[] fields = { new TIFFField(296, 3, 1, new char[] { (char)params.getResolutionUnit().getValue() }), new TIFFField(282, 5, 1, new long[][] { xRational }), new TIFFField(283, 5, 1, new long[][] { yRational }) };
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
/*  97 */         encodeParams.setExtraFields(fields);
/*     */       } 
/*     */     } 
/* 100 */     return encodeParams;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMIMEType() {
/* 105 */     return "image/tiff";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MultiImageWriter createMultiImageWriter(OutputStream out) throws IOException {
/* 111 */     return new TIFFMultiImageWriter(out);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean supportsMultiImageWriter() {
/* 117 */     return true;
/*     */   }
/*     */   
/*     */   private class TIFFMultiImageWriter
/*     */     implements MultiImageWriter {
/*     */     private OutputStream out;
/*     */     private TIFFEncodeParam encodeParams;
/*     */     private TIFFImageEncoder encoder;
/*     */     private Object context;
/*     */     
/*     */     public TIFFMultiImageWriter(OutputStream out) throws IOException {
/* 128 */       this.out = out;
/*     */     }
/*     */     
/*     */     public void writeImage(RenderedImage image, ImageWriterParams params) throws IOException {
/* 132 */       if (this.encoder == null) {
/* 133 */         this.encodeParams = TIFFImageWriter.this.createTIFFEncodeParams(params);
/* 134 */         this.encoder = new TIFFImageEncoder(this.out, (ImageEncodeParam)this.encodeParams);
/*     */       } 
/* 136 */       this.context = this.encoder.encodeMultiple(this.context, image);
/*     */     }
/*     */     
/*     */     public void close() throws IOException {
/* 140 */       if (this.encoder != null) {
/* 141 */         this.encoder.finishMultiple(this.context);
/*     */       }
/* 143 */       this.encoder = null;
/* 144 */       this.encodeParams = null;
/* 145 */       this.out.flush();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/writer/internal/TIFFImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */