/*     */ package org.apache.pdfbox.pdmodel.interactive.viewerpreferences;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDViewerPreferences
/*     */   implements COSObjectable
/*     */ {
/*     */   public static final String NON_FULL_SCREEN_PAGE_MODE_USE_NONE = "UseNone";
/*     */   public static final String NON_FULL_SCREEN_PAGE_MODE_USE_OUTLINES = "UseOutlines";
/*     */   public static final String NON_FULL_SCREEN_PAGE_MODE_USE_THUMBS = "UseThumbs";
/*     */   public static final String NON_FULL_SCREEN_PAGE_MODE_USE_OPTIONAL_CONTENT = "UseOC";
/*     */   public static final String READING_DIRECTION_L2R = "L2R";
/*     */   public static final String READING_DIRECTION_R2L = "R2L";
/*     */   public static final String BOUNDARY_MEDIA_BOX = "MediaBox";
/*     */   public static final String BOUNDARY_CROP_BOX = "CropBox";
/*     */   public static final String BOUNDARY_BLEED_BOX = "BleedBox";
/*     */   public static final String BOUNDARY_TRIM_BOX = "TrimBox";
/*     */   public static final String BOUNDARY_ART_BOX = "ArtBox";
/*     */   private final COSDictionary prefs;
/*     */   
/*     */   public enum NON_FULL_SCREEN_PAGE_MODE
/*     */   {
/*  65 */     UseNone,
/*     */ 
/*     */ 
/*     */     
/*  69 */     UseOutlines,
/*     */ 
/*     */ 
/*     */     
/*  73 */     UseThumbs,
/*     */ 
/*     */ 
/*     */     
/*  77 */     UseOC;
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
/*     */   public enum READING_DIRECTION
/*     */   {
/* 100 */     L2R,
/*     */ 
/*     */ 
/*     */     
/* 104 */     R2L;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum BOUNDARY
/*     */   {
/* 145 */     MediaBox,
/*     */ 
/*     */ 
/*     */     
/* 149 */     CropBox,
/*     */ 
/*     */ 
/*     */     
/* 153 */     BleedBox,
/*     */ 
/*     */ 
/*     */     
/* 157 */     TrimBox,
/*     */ 
/*     */ 
/*     */     
/* 161 */     ArtBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum DUPLEX
/*     */   {
/* 172 */     Simplex,
/*     */ 
/*     */ 
/*     */     
/* 176 */     DuplexFlipShortEdge,
/*     */ 
/*     */ 
/*     */     
/* 180 */     DuplexFlipLongEdge;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum PRINT_SCALING
/*     */   {
/* 191 */     None,
/*     */ 
/*     */ 
/*     */     
/* 195 */     AppDefault;
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
/*     */   public PDViewerPreferences(COSDictionary dic) {
/* 207 */     this.prefs = dic;
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
/* 218 */     return this.prefs;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hideToolbar() {
/* 228 */     return this.prefs.getBoolean(COSName.HIDE_TOOLBAR, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHideToolbar(boolean value) {
/* 238 */     this.prefs.setBoolean(COSName.HIDE_TOOLBAR, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hideMenubar() {
/* 248 */     return this.prefs.getBoolean(COSName.HIDE_MENUBAR, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHideMenubar(boolean value) {
/* 258 */     this.prefs.setBoolean(COSName.HIDE_MENUBAR, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hideWindowUI() {
/* 268 */     return this.prefs.getBoolean(COSName.HIDE_WINDOWUI, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHideWindowUI(boolean value) {
/* 278 */     this.prefs.setBoolean(COSName.HIDE_WINDOWUI, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean fitWindow() {
/* 288 */     return this.prefs.getBoolean(COSName.FIT_WINDOW, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFitWindow(boolean value) {
/* 298 */     this.prefs.setBoolean(COSName.FIT_WINDOW, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean centerWindow() {
/* 308 */     return this.prefs.getBoolean(COSName.CENTER_WINDOW, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCenterWindow(boolean value) {
/* 318 */     this.prefs.setBoolean(COSName.CENTER_WINDOW, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean displayDocTitle() {
/* 328 */     return this.prefs.getBoolean(COSName.DISPLAY_DOC_TITLE, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDisplayDocTitle(boolean value) {
/* 338 */     this.prefs.setBoolean(COSName.DISPLAY_DOC_TITLE, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNonFullScreenPageMode() {
/* 348 */     return this.prefs.getNameAsString(COSName.NON_FULL_SCREEN_PAGE_MODE, NON_FULL_SCREEN_PAGE_MODE.UseNone
/* 349 */         .toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNonFullScreenPageMode(NON_FULL_SCREEN_PAGE_MODE value) {
/* 359 */     this.prefs.setName(COSName.NON_FULL_SCREEN_PAGE_MODE, value.toString());
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
/*     */   public void setNonFullScreenPageMode(String value) {
/* 371 */     this.prefs.setName(COSName.NON_FULL_SCREEN_PAGE_MODE, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReadingDirection() {
/* 381 */     return this.prefs.getNameAsString(COSName.DIRECTION, READING_DIRECTION.L2R.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReadingDirection(READING_DIRECTION value) {
/* 391 */     this.prefs.setName(COSName.DIRECTION, value.toString());
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
/*     */   public void setReadingDirection(String value) {
/* 403 */     this.prefs.setName(COSName.DIRECTION, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getViewArea() {
/* 413 */     return this.prefs.getNameAsString(COSName.VIEW_AREA, BOUNDARY.CropBox.toString());
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
/*     */   public void setViewArea(String value) {
/* 425 */     this.prefs.setName(COSName.VIEW_AREA, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setViewArea(BOUNDARY value) {
/* 435 */     this.prefs.setName(COSName.VIEW_AREA, value.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getViewClip() {
/* 445 */     return this.prefs.getNameAsString(COSName.VIEW_CLIP, BOUNDARY.CropBox.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setViewClip(BOUNDARY value) {
/* 455 */     this.prefs.setName(COSName.VIEW_CLIP, value.toString());
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
/*     */   public void setViewClip(String value) {
/* 467 */     this.prefs.setName(COSName.VIEW_CLIP, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrintArea() {
/* 477 */     return this.prefs.getNameAsString(COSName.PRINT_AREA, BOUNDARY.CropBox.toString());
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
/*     */   public void setPrintArea(String value) {
/* 489 */     this.prefs.setName(COSName.PRINT_AREA, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrintArea(BOUNDARY value) {
/* 499 */     this.prefs.setName(COSName.PRINT_AREA, value.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrintClip() {
/* 509 */     return this.prefs.getNameAsString(COSName.PRINT_CLIP, BOUNDARY.CropBox.toString());
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
/*     */   public void setPrintClip(String value) {
/* 521 */     this.prefs.setName(COSName.PRINT_CLIP, value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrintClip(BOUNDARY value) {
/* 531 */     this.prefs.setName(COSName.PRINT_CLIP, value.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDuplex() {
/* 541 */     return this.prefs.getNameAsString(COSName.DUPLEX);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDuplex(DUPLEX value) {
/* 551 */     this.prefs.setName(COSName.DUPLEX, value.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPrintScaling() {
/* 561 */     return this.prefs.getNameAsString(COSName.PRINT_SCALING, PRINT_SCALING.AppDefault.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPrintScaling(PRINT_SCALING value) {
/* 571 */     this.prefs.setName(COSName.PRINT_SCALING, value.toString());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/viewerpreferences/PDViewerPreferences.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */