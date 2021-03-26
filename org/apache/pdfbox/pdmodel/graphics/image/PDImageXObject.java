/*     */ package org.apache.pdfbox.pdmodel.graphics.image;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.List;
/*     */ import javax.imageio.ImageIO;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSInputStream;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.filter.DecodeOptions;
/*     */ import org.apache.pdfbox.filter.DecodeResult;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDMetadata;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
/*     */ import org.apache.pdfbox.util.filetypedetector.FileType;
/*     */ import org.apache.pdfbox.util.filetypedetector.FileTypeDetector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PDImageXObject
/*     */   extends PDXObject
/*     */   implements PDImage
/*     */ {
/*  67 */   private static final Log LOG = LogFactory.getLog(PDImageXObject.class);
/*     */   
/*     */   private SoftReference<BufferedImage> cachedImage;
/*     */   
/*     */   private PDColorSpace colorSpace;
/*     */   
/*  73 */   private int cachedImageSubsampling = Integer.MAX_VALUE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final PDResources resources;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDImageXObject(PDDocument document) throws IOException {
/*  90 */     this(new PDStream(document), (PDResources)null);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDImageXObject(PDDocument document, InputStream encodedStream, COSBase cosFilter, int width, int height, int bitsPerComponent, PDColorSpace initColorSpace) throws IOException {
/* 111 */     super(createRawStream(document, encodedStream), COSName.IMAGE);
/* 112 */     getCOSObject().setItem(COSName.FILTER, cosFilter);
/* 113 */     this.resources = null;
/* 114 */     this.colorSpace = null;
/* 115 */     setBitsPerComponent(bitsPerComponent);
/* 116 */     setWidth(width);
/* 117 */     setHeight(height);
/* 118 */     setColorSpace(initColorSpace);
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
/*     */   public PDImageXObject(PDStream stream, PDResources resources) throws IOException {
/* 132 */     super(stream, COSName.IMAGE);
/* 133 */     this.resources = resources;
/* 134 */     List<COSName> filters = stream.getFilters();
/* 135 */     if (filters != null && !filters.isEmpty() && COSName.JPX_DECODE.equals(filters.get(filters.size() - 1))) {
/*     */       
/* 137 */       COSInputStream is = null;
/*     */       
/*     */       try {
/* 140 */         is = stream.createInputStream();
/* 141 */         DecodeResult decodeResult = is.getDecodeResult();
/* 142 */         stream.getCOSObject().addAll(decodeResult.getParameters());
/* 143 */         this.colorSpace = (PDColorSpace)decodeResult.getJPXColorSpace();
/*     */       }
/*     */       finally {
/*     */         
/* 147 */         IOUtils.closeQuietly((Closeable)is);
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
/*     */   public static PDImageXObject createThumbnail(COSStream cosStream) throws IOException {
/* 161 */     PDStream pdStream = new PDStream(cosStream);
/* 162 */     return new PDImageXObject(pdStream, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static COSStream createRawStream(PDDocument document, InputStream rawInput) throws IOException {
/* 171 */     COSStream stream = document.getDocument().createCOSStream();
/* 172 */     OutputStream output = null;
/*     */     
/*     */     try {
/* 175 */       output = stream.createRawOutputStream();
/* 176 */       IOUtils.copy(rawInput, output);
/*     */     }
/*     */     finally {
/*     */       
/* 180 */       if (output != null)
/*     */       {
/* 182 */         output.close();
/*     */       }
/*     */     } 
/* 185 */     return stream;
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
/*     */   public static PDImageXObject createFromFile(String imagePath, PDDocument doc) throws IOException {
/* 200 */     return createFromFileByExtension(new File(imagePath), doc);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PDImageXObject createFromFileByExtension(File file, PDDocument doc) throws IOException {
/* 220 */     String name = file.getName();
/* 221 */     int dot = file.getName().lastIndexOf('.');
/* 222 */     if (dot == -1)
/*     */     {
/* 224 */       throw new IllegalArgumentException("Image type not supported: " + name);
/*     */     }
/* 226 */     String ext = name.substring(dot + 1).toLowerCase();
/* 227 */     if ("jpg".equals(ext) || "jpeg".equals(ext)) {
/*     */       
/* 229 */       FileInputStream fis = new FileInputStream(file);
/* 230 */       PDImageXObject imageXObject = JPEGFactory.createFromStream(doc, fis);
/* 231 */       fis.close();
/* 232 */       return imageXObject;
/*     */     } 
/* 234 */     if ("tif".equals(ext) || "tiff".equals(ext))
/*     */     {
/* 236 */       return CCITTFactory.createFromFile(doc, file);
/*     */     }
/* 238 */     if ("gif".equals(ext) || "bmp".equals(ext) || "png".equals(ext)) {
/*     */       
/* 240 */       BufferedImage bim = ImageIO.read(file);
/* 241 */       return LosslessFactory.createFromImage(doc, bim);
/*     */     } 
/* 243 */     throw new IllegalArgumentException("Image type not supported: " + name);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PDImageXObject createFromFileByContent(File file, PDDocument doc) throws IOException {
/* 263 */     FileInputStream fileInputStream = null;
/* 264 */     BufferedInputStream bufferedInputStream = null;
/* 265 */     FileType fileType = null;
/*     */     
/*     */     try {
/* 268 */       fileInputStream = new FileInputStream(file);
/* 269 */       bufferedInputStream = new BufferedInputStream(fileInputStream);
/* 270 */       fileType = FileTypeDetector.detectFileType(bufferedInputStream);
/*     */     }
/* 272 */     catch (IOException e) {
/*     */       
/* 274 */       throw new IOException("Could not determine file type: " + file.getName(), e);
/*     */     }
/*     */     finally {
/*     */       
/* 278 */       IOUtils.closeQuietly(fileInputStream);
/* 279 */       IOUtils.closeQuietly(bufferedInputStream);
/*     */     } 
/* 281 */     if (fileType == null)
/*     */     {
/* 283 */       throw new IllegalArgumentException("Image type not supported: " + file.getName());
/*     */     }
/*     */     
/* 286 */     if (fileType.equals(FileType.JPEG)) {
/*     */       
/* 288 */       FileInputStream fis = new FileInputStream(file);
/* 289 */       PDImageXObject imageXObject = JPEGFactory.createFromStream(doc, fis);
/* 290 */       fis.close();
/* 291 */       return imageXObject;
/*     */     } 
/* 293 */     if (fileType.equals(FileType.TIFF)) {
/*     */       
/*     */       try {
/*     */         
/* 297 */         return CCITTFactory.createFromFile(doc, file);
/*     */       }
/* 299 */       catch (IOException ex) {
/*     */         
/* 301 */         LOG.debug("Reading as TIFF failed, setting fileType to PNG", ex);
/*     */ 
/*     */ 
/*     */         
/* 305 */         fileType = FileType.PNG;
/*     */       } 
/*     */     }
/* 308 */     if (fileType.equals(FileType.BMP) || fileType.equals(FileType.GIF) || fileType.equals(FileType.PNG)) {
/*     */       
/* 310 */       BufferedImage bim = ImageIO.read(file);
/* 311 */       return LosslessFactory.createFromImage(doc, bim);
/*     */     } 
/* 313 */     throw new IllegalArgumentException("Image type " + fileType + " not supported: " + file.getName());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PDImageXObject createFromByteArray(PDDocument document, byte[] byteArray, String name) throws IOException {
/*     */     FileType fileType;
/*     */     try {
/* 337 */       fileType = FileTypeDetector.detectFileType(byteArray);
/*     */     }
/* 339 */     catch (IOException e) {
/*     */       
/* 341 */       throw new IOException("Could not determine file type: " + name, e);
/*     */     } 
/* 343 */     if (fileType == null)
/*     */     {
/* 345 */       throw new IllegalArgumentException("Image type not supported: " + name);
/*     */     }
/*     */     
/* 348 */     if (fileType.equals(FileType.JPEG))
/*     */     {
/* 350 */       return JPEGFactory.createFromByteArray(document, byteArray);
/*     */     }
/* 352 */     if (fileType.equals(FileType.TIFF)) {
/*     */       
/*     */       try {
/*     */         
/* 356 */         return CCITTFactory.createFromByteArray(document, byteArray);
/*     */       }
/* 358 */       catch (IOException ex) {
/*     */         
/* 360 */         LOG.debug("Reading as TIFF failed, setting fileType to PNG", ex);
/*     */ 
/*     */ 
/*     */         
/* 364 */         fileType = FileType.PNG;
/*     */       } 
/*     */     }
/* 367 */     if (fileType.equals(FileType.BMP) || fileType.equals(FileType.GIF) || fileType.equals(FileType.PNG)) {
/*     */       
/* 369 */       ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
/* 370 */       BufferedImage bim = ImageIO.read(bais);
/* 371 */       return LosslessFactory.createFromImage(document, bim);
/*     */     } 
/* 373 */     throw new IllegalArgumentException("Image type " + fileType + " not supported: " + name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDMetadata getMetadata() {
/* 382 */     COSStream cosStream = getCOSObject().getCOSStream(COSName.METADATA);
/* 383 */     if (cosStream != null)
/*     */     {
/* 385 */       return new PDMetadata(cosStream);
/*     */     }
/* 387 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMetadata(PDMetadata meta) {
/* 396 */     getCOSObject().setItem(COSName.METADATA, (COSObjectable)meta);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructParent() {
/* 406 */     return getCOSObject().getInt(COSName.STRUCT_PARENT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStructParent(int key) {
/* 415 */     getCOSObject().setInt(COSName.STRUCT_PARENT, key);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getImage() throws IOException {
/* 425 */     return getImage((Rectangle)null, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getImage(Rectangle region, int subsampling) throws IOException {
/* 434 */     if (region == null && subsampling == this.cachedImageSubsampling && this.cachedImage != null) {
/*     */       
/* 436 */       BufferedImage cached = this.cachedImage.get();
/* 437 */       if (cached != null)
/*     */       {
/* 439 */         return cached;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 444 */     BufferedImage image = SampledImageReader.getRGBImage(this, region, subsampling, getColorKeyMask());
/*     */ 
/*     */     
/* 447 */     PDImageXObject softMask = getSoftMask();
/* 448 */     if (softMask != null) {
/*     */       
/* 450 */       float[] matte = extractMatte(softMask);
/* 451 */       image = applyMask(image, softMask.getOpaqueImage(), true, matte);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 456 */       PDImageXObject mask = getMask();
/* 457 */       if (mask != null && mask.isStencil())
/*     */       {
/* 459 */         image = applyMask(image, mask.getOpaqueImage(), false, (float[])null);
/*     */       }
/*     */     } 
/*     */     
/* 463 */     if (region == null && subsampling <= this.cachedImageSubsampling) {
/*     */ 
/*     */ 
/*     */       
/* 467 */       this.cachedImageSubsampling = subsampling;
/* 468 */       this.cachedImage = new SoftReference<BufferedImage>(image);
/*     */     } 
/*     */     
/* 471 */     return image;
/*     */   }
/*     */ 
/*     */   
/*     */   private float[] extractMatte(PDImageXObject softMask) throws IOException {
/* 476 */     COSBase base = softMask.getCOSObject().getItem(COSName.MATTE);
/* 477 */     float[] matte = null;
/* 478 */     if (base instanceof COSArray) {
/*     */ 
/*     */ 
/*     */       
/* 482 */       matte = ((COSArray)base).toFloatArray();
/*     */       
/* 484 */       matte = getColorSpace().toRGB(matte);
/*     */     } 
/* 486 */     return matte;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getStencilImage(Paint paint) throws IOException {
/* 496 */     if (!isStencil())
/*     */     {
/* 498 */       throw new IllegalStateException("Image is not a stencil");
/*     */     }
/* 500 */     return SampledImageReader.getStencilImage(this, paint);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage getOpaqueImage() throws IOException {
/* 511 */     return SampledImageReader.getRGBImage(this, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage applyMask(BufferedImage image, BufferedImage mask, boolean isSoft, float[] matte) throws IOException {
/* 520 */     if (mask == null)
/*     */     {
/* 522 */       return image;
/*     */     }
/*     */     
/* 525 */     int width = image.getWidth();
/* 526 */     int height = image.getHeight();
/*     */ 
/*     */     
/* 529 */     if (mask.getWidth() < width || mask.getHeight() < height) {
/*     */       
/* 531 */       mask = scaleImage(mask, width, height);
/*     */     }
/* 533 */     else if (mask.getWidth() > width || mask.getHeight() > height) {
/*     */       
/* 535 */       width = mask.getWidth();
/* 536 */       height = mask.getHeight();
/* 537 */       image = scaleImage(image, width, height);
/*     */     }
/* 539 */     else if ((image.getRaster().getPixel(0, 0, (int[])null)).length < 3) {
/*     */ 
/*     */       
/* 542 */       image = scaleImage(image, width, height);
/*     */     } 
/*     */ 
/*     */     
/* 546 */     BufferedImage masked = new BufferedImage(width, height, 2);
/* 547 */     WritableRaster src = image.getRaster();
/* 548 */     WritableRaster dest = masked.getRaster();
/* 549 */     WritableRaster alpha = mask.getRaster();
/*     */     
/* 551 */     float[] rgb = new float[4];
/* 552 */     float[] rgba = new float[4];
/* 553 */     float[] alphaPixel = null;
/* 554 */     for (int y = 0; y < height; y++) {
/*     */       
/* 556 */       for (int x = 0; x < width; x++) {
/*     */         
/* 558 */         src.getPixel(x, y, rgb);
/*     */         
/* 560 */         rgba[0] = rgb[0];
/* 561 */         rgba[1] = rgb[1];
/* 562 */         rgba[2] = rgb[2];
/*     */         
/* 564 */         alphaPixel = alpha.getPixel(x, y, alphaPixel);
/* 565 */         if (isSoft) {
/*     */           
/* 567 */           rgba[3] = alphaPixel[0];
/* 568 */           if (matte != null && Float.compare(alphaPixel[0], 0.0F) != 0)
/*     */           {
/* 570 */             rgba[0] = clampColor(((rgba[0] / 255.0F - matte[0]) / alphaPixel[0] / 255.0F + matte[0]) * 255.0F);
/* 571 */             rgba[1] = clampColor(((rgba[1] / 255.0F - matte[1]) / alphaPixel[0] / 255.0F + matte[1]) * 255.0F);
/* 572 */             rgba[2] = clampColor(((rgba[2] / 255.0F - matte[2]) / alphaPixel[0] / 255.0F + matte[2]) * 255.0F);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 577 */           rgba[3] = 255.0F - alphaPixel[0];
/*     */         } 
/*     */         
/* 580 */         dest.setPixel(x, y, rgba);
/*     */       } 
/*     */     } 
/*     */     
/* 584 */     return masked;
/*     */   }
/*     */ 
/*     */   
/*     */   private float clampColor(float color) {
/* 589 */     return (color < 0.0F) ? 0.0F : ((color > 255.0F) ? 255.0F : color);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage scaleImage(BufferedImage image, int width, int height) {
/* 597 */     BufferedImage image2 = new BufferedImage(width, height, 1);
/* 598 */     Graphics2D g = image2.createGraphics();
/* 599 */     if (getInterpolate()) {
/*     */       
/* 601 */       g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
/*     */       
/* 603 */       g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
/*     */     } 
/*     */     
/* 606 */     g.drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(), image.getHeight(), null);
/* 607 */     g.dispose();
/* 608 */     return image2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDImageXObject getMask() throws IOException {
/* 618 */     COSBase mask = getCOSObject().getDictionaryObject(COSName.MASK);
/* 619 */     if (mask instanceof COSArray)
/*     */     {
/*     */       
/* 622 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 626 */     COSStream cosStream = getCOSObject().getCOSStream(COSName.MASK);
/* 627 */     if (cosStream != null)
/*     */     {
/*     */       
/* 630 */       return new PDImageXObject(new PDStream(cosStream), null);
/*     */     }
/* 632 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getColorKeyMask() {
/* 642 */     COSBase mask = getCOSObject().getDictionaryObject(COSName.MASK);
/* 643 */     if (mask instanceof COSArray)
/*     */     {
/* 645 */       return (COSArray)mask;
/*     */     }
/* 647 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDImageXObject getSoftMask() throws IOException {
/* 657 */     COSStream cosStream = getCOSObject().getCOSStream(COSName.SMASK);
/* 658 */     if (cosStream != null)
/*     */     {
/*     */       
/* 661 */       return new PDImageXObject(new PDStream(cosStream), null);
/*     */     }
/* 663 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBitsPerComponent() {
/* 669 */     if (isStencil())
/*     */     {
/* 671 */       return 1;
/*     */     }
/*     */ 
/*     */     
/* 675 */     return getCOSObject().getInt(COSName.BITS_PER_COMPONENT, COSName.BPC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBitsPerComponent(int bpc) {
/* 682 */     getCOSObject().setInt(COSName.BITS_PER_COMPONENT, bpc);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColorSpace getColorSpace() throws IOException {
/* 688 */     if (this.colorSpace == null) {
/*     */       
/* 690 */       COSBase cosBase = getCOSObject().getItem(COSName.COLORSPACE, COSName.CS);
/* 691 */       if (cosBase != null) {
/*     */         
/* 693 */         COSObject indirect = null;
/* 694 */         if (cosBase instanceof COSObject && this.resources != null && this.resources
/* 695 */           .getResourceCache() != null) {
/*     */ 
/*     */ 
/*     */           
/* 699 */           indirect = (COSObject)cosBase;
/* 700 */           this.colorSpace = this.resources.getResourceCache().getColorSpace(indirect);
/* 701 */           if (this.colorSpace != null)
/*     */           {
/* 703 */             return this.colorSpace;
/*     */           }
/*     */         } 
/* 706 */         this.colorSpace = PDColorSpace.create(cosBase, this.resources);
/* 707 */         if (indirect != null)
/*     */         {
/* 709 */           this.resources.getResourceCache().put(indirect, this.colorSpace);
/*     */         }
/*     */       } else {
/* 712 */         if (isStencil())
/*     */         {
/*     */           
/* 715 */           return (PDColorSpace)PDDeviceGray.INSTANCE;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 720 */         throw new IOException("could not determine color space");
/*     */       } 
/*     */     } 
/* 723 */     return this.colorSpace;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream createInputStream() throws IOException {
/* 729 */     return (InputStream)getStream().createInputStream();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream createInputStream(DecodeOptions options) throws IOException {
/* 735 */     return (InputStream)getStream().createInputStream(options);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream createInputStream(List<String> stopFilters) throws IOException {
/* 741 */     return getStream().createInputStream(stopFilters);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/* 747 */     return (getStream().getCOSObject().getLength() == 0L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColorSpace(PDColorSpace cs) {
/* 753 */     getCOSObject().setItem(COSName.COLORSPACE, (cs != null) ? cs.getCOSObject() : null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeight() {
/* 759 */     return getCOSObject().getInt(COSName.HEIGHT);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHeight(int h) {
/* 765 */     getCOSObject().setInt(COSName.HEIGHT, h);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidth() {
/* 771 */     return getCOSObject().getInt(COSName.WIDTH);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWidth(int w) {
/* 777 */     getCOSObject().setInt(COSName.WIDTH, w);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getInterpolate() {
/* 783 */     return getCOSObject().getBoolean(COSName.INTERPOLATE, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInterpolate(boolean value) {
/* 789 */     getCOSObject().setBoolean(COSName.INTERPOLATE, value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDecode(COSArray decode) {
/* 795 */     getCOSObject().setItem(COSName.DECODE, (COSBase)decode);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getDecode() {
/* 801 */     COSBase decode = getCOSObject().getDictionaryObject(COSName.DECODE);
/* 802 */     if (decode instanceof COSArray)
/*     */     {
/* 804 */       return (COSArray)decode;
/*     */     }
/* 806 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStencil() {
/* 812 */     return getCOSObject().getBoolean(COSName.IMAGE_MASK, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStencil(boolean isStencil) {
/* 818 */     getCOSObject().setBoolean(COSName.IMAGE_MASK, isStencil);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSuffix() {
/* 828 */     List<COSName> filters = getStream().getFilters();
/*     */     
/* 830 */     if (filters == null)
/*     */     {
/* 832 */       return "png";
/*     */     }
/* 834 */     if (filters.contains(COSName.DCT_DECODE))
/*     */     {
/* 836 */       return "jpg";
/*     */     }
/* 838 */     if (filters.contains(COSName.JPX_DECODE))
/*     */     {
/* 840 */       return "jpx";
/*     */     }
/* 842 */     if (filters.contains(COSName.CCITTFAX_DECODE))
/*     */     {
/* 844 */       return "tiff";
/*     */     }
/* 846 */     if (filters.contains(COSName.FLATE_DECODE) || filters
/* 847 */       .contains(COSName.LZW_DECODE) || filters
/* 848 */       .contains(COSName.RUN_LENGTH_DECODE))
/*     */     {
/* 850 */       return "png";
/*     */     }
/* 852 */     if (filters.contains(COSName.JBIG2_DECODE))
/*     */     {
/* 854 */       return "jb2";
/*     */     }
/*     */ 
/*     */     
/* 858 */     LOG.warn("getSuffix() returns null, filters: " + filters);
/* 859 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/graphics/image/PDImageXObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */