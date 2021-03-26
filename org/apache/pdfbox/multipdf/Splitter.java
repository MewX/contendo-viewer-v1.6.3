/*     */ package org.apache.pdfbox.multipdf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.io.MemoryUsageSetting;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDActionGoTo;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
/*     */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
/*     */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Splitter
/*     */ {
/*     */   private PDDocument sourceDocument;
/*     */   private PDDocument currentDestinationDocument;
/*  44 */   private int splitLength = 1;
/*  45 */   private int startPage = Integer.MIN_VALUE;
/*  46 */   private int endPage = Integer.MAX_VALUE;
/*     */   
/*     */   private List<PDDocument> destinationDocuments;
/*  49 */   private int currentPageNumber = 0;
/*     */   
/*  51 */   private MemoryUsageSetting memoryUsageSetting = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MemoryUsageSetting getMemoryUsageSetting() {
/*  58 */     return this.memoryUsageSetting;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMemoryUsageSetting(MemoryUsageSetting memoryUsageSetting) {
/*  68 */     this.memoryUsageSetting = memoryUsageSetting;
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
/*     */   public List<PDDocument> split(PDDocument document) throws IOException {
/*  82 */     this.destinationDocuments = new ArrayList<PDDocument>();
/*  83 */     this.sourceDocument = document;
/*  84 */     processPages();
/*  85 */     return this.destinationDocuments;
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
/*     */   public void setSplitAtPage(int split) {
/* 100 */     if (split <= 0)
/*     */     {
/* 102 */       throw new IllegalArgumentException("Number of pages is smaller than one");
/*     */     }
/* 104 */     this.splitLength = split;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartPage(int start) {
/* 115 */     if (start <= 0)
/*     */     {
/* 117 */       throw new IllegalArgumentException("Start page is smaller than one");
/*     */     }
/* 119 */     this.startPage = start;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndPage(int end) {
/* 130 */     if (end <= 0)
/*     */     {
/* 132 */       throw new IllegalArgumentException("End page is smaller than one");
/*     */     }
/* 134 */     this.endPage = end;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void processPages() throws IOException {
/* 144 */     for (PDPage page : this.sourceDocument.getPages()) {
/*     */       
/* 146 */       if (this.currentPageNumber + 1 >= this.startPage && this.currentPageNumber + 1 <= this.endPage) {
/*     */         
/* 148 */         processPage(page);
/* 149 */         this.currentPageNumber++;
/*     */         
/*     */         continue;
/*     */       } 
/* 153 */       if (this.currentPageNumber > this.endPage) {
/*     */         break;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 159 */       this.currentPageNumber++;
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
/*     */   private void createNewDocumentIfNecessary() throws IOException {
/* 172 */     if (splitAtPage(this.currentPageNumber) || this.currentDestinationDocument == null) {
/*     */       
/* 174 */       this.currentDestinationDocument = createNewDocument();
/* 175 */       this.destinationDocuments.add(this.currentDestinationDocument);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean splitAtPage(int pageNumber) {
/* 196 */     return ((pageNumber + 1 - Math.max(1, this.startPage)) % this.splitLength == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PDDocument createNewDocument() throws IOException {
/* 207 */     PDDocument document = (this.memoryUsageSetting == null) ? new PDDocument() : new PDDocument(this.memoryUsageSetting);
/*     */     
/* 209 */     document.getDocument().setVersion(getSourceDocument().getVersion());
/* 210 */     document.setDocumentInformation(getSourceDocument().getDocumentInformation());
/* 211 */     document.getDocumentCatalog().setViewerPreferences(
/* 212 */         getSourceDocument().getDocumentCatalog().getViewerPreferences());
/* 213 */     return document;
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
/*     */   protected void processPage(PDPage page) throws IOException {
/* 225 */     createNewDocumentIfNecessary();
/*     */     
/* 227 */     PDPage imported = getDestinationDocument().importPage(page);
/* 228 */     imported.setResources(page.getResources());
/*     */     
/* 230 */     processAnnotations(imported);
/*     */   }
/*     */ 
/*     */   
/*     */   private void processAnnotations(PDPage imported) throws IOException {
/* 235 */     List<PDAnnotation> annotations = imported.getAnnotations();
/* 236 */     for (PDAnnotation annotation : annotations) {
/*     */       
/* 238 */       if (annotation instanceof PDAnnotationLink) {
/*     */         
/* 240 */         PDAnnotationLink link = (PDAnnotationLink)annotation;
/* 241 */         PDDestination destination = link.getDestination();
/* 242 */         if (destination == null && link.getAction() != null) {
/*     */           
/* 244 */           PDAction action = link.getAction();
/* 245 */           if (action instanceof PDActionGoTo)
/*     */           {
/* 247 */             destination = ((PDActionGoTo)action).getDestination();
/*     */           }
/*     */         } 
/* 250 */         if (destination instanceof PDPageDestination)
/*     */         {
/*     */           
/* 253 */           ((PDPageDestination)destination).setPage(null);
/*     */         }
/*     */       } 
/*     */       
/* 257 */       annotation.setPage(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final PDDocument getSourceDocument() {
/* 267 */     return this.sourceDocument;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final PDDocument getDestinationDocument() {
/* 277 */     return this.currentDestinationDocument;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/multipdf/Splitter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */