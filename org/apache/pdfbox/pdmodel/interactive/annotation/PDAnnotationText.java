/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDTextAppearanceHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDAnnotationText
/*     */   extends PDAnnotationMarkup
/*     */ {
/*     */   private PDAppearanceHandler customAppearanceHandler;
/*     */   public static final String NAME_COMMENT = "Comment";
/*     */   public static final String NAME_KEY = "Key";
/*     */   public static final String NAME_NOTE = "Note";
/*     */   public static final String NAME_HELP = "Help";
/*     */   public static final String NAME_NEW_PARAGRAPH = "NewParagraph";
/*     */   public static final String NAME_PARAGRAPH = "Paragraph";
/*     */   public static final String NAME_INSERT = "Insert";
/*     */   public static final String NAME_CIRCLE = "Circle";
/*     */   public static final String NAME_CROSS = "Cross";
/*     */   public static final String NAME_STAR = "Star";
/*     */   public static final String NAME_CHECK = "Check";
/*     */   public static final String NAME_RIGHT_ARROW = "RightArrow";
/*     */   public static final String NAME_RIGHT_POINTER = "RightPointer";
/*     */   public static final String NAME_UP_ARROW = "UpArrow";
/*     */   public static final String NAME_UP_LEFT_ARROW = "UpLeftArrow";
/*     */   public static final String NAME_CROSS_HAIRS = "CrossHairs";
/*     */   public static final String SUB_TYPE = "Text";
/*     */   
/*     */   public PDAnnotationText() {
/* 127 */     getCOSObject().setName(COSName.SUBTYPE, "Text");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAnnotationText(COSDictionary field) {
/* 137 */     super(field);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpen(boolean open) {
/* 147 */     getCOSObject().setBoolean(COSName.getPDFName("Open"), open);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getOpen() {
/* 157 */     return getCOSObject().getBoolean(COSName.getPDFName("Open"), false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 168 */     getCOSObject().setName(COSName.NAME, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 179 */     return getCOSObject().getNameAsString(COSName.NAME, "Note");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getState() {
/* 189 */     return getCOSObject().getString(COSName.STATE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setState(String state) {
/* 199 */     getCOSObject().setString(COSName.STATE, state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStateModel() {
/* 209 */     return getCOSObject().getString(COSName.STATE_MODEL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStateModel(String stateModel) {
/* 219 */     getCOSObject().setString(COSName.STATE_MODEL, stateModel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCustomAppearanceHandler(PDAppearanceHandler appearanceHandler) {
/* 229 */     this.customAppearanceHandler = appearanceHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void constructAppearances() {
/* 235 */     if (this.customAppearanceHandler == null) {
/*     */       
/* 237 */       PDTextAppearanceHandler appearanceHandler = new PDTextAppearanceHandler(this);
/* 238 */       appearanceHandler.generateAppearanceStreams();
/*     */     }
/*     */     else {
/*     */       
/* 242 */       this.customAppearanceHandler.generateAppearanceStreams();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDAnnotationText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */