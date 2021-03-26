/*     */ package org.apache.pdfbox.multipdf;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.math.BigDecimal;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInputStream;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Overlay
/*     */   implements Closeable
/*     */ {
/*     */   private LayoutPage defaultOverlayPage;
/*     */   private LayoutPage firstPageOverlayPage;
/*     */   private LayoutPage lastPageOverlayPage;
/*     */   private LayoutPage oddPageOverlayPage;
/*     */   private LayoutPage evenPageOverlayPage;
/*     */   
/*     */   public enum Position
/*     */   {
/*  58 */     FOREGROUND, BACKGROUND;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   private final Set<PDDocument> openDocuments = new HashSet<PDDocument>();
/*  68 */   private Map<Integer, LayoutPage> specificPageOverlayPage = new HashMap<Integer, LayoutPage>();
/*     */   
/*  70 */   private Position position = Position.BACKGROUND;
/*     */   
/*  72 */   private String inputFileName = null;
/*  73 */   private PDDocument inputPDFDocument = null;
/*     */   
/*  75 */   private String defaultOverlayFilename = null;
/*  76 */   private PDDocument defaultOverlay = null;
/*     */   
/*  78 */   private String firstPageOverlayFilename = null;
/*  79 */   private PDDocument firstPageOverlay = null;
/*     */   
/*  81 */   private String lastPageOverlayFilename = null;
/*  82 */   private PDDocument lastPageOverlay = null;
/*     */   
/*  84 */   private String allPagesOverlayFilename = null;
/*  85 */   private PDDocument allPagesOverlay = null;
/*     */   
/*  87 */   private String oddPageOverlayFilename = null;
/*  88 */   private PDDocument oddPageOverlay = null;
/*     */   
/*  90 */   private String evenPageOverlayFilename = null;
/*  91 */   private PDDocument evenPageOverlay = null;
/*     */   
/*  93 */   private int numberOfOverlayPages = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean useAllOverlayPages = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDocument overlay(Map<Integer, String> specificPageOverlayFile) throws IOException {
/* 110 */     Map<String, PDDocument> loadedDocuments = new HashMap<String, PDDocument>();
/* 111 */     Map<PDDocument, LayoutPage> layouts = new HashMap<PDDocument, LayoutPage>();
/* 112 */     loadPDFs();
/* 113 */     for (Map.Entry<Integer, String> e : specificPageOverlayFile.entrySet()) {
/*     */       
/* 115 */       PDDocument doc = loadedDocuments.get(e.getValue());
/* 116 */       if (doc == null) {
/*     */         
/* 118 */         doc = loadPDF(e.getValue());
/* 119 */         loadedDocuments.put(e.getValue(), doc);
/* 120 */         layouts.put(doc, getLayoutPage(doc));
/*     */       } 
/* 122 */       this.openDocuments.add(doc);
/* 123 */       this.specificPageOverlayPage.put(e.getKey(), layouts.get(doc));
/*     */     } 
/* 125 */     processPages(this.inputPDFDocument);
/* 126 */     return this.inputPDFDocument;
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
/*     */   public PDDocument overlayDocuments(Map<Integer, PDDocument> specificPageOverlayDocuments) throws IOException {
/* 144 */     loadPDFs();
/* 145 */     for (Map.Entry<Integer, PDDocument> e : specificPageOverlayDocuments.entrySet()) {
/*     */       
/* 147 */       PDDocument doc = e.getValue();
/* 148 */       if (doc != null)
/*     */       {
/* 150 */         this.specificPageOverlayPage.put(e.getKey(), getLayoutPage(doc));
/*     */       }
/*     */     } 
/* 153 */     processPages(this.inputPDFDocument);
/* 154 */     return this.inputPDFDocument;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 165 */     if (this.defaultOverlay != null)
/*     */     {
/* 167 */       this.defaultOverlay.close();
/*     */     }
/* 169 */     if (this.firstPageOverlay != null)
/*     */     {
/* 171 */       this.firstPageOverlay.close();
/*     */     }
/* 173 */     if (this.lastPageOverlay != null)
/*     */     {
/* 175 */       this.lastPageOverlay.close();
/*     */     }
/* 177 */     if (this.allPagesOverlay != null)
/*     */     {
/* 179 */       this.allPagesOverlay.close();
/*     */     }
/* 181 */     if (this.oddPageOverlay != null)
/*     */     {
/* 183 */       this.oddPageOverlay.close();
/*     */     }
/* 185 */     if (this.evenPageOverlay != null)
/*     */     {
/* 187 */       this.evenPageOverlay.close();
/*     */     }
/* 189 */     for (PDDocument doc : this.openDocuments)
/*     */     {
/* 191 */       doc.close();
/*     */     }
/* 193 */     this.openDocuments.clear();
/* 194 */     this.specificPageOverlayPage.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void loadPDFs() throws IOException {
/* 200 */     if (this.inputFileName != null)
/*     */     {
/* 202 */       this.inputPDFDocument = loadPDF(this.inputFileName);
/*     */     }
/*     */     
/* 205 */     if (this.defaultOverlayFilename != null)
/*     */     {
/* 207 */       this.defaultOverlay = loadPDF(this.defaultOverlayFilename);
/*     */     }
/* 209 */     if (this.defaultOverlay != null)
/*     */     {
/* 211 */       this.defaultOverlayPage = getLayoutPage(this.defaultOverlay);
/*     */     }
/*     */     
/* 214 */     if (this.firstPageOverlayFilename != null)
/*     */     {
/* 216 */       this.firstPageOverlay = loadPDF(this.firstPageOverlayFilename);
/*     */     }
/* 218 */     if (this.firstPageOverlay != null)
/*     */     {
/* 220 */       this.firstPageOverlayPage = getLayoutPage(this.firstPageOverlay);
/*     */     }
/*     */     
/* 223 */     if (this.lastPageOverlayFilename != null)
/*     */     {
/* 225 */       this.lastPageOverlay = loadPDF(this.lastPageOverlayFilename);
/*     */     }
/* 227 */     if (this.lastPageOverlay != null)
/*     */     {
/* 229 */       this.lastPageOverlayPage = getLayoutPage(this.lastPageOverlay);
/*     */     }
/*     */     
/* 232 */     if (this.oddPageOverlayFilename != null)
/*     */     {
/* 234 */       this.oddPageOverlay = loadPDF(this.oddPageOverlayFilename);
/*     */     }
/* 236 */     if (this.oddPageOverlay != null)
/*     */     {
/* 238 */       this.oddPageOverlayPage = getLayoutPage(this.oddPageOverlay);
/*     */     }
/*     */     
/* 241 */     if (this.evenPageOverlayFilename != null)
/*     */     {
/* 243 */       this.evenPageOverlay = loadPDF(this.evenPageOverlayFilename);
/*     */     }
/* 245 */     if (this.evenPageOverlay != null)
/*     */     {
/* 247 */       this.evenPageOverlayPage = getLayoutPage(this.evenPageOverlay);
/*     */     }
/*     */     
/* 250 */     if (this.allPagesOverlayFilename != null)
/*     */     {
/* 252 */       this.allPagesOverlay = loadPDF(this.allPagesOverlayFilename);
/*     */     }
/* 254 */     if (this.allPagesOverlay != null) {
/*     */       
/* 256 */       this.specificPageOverlayPage = getLayoutPages(this.allPagesOverlay);
/* 257 */       this.useAllOverlayPages = true;
/* 258 */       this.numberOfOverlayPages = this.specificPageOverlayPage.size();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private PDDocument loadPDF(String pdfName) throws IOException {
/* 264 */     return PDDocument.load(new File(pdfName));
/*     */   }
/*     */ 
/*     */   
/*     */   private static final class LayoutPage
/*     */   {
/*     */     private final PDRectangle overlayMediaBox;
/*     */     
/*     */     private final COSStream overlayContentStream;
/*     */     
/*     */     private final COSDictionary overlayResources;
/*     */ 
/*     */     
/*     */     private LayoutPage(PDRectangle mediaBox, COSStream contentStream, COSDictionary resources) {
/* 278 */       this.overlayMediaBox = mediaBox;
/* 279 */       this.overlayContentStream = contentStream;
/* 280 */       this.overlayResources = resources;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private LayoutPage getLayoutPage(PDDocument doc) throws IOException {
/* 286 */     PDPage page = doc.getPage(0);
/* 287 */     COSBase contents = page.getCOSObject().getDictionaryObject(COSName.CONTENTS);
/* 288 */     PDResources resources = page.getResources();
/* 289 */     if (resources == null)
/*     */     {
/* 291 */       resources = new PDResources();
/*     */     }
/* 293 */     return new LayoutPage(page.getMediaBox(), createCombinedContentStream(contents), resources
/* 294 */         .getCOSObject());
/*     */   }
/*     */ 
/*     */   
/*     */   private Map<Integer, LayoutPage> getLayoutPages(PDDocument doc) throws IOException {
/* 299 */     int numberOfPages = doc.getNumberOfPages();
/* 300 */     Map<Integer, LayoutPage> layoutPages = new HashMap<Integer, LayoutPage>(numberOfPages);
/* 301 */     for (int i = 0; i < numberOfPages; i++) {
/*     */       
/* 303 */       PDPage page = doc.getPage(i);
/* 304 */       COSBase contents = page.getCOSObject().getDictionaryObject(COSName.CONTENTS);
/* 305 */       PDResources resources = page.getResources();
/* 306 */       if (resources == null)
/*     */       {
/* 308 */         resources = new PDResources();
/*     */       }
/* 310 */       layoutPages.put(Integer.valueOf(i), new LayoutPage(page.getMediaBox(), createCombinedContentStream(contents), resources
/* 311 */             .getCOSObject()));
/*     */     } 
/* 313 */     return layoutPages;
/*     */   }
/*     */ 
/*     */   
/*     */   private COSStream createCombinedContentStream(COSBase contents) throws IOException {
/* 318 */     List<COSStream> contentStreams = createContentStreamList(contents);
/*     */     
/* 320 */     COSStream concatStream = this.inputPDFDocument.getDocument().createCOSStream();
/* 321 */     OutputStream out = concatStream.createOutputStream((COSBase)COSName.FLATE_DECODE);
/* 322 */     for (COSStream contentStream : contentStreams) {
/*     */       
/* 324 */       COSInputStream cOSInputStream = contentStream.createInputStream();
/* 325 */       IOUtils.copy((InputStream)cOSInputStream, out);
/* 326 */       out.flush();
/* 327 */       cOSInputStream.close();
/*     */     } 
/* 329 */     out.close();
/* 330 */     return concatStream;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private List<COSStream> createContentStreamList(COSBase contents) throws IOException {
/* 336 */     List<COSStream> contentStreams = new ArrayList<COSStream>();
/* 337 */     if (contents == null)
/*     */     {
/* 339 */       return contentStreams;
/*     */     }
/* 341 */     if (contents instanceof COSStream) {
/*     */       
/* 343 */       contentStreams.add((COSStream)contents);
/*     */     }
/* 345 */     else if (contents instanceof COSArray) {
/*     */       
/* 347 */       for (COSBase item : contents)
/*     */       {
/* 349 */         contentStreams.addAll(createContentStreamList(item));
/*     */       }
/*     */     }
/* 352 */     else if (contents instanceof COSObject) {
/*     */       
/* 354 */       contentStreams.addAll(createContentStreamList(((COSObject)contents).getObject()));
/*     */     }
/*     */     else {
/*     */       
/* 358 */       throw new IOException("Unknown content type: " + contents.getClass().getName());
/*     */     } 
/* 360 */     return contentStreams;
/*     */   }
/*     */ 
/*     */   
/*     */   private void processPages(PDDocument document) throws IOException {
/* 365 */     int pageCounter = 0;
/* 366 */     for (PDPage page : document.getPages()) {
/*     */       
/* 368 */       pageCounter++;
/* 369 */       COSDictionary pageDictionary = page.getCOSObject();
/* 370 */       COSBase originalContent = pageDictionary.getDictionaryObject(COSName.CONTENTS);
/* 371 */       COSArray newContentArray = new COSArray();
/* 372 */       LayoutPage layoutPage = getLayoutPage(pageCounter, document.getNumberOfPages());
/* 373 */       if (layoutPage == null) {
/*     */         continue;
/*     */       }
/*     */       
/* 377 */       switch (this.position) {
/*     */ 
/*     */         
/*     */         case FOREGROUND:
/* 381 */           newContentArray.add((COSBase)createStream("q\n"));
/* 382 */           addOriginalContent(originalContent, newContentArray);
/*     */           
/* 384 */           newContentArray.add((COSBase)createStream("Q\n"));
/*     */           
/* 386 */           overlayPage(page, layoutPage, newContentArray);
/*     */           break;
/*     */         
/*     */         case BACKGROUND:
/* 390 */           overlayPage(page, layoutPage, newContentArray);
/*     */           
/* 392 */           addOriginalContent(originalContent, newContentArray);
/*     */           break;
/*     */         default:
/* 395 */           throw new IOException("Unknown type of position:" + this.position);
/*     */       } 
/* 397 */       pageDictionary.setItem(COSName.CONTENTS, (COSBase)newContentArray);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void addOriginalContent(COSBase contents, COSArray contentArray) throws IOException {
/* 403 */     if (contents == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 408 */     if (contents instanceof COSStream) {
/*     */       
/* 410 */       contentArray.add(contents);
/*     */     }
/* 412 */     else if (contents instanceof COSArray) {
/*     */       
/* 414 */       contentArray.addAll((COSArray)contents);
/*     */     }
/*     */     else {
/*     */       
/* 418 */       throw new IOException("Unknown content type: " + contents.getClass().getName());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void overlayPage(PDPage page, LayoutPage layoutPage, COSArray array) throws IOException {
/* 425 */     PDResources resources = page.getResources();
/* 426 */     if (resources == null) {
/*     */       
/* 428 */       resources = new PDResources();
/* 429 */       page.setResources(resources);
/*     */     } 
/* 431 */     COSName xObjectId = createOverlayXObject(page, layoutPage, layoutPage
/* 432 */         .overlayContentStream);
/* 433 */     array.add((COSBase)createOverlayStream(page, layoutPage, xObjectId));
/*     */   }
/*     */ 
/*     */   
/*     */   private LayoutPage getLayoutPage(int pageNumber, int numberOfPages) {
/* 438 */     LayoutPage layoutPage = null;
/* 439 */     if (!this.useAllOverlayPages && this.specificPageOverlayPage.containsKey(Integer.valueOf(pageNumber))) {
/*     */       
/* 441 */       layoutPage = this.specificPageOverlayPage.get(Integer.valueOf(pageNumber));
/*     */     }
/* 443 */     else if (pageNumber == 1 && this.firstPageOverlayPage != null) {
/*     */       
/* 445 */       layoutPage = this.firstPageOverlayPage;
/*     */     }
/* 447 */     else if (pageNumber == numberOfPages && this.lastPageOverlayPage != null) {
/*     */       
/* 449 */       layoutPage = this.lastPageOverlayPage;
/*     */     }
/* 451 */     else if (pageNumber % 2 == 1 && this.oddPageOverlayPage != null) {
/*     */       
/* 453 */       layoutPage = this.oddPageOverlayPage;
/*     */     }
/* 455 */     else if (pageNumber % 2 == 0 && this.evenPageOverlayPage != null) {
/*     */       
/* 457 */       layoutPage = this.evenPageOverlayPage;
/*     */     }
/* 459 */     else if (this.defaultOverlayPage != null) {
/*     */       
/* 461 */       layoutPage = this.defaultOverlayPage;
/*     */     }
/* 463 */     else if (this.useAllOverlayPages) {
/*     */       
/* 465 */       int usePageNum = (pageNumber - 1) % this.numberOfOverlayPages;
/* 466 */       layoutPage = this.specificPageOverlayPage.get(Integer.valueOf(usePageNum));
/*     */     } 
/* 468 */     return layoutPage;
/*     */   }
/*     */ 
/*     */   
/*     */   private COSName createOverlayXObject(PDPage page, LayoutPage layoutPage, COSStream contentStream) {
/* 473 */     PDFormXObject xobjForm = new PDFormXObject(contentStream);
/* 474 */     xobjForm.setResources(new PDResources(layoutPage.overlayResources));
/* 475 */     xobjForm.setFormType(1);
/* 476 */     xobjForm.setBBox(layoutPage.overlayMediaBox.createRetranslatedRectangle());
/* 477 */     xobjForm.setMatrix(new AffineTransform());
/* 478 */     PDResources resources = page.getResources();
/* 479 */     return resources.add((PDXObject)xobjForm, "OL");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSStream createOverlayStream(PDPage page, LayoutPage layoutPage, COSName xObjectId) throws IOException {
/* 486 */     StringBuilder overlayStream = new StringBuilder();
/* 487 */     overlayStream.append("q\nq\n");
/* 488 */     AffineTransform at = calculateAffineTransform(page, layoutPage.overlayMediaBox);
/* 489 */     double[] flatmatrix = new double[6];
/* 490 */     at.getMatrix(flatmatrix);
/* 491 */     for (double v : flatmatrix) {
/*     */       
/* 493 */       overlayStream.append(float2String((float)v));
/* 494 */       overlayStream.append(" ");
/*     */     } 
/* 496 */     overlayStream.append(" cm\n/");
/* 497 */     overlayStream.append(xObjectId.getName());
/* 498 */     overlayStream.append(" Do Q\nQ\n");
/* 499 */     return createStream(overlayStream.toString());
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
/*     */   protected AffineTransform calculateAffineTransform(PDPage page, PDRectangle overlayMediaBox) {
/* 513 */     AffineTransform at = new AffineTransform();
/* 514 */     PDRectangle pageMediaBox = page.getMediaBox();
/* 515 */     float hShift = (pageMediaBox.getWidth() - overlayMediaBox.getWidth()) / 2.0F;
/* 516 */     float vShift = (pageMediaBox.getHeight() - overlayMediaBox.getHeight()) / 2.0F;
/* 517 */     at.translate(hShift, vShift);
/* 518 */     return at;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String float2String(float floatValue) {
/* 525 */     BigDecimal value = new BigDecimal(String.valueOf(floatValue));
/* 526 */     String stringValue = value.toPlainString();
/*     */     
/* 528 */     if (stringValue.indexOf('.') > -1 && !stringValue.endsWith(".0"))
/*     */     {
/* 530 */       while (stringValue.endsWith("0") && !stringValue.endsWith(".0"))
/*     */       {
/* 532 */         stringValue = stringValue.substring(0, stringValue.length() - 1);
/*     */       }
/*     */     }
/* 535 */     return stringValue;
/*     */   }
/*     */ 
/*     */   
/*     */   private COSStream createStream(String content) throws IOException {
/* 540 */     COSStream stream = this.inputPDFDocument.getDocument().createCOSStream();
/* 541 */     OutputStream out = stream.createOutputStream(
/* 542 */         (content.length() > 20) ? (COSBase)COSName.FLATE_DECODE : null);
/* 543 */     out.write(content.getBytes("ISO-8859-1"));
/* 544 */     out.close();
/* 545 */     return stream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOverlayPosition(Position overlayPosition) {
/* 555 */     this.position = overlayPosition;
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
/*     */   public void setInputFile(String inputFile) {
/* 567 */     this.inputFileName = inputFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInputPDF(PDDocument inputPDF) {
/* 578 */     this.inputPDFDocument = inputPDF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getInputFile() {
/* 588 */     return this.inputFileName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultOverlayFile(String defaultOverlayFile) {
/* 598 */     this.defaultOverlayFilename = defaultOverlayFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultOverlayPDF(PDDocument defaultOverlayPDF) {
/* 608 */     this.defaultOverlay = defaultOverlayPDF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultOverlayFile() {
/* 618 */     return this.defaultOverlayFilename;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFirstPageOverlayFile(String firstPageOverlayFile) {
/* 628 */     this.firstPageOverlayFilename = firstPageOverlayFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFirstPageOverlayPDF(PDDocument firstPageOverlayPDF) {
/* 638 */     this.firstPageOverlay = firstPageOverlayPDF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLastPageOverlayFile(String lastPageOverlayFile) {
/* 648 */     this.lastPageOverlayFilename = lastPageOverlayFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLastPageOverlayPDF(PDDocument lastPageOverlayPDF) {
/* 658 */     this.lastPageOverlay = lastPageOverlayPDF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAllPagesOverlayFile(String allPagesOverlayFile) {
/* 668 */     this.allPagesOverlayFilename = allPagesOverlayFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAllPagesOverlayPDF(PDDocument allPagesOverlayPDF) {
/* 679 */     this.allPagesOverlay = allPagesOverlayPDF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOddPageOverlayFile(String oddPageOverlayFile) {
/* 689 */     this.oddPageOverlayFilename = oddPageOverlayFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOddPageOverlayPDF(PDDocument oddPageOverlayPDF) {
/* 699 */     this.oddPageOverlay = oddPageOverlayPDF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEvenPageOverlayFile(String evenPageOverlayFile) {
/* 709 */     this.evenPageOverlayFilename = evenPageOverlayFile;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEvenPageOverlayPDF(PDDocument evenPageOverlayPDF) {
/* 719 */     this.evenPageOverlay = evenPageOverlayPDF;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/multipdf/Overlay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */