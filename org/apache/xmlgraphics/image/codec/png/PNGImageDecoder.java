/*     */ package org.apache.xmlgraphics.image.codec.png;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import org.apache.xmlgraphics.image.codec.util.ImageDecoderImpl;
/*     */ import org.apache.xmlgraphics.image.codec.util.ImageInputStreamSeekableStreamAdapter;
/*     */ import org.apache.xmlgraphics.image.codec.util.PropertyUtil;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSize;
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
/*     */ 
/*     */ 
/*     */ public class PNGImageDecoder
/*     */   extends ImageDecoderImpl
/*     */ {
/*     */   public PNGImageDecoder(InputStream input, PNGDecodeParam param) {
/*  78 */     super(input, param);
/*     */   }
/*     */ 
/*     */   
/*     */   public RenderedImage decodeAsRenderedImage(int page) throws IOException {
/*  83 */     if (page != 0) {
/*  84 */       throw new IOException(PropertyUtil.getString("PNGImageDecoder19"));
/*     */     }
/*  86 */     return (RenderedImage)new PNGImage((InputStream)this.input, (PNGDecodeParam)this.param);
/*     */   }
/*     */   
/*     */   public static void readPNGHeader(ImageInputStream inputStream, ImageSize size) throws IOException {
/*  90 */     ImageInputStreamSeekableStreamAdapter imageInputStreamSeekableStreamAdapter = new ImageInputStreamSeekableStreamAdapter(inputStream)
/*     */       {
/*     */         public void close() throws IOException {}
/*     */       };
/*  94 */     PNGImage pngImage = new PNGImage((InputStream)imageInputStreamSeekableStreamAdapter);
/*  95 */     size.setSizeInPixels(pngImage.getWidth(), pngImage.getHeight());
/*  96 */     double dpiHorz = size.getDpiHorizontal();
/*  97 */     double dpiVert = size.getDpiVertical();
/*  98 */     if (pngImage.unitSpecifier == 1) {
/*  99 */       if (pngImage.xPixelsPerUnit != 0) {
/* 100 */         dpiHorz = pngImage.xPixelsPerUnit * 0.0254D;
/*     */       }
/* 102 */       if (pngImage.yPixelsPerUnit != 0) {
/* 103 */         dpiVert = pngImage.yPixelsPerUnit * 0.0254D;
/*     */       }
/*     */     } 
/* 106 */     size.setResolution(dpiHorz, dpiVert);
/* 107 */     size.calcSizeFromPixels();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/codec/png/PNGImageDecoder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */