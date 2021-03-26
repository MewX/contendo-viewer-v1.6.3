/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FDFAnnotationText
/*     */   extends FDFAnnotation
/*     */ {
/*     */   public static final String SUBTYPE = "Text";
/*     */   
/*     */   public FDFAnnotationText() {
/*  45 */     this.annot.setName(COSName.SUBTYPE, "Text");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationText(COSDictionary a) {
/*  55 */     super(a);
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
/*     */   public FDFAnnotationText(Element element) throws IOException {
/*  67 */     super(element);
/*  68 */     this.annot.setName(COSName.SUBTYPE, "Text");
/*  69 */     String icon = element.getAttribute("icon");
/*  70 */     if (icon != null && !icon.isEmpty())
/*     */     {
/*  72 */       setIcon(element.getAttribute("icon"));
/*     */     }
/*  74 */     String state = element.getAttribute("state");
/*  75 */     if (state != null && !state.isEmpty()) {
/*     */       
/*  77 */       String statemodel = element.getAttribute("statemodel");
/*  78 */       if (statemodel != null && !statemodel.isEmpty()) {
/*     */         
/*  80 */         setState(element.getAttribute("state"));
/*  81 */         setStateModel(element.getAttribute("statemodel"));
/*     */       } 
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
/*     */   public void setIcon(String icon) {
/*  94 */     this.annot.setName(COSName.NAME, icon);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getIcon() {
/* 105 */     return this.annot.getNameAsString(COSName.NAME, "Note");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getState() {
/* 115 */     return this.annot.getString(COSName.STATE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setState(String state) {
/* 125 */     this.annot.setString(COSName.STATE, state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getStateModel() {
/* 135 */     return this.annot.getString(COSName.STATE_MODEL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStateModel(String stateModel) {
/* 145 */     this.annot.setString(COSName.STATE_MODEL, stateModel);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationText.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */