/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDAction;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDActionFactory;
/*     */ import org.apache.pdfbox.pdmodel.interactive.action.PDActionURI;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDLinkAppearanceHandler;
/*     */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDAnnotationLink
/*     */   extends PDAnnotation
/*     */ {
/*     */   private PDAppearanceHandler customAppearanceHandler;
/*     */   public static final String HIGHLIGHT_MODE_NONE = "N";
/*     */   public static final String HIGHLIGHT_MODE_INVERT = "I";
/*     */   public static final String HIGHLIGHT_MODE_OUTLINE = "O";
/*     */   public static final String HIGHLIGHT_MODE_PUSH = "P";
/*     */   public static final String SUB_TYPE = "Link";
/*     */   
/*     */   public PDAnnotationLink() {
/*  69 */     getCOSObject().setName(COSName.SUBTYPE, "Link");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAnnotationLink(COSDictionary field) {
/*  79 */     super(field);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAction getAction() {
/*  90 */     COSBase base = getCOSObject().getDictionaryObject(COSName.A);
/*  91 */     if (base instanceof COSDictionary)
/*     */     {
/*  93 */       return PDActionFactory.createAction((COSDictionary)base);
/*     */     }
/*  95 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAction(PDAction action) {
/* 106 */     getCOSObject().setItem(COSName.A, (COSObjectable)action);
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
/* 117 */     getCOSObject().setItem(COSName.BS, bs);
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
/* 128 */     COSBase bs = getCOSObject().getDictionaryObject(COSName.BS);
/* 129 */     if (bs instanceof COSDictionary)
/*     */     {
/* 131 */       return new PDBorderStyleDictionary((COSDictionary)bs);
/*     */     }
/* 133 */     return null;
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
/*     */   public PDDestination getDestination() throws IOException {
/* 146 */     COSBase base = getCOSObject().getDictionaryObject(COSName.DEST);
/* 147 */     return PDDestination.create(base);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDestination(PDDestination dest) {
/* 157 */     getCOSObject().setItem(COSName.DEST, (COSObjectable)dest);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHighlightMode() {
/* 167 */     return getCOSObject().getNameAsString(COSName.H, "I");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHighlightMode(String mode) {
/* 177 */     getCOSObject().setName(COSName.H, mode);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPreviousURI(PDActionURI pa) {
/* 187 */     getCOSObject().setItem("PA", (COSObjectable)pa);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDActionURI getPreviousURI() {
/* 197 */     COSBase base = getCOSObject().getDictionaryObject("PA");
/* 198 */     if (base instanceof COSDictionary)
/*     */     {
/* 200 */       return new PDActionURI((COSDictionary)base);
/*     */     }
/* 202 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setQuadPoints(float[] quadPoints) {
/* 212 */     COSArray newQuadPoints = new COSArray();
/* 213 */     newQuadPoints.setFloatArray(quadPoints);
/* 214 */     getCOSObject().setItem(COSName.QUADPOINTS, (COSBase)newQuadPoints);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getQuadPoints() {
/* 224 */     COSBase base = getCOSObject().getDictionaryObject(COSName.QUADPOINTS);
/* 225 */     if (base instanceof COSArray)
/*     */     {
/* 227 */       return ((COSArray)base).toFloatArray();
/*     */     }
/*     */     
/* 230 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCustomAppearanceHandler(PDAppearanceHandler appearanceHandler) {
/* 240 */     this.customAppearanceHandler = appearanceHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void constructAppearances() {
/* 246 */     if (this.customAppearanceHandler == null) {
/*     */       
/* 248 */       PDLinkAppearanceHandler appearanceHandler = new PDLinkAppearanceHandler(this);
/* 249 */       appearanceHandler.generateAppearanceStreams();
/*     */     }
/*     */     else {
/*     */       
/* 253 */       this.customAppearanceHandler.generateAppearanceStreams();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDAnnotationLink.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */