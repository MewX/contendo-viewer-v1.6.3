/*      */ package org.apache.pdfbox.pdmodel;
/*      */ 
/*      */ import java.awt.Point;
/*      */ import java.awt.image.Raster;
/*      */ import java.awt.image.WritableRaster;
/*      */ import java.io.BufferedOutputStream;
/*      */ import java.io.Closeable;
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.apache.fontbox.ttf.TrueTypeFont;
/*      */ import org.apache.pdfbox.cos.COSArray;
/*      */ import org.apache.pdfbox.cos.COSBase;
/*      */ import org.apache.pdfbox.cos.COSDictionary;
/*      */ import org.apache.pdfbox.cos.COSDocument;
/*      */ import org.apache.pdfbox.cos.COSInteger;
/*      */ import org.apache.pdfbox.cos.COSName;
/*      */ import org.apache.pdfbox.cos.COSNumber;
/*      */ import org.apache.pdfbox.cos.COSObject;
/*      */ import org.apache.pdfbox.io.IOUtils;
/*      */ import org.apache.pdfbox.io.MemoryUsageSetting;
/*      */ import org.apache.pdfbox.io.RandomAccess;
/*      */ import org.apache.pdfbox.io.RandomAccessBuffer;
/*      */ import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
/*      */ import org.apache.pdfbox.io.RandomAccessRead;
/*      */ import org.apache.pdfbox.io.ScratchFile;
/*      */ import org.apache.pdfbox.pdfparser.PDFParser;
/*      */ import org.apache.pdfbox.pdfwriter.COSWriter;
/*      */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
/*      */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*      */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*      */ import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
/*      */ import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
/*      */ import org.apache.pdfbox.pdmodel.encryption.PDEncryption;
/*      */ import org.apache.pdfbox.pdmodel.encryption.ProtectionPolicy;
/*      */ import org.apache.pdfbox.pdmodel.encryption.SecurityHandler;
/*      */ import org.apache.pdfbox.pdmodel.encryption.SecurityHandlerFactory;
/*      */ import org.apache.pdfbox.pdmodel.font.PDFont;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
/*      */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*      */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
/*      */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAppearanceDictionary;
/*      */ import org.apache.pdfbox.pdmodel.interactive.digitalsignature.ExternalSigningSupport;
/*      */ import org.apache.pdfbox.pdmodel.interactive.digitalsignature.PDSignature;
/*      */ import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureInterface;
/*      */ import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SignatureOptions;
/*      */ import org.apache.pdfbox.pdmodel.interactive.digitalsignature.SigningSupport;
/*      */ import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
/*      */ import org.apache.pdfbox.pdmodel.interactive.form.PDField;
/*      */ import org.apache.pdfbox.pdmodel.interactive.form.PDSignatureField;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PDDocument
/*      */   implements Closeable
/*      */ {
/*   95 */   private static final int[] RESERVE_BYTE_RANGE = new int[] { 0, 1000000000, 1000000000, 1000000000 };
/*      */   
/*   97 */   private static final Log LOG = LogFactory.getLog(PDDocument.class);
/*      */   
/*      */   private final COSDocument document;
/*      */   private PDDocumentInformation documentInformation;
/*      */   private PDDocumentCatalog documentCatalog;
/*      */   private PDEncryption encryption;
/*      */   
/*      */   static {
/*      */     try {
/*  106 */       WritableRaster raster = Raster.createBandedRaster(0, 1, 1, 3, new Point(0, 0));
/*  107 */       PDDeviceRGB.INSTANCE.toRGBImage(raster);
/*      */     }
/*  109 */     catch (IOException ex) {
/*      */       
/*  111 */       LOG.debug("voodoo error", ex);
/*      */     } 
/*      */ 
/*      */     
/*      */     try {
/*  116 */       COSNumber.get("0");
/*  117 */       COSNumber.get("1");
/*      */     }
/*  119 */     catch (IOException iOException) {}
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean allSecurityToBeRemoved;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Long documentId;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final RandomAccessRead pdfSource;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private AccessPermission accessPermission;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  149 */   private final Set<PDFont> fontsToSubset = new HashSet<PDFont>();
/*      */ 
/*      */   
/*  152 */   private final Set<TrueTypeFont> fontsToClose = new HashSet<TrueTypeFont>();
/*      */ 
/*      */   
/*      */   private SignatureInterface signInterface;
/*      */ 
/*      */   
/*      */   private SigningSupport signingSupport;
/*      */ 
/*      */   
/*  161 */   private ResourceCache resourceCache = new DefaultResourceCache();
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean signatureAdded = false;
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDDocument() {
/*  172 */     this(MemoryUsageSetting.setupMainMemoryOnly());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDDocument(MemoryUsageSetting memUsageSetting) {
/*  183 */     ScratchFile scratchFile = null;
/*      */     
/*      */     try {
/*  186 */       scratchFile = new ScratchFile(memUsageSetting);
/*      */     }
/*  188 */     catch (IOException ioe) {
/*      */       
/*  190 */       LOG.warn("Error initializing scratch file: " + ioe.getMessage() + ". Fall back to main memory usage only.");
/*      */ 
/*      */       
/*      */       try {
/*  194 */         scratchFile = new ScratchFile(MemoryUsageSetting.setupMainMemoryOnly());
/*      */       }
/*  196 */       catch (IOException iOException) {}
/*      */     } 
/*      */     
/*  199 */     this.document = new COSDocument(scratchFile);
/*  200 */     this.pdfSource = null;
/*      */ 
/*      */     
/*  203 */     COSDictionary trailer = new COSDictionary();
/*  204 */     this.document.setTrailer(trailer);
/*      */ 
/*      */     
/*  207 */     COSDictionary rootDictionary = new COSDictionary();
/*  208 */     trailer.setItem(COSName.ROOT, (COSBase)rootDictionary);
/*  209 */     rootDictionary.setItem(COSName.TYPE, (COSBase)COSName.CATALOG);
/*  210 */     rootDictionary.setItem(COSName.VERSION, (COSBase)COSName.getPDFName("1.4"));
/*      */ 
/*      */     
/*  213 */     COSDictionary pages = new COSDictionary();
/*  214 */     rootDictionary.setItem(COSName.PAGES, (COSBase)pages);
/*  215 */     pages.setItem(COSName.TYPE, (COSBase)COSName.PAGES);
/*  216 */     COSArray kidsArray = new COSArray();
/*  217 */     pages.setItem(COSName.KIDS, (COSBase)kidsArray);
/*  218 */     pages.setItem(COSName.COUNT, (COSBase)COSInteger.ZERO);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDDocument(COSDocument doc) {
/*  228 */     this(doc, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDDocument(COSDocument doc, RandomAccessRead source) {
/*  239 */     this(doc, source, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDDocument(COSDocument doc, RandomAccessRead source, AccessPermission permission) {
/*  252 */     this.document = doc;
/*  253 */     this.pdfSource = source;
/*  254 */     this.accessPermission = permission;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPage(PDPage page) {
/*  265 */     getPages().add(page);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSignature(PDSignature sigObject) throws IOException {
/*  283 */     addSignature(sigObject, new SignatureOptions());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSignature(PDSignature sigObject, SignatureOptions options) throws IOException {
/*  302 */     addSignature(sigObject, null, options);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSignature(PDSignature sigObject, SignatureInterface signatureInterface) throws IOException {
/*  320 */     addSignature(sigObject, signatureInterface, new SignatureOptions());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSignature(PDSignature sigObject, SignatureInterface signatureInterface, SignatureOptions options) throws IOException {
/*  342 */     if (this.signatureAdded)
/*      */     {
/*  344 */       throw new IllegalStateException("Only one signature may be added in a document");
/*      */     }
/*  346 */     this.signatureAdded = true;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  351 */     int preferredSignatureSize = options.getPreferredSignatureSize();
/*  352 */     if (preferredSignatureSize > 0) {
/*      */       
/*  354 */       sigObject.setContents(new byte[preferredSignatureSize]);
/*      */     }
/*      */     else {
/*      */       
/*  358 */       sigObject.setContents(new byte[9472]);
/*      */     } 
/*      */ 
/*      */     
/*  362 */     sigObject.setByteRange(RESERVE_BYTE_RANGE);
/*      */     
/*  364 */     this.signInterface = signatureInterface;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  369 */     int pageCount = getNumberOfPages();
/*  370 */     if (pageCount == 0)
/*      */     {
/*  372 */       throw new IllegalStateException("Cannot sign an empty document");
/*      */     }
/*      */     
/*  375 */     int startIndex = Math.min(Math.max(options.getPage(), 0), pageCount - 1);
/*  376 */     PDPage page = getPage(startIndex);
/*      */ 
/*      */     
/*  379 */     PDDocumentCatalog catalog = getDocumentCatalog();
/*  380 */     PDAcroForm acroForm = catalog.getAcroForm();
/*  381 */     catalog.getCOSObject().setNeedToBeUpdated(true);
/*      */     
/*  383 */     if (acroForm == null) {
/*      */       
/*  385 */       acroForm = new PDAcroForm(this);
/*  386 */       catalog.setAcroForm(acroForm);
/*      */     }
/*      */     else {
/*      */       
/*  390 */       acroForm.getCOSObject().setNeedToBeUpdated(true);
/*      */     } 
/*      */     
/*  393 */     PDSignatureField signatureField = null;
/*  394 */     if (!(acroForm.getCOSObject().getDictionaryObject(COSName.FIELDS) instanceof COSArray)) {
/*      */       
/*  396 */       acroForm.getCOSObject().setItem(COSName.FIELDS, (COSBase)new COSArray());
/*      */     }
/*      */     else {
/*      */       
/*  400 */       COSArray fieldArray = (COSArray)acroForm.getCOSObject().getDictionaryObject(COSName.FIELDS);
/*  401 */       fieldArray.setNeedToBeUpdated(true);
/*  402 */       signatureField = findSignatureField(acroForm.getFieldIterator(), sigObject);
/*      */     } 
/*  404 */     if (signatureField == null) {
/*      */       
/*  406 */       signatureField = new PDSignatureField(acroForm);
/*      */       
/*  408 */       signatureField.setValue(sigObject);
/*      */       
/*  410 */       ((PDAnnotationWidget)signatureField.getWidgets().get(0)).setPage(page);
/*      */     }
/*      */     else {
/*      */       
/*  414 */       sigObject.getCOSObject().setNeedToBeUpdated(true);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  423 */     ((PDAnnotationWidget)signatureField.getWidgets().get(0)).setPrinted(true);
/*      */ 
/*      */     
/*  426 */     List<PDField> acroFormFields = acroForm.getFields();
/*  427 */     acroForm.getCOSObject().setDirect(true);
/*  428 */     acroForm.setSignaturesExist(true);
/*  429 */     acroForm.setAppendOnly(true);
/*      */     
/*  431 */     boolean checkFields = checkSignatureField(acroForm.getFieldIterator(), signatureField);
/*  432 */     if (checkFields) {
/*      */       
/*  434 */       signatureField.getCOSObject().setNeedToBeUpdated(true);
/*      */     }
/*      */     else {
/*      */       
/*  438 */       acroFormFields.add(signatureField);
/*      */     } 
/*      */ 
/*      */     
/*  442 */     COSDocument visualSignature = options.getVisualSignature();
/*      */ 
/*      */     
/*  445 */     if (visualSignature == null) {
/*      */       
/*  447 */       prepareNonVisibleSignature(signatureField);
/*      */       
/*      */       return;
/*      */     } 
/*  451 */     prepareVisibleSignature(signatureField, acroForm, visualSignature);
/*      */ 
/*      */     
/*  454 */     List<PDAnnotation> annotations = page.getAnnotations();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  459 */     page.setAnnotations(annotations);
/*      */ 
/*      */ 
/*      */     
/*  463 */     if (!(annotations instanceof COSArrayList) || !(acroFormFields instanceof COSArrayList) || 
/*      */       
/*  465 */       !((COSArrayList)annotations).toList().equals(((COSArrayList)acroFormFields).toList()) || !checkFields) {
/*      */ 
/*      */       
/*  468 */       PDAnnotationWidget widget = signatureField.getWidgets().get(0);
/*      */       
/*  470 */       if (checkSignatureAnnotation(annotations, widget)) {
/*      */         
/*  472 */         widget.getCOSObject().setNeedToBeUpdated(true);
/*      */       }
/*      */       else {
/*      */         
/*  476 */         annotations.add(widget);
/*      */       } 
/*      */     } 
/*  479 */     page.getCOSObject().setNeedToBeUpdated(true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private PDSignatureField findSignatureField(Iterator<PDField> fieldIterator, PDSignature sigObject) {
/*  491 */     PDSignatureField signatureField = null;
/*  492 */     while (fieldIterator.hasNext()) {
/*      */       
/*  494 */       PDField pdField = fieldIterator.next();
/*  495 */       if (pdField instanceof PDSignatureField) {
/*      */         
/*  497 */         PDSignature signature = ((PDSignatureField)pdField).getSignature();
/*  498 */         if (signature != null && signature.getCOSObject().equals(sigObject.getCOSObject()))
/*      */         {
/*  500 */           signatureField = (PDSignatureField)pdField;
/*      */         }
/*      */       } 
/*      */     } 
/*  504 */     return signatureField;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean checkSignatureField(Iterator<PDField> fieldIterator, PDSignatureField signatureField) {
/*  516 */     while (fieldIterator.hasNext()) {
/*      */       
/*  518 */       PDField field = fieldIterator.next();
/*  519 */       if (field instanceof PDSignatureField && field
/*  520 */         .getCOSObject().equals(signatureField.getCOSObject()))
/*      */       {
/*  522 */         return true;
/*      */       }
/*      */     } 
/*  525 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean checkSignatureAnnotation(List<PDAnnotation> annotations, PDAnnotationWidget widget) {
/*  537 */     for (PDAnnotation annotation : annotations) {
/*      */       
/*  539 */       if (annotation.getCOSObject().equals(widget.getCOSObject()))
/*      */       {
/*  541 */         return true;
/*      */       }
/*      */     } 
/*  544 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void prepareVisibleSignature(PDSignatureField signatureField, PDAcroForm acroForm, COSDocument visualSignature) {
/*  551 */     boolean annotNotFound = true;
/*  552 */     boolean sigFieldNotFound = true;
/*  553 */     for (COSObject cosObject : visualSignature.getObjects()) {
/*      */       
/*  555 */       if (!annotNotFound && !sigFieldNotFound) {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/*  560 */       COSBase base = cosObject.getObject();
/*  561 */       if (base instanceof COSDictionary) {
/*      */         
/*  563 */         COSDictionary cosBaseDict = (COSDictionary)base;
/*      */ 
/*      */         
/*  566 */         COSBase type = cosBaseDict.getDictionaryObject(COSName.TYPE);
/*  567 */         if (annotNotFound && COSName.ANNOT.equals(type)) {
/*      */           
/*  569 */           assignSignatureRectangle(signatureField, cosBaseDict);
/*  570 */           annotNotFound = false;
/*      */         } 
/*      */ 
/*      */         
/*  574 */         COSBase fieldType = cosBaseDict.getDictionaryObject(COSName.FT);
/*  575 */         COSBase apDict = cosBaseDict.getDictionaryObject(COSName.AP);
/*  576 */         if (sigFieldNotFound && COSName.SIG.equals(fieldType) && apDict instanceof COSDictionary) {
/*      */           
/*  578 */           assignAppearanceDictionary(signatureField, (COSDictionary)apDict);
/*  579 */           assignAcroFormDefaultResource(acroForm, cosBaseDict);
/*  580 */           sigFieldNotFound = false;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  585 */     if (annotNotFound || sigFieldNotFound)
/*      */     {
/*  587 */       throw new IllegalArgumentException("Template is missing required objects");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void assignSignatureRectangle(PDSignatureField signatureField, COSDictionary annotDict) {
/*  594 */     COSArray rectArray = (COSArray)annotDict.getDictionaryObject(COSName.RECT);
/*  595 */     PDRectangle rect = new PDRectangle(rectArray);
/*  596 */     PDRectangle existingRectangle = ((PDAnnotationWidget)signatureField.getWidgets().get(0)).getRectangle();
/*      */ 
/*      */     
/*  599 */     if (existingRectangle == null || existingRectangle.getCOSArray().size() != 4)
/*      */     {
/*  601 */       ((PDAnnotationWidget)signatureField.getWidgets().get(0)).setRectangle(rect);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void assignAppearanceDictionary(PDSignatureField signatureField, COSDictionary apDict) {
/*  608 */     PDAppearanceDictionary ap = new PDAppearanceDictionary(apDict);
/*  609 */     apDict.setDirect(true);
/*  610 */     ((PDAnnotationWidget)signatureField.getWidgets().get(0)).setAppearance(ap);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void assignAcroFormDefaultResource(PDAcroForm acroForm, COSDictionary newDict) {
/*  616 */     COSBase newBase = newDict.getDictionaryObject(COSName.DR);
/*  617 */     if (newBase instanceof COSDictionary) {
/*      */       
/*  619 */       COSDictionary newDR = (COSDictionary)newBase;
/*  620 */       PDResources defaultResources = acroForm.getDefaultResources();
/*  621 */       if (defaultResources == null) {
/*      */         
/*  623 */         acroForm.getCOSObject().setItem(COSName.DR, (COSBase)newDR);
/*  624 */         newDR.setDirect(true);
/*  625 */         newDR.setNeedToBeUpdated(true);
/*      */       }
/*      */       else {
/*      */         
/*  629 */         COSDictionary oldDR = defaultResources.getCOSObject();
/*  630 */         COSBase newXObjectBase = newDR.getItem(COSName.XOBJECT);
/*  631 */         COSBase oldXObjectBase = oldDR.getItem(COSName.XOBJECT);
/*  632 */         if (newXObjectBase instanceof COSDictionary && oldXObjectBase instanceof COSDictionary) {
/*      */ 
/*      */           
/*  635 */           ((COSDictionary)oldXObjectBase).addAll((COSDictionary)newXObjectBase);
/*  636 */           oldDR.setNeedToBeUpdated(true);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void prepareNonVisibleSignature(PDSignatureField signatureField) throws IOException {
/*  648 */     ((PDAnnotationWidget)signatureField.getWidgets().get(0)).setRectangle(new PDRectangle());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void addSignatureField(List<PDSignatureField> sigFields, SignatureInterface signatureInterface, SignatureOptions options) throws IOException {
/*  666 */     PDDocumentCatalog catalog = getDocumentCatalog();
/*  667 */     catalog.getCOSObject().setNeedToBeUpdated(true);
/*      */     
/*  669 */     PDAcroForm acroForm = catalog.getAcroForm();
/*  670 */     if (acroForm == null) {
/*      */       
/*  672 */       acroForm = new PDAcroForm(this);
/*  673 */       catalog.setAcroForm(acroForm);
/*      */     } 
/*  675 */     COSDictionary acroFormDict = acroForm.getCOSObject();
/*  676 */     acroFormDict.setDirect(true);
/*  677 */     acroFormDict.setNeedToBeUpdated(true);
/*  678 */     if (!acroForm.isSignaturesExist())
/*      */     {
/*      */       
/*  681 */       acroForm.setSignaturesExist(true);
/*      */     }
/*      */     
/*  684 */     List<PDField> acroformFields = acroForm.getFields();
/*      */     
/*  686 */     for (PDSignatureField sigField : sigFields) {
/*      */       
/*  688 */       sigField.getCOSObject().setNeedToBeUpdated(true);
/*      */ 
/*      */       
/*  691 */       boolean checkSignatureField = checkSignatureField(acroForm.getFieldIterator(), sigField);
/*  692 */       if (checkSignatureField) {
/*      */         
/*  694 */         sigField.getCOSObject().setNeedToBeUpdated(true);
/*      */       }
/*      */       else {
/*      */         
/*  698 */         acroformFields.add(sigField);
/*      */       } 
/*      */ 
/*      */       
/*  702 */       if (sigField.getSignature() != null) {
/*      */         
/*  704 */         sigField.getCOSObject().setNeedToBeUpdated(true);
/*  705 */         if (options == null);
/*      */ 
/*      */ 
/*      */         
/*  709 */         addSignature(sigField.getSignature(), signatureInterface, options);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removePage(PDPage page) {
/*  721 */     getPages().remove(page);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removePage(int pageNumber) {
/*  731 */     getPages().remove(pageNumber);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDPage importPage(PDPage page) throws IOException {
/*  761 */     PDPage importedPage = new PDPage(new COSDictionary(page.getCOSObject()), this.resourceCache);
/*  762 */     PDStream dest = new PDStream(this, page.getContents(), COSName.FLATE_DECODE);
/*  763 */     importedPage.setContents(dest);
/*  764 */     addPage(importedPage);
/*  765 */     importedPage.setCropBox(page.getCropBox());
/*  766 */     importedPage.setMediaBox(page.getMediaBox());
/*  767 */     importedPage.setRotation(page.getRotation());
/*  768 */     if (page.getResources() != null && !page.getCOSObject().containsKey(COSName.RESOURCES)) {
/*      */       
/*  770 */       LOG.warn("inherited resources of source document are not imported to destination page");
/*  771 */       LOG.warn("call importedPage.setResources(page.getResources()) to do this");
/*      */     } 
/*  773 */     return importedPage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSDocument getDocument() {
/*  783 */     return this.document;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDDocumentInformation getDocumentInformation() {
/*  794 */     if (this.documentInformation == null) {
/*      */       
/*  796 */       COSDictionary trailer = this.document.getTrailer();
/*  797 */       COSDictionary infoDic = trailer.getCOSDictionary(COSName.INFO);
/*  798 */       if (infoDic == null) {
/*      */         
/*  800 */         infoDic = new COSDictionary();
/*  801 */         trailer.setItem(COSName.INFO, (COSBase)infoDic);
/*      */       } 
/*  803 */       this.documentInformation = new PDDocumentInformation(infoDic);
/*      */     } 
/*  805 */     return this.documentInformation;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentInformation(PDDocumentInformation info) {
/*  815 */     this.documentInformation = info;
/*  816 */     this.document.getTrailer().setItem(COSName.INFO, (COSBase)info.getCOSObject());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDDocumentCatalog getDocumentCatalog() {
/*  826 */     if (this.documentCatalog == null) {
/*      */       
/*  828 */       COSDictionary trailer = this.document.getTrailer();
/*  829 */       COSBase dictionary = trailer.getDictionaryObject(COSName.ROOT);
/*  830 */       if (dictionary instanceof COSDictionary) {
/*      */         
/*  832 */         this.documentCatalog = new PDDocumentCatalog(this, (COSDictionary)dictionary);
/*      */       }
/*      */       else {
/*      */         
/*  836 */         this.documentCatalog = new PDDocumentCatalog(this);
/*      */       } 
/*      */     } 
/*  839 */     return this.documentCatalog;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEncrypted() {
/*  849 */     return this.document.isEncrypted();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDEncryption getEncryption() {
/*  862 */     if (this.encryption == null && isEncrypted())
/*      */     {
/*  864 */       this.encryption = new PDEncryption(this.document.getEncryptionDictionary());
/*      */     }
/*  866 */     return this.encryption;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEncryptionDictionary(PDEncryption encryption) throws IOException {
/*  878 */     this.encryption = encryption;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDSignature getLastSignatureDictionary() throws IOException {
/*  890 */     List<PDSignature> signatureDictionaries = getSignatureDictionaries();
/*  891 */     int size = signatureDictionaries.size();
/*  892 */     if (size > 0)
/*      */     {
/*  894 */       return signatureDictionaries.get(size - 1);
/*      */     }
/*  896 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<PDSignatureField> getSignatureFields() throws IOException {
/*  907 */     List<PDSignatureField> fields = new ArrayList<PDSignatureField>();
/*  908 */     PDAcroForm acroForm = getDocumentCatalog().getAcroForm();
/*  909 */     if (acroForm != null)
/*      */     {
/*  911 */       for (PDField field : acroForm.getFieldTree()) {
/*      */         
/*  913 */         if (field instanceof PDSignatureField)
/*      */         {
/*  915 */           fields.add((PDSignatureField)field);
/*      */         }
/*      */       } 
/*      */     }
/*  919 */     return fields;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<PDSignature> getSignatureDictionaries() throws IOException {
/*  930 */     List<PDSignature> signatures = new ArrayList<PDSignature>();
/*  931 */     for (PDSignatureField field : getSignatureFields()) {
/*      */       
/*  933 */       COSBase value = field.getCOSObject().getDictionaryObject(COSName.V);
/*  934 */       if (value != null)
/*      */       {
/*  936 */         signatures.add(new PDSignature((COSDictionary)value));
/*      */       }
/*      */     } 
/*  939 */     return signatures;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void registerTrueTypeFontForClosing(TrueTypeFont ttf) {
/*  951 */     this.fontsToClose.add(ttf);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   Set<PDFont> getFontsToSubset() {
/*  959 */     return this.fontsToSubset;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(File file) throws InvalidPasswordException, IOException {
/*  974 */     return load(file, "", MemoryUsageSetting.setupMainMemoryOnly());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(File file, MemoryUsageSetting memUsageSetting) throws InvalidPasswordException, IOException {
/*  991 */     return load(file, "", (InputStream)null, (String)null, memUsageSetting);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(File file, String password) throws InvalidPasswordException, IOException {
/* 1008 */     return load(file, password, (InputStream)null, (String)null, MemoryUsageSetting.setupMainMemoryOnly());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(File file, String password, MemoryUsageSetting memUsageSetting) throws InvalidPasswordException, IOException {
/* 1026 */     return load(file, password, (InputStream)null, (String)null, memUsageSetting);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(File file, String password, InputStream keyStore, String alias) throws IOException {
/* 1044 */     return load(file, password, keyStore, alias, MemoryUsageSetting.setupMainMemoryOnly());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(File file, String password, InputStream keyStore, String alias, MemoryUsageSetting memUsageSetting) throws IOException {
/* 1063 */     RandomAccessBufferedFileInputStream raFile = new RandomAccessBufferedFileInputStream(file);
/*      */     
/*      */     try {
/* 1066 */       ScratchFile scratchFile = new ScratchFile(memUsageSetting);
/*      */       
/*      */       try {
/* 1069 */         PDFParser parser = new PDFParser((RandomAccessRead)raFile, password, keyStore, alias, scratchFile);
/* 1070 */         parser.parse();
/* 1071 */         return parser.getPDDocument();
/*      */       }
/* 1073 */       catch (IOException ioe) {
/*      */         
/* 1075 */         IOUtils.closeQuietly((Closeable)scratchFile);
/* 1076 */         throw ioe;
/*      */       }
/*      */     
/* 1079 */     } catch (IOException ioe) {
/*      */       
/* 1081 */       IOUtils.closeQuietly((Closeable)raFile);
/* 1082 */       throw ioe;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(InputStream input) throws InvalidPasswordException, IOException {
/* 1099 */     return load(input, "", (InputStream)null, (String)null, MemoryUsageSetting.setupMainMemoryOnly());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(InputStream input, MemoryUsageSetting memUsageSetting) throws InvalidPasswordException, IOException {
/* 1117 */     return load(input, "", (InputStream)null, (String)null, memUsageSetting);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(InputStream input, String password) throws InvalidPasswordException, IOException {
/* 1135 */     return load(input, password, (InputStream)null, (String)null, MemoryUsageSetting.setupMainMemoryOnly());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(InputStream input, String password, InputStream keyStore, String alias) throws IOException {
/* 1154 */     return load(input, password, keyStore, alias, MemoryUsageSetting.setupMainMemoryOnly());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(InputStream input, String password, MemoryUsageSetting memUsageSetting) throws InvalidPasswordException, IOException {
/* 1173 */     return load(input, password, (InputStream)null, (String)null, memUsageSetting);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(InputStream input, String password, InputStream keyStore, String alias, MemoryUsageSetting memUsageSetting) throws IOException {
/* 1194 */     ScratchFile scratchFile = new ScratchFile(memUsageSetting);
/*      */     
/*      */     try {
/* 1197 */       RandomAccess randomAccess = scratchFile.createBuffer(input);
/* 1198 */       PDFParser parser = new PDFParser((RandomAccessRead)randomAccess, password, keyStore, alias, scratchFile);
/* 1199 */       parser.parse();
/* 1200 */       return parser.getPDDocument();
/*      */     }
/* 1202 */     catch (IOException ioe) {
/*      */       
/* 1204 */       IOUtils.closeQuietly((Closeable)scratchFile);
/* 1205 */       throw ioe;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(byte[] input) throws InvalidPasswordException, IOException {
/* 1221 */     return load(input, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(byte[] input, String password) throws InvalidPasswordException, IOException {
/* 1238 */     return load(input, password, (InputStream)null, (String)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(byte[] input, String password, InputStream keyStore, String alias) throws IOException {
/* 1257 */     return load(input, password, keyStore, alias, MemoryUsageSetting.setupMainMemoryOnly());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static PDDocument load(byte[] input, String password, InputStream keyStore, String alias, MemoryUsageSetting memUsageSetting) throws IOException {
/* 1277 */     ScratchFile scratchFile = new ScratchFile(memUsageSetting);
/* 1278 */     RandomAccessBuffer randomAccessBuffer = new RandomAccessBuffer(input);
/* 1279 */     PDFParser parser = new PDFParser((RandomAccessRead)randomAccessBuffer, password, keyStore, alias, scratchFile);
/* 1280 */     parser.parse();
/* 1281 */     return parser.getPDDocument();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void save(String fileName) throws IOException {
/* 1293 */     save(new File(fileName));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void save(File file) throws IOException {
/* 1305 */     save(new BufferedOutputStream(new FileOutputStream(file)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void save(OutputStream output) throws IOException {
/* 1318 */     if (this.document.isClosed())
/*      */     {
/* 1320 */       throw new IOException("Cannot save a document which has been closed");
/*      */     }
/*      */ 
/*      */     
/* 1324 */     for (PDFont font : this.fontsToSubset)
/*      */     {
/* 1326 */       font.subset();
/*      */     }
/* 1328 */     this.fontsToSubset.clear();
/*      */ 
/*      */     
/* 1331 */     COSWriter writer = new COSWriter(output);
/*      */     
/*      */     try {
/* 1334 */       writer.write(this);
/*      */     }
/*      */     finally {
/*      */       
/* 1338 */       writer.close();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveIncremental(OutputStream output) throws IOException {
/* 1361 */     COSWriter writer = null;
/*      */     
/*      */     try {
/* 1364 */       if (this.pdfSource == null)
/*      */       {
/* 1366 */         throw new IllegalStateException("document was not loaded from a file or a stream");
/*      */       }
/* 1368 */       writer = new COSWriter(output, this.pdfSource);
/* 1369 */       writer.write(this, this.signInterface);
/*      */     }
/*      */     finally {
/*      */       
/* 1373 */       if (writer != null)
/*      */       {
/* 1375 */         writer.close();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ExternalSigningSupport saveIncrementalForExternalSigning(OutputStream output) throws IOException {
/* 1421 */     if (this.pdfSource == null)
/*      */     {
/* 1423 */       throw new IllegalStateException("document was not loaded from a file or a stream");
/*      */     }
/*      */ 
/*      */     
/* 1427 */     PDSignature foundSignature = null;
/* 1428 */     for (PDSignature sig : getSignatureDictionaries()) {
/*      */       
/* 1430 */       foundSignature = sig;
/* 1431 */       if (sig.getCOSObject().isNeedToBeUpdated()) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */     
/* 1436 */     int[] byteRange = foundSignature.getByteRange();
/* 1437 */     if (!Arrays.equals(byteRange, RESERVE_BYTE_RANGE))
/*      */     {
/* 1439 */       throw new IllegalStateException("signature reserve byte range has been changed after addSignature(), please set the byte range that existed after addSignature()");
/*      */     }
/*      */     
/* 1442 */     COSWriter writer = new COSWriter(output, this.pdfSource);
/* 1443 */     writer.write(this);
/* 1444 */     this.signingSupport = new SigningSupport(writer);
/* 1445 */     return (ExternalSigningSupport)this.signingSupport;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDPage getPage(int pageIndex) {
/* 1460 */     return getDocumentCatalog().getPages().get(pageIndex);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDPageTree getPages() {
/* 1470 */     return getDocumentCatalog().getPages();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfPages() {
/* 1480 */     return getDocumentCatalog().getPages().getCount();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void close() throws IOException {
/* 1491 */     if (!this.document.isClosed()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1498 */       IOException firstException = null;
/*      */ 
/*      */       
/* 1501 */       if (this.signingSupport != null)
/*      */       {
/* 1503 */         firstException = IOUtils.closeAndLogException((Closeable)this.signingSupport, LOG, "SigningSupport", firstException);
/*      */       }
/*      */ 
/*      */       
/* 1507 */       firstException = IOUtils.closeAndLogException((Closeable)this.document, LOG, "COSDocument", firstException);
/*      */ 
/*      */       
/* 1510 */       if (this.pdfSource != null)
/*      */       {
/* 1512 */         firstException = IOUtils.closeAndLogException((Closeable)this.pdfSource, LOG, "RandomAccessRead pdfSource", firstException);
/*      */       }
/*      */ 
/*      */       
/* 1516 */       for (TrueTypeFont ttf : this.fontsToClose)
/*      */       {
/* 1518 */         firstException = IOUtils.closeAndLogException((Closeable)ttf, LOG, "TrueTypeFont", firstException);
/*      */       }
/*      */ 
/*      */       
/* 1522 */       if (firstException != null)
/*      */       {
/* 1524 */         throw firstException;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void protect(ProtectionPolicy policy) throws IOException {
/* 1543 */     if (isAllSecurityToBeRemoved()) {
/*      */       
/* 1545 */       LOG.warn("do not call setAllSecurityToBeRemoved(true) before calling protect(), as protect() implies setAllSecurityToBeRemoved(false)");
/*      */       
/* 1547 */       setAllSecurityToBeRemoved(false);
/*      */     } 
/*      */     
/* 1550 */     if (!isEncrypted())
/*      */     {
/* 1552 */       this.encryption = new PDEncryption();
/*      */     }
/*      */     
/* 1555 */     SecurityHandler securityHandler = SecurityHandlerFactory.INSTANCE.newSecurityHandlerForPolicy(policy);
/* 1556 */     if (securityHandler == null)
/*      */     {
/* 1558 */       throw new IOException("No security handler for policy " + policy);
/*      */     }
/*      */     
/* 1561 */     getEncryption().setSecurityHandler(securityHandler);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AccessPermission getCurrentAccessPermission() {
/* 1574 */     if (this.accessPermission == null)
/*      */     {
/* 1576 */       this.accessPermission = AccessPermission.getOwnerAccessPermission();
/*      */     }
/* 1578 */     return this.accessPermission;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAllSecurityToBeRemoved() {
/* 1588 */     return this.allSecurityToBeRemoved;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAllSecurityToBeRemoved(boolean removeAllSecurity) {
/* 1598 */     this.allSecurityToBeRemoved = removeAllSecurity;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Long getDocumentId() {
/* 1608 */     return this.documentId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentId(Long docId) {
/* 1618 */     this.documentId = docId;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getVersion() {
/* 1628 */     float headerVersionFloat = getDocument().getVersion();
/*      */     
/* 1630 */     if (headerVersionFloat >= 1.4F) {
/*      */       
/* 1632 */       String catalogVersion = getDocumentCatalog().getVersion();
/* 1633 */       float catalogVersionFloat = -1.0F;
/* 1634 */       if (catalogVersion != null) {
/*      */         
/*      */         try {
/*      */           
/* 1638 */           catalogVersionFloat = Float.parseFloat(catalogVersion);
/*      */         }
/* 1640 */         catch (NumberFormatException exception) {
/*      */           
/* 1642 */           LOG.error("Can't extract the version number of the document catalog.", exception);
/*      */         } 
/*      */       }
/*      */       
/* 1646 */       return Math.max(catalogVersionFloat, headerVersionFloat);
/*      */     } 
/*      */ 
/*      */     
/* 1650 */     return headerVersionFloat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setVersion(float newVersion) {
/* 1662 */     float currentVersion = getVersion();
/*      */     
/* 1664 */     if (newVersion == currentVersion) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/* 1669 */     if (newVersion < currentVersion) {
/*      */       
/* 1671 */       LOG.error("It's not allowed to downgrade the version of a pdf.");
/*      */       
/*      */       return;
/*      */     } 
/* 1675 */     if (getDocument().getVersion() >= 1.4F) {
/*      */       
/* 1677 */       getDocumentCatalog().setVersion(Float.toString(newVersion));
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1682 */       getDocument().setVersion(newVersion);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ResourceCache getResourceCache() {
/* 1693 */     return this.resourceCache;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setResourceCache(ResourceCache resourceCache) {
/* 1703 */     this.resourceCache = resourceCache;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDDocument.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */