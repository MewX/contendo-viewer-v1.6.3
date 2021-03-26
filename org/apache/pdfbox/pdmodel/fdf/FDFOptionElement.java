/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSString;
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
/*     */ public class FDFOptionElement
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSArray option;
/*     */   
/*     */   public FDFOptionElement() {
/*  40 */     this.option = new COSArray();
/*  41 */     this.option.add((COSBase)new COSString(""));
/*  42 */     this.option.add((COSBase)new COSString(""));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFOptionElement(COSArray o) {
/*  52 */     this.option = o;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/*  63 */     return (COSBase)this.option;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getCOSArray() {
/*  73 */     return this.option;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOption() {
/*  83 */     return ((COSString)this.option.getObject(0)).getString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOption(String opt) {
/*  93 */     this.option.set(0, (COSBase)new COSString(opt));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDefaultAppearanceString() {
/* 103 */     return ((COSString)this.option.getObject(1)).getString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultAppearanceString(String da) {
/* 113 */     this.option.set(1, (COSBase)new COSString(da));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFOptionElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */