/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDCaretAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDFreeTextAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDInkAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDPolygonAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDPolylineAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDSoundAppearanceHandler;
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
/*     */ public class PDAnnotationMarkup
/*     */   extends PDAnnotation
/*     */ {
/*     */   private PDAppearanceHandler customAppearanceHandler;
/*     */   public static final String SUB_TYPE_FREETEXT = "FreeText";
/*     */   public static final String SUB_TYPE_POLYGON = "Polygon";
/*     */   public static final String SUB_TYPE_POLYLINE = "PolyLine";
/*     */   public static final String SUB_TYPE_CARET = "Caret";
/*     */   public static final String SUB_TYPE_INK = "Ink";
/*     */   public static final String SUB_TYPE_SOUND = "Sound";
/*     */   public static final String IT_FREE_TEXT = "FreeText";
/*     */   public static final String IT_FREE_TEXT_CALLOUT = "FreeTextCallout";
/*     */   public static final String IT_FREE_TEXT_TYPE_WRITER = "FreeTextTypeWriter";
/*     */   public static final String RT_REPLY = "R";
/*     */   public static final String RT_GROUP = "Group";
/*     */   
/*     */   public PDAnnotationMarkup() {}
/*     */   
/*     */   public PDAnnotationMarkup(COSDictionary dict) {
/* 125 */     super(dict);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitlePopup() {
/* 136 */     return getCOSObject().getString(COSName.T);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitlePopup(String t) {
/* 147 */     getCOSObject().setString(COSName.T, t);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAnnotationPopup getPopup() {
/* 157 */     COSDictionary popup = (COSDictionary)getCOSObject().getDictionaryObject("Popup");
/* 158 */     if (popup != null)
/*     */     {
/* 160 */       return new PDAnnotationPopup(popup);
/*     */     }
/*     */ 
/*     */     
/* 164 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPopup(PDAnnotationPopup popup) {
/* 175 */     getCOSObject().setItem("Popup", popup);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getConstantOpacity() {
/* 185 */     return getCOSObject().getFloat(COSName.CA, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConstantOpacity(float ca) {
/* 195 */     getCOSObject().setFloat(COSName.CA, ca);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRichContents() {
/* 205 */     COSBase base = getCOSObject().getDictionaryObject(COSName.RC);
/* 206 */     if (base instanceof COSString)
/*     */     {
/* 208 */       return ((COSString)base).getString();
/*     */     }
/* 210 */     if (base instanceof COSStream)
/*     */     {
/* 212 */       return ((COSStream)base).toTextString();
/*     */     }
/*     */ 
/*     */     
/* 216 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRichContents(String rc) {
/* 227 */     getCOSObject().setItem(COSName.RC, (COSBase)new COSString(rc));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Calendar getCreationDate() throws IOException {
/* 238 */     return getCOSObject().getDate(COSName.CREATION_DATE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreationDate(Calendar creationDate) {
/* 248 */     getCOSObject().setDate(COSName.CREATION_DATE, creationDate);
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
/*     */   public PDAnnotation getInReplyTo() throws IOException {
/* 260 */     COSBase base = getCOSObject().getDictionaryObject("IRT");
/* 261 */     if (base instanceof COSDictionary)
/*     */     {
/* 263 */       return PDAnnotation.createAnnotation(base);
/*     */     }
/* 265 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInReplyTo(PDAnnotation irt) {
/* 276 */     getCOSObject().setItem("IRT", irt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubject() {
/* 286 */     return getCOSObject().getString(COSName.SUBJ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubject(String subj) {
/* 296 */     getCOSObject().setString(COSName.SUBJ, subj);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReplyType() {
/* 307 */     return getCOSObject().getNameAsString("RT", "R");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReplyType(String rt) {
/* 318 */     getCOSObject().setName("RT", rt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIntent() {
/* 329 */     return getCOSObject().getNameAsString(COSName.IT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIntent(String it) {
/* 340 */     getCOSObject().setName(COSName.IT, it);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDExternalDataDictionary getExternalData() {
/* 350 */     COSBase exData = getCOSObject().getDictionaryObject("ExData");
/* 351 */     if (exData instanceof COSDictionary)
/*     */     {
/* 353 */       return new PDExternalDataDictionary((COSDictionary)exData);
/*     */     }
/* 355 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExternalData(PDExternalDataDictionary externalData) {
/* 365 */     getCOSObject().setItem("ExData", externalData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBorderStyle(PDBorderStyleDictionary bs) {
/* 376 */     getCOSObject().setItem(COSName.BS, bs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDBorderStyleDictionary getBorderStyle() {
/* 386 */     COSBase bs = getCOSObject().getDictionaryObject(COSName.BS);
/* 387 */     if (bs instanceof COSDictionary)
/*     */     {
/* 389 */       return new PDBorderStyleDictionary((COSDictionary)bs);
/*     */     }
/* 391 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setLineEndingStyle(String style) {
/* 401 */     getCOSObject().setName(COSName.LE, style);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLineEndingStyle() {
/* 412 */     return getCOSObject().getNameAsString(COSName.LE, "None");
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
/*     */   public void setInteriorColor(PDColor ic) {
/* 425 */     getCOSObject().setItem(COSName.IC, (COSBase)ic.toCOSArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getInteriorColor() {
/* 435 */     return getColor(COSName.IC);
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
/*     */   public void setBorderEffect(PDBorderEffectDictionary be) {
/* 447 */     getCOSObject().setItem(COSName.BE, be);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDBorderEffectDictionary getBorderEffect() {
/* 458 */     COSDictionary be = (COSDictionary)getCOSObject().getDictionaryObject(COSName.BE);
/* 459 */     if (be != null)
/*     */     {
/* 461 */       return new PDBorderEffectDictionary(be);
/*     */     }
/*     */ 
/*     */     
/* 465 */     return null;
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
/*     */   public void setInkList(float[][] inkList) {
/* 478 */     if (inkList == null) {
/*     */       
/* 480 */       getCOSObject().removeItem(COSName.INKLIST);
/*     */       return;
/*     */     } 
/* 483 */     COSArray array = new COSArray();
/* 484 */     for (float[] path : inkList) {
/*     */       
/* 486 */       COSArray innerArray = new COSArray();
/* 487 */       innerArray.setFloatArray(path);
/* 488 */       array.add((COSBase)innerArray);
/*     */     } 
/* 490 */     getCOSObject().setItem(COSName.INKLIST, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[][] getInkList() {
/* 501 */     COSBase base = getCOSObject().getDictionaryObject(COSName.INKLIST);
/* 502 */     if (base instanceof COSArray) {
/*     */       
/* 504 */       COSArray array = (COSArray)base;
/* 505 */       float[][] inkList = new float[array.size()][];
/* 506 */       for (int i = 0; i < array.size(); i++) {
/*     */         
/* 508 */         COSBase base2 = array.getObject(i);
/* 509 */         if (base2 instanceof COSArray) {
/*     */           
/* 511 */           inkList[i] = ((COSArray)array.getObject(i)).toFloatArray();
/*     */         }
/*     */         else {
/*     */           
/* 515 */           inkList[i] = new float[0];
/*     */         } 
/*     */       } 
/* 518 */       return inkList;
/*     */     } 
/* 520 */     return new float[0][0];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultAppearance() {
/* 530 */     return getCOSObject().getString(COSName.DA);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultAppearance(String daValue) {
/* 540 */     getCOSObject().setString(COSName.DA, daValue);
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
/*     */   public String getDefaultStyleString() {
/* 552 */     return getCOSObject().getString(COSName.DS);
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
/*     */   public void setDefaultStyleString(String defaultStyleString) {
/* 564 */     getCOSObject().setString(COSName.DS, defaultStyleString);
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
/*     */   public int getQ() {
/* 579 */     return getCOSObject().getInt(COSName.Q, 0);
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
/* 590 */     getCOSObject().setInt(COSName.Q, q);
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
/*     */   public void setRectDifference(PDRectangle rd) {
/* 603 */     getCOSObject().setItem(COSName.RD, (COSObjectable)rd);
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
/*     */   public PDRectangle getRectDifference() {
/* 615 */     COSBase base = getCOSObject().getDictionaryObject(COSName.RD);
/* 616 */     if (base instanceof COSArray)
/*     */     {
/* 618 */       return new PDRectangle((COSArray)base);
/*     */     }
/* 620 */     return null;
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
/*     */   public void setRectDifferences(float difference) {
/* 632 */     setRectDifferences(difference, difference, difference, difference);
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
/*     */   public void setRectDifferences(float differenceLeft, float differenceTop, float differenceRight, float differenceBottom) {
/* 647 */     COSArray margins = new COSArray();
/* 648 */     margins.add((COSBase)new COSFloat(differenceLeft));
/* 649 */     margins.add((COSBase)new COSFloat(differenceTop));
/* 650 */     margins.add((COSBase)new COSFloat(differenceRight));
/* 651 */     margins.add((COSBase)new COSFloat(differenceBottom));
/* 652 */     getCOSObject().setItem(COSName.RD, (COSBase)margins);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getRectDifferences() {
/* 663 */     COSBase margin = getCOSObject().getItem(COSName.RD);
/* 664 */     if (margin instanceof COSArray)
/*     */     {
/* 666 */       return ((COSArray)margin).toFloatArray();
/*     */     }
/* 668 */     return new float[0];
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
/*     */   public final void setCallout(float[] callout) {
/* 682 */     COSArray newCallout = new COSArray();
/* 683 */     newCallout.setFloatArray(callout);
/* 684 */     getCOSObject().setItem(COSName.CL, (COSBase)newCallout);
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
/*     */   public float[] getCallout() {
/* 698 */     COSBase base = getCOSObject().getDictionaryObject(COSName.CL);
/* 699 */     if (base instanceof COSArray)
/*     */     {
/* 701 */       return ((COSArray)base).toFloatArray();
/*     */     }
/* 703 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartPointEndingStyle(String style) {
/* 713 */     String actualStyle = (style == null) ? "None" : style;
/* 714 */     COSBase base = getCOSObject().getDictionaryObject(COSName.LE);
/*     */     
/* 716 */     if (!(base instanceof COSArray) || ((COSArray)base).size() == 0) {
/*     */       
/* 718 */       COSArray array = new COSArray();
/* 719 */       array.add((COSBase)COSName.getPDFName(actualStyle));
/* 720 */       array.add((COSBase)COSName.getPDFName("None"));
/* 721 */       getCOSObject().setItem(COSName.LE, (COSBase)array);
/*     */     }
/*     */     else {
/*     */       
/* 725 */       COSArray array = (COSArray)base;
/* 726 */       array.setName(0, actualStyle);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStartPointEndingStyle() {
/* 737 */     COSBase base = getCOSObject().getDictionaryObject(COSName.LE);
/* 738 */     if (base instanceof COSArray && ((COSArray)base).size() >= 2)
/*     */     {
/* 740 */       return ((COSArray)base).getName(0, "None");
/*     */     }
/* 742 */     return "None";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndPointEndingStyle(String style) {
/* 752 */     String actualStyle = (style == null) ? "None" : style;
/* 753 */     COSBase base = getCOSObject().getDictionaryObject(COSName.LE);
/*     */     
/* 755 */     if (!(base instanceof COSArray) || ((COSArray)base).size() < 2) {
/*     */       
/* 757 */       COSArray array = new COSArray();
/* 758 */       array.add((COSBase)COSName.getPDFName("None"));
/* 759 */       array.add((COSBase)COSName.getPDFName(actualStyle));
/* 760 */       getCOSObject().setItem(COSName.LE, (COSBase)array);
/*     */     }
/*     */     else {
/*     */       
/* 764 */       COSArray array = (COSArray)base;
/* 765 */       array.setName(1, actualStyle);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEndPointEndingStyle() {
/* 776 */     COSBase base = getCOSObject().getDictionaryObject(COSName.LE);
/* 777 */     if (base instanceof COSArray && ((COSArray)base).size() >= 2)
/*     */     {
/* 779 */       return ((COSArray)base).getName(1, "None");
/*     */     }
/* 781 */     return "None";
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
/*     */   public float[] getVertices() {
/* 793 */     COSBase base = getCOSObject().getDictionaryObject(COSName.VERTICES);
/* 794 */     if (base instanceof COSArray)
/*     */     {
/* 796 */       return ((COSArray)base).toFloatArray();
/*     */     }
/* 798 */     return null;
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
/*     */   public void setVertices(float[] points) {
/* 810 */     COSArray ar = new COSArray();
/* 811 */     ar.setFloatArray(points);
/* 812 */     getCOSObject().setItem(COSName.VERTICES, (COSBase)ar);
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
/*     */   public float[][] getPath() {
/* 825 */     COSBase base = getCOSObject().getDictionaryObject(COSName.PATH);
/* 826 */     if (base instanceof COSArray) {
/*     */       
/* 828 */       COSArray array = (COSArray)base;
/* 829 */       float[][] pathArray = new float[array.size()][];
/* 830 */       for (int i = 0; i < array.size(); i++) {
/*     */         
/* 832 */         COSBase base2 = array.getObject(i);
/* 833 */         if (base2 instanceof COSArray) {
/*     */           
/* 835 */           pathArray[i] = ((COSArray)array.getObject(i)).toFloatArray();
/*     */         }
/*     */         else {
/*     */           
/* 839 */           pathArray[i] = new float[0];
/*     */         } 
/*     */       } 
/* 842 */       return pathArray;
/*     */     } 
/* 844 */     return (float[][])null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCustomAppearanceHandler(PDAppearanceHandler appearanceHandler) {
/* 854 */     this.customAppearanceHandler = appearanceHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void constructAppearances() {
/* 860 */     if (this.customAppearanceHandler == null) {
/*     */       PDSoundAppearanceHandler pDSoundAppearanceHandler;
/* 862 */       PDAppearanceHandler appearanceHandler = null;
/* 863 */       if ("Caret".equals(getSubtype())) {
/*     */         
/* 865 */         PDCaretAppearanceHandler pDCaretAppearanceHandler = new PDCaretAppearanceHandler(this);
/*     */       }
/* 867 */       else if ("FreeText".equals(getSubtype())) {
/*     */         
/* 869 */         PDFreeTextAppearanceHandler pDFreeTextAppearanceHandler = new PDFreeTextAppearanceHandler(this);
/*     */       }
/* 871 */       else if ("Ink".equals(getSubtype())) {
/*     */         
/* 873 */         PDInkAppearanceHandler pDInkAppearanceHandler = new PDInkAppearanceHandler(this);
/*     */       }
/* 875 */       else if ("Polygon".equals(getSubtype())) {
/*     */         
/* 877 */         PDPolygonAppearanceHandler pDPolygonAppearanceHandler = new PDPolygonAppearanceHandler(this);
/*     */       }
/* 879 */       else if ("PolyLine".equals(getSubtype())) {
/*     */         
/* 881 */         PDPolylineAppearanceHandler pDPolylineAppearanceHandler = new PDPolylineAppearanceHandler(this);
/*     */       }
/* 883 */       else if ("Sound".equals(getSubtype())) {
/*     */         
/* 885 */         pDSoundAppearanceHandler = new PDSoundAppearanceHandler(this);
/*     */       } 
/*     */       
/* 888 */       if (pDSoundAppearanceHandler != null)
/*     */       {
/* 890 */         pDSoundAppearanceHandler.generateAppearanceStreams();
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 895 */       this.customAppearanceHandler.generateAppearanceStreams();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDAnnotationMarkup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */