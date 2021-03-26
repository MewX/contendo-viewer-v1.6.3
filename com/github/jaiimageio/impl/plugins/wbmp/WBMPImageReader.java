/*     */ package com.github.jaiimageio.impl.plugins.wbmp;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.ImageUtil;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.MultiPixelPackedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import javax.imageio.IIOException;
/*     */ import javax.imageio.ImageReadParam;
/*     */ import javax.imageio.ImageReader;
/*     */ import javax.imageio.ImageTypeSpecifier;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.spi.ImageReaderSpi;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WBMPImageReader
/*     */   extends ImageReader
/*     */ {
/*  75 */   private ImageInputStream iis = null;
/*     */ 
/*     */   
/*     */   private boolean gotHeader = false;
/*     */ 
/*     */   
/*     */   private long imageDataOffset;
/*     */ 
/*     */   
/*     */   private int width;
/*     */ 
/*     */   
/*     */   private int height;
/*     */ 
/*     */   
/*     */   private int wbmpType;
/*     */ 
/*     */   
/*     */   private WBMPMetadata metadata;
/*     */ 
/*     */   
/*     */   public WBMPImageReader(ImageReaderSpi originator) {
/*  97 */     super(originator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInput(Object input, boolean seekForwardOnly, boolean ignoreMetadata) {
/* 104 */     super.setInput(input, seekForwardOnly, ignoreMetadata);
/* 105 */     this.iis = (ImageInputStream)input;
/* 106 */     this.gotHeader = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumImages(boolean allowSearch) throws IOException {
/* 111 */     if (this.iis == null) {
/* 112 */       throw new IllegalStateException(I18N.getString("GetNumImages0"));
/*     */     }
/* 114 */     if (this.seekForwardOnly && allowSearch) {
/* 115 */       throw new IllegalStateException(I18N.getString("GetNumImages1"));
/*     */     }
/* 117 */     return 1;
/*     */   }
/*     */   
/*     */   public int getWidth(int imageIndex) throws IOException {
/* 121 */     checkIndex(imageIndex);
/* 122 */     readHeader();
/* 123 */     return this.width;
/*     */   }
/*     */   
/*     */   public int getHeight(int imageIndex) throws IOException {
/* 127 */     checkIndex(imageIndex);
/* 128 */     readHeader();
/* 129 */     return this.height;
/*     */   }
/*     */   
/*     */   public boolean isRandomAccessEasy(int imageIndex) throws IOException {
/* 133 */     checkIndex(imageIndex);
/* 134 */     return true;
/*     */   }
/*     */   
/*     */   private void checkIndex(int imageIndex) {
/* 138 */     if (imageIndex != 0) {
/* 139 */       throw new IndexOutOfBoundsException(I18N.getString("WBMPImageReader0"));
/*     */     }
/*     */   }
/*     */   
/*     */   public void readHeader() throws IOException {
/* 144 */     if (this.gotHeader) {
/*     */ 
/*     */       
/* 147 */       this.iis.seek(this.imageDataOffset);
/*     */       
/*     */       return;
/*     */     } 
/* 151 */     if (this.iis == null) {
/* 152 */       throw new IllegalStateException(I18N.getString("WBMPImageReader1"));
/*     */     }
/*     */     
/* 155 */     this.metadata = new WBMPMetadata();
/* 156 */     this.wbmpType = this.iis.readByte();
/* 157 */     byte fixHeaderField = this.iis.readByte();
/*     */ 
/*     */     
/* 160 */     if (fixHeaderField != 0 || 
/* 161 */       !isValidWbmpType(this.wbmpType)) {
/* 162 */       throw new IIOException(I18N.getString("WBMPImageReader2"));
/*     */     }
/*     */     
/* 165 */     this.metadata.wbmpType = this.wbmpType;
/*     */ 
/*     */     
/* 168 */     this.width = ImageUtil.readMultiByteInteger(this.iis);
/* 169 */     this.metadata.width = this.width;
/*     */ 
/*     */     
/* 172 */     this.height = ImageUtil.readMultiByteInteger(this.iis);
/* 173 */     this.metadata.height = this.height;
/*     */     
/* 175 */     this.gotHeader = true;
/*     */     
/* 177 */     this.imageDataOffset = this.iis.getStreamPosition();
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator getImageTypes(int imageIndex) throws IOException {
/* 182 */     checkIndex(imageIndex);
/* 183 */     readHeader();
/*     */     
/* 185 */     BufferedImage bi = new BufferedImage(1, 1, 12);
/*     */     
/* 187 */     ArrayList<ImageTypeSpecifier> list = new ArrayList(1);
/* 188 */     list.add(new ImageTypeSpecifier(bi));
/* 189 */     return list.iterator();
/*     */   }
/*     */   
/*     */   public ImageReadParam getDefaultReadParam() {
/* 193 */     return new ImageReadParam();
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadata getImageMetadata(int imageIndex) throws IOException {
/* 198 */     checkIndex(imageIndex);
/* 199 */     if (this.metadata == null) {
/* 200 */       readHeader();
/*     */     }
/* 202 */     return this.metadata;
/*     */   }
/*     */   
/*     */   public IIOMetadata getStreamMetadata() throws IOException {
/* 206 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage read(int imageIndex, ImageReadParam param) throws IOException {
/* 212 */     if (this.iis == null) {
/* 213 */       throw new IllegalStateException(I18N.getString("WBMPImageReader1"));
/*     */     }
/*     */     
/* 216 */     checkIndex(imageIndex);
/* 217 */     clearAbortRequest();
/* 218 */     processImageStarted(imageIndex);
/* 219 */     if (param == null) {
/* 220 */       param = getDefaultReadParam();
/*     */     }
/*     */     
/* 223 */     readHeader();
/*     */     
/* 225 */     Rectangle sourceRegion = new Rectangle(0, 0, 0, 0);
/* 226 */     Rectangle destinationRegion = new Rectangle(0, 0, 0, 0);
/*     */     
/* 228 */     computeRegions(param, this.width, this.height, param
/* 229 */         .getDestination(), sourceRegion, destinationRegion);
/*     */ 
/*     */ 
/*     */     
/* 233 */     int scaleX = param.getSourceXSubsampling();
/* 234 */     int scaleY = param.getSourceYSubsampling();
/* 235 */     int xOffset = param.getSubsamplingXOffset();
/* 236 */     int yOffset = param.getSubsamplingYOffset();
/*     */ 
/*     */     
/* 239 */     BufferedImage bi = param.getDestination();
/*     */     
/* 241 */     if (bi == null) {
/* 242 */       bi = new BufferedImage(destinationRegion.x + destinationRegion.width, destinationRegion.y + destinationRegion.height, 12);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 248 */     boolean noTransform = (destinationRegion.equals(new Rectangle(0, 0, this.width, this.height)) && destinationRegion.equals(new Rectangle(0, 0, bi.getWidth(), bi.getHeight())));
/*     */ 
/*     */     
/* 251 */     WritableRaster tile = bi.getWritableTile(0, 0);
/*     */ 
/*     */ 
/*     */     
/* 255 */     MultiPixelPackedSampleModel sm = (MultiPixelPackedSampleModel)bi.getSampleModel();
/*     */     
/* 257 */     if (noTransform) {
/* 258 */       if (abortRequested()) {
/* 259 */         processReadAborted();
/* 260 */         return bi;
/*     */       } 
/*     */ 
/*     */       
/* 264 */       this.iis.read(((DataBufferByte)tile.getDataBuffer()).getData(), 0, this.height * sm
/* 265 */           .getScanlineStride());
/* 266 */       processImageUpdate(bi, 0, 0, this.width, this.height, 1, 1, new int[] { 0 });
/*     */ 
/*     */ 
/*     */       
/* 270 */       processImageProgress(100.0F);
/*     */     } else {
/* 272 */       int len = (this.width + 7) / 8;
/* 273 */       byte[] buf = new byte[len];
/* 274 */       byte[] data = ((DataBufferByte)tile.getDataBuffer()).getData();
/* 275 */       int lineStride = sm.getScanlineStride();
/* 276 */       this.iis.skipBytes(len * sourceRegion.y);
/* 277 */       int skipLength = len * (scaleY - 1);
/*     */ 
/*     */       
/* 280 */       int[] srcOff = new int[destinationRegion.width];
/* 281 */       int[] destOff = new int[destinationRegion.width];
/* 282 */       int[] srcPos = new int[destinationRegion.width];
/* 283 */       int[] destPos = new int[destinationRegion.width];
/*     */       
/* 285 */       int i = destinationRegion.x, x = sourceRegion.x, m = 0;
/* 286 */       for (; i < destinationRegion.x + destinationRegion.width; 
/* 287 */         i++, m++, x += scaleX) {
/* 288 */         srcPos[m] = x >> 3;
/* 289 */         srcOff[m] = 7 - (x & 0x7);
/* 290 */         destPos[m] = i >> 3;
/* 291 */         destOff[m] = 7 - (i & 0x7);
/*     */       } 
/*     */       
/* 294 */       int j = 0, y = sourceRegion.y;
/* 295 */       int k = destinationRegion.y * lineStride;
/* 296 */       for (; j < destinationRegion.height; j++, y += scaleY) {
/*     */         
/* 298 */         if (abortRequested())
/*     */           break; 
/* 300 */         this.iis.read(buf, 0, len);
/* 301 */         for (int n = 0; n < destinationRegion.width; n++) {
/*     */           
/* 303 */           int v = buf[srcPos[n]] >> srcOff[n] & 0x1;
/* 304 */           data[k + destPos[n]] = (byte)(data[k + destPos[n]] | v << destOff[n]);
/*     */         } 
/*     */         
/* 307 */         k += lineStride;
/* 308 */         this.iis.skipBytes(skipLength);
/* 309 */         processImageUpdate(bi, 0, j, destinationRegion.width, 1, 1, 1, new int[] { 0 });
/*     */ 
/*     */ 
/*     */         
/* 313 */         processImageProgress(100.0F * j / destinationRegion.height);
/*     */       } 
/*     */     } 
/*     */     
/* 317 */     if (abortRequested()) {
/* 318 */       processReadAborted();
/*     */     } else {
/* 320 */       processImageComplete();
/* 321 */     }  return bi;
/*     */   }
/*     */   
/*     */   public boolean canReadRaster() {
/* 325 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Raster readRaster(int imageIndex, ImageReadParam param) throws IOException {
/* 330 */     BufferedImage bi = read(imageIndex, param);
/* 331 */     return bi.getData();
/*     */   }
/*     */   
/*     */   public void reset() {
/* 335 */     super.reset();
/* 336 */     this.iis = null;
/* 337 */     this.gotHeader = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isValidWbmpType(int type) {
/* 345 */     return (type == 0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/wbmp/WBMPImageReader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */