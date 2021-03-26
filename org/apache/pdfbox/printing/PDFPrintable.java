/*     */ package org.apache.pdfbox.printing;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.awt.print.PageFormat;
/*     */ import java.awt.print.Printable;
/*     */ import java.awt.print.PrinterException;
/*     */ import java.awt.print.PrinterIOException;
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.rendering.PDFRenderer;
/*     */ import org.apache.pdfbox.rendering.RenderDestination;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PDFPrintable
/*     */   implements Printable
/*     */ {
/*     */   private final PDDocument document;
/*     */   private final PDFRenderer renderer;
/*     */   private final boolean showPageBorder;
/*     */   private final Scaling scaling;
/*     */   private final float dpi;
/*     */   private final boolean center;
/*     */   private boolean subsamplingAllowed = false;
/*  53 */   private RenderingHints renderingHints = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFPrintable(PDDocument document) {
/*  62 */     this(document, Scaling.SHRINK_TO_FIT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFPrintable(PDDocument document, Scaling scaling) {
/*  73 */     this(document, scaling, false, 0.0F);
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
/*     */   public PDFPrintable(PDDocument document, Scaling scaling, boolean showPageBorder) {
/*  85 */     this(document, scaling, showPageBorder, 0.0F);
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
/*     */   public PDFPrintable(PDDocument document, Scaling scaling, boolean showPageBorder, float dpi) {
/*  99 */     this(document, scaling, showPageBorder, dpi, true);
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
/*     */   public PDFPrintable(PDDocument document, Scaling scaling, boolean showPageBorder, float dpi, boolean center) {
/* 115 */     this.document = document;
/* 116 */     this.renderer = new PDFRenderer(document);
/* 117 */     this.scaling = scaling;
/* 118 */     this.showPageBorder = showPageBorder;
/* 119 */     this.dpi = dpi;
/* 120 */     this.center = center;
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
/* 134 */     return this.subsamplingAllowed;
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
/* 148 */     this.subsamplingAllowed = subsamplingAllowed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderingHints getRenderingHints() {
/* 158 */     return this.renderingHints;
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
/* 170 */     this.renderingHints = renderingHints;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
/* 177 */     if (pageIndex < 0 || pageIndex >= this.document.getNumberOfPages())
/*     */     {
/* 179 */       return 1;
/*     */     }
/*     */     
/*     */     try {
/* 183 */       Graphics2D graphics2D = (Graphics2D)graphics;
/*     */       
/* 185 */       PDPage page = this.document.getPage(pageIndex);
/* 186 */       PDRectangle cropBox = getRotatedCropBox(page);
/*     */ 
/*     */       
/* 189 */       double imageableWidth = pageFormat.getImageableWidth();
/* 190 */       double imageableHeight = pageFormat.getImageableHeight();
/*     */       
/* 192 */       double scale = 1.0D;
/* 193 */       if (this.scaling != Scaling.ACTUAL_SIZE) {
/*     */ 
/*     */         
/* 196 */         double scaleX = imageableWidth / cropBox.getWidth();
/* 197 */         double scaleY = imageableHeight / cropBox.getHeight();
/* 198 */         scale = Math.min(scaleX, scaleY);
/*     */ 
/*     */         
/* 201 */         if (scale > 1.0D && this.scaling == Scaling.SHRINK_TO_FIT)
/*     */         {
/* 203 */           scale = 1.0D;
/*     */         }
/*     */ 
/*     */         
/* 207 */         if (scale < 1.0D && this.scaling == Scaling.STRETCH_TO_FIT)
/*     */         {
/* 209 */           scale = 1.0D;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 214 */       graphics2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
/*     */ 
/*     */       
/* 217 */       if (this.center)
/*     */       {
/* 219 */         graphics2D.translate((imageableWidth - cropBox.getWidth() * scale) / 2.0D, (imageableHeight - cropBox
/* 220 */             .getHeight() * scale) / 2.0D);
/*     */       }
/*     */ 
/*     */       
/* 224 */       Graphics2D printerGraphics = null;
/* 225 */       BufferedImage image = null;
/* 226 */       if (this.dpi > 0.0F) {
/*     */         
/* 228 */         float dpiScale = this.dpi / 72.0F;
/* 229 */         image = new BufferedImage((int)(imageableWidth * dpiScale / scale), (int)(imageableHeight * dpiScale / scale), 2);
/*     */ 
/*     */ 
/*     */         
/* 233 */         printerGraphics = graphics2D;
/* 234 */         graphics2D = image.createGraphics();
/*     */ 
/*     */         
/* 237 */         printerGraphics.scale(scale / dpiScale, scale / dpiScale);
/* 238 */         scale = dpiScale;
/*     */       } 
/*     */ 
/*     */       
/* 242 */       AffineTransform transform = (AffineTransform)graphics2D.getTransform().clone();
/* 243 */       graphics2D.setBackground(Color.WHITE);
/* 244 */       this.renderer.setSubsamplingAllowed(this.subsamplingAllowed);
/* 245 */       this.renderer.setRenderingHints(this.renderingHints);
/* 246 */       this.renderer.renderPageToGraphics(pageIndex, graphics2D, (float)scale, (float)scale, RenderDestination.PRINT);
/*     */ 
/*     */       
/* 249 */       if (this.showPageBorder) {
/*     */         
/* 251 */         graphics2D.setTransform(transform);
/* 252 */         graphics2D.setClip(0, 0, (int)imageableWidth, (int)imageableHeight);
/* 253 */         graphics2D.scale(scale, scale);
/* 254 */         graphics2D.setColor(Color.GRAY);
/* 255 */         graphics2D.setStroke(new BasicStroke(0.5F));
/* 256 */         graphics.drawRect(0, 0, (int)cropBox.getWidth(), (int)cropBox.getHeight());
/*     */       } 
/*     */ 
/*     */       
/* 260 */       if (printerGraphics != null) {
/*     */         
/* 262 */         printerGraphics.setBackground(Color.WHITE);
/* 263 */         printerGraphics.clearRect(0, 0, image.getWidth(), image.getHeight());
/* 264 */         printerGraphics.drawImage(image, 0, 0, (ImageObserver)null);
/* 265 */         graphics2D.dispose();
/*     */       } 
/*     */       
/* 268 */       return 0;
/*     */     }
/* 270 */     catch (IOException e) {
/*     */       
/* 272 */       throw new PrinterIOException(e);
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
/*     */   static PDRectangle getRotatedCropBox(PDPage page) {
/* 284 */     PDRectangle cropBox = page.getCropBox();
/* 285 */     int rotationAngle = page.getRotation();
/* 286 */     if (rotationAngle == 90 || rotationAngle == 270)
/*     */     {
/* 288 */       return new PDRectangle(cropBox.getLowerLeftY(), cropBox.getLowerLeftX(), cropBox
/* 289 */           .getHeight(), cropBox.getWidth());
/*     */     }
/*     */ 
/*     */     
/* 293 */     return cropBox;
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
/*     */   static PDRectangle getRotatedMediaBox(PDPage page) {
/* 305 */     PDRectangle mediaBox = page.getMediaBox();
/* 306 */     int rotationAngle = page.getRotation();
/* 307 */     if (rotationAngle == 90 || rotationAngle == 270)
/*     */     {
/* 309 */       return new PDRectangle(mediaBox.getLowerLeftY(), mediaBox.getLowerLeftX(), mediaBox
/* 310 */           .getHeight(), mediaBox.getWidth());
/*     */     }
/*     */ 
/*     */     
/* 314 */     return mediaBox;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/printing/PDFPrintable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */