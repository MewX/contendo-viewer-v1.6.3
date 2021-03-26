/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDAnnotationPopup
/*     */   extends PDAnnotation
/*     */ {
/*     */   public static final String SUB_TYPE = "Popup";
/*     */   
/*     */   public PDAnnotationPopup() {
/*  42 */     getCOSObject().setName(COSName.SUBTYPE, "Popup");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAnnotationPopup(COSDictionary field) {
/*  52 */     super(field);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpen(boolean open) {
/*  62 */     getCOSObject().setBoolean("Open", open);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getOpen() {
/*  72 */     return getCOSObject().getBoolean("Open", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setParent(PDAnnotationMarkup annot) {
/*  82 */     getCOSObject().setItem(COSName.PARENT, (COSBase)annot.getCOSObject());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDAnnotationMarkup getParent() {
/*  92 */     PDAnnotationMarkup am = null;
/*     */     
/*     */     try {
/*  95 */       am = (PDAnnotationMarkup)PDAnnotation.createAnnotation(getCOSObject()
/*  96 */           .getDictionaryObject(COSName.PARENT, COSName.P));
/*     */     }
/*  98 */     catch (IOException iOException) {}
/*     */ 
/*     */ 
/*     */     
/* 102 */     return am;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDAnnotationPopup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */