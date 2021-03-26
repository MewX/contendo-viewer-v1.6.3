/*     */ package org.apache.batik.ext.awt.image.codec.imageio;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Iterator;
/*     */ import javax.imageio.IIOImage;
/*     */ import javax.imageio.ImageIO;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.ImageWriteParam;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.event.IIOWriteWarningListener;
/*     */ import javax.imageio.metadata.IIOInvalidTreeException;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.metadata.IIOMetadataNode;
/*     */ import javax.imageio.stream.ImageOutputStream;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriter;
/*     */ import org.apache.batik.ext.awt.image.spi.ImageWriterParams;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public class ImageIOImageWriter
/*     */   implements IIOWriteWarningListener, ImageWriter
/*     */ {
/*     */   private String targetMIME;
/*     */   
/*     */   public ImageIOImageWriter(String mime) {
/*  56 */     this.targetMIME = mime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeImage(RenderedImage image, OutputStream out) throws IOException {
/*  63 */     writeImage(image, out, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeImage(RenderedImage image, OutputStream out, ImageWriterParams params) throws IOException {
/*  73 */     Iterator<ImageWriter> iter = ImageIO.getImageWritersByMIMEType(getMIMEType());
/*  74 */     ImageWriter iiowriter = null;
/*     */     try {
/*  76 */       iiowriter = iter.next();
/*  77 */       if (iiowriter != null) {
/*  78 */         iiowriter.addIIOWriteWarningListener(this);
/*     */         
/*  80 */         ImageOutputStream imgout = null; try {
/*     */           ImageTypeSpecifier type;
/*  82 */           imgout = ImageIO.createImageOutputStream(out);
/*  83 */           ImageWriteParam iwParam = getDefaultWriteParam(iiowriter, image, params);
/*     */ 
/*     */           
/*  86 */           if (iwParam.getDestinationType() != null) {
/*  87 */             type = iwParam.getDestinationType();
/*     */           } else {
/*  89 */             type = ImageTypeSpecifier.createFromRenderedImage(image);
/*     */           } 
/*     */ 
/*     */           
/*  93 */           IIOMetadata meta = iiowriter.getDefaultImageMetadata(type, iwParam);
/*     */ 
/*     */           
/*  96 */           if (params != null && meta != null) {
/*  97 */             meta = updateMetadata(meta, params);
/*     */           }
/*     */ 
/*     */           
/* 101 */           iiowriter.setOutput(imgout);
/* 102 */           IIOImage iioimg = new IIOImage(image, null, meta);
/* 103 */           iiowriter.write(null, iioimg, iwParam);
/*     */         } finally {
/* 105 */           if (imgout != null) {
/* 106 */             imgout.close();
/*     */           }
/*     */         } 
/*     */       } else {
/* 110 */         throw new UnsupportedOperationException("No ImageIO codec for writing " + getMIMEType() + " is available!");
/*     */       } 
/*     */     } finally {
/*     */       
/* 114 */       if (iiowriter != null) {
/* 115 */         iiowriter.dispose();
/*     */       }
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
/*     */   protected ImageWriteParam getDefaultWriteParam(ImageWriter iiowriter, RenderedImage image, ImageWriterParams params) {
/* 130 */     ImageWriteParam param = iiowriter.getDefaultWriteParam();
/* 131 */     if (params != null && params.getCompressionMethod() != null) {
/* 132 */       param.setCompressionMode(2);
/* 133 */       param.setCompressionType(params.getCompressionMethod());
/*     */     } 
/* 135 */     return param;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected IIOMetadata updateMetadata(IIOMetadata meta, ImageWriterParams params) {
/* 145 */     String stdmeta = "javax_imageio_1.0";
/* 146 */     if (meta.isStandardMetadataFormatSupported()) {
/* 147 */       IIOMetadataNode root = (IIOMetadataNode)meta.getAsTree("javax_imageio_1.0");
/* 148 */       IIOMetadataNode dim = getChildNode(root, "Dimension");
/*     */       
/* 150 */       if (params.getResolution() != null) {
/* 151 */         IIOMetadataNode child = getChildNode(dim, "HorizontalPixelSize");
/* 152 */         if (child == null) {
/* 153 */           child = new IIOMetadataNode("HorizontalPixelSize");
/* 154 */           dim.appendChild(child);
/*     */         } 
/* 156 */         child.setAttribute("value", Double.toString(params.getResolution().doubleValue() / 25.4D));
/*     */         
/* 158 */         child = getChildNode(dim, "VerticalPixelSize");
/* 159 */         if (child == null) {
/* 160 */           child = new IIOMetadataNode("VerticalPixelSize");
/* 161 */           dim.appendChild(child);
/*     */         } 
/* 163 */         child.setAttribute("value", Double.toString(params.getResolution().doubleValue() / 25.4D));
/*     */       } 
/*     */       
/*     */       try {
/* 167 */         meta.mergeTree("javax_imageio_1.0", root);
/* 168 */       } catch (IIOInvalidTreeException e) {
/* 169 */         throw new RuntimeException("Cannot update image metadata: " + e.getMessage());
/*     */       } 
/*     */     } 
/*     */     
/* 173 */     return meta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static IIOMetadataNode getChildNode(Node n, String name) {
/* 183 */     NodeList nodes = n.getChildNodes();
/* 184 */     for (int i = 0; i < nodes.getLength(); i++) {
/* 185 */       Node child = nodes.item(i);
/* 186 */       if (name.equals(child.getNodeName())) {
/* 187 */         return (IIOMetadataNode)child;
/*     */       }
/*     */     } 
/* 190 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMIMEType() {
/* 197 */     return this.targetMIME;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void warningOccurred(ImageWriter source, int imageIndex, String warning) {
/* 205 */     System.err.println("Problem while writing image using ImageI/O: " + warning);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/imageio/ImageIOImageWriter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */