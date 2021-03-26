/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.PDPage;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.markedcontent.PDPropertyList;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceCMYK;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
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
/*     */ public abstract class PDAnnotation
/*     */   implements COSObjectable
/*     */ {
/*  50 */   private static final Log LOG = LogFactory.getLog(PDAnnotation.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int FLAG_INVISIBLE = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int FLAG_HIDDEN = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int FLAG_PRINTED = 4;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int FLAG_NO_ZOOM = 8;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int FLAG_NO_ROTATE = 16;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int FLAG_NO_VIEW = 32;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int FLAG_READ_ONLY = 64;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int FLAG_LOCKED = 128;
/*     */ 
/*     */ 
/*     */   
/*     */   private static final int FLAG_TOGGLE_NO_VIEW = 256;
/*     */ 
/*     */ 
/*     */   
/*     */   private final COSDictionary dictionary;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static PDAnnotation createAnnotation(COSBase base) throws IOException {
/* 101 */     PDAnnotation annot = null;
/* 102 */     if (base instanceof COSDictionary) {
/*     */       
/* 104 */       COSDictionary annotDic = (COSDictionary)base;
/* 105 */       String subtype = annotDic.getNameAsString(COSName.SUBTYPE);
/* 106 */       if ("FileAttachment".equals(subtype))
/*     */       {
/* 108 */         annot = new PDAnnotationFileAttachment(annotDic);
/*     */       }
/* 110 */       else if ("Line".equals(subtype))
/*     */       {
/* 112 */         annot = new PDAnnotationLine(annotDic);
/*     */       }
/* 114 */       else if ("Link".equals(subtype))
/*     */       {
/* 116 */         annot = new PDAnnotationLink(annotDic);
/*     */       }
/* 118 */       else if ("Popup".equals(subtype))
/*     */       {
/* 120 */         annot = new PDAnnotationPopup(annotDic);
/*     */       }
/* 122 */       else if ("Stamp".equals(subtype))
/*     */       {
/* 124 */         annot = new PDAnnotationRubberStamp(annotDic);
/*     */       }
/* 126 */       else if ("Square".equals(subtype) || "Circle"
/* 127 */         .equals(subtype))
/*     */       {
/* 129 */         annot = new PDAnnotationSquareCircle(annotDic);
/*     */       }
/* 131 */       else if ("Text".equals(subtype))
/*     */       {
/* 133 */         annot = new PDAnnotationText(annotDic);
/*     */       }
/* 135 */       else if ("Highlight".equals(subtype) || "Underline"
/* 136 */         .equals(subtype) || "Squiggly"
/* 137 */         .equals(subtype) || "StrikeOut"
/* 138 */         .equals(subtype))
/*     */       {
/*     */         
/* 141 */         annot = new PDAnnotationTextMarkup(annotDic);
/*     */       }
/* 143 */       else if ("Widget".equals(subtype))
/*     */       {
/* 145 */         annot = new PDAnnotationWidget(annotDic);
/*     */       }
/* 147 */       else if ("FreeText".equals(subtype) || "Polygon"
/* 148 */         .equals(subtype) || "PolyLine"
/* 149 */         .equals(subtype) || "Caret"
/* 150 */         .equals(subtype) || "Ink"
/* 151 */         .equals(subtype) || "Sound"
/* 152 */         .equals(subtype))
/*     */       {
/* 154 */         annot = new PDAnnotationMarkup(annotDic);
/*     */       
/*     */       }
/*     */       else
/*     */       {
/*     */         
/* 160 */         annot = new PDAnnotationUnknown(annotDic);
/* 161 */         LOG.debug("Unknown or unsupported annotation subtype " + subtype);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 166 */       throw new IOException("Error: Unknown annotation type " + base);
/*     */     } 
/*     */     
/* 169 */     return annot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAnnotation() {
/* 177 */     this.dictionary = new COSDictionary();
/* 178 */     this.dictionary.setItem(COSName.TYPE, (COSBase)COSName.ANNOT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAnnotation(COSDictionary dict) {
/* 188 */     this.dictionary = dict;
/* 189 */     this.dictionary.setItem(COSName.TYPE, (COSBase)COSName.ANNOT);
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
/*     */   public PDRectangle getRectangle() {
/* 201 */     COSArray rectArray = (COSArray)this.dictionary.getDictionaryObject(COSName.RECT);
/* 202 */     PDRectangle rectangle = null;
/* 203 */     if (rectArray != null)
/*     */     {
/* 205 */       if (rectArray.size() == 4 && rectArray.getObject(0) instanceof org.apache.pdfbox.cos.COSNumber && rectArray
/* 206 */         .getObject(1) instanceof org.apache.pdfbox.cos.COSNumber && rectArray
/* 207 */         .getObject(2) instanceof org.apache.pdfbox.cos.COSNumber && rectArray
/* 208 */         .getObject(3) instanceof org.apache.pdfbox.cos.COSNumber) {
/*     */         
/* 210 */         rectangle = new PDRectangle(rectArray);
/*     */       }
/*     */       else {
/*     */         
/* 214 */         LOG.warn(rectArray + " is not a rectangle array, returning null");
/*     */       } 
/*     */     }
/* 217 */     return rectangle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRectangle(PDRectangle rectangle) {
/* 227 */     this.dictionary.setItem(COSName.RECT, (COSBase)rectangle.getCOSArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnnotationFlags() {
/* 237 */     return getCOSObject().getInt(COSName.F, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnnotationFlags(int flags) {
/* 247 */     getCOSObject().setInt(COSName.F, flags);
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
/* 258 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSName getAppearanceState() {
/* 267 */     return getCOSObject().getCOSName(COSName.AS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAppearanceState(String as) {
/* 277 */     getCOSObject().setName(COSName.AS, as);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAppearanceDictionary getAppearance() {
/* 287 */     COSBase base = this.dictionary.getDictionaryObject(COSName.AP);
/* 288 */     if (base instanceof COSDictionary)
/*     */     {
/* 290 */       return new PDAppearanceDictionary((COSDictionary)base);
/*     */     }
/* 292 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAppearance(PDAppearanceDictionary appearance) {
/* 302 */     this.dictionary.setItem(COSName.AP, appearance);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAppearanceStream getNormalAppearanceStream() {
/* 311 */     PDAppearanceDictionary appearanceDict = getAppearance();
/* 312 */     if (appearanceDict == null)
/*     */     {
/* 314 */       return null;
/*     */     }
/*     */     
/* 317 */     PDAppearanceEntry normalAppearance = appearanceDict.getNormalAppearance();
/* 318 */     if (normalAppearance == null)
/*     */     {
/* 320 */       return null;
/*     */     }
/*     */     
/* 323 */     if (normalAppearance.isSubDictionary()) {
/*     */       
/* 325 */       COSName state = getAppearanceState();
/* 326 */       return normalAppearance.getSubDictionary().get(state);
/*     */     } 
/*     */ 
/*     */     
/* 330 */     return normalAppearance.getAppearanceStream();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInvisible() {
/* 341 */     return getCOSObject().getFlag(COSName.F, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInvisible(boolean invisible) {
/* 351 */     getCOSObject().setFlag(COSName.F, 1, invisible);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHidden() {
/* 361 */     return getCOSObject().getFlag(COSName.F, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHidden(boolean hidden) {
/* 371 */     getCOSObject().setFlag(COSName.F, 2, hidden);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPrinted() {
/* 381 */     return getCOSObject().getFlag(COSName.F, 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrinted(boolean printed) {
/* 391 */     getCOSObject().setFlag(COSName.F, 4, printed);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNoZoom() {
/* 401 */     return getCOSObject().getFlag(COSName.F, 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNoZoom(boolean noZoom) {
/* 411 */     getCOSObject().setFlag(COSName.F, 8, noZoom);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNoRotate() {
/* 421 */     return getCOSObject().getFlag(COSName.F, 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNoRotate(boolean noRotate) {
/* 431 */     getCOSObject().setFlag(COSName.F, 16, noRotate);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNoView() {
/* 441 */     return getCOSObject().getFlag(COSName.F, 32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNoView(boolean noView) {
/* 451 */     getCOSObject().setFlag(COSName.F, 32, noView);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadOnly() {
/* 461 */     return getCOSObject().getFlag(COSName.F, 64);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReadOnly(boolean readOnly) {
/* 471 */     getCOSObject().setFlag(COSName.F, 64, readOnly);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLocked() {
/* 481 */     return getCOSObject().getFlag(COSName.F, 128);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocked(boolean locked) {
/* 491 */     getCOSObject().setFlag(COSName.F, 128, locked);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isToggleNoView() {
/* 501 */     return getCOSObject().getFlag(COSName.F, 256);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setToggleNoView(boolean toggleNoView) {
/* 511 */     getCOSObject().setFlag(COSName.F, 256, toggleNoView);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContents() {
/* 521 */     return this.dictionary.getString(COSName.CONTENTS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContents(String value) {
/* 531 */     this.dictionary.setString(COSName.CONTENTS, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getModifiedDate() {
/* 541 */     return getCOSObject().getString(COSName.M);
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
/*     */   public void setModifiedDate(String m) {
/* 555 */     getCOSObject().setString(COSName.M, m);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setModifiedDate(Calendar c) {
/* 565 */     getCOSObject().setDate(COSName.M, c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAnnotationName() {
/* 576 */     return getCOSObject().getString(COSName.NM);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnnotationName(String nm) {
/* 587 */     getCOSObject().setString(COSName.NM, nm);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getStructParent() {
/* 598 */     return getCOSObject().getInt(COSName.STRUCT_PARENT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStructParent(int structParent) {
/* 608 */     getCOSObject().setInt(COSName.STRUCT_PARENT, structParent);
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
/*     */   public PDPropertyList getOptionalContent() {
/* 620 */     COSBase base = getCOSObject().getDictionaryObject(COSName.OC);
/* 621 */     if (base instanceof COSDictionary)
/*     */     {
/* 623 */       return PDPropertyList.create((COSDictionary)base);
/*     */     }
/* 625 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOptionalContent(PDPropertyList oc) {
/* 635 */     getCOSObject().setItem(COSName.OC, (COSObjectable)oc);
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
/*     */   public COSArray getBorder() {
/*     */     COSArray border;
/* 649 */     COSBase base = getCOSObject().getDictionaryObject(COSName.BORDER);
/*     */     
/* 651 */     if (base instanceof COSArray) {
/*     */       
/* 653 */       border = (COSArray)base;
/* 654 */       if (border.size() < 3)
/*     */       {
/*     */         
/* 657 */         COSArray newBorder = new COSArray();
/* 658 */         newBorder.addAll(border);
/* 659 */         border = newBorder;
/*     */         
/* 661 */         while (border.size() < 3)
/*     */         {
/* 663 */           border.add((COSBase)COSInteger.ZERO);
/*     */         }
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 669 */       border = new COSArray();
/* 670 */       border.add((COSBase)COSInteger.ZERO);
/* 671 */       border.add((COSBase)COSInteger.ZERO);
/* 672 */       border.add((COSBase)COSInteger.ONE);
/*     */     } 
/* 674 */     return border;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBorder(COSArray borderArray) {
/* 684 */     getCOSObject().setItem(COSName.BORDER, (COSBase)borderArray);
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
/*     */   public void setColor(PDColor c) {
/* 698 */     getCOSObject().setItem(COSName.C, (COSBase)c.toCOSArray());
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
/*     */   public PDColor getColor() {
/* 714 */     return getColor(COSName.C);
/*     */   }
/*     */ 
/*     */   
/*     */   protected PDColor getColor(COSName itemName) {
/* 719 */     COSBase c = getCOSObject().getItem(itemName);
/* 720 */     if (c instanceof COSArray) {
/*     */       PDDeviceGray pDDeviceGray; PDDeviceRGB pDDeviceRGB; PDDeviceCMYK pDDeviceCMYK;
/* 722 */       PDColorSpace colorSpace = null;
/* 723 */       switch (((COSArray)c).size()) {
/*     */         
/*     */         case 1:
/* 726 */           pDDeviceGray = PDDeviceGray.INSTANCE;
/*     */           break;
/*     */         case 3:
/* 729 */           pDDeviceRGB = PDDeviceRGB.INSTANCE;
/*     */           break;
/*     */         case 4:
/* 732 */           pDDeviceCMYK = PDDeviceCMYK.INSTANCE;
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/* 737 */       return new PDColor((COSArray)c, (PDColorSpace)pDDeviceCMYK);
/*     */     } 
/* 739 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubtype() {
/* 749 */     return getCOSObject().getNameAsString(COSName.SUBTYPE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPage(PDPage page) {
/* 759 */     getCOSObject().setItem(COSName.P, (COSObjectable)page);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPage getPage() {
/* 769 */     COSBase base = getCOSObject().getDictionaryObject(COSName.P);
/* 770 */     if (base instanceof COSDictionary)
/*     */     {
/* 772 */       return new PDPage((COSDictionary)base);
/*     */     }
/* 774 */     return null;
/*     */   }
/*     */   
/*     */   public void constructAppearances() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDAnnotation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */