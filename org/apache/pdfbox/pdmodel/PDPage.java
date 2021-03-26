/*     */ package org.apache.pdfbox.pdmodel;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.SequenceInputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.contentstream.PDContentStream;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDMetadata;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDPageAdditionalActions;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.AnnotationFilter;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotation;
/*     */ import org.apache.pdfbox.pdmodel.interactive.measurement.PDViewportDictionary;
/*     */ import org.apache.pdfbox.pdmodel.interactive.pagenavigation.PDThreadBead;
/*     */ import org.apache.pdfbox.pdmodel.interactive.pagenavigation.PDTransition;
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
/*     */ 
/*     */ 
/*     */ public class PDPage
/*     */   implements PDContentStream, COSObjectable
/*     */ {
/*  60 */   private static final Log LOG = LogFactory.getLog(PDPage.class);
/*     */   
/*     */   private final COSDictionary page;
/*     */   
/*     */   private PDResources pageResources;
/*     */   
/*     */   private ResourceCache resourceCache;
/*     */   
/*     */   private PDRectangle mediaBox;
/*     */ 
/*     */   
/*     */   public PDPage() {
/*  72 */     this(PDRectangle.LETTER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPage(PDRectangle mediaBox) {
/*  82 */     this.page = new COSDictionary();
/*  83 */     this.page.setItem(COSName.TYPE, (COSBase)COSName.PAGE);
/*  84 */     this.page.setItem(COSName.MEDIA_BOX, (COSObjectable)mediaBox);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPage(COSDictionary pageDictionary) {
/*  94 */     this.page = pageDictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   PDPage(COSDictionary pageDictionary, ResourceCache resourceCache) {
/* 104 */     this.page = pageDictionary;
/* 105 */     this.resourceCache = resourceCache;
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
/* 116 */     return this.page;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator<PDStream> getContentStreams() {
/* 126 */     List<PDStream> streams = new ArrayList<PDStream>();
/* 127 */     COSBase base = this.page.getDictionaryObject(COSName.CONTENTS);
/* 128 */     if (base instanceof COSStream) {
/*     */       
/* 130 */       streams.add(new PDStream((COSStream)base));
/*     */     }
/* 132 */     else if (base instanceof COSArray && ((COSArray)base).size() > 0) {
/*     */       
/* 134 */       COSArray array = (COSArray)base;
/* 135 */       for (int i = 0; i < array.size(); i++) {
/*     */         
/* 137 */         COSStream stream = (COSStream)array.getObject(i);
/* 138 */         streams.add(new PDStream(stream));
/*     */       } 
/*     */     } 
/* 141 */     return streams.iterator();
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
/*     */   public InputStream getContents() throws IOException {
/* 154 */     COSBase base = this.page.getDictionaryObject(COSName.CONTENTS);
/* 155 */     if (base instanceof COSStream)
/*     */     {
/* 157 */       return (InputStream)((COSStream)base).createInputStream();
/*     */     }
/* 159 */     if (base instanceof COSArray && ((COSArray)base).size() > 0) {
/*     */       
/* 161 */       COSArray streams = (COSArray)base;
/* 162 */       byte[] delimiter = { 10 };
/* 163 */       List<InputStream> inputStreams = new ArrayList<InputStream>();
/* 164 */       for (int i = 0; i < streams.size(); i++) {
/*     */         
/* 166 */         COSBase strm = streams.getObject(i);
/* 167 */         if (strm instanceof COSStream) {
/*     */           
/* 169 */           COSStream stream = (COSStream)strm;
/* 170 */           inputStreams.add(stream.createInputStream());
/* 171 */           inputStreams.add(new ByteArrayInputStream(delimiter));
/*     */         } 
/*     */       } 
/* 174 */       return new SequenceInputStream(Collections.enumeration(inputStreams));
/*     */     } 
/* 176 */     return new ByteArrayInputStream(new byte[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasContents() {
/* 184 */     COSBase contents = this.page.getDictionaryObject(COSName.CONTENTS);
/* 185 */     if (contents instanceof COSStream)
/*     */     {
/* 187 */       return (((COSStream)contents).size() > 0);
/*     */     }
/* 189 */     if (contents instanceof COSArray)
/*     */     {
/* 191 */       return (((COSArray)contents).size() > 0);
/*     */     }
/* 193 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDResources getResources() {
/* 202 */     if (this.pageResources == null) {
/*     */       
/* 204 */       COSBase base = PDPageTree.getInheritableAttribute(this.page, COSName.RESOURCES);
/*     */ 
/*     */       
/* 207 */       if (base instanceof COSDictionary)
/*     */       {
/* 209 */         this.pageResources = new PDResources((COSDictionary)base, this.resourceCache);
/*     */       }
/*     */     } 
/* 212 */     return this.pageResources;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setResources(PDResources resources) {
/* 222 */     this.pageResources = resources;
/* 223 */     if (resources != null) {
/*     */       
/* 225 */       this.page.setItem(COSName.RESOURCES, resources);
/*     */     }
/*     */     else {
/*     */       
/* 229 */       this.page.removeItem(COSName.RESOURCES);
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
/*     */   public int getStructParents() {
/* 241 */     return this.page.getInt(COSName.STRUCT_PARENTS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStructParents(int structParents) {
/* 251 */     this.page.setInt(COSName.STRUCT_PARENTS, structParents);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle getBBox() {
/* 257 */     return getCropBox();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix getMatrix() {
/* 264 */     return new Matrix();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle getMediaBox() {
/* 275 */     if (this.mediaBox == null) {
/*     */       
/* 277 */       COSBase base = PDPageTree.getInheritableAttribute(this.page, COSName.MEDIA_BOX);
/* 278 */       if (base instanceof COSArray)
/*     */       {
/* 280 */         this.mediaBox = new PDRectangle((COSArray)base);
/*     */       }
/*     */     } 
/* 283 */     if (this.mediaBox == null) {
/*     */       
/* 285 */       LOG.debug("Can't find MediaBox, will use U.S. Letter");
/* 286 */       this.mediaBox = PDRectangle.LETTER;
/*     */     } 
/* 288 */     return this.mediaBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMediaBox(PDRectangle mediaBox) {
/* 298 */     this.mediaBox = mediaBox;
/* 299 */     if (mediaBox == null) {
/*     */       
/* 301 */       this.page.removeItem(COSName.MEDIA_BOX);
/*     */     }
/*     */     else {
/*     */       
/* 305 */       this.page.setItem(COSName.MEDIA_BOX, (COSObjectable)mediaBox);
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
/*     */   public PDRectangle getCropBox() {
/* 317 */     COSBase base = PDPageTree.getInheritableAttribute(this.page, COSName.CROP_BOX);
/* 318 */     if (base instanceof COSArray)
/*     */     {
/* 320 */       return clipToMediaBox(new PDRectangle((COSArray)base));
/*     */     }
/*     */ 
/*     */     
/* 324 */     return getMediaBox();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCropBox(PDRectangle cropBox) {
/* 335 */     if (cropBox == null) {
/*     */       
/* 337 */       this.page.removeItem(COSName.CROP_BOX);
/*     */     }
/*     */     else {
/*     */       
/* 341 */       this.page.setItem(COSName.CROP_BOX, (COSBase)cropBox.getCOSArray());
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
/*     */   public PDRectangle getBleedBox() {
/* 354 */     COSBase base = this.page.getDictionaryObject(COSName.BLEED_BOX);
/* 355 */     if (base instanceof COSArray)
/*     */     {
/* 357 */       return clipToMediaBox(new PDRectangle((COSArray)base));
/*     */     }
/*     */ 
/*     */     
/* 361 */     return getCropBox();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBleedBox(PDRectangle bleedBox) {
/* 372 */     if (bleedBox == null) {
/*     */       
/* 374 */       this.page.removeItem(COSName.BLEED_BOX);
/*     */     }
/*     */     else {
/*     */       
/* 378 */       this.page.setItem(COSName.BLEED_BOX, (COSObjectable)bleedBox);
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
/*     */   public PDRectangle getTrimBox() {
/* 390 */     COSBase base = this.page.getDictionaryObject(COSName.TRIM_BOX);
/* 391 */     if (base instanceof COSArray)
/*     */     {
/* 393 */       return clipToMediaBox(new PDRectangle((COSArray)base));
/*     */     }
/*     */ 
/*     */     
/* 397 */     return getCropBox();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrimBox(PDRectangle trimBox) {
/* 408 */     if (trimBox == null) {
/*     */       
/* 410 */       this.page.removeItem(COSName.TRIM_BOX);
/*     */     }
/*     */     else {
/*     */       
/* 414 */       this.page.setItem(COSName.TRIM_BOX, (COSObjectable)trimBox);
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
/*     */   public PDRectangle getArtBox() {
/* 427 */     COSBase base = this.page.getDictionaryObject(COSName.ART_BOX);
/* 428 */     if (base instanceof COSArray)
/*     */     {
/* 430 */       return clipToMediaBox(new PDRectangle((COSArray)base));
/*     */     }
/*     */ 
/*     */     
/* 434 */     return getCropBox();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setArtBox(PDRectangle artBox) {
/* 445 */     if (artBox == null) {
/*     */       
/* 447 */       this.page.removeItem(COSName.ART_BOX);
/*     */     }
/*     */     else {
/*     */       
/* 451 */       this.page.setItem(COSName.ART_BOX, (COSObjectable)artBox);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PDRectangle clipToMediaBox(PDRectangle box) {
/* 460 */     PDRectangle mediaBox = getMediaBox();
/* 461 */     PDRectangle result = new PDRectangle();
/* 462 */     result.setLowerLeftX(Math.max(mediaBox.getLowerLeftX(), box.getLowerLeftX()));
/* 463 */     result.setLowerLeftY(Math.max(mediaBox.getLowerLeftY(), box.getLowerLeftY()));
/* 464 */     result.setUpperRightX(Math.min(mediaBox.getUpperRightX(), box.getUpperRightX()));
/* 465 */     result.setUpperRightY(Math.min(mediaBox.getUpperRightY(), box.getUpperRightY()));
/* 466 */     return result;
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
/*     */   public int getRotation() {
/* 479 */     COSBase obj = PDPageTree.getInheritableAttribute(this.page, COSName.ROTATE);
/* 480 */     if (obj instanceof COSNumber) {
/*     */       
/* 482 */       int rotationAngle = ((COSNumber)obj).intValue();
/* 483 */       if (rotationAngle % 90 == 0)
/*     */       {
/* 485 */         return (rotationAngle % 360 + 360) % 360;
/*     */       }
/*     */     } 
/* 488 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotation(int rotation) {
/* 498 */     this.page.setInt(COSName.ROTATE, rotation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContents(PDStream contents) {
/* 508 */     this.page.setItem(COSName.CONTENTS, (COSObjectable)contents);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContents(List<PDStream> contents) {
/* 518 */     COSArray array = new COSArray();
/* 519 */     for (PDStream stream : contents)
/*     */     {
/* 521 */       array.add((COSObjectable)stream);
/*     */     }
/* 523 */     this.page.setItem(COSName.CONTENTS, (COSBase)array);
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
/*     */   public List<PDThreadBead> getThreadBeads() {
/* 535 */     COSArray beads = (COSArray)this.page.getDictionaryObject(COSName.B);
/* 536 */     if (beads == null)
/*     */     {
/* 538 */       beads = new COSArray();
/*     */     }
/* 540 */     List<PDThreadBead> pdObjects = new ArrayList<PDThreadBead>();
/* 541 */     for (int i = 0; i < beads.size(); i++) {
/*     */       
/* 543 */       COSBase base = beads.getObject(i);
/* 544 */       PDThreadBead bead = null;
/*     */       
/* 546 */       if (base instanceof COSDictionary)
/*     */       {
/* 548 */         bead = new PDThreadBead((COSDictionary)base);
/*     */       }
/* 550 */       pdObjects.add(bead);
/*     */     } 
/* 552 */     return (List<PDThreadBead>)new COSArrayList(pdObjects, beads);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThreadBeads(List<PDThreadBead> beads) {
/* 562 */     this.page.setItem(COSName.B, (COSBase)COSArrayList.converterToCOSArray(beads));
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
/* 573 */     PDMetadata retval = null;
/* 574 */     COSBase base = this.page.getDictionaryObject(COSName.METADATA);
/* 575 */     if (base instanceof COSStream)
/*     */     {
/* 577 */       retval = new PDMetadata((COSStream)base);
/*     */     }
/* 579 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMetadata(PDMetadata meta) {
/* 589 */     this.page.setItem(COSName.METADATA, (COSObjectable)meta);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPageAdditionalActions getActions() {
/*     */     COSDictionary addAct;
/* 600 */     COSBase base = this.page.getDictionaryObject(COSName.AA);
/* 601 */     if (base instanceof COSDictionary) {
/*     */       
/* 603 */       addAct = (COSDictionary)base;
/*     */     }
/*     */     else {
/*     */       
/* 607 */       addAct = new COSDictionary();
/* 608 */       this.page.setItem(COSName.AA, (COSBase)addAct);
/*     */     } 
/* 610 */     return new PDPageAdditionalActions(addAct);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActions(PDPageAdditionalActions actions) {
/* 620 */     this.page.setItem(COSName.AA, (COSObjectable)actions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDTransition getTransition() {
/* 628 */     COSBase base = this.page.getDictionaryObject(COSName.TRANS);
/* 629 */     return (base instanceof COSDictionary) ? new PDTransition((COSDictionary)base) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransition(PDTransition transition) {
/* 637 */     this.page.setItem(COSName.TRANS, (COSObjectable)transition);
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
/*     */   public void setTransition(PDTransition transition, float duration) {
/* 649 */     this.page.setItem(COSName.TRANS, (COSObjectable)transition);
/* 650 */     this.page.setItem(COSName.DUR, (COSBase)new COSFloat(duration));
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
/*     */   public List<PDAnnotation> getAnnotations() throws IOException {
/* 663 */     return getAnnotations(new AnnotationFilter()
/*     */         {
/*     */           
/*     */           public boolean accept(PDAnnotation annotation)
/*     */           {
/* 668 */             return true;
/*     */           }
/*     */         });
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
/*     */   public List<PDAnnotation> getAnnotations(AnnotationFilter annotationFilter) throws IOException {
/* 684 */     COSBase base = this.page.getDictionaryObject(COSName.ANNOTS);
/* 685 */     if (base instanceof COSArray) {
/*     */       
/* 687 */       COSArray annots = (COSArray)base;
/* 688 */       List<PDAnnotation> actuals = new ArrayList<PDAnnotation>();
/* 689 */       for (int i = 0; i < annots.size(); i++) {
/*     */         
/* 691 */         COSBase item = annots.getObject(i);
/* 692 */         if (item != null) {
/*     */ 
/*     */ 
/*     */           
/* 696 */           PDAnnotation createdAnnotation = PDAnnotation.createAnnotation(item);
/* 697 */           if (annotationFilter.accept(createdAnnotation))
/*     */           {
/* 699 */             actuals.add(createdAnnotation); } 
/*     */         } 
/*     */       } 
/* 702 */       return (List<PDAnnotation>)new COSArrayList(actuals, annots);
/*     */     } 
/* 704 */     return (List<PDAnnotation>)new COSArrayList(this.page, COSName.ANNOTS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnnotations(List<PDAnnotation> annotations) {
/* 714 */     this.page.setItem(COSName.ANNOTS, (COSBase)COSArrayList.converterToCOSArray(annotations));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/* 720 */     return (other instanceof PDPage && ((PDPage)other).getCOSObject() == getCOSObject());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 726 */     return this.page.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ResourceCache getResourceCache() {
/* 736 */     return this.resourceCache;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PDViewportDictionary> getViewports() {
/* 746 */     COSBase base = this.page.getDictionaryObject(COSName.VP);
/* 747 */     if (!(base instanceof COSArray))
/*     */     {
/* 749 */       return null;
/*     */     }
/* 751 */     COSArray array = (COSArray)base;
/* 752 */     List<PDViewportDictionary> viewports = new ArrayList<PDViewportDictionary>();
/* 753 */     for (int i = 0; i < array.size(); i++) {
/*     */       
/* 755 */       COSBase base2 = array.getObject(i);
/* 756 */       if (base2 instanceof COSDictionary) {
/*     */         
/* 758 */         viewports.add(new PDViewportDictionary((COSDictionary)base2));
/*     */       }
/*     */       else {
/*     */         
/* 762 */         LOG.warn("Array element " + base2 + " is skipped, must be a (viewport) dictionary");
/*     */       } 
/*     */     } 
/* 765 */     return viewports;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setViewports(List<PDViewportDictionary> viewports) {
/* 775 */     if (viewports == null) {
/*     */       
/* 777 */       this.page.removeItem(COSName.VP);
/*     */       return;
/*     */     } 
/* 780 */     COSArray array = new COSArray();
/* 781 */     for (PDViewportDictionary viewport : viewports)
/*     */     {
/* 783 */       array.add((COSObjectable)viewport);
/*     */     }
/* 785 */     this.page.setItem(COSName.VP, (COSBase)array);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDPage.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */