/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.PDPageContentStream;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.fdf.FDFCatalog;
/*     */ import org.apache.pdfbox.pdmodel.fdf.FDFDictionary;
/*     */ import org.apache.pdfbox.pdmodel.fdf.FDFDocument;
/*     */ import org.apache.pdfbox.pdmodel.fdf.FDFField;
/*     */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*     */ import org.apache.pdfbox.pdmodel.font.PDType1Font;
/*     */ import org.apache.pdfbox.pdmodel.graphics.PDXObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceStream;
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
/*     */ 
/*     */ public final class PDAcroForm
/*     */   implements COSObjectable
/*     */ {
/*  61 */   private static final Log LOG = LogFactory.getLog(PDAcroForm.class);
/*     */ 
/*     */   
/*     */   private static final int FLAG_SIGNATURES_EXIST = 1;
/*     */ 
/*     */   
/*     */   private static final int FLAG_APPEND_ONLY = 2;
/*     */ 
/*     */   
/*     */   private final PDDocument document;
/*     */   
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   private Map<String, PDField> fieldCache;
/*     */ 
/*     */   
/*     */   public PDAcroForm(PDDocument doc) {
/*  78 */     this.document = doc;
/*  79 */     this.dictionary = new COSDictionary();
/*  80 */     this.dictionary.setItem(COSName.FIELDS, (COSBase)new COSArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAcroForm(PDDocument doc, COSDictionary form) {
/*  91 */     this.document = doc;
/*  92 */     this.dictionary = form;
/*  93 */     verifyOrCreateDefaults();
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
/*     */   private void verifyOrCreateDefaults() {
/* 106 */     String adobeDefaultAppearanceString = "/Helv 0 Tf 0 g ";
/*     */ 
/*     */     
/* 109 */     if (getDefaultAppearance().length() == 0) {
/*     */       
/* 111 */       setDefaultAppearance("/Helv 0 Tf 0 g ");
/* 112 */       this.dictionary.setNeedToBeUpdated(true);
/*     */     } 
/*     */ 
/*     */     
/* 116 */     PDResources defaultResources = getDefaultResources();
/* 117 */     if (defaultResources == null) {
/*     */       
/* 119 */       defaultResources = new PDResources();
/* 120 */       setDefaultResources(defaultResources);
/* 121 */       this.dictionary.setNeedToBeUpdated(true);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     COSDictionary fontDict = defaultResources.getCOSObject().getCOSDictionary(COSName.FONT);
/* 131 */     if (fontDict == null) {
/*     */       
/* 133 */       fontDict = new COSDictionary();
/* 134 */       defaultResources.getCOSObject().setItem(COSName.FONT, (COSBase)fontDict);
/*     */     } 
/* 136 */     if (!fontDict.containsKey(COSName.HELV)) {
/*     */       
/* 138 */       defaultResources.put(COSName.HELV, (PDFont)PDType1Font.HELVETICA);
/* 139 */       defaultResources.getCOSObject().setNeedToBeUpdated(true);
/* 140 */       fontDict.setNeedToBeUpdated(true);
/*     */     } 
/* 142 */     if (!fontDict.containsKey(COSName.ZA_DB)) {
/*     */       
/* 144 */       defaultResources.put(COSName.ZA_DB, (PDFont)PDType1Font.ZAPF_DINGBATS);
/* 145 */       defaultResources.getCOSObject().setNeedToBeUpdated(true);
/* 146 */       fontDict.setNeedToBeUpdated(true);
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
/*     */   PDDocument getDocument() {
/* 158 */     return this.document;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/* 164 */     return this.dictionary;
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
/*     */   public void importFDF(FDFDocument fdf) throws IOException {
/* 177 */     List<FDFField> fields = fdf.getCatalog().getFDF().getFields();
/* 178 */     if (fields != null)
/*     */     {
/* 180 */       for (FDFField field : fields) {
/*     */         
/* 182 */         FDFField fdfField = field;
/* 183 */         PDField docField = getField(fdfField.getPartialFieldName());
/* 184 */         if (docField != null)
/*     */         {
/* 186 */           docField.importFDF(fdfField);
/*     */         }
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
/*     */   public FDFDocument exportFDF() throws IOException {
/* 200 */     FDFDocument fdf = new FDFDocument();
/* 201 */     FDFCatalog catalog = fdf.getCatalog();
/* 202 */     FDFDictionary fdfDict = new FDFDictionary();
/* 203 */     catalog.setFDF(fdfDict);
/*     */     
/* 205 */     List<FDFField> fdfFields = new ArrayList<FDFField>();
/* 206 */     List<PDField> fields = getFields();
/* 207 */     for (PDField field : fields)
/*     */     {
/* 209 */       fdfFields.add(field.exportFDF());
/*     */     }
/*     */     
/* 212 */     fdfDict.setID(this.document.getDocument().getDocumentID());
/*     */     
/* 214 */     if (!fdfFields.isEmpty())
/*     */     {
/* 216 */       fdfDict.setFields(fdfFields);
/*     */     }
/* 218 */     return fdf;
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
/*     */   public void flatten() throws IOException {
/* 238 */     if (xfaIsDynamic()) {
/*     */       
/* 240 */       LOG.warn("Flatten for a dynamix XFA form is not supported");
/*     */       
/*     */       return;
/*     */     } 
/* 244 */     List<PDField> fields = new ArrayList<PDField>();
/* 245 */     for (PDField field : getFieldTree())
/*     */     {
/* 247 */       fields.add(field);
/*     */     }
/* 249 */     flatten(fields, false);
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
/*     */   public void flatten(List<PDField> fields, boolean refreshAppearances) throws IOException {
/* 269 */     if (fields.isEmpty()) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 274 */     if (!refreshAppearances && getNeedAppearances()) {
/*     */       
/* 276 */       LOG.warn("acroForm.getNeedAppearances() returns true, visual field appearances may not have been set");
/*     */       
/* 278 */       LOG.warn("call acroForm.refreshAppearances() or use the flatten() method with refreshAppearances parameter");
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 284 */     if (xfaIsDynamic()) {
/*     */       
/* 286 */       LOG.warn("Flatten for a dynamix XFA form is not supported");
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 291 */     if (refreshAppearances)
/*     */     {
/* 293 */       refreshAppearances(fields);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 304 */     Map<COSDictionary, Map<COSDictionary, PDAnnotationWidget>> pagesWidgetsMap = buildPagesWidgetsMap(fields);
/*     */ 
/*     */     
/* 307 */     for (PDPage page : this.document.getPages()) {
/*     */       
/* 309 */       Map<COSDictionary, PDAnnotationWidget> widgetsForPageMap = pagesWidgetsMap.get(page.getCOSObject());
/* 310 */       boolean isContentStreamWrapped = false;
/*     */       
/* 312 */       List<PDAnnotation> annotations = new ArrayList<PDAnnotation>();
/*     */       
/* 314 */       for (PDAnnotation annotation : page.getAnnotations()) {
/*     */         
/* 316 */         if (widgetsForPageMap != null && widgetsForPageMap.get(annotation.getCOSObject()) == null) {
/*     */           
/* 318 */           annotations.add(annotation); continue;
/*     */         } 
/* 320 */         if (!annotation.isInvisible() && !annotation.isHidden() && annotation.getNormalAppearanceStream() != null) {
/*     */           PDPageContentStream contentStream;
/* 322 */           if (!isContentStreamWrapped) {
/*     */             
/* 324 */             contentStream = new PDPageContentStream(this.document, page, PDPageContentStream.AppendMode.APPEND, true, true);
/* 325 */             isContentStreamWrapped = true;
/*     */           }
/*     */           else {
/*     */             
/* 329 */             contentStream = new PDPageContentStream(this.document, page, PDPageContentStream.AppendMode.APPEND, true);
/*     */           } 
/*     */           
/* 332 */           PDAppearanceStream appearanceStream = annotation.getNormalAppearanceStream();
/*     */           
/* 334 */           PDFormXObject fieldObject = new PDFormXObject(appearanceStream.getCOSObject());
/*     */           
/* 336 */           contentStream.saveGraphicsState();
/*     */ 
/*     */ 
/*     */           
/* 340 */           boolean needsTranslation = resolveNeedsTranslation(appearanceStream);
/*     */ 
/*     */ 
/*     */           
/* 344 */           boolean needsScaling = resolveNeedsScaling(appearanceStream);
/*     */           
/* 346 */           Matrix transformationMatrix = new Matrix();
/* 347 */           boolean transformed = false;
/*     */           
/* 349 */           if (needsTranslation) {
/*     */             
/* 351 */             transformationMatrix.translate(annotation.getRectangle().getLowerLeftX(), annotation
/* 352 */                 .getRectangle().getLowerLeftY());
/* 353 */             transformed = true;
/*     */           } 
/*     */           
/* 356 */           if (needsScaling) {
/*     */             
/* 358 */             PDRectangle bbox = appearanceStream.getBBox();
/* 359 */             PDRectangle fieldRect = annotation.getRectangle();
/*     */             
/* 361 */             if (bbox.getWidth() - fieldRect.getWidth() != 0.0F && bbox.getHeight() - fieldRect.getHeight() != 0.0F) {
/*     */               
/* 363 */               float xScale = fieldRect.getWidth() / bbox.getWidth();
/* 364 */               float yScale = fieldRect.getHeight() / bbox.getHeight();
/* 365 */               Matrix scalingMatrix = Matrix.getScaleInstance(xScale, yScale);
/* 366 */               transformationMatrix.concatenate(scalingMatrix);
/* 367 */               transformed = true;
/*     */             } 
/*     */           } 
/*     */           
/* 371 */           if (transformed)
/*     */           {
/* 373 */             contentStream.transform(transformationMatrix);
/*     */           }
/*     */           
/* 376 */           contentStream.drawForm(fieldObject);
/* 377 */           contentStream.restoreGraphicsState();
/* 378 */           contentStream.close();
/*     */         } 
/*     */       } 
/* 381 */       page.setAnnotations(annotations);
/*     */     } 
/*     */ 
/*     */     
/* 385 */     removeFields(fields);
/*     */ 
/*     */     
/* 388 */     this.dictionary.removeItem(COSName.XFA);
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
/*     */   public void refreshAppearances() throws IOException {
/* 400 */     for (PDField field : getFieldTree()) {
/*     */       
/* 402 */       if (field instanceof PDTerminalField)
/*     */       {
/* 404 */         ((PDTerminalField)field).constructAppearances();
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
/*     */   public void refreshAppearances(List<PDField> fields) throws IOException {
/* 418 */     for (PDField field : fields) {
/*     */       
/* 420 */       if (field instanceof PDTerminalField)
/*     */       {
/* 422 */         ((PDTerminalField)field).constructAppearances();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PDField> getFields() {
/* 443 */     COSArray cosFields = (COSArray)this.dictionary.getDictionaryObject(COSName.FIELDS);
/* 444 */     if (cosFields == null)
/*     */     {
/* 446 */       return Collections.emptyList();
/*     */     }
/* 448 */     List<PDField> pdFields = new ArrayList<PDField>();
/* 449 */     for (int i = 0; i < cosFields.size(); i++) {
/*     */       
/* 451 */       COSDictionary element = (COSDictionary)cosFields.getObject(i);
/* 452 */       if (element != null) {
/*     */         
/* 454 */         PDField field = PDField.fromDictionary(this, element, null);
/* 455 */         if (field != null)
/*     */         {
/* 457 */           pdFields.add(field);
/*     */         }
/*     */       } 
/*     */     } 
/* 461 */     return (List<PDField>)new COSArrayList(pdFields, cosFields);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFields(List<PDField> fields) {
/* 471 */     this.dictionary.setItem(COSName.FIELDS, (COSBase)COSArrayList.converterToCOSArray(fields));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<PDField> getFieldIterator() {
/* 479 */     return (new PDFieldTree(this)).iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFieldTree getFieldTree() {
/* 487 */     return new PDFieldTree(this);
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
/*     */   public void setCacheFields(boolean cache) {
/* 500 */     if (cache) {
/*     */       
/* 502 */       this.fieldCache = new HashMap<String, PDField>();
/*     */       
/* 504 */       for (PDField field : getFieldTree())
/*     */       {
/* 506 */         this.fieldCache.put(field.getFullyQualifiedName(), field);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 511 */       this.fieldCache = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCachingFields() {
/* 522 */     return (this.fieldCache != null);
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
/*     */   public PDField getField(String fullyQualifiedName) {
/* 534 */     if (this.fieldCache != null)
/*     */     {
/* 536 */       return this.fieldCache.get(fullyQualifiedName);
/*     */     }
/*     */ 
/*     */     
/* 540 */     for (PDField field : getFieldTree()) {
/*     */       
/* 542 */       if (field.getFullyQualifiedName().equals(fullyQualifiedName))
/*     */       {
/* 544 */         return field;
/*     */       }
/*     */     } 
/*     */     
/* 548 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultAppearance() {
/* 558 */     return this.dictionary.getString(COSName.DA, "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultAppearance(String daValue) {
/* 568 */     this.dictionary.setString(COSName.DA, daValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getNeedAppearances() {
/* 579 */     return this.dictionary.getBoolean(COSName.NEED_APPEARANCES, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNeedAppearances(Boolean value) {
/* 590 */     this.dictionary.setBoolean(COSName.NEED_APPEARANCES, value.booleanValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDResources getDefaultResources() {
/* 600 */     PDResources retval = null;
/* 601 */     COSBase base = this.dictionary.getDictionaryObject(COSName.DR);
/* 602 */     if (base instanceof COSDictionary)
/*     */     {
/* 604 */       retval = new PDResources((COSDictionary)base, this.document.getResourceCache());
/*     */     }
/* 606 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultResources(PDResources dr) {
/* 616 */     this.dictionary.setItem(COSName.DR, (COSObjectable)dr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasXFA() {
/* 626 */     return this.dictionary.containsKey(COSName.XFA);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean xfaIsDynamic() {
/* 636 */     return (hasXFA() && getFields().isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDXFAResource getXFA() {
/* 646 */     PDXFAResource xfa = null;
/* 647 */     COSBase base = this.dictionary.getDictionaryObject(COSName.XFA);
/* 648 */     if (base != null)
/*     */     {
/* 650 */       xfa = new PDXFAResource(base);
/*     */     }
/* 652 */     return xfa;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXFA(PDXFAResource xfa) {
/* 662 */     this.dictionary.setItem(COSName.XFA, xfa);
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
/*     */   public int getQ() {
/* 678 */     int retval = 0;
/* 679 */     COSNumber number = (COSNumber)this.dictionary.getDictionaryObject(COSName.Q);
/* 680 */     if (number != null)
/*     */     {
/* 682 */       retval = number.intValue();
/*     */     }
/* 684 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQ(int q) {
/* 695 */     this.dictionary.setInt(COSName.Q, q);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSignaturesExist() {
/* 705 */     return this.dictionary.getFlag(COSName.SIG_FLAGS, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSignaturesExist(boolean signaturesExist) {
/* 715 */     this.dictionary.setFlag(COSName.SIG_FLAGS, 1, signaturesExist);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAppendOnly() {
/* 725 */     return this.dictionary.getFlag(COSName.SIG_FLAGS, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAppendOnly(boolean appendOnly) {
/* 735 */     this.dictionary.setFlag(COSName.SIG_FLAGS, 2, appendOnly);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean resolveNeedsTranslation(PDAppearanceStream appearanceStream) {
/* 746 */     boolean needsTranslation = true;
/*     */     
/* 748 */     PDResources resources = appearanceStream.getResources();
/* 749 */     if (resources != null && resources.getXObjectNames().iterator().hasNext()) {
/*     */ 
/*     */       
/* 752 */       Iterator<COSName> xObjectNames = resources.getXObjectNames().iterator();
/*     */       
/* 754 */       while (xObjectNames.hasNext()) {
/*     */ 
/*     */         
/*     */         try {
/*     */ 
/*     */           
/* 760 */           PDXObject xObject = resources.getXObject(xObjectNames.next());
/* 761 */           if (xObject instanceof PDFormXObject)
/*     */           {
/* 763 */             PDRectangle bbox = ((PDFormXObject)xObject).getBBox();
/* 764 */             float llX = bbox.getLowerLeftX();
/* 765 */             float llY = bbox.getLowerLeftY();
/* 766 */             if (Float.compare(llX, 0.0F) != 0 && Float.compare(llY, 0.0F) != 0)
/*     */             {
/* 768 */               needsTranslation = false;
/*     */             }
/*     */           }
/*     */         
/* 772 */         } catch (IOException iOException) {}
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 778 */       return needsTranslation;
/*     */     } 
/*     */     
/* 781 */     return true;
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
/*     */   private boolean resolveNeedsScaling(PDAppearanceStream appearanceStream) {
/* 793 */     PDResources resources = appearanceStream.getResources();
/* 794 */     return (resources != null && resources.getXObjectNames().iterator().hasNext());
/*     */   }
/*     */ 
/*     */   
/*     */   private Map<COSDictionary, Map<COSDictionary, PDAnnotationWidget>> buildPagesWidgetsMap(List<PDField> fields) {
/* 799 */     Map<COSDictionary, Map<COSDictionary, PDAnnotationWidget>> pagesAnnotationsMap = new HashMap<COSDictionary, Map<COSDictionary, PDAnnotationWidget>>();
/* 800 */     boolean hasMissingPageRef = false;
/*     */     
/* 802 */     for (PDField field : fields) {
/*     */       
/* 804 */       List<PDAnnotationWidget> widgets = field.getWidgets();
/* 805 */       for (PDAnnotationWidget widget : widgets) {
/*     */         
/* 807 */         PDPage pageForWidget = widget.getPage();
/* 808 */         if (pageForWidget != null) {
/*     */           
/* 810 */           if (pagesAnnotationsMap.get(pageForWidget.getCOSObject()) == null) {
/*     */             
/* 812 */             Map<COSDictionary, PDAnnotationWidget> map = new HashMap<COSDictionary, PDAnnotationWidget>();
/* 813 */             map.put(widget.getCOSObject(), widget);
/* 814 */             pagesAnnotationsMap.put(pageForWidget.getCOSObject(), map);
/*     */             
/*     */             continue;
/*     */           } 
/* 818 */           Map<COSDictionary, PDAnnotationWidget> widgetsForPage = pagesAnnotationsMap.get(pageForWidget.getCOSObject());
/* 819 */           widgetsForPage.put(widget.getCOSObject(), widget);
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 824 */         hasMissingPageRef = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 832 */     if (hasMissingPageRef)
/*     */     {
/* 834 */       LOG.warn("There has been a widget with a missing page reference. Please report to the PDFBox project");
/*     */     }
/*     */     
/* 837 */     return pagesAnnotationsMap;
/*     */   }
/*     */ 
/*     */   
/*     */   private void removeFields(List<PDField> fields) {
/* 842 */     for (PDField field : fields) {
/* 843 */       if (field.getParent() == null) {
/*     */         
/* 845 */         COSArray cosFields = (COSArray)this.dictionary.getDictionaryObject(COSName.FIELDS);
/* 846 */         for (int j = 0; j < cosFields.size(); j++) {
/*     */           
/* 848 */           COSDictionary element = (COSDictionary)cosFields.getObject(j);
/* 849 */           if (field.getCOSObject().equals(element)) {
/* 850 */             cosFields.remove(j);
/*     */           }
/*     */         } 
/*     */         
/*     */         continue;
/*     */       } 
/* 856 */       COSArray kids = (COSArray)field.getParent().getCOSObject().getDictionaryObject(COSName.KIDS);
/* 857 */       for (int i = 0; i < kids.size(); i++) {
/*     */         
/* 859 */         COSDictionary element = (COSDictionary)kids.getObject(i);
/* 860 */         if (field.getCOSObject().equals(element))
/* 861 */           kids.remove(i); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDAcroForm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */