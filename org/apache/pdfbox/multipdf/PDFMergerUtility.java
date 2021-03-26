/*      */ package org.apache.pdfbox.multipdf;
/*      */ 
/*      */ import java.io.Closeable;
/*      */ import java.io.File;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.logging.Log;
/*      */ import org.apache.commons.logging.LogFactory;
/*      */ import org.apache.pdfbox.cos.COSArray;
/*      */ import org.apache.pdfbox.cos.COSBase;
/*      */ import org.apache.pdfbox.cos.COSDictionary;
/*      */ import org.apache.pdfbox.cos.COSInteger;
/*      */ import org.apache.pdfbox.cos.COSName;
/*      */ import org.apache.pdfbox.cos.COSNumber;
/*      */ import org.apache.pdfbox.cos.COSStream;
/*      */ import org.apache.pdfbox.io.IOUtils;
/*      */ import org.apache.pdfbox.io.MemoryUsageSetting;
/*      */ import org.apache.pdfbox.pdmodel.PDDocument;
/*      */ import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
/*      */ import org.apache.pdfbox.pdmodel.PDDocumentInformation;
/*      */ import org.apache.pdfbox.pdmodel.PDDocumentNameDestinationDictionary;
/*      */ import org.apache.pdfbox.pdmodel.PDDocumentNameDictionary;
/*      */ import org.apache.pdfbox.pdmodel.PDPage;
/*      */ import org.apache.pdfbox.pdmodel.PDResources;
/*      */ import org.apache.pdfbox.pdmodel.PDStructureElementNameTreeNode;
/*      */ import org.apache.pdfbox.pdmodel.PageMode;
/*      */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*      */ import org.apache.pdfbox.pdmodel.common.PDDestinationOrAction;
/*      */ import org.apache.pdfbox.pdmodel.common.PDMetadata;
/*      */ import org.apache.pdfbox.pdmodel.common.PDNameTreeNode;
/*      */ import org.apache.pdfbox.pdmodel.common.PDNumberTreeNode;
/*      */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*      */ import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDMarkInfo;
/*      */ import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDParentTreeValue;
/*      */ import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureElement;
/*      */ import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureTreeRoot;
/*      */ import org.apache.pdfbox.pdmodel.graphics.color.PDOutputIntent;
/*      */ import org.apache.pdfbox.pdmodel.interactive.action.PDActionGoTo;
/*      */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*      */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationWidget;
/*      */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
/*      */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
/*      */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
/*      */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
/*      */ import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
/*      */ import org.apache.pdfbox.pdmodel.interactive.form.PDField;
/*      */ import org.apache.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences;
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
/*      */ public class PDFMergerUtility
/*      */ {
/*   90 */   private static final Log LOG = LogFactory.getLog(PDFMergerUtility.class);
/*      */   
/*      */   private final List<Object> sources;
/*      */   private String destinationFileName;
/*      */   private OutputStream destinationStream;
/*      */   private boolean ignoreAcroFormErrors = false;
/*   96 */   private PDDocumentInformation destinationDocumentInformation = null;
/*   97 */   private PDMetadata destinationMetadata = null;
/*      */   
/*   99 */   private DocumentMergeMode documentMergeMode = DocumentMergeMode.PDFBOX_LEGACY_MODE;
/*  100 */   private AcroFormMergeMode acroFormMergeMode = AcroFormMergeMode.PDFBOX_LEGACY_MODE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private int nextFieldNum;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public enum DocumentMergeMode
/*      */   {
/*  119 */     OPTIMIZE_RESOURCES_MODE,
/*  120 */     PDFBOX_LEGACY_MODE;
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
/*      */   public enum AcroFormMergeMode
/*      */   {
/*  140 */     JOIN_FORM_FIELDS_MODE,
/*  141 */     PDFBOX_LEGACY_MODE;
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
/*      */   public AcroFormMergeMode getAcroFormMergeMode() {
/*  159 */     return this.acroFormMergeMode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAcroFormMergeMode(AcroFormMergeMode theAcroFormMergeMode) {
/*  169 */     this.acroFormMergeMode = theAcroFormMergeMode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDocumentMergeMode(DocumentMergeMode theDocumentMergeMode) {
/*  179 */     this.documentMergeMode = theDocumentMergeMode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public DocumentMergeMode getDocumentMergeMode() {
/*  189 */     return this.documentMergeMode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getDestinationFileName() {
/*  199 */     return this.destinationFileName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDestinationFileName(String destination) {
/*  209 */     this.destinationFileName = destination;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public OutputStream getDestinationStream() {
/*  219 */     return this.destinationStream;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDestinationStream(OutputStream destStream) {
/*  229 */     this.destinationStream = destStream;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDDocumentInformation getDestinationDocumentInformation() {
/*  240 */     return this.destinationDocumentInformation;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDestinationDocumentInformation(PDDocumentInformation info) {
/*  251 */     this.destinationDocumentInformation = info;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PDMetadata getDestinationMetadata() {
/*  262 */     return this.destinationMetadata;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDestinationMetadata(PDMetadata meta) {
/*  273 */     this.destinationMetadata = meta;
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
/*      */   public void addSource(String source) throws FileNotFoundException {
/*  285 */     addSource(new File(source));
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
/*      */   public void addSource(File source) throws FileNotFoundException {
/*  297 */     this.sources.add(source);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSource(InputStream source) {
/*  307 */     this.sources.add(source);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addSources(List<InputStream> sourcesList) {
/*  318 */     this.sources.addAll(sourcesList);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void mergeDocuments() throws IOException {
/*  330 */     mergeDocuments(MemoryUsageSetting.setupMainMemoryOnly());
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
/*      */   public void mergeDocuments(MemoryUsageSetting memUsageSetting) throws IOException {
/*  344 */     if (this.documentMergeMode == DocumentMergeMode.PDFBOX_LEGACY_MODE) {
/*      */       
/*  346 */       legacyMergeDocuments(memUsageSetting);
/*      */     }
/*  348 */     else if (this.documentMergeMode == DocumentMergeMode.OPTIMIZE_RESOURCES_MODE) {
/*      */       
/*  350 */       optimizedMergeDocuments(memUsageSetting);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void optimizedMergeDocuments(MemoryUsageSetting memUsageSetting) throws IOException {
/*  356 */     PDDocument destination = null;
/*      */     
/*      */     try {
/*  359 */       destination = new PDDocument(memUsageSetting);
/*  360 */       PDFCloneUtility cloner = new PDFCloneUtility(destination);
/*      */       
/*  362 */       for (Object sourceObject : this.sources) {
/*      */         
/*  364 */         PDDocument sourceDoc = null;
/*      */         
/*      */         try {
/*  367 */           if (sourceObject instanceof File) {
/*      */             
/*  369 */             sourceDoc = PDDocument.load((File)sourceObject, memUsageSetting);
/*      */           }
/*      */           else {
/*      */             
/*  373 */             sourceDoc = PDDocument.load((InputStream)sourceObject, memUsageSetting);
/*      */           } 
/*      */           
/*  376 */           for (PDPage page : sourceDoc.getPages())
/*      */           {
/*  378 */             PDPage newPage = new PDPage((COSDictionary)cloner.cloneForNewDocument(page.getCOSObject()));
/*  379 */             newPage.setCropBox(page.getCropBox());
/*  380 */             newPage.setMediaBox(page.getMediaBox());
/*  381 */             newPage.setRotation(page.getRotation());
/*  382 */             PDResources resources = page.getResources();
/*  383 */             if (resources != null) {
/*      */ 
/*      */               
/*  386 */               newPage.setResources(new PDResources((COSDictionary)cloner.cloneForNewDocument(resources)));
/*      */             }
/*      */             else {
/*      */               
/*  390 */               newPage.setResources(new PDResources());
/*      */             } 
/*  392 */             destination.addPage(newPage);
/*      */           }
/*      */         
/*      */         } finally {
/*      */           
/*  397 */           IOUtils.closeQuietly((Closeable)sourceDoc);
/*      */         } 
/*      */       } 
/*      */       
/*  401 */       if (this.destinationStream == null)
/*      */       {
/*  403 */         destination.save(this.destinationFileName);
/*      */       }
/*      */       else
/*      */       {
/*  407 */         destination.save(this.destinationStream);
/*      */       }
/*      */     
/*      */     } finally {
/*      */       
/*  412 */       IOUtils.closeQuietly((Closeable)destination);
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
/*      */   private void legacyMergeDocuments(MemoryUsageSetting memUsageSetting) throws IOException {
/*  427 */     PDDocument destination = null;
/*  428 */     if (this.sources != null && this.sources.size() > 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  437 */       List<PDDocument> tobeclosed = new ArrayList<PDDocument>();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/*  443 */         MemoryUsageSetting partitionedMemSetting = (memUsageSetting != null) ? memUsageSetting.getPartitionedCopy(this.sources.size() + 1) : MemoryUsageSetting.setupMainMemoryOnly();
/*  444 */         destination = new PDDocument(partitionedMemSetting);
/*      */         
/*  446 */         for (Object sourceObject : this.sources) {
/*      */           
/*  448 */           PDDocument sourceDoc = null;
/*  449 */           if (sourceObject instanceof File) {
/*      */             
/*  451 */             sourceDoc = PDDocument.load((File)sourceObject, partitionedMemSetting);
/*      */           }
/*      */           else {
/*      */             
/*  455 */             sourceDoc = PDDocument.load((InputStream)sourceObject, partitionedMemSetting);
/*      */           } 
/*      */           
/*  458 */           tobeclosed.add(sourceDoc);
/*  459 */           appendDocument(destination, sourceDoc);
/*      */         } 
/*      */ 
/*      */         
/*  463 */         if (this.destinationDocumentInformation != null)
/*      */         {
/*  465 */           destination.setDocumentInformation(this.destinationDocumentInformation);
/*      */         }
/*  467 */         if (this.destinationMetadata != null)
/*      */         {
/*  469 */           destination.getDocumentCatalog().setMetadata(this.destinationMetadata);
/*      */         }
/*      */         
/*  472 */         if (this.destinationStream == null)
/*      */         {
/*  474 */           destination.save(this.destinationFileName);
/*      */         }
/*      */         else
/*      */         {
/*  478 */           destination.save(this.destinationStream);
/*      */         }
/*      */       
/*      */       } finally {
/*      */         
/*  483 */         if (destination != null)
/*      */         {
/*  485 */           IOUtils.closeAndLogException((Closeable)destination, LOG, "PDDocument", null);
/*      */         }
/*      */         
/*  488 */         for (PDDocument doc : tobeclosed)
/*      */         {
/*  490 */           IOUtils.closeAndLogException((Closeable)doc, LOG, "PDDocument", null);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void appendDocument(PDDocument destination, PDDocument source) throws IOException {
/*  507 */     if (source.getDocument().isClosed())
/*      */     {
/*  509 */       throw new IOException("Error: source PDF is closed.");
/*      */     }
/*  511 */     if (destination.getDocument().isClosed())
/*      */     {
/*  513 */       throw new IOException("Error: destination PDF is closed.");
/*      */     }
/*      */     
/*  516 */     PDDocumentCatalog destCatalog = destination.getDocumentCatalog();
/*  517 */     PDDocumentCatalog srcCatalog = source.getDocumentCatalog();
/*      */     
/*  519 */     if (isDynamicXfa(srcCatalog.getAcroForm()))
/*      */     {
/*  521 */       throw new IOException("Error: can't merge source document containing dynamic XFA form content.");
/*      */     }
/*      */     
/*  524 */     PDDocumentInformation destInfo = destination.getDocumentInformation();
/*  525 */     PDDocumentInformation srcInfo = source.getDocumentInformation();
/*  526 */     mergeInto(srcInfo.getCOSObject(), destInfo.getCOSObject(), Collections.emptySet());
/*      */ 
/*      */     
/*  529 */     float destVersion = destination.getVersion();
/*  530 */     float srcVersion = source.getVersion();
/*      */     
/*  532 */     if (destVersion < srcVersion)
/*      */     {
/*  534 */       destination.setVersion(srcVersion);
/*      */     }
/*      */     
/*  537 */     int pageIndexOpenActionDest = -1;
/*  538 */     if (destCatalog.getOpenAction() == null) {
/*      */ 
/*      */       
/*  541 */       PDDestinationOrAction openAction = null;
/*      */       
/*      */       try {
/*  544 */         openAction = srcCatalog.getOpenAction();
/*      */       }
/*  546 */       catch (IOException ex) {
/*      */ 
/*      */         
/*  549 */         LOG.error("Invalid OpenAction ignored", ex);
/*      */       } 
/*  551 */       PDDestination openActionDestination = null;
/*  552 */       if (openAction instanceof PDActionGoTo) {
/*      */         
/*  554 */         openActionDestination = ((PDActionGoTo)openAction).getDestination();
/*      */       }
/*  556 */       else if (openAction instanceof PDDestination) {
/*      */         
/*  558 */         openActionDestination = (PDDestination)openAction;
/*      */       } 
/*      */ 
/*      */       
/*  562 */       if (openActionDestination instanceof PDPageDestination) {
/*      */         
/*  564 */         PDPage page = ((PDPageDestination)openActionDestination).getPage();
/*  565 */         if (page != null)
/*      */         {
/*  567 */           pageIndexOpenActionDest = srcCatalog.getPages().indexOf(page);
/*      */         }
/*      */       } 
/*      */       
/*  571 */       destCatalog.setOpenAction(openAction);
/*      */     } 
/*      */     
/*  574 */     PDFCloneUtility cloner = new PDFCloneUtility(destination);
/*  575 */     mergeAcroForm(cloner, destCatalog, srcCatalog);
/*      */     
/*  577 */     COSArray destThreads = (COSArray)destCatalog.getCOSObject().getDictionaryObject(COSName.THREADS);
/*  578 */     COSArray srcThreads = (COSArray)cloner.cloneForNewDocument(destCatalog.getCOSObject().getDictionaryObject(COSName.THREADS));
/*      */     
/*  580 */     if (destThreads == null) {
/*      */       
/*  582 */       destCatalog.getCOSObject().setItem(COSName.THREADS, (COSBase)srcThreads);
/*      */     }
/*      */     else {
/*      */       
/*  586 */       destThreads.addAll(srcThreads);
/*      */     } 
/*      */     
/*  589 */     PDDocumentNameDictionary destNames = destCatalog.getNames();
/*  590 */     PDDocumentNameDictionary srcNames = srcCatalog.getNames();
/*  591 */     if (srcNames != null)
/*      */     {
/*  593 */       if (destNames == null) {
/*      */         
/*  595 */         destCatalog.getCOSObject().setItem(COSName.NAMES, cloner.cloneForNewDocument(srcNames));
/*      */       }
/*      */       else {
/*      */         
/*  599 */         cloner.cloneMerge((COSObjectable)srcNames, (COSObjectable)destNames);
/*      */       } 
/*      */     }
/*      */     
/*  603 */     if (destNames != null) {
/*      */ 
/*      */       
/*  606 */       destNames.getCOSObject().removeItem(COSName.ID_TREE);
/*  607 */       LOG.warn("Removed /IDTree from /Names dictionary, doesn't belong there");
/*      */     } 
/*      */     
/*  610 */     PDDocumentNameDestinationDictionary destDests = destCatalog.getDests();
/*  611 */     PDDocumentNameDestinationDictionary srcDests = srcCatalog.getDests();
/*  612 */     if (srcDests != null)
/*      */     {
/*  614 */       if (destDests == null) {
/*      */         
/*  616 */         destCatalog.getCOSObject().setItem(COSName.DESTS, cloner.cloneForNewDocument(srcDests));
/*      */       }
/*      */       else {
/*      */         
/*  620 */         cloner.cloneMerge((COSObjectable)srcDests, (COSObjectable)destDests);
/*      */       } 
/*      */     }
/*      */     
/*  624 */     PDDocumentOutline destOutline = destCatalog.getDocumentOutline();
/*  625 */     PDDocumentOutline srcOutline = srcCatalog.getDocumentOutline();
/*  626 */     if (srcOutline != null)
/*      */     {
/*  628 */       if (destOutline == null || destOutline.getFirstChild() == null) {
/*      */         
/*  630 */         PDDocumentOutline cloned = new PDDocumentOutline((COSDictionary)cloner.cloneForNewDocument(srcOutline));
/*  631 */         destCatalog.setDocumentOutline(cloned);
/*      */       
/*      */       }
/*      */       else {
/*      */         
/*  636 */         PDOutlineItem destLastOutlineItem = destOutline.getFirstChild();
/*  637 */         while (destLastOutlineItem.getNextSibling() != null)
/*      */         {
/*  639 */           destLastOutlineItem = destLastOutlineItem.getNextSibling();
/*      */         }
/*  641 */         for (PDOutlineItem item : srcOutline.children()) {
/*      */ 
/*      */ 
/*      */           
/*  645 */           COSDictionary clonedDict = (COSDictionary)cloner.cloneForNewDocument(item);
/*  646 */           clonedDict.removeItem(COSName.PREV);
/*  647 */           clonedDict.removeItem(COSName.NEXT);
/*  648 */           PDOutlineItem clonedItem = new PDOutlineItem(clonedDict);
/*  649 */           destLastOutlineItem.insertSiblingAfter(clonedItem);
/*  650 */           destLastOutlineItem = destLastOutlineItem.getNextSibling();
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/*  655 */     PageMode destPageMode = destCatalog.getPageMode();
/*  656 */     PageMode srcPageMode = srcCatalog.getPageMode();
/*  657 */     if (destPageMode == null)
/*      */     {
/*  659 */       destCatalog.setPageMode(srcPageMode);
/*      */     }
/*      */     
/*  662 */     COSDictionary destLabels = (COSDictionary)destCatalog.getCOSObject().getDictionaryObject(COSName.PAGE_LABELS);
/*      */ 
/*      */     
/*  665 */     COSDictionary srcLabels = (COSDictionary)srcCatalog.getCOSObject().getDictionaryObject(COSName.PAGE_LABELS);
/*  666 */     if (srcLabels != null) {
/*      */       COSArray destNums;
/*  668 */       int destPageCount = destination.getNumberOfPages();
/*      */       
/*  670 */       if (destLabels == null) {
/*      */         
/*  672 */         destLabels = new COSDictionary();
/*  673 */         destNums = new COSArray();
/*  674 */         destLabels.setItem(COSName.NUMS, (COSBase)destNums);
/*  675 */         destCatalog.getCOSObject().setItem(COSName.PAGE_LABELS, (COSBase)destLabels);
/*      */       }
/*      */       else {
/*      */         
/*  679 */         destNums = (COSArray)destLabels.getDictionaryObject(COSName.NUMS);
/*      */       } 
/*  681 */       COSArray srcNums = (COSArray)srcLabels.getDictionaryObject(COSName.NUMS);
/*  682 */       if (srcNums != null) {
/*      */         
/*  684 */         int startSize = destNums.size();
/*  685 */         for (int i = 0; i < srcNums.size(); i += 2) {
/*      */           
/*  687 */           COSBase base = srcNums.getObject(i);
/*  688 */           if (!(base instanceof COSNumber)) {
/*      */             
/*  690 */             LOG.error("page labels ignored, index " + i + " should be a number, but is " + base);
/*      */             
/*  692 */             while (destNums.size() > startSize)
/*      */             {
/*  694 */               destNums.remove(startSize);
/*      */             }
/*      */             break;
/*      */           } 
/*  698 */           COSNumber labelIndex = (COSNumber)base;
/*  699 */           long labelIndexValue = labelIndex.intValue();
/*  700 */           destNums.add((COSBase)COSInteger.get(labelIndexValue + destPageCount));
/*  701 */           destNums.add(cloner.cloneForNewDocument(srcNums.getObject(i + 1)));
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  706 */     COSStream destMetadata = (COSStream)destCatalog.getCOSObject().getDictionaryObject(COSName.METADATA);
/*  707 */     COSStream srcMetadata = (COSStream)srcCatalog.getCOSObject().getDictionaryObject(COSName.METADATA);
/*  708 */     if (destMetadata == null && srcMetadata != null) {
/*      */       
/*      */       try {
/*      */         
/*  712 */         PDStream newStream = new PDStream(destination, (InputStream)srcMetadata.createInputStream(), (COSName)null);
/*  713 */         mergeInto((COSDictionary)srcMetadata, (COSDictionary)newStream.getCOSObject(), new HashSet<COSName>(
/*  714 */               Arrays.asList(new COSName[] { COSName.FILTER, COSName.LENGTH })));
/*  715 */         destCatalog.getCOSObject().setItem(COSName.METADATA, (COSObjectable)newStream);
/*      */       }
/*  717 */       catch (IOException ex) {
/*      */ 
/*      */         
/*  720 */         LOG.error("Metadata skipped because it could not be read", ex);
/*      */       } 
/*      */     }
/*      */     
/*  724 */     COSDictionary destOCP = (COSDictionary)destCatalog.getCOSObject().getDictionaryObject(COSName.OCPROPERTIES);
/*  725 */     COSDictionary srcOCP = (COSDictionary)srcCatalog.getCOSObject().getDictionaryObject(COSName.OCPROPERTIES);
/*  726 */     if (destOCP == null && srcOCP != null) {
/*      */       
/*  728 */       destCatalog.getCOSObject().setItem(COSName.OCPROPERTIES, cloner.cloneForNewDocument(srcOCP));
/*      */     }
/*  730 */     else if (destOCP != null && srcOCP != null) {
/*      */       
/*  732 */       cloner.cloneMerge((COSObjectable)srcOCP, (COSObjectable)destOCP);
/*      */     } 
/*      */     
/*  735 */     mergeOutputIntents(cloner, srcCatalog, destCatalog);
/*      */ 
/*      */     
/*  738 */     boolean mergeStructTree = false;
/*  739 */     int destParentTreeNextKey = -1;
/*  740 */     Map<Integer, COSObjectable> srcNumberTreeAsMap = null;
/*  741 */     Map<Integer, COSObjectable> destNumberTreeAsMap = null;
/*  742 */     PDStructureTreeRoot srcStructTree = srcCatalog.getStructureTreeRoot();
/*  743 */     PDStructureTreeRoot destStructTree = destCatalog.getStructureTreeRoot();
/*  744 */     if (destStructTree == null && srcStructTree != null) {
/*      */ 
/*      */ 
/*      */       
/*  748 */       destStructTree = new PDStructureTreeRoot();
/*  749 */       destCatalog.setStructureTreeRoot(destStructTree);
/*  750 */       destStructTree.setParentTree(new PDNumberTreeNode(PDParentTreeValue.class));
/*      */       
/*  752 */       for (PDPage page : destCatalog.getPages()) {
/*      */         
/*  754 */         page.getCOSObject().removeItem(COSName.STRUCT_PARENTS);
/*  755 */         for (PDAnnotation ann : page.getAnnotations())
/*      */         {
/*  757 */           ann.getCOSObject().removeItem(COSName.STRUCT_PARENT);
/*      */         }
/*      */       } 
/*      */     } 
/*  761 */     if (destStructTree != null) {
/*      */       
/*  763 */       PDNumberTreeNode destParentTree = destStructTree.getParentTree();
/*  764 */       destParentTreeNextKey = destStructTree.getParentTreeNextKey();
/*  765 */       if (destParentTree != null) {
/*      */         
/*  767 */         destNumberTreeAsMap = getNumberTreeAsMap(destParentTree);
/*  768 */         if (destParentTreeNextKey < 0)
/*      */         {
/*  770 */           if (destNumberTreeAsMap.isEmpty()) {
/*      */             
/*  772 */             destParentTreeNextKey = 0;
/*      */           }
/*      */           else {
/*      */             
/*  776 */             destParentTreeNextKey = ((Integer)Collections.<Integer>max(destNumberTreeAsMap.keySet())).intValue() + 1;
/*      */           } 
/*      */         }
/*  779 */         if (destParentTreeNextKey >= 0 && srcStructTree != null) {
/*      */           
/*  781 */           PDNumberTreeNode srcParentTree = srcStructTree.getParentTree();
/*  782 */           if (srcParentTree != null) {
/*      */             
/*  784 */             srcNumberTreeAsMap = getNumberTreeAsMap(srcParentTree);
/*  785 */             if (!srcNumberTreeAsMap.isEmpty())
/*      */             {
/*  787 */               mergeStructTree = true;
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  794 */     Map<COSDictionary, COSDictionary> objMapping = new HashMap<COSDictionary, COSDictionary>();
/*  795 */     int pageIndex = 0;
/*  796 */     for (PDPage page : srcCatalog.getPages()) {
/*      */       
/*  798 */       PDPage newPage = new PDPage((COSDictionary)cloner.cloneForNewDocument(page.getCOSObject()));
/*  799 */       if (!mergeStructTree) {
/*      */ 
/*      */         
/*  802 */         newPage.getCOSObject().removeItem(COSName.STRUCT_PARENTS);
/*  803 */         for (PDAnnotation ann : newPage.getAnnotations())
/*      */         {
/*  805 */           ann.getCOSObject().removeItem(COSName.STRUCT_PARENT);
/*      */         }
/*      */       } 
/*  808 */       newPage.setCropBox(page.getCropBox());
/*  809 */       newPage.setMediaBox(page.getMediaBox());
/*  810 */       newPage.setRotation(page.getRotation());
/*  811 */       PDResources resources = page.getResources();
/*  812 */       if (resources != null) {
/*      */ 
/*      */         
/*  815 */         newPage.setResources(new PDResources((COSDictionary)cloner.cloneForNewDocument(resources)));
/*      */       }
/*      */       else {
/*      */         
/*  819 */         newPage.setResources(new PDResources());
/*      */       } 
/*  821 */       if (mergeStructTree) {
/*      */ 
/*      */ 
/*      */         
/*  825 */         updateStructParentEntries(newPage, destParentTreeNextKey);
/*  826 */         objMapping.put(page.getCOSObject(), newPage.getCOSObject());
/*  827 */         List<PDAnnotation> oldAnnots = page.getAnnotations();
/*  828 */         List<PDAnnotation> newAnnots = newPage.getAnnotations();
/*  829 */         for (int i = 0; i < oldAnnots.size(); i++)
/*      */         {
/*  831 */           objMapping.put(((PDAnnotation)oldAnnots.get(i)).getCOSObject(), ((PDAnnotation)newAnnots.get(i)).getCOSObject());
/*      */         }
/*      */       } 
/*      */       
/*  835 */       destination.addPage(newPage);
/*      */       
/*  837 */       if (pageIndex == pageIndexOpenActionDest) {
/*      */         PDPageDestination pageDestination;
/*      */ 
/*      */         
/*  841 */         PDDestinationOrAction openAction = destCatalog.getOpenAction();
/*      */         
/*  843 */         if (openAction instanceof PDActionGoTo) {
/*      */           
/*  845 */           pageDestination = (PDPageDestination)((PDActionGoTo)openAction).getDestination();
/*      */         }
/*      */         else {
/*      */           
/*  849 */           pageDestination = (PDPageDestination)openAction;
/*      */         } 
/*  851 */         pageDestination.setPage(newPage);
/*      */       } 
/*  853 */       pageIndex++;
/*      */     } 
/*  855 */     if (mergeStructTree) {
/*      */       
/*  857 */       updatePageReferences(cloner, srcNumberTreeAsMap, objMapping);
/*  858 */       int maxSrcKey = -1;
/*  859 */       for (Map.Entry<Integer, COSObjectable> entry : srcNumberTreeAsMap.entrySet()) {
/*      */         
/*  861 */         int srcKey = ((Integer)entry.getKey()).intValue();
/*  862 */         maxSrcKey = Math.max(srcKey, maxSrcKey);
/*  863 */         destNumberTreeAsMap.put(Integer.valueOf(destParentTreeNextKey + srcKey), cloner.cloneForNewDocument(entry.getValue()));
/*      */       } 
/*  865 */       destParentTreeNextKey += maxSrcKey + 1;
/*  866 */       PDNumberTreeNode newParentTreeNode = new PDNumberTreeNode(PDParentTreeValue.class);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  872 */       newParentTreeNode.setNumbers(destNumberTreeAsMap);
/*      */       
/*  874 */       destStructTree.setParentTree(newParentTreeNode);
/*  875 */       destStructTree.setParentTreeNextKey(destParentTreeNextKey);
/*      */       
/*  877 */       mergeKEntries(cloner, srcStructTree, destStructTree);
/*  878 */       mergeRoleMap(srcStructTree, destStructTree);
/*  879 */       mergeIDTree(cloner, srcStructTree, destStructTree);
/*  880 */       mergeMarkInfo(destCatalog, srcCatalog);
/*  881 */       mergeLanguage(destCatalog, srcCatalog);
/*  882 */       mergeViewerPreferences(destCatalog, srcCatalog);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void mergeViewerPreferences(PDDocumentCatalog destCatalog, PDDocumentCatalog srcCatalog) {
/*  888 */     PDViewerPreferences srcViewerPreferences = srcCatalog.getViewerPreferences();
/*  889 */     if (srcViewerPreferences == null) {
/*      */       return;
/*      */     }
/*      */     
/*  893 */     PDViewerPreferences destViewerPreferences = destCatalog.getViewerPreferences();
/*  894 */     if (destViewerPreferences == null) {
/*      */       
/*  896 */       destViewerPreferences = new PDViewerPreferences(new COSDictionary());
/*  897 */       destCatalog.setViewerPreferences(destViewerPreferences);
/*      */     } 
/*  899 */     mergeInto(srcViewerPreferences.getCOSObject(), destViewerPreferences.getCOSObject(), 
/*  900 */         Collections.emptySet());
/*      */ 
/*      */     
/*  903 */     if (srcViewerPreferences.hideToolbar() || destViewerPreferences.hideToolbar())
/*      */     {
/*  905 */       destViewerPreferences.setHideToolbar(true);
/*      */     }
/*  907 */     if (srcViewerPreferences.hideMenubar() || destViewerPreferences.hideMenubar())
/*      */     {
/*  909 */       destViewerPreferences.setHideMenubar(true);
/*      */     }
/*  911 */     if (srcViewerPreferences.hideWindowUI() || destViewerPreferences.hideWindowUI())
/*      */     {
/*  913 */       destViewerPreferences.setHideWindowUI(true);
/*      */     }
/*  915 */     if (srcViewerPreferences.fitWindow() || destViewerPreferences.fitWindow())
/*      */     {
/*  917 */       destViewerPreferences.setFitWindow(true);
/*      */     }
/*  919 */     if (srcViewerPreferences.centerWindow() || destViewerPreferences.centerWindow())
/*      */     {
/*  921 */       destViewerPreferences.setCenterWindow(true);
/*      */     }
/*  923 */     if (srcViewerPreferences.displayDocTitle() || destViewerPreferences.displayDocTitle())
/*      */     {
/*  925 */       destViewerPreferences.setDisplayDocTitle(true);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void mergeLanguage(PDDocumentCatalog destCatalog, PDDocumentCatalog srcCatalog) {
/*  931 */     if (destCatalog.getLanguage() == null && srcCatalog.getLanguage() != null)
/*      */     {
/*  933 */       destCatalog.setLanguage(srcCatalog.getLanguage());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void mergeMarkInfo(PDDocumentCatalog destCatalog, PDDocumentCatalog srcCatalog) {
/*  939 */     PDMarkInfo destMark = destCatalog.getMarkInfo();
/*  940 */     PDMarkInfo srcMark = srcCatalog.getMarkInfo();
/*  941 */     if (destMark == null)
/*      */     {
/*  943 */       destMark = new PDMarkInfo();
/*      */     }
/*  945 */     if (srcMark == null)
/*      */     {
/*  947 */       srcMark = new PDMarkInfo();
/*      */     }
/*  949 */     destMark.setMarked(true);
/*  950 */     destMark.setSuspect((srcMark.isSuspect() || destMark.isSuspect()));
/*  951 */     destMark.setSuspect((srcMark.usesUserProperties() || destMark.usesUserProperties()));
/*  952 */     destCatalog.setMarkInfo(destMark);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void mergeKEntries(PDFCloneUtility cloner, PDStructureTreeRoot srcStructTree, PDStructureTreeRoot destStructTree) throws IOException {
/*  960 */     COSArray newKArray = new COSArray();
/*  961 */     if (destStructTree.getK() != null) {
/*      */       
/*  963 */       COSBase base = destStructTree.getK();
/*  964 */       if (base instanceof COSArray) {
/*      */         
/*  966 */         newKArray.addAll((COSArray)base);
/*      */       }
/*      */       else {
/*      */         
/*  970 */         newKArray.add(base);
/*      */       } 
/*      */     } 
/*  973 */     if (srcStructTree.getK() != null) {
/*      */       
/*  975 */       COSBase base = cloner.cloneForNewDocument(srcStructTree.getK());
/*  976 */       if (base instanceof COSArray) {
/*      */         
/*  978 */         newKArray.addAll((COSArray)base);
/*      */       }
/*      */       else {
/*      */         
/*  982 */         newKArray.add(base);
/*      */       } 
/*      */     } 
/*  985 */     if (newKArray.size() > 0) {
/*      */       
/*  987 */       COSDictionary kDictLevel0 = new COSDictionary();
/*  988 */       updateParentEntry(newKArray, kDictLevel0);
/*  989 */       kDictLevel0.setItem(COSName.K, (COSBase)newKArray);
/*  990 */       kDictLevel0.setItem(COSName.P, (COSObjectable)destStructTree);
/*  991 */       kDictLevel0.setItem(COSName.S, (COSBase)COSName.DOCUMENT);
/*  992 */       destStructTree.setK((COSBase)kDictLevel0);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void mergeIDTree(PDFCloneUtility cloner, PDStructureTreeRoot srcStructTree, PDStructureTreeRoot destStructTree) throws IOException {
/* 1000 */     PDNameTreeNode<PDStructureElement> srcIDTree = srcStructTree.getIDTree();
/* 1001 */     PDNameTreeNode<PDStructureElement> destIDTree = destStructTree.getIDTree();
/* 1002 */     if (srcIDTree == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1006 */     if (destIDTree == null)
/*      */     {
/* 1008 */       pDStructureElementNameTreeNode = new PDStructureElementNameTreeNode();
/*      */     }
/* 1010 */     Map<String, PDStructureElement> srcNames = getIDTreeAsMap(srcIDTree);
/* 1011 */     Map<String, PDStructureElement> destNames = getIDTreeAsMap((PDNameTreeNode<PDStructureElement>)pDStructureElementNameTreeNode);
/* 1012 */     for (Map.Entry<String, PDStructureElement> entry : srcNames.entrySet()) {
/*      */       
/* 1014 */       if (destNames.containsKey(entry.getKey())) {
/*      */         
/* 1016 */         LOG.warn("key " + (String)entry.getKey() + " already exists in destination IDTree");
/*      */         
/*      */         continue;
/*      */       } 
/* 1020 */       destNames.put(entry.getKey(), new PDStructureElement((COSDictionary)cloner
/* 1021 */             .cloneForNewDocument(((PDStructureElement)entry.getValue()).getCOSObject())));
/*      */     } 
/*      */     
/* 1024 */     PDStructureElementNameTreeNode pDStructureElementNameTreeNode = new PDStructureElementNameTreeNode();
/* 1025 */     pDStructureElementNameTreeNode.setNames(destNames);
/* 1026 */     destStructTree.setIDTree((PDNameTreeNode)pDStructureElementNameTreeNode);
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
/*      */   static Map<String, PDStructureElement> getIDTreeAsMap(PDNameTreeNode<PDStructureElement> idTree) throws IOException {
/* 1038 */     Map<String, PDStructureElement> names = idTree.getNames();
/* 1039 */     if (names == null) {
/*      */       
/* 1041 */       names = new LinkedHashMap<String, PDStructureElement>();
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1046 */       names = new LinkedHashMap<String, PDStructureElement>(names);
/*      */     } 
/* 1048 */     List<PDNameTreeNode<PDStructureElement>> kids = idTree.getKids();
/* 1049 */     if (kids != null)
/*      */     {
/* 1051 */       for (PDNameTreeNode<PDStructureElement> kid : kids)
/*      */       {
/* 1053 */         names.putAll(getIDTreeAsMap(kid));
/*      */       }
/*      */     }
/* 1056 */     return names;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static Map<Integer, COSObjectable> getNumberTreeAsMap(PDNumberTreeNode tree) throws IOException {
/* 1064 */     Map<Integer, COSObjectable> numbers = tree.getNumbers();
/* 1065 */     if (numbers == null) {
/*      */       
/* 1067 */       numbers = new LinkedHashMap<Integer, COSObjectable>();
/*      */     
/*      */     }
/*      */     else {
/*      */       
/* 1072 */       numbers = new LinkedHashMap<Integer, COSObjectable>(numbers);
/*      */     } 
/* 1074 */     List<PDNumberTreeNode> kids = tree.getKids();
/* 1075 */     if (kids != null)
/*      */     {
/* 1077 */       for (PDNumberTreeNode kid : kids)
/*      */       {
/* 1079 */         numbers.putAll(getNumberTreeAsMap(kid));
/*      */       }
/*      */     }
/* 1082 */     return numbers;
/*      */   }
/*      */ 
/*      */   
/*      */   private void mergeRoleMap(PDStructureTreeRoot srcStructTree, PDStructureTreeRoot destStructTree) {
/* 1087 */     COSDictionary srcDict = srcStructTree.getCOSObject().getCOSDictionary(COSName.ROLE_MAP);
/* 1088 */     COSDictionary destDict = destStructTree.getCOSObject().getCOSDictionary(COSName.ROLE_MAP);
/* 1089 */     if (srcDict == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1093 */     if (destDict == null) {
/*      */       
/* 1095 */       destStructTree.getCOSObject().setItem(COSName.ROLE_MAP, (COSBase)srcDict);
/*      */       return;
/*      */     } 
/* 1098 */     for (Map.Entry<COSName, COSBase> entry : (Iterable<Map.Entry<COSName, COSBase>>)srcDict.entrySet()) {
/*      */       
/* 1100 */       COSBase destValue = destDict.getDictionaryObject(entry.getKey());
/* 1101 */       if (destValue != null && destValue.equals(entry.getValue())) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */       
/* 1106 */       if (destDict.containsKey(entry.getKey())) {
/*      */         
/* 1108 */         LOG.warn("key " + entry.getKey() + " already exists in destination RoleMap");
/*      */         
/*      */         continue;
/*      */       } 
/* 1112 */       destDict.setItem(entry.getKey(), entry.getValue());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void mergeOutputIntents(PDFCloneUtility cloner, PDDocumentCatalog srcCatalog, PDDocumentCatalog destCatalog) throws IOException {
/* 1122 */     List<PDOutputIntent> srcOutputIntents = srcCatalog.getOutputIntents();
/* 1123 */     List<PDOutputIntent> dstOutputIntents = destCatalog.getOutputIntents();
/* 1124 */     for (PDOutputIntent srcOI : srcOutputIntents) {
/*      */       
/* 1126 */       String srcOCI = srcOI.getOutputConditionIdentifier();
/* 1127 */       if (srcOCI != null && !"Custom".equals(srcOCI)) {
/*      */ 
/*      */         
/* 1130 */         boolean skip = false;
/* 1131 */         for (PDOutputIntent dstOI : dstOutputIntents) {
/*      */           
/* 1133 */           if (dstOI.getOutputConditionIdentifier().equals(srcOCI)) {
/*      */             
/* 1135 */             skip = true;
/*      */             break;
/*      */           } 
/*      */         } 
/* 1139 */         if (skip) {
/*      */           continue;
/*      */         }
/*      */       } 
/*      */       
/* 1144 */       destCatalog.addOutputIntent(new PDOutputIntent((COSDictionary)cloner.cloneForNewDocument(srcOI)));
/* 1145 */       dstOutputIntents.add(srcOI);
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
/*      */   private void mergeAcroForm(PDFCloneUtility cloner, PDDocumentCatalog destCatalog, PDDocumentCatalog srcCatalog) throws IOException {
/*      */     try {
/* 1163 */       PDAcroForm destAcroForm = destCatalog.getAcroForm();
/* 1164 */       PDAcroForm srcAcroForm = srcCatalog.getAcroForm();
/*      */       
/* 1166 */       if (destAcroForm == null && srcAcroForm != null)
/*      */       {
/* 1168 */         destCatalog.getCOSObject().setItem(COSName.ACRO_FORM, cloner
/* 1169 */             .cloneForNewDocument(srcAcroForm.getCOSObject()));
/*      */ 
/*      */ 
/*      */       
/*      */       }
/* 1174 */       else if (srcAcroForm != null)
/*      */       {
/* 1176 */         if (this.acroFormMergeMode == AcroFormMergeMode.PDFBOX_LEGACY_MODE)
/*      */         {
/* 1178 */           acroFormLegacyMode(cloner, destAcroForm, srcAcroForm);
/*      */         }
/* 1180 */         else if (this.acroFormMergeMode == AcroFormMergeMode.JOIN_FORM_FIELDS_MODE)
/*      */         {
/* 1182 */           acroFormJoinFieldsMode(cloner, destAcroForm, srcAcroForm);
/*      */         }
/*      */       
/*      */       }
/*      */     
/* 1187 */     } catch (IOException e) {
/*      */ 
/*      */       
/* 1190 */       if (!this.ignoreAcroFormErrors)
/*      */       {
/* 1192 */         throw new IOException(e);
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
/*      */   private void acroFormJoinFieldsMode(PDFCloneUtility cloner, PDAcroForm destAcroForm, PDAcroForm srcAcroForm) throws IOException {
/* 1209 */     List<PDField> srcFields = srcAcroForm.getFields();
/*      */ 
/*      */     
/* 1212 */     if (srcFields != null && !srcFields.isEmpty()) {
/*      */       COSArray destFields;
/*      */ 
/*      */       
/* 1216 */       COSBase base = destAcroForm.getCOSObject().getItem(COSName.FIELDS);
/* 1217 */       if (base instanceof COSArray) {
/*      */         
/* 1219 */         destFields = (COSArray)base;
/*      */       }
/*      */       else {
/*      */         
/* 1223 */         destFields = new COSArray();
/*      */       } 
/*      */       
/* 1226 */       for (PDField srcField : srcAcroForm.getFieldTree()) {
/*      */ 
/*      */ 
/*      */         
/* 1230 */         PDField destinationField = destAcroForm.getField(srcField.getFullyQualifiedName());
/* 1231 */         if (destinationField == null) {
/*      */ 
/*      */           
/* 1234 */           COSDictionary importedField = (COSDictionary)cloner.cloneForNewDocument(srcField.getCOSObject());
/* 1235 */           destFields.add((COSBase)importedField);
/*      */           
/*      */           continue;
/*      */         } 
/* 1239 */         mergeFields(cloner, destinationField, srcField);
/*      */       } 
/*      */       
/* 1242 */       destAcroForm.getCOSObject().setItem(COSName.FIELDS, (COSBase)destFields);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void mergeFields(PDFCloneUtility cloner, PDField destField, PDField srcField) {
/* 1248 */     if (destField instanceof org.apache.pdfbox.pdmodel.interactive.form.PDNonTerminalField && srcField instanceof org.apache.pdfbox.pdmodel.interactive.form.PDNonTerminalField) {
/*      */       
/* 1250 */       LOG.info("Skipping non terminal field " + srcField.getFullyQualifiedName());
/*      */       
/*      */       return;
/*      */     } 
/* 1254 */     if (destField.getFieldType() == "Tx" && destField.getFieldType() == "Tx") {
/*      */ 
/*      */       
/* 1257 */       if (destField.getCOSObject().containsKey(COSName.KIDS)) {
/*      */         
/* 1259 */         COSArray widgets = destField.getCOSObject().getCOSArray(COSName.KIDS);
/* 1260 */         for (PDAnnotationWidget srcWidget : srcField.getWidgets())
/*      */         {
/*      */           try
/*      */           {
/* 1264 */             widgets.add(cloner.cloneForNewDocument(srcWidget.getCOSObject()));
/*      */           }
/* 1266 */           catch (IOException ioe)
/*      */           {
/* 1268 */             LOG.warn("Unable to clone widget for source field " + srcField.getFullyQualifiedName());
/*      */           }
/*      */         
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1275 */         COSArray widgets = new COSArray();
/*      */         
/*      */         try {
/* 1278 */           COSDictionary widgetAsCOS = (COSDictionary)cloner.cloneForNewDocument(destField.getWidgets().get(0));
/* 1279 */           cleanupWidgetCOSDictionary(widgetAsCOS, true);
/* 1280 */           widgetAsCOS.setItem(COSName.PARENT, (COSObjectable)destField);
/* 1281 */           widgets.add((COSBase)widgetAsCOS);
/* 1282 */           for (PDAnnotationWidget srcWidget : srcField.getWidgets()) {
/*      */ 
/*      */             
/*      */             try {
/* 1286 */               widgetAsCOS = (COSDictionary)cloner.cloneForNewDocument(srcWidget.getCOSObject());
/* 1287 */               cleanupWidgetCOSDictionary(widgetAsCOS, false);
/* 1288 */               widgetAsCOS.setItem(COSName.PARENT, (COSObjectable)destField);
/* 1289 */               widgets.add((COSBase)widgetAsCOS);
/*      */             }
/* 1291 */             catch (IOException ioe) {
/*      */               
/* 1293 */               LOG.warn("Unable to clone widget for source field " + srcField.getFullyQualifiedName());
/*      */             } 
/*      */           } 
/*      */           
/* 1297 */           destField.getCOSObject().setItem(COSName.KIDS, (COSBase)widgets);
/* 1298 */           cleanupFieldCOSDictionary(destField.getCOSObject());
/*      */         }
/* 1300 */         catch (IOException ioe) {
/*      */           
/* 1302 */           LOG.warn("Unable to clone widget for destination field " + destField.getFullyQualifiedName());
/*      */         }
/*      */       
/*      */       } 
/*      */     } else {
/*      */       
/* 1308 */       LOG.info("Only merging two text fields is currently supported");
/* 1309 */       LOG.info("Skipping merging of " + srcField.getFullyQualifiedName() + " into " + destField.getFullyQualifiedName());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void cleanupFieldCOSDictionary(COSDictionary fieldCos) {
/* 1318 */     fieldCos.removeItem(COSName.F);
/* 1319 */     fieldCos.removeItem(COSName.MK);
/* 1320 */     fieldCos.removeItem(COSName.P);
/* 1321 */     fieldCos.removeItem(COSName.RECT);
/* 1322 */     fieldCos.removeItem(COSName.SUBTYPE);
/* 1323 */     fieldCos.removeItem(COSName.TYPE);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void cleanupWidgetCOSDictionary(COSDictionary widgetCos, boolean removeDAEntry) {
/* 1332 */     if (removeDAEntry)
/*      */     {
/* 1334 */       widgetCos.removeItem(COSName.DA);
/*      */     }
/* 1336 */     widgetCos.removeItem(COSName.FT);
/* 1337 */     widgetCos.removeItem(COSName.T);
/* 1338 */     widgetCos.removeItem(COSName.V);
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
/*      */   private void acroFormLegacyMode(PDFCloneUtility cloner, PDAcroForm destAcroForm, PDAcroForm srcAcroForm) throws IOException {
/* 1353 */     List<PDField> srcFields = srcAcroForm.getFields();
/*      */ 
/*      */     
/* 1356 */     if (srcFields != null && !srcFields.isEmpty()) {
/*      */       COSArray destFields;
/*      */ 
/*      */ 
/*      */       
/* 1361 */       String prefix = "dummyFieldName";
/* 1362 */       int prefixLength = "dummyFieldName".length();
/*      */       
/* 1364 */       for (PDField destField : destAcroForm.getFieldTree()) {
/*      */         
/* 1366 */         String fieldName = destField.getPartialName();
/* 1367 */         if (fieldName.startsWith("dummyFieldName"))
/*      */         {
/* 1369 */           this.nextFieldNum = Math.max(this.nextFieldNum, Integer.parseInt(fieldName.substring(prefixLength, fieldName.length())) + 1);
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1375 */       COSBase base = destAcroForm.getCOSObject().getItem(COSName.FIELDS);
/* 1376 */       if (base instanceof COSArray) {
/*      */         
/* 1378 */         destFields = (COSArray)base;
/*      */       }
/*      */       else {
/*      */         
/* 1382 */         destFields = new COSArray();
/*      */       } 
/*      */       
/* 1385 */       for (PDField srcField : srcAcroForm.getFields()) {
/*      */         
/* 1387 */         COSDictionary dstField = (COSDictionary)cloner.cloneForNewDocument(srcField.getCOSObject());
/*      */ 
/*      */         
/* 1390 */         if (destAcroForm.getField(srcField.getFullyQualifiedName()) != null)
/*      */         {
/* 1392 */           dstField.setString(COSName.T, "dummyFieldName" + this.nextFieldNum++);
/*      */         }
/* 1394 */         destFields.add((COSBase)dstField);
/*      */       } 
/* 1396 */       destAcroForm.getCOSObject().setItem(COSName.FIELDS, (COSBase)destFields);
/*      */     } 
/*      */   }
/*      */   public PDFMergerUtility() {
/* 1400 */     this.nextFieldNum = 1;
/*      */     this.sources = new ArrayList();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isIgnoreAcroFormErrors() {
/* 1409 */     return this.ignoreAcroFormErrors;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIgnoreAcroFormErrors(boolean ignoreAcroFormErrorsValue) {
/* 1420 */     this.ignoreAcroFormErrors = ignoreAcroFormErrorsValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updatePageReferences(PDFCloneUtility cloner, Map<Integer, COSObjectable> numberTreeAsMap, Map<COSDictionary, COSDictionary> objMapping) throws IOException {
/* 1430 */     for (COSObjectable obj : numberTreeAsMap.values()) {
/*      */       
/* 1432 */       if (obj == null) {
/*      */         continue;
/*      */       }
/*      */       
/* 1436 */       PDParentTreeValue val = (PDParentTreeValue)obj;
/* 1437 */       COSBase base = val.getCOSObject();
/* 1438 */       if (base instanceof COSArray) {
/*      */         
/* 1440 */         updatePageReferences(cloner, (COSArray)base, objMapping);
/*      */         
/*      */         continue;
/*      */       } 
/* 1444 */       updatePageReferences(cloner, (COSDictionary)base, objMapping);
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
/*      */   private void updatePageReferences(PDFCloneUtility cloner, COSDictionary parentTreeEntry, Map<COSDictionary, COSDictionary> objMapping) throws IOException {
/* 1459 */     COSDictionary pageDict = parentTreeEntry.getCOSDictionary(COSName.PG);
/* 1460 */     if (objMapping.containsKey(pageDict))
/*      */     {
/* 1462 */       parentTreeEntry.setItem(COSName.PG, (COSBase)objMapping.get(pageDict));
/*      */     }
/* 1464 */     COSBase obj = parentTreeEntry.getDictionaryObject(COSName.OBJ);
/* 1465 */     if (obj instanceof COSDictionary) {
/*      */       
/* 1467 */       COSDictionary objDict = (COSDictionary)obj;
/* 1468 */       if (objMapping.containsKey(objDict)) {
/*      */         
/* 1470 */         parentTreeEntry.setItem(COSName.OBJ, (COSBase)objMapping.get(objDict));
/*      */       
/*      */       }
/*      */       else {
/*      */ 
/*      */         
/* 1476 */         COSBase item = parentTreeEntry.getItem(COSName.OBJ);
/* 1477 */         if (item instanceof org.apache.pdfbox.cos.COSObject) {
/*      */           
/* 1479 */           LOG.debug("clone potential orphan object in structure tree: " + item + ", Type: " + objDict
/* 1480 */               .getNameAsString(COSName.TYPE) + ", Subtype: " + objDict
/* 1481 */               .getNameAsString(COSName.SUBTYPE) + ", T: " + objDict
/* 1482 */               .getNameAsString(COSName.T));
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1487 */           LOG.debug("clone potential orphan object in structure tree, Type: " + objDict
/* 1488 */               .getNameAsString(COSName.TYPE) + ", Subtype: " + objDict
/* 1489 */               .getNameAsString(COSName.SUBTYPE) + ", T: " + objDict
/* 1490 */               .getNameAsString(COSName.T));
/*      */         } 
/* 1492 */         parentTreeEntry.setItem(COSName.OBJ, cloner.cloneForNewDocument(obj));
/*      */       } 
/*      */     } 
/* 1495 */     COSBase kSubEntry = parentTreeEntry.getDictionaryObject(COSName.K);
/* 1496 */     if (kSubEntry instanceof COSArray) {
/*      */       
/* 1498 */       updatePageReferences(cloner, (COSArray)kSubEntry, objMapping);
/*      */     }
/* 1500 */     else if (kSubEntry instanceof COSDictionary) {
/*      */       
/* 1502 */       updatePageReferences(cloner, (COSDictionary)kSubEntry, objMapping);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updatePageReferences(PDFCloneUtility cloner, COSArray parentTreeEntry, Map<COSDictionary, COSDictionary> objMapping) throws IOException {
/* 1510 */     for (int i = 0; i < parentTreeEntry.size(); i++) {
/*      */       
/* 1512 */       COSBase subEntry = parentTreeEntry.getObject(i);
/* 1513 */       if (subEntry instanceof COSArray) {
/*      */         
/* 1515 */         updatePageReferences(cloner, (COSArray)subEntry, objMapping);
/*      */       }
/* 1517 */       else if (subEntry instanceof COSDictionary) {
/*      */         
/* 1519 */         updatePageReferences(cloner, (COSDictionary)subEntry, objMapping);
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
/*      */   private void updateParentEntry(COSArray kArray, COSDictionary newParent) {
/* 1532 */     for (int i = 0; i < kArray.size(); i++) {
/*      */       
/* 1534 */       COSBase subEntry = kArray.getObject(i);
/* 1535 */       if (subEntry instanceof COSDictionary) {
/*      */         
/* 1537 */         COSDictionary dictEntry = (COSDictionary)subEntry;
/* 1538 */         if (dictEntry.getDictionaryObject(COSName.P) != null)
/*      */         {
/* 1540 */           dictEntry.setItem(COSName.P, (COSBase)newParent);
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
/*      */ 
/*      */   
/*      */   private void updateStructParentEntries(PDPage page, int structParentOffset) throws IOException {
/* 1554 */     if (page.getStructParents() >= 0)
/*      */     {
/* 1556 */       page.setStructParents(page.getStructParents() + structParentOffset);
/*      */     }
/* 1558 */     List<PDAnnotation> annots = page.getAnnotations();
/* 1559 */     List<PDAnnotation> newannots = new ArrayList<PDAnnotation>();
/* 1560 */     for (PDAnnotation annot : annots) {
/*      */       
/* 1562 */       if (annot.getStructParent() >= 0)
/*      */       {
/* 1564 */         annot.setStructParent(annot.getStructParent() + structParentOffset);
/*      */       }
/* 1566 */       newannots.add(annot);
/*      */     } 
/* 1568 */     page.setAnnotations(newannots);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isDynamicXfa(PDAcroForm acroForm) {
/* 1579 */     return (acroForm != null && acroForm.xfaIsDynamic());
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
/*      */   private void mergeInto(COSDictionary src, COSDictionary dst, Set<COSName> exclude) {
/* 1594 */     for (Map.Entry<COSName, COSBase> entry : (Iterable<Map.Entry<COSName, COSBase>>)src.entrySet()) {
/*      */       
/* 1596 */       if (!exclude.contains(entry.getKey()) && !dst.containsKey(entry.getKey()))
/*      */       {
/* 1598 */         dst.setItem(entry.getKey(), entry.getValue());
/*      */       }
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/multipdf/PDFMergerUtility.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */