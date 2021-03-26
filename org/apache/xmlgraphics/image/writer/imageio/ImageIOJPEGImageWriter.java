/*     */ package org.apache.xmlgraphics.image.writer.imageio;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import javax.imageio.ImageWriteParam;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.metadata.IIOInvalidTreeException;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.metadata.IIOMetadataNode;
/*     */ import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
/*     */ import org.apache.xmlgraphics.image.writer.ImageWriterParams;
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
/*     */ 
/*     */ public class ImageIOJPEGImageWriter
/*     */   extends ImageIOImageWriter
/*     */ {
/*     */   private static final String JPEG_NATIVE_FORMAT = "javax_imageio_jpeg_image_1.0";
/*     */   
/*     */   public ImageIOJPEGImageWriter() {
/*  47 */     super("image/jpeg");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected IIOMetadata updateMetadata(RenderedImage image, IIOMetadata meta, ImageWriterParams params) {
/*  53 */     if ("javax_imageio_jpeg_image_1.0".equals(meta.getNativeMetadataFormatName())) {
/*  54 */       meta = addAdobeTransform(meta);
/*  55 */       IIOMetadataNode root = (IIOMetadataNode)meta.getAsTree("javax_imageio_jpeg_image_1.0");
/*  56 */       IIOMetadataNode jv = getChildNode(root, "JPEGvariety");
/*  57 */       if (jv == null) {
/*  58 */         jv = new IIOMetadataNode("JPEGvariety");
/*  59 */         root.appendChild(jv);
/*     */       } 
/*     */       
/*  62 */       if (params.getResolution() != null) {
/*  63 */         IIOMetadataNode child = getChildNode(jv, "app0JFIF");
/*  64 */         if (child == null) {
/*  65 */           child = new IIOMetadataNode("app0JFIF");
/*  66 */           jv.appendChild(child);
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/*  71 */         child.setAttribute("majorVersion", null);
/*  72 */         child.setAttribute("minorVersion", null);
/*  73 */         switch (params.getResolutionUnit()) {
/*     */           case INCH:
/*  75 */             child.setAttribute("resUnits", "1");
/*     */             break;
/*     */           case CENTIMETER:
/*  78 */             child.setAttribute("resUnits", "2");
/*     */             break;
/*     */           default:
/*  81 */             child.setAttribute("resUnits", "0"); break;
/*     */         } 
/*  83 */         child.setAttribute("Xdensity", params.getXResolution().toString());
/*  84 */         child.setAttribute("Ydensity", params.getYResolution().toString());
/*  85 */         child.setAttribute("thumbWidth", null);
/*  86 */         child.setAttribute("thumbHeight", null);
/*     */       } 
/*     */       try {
/*  89 */         meta.setFromTree("javax_imageio_jpeg_image_1.0", root);
/*     */       }
/*  91 */       catch (IIOInvalidTreeException e) {
/*  92 */         throw new RuntimeException("Cannot update image metadata: " + e.getMessage(), e);
/*     */       } 
/*     */     } 
/*     */     
/*  96 */     return meta;
/*     */   }
/*     */ 
/*     */   
/*     */   private static IIOMetadata addAdobeTransform(IIOMetadata meta) {
/* 101 */     IIOMetadataNode root = (IIOMetadataNode)meta.getAsTree("javax_imageio_jpeg_image_1.0");
/*     */     
/* 103 */     IIOMetadataNode markerSequence = getChildNode(root, "markerSequence");
/* 104 */     if (markerSequence == null) {
/* 105 */       throw new RuntimeException("Invalid metadata!");
/*     */     }
/*     */     
/* 108 */     IIOMetadataNode adobeTransform = getChildNode(markerSequence, "app14Adobe");
/* 109 */     if (adobeTransform == null) {
/* 110 */       adobeTransform = new IIOMetadataNode("app14Adobe");
/* 111 */       adobeTransform.setAttribute("transform", "1");
/* 112 */       adobeTransform.setAttribute("version", "101");
/* 113 */       adobeTransform.setAttribute("flags0", "0");
/* 114 */       adobeTransform.setAttribute("flags1", "0");
/*     */       
/* 116 */       markerSequence.appendChild(adobeTransform);
/*     */     } else {
/* 118 */       adobeTransform.setAttribute("transform", "1");
/*     */     } 
/*     */     
/*     */     try {
/* 122 */       meta.setFromTree("javax_imageio_jpeg_image_1.0", root);
/* 123 */     } catch (IIOInvalidTreeException e) {
/* 124 */       throw new RuntimeException("Cannot update image metadata: " + e.getMessage(), e);
/*     */     } 
/*     */     
/* 127 */     return meta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ImageWriteParam getDefaultWriteParam(ImageWriter iiowriter, RenderedImage image, ImageWriterParams params) {
/* 135 */     JPEGImageWriteParam param = new JPEGImageWriteParam(iiowriter.getLocale());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 147 */     return param;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/writer/imageio/ImageIOJPEGImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */