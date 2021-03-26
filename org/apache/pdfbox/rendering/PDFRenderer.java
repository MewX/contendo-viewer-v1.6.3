/*     */ package org.apache.pdfbox.rendering;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.DisplayMode;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.io.IOException;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.graphics.blend.BlendMode;
/*     */ import org.apache.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup;
/*     */ import org.apache.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentProperties;
/*     */ import org.apache.pdfbox.pdmodel.graphics.state.PDExtendedGraphicsState;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.AnnotationFilter;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDFRenderer
/*     */ {
/*  52 */   private static final Log LOG = LogFactory.getLog(PDFRenderer.class);
/*     */ 
/*     */ 
/*     */   
/*     */   protected final PDDocument document;
/*     */ 
/*     */ 
/*     */   
/*  60 */   private AnnotationFilter annotationFilter = new AnnotationFilter()
/*     */     {
/*     */       
/*     */       public boolean accept(PDAnnotation annotation)
/*     */       {
/*  65 */         return true;
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   private boolean subsamplingAllowed = false;
/*     */   
/*     */   private RenderDestination defaultDestination;
/*  73 */   private RenderingHints renderingHints = null;
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage pageImage;
/*     */ 
/*     */   
/*     */   private static boolean kcmsLogged = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFRenderer(PDDocument document) {
/*  85 */     this.document = document;
/*     */     
/*  87 */     if (!kcmsLogged) {
/*     */       
/*  89 */       suggestKCMS();
/*  90 */       kcmsLogged = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnnotationFilter getAnnotationsFilter() {
/* 101 */     return this.annotationFilter;
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
/*     */   public void setAnnotationsFilter(AnnotationFilter annotationsFilter) {
/* 113 */     this.annotationFilter = annotationsFilter;
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
/*     */   public boolean isSubsamplingAllowed() {
/* 127 */     return this.subsamplingAllowed;
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
/*     */   public void setSubsamplingAllowed(boolean subsamplingAllowed) {
/* 141 */     this.subsamplingAllowed = subsamplingAllowed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderDestination getDefaultDestination() {
/* 149 */     return this.defaultDestination;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultDestination(RenderDestination defaultDestination) {
/* 157 */     this.defaultDestination = defaultDestination;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderingHints getRenderingHints() {
/* 167 */     return this.renderingHints;
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
/*     */   public void setRenderingHints(RenderingHints renderingHints) {
/* 179 */     this.renderingHints = renderingHints;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage renderImage(int pageIndex) throws IOException {
/* 190 */     return renderImage(pageIndex, 1.0F);
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
/*     */   public BufferedImage renderImage(int pageIndex, float scale) throws IOException {
/* 203 */     return renderImage(pageIndex, scale, ImageType.RGB);
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
/*     */   public BufferedImage renderImageWithDPI(int pageIndex, float dpi) throws IOException {
/* 215 */     return renderImage(pageIndex, dpi / 72.0F, ImageType.RGB);
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
/*     */   public BufferedImage renderImageWithDPI(int pageIndex, float dpi, ImageType imageType) throws IOException {
/* 229 */     return renderImage(pageIndex, dpi / 72.0F, imageType);
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
/*     */   public BufferedImage renderImage(int pageIndex, float scale, ImageType imageType) throws IOException {
/* 243 */     return renderImage(pageIndex, scale, imageType, (this.defaultDestination == null) ? RenderDestination.EXPORT : this.defaultDestination);
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
/*     */   public BufferedImage renderImage(int pageIndex, float scale, ImageType imageType, RenderDestination destination) throws IOException {
/*     */     BufferedImage image;
/* 259 */     PDPage page = this.document.getPage(pageIndex);
/*     */     
/* 261 */     PDRectangle cropbBox = page.getCropBox();
/* 262 */     float widthPt = cropbBox.getWidth();
/* 263 */     float heightPt = cropbBox.getHeight();
/*     */ 
/*     */     
/* 266 */     int widthPx = (int)Math.max(Math.floor((widthPt * scale)), 1.0D);
/* 267 */     int heightPx = (int)Math.max(Math.floor((heightPt * scale)), 1.0D);
/*     */ 
/*     */     
/* 270 */     if (widthPx * heightPx > 2147483647L)
/*     */     {
/* 272 */       throw new IOException("Maximum size of image exceeded (w * h * scale) = " + widthPt + " * " + heightPt + " * " + scale + " > " + 2147483647);
/*     */     }
/*     */ 
/*     */     
/* 276 */     int rotationAngle = page.getRotation();
/*     */     
/* 278 */     int bimType = imageType.toBufferedImageType();
/* 279 */     if (imageType != ImageType.ARGB && hasBlendMode(page))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 285 */       bimType = 2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 290 */     if (rotationAngle == 90 || rotationAngle == 270) {
/*     */       
/* 292 */       image = new BufferedImage(heightPx, widthPx, bimType);
/*     */     }
/*     */     else {
/*     */       
/* 296 */       image = new BufferedImage(widthPx, heightPx, bimType);
/*     */     } 
/*     */     
/* 299 */     this.pageImage = image;
/*     */ 
/*     */     
/* 302 */     Graphics2D g = image.createGraphics();
/* 303 */     if (image.getType() == 2) {
/*     */       
/* 305 */       g.setBackground(new Color(0, 0, 0, 0));
/*     */     }
/*     */     else {
/*     */       
/* 309 */       g.setBackground(Color.WHITE);
/*     */     } 
/* 311 */     g.clearRect(0, 0, image.getWidth(), image.getHeight());
/*     */     
/* 313 */     transform(g, page, scale, scale);
/*     */ 
/*     */ 
/*     */     
/* 317 */     RenderingHints actualRenderingHints = (this.renderingHints == null) ? createDefaultRenderingHints(g) : this.renderingHints;
/* 318 */     PageDrawerParameters parameters = new PageDrawerParameters(this, page, this.subsamplingAllowed, destination, actualRenderingHints);
/*     */     
/* 320 */     PageDrawer drawer = createPageDrawer(parameters);
/* 321 */     drawer.drawPage(g, page.getCropBox());
/*     */     
/* 323 */     g.dispose();
/*     */     
/* 325 */     if (image.getType() != imageType.toBufferedImageType()) {
/*     */ 
/*     */ 
/*     */       
/* 329 */       BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), imageType.toBufferedImageType());
/* 330 */       Graphics2D dstGraphics = newImage.createGraphics();
/* 331 */       dstGraphics.setBackground(Color.WHITE);
/* 332 */       dstGraphics.clearRect(0, 0, image.getWidth(), image.getHeight());
/* 333 */       dstGraphics.drawImage(image, 0, 0, (ImageObserver)null);
/* 334 */       dstGraphics.dispose();
/* 335 */       image = newImage;
/*     */     } 
/*     */     
/* 338 */     return image;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderPageToGraphics(int pageIndex, Graphics2D graphics) throws IOException {
/* 349 */     renderPageToGraphics(pageIndex, graphics, 1.0F);
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
/*     */   public void renderPageToGraphics(int pageIndex, Graphics2D graphics, float scale) throws IOException {
/* 362 */     renderPageToGraphics(pageIndex, graphics, scale, scale);
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
/*     */   public void renderPageToGraphics(int pageIndex, Graphics2D graphics, float scaleX, float scaleY) throws IOException {
/* 377 */     renderPageToGraphics(pageIndex, graphics, scaleX, scaleY, (this.defaultDestination == null) ? RenderDestination.VIEW : this.defaultDestination);
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
/*     */   public void renderPageToGraphics(int pageIndex, Graphics2D graphics, float scaleX, float scaleY, RenderDestination destination) throws IOException {
/* 394 */     PDPage page = this.document.getPage(pageIndex);
/*     */ 
/*     */     
/* 397 */     transform(graphics, page, scaleX, scaleY);
/*     */     
/* 399 */     PDRectangle cropBox = page.getCropBox();
/* 400 */     graphics.clearRect(0, 0, (int)cropBox.getWidth(), (int)cropBox.getHeight());
/*     */ 
/*     */ 
/*     */     
/* 404 */     RenderingHints actualRenderingHints = (this.renderingHints == null) ? createDefaultRenderingHints(graphics) : this.renderingHints;
/* 405 */     PageDrawerParameters parameters = new PageDrawerParameters(this, page, this.subsamplingAllowed, destination, actualRenderingHints);
/*     */     
/* 407 */     PageDrawer drawer = createPageDrawer(parameters);
/* 408 */     drawer.drawPage(graphics, cropBox);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isGroupEnabled(PDOptionalContentGroup group) {
/* 418 */     PDOptionalContentProperties ocProperties = this.document.getDocumentCatalog().getOCProperties();
/* 419 */     return (ocProperties == null || ocProperties.isGroupEnabled(group));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void transform(Graphics2D graphics, PDPage page, float scaleX, float scaleY) {
/* 425 */     graphics.scale(scaleX, scaleY);
/*     */ 
/*     */     
/* 428 */     int rotationAngle = page.getRotation();
/* 429 */     PDRectangle cropBox = page.getCropBox();
/*     */     
/* 431 */     if (rotationAngle != 0) {
/*     */       
/* 433 */       float translateX = 0.0F;
/* 434 */       float translateY = 0.0F;
/* 435 */       switch (rotationAngle) {
/*     */         
/*     */         case 90:
/* 438 */           translateX = cropBox.getHeight();
/*     */           break;
/*     */         case 270:
/* 441 */           translateY = cropBox.getWidth();
/*     */           break;
/*     */         case 180:
/* 444 */           translateX = cropBox.getWidth();
/* 445 */           translateY = cropBox.getHeight();
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/* 450 */       graphics.translate(translateX, translateY);
/* 451 */       graphics.rotate(Math.toRadians(rotationAngle));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isBitonal(Graphics2D graphics) {
/* 457 */     GraphicsConfiguration deviceConfiguration = graphics.getDeviceConfiguration();
/* 458 */     if (deviceConfiguration == null)
/*     */     {
/* 460 */       return false;
/*     */     }
/* 462 */     GraphicsDevice device = deviceConfiguration.getDevice();
/* 463 */     if (device == null)
/*     */     {
/* 465 */       return false;
/*     */     }
/* 467 */     DisplayMode displayMode = device.getDisplayMode();
/* 468 */     if (displayMode == null)
/*     */     {
/* 470 */       return false;
/*     */     }
/* 472 */     return (displayMode.getBitDepth() == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   private RenderingHints createDefaultRenderingHints(Graphics2D graphics) {
/* 477 */     RenderingHints r = new RenderingHints(null);
/* 478 */     r.put(RenderingHints.KEY_INTERPOLATION, isBitonal(graphics) ? RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR : RenderingHints.VALUE_INTERPOLATION_BICUBIC);
/*     */ 
/*     */     
/* 481 */     r.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
/* 482 */     r.put(RenderingHints.KEY_ANTIALIASING, isBitonal(graphics) ? RenderingHints.VALUE_ANTIALIAS_OFF : RenderingHints.VALUE_ANTIALIAS_ON);
/*     */ 
/*     */     
/* 485 */     return r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PageDrawer createPageDrawer(PageDrawerParameters parameters) throws IOException {
/* 493 */     PageDrawer pageDrawer = new PageDrawer(parameters);
/* 494 */     pageDrawer.setAnnotationFilter(this.annotationFilter);
/* 495 */     return pageDrawer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasBlendMode(PDPage page) {
/* 501 */     PDResources resources = page.getResources();
/* 502 */     if (resources == null)
/*     */     {
/* 504 */       return false;
/*     */     }
/* 506 */     for (COSName name : resources.getExtGStateNames()) {
/*     */       
/* 508 */       PDExtendedGraphicsState extGState = resources.getExtGState(name);
/* 509 */       if (extGState == null) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 515 */       BlendMode blendMode = extGState.getBlendMode();
/* 516 */       if (blendMode != BlendMode.NORMAL)
/*     */       {
/* 518 */         return true;
/*     */       }
/*     */     } 
/* 521 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   BufferedImage getPageImage() {
/* 531 */     return this.pageImage;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void suggestKCMS() {
/* 536 */     String cmmProperty = System.getProperty("sun.java2d.cmm");
/* 537 */     if (isMinJdk8() && !"sun.java2d.cmm.kcms.KcmsServiceProvider".equals(cmmProperty)) {
/*     */       
/*     */       try {
/*     */ 
/*     */         
/* 542 */         Class.forName("sun.java2d.cmm.kcms.KcmsServiceProvider");
/*     */         
/* 544 */         String version = System.getProperty("java.version");
/* 545 */         if (version == null || 
/* 546 */           isGoodVersion(version, "1.8.0_(\\d+)", 191) || 
/* 547 */           isGoodVersion(version, "9.0.(\\d+)", 4)) {
/*     */           return;
/*     */         }
/*     */         
/* 551 */         LOG.info("Your current java version is: " + version);
/* 552 */         LOG.info("To get higher rendering speed on old java 1.8 or 9 versions,");
/* 553 */         LOG.info("  update to the latest 1.8 or 9 version (>= 1.8.0_191 or >= 9.0.4),");
/* 554 */         LOG.info("  or");
/* 555 */         LOG.info("  use the option -Dsun.java2d.cmm=sun.java2d.cmm.kcms.KcmsServiceProvider");
/* 556 */         LOG.info("  or call System.setProperty(\"sun.java2d.cmm\", \"sun.java2d.cmm.kcms.KcmsServiceProvider\")");
/*     */       }
/* 558 */       catch (ClassNotFoundException classNotFoundException) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isGoodVersion(String version, String regex, int min) {
/* 567 */     Matcher matcher = Pattern.compile(regex).matcher(version);
/* 568 */     if (matcher.matches() && matcher.groupCount() >= 1) {
/*     */       
/*     */       try {
/*     */         
/* 572 */         int v = Integer.parseInt(matcher.group(1));
/* 573 */         if (v >= min)
/*     */         {
/*     */           
/* 576 */           return true;
/*     */         }
/*     */       }
/* 579 */       catch (NumberFormatException ex) {
/*     */         
/* 581 */         return true;
/*     */       } 
/*     */     }
/* 584 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isMinJdk8() {
/* 590 */     String version = System.getProperty("java.specification.version");
/* 591 */     StringTokenizer st = new StringTokenizer(version, ".");
/*     */     
/*     */     try {
/* 594 */       int major = Integer.parseInt(st.nextToken());
/* 595 */       int minor = 0;
/* 596 */       if (st.hasMoreTokens())
/*     */       {
/* 598 */         minor = Integer.parseInt(st.nextToken());
/*     */       }
/* 600 */       return (major > 1 || (major == 1 && minor >= 8));
/*     */     }
/* 602 */     catch (NumberFormatException nfe) {
/*     */ 
/*     */       
/* 605 */       return true;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/rendering/PDFRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */