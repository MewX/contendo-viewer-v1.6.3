/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
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
/*     */ public class FDFAnnotationCaret
/*     */   extends FDFAnnotation
/*     */ {
/*     */   public static final String SUBTYPE = "Caret";
/*     */   
/*     */   public FDFAnnotationCaret() {
/*  45 */     this.annot.setName(COSName.SUBTYPE, "Caret");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationCaret(COSDictionary a) {
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
/*     */   public FDFAnnotationCaret(Element element) throws IOException {
/*  67 */     super(element);
/*  68 */     this.annot.setName(COSName.SUBTYPE, "Caret");
/*     */     
/*  70 */     initFringe(element);
/*     */     
/*  72 */     String symbol = element.getAttribute("symbol");
/*  73 */     if (symbol != null && !symbol.isEmpty())
/*     */     {
/*  75 */       setSymbol(element.getAttribute("symbol"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void initFringe(Element element) throws IOException {
/*  81 */     String fringe = element.getAttribute("fringe");
/*  82 */     if (fringe != null && !fringe.isEmpty()) {
/*     */       
/*  84 */       String[] fringeValues = fringe.split(",");
/*  85 */       if (fringeValues.length != 4)
/*     */       {
/*  87 */         throw new IOException("Error: wrong amount of numbers in attribute 'fringe'");
/*     */       }
/*  89 */       PDRectangle rect = new PDRectangle();
/*  90 */       rect.setLowerLeftX(Float.parseFloat(fringeValues[0]));
/*  91 */       rect.setLowerLeftY(Float.parseFloat(fringeValues[1]));
/*  92 */       rect.setUpperRightX(Float.parseFloat(fringeValues[2]));
/*  93 */       rect.setUpperRightY(Float.parseFloat(fringeValues[3]));
/*  94 */       setFringe(rect);
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
/*     */   public final void setFringe(PDRectangle fringe) {
/* 106 */     this.annot.setItem(COSName.RD, (COSObjectable)fringe);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle getFringe() {
/* 117 */     COSArray rd = (COSArray)this.annot.getDictionaryObject(COSName.RD);
/* 118 */     if (rd != null)
/*     */     {
/* 120 */       return new PDRectangle(rd);
/*     */     }
/*     */ 
/*     */     
/* 124 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setSymbol(String symbol) {
/* 135 */     String newSymbol = "None";
/* 136 */     if ("paragraph".equals(symbol))
/*     */     {
/* 138 */       newSymbol = "P";
/*     */     }
/* 140 */     this.annot.setString(COSName.SY, newSymbol);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSymbol() {
/* 150 */     return this.annot.getString(COSName.SY);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationCaret.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */