/*     */ package org.apache.pdfbox.filter;
/*     */ 
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.DataBufferUShort;
/*     */ import java.awt.image.Raster;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import javax.imageio.ImageReadParam;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.imageio.stream.MemoryCacheImageInputStream;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDJPXColorSpace;
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
/*     */ public final class JPXFilter
/*     */   extends Filter
/*     */ {
/*     */   public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index, DecodeOptions options) throws IOException {
/*     */     DataBufferByte byteBuffer;
/*     */     DataBufferUShort wordBuffer;
/*     */     int ar[], y;
/*  62 */     DecodeResult result = new DecodeResult(new COSDictionary());
/*  63 */     result.getParameters().addAll(parameters);
/*  64 */     BufferedImage image = readJPX(encoded, options, result);
/*     */     
/*  66 */     Raster raster = image.getRaster();
/*  67 */     switch (raster.getDataBuffer().getDataType()) {
/*     */       
/*     */       case 0:
/*  70 */         byteBuffer = (DataBufferByte)raster.getDataBuffer();
/*  71 */         decoded.write(byteBuffer.getData());
/*  72 */         return result;
/*     */       
/*     */       case 1:
/*  75 */         wordBuffer = (DataBufferUShort)raster.getDataBuffer();
/*  76 */         for (short w : wordBuffer.getData()) {
/*     */           
/*  78 */           decoded.write(w >> 8);
/*  79 */           decoded.write(w);
/*     */         } 
/*  81 */         return result;
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/*  86 */         ar = new int[raster.getNumBands()];
/*  87 */         for (y = 0; y < image.getHeight(); y++) {
/*     */           
/*  89 */           for (int x = 0; x < image.getWidth(); x++) {
/*     */             
/*  91 */             raster.getPixel(x, y, ar);
/*  92 */             for (int i = 0; i < ar.length; i++)
/*     */             {
/*  94 */               decoded.write(ar[i]);
/*     */             }
/*     */           } 
/*     */         } 
/*  98 */         return result;
/*     */     } 
/*     */     
/* 101 */     throw new IOException("Data type " + raster.getDataBuffer().getDataType() + " not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index) throws IOException {
/* 109 */     return decode(encoded, decoded, parameters, index, DecodeOptions.DEFAULT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage readJPX(InputStream input, DecodeOptions options, DecodeResult result) throws IOException {
/* 115 */     ImageReader reader = findImageReader("JPEG2000", "Java Advanced Imaging (JAI) Image I/O Tools are not installed");
/* 116 */     ImageInputStream iis = null;
/*     */     
/*     */     try {
/*     */       BufferedImage image;
/* 120 */       iis = new MemoryCacheImageInputStream(input);
/*     */       
/* 122 */       reader.setInput(iis, true, true);
/* 123 */       ImageReadParam irp = reader.getDefaultReadParam();
/* 124 */       irp.setSourceRegion(options.getSourceRegion());
/* 125 */       irp.setSourceSubsampling(options.getSubsamplingX(), options.getSubsamplingY(), options
/* 126 */           .getSubsamplingOffsetX(), options.getSubsamplingOffsetY());
/* 127 */       options.setFilterSubsampled(true);
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 132 */         image = reader.read(0, irp);
/*     */       }
/* 134 */       catch (Exception e) {
/*     */ 
/*     */         
/* 137 */         throw new IOException("Could not read JPEG 2000 (JPX) image", e);
/*     */       } 
/*     */       
/* 140 */       COSDictionary parameters = result.getParameters();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 146 */       int bpc = image.getColorModel().getPixelSize() / image.getRaster().getNumBands();
/* 147 */       parameters.setInt(COSName.BITS_PER_COMPONENT, bpc);
/*     */ 
/*     */       
/* 150 */       if (!parameters.getBoolean(COSName.IMAGE_MASK, false))
/*     */       {
/* 152 */         parameters.setItem(COSName.DECODE, null);
/*     */       }
/*     */ 
/*     */       
/* 156 */       parameters.setInt(COSName.WIDTH, reader.getWidth(0));
/* 157 */       parameters.setInt(COSName.HEIGHT, reader.getHeight(0));
/*     */ 
/*     */       
/* 160 */       if (!parameters.containsKey(COSName.COLORSPACE))
/*     */       {
/* 162 */         if (image.getSampleModel() instanceof java.awt.image.MultiPixelPackedSampleModel && image
/* 163 */           .getColorModel().getPixelSize() == 1 && image
/* 164 */           .getRaster().getNumBands() == 1 && image
/* 165 */           .getColorModel() instanceof java.awt.image.IndexColorModel) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 171 */           result.setColorSpace(new PDJPXColorSpace(ColorSpace.getInstance(1003)));
/*     */         }
/*     */         else {
/*     */           
/* 175 */           result.setColorSpace(new PDJPXColorSpace(image.getColorModel().getColorSpace()));
/*     */         } 
/*     */       }
/*     */       
/* 179 */       return image;
/*     */     }
/*     */     finally {
/*     */       
/* 183 */       if (iis != null)
/*     */       {
/* 185 */         iis.close();
/*     */       }
/* 187 */       reader.dispose();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void encode(InputStream input, OutputStream encoded, COSDictionary parameters) throws IOException {
/* 198 */     throw new UnsupportedOperationException("JPX encoding not implemented");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/JPXFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */