/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDLineAppearanceHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDAnnotationLine
/*     */   extends PDAnnotationMarkup
/*     */ {
/*     */   private PDAppearanceHandler customAppearanceHandler;
/*     */   public static final String IT_LINE_ARROW = "LineArrow";
/*     */   public static final String IT_LINE_DIMENSION = "LineDimension";
/*     */   public static final String LE_SQUARE = "Square";
/*     */   public static final String LE_CIRCLE = "Circle";
/*     */   public static final String LE_DIAMOND = "Diamond";
/*     */   public static final String LE_OPEN_ARROW = "OpenArrow";
/*     */   public static final String LE_CLOSED_ARROW = "ClosedArrow";
/*     */   public static final String LE_NONE = "None";
/*     */   public static final String LE_BUTT = "Butt";
/*     */   public static final String LE_R_OPEN_ARROW = "ROpenArrow";
/*     */   public static final String LE_R_CLOSED_ARROW = "RClosedArrow";
/*     */   public static final String LE_SLASH = "Slash";
/*     */   public static final String SUB_TYPE = "Line";
/*     */   
/*     */   public PDAnnotationLine() {
/* 115 */     getCOSObject().setName(COSName.SUBTYPE, "Line");
/*     */     
/* 117 */     setLine(new float[] { 0.0F, 0.0F, 0.0F, 0.0F });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAnnotationLine(COSDictionary field) {
/* 127 */     super(field);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLine(float[] l) {
/* 137 */     COSArray newL = new COSArray();
/* 138 */     newL.setFloatArray(l);
/* 139 */     getCOSObject().setItem(COSName.L, (COSBase)newL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getLine() {
/* 149 */     COSArray l = (COSArray)getCOSObject().getDictionaryObject(COSName.L);
/* 150 */     return l.toFloatArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStartPointEndingStyle(String style) {
/* 160 */     if (style == null)
/*     */     {
/* 162 */       style = "None";
/*     */     }
/* 164 */     COSBase base = getCOSObject().getDictionaryObject(COSName.LE);
/*     */     
/* 166 */     if (!(base instanceof COSArray) || ((COSArray)base).size() == 0) {
/*     */       
/* 168 */       COSArray array = new COSArray();
/* 169 */       array.add((COSBase)COSName.getPDFName(style));
/* 170 */       array.add((COSBase)COSName.getPDFName("None"));
/* 171 */       getCOSObject().setItem(COSName.LE, (COSBase)array);
/*     */     }
/*     */     else {
/*     */       
/* 175 */       COSArray array = (COSArray)base;
/* 176 */       array.setName(0, style);
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
/* 187 */     COSBase base = getCOSObject().getDictionaryObject(COSName.LE);
/* 188 */     if (base instanceof COSArray && ((COSArray)base).size() >= 2)
/*     */     {
/* 190 */       return ((COSArray)base).getName(0);
/*     */     }
/* 192 */     return "None";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEndPointEndingStyle(String style) {
/* 202 */     if (style == null)
/*     */     {
/* 204 */       style = "None";
/*     */     }
/* 206 */     COSBase base = getCOSObject().getDictionaryObject(COSName.LE);
/*     */     
/* 208 */     if (!(base instanceof COSArray) || ((COSArray)base).size() < 2) {
/*     */       
/* 210 */       COSArray array = new COSArray();
/* 211 */       array.add((COSBase)COSName.getPDFName("None"));
/* 212 */       array.add((COSBase)COSName.getPDFName(style));
/* 213 */       getCOSObject().setItem(COSName.LE, (COSBase)array);
/*     */     }
/*     */     else {
/*     */       
/* 217 */       COSArray array = (COSArray)base;
/* 218 */       array.setName(1, style);
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
/* 229 */     COSBase base = getCOSObject().getDictionaryObject(COSName.LE);
/* 230 */     if (base instanceof COSArray && ((COSArray)base).size() >= 2)
/*     */     {
/* 232 */       return ((COSArray)base).getName(1);
/*     */     }
/* 234 */     return "None";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInteriorColor(PDColor ic) {
/* 244 */     getCOSObject().setItem(COSName.IC, (COSBase)ic.toCOSArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getInteriorColor() {
/* 255 */     return getColor(COSName.IC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCaption(boolean cap) {
/* 265 */     getCOSObject().setBoolean(COSName.CAP, cap);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCaption() {
/* 275 */     return getCOSObject().getBoolean(COSName.CAP, false);
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
/*     */   public void setBorderStyle(PDBorderStyleDictionary bs) {
/* 287 */     getCOSObject().setItem(COSName.BS, bs);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDBorderStyleDictionary getBorderStyle() {
/* 298 */     COSBase bs = getCOSObject().getDictionaryObject(COSName.BS);
/* 299 */     if (bs instanceof COSDictionary)
/*     */     {
/* 301 */       return new PDBorderStyleDictionary((COSDictionary)bs);
/*     */     }
/* 303 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLeaderLineLength() {
/* 313 */     return getCOSObject().getFloat(COSName.LL, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeaderLineLength(float leaderLineLength) {
/* 323 */     getCOSObject().setFloat(COSName.LL, leaderLineLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLeaderLineExtensionLength() {
/* 333 */     return getCOSObject().getFloat(COSName.LLE, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeaderLineExtensionLength(float leaderLineExtensionLength) {
/* 343 */     getCOSObject().setFloat(COSName.LLE, leaderLineExtensionLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getLeaderLineOffsetLength() {
/* 353 */     return getCOSObject().getFloat(COSName.LLO, 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLeaderLineOffsetLength(float leaderLineOffsetLength) {
/* 363 */     getCOSObject().setFloat(COSName.LLO, leaderLineOffsetLength);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCaptionPositioning() {
/* 373 */     return getCOSObject().getNameAsString(COSName.CP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCaptionPositioning(String captionPositioning) {
/* 383 */     getCOSObject().setName(COSName.CP, captionPositioning);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCaptionHorizontalOffset(float offset) {
/* 393 */     COSArray array = (COSArray)getCOSObject().getDictionaryObject(COSName.CO);
/* 394 */     if (array == null) {
/*     */       
/* 396 */       array = new COSArray();
/* 397 */       array.setFloatArray(new float[] { offset, 0.0F });
/* 398 */       getCOSObject().setItem(COSName.CO, (COSBase)array);
/*     */     }
/*     */     else {
/*     */       
/* 402 */       array.set(0, (COSBase)new COSFloat(offset));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCaptionHorizontalOffset() {
/* 413 */     float retval = 0.0F;
/* 414 */     COSArray array = (COSArray)getCOSObject().getDictionaryObject(COSName.CO);
/* 415 */     if (array != null)
/*     */     {
/* 417 */       retval = array.toFloatArray()[0];
/*     */     }
/*     */     
/* 420 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCaptionVerticalOffset(float offset) {
/* 430 */     COSArray array = (COSArray)getCOSObject().getDictionaryObject(COSName.CO);
/* 431 */     if (array == null) {
/*     */       
/* 433 */       array = new COSArray();
/* 434 */       array.setFloatArray(new float[] { 0.0F, offset });
/* 435 */       getCOSObject().setItem(COSName.CO, (COSBase)array);
/*     */     }
/*     */     else {
/*     */       
/* 439 */       array.set(1, (COSBase)new COSFloat(offset));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCaptionVerticalOffset() {
/* 450 */     float retval = 0.0F;
/* 451 */     COSArray array = (COSArray)getCOSObject().getDictionaryObject(COSName.CO);
/* 452 */     if (array != null)
/*     */     {
/* 454 */       retval = array.toFloatArray()[1];
/*     */     }
/* 456 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCustomAppearanceHandler(PDAppearanceHandler appearanceHandler) {
/* 466 */     this.customAppearanceHandler = appearanceHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void constructAppearances() {
/* 472 */     if (this.customAppearanceHandler == null) {
/*     */       
/* 474 */       PDLineAppearanceHandler appearanceHandler = new PDLineAppearanceHandler(this);
/* 475 */       appearanceHandler.generateAppearanceStreams();
/*     */     }
/*     */     else {
/*     */       
/* 479 */       this.customAppearanceHandler.generateAppearanceStreams();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDAnnotationLine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */