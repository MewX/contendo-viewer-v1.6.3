/*     */ package org.apache.xmlgraphics.image.writer.imageio;
/*     */ 
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import javax.imageio.ImageWriteParam;
/*     */ import javax.imageio.ImageWriter;
/*     */ import javax.imageio.metadata.IIOInvalidTreeException;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.metadata.IIOMetadataNode;
/*     */ import org.apache.xmlgraphics.image.writer.Endianness;
/*     */ import org.apache.xmlgraphics.image.writer.ImageWriterParams;
/*     */ import org.apache.xmlgraphics.image.writer.ResolutionUnit;
/*     */ import org.w3c.dom.Node;
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
/*     */ public class ImageIOTIFFImageWriter
/*     */   extends ImageIOImageWriter
/*     */ {
/*     */   private static final String SUN_TIFF_NATIVE_FORMAT = "com_sun_media_imageio_plugins_tiff_image_1.0";
/*     */   private static final String SUN_TIFF_NATIVE_STREAM_FORMAT = "com_sun_media_imageio_plugins_tiff_stream_1.0";
/*     */   private static final String DENOMINATOR_CENTIMETER = "/10000";
/*     */   private static final String DENOMINATOR_INCH = "/1";
/*     */   
/*     */   public ImageIOTIFFImageWriter() {
/*  56 */     super("image/tiff");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected IIOMetadata updateMetadata(RenderedImage image, IIOMetadata meta, ImageWriterParams params) {
/*  63 */     meta = super.updateMetadata(image, meta, params);
/*     */ 
/*     */ 
/*     */     
/*  67 */     if (params.getResolution() != null && 
/*  68 */       "com_sun_media_imageio_plugins_tiff_image_1.0".equals(meta.getNativeMetadataFormatName())) {
/*     */       
/*  70 */       IIOMetadataNode root = new IIOMetadataNode("com_sun_media_imageio_plugins_tiff_image_1.0");
/*  71 */       IIOMetadataNode ifd = getChildNode(root, "TIFFIFD");
/*  72 */       if (ifd == null) {
/*  73 */         ifd = new IIOMetadataNode("TIFFIFD");
/*  74 */         ifd.setAttribute("tagSets", "com.sun.media.imageio.plugins.tiff.BaselineTIFFTagSet");
/*     */         
/*  76 */         root.appendChild(ifd);
/*     */       } 
/*  78 */       ifd.appendChild(createResolutionUnitField(params));
/*  79 */       ifd.appendChild(createResolutionField(282, "XResolution", params.getXResolution(), params.getResolutionUnit()));
/*     */       
/*  81 */       ifd.appendChild(createResolutionField(283, "YResolution", params.getYResolution(), params.getResolutionUnit()));
/*     */       
/*  83 */       int rows = params.isSingleStrip() ? image.getHeight() : params.getRowsPerStrip();
/*  84 */       ifd.appendChild(createShortMetadataNode(278, "RowsPerStrip", Integer.toString(rows)));
/*     */ 
/*     */       
/*     */       try {
/*  88 */         meta.mergeTree("com_sun_media_imageio_plugins_tiff_image_1.0", root);
/*  89 */       } catch (IIOInvalidTreeException e) {
/*  90 */         throw new RuntimeException("Cannot update image metadata: " + e.getMessage(), e);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  95 */     return meta;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private IIOMetadataNode createResolutionField(int number, String name, Integer resolution, ResolutionUnit unit) {
/*     */     String value;
/* 107 */     if (unit == ResolutionUnit.INCH) {
/*     */       
/* 109 */       value = resolution + "/1";
/*     */     }
/*     */     else {
/*     */       
/* 113 */       float pixSzMM = 25.4F / resolution.floatValue();
/* 114 */       int numPix = (int)((100000.0F / pixSzMM) + 0.5D);
/* 115 */       value = numPix + "/10000";
/*     */     } 
/*     */ 
/*     */     
/* 119 */     return createRationalMetadataNode(number, name, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private IIOMetadataNode createResolutionUnitField(ImageWriterParams params) {
/* 128 */     return createShortMetadataNode(296, "ResolutionUnit", Integer.toString(params.getResolutionUnit().getValue()), params.getResolutionUnit().getDescription());
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
/*     */   public static final IIOMetadataNode createShortMetadataNode(int number, String name, String value) {
/* 144 */     return createShortMetadataNode(number, name, value, (String)null);
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
/*     */   public static final IIOMetadataNode createShortMetadataNode(int number, String name, String value, String description) {
/* 159 */     IIOMetadataNode field = createMetadataField(number, name);
/*     */ 
/*     */     
/* 162 */     IIOMetadataNode arrayNode = new IIOMetadataNode("TIFFShorts");
/* 163 */     field.appendChild(arrayNode);
/* 164 */     IIOMetadataNode valueNode = new IIOMetadataNode("TIFFShort");
/* 165 */     valueNode.setAttribute("value", value);
/* 166 */     if (description != null) {
/* 167 */       valueNode.setAttribute("description", description);
/*     */     }
/* 169 */     arrayNode.appendChild(valueNode);
/*     */     
/* 171 */     return field;
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
/*     */   public static final IIOMetadataNode createRationalMetadataNode(int number, String name, String value) {
/* 186 */     IIOMetadataNode field = createMetadataField(number, name);
/*     */ 
/*     */     
/* 189 */     IIOMetadataNode arrayNode = new IIOMetadataNode("TIFFRationals");
/* 190 */     field.appendChild(arrayNode);
/* 191 */     IIOMetadataNode valueNode = new IIOMetadataNode("TIFFRational");
/* 192 */     valueNode.setAttribute("value", value);
/* 193 */     arrayNode.appendChild(valueNode);
/*     */     
/* 195 */     return field;
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
/*     */   public static final IIOMetadataNode createMetadataField(int number, String name) {
/* 207 */     IIOMetadataNode field = new IIOMetadataNode("TIFFField");
/* 208 */     field.setAttribute("number", Integer.toString(number));
/* 209 */     field.setAttribute("name", name);
/* 210 */     return field;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected IIOMetadata createStreamMetadata(ImageWriter writer, ImageWriteParam writeParam, ImageWriterParams params) {
/* 217 */     Endianness endian = (params != null) ? params.getEndianness() : Endianness.DEFAULT;
/* 218 */     if (endian == Endianness.DEFAULT || endian == null) {
/* 219 */       return super.createStreamMetadata(writer, writeParam, params);
/*     */     }
/*     */ 
/*     */     
/* 223 */     IIOMetadata streamMetadata = writer.getDefaultStreamMetadata(writeParam);
/* 224 */     Set<String> names = new HashSet<String>(Arrays.asList(streamMetadata.getMetadataFormatNames()));
/*     */     
/* 226 */     if (names.contains("com_sun_media_imageio_plugins_tiff_stream_1.0")) {
/* 227 */       Node root = streamMetadata.getAsTree("com_sun_media_imageio_plugins_tiff_stream_1.0");
/* 228 */       root.getFirstChild().getAttributes().item(0).setNodeValue(endian.toString());
/*     */       try {
/* 230 */         streamMetadata.setFromTree("com_sun_media_imageio_plugins_tiff_stream_1.0", root);
/* 231 */       } catch (IIOInvalidTreeException e) {
/*     */         
/* 233 */         throw new IllegalStateException("Could not replace TIFF stream metadata: " + e.getMessage(), e);
/*     */       } 
/*     */     } 
/*     */     
/* 237 */     return streamMetadata;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/writer/imageio/ImageIOTIFFImageWriter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */