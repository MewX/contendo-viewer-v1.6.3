/*    */ package org.apache.xmlgraphics.image.loader.impl;
/*    */ 
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.util.Map;
/*    */ import javax.imageio.stream.ImageInputStream;
/*    */ import javax.xml.transform.Source;
/*    */ import org.apache.xmlgraphics.image.codec.png.PNGDecodeParam;
/*    */ import org.apache.xmlgraphics.image.codec.png.PNGImageDecoder;
/*    */ import org.apache.xmlgraphics.image.codec.util.ImageInputStreamSeekableStreamAdapter;
/*    */ import org.apache.xmlgraphics.image.loader.Image;
/*    */ import org.apache.xmlgraphics.image.loader.ImageException;
/*    */ import org.apache.xmlgraphics.image.loader.ImageFlavor;
/*    */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*    */ import org.apache.xmlgraphics.image.loader.ImageSessionContext;
/*    */ import org.apache.xmlgraphics.image.loader.util.ImageUtil;
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
/*    */ public class ImageLoaderPNG
/*    */   extends AbstractImageLoader
/*    */ {
/*    */   public Image loadImage(ImageInfo info, Map hints, ImageSessionContext session) throws ImageException, IOException {
/* 50 */     Source src = session.needSource(info.getOriginalURI());
/* 51 */     ImageInputStream imgStream = ImageUtil.needImageInputStream(src);
/*    */     
/* 53 */     ImageInputStreamSeekableStreamAdapter imageInputStreamSeekableStreamAdapter = new ImageInputStreamSeekableStreamAdapter(imgStream);
/*    */     
/* 55 */     PNGImageDecoder decoder = new PNGImageDecoder((InputStream)imageInputStreamSeekableStreamAdapter, new PNGDecodeParam());
/* 56 */     RenderedImage image = decoder.decodeAsRenderedImage();
/*    */ 
/*    */     
/* 59 */     return new ImageRendered(info, image, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public ImageFlavor getTargetFlavor() {
/* 64 */     return ImageFlavor.RENDERED_IMAGE;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getUsagePenalty() {
/* 72 */     return 1000;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/ImageLoaderPNG.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */