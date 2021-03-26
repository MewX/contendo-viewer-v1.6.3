/*     */ package org.apache.pdfbox.pdmodel.fdf;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
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
/*     */ public class FDFAnnotationSquare
/*     */   extends FDFAnnotation
/*     */ {
/*     */   public static final String SUBTYPE = "Square";
/*     */   
/*     */   public FDFAnnotationSquare() {
/*  47 */     this.annot.setName(COSName.SUBTYPE, "Square");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationSquare(COSDictionary a) {
/*  57 */     super(a);
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
/*     */   public FDFAnnotationSquare(Element element) throws IOException {
/*  69 */     super(element);
/*  70 */     this.annot.setName(COSName.SUBTYPE, "Square");
/*     */     
/*  72 */     String color = element.getAttribute("interior-color");
/*  73 */     if (color != null && color.length() == 7 && color.charAt(0) == '#') {
/*     */       
/*  75 */       int colorValue = Integer.parseInt(color.substring(1, 7), 16);
/*  76 */       setInteriorColor(new Color(colorValue));
/*     */     } 
/*     */     
/*  79 */     initFringe(element);
/*     */   }
/*     */ 
/*     */   
/*     */   private void initFringe(Element element) throws IOException {
/*  84 */     String fringe = element.getAttribute("fringe");
/*  85 */     if (fringe != null && !fringe.isEmpty()) {
/*     */       
/*  87 */       String[] fringeValues = fringe.split(",");
/*  88 */       if (fringeValues.length != 4)
/*     */       {
/*  90 */         throw new IOException("Error: wrong amount of numbers in attribute 'fringe'");
/*     */       }
/*  92 */       PDRectangle rect = new PDRectangle();
/*  93 */       rect.setLowerLeftX(Float.parseFloat(fringeValues[0]));
/*  94 */       rect.setLowerLeftY(Float.parseFloat(fringeValues[1]));
/*  95 */       rect.setUpperRightX(Float.parseFloat(fringeValues[2]));
/*  96 */       rect.setUpperRightY(Float.parseFloat(fringeValues[3]));
/*  97 */       setFringe(rect);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setInteriorColor(Color color) {
/* 108 */     COSArray array = null;
/* 109 */     if (color != null) {
/*     */       
/* 111 */       float[] colors = color.getRGBColorComponents(null);
/* 112 */       array = new COSArray();
/* 113 */       array.setFloatArray(colors);
/*     */     } 
/* 115 */     this.annot.setItem(COSName.IC, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getInteriorColor() {
/* 125 */     Color retval = null;
/* 126 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.IC);
/* 127 */     if (array != null) {
/*     */       
/* 129 */       float[] rgb = array.toFloatArray();
/* 130 */       if (rgb.length >= 3)
/*     */       {
/* 132 */         retval = new Color(rgb[0], rgb[1], rgb[2]);
/*     */       }
/*     */     } 
/* 135 */     return retval;
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
/* 146 */     this.annot.setItem(COSName.RD, (COSObjectable)fringe);
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
/* 157 */     COSArray rd = (COSArray)this.annot.getDictionaryObject(COSName.RD);
/* 158 */     if (rd != null)
/*     */     {
/* 160 */       return new PDRectangle(rd);
/*     */     }
/*     */ 
/*     */     
/* 164 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationSquare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */