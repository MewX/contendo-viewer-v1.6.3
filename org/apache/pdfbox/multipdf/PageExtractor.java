/*     */ package org.apache.pdfbox.multipdf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PageExtractor
/*     */ {
/*     */   private PDDocument sourceDocument;
/*  33 */   private int startPage = 1;
/*     */   
/*  35 */   private int endPage = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PageExtractor(PDDocument sourceDocument) {
/*  43 */     this.sourceDocument = sourceDocument;
/*  44 */     this.endPage = sourceDocument.getNumberOfPages();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PageExtractor(PDDocument sourceDocument, int startPage, int endPage) {
/*  55 */     this(sourceDocument);
/*  56 */     this.startPage = startPage;
/*  57 */     this.endPage = endPage;
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
/*     */   public PDDocument extract() throws IOException {
/*  74 */     PDDocument extractedDocument = new PDDocument();
/*  75 */     extractedDocument.setDocumentInformation(this.sourceDocument.getDocumentInformation());
/*  76 */     extractedDocument.getDocumentCatalog().setViewerPreferences(this.sourceDocument
/*  77 */         .getDocumentCatalog().getViewerPreferences());
/*     */     
/*  79 */     for (int i = this.startPage; i <= this.endPage; i++) {
/*     */       
/*  81 */       PDPage page = this.sourceDocument.getPage(i - 1);
/*  82 */       PDPage imported = extractedDocument.importPage(page);
/*  83 */       imported.setCropBox(page.getCropBox());
/*  84 */       imported.setMediaBox(page.getMediaBox());
/*  85 */       imported.setResources(page.getResources());
/*  86 */       imported.setRotation(page.getRotation());
/*     */     } 
/*     */     
/*  89 */     return extractedDocument;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStartPage() {
/*  98 */     return this.startPage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartPage(int startPage) {
/* 107 */     this.startPage = startPage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEndPage() {
/* 116 */     return this.endPage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndPage(int endPage) {
/* 125 */     this.endPage = endPage;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/multipdf/PageExtractor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */