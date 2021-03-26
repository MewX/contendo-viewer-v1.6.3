/*     */ package org.apache.pdfbox.pdmodel;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDDestinationOrAction;
/*     */ import org.apache.pdfbox.pdmodel.common.PDMetadata;
/*     */ import org.apache.pdfbox.pdmodel.common.PDPageLabels;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDMarkInfo;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureTreeRoot;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDOutputIntent;
/*     */ import org.apache.pdfbox.pdmodel.graphics.optionalcontent.PDOptionalContentProperties;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDActionFactory;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDDocumentCatalogAdditionalActions;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDURIDictionary;
/*     */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
/*     */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDNamedDestination;
/*     */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
/*     */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
/*     */ import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
/*     */ import org.apache.pdfbox.pdmodel.interactive.pagenavigation.PDThread;
/*     */ import org.apache.pdfbox.pdmodel.interactive.viewerpreferences.PDViewerPreferences;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDDocumentCatalog
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary root;
/*     */   private final PDDocument document;
/*     */   private PDAcroForm cachedAcroForm;
/*     */   
/*     */   public PDDocumentCatalog(PDDocument doc) {
/*  68 */     this.document = doc;
/*  69 */     this.root = new COSDictionary();
/*  70 */     this.root.setItem(COSName.TYPE, (COSBase)COSName.CATALOG);
/*  71 */     this.document.getDocument().getTrailer().setItem(COSName.ROOT, (COSBase)this.root);
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
/*     */   public PDDocumentCatalog(PDDocument doc, COSDictionary rootDictionary) {
/*  83 */     this.document = doc;
/*  84 */     this.root = rootDictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/*  95 */     return this.root;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAcroForm getAcroForm() {
/* 105 */     if (this.cachedAcroForm == null) {
/*     */       
/* 107 */       COSDictionary dict = (COSDictionary)this.root.getDictionaryObject(COSName.ACRO_FORM);
/* 108 */       this.cachedAcroForm = (dict == null) ? null : new PDAcroForm(this.document, dict);
/*     */     } 
/* 110 */     return this.cachedAcroForm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAcroForm(PDAcroForm acroForm) {
/* 120 */     this.root.setItem(COSName.ACRO_FORM, (COSObjectable)acroForm);
/* 121 */     this.cachedAcroForm = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPageTree getPages() {
/* 132 */     return new PDPageTree((COSDictionary)this.root.getDictionaryObject(COSName.PAGES), this.document);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDViewerPreferences getViewerPreferences() {
/* 142 */     COSBase base = this.root.getDictionaryObject(COSName.VIEWER_PREFERENCES);
/* 143 */     return (base instanceof COSDictionary) ? new PDViewerPreferences((COSDictionary)base) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setViewerPreferences(PDViewerPreferences prefs) {
/* 153 */     this.root.setItem(COSName.VIEWER_PREFERENCES, (COSObjectable)prefs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDocumentOutline getDocumentOutline() {
/* 163 */     COSBase cosObj = this.root.getDictionaryObject(COSName.OUTLINES);
/* 164 */     return (cosObj instanceof COSDictionary) ? new PDDocumentOutline((COSDictionary)cosObj) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDocumentOutline(PDDocumentOutline outlines) {
/* 174 */     this.root.setItem(COSName.OUTLINES, (COSObjectable)outlines);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PDThread> getThreads() {
/* 184 */     COSArray array = (COSArray)this.root.getDictionaryObject(COSName.THREADS);
/* 185 */     if (array == null) {
/*     */       
/* 187 */       array = new COSArray();
/* 188 */       this.root.setItem(COSName.THREADS, (COSBase)array);
/*     */     } 
/* 190 */     List<PDThread> pdObjects = new ArrayList<PDThread>();
/* 191 */     for (int i = 0; i < array.size(); i++)
/*     */     {
/* 193 */       pdObjects.add(new PDThread((COSDictionary)array.getObject(i)));
/*     */     }
/* 195 */     return (List<PDThread>)new COSArrayList(pdObjects, array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThreads(List<PDThread> threads) {
/* 205 */     this.root.setItem(COSName.THREADS, (COSBase)COSArrayList.converterToCOSArray(threads));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDMetadata getMetadata() {
/* 216 */     COSBase metaObj = this.root.getDictionaryObject(COSName.METADATA);
/* 217 */     if (metaObj instanceof COSStream)
/*     */     {
/* 219 */       return new PDMetadata((COSStream)metaObj);
/*     */     }
/* 221 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMetadata(PDMetadata meta) {
/* 231 */     this.root.setItem(COSName.METADATA, (COSObjectable)meta);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpenAction(PDDestinationOrAction action) {
/* 241 */     this.root.setItem(COSName.OPEN_ACTION, (COSObjectable)action);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDestinationOrAction getOpenAction() throws IOException {
/* 252 */     COSBase openAction = this.root.getDictionaryObject(COSName.OPEN_ACTION);
/* 253 */     if (openAction instanceof COSDictionary)
/*     */     {
/* 255 */       return (PDDestinationOrAction)PDActionFactory.createAction((COSDictionary)openAction);
/*     */     }
/* 257 */     if (openAction instanceof COSArray)
/*     */     {
/* 259 */       return (PDDestinationOrAction)PDDestination.create(openAction);
/*     */     }
/*     */ 
/*     */     
/* 263 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDocumentCatalogAdditionalActions getActions() {
/* 271 */     COSDictionary addAction = (COSDictionary)this.root.getDictionaryObject(COSName.AA);
/* 272 */     if (addAction == null) {
/*     */       
/* 274 */       addAction = new COSDictionary();
/* 275 */       this.root.setItem(COSName.AA, (COSBase)addAction);
/*     */     } 
/* 277 */     return new PDDocumentCatalogAdditionalActions(addAction);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActions(PDDocumentCatalogAdditionalActions actions) {
/* 287 */     this.root.setItem(COSName.AA, (COSObjectable)actions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDocumentNameDictionary getNames() {
/* 295 */     COSDictionary names = (COSDictionary)this.root.getDictionaryObject(COSName.NAMES);
/* 296 */     return (names == null) ? null : new PDDocumentNameDictionary(this, names);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDDocumentNameDestinationDictionary getDests() {
/* 304 */     PDDocumentNameDestinationDictionary nameDic = null;
/* 305 */     COSDictionary dests = (COSDictionary)this.root.getDictionaryObject(COSName.DESTS);
/* 306 */     if (dests != null)
/*     */     {
/* 308 */       nameDic = new PDDocumentNameDestinationDictionary(dests);
/*     */     }
/* 310 */     return nameDic;
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
/*     */   public PDPageDestination findNamedDestinationPage(PDNamedDestination namedDest) throws IOException {
/* 322 */     PDPageDestination pageDestination = null;
/* 323 */     PDDocumentNameDictionary namesDict = getNames();
/* 324 */     if (namesDict != null) {
/*     */       
/* 326 */       PDDestinationNameTreeNode destsTree = namesDict.getDests();
/* 327 */       if (destsTree != null)
/*     */       {
/* 329 */         pageDestination = (PDPageDestination)destsTree.getValue(namedDest.getNamedDestination());
/*     */       }
/*     */     } 
/* 332 */     if (pageDestination == null) {
/*     */ 
/*     */       
/* 335 */       PDDocumentNameDestinationDictionary nameDestDict = getDests();
/* 336 */       if (nameDestDict != null) {
/*     */         
/* 338 */         String name = namedDest.getNamedDestination();
/* 339 */         pageDestination = (PDPageDestination)nameDestDict.getDestination(name);
/*     */       } 
/*     */     } 
/* 342 */     return pageDestination;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNames(PDDocumentNameDictionary names) {
/* 352 */     this.root.setItem(COSName.NAMES, names);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDMarkInfo getMarkInfo() {
/* 363 */     COSDictionary dic = (COSDictionary)this.root.getDictionaryObject(COSName.MARK_INFO);
/* 364 */     return (dic == null) ? null : new PDMarkInfo(dic);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMarkInfo(PDMarkInfo markInfo) {
/* 374 */     this.root.setItem(COSName.MARK_INFO, (COSObjectable)markInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PDOutputIntent> getOutputIntents() {
/* 384 */     List<PDOutputIntent> retval = new ArrayList<PDOutputIntent>();
/* 385 */     COSArray array = (COSArray)this.root.getDictionaryObject(COSName.OUTPUT_INTENTS);
/* 386 */     if (array != null)
/*     */     {
/* 388 */       for (COSBase cosBase : array) {
/*     */         
/* 390 */         if (cosBase instanceof COSObject)
/*     */         {
/* 392 */           cosBase = ((COSObject)cosBase).getObject();
/*     */         }
/* 394 */         PDOutputIntent oi = new PDOutputIntent((COSDictionary)cosBase);
/* 395 */         retval.add(oi);
/*     */       } 
/*     */     }
/* 398 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addOutputIntent(PDOutputIntent outputIntent) {
/* 409 */     COSArray array = (COSArray)this.root.getDictionaryObject(COSName.OUTPUT_INTENTS);
/* 410 */     if (array == null) {
/*     */       
/* 412 */       array = new COSArray();
/* 413 */       this.root.setItem(COSName.OUTPUT_INTENTS, (COSBase)array);
/*     */     } 
/* 415 */     array.add(outputIntent.getCOSObject());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOutputIntents(List<PDOutputIntent> outputIntents) {
/* 426 */     COSArray array = new COSArray();
/* 427 */     for (PDOutputIntent intent : outputIntents)
/*     */     {
/* 429 */       array.add(intent.getCOSObject());
/*     */     }
/* 431 */     this.root.setItem(COSName.OUTPUT_INTENTS, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PageMode getPageMode() {
/* 441 */     String mode = this.root.getNameAsString(COSName.PAGE_MODE);
/* 442 */     if (mode != null) {
/*     */       
/*     */       try {
/*     */         
/* 446 */         return PageMode.fromString(mode);
/*     */       }
/* 448 */       catch (IllegalArgumentException e) {
/*     */         
/* 450 */         return PageMode.USE_NONE;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 455 */     return PageMode.USE_NONE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPageMode(PageMode mode) {
/* 466 */     this.root.setName(COSName.PAGE_MODE, mode.stringValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PageLayout getPageLayout() {
/* 476 */     String mode = this.root.getNameAsString(COSName.PAGE_LAYOUT);
/* 477 */     if (mode != null)
/*     */     {
/* 479 */       return PageLayout.fromString(mode);
/*     */     }
/*     */ 
/*     */     
/* 483 */     return PageLayout.SINGLE_PAGE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPageLayout(PageLayout layout) {
/* 494 */     this.root.setName(COSName.PAGE_LAYOUT, layout.stringValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDURIDictionary getURI() {
/* 504 */     COSDictionary uri = (COSDictionary)this.root.getDictionaryObject(COSName.URI);
/* 505 */     return (uri == null) ? null : new PDURIDictionary(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setURI(PDURIDictionary uri) {
/* 515 */     this.root.setItem(COSName.URI, (COSObjectable)uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStructureTreeRoot getStructureTreeRoot() {
/* 525 */     COSDictionary dict = this.root.getCOSDictionary(COSName.STRUCT_TREE_ROOT);
/* 526 */     return (dict == null) ? null : new PDStructureTreeRoot(dict);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStructureTreeRoot(PDStructureTreeRoot treeRoot) {
/* 536 */     this.root.setItem(COSName.STRUCT_TREE_ROOT, (COSObjectable)treeRoot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLanguage() {
/* 546 */     return this.root.getString(COSName.LANG);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLanguage(String language) {
/* 556 */     this.root.setString(COSName.LANG, language);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVersion() {
/* 566 */     return this.root.getNameAsString(COSName.VERSION);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(String version) {
/* 576 */     this.root.setName(COSName.VERSION, version);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPageLabels getPageLabels() throws IOException {
/* 587 */     COSDictionary dict = (COSDictionary)this.root.getDictionaryObject(COSName.PAGE_LABELS);
/* 588 */     return (dict == null) ? null : new PDPageLabels(this.document, dict);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPageLabels(PDPageLabels labels) {
/* 598 */     this.root.setItem(COSName.PAGE_LABELS, (COSObjectable)labels);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDOptionalContentProperties getOCProperties() {
/* 608 */     COSDictionary dict = (COSDictionary)this.root.getDictionaryObject(COSName.OCPROPERTIES);
/* 609 */     return (dict == null) ? null : new PDOptionalContentProperties(dict);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOCProperties(PDOptionalContentProperties ocProperties) {
/* 620 */     this.root.setItem(COSName.OCPROPERTIES, (COSObjectable)ocProperties);
/*     */ 
/*     */     
/* 623 */     if (ocProperties != null && this.document.getVersion() < 1.5D)
/*     */     {
/* 625 */       this.document.setVersion(1.5F);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDDocumentCatalog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */