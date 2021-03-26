/*     */ package org.apache.batik.ext.awt.image.codec.imageio;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import javax.imageio.ImageWriteParam;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.metadata.IIOInvalidTreeException;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.metadata.IIOMetadataNode;
/*     */ import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriterParams;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageIOJPEGImageWriter
/*     */   extends ImageIOImageWriter
/*     */ {
/*     */   private static final String JPEG_NATIVE_FORMAT = "javax_imageio_jpeg_image_1.0";
/*     */   
/*     */   public ImageIOJPEGImageWriter() {
/*  45 */     super("image/jpeg");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected IIOMetadata updateMetadata(IIOMetadata meta, ImageWriterParams params) {
/*  52 */     if ("javax_imageio_jpeg_image_1.0".equals(meta.getNativeMetadataFormatName())) {
/*  53 */       meta = addAdobeTransform(meta);
/*     */       
/*  55 */       IIOMetadataNode root = (IIOMetadataNode)meta.getAsTree("javax_imageio_jpeg_image_1.0");
/*     */       
/*  57 */       IIOMetadataNode jv = getChildNode(root, "JPEGvariety");
/*  58 */       if (jv == null) {
/*  59 */         jv = new IIOMetadataNode("JPEGvariety");
/*  60 */         root.appendChild(jv);
/*     */       } 
/*     */       
/*  63 */       if (params.getResolution() != null) {
/*  64 */         IIOMetadataNode child = getChildNode(jv, "app0JFIF");
/*  65 */         if (child == null) {
/*  66 */           child = new IIOMetadataNode("app0JFIF");
/*  67 */           jv.appendChild(child);
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/*  72 */         child.setAttribute("majorVersion", null);
/*  73 */         child.setAttribute("minorVersion", null);
/*  74 */         child.setAttribute("resUnits", "1");
/*  75 */         child.setAttribute("Xdensity", params.getResolution().toString());
/*  76 */         child.setAttribute("Ydensity", params.getResolution().toString());
/*  77 */         child.setAttribute("thumbWidth", null);
/*  78 */         child.setAttribute("thumbHeight", null);
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/*  83 */         meta.setFromTree("javax_imageio_jpeg_image_1.0", root);
/*  84 */       } catch (IIOInvalidTreeException e) {
/*  85 */         throw new RuntimeException("Cannot update image metadata: " + e.getMessage(), e);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  92 */     return meta;
/*     */   }
/*     */ 
/*     */   
/*     */   private static IIOMetadata addAdobeTransform(IIOMetadata meta) {
/*  97 */     IIOMetadataNode root = (IIOMetadataNode)meta.getAsTree("javax_imageio_jpeg_image_1.0");
/*     */     
/*  99 */     IIOMetadataNode markerSequence = getChildNode(root, "markerSequence");
/* 100 */     if (markerSequence == null) {
/* 101 */       throw new RuntimeException("Invalid metadata!");
/*     */     }
/*     */     
/* 104 */     IIOMetadataNode adobeTransform = getChildNode(markerSequence, "app14Adobe");
/* 105 */     if (adobeTransform == null) {
/* 106 */       adobeTransform = new IIOMetadataNode("app14Adobe");
/* 107 */       adobeTransform.setAttribute("transform", "1");
/* 108 */       adobeTransform.setAttribute("version", "101");
/* 109 */       adobeTransform.setAttribute("flags0", "0");
/* 110 */       adobeTransform.setAttribute("flags1", "0");
/*     */       
/* 112 */       markerSequence.appendChild(adobeTransform);
/*     */     } else {
/* 114 */       adobeTransform.setAttribute("transform", "1");
/*     */     } 
/*     */     
/*     */     try {
/* 118 */       meta.setFromTree("javax_imageio_jpeg_image_1.0", root);
/* 119 */     } catch (IIOInvalidTreeException e) {
/* 120 */       throw new RuntimeException("Cannot update image metadata: " + e.getMessage(), e);
/*     */     } 
/*     */     
/* 123 */     return meta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ImageWriteParam getDefaultWriteParam(ImageWriter iiowriter, RenderedImage image, ImageWriterParams params) {
/* 131 */     JPEGImageWriteParam param = new JPEGImageWriteParam(iiowriter.getLocale());
/* 132 */     param.setCompressionMode(2);
/* 133 */     param.setCompressionQuality(params.getJPEGQuality().floatValue());
/* 134 */     if (params.getCompressionMethod() != null && !"JPEG".equals(params.getCompressionMethod()))
/*     */     {
/* 136 */       throw new IllegalArgumentException("No compression method other than JPEG is supported for JPEG output!");
/*     */     }
/*     */     
/* 139 */     if (params.getJPEGForceBaseline().booleanValue()) {
/* 140 */       param.setProgressiveMode(0);
/*     */     }
/* 142 */     return param;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/imageio/ImageIOJPEGImageWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */