/*     */ package org.apache.xmlgraphics.image.loader.impl;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.ByteOrder;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.xml.transform.Source;
/*     */ import org.apache.xmlgraphics.image.loader.ImageContext;
/*     */ import org.apache.xmlgraphics.image.loader.ImageException;
/*     */ import org.apache.xmlgraphics.image.loader.ImageInfo;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSize;
/*     */ import org.apache.xmlgraphics.image.loader.util.ImageUtil;
/*     */ import org.apache.xmlgraphics.util.UnitConv;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PreloaderEMF
/*     */   extends AbstractImagePreloader
/*     */ {
/*     */   protected static final int EMF_SIG_LENGTH = 88;
/*     */   private static final int SIGNATURE_OFFSET = 40;
/*     */   private static final int WIDTH_OFFSET = 32;
/*     */   private static final int HEIGHT_OFFSET = 36;
/*     */   private static final int HRES_PIXEL_OFFSET = 72;
/*     */   private static final int VRES_PIXEL_OFFSET = 76;
/*     */   private static final int HRES_MM_OFFSET = 80;
/*     */   private static final int VRES_MM_OFFSET = 84;
/*     */   
/*     */   public ImageInfo preloadImage(String uri, Source src, ImageContext context) throws IOException, ImageException {
/*  61 */     if (!ImageUtil.hasImageInputStream(src)) {
/*  62 */       return null;
/*     */     }
/*  64 */     ImageInputStream in = ImageUtil.needImageInputStream(src);
/*  65 */     byte[] header = getHeader(in, 88);
/*  66 */     boolean supported = (header[40] == 32 && header[41] == 69 && header[42] == 77 && header[43] == 70);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  72 */     if (supported) {
/*  73 */       ImageInfo info = new ImageInfo(uri, "image/emf");
/*  74 */       info.setSize(determineSize(in, context));
/*  75 */       return info;
/*     */     } 
/*  77 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private ImageSize determineSize(ImageInputStream in, ImageContext context) throws IOException, ImageException {
/*  83 */     in.mark();
/*  84 */     ByteOrder oldByteOrder = in.getByteOrder();
/*     */     try {
/*  86 */       ImageSize size = new ImageSize();
/*     */ 
/*     */       
/*  89 */       in.setByteOrder(ByteOrder.LITTLE_ENDIAN);
/*     */ 
/*     */       
/*  92 */       in.skipBytes(32);
/*  93 */       int width = (int)in.readUnsignedInt();
/*  94 */       int height = (int)in.readUnsignedInt();
/*     */       
/*  96 */       in.skipBytes(32);
/*  97 */       long hresPixel = in.readUnsignedInt();
/*  98 */       long vresPixel = in.readUnsignedInt();
/*  99 */       long hresMM = in.readUnsignedInt();
/* 100 */       long vresMM = in.readUnsignedInt();
/* 101 */       double resHorz = hresPixel / UnitConv.mm2in(hresMM);
/* 102 */       double resVert = vresPixel / UnitConv.mm2in(vresMM);
/* 103 */       size.setResolution(resHorz, resVert);
/*     */       
/* 105 */       width = (int)Math.round(UnitConv.mm2mpt((width / 100.0F)));
/* 106 */       height = (int)Math.round(UnitConv.mm2mpt((height / 100.0F)));
/* 107 */       size.setSizeInMillipoints(width, height);
/* 108 */       size.calcPixelsFromSize();
/*     */       
/* 110 */       return size;
/*     */     } finally {
/* 112 */       in.setByteOrder(oldByteOrder);
/* 113 */       in.reset();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/PreloaderEMF.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */