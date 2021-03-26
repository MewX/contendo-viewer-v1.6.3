/*     */ package com.github.jaiimageio.impl.plugins.raw;
/*     */ 
/*     */ import com.github.jaiimageio.stream.RawImageInputStream;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Hashtable;
/*     */ import java.util.Iterator;
/*     */ import javax.imageio.ImageReadParam;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.spi.ImageReaderSpi;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RawImageReader
/*     */   extends ImageReader
/*     */ {
/*  90 */   private RawImageInputStream iis = null;
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
/*     */   public static void computeRegionsWrapper(ImageReadParam param, int srcWidth, int srcHeight, BufferedImage image, Rectangle srcRegion, Rectangle destRegion) {
/* 102 */     computeRegions(param, srcWidth, srcHeight, image, srcRegion, destRegion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RawImageReader(ImageReaderSpi originator) {
/* 110 */     super(originator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInput(Object input, boolean seekForwardOnly, boolean ignoreMetadata) {
/* 120 */     super.setInput(input, seekForwardOnly, ignoreMetadata);
/* 121 */     this.iis = (RawImageInputStream)input;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumImages(boolean allowSearch) throws IOException {
/* 126 */     return this.iis.getNumImages();
/*     */   }
/*     */   
/*     */   public int getWidth(int imageIndex) throws IOException {
/* 130 */     checkIndex(imageIndex);
/* 131 */     return (this.iis.getImageDimension(imageIndex)).width;
/*     */   }
/*     */   
/*     */   public int getHeight(int imageIndex) throws IOException {
/* 135 */     checkIndex(imageIndex);
/*     */     
/* 137 */     return (this.iis.getImageDimension(imageIndex)).height;
/*     */   }
/*     */   
/*     */   public int getTileWidth(int imageIndex) throws IOException {
/* 141 */     checkIndex(imageIndex);
/* 142 */     return this.iis.getImageType().getSampleModel().getWidth();
/*     */   }
/*     */   
/*     */   public int getTileHeight(int imageIndex) throws IOException {
/* 146 */     checkIndex(imageIndex);
/* 147 */     return this.iis.getImageType().getSampleModel().getHeight();
/*     */   }
/*     */   
/*     */   private void checkIndex(int imageIndex) throws IOException {
/* 151 */     if (imageIndex < 0 || imageIndex >= getNumImages(true)) {
/* 152 */       throw new IndexOutOfBoundsException(I18N.getString("RawImageReader0"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator getImageTypes(int imageIndex) throws IOException {
/* 158 */     checkIndex(imageIndex);
/* 159 */     ArrayList<ImageTypeSpecifier> list = new ArrayList(1);
/* 160 */     list.add(this.iis.getImageType());
/* 161 */     return list.iterator();
/*     */   }
/*     */   
/*     */   public ImageReadParam getDefaultReadParam() {
/* 165 */     return new ImageReadParam();
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadata getImageMetadata(int imageIndex) throws IOException {
/* 170 */     return null;
/*     */   }
/*     */   
/*     */   public IIOMetadata getStreamMetadata() throws IOException {
/* 174 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isRandomAccessEasy(int imageIndex) throws IOException {
/* 178 */     checkIndex(imageIndex);
/* 179 */     return true;
/*     */   }
/*     */   
/*     */   public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
/*     */     WritableRaster raster;
/* 184 */     if (param == null)
/* 185 */       param = getDefaultReadParam(); 
/* 186 */     checkIndex(imageIndex);
/* 187 */     clearAbortRequest();
/* 188 */     processImageStarted(imageIndex);
/*     */     
/* 190 */     BufferedImage bi = param.getDestination();
/* 191 */     RawRenderedImage image = new RawRenderedImage(this.iis, this, param, imageIndex);
/*     */     
/* 193 */     Point offset = param.getDestinationOffset();
/*     */ 
/*     */     
/* 196 */     if (bi == null) {
/* 197 */       ColorModel colorModel = image.getColorModel();
/* 198 */       SampleModel sampleModel = image.getSampleModel();
/*     */ 
/*     */       
/* 201 */       ImageTypeSpecifier type = param.getDestinationType();
/* 202 */       if (type != null) {
/* 203 */         colorModel = type.getColorModel();
/*     */       }
/* 205 */       raster = Raster.createWritableRaster(sampleModel
/* 206 */           .createCompatibleSampleModel(image.getMinX() + image
/* 207 */             .getWidth(), image
/* 208 */             .getMinY() + image
/* 209 */             .getHeight()), new Point(0, 0));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 215 */       bi = new BufferedImage(colorModel, raster, (colorModel != null) ? colorModel.isAlphaPremultiplied() : false, new Hashtable<Object, Object>());
/*     */     } else {
/*     */       
/* 218 */       raster = bi.getWritableTile(0, 0);
/*     */     } 
/*     */     
/* 221 */     image.setDestImage(bi);
/*     */     
/* 223 */     image.readAsRaster(raster);
/* 224 */     image.clearDestImage();
/*     */     
/* 226 */     if (abortRequested()) {
/* 227 */       processReadAborted();
/*     */     } else {
/* 229 */       processImageComplete();
/* 230 */     }  return bi;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderedImage readAsRenderedImage(int imageIndex, ImageReadParam param) throws IOException {
/* 236 */     if (param == null) {
/* 237 */       param = getDefaultReadParam();
/*     */     }
/* 239 */     checkIndex(imageIndex);
/* 240 */     clearAbortRequest();
/* 241 */     processImageStarted(0);
/*     */     
/* 243 */     RawRenderedImage rawRenderedImage = new RawRenderedImage(this.iis, this, param, imageIndex);
/*     */ 
/*     */     
/* 246 */     if (abortRequested()) {
/* 247 */       processReadAborted();
/*     */     } else {
/* 249 */       processImageComplete();
/* 250 */     }  return (RenderedImage)rawRenderedImage;
/*     */   }
/*     */ 
/*     */   
/*     */   public Raster readRaster(int imageIndex, ImageReadParam param) throws IOException {
/* 255 */     BufferedImage bi = read(imageIndex, param);
/* 256 */     return bi.getData();
/*     */   }
/*     */   
/*     */   public boolean canReadRaster() {
/* 260 */     return true;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 264 */     super.reset();
/* 265 */     this.iis = null;
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
/*     */   public void processImageUpdateWrapper(BufferedImage theImage, int minX, int minY, int width, int height, int periodX, int periodY, int[] bands) {
/* 277 */     processImageUpdate(theImage, minX, minY, width, height, periodX, periodY, bands);
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
/*     */   public void processImageProgressWrapper(float percentageDone) {
/* 289 */     processImageProgress(percentageDone);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getAbortRequest() {
/* 296 */     return abortRequested();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/raw/RawImageReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */