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
/*     */ public class FDFAnnotationCircle
/*     */   extends FDFAnnotation
/*     */ {
/*     */   public static final String SUBTYPE = "Circle";
/*     */   
/*     */   public FDFAnnotationCircle() {
/*  46 */     this.annot.setName(COSName.SUBTYPE, "Circle");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDFAnnotationCircle(COSDictionary a) {
/*  56 */     super(a);
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
/*     */   public FDFAnnotationCircle(Element element) throws IOException {
/*  68 */     super(element);
/*  69 */     this.annot.setName(COSName.SUBTYPE, "Circle");
/*     */     
/*  71 */     String color = element.getAttribute("interior-color");
/*  72 */     if (color != null && color.length() == 7 && color.charAt(0) == '#') {
/*     */       
/*  74 */       int colorValue = Integer.parseInt(color.substring(1, 7), 16);
/*  75 */       setInteriorColor(new Color(colorValue));
/*     */     } 
/*     */     
/*  78 */     initFringe(element);
/*     */   }
/*     */ 
/*     */   
/*     */   private void initFringe(Element element) throws IOException {
/*  83 */     String fringe = element.getAttribute("fringe");
/*  84 */     if (fringe != null && !fringe.isEmpty()) {
/*     */       
/*  86 */       String[] fringeValues = fringe.split(",");
/*  87 */       if (fringeValues.length != 4)
/*     */       {
/*  89 */         throw new IOException("Error: wrong amount of numbers in attribute 'fringe'");
/*     */       }
/*  91 */       PDRectangle rect = new PDRectangle();
/*  92 */       rect.setLowerLeftX(Float.parseFloat(fringeValues[0]));
/*  93 */       rect.setLowerLeftY(Float.parseFloat(fringeValues[1]));
/*  94 */       rect.setUpperRightX(Float.parseFloat(fringeValues[2]));
/*  95 */       rect.setUpperRightY(Float.parseFloat(fringeValues[3]));
/*  96 */       setFringe(rect);
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
/* 107 */     COSArray array = null;
/* 108 */     if (color != null) {
/*     */       
/* 110 */       float[] colors = color.getRGBColorComponents(null);
/* 111 */       array = new COSArray();
/* 112 */       array.setFloatArray(colors);
/*     */     } 
/* 114 */     this.annot.setItem(COSName.IC, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getInteriorColor() {
/* 124 */     Color retval = null;
/* 125 */     COSArray array = (COSArray)this.annot.getDictionaryObject(COSName.IC);
/* 126 */     if (array != null) {
/*     */       
/* 128 */       float[] rgb = array.toFloatArray();
/* 129 */       if (rgb.length >= 3)
/*     */       {
/* 131 */         retval = new Color(rgb[0], rgb[1], rgb[2]);
/*     */       }
/*     */     } 
/* 134 */     return retval;
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
/* 145 */     this.annot.setItem(COSName.RD, (COSObjectable)fringe);
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
/* 156 */     COSArray rd = (COSArray)this.annot.getDictionaryObject(COSName.RD);
/* 157 */     if (rd != null)
/*     */     {
/* 159 */       return new PDRectangle(rd);
/*     */     }
/*     */ 
/*     */     
/* 163 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/fdf/FDFAnnotationCircle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */