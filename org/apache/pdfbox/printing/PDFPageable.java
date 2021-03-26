/*     */ package org.apache.pdfbox.printing;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.print.Book;
/*     */ import java.awt.print.PageFormat;
/*     */ import java.awt.print.Paper;
/*     */ import java.awt.print.Printable;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PDFPageable
/*     */   extends Book
/*     */ {
/*     */   private final PDDocument document;
/*     */   private final boolean showPageBorder;
/*     */   private final float dpi;
/*     */   private final Orientation orientation;
/*     */   private boolean subsamplingAllowed = false;
/*  41 */   private RenderingHints renderingHints = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFPageable(PDDocument document) {
/*  50 */     this(document, Orientation.AUTO, false, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFPageable(PDDocument document, Orientation orientation) {
/*  61 */     this(document, orientation, false, 0.0F);
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
/*     */   public PDFPageable(PDDocument document, Orientation orientation, boolean showPageBorder) {
/*  74 */     this(document, orientation, showPageBorder, 0.0F);
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
/*     */   public PDFPageable(PDDocument document, Orientation orientation, boolean showPageBorder, float dpi) {
/*  89 */     this.document = document;
/*  90 */     this.orientation = orientation;
/*  91 */     this.showPageBorder = showPageBorder;
/*  92 */     this.dpi = dpi;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderingHints getRenderingHints() {
/* 102 */     return this.renderingHints;
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
/* 114 */     this.renderingHints = renderingHints;
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
/* 128 */     return this.subsamplingAllowed;
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
/* 142 */     this.subsamplingAllowed = subsamplingAllowed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfPages() {
/* 148 */     return this.document.getNumberOfPages();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PageFormat getPageFormat(int pageIndex) {
/*     */     Paper paper;
/*     */     boolean isLandscape;
/* 159 */     PDPage page = this.document.getPage(pageIndex);
/* 160 */     PDRectangle mediaBox = PDFPrintable.getRotatedMediaBox(page);
/* 161 */     PDRectangle cropBox = PDFPrintable.getRotatedCropBox(page);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     if (mediaBox.getWidth() > mediaBox.getHeight()) {
/*     */ 
/*     */       
/* 174 */       paper = new Paper();
/* 175 */       paper.setSize(mediaBox.getHeight(), mediaBox.getWidth());
/* 176 */       paper.setImageableArea(cropBox.getLowerLeftY(), cropBox.getLowerLeftX(), cropBox
/* 177 */           .getHeight(), cropBox.getWidth());
/* 178 */       isLandscape = true;
/*     */     }
/*     */     else {
/*     */       
/* 182 */       paper = new Paper();
/* 183 */       paper.setSize(mediaBox.getWidth(), mediaBox.getHeight());
/* 184 */       paper.setImageableArea(cropBox.getLowerLeftX(), cropBox.getLowerLeftY(), cropBox
/* 185 */           .getWidth(), cropBox.getHeight());
/* 186 */       isLandscape = false;
/*     */     } 
/*     */     
/* 189 */     PageFormat format = new PageFormat();
/* 190 */     format.setPaper(paper);
/*     */ 
/*     */     
/* 193 */     switch (this.orientation) {
/*     */       
/*     */       case AUTO:
/* 196 */         format.setOrientation(isLandscape ? 0 : 1);
/*     */         break;
/*     */       case LANDSCAPE:
/* 199 */         format.setOrientation(0);
/*     */         break;
/*     */       case PORTRAIT:
/* 202 */         format.setOrientation(1);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 208 */     return format;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Printable getPrintable(int i) {
/* 214 */     if (i >= getNumberOfPages())
/*     */     {
/* 216 */       throw new IndexOutOfBoundsException(i + " >= " + getNumberOfPages());
/*     */     }
/* 218 */     PDFPrintable printable = new PDFPrintable(this.document, Scaling.ACTUAL_SIZE, this.showPageBorder, this.dpi);
/* 219 */     printable.setSubsamplingAllowed(this.subsamplingAllowed);
/* 220 */     printable.setRenderingHints(this.renderingHints);
/* 221 */     return printable;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/printing/PDFPageable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */