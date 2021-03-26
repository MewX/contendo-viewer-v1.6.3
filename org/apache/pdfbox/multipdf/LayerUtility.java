/*     */ package org.apache.pdfbox.multipdf;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.fontbox.util.BoundingBox;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.PDPageContentStream;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
/*     */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentGroup;
/*     */ import org.apache.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentProperties;
/*     */ import org.apache.pdfbox.util.Matrix;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LayerUtility
/*     */ {
/*  54 */   private static final Log LOG = LogFactory.getLog(LayerUtility.class);
/*     */ 
/*     */   
/*     */   private static final boolean DEBUG = true;
/*     */ 
/*     */   
/*     */   private final PDDocument targetDoc;
/*     */ 
/*     */   
/*     */   private final PDFCloneUtility cloner;
/*     */ 
/*     */   
/*     */   public LayerUtility(PDDocument document) {
/*  67 */     this.targetDoc = document;
/*  68 */     this.cloner = new PDFCloneUtility(document);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDocument getDocument() {
/*  77 */     return this.targetDoc;
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
/*     */   public void wrapInSaveRestore(PDPage page) throws IOException {
/*  89 */     COSStream saveGraphicsStateStream = getDocument().getDocument().createCOSStream();
/*  90 */     OutputStream saveStream = saveGraphicsStateStream.createOutputStream();
/*  91 */     saveStream.write("q\n".getBytes("ISO-8859-1"));
/*  92 */     saveStream.close();
/*     */     
/*  94 */     COSStream restoreGraphicsStateStream = getDocument().getDocument().createCOSStream();
/*  95 */     OutputStream restoreStream = restoreGraphicsStateStream.createOutputStream();
/*  96 */     restoreStream.write("Q\n".getBytes("ISO-8859-1"));
/*  97 */     restoreStream.close();
/*     */ 
/*     */ 
/*     */     
/* 101 */     COSDictionary pageDictionary = page.getCOSObject();
/* 102 */     COSBase contents = pageDictionary.getDictionaryObject(COSName.CONTENTS);
/* 103 */     if (contents instanceof COSStream) {
/*     */       
/* 105 */       COSStream contentsStream = (COSStream)contents;
/*     */       
/* 107 */       COSArray array = new COSArray();
/* 108 */       array.add((COSBase)saveGraphicsStateStream);
/* 109 */       array.add((COSBase)contentsStream);
/* 110 */       array.add((COSBase)restoreGraphicsStateStream);
/*     */       
/* 112 */       pageDictionary.setItem(COSName.CONTENTS, (COSBase)array);
/*     */     }
/* 114 */     else if (contents instanceof COSArray) {
/*     */       
/* 116 */       COSArray contentsArray = (COSArray)contents;
/*     */       
/* 118 */       contentsArray.add(0, (COSBase)saveGraphicsStateStream);
/* 119 */       contentsArray.add((COSBase)restoreGraphicsStateStream);
/*     */     }
/*     */     else {
/*     */       
/* 123 */       throw new IOException("Contents are unknown type: " + contents.getClass().getName());
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
/*     */   public PDFormXObject importPageAsForm(PDDocument sourceDoc, int pageNumber) throws IOException {
/* 141 */     PDPage page = sourceDoc.getPage(pageNumber);
/* 142 */     return importPageAsForm(sourceDoc, page);
/*     */   }
/*     */   
/* 145 */   private static final Set<String> PAGE_TO_FORM_FILTER = new HashSet<String>(
/* 146 */       Arrays.asList(new String[] { "Group", "LastModified", "Metadata" }));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFormXObject importPageAsForm(PDDocument sourceDoc, PDPage page) throws IOException {
/* 162 */     importOcProperties(sourceDoc);
/*     */     
/* 164 */     PDStream newStream = new PDStream(this.targetDoc, page.getContents(), COSName.FLATE_DECODE);
/* 165 */     PDFormXObject form = new PDFormXObject(newStream);
/*     */ 
/*     */     
/* 168 */     PDResources pageRes = page.getResources();
/* 169 */     PDResources formRes = new PDResources();
/* 170 */     this.cloner.cloneMerge((COSObjectable)pageRes, (COSObjectable)formRes);
/* 171 */     form.setResources(formRes);
/*     */ 
/*     */     
/* 174 */     transferDict(page.getCOSObject(), (COSDictionary)form.getCOSObject(), PAGE_TO_FORM_FILTER, true);
/*     */     
/* 176 */     Matrix matrix = form.getMatrix();
/* 177 */     AffineTransform at = matrix.createAffineTransform();
/* 178 */     PDRectangle mediaBox = page.getMediaBox();
/* 179 */     PDRectangle cropBox = page.getCropBox();
/* 180 */     PDRectangle viewBox = (cropBox != null) ? cropBox : mediaBox;
/*     */ 
/*     */     
/* 183 */     int rotation = page.getRotation();
/*     */ 
/*     */ 
/*     */     
/* 187 */     at.translate((mediaBox.getLowerLeftX() - viewBox.getLowerLeftX()), (mediaBox
/* 188 */         .getLowerLeftY() - viewBox.getLowerLeftY()));
/* 189 */     switch (rotation) {
/*     */       
/*     */       case 90:
/* 192 */         at.scale((viewBox.getWidth() / viewBox.getHeight()), (viewBox.getHeight() / viewBox.getWidth()));
/* 193 */         at.translate(0.0D, viewBox.getWidth());
/* 194 */         at.rotate(-1.5707963267948966D);
/*     */         break;
/*     */       case 180:
/* 197 */         at.translate(viewBox.getWidth(), viewBox.getHeight());
/* 198 */         at.rotate(-3.141592653589793D);
/*     */         break;
/*     */       case 270:
/* 201 */         at.scale((viewBox.getWidth() / viewBox.getHeight()), (viewBox.getHeight() / viewBox.getWidth()));
/* 202 */         at.translate(viewBox.getHeight(), 0.0D);
/* 203 */         at.rotate(-4.71238898038469D);
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 209 */     at.translate(-viewBox.getLowerLeftX(), -viewBox.getLowerLeftY());
/* 210 */     if (!at.isIdentity())
/*     */     {
/* 212 */       form.setMatrix(at);
/*     */     }
/*     */     
/* 215 */     BoundingBox bbox = new BoundingBox();
/* 216 */     bbox.setLowerLeftX(viewBox.getLowerLeftX());
/* 217 */     bbox.setLowerLeftY(viewBox.getLowerLeftY());
/* 218 */     bbox.setUpperRightX(viewBox.getUpperRightX());
/* 219 */     bbox.setUpperRightY(viewBox.getUpperRightY());
/* 220 */     form.setBBox(new PDRectangle(bbox));
/*     */     
/* 222 */     return form;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public PDOptionalContentGroup appendFormAsLayer(PDPage targetPage, PDFormXObject form, AffineTransform transform, String layerName) throws IOException {
/* 247 */     PDDocumentCatalog catalog = this.targetDoc.getDocumentCatalog();
/* 248 */     PDOptionalContentProperties ocprops = catalog.getOCProperties();
/* 249 */     if (ocprops == null) {
/*     */       
/* 251 */       ocprops = new PDOptionalContentProperties();
/* 252 */       catalog.setOCProperties(ocprops);
/*     */     } 
/* 254 */     if (ocprops.hasGroup(layerName))
/*     */     {
/* 256 */       throw new IllegalArgumentException("Optional group (layer) already exists: " + layerName);
/*     */     }
/*     */     
/* 259 */     PDRectangle cropBox = targetPage.getCropBox();
/* 260 */     if ((cropBox.getLowerLeftX() < 0.0F || cropBox.getLowerLeftY() < 0.0F) && transform.isIdentity())
/*     */     {
/*     */       
/* 263 */       LOG.warn("Negative cropBox " + cropBox + " and identity transform may make your form invisible");
/*     */     }
/*     */ 
/*     */     
/* 267 */     PDOptionalContentGroup layer = new PDOptionalContentGroup(layerName);
/* 268 */     ocprops.addGroup(layer);
/*     */     
/* 270 */     PDPageContentStream contentStream = new PDPageContentStream(this.targetDoc, targetPage, PDPageContentStream.AppendMode.APPEND, false);
/*     */     
/* 272 */     contentStream.beginMarkedContent(COSName.OC, (PDPropertyList)layer);
/* 273 */     contentStream.saveGraphicsState();
/* 274 */     contentStream.transform(new Matrix(transform));
/* 275 */     contentStream.drawForm(form);
/* 276 */     contentStream.restoreGraphicsState();
/* 277 */     contentStream.endMarkedContent();
/* 278 */     contentStream.close();
/*     */     
/* 280 */     return layer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void transferDict(COSDictionary orgDict, COSDictionary targetDict, Set<String> filter, boolean inclusive) throws IOException {
/* 286 */     for (Map.Entry<COSName, COSBase> entry : (Iterable<Map.Entry<COSName, COSBase>>)orgDict.entrySet()) {
/*     */       
/* 288 */       COSName key = entry.getKey();
/* 289 */       if (inclusive && !filter.contains(key.getName())) {
/*     */         continue;
/*     */       }
/*     */       
/* 293 */       if (!inclusive && filter.contains(key.getName())) {
/*     */         continue;
/*     */       }
/*     */       
/* 297 */       targetDict.setItem(key, this.cloner
/* 298 */           .cloneForNewDocument(entry.getValue()));
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
/*     */   private void importOcProperties(PDDocument srcDoc) throws IOException {
/* 311 */     PDDocumentCatalog srcCatalog = srcDoc.getDocumentCatalog();
/* 312 */     PDOptionalContentProperties srcOCProperties = srcCatalog.getOCProperties();
/* 313 */     if (srcOCProperties == null) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 318 */     PDDocumentCatalog dstCatalog = this.targetDoc.getDocumentCatalog();
/* 319 */     PDOptionalContentProperties dstOCProperties = dstCatalog.getOCProperties();
/*     */     
/* 321 */     if (dstOCProperties == null) {
/*     */       
/* 323 */       dstCatalog.setOCProperties(new PDOptionalContentProperties((COSDictionary)this.cloner
/* 324 */             .cloneForNewDocument(srcOCProperties)));
/*     */     }
/*     */     else {
/*     */       
/* 328 */       this.cloner.cloneMerge((COSObjectable)srcOCProperties, (COSObjectable)dstOCProperties);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/multipdf/LayerUtility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */