/*     */ package org.apache.xmlgraphics.image.writer.imageio;
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
/*     */ import org.apache.xmlgraphics.image.writer.ImageWriter;
/*     */ import org.apache.xmlgraphics.image.writer.ImageWriterParams;
/*     */ import org.apache.xmlgraphics.image.writer.MultiImageWriter;
/*     */ import org.apache.xmlgraphics.image.writer.ResolutionUnit;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageIOImageWriter
/*     */   implements IIOWriteWarningListener, ImageWriter
/*     */ {
/*     */   private static final String DIMENSION = "Dimension";
/*     */   private static final String VERTICAL_PIXEL_SIZE = "VerticalPixelSize";
/*     */   private static final String HORIZONTAL_PIXEL_SIZE = "HorizontalPixelSize";
/*     */   private static final String STANDARD_METADATA_FORMAT = "javax_imageio_1.0";
/*     */   private String targetMIME;
/*     */   
/*     */   public ImageIOImageWriter(String mime) {
/*  66 */     this.targetMIME = mime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void writeImage(RenderedImage image, OutputStream out) throws IOException {
/*  71 */     writeImage(image, out, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeImage(RenderedImage image, OutputStream out, ImageWriterParams params) throws IOException {
/*  78 */     ImageWriter iiowriter = getIIOImageWriter();
/*  79 */     iiowriter.addIIOWriteWarningListener(this);
/*     */     
/*  81 */     ImageOutputStream imgout = ImageIO.createImageOutputStream(out);
/*     */     try {
/*     */       ImageTypeSpecifier type;
/*  84 */       ImageWriteParam iwParam = getDefaultWriteParam(iiowriter, image, params);
/*     */       
/*  86 */       IIOMetadata streamMetadata = createStreamMetadata(iiowriter, iwParam, params);
/*     */ 
/*     */       
/*  89 */       if (iwParam.getDestinationType() != null) {
/*  90 */         type = iwParam.getDestinationType();
/*     */       } else {
/*  92 */         type = ImageTypeSpecifier.createFromRenderedImage(image);
/*     */       } 
/*     */ 
/*     */       
/*  96 */       IIOMetadata meta = iiowriter.getDefaultImageMetadata(type, iwParam);
/*     */ 
/*     */       
/*  99 */       if (params != null && meta != null) {
/* 100 */         meta = updateMetadata(image, meta, params);
/*     */       }
/*     */ 
/*     */       
/* 104 */       iiowriter.setOutput(imgout);
/* 105 */       IIOImage iioimg = new IIOImage(image, null, meta);
/* 106 */       iiowriter.write(streamMetadata, iioimg, iwParam);
/*     */     } finally {
/*     */       
/* 109 */       imgout.close();
/* 110 */       iiowriter.dispose();
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
/*     */   protected IIOMetadata createStreamMetadata(ImageWriter writer, ImageWriteParam writeParam, ImageWriterParams params) {
/* 125 */     return null;
/*     */   }
/*     */   
/*     */   private ImageWriter getIIOImageWriter() {
/* 129 */     Iterator<ImageWriter> iter = ImageIO.getImageWritersByMIMEType(getMIMEType());
/* 130 */     ImageWriter iiowriter = null;
/* 131 */     if (iter.hasNext()) {
/* 132 */       iiowriter = iter.next();
/*     */     }
/* 134 */     if (iiowriter == null) {
/* 135 */       throw new UnsupportedOperationException("No ImageIO codec for writing " + getMIMEType() + " is available!");
/*     */     }
/*     */     
/* 138 */     return iiowriter;
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
/* 151 */     ImageWriteParam param = iiowriter.getDefaultWriteParam();
/*     */     
/* 153 */     if (params != null && params.getCompressionMethod() != null) {
/* 154 */       param.setCompressionMode(2);
/* 155 */       param.setCompressionType(params.getCompressionMethod());
/*     */     } 
/* 157 */     return param;
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
/*     */   protected IIOMetadata updateMetadata(RenderedImage image, IIOMetadata meta, ImageWriterParams params) {
/* 169 */     if (meta.isStandardMetadataFormatSupported() && params.getResolution() != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 174 */       float multiplier = (ResolutionUnit.CENTIMETER == params.getResolutionUnit()) ? 10.0F : 25.4F;
/* 175 */       double pixelWidthInMillimeters = multiplier / params.getXResolution().doubleValue();
/* 176 */       double pixelHeightInMillimeters = multiplier / params.getYResolution().doubleValue();
/*     */ 
/*     */       
/* 179 */       updatePixelSize(meta, pixelWidthInMillimeters, pixelHeightInMillimeters);
/*     */ 
/*     */       
/* 182 */       double checkMerged = getHorizontalPixelSize(meta);
/* 183 */       if (!equals(checkMerged, pixelWidthInMillimeters, 1.0E-5D)) {
/*     */ 
/*     */ 
/*     */         
/* 187 */         double horzValue = 1.0D / pixelWidthInMillimeters;
/* 188 */         double vertValue = 1.0D / pixelHeightInMillimeters;
/* 189 */         updatePixelSize(meta, horzValue, vertValue);
/*     */       } 
/*     */     } 
/* 192 */     return meta;
/*     */   }
/*     */   
/*     */   private static boolean equals(double d1, double d2, double maxDelta) {
/* 196 */     return (Math.abs(d1 - d2) <= maxDelta);
/*     */   }
/*     */   
/*     */   private double getHorizontalPixelSize(IIOMetadata meta) {
/* 200 */     double result = 0.0D;
/* 201 */     IIOMetadataNode root = (IIOMetadataNode)meta.getAsTree("javax_imageio_1.0");
/* 202 */     IIOMetadataNode dim = getChildNode(root, "Dimension");
/* 203 */     if (dim != null) {
/* 204 */       IIOMetadataNode horz = getChildNode(dim, "HorizontalPixelSize");
/* 205 */       if (horz != null) {
/* 206 */         result = Double.parseDouble(horz.getAttribute("value"));
/*     */       }
/*     */     } 
/* 209 */     return result;
/*     */   }
/*     */   
/*     */   private void updatePixelSize(IIOMetadata meta, double horzValue, double vertValue) {
/* 213 */     IIOMetadataNode root = (IIOMetadataNode)meta.getAsTree("javax_imageio_1.0");
/* 214 */     IIOMetadataNode dim = getChildNode(root, "Dimension");
/*     */ 
/*     */     
/* 217 */     IIOMetadataNode child = getChildNode(dim, "HorizontalPixelSize");
/* 218 */     if (child == null) {
/* 219 */       child = new IIOMetadataNode("HorizontalPixelSize");
/* 220 */       dim.appendChild(child);
/*     */     } 
/* 222 */     child.setAttribute("value", Double.toString(horzValue));
/*     */     
/* 224 */     child = getChildNode(dim, "VerticalPixelSize");
/* 225 */     if (child == null) {
/* 226 */       child = new IIOMetadataNode("VerticalPixelSize");
/* 227 */       dim.appendChild(child);
/*     */     } 
/* 229 */     child.setAttribute("value", Double.toString(vertValue));
/*     */     try {
/* 231 */       meta.mergeTree("javax_imageio_1.0", root);
/* 232 */     } catch (IIOInvalidTreeException e) {
/* 233 */       throw new RuntimeException("Cannot update image metadata: " + e.getMessage());
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
/*     */   protected static IIOMetadataNode getChildNode(Node n, String name) {
/* 245 */     NodeList nodes = n.getChildNodes();
/* 246 */     for (int i = 0; i < nodes.getLength(); i++) {
/* 247 */       Node child = nodes.item(i);
/* 248 */       if (name.equals(child.getNodeName())) {
/* 249 */         return (IIOMetadataNode)child;
/*     */       }
/*     */     } 
/* 252 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMIMEType() {
/* 257 */     return this.targetMIME;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFunctional() {
/* 262 */     Iterator<ImageWriter> iter = ImageIO.getImageWritersByMIMEType(getMIMEType());
/*     */     
/* 264 */     return iter.hasNext();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void warningOccurred(ImageWriter source, int imageIndex, String warning) {
/* 270 */     System.err.println("Problem while writing image using ImageI/O: " + warning);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MultiImageWriter createMultiImageWriter(OutputStream out) throws IOException {
/* 276 */     return new IIOMultiImageWriter(out);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean supportsMultiImageWriter() {
/* 281 */     ImageWriter iiowriter = getIIOImageWriter();
/*     */     try {
/* 283 */       return iiowriter.canWriteSequence();
/*     */     } finally {
/* 285 */       iiowriter.dispose();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class IIOMultiImageWriter
/*     */     implements MultiImageWriter
/*     */   {
/* 296 */     private ImageWriter iiowriter = ImageIOImageWriter.this.getIIOImageWriter(); private ImageOutputStream imageStream; public IIOMultiImageWriter(OutputStream out) throws IOException {
/* 297 */       if (!this.iiowriter.canWriteSequence()) {
/* 298 */         throw new UnsupportedOperationException("This ImageWriter does not support writing multiple images to a single image file.");
/*     */       }
/*     */       
/* 301 */       this.iiowriter.addIIOWriteWarningListener(ImageIOImageWriter.this);
/*     */       
/* 303 */       this.imageStream = ImageIO.createImageOutputStream(out);
/* 304 */       this.iiowriter.setOutput(this.imageStream);
/*     */     }
/*     */     private boolean prepared;
/*     */     public void writeImage(RenderedImage image, ImageWriterParams params) throws IOException {
/*     */       ImageTypeSpecifier type;
/* 309 */       if (this.iiowriter == null) {
/* 310 */         throw new IllegalStateException("MultiImageWriter already closed!");
/*     */       }
/* 312 */       ImageWriteParam iwParam = ImageIOImageWriter.this.getDefaultWriteParam(this.iiowriter, image, params);
/*     */       
/* 314 */       if (!this.prepared) {
/*     */         
/* 316 */         IIOMetadata streamMetadata = ImageIOImageWriter.this.createStreamMetadata(this.iiowriter, iwParam, params);
/* 317 */         this.iiowriter.prepareWriteSequence(streamMetadata);
/* 318 */         this.prepared = true;
/*     */       } 
/*     */ 
/*     */       
/* 322 */       if (iwParam.getDestinationType() != null) {
/* 323 */         type = iwParam.getDestinationType();
/*     */       } else {
/* 325 */         type = ImageTypeSpecifier.createFromRenderedImage(image);
/*     */       } 
/*     */ 
/*     */       
/* 329 */       IIOMetadata meta = this.iiowriter.getDefaultImageMetadata(type, iwParam);
/*     */ 
/*     */       
/* 332 */       if (params != null && meta != null) {
/* 333 */         meta = ImageIOImageWriter.this.updateMetadata(image, meta, params);
/*     */       }
/*     */ 
/*     */       
/* 337 */       IIOImage iioimg = new IIOImage(image, null, meta);
/* 338 */       this.iiowriter.writeToSequence(iioimg, iwParam);
/*     */     }
/*     */     
/*     */     public void close() throws IOException {
/* 342 */       this.imageStream.close();
/* 343 */       this.imageStream = null;
/* 344 */       this.iiowriter.dispose();
/* 345 */       this.iiowriter = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/writer/imageio/ImageIOImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */